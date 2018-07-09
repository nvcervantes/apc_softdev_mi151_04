package okhttp3.internal.cache;

import java.io.Closeable;
import java.io.EOFException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.Flushable;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.NoSuchElementException;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.io.FileSystem;
import okhttp3.internal.platform.Platform;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;
import okio.Sink;
import okio.Source;

public final class DiskLruCache
  implements Closeable, Flushable
{
  static final long ANY_SEQUENCE_NUMBER = -1L;
  private static final String CLEAN = "CLEAN";
  private static final String DIRTY = "DIRTY";
  static final String JOURNAL_FILE = "journal";
  static final String JOURNAL_FILE_BACKUP = "journal.bkp";
  static final String JOURNAL_FILE_TEMP = "journal.tmp";
  static final Pattern LEGAL_KEY_PATTERN = Pattern.compile("[a-z0-9_-]{1,120}");
  static final String MAGIC = "libcore.io.DiskLruCache";
  private static final String READ = "READ";
  private static final String REMOVE = "REMOVE";
  static final String VERSION_1 = "1";
  private final int appVersion;
  private final Runnable cleanupRunnable = new Runnable()
  {
    public void run()
    {
      synchronized (DiskLruCache.this)
      {
        if ((initialized ^ true | closed)) {
          return;
        }
        try
        {
          trimToSize();
        }
        catch (IOException localIOException1)
        {
          for (;;) {}
        }
        mostRecentTrimFailed = true;
        try
        {
          if (!journalRebuildRequired()) {
            break label97;
          }
          rebuildJournal();
          redundantOpCount = 0;
        }
        catch (IOException localIOException2)
        {
          label97:
          for (;;) {}
        }
        mostRecentRebuildFailed = true;
        journalWriter = Okio.buffer(Okio.blackhole());
        return;
      }
    }
  };
  boolean closed;
  final File directory;
  private final Executor executor;
  final FileSystem fileSystem;
  boolean hasJournalErrors;
  boolean initialized;
  private final File journalFile;
  private final File journalFileBackup;
  private final File journalFileTmp;
  BufferedSink journalWriter;
  final LinkedHashMap<String, Entry> lruEntries = new LinkedHashMap(0, 0.75F, true);
  private long maxSize;
  boolean mostRecentRebuildFailed;
  boolean mostRecentTrimFailed;
  private long nextSequenceNumber = 0L;
  int redundantOpCount;
  private long size = 0L;
  final int valueCount;
  
  DiskLruCache(FileSystem paramFileSystem, File paramFile, int paramInt1, int paramInt2, long paramLong, Executor paramExecutor)
  {
    fileSystem = paramFileSystem;
    directory = paramFile;
    appVersion = paramInt1;
    journalFile = new File(paramFile, "journal");
    journalFileTmp = new File(paramFile, "journal.tmp");
    journalFileBackup = new File(paramFile, "journal.bkp");
    valueCount = paramInt2;
    maxSize = paramLong;
    executor = paramExecutor;
  }
  
  private void checkNotClosed()
  {
    try
    {
      if (isClosed()) {
        throw new IllegalStateException("cache is closed");
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public static DiskLruCache create(FileSystem paramFileSystem, File paramFile, int paramInt1, int paramInt2, long paramLong)
  {
    if (paramLong <= 0L) {
      throw new IllegalArgumentException("maxSize <= 0");
    }
    if (paramInt2 <= 0) {
      throw new IllegalArgumentException("valueCount <= 0");
    }
    return new DiskLruCache(paramFileSystem, paramFile, paramInt1, paramInt2, paramLong, new ThreadPoolExecutor(0, 1, 60L, TimeUnit.SECONDS, new LinkedBlockingQueue(), Util.threadFactory("OkHttp DiskLruCache", true)));
  }
  
  private BufferedSink newJournalWriter()
    throws FileNotFoundException
  {
    Okio.buffer(new FaultHidingSink(fileSystem.appendingSink(journalFile))
    {
      protected void onException(IOException paramAnonymousIOException)
      {
        hasJournalErrors = true;
      }
    });
  }
  
  private void processJournal()
    throws IOException
  {
    fileSystem.delete(journalFileTmp);
    Iterator localIterator = lruEntries.values().iterator();
    while (localIterator.hasNext())
    {
      Entry localEntry = (Entry)localIterator.next();
      Editor localEditor = currentEditor;
      int j = 0;
      int i = 0;
      if (localEditor == null)
      {
        while (i < valueCount)
        {
          size += lengths[i];
          i += 1;
        }
      }
      else
      {
        currentEditor = null;
        i = j;
        while (i < valueCount)
        {
          fileSystem.delete(cleanFiles[i]);
          fileSystem.delete(dirtyFiles[i]);
          i += 1;
        }
        localIterator.remove();
      }
    }
  }
  
  private void readJournal()
    throws IOException
  {
    localBufferedSource = Okio.buffer(fileSystem.source(journalFile));
    for (;;)
    {
      try
      {
        str1 = localBufferedSource.readUtf8LineStrict();
        str2 = localBufferedSource.readUtf8LineStrict();
        localObject2 = localBufferedSource.readUtf8LineStrict();
        str3 = localBufferedSource.readUtf8LineStrict();
        str4 = localBufferedSource.readUtf8LineStrict();
        if (("libcore.io.DiskLruCache".equals(str1)) && ("1".equals(str2)) && (Integer.toString(appVersion).equals(localObject2)) && (Integer.toString(valueCount).equals(str3)))
        {
          boolean bool = "".equals(str4);
          if (bool) {
            i = 0;
          }
        }
      }
      finally
      {
        String str1;
        String str2;
        Object localObject2;
        String str3;
        String str4;
        int i;
        Util.closeQuietly(localBufferedSource);
      }
      try
      {
        readJournalLine(localBufferedSource.readUtf8LineStrict());
        i += 1;
      }
      catch (EOFException localEOFException) {}
    }
    redundantOpCount = (i - lruEntries.size());
    if (!localBufferedSource.exhausted()) {
      rebuildJournal();
    } else {
      journalWriter = newJournalWriter();
    }
    Util.closeQuietly(localBufferedSource);
    return;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("unexpected journal header: [");
    ((StringBuilder)localObject2).append(str1);
    ((StringBuilder)localObject2).append(", ");
    ((StringBuilder)localObject2).append(str2);
    ((StringBuilder)localObject2).append(", ");
    ((StringBuilder)localObject2).append(str3);
    ((StringBuilder)localObject2).append(", ");
    ((StringBuilder)localObject2).append(str4);
    ((StringBuilder)localObject2).append("]");
    throw new IOException(((StringBuilder)localObject2).toString());
  }
  
  private void readJournalLine(String paramString)
    throws IOException
  {
    int i = paramString.indexOf(' ');
    if (i == -1)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("unexpected journal line: ");
      ((StringBuilder)localObject1).append(paramString);
      throw new IOException(((StringBuilder)localObject1).toString());
    }
    int j = i + 1;
    int k = paramString.indexOf(' ', j);
    if (k == -1)
    {
      localObject2 = paramString.substring(j);
      localObject1 = localObject2;
      if (i == "REMOVE".length())
      {
        localObject1 = localObject2;
        if (paramString.startsWith("REMOVE")) {
          lruEntries.remove(localObject2);
        }
      }
    }
    else
    {
      localObject1 = paramString.substring(j, k);
    }
    Entry localEntry = (Entry)lruEntries.get(localObject1);
    Object localObject2 = localEntry;
    if (localEntry == null)
    {
      localObject2 = new Entry((String)localObject1);
      lruEntries.put(localObject1, localObject2);
    }
    if ((k != -1) && (i == "CLEAN".length()) && (paramString.startsWith("CLEAN")))
    {
      paramString = paramString.substring(k + 1).split(" ");
      readable = true;
      currentEditor = null;
      ((Entry)localObject2).setLengths(paramString);
      return;
    }
    if ((k == -1) && (i == "DIRTY".length()) && (paramString.startsWith("DIRTY")))
    {
      currentEditor = new Editor((Entry)localObject2);
      return;
    }
    if ((k == -1) && (i == "READ".length()) && (paramString.startsWith("READ"))) {
      return;
    }
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("unexpected journal line: ");
    ((StringBuilder)localObject1).append(paramString);
    throw new IOException(((StringBuilder)localObject1).toString());
  }
  
  private void validateKey(String paramString)
  {
    if (!LEGAL_KEY_PATTERN.matcher(paramString).matches())
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("keys must match regex [a-z0-9_-]{1,120}: \"");
      localStringBuilder.append(paramString);
      localStringBuilder.append("\"");
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
  }
  
  public void close()
    throws IOException
  {
    for (;;)
    {
      int i;
      try
      {
        if ((initialized) && (!closed))
        {
          Entry[] arrayOfEntry = (Entry[])lruEntries.values().toArray(new Entry[lruEntries.size()]);
          int j = arrayOfEntry.length;
          i = 0;
          if (i < j)
          {
            Entry localEntry = arrayOfEntry[i];
            if (currentEditor != null) {
              currentEditor.abort();
            }
          }
          else
          {
            trimToSize();
            journalWriter.close();
            journalWriter = null;
            closed = true;
          }
        }
        else
        {
          closed = true;
          return;
        }
      }
      finally {}
      i += 1;
    }
  }
  
  void completeEdit(Editor paramEditor, boolean paramBoolean)
    throws IOException
  {
    for (;;)
    {
      int j;
      try
      {
        Entry localEntry = entry;
        if (currentEditor != paramEditor) {
          throw new IllegalStateException();
        }
        int k = 0;
        j = k;
        if (paramBoolean)
        {
          j = k;
          if (!readable)
          {
            int i = 0;
            j = k;
            if (i < valueCount)
            {
              if (written[i] == 0)
              {
                paramEditor.abort();
                paramEditor = new StringBuilder();
                paramEditor.append("Newly created entry didn't create value for index ");
                paramEditor.append(i);
                throw new IllegalStateException(paramEditor.toString());
              }
              if (!fileSystem.exists(dirtyFiles[i]))
              {
                paramEditor.abort();
                return;
              }
              i += 1;
              continue;
            }
          }
        }
        long l1;
        if (j < valueCount)
        {
          paramEditor = dirtyFiles[j];
          if (paramBoolean)
          {
            if (fileSystem.exists(paramEditor))
            {
              File localFile = cleanFiles[j];
              fileSystem.rename(paramEditor, localFile);
              l1 = lengths[j];
              long l2 = fileSystem.size(localFile);
              lengths[j] = l2;
              size = (size - l1 + l2);
            }
          }
          else {
            fileSystem.delete(paramEditor);
          }
        }
        else
        {
          redundantOpCount += 1;
          currentEditor = null;
          if ((readable | paramBoolean))
          {
            readable = true;
            journalWriter.writeUtf8("CLEAN").writeByte(32);
            journalWriter.writeUtf8(key);
            localEntry.writeLengths(journalWriter);
            journalWriter.writeByte(10);
            if (paramBoolean)
            {
              l1 = nextSequenceNumber;
              nextSequenceNumber = (l1 + 1L);
              sequenceNumber = l1;
            }
          }
          else
          {
            lruEntries.remove(key);
            journalWriter.writeUtf8("REMOVE").writeByte(32);
            journalWriter.writeUtf8(key);
            journalWriter.writeByte(10);
          }
          journalWriter.flush();
          if ((size > maxSize) || (journalRebuildRequired())) {
            executor.execute(cleanupRunnable);
          }
          return;
        }
      }
      finally {}
      j += 1;
    }
  }
  
  public void delete()
    throws IOException
  {
    close();
    fileSystem.deleteContents(directory);
  }
  
  @Nullable
  public Editor edit(String paramString)
    throws IOException
  {
    return edit(paramString, -1L);
  }
  
  Editor edit(String paramString, long paramLong)
    throws IOException
  {
    try
    {
      initialize();
      checkNotClosed();
      validateKey(paramString);
      Entry localEntry = (Entry)lruEntries.get(paramString);
      if (paramLong != -1L) {
        if (localEntry != null)
        {
          long l = sequenceNumber;
          if (l == paramLong) {}
        }
        else
        {
          return null;
        }
      }
      Object localObject;
      if (localEntry != null)
      {
        localObject = currentEditor;
        if (localObject != null) {
          return null;
        }
      }
      if ((!mostRecentTrimFailed) && (!mostRecentRebuildFailed))
      {
        journalWriter.writeUtf8("DIRTY").writeByte(32).writeUtf8(paramString).writeByte(10);
        journalWriter.flush();
        boolean bool = hasJournalErrors;
        if (bool) {
          return null;
        }
        localObject = localEntry;
        if (localEntry == null)
        {
          localObject = new Entry(paramString);
          lruEntries.put(paramString, localObject);
        }
        paramString = new Editor((Entry)localObject);
        currentEditor = paramString;
        return paramString;
      }
      executor.execute(cleanupRunnable);
      return null;
    }
    finally {}
  }
  
  public void evictAll()
    throws IOException
  {
    try
    {
      initialize();
      Entry[] arrayOfEntry = (Entry[])lruEntries.values().toArray(new Entry[lruEntries.size()]);
      int j = arrayOfEntry.length;
      int i = 0;
      while (i < j)
      {
        removeEntry(arrayOfEntry[i]);
        i += 1;
      }
      mostRecentTrimFailed = false;
      return;
    }
    finally {}
  }
  
  public void flush()
    throws IOException
  {
    try
    {
      boolean bool = initialized;
      if (!bool) {
        return;
      }
      checkNotClosed();
      trimToSize();
      journalWriter.flush();
      return;
    }
    finally {}
  }
  
  public Snapshot get(String paramString)
    throws IOException
  {
    try
    {
      initialize();
      checkNotClosed();
      validateKey(paramString);
      Object localObject = (Entry)lruEntries.get(paramString);
      if ((localObject != null) && (readable))
      {
        localObject = ((Entry)localObject).snapshot();
        if (localObject == null) {
          return null;
        }
        redundantOpCount += 1;
        journalWriter.writeUtf8("READ").writeByte(32).writeUtf8(paramString).writeByte(10);
        if (journalRebuildRequired()) {
          executor.execute(cleanupRunnable);
        }
        return localObject;
      }
      return null;
    }
    finally {}
  }
  
  public File getDirectory()
  {
    return directory;
  }
  
  public long getMaxSize()
  {
    try
    {
      long l = maxSize;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void initialize()
    throws IOException
  {
    try
    {
      boolean bool = initialized;
      if (bool) {
        return;
      }
      if (fileSystem.exists(journalFileBackup)) {
        if (fileSystem.exists(journalFile)) {
          fileSystem.delete(journalFileBackup);
        } else {
          fileSystem.rename(journalFileBackup, journalFile);
        }
      }
      bool = fileSystem.exists(journalFile);
      if (bool) {
        try
        {
          readJournal();
          processJournal();
          initialized = true;
          return;
        }
        catch (IOException localIOException)
        {
          Platform localPlatform = Platform.get();
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append("DiskLruCache ");
          localStringBuilder.append(directory);
          localStringBuilder.append(" is corrupt: ");
          localStringBuilder.append(localIOException.getMessage());
          localStringBuilder.append(", removing");
          localPlatform.log(5, localStringBuilder.toString(), localIOException);
        }
      }
      try
      {
        delete();
        closed = false;
      }
      finally
      {
        closed = false;
      }
      initialized = true;
      return;
    }
    finally {}
  }
  
  public boolean isClosed()
  {
    try
    {
      boolean bool = closed;
      return bool;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  boolean journalRebuildRequired()
  {
    return (redundantOpCount >= 2000) && (redundantOpCount >= lruEntries.size());
  }
  
  /* Error */
  void rebuildJournal()
    throws IOException
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 302	okhttp3/internal/cache/DiskLruCache:journalWriter	Lokio/BufferedSink;
    //   6: ifnull +12 -> 18
    //   9: aload_0
    //   10: getfield 302	okhttp3/internal/cache/DiskLruCache:journalWriter	Lokio/BufferedSink;
    //   13: invokeinterface 408 1 0
    //   18: aload_0
    //   19: getfield 122	okhttp3/internal/cache/DiskLruCache:fileSystem	Lokhttp3/internal/io/FileSystem;
    //   22: aload_0
    //   23: getfield 135	okhttp3/internal/cache/DiskLruCache:journalFileTmp	Ljava/io/File;
    //   26: invokeinterface 533 2 0
    //   31: invokestatic 209	okio/Okio:buffer	(Lokio/Sink;)Lokio/BufferedSink;
    //   34: astore_1
    //   35: aload_1
    //   36: ldc 51
    //   38: invokeinterface 439 2 0
    //   43: bipush 10
    //   45: invokeinterface 443 2 0
    //   50: pop
    //   51: aload_1
    //   52: ldc 58
    //   54: invokeinterface 439 2 0
    //   59: bipush 10
    //   61: invokeinterface 443 2 0
    //   66: pop
    //   67: aload_1
    //   68: aload_0
    //   69: getfield 126	okhttp3/internal/cache/DiskLruCache:appVersion	I
    //   72: i2l
    //   73: invokeinterface 537 3 0
    //   78: bipush 10
    //   80: invokeinterface 443 2 0
    //   85: pop
    //   86: aload_1
    //   87: aload_0
    //   88: getfield 139	okhttp3/internal/cache/DiskLruCache:valueCount	I
    //   91: i2l
    //   92: invokeinterface 537 3 0
    //   97: bipush 10
    //   99: invokeinterface 443 2 0
    //   104: pop
    //   105: aload_1
    //   106: bipush 10
    //   108: invokeinterface 443 2 0
    //   113: pop
    //   114: aload_0
    //   115: getfield 113	okhttp3/internal/cache/DiskLruCache:lruEntries	Ljava/util/LinkedHashMap;
    //   118: invokevirtual 221	java/util/LinkedHashMap:values	()Ljava/util/Collection;
    //   121: invokeinterface 227 1 0
    //   126: astore_2
    //   127: aload_2
    //   128: invokeinterface 232 1 0
    //   133: ifeq +103 -> 236
    //   136: aload_2
    //   137: invokeinterface 236 1 0
    //   142: checkcast 21	okhttp3/internal/cache/DiskLruCache$Entry
    //   145: astore_3
    //   146: aload_3
    //   147: getfield 240	okhttp3/internal/cache/DiskLruCache$Entry:currentEditor	Lokhttp3/internal/cache/DiskLruCache$Editor;
    //   150: ifnull +42 -> 192
    //   153: aload_1
    //   154: ldc 37
    //   156: invokeinterface 439 2 0
    //   161: bipush 32
    //   163: invokeinterface 443 2 0
    //   168: pop
    //   169: aload_1
    //   170: aload_3
    //   171: getfield 446	okhttp3/internal/cache/DiskLruCache$Entry:key	Ljava/lang/String;
    //   174: invokeinterface 439 2 0
    //   179: pop
    //   180: aload_1
    //   181: bipush 10
    //   183: invokeinterface 443 2 0
    //   188: pop
    //   189: goto -62 -> 127
    //   192: aload_1
    //   193: ldc 35
    //   195: invokeinterface 439 2 0
    //   200: bipush 32
    //   202: invokeinterface 443 2 0
    //   207: pop
    //   208: aload_1
    //   209: aload_3
    //   210: getfield 446	okhttp3/internal/cache/DiskLruCache$Entry:key	Ljava/lang/String;
    //   213: invokeinterface 439 2 0
    //   218: pop
    //   219: aload_3
    //   220: aload_1
    //   221: invokevirtual 450	okhttp3/internal/cache/DiskLruCache$Entry:writeLengths	(Lokio/BufferedSink;)V
    //   224: aload_1
    //   225: bipush 10
    //   227: invokeinterface 443 2 0
    //   232: pop
    //   233: goto -106 -> 127
    //   236: aload_1
    //   237: invokeinterface 408 1 0
    //   242: aload_0
    //   243: getfield 122	okhttp3/internal/cache/DiskLruCache:fileSystem	Lokhttp3/internal/io/FileSystem;
    //   246: aload_0
    //   247: getfield 133	okhttp3/internal/cache/DiskLruCache:journalFile	Ljava/io/File;
    //   250: invokeinterface 428 2 0
    //   255: ifeq +20 -> 275
    //   258: aload_0
    //   259: getfield 122	okhttp3/internal/cache/DiskLruCache:fileSystem	Lokhttp3/internal/io/FileSystem;
    //   262: aload_0
    //   263: getfield 133	okhttp3/internal/cache/DiskLruCache:journalFile	Ljava/io/File;
    //   266: aload_0
    //   267: getfield 137	okhttp3/internal/cache/DiskLruCache:journalFileBackup	Ljava/io/File;
    //   270: invokeinterface 432 3 0
    //   275: aload_0
    //   276: getfield 122	okhttp3/internal/cache/DiskLruCache:fileSystem	Lokhttp3/internal/io/FileSystem;
    //   279: aload_0
    //   280: getfield 135	okhttp3/internal/cache/DiskLruCache:journalFileTmp	Ljava/io/File;
    //   283: aload_0
    //   284: getfield 133	okhttp3/internal/cache/DiskLruCache:journalFile	Ljava/io/File;
    //   287: invokeinterface 432 3 0
    //   292: aload_0
    //   293: getfield 122	okhttp3/internal/cache/DiskLruCache:fileSystem	Lokhttp3/internal/io/FileSystem;
    //   296: aload_0
    //   297: getfield 137	okhttp3/internal/cache/DiskLruCache:journalFileBackup	Ljava/io/File;
    //   300: invokeinterface 217 2 0
    //   305: aload_0
    //   306: aload_0
    //   307: invokespecial 300	okhttp3/internal/cache/DiskLruCache:newJournalWriter	()Lokio/BufferedSink;
    //   310: putfield 302	okhttp3/internal/cache/DiskLruCache:journalWriter	Lokio/BufferedSink;
    //   313: aload_0
    //   314: iconst_0
    //   315: putfield 489	okhttp3/internal/cache/DiskLruCache:hasJournalErrors	Z
    //   318: aload_0
    //   319: iconst_0
    //   320: putfield 487	okhttp3/internal/cache/DiskLruCache:mostRecentRebuildFailed	Z
    //   323: aload_0
    //   324: monitorexit
    //   325: return
    //   326: astore_2
    //   327: aload_1
    //   328: invokeinterface 408 1 0
    //   333: aload_2
    //   334: athrow
    //   335: astore_1
    //   336: aload_0
    //   337: monitorexit
    //   338: aload_1
    //   339: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	340	0	this	DiskLruCache
    //   34	294	1	localBufferedSink	BufferedSink
    //   335	4	1	localObject1	Object
    //   126	11	2	localIterator	Iterator
    //   326	8	2	localObject2	Object
    //   145	75	3	localEntry	Entry
    // Exception table:
    //   from	to	target	type
    //   35	127	326	finally
    //   127	189	326	finally
    //   192	233	326	finally
    //   2	18	335	finally
    //   18	35	335	finally
    //   236	275	335	finally
    //   275	323	335	finally
    //   327	335	335	finally
  }
  
  public boolean remove(String paramString)
    throws IOException
  {
    try
    {
      initialize();
      checkNotClosed();
      validateKey(paramString);
      paramString = (Entry)lruEntries.get(paramString);
      if (paramString == null) {
        return false;
      }
      boolean bool = removeEntry(paramString);
      if ((bool) && (size <= maxSize)) {
        mostRecentTrimFailed = false;
      }
      return bool;
    }
    finally {}
  }
  
  boolean removeEntry(Entry paramEntry)
    throws IOException
  {
    if (currentEditor != null) {
      currentEditor.detach();
    }
    int i = 0;
    while (i < valueCount)
    {
      fileSystem.delete(cleanFiles[i]);
      size -= lengths[i];
      lengths[i] = 0L;
      i += 1;
    }
    redundantOpCount += 1;
    journalWriter.writeUtf8("REMOVE").writeByte(32).writeUtf8(key).writeByte(10);
    lruEntries.remove(key);
    if (journalRebuildRequired()) {
      executor.execute(cleanupRunnable);
    }
    return true;
  }
  
  public void setMaxSize(long paramLong)
  {
    try
    {
      maxSize = paramLong;
      if (initialized) {
        executor.execute(cleanupRunnable);
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public long size()
    throws IOException
  {
    try
    {
      initialize();
      long l = size;
      return l;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public Iterator<Snapshot> snapshots()
    throws IOException
  {
    try
    {
      initialize();
      Iterator local3 = new Iterator()
      {
        final Iterator<DiskLruCache.Entry> delegate = new ArrayList(lruEntries.values()).iterator();
        DiskLruCache.Snapshot nextSnapshot;
        DiskLruCache.Snapshot removeSnapshot;
        
        public boolean hasNext()
        {
          if (nextSnapshot != null) {
            return true;
          }
          synchronized (DiskLruCache.this)
          {
            if (closed) {
              return false;
            }
            while (delegate.hasNext())
            {
              DiskLruCache.Snapshot localSnapshot = ((DiskLruCache.Entry)delegate.next()).snapshot();
              if (localSnapshot != null)
              {
                nextSnapshot = localSnapshot;
                return true;
              }
            }
            return false;
          }
        }
        
        public DiskLruCache.Snapshot next()
        {
          if (!hasNext()) {
            throw new NoSuchElementException();
          }
          removeSnapshot = nextSnapshot;
          nextSnapshot = null;
          return removeSnapshot;
        }
        
        /* Error */
        public void remove()
        {
          // Byte code:
          //   0: aload_0
          //   1: getfield 76	okhttp3/internal/cache/DiskLruCache$3:removeSnapshot	Lokhttp3/internal/cache/DiskLruCache$Snapshot;
          //   4: ifnonnull +13 -> 17
          //   7: new 81	java/lang/IllegalStateException
          //   10: dup
          //   11: ldc 83
          //   13: invokespecial 86	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
          //   16: athrow
          //   17: aload_0
          //   18: getfield 24	okhttp3/internal/cache/DiskLruCache$3:this$0	Lokhttp3/internal/cache/DiskLruCache;
          //   21: aload_0
          //   22: getfield 76	okhttp3/internal/cache/DiskLruCache$3:removeSnapshot	Lokhttp3/internal/cache/DiskLruCache$Snapshot;
          //   25: invokestatic 92	okhttp3/internal/cache/DiskLruCache$Snapshot:access$000	(Lokhttp3/internal/cache/DiskLruCache$Snapshot;)Ljava/lang/String;
          //   28: invokevirtual 95	okhttp3/internal/cache/DiskLruCache:remove	(Ljava/lang/String;)Z
          //   31: pop
          //   32: goto +11 -> 43
          //   35: astore_1
          //   36: aload_0
          //   37: aconst_null
          //   38: putfield 76	okhttp3/internal/cache/DiskLruCache$3:removeSnapshot	Lokhttp3/internal/cache/DiskLruCache$Snapshot;
          //   41: aload_1
          //   42: athrow
          //   43: aload_0
          //   44: aconst_null
          //   45: putfield 76	okhttp3/internal/cache/DiskLruCache$3:removeSnapshot	Lokhttp3/internal/cache/DiskLruCache$Snapshot;
          //   48: return
          //   49: astore_1
          //   50: goto -7 -> 43
          // Local variable table:
          //   start	length	slot	name	signature
          //   0	53	0	this	3
          //   35	7	1	localObject	Object
          //   49	1	1	localIOException	IOException
          // Exception table:
          //   from	to	target	type
          //   17	32	35	finally
          //   17	32	49	java/io/IOException
        }
      };
      return local3;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  void trimToSize()
    throws IOException
  {
    while (size > maxSize) {
      removeEntry((Entry)lruEntries.values().iterator().next());
    }
    mostRecentTrimFailed = false;
  }
  
  public final class Editor
  {
    private boolean done;
    final DiskLruCache.Entry entry;
    final boolean[] written;
    
    Editor(DiskLruCache.Entry paramEntry)
    {
      entry = paramEntry;
      if (readable) {
        this$1 = null;
      } else {
        this$1 = new boolean[valueCount];
      }
      written = DiskLruCache.this;
    }
    
    public void abort()
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (done) {
          throw new IllegalStateException();
        }
        if (entry.currentEditor == this) {
          completeEdit(this, false);
        }
        done = true;
        return;
      }
    }
    
    public void abortUnlessCommitted()
    {
      synchronized (DiskLruCache.this)
      {
        if (!done)
        {
          Editor localEditor = entry.currentEditor;
          if (localEditor != this) {}
        }
      }
      try
      {
        completeEdit(this, false);
        return;
        localObject = finally;
        throw localObject;
      }
      catch (IOException localIOException)
      {
        for (;;) {}
      }
    }
    
    public void commit()
      throws IOException
    {
      synchronized (DiskLruCache.this)
      {
        if (done) {
          throw new IllegalStateException();
        }
        if (entry.currentEditor == this) {
          completeEdit(this, true);
        }
        done = true;
        return;
      }
    }
    
    void detach()
    {
      int i;
      if (entry.currentEditor == this) {
        i = 0;
      }
      for (;;)
      {
        if (i < valueCount) {}
        try
        {
          fileSystem.delete(entry.dirtyFiles[i]);
          i += 1;
          continue;
          entry.currentEditor = null;
          return;
        }
        catch (IOException localIOException)
        {
          for (;;) {}
        }
      }
    }
    
    public Sink newSink(int paramInt)
    {
      synchronized (DiskLruCache.this)
      {
        if (done) {
          throw new IllegalStateException();
        }
        if (entry.currentEditor != this)
        {
          localObject1 = Okio.blackhole();
          return localObject1;
        }
        if (!entry.readable) {
          written[paramInt] = true;
        }
        Object localObject1 = entry.dirtyFiles[paramInt];
        try
        {
          localObject1 = fileSystem.sink((File)localObject1);
          localObject1 = new FaultHidingSink((Sink)localObject1)
          {
            protected void onException(IOException arg1)
            {
              synchronized (DiskLruCache.this)
              {
                detach();
                return;
              }
            }
          };
          return localObject1;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          for (;;) {}
        }
        localObject1 = Okio.blackhole();
        return localObject1;
      }
    }
    
    public Source newSource(int paramInt)
    {
      synchronized (DiskLruCache.this)
      {
        if (done) {
          throw new IllegalStateException();
        }
        Object localObject1;
        if (entry.readable)
        {
          localObject1 = entry.currentEditor;
          if (localObject1 != this) {}
        }
        try
        {
          localObject1 = fileSystem.source(entry.cleanFiles[paramInt]);
          return localObject1;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          for (;;) {}
        }
        return null;
        return null;
      }
    }
  }
  
  private final class Entry
  {
    final File[] cleanFiles;
    DiskLruCache.Editor currentEditor;
    final File[] dirtyFiles;
    final String key;
    final long[] lengths;
    boolean readable;
    long sequenceNumber;
    
    Entry(String paramString)
    {
      key = paramString;
      lengths = new long[valueCount];
      cleanFiles = new File[valueCount];
      dirtyFiles = new File[valueCount];
      paramString = new StringBuilder(paramString);
      paramString.append('.');
      int j = paramString.length();
      int i = 0;
      while (i < valueCount)
      {
        paramString.append(i);
        cleanFiles[i] = new File(directory, paramString.toString());
        paramString.append(".tmp");
        dirtyFiles[i] = new File(directory, paramString.toString());
        paramString.setLength(j);
        i += 1;
      }
    }
    
    private IOException invalidLengths(String[] paramArrayOfString)
      throws IOException
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("unexpected journal line: ");
      localStringBuilder.append(Arrays.toString(paramArrayOfString));
      throw new IOException(localStringBuilder.toString());
    }
    
    void setLengths(String[] paramArrayOfString)
      throws IOException
    {
      if (paramArrayOfString.length != valueCount) {
        throw invalidLengths(paramArrayOfString);
      }
      int i = 0;
      try
      {
        while (i < paramArrayOfString.length)
        {
          lengths[i] = Long.parseLong(paramArrayOfString[i]);
          i += 1;
        }
        return;
      }
      catch (NumberFormatException localNumberFormatException)
      {
        for (;;) {}
      }
      throw invalidLengths(paramArrayOfString);
    }
    
    DiskLruCache.Snapshot snapshot()
    {
      if (!Thread.holdsLock(DiskLruCache.this)) {
        throw new AssertionError();
      }
      Source[] arrayOfSource = new Source[valueCount];
      Object localObject = (long[])lengths.clone();
      j = 0;
      i = 0;
      for (;;)
      {
        try
        {
          if (i < valueCount)
          {
            arrayOfSource[i] = fileSystem.source(cleanFiles[i]);
            i += 1;
            continue;
          }
          localObject = new DiskLruCache.Snapshot(DiskLruCache.this, key, sequenceNumber, arrayOfSource, (long[])localObject);
          return localObject;
        }
        catch (FileNotFoundException localFileNotFoundException)
        {
          try
          {
            removeEntry(this);
            return null;
            localFileNotFoundException = localFileNotFoundException;
            i = j;
          }
          catch (IOException localIOException)
          {
            continue;
          }
        }
        if ((i >= valueCount) || (arrayOfSource[i] == null)) {
          continue;
        }
        Util.closeQuietly(arrayOfSource[i]);
        i += 1;
      }
    }
    
    void writeLengths(BufferedSink paramBufferedSink)
      throws IOException
    {
      long[] arrayOfLong = lengths;
      int i = 0;
      int j = arrayOfLong.length;
      while (i < j)
      {
        long l = arrayOfLong[i];
        paramBufferedSink.writeByte(32).writeDecimalLong(l);
        i += 1;
      }
    }
  }
  
  public final class Snapshot
    implements Closeable
  {
    private final String key;
    private final long[] lengths;
    private final long sequenceNumber;
    private final Source[] sources;
    
    Snapshot(String paramString, long paramLong, Source[] paramArrayOfSource, long[] paramArrayOfLong)
    {
      key = paramString;
      sequenceNumber = paramLong;
      sources = paramArrayOfSource;
      lengths = paramArrayOfLong;
    }
    
    public void close()
    {
      Source[] arrayOfSource = sources;
      int i = 0;
      int j = arrayOfSource.length;
      while (i < j)
      {
        Util.closeQuietly(arrayOfSource[i]);
        i += 1;
      }
    }
    
    @Nullable
    public DiskLruCache.Editor edit()
      throws IOException
    {
      return edit(key, sequenceNumber);
    }
    
    public long getLength(int paramInt)
    {
      return lengths[paramInt];
    }
    
    public Source getSource(int paramInt)
    {
      return sources[paramInt];
    }
    
    public String key()
    {
      return key;
    }
  }
}

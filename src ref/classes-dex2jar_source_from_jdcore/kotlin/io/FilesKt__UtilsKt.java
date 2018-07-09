package kotlin.io;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000<\n\000\n\002\020\016\n\002\030\002\n\002\b\f\n\002\020\013\n\002\b\003\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\004\n\002\020 \n\000\n\002\030\002\n\002\b\f\032(\020\t\032\0020\0022\b\b\002\020\n\032\0020\0012\n\b\002\020\013\032\004\030\0010\0012\n\b\002\020\f\032\004\030\0010\002\032(\020\r\032\0020\0022\b\b\002\020\n\032\0020\0012\n\b\002\020\013\032\004\030\0010\0012\n\b\002\020\f\032\004\030\0010\002\0328\020\016\032\0020\017*\0020\0022\006\020\020\032\0020\0022\b\b\002\020\021\032\0020\0172\032\b\002\020\022\032\024\022\004\022\0020\002\022\004\022\0020\024\022\004\022\0020\0250\023\032&\020\026\032\0020\002*\0020\0022\006\020\020\032\0020\0022\b\b\002\020\021\032\0020\0172\b\b\002\020\027\032\0020\030\032\n\020\031\032\0020\017*\0020\002\032\022\020\032\032\0020\017*\0020\0022\006\020\033\032\0020\002\032\022\020\032\032\0020\017*\0020\0022\006\020\033\032\0020\001\032\n\020\034\032\0020\002*\0020\002\032\035\020\034\032\b\022\004\022\0020\0020\035*\b\022\004\022\0020\0020\035H\002¢\006\002\b\036\032\021\020\034\032\0020\037*\0020\037H\002¢\006\002\b\036\032\022\020 \032\0020\002*\0020\0022\006\020!\032\0020\002\032\024\020\"\032\004\030\0010\002*\0020\0022\006\020!\032\0020\002\032\022\020#\032\0020\002*\0020\0022\006\020!\032\0020\002\032\022\020$\032\0020\002*\0020\0022\006\020%\032\0020\002\032\022\020$\032\0020\002*\0020\0022\006\020%\032\0020\001\032\022\020&\032\0020\002*\0020\0022\006\020%\032\0020\002\032\022\020&\032\0020\002*\0020\0022\006\020%\032\0020\001\032\022\020'\032\0020\017*\0020\0022\006\020\033\032\0020\002\032\022\020'\032\0020\017*\0020\0022\006\020\033\032\0020\001\032\022\020(\032\0020\001*\0020\0022\006\020!\032\0020\002\032\033\020)\032\004\030\0010\001*\0020\0022\006\020!\032\0020\002H\002¢\006\002\b*\"\025\020\000\032\0020\001*\0020\0028F¢\006\006\032\004\b\003\020\004\"\025\020\005\032\0020\001*\0020\0028F¢\006\006\032\004\b\006\020\004\"\025\020\007\032\0020\001*\0020\0028F¢\006\006\032\004\b\b\020\004¨\006+"}, d2={"extension", "", "Ljava/io/File;", "getExtension", "(Ljava/io/File;)Ljava/lang/String;", "invariantSeparatorsPath", "getInvariantSeparatorsPath", "nameWithoutExtension", "getNameWithoutExtension", "createTempDir", "prefix", "suffix", "directory", "createTempFile", "copyRecursively", "", "target", "overwrite", "onError", "Lkotlin/Function2;", "Ljava/io/IOException;", "Lkotlin/io/OnErrorAction;", "copyTo", "bufferSize", "", "deleteRecursively", "endsWith", "other", "normalize", "", "normalize$FilesKt__UtilsKt", "Lkotlin/io/FilePathComponents;", "relativeTo", "base", "relativeToOrNull", "relativeToOrSelf", "resolve", "relative", "resolveSibling", "startsWith", "toRelativeString", "toRelativeStringOrNull", "toRelativeStringOrNull$FilesKt__UtilsKt", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/io/FilesKt")
class FilesKt__UtilsKt
  extends FilesKt__FileTreeWalkKt
{
  public FilesKt__UtilsKt() {}
  
  public static final boolean copyRecursively(@NotNull File paramFile1, @NotNull File paramFile2, boolean paramBoolean, @NotNull Function2<? super File, ? super IOException, ? extends OnErrorAction> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramFile1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFile2, "target");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "onError");
    if (!paramFile1.exists()) {
      return (OnErrorAction)paramFunction2.invoke(paramFile1, new NoSuchFileException(paramFile1, null, "The source file doesn't exist.", 2, null)) != OnErrorAction.TERMINATE;
    }
    for (;;)
    {
      boolean bool;
      try
      {
        Iterator localIterator = FilesKt.walkTopDown(paramFile1).onFail((Function2)new Lambda(paramFunction2)
        {
          public final void invoke(@NotNull File paramAnonymousFile, @NotNull IOException paramAnonymousIOException)
          {
            Intrinsics.checkParameterIsNotNull(paramAnonymousFile, "f");
            Intrinsics.checkParameterIsNotNull(paramAnonymousIOException, "e");
            if ((OnErrorAction)$onError.invoke(paramAnonymousFile, paramAnonymousIOException) == OnErrorAction.TERMINATE) {
              throw ((Throwable)new TerminateException(paramAnonymousFile));
            }
          }
        }).iterator();
        if (localIterator.hasNext())
        {
          Object localObject1 = (File)localIterator.next();
          if (!((File)localObject1).exists())
          {
            if ((OnErrorAction)paramFunction2.invoke(localObject1, new NoSuchFileException((File)localObject1, null, "The source file doesn't exist.", 2, null)) != OnErrorAction.TERMINATE) {
              continue;
            }
            return false;
          }
          Object localObject2 = new File(paramFile2, FilesKt.toRelativeString((File)localObject1, paramFile1));
          if (((File)localObject2).exists())
          {
            if (!((File)localObject1).isDirectory()) {
              break label336;
            }
            if (!((File)localObject2).isDirectory())
            {
              break label336;
              if (((File)localObject2).isDirectory())
              {
                bool = FilesKt.deleteRecursively((File)localObject2) ^ true;
              }
              else
              {
                if (((File)localObject2).delete()) {
                  break label346;
                }
                break label340;
              }
              if (bool)
              {
                if ((OnErrorAction)paramFunction2.invoke(localObject2, new FileAlreadyExistsException((File)localObject1, (File)localObject2, "The destination file already exists.")) != OnErrorAction.TERMINATE) {
                  continue;
                }
                return false;
              }
            }
          }
          if (((File)localObject1).isDirectory())
          {
            ((File)localObject2).mkdirs();
            continue;
          }
          if (FilesKt.copyTo$default((File)localObject1, (File)localObject2, paramBoolean, 0, 4, null).length() == ((File)localObject1).length()) {
            continue;
          }
          localObject1 = (OnErrorAction)paramFunction2.invoke(localObject1, new IOException("Source file wasn't copied completely, length of destination file differs."));
          localObject2 = OnErrorAction.TERMINATE;
          if (localObject1 != localObject2) {
            continue;
          }
          return false;
        }
        else
        {
          return true;
        }
      }
      catch (TerminateException paramFile1)
      {
        return false;
      }
      label336:
      if (!paramBoolean)
      {
        label340:
        bool = true;
        continue;
        label346:
        bool = false;
      }
    }
  }
  
  /* Error */
  @NotNull
  public static final File copyTo(@NotNull File paramFile1, @NotNull File paramFile2, boolean paramBoolean, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 75
    //   3: invokestatic 81	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc 82
    //   9: invokestatic 81	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   12: aload_0
    //   13: invokevirtual 89	java/io/File:exists	()Z
    //   16: ifne +20 -> 36
    //   19: new 91	kotlin/io/NoSuchFileException
    //   22: dup
    //   23: aload_0
    //   24: aconst_null
    //   25: ldc 93
    //   27: iconst_2
    //   28: aconst_null
    //   29: invokespecial 96	kotlin/io/NoSuchFileException:<init>	(Ljava/io/File;Ljava/io/File;Ljava/lang/String;ILkotlin/jvm/internal/DefaultConstructorMarker;)V
    //   32: checkcast 188	java/lang/Throwable
    //   35: athrow
    //   36: aload_1
    //   37: invokevirtual 89	java/io/File:exists	()Z
    //   40: ifeq +46 -> 86
    //   43: iconst_1
    //   44: istore 4
    //   46: iload_2
    //   47: ifne +6 -> 53
    //   50: goto +16 -> 66
    //   53: aload_1
    //   54: invokevirtual 149	java/io/File:delete	()Z
    //   57: ifne +6 -> 63
    //   60: goto +6 -> 66
    //   63: iconst_0
    //   64: istore 4
    //   66: iload 4
    //   68: ifeq +18 -> 86
    //   71: new 151	kotlin/io/FileAlreadyExistsException
    //   74: dup
    //   75: aload_0
    //   76: aload_1
    //   77: ldc -103
    //   79: invokespecial 156	kotlin/io/FileAlreadyExistsException:<init>	(Ljava/io/File;Ljava/io/File;Ljava/lang/String;)V
    //   82: checkcast 188	java/lang/Throwable
    //   85: athrow
    //   86: aload_0
    //   87: invokevirtual 143	java/io/File:isDirectory	()Z
    //   90: ifeq +25 -> 115
    //   93: aload_1
    //   94: invokevirtual 159	java/io/File:mkdirs	()Z
    //   97: ifne +138 -> 235
    //   100: new 190	kotlin/io/FileSystemException
    //   103: dup
    //   104: aload_0
    //   105: aload_1
    //   106: ldc -64
    //   108: invokespecial 193	kotlin/io/FileSystemException:<init>	(Ljava/io/File;Ljava/io/File;Ljava/lang/String;)V
    //   111: checkcast 188	java/lang/Throwable
    //   114: athrow
    //   115: aload_1
    //   116: invokevirtual 197	java/io/File:getParentFile	()Ljava/io/File;
    //   119: astore 5
    //   121: aload 5
    //   123: ifnull +9 -> 132
    //   126: aload 5
    //   128: invokevirtual 159	java/io/File:mkdirs	()Z
    //   131: pop
    //   132: new 199	java/io/FileInputStream
    //   135: dup
    //   136: aload_0
    //   137: invokespecial 202	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   140: checkcast 204	java/io/Closeable
    //   143: astore 8
    //   145: aconst_null
    //   146: checkcast 188	java/lang/Throwable
    //   149: astore 6
    //   151: aload 6
    //   153: astore 5
    //   155: aload 8
    //   157: checkcast 199	java/io/FileInputStream
    //   160: astore 10
    //   162: aload 6
    //   164: astore 5
    //   166: new 206	java/io/FileOutputStream
    //   169: dup
    //   170: aload_1
    //   171: invokespecial 207	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   174: checkcast 204	java/io/Closeable
    //   177: astore 9
    //   179: aload 6
    //   181: astore 5
    //   183: aconst_null
    //   184: checkcast 188	java/lang/Throwable
    //   187: astore 7
    //   189: aload 7
    //   191: astore_0
    //   192: aload 9
    //   194: checkcast 206	java/io/FileOutputStream
    //   197: astore 5
    //   199: aload 7
    //   201: astore_0
    //   202: aload 10
    //   204: checkcast 209	java/io/InputStream
    //   207: aload 5
    //   209: checkcast 211	java/io/OutputStream
    //   212: iload_3
    //   213: invokestatic 216	kotlin/io/ByteStreamsKt:copyTo	(Ljava/io/InputStream;Ljava/io/OutputStream;I)J
    //   216: pop2
    //   217: aload 6
    //   219: astore 5
    //   221: aload 9
    //   223: aload 7
    //   225: invokestatic 222	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   228: aload 8
    //   230: aload 6
    //   232: invokestatic 222	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   235: aload_1
    //   236: areturn
    //   237: astore_1
    //   238: goto +8 -> 246
    //   241: astore_1
    //   242: aload_1
    //   243: astore_0
    //   244: aload_1
    //   245: athrow
    //   246: aload 6
    //   248: astore 5
    //   250: aload 9
    //   252: aload_0
    //   253: invokestatic 222	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   256: aload 6
    //   258: astore 5
    //   260: aload_1
    //   261: athrow
    //   262: astore_0
    //   263: goto +9 -> 272
    //   266: astore_0
    //   267: aload_0
    //   268: astore 5
    //   270: aload_0
    //   271: athrow
    //   272: aload 8
    //   274: aload 5
    //   276: invokestatic 222	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   279: aload_0
    //   280: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	281	0	paramFile1	File
    //   0	281	1	paramFile2	File
    //   0	281	2	paramBoolean	boolean
    //   0	281	3	paramInt	int
    //   44	23	4	i	int
    //   119	156	5	localObject	Object
    //   149	108	6	localThrowable1	Throwable
    //   187	37	7	localThrowable2	Throwable
    //   143	130	8	localCloseable1	java.io.Closeable
    //   177	74	9	localCloseable2	java.io.Closeable
    //   160	43	10	localFileInputStream	java.io.FileInputStream
    // Exception table:
    //   from	to	target	type
    //   192	199	237	finally
    //   202	217	237	finally
    //   244	246	237	finally
    //   192	199	241	java/lang/Throwable
    //   202	217	241	java/lang/Throwable
    //   155	162	262	finally
    //   166	179	262	finally
    //   183	189	262	finally
    //   221	228	262	finally
    //   250	256	262	finally
    //   260	262	262	finally
    //   270	272	262	finally
    //   155	162	266	java/lang/Throwable
    //   166	179	266	java/lang/Throwable
    //   183	189	266	java/lang/Throwable
    //   221	228	266	java/lang/Throwable
    //   250	256	266	java/lang/Throwable
    //   260	262	266	java/lang/Throwable
  }
  
  @NotNull
  public static final File createTempDir(@NotNull String paramString1, @Nullable String paramString2, @Nullable File paramFile)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "prefix");
    paramString1 = File.createTempFile(paramString1, paramString2, paramFile);
    paramString1.delete();
    if (paramString1.mkdir())
    {
      Intrinsics.checkExpressionValueIsNotNull(paramString1, "dir");
      return paramString1;
    }
    paramString2 = new StringBuilder();
    paramString2.append("Unable to create temporary directory ");
    paramString2.append(paramString1);
    paramString2.append('.');
    throw ((Throwable)new IOException(paramString2.toString()));
  }
  
  @NotNull
  public static final File createTempFile(@NotNull String paramString1, @Nullable String paramString2, @Nullable File paramFile)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "prefix");
    paramString1 = File.createTempFile(paramString1, paramString2, paramFile);
    Intrinsics.checkExpressionValueIsNotNull(paramString1, "File.createTempFile(prefix, suffix, directory)");
    return paramString1;
  }
  
  public static final boolean deleteRecursively(@NotNull File paramFile)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    paramFile = ((Sequence)FilesKt.walkBottomUp(paramFile)).iterator();
    for (boolean bool = true;; bool = false)
    {
      if (!paramFile.hasNext()) {
        return bool;
      }
      File localFile = (File)paramFile.next();
      if (((localFile.delete()) || (!localFile.exists())) && (bool)) {
        break;
      }
    }
    return bool;
  }
  
  public static final boolean endsWith(@NotNull File paramFile1, @NotNull File paramFile2)
  {
    Intrinsics.checkParameterIsNotNull(paramFile1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFile2, "other");
    FilePathComponents localFilePathComponents1 = FilesKt.toComponents(paramFile1);
    FilePathComponents localFilePathComponents2 = FilesKt.toComponents(paramFile2);
    if (localFilePathComponents2.isRooted()) {
      return Intrinsics.areEqual(paramFile1, paramFile2);
    }
    int i = localFilePathComponents1.getSize() - localFilePathComponents2.getSize();
    if (i < 0) {
      return false;
    }
    return localFilePathComponents1.getSegments().subList(i, localFilePathComponents1.getSize()).equals(localFilePathComponents2.getSegments());
  }
  
  public static final boolean endsWith(@NotNull File paramFile, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString, "other");
    return FilesKt.endsWith(paramFile, new File(paramString));
  }
  
  @NotNull
  public static final String getExtension(@NotNull File paramFile)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    paramFile = paramFile.getName();
    Intrinsics.checkExpressionValueIsNotNull(paramFile, "name");
    return StringsKt.substringAfterLast(paramFile, '.', "");
  }
  
  @NotNull
  public static final String getInvariantSeparatorsPath(@NotNull File paramFile)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    if (File.separatorChar != '/')
    {
      paramFile = paramFile.getPath();
      Intrinsics.checkExpressionValueIsNotNull(paramFile, "path");
      return StringsKt.replace$default(paramFile, File.separatorChar, '/', false, 4, null);
    }
    paramFile = paramFile.getPath();
    Intrinsics.checkExpressionValueIsNotNull(paramFile, "path");
    return paramFile;
  }
  
  @NotNull
  public static final String getNameWithoutExtension(@NotNull File paramFile)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    paramFile = paramFile.getName();
    Intrinsics.checkExpressionValueIsNotNull(paramFile, "name");
    return StringsKt.substringBeforeLast$default(paramFile, ".", null, 2, null);
  }
  
  @NotNull
  public static final File normalize(@NotNull File paramFile)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    Object localObject = FilesKt.toComponents(paramFile);
    paramFile = ((FilePathComponents)localObject).getRoot();
    localObject = (Iterable)normalize$FilesKt__UtilsKt(((FilePathComponents)localObject).getSegments());
    String str = File.separator;
    Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
    return FilesKt.resolve(paramFile, CollectionsKt.joinToString$default((Iterable)localObject, (CharSequence)str, null, null, 0, null, null, 62, null));
  }
  
  private static final List<File> normalize$FilesKt__UtilsKt(@NotNull List<? extends File> paramList)
  {
    List localList = (List)new ArrayList(paramList.size());
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      File localFile = (File)paramList.next();
      String str = localFile.getName();
      if (str != null)
      {
        int i = str.hashCode();
        if (i != 46)
        {
          if ((i == 1472) && (str.equals("..")))
          {
            if ((!localList.isEmpty()) && ((Intrinsics.areEqual(((File)CollectionsKt.last(localList)).getName(), "..") ^ true)))
            {
              localList.remove(localList.size() - 1);
              continue;
            }
            localList.add(localFile);
          }
        }
        else {
          if (str.equals(".")) {
            continue;
          }
        }
      }
      localList.add(localFile);
    }
    return localList;
  }
  
  private static final FilePathComponents normalize$FilesKt__UtilsKt(@NotNull FilePathComponents paramFilePathComponents)
  {
    return new FilePathComponents(paramFilePathComponents.getRoot(), normalize$FilesKt__UtilsKt(paramFilePathComponents.getSegments()));
  }
  
  @NotNull
  public static final File relativeTo(@NotNull File paramFile1, @NotNull File paramFile2)
  {
    Intrinsics.checkParameterIsNotNull(paramFile1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFile2, "base");
    return new File(FilesKt.toRelativeString(paramFile1, paramFile2));
  }
  
  @Nullable
  public static final File relativeToOrNull(@NotNull File paramFile1, @NotNull File paramFile2)
  {
    Intrinsics.checkParameterIsNotNull(paramFile1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFile2, "base");
    paramFile1 = toRelativeStringOrNull$FilesKt__UtilsKt(paramFile1, paramFile2);
    if (paramFile1 != null) {
      return new File(paramFile1);
    }
    return null;
  }
  
  @NotNull
  public static final File relativeToOrSelf(@NotNull File paramFile1, @NotNull File paramFile2)
  {
    Intrinsics.checkParameterIsNotNull(paramFile1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFile2, "base");
    paramFile2 = toRelativeStringOrNull$FilesKt__UtilsKt(paramFile1, paramFile2);
    if (paramFile2 != null) {
      paramFile1 = new File(paramFile2);
    }
    return paramFile1;
  }
  
  @NotNull
  public static final File resolve(@NotNull File paramFile1, @NotNull File paramFile2)
  {
    Intrinsics.checkParameterIsNotNull(paramFile1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFile2, "relative");
    if (FilesKt.isRooted(paramFile2)) {
      return paramFile2;
    }
    paramFile1 = paramFile1.toString();
    Intrinsics.checkExpressionValueIsNotNull(paramFile1, "baseName");
    Object localObject = (CharSequence)paramFile1;
    int i;
    if (((CharSequence)localObject).length() == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if ((i == 0) && (!StringsKt.endsWith$default((CharSequence)localObject, File.separatorChar, false, 2, null)))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(paramFile1);
      ((StringBuilder)localObject).append(File.separatorChar);
      ((StringBuilder)localObject).append(paramFile2);
      return new File(((StringBuilder)localObject).toString());
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append(paramFile1);
    ((StringBuilder)localObject).append(paramFile2);
    return new File(((StringBuilder)localObject).toString());
  }
  
  @NotNull
  public static final File resolve(@NotNull File paramFile, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString, "relative");
    return FilesKt.resolve(paramFile, new File(paramString));
  }
  
  @NotNull
  public static final File resolveSibling(@NotNull File paramFile1, @NotNull File paramFile2)
  {
    Intrinsics.checkParameterIsNotNull(paramFile1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFile2, "relative");
    FilePathComponents localFilePathComponents = FilesKt.toComponents(paramFile1);
    if (localFilePathComponents.getSize() == 0) {
      paramFile1 = new File("..");
    } else {
      paramFile1 = localFilePathComponents.subPath(0, localFilePathComponents.getSize() - 1);
    }
    return FilesKt.resolve(FilesKt.resolve(localFilePathComponents.getRoot(), paramFile1), paramFile2);
  }
  
  @NotNull
  public static final File resolveSibling(@NotNull File paramFile, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString, "relative");
    return FilesKt.resolveSibling(paramFile, new File(paramString));
  }
  
  public static final boolean startsWith(@NotNull File paramFile1, @NotNull File paramFile2)
  {
    Intrinsics.checkParameterIsNotNull(paramFile1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFile2, "other");
    paramFile1 = FilesKt.toComponents(paramFile1);
    paramFile2 = FilesKt.toComponents(paramFile2);
    if ((Intrinsics.areEqual(paramFile1.getRoot(), paramFile2.getRoot()) ^ true)) {
      return false;
    }
    if (paramFile1.getSize() < paramFile2.getSize()) {
      return false;
    }
    return paramFile1.getSegments().subList(0, paramFile2.getSize()).equals(paramFile2.getSegments());
  }
  
  public static final boolean startsWith(@NotNull File paramFile, @NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString, "other");
    return FilesKt.startsWith(paramFile, new File(paramString));
  }
  
  @NotNull
  public static final String toRelativeString(@NotNull File paramFile1, @NotNull File paramFile2)
  {
    Intrinsics.checkParameterIsNotNull(paramFile1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFile2, "base");
    Object localObject = toRelativeStringOrNull$FilesKt__UtilsKt(paramFile1, paramFile2);
    if (localObject != null) {
      return localObject;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("this and base files have different roots: ");
    ((StringBuilder)localObject).append(paramFile1);
    ((StringBuilder)localObject).append(" and ");
    ((StringBuilder)localObject).append(paramFile2);
    ((StringBuilder)localObject).append('.');
    throw ((Throwable)new IllegalArgumentException(((StringBuilder)localObject).toString()));
  }
  
  private static final String toRelativeStringOrNull$FilesKt__UtilsKt(@NotNull File paramFile1, File paramFile2)
  {
    Object localObject = normalize$FilesKt__UtilsKt(FilesKt.toComponents(paramFile1));
    paramFile2 = normalize$FilesKt__UtilsKt(FilesKt.toComponents(paramFile2));
    if ((Intrinsics.areEqual(((FilePathComponents)localObject).getRoot(), paramFile2.getRoot()) ^ true)) {
      return null;
    }
    int k = paramFile2.getSize();
    int m = ((FilePathComponents)localObject).getSize();
    int i = 0;
    int j = Math.min(m, k);
    while ((i < j) && (Intrinsics.areEqual((File)((FilePathComponents)localObject).getSegments().get(i), (File)paramFile2.getSegments().get(i)))) {
      i += 1;
    }
    paramFile1 = new StringBuilder();
    j = k - 1;
    if (j >= i) {
      for (;;)
      {
        if (Intrinsics.areEqual(((File)paramFile2.getSegments().get(j)).getName(), "..")) {
          return null;
        }
        paramFile1.append("..");
        if (j != i) {
          paramFile1.append(File.separatorChar);
        }
        if (j == i) {
          break;
        }
        j -= 1;
      }
    }
    if (i < m)
    {
      if (i < k) {
        paramFile1.append(File.separatorChar);
      }
      paramFile2 = (Iterable)CollectionsKt.drop((Iterable)((FilePathComponents)localObject).getSegments(), i);
      localObject = (Appendable)paramFile1;
      String str = File.separator;
      Intrinsics.checkExpressionValueIsNotNull(str, "File.separator");
      CollectionsKt.joinTo$default(paramFile2, (Appendable)localObject, (CharSequence)str, null, null, 0, null, null, 124, null);
    }
    return paramFile1.toString();
  }
}

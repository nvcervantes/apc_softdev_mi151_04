package okhttp3.internal.cache2;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.channels.FileChannel;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.ByteString;
import okio.Source;
import okio.Timeout;

final class Relay
{
  private static final long FILE_HEADER_SIZE = 32L;
  static final ByteString PREFIX_CLEAN = ByteString.encodeUtf8("OkHttp cache v1\n");
  static final ByteString PREFIX_DIRTY = ByteString.encodeUtf8("OkHttp DIRTY :(\n");
  private static final int SOURCE_FILE = 2;
  private static final int SOURCE_UPSTREAM = 1;
  final Buffer buffer = new Buffer();
  final long bufferMaxSize;
  boolean complete;
  RandomAccessFile file;
  private final ByteString metadata;
  int sourceCount;
  Source upstream;
  final Buffer upstreamBuffer = new Buffer();
  long upstreamPos;
  Thread upstreamReader;
  
  private Relay(RandomAccessFile paramRandomAccessFile, Source paramSource, long paramLong1, ByteString paramByteString, long paramLong2)
  {
    file = paramRandomAccessFile;
    upstream = paramSource;
    boolean bool;
    if (paramSource == null) {
      bool = true;
    } else {
      bool = false;
    }
    complete = bool;
    upstreamPos = paramLong1;
    metadata = paramByteString;
    bufferMaxSize = paramLong2;
  }
  
  public static Relay edit(File paramFile, Source paramSource, ByteString paramByteString, long paramLong)
    throws IOException
  {
    paramFile = new RandomAccessFile(paramFile, "rw");
    paramSource = new Relay(paramFile, paramSource, 0L, paramByteString, paramLong);
    paramFile.setLength(0L);
    paramSource.writeHeader(PREFIX_DIRTY, -1L, -1L);
    return paramSource;
  }
  
  public static Relay read(File paramFile)
    throws IOException
  {
    paramFile = new RandomAccessFile(paramFile, "rw");
    FileOperator localFileOperator = new FileOperator(paramFile.getChannel());
    Buffer localBuffer = new Buffer();
    localFileOperator.read(0L, localBuffer, 32L);
    if (!localBuffer.readByteString(PREFIX_CLEAN.size()).equals(PREFIX_CLEAN)) {
      throw new IOException("unreadable cache file");
    }
    long l1 = localBuffer.readLong();
    long l2 = localBuffer.readLong();
    localBuffer = new Buffer();
    localFileOperator.read(32L + l1, localBuffer, l2);
    return new Relay(paramFile, null, l1, localBuffer.readByteString(), 0L);
  }
  
  private void writeHeader(ByteString paramByteString, long paramLong1, long paramLong2)
    throws IOException
  {
    Buffer localBuffer = new Buffer();
    localBuffer.write(paramByteString);
    localBuffer.writeLong(paramLong1);
    localBuffer.writeLong(paramLong2);
    if (localBuffer.size() != 32L) {
      throw new IllegalArgumentException();
    }
    new FileOperator(file.getChannel()).write(0L, localBuffer, 32L);
  }
  
  private void writeMetadata(long paramLong)
    throws IOException
  {
    Buffer localBuffer = new Buffer();
    localBuffer.write(metadata);
    new FileOperator(file.getChannel()).write(32L + paramLong, localBuffer, metadata.size());
  }
  
  void commit(long paramLong)
    throws IOException
  {
    writeMetadata(paramLong);
    file.getChannel().force(false);
    writeHeader(PREFIX_CLEAN, paramLong, metadata.size());
    file.getChannel().force(false);
    try
    {
      complete = true;
      Util.closeQuietly(upstream);
      upstream = null;
      return;
    }
    finally {}
  }
  
  boolean isClosed()
  {
    return file == null;
  }
  
  public ByteString metadata()
  {
    return metadata;
  }
  
  public Source newSource()
  {
    try
    {
      if (file == null) {
        return null;
      }
      sourceCount += 1;
      return new RelaySource();
    }
    finally {}
  }
  
  class RelaySource
    implements Source
  {
    private FileOperator fileOperator = new FileOperator(file.getChannel());
    private long sourcePos;
    private final Timeout timeout = new Timeout();
    
    RelaySource() {}
    
    public void close()
      throws IOException
    {
      if (fileOperator == null) {
        return;
      }
      RandomAccessFile localRandomAccessFile = null;
      fileOperator = null;
      synchronized (Relay.this)
      {
        Relay localRelay2 = Relay.this;
        sourceCount -= 1;
        if (sourceCount == 0)
        {
          localRandomAccessFile = file;
          file = null;
        }
        if (localRandomAccessFile != null) {
          Util.closeQuietly(localRandomAccessFile);
        }
        return;
      }
    }
    
    public long read(Buffer arg1, long paramLong)
      throws IOException
    {
      if (fileOperator == null) {
        throw new IllegalStateException("closed");
      }
      synchronized (Relay.this)
      {
        for (;;)
        {
          l2 = sourcePos;
          l1 = upstreamPos;
          if (l2 != l1) {
            break label109;
          }
          if (complete) {
            return -1L;
          }
          if (upstreamReader == null) {
            break;
          }
          timeout.waitUntilNotified(Relay.this);
        }
        upstreamReader = Thread.currentThread();
        int i = 1;
        break label140;
        label109:
        l2 = l1 - buffer.size();
        if (sourcePos < l2)
        {
          i = 2;
          label140:
          if (i == 2)
          {
            paramLong = Math.min(paramLong, l1 - sourcePos);
            fileOperator.read(32L + sourcePos, ???, paramLong);
            sourcePos += paramLong;
            return paramLong;
          }
          try
          {
            l2 = upstream.read(upstreamBuffer, bufferMaxSize);
            if (l2 == -1L)
            {
              commit(l1);
              synchronized (Relay.this)
              {
                upstreamReader = null;
                notifyAll();
                return -1L;
              }
            }
            paramLong = Math.min(l2, paramLong);
            upstreamBuffer.copyTo(???, 0L, paramLong);
            sourcePos += paramLong;
            fileOperator.write(32L + l1, upstreamBuffer.clone(), l2);
            synchronized (Relay.this)
            {
              buffer.write(upstreamBuffer, l2);
              if (buffer.size() > bufferMaxSize) {
                buffer.skip(buffer.size() - bufferMaxSize);
              }
              Relay localRelay2 = Relay.this;
              upstreamPos += l2;
              synchronized (Relay.this)
              {
                upstreamReader = null;
                notifyAll();
                return paramLong;
              }
            }
            synchronized (Relay.this)
            {
              upstreamReader = null;
              notifyAll();
              throw localObject4;
            }
          }
          finally {}
        }
      }
    }
    
    public Timeout timeout()
    {
      return timeout;
    }
  }
}

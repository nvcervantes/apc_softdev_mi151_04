package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.charset.Charset;

final class RealBufferedSink
  implements BufferedSink
{
  public final Buffer buffer = new Buffer();
  boolean closed;
  public final Sink sink;
  
  RealBufferedSink(Sink paramSink)
  {
    if (paramSink == null) {
      throw new NullPointerException("sink == null");
    }
    sink = paramSink;
  }
  
  public Buffer buffer()
  {
    return buffer;
  }
  
  public void close()
    throws IOException
  {
    if (closed) {
      return;
    }
    Object localObject2 = null;
    Object localObject1 = localObject2;
    try
    {
      if (buffer.size > 0L)
      {
        sink.write(buffer, buffer.size);
        localObject1 = localObject2;
      }
    }
    catch (Throwable localThrowable1) {}
    try
    {
      sink.close();
      localObject2 = localThrowable1;
    }
    catch (Throwable localThrowable2)
    {
      localObject2 = localThrowable1;
      if (localThrowable1 == null) {
        localObject2 = localThrowable2;
      }
    }
    closed = true;
    if (localObject2 != null) {
      Util.sneakyRethrow(localObject2);
    }
  }
  
  public BufferedSink emit()
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    long l = buffer.size();
    if (l > 0L) {
      sink.write(buffer, l);
    }
    return this;
  }
  
  public BufferedSink emitCompleteSegments()
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    long l = buffer.completeSegmentByteCount();
    if (l > 0L) {
      sink.write(buffer, l);
    }
    return this;
  }
  
  public void flush()
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    if (buffer.size > 0L) {
      sink.write(buffer, buffer.size);
    }
    sink.flush();
  }
  
  public OutputStream outputStream()
  {
    new OutputStream()
    {
      public void close()
        throws IOException
      {
        RealBufferedSink.this.close();
      }
      
      public void flush()
        throws IOException
      {
        if (!closed) {
          RealBufferedSink.this.flush();
        }
      }
      
      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(RealBufferedSink.this);
        localStringBuilder.append(".outputStream()");
        return localStringBuilder.toString();
      }
      
      public void write(int paramAnonymousInt)
        throws IOException
      {
        if (closed) {
          throw new IOException("closed");
        }
        buffer.writeByte((byte)paramAnonymousInt);
        emitCompleteSegments();
      }
      
      public void write(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
        throws IOException
      {
        if (closed) {
          throw new IOException("closed");
        }
        buffer.write(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
        emitCompleteSegments();
      }
    };
  }
  
  public Timeout timeout()
  {
    return sink.timeout();
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("buffer(");
    localStringBuilder.append(sink);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public BufferedSink write(ByteString paramByteString)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.write(paramByteString);
    return emitCompleteSegments();
  }
  
  public BufferedSink write(Source paramSource, long paramLong)
    throws IOException
  {
    while (paramLong > 0L)
    {
      long l = paramSource.read(buffer, paramLong);
      if (l == -1L) {
        throw new EOFException();
      }
      emitCompleteSegments();
      paramLong -= l;
    }
    return this;
  }
  
  public BufferedSink write(byte[] paramArrayOfByte)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.write(paramArrayOfByte);
    return emitCompleteSegments();
  }
  
  public BufferedSink write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.write(paramArrayOfByte, paramInt1, paramInt2);
    return emitCompleteSegments();
  }
  
  public void write(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.write(paramBuffer, paramLong);
    emitCompleteSegments();
  }
  
  public long writeAll(Source paramSource)
    throws IOException
  {
    if (paramSource == null) {
      throw new IllegalArgumentException("source == null");
    }
    long l2;
    for (long l1 = 0L;; l1 += l2)
    {
      l2 = paramSource.read(buffer, 8192L);
      if (l2 == -1L) {
        break;
      }
      emitCompleteSegments();
    }
    return l1;
  }
  
  public BufferedSink writeByte(int paramInt)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeByte(paramInt);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeDecimalLong(long paramLong)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeDecimalLong(paramLong);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeHexadecimalUnsignedLong(long paramLong)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeHexadecimalUnsignedLong(paramLong);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeInt(int paramInt)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeInt(paramInt);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeIntLe(int paramInt)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeIntLe(paramInt);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeLong(long paramLong)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeLong(paramLong);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeLongLe(long paramLong)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeLongLe(paramLong);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeShort(int paramInt)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeShort(paramInt);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeShortLe(int paramInt)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeShortLe(paramInt);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeString(String paramString, int paramInt1, int paramInt2, Charset paramCharset)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeString(paramString, paramInt1, paramInt2, paramCharset);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeString(String paramString, Charset paramCharset)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeString(paramString, paramCharset);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeUtf8(String paramString)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeUtf8(paramString);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeUtf8(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeUtf8(paramString, paramInt1, paramInt2);
    return emitCompleteSegments();
  }
  
  public BufferedSink writeUtf8CodePoint(int paramInt)
    throws IOException
  {
    if (closed) {
      throw new IllegalStateException("closed");
    }
    buffer.writeUtf8CodePoint(paramInt);
    return emitCompleteSegments();
  }
}

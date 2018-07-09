package okhttp3.internal.ws;

import java.io.IOException;
import java.util.Random;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;
import okio.Sink;
import okio.Timeout;

final class WebSocketWriter
{
  boolean activeWriter;
  final Buffer buffer = new Buffer();
  final FrameSink frameSink = new FrameSink();
  final boolean isClient;
  final byte[] maskBuffer;
  final byte[] maskKey;
  final Random random;
  final BufferedSink sink;
  boolean writerClosed;
  
  WebSocketWriter(boolean paramBoolean, BufferedSink paramBufferedSink, Random paramRandom)
  {
    if (paramBufferedSink == null) {
      throw new NullPointerException("sink == null");
    }
    if (paramRandom == null) {
      throw new NullPointerException("random == null");
    }
    isClient = paramBoolean;
    sink = paramBufferedSink;
    random = paramRandom;
    paramRandom = null;
    if (paramBoolean) {
      paramBufferedSink = new byte[4];
    } else {
      paramBufferedSink = null;
    }
    maskKey = paramBufferedSink;
    paramBufferedSink = paramRandom;
    if (paramBoolean) {
      paramBufferedSink = new byte[' '];
    }
    maskBuffer = paramBufferedSink;
  }
  
  private void writeControlFrame(int paramInt, ByteString paramByteString)
    throws IOException
  {
    if (writerClosed) {
      throw new IOException("closed");
    }
    int i = paramByteString.size();
    if (i > 125L) {
      throw new IllegalArgumentException("Payload size must be less than or equal to 125");
    }
    sink.writeByte(paramInt | 0x80);
    if (isClient)
    {
      sink.writeByte(i | 0x80);
      random.nextBytes(maskKey);
      sink.write(maskKey);
      paramByteString = paramByteString.toByteArray();
      WebSocketProtocol.toggleMask(paramByteString, paramByteString.length, maskKey, 0L);
      sink.write(paramByteString);
    }
    else
    {
      sink.writeByte(i);
      sink.write(paramByteString);
    }
    sink.flush();
  }
  
  Sink newMessageSink(int paramInt, long paramLong)
  {
    if (activeWriter) {
      throw new IllegalStateException("Another message writer is active. Did you call close()?");
    }
    activeWriter = true;
    frameSink.formatOpcode = paramInt;
    frameSink.contentLength = paramLong;
    frameSink.isFirstFrame = true;
    frameSink.closed = false;
    return frameSink;
  }
  
  void writeClose(int paramInt, ByteString paramByteString)
    throws IOException
  {
    Object localObject = ByteString.EMPTY;
    if ((paramInt != 0) || (paramByteString != null))
    {
      if (paramInt != 0) {
        WebSocketProtocol.validateCloseCode(paramInt);
      }
      localObject = new Buffer();
      ((Buffer)localObject).writeShort(paramInt);
      if (paramByteString != null) {
        ((Buffer)localObject).write(paramByteString);
      }
      localObject = ((Buffer)localObject).readByteString();
    }
    try
    {
      writeControlFrame(8, (ByteString)localObject);
      return;
    }
    finally
    {
      writerClosed = true;
    }
  }
  
  void writeMessageFrame(int paramInt, long paramLong, boolean paramBoolean1, boolean paramBoolean2)
    throws IOException
  {
    if (writerClosed) {
      throw new IOException("closed");
    }
    if (!paramBoolean1) {
      paramInt = 0;
    }
    int i = paramInt;
    if (paramBoolean2) {
      i = paramInt | 0x80;
    }
    sink.writeByte(i);
    if (isClient) {
      paramInt = 128;
    } else {
      paramInt = 0;
    }
    if (paramLong <= 125L)
    {
      i = (int)paramLong;
      sink.writeByte(paramInt | i);
    }
    else if (paramLong <= 65535L)
    {
      sink.writeByte(paramInt | 0x7E);
      sink.writeShort((int)paramLong);
    }
    else
    {
      sink.writeByte(paramInt | 0x7F);
      sink.writeLong(paramLong);
    }
    if (isClient)
    {
      random.nextBytes(maskKey);
      sink.write(maskKey);
      long l2;
      for (long l1 = 0L; l1 < paramLong; l1 += l2)
      {
        paramInt = (int)Math.min(paramLong, maskBuffer.length);
        paramInt = buffer.read(maskBuffer, 0, paramInt);
        if (paramInt == -1) {
          throw new AssertionError();
        }
        byte[] arrayOfByte = maskBuffer;
        l2 = paramInt;
        WebSocketProtocol.toggleMask(arrayOfByte, l2, maskKey, l1);
        sink.write(maskBuffer, 0, paramInt);
      }
    }
    sink.write(buffer, paramLong);
    sink.emit();
  }
  
  void writePing(ByteString paramByteString)
    throws IOException
  {
    writeControlFrame(9, paramByteString);
  }
  
  void writePong(ByteString paramByteString)
    throws IOException
  {
    writeControlFrame(10, paramByteString);
  }
  
  final class FrameSink
    implements Sink
  {
    boolean closed;
    long contentLength;
    int formatOpcode;
    boolean isFirstFrame;
    
    FrameSink() {}
    
    public void close()
      throws IOException
    {
      if (closed) {
        throw new IOException("closed");
      }
      writeMessageFrame(formatOpcode, buffer.size(), isFirstFrame, true);
      closed = true;
      activeWriter = false;
    }
    
    public void flush()
      throws IOException
    {
      if (closed) {
        throw new IOException("closed");
      }
      writeMessageFrame(formatOpcode, buffer.size(), isFirstFrame, false);
      isFirstFrame = false;
    }
    
    public Timeout timeout()
    {
      return sink.timeout();
    }
    
    public void write(Buffer paramBuffer, long paramLong)
      throws IOException
    {
      if (closed) {
        throw new IOException("closed");
      }
      buffer.write(paramBuffer, paramLong);
      int i;
      if ((isFirstFrame) && (contentLength != -1L) && (buffer.size() > contentLength - 8192L)) {
        i = 1;
      } else {
        i = 0;
      }
      paramLong = buffer.completeSegmentByteCount();
      if ((paramLong > 0L) && (i == 0))
      {
        writeMessageFrame(formatOpcode, paramLong, isFirstFrame, false);
        isFirstFrame = false;
      }
    }
  }
}

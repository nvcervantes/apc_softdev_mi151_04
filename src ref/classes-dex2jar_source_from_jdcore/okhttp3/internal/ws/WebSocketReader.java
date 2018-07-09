package okhttp3.internal.ws;

import java.io.EOFException;
import java.io.IOException;
import java.net.ProtocolException;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Timeout;

final class WebSocketReader
{
  boolean closed;
  long frameBytesRead;
  final FrameCallback frameCallback;
  long frameLength;
  final boolean isClient;
  boolean isControlFrame;
  boolean isFinalFrame;
  boolean isMasked;
  final byte[] maskBuffer = new byte[' '];
  final byte[] maskKey = new byte[4];
  int opcode;
  final BufferedSource source;
  
  WebSocketReader(boolean paramBoolean, BufferedSource paramBufferedSource, FrameCallback paramFrameCallback)
  {
    if (paramBufferedSource == null) {
      throw new NullPointerException("source == null");
    }
    if (paramFrameCallback == null) {
      throw new NullPointerException("frameCallback == null");
    }
    isClient = paramBoolean;
    source = paramBufferedSource;
    frameCallback = paramFrameCallback;
  }
  
  private void readControlFrame()
    throws IOException
  {
    Object localObject2 = new Buffer();
    if (frameBytesRead < frameLength) {
      if (isClient) {
        source.readFully((Buffer)localObject2, frameLength);
      } else {
        while (frameBytesRead < frameLength)
        {
          i = (int)Math.min(frameLength - frameBytesRead, maskBuffer.length);
          i = source.read(maskBuffer, 0, i);
          if (i == -1) {
            throw new EOFException();
          }
          localObject1 = maskBuffer;
          l = i;
          WebSocketProtocol.toggleMask((byte[])localObject1, l, maskKey, frameBytesRead);
          ((Buffer)localObject2).write(maskBuffer, 0, i);
          frameBytesRead += l;
        }
      }
    }
    switch (opcode)
    {
    default: 
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Unknown control opcode: ");
      ((StringBuilder)localObject1).append(Integer.toHexString(opcode));
      throw new ProtocolException(((StringBuilder)localObject1).toString());
    case 10: 
      frameCallback.onReadPong(((Buffer)localObject2).readByteString());
      return;
    case 9: 
      frameCallback.onReadPing(((Buffer)localObject2).readByteString());
      return;
    }
    int i = 1005;
    Object localObject1 = "";
    long l = ((Buffer)localObject2).size();
    if (l == 1L) {
      throw new ProtocolException("Malformed close payload length of 1.");
    }
    if (l != 0L)
    {
      i = ((Buffer)localObject2).readShort();
      localObject1 = ((Buffer)localObject2).readUtf8();
      localObject2 = WebSocketProtocol.closeCodeExceptionMessage(i);
      if (localObject2 != null) {
        throw new ProtocolException((String)localObject2);
      }
    }
    frameCallback.onReadClose(i, (String)localObject1);
    closed = true;
  }
  
  private void readHeader()
    throws IOException
  {
    if (closed) {
      throw new IOException("closed");
    }
    long l = source.timeout().timeoutNanos();
    source.timeout().clearTimeout();
    try
    {
      int i = source.readByte();
      int k = i & 0xFF;
      source.timeout().timeout(l, TimeUnit.NANOSECONDS);
      opcode = (k & 0xF);
      boolean bool2 = false;
      boolean bool1;
      if ((k & 0x80) != 0) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      isFinalFrame = bool1;
      if ((k & 0x8) != 0) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      isControlFrame = bool1;
      if ((isControlFrame) && (!isFinalFrame)) {
        throw new ProtocolException("Control frames must be final.");
      }
      if ((k & 0x40) != 0) {
        i = 1;
      } else {
        i = 0;
      }
      int j;
      if ((k & 0x20) != 0) {
        j = 1;
      } else {
        j = 0;
      }
      if ((k & 0x10) != 0) {
        k = 1;
      } else {
        k = 0;
      }
      if ((i == 0) && (j == 0) && (k == 0))
      {
        i = source.readByte() & 0xFF;
        bool1 = bool2;
        if ((i & 0x80) != 0) {
          bool1 = true;
        }
        isMasked = bool1;
        Object localObject1;
        if (isMasked == isClient)
        {
          if (isClient) {
            localObject1 = "Server-sent frames must not be masked.";
          } else {
            localObject1 = "Client-sent frames must be masked.";
          }
          throw new ProtocolException((String)localObject1);
        }
        frameLength = (i & 0x7F);
        if (frameLength == 126L)
        {
          frameLength = (source.readShort() & 0xFFFF);
        }
        else if (frameLength == 127L)
        {
          frameLength = source.readLong();
          if (frameLength < 0L)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Frame length 0x");
            ((StringBuilder)localObject1).append(Long.toHexString(frameLength));
            ((StringBuilder)localObject1).append(" > 0x7FFFFFFFFFFFFFFF");
            throw new ProtocolException(((StringBuilder)localObject1).toString());
          }
        }
        frameBytesRead = 0L;
        if ((isControlFrame) && (frameLength > 125L)) {
          throw new ProtocolException("Control frame must be less than 125B.");
        }
        if (isMasked) {
          source.readFully(maskKey);
        }
        return;
      }
      throw new ProtocolException("Reserved flags are unsupported.");
    }
    finally
    {
      source.timeout().timeout(l, TimeUnit.NANOSECONDS);
    }
  }
  
  private void readMessage(Buffer paramBuffer)
    throws IOException
  {
    for (;;)
    {
      if (closed) {
        throw new IOException("closed");
      }
      if (frameBytesRead == frameLength)
      {
        if (isFinalFrame) {
          return;
        }
        readUntilNonControlFrame();
        if (opcode != 0)
        {
          paramBuffer = new StringBuilder();
          paramBuffer.append("Expected continuation opcode. Got: ");
          paramBuffer.append(Integer.toHexString(opcode));
          throw new ProtocolException(paramBuffer.toString());
        }
        if ((isFinalFrame) && (frameLength == 0L)) {
          return;
        }
      }
      long l1 = frameLength - frameBytesRead;
      if (isMasked)
      {
        l1 = Math.min(l1, maskBuffer.length);
        l1 = source.read(maskBuffer, 0, (int)l1);
        if (l1 == -1L) {
          throw new EOFException();
        }
        WebSocketProtocol.toggleMask(maskBuffer, l1, maskKey, frameBytesRead);
        paramBuffer.write(maskBuffer, 0, (int)l1);
      }
      else
      {
        long l2 = source.read(paramBuffer, l1);
        l1 = l2;
        if (l2 == -1L) {
          throw new EOFException();
        }
      }
      frameBytesRead += l1;
    }
  }
  
  private void readMessageFrame()
    throws IOException
  {
    int i = opcode;
    if ((i != 1) && (i != 2))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unknown opcode: ");
      ((StringBuilder)localObject).append(Integer.toHexString(i));
      throw new ProtocolException(((StringBuilder)localObject).toString());
    }
    Object localObject = new Buffer();
    readMessage((Buffer)localObject);
    if (i == 1)
    {
      frameCallback.onReadMessage(((Buffer)localObject).readUtf8());
      return;
    }
    frameCallback.onReadMessage(((Buffer)localObject).readByteString());
  }
  
  void processNextFrame()
    throws IOException
  {
    readHeader();
    if (isControlFrame)
    {
      readControlFrame();
      return;
    }
    readMessageFrame();
  }
  
  void readUntilNonControlFrame()
    throws IOException
  {
    while (!closed)
    {
      readHeader();
      if (!isControlFrame) {
        return;
      }
      readControlFrame();
    }
  }
  
  public static abstract interface FrameCallback
  {
    public abstract void onReadClose(int paramInt, String paramString);
    
    public abstract void onReadMessage(String paramString)
      throws IOException;
    
    public abstract void onReadMessage(ByteString paramByteString)
      throws IOException;
    
    public abstract void onReadPing(ByteString paramByteString);
    
    public abstract void onReadPong(ByteString paramByteString);
  }
}

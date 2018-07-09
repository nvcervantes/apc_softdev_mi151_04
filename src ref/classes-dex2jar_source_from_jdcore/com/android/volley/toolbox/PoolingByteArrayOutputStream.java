package com.android.volley.toolbox;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public class PoolingByteArrayOutputStream
  extends ByteArrayOutputStream
{
  private static final int DEFAULT_SIZE = 256;
  private final ByteArrayPool mPool;
  
  public PoolingByteArrayOutputStream(ByteArrayPool paramByteArrayPool)
  {
    this(paramByteArrayPool, 256);
  }
  
  public PoolingByteArrayOutputStream(ByteArrayPool paramByteArrayPool, int paramInt)
  {
    mPool = paramByteArrayPool;
    buf = mPool.getBuf(Math.max(paramInt, 256));
  }
  
  private void expand(int paramInt)
  {
    if (count + paramInt <= buf.length) {
      return;
    }
    byte[] arrayOfByte = mPool.getBuf((count + paramInt) * 2);
    System.arraycopy(buf, 0, arrayOfByte, 0, count);
    mPool.returnBuf(buf);
    buf = arrayOfByte;
  }
  
  public void close()
    throws IOException
  {
    mPool.returnBuf(buf);
    buf = null;
    super.close();
  }
  
  public void finalize()
  {
    mPool.returnBuf(buf);
  }
  
  public void write(int paramInt)
  {
    try
    {
      expand(1);
      super.write(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      expand(paramInt2);
      super.write(paramArrayOfByte, paramInt1, paramInt2);
      return;
    }
    finally
    {
      paramArrayOfByte = finally;
      throw paramArrayOfByte;
    }
  }
}

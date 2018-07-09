package com.google.android.gms.internal;

import java.io.ByteArrayOutputStream;
import java.io.IOException;

public final class zzau
  extends ByteArrayOutputStream
{
  private final zzak zza;
  
  public zzau(zzak paramZzak, int paramInt)
  {
    zza = paramZzak;
    buf = zza.zza(Math.max(paramInt, 256));
  }
  
  private final void zza(int paramInt)
  {
    if (count + paramInt <= buf.length) {
      return;
    }
    byte[] arrayOfByte = zza.zza(count + paramInt << 1);
    System.arraycopy(buf, 0, arrayOfByte, 0, count);
    zza.zza(buf);
    buf = arrayOfByte;
  }
  
  public final void close()
    throws IOException
  {
    zza.zza(buf);
    buf = null;
    super.close();
  }
  
  public final void finalize()
  {
    zza.zza(buf);
  }
  
  public final void write(int paramInt)
  {
    try
    {
      zza(1);
      super.write(paramInt);
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public final void write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    try
    {
      zza(paramInt2);
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

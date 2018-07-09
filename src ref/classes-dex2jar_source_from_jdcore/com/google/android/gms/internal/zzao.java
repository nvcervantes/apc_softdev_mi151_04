package com.google.android.gms.internal;

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

final class zzao
  extends FilterInputStream
{
  private final long zza;
  private long zzb;
  
  zzao(InputStream paramInputStream, long paramLong)
  {
    super(paramInputStream);
    zza = paramLong;
  }
  
  public final int read()
    throws IOException
  {
    int i = super.read();
    if (i != -1) {
      zzb += 1L;
    }
    return i;
  }
  
  public final int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws IOException
  {
    paramInt1 = super.read(paramArrayOfByte, paramInt1, paramInt2);
    if (paramInt1 != -1) {
      zzb += paramInt1;
    }
    return paramInt1;
  }
  
  final long zza()
  {
    return zza - zzb;
  }
}

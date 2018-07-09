package com.google.android.gms.common;

import com.google.android.gms.common.internal.Hide;
import java.util.Arrays;

@Hide
final class zzi
  extends zzh
{
  private final byte[] zza;
  
  zzi(byte[] paramArrayOfByte)
  {
    super(Arrays.copyOfRange(paramArrayOfByte, 0, 25));
    zza = paramArrayOfByte;
  }
  
  final byte[] zza()
  {
    return zza;
  }
}

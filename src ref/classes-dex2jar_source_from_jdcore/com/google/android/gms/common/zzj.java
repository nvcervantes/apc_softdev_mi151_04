package com.google.android.gms.common;

import com.google.android.gms.common.internal.Hide;
import java.lang.ref.WeakReference;

@Hide
abstract class zzj
  extends zzh
{
  private static final WeakReference<byte[]> zzb = new WeakReference(null);
  private WeakReference<byte[]> zza = zzb;
  
  zzj(byte[] paramArrayOfByte)
  {
    super(paramArrayOfByte);
  }
  
  final byte[] zza()
  {
    try
    {
      byte[] arrayOfByte2 = (byte[])zza.get();
      byte[] arrayOfByte1 = arrayOfByte2;
      if (arrayOfByte2 == null)
      {
        arrayOfByte1 = zzd();
        zza = new WeakReference(arrayOfByte1);
      }
      return arrayOfByte1;
    }
    finally {}
  }
  
  protected abstract byte[] zzd();
}

package com.google.android.gms.internal;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

public final class zzak
{
  private static Comparator<byte[]> zze = new zzal();
  private final List<byte[]> zza = new LinkedList();
  private final List<byte[]> zzb = new ArrayList(64);
  private int zzc = 0;
  private final int zzd = 4096;
  
  public zzak(int paramInt) {}
  
  private final void zza()
  {
    try
    {
      while (zzc > zzd)
      {
        byte[] arrayOfByte = (byte[])zza.remove(0);
        zzb.remove(arrayOfByte);
        zzc -= arrayOfByte.length;
      }
      return;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public final void zza(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte != null) {
      try
      {
        if (paramArrayOfByte.length <= zzd)
        {
          zza.add(paramArrayOfByte);
          int j = Collections.binarySearch(zzb, paramArrayOfByte, zze);
          int i = j;
          if (j < 0) {
            i = -j - 1;
          }
          zzb.add(i, paramArrayOfByte);
          zzc += paramArrayOfByte.length;
          zza();
          return;
        }
      }
      finally {}
    }
  }
  
  public final byte[] zza(int paramInt)
  {
    int i = 0;
    try
    {
      while (i < zzb.size())
      {
        arrayOfByte = (byte[])zzb.get(i);
        if (arrayOfByte.length >= paramInt)
        {
          zzc -= arrayOfByte.length;
          zzb.remove(i);
          zza.remove(arrayOfByte);
          return arrayOfByte;
        }
        i += 1;
      }
      byte[] arrayOfByte = new byte[paramInt];
      return arrayOfByte;
    }
    finally {}
  }
}

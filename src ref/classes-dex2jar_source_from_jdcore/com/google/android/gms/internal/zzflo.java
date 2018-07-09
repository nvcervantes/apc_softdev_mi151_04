package com.google.android.gms.internal;

public final class zzflo
  implements Cloneable
{
  private static final zzflp zza = new zzflp();
  private boolean zzb = false;
  private int[] zzc;
  private zzflp[] zzd;
  private int zze;
  
  zzflo()
  {
    this(10);
  }
  
  private zzflo(int paramInt)
  {
    paramInt = zzc(paramInt);
    zzc = new int[paramInt];
    zzd = new zzflp[paramInt];
    zze = 0;
  }
  
  private static int zzc(int paramInt)
  {
    int j = paramInt << 2;
    paramInt = 4;
    int i;
    for (;;)
    {
      i = j;
      if (paramInt >= 32) {
        break;
      }
      i = (1 << paramInt) - 12;
      if (j <= i) {
        break;
      }
      paramInt += 1;
    }
    return i / 4;
  }
  
  private final int zzd(int paramInt)
  {
    int j = zze - 1;
    int i = 0;
    while (i <= j)
    {
      int k = i + j >>> 1;
      int m = zzc[k];
      if (m < paramInt) {
        i = k + 1;
      } else if (m > paramInt) {
        j = k - 1;
      } else {
        return k;
      }
    }
    return i ^ 0xFFFFFFFF;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzflo)) {
      return false;
    }
    paramObject = (zzflo)paramObject;
    if (zze != zze) {
      return false;
    }
    Object localObject = zzc;
    int[] arrayOfInt = zzc;
    int j = zze;
    int i = 0;
    while (i < j)
    {
      if (localObject[i] != arrayOfInt[i])
      {
        i = 0;
        break label83;
      }
      i += 1;
    }
    i = 1;
    label83:
    if (i != 0)
    {
      localObject = zzd;
      paramObject = zzd;
      j = zze;
      i = 0;
      while (i < j)
      {
        if (!localObject[i].equals(paramObject[i]))
        {
          i = 0;
          break label137;
        }
        i += 1;
      }
      i = 1;
      label137:
      if (i != 0) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    int j = 17;
    int i = 0;
    while (i < zze)
    {
      j = (j * 31 + zzc[i]) * 31 + zzd[i].hashCode();
      i += 1;
    }
    return j;
  }
  
  final int zza()
  {
    return zze;
  }
  
  final zzflp zza(int paramInt)
  {
    paramInt = zzd(paramInt);
    if ((paramInt >= 0) && (zzd[paramInt] != zza)) {
      return zzd[paramInt];
    }
    return null;
  }
  
  final void zza(int paramInt, zzflp paramZzflp)
  {
    int i = zzd(paramInt);
    if (i >= 0)
    {
      zzd[i] = paramZzflp;
      return;
    }
    i ^= 0xFFFFFFFF;
    if ((i < zze) && (zzd[i] == zza))
    {
      zzc[i] = paramInt;
      zzd[i] = paramZzflp;
      return;
    }
    int j;
    int[] arrayOfInt;
    Object localObject;
    if (zze >= zzc.length)
    {
      j = zzc(zze + 1);
      arrayOfInt = new int[j];
      localObject = new zzflp[j];
      System.arraycopy(zzc, 0, arrayOfInt, 0, zzc.length);
      System.arraycopy(zzd, 0, localObject, 0, zzd.length);
      zzc = arrayOfInt;
      zzd = ((zzflp[])localObject);
    }
    if (zze - i != 0)
    {
      arrayOfInt = zzc;
      localObject = zzc;
      j = i + 1;
      System.arraycopy(arrayOfInt, i, localObject, j, zze - i);
      System.arraycopy(zzd, i, zzd, j, zze - i);
    }
    zzc[i] = paramInt;
    zzd[i] = paramZzflp;
    zze += 1;
  }
  
  final zzflp zzb(int paramInt)
  {
    return zzd[paramInt];
  }
  
  public final boolean zzb()
  {
    return zze == 0;
  }
}

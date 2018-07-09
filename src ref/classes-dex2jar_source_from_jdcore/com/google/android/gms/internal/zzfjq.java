package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

final class zzfjq
  extends zzfgs
{
  private static final int[] zzb;
  private final int zzc;
  private final zzfgs zzd;
  private final zzfgs zze;
  private final int zzf;
  private final int zzg;
  
  static
  {
    ArrayList localArrayList = new ArrayList();
    int i = 1;
    int j = 1;
    for (;;)
    {
      int k = j;
      if (i <= 0) {
        break;
      }
      localArrayList.add(Integer.valueOf(i));
      j = i;
      i = k + i;
    }
    localArrayList.add(Integer.valueOf(Integer.MAX_VALUE));
    zzb = new int[localArrayList.size()];
    i = 0;
    while (i < zzb.length)
    {
      zzb[i] = ((Integer)localArrayList.get(i)).intValue();
      i += 1;
    }
  }
  
  private zzfjq(zzfgs paramZzfgs1, zzfgs paramZzfgs2)
  {
    zzd = paramZzfgs1;
    zze = paramZzfgs2;
    zzf = paramZzfgs1.zza();
    zzc = (zzf + paramZzfgs2.zza());
    zzg = (Math.max(paramZzfgs1.zze(), paramZzfgs2.zze()) + 1);
  }
  
  static zzfgs zza(zzfgs paramZzfgs1, zzfgs paramZzfgs2)
  {
    if (paramZzfgs2.zza() == 0) {
      return paramZzfgs1;
    }
    if (paramZzfgs1.zza() == 0) {
      return paramZzfgs2;
    }
    int i = paramZzfgs1.zza() + paramZzfgs2.zza();
    if (i < 128) {
      return zzb(paramZzfgs1, paramZzfgs2);
    }
    if ((paramZzfgs1 instanceof zzfjq))
    {
      zzfjq localZzfjq = (zzfjq)paramZzfgs1;
      if (zze.zza() + paramZzfgs2.zza() < 128)
      {
        paramZzfgs1 = zzb(zze, paramZzfgs2);
        return new zzfjq(zzd, paramZzfgs1);
      }
      if ((zzd.zze() > zze.zze()) && (localZzfjq.zze() > paramZzfgs2.zze()))
      {
        paramZzfgs1 = new zzfjq(zze, paramZzfgs2);
        return new zzfjq(zzd, paramZzfgs1);
      }
    }
    int j = Math.max(paramZzfgs1.zze(), paramZzfgs2.zze());
    if (i >= zzb[(j + 1)]) {
      return new zzfjq(paramZzfgs1, paramZzfgs2);
    }
    return zzfjs.zza(new zzfjs(null), paramZzfgs1, paramZzfgs2);
  }
  
  private static zzfgs zzb(zzfgs paramZzfgs1, zzfgs paramZzfgs2)
  {
    int i = paramZzfgs1.zza();
    int j = paramZzfgs2.zza();
    byte[] arrayOfByte = new byte[i + j];
    paramZzfgs1.zza(arrayOfByte, 0, 0, i);
    paramZzfgs2.zza(arrayOfByte, 0, i, j);
    return zzfgs.zzb(arrayOfByte);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzfgs)) {
      return false;
    }
    paramObject = (zzfgs)paramObject;
    if (zzc != paramObject.zza()) {
      return false;
    }
    if (zzc == 0) {
      return true;
    }
    int i = zzg();
    int j = paramObject.zzg();
    if ((i != 0) && (j != 0) && (i != j)) {
      return false;
    }
    zzfjt localZzfjt1 = new zzfjt(this, null);
    zzfgy localZzfgy = (zzfgy)localZzfjt1.next();
    zzfjt localZzfjt2 = new zzfjt(paramObject, null);
    paramObject = (zzfgy)localZzfjt2.next();
    j = 0;
    i = j;
    int k = i;
    for (;;)
    {
      int i1 = localZzfgy.zza() - j;
      int m = paramObject.zza() - i;
      int n = Math.min(i1, m);
      boolean bool;
      if (j == 0) {
        bool = localZzfgy.zza(paramObject, i, n);
      } else {
        bool = paramObject.zza(localZzfgy, j, n);
      }
      if (!bool) {
        return false;
      }
      k += n;
      if (k >= zzc)
      {
        if (k == zzc) {
          return true;
        }
        throw new IllegalStateException();
      }
      if (n == i1)
      {
        localZzfgy = (zzfgy)localZzfjt1.next();
        j = 0;
      }
      else
      {
        j += n;
      }
      if (n == m)
      {
        paramObject = (zzfgy)localZzfjt2.next();
        i = 0;
      }
      else
      {
        i += n;
      }
    }
  }
  
  public final byte zza(int paramInt)
  {
    zzb(paramInt, zzc);
    zzfgs localZzfgs;
    if (paramInt < zzf) {
      localZzfgs = zzd;
    }
    for (;;)
    {
      return localZzfgs.zza(paramInt);
      localZzfgs = zze;
      paramInt -= zzf;
    }
  }
  
  public final int zza()
  {
    return zzc;
  }
  
  protected final int zza(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt2 + paramInt3 <= zzf) {
      return zzd.zza(paramInt1, paramInt2, paramInt3);
    }
    if (paramInt2 >= zzf) {
      return zze.zza(paramInt1, paramInt2 - zzf, paramInt3);
    }
    int i = zzf - paramInt2;
    paramInt1 = zzd.zza(paramInt1, paramInt2, i);
    return zze.zza(paramInt1, 0, paramInt3 - i);
  }
  
  public final zzfgs zza(int paramInt1, int paramInt2)
  {
    int i = zzb(paramInt1, paramInt2, zzc);
    if (i == 0) {
      return zzfgs.zza;
    }
    if (i == zzc) {
      return this;
    }
    if (paramInt2 <= zzf) {
      localZzfgs = zzd;
    }
    for (;;)
    {
      return localZzfgs.zza(paramInt1, paramInt2);
      if (paramInt1 < zzf) {
        break;
      }
      localZzfgs = zze;
      paramInt1 -= zzf;
      paramInt2 -= zzf;
    }
    zzfgs localZzfgs = zzd;
    return new zzfjq(localZzfgs.zza(paramInt1, localZzfgs.zza()), zze.zza(0, paramInt2 - zzf));
  }
  
  final void zza(zzfgr paramZzfgr)
    throws IOException
  {
    zzd.zza(paramZzfgr);
    zze.zza(paramZzfgr);
  }
  
  protected final void zzb(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt1 + paramInt3 <= zzf)
    {
      zzd.zzb(paramArrayOfByte, paramInt1, paramInt2, paramInt3);
      return;
    }
    if (paramInt1 >= zzf)
    {
      zze.zzb(paramArrayOfByte, paramInt1 - zzf, paramInt2, paramInt3);
      return;
    }
    int i = zzf - paramInt1;
    zzd.zzb(paramArrayOfByte, paramInt1, paramInt2, i);
    zze.zzb(paramArrayOfByte, 0, paramInt2 + i, paramInt3 - i);
  }
  
  public final zzfhb zzd()
  {
    return zzfhb.zza(new zzfju(this));
  }
  
  protected final int zze()
  {
    return zzg;
  }
  
  protected final boolean zzf()
  {
    return zzc >= zzb[zzg];
  }
}

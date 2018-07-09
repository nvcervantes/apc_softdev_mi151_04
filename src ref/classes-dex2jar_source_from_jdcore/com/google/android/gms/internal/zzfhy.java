package com.google.android.gms.internal;

import java.util.Arrays;
import java.util.Collection;
import java.util.RandomAccess;

final class zzfhy
  extends zzfgn<Integer>
  implements zzfic, zzfjm, RandomAccess
{
  private static final zzfhy zza;
  private int[] zzb;
  private int zzc;
  
  static
  {
    zzfhy localZzfhy = new zzfhy();
    zza = localZzfhy;
    localZzfhy.zzb();
  }
  
  zzfhy()
  {
    this(new int[10], 0);
  }
  
  private zzfhy(int[] paramArrayOfInt, int paramInt)
  {
    zzb = paramArrayOfInt;
    zzc = paramInt;
  }
  
  private final void zza(int paramInt1, int paramInt2)
  {
    zzc();
    if ((paramInt1 >= 0) && (paramInt1 <= zzc))
    {
      if (zzc < zzb.length)
      {
        System.arraycopy(zzb, paramInt1, zzb, paramInt1 + 1, zzc - paramInt1);
      }
      else
      {
        int[] arrayOfInt = new int[zzc * 3 / 2 + 1];
        System.arraycopy(zzb, 0, arrayOfInt, 0, paramInt1);
        System.arraycopy(zzb, paramInt1, arrayOfInt, paramInt1 + 1, zzc - paramInt1);
        zzb = arrayOfInt;
      }
      zzb[paramInt1] = paramInt2;
      zzc += 1;
      modCount += 1;
      return;
    }
    throw new IndexOutOfBoundsException(zzf(paramInt1));
  }
  
  public static zzfhy zzd()
  {
    return zza;
  }
  
  private final void zze(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < zzc)) {
      return;
    }
    throw new IndexOutOfBoundsException(zzf(paramInt));
  }
  
  private final String zzf(int paramInt)
  {
    int i = zzc;
    StringBuilder localStringBuilder = new StringBuilder(35);
    localStringBuilder.append("Index:");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(", Size:");
    localStringBuilder.append(i);
    return localStringBuilder.toString();
  }
  
  public final boolean addAll(Collection<? extends Integer> paramCollection)
  {
    zzc();
    zzfhz.zza(paramCollection);
    if (!(paramCollection instanceof zzfhy)) {
      return super.addAll(paramCollection);
    }
    paramCollection = (zzfhy)paramCollection;
    if (zzc == 0) {
      return false;
    }
    if (Integer.MAX_VALUE - zzc < zzc) {
      throw new OutOfMemoryError();
    }
    int i = zzc + zzc;
    if (i > zzb.length) {
      zzb = Arrays.copyOf(zzb, i);
    }
    System.arraycopy(zzb, 0, zzb, zzc, zzc);
    zzc = i;
    modCount += 1;
    return true;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzfhy)) {
      return super.equals(paramObject);
    }
    paramObject = (zzfhy)paramObject;
    if (zzc != zzc) {
      return false;
    }
    paramObject = zzb;
    int i = 0;
    while (i < zzc)
    {
      if (zzb[i] != paramObject[i]) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public final int hashCode()
  {
    int j = 1;
    int i = 0;
    while (i < zzc)
    {
      j = j * 31 + zzb[i];
      i += 1;
    }
    return j;
  }
  
  public final boolean remove(Object paramObject)
  {
    zzc();
    int i = 0;
    while (i < zzc)
    {
      if (paramObject.equals(Integer.valueOf(zzb[i])))
      {
        System.arraycopy(zzb, i + 1, zzb, i, zzc - i);
        zzc -= 1;
        modCount += 1;
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public final int size()
  {
    return zzc;
  }
  
  public final zzfic zza(int paramInt)
  {
    if (paramInt < zzc) {
      throw new IllegalArgumentException();
    }
    return new zzfhy(Arrays.copyOf(zzb, paramInt), zzc);
  }
  
  public final int zzb(int paramInt)
  {
    zze(paramInt);
    return zzb[paramInt];
  }
  
  public final void zzc(int paramInt)
  {
    zza(zzc, paramInt);
  }
}

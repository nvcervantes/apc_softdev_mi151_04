package com.google.android.gms.internal;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;

public abstract class zzfgs
  implements Serializable, Iterable<Byte>
{
  public static final zzfgs zza = new zzfgz(zzfhz.zzb);
  private static final zzfgw zzb;
  private int zzc = 0;
  
  static
  {
    Object localObject;
    if (zzfgo.zza()) {
      localObject = new zzfha(null);
    } else {
      localObject = new zzfgu(null);
    }
    zzb = (zzfgw)localObject;
  }
  
  zzfgs() {}
  
  public static zzfgs zza(Iterable<zzfgs> paramIterable)
  {
    int i = ((Collection)paramIterable).size();
    if (i == 0) {
      return zza;
    }
    return zza(paramIterable.iterator(), i);
  }
  
  public static zzfgs zza(String paramString)
  {
    return new zzfgz(paramString.getBytes(zzfhz.zza));
  }
  
  private static zzfgs zza(Iterator<zzfgs> paramIterator, int paramInt)
  {
    if (paramInt <= 0) {
      throw new IllegalArgumentException(String.format("length (%s) must be >= 1", new Object[] { Integer.valueOf(paramInt) }));
    }
    if (paramInt == 1) {
      return (zzfgs)paramIterator.next();
    }
    int i = paramInt >>> 1;
    zzfgs localZzfgs = zza(paramIterator, i);
    paramIterator = zza(paramIterator, paramInt - i);
    if (Integer.MAX_VALUE - localZzfgs.zza() < paramIterator.zza())
    {
      paramInt = localZzfgs.zza();
      i = paramIterator.zza();
      paramIterator = new StringBuilder(53);
      paramIterator.append("ByteString would be too long: ");
      paramIterator.append(paramInt);
      paramIterator.append("+");
      paramIterator.append(i);
      throw new IllegalArgumentException(paramIterator.toString());
    }
    return zzfjq.zza(localZzfgs, paramIterator);
  }
  
  public static zzfgs zza(byte[] paramArrayOfByte)
  {
    return zza(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static zzfgs zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new zzfgz(zzb.zza(paramArrayOfByte, paramInt1, paramInt2));
  }
  
  static int zzb(int paramInt1, int paramInt2, int paramInt3)
  {
    int i = paramInt2 - paramInt1;
    if ((paramInt1 | paramInt2 | i | paramInt3 - paramInt2) < 0)
    {
      if (paramInt1 < 0)
      {
        localStringBuilder = new StringBuilder(32);
        localStringBuilder.append("Beginning index: ");
        localStringBuilder.append(paramInt1);
        localStringBuilder.append(" < 0");
        throw new IndexOutOfBoundsException(localStringBuilder.toString());
      }
      if (paramInt2 < paramInt1)
      {
        localStringBuilder = new StringBuilder(66);
        localStringBuilder.append("Beginning index larger than ending index: ");
        localStringBuilder.append(paramInt1);
        localStringBuilder.append(", ");
        localStringBuilder.append(paramInt2);
        throw new IndexOutOfBoundsException(localStringBuilder.toString());
      }
      StringBuilder localStringBuilder = new StringBuilder(37);
      localStringBuilder.append("End index: ");
      localStringBuilder.append(paramInt2);
      localStringBuilder.append(" >= ");
      localStringBuilder.append(paramInt3);
      throw new IndexOutOfBoundsException(localStringBuilder.toString());
    }
    return i;
  }
  
  static zzfgs zzb(byte[] paramArrayOfByte)
  {
    return new zzfgz(paramArrayOfByte);
  }
  
  static zzfgx zzb(int paramInt)
  {
    return new zzfgx(paramInt, null);
  }
  
  static void zzb(int paramInt1, int paramInt2)
  {
    if ((paramInt2 - (paramInt1 + 1) | paramInt1) < 0)
    {
      if (paramInt1 < 0)
      {
        localStringBuilder = new StringBuilder(22);
        localStringBuilder.append("Index < 0: ");
        localStringBuilder.append(paramInt1);
        throw new ArrayIndexOutOfBoundsException(localStringBuilder.toString());
      }
      StringBuilder localStringBuilder = new StringBuilder(40);
      localStringBuilder.append("Index > length: ");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append(", ");
      localStringBuilder.append(paramInt2);
      throw new ArrayIndexOutOfBoundsException(localStringBuilder.toString());
    }
  }
  
  public abstract boolean equals(Object paramObject);
  
  public final int hashCode()
  {
    int j = zzc;
    int i = j;
    if (j == 0)
    {
      i = zza();
      j = zza(i, 0, i);
      i = j;
      if (j == 0) {
        i = 1;
      }
      zzc = i;
    }
    return i;
  }
  
  public final String toString()
  {
    return String.format("<ByteString@%s size=%d>", new Object[] { Integer.toHexString(System.identityHashCode(this)), Integer.valueOf(zza()) });
  }
  
  public abstract byte zza(int paramInt);
  
  public abstract int zza();
  
  protected abstract int zza(int paramInt1, int paramInt2, int paramInt3);
  
  public abstract zzfgs zza(int paramInt1, int paramInt2);
  
  abstract void zza(zzfgr paramZzfgr)
    throws IOException;
  
  public final void zza(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    zzb(paramInt1, paramInt1 + paramInt3, zza());
    zzb(paramInt2, paramInt2 + paramInt3, paramArrayOfByte.length);
    if (paramInt3 > 0) {
      zzb(paramArrayOfByte, paramInt1, paramInt2, paramInt3);
    }
  }
  
  protected abstract void zzb(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3);
  
  public final boolean zzb()
  {
    return zza() == 0;
  }
  
  public final byte[] zzc()
  {
    int i = zza();
    if (i == 0) {
      return zzfhz.zzb;
    }
    byte[] arrayOfByte = new byte[i];
    zzb(arrayOfByte, 0, 0, i);
    return arrayOfByte;
  }
  
  public abstract zzfhb zzd();
  
  protected abstract int zze();
  
  protected abstract boolean zzf();
  
  protected final int zzg()
  {
    return zzc;
  }
}

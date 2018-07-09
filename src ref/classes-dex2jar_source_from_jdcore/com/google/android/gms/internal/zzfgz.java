package com.google.android.gms.internal;

import java.io.IOException;

class zzfgz
  extends zzfgy
{
  protected final byte[] zzb;
  
  zzfgz(byte[] paramArrayOfByte)
  {
    zzb = paramArrayOfByte;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzfgs)) {
      return false;
    }
    if (zza() != ((zzfgs)paramObject).zza()) {
      return false;
    }
    if (zza() == 0) {
      return true;
    }
    if ((paramObject instanceof zzfgz))
    {
      paramObject = (zzfgz)paramObject;
      int i = zzg();
      int j = paramObject.zzg();
      if ((i != 0) && (j != 0) && (i != j)) {
        return false;
      }
      return zza(paramObject, 0, zza());
    }
    return paramObject.equals(this);
  }
  
  public byte zza(int paramInt)
  {
    return zzb[paramInt];
  }
  
  public int zza()
  {
    return zzb.length;
  }
  
  protected final int zza(int paramInt1, int paramInt2, int paramInt3)
  {
    return zzfhz.zza(paramInt1, zzb, zzh() + paramInt2, paramInt3);
  }
  
  public final zzfgs zza(int paramInt1, int paramInt2)
  {
    paramInt2 = zzb(paramInt1, paramInt2, zza());
    if (paramInt2 == 0) {
      return zzfgs.zza;
    }
    return new zzfgv(zzb, zzh() + paramInt1, paramInt2);
  }
  
  final void zza(zzfgr paramZzfgr)
    throws IOException
  {
    paramZzfgr.zza(zzb, zzh(), zza());
  }
  
  final boolean zza(zzfgs paramZzfgs, int paramInt1, int paramInt2)
  {
    if (paramInt2 > paramZzfgs.zza())
    {
      paramInt1 = zza();
      paramZzfgs = new StringBuilder(40);
      paramZzfgs.append("Length too large: ");
      paramZzfgs.append(paramInt2);
      paramZzfgs.append(paramInt1);
      throw new IllegalArgumentException(paramZzfgs.toString());
    }
    int i = paramInt1 + paramInt2;
    if (i > paramZzfgs.zza())
    {
      i = paramZzfgs.zza();
      paramZzfgs = new StringBuilder(59);
      paramZzfgs.append("Ran off end of other: ");
      paramZzfgs.append(paramInt1);
      paramZzfgs.append(", ");
      paramZzfgs.append(paramInt2);
      paramZzfgs.append(", ");
      paramZzfgs.append(i);
      throw new IllegalArgumentException(paramZzfgs.toString());
    }
    if ((paramZzfgs instanceof zzfgz))
    {
      paramZzfgs = (zzfgz)paramZzfgs;
      byte[] arrayOfByte1 = zzb;
      byte[] arrayOfByte2 = zzb;
      int j = zzh();
      i = zzh();
      paramInt1 = paramZzfgs.zzh() + paramInt1;
      while (i < j + paramInt2)
      {
        if (arrayOfByte1[i] != arrayOfByte2[paramInt1]) {
          return false;
        }
        i += 1;
        paramInt1 += 1;
      }
      return true;
    }
    return paramZzfgs.zza(paramInt1, i).equals(zza(0, paramInt2));
  }
  
  protected void zzb(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    System.arraycopy(zzb, paramInt1, paramArrayOfByte, paramInt2, paramInt3);
  }
  
  public final zzfhb zzd()
  {
    return zzfhb.zza(zzb, zzh(), zza(), true);
  }
  
  protected int zzh()
  {
    return 0;
  }
}

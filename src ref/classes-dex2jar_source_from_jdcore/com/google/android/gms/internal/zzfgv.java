package com.google.android.gms.internal;

final class zzfgv
  extends zzfgz
{
  private final int zzc;
  private final int zzd;
  
  zzfgv(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    super(paramArrayOfByte);
    zzb(paramInt1, paramInt1 + paramInt2, paramArrayOfByte.length);
    zzc = paramInt1;
    zzd = paramInt2;
  }
  
  public final byte zza(int paramInt)
  {
    zzb(paramInt, zza());
    return zzb[(zzc + paramInt)];
  }
  
  public final int zza()
  {
    return zzd;
  }
  
  protected final void zzb(byte[] paramArrayOfByte, int paramInt1, int paramInt2, int paramInt3)
  {
    System.arraycopy(zzb, zzh() + paramInt1, paramArrayOfByte, paramInt2, paramInt3);
  }
  
  protected final int zzh()
  {
    return zzc;
  }
}

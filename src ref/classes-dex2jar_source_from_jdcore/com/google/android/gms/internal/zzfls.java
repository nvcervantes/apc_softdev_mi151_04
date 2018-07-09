package com.google.android.gms.internal;

import java.io.IOException;

public abstract class zzfls
{
  protected volatile int zzay = -1;
  
  public zzfls() {}
  
  public static final <T extends zzfls> T zza(T paramT, byte[] paramArrayOfByte)
    throws zzflr
  {
    return zza(paramT, paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  private static <T extends zzfls> T zza(T paramT, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
    throws zzflr
  {
    try
    {
      paramArrayOfByte = zzflj.zza(paramArrayOfByte, 0, paramInt2);
      paramT.zza(paramArrayOfByte);
      paramArrayOfByte.zza(0);
      return paramT;
    }
    catch (IOException paramT)
    {
      throw new RuntimeException("Reading from a byte array threw an IOException (should never happen).", paramT);
    }
    catch (zzflr paramT)
    {
      throw paramT;
    }
  }
  
  public static final byte[] zza(zzfls paramZzfls)
  {
    byte[] arrayOfByte = new byte[paramZzfls.zzf()];
    int i = arrayOfByte.length;
    try
    {
      zzflk localZzflk = zzflk.zza(arrayOfByte, 0, i);
      paramZzfls.zza(localZzflk);
      localZzflk.zza();
      return arrayOfByte;
    }
    catch (IOException paramZzfls)
    {
      throw new RuntimeException("Serializing to a byte array threw an IOException (should never happen).", paramZzfls);
    }
  }
  
  public String toString()
  {
    return zzflt.zza(this);
  }
  
  protected int zza()
  {
    return 0;
  }
  
  public abstract zzfls zza(zzflj paramZzflj)
    throws IOException;
  
  public void zza(zzflk paramZzflk)
    throws IOException
  {}
  
  public zzfls zzd()
    throws CloneNotSupportedException
  {
    return (zzfls)super.clone();
  }
  
  public final int zze()
  {
    if (zzay < 0) {
      zzf();
    }
    return zzay;
  }
  
  public final int zzf()
  {
    int i = zza();
    zzay = i;
    return i;
  }
}

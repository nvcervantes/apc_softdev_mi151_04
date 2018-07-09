package com.google.android.gms.internal;

public class zzae
  extends Exception
{
  private zzp zza;
  private long zzb;
  
  public zzae()
  {
    zza = null;
  }
  
  public zzae(zzp paramZzp)
  {
    zza = paramZzp;
  }
  
  public zzae(String paramString)
  {
    super(paramString);
    zza = null;
  }
  
  public zzae(Throwable paramThrowable)
  {
    super(paramThrowable);
    zza = null;
  }
  
  final void zza(long paramLong)
  {
    zzb = paramLong;
  }
}

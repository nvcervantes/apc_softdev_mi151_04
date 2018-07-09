package com.google.android.gms.internal;

public class zzfik
{
  private static final zzfhm zza = ;
  private zzfgs zzb;
  private volatile zzfjc zzc;
  private volatile zzfgs zzd;
  
  public zzfik() {}
  
  private zzfjc zzb(zzfjc paramZzfjc)
  {
    if (zzc == null) {
      for (;;)
      {
        try
        {
          if (zzc == null) {}
        }
        finally {}
        try
        {
          zzc = paramZzfjc;
          zzd = zzfgs.zza;
        }
        catch (zzfie localZzfie)
        {
          continue;
        }
        zzc = paramZzfjc;
        zzd = zzfgs.zza;
      }
    }
    return zzc;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzfik)) {
      return false;
    }
    paramObject = (zzfik)paramObject;
    zzfjc localZzfjc1 = zzc;
    zzfjc localZzfjc2 = zzc;
    if ((localZzfjc1 == null) && (localZzfjc2 == null)) {
      return zzc().equals(paramObject.zzc());
    }
    if ((localZzfjc1 != null) && (localZzfjc2 != null)) {
      return localZzfjc1.equals(localZzfjc2);
    }
    if (localZzfjc1 != null) {
      return localZzfjc1.equals(paramObject.zzb(localZzfjc1.zzw()));
    }
    return zzb(localZzfjc2.zzw()).equals(localZzfjc2);
  }
  
  public int hashCode()
  {
    return 1;
  }
  
  public final zzfjc zza(zzfjc paramZzfjc)
  {
    zzfjc localZzfjc = zzc;
    zzb = null;
    zzd = null;
    zzc = paramZzfjc;
    return localZzfjc;
  }
  
  public final int zzb()
  {
    if (zzd != null) {
      return zzd.zza();
    }
    if (zzc != null) {
      return zzc.zza();
    }
    return 0;
  }
  
  public final zzfgs zzc()
  {
    if (zzd != null) {
      return zzd;
    }
    try
    {
      if (zzd != null)
      {
        localZzfgs = zzd;
        return localZzfgs;
      }
      if (zzc == null) {}
      for (zzfgs localZzfgs = zzfgs.zza;; localZzfgs = zzc.zzp())
      {
        zzd = localZzfgs;
        break;
      }
      localZzfgs = zzd;
      return localZzfgs;
    }
    finally {}
  }
}

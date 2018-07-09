package com.google.android.gms.common.internal;

import com.google.android.gms.internal.zzbgl;

public abstract class DowngradeableSafeParcel
  extends zzbgl
  implements ReflectedParcelable
{
  private static final Object zza = new Object();
  private static ClassLoader zzb;
  private static Integer zzc;
  private boolean zzd = false;
  
  public DowngradeableSafeParcel() {}
  
  @Hide
  protected static Integer o_()
  {
    synchronized (zza)
    {
      return null;
    }
  }
  
  @Hide
  protected static boolean zza(String paramString)
  {
    zzb();
    return true;
  }
  
  @Hide
  private static ClassLoader zzb()
  {
    synchronized (zza)
    {
      return null;
    }
  }
}

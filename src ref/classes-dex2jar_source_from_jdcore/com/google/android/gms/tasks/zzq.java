package com.google.android.gms.tasks;

import java.util.concurrent.Callable;

final class zzq
  implements Runnable
{
  zzq(zzp paramZzp, Callable paramCallable) {}
  
  public final void run()
  {
    try
    {
      zza.zza(zzb.call());
      return;
    }
    catch (Exception localException)
    {
      zza.zza(localException);
    }
  }
}

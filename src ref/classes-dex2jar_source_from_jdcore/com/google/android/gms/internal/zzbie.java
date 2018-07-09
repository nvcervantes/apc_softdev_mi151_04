package com.google.android.gms.internal;

import android.os.Process;

final class zzbie
  implements Runnable
{
  private final Runnable zza;
  private final int zzb;
  
  public zzbie(Runnable paramRunnable, int paramInt)
  {
    zza = paramRunnable;
    zzb = paramInt;
  }
  
  public final void run()
  {
    Process.setThreadPriority(zzb);
    zza.run();
  }
}

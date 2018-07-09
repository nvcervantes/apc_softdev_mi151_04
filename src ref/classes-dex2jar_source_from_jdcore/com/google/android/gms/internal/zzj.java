package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

final class zzj
  implements Executor
{
  zzj(zzi paramZzi, Handler paramHandler) {}
  
  public final void execute(Runnable paramRunnable)
  {
    zza.post(paramRunnable);
  }
}

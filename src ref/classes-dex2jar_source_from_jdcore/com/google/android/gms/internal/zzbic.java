package com.google.android.gms.internal;

import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import java.util.concurrent.Executor;

public final class zzbic
  implements Executor
{
  private final Handler zza;
  
  public zzbic(Looper paramLooper)
  {
    zza = new Handler(paramLooper);
  }
  
  public final void execute(@NonNull Runnable paramRunnable)
  {
    zza.post(paramRunnable);
  }
}

package com.google.android.gms.internal;

import android.os.Handler;
import java.util.concurrent.Executor;

public final class zzi
  implements zzaa
{
  private final Executor zza;
  
  public zzi(Handler paramHandler)
  {
    zza = new zzj(this, paramHandler);
  }
  
  public final void zza(zzr<?> paramZzr, zzae paramZzae)
  {
    paramZzr.zza("post-error");
    paramZzae = zzx.zza(paramZzae);
    zza.execute(new zzk(this, paramZzr, paramZzae, null));
  }
  
  public final void zza(zzr<?> paramZzr, zzx<?> paramZzx)
  {
    zza(paramZzr, paramZzx, null);
  }
  
  public final void zza(zzr<?> paramZzr, zzx<?> paramZzx, Runnable paramRunnable)
  {
    paramZzr.zzk();
    paramZzr.zza("post-response");
    zza.execute(new zzk(this, paramZzr, paramZzx, paramRunnable));
  }
}

package com.google.android.gms.common.api.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.app.Application;
import android.app.Application.ActivityLifecycleCallbacks;
import android.content.ComponentCallbacks2;
import android.content.res.Configuration;
import android.os.Bundle;
import com.google.android.gms.common.util.zzs;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

public final class zzk
  implements Application.ActivityLifecycleCallbacks, ComponentCallbacks2
{
  private static final zzk zza = new zzk();
  private final AtomicBoolean zzb = new AtomicBoolean();
  private final AtomicBoolean zzc = new AtomicBoolean();
  private final ArrayList<zzl> zzd = new ArrayList();
  private boolean zze = false;
  
  private zzk() {}
  
  public static zzk zza()
  {
    return zza;
  }
  
  public static void zza(Application paramApplication)
  {
    synchronized (zza)
    {
      if (!zzazze)
      {
        paramApplication.registerActivityLifecycleCallbacks(zza);
        paramApplication.registerComponentCallbacks(zza);
        zzazze = true;
      }
      return;
    }
  }
  
  private final void zzb(boolean paramBoolean)
  {
    synchronized (zza)
    {
      ArrayList localArrayList = (ArrayList)zzd;
      int j = localArrayList.size();
      int i = 0;
      while (i < j)
      {
        Object localObject2 = localArrayList.get(i);
        i += 1;
        ((zzl)localObject2).zza(paramBoolean);
      }
      return;
    }
  }
  
  public final void onActivityCreated(Activity paramActivity, Bundle paramBundle)
  {
    boolean bool = zzb.compareAndSet(true, false);
    zzc.set(true);
    if (bool) {
      zzb(false);
    }
  }
  
  public final void onActivityDestroyed(Activity paramActivity) {}
  
  public final void onActivityPaused(Activity paramActivity) {}
  
  public final void onActivityResumed(Activity paramActivity)
  {
    boolean bool = zzb.compareAndSet(true, false);
    zzc.set(true);
    if (bool) {
      zzb(false);
    }
  }
  
  public final void onActivitySaveInstanceState(Activity paramActivity, Bundle paramBundle) {}
  
  public final void onActivityStarted(Activity paramActivity) {}
  
  public final void onActivityStopped(Activity paramActivity) {}
  
  public final void onConfigurationChanged(Configuration paramConfiguration) {}
  
  public final void onLowMemory() {}
  
  public final void onTrimMemory(int paramInt)
  {
    if ((paramInt == 20) && (zzb.compareAndSet(false, true)))
    {
      zzc.set(true);
      zzb(true);
    }
  }
  
  public final void zza(zzl paramZzl)
  {
    synchronized (zza)
    {
      zzd.add(paramZzl);
      return;
    }
  }
  
  @TargetApi(16)
  public final boolean zza(boolean paramBoolean)
  {
    if (!zzc.get()) {
      if (zzs.zzb())
      {
        ActivityManager.RunningAppProcessInfo localRunningAppProcessInfo = new ActivityManager.RunningAppProcessInfo();
        ActivityManager.getMyMemoryState(localRunningAppProcessInfo);
        if ((!zzc.getAndSet(true)) && (importance > 100)) {
          zzb.set(true);
        }
      }
      else
      {
        return true;
      }
    }
    return zzb.get();
  }
  
  public final boolean zzb()
  {
    return zzb.get();
  }
}

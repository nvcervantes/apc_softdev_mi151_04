package com.google.android.gms.common.api.internal;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArrayMap;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.ref.WeakReference;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

public final class zzcg
  extends Fragment
  implements zzcf
{
  private static WeakHashMap<Activity, WeakReference<zzcg>> zza = new WeakHashMap();
  private Map<String, LifecycleCallback> zzb = new ArrayMap();
  private int zzc = 0;
  private Bundle zzd;
  
  public zzcg() {}
  
  public static zzcg zza(Activity paramActivity)
  {
    Object localObject = (WeakReference)zza.get(paramActivity);
    if (localObject != null)
    {
      localObject = (zzcg)((WeakReference)localObject).get();
      if (localObject != null) {
        return localObject;
      }
    }
    try
    {
      zzcg localZzcg = (zzcg)paramActivity.getFragmentManager().findFragmentByTag("LifecycleFragmentImpl");
      if (localZzcg != null)
      {
        localObject = localZzcg;
        if (!localZzcg.isRemoving()) {}
      }
      else
      {
        localObject = new zzcg();
        paramActivity.getFragmentManager().beginTransaction().add((Fragment)localObject, "LifecycleFragmentImpl").commitAllowingStateLoss();
      }
      zza.put(paramActivity, new WeakReference(localObject));
      return localObject;
    }
    catch (ClassCastException paramActivity)
    {
      throw new IllegalStateException("Fragment with tag LifecycleFragmentImpl is not a LifecycleFragmentImpl", paramActivity);
    }
  }
  
  public final void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    Iterator localIterator = zzb.values().iterator();
    while (localIterator.hasNext()) {
      ((LifecycleCallback)localIterator.next()).zza(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  public final void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    super.onActivityResult(paramInt1, paramInt2, paramIntent);
    Iterator localIterator = zzb.values().iterator();
    while (localIterator.hasNext()) {
      ((LifecycleCallback)localIterator.next()).zza(paramInt1, paramInt2, paramIntent);
    }
  }
  
  public final void onCreate(Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    zzc = 1;
    zzd = paramBundle;
    Iterator localIterator = zzb.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = (Map.Entry)localIterator.next();
      LifecycleCallback localLifecycleCallback = (LifecycleCallback)((Map.Entry)localObject).getValue();
      if (paramBundle != null) {
        localObject = paramBundle.getBundle((String)((Map.Entry)localObject).getKey());
      } else {
        localObject = null;
      }
      localLifecycleCallback.zza((Bundle)localObject);
    }
  }
  
  public final void onDestroy()
  {
    super.onDestroy();
    zzc = 5;
    Iterator localIterator = zzb.values().iterator();
    while (localIterator.hasNext()) {
      ((LifecycleCallback)localIterator.next()).zzh();
    }
  }
  
  public final void onResume()
  {
    super.onResume();
    zzc = 3;
    Iterator localIterator = zzb.values().iterator();
    while (localIterator.hasNext()) {
      ((LifecycleCallback)localIterator.next()).zze();
    }
  }
  
  public final void onSaveInstanceState(Bundle paramBundle)
  {
    super.onSaveInstanceState(paramBundle);
    if (paramBundle == null) {
      return;
    }
    Iterator localIterator = zzb.entrySet().iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      Bundle localBundle = new Bundle();
      ((LifecycleCallback)localEntry.getValue()).zzb(localBundle);
      paramBundle.putBundle((String)localEntry.getKey(), localBundle);
    }
  }
  
  public final void onStart()
  {
    super.onStart();
    zzc = 2;
    Iterator localIterator = zzb.values().iterator();
    while (localIterator.hasNext()) {
      ((LifecycleCallback)localIterator.next()).zza();
    }
  }
  
  public final void onStop()
  {
    super.onStop();
    zzc = 4;
    Iterator localIterator = zzb.values().iterator();
    while (localIterator.hasNext()) {
      ((LifecycleCallback)localIterator.next()).zzb();
    }
  }
  
  public final Activity zza()
  {
    return getActivity();
  }
  
  public final <T extends LifecycleCallback> T zza(String paramString, Class<T> paramClass)
  {
    return (LifecycleCallback)paramClass.cast(zzb.get(paramString));
  }
  
  public final void zza(String paramString, @NonNull LifecycleCallback paramLifecycleCallback)
  {
    if (!zzb.containsKey(paramString))
    {
      zzb.put(paramString, paramLifecycleCallback);
      if (zzc > 0) {
        new Handler(Looper.getMainLooper()).post(new zzch(this, paramLifecycleCallback, paramString));
      }
      return;
    }
    paramLifecycleCallback = new StringBuilder(59 + String.valueOf(paramString).length());
    paramLifecycleCallback.append("LifecycleCallback with tag ");
    paramLifecycleCallback.append(paramString);
    paramLifecycleCallback.append(" already added to this fragment.");
    throw new IllegalArgumentException(paramLifecycleCallback.toString());
  }
}

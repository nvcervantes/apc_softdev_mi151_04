package com.google.android.gms.dynamic;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzu;
import java.util.LinkedList;

@Hide
public abstract class zza<T extends LifecycleDelegate>
{
  private T zza;
  private Bundle zzb;
  private LinkedList<zzi> zzc;
  private final zzo<T> zzd = new zzb(this);
  
  public zza() {}
  
  private final void zza(int paramInt)
  {
    while ((!zzc.isEmpty()) && (((zzi)zzc.getLast()).zza() >= paramInt)) {
      zzc.removeLast();
    }
  }
  
  private final void zza(Bundle paramBundle, zzi paramZzi)
  {
    if (zza != null)
    {
      paramZzi.zza(zza);
      return;
    }
    if (zzc == null) {
      zzc = new LinkedList();
    }
    zzc.add(paramZzi);
    if (paramBundle != null) {
      if (zzb == null) {
        zzb = ((Bundle)paramBundle.clone());
      } else {
        zzb.putAll(paramBundle);
      }
    }
    zza(zzd);
  }
  
  public static void zzb(FrameLayout paramFrameLayout)
  {
    Object localObject1 = GoogleApiAvailability.getInstance();
    Context localContext = paramFrameLayout.getContext();
    int i = ((com.google.android.gms.common.zzf)localObject1).isGooglePlayServicesAvailable(localContext);
    Object localObject2 = zzu.zzc(localContext, i);
    localObject1 = zzu.zze(localContext, i);
    LinearLayout localLinearLayout = new LinearLayout(paramFrameLayout.getContext());
    localLinearLayout.setOrientation(1);
    localLinearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.addView(localLinearLayout);
    paramFrameLayout = new TextView(paramFrameLayout.getContext());
    paramFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.setText((CharSequence)localObject2);
    localLinearLayout.addView(paramFrameLayout);
    paramFrameLayout = com.google.android.gms.common.zzf.zza(localContext, i, null);
    if (paramFrameLayout != null)
    {
      localObject2 = new Button(localContext);
      ((Button)localObject2).setId(16908313);
      ((Button)localObject2).setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
      ((Button)localObject2).setText((CharSequence)localObject1);
      localLinearLayout.addView((View)localObject2);
      ((Button)localObject2).setOnClickListener(new zzf(localContext, paramFrameLayout));
    }
  }
  
  public final View zza(LayoutInflater paramLayoutInflater, ViewGroup paramViewGroup, Bundle paramBundle)
  {
    FrameLayout localFrameLayout = new FrameLayout(paramLayoutInflater.getContext());
    zza(paramBundle, new zze(this, localFrameLayout, paramLayoutInflater, paramViewGroup, paramBundle));
    if (zza == null) {
      zza(localFrameLayout);
    }
    return localFrameLayout;
  }
  
  public final T zza()
  {
    return zza;
  }
  
  public final void zza(Activity paramActivity, Bundle paramBundle1, Bundle paramBundle2)
  {
    zza(paramBundle2, new zzc(this, paramActivity, paramBundle1, paramBundle2));
  }
  
  public final void zza(Bundle paramBundle)
  {
    zza(paramBundle, new zzd(this, paramBundle));
  }
  
  protected void zza(FrameLayout paramFrameLayout)
  {
    Object localObject1 = GoogleApiAvailability.getInstance();
    Context localContext = paramFrameLayout.getContext();
    int i = ((com.google.android.gms.common.zzf)localObject1).isGooglePlayServicesAvailable(localContext);
    Object localObject2 = zzu.zzc(localContext, i);
    localObject1 = zzu.zze(localContext, i);
    LinearLayout localLinearLayout = new LinearLayout(paramFrameLayout.getContext());
    localLinearLayout.setOrientation(1);
    localLinearLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.addView(localLinearLayout);
    paramFrameLayout = new TextView(paramFrameLayout.getContext());
    paramFrameLayout.setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
    paramFrameLayout.setText((CharSequence)localObject2);
    localLinearLayout.addView(paramFrameLayout);
    paramFrameLayout = com.google.android.gms.common.zzf.zza(localContext, i, null);
    if (paramFrameLayout != null)
    {
      localObject2 = new Button(localContext);
      ((Button)localObject2).setId(16908313);
      ((Button)localObject2).setLayoutParams(new FrameLayout.LayoutParams(-2, -2));
      ((Button)localObject2).setText((CharSequence)localObject1);
      localLinearLayout.addView((View)localObject2);
      ((Button)localObject2).setOnClickListener(new zzf(localContext, paramFrameLayout));
    }
  }
  
  protected abstract void zza(zzo<T> paramZzo);
  
  public final void zzb()
  {
    zza(null, new zzg(this));
  }
  
  public final void zzb(Bundle paramBundle)
  {
    if (zza != null)
    {
      zza.onSaveInstanceState(paramBundle);
      return;
    }
    if (zzb != null) {
      paramBundle.putAll(zzb);
    }
  }
  
  public final void zzc()
  {
    zza(null, new zzh(this));
  }
  
  public final void zzd()
  {
    if (zza != null)
    {
      zza.onPause();
      return;
    }
    zza(5);
  }
  
  public final void zze()
  {
    if (zza != null)
    {
      zza.onStop();
      return;
    }
    zza(4);
  }
  
  public final void zzf()
  {
    if (zza != null)
    {
      zza.onDestroyView();
      return;
    }
    zza(2);
  }
  
  public final void zzg()
  {
    if (zza != null)
    {
      zza.onDestroy();
      return;
    }
    zza(1);
  }
  
  public final void zzh()
  {
    if (zza != null) {
      zza.onLowMemory();
    }
  }
}

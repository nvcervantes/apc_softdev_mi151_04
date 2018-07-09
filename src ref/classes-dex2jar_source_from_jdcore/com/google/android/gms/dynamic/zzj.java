package com.google.android.gms.dynamic;

import android.annotation.SuppressLint;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.google.android.gms.common.internal.Hide;

@SuppressLint({"NewApi"})
@Hide
public final class zzj
  extends zzl
{
  private Fragment zza;
  
  private zzj(Fragment paramFragment)
  {
    zza = paramFragment;
  }
  
  public static zzj zza(Fragment paramFragment)
  {
    if (paramFragment != null) {
      return new zzj(paramFragment);
    }
    return null;
  }
  
  public final IObjectWrapper zza()
  {
    return zzn.zza(zza.getActivity());
  }
  
  public final void zza(Intent paramIntent)
  {
    zza.startActivity(paramIntent);
  }
  
  public final void zza(Intent paramIntent, int paramInt)
  {
    zza.startActivityForResult(paramIntent, paramInt);
  }
  
  public final void zza(IObjectWrapper paramIObjectWrapper)
  {
    paramIObjectWrapper = (View)zzn.zza(paramIObjectWrapper);
    zza.registerForContextMenu(paramIObjectWrapper);
  }
  
  public final void zza(boolean paramBoolean)
  {
    zza.setHasOptionsMenu(paramBoolean);
  }
  
  public final Bundle zzb()
  {
    return zza.getArguments();
  }
  
  public final void zzb(IObjectWrapper paramIObjectWrapper)
  {
    paramIObjectWrapper = (View)zzn.zza(paramIObjectWrapper);
    zza.unregisterForContextMenu(paramIObjectWrapper);
  }
  
  public final void zzb(boolean paramBoolean)
  {
    zza.setMenuVisibility(paramBoolean);
  }
  
  public final int zzc()
  {
    return zza.getId();
  }
  
  public final void zzc(boolean paramBoolean)
  {
    zza.setRetainInstance(paramBoolean);
  }
  
  public final zzk zzd()
  {
    return zza(zza.getParentFragment());
  }
  
  public final void zzd(boolean paramBoolean)
  {
    zza.setUserVisibleHint(paramBoolean);
  }
  
  public final IObjectWrapper zze()
  {
    return zzn.zza(zza.getResources());
  }
  
  public final boolean zzf()
  {
    return zza.getRetainInstance();
  }
  
  public final String zzg()
  {
    return zza.getTag();
  }
  
  public final zzk zzh()
  {
    return zza(zza.getTargetFragment());
  }
  
  public final int zzi()
  {
    return zza.getTargetRequestCode();
  }
  
  public final boolean zzj()
  {
    return zza.getUserVisibleHint();
  }
  
  public final IObjectWrapper zzk()
  {
    return zzn.zza(zza.getView());
  }
  
  public final boolean zzl()
  {
    return zza.isAdded();
  }
  
  public final boolean zzm()
  {
    return zza.isDetached();
  }
  
  public final boolean zzn()
  {
    return zza.isHidden();
  }
  
  public final boolean zzo()
  {
    return zza.isInLayout();
  }
  
  public final boolean zzp()
  {
    return zza.isRemoving();
  }
  
  public final boolean zzq()
  {
    return zza.isResumed();
  }
  
  public final boolean zzr()
  {
    return zza.isVisible();
  }
}

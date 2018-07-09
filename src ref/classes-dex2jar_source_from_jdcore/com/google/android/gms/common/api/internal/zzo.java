package com.google.android.gms.common.api.internal;

import android.app.PendingIntent;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.zzf;
import java.util.concurrent.atomic.AtomicReference;

public abstract class zzo
  extends LifecycleCallback
  implements DialogInterface.OnCancelListener
{
  protected volatile boolean zza;
  protected final AtomicReference<zzp> zzb = new AtomicReference(null);
  protected final GoogleApiAvailability zzc;
  private final Handler zze = new Handler(Looper.getMainLooper());
  
  protected zzo(zzcf paramZzcf)
  {
    this(paramZzcf, GoogleApiAvailability.getInstance());
  }
  
  private zzo(zzcf paramZzcf, GoogleApiAvailability paramGoogleApiAvailability)
  {
    super(paramZzcf);
    zzc = paramGoogleApiAvailability;
  }
  
  private static int zza(@Nullable zzp paramZzp)
  {
    if (paramZzp == null) {
      return -1;
    }
    return paramZzp.zza();
  }
  
  public void onCancel(DialogInterface paramDialogInterface)
  {
    zza(new ConnectionResult(13, null), zza((zzp)zzb.get()));
    zzd();
  }
  
  public void zza()
  {
    super.zza();
    zza = true;
  }
  
  public final void zza(int paramInt1, int paramInt2, Intent paramIntent)
  {
    zzp localZzp2 = (zzp)zzb.get();
    int j = 1;
    int i = 1;
    zzp localZzp1;
    switch (paramInt1)
    {
    default: 
      localZzp1 = localZzp2;
      break;
    case 2: 
      j = zzc.isGooglePlayServicesAvailable(zzg());
      if (j == 0) {
        paramInt1 = i;
      } else {
        paramInt1 = 0;
      }
      if (localZzp2 == null) {
        return;
      }
      localZzp1 = localZzp2;
      paramInt2 = paramInt1;
      if (localZzp2.zzb().getErrorCode() != 18) {
        break label185;
      }
      localZzp1 = localZzp2;
      paramInt2 = paramInt1;
      if (j != 18) {
        break label185;
      }
      return;
    case 1: 
      if (paramInt2 == -1)
      {
        localZzp1 = localZzp2;
        paramInt2 = j;
        break label185;
      }
      localZzp1 = localZzp2;
      if (paramInt2 == 0)
      {
        paramInt1 = 13;
        if (paramIntent != null) {
          paramInt1 = paramIntent.getIntExtra("<<ResolutionFailureErrorDetail>>", 13);
        }
        localZzp1 = new zzp(new ConnectionResult(paramInt1, null), zza(localZzp2));
        zzb.set(localZzp1);
      }
      break;
    }
    paramInt2 = 0;
    label185:
    if (paramInt2 != 0)
    {
      zzd();
      return;
    }
    if (localZzp1 != null) {
      zza(localZzp1.zzb(), localZzp1.zza());
    }
  }
  
  public final void zza(Bundle paramBundle)
  {
    super.zza(paramBundle);
    if (paramBundle != null)
    {
      AtomicReference localAtomicReference = zzb;
      if (paramBundle.getBoolean("resolving_error", false)) {
        paramBundle = new zzp(new ConnectionResult(paramBundle.getInt("failed_status"), (PendingIntent)paramBundle.getParcelable("failed_resolution")), paramBundle.getInt("failed_client_id", -1));
      } else {
        paramBundle = null;
      }
      localAtomicReference.set(paramBundle);
    }
  }
  
  protected abstract void zza(ConnectionResult paramConnectionResult, int paramInt);
  
  public void zzb()
  {
    super.zzb();
    zza = false;
  }
  
  public final void zzb(Bundle paramBundle)
  {
    super.zzb(paramBundle);
    zzp localZzp = (zzp)zzb.get();
    if (localZzp != null)
    {
      paramBundle.putBoolean("resolving_error", true);
      paramBundle.putInt("failed_client_id", localZzp.zza());
      paramBundle.putInt("failed_status", localZzp.zzb().getErrorCode());
      paramBundle.putParcelable("failed_resolution", localZzp.zzb().getResolution());
    }
  }
  
  public final void zzb(ConnectionResult paramConnectionResult, int paramInt)
  {
    paramConnectionResult = new zzp(paramConnectionResult, paramInt);
    if (zzb.compareAndSet(null, paramConnectionResult)) {
      zze.post(new zzq(this, paramConnectionResult));
    }
  }
  
  protected abstract void zzc();
  
  protected final void zzd()
  {
    zzb.set(null);
    zzc();
  }
}

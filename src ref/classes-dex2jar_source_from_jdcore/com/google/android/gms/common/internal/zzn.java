package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;

@Hide
public final class zzn
  extends zze
{
  private IBinder zza;
  
  @BinderThread
  public zzn(zzd paramZzd, int paramInt, IBinder paramIBinder, Bundle paramBundle)
  {
    super(paramZzd, paramInt, paramBundle);
    zza = paramIBinder;
  }
  
  protected final void zza(ConnectionResult paramConnectionResult)
  {
    if (zzd.zzg(zzb) != null) {
      zzd.zzg(zzb).zza(paramConnectionResult);
    }
    zzb.zza(paramConnectionResult);
  }
  
  protected final boolean zza()
  {
    boolean bool2 = false;
    try
    {
      Object localObject = zza.getInterfaceDescriptor();
      if (!zzb.zzb().equals(localObject))
      {
        String str = zzb.zzb();
        StringBuilder localStringBuilder = new StringBuilder(34 + String.valueOf(str).length() + String.valueOf(localObject).length());
        localStringBuilder.append("service descriptor mismatch: ");
        localStringBuilder.append(str);
        localStringBuilder.append(" vs. ");
        localStringBuilder.append((String)localObject);
        Log.e("GmsClient", localStringBuilder.toString());
        return false;
      }
      localObject = zzb.zza(zza);
      boolean bool1 = bool2;
      if (localObject != null) {
        if (!zzd.zza(zzb, 2, 4, (IInterface)localObject))
        {
          bool1 = bool2;
          if (!zzd.zza(zzb, 3, 4, (IInterface)localObject)) {}
        }
        else
        {
          zzd.zza(zzb, null);
          localObject = zzb.q_();
          if (zzd.zze(zzb) != null) {
            zzd.zze(zzb).zza((Bundle)localObject);
          }
          bool1 = true;
        }
      }
      return bool1;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
    Log.w("GmsClient", "service probably died");
    return false;
  }
}

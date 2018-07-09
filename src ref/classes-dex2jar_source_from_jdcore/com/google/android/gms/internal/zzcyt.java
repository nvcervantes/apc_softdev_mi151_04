package com.google.android.gms.internal;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.internal.zzaa;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzab;
import com.google.android.gms.common.internal.zzan;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzbr;
import com.google.android.gms.common.internal.zzd;
import com.google.android.gms.common.internal.zzm;
import com.google.android.gms.common.internal.zzr;

public final class zzcyt
  extends zzab<zzcyr>
  implements zzcyj
{
  private final boolean zzd = true;
  private final zzr zze;
  private final Bundle zzf;
  private Integer zzg;
  
  private zzcyt(Context paramContext, Looper paramLooper, boolean paramBoolean, zzr paramZzr, Bundle paramBundle, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 44, paramZzr, paramConnectionCallbacks, paramOnConnectionFailedListener);
    zze = paramZzr;
    zzf = paramBundle;
    zzg = paramZzr.zzl();
  }
  
  public zzcyt(Context paramContext, Looper paramLooper, boolean paramBoolean, zzr paramZzr, zzcyk paramZzcyk, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this(paramContext, paramLooper, true, paramZzr, zza(paramZzr), paramConnectionCallbacks, paramOnConnectionFailedListener);
  }
  
  public static Bundle zza(zzr paramZzr)
  {
    zzcyk localZzcyk = paramZzr.zzk();
    Integer localInteger = paramZzr.zzl();
    Bundle localBundle = new Bundle();
    localBundle.putParcelable("com.google.android.gms.signin.internal.clientRequestedAccount", paramZzr.zzb());
    if (localInteger != null) {
      localBundle.putInt("com.google.android.gms.common.internal.ClientSettings.sessionId", localInteger.intValue());
    }
    if (localZzcyk != null)
    {
      localBundle.putBoolean("com.google.android.gms.signin.internal.offlineAccessRequested", localZzcyk.zza());
      localBundle.putBoolean("com.google.android.gms.signin.internal.idTokenRequested", localZzcyk.zzb());
      localBundle.putString("com.google.android.gms.signin.internal.serverClientId", localZzcyk.zzc());
      localBundle.putBoolean("com.google.android.gms.signin.internal.usePromptModeForAuthCode", true);
      localBundle.putBoolean("com.google.android.gms.signin.internal.forceCodeForRefreshToken", localZzcyk.zzd());
      localBundle.putString("com.google.android.gms.signin.internal.hostedDomain", localZzcyk.zze());
      localBundle.putBoolean("com.google.android.gms.signin.internal.waitForAccessTokenRefresh", localZzcyk.zzf());
      if (localZzcyk.zzg() != null) {
        localBundle.putLong("com.google.android.gms.signin.internal.authApiSignInModuleVersion", localZzcyk.zzg().longValue());
      }
      if (localZzcyk.zzh() != null) {
        localBundle.putLong("com.google.android.gms.signin.internal.realClientLibraryVersion", localZzcyk.zzh().longValue());
      }
    }
    return localBundle;
  }
  
  public final boolean l_()
  {
    return zzd;
  }
  
  protected final String zza()
  {
    return "com.google.android.gms.signin.service.START";
  }
  
  public final void zza(zzan paramZzan, boolean paramBoolean)
  {
    try
    {
      ((zzcyr)zzaf()).zza(paramZzan, zzg.intValue(), paramBoolean);
      return;
    }
    catch (RemoteException paramZzan)
    {
      for (;;) {}
    }
    Log.w("SignInClientImpl", "Remote service probably died when saveDefaultAccount is called");
  }
  
  public final void zza(zzcyp paramZzcyp)
  {
    zzbq.zza(paramZzcyp, "Expecting a valid ISignInCallbacks");
    try
    {
      Account localAccount = zze.zzc();
      Object localObject = null;
      if ("<<default account>>".equals(name)) {
        localObject = zzaa.zza(zzaa()).zza();
      }
      localObject = new zzbr(localAccount, zzg.intValue(), (GoogleSignInAccount)localObject);
      ((zzcyr)zzaf()).zza(new zzcyu((zzbr)localObject), paramZzcyp);
      return;
    }
    catch (RemoteException localRemoteException)
    {
      Log.w("SignInClientImpl", "Remote service probably died when signIn is called");
    }
    try
    {
      paramZzcyp.zza(new zzcyw(8));
      return;
    }
    catch (RemoteException paramZzcyp)
    {
      for (;;) {}
    }
    Log.wtf("SignInClientImpl", "ISignInCallbacks#onSignInComplete should be executed from the same process, unexpected RemoteException.", localRemoteException);
  }
  
  protected final String zzb()
  {
    return "com.google.android.gms.signin.internal.ISignInService";
  }
  
  protected final Bundle zzc()
  {
    String str = zze.zzh();
    if (!zzaa().getPackageName().equals(str)) {
      zzf.putString("com.google.android.gms.signin.internal.realClientPackageName", zze.zzh());
    }
    return zzf;
  }
  
  public final void zzh()
  {
    try
    {
      ((zzcyr)zzaf()).zza(zzg.intValue());
      return;
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
    Log.w("SignInClientImpl", "Remote service probably died when clearAccountFromSessionStore is called");
  }
  
  public final void zzi()
  {
    zza(new zzm(this));
  }
}

package com.google.android.gms.security;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.zzf;
import com.google.android.gms.common.zzs;
import java.lang.reflect.Method;

public class ProviderInstaller
{
  public static final String PROVIDER_NAME = "GmsCore_OpenSSL";
  private static final zzf zza = ;
  private static final Object zzb = new Object();
  private static Method zzc;
  
  public ProviderInstaller() {}
  
  public static void installIfNeeded(Context paramContext)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    zzbq.zza(paramContext, "Context must not be null");
    zzf.zzb(paramContext);
    paramContext = zzs.getRemoteContext(paramContext);
    if (paramContext == null)
    {
      Log.e("ProviderInstaller", "Failed to get remote context");
      throw new GooglePlayServicesNotAvailableException(8);
    }
    synchronized (zzb)
    {
      try
      {
        if (zzc == null) {
          zzc = paramContext.getClassLoader().loadClass("com.google.android.gms.common.security.ProviderInstallerImpl").getMethod("insertProvider", new Class[] { Context.class });
        }
        zzc.invoke(null, new Object[] { paramContext });
        return;
      }
      catch (Exception paramContext)
      {
        paramContext = String.valueOf(paramContext.getMessage());
        if (paramContext.length() != 0) {
          paramContext = "Failed to install provider: ".concat(paramContext);
        } else {
          paramContext = new String("Failed to install provider: ");
        }
        Log.e("ProviderInstaller", paramContext);
        throw new GooglePlayServicesNotAvailableException(8);
      }
      throw paramContext;
    }
  }
  
  public static void installIfNeededAsync(Context paramContext, ProviderInstallListener paramProviderInstallListener)
  {
    zzbq.zza(paramContext, "Context must not be null");
    zzbq.zza(paramProviderInstallListener, "Listener must not be null");
    zzbq.zzb("Must be called on the UI thread");
    new zza(paramContext, paramProviderInstallListener).execute(new Void[0]);
  }
  
  public static abstract interface ProviderInstallListener
  {
    public abstract void onProviderInstallFailed(int paramInt, Intent paramIntent);
    
    public abstract void onProviderInstalled();
  }
}

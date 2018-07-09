package com.google.android.gms.security;

import android.content.Context;
import android.os.AsyncTask;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;

final class zza
  extends AsyncTask<Void, Void, Integer>
{
  zza(Context paramContext, ProviderInstaller.ProviderInstallListener paramProviderInstallListener) {}
  
  private final Integer zza(Void... paramVarArgs)
  {
    try
    {
      ProviderInstaller.installIfNeeded(zza);
      i = 0;
    }
    catch (GooglePlayServicesNotAvailableException paramVarArgs)
    {
      for (;;)
      {
        i = errorCode;
      }
    }
    catch (GooglePlayServicesRepairableException paramVarArgs)
    {
      for (;;)
      {
        int i = paramVarArgs.getConnectionStatusCode();
      }
    }
    return Integer.valueOf(i);
  }
}

package com.google.android.gms.location;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.ApiOptions.NoOptions;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzcfy;
import com.google.android.gms.internal.zzcgn;
import com.google.android.gms.internal.zzchh;
import com.google.android.gms.internal.zzchs;

public class LocationServices
{
  public static final Api<Api.ApiOptions.NoOptions> API = new Api("LocationServices.API", zzb, zza);
  @Deprecated
  public static final FusedLocationProviderApi FusedLocationApi = new zzcfy();
  @Deprecated
  public static final GeofencingApi GeofencingApi = new zzcgn();
  @Deprecated
  public static final SettingsApi SettingsApi = new zzchs();
  private static final Api.zzf<zzchh> zza = new Api.zzf();
  private static final Api.zza<zzchh, Api.ApiOptions.NoOptions> zzb = new zzad();
  
  private LocationServices() {}
  
  public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Activity paramActivity)
  {
    return new FusedLocationProviderClient(paramActivity);
  }
  
  public static FusedLocationProviderClient getFusedLocationProviderClient(@NonNull Context paramContext)
  {
    return new FusedLocationProviderClient(paramContext);
  }
  
  public static GeofencingClient getGeofencingClient(@NonNull Activity paramActivity)
  {
    return new GeofencingClient(paramActivity);
  }
  
  public static GeofencingClient getGeofencingClient(@NonNull Context paramContext)
  {
    return new GeofencingClient(paramContext);
  }
  
  public static SettingsClient getSettingsClient(@NonNull Activity paramActivity)
  {
    return new SettingsClient(paramActivity);
  }
  
  public static SettingsClient getSettingsClient(@NonNull Context paramContext)
  {
    return new SettingsClient(paramContext);
  }
  
  @Hide
  public static zzchh zza(GoogleApiClient paramGoogleApiClient)
  {
    boolean bool2 = false;
    if (paramGoogleApiClient != null) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzbq.zzb(bool1, "GoogleApiClient parameter is required.");
    paramGoogleApiClient = (zzchh)paramGoogleApiClient.zza(zza);
    boolean bool1 = bool2;
    if (paramGoogleApiClient != null) {
      bool1 = true;
    }
    zzbq.zza(bool1, "GoogleApiClient is not configured to use the LocationServices.API Api. Pass thisinto GoogleApiClient.Builder#addApi() to use this feature.");
    return paramGoogleApiClient;
  }
  
  @Hide
  public static abstract class zza<R extends Result>
    extends zzm<R, zzchh>
  {
    public zza(GoogleApiClient paramGoogleApiClient)
    {
      super(paramGoogleApiClient);
    }
  }
}

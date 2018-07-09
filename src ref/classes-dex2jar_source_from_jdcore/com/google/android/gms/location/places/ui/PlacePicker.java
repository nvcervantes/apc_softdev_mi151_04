package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgq;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlacePicker
  extends zza
{
  public static final int RESULT_ERROR = 2;
  
  private PlacePicker() {}
  
  @Deprecated
  public static String getAttributions(Intent paramIntent)
  {
    return paramIntent.getStringExtra("third_party_attributions");
  }
  
  public static LatLngBounds getLatLngBounds(Intent paramIntent)
  {
    return (LatLngBounds)zzbgq.zza(paramIntent, "final_latlng_bounds", LatLngBounds.CREATOR);
  }
  
  public static Place getPlace(Context paramContext, Intent paramIntent)
  {
    return zza.getPlace(paramContext, paramIntent);
  }
  
  @Deprecated
  public static Place getPlace(Intent paramIntent, Context paramContext)
  {
    return zza.getPlace(paramContext, paramIntent);
  }
  
  public static class IntentBuilder
    extends zzb
  {
    public IntentBuilder()
    {
      super();
      zza.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
    }
    
    public Intent build(Activity paramActivity)
      throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
    {
      return super.build(paramActivity);
    }
    
    public IntentBuilder setLatLngBounds(LatLngBounds paramLatLngBounds)
    {
      zzbq.zza(paramLatLngBounds);
      zzbgq.zza(paramLatLngBounds, zza, "latlng_bounds");
      return this;
    }
  }
}

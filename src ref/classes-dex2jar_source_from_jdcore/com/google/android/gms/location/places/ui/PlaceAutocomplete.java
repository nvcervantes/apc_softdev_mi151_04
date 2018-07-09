package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.Nullable;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.places.AutocompleteFilter;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLngBounds;

public class PlaceAutocomplete
  extends zza
{
  public static final int MODE_FULLSCREEN = 1;
  public static final int MODE_OVERLAY = 2;
  public static final int RESULT_ERROR = 2;
  
  private PlaceAutocomplete() {}
  
  public static Place getPlace(Context paramContext, Intent paramIntent)
  {
    return zza.getPlace(paramContext, paramIntent);
  }
  
  public static Status getStatus(Context paramContext, Intent paramIntent)
  {
    return zza.getStatus(paramContext, paramIntent);
  }
  
  public static class IntentBuilder
    extends zzb
  {
    public IntentBuilder(int paramInt)
    {
      super();
      zza.putExtra("gmscore_client_jar_version", GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE);
      zza.putExtra("mode", paramInt);
      zza.putExtra("origin", 2);
    }
    
    public Intent build(Activity paramActivity)
      throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
    {
      return super.build(paramActivity);
    }
    
    public IntentBuilder setBoundsBias(@Nullable LatLngBounds paramLatLngBounds)
    {
      if (paramLatLngBounds != null)
      {
        zza.putExtra("bounds", paramLatLngBounds);
        return this;
      }
      zza.removeExtra("bounds");
      return this;
    }
    
    public IntentBuilder setFilter(@Nullable AutocompleteFilter paramAutocompleteFilter)
    {
      if (paramAutocompleteFilter != null)
      {
        zza.putExtra("filter", paramAutocompleteFilter);
        return this;
      }
      zza.removeExtra("filter");
      return this;
    }
    
    @Hide
    public final IntentBuilder zza(int paramInt)
    {
      zza.putExtra("origin", 1);
      return this;
    }
    
    @Hide
    public final IntentBuilder zza(@Nullable String paramString)
    {
      if (paramString != null)
      {
        zza.putExtra("initial_query", paramString);
        return this;
      }
      zza.removeExtra("initial_query");
      return this;
    }
  }
}

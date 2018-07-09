package com.google.android.gms.location.places.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Resources.Theme;
import android.util.TypedValue;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.Hide;

public class zzb
{
  @Hide
  protected final Intent zza;
  
  public zzb(String paramString)
  {
    zza = new Intent(paramString);
    zza.setPackage("com.google.android.gms");
  }
  
  protected Intent build(Activity paramActivity)
    throws GooglePlayServicesRepairableException, GooglePlayServicesNotAvailableException
  {
    Resources.Theme localTheme = paramActivity.getTheme();
    TypedValue localTypedValue1 = new TypedValue();
    TypedValue localTypedValue2 = new TypedValue();
    if ((localTheme.resolveAttribute(16843827, localTypedValue1, true)) && (!zza.hasExtra("primary_color"))) {
      zza.putExtra("primary_color", data);
    }
    if ((localTheme.resolveAttribute(16843828, localTypedValue2, true)) && (!zza.hasExtra("primary_color_dark"))) {
      zza.putExtra("primary_color_dark", data);
    }
    GoogleApiAvailability.getInstance();
    GoogleApiAvailability.zzb(paramActivity);
    return zza;
  }
}

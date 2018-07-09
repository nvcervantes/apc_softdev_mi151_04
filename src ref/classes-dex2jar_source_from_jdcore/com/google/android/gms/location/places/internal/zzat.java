package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.text.TextUtils;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

@Hide
public final class zzat
  extends zzaw
  implements Place
{
  private final String zzc = zza("place_id", "");
  private final zzag zzd;
  
  public zzat(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
    if ((getPlaceTypes().size() <= 0) && ((getPhoneNumber() == null) || (getPhoneNumber().length() <= 0)) && ((getWebsiteUri() == null) || (getWebsiteUri().equals(Uri.EMPTY))) && (getRating() < 0.0F) && (getPriceLevel() < 0)) {
      paramInt = 0;
    } else {
      paramInt = 1;
    }
    paramDataHolder = null;
    if (paramInt != 0)
    {
      List localList = getPlaceTypes();
      if (getPhoneNumber() != null) {
        paramDataHolder = getPhoneNumber().toString();
      }
      paramDataHolder = new zzag(localList, paramDataHolder, getWebsiteUri(), getRating(), getPriceLevel());
    }
    else
    {
      paramDataHolder = null;
    }
    zzd = paramDataHolder;
  }
  
  private final List<String> zza()
  {
    return zzb("place_attributions", Collections.emptyList());
  }
  
  public final CharSequence getAddress()
  {
    return zza("place_address", "");
  }
  
  public final CharSequence getAttributions()
  {
    return zzg.zza(zza());
  }
  
  public final String getId()
  {
    return zzc;
  }
  
  public final LatLng getLatLng()
  {
    return (LatLng)zza("place_lat_lng", LatLng.CREATOR);
  }
  
  public final Locale getLocale()
  {
    String str = zza("place_locale_language", "");
    if (!TextUtils.isEmpty(str)) {
      return new Locale(str, zza("place_locale_country", ""));
    }
    str = zza("place_locale", "");
    if (!TextUtils.isEmpty(str)) {
      return new Locale(str);
    }
    return Locale.getDefault();
  }
  
  public final CharSequence getName()
  {
    return zza("place_name", "");
  }
  
  public final CharSequence getPhoneNumber()
  {
    return zza("place_phone_number", "");
  }
  
  public final List<Integer> getPlaceTypes()
  {
    return zza("place_types", Collections.emptyList());
  }
  
  public final int getPriceLevel()
  {
    return zza("place_price_level", -1);
  }
  
  public final float getRating()
  {
    return zza("place_rating", -1.0F);
  }
  
  public final LatLngBounds getViewport()
  {
    return (LatLngBounds)zza("place_viewport", LatLngBounds.CREATOR);
  }
  
  public final Uri getWebsiteUri()
  {
    String str = zza("place_website_uri", null);
    if (str == null) {
      return null;
    }
    return Uri.parse(str);
  }
}

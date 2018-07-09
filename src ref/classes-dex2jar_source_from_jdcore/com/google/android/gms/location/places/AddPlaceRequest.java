package com.google.android.gms.location.places;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.maps.model.LatLng;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Deprecated
public class AddPlaceRequest
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<AddPlaceRequest> CREATOR = new zzb();
  private final String zza;
  private final LatLng zzb;
  private final String zzc;
  private final List<Integer> zzd;
  private final String zze;
  private final Uri zzf;
  
  public AddPlaceRequest(String paramString1, LatLng paramLatLng, String paramString2, List<Integer> paramList, Uri paramUri)
  {
    this(paramString1, paramLatLng, paramString2, paramList, null, (Uri)zzbq.zza(paramUri));
  }
  
  public AddPlaceRequest(String paramString1, LatLng paramLatLng, String paramString2, List<Integer> paramList, String paramString3)
  {
    this(paramString1, paramLatLng, paramString2, paramList, zzbq.zza(paramString3), null);
  }
  
  public AddPlaceRequest(String paramString1, LatLng paramLatLng, String paramString2, List<Integer> paramList, String paramString3, Uri paramUri)
  {
    zza = zzbq.zza(paramString1);
    zzb = ((LatLng)zzbq.zza(paramLatLng));
    zzc = zzbq.zza(paramString2);
    zzd = new ArrayList((Collection)zzbq.zza(paramList));
    boolean bool1 = zzd.isEmpty();
    boolean bool2 = true;
    zzbq.zzb(bool1 ^ true, "At least one place type should be provided.");
    bool1 = bool2;
    if (TextUtils.isEmpty(paramString3)) {
      if (paramUri != null) {
        bool1 = bool2;
      } else {
        bool1 = false;
      }
    }
    zzbq.zzb(bool1, "One of phone number or URI should be provided.");
    zze = paramString3;
    zzf = paramUri;
  }
  
  public String getAddress()
  {
    return zzc;
  }
  
  public LatLng getLatLng()
  {
    return zzb;
  }
  
  public String getName()
  {
    return zza;
  }
  
  @Nullable
  public String getPhoneNumber()
  {
    return zze;
  }
  
  public List<Integer> getPlaceTypes()
  {
    return zzd;
  }
  
  @Nullable
  public Uri getWebsiteUri()
  {
    return zzf;
  }
  
  public String toString()
  {
    return zzbg.zza(this).zza("name", zza).zza("latLng", zzb).zza("address", zzc).zza("placeTypes", zzd).zza("phoneNumer", zze).zza("websiteUri", zzf).toString();
  }
  
  @Hide
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, getName(), false);
    zzbgo.zza(paramParcel, 2, getLatLng(), paramInt, false);
    zzbgo.zza(paramParcel, 3, getAddress(), false);
    zzbgo.zza(paramParcel, 4, getPlaceTypes(), false);
    zzbgo.zza(paramParcel, 5, getPhoneNumber(), false);
    zzbgo.zza(paramParcel, 6, getWebsiteUri(), paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}

package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

@Hide
public final class PlaceEntity
  extends zzbgl
  implements ReflectedParcelable, Place
{
  @Hide
  public static final Parcelable.Creator<PlaceEntity> CREATOR = new zzaf();
  private final String zza;
  private final Bundle zzb;
  @Deprecated
  private final zzal zzc;
  private final LatLng zzd;
  private final float zze;
  private final LatLngBounds zzf;
  private final String zzg;
  private final Uri zzh;
  private final boolean zzi;
  private final float zzj;
  private final int zzk;
  private final List<Integer> zzl;
  private final List<Integer> zzm;
  private final String zzn;
  private final String zzo;
  private final String zzp;
  private final String zzq;
  private final List<String> zzr;
  private final zzan zzs;
  private final zzag zzt;
  private final String zzu;
  private final Map<Integer, String> zzv;
  private final TimeZone zzw;
  private Locale zzx;
  
  @Hide
  PlaceEntity(String paramString1, List<Integer> paramList1, List<Integer> paramList2, Bundle paramBundle, String paramString2, String paramString3, String paramString4, String paramString5, List<String> paramList, LatLng paramLatLng, float paramFloat1, LatLngBounds paramLatLngBounds, String paramString6, Uri paramUri, boolean paramBoolean, float paramFloat2, int paramInt, zzal paramZzal, zzan paramZzan, zzag paramZzag, String paramString7)
  {
    zza = paramString1;
    zzm = Collections.unmodifiableList(paramList1);
    zzl = paramList2;
    if (paramBundle == null) {
      paramBundle = new Bundle();
    }
    zzb = paramBundle;
    zzn = paramString2;
    zzo = paramString3;
    zzp = paramString4;
    zzq = paramString5;
    if (paramList == null) {
      paramList = Collections.emptyList();
    }
    zzr = paramList;
    zzd = paramLatLng;
    zze = paramFloat1;
    zzf = paramLatLngBounds;
    if (paramString6 == null) {
      paramString6 = "UTC";
    }
    zzg = paramString6;
    zzh = paramUri;
    zzi = paramBoolean;
    zzj = paramFloat2;
    zzk = paramInt;
    zzv = Collections.unmodifiableMap(new HashMap());
    zzw = null;
    zzx = null;
    zzc = paramZzal;
    zzs = paramZzan;
    zzt = paramZzag;
    zzu = paramString7;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof PlaceEntity)) {
      return false;
    }
    paramObject = (PlaceEntity)paramObject;
    return (zza.equals(zza)) && (zzbg.zza(zzx, zzx));
  }
  
  public final CharSequence getAttributions()
  {
    return zzg.zza(zzr);
  }
  
  public final String getId()
  {
    return zza;
  }
  
  public final LatLng getLatLng()
  {
    return zzd;
  }
  
  public final Locale getLocale()
  {
    return zzx;
  }
  
  public final List<Integer> getPlaceTypes()
  {
    return zzm;
  }
  
  public final int getPriceLevel()
  {
    return zzk;
  }
  
  public final float getRating()
  {
    return zzj;
  }
  
  public final LatLngBounds getViewport()
  {
    return zzf;
  }
  
  public final Uri getWebsiteUri()
  {
    return zzh;
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { zza, zzx });
  }
  
  public final boolean isDataValid()
  {
    return true;
  }
  
  @SuppressLint({"DefaultLocale"})
  public final String toString()
  {
    return zzbg.zza(this).zza("id", zza).zza("placeTypes", zzm).zza("locale", zzx).zza("name", zzn).zza("address", zzo).zza("phoneNumber", zzp).zza("latlng", zzd).zza("viewport", zzf).zza("websiteUri", zzh).zza("isPermanentlyClosed", Boolean.valueOf(zzi)).zza("priceLevel", Integer.valueOf(zzk)).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, getId(), false);
    zzbgo.zza(paramParcel, 2, zzb, false);
    zzbgo.zza(paramParcel, 3, zzc, paramInt, false);
    zzbgo.zza(paramParcel, 4, getLatLng(), paramInt, false);
    zzbgo.zza(paramParcel, 5, zze);
    zzbgo.zza(paramParcel, 6, getViewport(), paramInt, false);
    zzbgo.zza(paramParcel, 7, zzg, false);
    zzbgo.zza(paramParcel, 8, getWebsiteUri(), paramInt, false);
    zzbgo.zza(paramParcel, 9, zzi);
    zzbgo.zza(paramParcel, 10, getRating());
    zzbgo.zza(paramParcel, 11, getPriceLevel());
    zzbgo.zza(paramParcel, 13, zzl, false);
    zzbgo.zza(paramParcel, 14, (String)getAddress(), false);
    zzbgo.zza(paramParcel, 15, (String)getPhoneNumber(), false);
    zzbgo.zza(paramParcel, 16, zzq, false);
    zzbgo.zzb(paramParcel, 17, zzr, false);
    zzbgo.zza(paramParcel, 19, (String)getName(), false);
    zzbgo.zza(paramParcel, 20, getPlaceTypes(), false);
    zzbgo.zza(paramParcel, 21, zzs, paramInt, false);
    zzbgo.zza(paramParcel, 22, zzt, paramInt, false);
    zzbgo.zza(paramParcel, 23, zzu, false);
    zzbgo.zza(paramParcel, i);
  }
  
  public final void zza(Locale paramLocale)
  {
    zzx = paramLocale;
  }
  
  public static final class zza
  {
    private String zza;
    private String zzb;
    private LatLng zzc;
    private float zzd;
    private LatLngBounds zze;
    private Uri zzf;
    private boolean zzg;
    private float zzh = -1.0F;
    private int zzi = -1;
    private List<Integer> zzj;
    private String zzk;
    private String zzl;
    private List<String> zzm;
    private zzan zzn;
    private zzag zzo;
    private String zzp;
    
    public zza() {}
    
    public final zza zza(float paramFloat)
    {
      zzd = paramFloat;
      return this;
    }
    
    public final zza zza(int paramInt)
    {
      zzi = paramInt;
      return this;
    }
    
    public final zza zza(Uri paramUri)
    {
      zzf = paramUri;
      return this;
    }
    
    public final zza zza(zzag paramZzag)
    {
      zzo = paramZzag;
      return this;
    }
    
    public final zza zza(zzan paramZzan)
    {
      zzn = paramZzan;
      return this;
    }
    
    public final zza zza(LatLng paramLatLng)
    {
      zzc = paramLatLng;
      return this;
    }
    
    public final zza zza(LatLngBounds paramLatLngBounds)
    {
      zze = paramLatLngBounds;
      return this;
    }
    
    public final zza zza(String paramString)
    {
      zza = paramString;
      return this;
    }
    
    public final zza zza(List<Integer> paramList)
    {
      zzj = paramList;
      return this;
    }
    
    public final zza zza(boolean paramBoolean)
    {
      zzg = paramBoolean;
      return this;
    }
    
    public final PlaceEntity zza()
    {
      return new PlaceEntity(zza, zzj, Collections.emptyList(), null, zzb, zzk, zzl, null, zzm, zzc, zzd, zze, null, zzf, zzg, zzh, zzi, new zzal(zzb, zzk, zzl, null, zzm), zzn, zzo, zzp);
    }
    
    public final zza zzb(float paramFloat)
    {
      zzh = paramFloat;
      return this;
    }
    
    public final zza zzb(String paramString)
    {
      zzb = paramString;
      return this;
    }
    
    public final zza zzb(List<String> paramList)
    {
      zzm = paramList;
      return this;
    }
    
    public final zza zzc(String paramString)
    {
      zzk = paramString;
      return this;
    }
    
    public final zza zzd(String paramString)
    {
      zzl = paramString;
      return this;
    }
    
    public final zza zze(String paramString)
    {
      zzp = paramString;
      return this;
    }
  }
}

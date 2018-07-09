package com.google.android.gms.maps;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.StreetViewPanoramaCamera;
import com.google.android.gms.maps.model.StreetViewSource;

public final class StreetViewPanoramaOptions
  extends zzbgl
  implements ReflectedParcelable
{
  @Hide
  public static final Parcelable.Creator<StreetViewPanoramaOptions> CREATOR = new zzai();
  private StreetViewPanoramaCamera zza;
  private String zzb;
  private LatLng zzc;
  private Integer zzd;
  private Boolean zze = Boolean.valueOf(true);
  private Boolean zzf = Boolean.valueOf(true);
  private Boolean zzg = Boolean.valueOf(true);
  private Boolean zzh = Boolean.valueOf(true);
  private Boolean zzi;
  private StreetViewSource zzj = StreetViewSource.DEFAULT;
  
  public StreetViewPanoramaOptions() {}
  
  @Hide
  StreetViewPanoramaOptions(StreetViewPanoramaCamera paramStreetViewPanoramaCamera, String paramString, LatLng paramLatLng, Integer paramInteger, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, byte paramByte5, StreetViewSource paramStreetViewSource)
  {
    zza = paramStreetViewPanoramaCamera;
    zzc = paramLatLng;
    zzd = paramInteger;
    zzb = paramString;
    zze = zza.zza(paramByte1);
    zzf = zza.zza(paramByte2);
    zzg = zza.zza(paramByte3);
    zzh = zza.zza(paramByte4);
    zzi = zza.zza(paramByte5);
    zzj = paramStreetViewSource;
  }
  
  public final Boolean getPanningGesturesEnabled()
  {
    return zzg;
  }
  
  public final String getPanoramaId()
  {
    return zzb;
  }
  
  public final LatLng getPosition()
  {
    return zzc;
  }
  
  public final Integer getRadius()
  {
    return zzd;
  }
  
  public final StreetViewSource getSource()
  {
    return zzj;
  }
  
  public final Boolean getStreetNamesEnabled()
  {
    return zzh;
  }
  
  public final StreetViewPanoramaCamera getStreetViewPanoramaCamera()
  {
    return zza;
  }
  
  public final Boolean getUseViewLifecycleInFragment()
  {
    return zzi;
  }
  
  public final Boolean getUserNavigationEnabled()
  {
    return zze;
  }
  
  public final Boolean getZoomGesturesEnabled()
  {
    return zzf;
  }
  
  public final StreetViewPanoramaOptions panningGesturesEnabled(boolean paramBoolean)
  {
    zzg = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final StreetViewPanoramaOptions panoramaCamera(StreetViewPanoramaCamera paramStreetViewPanoramaCamera)
  {
    zza = paramStreetViewPanoramaCamera;
    return this;
  }
  
  public final StreetViewPanoramaOptions panoramaId(String paramString)
  {
    zzb = paramString;
    return this;
  }
  
  public final StreetViewPanoramaOptions position(LatLng paramLatLng)
  {
    zzc = paramLatLng;
    return this;
  }
  
  public final StreetViewPanoramaOptions position(LatLng paramLatLng, StreetViewSource paramStreetViewSource)
  {
    zzc = paramLatLng;
    zzj = paramStreetViewSource;
    return this;
  }
  
  public final StreetViewPanoramaOptions position(LatLng paramLatLng, Integer paramInteger)
  {
    zzc = paramLatLng;
    zzd = paramInteger;
    return this;
  }
  
  public final StreetViewPanoramaOptions position(LatLng paramLatLng, Integer paramInteger, StreetViewSource paramStreetViewSource)
  {
    zzc = paramLatLng;
    zzd = paramInteger;
    zzj = paramStreetViewSource;
    return this;
  }
  
  public final StreetViewPanoramaOptions streetNamesEnabled(boolean paramBoolean)
  {
    zzh = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final String toString()
  {
    return zzbg.zza(this).zza("PanoramaId", zzb).zza("Position", zzc).zza("Radius", zzd).zza("Source", zzj).zza("StreetViewPanoramaCamera", zza).zza("UserNavigationEnabled", zze).zza("ZoomGesturesEnabled", zzf).zza("PanningGesturesEnabled", zzg).zza("StreetNamesEnabled", zzh).zza("UseViewLifecycleInFragment", zzi).toString();
  }
  
  public final StreetViewPanoramaOptions useViewLifecycleInFragment(boolean paramBoolean)
  {
    zzi = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final StreetViewPanoramaOptions userNavigationEnabled(boolean paramBoolean)
  {
    zze = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, getStreetViewPanoramaCamera(), paramInt, false);
    zzbgo.zza(paramParcel, 3, getPanoramaId(), false);
    zzbgo.zza(paramParcel, 4, getPosition(), paramInt, false);
    zzbgo.zza(paramParcel, 5, getRadius(), false);
    zzbgo.zza(paramParcel, 6, zza.zza(zze));
    zzbgo.zza(paramParcel, 7, zza.zza(zzf));
    zzbgo.zza(paramParcel, 8, zza.zza(zzg));
    zzbgo.zza(paramParcel, 9, zza.zza(zzh));
    zzbgo.zza(paramParcel, 10, zza.zza(zzi));
    zzbgo.zza(paramParcel, 11, getSource(), paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
  
  public final StreetViewPanoramaOptions zoomGesturesEnabled(boolean paramBoolean)
  {
    zzf = Boolean.valueOf(paramBoolean);
    return this;
  }
}

package com.google.android.gms.maps;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.AttributeSet;
import com.google.android.gms.R.styleable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.maps.internal.zza;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLngBounds;

public final class GoogleMapOptions
  extends zzbgl
  implements ReflectedParcelable
{
  @Hide
  public static final Parcelable.Creator<GoogleMapOptions> CREATOR = new zzaa();
  private Boolean zza;
  private Boolean zzb;
  private int zzc = -1;
  private CameraPosition zzd;
  private Boolean zze;
  private Boolean zzf;
  private Boolean zzg;
  private Boolean zzh;
  private Boolean zzi;
  private Boolean zzj;
  private Boolean zzk;
  private Boolean zzl;
  private Boolean zzm;
  private Float zzn = null;
  private Float zzo = null;
  private LatLngBounds zzp = null;
  
  public GoogleMapOptions() {}
  
  @Hide
  GoogleMapOptions(byte paramByte1, byte paramByte2, int paramInt, CameraPosition paramCameraPosition, byte paramByte3, byte paramByte4, byte paramByte5, byte paramByte6, byte paramByte7, byte paramByte8, byte paramByte9, byte paramByte10, byte paramByte11, Float paramFloat1, Float paramFloat2, LatLngBounds paramLatLngBounds)
  {
    zza = zza.zza(paramByte1);
    zzb = zza.zza(paramByte2);
    zzc = paramInt;
    zzd = paramCameraPosition;
    zze = zza.zza(paramByte3);
    zzf = zza.zza(paramByte4);
    zzg = zza.zza(paramByte5);
    zzh = zza.zza(paramByte6);
    zzi = zza.zza(paramByte7);
    zzj = zza.zza(paramByte8);
    zzk = zza.zza(paramByte9);
    zzl = zza.zza(paramByte10);
    zzm = zza.zza(paramByte11);
    zzn = paramFloat1;
    zzo = paramFloat2;
    zzp = paramLatLngBounds;
  }
  
  public static GoogleMapOptions createFromAttributes(Context paramContext, AttributeSet paramAttributeSet)
  {
    if (paramAttributeSet == null) {
      return null;
    }
    TypedArray localTypedArray = paramContext.getResources().obtainAttributes(paramAttributeSet, R.styleable.MapAttrs);
    GoogleMapOptions localGoogleMapOptions = new GoogleMapOptions();
    if (localTypedArray.hasValue(R.styleable.MapAttrs_mapType)) {
      localGoogleMapOptions.mapType(localTypedArray.getInt(R.styleable.MapAttrs_mapType, -1));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_zOrderOnTop)) {
      localGoogleMapOptions.zOrderOnTop(localTypedArray.getBoolean(R.styleable.MapAttrs_zOrderOnTop, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_useViewLifecycle)) {
      localGoogleMapOptions.useViewLifecycleInFragment(localTypedArray.getBoolean(R.styleable.MapAttrs_useViewLifecycle, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiCompass)) {
      localGoogleMapOptions.compassEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiCompass, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiRotateGestures)) {
      localGoogleMapOptions.rotateGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiRotateGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiScrollGestures)) {
      localGoogleMapOptions.scrollGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiScrollGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiTiltGestures)) {
      localGoogleMapOptions.tiltGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiTiltGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiZoomGestures)) {
      localGoogleMapOptions.zoomGesturesEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiZoomGestures, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiZoomControls)) {
      localGoogleMapOptions.zoomControlsEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiZoomControls, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_liteMode)) {
      localGoogleMapOptions.liteMode(localTypedArray.getBoolean(R.styleable.MapAttrs_liteMode, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_uiMapToolbar)) {
      localGoogleMapOptions.mapToolbarEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_uiMapToolbar, true));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_ambientEnabled)) {
      localGoogleMapOptions.ambientEnabled(localTypedArray.getBoolean(R.styleable.MapAttrs_ambientEnabled, false));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_cameraMinZoomPreference)) {
      localGoogleMapOptions.minZoomPreference(localTypedArray.getFloat(R.styleable.MapAttrs_cameraMinZoomPreference, Float.NEGATIVE_INFINITY));
    }
    if (localTypedArray.hasValue(R.styleable.MapAttrs_cameraMinZoomPreference)) {
      localGoogleMapOptions.maxZoomPreference(localTypedArray.getFloat(R.styleable.MapAttrs_cameraMaxZoomPreference, Float.POSITIVE_INFINITY));
    }
    localGoogleMapOptions.latLngBoundsForCameraTarget(LatLngBounds.createFromAttributes(paramContext, paramAttributeSet));
    localGoogleMapOptions.camera(CameraPosition.createFromAttributes(paramContext, paramAttributeSet));
    localTypedArray.recycle();
    return localGoogleMapOptions;
  }
  
  public final GoogleMapOptions ambientEnabled(boolean paramBoolean)
  {
    zzm = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions camera(CameraPosition paramCameraPosition)
  {
    zzd = paramCameraPosition;
    return this;
  }
  
  public final GoogleMapOptions compassEnabled(boolean paramBoolean)
  {
    zzf = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final Boolean getAmbientEnabled()
  {
    return zzm;
  }
  
  public final CameraPosition getCamera()
  {
    return zzd;
  }
  
  public final Boolean getCompassEnabled()
  {
    return zzf;
  }
  
  public final LatLngBounds getLatLngBoundsForCameraTarget()
  {
    return zzp;
  }
  
  public final Boolean getLiteMode()
  {
    return zzk;
  }
  
  public final Boolean getMapToolbarEnabled()
  {
    return zzl;
  }
  
  public final int getMapType()
  {
    return zzc;
  }
  
  public final Float getMaxZoomPreference()
  {
    return zzo;
  }
  
  public final Float getMinZoomPreference()
  {
    return zzn;
  }
  
  public final Boolean getRotateGesturesEnabled()
  {
    return zzj;
  }
  
  public final Boolean getScrollGesturesEnabled()
  {
    return zzg;
  }
  
  public final Boolean getTiltGesturesEnabled()
  {
    return zzi;
  }
  
  public final Boolean getUseViewLifecycleInFragment()
  {
    return zzb;
  }
  
  public final Boolean getZOrderOnTop()
  {
    return zza;
  }
  
  public final Boolean getZoomControlsEnabled()
  {
    return zze;
  }
  
  public final Boolean getZoomGesturesEnabled()
  {
    return zzh;
  }
  
  public final GoogleMapOptions latLngBoundsForCameraTarget(LatLngBounds paramLatLngBounds)
  {
    zzp = paramLatLngBounds;
    return this;
  }
  
  public final GoogleMapOptions liteMode(boolean paramBoolean)
  {
    zzk = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions mapToolbarEnabled(boolean paramBoolean)
  {
    zzl = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions mapType(int paramInt)
  {
    zzc = paramInt;
    return this;
  }
  
  public final GoogleMapOptions maxZoomPreference(float paramFloat)
  {
    zzo = Float.valueOf(paramFloat);
    return this;
  }
  
  public final GoogleMapOptions minZoomPreference(float paramFloat)
  {
    zzn = Float.valueOf(paramFloat);
    return this;
  }
  
  public final GoogleMapOptions rotateGesturesEnabled(boolean paramBoolean)
  {
    zzj = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions scrollGesturesEnabled(boolean paramBoolean)
  {
    zzg = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions tiltGesturesEnabled(boolean paramBoolean)
  {
    zzi = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final String toString()
  {
    return zzbg.zza(this).zza("MapType", Integer.valueOf(zzc)).zza("LiteMode", zzk).zza("Camera", zzd).zza("CompassEnabled", zzf).zza("ZoomControlsEnabled", zze).zza("ScrollGesturesEnabled", zzg).zza("ZoomGesturesEnabled", zzh).zza("TiltGesturesEnabled", zzi).zza("RotateGesturesEnabled", zzj).zza("MapToolbarEnabled", zzl).zza("AmbientEnabled", zzm).zza("MinZoomPreference", zzn).zza("MaxZoomPreference", zzo).zza("LatLngBoundsForCameraTarget", zzp).zza("ZOrderOnTop", zza).zza("UseViewLifecycleInFragment", zzb).toString();
  }
  
  public final GoogleMapOptions useViewLifecycleInFragment(boolean paramBoolean)
  {
    zzb = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, zza.zza(zza));
    zzbgo.zza(paramParcel, 3, zza.zza(zzb));
    zzbgo.zza(paramParcel, 4, getMapType());
    zzbgo.zza(paramParcel, 5, getCamera(), paramInt, false);
    zzbgo.zza(paramParcel, 6, zza.zza(zze));
    zzbgo.zza(paramParcel, 7, zza.zza(zzf));
    zzbgo.zza(paramParcel, 8, zza.zza(zzg));
    zzbgo.zza(paramParcel, 9, zza.zza(zzh));
    zzbgo.zza(paramParcel, 10, zza.zza(zzi));
    zzbgo.zza(paramParcel, 11, zza.zza(zzj));
    zzbgo.zza(paramParcel, 12, zza.zza(zzk));
    zzbgo.zza(paramParcel, 14, zza.zza(zzl));
    zzbgo.zza(paramParcel, 15, zza.zza(zzm));
    zzbgo.zza(paramParcel, 16, getMinZoomPreference(), false);
    zzbgo.zza(paramParcel, 17, getMaxZoomPreference(), false);
    zzbgo.zza(paramParcel, 18, getLatLngBoundsForCameraTarget(), paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
  
  public final GoogleMapOptions zOrderOnTop(boolean paramBoolean)
  {
    zza = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions zoomControlsEnabled(boolean paramBoolean)
  {
    zze = Boolean.valueOf(paramBoolean);
    return this;
  }
  
  public final GoogleMapOptions zoomGesturesEnabled(boolean paramBoolean)
  {
    zzh = Boolean.valueOf(paramBoolean);
    return this;
  }
}

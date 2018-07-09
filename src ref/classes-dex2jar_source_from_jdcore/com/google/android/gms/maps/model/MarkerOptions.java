package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class MarkerOptions
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<MarkerOptions> CREATOR = new zzh();
  private LatLng zza;
  private String zzb;
  private String zzc;
  private BitmapDescriptor zzd;
  private float zze = 0.5F;
  private float zzf = 1.0F;
  private boolean zzg;
  private boolean zzh = true;
  private boolean zzi = false;
  private float zzj = 0.0F;
  private float zzk = 0.5F;
  private float zzl = 0.0F;
  private float zzm = 1.0F;
  private float zzn;
  
  public MarkerOptions() {}
  
  @Hide
  MarkerOptions(LatLng paramLatLng, String paramString1, String paramString2, IBinder paramIBinder, float paramFloat1, float paramFloat2, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, float paramFloat7)
  {
    zza = paramLatLng;
    zzb = paramString1;
    zzc = paramString2;
    if (paramIBinder == null) {}
    for (zzd = null;; zzd = new BitmapDescriptor(IObjectWrapper.zza.zza(paramIBinder))) {
      break;
    }
    zze = paramFloat1;
    zzf = paramFloat2;
    zzg = paramBoolean1;
    zzh = paramBoolean2;
    zzi = paramBoolean3;
    zzj = paramFloat3;
    zzk = paramFloat4;
    zzl = paramFloat5;
    zzm = paramFloat6;
    zzn = paramFloat7;
  }
  
  public final MarkerOptions alpha(float paramFloat)
  {
    zzm = paramFloat;
    return this;
  }
  
  public final MarkerOptions anchor(float paramFloat1, float paramFloat2)
  {
    zze = paramFloat1;
    zzf = paramFloat2;
    return this;
  }
  
  public final MarkerOptions draggable(boolean paramBoolean)
  {
    zzg = paramBoolean;
    return this;
  }
  
  public final MarkerOptions flat(boolean paramBoolean)
  {
    zzi = paramBoolean;
    return this;
  }
  
  public final float getAlpha()
  {
    return zzm;
  }
  
  public final float getAnchorU()
  {
    return zze;
  }
  
  public final float getAnchorV()
  {
    return zzf;
  }
  
  public final BitmapDescriptor getIcon()
  {
    return zzd;
  }
  
  public final float getInfoWindowAnchorU()
  {
    return zzk;
  }
  
  public final float getInfoWindowAnchorV()
  {
    return zzl;
  }
  
  public final LatLng getPosition()
  {
    return zza;
  }
  
  public final float getRotation()
  {
    return zzj;
  }
  
  public final String getSnippet()
  {
    return zzc;
  }
  
  public final String getTitle()
  {
    return zzb;
  }
  
  public final float getZIndex()
  {
    return zzn;
  }
  
  public final MarkerOptions icon(@Nullable BitmapDescriptor paramBitmapDescriptor)
  {
    zzd = paramBitmapDescriptor;
    return this;
  }
  
  public final MarkerOptions infoWindowAnchor(float paramFloat1, float paramFloat2)
  {
    zzk = paramFloat1;
    zzl = paramFloat2;
    return this;
  }
  
  public final boolean isDraggable()
  {
    return zzg;
  }
  
  public final boolean isFlat()
  {
    return zzi;
  }
  
  public final boolean isVisible()
  {
    return zzh;
  }
  
  public final MarkerOptions position(@NonNull LatLng paramLatLng)
  {
    if (paramLatLng == null) {
      throw new IllegalArgumentException("latlng cannot be null - a position is required.");
    }
    zza = paramLatLng;
    return this;
  }
  
  public final MarkerOptions rotation(float paramFloat)
  {
    zzj = paramFloat;
    return this;
  }
  
  public final MarkerOptions snippet(@Nullable String paramString)
  {
    zzc = paramString;
    return this;
  }
  
  public final MarkerOptions title(@Nullable String paramString)
  {
    zzb = paramString;
    return this;
  }
  
  public final MarkerOptions visible(boolean paramBoolean)
  {
    zzh = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, getPosition(), paramInt, false);
    zzbgo.zza(paramParcel, 3, getTitle(), false);
    zzbgo.zza(paramParcel, 4, getSnippet(), false);
    IBinder localIBinder;
    if (zzd == null) {
      localIBinder = null;
    } else {
      localIBinder = zzd.zza().asBinder();
    }
    zzbgo.zza(paramParcel, 5, localIBinder, false);
    zzbgo.zza(paramParcel, 6, getAnchorU());
    zzbgo.zza(paramParcel, 7, getAnchorV());
    zzbgo.zza(paramParcel, 8, isDraggable());
    zzbgo.zza(paramParcel, 9, isVisible());
    zzbgo.zza(paramParcel, 10, isFlat());
    zzbgo.zza(paramParcel, 11, getRotation());
    zzbgo.zza(paramParcel, 12, getInfoWindowAnchorU());
    zzbgo.zza(paramParcel, 13, getInfoWindowAnchorV());
    zzbgo.zza(paramParcel, 14, getAlpha());
    zzbgo.zza(paramParcel, 15, getZIndex());
    zzbgo.zza(paramParcel, i);
  }
  
  public final MarkerOptions zIndex(float paramFloat)
  {
    zzn = paramFloat;
    return this;
  }
}

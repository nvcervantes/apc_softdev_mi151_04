package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class GroundOverlayOptions
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<GroundOverlayOptions> CREATOR = new zzd();
  public static final float NO_DIMENSION = -1.0F;
  @NonNull
  private BitmapDescriptor zza;
  private LatLng zzb;
  private float zzc;
  private float zzd;
  private LatLngBounds zze;
  private float zzf;
  private float zzg;
  private boolean zzh = true;
  private float zzi = 0.0F;
  private float zzj = 0.5F;
  private float zzk = 0.5F;
  private boolean zzl = false;
  
  public GroundOverlayOptions() {}
  
  @Hide
  GroundOverlayOptions(IBinder paramIBinder, LatLng paramLatLng, float paramFloat1, float paramFloat2, LatLngBounds paramLatLngBounds, float paramFloat3, float paramFloat4, boolean paramBoolean1, float paramFloat5, float paramFloat6, float paramFloat7, boolean paramBoolean2)
  {
    zza = new BitmapDescriptor(IObjectWrapper.zza.zza(paramIBinder));
    zzb = paramLatLng;
    zzc = paramFloat1;
    zzd = paramFloat2;
    zze = paramLatLngBounds;
    zzf = paramFloat3;
    zzg = paramFloat4;
    zzh = paramBoolean1;
    zzi = paramFloat5;
    zzj = paramFloat6;
    zzk = paramFloat7;
    zzl = paramBoolean2;
  }
  
  private final GroundOverlayOptions zza(LatLng paramLatLng, float paramFloat1, float paramFloat2)
  {
    zzb = paramLatLng;
    zzc = paramFloat1;
    zzd = paramFloat2;
    return this;
  }
  
  public final GroundOverlayOptions anchor(float paramFloat1, float paramFloat2)
  {
    zzj = paramFloat1;
    zzk = paramFloat2;
    return this;
  }
  
  public final GroundOverlayOptions bearing(float paramFloat)
  {
    zzf = ((paramFloat % 360.0F + 360.0F) % 360.0F);
    return this;
  }
  
  public final GroundOverlayOptions clickable(boolean paramBoolean)
  {
    zzl = paramBoolean;
    return this;
  }
  
  public final float getAnchorU()
  {
    return zzj;
  }
  
  public final float getAnchorV()
  {
    return zzk;
  }
  
  public final float getBearing()
  {
    return zzf;
  }
  
  public final LatLngBounds getBounds()
  {
    return zze;
  }
  
  public final float getHeight()
  {
    return zzd;
  }
  
  public final BitmapDescriptor getImage()
  {
    return zza;
  }
  
  public final LatLng getLocation()
  {
    return zzb;
  }
  
  public final float getTransparency()
  {
    return zzi;
  }
  
  public final float getWidth()
  {
    return zzc;
  }
  
  public final float getZIndex()
  {
    return zzg;
  }
  
  public final GroundOverlayOptions image(@NonNull BitmapDescriptor paramBitmapDescriptor)
  {
    zzbq.zza(paramBitmapDescriptor, "imageDescriptor must not be null");
    zza = paramBitmapDescriptor;
    return this;
  }
  
  public final boolean isClickable()
  {
    return zzl;
  }
  
  public final boolean isVisible()
  {
    return zzh;
  }
  
  public final GroundOverlayOptions position(LatLng paramLatLng, float paramFloat)
  {
    LatLngBounds localLatLngBounds = zze;
    boolean bool2 = false;
    if (localLatLngBounds == null) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzbq.zza(bool1, "Position has already been set using positionFromBounds");
    if (paramLatLng != null) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzbq.zzb(bool1, "Location must be specified");
    boolean bool1 = bool2;
    if (paramFloat >= 0.0F) {
      bool1 = true;
    }
    zzbq.zzb(bool1, "Width must be non-negative");
    return zza(paramLatLng, paramFloat, -1.0F);
  }
  
  public final GroundOverlayOptions position(LatLng paramLatLng, float paramFloat1, float paramFloat2)
  {
    LatLngBounds localLatLngBounds = zze;
    boolean bool2 = false;
    if (localLatLngBounds == null) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzbq.zza(bool1, "Position has already been set using positionFromBounds");
    if (paramLatLng != null) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzbq.zzb(bool1, "Location must be specified");
    if (paramFloat1 >= 0.0F) {
      bool1 = true;
    } else {
      bool1 = false;
    }
    zzbq.zzb(bool1, "Width must be non-negative");
    boolean bool1 = bool2;
    if (paramFloat2 >= 0.0F) {
      bool1 = true;
    }
    zzbq.zzb(bool1, "Height must be non-negative");
    return zza(paramLatLng, paramFloat1, paramFloat2);
  }
  
  public final GroundOverlayOptions positionFromBounds(LatLngBounds paramLatLngBounds)
  {
    boolean bool;
    if (zzb == null) {
      bool = true;
    } else {
      bool = false;
    }
    String str = String.valueOf(zzb);
    StringBuilder localStringBuilder = new StringBuilder(46 + String.valueOf(str).length());
    localStringBuilder.append("Position has already been set using position: ");
    localStringBuilder.append(str);
    zzbq.zza(bool, localStringBuilder.toString());
    zze = paramLatLngBounds;
    return this;
  }
  
  public final GroundOverlayOptions transparency(float paramFloat)
  {
    boolean bool;
    if ((paramFloat >= 0.0F) && (paramFloat <= 1.0F)) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "Transparency must be in the range [0..1]");
    zzi = paramFloat;
    return this;
  }
  
  public final GroundOverlayOptions visible(boolean paramBoolean)
  {
    zzh = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, zza.zza().asBinder(), false);
    zzbgo.zza(paramParcel, 3, getLocation(), paramInt, false);
    zzbgo.zza(paramParcel, 4, getWidth());
    zzbgo.zza(paramParcel, 5, getHeight());
    zzbgo.zza(paramParcel, 6, getBounds(), paramInt, false);
    zzbgo.zza(paramParcel, 7, getBearing());
    zzbgo.zza(paramParcel, 8, getZIndex());
    zzbgo.zza(paramParcel, 9, isVisible());
    zzbgo.zza(paramParcel, 10, getTransparency());
    zzbgo.zza(paramParcel, 11, getAnchorU());
    zzbgo.zza(paramParcel, 12, getAnchorV());
    zzbgo.zza(paramParcel, 13, isClickable());
    zzbgo.zza(paramParcel, i);
  }
  
  public final GroundOverlayOptions zIndex(float paramFloat)
  {
    zzg = paramFloat;
    return this;
  }
}

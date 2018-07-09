package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.maps.model.internal.zzaa;
import com.google.android.gms.maps.model.internal.zzz;

public final class TileOverlayOptions
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<TileOverlayOptions> CREATOR = new zzu();
  private zzz zza;
  private TileProvider zzb;
  private boolean zzc = true;
  private float zzd;
  private boolean zze = true;
  private float zzf = 0.0F;
  
  public TileOverlayOptions() {}
  
  @Hide
  TileOverlayOptions(IBinder paramIBinder, boolean paramBoolean1, float paramFloat1, boolean paramBoolean2, float paramFloat2)
  {
    zza = zzaa.zza(paramIBinder);
    if (zza == null) {
      paramIBinder = null;
    } else {
      paramIBinder = new zzs(this);
    }
    zzb = paramIBinder;
    zzc = paramBoolean1;
    zzd = paramFloat1;
    zze = paramBoolean2;
    zzf = paramFloat2;
  }
  
  public final TileOverlayOptions fadeIn(boolean paramBoolean)
  {
    zze = paramBoolean;
    return this;
  }
  
  public final boolean getFadeIn()
  {
    return zze;
  }
  
  public final TileProvider getTileProvider()
  {
    return zzb;
  }
  
  public final float getTransparency()
  {
    return zzf;
  }
  
  public final float getZIndex()
  {
    return zzd;
  }
  
  public final boolean isVisible()
  {
    return zzc;
  }
  
  public final TileOverlayOptions tileProvider(TileProvider paramTileProvider)
  {
    zzb = paramTileProvider;
    if (zzb == null) {
      paramTileProvider = null;
    } else {
      paramTileProvider = new zzt(this, paramTileProvider);
    }
    zza = paramTileProvider;
    return this;
  }
  
  public final TileOverlayOptions transparency(float paramFloat)
  {
    boolean bool;
    if ((paramFloat >= 0.0F) && (paramFloat <= 1.0F)) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "Transparency must be in the range [0..1]");
    zzf = paramFloat;
    return this;
  }
  
  public final TileOverlayOptions visible(boolean paramBoolean)
  {
    zzc = paramBoolean;
    return this;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, zza.asBinder(), false);
    zzbgo.zza(paramParcel, 3, isVisible());
    zzbgo.zza(paramParcel, 4, getZIndex());
    zzbgo.zza(paramParcel, 5, getFadeIn());
    zzbgo.zza(paramParcel, 6, getTransparency());
    zzbgo.zza(paramParcel, paramInt);
  }
  
  public final TileOverlayOptions zIndex(float paramFloat)
  {
    zzd = paramFloat;
    return this;
  }
}

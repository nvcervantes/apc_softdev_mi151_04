package com.google.android.gms.location;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzbgq;

public final class LocationSettingsStates
  extends zzbgl
{
  public static final Parcelable.Creator<LocationSettingsStates> CREATOR = new zzai();
  private final boolean zza;
  private final boolean zzb;
  private final boolean zzc;
  private final boolean zzd;
  private final boolean zze;
  private final boolean zzf;
  
  @Hide
  public LocationSettingsStates(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, boolean paramBoolean6)
  {
    zza = paramBoolean1;
    zzb = paramBoolean2;
    zzc = paramBoolean3;
    zzd = paramBoolean4;
    zze = paramBoolean5;
    zzf = paramBoolean6;
  }
  
  public static LocationSettingsStates fromIntent(Intent paramIntent)
  {
    return (LocationSettingsStates)zzbgq.zza(paramIntent, "com.google.android.gms.location.LOCATION_SETTINGS_STATES", CREATOR);
  }
  
  public final boolean isBlePresent()
  {
    return zzf;
  }
  
  public final boolean isBleUsable()
  {
    return zzc;
  }
  
  public final boolean isGpsPresent()
  {
    return zzd;
  }
  
  public final boolean isGpsUsable()
  {
    return zza;
  }
  
  public final boolean isLocationPresent()
  {
    return (zzd) || (zze);
  }
  
  public final boolean isLocationUsable()
  {
    return (zza) || (zzb);
  }
  
  public final boolean isNetworkLocationPresent()
  {
    return zze;
  }
  
  public final boolean isNetworkLocationUsable()
  {
    return zzb;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, isGpsUsable());
    zzbgo.zza(paramParcel, 2, isNetworkLocationUsable());
    zzbgo.zza(paramParcel, 3, isBleUsable());
    zzbgo.zza(paramParcel, 4, isGpsPresent());
    zzbgo.zza(paramParcel, 5, isNetworkLocationPresent());
    zzbgo.zza(paramParcel, 6, isBlePresent());
    zzbgo.zza(paramParcel, paramInt);
  }
}

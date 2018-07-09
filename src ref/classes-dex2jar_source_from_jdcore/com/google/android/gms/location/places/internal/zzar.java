package com.google.android.gms.location.places.internal;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.location.places.PlacePhotoMetadata;
import com.google.android.gms.location.places.PlacePhotoResult;
import com.google.android.gms.location.places.Places;
import java.util.Arrays;

@Hide
public final class zzar
  implements PlacePhotoMetadata
{
  private final String zza;
  private final int zzb;
  private final int zzc;
  private final CharSequence zzd;
  private final int zze;
  
  public zzar(String paramString, int paramInt1, int paramInt2, CharSequence paramCharSequence, int paramInt3)
  {
    zza = paramString;
    zzb = paramInt1;
    zzc = paramInt2;
    zzd = paramCharSequence;
    zze = paramInt3;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzar)) {
      return false;
    }
    paramObject = (zzar)paramObject;
    return (zzb == zzb) && (zzc == zzc) && (zzbg.zza(zza, zza)) && (zzbg.zza(zzd, zzd));
  }
  
  public final CharSequence getAttributions()
  {
    return zzd;
  }
  
  public final int getMaxHeight()
  {
    return zzc;
  }
  
  public final int getMaxWidth()
  {
    return zzb;
  }
  
  public final PendingResult<PlacePhotoResult> getPhoto(GoogleApiClient paramGoogleApiClient)
  {
    return getScaledPhoto(paramGoogleApiClient, getMaxWidth(), getMaxHeight());
  }
  
  public final PendingResult<PlacePhotoResult> getScaledPhoto(GoogleApiClient paramGoogleApiClient, int paramInt1, int paramInt2)
  {
    return ((zzh)Places.GeoDataApi).zza(paramGoogleApiClient, this, paramInt1, paramInt2);
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(zzb), Integer.valueOf(zzc), zza, zzd });
  }
  
  public final boolean isDataValid()
  {
    return true;
  }
  
  public final String zza()
  {
    return zza;
  }
  
  public final int zzb()
  {
    return zze;
  }
}

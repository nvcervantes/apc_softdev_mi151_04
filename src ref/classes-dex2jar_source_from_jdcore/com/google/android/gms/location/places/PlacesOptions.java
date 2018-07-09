package com.google.android.gms.location.places;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import java.util.Arrays;
import java.util.Locale;

public final class PlacesOptions
  implements Api.ApiOptions.Optional
{
  @Nullable
  @Hide
  public final String zza = null;
  @Nullable
  @Hide
  public final String zzb = null;
  @Hide
  public final int zzc = 0;
  @Nullable
  @Hide
  public final String zzd = null;
  @Nullable
  @Hide
  public final Locale zze = null;
  
  private PlacesOptions(Builder paramBuilder) {}
  
  public final boolean equals(Object paramObject)
  {
    return ((paramObject instanceof PlacesOptions)) && (zzbg.zza(null, null)) && (zzbg.zza(null, null)) && (zzbg.zza(Integer.valueOf(0), Integer.valueOf(0))) && (zzbg.zza(null, null)) && (zzbg.zza(null, null));
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { null, null, Integer.valueOf(0), null, null });
  }
  
  public static class Builder
  {
    private int zza = 0;
    
    public Builder() {}
    
    public PlacesOptions build()
    {
      return new PlacesOptions(this, null);
    }
  }
}

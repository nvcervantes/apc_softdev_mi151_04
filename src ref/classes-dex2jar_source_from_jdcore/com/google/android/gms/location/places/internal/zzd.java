package com.google.android.gms.location.places.internal;

import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Collections;
import java.util.List;

@Hide
public final class zzd
  extends zzaw
  implements AutocompletePrediction
{
  public zzd(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  private final String zza()
  {
    return zza("ap_description", "");
  }
  
  private final String zzb()
  {
    return zza("ap_primary_text", "");
  }
  
  private final String zzc()
  {
    return zza("ap_secondary_text", "");
  }
  
  private final List<zzb> zzd()
  {
    return zza("ap_matched_subscriptions", zzb.CREATOR, Collections.emptyList());
  }
  
  private final List<zzb> zze()
  {
    return zza("ap_primary_text_matched", zzb.CREATOR, Collections.emptyList());
  }
  
  private final List<zzb> zzf()
  {
    return zza("ap_secondary_text_matched", zzb.CREATOR, Collections.emptyList());
  }
  
  public final CharSequence getFullText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzg.zza(zza(), zzd(), paramCharacterStyle);
  }
  
  public final String getPlaceId()
  {
    return zza("ap_place_id", null);
  }
  
  public final List<Integer> getPlaceTypes()
  {
    return zza("ap_place_types", Collections.emptyList());
  }
  
  public final CharSequence getPrimaryText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzg.zza(zzb(), zze(), paramCharacterStyle);
  }
  
  public final CharSequence getSecondaryText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzg.zza(zzc(), zzf(), paramCharacterStyle);
  }
}

package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import android.text.style.CharacterStyle;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.location.places.AutocompletePrediction;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public final class zza
  extends zzbgl
  implements AutocompletePrediction
{
  @Hide
  public static final Parcelable.Creator<zza> CREATOR = new zzc();
  private static final List<zzb> zza = Collections.emptyList();
  private String zzb;
  private String zzc;
  private List<Integer> zzd;
  private List<zzb> zze;
  private int zzf;
  private String zzg;
  private List<zzb> zzh;
  private String zzi;
  private List<zzb> zzj;
  
  @Hide
  zza(String paramString1, List<Integer> paramList, int paramInt, String paramString2, List<zzb> paramList1, String paramString3, List<zzb> paramList2, String paramString4, List<zzb> paramList3)
  {
    zzc = paramString1;
    zzd = paramList;
    zzf = paramInt;
    zzb = paramString2;
    zze = paramList1;
    zzg = paramString3;
    zzh = paramList2;
    zzi = paramString4;
    zzj = paramList3;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zza)) {
      return false;
    }
    paramObject = (zza)paramObject;
    return (zzbg.zza(zzc, zzc)) && (zzbg.zza(zzd, zzd)) && (zzbg.zza(Integer.valueOf(zzf), Integer.valueOf(zzf))) && (zzbg.zza(zzb, zzb)) && (zzbg.zza(zze, zze)) && (zzbg.zza(zzg, zzg)) && (zzbg.zza(zzh, zzh)) && (zzbg.zza(zzi, zzi)) && (zzbg.zza(zzj, zzj));
  }
  
  public final CharSequence getFullText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzg.zza(zzb, zze, paramCharacterStyle);
  }
  
  @Nullable
  public final String getPlaceId()
  {
    return zzc;
  }
  
  public final List<Integer> getPlaceTypes()
  {
    return zzd;
  }
  
  public final CharSequence getPrimaryText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzg.zza(zzg, zzh, paramCharacterStyle);
  }
  
  public final CharSequence getSecondaryText(@Nullable CharacterStyle paramCharacterStyle)
  {
    return zzg.zza(zzi, zzj, paramCharacterStyle);
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { zzc, zzd, Integer.valueOf(zzf), zzb, zze, zzg, zzh, zzi, zzj });
  }
  
  public final boolean isDataValid()
  {
    return true;
  }
  
  public final String toString()
  {
    return zzbg.zza(this).zza("placeId", zzc).zza("placeTypes", zzd).zza("fullText", zzb).zza("fullTextMatchedSubstrings", zze).zza("primaryText", zzg).zza("primaryTextMatchedSubstrings", zzh).zza("secondaryText", zzi).zza("secondaryTextMatchedSubstrings", zzj).toString();
  }
  
  @Hide
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzb, false);
    zzbgo.zza(paramParcel, 2, zzc, false);
    zzbgo.zza(paramParcel, 3, zzd, false);
    zzbgo.zzc(paramParcel, 4, zze, false);
    zzbgo.zza(paramParcel, 5, zzf);
    zzbgo.zza(paramParcel, 6, zzg, false);
    zzbgo.zzc(paramParcel, 7, zzh, false);
    zzbgo.zza(paramParcel, 8, zzi, false);
    zzbgo.zzc(paramParcel, 9, zzj, false);
    zzbgo.zza(paramParcel, paramInt);
  }
}

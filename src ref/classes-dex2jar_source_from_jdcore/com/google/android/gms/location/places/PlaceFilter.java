package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class PlaceFilter
  extends zza
{
  @Hide
  public static final Parcelable.Creator<PlaceFilter> CREATOR = new zzh();
  private static final PlaceFilter zza = new PlaceFilter();
  private List<Integer> zzb;
  private boolean zzc;
  private List<zzo> zzd;
  private List<String> zze;
  private final Set<Integer> zzf;
  private final Set<zzo> zzg;
  private final Set<String> zzh;
  
  public PlaceFilter()
  {
    this(false, null);
  }
  
  @Hide
  private PlaceFilter(@Nullable Collection<Integer> paramCollection, boolean paramBoolean, @Nullable Collection<String> paramCollection1, @Nullable Collection<zzo> paramCollection2)
  {
    this(zza(null), paramBoolean, zza(paramCollection1), zza(null));
  }
  
  @Hide
  PlaceFilter(@Nullable List<Integer> paramList, boolean paramBoolean, @Nullable List<String> paramList1, @Nullable List<zzo> paramList2)
  {
    if (paramList == null) {
      paramList = Collections.emptyList();
    } else {
      paramList = Collections.unmodifiableList(paramList);
    }
    zzb = paramList;
    zzc = paramBoolean;
    if (paramList2 == null) {
      paramList = Collections.emptyList();
    } else {
      paramList = Collections.unmodifiableList(paramList2);
    }
    zzd = paramList;
    if (paramList1 == null) {
      paramList = Collections.emptyList();
    } else {
      paramList = Collections.unmodifiableList(paramList1);
    }
    zze = paramList;
    zzf = zza(zzb);
    zzg = zza(zzd);
    zzh = zza(zze);
  }
  
  public PlaceFilter(boolean paramBoolean, @Nullable Collection<String> paramCollection)
  {
    this(null, paramBoolean, paramCollection, null);
  }
  
  @Deprecated
  @Hide
  public static PlaceFilter zza()
  {
    new zza(null);
    return new PlaceFilter(null, false, null, null);
  }
  
  @Hide
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof PlaceFilter)) {
      return false;
    }
    paramObject = (PlaceFilter)paramObject;
    return (zzf.equals(zzf)) && (zzc == zzc) && (zzg.equals(zzg)) && (zzh.equals(zzh));
  }
  
  public final Set<String> getPlaceIds()
  {
    return zzh;
  }
  
  @Hide
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { zzf, Boolean.valueOf(zzc), zzg, zzh });
  }
  
  public final boolean isRestrictedToPlacesOpenNow()
  {
    return zzc;
  }
  
  @Hide
  public final String toString()
  {
    zzbi localZzbi = zzbg.zza(this);
    if (!zzf.isEmpty()) {
      localZzbi.zza("types", zzf);
    }
    localZzbi.zza("requireOpenNow", Boolean.valueOf(zzc));
    if (!zzh.isEmpty()) {
      localZzbi.zza("placeIds", zzh);
    }
    if (!zzg.isEmpty()) {
      localZzbi.zza("requestedUserDataTypes", zzg);
    }
    return localZzbi.toString();
  }
  
  @Hide
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzb, false);
    zzbgo.zza(paramParcel, 3, zzc);
    zzbgo.zzc(paramParcel, 4, zzd, false);
    zzbgo.zzb(paramParcel, 6, zze, false);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  @Deprecated
  @Hide
  public static final class zza
  {
    private Collection<Integer> zza = null;
    private boolean zzb = false;
    private Collection<zzo> zzc = null;
    private String[] zzd = null;
    
    private zza() {}
  }
}

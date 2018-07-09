package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.location.LocationRequest;
import java.util.Collections;
import java.util.List;

@Hide
public final class zzchl
  extends zzbgl
{
  public static final Parcelable.Creator<zzchl> CREATOR = new zzchm();
  static final List<zzcfs> zza = ;
  private LocationRequest zzb;
  private List<zzcfs> zzc;
  @Nullable
  private String zzd;
  private boolean zze;
  private boolean zzf;
  private boolean zzg;
  @Nullable
  private String zzh;
  private boolean zzi = true;
  
  zzchl(LocationRequest paramLocationRequest, List<zzcfs> paramList, @Nullable String paramString1, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, String paramString2)
  {
    zzb = paramLocationRequest;
    zzc = paramList;
    zzd = paramString1;
    zze = paramBoolean1;
    zzf = paramBoolean2;
    zzg = paramBoolean3;
    zzh = paramString2;
  }
  
  @Deprecated
  public static zzchl zza(LocationRequest paramLocationRequest)
  {
    return new zzchl(paramLocationRequest, zza, null, false, false, false, null);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzchl)) {
      return false;
    }
    paramObject = (zzchl)paramObject;
    return (zzbg.zza(zzb, zzb)) && (zzbg.zza(zzc, zzc)) && (zzbg.zza(zzd, zzd)) && (zze == zze) && (zzf == zzf) && (zzg == zzg) && (zzbg.zza(zzh, zzh));
  }
  
  public final int hashCode()
  {
    return zzb.hashCode();
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(zzb.toString());
    if (zzd != null)
    {
      localStringBuilder.append(" tag=");
      localStringBuilder.append(zzd);
    }
    if (zzh != null)
    {
      localStringBuilder.append(" moduleId=");
      localStringBuilder.append(zzh);
    }
    localStringBuilder.append(" hideAppOps=");
    localStringBuilder.append(zze);
    localStringBuilder.append(" clients=");
    localStringBuilder.append(zzc);
    localStringBuilder.append(" forceCoarseLocation=");
    localStringBuilder.append(zzf);
    if (zzg) {
      localStringBuilder.append(" exemptFromBackgroundThrottle");
    }
    return localStringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzb, paramInt, false);
    zzbgo.zzc(paramParcel, 5, zzc, false);
    zzbgo.zza(paramParcel, 6, zzd, false);
    zzbgo.zza(paramParcel, 7, zze);
    zzbgo.zza(paramParcel, 8, zzf);
    zzbgo.zza(paramParcel, 9, zzg);
    zzbgo.zza(paramParcel, 10, zzh, false);
    zzbgo.zza(paramParcel, i);
  }
}

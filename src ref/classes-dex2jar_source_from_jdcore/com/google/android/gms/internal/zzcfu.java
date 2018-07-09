package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.location.zzj;
import java.util.Collections;
import java.util.List;

@Hide
public final class zzcfu
  extends zzbgl
{
  public static final Parcelable.Creator<zzcfu> CREATOR = new zzcfv();
  static final List<zzcfs> zza = ;
  static final zzj zzb = new zzj();
  private zzj zzc;
  private List<zzcfs> zzd;
  @Nullable
  private String zze;
  
  zzcfu(zzj paramZzj, List<zzcfs> paramList, String paramString)
  {
    zzc = paramZzj;
    zzd = paramList;
    zze = paramString;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzcfu)) {
      return false;
    }
    paramObject = (zzcfu)paramObject;
    return (zzbg.zza(zzc, zzc)) && (zzbg.zza(zzd, zzd)) && (zzbg.zza(zze, zze));
  }
  
  public final int hashCode()
  {
    return zzc.hashCode();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzc, paramInt, false);
    zzbgo.zzc(paramParcel, 2, zzd, false);
    zzbgo.zza(paramParcel, 3, zze, false);
    zzbgo.zza(paramParcel, i);
  }
}

package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Collections;
import java.util.List;

public final class zzap
  extends zzbgl
{
  public static final Parcelable.Creator<zzap> CREATOR = new zzf();
  private int zza;
  private int zzb;
  private int zzc;
  private int zzd;
  private int zze;
  private int zzf;
  private List<zzao> zzg;
  
  zzap(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, List<zzao> paramList)
  {
    zza = paramInt1;
    zzb = paramInt2;
    zzc = paramInt3;
    zzd = paramInt4;
    zze = paramInt5;
    zzf = paramInt6;
    zzg = Collections.unmodifiableList(paramList);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, 3, zzc);
    zzbgo.zza(paramParcel, 4, zzd);
    zzbgo.zza(paramParcel, 5, zze);
    zzbgo.zza(paramParcel, 6, zzf);
    zzbgo.zzc(paramParcel, 7, zzg, false);
    zzbgo.zza(paramParcel, paramInt);
  }
}

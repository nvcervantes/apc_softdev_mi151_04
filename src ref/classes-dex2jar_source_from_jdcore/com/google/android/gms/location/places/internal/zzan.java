package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Collections;
import java.util.List;

public final class zzan
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<zzan> CREATOR = new zzaq();
  private List<zzao> zza;
  private List<zzap> zzb;
  
  zzan(List<zzao> paramList, List<zzap> paramList1)
  {
    zza = Collections.unmodifiableList(paramList);
    zzb = Collections.unmodifiableList(paramList1);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zzc(paramParcel, 1, zza, false);
    zzbgo.zzc(paramParcel, 2, zzb, false);
    zzbgo.zza(paramParcel, paramInt);
  }
}

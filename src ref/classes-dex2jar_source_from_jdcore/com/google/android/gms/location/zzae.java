package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

@Hide
public final class zzae
  extends zzbgl
{
  public static final Parcelable.Creator<zzae> CREATOR = new zzaf();
  private final String zza;
  private final String zzb;
  private final String zzc;
  
  @Hide
  zzae(String paramString1, String paramString2, String paramString3)
  {
    zzc = paramString1;
    zza = paramString2;
    zzb = paramString3;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza, false);
    zzbgo.zza(paramParcel, 2, zzb, false);
    zzbgo.zza(paramParcel, 5, zzc, false);
    zzbgo.zza(paramParcel, paramInt);
  }
}

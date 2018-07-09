package com.google.android.gms.location.places.internal;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Collections;
import java.util.List;

@Hide
public final class zzag
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<zzag> CREATOR = new zzah();
  private final List<Integer> zza;
  private final String zzb;
  private final Uri zzc;
  private final float zzd;
  private final int zze;
  
  zzag(List<Integer> paramList, String paramString, Uri paramUri, float paramFloat, int paramInt)
  {
    zza = Collections.unmodifiableList(paramList);
    zzb = paramString;
    zzc = paramUri;
    zzd = paramFloat;
    zze = paramInt;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza, false);
    zzbgo.zza(paramParcel, 2, zzb, false);
    zzbgo.zza(paramParcel, 3, zzc, paramInt, false);
    zzbgo.zza(paramParcel, 4, zzd);
    zzbgo.zza(paramParcel, 5, zze);
    zzbgo.zza(paramParcel, i);
  }
}

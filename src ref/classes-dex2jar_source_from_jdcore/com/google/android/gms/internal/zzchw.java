package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import java.util.Arrays;
import java.util.List;

@Deprecated
@Hide
public final class zzchw
  extends zzbgl
{
  public static final Parcelable.Creator<zzchw> CREATOR = new zzchy();
  private final String zza;
  private final String zzb;
  private final List<zzchu> zzc;
  
  zzchw(String paramString1, String paramString2, List<zzchu> paramList)
  {
    zza = paramString1;
    zzb = paramString2;
    zzc = paramList;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzchw)) {
      return false;
    }
    paramObject = (zzchw)paramObject;
    return (zza.equals(zza)) && (zzb.equals(zzb)) && (zzc.equals(zzc));
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { zza, zzb, zzc });
  }
  
  public final String toString()
  {
    return zzbg.zza(this).zza("accountName", zza).zza("placeId", zzb).zza("placeAliases", zzc).toString();
  }
  
  @Hide
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza, false);
    zzbgo.zza(paramParcel, 2, zzb, false);
    zzbgo.zzc(paramParcel, 6, zzc, false);
    zzbgo.zza(paramParcel, paramInt);
  }
}

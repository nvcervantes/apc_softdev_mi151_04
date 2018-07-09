package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.List;

@Deprecated
@Hide
public final class zzal
  extends zzbgl
{
  public static final Parcelable.Creator<zzal> CREATOR = new zzam();
  private String zza;
  private String zzb;
  private String zzc;
  private String zzd;
  private List<String> zze;
  
  public zzal(String paramString1, String paramString2, String paramString3, String paramString4, List<String> paramList)
  {
    zza = paramString1;
    zzb = paramString2;
    zzc = paramString3;
    zzd = paramString4;
    zze = paramList;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzal)) {
      return false;
    }
    paramObject = (zzal)paramObject;
    return (zzbg.zza(zza, zza)) && (zzbg.zza(zzb, zzb)) && (zzbg.zza(zzc, zzc)) && (zzbg.zza(zzd, zzd)) && (zzbg.zza(zze, zze));
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { zza, zzb, zzc, zzd });
  }
  
  public final String toString()
  {
    return zzbg.zza(this).zza("name", zza).zza("address", zzb).zza("internationalPhoneNumber", zzc).zza("regularOpenHours", zzd).zza("attributions", zze).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza, false);
    zzbgo.zza(paramParcel, 2, zzb, false);
    zzbgo.zza(paramParcel, 3, zzc, false);
    zzbgo.zza(paramParcel, 4, zzd, false);
    zzbgo.zzb(paramParcel, 5, zze, false);
    zzbgo.zza(paramParcel, paramInt);
  }
}

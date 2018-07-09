package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzf;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Set;

@Hide
public final class zzo
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<zzo> CREATOR = new zzp();
  private static zzo zza = zza("test_type", 1);
  private static zzo zzb = zza("labeled_place", 6);
  private static zzo zzc = zza("here_content", 7);
  @Hide
  private static Set<zzo> zzd = zzf.zza(zza, zzb, zzc);
  private String zze;
  private int zzf;
  
  zzo(String paramString, int paramInt)
  {
    zzbq.zza(paramString);
    zze = paramString;
    zzf = paramInt;
  }
  
  private static zzo zza(String paramString, int paramInt)
  {
    return new zzo(paramString, paramInt);
  }
  
  @Hide
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzo)) {
      return false;
    }
    paramObject = (zzo)paramObject;
    return (zze.equals(zze)) && (zzf == zzf);
  }
  
  @Hide
  public final int hashCode()
  {
    return zze.hashCode();
  }
  
  @Hide
  public final String toString()
  {
    return zze;
  }
  
  @Hide
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zze, false);
    zzbgo.zza(paramParcel, 2, zzf);
    zzbgo.zza(paramParcel, paramInt);
  }
}

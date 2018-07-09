package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class zzb
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<zzb> CREATOR = new zzax();
  final int zza;
  final int zzb;
  
  @Hide
  public zzb(int paramInt1, int paramInt2)
  {
    zza = paramInt1;
    zzb = paramInt2;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzb)) {
      return false;
    }
    paramObject = (zzb)paramObject;
    return (zzbg.zza(Integer.valueOf(zza), Integer.valueOf(zza))) && (zzbg.zza(Integer.valueOf(zzb), Integer.valueOf(zzb)));
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(zza), Integer.valueOf(zzb) });
  }
  
  public final String toString()
  {
    return zzbg.zza(this).zza("offset", Integer.valueOf(zza)).zza("length", Integer.valueOf(zzb)).toString();
  }
  
  @Hide
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, paramInt);
  }
}

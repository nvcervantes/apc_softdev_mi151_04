package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class StreetViewSource
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<StreetViewSource> CREATOR = new zzq();
  public static final StreetViewSource DEFAULT = new StreetViewSource(0);
  public static final StreetViewSource OUTDOOR = new StreetViewSource(1);
  private static final String zza = "StreetViewSource";
  private final int zzb;
  
  @Hide
  public StreetViewSource(int paramInt)
  {
    zzb = paramInt;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof StreetViewSource)) {
      return false;
    }
    paramObject = (StreetViewSource)paramObject;
    return zzb == zzb;
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(zzb) });
  }
  
  public final String toString()
  {
    String str;
    switch (zzb)
    {
    default: 
      str = String.format("UNKNOWN(%s)", new Object[] { Integer.valueOf(zzb) });
      break;
    case 1: 
      str = "OUTDOOR";
      break;
    case 0: 
      str = "DEFAULT";
    }
    return String.format("StreetViewSource:%s", new Object[] { str });
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, paramInt);
  }
}

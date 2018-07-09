package com.google.android.gms.common.api;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class Scope
  extends zzbgl
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<Scope> CREATOR = new zzf();
  private int zza;
  private final String zzb;
  
  Scope(int paramInt, String paramString)
  {
    zzbq.zza(paramString, "scopeUri must not be null or empty");
    zza = paramInt;
    zzb = paramString;
  }
  
  public Scope(String paramString)
  {
    this(1, paramString);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof Scope)) {
      return false;
    }
    return zzb.equals(zzb);
  }
  
  public final int hashCode()
  {
    return zzb.hashCode();
  }
  
  public final String toString()
  {
    return zzb;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb, false);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  @Hide
  public final String zza()
  {
    return zzb;
  }
}

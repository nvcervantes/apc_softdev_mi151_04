package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import java.util.Arrays;

@Deprecated
@Hide
public final class zzchu
  extends zzbgl
{
  public static final Parcelable.Creator<zzchu> CREATOR = new zzchv();
  private static zzchu zza = new zzchu("Home");
  private static zzchu zzb = new zzchu("Work");
  private final String zzc;
  
  zzchu(String paramString)
  {
    zzc = paramString;
  }
  
  @Hide
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzchu)) {
      return false;
    }
    paramObject = (zzchu)paramObject;
    return zzbg.zza(zzc, zzc);
  }
  
  @Hide
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { zzc });
  }
  
  @Hide
  public final String toString()
  {
    return zzbg.zza(this).zza("alias", zzc).toString();
  }
  
  @Hide
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzc, false);
    zzbgo.zza(paramParcel, paramInt);
  }
}

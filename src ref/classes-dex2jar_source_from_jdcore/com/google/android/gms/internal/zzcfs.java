package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;

@Hide
public final class zzcfs
  extends zzbgl
{
  public static final Parcelable.Creator<zzcfs> CREATOR = new zzcft();
  private int zza;
  private String zzb;
  
  public zzcfs(int paramInt, String paramString)
  {
    zza = paramInt;
    zzb = paramString;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (paramObject != null)
    {
      if (!(paramObject instanceof zzcfs)) {
        return false;
      }
      paramObject = (zzcfs)paramObject;
      if ((zza == zza) && (zzbg.zza(zzb, zzb))) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    return zza;
  }
  
  public final String toString()
  {
    return String.format("%d:%s", new Object[] { Integer.valueOf(zza), zzb });
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb, false);
    zzbgo.zza(paramParcel, paramInt);
  }
}

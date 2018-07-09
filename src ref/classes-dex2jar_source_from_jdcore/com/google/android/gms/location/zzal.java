package com.google.android.gms.location;

import android.app.PendingIntent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Collections;
import java.util.List;

@Hide
public final class zzal
  extends zzbgl
{
  public static final Parcelable.Creator<zzal> CREATOR = new zzam();
  private final List<String> zza;
  private final PendingIntent zzb;
  @Hide
  private final String zzc;
  
  @Hide
  zzal(@Nullable List<String> paramList, @Nullable PendingIntent paramPendingIntent, String paramString)
  {
    if (paramList == null) {
      paramList = Collections.emptyList();
    } else {
      paramList = Collections.unmodifiableList(paramList);
    }
    zza = paramList;
    zzb = paramPendingIntent;
    zzc = paramString;
  }
  
  public static zzal zza(PendingIntent paramPendingIntent)
  {
    zzbq.zza(paramPendingIntent, "PendingIntent can not be null.");
    return new zzal(null, paramPendingIntent, "");
  }
  
  public static zzal zza(List<String> paramList)
  {
    zzbq.zza(paramList, "geofence can't be null.");
    zzbq.zzb(paramList.isEmpty() ^ true, "Geofences must contains at least one id.");
    return new zzal(paramList, null, "");
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zzb(paramParcel, 1, zza, false);
    zzbgo.zza(paramParcel, 2, zzb, paramInt, false);
    zzbgo.zza(paramParcel, 3, zzc, false);
    zzbgo.zza(paramParcel, i);
  }
}

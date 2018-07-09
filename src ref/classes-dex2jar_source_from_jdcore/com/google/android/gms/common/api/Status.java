package com.google.android.gms.common.api;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class Status
  extends zzbgl
  implements Result, ReflectedParcelable
{
  public static final Parcelable.Creator<Status> CREATOR = new zzg();
  @Hide
  public static final Status zza = new Status(0);
  @Hide
  public static final Status zzb = new Status(14);
  @Hide
  public static final Status zzc = new Status(8);
  @Hide
  public static final Status zzd = new Status(15);
  @Hide
  public static final Status zze = new Status(16);
  @Hide
  private static Status zzf = new Status(17);
  @Hide
  private static Status zzg = new Status(18);
  private int zzh;
  private final int zzi;
  @Nullable
  private final String zzj;
  @Nullable
  private final PendingIntent zzk;
  
  public Status(int paramInt)
  {
    this(paramInt, null);
  }
  
  Status(int paramInt1, int paramInt2, @Nullable String paramString, @Nullable PendingIntent paramPendingIntent)
  {
    zzh = paramInt1;
    zzi = paramInt2;
    zzj = paramString;
    zzk = paramPendingIntent;
  }
  
  public Status(int paramInt, @Nullable String paramString)
  {
    this(1, paramInt, paramString, null);
  }
  
  public Status(int paramInt, @Nullable String paramString, @Nullable PendingIntent paramPendingIntent)
  {
    this(1, paramInt, paramString, paramPendingIntent);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof Status)) {
      return false;
    }
    paramObject = (Status)paramObject;
    return (zzh == zzh) && (zzi == zzi) && (zzbg.zza(zzj, zzj)) && (zzbg.zza(zzk, zzk));
  }
  
  public final PendingIntent getResolution()
  {
    return zzk;
  }
  
  public final Status getStatus()
  {
    return this;
  }
  
  public final int getStatusCode()
  {
    return zzi;
  }
  
  @Nullable
  public final String getStatusMessage()
  {
    return zzj;
  }
  
  public final boolean hasResolution()
  {
    return zzk != null;
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(zzh), Integer.valueOf(zzi), zzj, zzk });
  }
  
  public final boolean isCanceled()
  {
    return zzi == 16;
  }
  
  public final boolean isInterrupted()
  {
    return zzi == 14;
  }
  
  public final boolean isSuccess()
  {
    return zzi <= 0;
  }
  
  public final void startResolutionForResult(Activity paramActivity, int paramInt)
    throws IntentSender.SendIntentException
  {
    if (!hasResolution()) {
      return;
    }
    paramActivity.startIntentSenderForResult(zzk.getIntentSender(), paramInt, null, 0, 0, 0);
  }
  
  public final String toString()
  {
    return zzbg.zza(this).zza("statusCode", zza()).zza("resolution", zzk).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, getStatusCode());
    zzbgo.zza(paramParcel, 2, getStatusMessage(), false);
    zzbgo.zza(paramParcel, 3, zzk, paramInt, false);
    zzbgo.zza(paramParcel, 1000, zzh);
    zzbgo.zza(paramParcel, i);
  }
  
  @Hide
  public final String zza()
  {
    if (zzj != null) {
      return zzj;
    }
    return CommonStatusCodes.getStatusCodeString(zzi);
  }
}

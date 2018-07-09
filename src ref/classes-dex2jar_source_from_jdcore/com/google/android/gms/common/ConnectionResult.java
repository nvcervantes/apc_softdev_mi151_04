package com.google.android.gms.common;

import android.app.Activity;
import android.app.PendingIntent;
import android.content.IntentSender.SendIntentException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public final class ConnectionResult
  extends zzbgl
{
  public static final int API_UNAVAILABLE = 16;
  public static final int CANCELED = 13;
  public static final Parcelable.Creator<ConnectionResult> CREATOR = new zzb();
  public static final int DEVELOPER_ERROR = 10;
  @Deprecated
  public static final int DRIVE_EXTERNAL_STORAGE_REQUIRED = 1500;
  public static final int INTERNAL_ERROR = 8;
  public static final int INTERRUPTED = 15;
  public static final int INVALID_ACCOUNT = 5;
  public static final int LICENSE_CHECK_FAILED = 11;
  public static final int NETWORK_ERROR = 7;
  public static final int RESOLUTION_REQUIRED = 6;
  public static final int RESTRICTED_PROFILE = 20;
  public static final int SERVICE_DISABLED = 3;
  public static final int SERVICE_INVALID = 9;
  public static final int SERVICE_MISSING = 1;
  public static final int SERVICE_MISSING_PERMISSION = 19;
  public static final int SERVICE_UPDATING = 18;
  public static final int SERVICE_VERSION_UPDATE_REQUIRED = 2;
  public static final int SIGN_IN_FAILED = 17;
  public static final int SIGN_IN_REQUIRED = 4;
  public static final int SUCCESS = 0;
  public static final int TIMEOUT = 14;
  @Hide
  public static final ConnectionResult zza = new ConnectionResult(0);
  private int zzb;
  private final int zzc;
  private final PendingIntent zzd;
  private final String zze;
  
  public ConnectionResult(int paramInt)
  {
    this(paramInt, null, null);
  }
  
  ConnectionResult(int paramInt1, int paramInt2, PendingIntent paramPendingIntent, String paramString)
  {
    zzb = paramInt1;
    zzc = paramInt2;
    zzd = paramPendingIntent;
    zze = paramString;
  }
  
  public ConnectionResult(int paramInt, PendingIntent paramPendingIntent)
  {
    this(paramInt, paramPendingIntent, null);
  }
  
  public ConnectionResult(int paramInt, PendingIntent paramPendingIntent, String paramString)
  {
    this(1, paramInt, paramPendingIntent, paramString);
  }
  
  static String zza(int paramInt)
  {
    if (paramInt != 99)
    {
      if (paramInt != 1500)
      {
        switch (paramInt)
        {
        default: 
          switch (paramInt)
          {
          default: 
            StringBuilder localStringBuilder = new StringBuilder(31);
            localStringBuilder.append("UNKNOWN_ERROR_CODE(");
            localStringBuilder.append(paramInt);
            localStringBuilder.append(")");
            return localStringBuilder.toString();
          case 21: 
            return "API_VERSION_UPDATE_REQUIRED";
          case 20: 
            return "RESTRICTED_PROFILE";
          case 19: 
            return "SERVICE_MISSING_PERMISSION";
          case 18: 
            return "SERVICE_UPDATING";
          case 17: 
            return "SIGN_IN_FAILED";
          case 16: 
            return "API_UNAVAILABLE";
          case 15: 
            return "INTERRUPTED";
          case 14: 
            return "TIMEOUT";
          }
          return "CANCELED";
        case 11: 
          return "LICENSE_CHECK_FAILED";
        case 10: 
          return "DEVELOPER_ERROR";
        case 9: 
          return "SERVICE_INVALID";
        case 8: 
          return "INTERNAL_ERROR";
        case 7: 
          return "NETWORK_ERROR";
        case 6: 
          return "RESOLUTION_REQUIRED";
        case 5: 
          return "INVALID_ACCOUNT";
        case 4: 
          return "SIGN_IN_REQUIRED";
        case 3: 
          return "SERVICE_DISABLED";
        case 2: 
          return "SERVICE_VERSION_UPDATE_REQUIRED";
        case 1: 
          return "SERVICE_MISSING";
        case 0: 
          return "SUCCESS";
        }
        return "UNKNOWN";
      }
      return "DRIVE_EXTERNAL_STORAGE_REQUIRED";
    }
    return "UNFINISHED";
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof ConnectionResult)) {
      return false;
    }
    paramObject = (ConnectionResult)paramObject;
    return (zzc == zzc) && (zzbg.zza(zzd, zzd)) && (zzbg.zza(zze, zze));
  }
  
  public final int getErrorCode()
  {
    return zzc;
  }
  
  @Nullable
  public final String getErrorMessage()
  {
    return zze;
  }
  
  @Nullable
  public final PendingIntent getResolution()
  {
    return zzd;
  }
  
  public final boolean hasResolution()
  {
    return (zzc != 0) && (zzd != null);
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(zzc), zzd, zze });
  }
  
  public final boolean isSuccess()
  {
    return zzc == 0;
  }
  
  public final void startResolutionForResult(Activity paramActivity, int paramInt)
    throws IntentSender.SendIntentException
  {
    if (!hasResolution()) {
      return;
    }
    paramActivity.startIntentSenderForResult(zzd.getIntentSender(), paramInt, null, 0, 0, 0);
  }
  
  public final String toString()
  {
    return zzbg.zza(this).zza("statusCode", zza(zzc)).zza("resolution", zzd).zza("message", zze).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzb);
    zzbgo.zza(paramParcel, 2, getErrorCode());
    zzbgo.zza(paramParcel, 3, getResolution(), paramInt, false);
    zzbgo.zza(paramParcel, 4, getErrorMessage(), false);
    zzbgo.zza(paramParcel, i);
  }
}

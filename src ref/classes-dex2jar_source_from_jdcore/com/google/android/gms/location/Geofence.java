package com.google.android.gms.location;

import android.os.SystemClock;
import com.google.android.gms.internal.zzchp;

public abstract interface Geofence
{
  public static final int GEOFENCE_TRANSITION_DWELL = 4;
  public static final int GEOFENCE_TRANSITION_ENTER = 1;
  public static final int GEOFENCE_TRANSITION_EXIT = 2;
  public static final long NEVER_EXPIRE = -1L;
  
  public abstract String getRequestId();
  
  public static final class Builder
  {
    private String zza = null;
    private int zzb = 0;
    private long zzc = Long.MIN_VALUE;
    private short zzd = -1;
    private double zze;
    private double zzf;
    private float zzg;
    private int zzh = 0;
    private int zzi = -1;
    
    public Builder() {}
    
    public final Geofence build()
    {
      if (zza == null) {
        throw new IllegalArgumentException("Request ID not set.");
      }
      if (zzb == 0) {
        throw new IllegalArgumentException("Transitions types not set.");
      }
      if (((zzb & 0x4) != 0) && (zzi < 0)) {
        throw new IllegalArgumentException("Non-negative loitering delay needs to be set when transition types include GEOFENCE_TRANSITION_DWELLING.");
      }
      if (zzc == Long.MIN_VALUE) {
        throw new IllegalArgumentException("Expiration not set.");
      }
      if (zzd == -1) {
        throw new IllegalArgumentException("Geofence region not set.");
      }
      if (zzh < 0) {
        throw new IllegalArgumentException("Notification responsiveness should be nonnegative.");
      }
      return new zzchp(zza, zzb, (short)1, zze, zzf, zzg, zzc, zzh, zzi);
    }
    
    public final Builder setCircularRegion(double paramDouble1, double paramDouble2, float paramFloat)
    {
      zzd = 1;
      zze = paramDouble1;
      zzf = paramDouble2;
      zzg = paramFloat;
      return this;
    }
    
    public final Builder setExpirationDuration(long paramLong)
    {
      if (paramLong < 0L)
      {
        zzc = -1L;
        return this;
      }
      zzc = (SystemClock.elapsedRealtime() + paramLong);
      return this;
    }
    
    public final Builder setLoiteringDelay(int paramInt)
    {
      zzi = paramInt;
      return this;
    }
    
    public final Builder setNotificationResponsiveness(int paramInt)
    {
      zzh = paramInt;
      return this;
    }
    
    public final Builder setRequestId(String paramString)
    {
      zza = paramString;
      return this;
    }
    
    public final Builder setTransitionTypes(int paramInt)
    {
      zzb = paramInt;
      return this;
    }
  }
}

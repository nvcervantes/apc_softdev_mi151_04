package com.google.android.gms.common.api;

import android.os.Looper;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.zzco;
import com.google.android.gms.common.api.internal.zzdb;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;

public final class PendingResults
{
  private PendingResults() {}
  
  public static PendingResult<Status> canceledPendingResult()
  {
    zzdb localZzdb = new zzdb(Looper.getMainLooper());
    localZzdb.cancel();
    return localZzdb;
  }
  
  public static <R extends Result> PendingResult<R> canceledPendingResult(R paramR)
  {
    zzbq.zza(paramR, "Result must not be null");
    boolean bool;
    if (paramR.getStatus().getStatusCode() == 16) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool, "Status code must be CommonStatusCodes.CANCELED");
    paramR = new zza(paramR);
    paramR.cancel();
    return paramR;
  }
  
  public static <R extends Result> OptionalPendingResult<R> immediatePendingResult(R paramR)
  {
    zzbq.zza(paramR, "Result must not be null");
    zzc localZzc = new zzc(null);
    localZzc.zza(paramR);
    return new zzco(localZzc);
  }
  
  public static PendingResult<Status> immediatePendingResult(Status paramStatus)
  {
    zzbq.zza(paramStatus, "Result must not be null");
    zzdb localZzdb = new zzdb(Looper.getMainLooper());
    localZzdb.zza(paramStatus);
    return localZzdb;
  }
  
  @Hide
  public static <R extends Result> PendingResult<R> zza(R paramR, GoogleApiClient paramGoogleApiClient)
  {
    zzbq.zza(paramR, "Result must not be null");
    zzbq.zzb(paramR.getStatus().isSuccess() ^ true, "Status code must not be SUCCESS");
    paramGoogleApiClient = new zzb(paramGoogleApiClient, paramR);
    paramGoogleApiClient.zza(paramR);
    return paramGoogleApiClient;
  }
  
  @Hide
  public static PendingResult<Status> zza(Status paramStatus, GoogleApiClient paramGoogleApiClient)
  {
    zzbq.zza(paramStatus, "Result must not be null");
    paramGoogleApiClient = new zzdb(paramGoogleApiClient);
    paramGoogleApiClient.zza(paramStatus);
    return paramGoogleApiClient;
  }
  
  @Hide
  public static <R extends Result> OptionalPendingResult<R> zzb(R paramR, GoogleApiClient paramGoogleApiClient)
  {
    zzbq.zza(paramR, "Result must not be null");
    paramGoogleApiClient = new zzc(paramGoogleApiClient);
    paramGoogleApiClient.zza(paramR);
    return new zzco(paramGoogleApiClient);
  }
  
  static final class zza<R extends Result>
    extends BasePendingResult<R>
  {
    private final R zza;
    
    public zza(R paramR)
    {
      super();
      zza = paramR;
    }
    
    protected final R zza(Status paramStatus)
    {
      if (paramStatus.getStatusCode() != zza.getStatus().getStatusCode()) {
        throw new UnsupportedOperationException("Creating failed results is not supported");
      }
      return zza;
    }
  }
  
  static final class zzb<R extends Result>
    extends BasePendingResult<R>
  {
    private final R zza;
    
    public zzb(GoogleApiClient paramGoogleApiClient, R paramR)
    {
      super();
      zza = paramR;
    }
    
    protected final R zza(Status paramStatus)
    {
      return zza;
    }
  }
  
  static final class zzc<R extends Result>
    extends BasePendingResult<R>
  {
    public zzc(GoogleApiClient paramGoogleApiClient)
    {
      super();
    }
    
    protected final R zza(Status paramStatus)
    {
      throw new UnsupportedOperationException("Creating failed results is not supported");
    }
  }
}

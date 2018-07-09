package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.support.annotation.Nullable;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzan;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzr;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public final class Api<O extends ApiOptions>
{
  private final zza<?, O> zza;
  private final zzh<?, O> zzb;
  private final zzf<?> zzc;
  private final zzi<?> zzd;
  private final String zze;
  
  @Hide
  public <C extends zze> Api(String paramString, zza<C, O> paramZza, zzf<C> paramZzf)
  {
    zzbq.zza(paramZza, "Cannot construct an Api with a null ClientBuilder");
    zzbq.zza(paramZzf, "Cannot construct an Api with a null ClientKey");
    zze = paramString;
    zza = paramZza;
    zzb = null;
    zzc = paramZzf;
    zzd = null;
  }
  
  @Hide
  public final zzd<?, O> zza()
  {
    return zza;
  }
  
  @Hide
  public final zza<?, O> zzb()
  {
    boolean bool;
    if (zza != null) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zza(bool, "This API was constructed with a SimpleClientBuilder. Use getSimpleClientBuilder");
    return zza;
  }
  
  @Hide
  public final zzc<?> zzc()
  {
    if (zzc != null) {
      return zzc;
    }
    throw new IllegalStateException("This API was constructed with null client keys. This should not be possible.");
  }
  
  @Hide
  public final String zzd()
  {
    return zze;
  }
  
  public static abstract interface ApiOptions
  {
    public static abstract interface HasAccountOptions
      extends Api.ApiOptions.HasOptions, Api.ApiOptions.NotRequiredOptions
    {
      public abstract Account getAccount();
    }
    
    public static abstract interface HasGoogleSignInAccountOptions
      extends Api.ApiOptions.HasOptions
    {
      public abstract GoogleSignInAccount getGoogleSignInAccount();
    }
    
    public static abstract interface HasOptions
      extends Api.ApiOptions
    {}
    
    public static final class NoOptions
      implements Api.ApiOptions.NotRequiredOptions
    {
      private NoOptions() {}
    }
    
    public static abstract interface NotRequiredOptions
      extends Api.ApiOptions
    {}
    
    public static abstract interface Optional
      extends Api.ApiOptions.HasOptions, Api.ApiOptions.NotRequiredOptions
    {}
  }
  
  @Hide
  public static abstract class zza<T extends Api.zze, O>
    extends Api.zzd<T, O>
  {
    public zza() {}
    
    @Hide
    public abstract T zza(Context paramContext, Looper paramLooper, zzr paramZzr, O paramO, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener);
  }
  
  @Hide
  public static abstract interface zzb {}
  
  @Hide
  public static class zzc<C extends Api.zzb>
  {
    public zzc() {}
  }
  
  @Hide
  public static class zzd<T extends Api.zzb, O>
  {
    public zzd() {}
    
    public int zza()
    {
      return Integer.MAX_VALUE;
    }
    
    public List<Scope> zza(O paramO)
    {
      return Collections.emptyList();
    }
  }
  
  @Hide
  public static abstract interface zze
    extends Api.zzb
  {
    public abstract boolean l_();
    
    public abstract void zza(zzan paramZzan, Set<Scope> paramSet);
    
    public abstract void zza(zzj paramZzj);
    
    public abstract void zza(zzp paramZzp);
    
    public abstract void zza(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
    
    public abstract boolean zze();
    
    public abstract Intent zzf();
    
    public abstract void zzg();
    
    public abstract boolean zzs();
    
    public abstract boolean zzt();
    
    public abstract boolean zzu();
    
    @Nullable
    public abstract IBinder zzv();
    
    @Hide
    public abstract String zzw();
    
    @Hide
    public abstract int zzx();
  }
  
  @Hide
  public static final class zzf<C extends Api.zze>
    extends Api.zzc<C>
  {
    public zzf() {}
  }
  
  @Hide
  public static abstract interface zzg<T extends IInterface>
    extends Api.zzb
  {}
  
  @Hide
  public static class zzh<T extends Api.zzg, O>
    extends Api.zzd<T, O>
  {}
  
  @Hide
  public static final class zzi<C extends Api.zzg>
    extends Api.zzc<C>
  {}
}

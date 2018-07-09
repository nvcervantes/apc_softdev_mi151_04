package com.google.android.gms.common.api;

import android.accounts.Account;
import android.app.Activity;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.api.internal.BasePendingResult;
import com.google.android.gms.common.api.internal.zzah;
import com.google.android.gms.common.api.internal.zzbm;
import com.google.android.gms.common.api.internal.zzbo;
import com.google.android.gms.common.api.internal.zzbw;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzck;
import com.google.android.gms.common.api.internal.zzcm;
import com.google.android.gms.common.api.internal.zzcq;
import com.google.android.gms.common.api.internal.zzcv;
import com.google.android.gms.common.api.internal.zzda;
import com.google.android.gms.common.api.internal.zzde;
import com.google.android.gms.common.api.internal.zzdo;
import com.google.android.gms.common.api.internal.zzg;
import com.google.android.gms.common.api.internal.zzh;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.common.internal.zzs;
import com.google.android.gms.tasks.Task;
import com.google.android.gms.tasks.TaskCompletionSource;
import java.util.Collection;
import java.util.Collections;

public class GoogleApi<O extends Api.ApiOptions>
{
  @Hide
  protected final zzbm zza;
  private final Context zzb;
  private final Api<O> zzc;
  private final O zzd;
  private final zzh<O> zze;
  private final Looper zzf;
  private final int zzg;
  private final GoogleApiClient zzh;
  private final zzda zzi;
  
  @MainThread
  @Hide
  public GoogleApi(@NonNull Activity paramActivity, Api<O> paramApi, O paramO, zza paramZza)
  {
    zzbq.zza(paramActivity, "Null activity is not permitted.");
    zzbq.zza(paramApi, "Api must not be null.");
    zzbq.zza(paramZza, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
    zzb = paramActivity.getApplicationContext();
    zzc = paramApi;
    zzd = paramO;
    zzf = zzc;
    zze = zzh.zza(zzc, zzd);
    zzh = new zzbw(this);
    zza = zzbm.zza(zzb);
    zzg = zza.zzc();
    zzi = zzb;
    zzah.zza(paramActivity, zza, zze);
    zza.zza(this);
  }
  
  @Deprecated
  @Hide
  public GoogleApi(@NonNull Activity paramActivity, Api<O> paramApi, O paramO, zzda paramZzda)
  {
    this(paramActivity, paramApi, paramO, new zzd().zza(paramZzda).zza(paramActivity.getMainLooper()).zza());
  }
  
  @Hide
  protected GoogleApi(@NonNull Context paramContext, Api<O> paramApi, Looper paramLooper)
  {
    zzbq.zza(paramContext, "Null context is not permitted.");
    zzbq.zza(paramApi, "Api must not be null.");
    zzbq.zza(paramLooper, "Looper must not be null.");
    zzb = paramContext.getApplicationContext();
    zzc = paramApi;
    zzd = null;
    zzf = paramLooper;
    zze = zzh.zza(paramApi);
    zzh = new zzbw(this);
    zza = zzbm.zza(zzb);
    zzg = zza.zzc();
    zzi = new zzg();
  }
  
  @Deprecated
  @Hide
  public GoogleApi(@NonNull Context paramContext, Api<O> paramApi, O paramO, Looper paramLooper, zzda paramZzda)
  {
    this(paramContext, paramApi, null, new zzd().zza(paramLooper).zza(paramZzda).zza());
  }
  
  @Hide
  public GoogleApi(@NonNull Context paramContext, Api<O> paramApi, O paramO, zza paramZza)
  {
    zzbq.zza(paramContext, "Null context is not permitted.");
    zzbq.zza(paramApi, "Api must not be null.");
    zzbq.zza(paramZza, "Settings must not be null; use Settings.DEFAULT_SETTINGS instead.");
    zzb = paramContext.getApplicationContext();
    zzc = paramApi;
    zzd = paramO;
    zzf = zzc;
    zze = zzh.zza(zzc, zzd);
    zzh = new zzbw(this);
    zza = zzbm.zza(zzb);
    zzg = zza.zzc();
    zzi = zzb;
    zza.zza(this);
  }
  
  @Deprecated
  @Hide
  public GoogleApi(@NonNull Context paramContext, Api<O> paramApi, O paramO, zzda paramZzda)
  {
    this(paramContext, paramApi, paramO, new zzd().zza(paramZzda).zza());
  }
  
  private final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zza(int paramInt, @NonNull T paramT)
  {
    paramT.zzg();
    zza.zza(this, paramInt, paramT);
    return paramT;
  }
  
  private final <TResult, A extends Api.zzb> Task<TResult> zza(int paramInt, @NonNull zzde<A, TResult> paramZzde)
  {
    TaskCompletionSource localTaskCompletionSource = new TaskCompletionSource();
    zza.zza(this, paramInt, paramZzde, localTaskCompletionSource, zzi);
    return localTaskCompletionSource.getTask();
  }
  
  @Hide
  private final zzs zzh()
  {
    zzs localZzs = new zzs();
    if ((zzd instanceof Api.ApiOptions.HasGoogleSignInAccountOptions))
    {
      localObject = ((Api.ApiOptions.HasGoogleSignInAccountOptions)zzd).getGoogleSignInAccount();
      if (localObject != null)
      {
        localObject = ((GoogleSignInAccount)localObject).getAccount();
        break label71;
      }
    }
    if ((zzd instanceof Api.ApiOptions.HasAccountOptions)) {
      localObject = ((Api.ApiOptions.HasAccountOptions)zzd).getAccount();
    } else {
      localObject = null;
    }
    label71:
    localZzs = localZzs.zza((Account)localObject);
    if ((zzd instanceof Api.ApiOptions.HasGoogleSignInAccountOptions))
    {
      localObject = ((Api.ApiOptions.HasGoogleSignInAccountOptions)zzd).getGoogleSignInAccount();
      if (localObject != null)
      {
        localObject = ((GoogleSignInAccount)localObject).zzd();
        break label116;
      }
    }
    Object localObject = Collections.emptySet();
    label116:
    return localZzs.zza((Collection)localObject);
  }
  
  @WorkerThread
  @Hide
  public Api.zze zza(Looper paramLooper, zzbo<O> paramZzbo)
  {
    zzr localZzr = zzh().zza(zzb.getPackageName()).zzb(zzb.getClass().getName()).zza();
    return zzc.zzb().zza(zzb, paramLooper, localZzr, zzd, paramZzbo, paramZzbo);
  }
  
  @Hide
  public final Api<O> zza()
  {
    return zzc;
  }
  
  @Hide
  public final <L> zzci<L> zza(@NonNull L paramL, String paramString)
  {
    return zzcm.zzb(paramL, zzf, paramString);
  }
  
  @Hide
  public zzcv zza(Context paramContext, Handler paramHandler)
  {
    return new zzcv(paramContext, paramHandler, zzh().zza());
  }
  
  @Hide
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zza(@NonNull T paramT)
  {
    return zza(0, paramT);
  }
  
  @Hide
  public final Task<Boolean> zza(@NonNull zzck<?> paramZzck)
  {
    zzbq.zza(paramZzck, "Listener key cannot be null.");
    return zza.zza(this, paramZzck);
  }
  
  @Hide
  public final <A extends Api.zzb, T extends zzcq<A, ?>, U extends zzdo<A, ?>> Task<Void> zza(@NonNull T paramT, U paramU)
  {
    zzbq.zza(paramT);
    zzbq.zza(paramU);
    zzbq.zza(paramT.zza(), "Listener has already been released.");
    zzbq.zza(paramU.zza(), "Listener has already been released.");
    zzbq.zzb(paramT.zza().equals(paramU.zza()), "Listener registration and unregistration methods must be constructed with the same ListenerHolder.");
    return zza.zza(this, paramT, paramU);
  }
  
  @Hide
  public final <TResult, A extends Api.zzb> Task<TResult> zza(zzde<A, TResult> paramZzde)
  {
    return zza(0, paramZzde);
  }
  
  @Hide
  public final O zzb()
  {
    return zzd;
  }
  
  @Hide
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    return zza(1, paramT);
  }
  
  @Hide
  public final <TResult, A extends Api.zzb> Task<TResult> zzb(zzde<A, TResult> paramZzde)
  {
    return zza(1, paramZzde);
  }
  
  @Hide
  public final zzh<O> zzc()
  {
    return zze;
  }
  
  @Hide
  public final <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzc(@NonNull T paramT)
  {
    return zza(2, paramT);
  }
  
  @Hide
  public final int zzd()
  {
    return zzg;
  }
  
  @Hide
  public final GoogleApiClient zze()
  {
    return zzh;
  }
  
  @Hide
  public final Looper zzf()
  {
    return zzf;
  }
  
  @Hide
  public final Context zzg()
  {
    return zzb;
  }
  
  @Hide
  public static final class zza
  {
    public static final zza zza = new zzd().zza();
    public final zzda zzb;
    public final Looper zzc;
    
    private zza(zzda paramZzda, Account paramAccount, Looper paramLooper)
    {
      zzb = paramZzda;
      zzc = paramLooper;
    }
  }
}

package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.internal.zzba;
import com.google.android.gms.common.api.internal.zzce;
import com.google.android.gms.common.api.internal.zzci;
import com.google.android.gms.common.api.internal.zzcu;
import com.google.android.gms.common.api.internal.zzdh;
import com.google.android.gms.common.api.internal.zzi;
import com.google.android.gms.common.api.internal.zzm;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzr;
import com.google.android.gms.internal.zzcyg;
import com.google.android.gms.internal.zzcyj;
import com.google.android.gms.internal.zzcyk;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient
{
  public static final int SIGN_IN_MODE_OPTIONAL = 2;
  public static final int SIGN_IN_MODE_REQUIRED = 1;
  private static final Set<GoogleApiClient> zza = Collections.newSetFromMap(new WeakHashMap());
  
  public GoogleApiClient() {}
  
  public static void dumpAll(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    Set localSet = zza;
    int i = 0;
    try
    {
      String str = String.valueOf(paramString).concat("  ");
      Iterator localIterator = zza.iterator();
      while (localIterator.hasNext())
      {
        GoogleApiClient localGoogleApiClient = (GoogleApiClient)localIterator.next();
        paramPrintWriter.append(paramString).append("GoogleApiClient#").println(i);
        localGoogleApiClient.dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
        i += 1;
      }
      return;
    }
    finally {}
  }
  
  @Hide
  public static Set<GoogleApiClient> zza()
  {
    synchronized (zza)
    {
      Set localSet2 = zza;
      return localSet2;
    }
  }
  
  public abstract ConnectionResult blockingConnect();
  
  public abstract ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit);
  
  public abstract PendingResult<Status> clearDefaultAccountAndReconnect();
  
  public abstract void connect();
  
  public void connect(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract void disconnect();
  
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  @NonNull
  public abstract ConnectionResult getConnectionResult(@NonNull Api<?> paramApi);
  
  public abstract boolean hasConnectedApi(@NonNull Api<?> paramApi);
  
  public abstract boolean isConnected();
  
  public abstract boolean isConnecting();
  
  public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void reconnect();
  
  public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void stopAutoManage(@NonNull FragmentActivity paramFragmentActivity);
  
  public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  @NonNull
  @Hide
  public <C extends Api.zze> C zza(@NonNull Api.zzc<C> paramZzc)
  {
    throw new UnsupportedOperationException();
  }
  
  @Hide
  public <L> zzci<L> zza(@NonNull L paramL)
  {
    throw new UnsupportedOperationException();
  }
  
  @Hide
  public <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zza(@NonNull T paramT)
  {
    throw new UnsupportedOperationException();
  }
  
  @Hide
  public void zza(zzdh paramZzdh)
  {
    throw new UnsupportedOperationException();
  }
  
  @Hide
  public boolean zza(@NonNull Api<?> paramApi)
  {
    throw new UnsupportedOperationException();
  }
  
  @Hide
  public boolean zza(zzcu paramZzcu)
  {
    throw new UnsupportedOperationException();
  }
  
  @Hide
  public Context zzb()
  {
    throw new UnsupportedOperationException();
  }
  
  @Hide
  public <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    throw new UnsupportedOperationException();
  }
  
  @Hide
  public void zzb(zzdh paramZzdh)
  {
    throw new UnsupportedOperationException();
  }
  
  @Hide
  public Looper zzc()
  {
    throw new UnsupportedOperationException();
  }
  
  @Hide
  public void zzd()
  {
    throw new UnsupportedOperationException();
  }
  
  public static final class Builder
  {
    private Account zza;
    private final Set<Scope> zzb = new HashSet();
    private final Set<Scope> zzc = new HashSet();
    private int zzd;
    private View zze;
    private String zzf;
    private String zzg;
    private final Map<Api<?>, com.google.android.gms.common.internal.zzt> zzh = new ArrayMap();
    private final Context zzi;
    private final Map<Api<?>, Api.ApiOptions> zzj = new ArrayMap();
    private zzce zzk;
    private int zzl = -1;
    private GoogleApiClient.OnConnectionFailedListener zzm;
    private Looper zzn;
    private GoogleApiAvailability zzo = GoogleApiAvailability.getInstance();
    private Api.zza<? extends zzcyj, zzcyk> zzp = zzcyg.zza;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzq = new ArrayList();
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzr = new ArrayList();
    private boolean zzs = false;
    
    public Builder(@NonNull Context paramContext)
    {
      zzi = paramContext;
      zzn = paramContext.getMainLooper();
      zzf = paramContext.getPackageName();
      zzg = paramContext.getClass().getName();
    }
    
    public Builder(@NonNull Context paramContext, @NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, @NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      this(paramContext);
      zzbq.zza(paramConnectionCallbacks, "Must provide a connected listener");
      zzq.add(paramConnectionCallbacks);
      zzbq.zza(paramOnConnectionFailedListener, "Must provide a connection failed listener");
      zzr.add(paramOnConnectionFailedListener);
    }
    
    private final <O extends Api.ApiOptions> void zza(Api<O> paramApi, O paramO, Scope... paramVarArgs)
    {
      paramO = new HashSet(paramApi.zza().zza(paramO));
      int i = 0;
      int j = paramVarArgs.length;
      while (i < j)
      {
        paramO.add(paramVarArgs[i]);
        i += 1;
      }
      zzh.put(paramApi, new com.google.android.gms.common.internal.zzt(paramO));
    }
    
    public final Builder addApi(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> paramApi)
    {
      zzbq.zza(paramApi, "Api must not be null");
      zzj.put(paramApi, null);
      paramApi = paramApi.zza().zza(null);
      zzc.addAll(paramApi);
      zzb.addAll(paramApi);
      return this;
    }
    
    public final <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull Api<O> paramApi, @NonNull O paramO)
    {
      zzbq.zza(paramApi, "Api must not be null");
      zzbq.zza(paramO, "Null options are not permitted for this Api");
      zzj.put(paramApi, paramO);
      paramApi = paramApi.zza().zza(paramO);
      zzc.addAll(paramApi);
      zzb.addAll(paramApi);
      return this;
    }
    
    public final <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull Api<O> paramApi, @NonNull O paramO, Scope... paramVarArgs)
    {
      zzbq.zza(paramApi, "Api must not be null");
      zzbq.zza(paramO, "Null options are not permitted for this Api");
      zzj.put(paramApi, paramO);
      zza(paramApi, paramO, paramVarArgs);
      return this;
    }
    
    public final Builder addApiIfAvailable(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> paramApi, Scope... paramVarArgs)
    {
      zzbq.zza(paramApi, "Api must not be null");
      zzj.put(paramApi, null);
      zza(paramApi, null, paramVarArgs);
      return this;
    }
    
    public final Builder addConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
    {
      zzbq.zza(paramConnectionCallbacks, "Listener must not be null");
      zzq.add(paramConnectionCallbacks);
      return this;
    }
    
    public final Builder addOnConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      zzbq.zza(paramOnConnectionFailedListener, "Listener must not be null");
      zzr.add(paramOnConnectionFailedListener);
      return this;
    }
    
    public final Builder addScope(@NonNull Scope paramScope)
    {
      zzbq.zza(paramScope, "Scope must not be null");
      zzb.add(paramScope);
      return this;
    }
    
    public final GoogleApiClient build()
    {
      zzbq.zzb(zzj.isEmpty() ^ true, "must call addApi() to add at least one API");
      zzr localZzr = zza();
      ??? = null;
      Object localObject4 = localZzr.zzg();
      ArrayMap localArrayMap1 = new ArrayMap();
      ArrayMap localArrayMap2 = new ArrayMap();
      ArrayList localArrayList = new ArrayList();
      Object localObject2 = zzj.keySet().iterator();
      int i = 0;
      boolean bool;
      while (((Iterator)localObject2).hasNext())
      {
        Api localApi = (Api)((Iterator)localObject2).next();
        Object localObject5 = zzj.get(localApi);
        if (((Map)localObject4).get(localApi) != null) {
          bool = true;
        } else {
          bool = false;
        }
        localArrayMap1.put(localApi, Boolean.valueOf(bool));
        Object localObject6 = new com.google.android.gms.common.api.internal.zzt(localApi, bool);
        localArrayList.add(localObject6);
        Api.zza localZza = localApi.zzb();
        localObject6 = localZza.zza(zzi, zzn, localZzr, localObject5, (GoogleApiClient.ConnectionCallbacks)localObject6, (GoogleApiClient.OnConnectionFailedListener)localObject6);
        localArrayMap2.put(localApi.zzc(), localObject6);
        if (localZza.zza() == 1) {
          if (localObject5 != null) {
            i = 1;
          } else {
            i = 0;
          }
        }
        localObject5 = ???;
        if (((Api.zze)localObject6).zze())
        {
          if (??? != null)
          {
            localObject2 = localApi.zzd();
            ??? = ((Api)???).zzd();
            localObject4 = new StringBuilder(21 + String.valueOf(localObject2).length() + String.valueOf(???).length());
            ((StringBuilder)localObject4).append((String)localObject2);
            ((StringBuilder)localObject4).append(" cannot be used with ");
            ((StringBuilder)localObject4).append((String)???);
            throw new IllegalStateException(((StringBuilder)localObject4).toString());
          }
          localObject5 = localApi;
        }
        ??? = localObject5;
      }
      if (??? != null)
      {
        if (i != 0)
        {
          ??? = ((Api)???).zzd();
          localObject2 = new StringBuilder(82 + String.valueOf(???).length());
          ((StringBuilder)localObject2).append("With using ");
          ((StringBuilder)localObject2).append((String)???);
          ((StringBuilder)localObject2).append(", GamesOptions can only be specified within GoogleSignInOptions.Builder");
          throw new IllegalStateException(((StringBuilder)localObject2).toString());
        }
        if (zza == null) {
          bool = true;
        } else {
          bool = false;
        }
        zzbq.zza(bool, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[] { ((Api)???).zzd() });
        zzbq.zza(zzb.equals(zzc), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[] { ((Api)???).zzd() });
      }
      i = zzba.zza(localArrayMap2.values(), true);
      localObject2 = new zzba(zzi, new ReentrantLock(), zzn, localZzr, zzo, zzp, localArrayMap1, zzq, zzr, localArrayMap2, zzl, i, localArrayList, false);
      synchronized (GoogleApiClient.zze())
      {
        GoogleApiClient.zze().add(localObject2);
        if (zzl >= 0) {
          zzi.zza(zzk).zza(zzl, (GoogleApiClient)localObject2, zzm);
        }
        return localObject2;
      }
    }
    
    public final Builder enableAutoManage(@NonNull FragmentActivity paramFragmentActivity, int paramInt, @Nullable GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      paramFragmentActivity = new zzce(paramFragmentActivity);
      boolean bool;
      if (paramInt >= 0) {
        bool = true;
      } else {
        bool = false;
      }
      zzbq.zzb(bool, "clientId must be non-negative");
      zzl = paramInt;
      zzm = paramOnConnectionFailedListener;
      zzk = paramFragmentActivity;
      return this;
    }
    
    public final Builder enableAutoManage(@NonNull FragmentActivity paramFragmentActivity, @Nullable GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      return enableAutoManage(paramFragmentActivity, 0, paramOnConnectionFailedListener);
    }
    
    public final Builder setAccountName(String paramString)
    {
      if (paramString == null) {
        paramString = null;
      } else {
        paramString = new Account(paramString, "com.google");
      }
      zza = paramString;
      return this;
    }
    
    public final Builder setGravityForPopups(int paramInt)
    {
      zzd = paramInt;
      return this;
    }
    
    public final Builder setHandler(@NonNull Handler paramHandler)
    {
      zzbq.zza(paramHandler, "Handler must not be null");
      zzn = paramHandler.getLooper();
      return this;
    }
    
    public final Builder setViewForPopups(@NonNull View paramView)
    {
      zzbq.zza(paramView, "View must not be null");
      zze = paramView;
      return this;
    }
    
    public final Builder useDefaultAccount()
    {
      return setAccountName("<<default account>>");
    }
    
    @Hide
    public final zzr zza()
    {
      zzcyk localZzcyk = zzcyk.zza;
      if (zzj.containsKey(zzcyg.zzb)) {
        localZzcyk = (zzcyk)zzj.get(zzcyg.zzb);
      }
      return new zzr(zza, zzb, zzh, zzd, zze, zzf, zzg, localZzcyk);
    }
  }
  
  public static abstract interface ConnectionCallbacks
  {
    public static final int CAUSE_NETWORK_LOST = 2;
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    
    public abstract void onConnected(@Nullable Bundle paramBundle);
    
    public abstract void onConnectionSuspended(int paramInt);
  }
  
  public static abstract interface OnConnectionFailedListener
  {
    public abstract void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult);
  }
}

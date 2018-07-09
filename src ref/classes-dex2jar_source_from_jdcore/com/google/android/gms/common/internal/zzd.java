package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzd<T extends IInterface>
{
  @Hide
  private static String[] zzaa = { "service_esmobile", "service_googleme" };
  final Handler zza;
  protected zzj zzb;
  protected AtomicInteger zzc = new AtomicInteger(0);
  private int zzd;
  private long zze;
  private long zzf;
  private int zzg;
  private long zzh;
  private zzam zzi;
  private final Context zzj;
  private final Looper zzk;
  private final zzag zzl;
  private final com.google.android.gms.common.zzf zzm;
  private final Object zzn = new Object();
  private final Object zzo = new Object();
  private zzay zzp;
  private T zzq;
  private final ArrayList<zzi<?>> zzr = new ArrayList();
  private zzl zzs;
  private int zzt = 1;
  private final zzf zzu;
  private final zzg zzv;
  private final int zzw;
  private final String zzx;
  private ConnectionResult zzy = null;
  private boolean zzz = false;
  
  protected zzd(Context paramContext, Looper paramLooper, int paramInt, zzf paramZzf, zzg paramZzg, String paramString)
  {
    this(paramContext, paramLooper, zzag.zza(paramContext), com.google.android.gms.common.zzf.zza(), paramInt, (zzf)zzbq.zza(paramZzf), (zzg)zzbq.zza(paramZzg), null);
  }
  
  protected zzd(Context paramContext, Looper paramLooper, zzag paramZzag, com.google.android.gms.common.zzf paramZzf, int paramInt, zzf paramZzf1, zzg paramZzg, String paramString)
  {
    zzj = ((Context)zzbq.zza(paramContext, "Context must not be null"));
    zzk = ((Looper)zzbq.zza(paramLooper, "Looper must not be null"));
    zzl = ((zzag)zzbq.zza(paramZzag, "Supervisor must not be null"));
    zzm = ((com.google.android.gms.common.zzf)zzbq.zza(paramZzf, "API availability must not be null"));
    zza = new zzh(this, paramLooper);
    zzw = paramInt;
    zzu = paramZzf1;
    zzv = paramZzg;
    zzx = paramString;
  }
  
  private final boolean zza(int paramInt1, int paramInt2, T paramT)
  {
    synchronized (zzn)
    {
      if (zzt != paramInt1) {
        return false;
      }
      zzb(paramInt2, paramT);
      return true;
    }
  }
  
  private final void zzb(int paramInt, T paramT)
  {
    boolean bool = true;
    int i;
    if (paramInt == 4) {
      i = 1;
    } else {
      i = 0;
    }
    int j;
    if (paramT != null) {
      j = 1;
    } else {
      j = 0;
    }
    if (i != j) {
      bool = false;
    }
    zzbq.zzb(bool);
    for (;;)
    {
      synchronized (zzn)
      {
        zzt = paramInt;
        zzq = paramT;
        zza(paramInt, paramT);
        switch (paramInt)
        {
        case 4: 
          zza(paramT);
          break;
        case 2: 
        case 3: 
          if ((zzs != null) && (zzi != null))
          {
            paramT = zzi.zza();
            str1 = zzi.zzb();
            localObject2 = new StringBuilder(70 + String.valueOf(paramT).length() + String.valueOf(str1).length());
            ((StringBuilder)localObject2).append("Calling connect() while still connected, missing disconnect() for ");
            ((StringBuilder)localObject2).append(paramT);
            ((StringBuilder)localObject2).append(" on ");
            ((StringBuilder)localObject2).append(str1);
            Log.e("GmsClient", ((StringBuilder)localObject2).toString());
            zzl.zza(zzi.zza(), zzi.zzb(), zzi.zzc(), zzs, zzi());
            zzc.incrementAndGet();
          }
          zzs = new zzl(this, zzc.get());
          zzi = new zzam(zzy(), zza(), false, 129);
          paramT = zzl;
          String str1 = zzi.zza();
          Object localObject2 = zzi.zzb();
          paramInt = zzi.zzc();
          zzl localZzl = zzs;
          String str2 = zzi();
          if (!paramT.zza(new zzah(str1, (String)localObject2, paramInt), localZzl, str2))
          {
            paramT = zzi.zza();
            str1 = zzi.zzb();
            localObject2 = new StringBuilder(34 + String.valueOf(paramT).length() + String.valueOf(str1).length());
            ((StringBuilder)localObject2).append("unable to connect to service: ");
            ((StringBuilder)localObject2).append(paramT);
            ((StringBuilder)localObject2).append(" on ");
            ((StringBuilder)localObject2).append(str1);
            Log.e("GmsClient", ((StringBuilder)localObject2).toString());
            zza(16, null, zzc.get());
          }
          break;
        case 1: 
          if (zzs != null)
          {
            zzl.zza(zza(), zzy(), 129, zzs, zzi());
            zzs = null;
          }
          return;
        }
      }
    }
  }
  
  @Hide
  private final void zzc(int paramInt)
  {
    if (zzj())
    {
      paramInt = 5;
      zzz = true;
    }
    else
    {
      paramInt = 4;
    }
    zza.sendMessage(zza.obtainMessage(paramInt, zzc.get(), 16));
  }
  
  @Nullable
  @Hide
  private final String zzi()
  {
    if (zzx == null) {
      return zzj.getClass().getName();
    }
    return zzx;
  }
  
  @Hide
  private final boolean zzj()
  {
    for (;;)
    {
      synchronized (zzn)
      {
        if (zzt == 3)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  private final boolean zzk()
  {
    if (zzz) {
      return false;
    }
    if (TextUtils.isEmpty(zzb())) {
      return false;
    }
    if (TextUtils.isEmpty(null)) {
      return false;
    }
    try
    {
      Class.forName(zzb());
      return true;
    }
    catch (ClassNotFoundException localClassNotFoundException) {}
    return false;
  }
  
  public boolean l_()
  {
    return false;
  }
  
  public Bundle q_()
  {
    return null;
  }
  
  @Nullable
  @Hide
  protected abstract T zza(IBinder paramIBinder);
  
  @NonNull
  @Hide
  protected abstract String zza();
  
  @CallSuper
  protected void zza(int paramInt)
  {
    zzd = paramInt;
    zze = System.currentTimeMillis();
  }
  
  @Hide
  protected final void zza(int paramInt1, @Nullable Bundle paramBundle, int paramInt2)
  {
    zza.sendMessage(zza.obtainMessage(7, paramInt2, -1, new zzo(this, paramInt1, null)));
  }
  
  protected void zza(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2)
  {
    zza.sendMessage(zza.obtainMessage(1, paramInt2, -1, new zzn(this, paramInt1, paramIBinder, paramBundle)));
  }
  
  void zza(int paramInt, T paramT) {}
  
  @CallSuper
  protected void zza(@NonNull T paramT)
  {
    zzf = System.currentTimeMillis();
  }
  
  @CallSuper
  protected void zza(ConnectionResult paramConnectionResult)
  {
    zzg = paramConnectionResult.getErrorCode();
    zzh = System.currentTimeMillis();
  }
  
  @WorkerThread
  @Hide
  public final void zza(zzan arg1, Set<Scope> paramSet)
  {
    Bundle localBundle = zzc();
    zzz localZzz = new zzz(zzw);
    zza = zzj.getPackageName();
    zzd = localBundle;
    if (paramSet != null) {
      zzc = ((Scope[])paramSet.toArray(new Scope[paramSet.size()]));
    }
    if (l_())
    {
      if (zzac() != null) {
        paramSet = zzac();
      } else {
        paramSet = new Account("<<default account>>", "com.google");
      }
      zze = paramSet;
      if (??? != null) {
        zzb = ???.asBinder();
      }
    }
    else if (zzag())
    {
      zze = zzac();
    }
    zzf = zzad();
    try
    {
      synchronized (zzo)
      {
        if (zzp != null) {
          zzp.zza(new zzk(this, zzc.get()), localZzz);
        } else {
          Log.w("GmsClient", "mServiceBroker is null, client disconnected");
        }
        return;
      }
      return;
    }
    catch (RemoteException|RuntimeException ???)
    {
      Log.w("GmsClient", "IGmsServiceBroker.getService failed", ???);
      zza(8, null, null, zzc.get());
      return;
    }
    catch (SecurityException ???)
    {
      throw ???;
    }
    catch (DeadObjectException ???)
    {
      Log.w("GmsClient", "IGmsServiceBroker.getService failed", ???);
      zzb(1);
    }
  }
  
  public void zza(@NonNull zzj paramZzj)
  {
    zzb = ((zzj)zzbq.zza(paramZzj, "Connection progress callbacks cannot be null."));
    zzb(2, null);
  }
  
  protected final void zza(@NonNull zzj paramZzj, int paramInt, @Nullable PendingIntent paramPendingIntent)
  {
    zzb = ((zzj)zzbq.zza(paramZzj, "Connection progress callbacks cannot be null."));
    zza.sendMessage(zza.obtainMessage(3, zzc.get(), paramInt, paramPendingIntent));
  }
  
  public void zza(@NonNull zzp paramZzp)
  {
    paramZzp.zza();
  }
  
  public final void zza(String paramString, FileDescriptor arg2, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    synchronized (zzn)
    {
      int i = zzt;
      paramArrayOfString = zzq;
      synchronized (zzo)
      {
        Object localObject = zzp;
        paramPrintWriter.append(paramString).append("mConnectState=");
        switch (i)
        {
        default: 
          ??? = "UNKNOWN";
        }
        for (;;)
        {
          paramPrintWriter.print(???);
          break;
          ??? = "DISCONNECTING";
          continue;
          ??? = "CONNECTED";
          continue;
          ??? = "LOCAL_CONNECTING";
          continue;
          ??? = "REMOTE_CONNECTING";
          continue;
          ??? = "DISCONNECTED";
        }
        paramPrintWriter.append(" mService=");
        if (paramArrayOfString == null) {
          paramPrintWriter.append("null");
        } else {
          paramPrintWriter.append(zzb()).append("@").append(Integer.toHexString(System.identityHashCode(paramArrayOfString.asBinder())));
        }
        paramPrintWriter.append(" mServiceBroker=");
        if (localObject == null) {
          paramPrintWriter.println("null");
        } else {
          paramPrintWriter.append("IGmsServiceBroker@").println(Integer.toHexString(System.identityHashCode(((zzay)localObject).asBinder())));
        }
        paramArrayOfString = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
        long l;
        StringBuilder localStringBuilder;
        if (zzf > 0L)
        {
          ??? = paramPrintWriter.append(paramString).append("lastConnectedTime=");
          l = zzf;
          localObject = paramArrayOfString.format(new Date(zzf));
          localStringBuilder = new StringBuilder(String.valueOf(localObject).length() + 21);
          localStringBuilder.append(l);
          localStringBuilder.append(" ");
          localStringBuilder.append((String)localObject);
          ???.println(localStringBuilder.toString());
        }
        if (zze > 0L)
        {
          paramPrintWriter.append(paramString).append("lastSuspendedCause=");
          switch (zzd)
          {
          default: 
            ??? = String.valueOf(zzd);
          }
          for (;;)
          {
            paramPrintWriter.append(???);
            break;
            ??? = "CAUSE_NETWORK_LOST";
            continue;
            ??? = "CAUSE_SERVICE_DISCONNECTED";
          }
          ??? = paramPrintWriter.append(" lastSuspendedTime=");
          l = zze;
          localObject = paramArrayOfString.format(new Date(zze));
          localStringBuilder = new StringBuilder(String.valueOf(localObject).length() + 21);
          localStringBuilder.append(l);
          localStringBuilder.append(" ");
          localStringBuilder.append((String)localObject);
          ???.println(localStringBuilder.toString());
        }
        if (zzh > 0L)
        {
          paramPrintWriter.append(paramString).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(zzg));
          paramString = paramPrintWriter.append(" lastFailedTime=");
          l = zzh;
          ??? = paramArrayOfString.format(new Date(zzh));
          paramPrintWriter = new StringBuilder(21 + String.valueOf(???).length());
          paramPrintWriter.append(l);
          paramPrintWriter.append(" ");
          paramPrintWriter.append(???);
          paramString.println(paramPrintWriter.toString());
        }
        return;
      }
    }
  }
  
  @Hide
  public final Context zzaa()
  {
    return zzj;
  }
  
  @Hide
  public final Looper zzab()
  {
    return zzk;
  }
  
  public Account zzac()
  {
    return null;
  }
  
  public zzc[] zzad()
  {
    return new zzc[0];
  }
  
  @Hide
  protected final void zzae()
  {
    if (!zzs()) {
      throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
    }
  }
  
  @Hide
  public final T zzaf()
    throws DeadObjectException
  {
    for (;;)
    {
      synchronized (zzn)
      {
        if (zzt == 5) {
          throw new DeadObjectException();
        }
        zzae();
        if (zzq != null)
        {
          bool = true;
          zzbq.zza(bool, "Client is connected but service is null");
          IInterface localIInterface = zzq;
          return localIInterface;
        }
      }
      boolean bool = false;
    }
  }
  
  public boolean zzag()
  {
    return false;
  }
  
  protected Set<Scope> zzah()
  {
    return Collections.EMPTY_SET;
  }
  
  @NonNull
  @Hide
  protected abstract String zzb();
  
  @Hide
  public final void zzb(int paramInt)
  {
    zza.sendMessage(zza.obtainMessage(6, zzc.get(), paramInt));
  }
  
  @Hide
  protected Bundle zzc()
  {
    return new Bundle();
  }
  
  public boolean zze()
  {
    return false;
  }
  
  public Intent zzf()
  {
    throw new UnsupportedOperationException("Not a sign in API");
  }
  
  public void zzg()
  {
    zzc.incrementAndGet();
    synchronized (zzr)
    {
      int j = zzr.size();
      int i = 0;
      while (i < j)
      {
        ((zzi)zzr.get(i)).zze();
        i += 1;
      }
      zzr.clear();
      synchronized (zzo)
      {
        zzp = null;
        zzb(1, null);
        return;
      }
    }
  }
  
  public final boolean zzs()
  {
    for (;;)
    {
      synchronized (zzn)
      {
        if (zzt == 4)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public final boolean zzt()
  {
    for (;;)
    {
      synchronized (zzn)
      {
        if (zzt == 2) {
          break label40;
        }
        if (zzt == 3)
        {
          break label40;
          return bool;
        }
      }
      boolean bool = false;
      continue;
      label40:
      bool = true;
    }
  }
  
  public boolean zzu()
  {
    return true;
  }
  
  @Nullable
  public final IBinder zzv()
  {
    synchronized (zzo)
    {
      if (zzp == null) {
        return null;
      }
      IBinder localIBinder = zzp.asBinder();
      return localIBinder;
    }
  }
  
  @Hide
  public final String zzw()
  {
    if ((zzs()) && (zzi != null)) {
      return zzi.zzb();
    }
    throw new RuntimeException("Failed to connect when checking package");
  }
  
  @Hide
  protected String zzy()
  {
    return "com.google.android.gms";
  }
  
  public final void zzz()
  {
    int i = zzm.isGooglePlayServicesAvailable(zzj);
    if (i != 0)
    {
      zzb(1, null);
      zza(new zzm(this), i, null);
      return;
    }
    zza(new zzm(this));
  }
}

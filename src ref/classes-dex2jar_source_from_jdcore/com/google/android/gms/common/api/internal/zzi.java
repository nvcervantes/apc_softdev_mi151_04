package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import android.util.SparseArray;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.internal.zzbq;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.atomic.AtomicReference;

public class zzi
  extends zzo
{
  private final SparseArray<zza> zze = new SparseArray();
  
  private zzi(zzcf paramZzcf)
  {
    super(paramZzcf);
    zzd.zza("AutoManageHelper", this);
  }
  
  public static zzi zza(zzce paramZzce)
  {
    paramZzce = zzb(paramZzce);
    zzi localZzi = (zzi)paramZzce.zza("AutoManageHelper", zzi.class);
    if (localZzi != null) {
      return localZzi;
    }
    return new zzi(paramZzce);
  }
  
  @Nullable
  private final zza zzb(int paramInt)
  {
    if (zze.size() <= paramInt) {
      return null;
    }
    return (zza)zze.get(zze.keyAt(paramInt));
  }
  
  public final void zza()
  {
    super.zza();
    boolean bool = zza;
    Object localObject = String.valueOf(zze);
    StringBuilder localStringBuilder = new StringBuilder(14 + String.valueOf(localObject).length());
    localStringBuilder.append("onStart ");
    localStringBuilder.append(bool);
    localStringBuilder.append(" ");
    localStringBuilder.append((String)localObject);
    Log.d("AutoManageHelper", localStringBuilder.toString());
    if (zzb.get() == null)
    {
      int i = 0;
      while (i < zze.size())
      {
        localObject = zzb(i);
        if (localObject != null) {
          zzb.connect();
        }
        i += 1;
      }
    }
  }
  
  public final void zza(int paramInt)
  {
    zza localZza = (zza)zze.get(paramInt);
    zze.remove(paramInt);
    if (localZza != null)
    {
      zzb.unregisterConnectionFailedListener(localZza);
      zzb.disconnect();
    }
  }
  
  public final void zza(int paramInt, GoogleApiClient paramGoogleApiClient, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzbq.zza(paramGoogleApiClient, "GoogleApiClient instance cannot be null");
    if (zze.indexOfKey(paramInt) < 0) {
      bool = true;
    } else {
      bool = false;
    }
    Object localObject = new StringBuilder(54);
    ((StringBuilder)localObject).append("Already managing a GoogleApiClient with id ");
    ((StringBuilder)localObject).append(paramInt);
    zzbq.zza(bool, ((StringBuilder)localObject).toString());
    localObject = (zzp)zzb.get();
    boolean bool = zza;
    String str = String.valueOf(localObject);
    StringBuilder localStringBuilder = new StringBuilder(49 + String.valueOf(str).length());
    localStringBuilder.append("starting AutoManage for client ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" ");
    localStringBuilder.append(bool);
    localStringBuilder.append(" ");
    localStringBuilder.append(str);
    Log.d("AutoManageHelper", localStringBuilder.toString());
    paramOnConnectionFailedListener = new zza(paramInt, paramGoogleApiClient, paramOnConnectionFailedListener);
    zze.put(paramInt, paramOnConnectionFailedListener);
    if ((zza) && (localObject == null))
    {
      paramOnConnectionFailedListener = String.valueOf(paramGoogleApiClient);
      localObject = new StringBuilder(11 + String.valueOf(paramOnConnectionFailedListener).length());
      ((StringBuilder)localObject).append("connecting ");
      ((StringBuilder)localObject).append(paramOnConnectionFailedListener);
      Log.d("AutoManageHelper", ((StringBuilder)localObject).toString());
      paramGoogleApiClient.connect();
    }
  }
  
  protected final void zza(ConnectionResult paramConnectionResult, int paramInt)
  {
    Log.w("AutoManageHelper", "Unresolved error while connecting client. Stopping auto-manage.");
    if (paramInt < 0)
    {
      Log.wtf("AutoManageHelper", "AutoManageLifecycleHelper received onErrorResolutionFailed callback but no failing client ID is set", new Exception());
      return;
    }
    Object localObject = (zza)zze.get(paramInt);
    if (localObject != null)
    {
      zza(paramInt);
      localObject = zzc;
      if (localObject != null) {
        ((GoogleApiClient.OnConnectionFailedListener)localObject).onConnectionFailed(paramConnectionResult);
      }
    }
  }
  
  public final void zza(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    int i = 0;
    while (i < zze.size())
    {
      zza localZza = zzb(i);
      if (localZza != null)
      {
        paramPrintWriter.append(paramString).append("GoogleApiClient #").print(zza);
        paramPrintWriter.println(":");
        zzb.dump(String.valueOf(paramString).concat("  "), paramFileDescriptor, paramPrintWriter, paramArrayOfString);
      }
      i += 1;
    }
  }
  
  public final void zzb()
  {
    super.zzb();
    int i = 0;
    while (i < zze.size())
    {
      zza localZza = zzb(i);
      if (localZza != null) {
        zzb.disconnect();
      }
      i += 1;
    }
  }
  
  protected final void zzc()
  {
    int i = 0;
    while (i < zze.size())
    {
      zza localZza = zzb(i);
      if (localZza != null) {
        zzb.connect();
      }
      i += 1;
    }
  }
  
  final class zza
    implements GoogleApiClient.OnConnectionFailedListener
  {
    public final int zza;
    public final GoogleApiClient zzb;
    public final GoogleApiClient.OnConnectionFailedListener zzc;
    
    public zza(int paramInt, GoogleApiClient paramGoogleApiClient, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      zza = paramInt;
      zzb = paramGoogleApiClient;
      zzc = paramOnConnectionFailedListener;
      paramGoogleApiClient.registerConnectionFailedListener(this);
    }
    
    public final void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
    {
      String str = String.valueOf(paramConnectionResult);
      StringBuilder localStringBuilder = new StringBuilder(27 + String.valueOf(str).length());
      localStringBuilder.append("beginFailureResolution for ");
      localStringBuilder.append(str);
      Log.d("AutoManageHelper", localStringBuilder.toString());
      zzb(paramConnectionResult, zza);
    }
  }
}

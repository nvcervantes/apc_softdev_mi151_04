package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public class zzak
  extends GoogleApiClient
{
  private final String zza;
  
  public zzak(String paramString)
  {
    zza = paramString;
  }
  
  public ConnectionResult blockingConnect()
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit)
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public PendingResult<Status> clearDefaultAccountAndReconnect()
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public void connect()
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public void disconnect()
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    throw new UnsupportedOperationException(zza);
  }
  
  @NonNull
  public ConnectionResult getConnectionResult(@NonNull Api<?> paramApi)
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public boolean hasConnectedApi(@NonNull Api<?> paramApi)
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public boolean isConnected()
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public boolean isConnecting()
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public void reconnect()
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public void stopAutoManage(@NonNull FragmentActivity paramFragmentActivity)
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    throw new UnsupportedOperationException(zza);
  }
  
  public void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    throw new UnsupportedOperationException(zza);
  }
}

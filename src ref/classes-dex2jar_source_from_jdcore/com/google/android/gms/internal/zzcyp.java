package com.google.android.gms.internal;

import android.os.IInterface;
import android.os.RemoteException;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Status;

public abstract interface zzcyp
  extends IInterface
{
  public abstract void zza(ConnectionResult paramConnectionResult, zzcym paramZzcym)
    throws RemoteException;
  
  public abstract void zza(Status paramStatus)
    throws RemoteException;
  
  public abstract void zza(Status paramStatus, GoogleSignInAccount paramGoogleSignInAccount)
    throws RemoteException;
  
  public abstract void zza(zzcyw paramZzcyw)
    throws RemoteException;
  
  public abstract void zzb(Status paramStatus)
    throws RemoteException;
}

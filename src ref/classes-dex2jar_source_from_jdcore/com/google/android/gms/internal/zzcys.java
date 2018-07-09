package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.zzan;

public final class zzcys
  extends zzev
  implements zzcyr
{
  zzcys(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.signin.internal.ISignInService");
  }
  
  public final void zza(int paramInt)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeInt(paramInt);
    zzb(7, localParcel);
  }
  
  public final void zza(zzan paramZzan, int paramInt, boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzan);
    localParcel.writeInt(paramInt);
    zzex.zza(localParcel, paramBoolean);
    zzb(9, localParcel);
  }
  
  public final void zza(zzcyu paramZzcyu, zzcyp paramZzcyp)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzcyu);
    zzex.zza(localParcel, paramZzcyp);
    zzb(12, localParcel);
  }
}

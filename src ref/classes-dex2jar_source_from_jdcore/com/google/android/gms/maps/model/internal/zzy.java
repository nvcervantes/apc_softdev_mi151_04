package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzy
  extends zzev
  implements zzw
{
  zzy(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.ITileOverlayDelegate");
  }
  
  public final void zza()
    throws RemoteException
  {
    zzb(1, a_());
  }
  
  public final void zza(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    zzb(4, localParcel);
  }
  
  public final void zza(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(6, localParcel);
  }
  
  public final boolean zza(zzw paramZzw)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzw);
    paramZzw = zza(8, localParcel);
    boolean bool = zzex.zza(paramZzw);
    paramZzw.recycle();
    return bool;
  }
  
  public final void zzb()
    throws RemoteException
  {
    zzb(2, a_());
  }
  
  public final void zzb(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    zzb(12, localParcel);
  }
  
  public final void zzb(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(10, localParcel);
  }
  
  public final String zzc()
    throws RemoteException
  {
    Parcel localParcel = zza(3, a_());
    String str = localParcel.readString();
    localParcel.recycle();
    return str;
  }
  
  public final float zzd()
    throws RemoteException
  {
    Parcel localParcel = zza(5, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final boolean zze()
    throws RemoteException
  {
    Parcel localParcel = zza(7, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final int zzf()
    throws RemoteException
  {
    Parcel localParcel = zza(9, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
  
  public final boolean zzg()
    throws RemoteException
  {
    Parcel localParcel = zza(11, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final float zzh()
    throws RemoteException
  {
    Parcel localParcel = zza(13, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
}

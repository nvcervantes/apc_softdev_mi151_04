package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.LatLng;

public final class zzr
  extends zzev
  implements zzp
{
  zzr(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IMarkerDelegate");
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
    zzb(22, localParcel);
  }
  
  public final void zza(float paramFloat1, float paramFloat2)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat1);
    localParcel.writeFloat(paramFloat2);
    zzb(19, localParcel);
  }
  
  public final void zza(IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    zzb(18, localParcel);
  }
  
  public final void zza(LatLng paramLatLng)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLatLng);
    zzb(3, localParcel);
  }
  
  public final void zza(String paramString)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeString(paramString);
    zzb(5, localParcel);
  }
  
  public final void zza(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(9, localParcel);
  }
  
  public final boolean zza(zzp paramZzp)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzp);
    paramZzp = zza(16, localParcel);
    boolean bool = zzex.zza(paramZzp);
    paramZzp.recycle();
    return bool;
  }
  
  public final String zzb()
    throws RemoteException
  {
    Parcel localParcel = zza(2, a_());
    String str = localParcel.readString();
    localParcel.recycle();
    return str;
  }
  
  public final void zzb(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    zzb(25, localParcel);
  }
  
  public final void zzb(float paramFloat1, float paramFloat2)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat1);
    localParcel.writeFloat(paramFloat2);
    zzb(24, localParcel);
  }
  
  public final void zzb(IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    zzb(29, localParcel);
  }
  
  public final void zzb(String paramString)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeString(paramString);
    zzb(7, localParcel);
  }
  
  public final void zzb(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(14, localParcel);
  }
  
  public final LatLng zzc()
    throws RemoteException
  {
    Parcel localParcel = zza(4, a_());
    LatLng localLatLng = (LatLng)zzex.zza(localParcel, LatLng.CREATOR);
    localParcel.recycle();
    return localLatLng;
  }
  
  public final void zzc(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    zzb(27, localParcel);
  }
  
  public final void zzc(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(20, localParcel);
  }
  
  public final String zzd()
    throws RemoteException
  {
    Parcel localParcel = zza(6, a_());
    String str = localParcel.readString();
    localParcel.recycle();
    return str;
  }
  
  public final String zze()
    throws RemoteException
  {
    Parcel localParcel = zza(8, a_());
    String str = localParcel.readString();
    localParcel.recycle();
    return str;
  }
  
  public final boolean zzf()
    throws RemoteException
  {
    Parcel localParcel = zza(10, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final void zzg()
    throws RemoteException
  {
    zzb(11, a_());
  }
  
  public final void zzh()
    throws RemoteException
  {
    zzb(12, a_());
  }
  
  public final boolean zzi()
    throws RemoteException
  {
    Parcel localParcel = zza(13, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final boolean zzj()
    throws RemoteException
  {
    Parcel localParcel = zza(15, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final int zzk()
    throws RemoteException
  {
    Parcel localParcel = zza(17, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
  
  public final boolean zzl()
    throws RemoteException
  {
    Parcel localParcel = zza(21, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final float zzm()
    throws RemoteException
  {
    Parcel localParcel = zza(23, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final float zzn()
    throws RemoteException
  {
    Parcel localParcel = zza(26, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final float zzo()
    throws RemoteException
  {
    Parcel localParcel = zza(28, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final IObjectWrapper zzp()
    throws RemoteException
  {
    Parcel localParcel = zza(30, a_());
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
}

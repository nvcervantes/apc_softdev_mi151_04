package com.google.android.gms.maps.model.internal;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.PatternItem;
import java.util.ArrayList;
import java.util.List;

public final class zzf
  extends zzev
  implements zzd
{
  zzf(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.ICircleDelegate");
  }
  
  public final void zza()
    throws RemoteException
  {
    zzb(1, a_());
  }
  
  public final void zza(double paramDouble)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeDouble(paramDouble);
    zzb(5, localParcel);
  }
  
  public final void zza(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    zzb(7, localParcel);
  }
  
  public final void zza(int paramInt)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeInt(paramInt);
    zzb(9, localParcel);
  }
  
  public final void zza(IObjectWrapper paramIObjectWrapper)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper);
    zzb(23, localParcel);
  }
  
  public final void zza(LatLng paramLatLng)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramLatLng);
    zzb(3, localParcel);
  }
  
  public final void zza(List<PatternItem> paramList)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeTypedList(paramList);
    zzb(21, localParcel);
  }
  
  public final void zza(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(15, localParcel);
  }
  
  public final boolean zza(zzd paramZzd)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzd);
    paramZzd = zza(17, localParcel);
    boolean bool = zzex.zza(paramZzd);
    paramZzd.recycle();
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
    zzb(13, localParcel);
  }
  
  public final void zzb(int paramInt)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeInt(paramInt);
    zzb(11, localParcel);
  }
  
  public final void zzb(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(19, localParcel);
  }
  
  public final LatLng zzc()
    throws RemoteException
  {
    Parcel localParcel = zza(4, a_());
    LatLng localLatLng = (LatLng)zzex.zza(localParcel, LatLng.CREATOR);
    localParcel.recycle();
    return localLatLng;
  }
  
  public final double zzd()
    throws RemoteException
  {
    Parcel localParcel = zza(6, a_());
    double d = localParcel.readDouble();
    localParcel.recycle();
    return d;
  }
  
  public final float zze()
    throws RemoteException
  {
    Parcel localParcel = zza(8, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final int zzf()
    throws RemoteException
  {
    Parcel localParcel = zza(10, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
  
  public final int zzg()
    throws RemoteException
  {
    Parcel localParcel = zza(12, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
  
  public final float zzh()
    throws RemoteException
  {
    Parcel localParcel = zza(14, a_());
    float f = localParcel.readFloat();
    localParcel.recycle();
    return f;
  }
  
  public final boolean zzi()
    throws RemoteException
  {
    Parcel localParcel = zza(16, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final int zzj()
    throws RemoteException
  {
    Parcel localParcel = zza(18, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
  
  public final boolean zzk()
    throws RemoteException
  {
    Parcel localParcel = zza(20, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final List<PatternItem> zzl()
    throws RemoteException
  {
    Parcel localParcel = zza(22, a_());
    ArrayList localArrayList = localParcel.createTypedArrayList(PatternItem.CREATOR);
    localParcel.recycle();
    return localArrayList;
  }
  
  public final IObjectWrapper zzm()
    throws RemoteException
  {
    Parcel localParcel = zza(24, a_());
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
}

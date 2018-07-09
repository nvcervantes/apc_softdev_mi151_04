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

public final class zzu
  extends zzev
  implements zzs
{
  zzu(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IPolygonDelegate");
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
    zzb(27, localParcel);
  }
  
  public final void zza(List<LatLng> paramList)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeTypedList(paramList);
    zzb(3, localParcel);
  }
  
  public final void zza(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(15, localParcel);
  }
  
  public final boolean zza(zzs paramZzs)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramZzs);
    paramZzs = zza(19, localParcel);
    boolean bool = zzex.zza(paramZzs);
    paramZzs.recycle();
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
  
  public final void zzb(List paramList)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeList(paramList);
    zzb(5, localParcel);
  }
  
  public final void zzb(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(17, localParcel);
  }
  
  public final List<LatLng> zzc()
    throws RemoteException
  {
    Parcel localParcel = zza(4, a_());
    ArrayList localArrayList = localParcel.createTypedArrayList(LatLng.CREATOR);
    localParcel.recycle();
    return localArrayList;
  }
  
  public final void zzc(int paramInt)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeInt(paramInt);
    zzb(23, localParcel);
  }
  
  public final void zzc(List<PatternItem> paramList)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeTypedList(paramList);
    zzb(25, localParcel);
  }
  
  public final void zzc(boolean paramBoolean)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramBoolean);
    zzb(21, localParcel);
  }
  
  public final List zzd()
    throws RemoteException
  {
    Parcel localParcel = zza(6, a_());
    ArrayList localArrayList = zzex.zzb(localParcel);
    localParcel.recycle();
    return localArrayList;
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
  
  public final boolean zzj()
    throws RemoteException
  {
    Parcel localParcel = zza(18, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final int zzk()
    throws RemoteException
  {
    Parcel localParcel = zza(20, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
  
  public final boolean zzl()
    throws RemoteException
  {
    Parcel localParcel = zza(22, a_());
    boolean bool = zzex.zza(localParcel);
    localParcel.recycle();
    return bool;
  }
  
  public final int zzm()
    throws RemoteException
  {
    Parcel localParcel = zza(24, a_());
    int i = localParcel.readInt();
    localParcel.recycle();
    return i;
  }
  
  public final List<PatternItem> zzn()
    throws RemoteException
  {
    Parcel localParcel = zza(26, a_());
    ArrayList localArrayList = localParcel.createTypedArrayList(PatternItem.CREATOR);
    localParcel.recycle();
    return localArrayList;
  }
  
  public final IObjectWrapper zzo()
    throws RemoteException
  {
    Parcel localParcel = zza(28, a_());
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
}

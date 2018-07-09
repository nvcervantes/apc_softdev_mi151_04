package com.google.android.gms.maps.model.internal;

import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzc
  extends zzev
  implements zza
{
  zzc(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.maps.model.internal.IBitmapDescriptorFactoryDelegate");
  }
  
  public final IObjectWrapper zza()
    throws RemoteException
  {
    Parcel localParcel = zza(4, a_());
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
  
  public final IObjectWrapper zza(float paramFloat)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeFloat(paramFloat);
    localParcel = zza(5, localParcel);
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
  
  public final IObjectWrapper zza(int paramInt)
    throws RemoteException
  {
    Parcel localParcel = a_();
    localParcel.writeInt(paramInt);
    localParcel = zza(1, localParcel);
    IObjectWrapper localIObjectWrapper = IObjectWrapper.zza.zza(localParcel.readStrongBinder());
    localParcel.recycle();
    return localIObjectWrapper;
  }
  
  public final IObjectWrapper zza(Bitmap paramBitmap)
    throws RemoteException
  {
    Object localObject = a_();
    zzex.zza((Parcel)localObject, paramBitmap);
    paramBitmap = zza(6, (Parcel)localObject);
    localObject = IObjectWrapper.zza.zza(paramBitmap.readStrongBinder());
    paramBitmap.recycle();
    return localObject;
  }
  
  public final IObjectWrapper zza(String paramString)
    throws RemoteException
  {
    Object localObject = a_();
    ((Parcel)localObject).writeString(paramString);
    paramString = zza(2, (Parcel)localObject);
    localObject = IObjectWrapper.zza.zza(paramString.readStrongBinder());
    paramString.recycle();
    return localObject;
  }
  
  public final IObjectWrapper zzb(String paramString)
    throws RemoteException
  {
    Object localObject = a_();
    ((Parcel)localObject).writeString(paramString);
    paramString = zza(3, (Parcel)localObject);
    localObject = IObjectWrapper.zza.zza(paramString.readStrongBinder());
    paramString.recycle();
    return localObject;
  }
  
  public final IObjectWrapper zzc(String paramString)
    throws RemoteException
  {
    Object localObject = a_();
    ((Parcel)localObject).writeString(paramString);
    paramString = zza(7, (Parcel)localObject);
    localObject = IObjectWrapper.zza.zza(paramString.readStrongBinder());
    paramString.recycle();
    return localObject;
  }
}

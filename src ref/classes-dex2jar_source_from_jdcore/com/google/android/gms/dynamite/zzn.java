package com.google.android.gms.dynamite;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.IObjectWrapper.zza;
import com.google.android.gms.internal.zzev;
import com.google.android.gms.internal.zzex;

public final class zzn
  extends zzev
  implements zzm
{
  zzn(IBinder paramIBinder)
  {
    super(paramIBinder, "com.google.android.gms.dynamite.IDynamiteLoaderV2");
  }
  
  public final IObjectWrapper zza(IObjectWrapper paramIObjectWrapper1, String paramString, int paramInt, IObjectWrapper paramIObjectWrapper2)
    throws RemoteException
  {
    Parcel localParcel = a_();
    zzex.zza(localParcel, paramIObjectWrapper1);
    localParcel.writeString(paramString);
    localParcel.writeInt(paramInt);
    zzex.zza(localParcel, paramIObjectWrapper2);
    paramIObjectWrapper1 = zza(2, localParcel);
    paramString = IObjectWrapper.zza.zza(paramIObjectWrapper1.readStrongBinder());
    paramIObjectWrapper1.recycle();
    return paramString;
  }
}

package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.dynamic.IObjectWrapper.zza;

public abstract class zzccn
  extends zzew
  implements zzccm
{
  public zzccn()
  {
    attachInterface(this, "com.google.android.gms.flags.IFlagProvider");
  }
  
  public static zzccm asInterface(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.flags.IFlagProvider");
    if ((localIInterface instanceof zzccm)) {
      return (zzccm)localIInterface;
    }
    return new zzcco(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    switch (paramInt1)
    {
    default: 
      return false;
    case 5: 
      paramParcel1 = getStringFlagValue(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readInt());
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    case 4: 
      long l = getLongFlagValue(paramParcel1.readString(), paramParcel1.readLong(), paramParcel1.readInt());
      paramParcel2.writeNoException();
      paramParcel2.writeLong(l);
      return true;
    case 3: 
      paramInt1 = getIntFlagValue(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.readInt());
      paramParcel2.writeNoException();
      paramParcel2.writeInt(paramInt1);
      return true;
    case 2: 
      boolean bool = getBooleanFlagValue(paramParcel1.readString(), zzex.zza(paramParcel1), paramParcel1.readInt());
      paramParcel2.writeNoException();
      zzex.zza(paramParcel2, bool);
      return true;
    }
    init(IObjectWrapper.zza.zza(paramParcel1.readStrongBinder()));
    paramParcel2.writeNoException();
    return true;
  }
}

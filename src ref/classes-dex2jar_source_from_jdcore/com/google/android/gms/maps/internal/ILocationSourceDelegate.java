package com.google.android.gms.maps.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzew;

@Hide
public abstract interface ILocationSourceDelegate
  extends IInterface
{
  public abstract void activate(zzah paramZzah)
    throws RemoteException;
  
  public abstract void deactivate()
    throws RemoteException;
  
  public static abstract class zza
    extends zzew
    implements ILocationSourceDelegate
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.maps.internal.ILocationSourceDelegate");
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
      case 2: 
        deactivate();
        break;
      case 1: 
        paramParcel1 = paramParcel1.readStrongBinder();
        if (paramParcel1 == null)
        {
          paramParcel1 = null;
        }
        else
        {
          IInterface localIInterface = paramParcel1.queryLocalInterface("com.google.android.gms.maps.internal.IOnLocationChangeListener");
          if ((localIInterface instanceof zzah)) {
            paramParcel1 = (zzah)localIInterface;
          } else {
            paramParcel1 = new zzai(paramParcel1);
          }
        }
        activate(paramParcel1);
      }
      paramParcel2.writeNoException();
      return true;
    }
  }
}

package com.google.android.gms.dynamic;

import android.content.Intent;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzl
  extends zzew
  implements zzk
{
  public zzl()
  {
    attachInterface(this, "com.google.android.gms.dynamic.IFragmentWrapper");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    if (zza(paramInt1, paramParcel1, paramParcel2, paramInt2)) {
      return true;
    }
    Object localObject = null;
    IInterface localIInterface = null;
    boolean bool;
    switch (paramInt1)
    {
    default: 
      return false;
    case 27: 
      paramParcel1 = paramParcel1.readStrongBinder();
      if (paramParcel1 == null)
      {
        paramParcel1 = localIInterface;
      }
      else
      {
        localIInterface = paramParcel1.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
        if ((localIInterface instanceof IObjectWrapper)) {
          paramParcel1 = (IObjectWrapper)localIInterface;
        } else {
          paramParcel1 = new zzm(paramParcel1);
        }
      }
      zzb(paramParcel1);
      break;
    case 26: 
      zza((Intent)zzex.zza(paramParcel1, Intent.CREATOR), paramParcel1.readInt());
      break;
    case 25: 
      zza((Intent)zzex.zza(paramParcel1, Intent.CREATOR));
      break;
    case 24: 
      zzd(zzex.zza(paramParcel1));
      break;
    case 23: 
      zzc(zzex.zza(paramParcel1));
      break;
    case 22: 
      zzb(zzex.zza(paramParcel1));
      break;
    case 21: 
      zza(zzex.zza(paramParcel1));
    case 20: 
      for (;;)
      {
        paramParcel2.writeNoException();
        return true;
        paramParcel1 = paramParcel1.readStrongBinder();
        if (paramParcel1 == null)
        {
          paramParcel1 = localObject;
        }
        else
        {
          localIInterface = paramParcel1.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
          if ((localIInterface instanceof IObjectWrapper)) {
            paramParcel1 = (IObjectWrapper)localIInterface;
          } else {
            paramParcel1 = new zzm(paramParcel1);
          }
        }
        zza(paramParcel1);
      }
    case 19: 
      bool = zzr();
      break;
    case 18: 
      bool = zzq();
      break;
    case 17: 
      bool = zzp();
      break;
    case 16: 
      bool = zzo();
      break;
    case 15: 
      bool = zzn();
      break;
    case 14: 
      bool = zzm();
      break;
    case 13: 
      bool = zzl();
      break;
    case 12: 
      paramParcel1 = zzk();
      break;
    case 11: 
      bool = zzj();
      break;
    case 10: 
      paramInt1 = zzi();
      break;
    case 9: 
      paramParcel1 = zzh();
      break;
    case 8: 
      paramParcel1 = zzg();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    case 7: 
      bool = zzf();
      paramParcel2.writeNoException();
      zzex.zza(paramParcel2, bool);
      return true;
    case 6: 
      paramParcel1 = zze();
      break;
    case 5: 
      paramParcel1 = zzd();
      break;
    case 4: 
      paramInt1 = zzc();
      paramParcel2.writeNoException();
      paramParcel2.writeInt(paramInt1);
      return true;
    case 3: 
      paramParcel1 = zzb();
      paramParcel2.writeNoException();
      zzex.zzb(paramParcel2, paramParcel1);
      return true;
    }
    paramParcel1 = zza();
    paramParcel2.writeNoException();
    zzex.zza(paramParcel2, paramParcel1);
    return true;
  }
}

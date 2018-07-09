package com.google.android.gms.location.places.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.internal.zzew;
import com.google.android.gms.internal.zzex;

public abstract class zzy
  extends zzew
  implements zzx
{
  public zzy()
  {
    attachInterface(this, "com.google.android.gms.location.places.internal.IPlacesCallbacks");
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
      zzd((DataHolder)zzex.zza(paramParcel1, DataHolder.CREATOR));
      return true;
    case 4: 
      zza((Status)zzex.zza(paramParcel1, Status.CREATOR));
      return true;
    case 3: 
      zzc((DataHolder)zzex.zza(paramParcel1, DataHolder.CREATOR));
      return true;
    case 2: 
      zzb((DataHolder)zzex.zza(paramParcel1, DataHolder.CREATOR));
      return true;
    }
    zza((DataHolder)zzex.zza(paramParcel1, DataHolder.CREATOR));
    return true;
  }
}

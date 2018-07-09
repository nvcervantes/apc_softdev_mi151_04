package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.RemoteException;
import com.google.android.gms.clearcut.zzc;
import com.google.android.gms.clearcut.zze;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.DataHolder;

public abstract class zzbfq
  extends zzew
  implements zzbfp
{
  public zzbfq()
  {
    attachInterface(this, "com.google.android.gms.clearcut.internal.IClearcutLoggerCallbacks");
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
    case 9: 
      zzb((Status)zzex.zza(paramParcel1, Status.CREATOR), (zzc)zzex.zza(paramParcel1, zzc.CREATOR));
      return true;
    case 8: 
      zza((Status)zzex.zza(paramParcel1, Status.CREATOR), (zzc)zzex.zza(paramParcel1, zzc.CREATOR));
      return true;
    case 7: 
      zza((DataHolder)zzex.zza(paramParcel1, DataHolder.CREATOR));
      return true;
    case 6: 
      zza((Status)zzex.zza(paramParcel1, Status.CREATOR), (zze[])paramParcel1.createTypedArray(zze.CREATOR));
      return true;
    case 5: 
      zzb((Status)zzex.zza(paramParcel1, Status.CREATOR), paramParcel1.readLong());
      return true;
    case 4: 
      zzc((Status)zzex.zza(paramParcel1, Status.CREATOR));
      return true;
    case 3: 
      zza((Status)zzex.zza(paramParcel1, Status.CREATOR), paramParcel1.readLong());
      return true;
    case 2: 
      zzb((Status)zzex.zza(paramParcel1, Status.CREATOR));
      return true;
    }
    zza((Status)zzex.zza(paramParcel1, Status.CREATOR));
    return true;
  }
}

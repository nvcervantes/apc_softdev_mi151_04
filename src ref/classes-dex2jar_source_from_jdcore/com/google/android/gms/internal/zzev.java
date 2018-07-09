package com.google.android.gms.internal;

import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public class zzev
  implements IInterface
{
  private final IBinder zza;
  private final String zzb;
  
  protected zzev(IBinder paramIBinder, String paramString)
  {
    zza = paramIBinder;
    zzb = paramString;
  }
  
  protected final Parcel a_()
  {
    Parcel localParcel = Parcel.obtain();
    localParcel.writeInterfaceToken(zzb);
    return localParcel;
  }
  
  public IBinder asBinder()
  {
    return zza;
  }
  
  /* Error */
  protected final Parcel zza(int paramInt, Parcel paramParcel)
    throws RemoteException
  {
    // Byte code:
    //   0: invokestatic 27	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   3: astore_3
    //   4: aload_0
    //   5: getfield 17	com/google/android/gms/internal/zzev:zza	Landroid/os/IBinder;
    //   8: iload_1
    //   9: aload_2
    //   10: aload_3
    //   11: iconst_0
    //   12: invokeinterface 44 5 0
    //   17: pop
    //   18: aload_3
    //   19: invokevirtual 47	android/os/Parcel:readException	()V
    //   22: aload_2
    //   23: invokevirtual 50	android/os/Parcel:recycle	()V
    //   26: aload_3
    //   27: areturn
    //   28: astore_3
    //   29: goto +12 -> 41
    //   32: astore 4
    //   34: aload_3
    //   35: invokevirtual 50	android/os/Parcel:recycle	()V
    //   38: aload 4
    //   40: athrow
    //   41: aload_2
    //   42: invokevirtual 50	android/os/Parcel:recycle	()V
    //   45: aload_3
    //   46: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	47	0	this	zzev
    //   0	47	1	paramInt	int
    //   0	47	2	paramParcel	Parcel
    //   3	24	3	localParcel	Parcel
    //   28	18	3	localObject	Object
    //   32	7	4	localRuntimeException	RuntimeException
    // Exception table:
    //   from	to	target	type
    //   4	22	28	finally
    //   34	41	28	finally
    //   4	22	32	java/lang/RuntimeException
  }
  
  protected final void zzb(int paramInt, Parcel paramParcel)
    throws RemoteException
  {
    Parcel localParcel = Parcel.obtain();
    try
    {
      zza.transact(paramInt, paramParcel, localParcel, 0);
      localParcel.readException();
      return;
    }
    finally
    {
      paramParcel.recycle();
      localParcel.recycle();
    }
  }
  
  protected final void zzc(int paramInt, Parcel paramParcel)
    throws RemoteException
  {
    try
    {
      zza.transact(paramInt, paramParcel, null, 1);
      return;
    }
    finally
    {
      paramParcel.recycle();
    }
  }
}

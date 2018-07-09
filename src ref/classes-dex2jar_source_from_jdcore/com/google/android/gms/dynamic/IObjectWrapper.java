package com.google.android.gms.dynamic;

import android.os.IBinder;
import android.os.IInterface;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzew;

@Hide
public abstract interface IObjectWrapper
  extends IInterface
{
  public static class zza
    extends zzew
    implements IObjectWrapper
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.dynamic.IObjectWrapper");
    }
    
    public static IObjectWrapper zza(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.dynamic.IObjectWrapper");
      if ((localIInterface instanceof IObjectWrapper)) {
        return (IObjectWrapper)localIInterface;
      }
      return new zzm(paramIBinder);
    }
  }
}

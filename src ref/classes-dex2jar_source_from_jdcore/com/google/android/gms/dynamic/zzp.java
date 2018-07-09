package com.google.android.gms.dynamic;

import android.content.Context;
import android.os.IBinder;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.zzs;

public abstract class zzp<T>
{
  private final String zza;
  private T zzb;
  
  protected zzp(String paramString)
  {
    zza = paramString;
  }
  
  protected abstract T zza(IBinder paramIBinder);
  
  protected final T zzb(Context paramContext)
    throws zzq
  {
    if (zzb == null)
    {
      zzbq.zza(paramContext);
      paramContext = zzs.getRemoteContext(paramContext);
      if (paramContext == null) {
        throw new zzq("Could not get remote context.");
      }
      paramContext = paramContext.getClassLoader();
      try
      {
        zzb = zza((IBinder)paramContext.loadClass(zza).newInstance());
      }
      catch (IllegalAccessException paramContext)
      {
        throw new zzq("Could not access creator.", paramContext);
      }
      catch (InstantiationException paramContext)
      {
        throw new zzq("Could not instantiate creator.", paramContext);
      }
      catch (ClassNotFoundException paramContext)
      {
        throw new zzq("Could not load creator class.", paramContext);
      }
    }
    return zzb;
  }
}

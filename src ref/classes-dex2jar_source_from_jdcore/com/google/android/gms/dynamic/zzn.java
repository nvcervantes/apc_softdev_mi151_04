package com.google.android.gms.dynamic;

import android.os.IBinder;
import com.google.android.gms.common.internal.Hide;
import java.lang.reflect.Field;

@Hide
public final class zzn<T>
  extends IObjectWrapper.zza
{
  private final T zza;
  
  private zzn(T paramT)
  {
    zza = paramT;
  }
  
  public static <T> IObjectWrapper zza(T paramT)
  {
    return new zzn(paramT);
  }
  
  public static <T> T zza(IObjectWrapper paramIObjectWrapper)
  {
    if ((paramIObjectWrapper instanceof zzn)) {
      return zza;
    }
    IBinder localIBinder = paramIObjectWrapper.asBinder();
    Field[] arrayOfField = localIBinder.getClass().getDeclaredFields();
    int i = 0;
    int m = arrayOfField.length;
    paramIObjectWrapper = null;
    int k;
    for (int j = 0; i < m; j = k)
    {
      Field localField = arrayOfField[i];
      k = j;
      if (!localField.isSynthetic())
      {
        k = j + 1;
        paramIObjectWrapper = localField;
      }
      i += 1;
    }
    if (j == 1)
    {
      if (!paramIObjectWrapper.isAccessible())
      {
        paramIObjectWrapper.setAccessible(true);
        try
        {
          paramIObjectWrapper = paramIObjectWrapper.get(localIBinder);
          return paramIObjectWrapper;
        }
        catch (IllegalAccessException paramIObjectWrapper)
        {
          throw new IllegalArgumentException("Could not access the field in remoteBinder.", paramIObjectWrapper);
        }
        catch (NullPointerException paramIObjectWrapper)
        {
          throw new IllegalArgumentException("Binder object is null.", paramIObjectWrapper);
        }
      }
      throw new IllegalArgumentException("IObjectWrapper declared field not private!");
    }
    i = arrayOfField.length;
    paramIObjectWrapper = new StringBuilder(64);
    paramIObjectWrapper.append("Unexpected number of IObjectWrapper declared fields: ");
    paramIObjectWrapper.append(i);
    throw new IllegalArgumentException(paramIObjectWrapper.toString());
  }
}

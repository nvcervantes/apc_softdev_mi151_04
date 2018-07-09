package com.google.android.gms.maps.internal;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.IBinder;
import android.os.IInterface;
import android.os.RemoteException;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.zzn;
import com.google.android.gms.dynamite.DynamiteModule;
import com.google.android.gms.maps.model.RuntimeRemoteException;

@Hide
public class zzbz
{
  private static final String zza = "zzbz";
  @SuppressLint({"StaticFieldLeak"})
  @Nullable
  private static Context zzb;
  private static zze zzc;
  
  public zzbz() {}
  
  public static zze zza(Context paramContext)
    throws GooglePlayServicesNotAvailableException
  {
    zzbq.zza(paramContext);
    if (zzc != null) {
      return zzc;
    }
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext);
    if (i != 0) {
      throw new GooglePlayServicesNotAvailableException(i);
    }
    Log.i(zza, "Making Creator dynamically");
    Object localObject = (IBinder)zza(zzb(paramContext).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl");
    if (localObject == null)
    {
      localObject = null;
    }
    else
    {
      IInterface localIInterface = ((IBinder)localObject).queryLocalInterface("com.google.android.gms.maps.internal.ICreator");
      if ((localIInterface instanceof zze)) {
        localObject = (zze)localIInterface;
      } else {
        localObject = new zzf((IBinder)localObject);
      }
    }
    zzc = (zze)localObject;
    try
    {
      zzc.zza(zzn.zza(zzb(paramContext).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
      return zzc;
    }
    catch (RemoteException paramContext)
    {
      throw new RuntimeRemoteException(paramContext);
    }
  }
  
  private static <T> T zza(Class<?> paramClass)
  {
    try
    {
      Object localObject = paramClass.newInstance();
      return localObject;
    }
    catch (InstantiationException localInstantiationException)
    {
      for (;;) {}
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      for (;;) {}
    }
    paramClass = String.valueOf(paramClass.getName());
    if (paramClass.length() != 0) {
      paramClass = "Unable to call the default constructor of ".concat(paramClass);
    } else {
      paramClass = new String("Unable to call the default constructor of ");
    }
    throw new IllegalStateException(paramClass);
    paramClass = String.valueOf(paramClass.getName());
    if (paramClass.length() != 0) {
      paramClass = "Unable to instantiate the dynamic class ".concat(paramClass);
    } else {
      paramClass = new String("Unable to instantiate the dynamic class ");
    }
    throw new IllegalStateException(paramClass);
  }
  
  private static <T> T zza(ClassLoader paramClassLoader, String paramString)
  {
    try
    {
      paramClassLoader = zza(((ClassLoader)zzbq.zza(paramClassLoader)).loadClass(paramString));
      return paramClassLoader;
    }
    catch (ClassNotFoundException paramClassLoader)
    {
      for (;;) {}
    }
    paramClassLoader = String.valueOf(paramString);
    if (paramClassLoader.length() != 0) {
      paramClassLoader = "Unable to find dynamic class ".concat(paramClassLoader);
    } else {
      paramClassLoader = new String("Unable to find dynamic class ");
    }
    throw new IllegalStateException(paramClassLoader);
  }
  
  @Nullable
  private static Context zzb(Context paramContext)
  {
    if (zzb != null) {
      return zzb;
    }
    paramContext = zzc(paramContext);
    zzb = paramContext;
    return paramContext;
  }
  
  @Nullable
  private static Context zzc(Context paramContext)
  {
    try
    {
      Context localContext = DynamiteModule.zza(paramContext, DynamiteModule.zza, "com.google.android.gms.maps_dynamite").zza();
      return localContext;
    }
    catch (Throwable localThrowable)
    {
      Log.e(zza, "Failed to load maps module, use legacy", localThrowable);
    }
    return GooglePlayServicesUtil.getRemoteContext(paramContext);
  }
}

package com.google.android.gms.common;

import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzat;
import com.google.android.gms.common.internal.zzau;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.dynamic.zzn;
import java.io.UnsupportedEncodingException;
import java.util.Arrays;

@Hide
abstract class zzh
  extends zzau
{
  private int zza;
  
  protected zzh(byte[] paramArrayOfByte)
  {
    boolean bool;
    if (paramArrayOfByte.length == 25) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zzb(bool);
    zza = Arrays.hashCode(paramArrayOfByte);
  }
  
  protected static byte[] zza(String paramString)
  {
    try
    {
      paramString = paramString.getBytes("ISO-8859-1");
      return paramString;
    }
    catch (UnsupportedEncodingException paramString)
    {
      throw new AssertionError(paramString);
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject != null)
    {
      if (!(paramObject instanceof zzat)) {
        return false;
      }
      try
      {
        paramObject = (zzat)paramObject;
        if (paramObject.zzc() != hashCode()) {
          return false;
        }
        paramObject = paramObject.zzb();
        if (paramObject == null) {
          return false;
        }
        paramObject = (byte[])zzn.zza(paramObject);
        boolean bool = Arrays.equals(zza(), paramObject);
        return bool;
      }
      catch (RemoteException paramObject)
      {
        Log.e("GoogleCertificates", "Failed to get Google certificates from remote", paramObject);
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    return zza;
  }
  
  abstract byte[] zza();
  
  public final IObjectWrapper zzb()
  {
    return zzn.zza(zza());
  }
  
  public final int zzc()
  {
    return hashCode();
  }
}

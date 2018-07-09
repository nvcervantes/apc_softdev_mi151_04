package com.google.android.gms.location.places.internal;

import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzc;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgp;
import com.google.android.gms.internal.zzbgq;
import com.google.android.gms.internal.zzdoh;
import com.google.android.gms.internal.zzflr;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Hide
public class zzaw
  extends zzc
{
  public zzaw(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  private final byte[] zza(String paramString, byte[] paramArrayOfByte)
  {
    if ((zza(paramString)) && (!zzi(paramString))) {
      return zzg(paramString);
    }
    return null;
  }
  
  protected final float zza(String paramString, float paramFloat)
  {
    if ((zza(paramString)) && (!zzi(paramString))) {
      return zzf(paramString);
    }
    return paramFloat;
  }
  
  protected final int zza(String paramString, int paramInt)
  {
    if ((zza(paramString)) && (!zzi(paramString))) {
      return zzc(paramString);
    }
    return paramInt;
  }
  
  protected final <E extends zzbgp> E zza(String paramString, Parcelable.Creator<E> paramCreator)
  {
    paramString = zza(paramString, null);
    if (paramString == null) {
      return null;
    }
    return zzbgq.zza(paramString, paramCreator);
  }
  
  protected final String zza(String paramString1, String paramString2)
  {
    if ((zza(paramString1)) && (!zzi(paramString1))) {
      return zze(paramString1);
    }
    return paramString2;
  }
  
  protected final <E extends zzbgp> List<E> zza(String paramString, Parcelable.Creator<E> paramCreator, List<E> paramList)
  {
    paramString = zza(paramString, null);
    if (paramString == null) {
      return paramList;
    }
    try
    {
      Object localObject = zzdoh.zza(paramString);
      if (zzc == null) {
        return paramList;
      }
      paramString = new ArrayList(zzc.length);
      localObject = zzc;
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        paramString.add(zzbgq.zza(localObject[i], paramCreator));
        i += 1;
      }
      return paramString;
    }
    catch (zzflr paramString)
    {
      if (Log.isLoggable("SafeDataBufferRef", 6)) {
        Log.e("SafeDataBufferRef", "Cannot parse byte[]", paramString);
      }
    }
    return paramList;
  }
  
  protected final List<Integer> zza(String paramString, List<Integer> paramList)
  {
    paramString = zza(paramString, null);
    if (paramString == null) {
      return paramList;
    }
    try
    {
      paramString = zzdoh.zza(paramString);
      if (zzb == null) {
        return paramList;
      }
      ArrayList localArrayList = new ArrayList(zzb.length);
      int i = 0;
      while (i < zzb.length)
      {
        localArrayList.add(Integer.valueOf(zzb[i]));
        i += 1;
      }
      return localArrayList;
    }
    catch (zzflr paramString)
    {
      if (Log.isLoggable("SafeDataBufferRef", 6)) {
        Log.e("SafeDataBufferRef", "Cannot parse byte[]", paramString);
      }
    }
    return paramList;
  }
  
  protected final List<String> zzb(String paramString, List<String> paramList)
  {
    paramString = zza(paramString, null);
    if (paramString == null) {
      return paramList;
    }
    try
    {
      paramString = zzdoh.zza(paramString);
      if (zza == null) {
        return paramList;
      }
      paramString = Arrays.asList(zza);
      return paramString;
    }
    catch (zzflr paramString)
    {
      if (Log.isLoggable("SafeDataBufferRef", 6)) {
        Log.e("SafeDataBufferRef", "Cannot parse byte[]", paramString);
      }
    }
    return paramList;
  }
}

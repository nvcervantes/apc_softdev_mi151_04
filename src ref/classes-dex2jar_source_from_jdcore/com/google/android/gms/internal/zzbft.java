package com.google.android.gms.internal;

import android.content.Context;
import android.util.Log;
import com.google.android.gms.clearcut.ClearcutLogger.zza;
import com.google.android.gms.phenotype.Phenotype;
import com.google.android.gms.phenotype.PhenotypeFlag;
import com.google.android.gms.phenotype.PhenotypeFlag.Factory;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

public final class zzbft
  implements ClearcutLogger.zza
{
  private static final Charset zza = Charset.forName("UTF-8");
  private static final PhenotypeFlag.Factory zzb = new PhenotypeFlag.Factory(Phenotype.getContentProviderUri("com.google.android.gms.clearcut.public")).withGservicePrefix("gms:playlog:service:sampling_").withPhenotypePrefix("LogSampling__");
  private static Map<String, PhenotypeFlag<String>> zzd;
  private static Boolean zze;
  private static Long zzf;
  private final Context zzc;
  
  public zzbft(Context paramContext)
  {
    zzc = paramContext;
    if (zzd == null) {
      zzd = new HashMap();
    }
    if (zzc != null) {
      PhenotypeFlag.maybeInit(zzc);
    }
  }
  
  private static zzbfu zza(String paramString)
  {
    if (paramString == null) {
      return null;
    }
    String str = "";
    int j = paramString.indexOf(',');
    int i = 0;
    if (j >= 0)
    {
      str = paramString.substring(0, j);
      i = j + 1;
    }
    j = paramString.indexOf('/', i);
    if (j <= 0)
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {
        paramString = "Failed to parse the rule: ".concat(paramString);
      } else {
        paramString = new String("Failed to parse the rule: ");
      }
      Log.e("LogSamplerImpl", paramString);
      return null;
    }
    try
    {
      long l1 = Long.parseLong(paramString.substring(i, j));
      long l2 = Long.parseLong(paramString.substring(j + 1));
      if ((l1 >= 0L) && (l2 >= 0L)) {
        return new zzbfu(str, l1, l2);
      }
      paramString = new StringBuilder(72);
      paramString.append("negative values not supported: ");
      paramString.append(l1);
      paramString.append("/");
      paramString.append(l2);
      Log.e("LogSamplerImpl", paramString.toString());
      return null;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      paramString = String.valueOf(paramString);
      if (paramString.length() != 0) {
        paramString = "parseLong() failed while parsing: ".concat(paramString);
      } else {
        paramString = new String("parseLong() failed while parsing: ");
      }
      Log.e("LogSamplerImpl", paramString, localNumberFormatException);
    }
    return null;
  }
  
  private static boolean zza(Context paramContext)
  {
    if (zze == null)
    {
      boolean bool;
      if (zzbih.zza(paramContext).zza("com.google.android.providers.gsf.permission.READ_GSERVICES") == 0) {
        bool = true;
      } else {
        bool = false;
      }
      zze = Boolean.valueOf(bool);
    }
    return zze.booleanValue();
  }
  
  public final boolean zza(String paramString, int paramInt)
  {
    Object localObject2 = null;
    if ((paramString == null) || (paramString.isEmpty())) {
      if (paramInt >= 0) {
        paramString = String.valueOf(paramInt);
      } else {
        paramString = null;
      }
    }
    if (paramString == null) {
      return true;
    }
    Object localObject1 = localObject2;
    if (zzc != null) {
      if (!zza(zzc))
      {
        localObject1 = localObject2;
      }
      else
      {
        localObject2 = (PhenotypeFlag)zzd.get(paramString);
        localObject1 = localObject2;
        if (localObject2 == null)
        {
          localObject1 = zzb.createFlag(paramString, null);
          zzd.put(paramString, localObject1);
        }
        localObject1 = (String)((PhenotypeFlag)localObject1).get();
      }
    }
    localObject1 = zza((String)localObject1);
    if (localObject1 == null) {
      return true;
    }
    localObject2 = zza;
    paramString = zzc;
    if (zzf == null)
    {
      if (paramString != null)
      {
        if (zza(paramString)) {}
        for (paramString = Long.valueOf(zzdnm.zza(paramString.getContentResolver(), "android_id", 0L));; paramString = Long.valueOf(0L))
        {
          zzf = paramString;
          break;
        }
      }
      l1 = 0L;
    }
    else
    {
      l1 = zzf.longValue();
    }
    if ((localObject2 != null) && (!((String)localObject2).isEmpty()))
    {
      paramString = ((String)localObject2).getBytes(zza);
      localObject2 = ByteBuffer.allocate(paramString.length + 8);
      ((ByteBuffer)localObject2).put(paramString);
      ((ByteBuffer)localObject2).putLong(l1);
      paramString = ((ByteBuffer)localObject2).array();
    }
    else
    {
      paramString = ByteBuffer.allocate(8).putLong(l1).array();
    }
    long l1 = zzbfo.zza(paramString);
    long l2 = zzb;
    long l3 = zzc;
    if ((l2 >= 0L) && (l3 >= 0L))
    {
      if (l3 > 0L)
      {
        if (l1 < 0L) {
          for (;;)
          {
            l1 = Long.MAX_VALUE % l3 + 1L + (l1 & 0x7FFFFFFFFFFFFFFF) % l3;
          }
        }
        if (l1 % l3 < l2) {
          return true;
        }
      }
      return false;
    }
    paramString = new StringBuilder(72);
    paramString.append("negative values not supported: ");
    paramString.append(l2);
    paramString.append("/");
    paramString.append(l3);
    throw new IllegalArgumentException(paramString.toString());
  }
}

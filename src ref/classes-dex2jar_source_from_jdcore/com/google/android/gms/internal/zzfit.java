package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfit<K, V>
{
  private final zzfiv<K, V> zza;
  private final K zzb;
  private final V zzc;
  
  private zzfit(zzfky paramZzfky1, K paramK, zzfky paramZzfky2, V paramV)
  {
    zza = new zzfiv(paramZzfky1, paramK, paramZzfky2, paramV);
    zzb = paramK;
    zzc = paramV;
  }
  
  static <K, V> int zza(zzfiv<K, V> paramZzfiv, K paramK, V paramV)
  {
    return zzfhq.zza(zza, 1, paramK) + zzfhq.zza(zzc, 2, paramV);
  }
  
  public static <K, V> zzfit<K, V> zza(zzfky paramZzfky1, K paramK, zzfky paramZzfky2, V paramV)
  {
    return new zzfit(paramZzfky1, paramK, paramZzfky2, paramV);
  }
  
  private static <T> T zza(zzfhb paramZzfhb, zzfhm paramZzfhm, zzfky paramZzfky, T paramT)
    throws IOException
  {
    switch (zzfiu.zza[paramZzfky.ordinal()])
    {
    default: 
      return zzfhq.zza(paramZzfhb, paramZzfky, true);
    case 3: 
      throw new RuntimeException("Groups are not allowed in maps.");
    case 2: 
      return Integer.valueOf(paramZzfhb.zzn());
    }
    paramZzfky = ((zzfjc)paramT).zzv();
    paramZzfhb.zza(paramZzfky, paramZzfhm);
    return paramZzfky.zze();
  }
  
  static <K, V> void zza(zzfhg paramZzfhg, zzfiv<K, V> paramZzfiv, K paramK, V paramV)
    throws IOException
  {
    zzfhq.zza(paramZzfhg, zza, 1, paramK);
    zzfhq.zza(paramZzfhg, zzc, 2, paramV);
  }
  
  public final int zza(int paramInt, K paramK, V paramV)
  {
    return zzfhg.zzf(paramInt) + zzfhg.zzm(zza(zza, paramK, paramV));
  }
  
  public final void zza(zzfhg paramZzfhg, int paramInt, K paramK, V paramV)
    throws IOException
  {
    paramZzfhg.zza(paramInt, 2);
    paramZzfhg.zzc(zza(zza, paramK, paramV));
    zza(paramZzfhg, zza, paramK, paramV);
  }
  
  public final void zza(zzfiw<K, V> paramZzfiw, zzfhb paramZzfhb, zzfhm paramZzfhm)
    throws IOException
  {
    int i = paramZzfhb.zzd(paramZzfhb.zzs());
    Object localObject2 = zza.zzb;
    Object localObject1 = zza.zzd;
    int j;
    do
    {
      for (;;)
      {
        j = paramZzfhb.zza();
        if (j == 0) {
          break label122;
        }
        if (j == (zza.zza.zzb() | 0x8))
        {
          localObject2 = zza(paramZzfhb, paramZzfhm, zza.zza, localObject2);
        }
        else
        {
          if (j != (zza.zzc.zzb() | 0x10)) {
            break;
          }
          localObject1 = zza(paramZzfhb, paramZzfhm, zza.zzc, localObject1);
        }
      }
    } while (paramZzfhb.zzb(j));
    label122:
    paramZzfhb.zza(0);
    paramZzfhb.zze(i);
    paramZzfiw.put(localObject2, localObject1);
  }
}

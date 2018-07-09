package com.google.android.gms.internal;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

final class zzfke
  implements Iterator<Map.Entry<K, V>>
{
  private int zza = -1;
  private boolean zzb;
  private Iterator<Map.Entry<K, V>> zzc;
  
  private zzfke(zzfjy paramZzfjy) {}
  
  private final Iterator<Map.Entry<K, V>> zza()
  {
    if (zzc == null) {
      zzc = zzfjy.zzc(zzd).entrySet().iterator();
    }
    return zzc;
  }
  
  public final boolean hasNext()
  {
    if (zza + 1 >= zzfjy.zzb(zzd).size()) {
      return (!zzfjy.zzc(zzd).isEmpty()) && (zza().hasNext());
    }
    return true;
  }
  
  public final void remove()
  {
    if (!zzb) {
      throw new IllegalStateException("remove() was called before next()");
    }
    zzb = false;
    zzfjy.zza(zzd);
    if (zza < zzfjy.zzb(zzd).size())
    {
      zzfjy localZzfjy = zzd;
      int i = zza;
      zza = (i - 1);
      zzfjy.zza(localZzfjy, i);
      return;
    }
    zza().remove();
  }
}

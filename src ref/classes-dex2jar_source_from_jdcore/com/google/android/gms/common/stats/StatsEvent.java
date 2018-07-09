package com.google.android.gms.common.stats;

import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;

public abstract class StatsEvent
  extends zzbgl
  implements ReflectedParcelable
{
  public StatsEvent() {}
  
  public String toString()
  {
    long l1 = zza();
    int i = zzb();
    long l2 = zzc();
    String str = zzd();
    StringBuilder localStringBuilder = new StringBuilder(53 + String.valueOf(str).length());
    localStringBuilder.append(l1);
    localStringBuilder.append("\t");
    localStringBuilder.append(i);
    localStringBuilder.append("\t");
    localStringBuilder.append(l2);
    localStringBuilder.append(str);
    return localStringBuilder.toString();
  }
  
  public abstract long zza();
  
  public abstract int zzb();
  
  public abstract long zzc();
  
  public abstract String zzd();
}

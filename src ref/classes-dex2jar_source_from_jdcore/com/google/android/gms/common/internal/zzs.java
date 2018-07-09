package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.support.v4.util.ArraySet;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzcyk;
import java.util.Collection;

public final class zzs
{
  private Account zza;
  private ArraySet<Scope> zzb;
  private int zzc = 0;
  private String zzd;
  private String zze;
  private zzcyk zzf = zzcyk.zza;
  
  public zzs() {}
  
  public final zzr zza()
  {
    return new zzr(zza, zzb, null, 0, null, zzd, zze, zzf);
  }
  
  public final zzs zza(Account paramAccount)
  {
    zza = paramAccount;
    return this;
  }
  
  public final zzs zza(String paramString)
  {
    zzd = paramString;
    return this;
  }
  
  public final zzs zza(Collection<Scope> paramCollection)
  {
    if (zzb == null) {
      zzb = new ArraySet();
    }
    zzb.addAll(paramCollection);
    return this;
  }
  
  public final zzs zzb(String paramString)
  {
    zze = paramString;
    return this;
  }
}

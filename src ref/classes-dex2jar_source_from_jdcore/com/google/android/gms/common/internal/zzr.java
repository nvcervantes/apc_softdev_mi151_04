package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.content.Context;
import android.view.View;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.internal.zzcyk;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public final class zzr
{
  private final Account zza;
  private final Set<Scope> zzb;
  private final Set<Scope> zzc;
  private final Map<Api<?>, zzt> zzd;
  private final int zze;
  private final View zzf;
  private final String zzg;
  private final String zzh;
  private final zzcyk zzi;
  private Integer zzj;
  
  @Hide
  public zzr(Account paramAccount, Set<Scope> paramSet, Map<Api<?>, zzt> paramMap, int paramInt, View paramView, String paramString1, String paramString2, zzcyk paramZzcyk)
  {
    zza = paramAccount;
    if (paramSet == null) {
      paramAccount = Collections.EMPTY_SET;
    } else {
      paramAccount = Collections.unmodifiableSet(paramSet);
    }
    zzb = paramAccount;
    paramAccount = paramMap;
    if (paramMap == null) {
      paramAccount = Collections.EMPTY_MAP;
    }
    zzd = paramAccount;
    zzf = paramView;
    zze = paramInt;
    zzg = paramString1;
    zzh = paramString2;
    zzi = paramZzcyk;
    paramAccount = new HashSet(zzb);
    paramSet = zzd.values().iterator();
    while (paramSet.hasNext()) {
      paramAccount.addAll(nextzza);
    }
    zzc = Collections.unmodifiableSet(paramAccount);
  }
  
  public static zzr zza(Context paramContext)
  {
    return new GoogleApiClient.Builder(paramContext).zza();
  }
  
  @Deprecated
  public final String zza()
  {
    if (zza != null) {
      return zza.name;
    }
    return null;
  }
  
  public final Set<Scope> zza(Api<?> paramApi)
  {
    paramApi = (zzt)zzd.get(paramApi);
    if ((paramApi != null) && (!zza.isEmpty()))
    {
      HashSet localHashSet = new HashSet(zzb);
      localHashSet.addAll(zza);
      return localHashSet;
    }
    return zzb;
  }
  
  public final void zza(Integer paramInteger)
  {
    zzj = paramInteger;
  }
  
  public final Account zzb()
  {
    return zza;
  }
  
  public final Account zzc()
  {
    if (zza != null) {
      return zza;
    }
    return new Account("<<default account>>", "com.google");
  }
  
  public final int zzd()
  {
    return zze;
  }
  
  public final Set<Scope> zze()
  {
    return zzb;
  }
  
  public final Set<Scope> zzf()
  {
    return zzc;
  }
  
  public final Map<Api<?>, zzt> zzg()
  {
    return zzd;
  }
  
  public final String zzh()
  {
    return zzg;
  }
  
  public final String zzi()
  {
    return zzh;
  }
  
  public final View zzj()
  {
    return zzf;
  }
  
  public final zzcyk zzk()
  {
    return zzi;
  }
  
  public final Integer zzl()
  {
    return zzj;
  }
}

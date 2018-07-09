package com.google.android.gms.internal;

import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzf;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.Hide;

public final class zzcyg
{
  @Hide
  public static final Api.zza<zzcyt, zzcyk> zza;
  @Hide
  public static final Api<zzcyk> zzb = new Api("SignIn.API", zza, zzc);
  @Hide
  private static Api.zzf<zzcyt> zzc = new Api.zzf();
  @Hide
  private static Api.zzf<zzcyt> zzd = new Api.zzf();
  @Hide
  private static Api.zza<zzcyt, Object> zze;
  private static Scope zzf;
  private static Scope zzg;
  @Hide
  private static Api<Object> zzh = new Api("SignIn.INTERNAL_API", zze, zzd);
  
  static
  {
    zza = new zzcyh();
    zze = new zzcyi();
    zzf = new Scope("profile");
    zzg = new Scope("email");
  }
}

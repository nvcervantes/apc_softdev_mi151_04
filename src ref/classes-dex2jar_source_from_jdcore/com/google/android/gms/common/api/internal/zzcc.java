package com.google.android.gms.common.api.internal;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Result;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.concurrent.TimeUnit;

public abstract interface zzcc
{
  public abstract ConnectionResult zza(long paramLong, TimeUnit paramTimeUnit);
  
  @Nullable
  public abstract ConnectionResult zza(@NonNull Api<?> paramApi);
  
  public abstract <A extends Api.zzb, R extends Result, T extends zzm<R, A>> T zza(@NonNull T paramT);
  
  public abstract void zza();
  
  public abstract void zza(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  public abstract boolean zza(zzcu paramZzcu);
  
  public abstract ConnectionResult zzb();
  
  public abstract <A extends Api.zzb, T extends zzm<? extends Result, A>> T zzb(@NonNull T paramT);
  
  public abstract void zzc();
  
  public abstract boolean zzd();
  
  public abstract boolean zze();
  
  public abstract void zzf();
  
  public abstract void zzg();
}

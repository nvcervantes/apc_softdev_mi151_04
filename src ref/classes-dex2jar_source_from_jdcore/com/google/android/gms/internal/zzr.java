package com.google.android.gms.internal;

import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import java.util.Collections;
import java.util.Map;

public abstract class zzr<T>
  implements Comparable<zzr<T>>
{
  private final zzaf.zza zza;
  private final int zzb;
  private final String zzc;
  private final int zzd;
  private final Object zze;
  private zzy zzf;
  private Integer zzg;
  private zzv zzh;
  private boolean zzi;
  private boolean zzj;
  private boolean zzk;
  private boolean zzl;
  private zzab zzm;
  private zzc zzn;
  private zzt zzo;
  
  public zzr(int paramInt, String paramString, zzy paramZzy)
  {
    zzaf.zza localZza;
    if (zzaf.zza.zza) {
      localZza = new zzaf.zza();
    } else {
      localZza = null;
    }
    zza = localZza;
    zze = new Object();
    zzi = true;
    int i = 0;
    zzj = false;
    zzk = false;
    zzl = false;
    zzn = null;
    zzb = paramInt;
    zzc = paramString;
    zzf = paramZzy;
    zzm = new zzh();
    paramInt = i;
    if (!TextUtils.isEmpty(paramString))
    {
      paramString = Uri.parse(paramString);
      paramInt = i;
      if (paramString != null)
      {
        paramString = paramString.getHost();
        paramInt = i;
        if (paramString != null) {
          paramInt = paramString.hashCode();
        }
      }
    }
    zzd = paramInt;
  }
  
  public String toString()
  {
    String str1 = String.valueOf(Integer.toHexString(zzd));
    if (str1.length() != 0) {
      str1 = "0x".concat(str1);
    } else {
      str1 = new String("0x");
    }
    String str2 = zzc;
    String str3 = String.valueOf(zzu.zza);
    String str4 = String.valueOf(zzg);
    StringBuilder localStringBuilder = new StringBuilder(3 + String.valueOf("[ ] ").length() + String.valueOf(str2).length() + String.valueOf(str1).length() + String.valueOf(str3).length() + String.valueOf(str4).length());
    localStringBuilder.append("[ ] ");
    localStringBuilder.append(str2);
    localStringBuilder.append(" ");
    localStringBuilder.append(str1);
    localStringBuilder.append(" ");
    localStringBuilder.append(str3);
    localStringBuilder.append(" ");
    localStringBuilder.append(str4);
    return localStringBuilder.toString();
  }
  
  public final int zza()
  {
    return zzb;
  }
  
  public final zzr<?> zza(int paramInt)
  {
    zzg = Integer.valueOf(paramInt);
    return this;
  }
  
  public final zzr<?> zza(zzc paramZzc)
  {
    zzn = paramZzc;
    return this;
  }
  
  public final zzr<?> zza(zzv paramZzv)
  {
    zzh = paramZzv;
    return this;
  }
  
  protected abstract zzx<T> zza(zzp paramZzp);
  
  public final void zza(zzae paramZzae)
  {
    synchronized (zze)
    {
      zzy localZzy = zzf;
      if (localZzy != null) {
        localZzy.zza(paramZzae);
      }
      return;
    }
  }
  
  final void zza(zzt paramZzt)
  {
    synchronized (zze)
    {
      zzo = paramZzt;
      return;
    }
  }
  
  final void zza(zzx<?> paramZzx)
  {
    synchronized (zze)
    {
      zzt localZzt = zzo;
      if (localZzt != null) {
        localZzt.zza(this, paramZzx);
      }
      return;
    }
  }
  
  protected abstract void zza(T paramT);
  
  public final void zza(String paramString)
  {
    if (zzaf.zza.zza) {
      zza.zza(paramString, Thread.currentThread().getId());
    }
  }
  
  public final int zzb()
  {
    return zzd;
  }
  
  final void zzb(String paramString)
  {
    if (zzh != null) {
      zzh.zzb(this);
    }
    if (zzaf.zza.zza)
    {
      long l = Thread.currentThread().getId();
      if (Looper.myLooper() != Looper.getMainLooper())
      {
        new Handler(Looper.getMainLooper()).post(new zzs(this, paramString, l));
        return;
      }
      zza.zza(paramString, l);
      zza.zza(toString());
    }
  }
  
  public final String zzc()
  {
    return zzc;
  }
  
  public final zzc zzd()
  {
    return zzn;
  }
  
  public final boolean zze()
  {
    synchronized (zze)
    {
      return false;
    }
  }
  
  public Map<String, String> zzf()
    throws zza
  {
    return Collections.emptyMap();
  }
  
  public byte[] zzg()
    throws zza
  {
    return null;
  }
  
  public final boolean zzh()
  {
    return zzi;
  }
  
  public final int zzi()
  {
    return zzm.zza();
  }
  
  public final zzab zzj()
  {
    return zzm;
  }
  
  public final void zzk()
  {
    synchronized (zze)
    {
      zzk = true;
      return;
    }
  }
  
  public final boolean zzl()
  {
    synchronized (zze)
    {
      boolean bool = zzk;
      return bool;
    }
  }
  
  final void zzm()
  {
    synchronized (zze)
    {
      zzt localZzt = zzo;
      if (localZzt != null) {
        localZzt.zza(this);
      }
      return;
    }
  }
}

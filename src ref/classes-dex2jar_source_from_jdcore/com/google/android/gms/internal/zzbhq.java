package com.google.android.gms.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;
import java.util.Map;

public final class zzbhq<I, O>
  extends zzbgl
{
  public static final zzbht CREATOR = new zzbht();
  protected final int zza;
  protected final boolean zzb;
  protected final int zzc;
  protected final boolean zzd;
  protected final String zze;
  protected final int zzf;
  protected final Class<? extends zzbhp> zzg;
  private final int zzh;
  private String zzi;
  private zzbhv zzj;
  private zzbhr<I, O> zzk;
  
  zzbhq(int paramInt1, int paramInt2, boolean paramBoolean1, int paramInt3, boolean paramBoolean2, String paramString1, int paramInt4, String paramString2, zzbhj paramZzbhj)
  {
    zzh = paramInt1;
    zza = paramInt2;
    zzb = paramBoolean1;
    zzc = paramInt3;
    zzd = paramBoolean2;
    zze = paramString1;
    zzf = paramInt4;
    if (paramString2 == null)
    {
      zzg = null;
      zzi = null;
    }
    else
    {
      zzg = zzbia.class;
      zzi = paramString2;
    }
    if (paramZzbhj == null)
    {
      zzk = null;
      return;
    }
    zzk = paramZzbhj.zza();
  }
  
  private zzbhq(int paramInt1, boolean paramBoolean1, int paramInt2, boolean paramBoolean2, String paramString, int paramInt3, Class<? extends zzbhp> paramClass, zzbhr<I, O> paramZzbhr)
  {
    zzh = 1;
    zza = paramInt1;
    zzb = paramBoolean1;
    zzc = paramInt2;
    zzd = paramBoolean2;
    zze = paramString;
    zzf = paramInt3;
    zzg = paramClass;
    if (paramClass == null) {}
    for (paramString = null;; paramString = paramClass.getCanonicalName())
    {
      zzi = paramString;
      break;
    }
    zzk = paramZzbhr;
  }
  
  public static zzbhq<Integer, Integer> zza(String paramString, int paramInt)
  {
    return new zzbhq(0, false, 0, false, paramString, paramInt, null, null);
  }
  
  public static zzbhq zza(String paramString, int paramInt, zzbhr<?, ?> paramZzbhr, boolean paramBoolean)
  {
    return new zzbhq(7, false, 0, false, paramString, paramInt, null, paramZzbhr);
  }
  
  public static <T extends zzbhp> zzbhq<T, T> zza(String paramString, int paramInt, Class<T> paramClass)
  {
    return new zzbhq(11, false, 11, false, paramString, paramInt, paramClass, null);
  }
  
  public static zzbhq<Boolean, Boolean> zzb(String paramString, int paramInt)
  {
    return new zzbhq(6, false, 6, false, paramString, paramInt, null, null);
  }
  
  public static <T extends zzbhp> zzbhq<ArrayList<T>, ArrayList<T>> zzb(String paramString, int paramInt, Class<T> paramClass)
  {
    return new zzbhq(11, true, 11, true, paramString, paramInt, paramClass, null);
  }
  
  public static zzbhq<String, String> zzc(String paramString, int paramInt)
  {
    return new zzbhq(7, false, 7, false, paramString, paramInt, null, null);
  }
  
  public static zzbhq<ArrayList<String>, ArrayList<String>> zzd(String paramString, int paramInt)
  {
    return new zzbhq(7, true, 7, true, paramString, paramInt, null, null);
  }
  
  private String zzd()
  {
    if (zzi == null) {
      return null;
    }
    return zzi;
  }
  
  public static zzbhq<byte[], byte[]> zze(String paramString, int paramInt)
  {
    return new zzbhq(8, false, 8, false, paramString, 4, null, null);
  }
  
  public final String toString()
  {
    zzbi localZzbi = zzbg.zza(this).zza("versionCode", Integer.valueOf(zzh)).zza("typeIn", Integer.valueOf(zza)).zza("typeInArray", Boolean.valueOf(zzb)).zza("typeOut", Integer.valueOf(zzc)).zza("typeOutArray", Boolean.valueOf(zzd)).zza("outputFieldName", zze).zza("safeParcelFieldId", Integer.valueOf(zzf)).zza("concreteTypeName", zzd());
    Class localClass = zzg;
    if (localClass != null) {
      localZzbi.zza("concreteType.class", localClass.getCanonicalName());
    }
    if (zzk != null) {
      localZzbi.zza("converterName", zzk.getClass().getCanonicalName());
    }
    return localZzbi.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzh);
    zzbgo.zza(paramParcel, 2, zza);
    zzbgo.zza(paramParcel, 3, zzb);
    zzbgo.zza(paramParcel, 4, zzc);
    zzbgo.zza(paramParcel, 5, zzd);
    zzbgo.zza(paramParcel, 6, zze, false);
    zzbgo.zza(paramParcel, 7, zzf);
    zzbgo.zza(paramParcel, 8, zzd(), false);
    zzbhj localZzbhj;
    if (zzk == null) {
      localZzbhj = null;
    } else {
      localZzbhj = zzbhj.zza(zzk);
    }
    zzbgo.zza(paramParcel, 9, localZzbhj, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
  
  public final int zza()
  {
    return zzf;
  }
  
  public final I zza(O paramO)
  {
    return zzk.zza(paramO);
  }
  
  public final void zza(zzbhv paramZzbhv)
  {
    zzj = paramZzbhv;
  }
  
  public final boolean zzb()
  {
    return zzk != null;
  }
  
  public final Map<String, zzbhq<?, ?>> zzc()
  {
    zzbq.zza(zzi);
    zzbq.zza(zzj);
    return zzj.zza(zzi);
  }
}

package com.google.android.gms.common.stats;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.internal.zzbgo;
import java.util.List;

public final class WakeLockEvent
  extends StatsEvent
{
  public static final Parcelable.Creator<WakeLockEvent> CREATOR = new zzd();
  private int zza;
  private final long zzb;
  private int zzc;
  private final String zzd;
  private final String zze;
  private final String zzf;
  private final int zzg;
  private final List<String> zzh;
  private final String zzi;
  private final long zzj;
  private int zzk;
  private final String zzl;
  private final float zzm;
  private final long zzn;
  private long zzo;
  
  WakeLockEvent(int paramInt1, long paramLong1, int paramInt2, String paramString1, int paramInt3, List<String> paramList, String paramString2, long paramLong2, int paramInt4, String paramString3, String paramString4, float paramFloat, long paramLong3, String paramString5)
  {
    zza = paramInt1;
    zzb = paramLong1;
    zzc = paramInt2;
    zzd = paramString1;
    zze = paramString3;
    zzf = paramString5;
    zzg = paramInt3;
    zzo = -1L;
    zzh = paramList;
    zzi = paramString2;
    zzj = paramLong2;
    zzk = paramInt4;
    zzl = paramString4;
    zzm = paramFloat;
    zzn = paramLong3;
  }
  
  public WakeLockEvent(long paramLong1, int paramInt1, String paramString1, int paramInt2, List<String> paramList, String paramString2, long paramLong2, int paramInt3, String paramString3, String paramString4, float paramFloat, long paramLong3, String paramString5)
  {
    this(2, paramLong1, paramInt1, paramString1, paramInt2, paramList, paramString2, paramLong2, paramInt3, paramString3, paramString4, paramFloat, paramLong3, paramString5);
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zza());
    zzbgo.zza(paramParcel, 4, zzd, false);
    zzbgo.zza(paramParcel, 5, zzg);
    zzbgo.zzb(paramParcel, 6, zzh, false);
    zzbgo.zza(paramParcel, 8, zzj);
    zzbgo.zza(paramParcel, 10, zze, false);
    zzbgo.zza(paramParcel, 11, zzb());
    zzbgo.zza(paramParcel, 12, zzi, false);
    zzbgo.zza(paramParcel, 13, zzl, false);
    zzbgo.zza(paramParcel, 14, zzk);
    zzbgo.zza(paramParcel, 15, zzm);
    zzbgo.zza(paramParcel, 16, zzn);
    zzbgo.zza(paramParcel, 17, zzf, false);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  public final long zza()
  {
    return zzb;
  }
  
  public final int zzb()
  {
    return zzc;
  }
  
  public final long zzc()
  {
    return zzo;
  }
  
  public final String zzd()
  {
    String str5 = zzd;
    int i = zzg;
    String str1;
    if (zzh == null) {
      str1 = "";
    } else {
      str1 = TextUtils.join(",", zzh);
    }
    int j = zzk;
    String str2;
    if (zze == null) {
      str2 = "";
    } else {
      str2 = zze;
    }
    String str3;
    if (zzl == null) {
      str3 = "";
    } else {
      str3 = zzl;
    }
    float f = zzm;
    String str4;
    if (zzf == null) {
      str4 = "";
    } else {
      str4 = zzf;
    }
    StringBuilder localStringBuilder = new StringBuilder(45 + String.valueOf(str5).length() + String.valueOf(str1).length() + String.valueOf(str2).length() + String.valueOf(str3).length() + String.valueOf(str4).length());
    localStringBuilder.append("\t");
    localStringBuilder.append(str5);
    localStringBuilder.append("\t");
    localStringBuilder.append(i);
    localStringBuilder.append("\t");
    localStringBuilder.append(str1);
    localStringBuilder.append("\t");
    localStringBuilder.append(j);
    localStringBuilder.append("\t");
    localStringBuilder.append(str2);
    localStringBuilder.append("\t");
    localStringBuilder.append(str3);
    localStringBuilder.append("\t");
    localStringBuilder.append(f);
    localStringBuilder.append("\t");
    localStringBuilder.append(str4);
    return localStringBuilder.toString();
  }
}

package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import java.util.Arrays;

@Hide
public final class zzbfv
  extends zzbgl
{
  public static final Parcelable.Creator<zzbfv> CREATOR = new zzbfw();
  public final int zza;
  public final String zzb;
  private String zzc;
  private int zzd;
  private String zze;
  private String zzf;
  private boolean zzg;
  private boolean zzh;
  private int zzi;
  
  public zzbfv(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, String paramString4, boolean paramBoolean, int paramInt3)
  {
    zzc = ((String)zzbq.zza(paramString1));
    zzd = paramInt1;
    zza = paramInt2;
    zzb = paramString2;
    zze = paramString3;
    zzf = paramString4;
    zzg = (paramBoolean ^ true);
    zzh = paramBoolean;
    zzi = paramInt3;
  }
  
  public zzbfv(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, boolean paramBoolean1, String paramString4, boolean paramBoolean2, int paramInt3)
  {
    zzc = paramString1;
    zzd = paramInt1;
    zza = paramInt2;
    zze = paramString2;
    zzf = paramString3;
    zzg = paramBoolean1;
    zzb = paramString4;
    zzh = paramBoolean2;
    zzi = paramInt3;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof zzbfv))
    {
      paramObject = (zzbfv)paramObject;
      if ((zzbg.zza(zzc, zzc)) && (zzd == zzd) && (zza == zza) && (zzbg.zza(zzb, zzb)) && (zzbg.zza(zze, zze)) && (zzbg.zza(zzf, zzf)) && (zzg == zzg) && (zzh == zzh) && (zzi == zzi)) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { zzc, Integer.valueOf(zzd), Integer.valueOf(zza), zzb, zze, zzf, Boolean.valueOf(zzg), Boolean.valueOf(zzh), Integer.valueOf(zzi) });
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PlayLoggerContext[");
    localStringBuilder.append("package=");
    localStringBuilder.append(zzc);
    localStringBuilder.append(',');
    localStringBuilder.append("packageVersionCode=");
    localStringBuilder.append(zzd);
    localStringBuilder.append(',');
    localStringBuilder.append("logSource=");
    localStringBuilder.append(zza);
    localStringBuilder.append(',');
    localStringBuilder.append("logSourceName=");
    localStringBuilder.append(zzb);
    localStringBuilder.append(',');
    localStringBuilder.append("uploadAccount=");
    localStringBuilder.append(zze);
    localStringBuilder.append(',');
    localStringBuilder.append("loggingId=");
    localStringBuilder.append(zzf);
    localStringBuilder.append(',');
    localStringBuilder.append("logAndroidId=");
    localStringBuilder.append(zzg);
    localStringBuilder.append(',');
    localStringBuilder.append("isAnonymous=");
    localStringBuilder.append(zzh);
    localStringBuilder.append(',');
    localStringBuilder.append("qosTier=");
    localStringBuilder.append(zzi);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, zzc, false);
    zzbgo.zza(paramParcel, 3, zzd);
    zzbgo.zza(paramParcel, 4, zza);
    zzbgo.zza(paramParcel, 5, zze, false);
    zzbgo.zza(paramParcel, 6, zzf, false);
    zzbgo.zza(paramParcel, 7, zzg);
    zzbgo.zza(paramParcel, 8, zzb, false);
    zzbgo.zza(paramParcel, 9, zzh);
    zzbgo.zza(paramParcel, 10, zzi);
    zzbgo.zza(paramParcel, paramInt);
  }
}

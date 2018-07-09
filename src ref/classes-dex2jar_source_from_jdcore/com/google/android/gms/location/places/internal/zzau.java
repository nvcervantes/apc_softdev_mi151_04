package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Locale;

@Hide
public final class zzau
  extends zzbgl
{
  public static final Parcelable.Creator<zzau> CREATOR = new zzav();
  private static zzau zza = new zzau("com.google.android.gms", Locale.getDefault(), null);
  private String zzb;
  private String zzc;
  private String zzd;
  private String zze;
  private int zzf;
  private int zzg;
  
  public zzau(String paramString1, String paramString2, String paramString3, String paramString4, int paramInt1, int paramInt2)
  {
    zzb = paramString1;
    zzc = paramString2;
    zzd = paramString3;
    zze = paramString4;
    zzf = paramInt1;
    zzg = paramInt2;
  }
  
  private zzau(String paramString1, Locale paramLocale, String paramString2)
  {
    this(paramString1, paramLocale.toString(), null, null, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, 0);
  }
  
  public zzau(String paramString1, Locale paramLocale, String paramString2, String paramString3, int paramInt)
  {
    this(paramString1, paramLocale.toString(), paramString2, paramString3, GoogleApiAvailability.GOOGLE_PLAY_SERVICES_VERSION_CODE, paramInt);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (!(paramObject instanceof zzau)) {
        return false;
      }
      paramObject = (zzau)paramObject;
      if ((zzf == zzf) && (zzg == zzg) && (zzc.equals(zzc)) && (zzb.equals(zzb)) && (zzbg.zza(zzd, zzd)) && (zzbg.zza(zze, zze))) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { zzb, zzc, zzd, zze, Integer.valueOf(zzf), Integer.valueOf(zzg) });
  }
  
  @SuppressLint({"DefaultLocale"})
  public final String toString()
  {
    return zzbg.zza(this).zza("clientPackageName", zzb).zza("locale", zzc).zza("accountName", zzd).zza("gCoreClientName", zze).toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzb, false);
    zzbgo.zza(paramParcel, 2, zzc, false);
    zzbgo.zza(paramParcel, 3, zzd, false);
    zzbgo.zza(paramParcel, 4, zze, false);
    zzbgo.zza(paramParcel, 6, zzf);
    zzbgo.zza(paramParcel, 7, zzg);
    zzbgo.zza(paramParcel, paramInt);
  }
}

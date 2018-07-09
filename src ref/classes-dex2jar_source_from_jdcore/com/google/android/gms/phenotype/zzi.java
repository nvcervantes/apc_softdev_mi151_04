package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Comparator;

public final class zzi
  extends zzbgl
  implements Comparable<zzi>
{
  public static final Parcelable.Creator<zzi> CREATOR = new zzk();
  private static Comparator<zzi> zzi = new zzj();
  public final String zza;
  public final int zzb;
  private long zzc;
  private boolean zzd;
  private double zze;
  private String zzf;
  private byte[] zzg;
  private int zzh;
  
  public zzi(String paramString1, long paramLong, boolean paramBoolean, double paramDouble, String paramString2, byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    zza = paramString1;
    zzc = paramLong;
    zzd = paramBoolean;
    zze = paramDouble;
    zzf = paramString2;
    zzg = paramArrayOfByte;
    zzh = paramInt1;
    zzb = paramInt2;
  }
  
  private static int zza(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2) {
      return -1;
    }
    if (paramInt1 == paramInt2) {
      return 0;
    }
    return 1;
  }
  
  public final boolean equals(Object paramObject)
  {
    if ((paramObject instanceof zzi))
    {
      paramObject = (zzi)paramObject;
      if ((zzn.zza(zza, zza)) && (zzh == zzh))
      {
        if (zzb != zzb) {
          return false;
        }
        switch (zzh)
        {
        default: 
          int i = zzh;
          paramObject = new StringBuilder(31);
          paramObject.append("Invalid enum value: ");
          paramObject.append(i);
          throw new AssertionError(paramObject.toString());
        case 5: 
          return Arrays.equals(zzg, zzg);
        case 4: 
          return zzn.zza(zzf, zzf);
        case 3: 
          return zze == zze;
        case 2: 
          return zzd == zzd;
        }
        if (zzc == zzc) {
          return true;
        }
      }
    }
    return false;
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Flag(");
    localStringBuilder.append(zza);
    localStringBuilder.append(", ");
    String str;
    switch (zzh)
    {
    default: 
      str = zza;
      int i = zzh;
      localStringBuilder = new StringBuilder(27 + String.valueOf(str).length());
      localStringBuilder.append("Invalid type: ");
      localStringBuilder.append(str);
      localStringBuilder.append(", ");
      localStringBuilder.append(i);
      throw new AssertionError(localStringBuilder.toString());
    case 5: 
      if (zzg == null)
      {
        str = "null";
      }
      else
      {
        localStringBuilder.append("'");
        str = Base64.encodeToString(zzg, 3);
      }
      break;
    case 4: 
      localStringBuilder.append("'");
      str = zzf;
      localStringBuilder.append(str);
      str = "'";
      localStringBuilder.append(str);
      break;
    case 3: 
      localStringBuilder.append(zze);
      break;
    case 2: 
      localStringBuilder.append(zzd);
      break;
    }
    localStringBuilder.append(zzc);
    localStringBuilder.append(", ");
    localStringBuilder.append(zzh);
    localStringBuilder.append(", ");
    localStringBuilder.append(zzb);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, zza, false);
    zzbgo.zza(paramParcel, 3, zzc);
    zzbgo.zza(paramParcel, 4, zzd);
    zzbgo.zza(paramParcel, 5, zze);
    zzbgo.zza(paramParcel, 6, zzf, false);
    zzbgo.zza(paramParcel, 7, zzg, false);
    zzbgo.zza(paramParcel, 8, zzh);
    zzbgo.zza(paramParcel, 9, zzb);
    zzbgo.zza(paramParcel, paramInt);
  }
}

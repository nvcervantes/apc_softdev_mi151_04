package com.google.android.gms.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.location.Geofence;
import java.util.Locale;

@Hide
public final class zzchp
  extends zzbgl
  implements Geofence
{
  public static final Parcelable.Creator<zzchp> CREATOR = new zzchq();
  private final String zza;
  private final long zzb;
  private final short zzc;
  private final double zzd;
  private final double zze;
  private final float zzf;
  private final int zzg;
  private final int zzh;
  private final int zzi;
  
  public zzchp(String paramString, int paramInt1, short paramShort, double paramDouble1, double paramDouble2, float paramFloat, long paramLong, int paramInt2, int paramInt3)
  {
    if ((paramString != null) && (paramString.length() <= 100))
    {
      if (paramFloat <= 0.0F)
      {
        paramString = new StringBuilder(31);
        paramString.append("invalid radius: ");
        paramString.append(paramFloat);
        throw new IllegalArgumentException(paramString.toString());
      }
      if ((paramDouble1 <= 90.0D) && (paramDouble1 >= -90.0D))
      {
        if ((paramDouble2 <= 180.0D) && (paramDouble2 >= -180.0D))
        {
          int i = paramInt1 & 0x7;
          if (i == 0)
          {
            paramString = new StringBuilder(46);
            paramString.append("No supported transition specified: ");
            paramString.append(paramInt1);
            throw new IllegalArgumentException(paramString.toString());
          }
          zzc = paramShort;
          zza = paramString;
          zzd = paramDouble1;
          zze = paramDouble2;
          zzf = paramFloat;
          zzb = paramLong;
          zzg = i;
          zzh = paramInt2;
          zzi = paramInt3;
          return;
        }
        paramString = new StringBuilder(43);
        paramString.append("invalid longitude: ");
        paramString.append(paramDouble2);
        throw new IllegalArgumentException(paramString.toString());
      }
      paramString = new StringBuilder(42);
      paramString.append("invalid latitude: ");
      paramString.append(paramDouble1);
      throw new IllegalArgumentException(paramString.toString());
    }
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramString = "requestId is null or too long: ".concat(paramString);
    } else {
      paramString = new String("requestId is null or too long: ");
    }
    throw new IllegalArgumentException(paramString);
  }
  
  public static zzchp zza(byte[] paramArrayOfByte)
  {
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
    localParcel.setDataPosition(0);
    paramArrayOfByte = (zzchp)CREATOR.createFromParcel(localParcel);
    localParcel.recycle();
    return paramArrayOfByte;
  }
  
  @Hide
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject == null) {
      return false;
    }
    if (!(paramObject instanceof zzchp)) {
      return false;
    }
    paramObject = (zzchp)paramObject;
    if (zzf != zzf) {
      return false;
    }
    if (zzd != zzd) {
      return false;
    }
    if (zze != zze) {
      return false;
    }
    return zzc == zzc;
  }
  
  public final String getRequestId()
  {
    return zza;
  }
  
  @Hide
  public final int hashCode()
  {
    long l = Double.doubleToLongBits(zzd);
    int i = (int)(l ^ l >>> 32);
    l = Double.doubleToLongBits(zze);
    return ((((i + 31) * 31 + (int)(l ^ l >>> 32)) * 31 + Float.floatToIntBits(zzf)) * 31 + zzc) * 31 + zzg;
  }
  
  @Hide
  public final String toString()
  {
    Locale localLocale = Locale.US;
    String str;
    if (zzc != 1) {
      str = null;
    } else {
      str = "CIRCLE";
    }
    return String.format(localLocale, "Geofence[%s id:%s transitions:%d %.6f, %.6f %.0fm, resp=%ds, dwell=%dms, @%d]", new Object[] { str, zza.replaceAll("\\p{C}", "?"), Integer.valueOf(zzg), Double.valueOf(zzd), Double.valueOf(zze), Float.valueOf(zzf), Integer.valueOf(zzh / 1000), Integer.valueOf(zzi), Long.valueOf(zzb) });
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, getRequestId(), false);
    zzbgo.zza(paramParcel, 2, zzb);
    zzbgo.zza(paramParcel, 3, zzc);
    zzbgo.zza(paramParcel, 4, zzd);
    zzbgo.zza(paramParcel, 5, zze);
    zzbgo.zza(paramParcel, 6, zzf);
    zzbgo.zza(paramParcel, 7, zzg);
    zzbgo.zza(paramParcel, 8, zzh);
    zzbgo.zza(paramParcel, 9, zzi);
    zzbgo.zza(paramParcel, paramInt);
  }
}

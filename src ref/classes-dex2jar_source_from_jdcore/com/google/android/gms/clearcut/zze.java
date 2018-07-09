package com.google.android.gms.clearcut;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbfv;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import com.google.android.gms.internal.zzfmr;
import com.google.android.gms.phenotype.ExperimentTokens;
import java.util.Arrays;

@Hide
public final class zze
  extends zzbgl
{
  public static final Parcelable.Creator<zze> CREATOR = new zzf();
  public zzbfv zza;
  public byte[] zzb;
  public final zzfmr zzc;
  public final ClearcutLogger.zzb zzd;
  public final ClearcutLogger.zzb zze;
  private int[] zzf;
  private String[] zzg;
  private int[] zzh;
  private byte[][] zzi;
  private ExperimentTokens[] zzj;
  private boolean zzk;
  
  public zze(zzbfv paramZzbfv, zzfmr paramZzfmr, ClearcutLogger.zzb paramZzb1, ClearcutLogger.zzb paramZzb2, int[] paramArrayOfInt1, String[] paramArrayOfString, int[] paramArrayOfInt2, byte[][] paramArrayOfByte, ExperimentTokens[] paramArrayOfExperimentTokens, boolean paramBoolean)
  {
    zza = paramZzbfv;
    zzc = paramZzfmr;
    zzd = paramZzb1;
    zze = null;
    zzf = paramArrayOfInt1;
    zzg = null;
    zzh = paramArrayOfInt2;
    zzi = null;
    zzj = null;
    zzk = paramBoolean;
  }
  
  zze(zzbfv paramZzbfv, byte[] paramArrayOfByte, int[] paramArrayOfInt1, String[] paramArrayOfString, int[] paramArrayOfInt2, byte[][] paramArrayOfByte1, boolean paramBoolean, ExperimentTokens[] paramArrayOfExperimentTokens)
  {
    zza = paramZzbfv;
    zzb = paramArrayOfByte;
    zzf = paramArrayOfInt1;
    zzg = paramArrayOfString;
    zzc = null;
    zzd = null;
    zze = null;
    zzh = paramArrayOfInt2;
    zzi = paramArrayOfByte1;
    zzj = paramArrayOfExperimentTokens;
    zzk = paramBoolean;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject instanceof zze))
    {
      paramObject = (zze)paramObject;
      if ((zzbg.zza(zza, zza)) && (Arrays.equals(zzb, zzb)) && (Arrays.equals(zzf, zzf)) && (Arrays.equals(zzg, zzg)) && (zzbg.zza(zzc, zzc)) && (zzbg.zza(zzd, zzd)) && (zzbg.zza(zze, zze)) && (Arrays.equals(zzh, zzh)) && (Arrays.deepEquals(zzi, zzi)) && (Arrays.equals(zzj, zzj)) && (zzk == zzk)) {
        return true;
      }
    }
    return false;
  }
  
  public final int hashCode()
  {
    return Arrays.hashCode(new Object[] { zza, zzb, zzf, zzg, zzc, zzd, zze, zzh, zzi, zzj, Boolean.valueOf(zzk) });
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("LogEventParcelable[");
    localStringBuilder.append(zza);
    localStringBuilder.append(", LogEventBytes: ");
    String str;
    if (zzb == null) {
      str = null;
    } else {
      str = new String(zzb);
    }
    localStringBuilder.append(str);
    localStringBuilder.append(", TestCodes: ");
    localStringBuilder.append(Arrays.toString(zzf));
    localStringBuilder.append(", MendelPackages: ");
    localStringBuilder.append(Arrays.toString(zzg));
    localStringBuilder.append(", LogEvent: ");
    localStringBuilder.append(zzc);
    localStringBuilder.append(", ExtensionProducer: ");
    localStringBuilder.append(zzd);
    localStringBuilder.append(", VeProducer: ");
    localStringBuilder.append(zze);
    localStringBuilder.append(", ExperimentIDs: ");
    localStringBuilder.append(Arrays.toString(zzh));
    localStringBuilder.append(", ExperimentTokens: ");
    localStringBuilder.append(Arrays.toString(zzi));
    localStringBuilder.append(", ExperimentTokensParcelables: ");
    localStringBuilder.append(Arrays.toString(zzj));
    localStringBuilder.append(", AddPhenotypeExperimentTokens: ");
    localStringBuilder.append(zzk);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, zza, paramInt, false);
    zzbgo.zza(paramParcel, 3, zzb, false);
    zzbgo.zza(paramParcel, 4, zzf, false);
    zzbgo.zza(paramParcel, 5, zzg, false);
    zzbgo.zza(paramParcel, 6, zzh, false);
    zzbgo.zza(paramParcel, 7, zzi, false);
    zzbgo.zza(paramParcel, 8, zzk);
    zzbgo.zza(paramParcel, 9, zzj, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}

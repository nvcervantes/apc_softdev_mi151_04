package com.google.android.gms.phenotype;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.annotation.KeepForSdk;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@KeepForSdk
public class ExperimentTokens
  extends zzbgl
{
  @KeepForSdk
  public static final Parcelable.Creator<ExperimentTokens> CREATOR = new zzh();
  private static byte[][] zza = new byte[0][];
  private static ExperimentTokens zzb = new ExperimentTokens("", null, zza, zza, zza, zza, null, null);
  private static final zza zzk = new zzd();
  private static final zza zzl = new zze();
  private static final zza zzm = new zzf();
  private static final zza zzn = new zzg();
  private String zzc;
  private byte[] zzd;
  private byte[][] zze;
  private byte[][] zzf;
  private byte[][] zzg;
  private byte[][] zzh;
  private int[] zzi;
  private byte[][] zzj;
  
  public ExperimentTokens(String paramString, byte[] paramArrayOfByte, byte[][] paramArrayOfByte1, byte[][] paramArrayOfByte2, byte[][] paramArrayOfByte3, byte[][] paramArrayOfByte4, int[] paramArrayOfInt, byte[][] paramArrayOfByte5)
  {
    zzc = paramString;
    zzd = paramArrayOfByte;
    zze = paramArrayOfByte1;
    zzf = paramArrayOfByte2;
    zzg = paramArrayOfByte3;
    zzh = paramArrayOfByte4;
    zzi = paramArrayOfInt;
    zzj = paramArrayOfByte5;
  }
  
  private static List<Integer> zza(int[] paramArrayOfInt)
  {
    if (paramArrayOfInt == null) {
      return Collections.emptyList();
    }
    ArrayList localArrayList = new ArrayList(paramArrayOfInt.length);
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(Integer.valueOf(paramArrayOfInt[i]));
      i += 1;
    }
    Collections.sort(localArrayList);
    return localArrayList;
  }
  
  private static List<String> zza(byte[][] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      return Collections.emptyList();
    }
    ArrayList localArrayList = new ArrayList(paramArrayOfByte.length);
    int j = paramArrayOfByte.length;
    int i = 0;
    while (i < j)
    {
      localArrayList.add(Base64.encodeToString(paramArrayOfByte[i], 3));
      i += 1;
    }
    Collections.sort(localArrayList);
    return localArrayList;
  }
  
  private static void zza(StringBuilder paramStringBuilder, String paramString, int[] paramArrayOfInt)
  {
    paramStringBuilder.append(paramString);
    paramStringBuilder.append("=");
    if (paramArrayOfInt == null) {}
    for (paramString = "null";; paramString = ")")
    {
      paramStringBuilder.append(paramString);
      return;
      paramStringBuilder.append("(");
      int k = paramArrayOfInt.length;
      int j = 1;
      int i = 0;
      while (i < k)
      {
        int m = paramArrayOfInt[i];
        if (j == 0) {
          paramStringBuilder.append(", ");
        }
        paramStringBuilder.append(m);
        i += 1;
        j = 0;
      }
    }
  }
  
  private static void zza(StringBuilder paramStringBuilder, String paramString, byte[][] paramArrayOfByte)
  {
    paramStringBuilder.append(paramString);
    paramStringBuilder.append("=");
    if (paramArrayOfByte == null) {}
    for (paramString = "null";; paramString = ")")
    {
      paramStringBuilder.append(paramString);
      return;
      paramStringBuilder.append("(");
      int k = paramArrayOfByte.length;
      int j = 1;
      int i = 0;
      while (i < k)
      {
        paramString = paramArrayOfByte[i];
        if (j == 0) {
          paramStringBuilder.append(", ");
        }
        paramStringBuilder.append("'");
        paramStringBuilder.append(Base64.encodeToString(paramString, 3));
        paramStringBuilder.append("'");
        i += 1;
        j = 0;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if ((paramObject instanceof ExperimentTokens))
    {
      paramObject = (ExperimentTokens)paramObject;
      if ((zzn.zza(zzc, zzc)) && (Arrays.equals(zzd, zzd)) && (zzn.zza(zza(zze), zza(zze))) && (zzn.zza(zza(zzf), zza(zzf))) && (zzn.zza(zza(zzg), zza(zzg))) && (zzn.zza(zza(zzh), zza(zzh))) && (zzn.zza(zza(zzi), zza(zzi))) && (zzn.zza(zza(zzj), zza(zzj)))) {
        return true;
      }
    }
    return false;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder1 = new StringBuilder("ExperimentTokens");
    localStringBuilder1.append("(");
    if (zzc == null)
    {
      localObject = "null";
    }
    else
    {
      localObject = zzc;
      StringBuilder localStringBuilder2 = new StringBuilder(2 + String.valueOf(localObject).length());
      localStringBuilder2.append("'");
      localStringBuilder2.append((String)localObject);
      localStringBuilder2.append("'");
      localObject = localStringBuilder2.toString();
    }
    localStringBuilder1.append((String)localObject);
    localStringBuilder1.append(", ");
    Object localObject = zzd;
    localStringBuilder1.append("direct");
    localStringBuilder1.append("=");
    if (localObject == null) {}
    for (localObject = "null";; localObject = "'")
    {
      localStringBuilder1.append((String)localObject);
      break;
      localStringBuilder1.append("'");
      localStringBuilder1.append(Base64.encodeToString((byte[])localObject, 3));
    }
    localStringBuilder1.append(", ");
    zza(localStringBuilder1, "GAIA", zze);
    localStringBuilder1.append(", ");
    zza(localStringBuilder1, "PSEUDO", zzf);
    localStringBuilder1.append(", ");
    zza(localStringBuilder1, "ALWAYS", zzg);
    localStringBuilder1.append(", ");
    zza(localStringBuilder1, "OTHER", zzh);
    localStringBuilder1.append(", ");
    zza(localStringBuilder1, "weak", zzi);
    localStringBuilder1.append(", ");
    zza(localStringBuilder1, "directs", zzj);
    localStringBuilder1.append(")");
    return localStringBuilder1.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, zzc, false);
    zzbgo.zza(paramParcel, 3, zzd, false);
    zzbgo.zza(paramParcel, 4, zze, false);
    zzbgo.zza(paramParcel, 5, zzf, false);
    zzbgo.zza(paramParcel, 6, zzg, false);
    zzbgo.zza(paramParcel, 7, zzh, false);
    zzbgo.zza(paramParcel, 8, zzi, false);
    zzbgo.zza(paramParcel, 9, zzj, false);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  static abstract interface zza {}
}

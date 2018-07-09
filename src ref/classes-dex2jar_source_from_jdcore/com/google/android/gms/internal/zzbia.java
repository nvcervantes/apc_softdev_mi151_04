package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.SparseArray;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzb;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzr;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class zzbia
  extends zzbhs
{
  public static final Parcelable.Creator<zzbia> CREATOR = new zzbib();
  private final int zza;
  private final Parcel zzb;
  private final int zzc;
  private final zzbhv zzd;
  private final String zze;
  private int zzf;
  private int zzg;
  
  zzbia(int paramInt, Parcel paramParcel, zzbhv paramZzbhv)
  {
    zza = paramInt;
    zzb = ((Parcel)zzbq.zza(paramParcel));
    zzc = 2;
    zzd = paramZzbhv;
    if (zzd == null) {}
    for (paramParcel = null;; paramParcel = zzd.zza())
    {
      zze = paramParcel;
      break;
    }
    zzf = 2;
  }
  
  private static HashMap<String, String> zza(Bundle paramBundle)
  {
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramBundle.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      localHashMap.put(str, paramBundle.getString(str));
    }
    return localHashMap;
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, Object paramObject)
  {
    switch (paramInt)
    {
    default: 
      paramStringBuilder = new StringBuilder(26);
      paramStringBuilder.append("Unknown type = ");
      paramStringBuilder.append(paramInt);
      throw new IllegalArgumentException(paramStringBuilder.toString());
    case 11: 
      throw new IllegalArgumentException("Method does not accept concrete type.");
    case 10: 
      zzr.zza(paramStringBuilder, (HashMap)paramObject);
      return;
    case 9: 
      paramStringBuilder.append("\"");
      paramStringBuilder.append(zzc.zzb((byte[])paramObject));
      paramStringBuilder.append("\"");
      return;
    case 8: 
      paramStringBuilder.append("\"");
      paramStringBuilder.append(zzc.zza((byte[])paramObject));
      paramStringBuilder.append("\"");
      return;
    case 7: 
      paramStringBuilder.append("\"");
      paramStringBuilder.append(zzq.zza(paramObject.toString()));
      paramStringBuilder.append("\"");
      return;
    }
    paramStringBuilder.append(paramObject);
  }
  
  private final void zza(StringBuilder paramStringBuilder, zzbhq<?, ?> paramZzbhq, Parcel paramParcel, int paramInt)
  {
    boolean bool = zzd;
    int j = 0;
    int i = 0;
    String str;
    if (bool)
    {
      paramStringBuilder.append("[");
      int k = zzc;
      Object localObject = null;
      str = null;
      switch (k)
      {
      default: 
        throw new IllegalStateException("Unknown field type out.");
      case 11: 
        paramParcel = zzbgm.zzae(paramParcel, paramInt);
        i = paramParcel.length;
        paramInt = 0;
      }
      while (paramInt < i)
      {
        if (paramInt > 0) {
          paramStringBuilder.append(",");
        }
        paramParcel[paramInt].setDataPosition(0);
        zza(paramStringBuilder, paramZzbhq.zzc(), paramParcel[paramInt]);
        paramInt += 1;
        continue;
        throw new UnsupportedOperationException("List of type BASE64, BASE64_URL_SAFE, or STRING_MAP is not supported");
        zzb.zza(paramStringBuilder, zzbgm.zzaa(paramParcel, paramInt));
        break;
        zzb.zza(paramStringBuilder, zzbgm.zzv(paramParcel, paramInt));
        break;
        zzb.zza(paramStringBuilder, zzbgm.zzz(paramParcel, paramInt));
        break;
        paramInt = zzbgm.zza(paramParcel, paramInt);
        i = paramParcel.dataPosition();
        if (paramInt == 0)
        {
          paramZzbhq = str;
        }
        else
        {
          paramZzbhq = paramParcel.createDoubleArray();
          paramParcel.setDataPosition(i + paramInt);
        }
        zzb.zza(paramStringBuilder, paramZzbhq);
        break;
        zzb.zza(paramStringBuilder, zzbgm.zzy(paramParcel, paramInt));
        break;
        zzb.zza(paramStringBuilder, zzbgm.zzx(paramParcel, paramInt));
        break;
        j = zzbgm.zza(paramParcel, paramInt);
        k = paramParcel.dataPosition();
        if (j == 0)
        {
          paramZzbhq = localObject;
        }
        else
        {
          int m = paramParcel.readInt();
          paramZzbhq = new BigInteger[m];
          paramInt = i;
          while (paramInt < m)
          {
            paramZzbhq[paramInt] = new BigInteger(paramParcel.createByteArray());
            paramInt += 1;
          }
          paramParcel.setDataPosition(k + j);
        }
        zzb.zza(paramStringBuilder, paramZzbhq);
        break;
        paramZzbhq = zzbgm.zzw(paramParcel, paramInt);
        i = paramZzbhq.length;
        paramInt = j;
        while (paramInt < i)
        {
          if (paramInt != 0) {
            paramStringBuilder.append(",");
          }
          paramStringBuilder.append(Integer.toString(paramZzbhq[paramInt]));
          paramInt += 1;
        }
      }
      paramStringBuilder.append("]");
      return;
    }
    switch (zzc)
    {
    default: 
      throw new IllegalStateException("Unknown field type out");
    case 11: 
      paramParcel = zzbgm.zzad(paramParcel, paramInt);
      paramParcel.setDataPosition(0);
      zza(paramStringBuilder, paramZzbhq.zzc(), paramParcel);
      return;
    case 10: 
      paramZzbhq = zzbgm.zzs(paramParcel, paramInt);
      paramParcel = paramZzbhq.keySet();
      paramParcel.size();
      paramStringBuilder.append("{");
      paramParcel = paramParcel.iterator();
      for (paramInt = 1; paramParcel.hasNext(); paramInt = 0)
      {
        str = (String)paramParcel.next();
        if (paramInt == 0) {
          paramStringBuilder.append(",");
        }
        paramStringBuilder.append("\"");
        paramStringBuilder.append(str);
        paramStringBuilder.append("\"");
        paramStringBuilder.append(":");
        paramStringBuilder.append("\"");
        paramStringBuilder.append(zzq.zza(paramZzbhq.getString(str)));
        paramStringBuilder.append("\"");
      }
      paramStringBuilder.append("}");
      return;
    case 9: 
      paramZzbhq = zzbgm.zzt(paramParcel, paramInt);
      paramStringBuilder.append("\"");
      paramStringBuilder.append(zzc.zzb(paramZzbhq));
      paramStringBuilder.append("\"");
      return;
    case 8: 
      paramZzbhq = zzbgm.zzt(paramParcel, paramInt);
      paramStringBuilder.append("\"");
      paramStringBuilder.append(zzc.zza(paramZzbhq));
      paramStringBuilder.append("\"");
      return;
    case 7: 
      paramZzbhq = zzbgm.zzq(paramParcel, paramInt);
      paramStringBuilder.append("\"");
      paramStringBuilder.append(zzq.zza(paramZzbhq));
      paramStringBuilder.append("\"");
      return;
    case 6: 
      paramStringBuilder.append(zzbgm.zzc(paramParcel, paramInt));
      return;
    case 5: 
      paramStringBuilder.append(zzbgm.zzp(paramParcel, paramInt));
      return;
    case 4: 
      paramStringBuilder.append(zzbgm.zzn(paramParcel, paramInt));
      return;
    case 3: 
      paramStringBuilder.append(zzbgm.zzl(paramParcel, paramInt));
      return;
    case 2: 
      paramStringBuilder.append(zzbgm.zzi(paramParcel, paramInt));
      return;
    case 1: 
      paramStringBuilder.append(zzbgm.zzk(paramParcel, paramInt));
      return;
    }
    paramStringBuilder.append(zzbgm.zzg(paramParcel, paramInt));
  }
  
  private final void zza(StringBuilder paramStringBuilder, zzbhq<?, ?> paramZzbhq, Object paramObject)
  {
    if (zzb)
    {
      paramObject = (ArrayList)paramObject;
      paramStringBuilder.append("[");
      int j = paramObject.size();
      int i = 0;
      while (i < j)
      {
        if (i != 0) {
          paramStringBuilder.append(",");
        }
        zza(paramStringBuilder, zza, paramObject.get(i));
        i += 1;
      }
      paramStringBuilder.append("]");
      return;
    }
    zza(paramStringBuilder, zza, paramObject);
  }
  
  private final void zza(StringBuilder paramStringBuilder, Map<String, zzbhq<?, ?>> paramMap, Parcel paramParcel)
  {
    SparseArray localSparseArray = new SparseArray();
    paramMap = paramMap.entrySet().iterator();
    Object localObject;
    while (paramMap.hasNext())
    {
      localObject = (Map.Entry)paramMap.next();
      localSparseArray.put(getValuezzf, localObject);
    }
    paramStringBuilder.append('{');
    int j = zzbgm.zza(paramParcel);
    int i = 0;
    while (paramParcel.dataPosition() < j)
    {
      int k = paramParcel.readInt();
      localObject = (Map.Entry)localSparseArray.get(0xFFFF & k);
      if (localObject != null)
      {
        if (i != 0) {
          paramStringBuilder.append(",");
        }
        paramMap = (String)((Map.Entry)localObject).getKey();
        localObject = (zzbhq)((Map.Entry)localObject).getValue();
        paramStringBuilder.append("\"");
        paramStringBuilder.append(paramMap);
        paramStringBuilder.append("\":");
        if (((zzbhq)localObject).zzb())
        {
          switch (zzc)
          {
          default: 
            i = zzc;
            paramStringBuilder = new StringBuilder(36);
            paramStringBuilder.append("Unknown field out type = ");
            paramStringBuilder.append(i);
            throw new IllegalArgumentException(paramStringBuilder.toString());
          case 11: 
            throw new IllegalArgumentException("Method does not accept concrete type.");
          case 10: 
            paramMap = zza(zzbgm.zzs(paramParcel, k));
            break;
          case 8: 
          case 9: 
            paramMap = zzbgm.zzt(paramParcel, k);
            break;
          case 7: 
            paramMap = zzbgm.zzq(paramParcel, k);
            break;
          case 6: 
            paramMap = Boolean.valueOf(zzbgm.zzc(paramParcel, k));
            break;
          case 5: 
            paramMap = zzbgm.zzp(paramParcel, k);
            break;
          case 4: 
            paramMap = Double.valueOf(zzbgm.zzn(paramParcel, k));
            break;
          case 3: 
            paramMap = Float.valueOf(zzbgm.zzl(paramParcel, k));
            break;
          case 2: 
            paramMap = Long.valueOf(zzbgm.zzi(paramParcel, k));
            break;
          case 1: 
            paramMap = zzbgm.zzk(paramParcel, k);
            break;
          case 0: 
            paramMap = Integer.valueOf(zzbgm.zzg(paramParcel, k));
          }
          zza(paramStringBuilder, (zzbhq)localObject, zza((zzbhq)localObject, paramMap));
        }
        else
        {
          zza(paramStringBuilder, (zzbhq)localObject, paramParcel, k);
        }
        i = 1;
      }
    }
    if (paramParcel.dataPosition() != j)
    {
      paramStringBuilder = new StringBuilder(37);
      paramStringBuilder.append("Overread allowed size end=");
      paramStringBuilder.append(j);
      throw new zzbgn(paramStringBuilder.toString(), paramParcel);
    }
    paramStringBuilder.append('}');
  }
  
  private Parcel zzb()
  {
    switch (zzf)
    {
    default: 
      break;
    case 0: 
      zzg = zzbgo.zza(zzb);
    case 1: 
      zzbgo.zza(zzb, zzg);
      zzf = 2;
    }
    return zzb;
  }
  
  public String toString()
  {
    zzbq.zza(zzd, "Cannot convert to JSON on client side.");
    Parcel localParcel = zzb();
    localParcel.setDataPosition(0);
    StringBuilder localStringBuilder = new StringBuilder(100);
    zza(localStringBuilder, zzd.zza(zze), localParcel);
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, zzb(), false);
    zzbhv localZzbhv;
    switch (zzc)
    {
    default: 
      paramInt = zzc;
      paramParcel = new StringBuilder(34);
      paramParcel.append("Invalid creation type: ");
      paramParcel.append(paramInt);
      throw new IllegalStateException(paramParcel.toString());
    case 1: 
    case 2: 
      localZzbhv = zzd;
      break;
    case 0: 
      localZzbhv = null;
    }
    zzbgo.zza(paramParcel, 3, localZzbhv, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
  
  public final Object zza(String paramString)
  {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
  
  public final Map<String, zzbhq<?, ?>> zza()
  {
    if (zzd == null) {
      return null;
    }
    return zzd.zza(zze);
  }
  
  public final boolean zzb(String paramString)
  {
    throw new UnsupportedOperationException("Converting to JSON does not require this method.");
  }
}

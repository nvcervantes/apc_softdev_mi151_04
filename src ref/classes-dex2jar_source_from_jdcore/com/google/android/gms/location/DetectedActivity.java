package com.google.android.gms.location;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;
import java.util.Comparator;

public class DetectedActivity
  extends zzbgl
{
  public static final Parcelable.Creator<DetectedActivity> CREATOR = new zzi();
  public static final int IN_VEHICLE = 0;
  public static final int ON_BICYCLE = 1;
  public static final int ON_FOOT = 2;
  public static final int RUNNING = 8;
  public static final int STILL = 3;
  public static final int TILTING = 5;
  public static final int UNKNOWN = 4;
  public static final int WALKING = 7;
  @Hide
  private static Comparator<DetectedActivity> zza = new zzh();
  @Hide
  private static int[] zzb = { 9, 10 };
  @Hide
  private static int[] zzc = { 0, 1, 2, 4, 5, 6, 7, 8, 10, 11, 12, 13, 14, 16, 17 };
  @Hide
  private static int[] zzd = { 0, 1, 2, 3, 7, 8, 16, 17 };
  private int zze;
  private int zzf;
  
  public DetectedActivity(int paramInt1, int paramInt2)
  {
    zze = paramInt1;
    zzf = paramInt2;
  }
  
  @Hide
  public static void zza(int paramInt)
  {
    Object localObject = zzd;
    int i = 0;
    int k = localObject.length;
    int j = 0;
    while (i < k)
    {
      if (localObject[i] == paramInt) {
        j = 1;
      }
      i += 1;
    }
    if (j == 0)
    {
      localObject = new StringBuilder(81);
      ((StringBuilder)localObject).append(paramInt);
      ((StringBuilder)localObject).append(" is not a valid DetectedActivity supported by Activity Transition API.");
      Log.w("DetectedActivity", ((StringBuilder)localObject).toString());
    }
  }
  
  @Hide
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (DetectedActivity)paramObject;
      if ((zze == zze) && (zzf == zzf)) {
        return true;
      }
    }
    return false;
  }
  
  public int getConfidence()
  {
    return zzf;
  }
  
  public int getType()
  {
    int j = zze;
    int i = j;
    if (j > 17) {
      i = 4;
    }
    return i;
  }
  
  @Hide
  public int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(zze), Integer.valueOf(zzf) });
  }
  
  public String toString()
  {
    int i = getType();
    String str;
    switch (i)
    {
    default: 
      switch (i)
      {
      default: 
        switch (i)
        {
        default: 
          str = Integer.toString(i);
          break;
        case 17: 
          str = "IN_RAIL_VEHICLE";
          break;
        case 16: 
          str = "IN_ROAD_VEHICLE";
        }
        break;
      case 8: 
        str = "RUNNING";
        break;
      case 7: 
        str = "WALKING";
      }
      break;
    case 5: 
      str = "TILTING";
      break;
    case 4: 
      str = "UNKNOWN";
      break;
    case 3: 
      str = "STILL";
      break;
    case 2: 
      str = "ON_FOOT";
      break;
    case 1: 
      str = "ON_BICYCLE";
      break;
    case 0: 
      str = "IN_VEHICLE";
    }
    i = zzf;
    StringBuilder localStringBuilder = new StringBuilder(48 + String.valueOf(str).length());
    localStringBuilder.append("DetectedActivity [type=");
    localStringBuilder.append(str);
    localStringBuilder.append(", confidence=");
    localStringBuilder.append(i);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zze);
    zzbgo.zza(paramParcel, 2, zzf);
    zzbgo.zza(paramParcel, paramInt);
  }
}

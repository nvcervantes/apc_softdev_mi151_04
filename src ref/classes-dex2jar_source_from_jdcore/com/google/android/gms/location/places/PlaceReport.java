package com.google.android.gms.location.places;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class PlaceReport
  extends zzbgl
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<PlaceReport> CREATOR = new zzl();
  private int zza;
  private final String zzb;
  private final String zzc;
  private final String zzd;
  
  @Hide
  PlaceReport(int paramInt, String paramString1, String paramString2, String paramString3)
  {
    zza = paramInt;
    zzb = paramString1;
    zzc = paramString2;
    zzd = paramString3;
  }
  
  public static PlaceReport create(String paramString1, String paramString2)
  {
    zzbq.zza(paramString1);
    zzbq.zza(paramString2);
    zzbq.zza("unknown");
    int i = "unknown".hashCode();
    boolean bool = false;
    switch (i)
    {
    default: 
      break;
    case 1287171955: 
      if ("unknown".equals("inferredRadioSignals")) {
        i = 3;
      }
      break;
    case 1164924125: 
      if ("unknown".equals("inferredSnappedToRoad")) {
        i = 5;
      }
      break;
    case -262743844: 
      if ("unknown".equals("inferredReverseGeocoding")) {
        i = 4;
      }
      break;
    case -284840886: 
      if ("unknown".equals("unknown")) {
        i = 0;
      }
      break;
    case -1194968642: 
      if ("unknown".equals("userReported")) {
        i = 1;
      }
      break;
    case -1436706272: 
      if ("unknown".equals("inferredGeofencing")) {
        i = 2;
      }
      break;
    }
    i = -1;
    switch (i)
    {
    default: 
      break;
    case 0: 
    case 1: 
    case 2: 
    case 3: 
    case 4: 
    case 5: 
      bool = true;
    }
    zzbq.zzb(bool, "Invalid source");
    return new PlaceReport(1, paramString1, paramString2, "unknown");
  }
  
  @Hide
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof PlaceReport)) {
      return false;
    }
    paramObject = (PlaceReport)paramObject;
    return (zzbg.zza(zzb, zzb)) && (zzbg.zza(zzc, zzc)) && (zzbg.zza(zzd, zzd));
  }
  
  public String getPlaceId()
  {
    return zzb;
  }
  
  public String getTag()
  {
    return zzc;
  }
  
  @Hide
  public int hashCode()
  {
    return Arrays.hashCode(new Object[] { zzb, zzc, zzd });
  }
  
  @Hide
  public String toString()
  {
    zzbi localZzbi = zzbg.zza(this);
    localZzbi.zza("placeId", zzb);
    localZzbi.zza("tag", zzc);
    if (!"unknown".equals(zzd)) {
      localZzbi.zza("source", zzd);
    }
    return localZzbi.toString();
  }
  
  @Hide
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zza);
    zzbgo.zza(paramParcel, 2, getPlaceId(), false);
    zzbgo.zza(paramParcel, 3, getTag(), false);
    zzbgo.zza(paramParcel, 4, zzd, false);
    zzbgo.zza(paramParcel, paramInt);
  }
}

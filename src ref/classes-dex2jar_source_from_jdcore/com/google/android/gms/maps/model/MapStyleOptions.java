package com.google.android.gms.maps.model;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.util.zzp;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.io.IOException;

public final class MapStyleOptions
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<MapStyleOptions> CREATOR = new zzg();
  private static final String zza = "MapStyleOptions";
  private String zzb;
  
  public MapStyleOptions(String paramString)
  {
    zzb = paramString;
  }
  
  public static MapStyleOptions loadRawResourceStyle(Context paramContext, int paramInt)
    throws Resources.NotFoundException
  {
    paramContext = paramContext.getResources().openRawResource(paramInt);
    try
    {
      paramContext = new MapStyleOptions(new String(zzp.zza(paramContext, true), "UTF-8"));
      return paramContext;
    }
    catch (IOException paramContext)
    {
      paramContext = String.valueOf(paramContext);
      StringBuilder localStringBuilder = new StringBuilder(37 + String.valueOf(paramContext).length());
      localStringBuilder.append("Failed to read resource ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(": ");
      localStringBuilder.append(paramContext);
      throw new Resources.NotFoundException(localStringBuilder.toString());
    }
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, zzb, false);
    zzbgo.zza(paramParcel, paramInt);
  }
}

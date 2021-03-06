package com.google.android.gms.location.places;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbi;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public class PlacePhotoResult
  extends zzbgl
  implements Result
{
  public static final Parcelable.Creator<PlacePhotoResult> CREATOR = new zzk();
  private final Status zza;
  private BitmapTeleporter zzb;
  private final Bitmap zzc;
  
  @Hide
  public PlacePhotoResult(Status paramStatus, BitmapTeleporter paramBitmapTeleporter)
  {
    zza = paramStatus;
    zzb = paramBitmapTeleporter;
    if (zzb != null) {}
    for (paramStatus = paramBitmapTeleporter.zza();; paramStatus = null)
    {
      zzc = paramStatus;
      return;
    }
  }
  
  public Bitmap getBitmap()
  {
    return zzc;
  }
  
  public Status getStatus()
  {
    return zza;
  }
  
  @Hide
  public String toString()
  {
    return zzbg.zza(this).zza("status", zza).zza("bitmap", zzc).toString();
  }
  
  @Hide
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, getStatus(), paramInt, false);
    zzbgo.zza(paramParcel, 2, zzb, paramInt, false);
    zzbgo.zza(paramParcel, i);
  }
}

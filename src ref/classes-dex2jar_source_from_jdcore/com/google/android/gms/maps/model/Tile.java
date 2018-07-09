package com.google.android.gms.maps.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;

public final class Tile
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<Tile> CREATOR = new zzr();
  public final byte[] data;
  public final int height;
  public final int width;
  
  public Tile(int paramInt1, int paramInt2, byte[] paramArrayOfByte)
  {
    width = paramInt1;
    height = paramInt2;
    data = paramArrayOfByte;
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, width);
    zzbgo.zza(paramParcel, 3, height);
    zzbgo.zza(paramParcel, 4, data, false);
    zzbgo.zza(paramParcel, paramInt);
  }
}

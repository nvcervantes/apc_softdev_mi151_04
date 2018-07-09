package com.google.android.gms.maps.model;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.dynamic.IObjectWrapper;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.util.Arrays;

public class Cap
  extends zzbgl
{
  @Hide
  public static final Parcelable.Creator<Cap> CREATOR = new zzb();
  private static final String zza = "Cap";
  private final int zzb;
  @Nullable
  private final BitmapDescriptor zzc;
  @Nullable
  private final Float zzd;
  
  @Hide
  protected Cap(int paramInt)
  {
    this(paramInt, null, null);
  }
  
  @Hide
  Cap(int paramInt, @Nullable IBinder paramIBinder, @Nullable Float paramFloat)
  {
    this(paramInt, paramIBinder, paramFloat);
  }
  
  private Cap(int paramInt, @Nullable BitmapDescriptor paramBitmapDescriptor, @Nullable Float paramFloat)
  {
    int i;
    if ((paramFloat != null) && (paramFloat.floatValue() > 0.0F)) {
      i = 1;
    } else {
      i = 0;
    }
    boolean bool;
    if ((paramInt == 3) && ((paramBitmapDescriptor == null) || (i == 0))) {
      bool = false;
    } else {
      bool = true;
    }
    zzbq.zzb(bool, String.format("Invalid Cap: type=%s bitmapDescriptor=%s bitmapRefWidth=%s", new Object[] { Integer.valueOf(paramInt), paramBitmapDescriptor, paramFloat }));
    zzb = paramInt;
    zzc = paramBitmapDescriptor;
    zzd = paramFloat;
  }
  
  @Hide
  protected Cap(@NonNull BitmapDescriptor paramBitmapDescriptor, float paramFloat)
  {
    this(3, paramBitmapDescriptor, Float.valueOf(paramFloat));
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof Cap)) {
      return false;
    }
    paramObject = (Cap)paramObject;
    return (zzb == zzb) && (zzbg.zza(zzc, zzc)) && (zzbg.zza(zzd, zzd));
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(new Object[] { Integer.valueOf(zzb), zzc, zzd });
  }
  
  public String toString()
  {
    int i = zzb;
    StringBuilder localStringBuilder = new StringBuilder(23);
    localStringBuilder.append("[Cap: type=");
    localStringBuilder.append(i);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramInt = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 2, zzb);
    IBinder localIBinder;
    if (zzc == null) {
      localIBinder = null;
    } else {
      localIBinder = zzc.zza().asBinder();
    }
    zzbgo.zza(paramParcel, 3, localIBinder, false);
    zzbgo.zza(paramParcel, 4, zzd, false);
    zzbgo.zza(paramParcel, paramInt);
  }
  
  @Hide
  final Cap zza()
  {
    switch (zzb)
    {
    default: 
      String str = zza;
      int i = zzb;
      StringBuilder localStringBuilder = new StringBuilder(29);
      localStringBuilder.append("Unknown Cap type: ");
      localStringBuilder.append(i);
      Log.w(str, localStringBuilder.toString());
      return this;
    case 3: 
      return new CustomCap(zzc, zzd.floatValue());
    case 2: 
      return new RoundCap();
    case 1: 
      return new SquareCap();
    }
    return new ButtCap();
  }
}

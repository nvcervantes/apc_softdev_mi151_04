package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgp;

@Hide
public class zzd<T extends zzbgp>
  extends AbstractDataBuffer<T>
{
  private static final String[] zzb = { "data" };
  private final Parcelable.Creator<T> zzc;
  
  public zzd(DataHolder paramDataHolder, Parcelable.Creator<T> paramCreator)
  {
    super(paramDataHolder);
    zzc = paramCreator;
  }
  
  public static <T extends zzbgp> void zza(DataHolder.zza paramZza, T paramT)
  {
    Parcel localParcel = Parcel.obtain();
    paramT.writeToParcel(localParcel, 0);
    paramT = new ContentValues();
    paramT.put("data", localParcel.marshall());
    paramZza.zza(paramT);
    localParcel.recycle();
  }
  
  public static DataHolder.zza zzb()
  {
    return DataHolder.zza(zzb);
  }
  
  public T zza(int paramInt)
  {
    Object localObject = zza.zzf("data", paramInt, zza.zza(paramInt));
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall((byte[])localObject, 0, localObject.length);
    localParcel.setDataPosition(0);
    localObject = (zzbgp)zzc.createFromParcel(localParcel);
    localParcel.recycle();
    return localObject;
  }
}

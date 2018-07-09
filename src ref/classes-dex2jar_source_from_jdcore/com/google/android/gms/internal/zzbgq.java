package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import java.util.ArrayList;

@Hide
public final class zzbgq
{
  public static <T extends zzbgp> T zza(Intent paramIntent, String paramString, Parcelable.Creator<T> paramCreator)
  {
    paramIntent = paramIntent.getByteArrayExtra(paramString);
    if (paramIntent == null) {
      return null;
    }
    return zza(paramIntent, paramCreator);
  }
  
  public static <T extends zzbgp> T zza(byte[] paramArrayOfByte, Parcelable.Creator<T> paramCreator)
  {
    zzbq.zza(paramCreator);
    Parcel localParcel = Parcel.obtain();
    localParcel.unmarshall(paramArrayOfByte, 0, paramArrayOfByte.length);
    localParcel.setDataPosition(0);
    paramArrayOfByte = (zzbgp)paramCreator.createFromParcel(localParcel);
    localParcel.recycle();
    return paramArrayOfByte;
  }
  
  public static <T extends zzbgp> void zza(T paramT, Intent paramIntent, String paramString)
  {
    paramIntent.putExtra(paramString, zza(paramT));
  }
  
  public static <T extends zzbgp> byte[] zza(T paramT)
  {
    Parcel localParcel = Parcel.obtain();
    paramT.writeToParcel(localParcel, 0);
    paramT = localParcel.marshall();
    localParcel.recycle();
    return paramT;
  }
  
  public static <T extends zzbgp> ArrayList<T> zzb(Intent paramIntent, String paramString, Parcelable.Creator<T> paramCreator)
  {
    paramString = (ArrayList)paramIntent.getSerializableExtra(paramString);
    if (paramString == null) {
      return null;
    }
    paramIntent = new ArrayList(paramString.size());
    paramString = (ArrayList)paramString;
    int j = paramString.size();
    int i = 0;
    while (i < j)
    {
      Object localObject = paramString.get(i);
      i += 1;
      paramIntent.add(zza((byte[])localObject, paramCreator));
    }
    return paramIntent;
  }
}

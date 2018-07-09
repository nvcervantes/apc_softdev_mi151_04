package com.google.android.gms.internal;

import com.google.android.gms.common.internal.Hide;
import java.util.Collection;
import java.util.Iterator;
import java.util.Map;

public abstract class zzbhs
  extends zzbhp
  implements zzbgp
{
  public zzbhs() {}
  
  @Hide
  public final int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!getClass().isInstance(paramObject)) {
      return false;
    }
    paramObject = (zzbhp)paramObject;
    Iterator localIterator = zza().values().iterator();
    while (localIterator.hasNext())
    {
      zzbhq localZzbhq = (zzbhq)localIterator.next();
      if (zza(localZzbhq))
      {
        if ((!paramObject.zza(localZzbhq)) || (!zzb(localZzbhq).equals(paramObject.zzb(localZzbhq)))) {
          return false;
        }
      }
      else if (paramObject.zza(localZzbhq)) {
        return false;
      }
    }
    return true;
  }
  
  public int hashCode()
  {
    Iterator localIterator = zza().values().iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      zzbhq localZzbhq = (zzbhq)localIterator.next();
      if (zza(localZzbhq)) {
        i = i * 31 + zzb(localZzbhq).hashCode();
      }
    }
    return i;
  }
  
  public Object zza(String paramString)
  {
    return null;
  }
  
  public boolean zzb(String paramString)
  {
    return false;
  }
}

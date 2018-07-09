package com.google.android.gms.internal;

import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.util.zzc;
import com.google.android.gms.common.util.zzq;
import com.google.android.gms.common.util.zzr;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public abstract class zzbhp
{
  public zzbhp() {}
  
  protected static <O, I> I zza(zzbhq<I, O> paramZzbhq, Object paramObject)
  {
    if (zzbhq.zza(paramZzbhq) != null) {
      return paramZzbhq.zza(paramObject);
    }
    return paramObject;
  }
  
  private static void zza(StringBuilder paramStringBuilder, zzbhq paramZzbhq, Object paramObject)
  {
    if (zza == 11) {}
    for (paramZzbhq = ((zzbhp)zzg.cast(paramObject)).toString();; paramZzbhq = "\"")
    {
      paramStringBuilder.append(paramZzbhq);
      return;
      if (zza != 7) {
        break;
      }
      paramStringBuilder.append("\"");
      paramStringBuilder.append(zzq.zza((String)paramObject));
    }
    paramStringBuilder.append(paramObject);
  }
  
  private static void zza(StringBuilder paramStringBuilder, zzbhq paramZzbhq, ArrayList<Object> paramArrayList)
  {
    paramStringBuilder.append("[");
    int j = paramArrayList.size();
    int i = 0;
    while (i < j)
    {
      if (i > 0) {
        paramStringBuilder.append(",");
      }
      Object localObject = paramArrayList.get(i);
      if (localObject != null) {
        zza(paramStringBuilder, paramZzbhq, localObject);
      }
      i += 1;
    }
    paramStringBuilder.append("]");
  }
  
  public String toString()
  {
    Map localMap = zza();
    StringBuilder localStringBuilder = new StringBuilder(100);
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      String str2 = (String)localIterator.next();
      zzbhq localZzbhq = (zzbhq)localMap.get(str2);
      if (zza(localZzbhq))
      {
        Object localObject = zza(localZzbhq, zzb(localZzbhq));
        if (localStringBuilder.length() == 0) {}
        for (str1 = "{";; str1 = ",")
        {
          localStringBuilder.append(str1);
          break;
        }
        localStringBuilder.append("\"");
        localStringBuilder.append(str2);
        localStringBuilder.append("\":");
        if (localObject == null) {}
        for (str1 = "null";; str1 = "\"")
        {
          localStringBuilder.append(str1);
          break;
          switch (zzc)
          {
          default: 
            if (!zzb) {
              break label261;
            }
            zza(localStringBuilder, localZzbhq, (ArrayList)localObject);
            break;
          case 10: 
            zzr.zza(localStringBuilder, (HashMap)localObject);
            break;
          case 9: 
            localStringBuilder.append("\"");
            str1 = zzc.zzb((byte[])localObject);
            break;
          case 8: 
            localStringBuilder.append("\"");
            str1 = zzc.zza((byte[])localObject);
            localStringBuilder.append(str1);
          }
        }
        label261:
        zza(localStringBuilder, localZzbhq, localObject);
      }
    }
    if (localStringBuilder.length() > 0) {}
    for (String str1 = "}";; str1 = "{}")
    {
      localStringBuilder.append(str1);
      break;
    }
    return localStringBuilder.toString();
  }
  
  protected abstract Object zza(String paramString);
  
  public abstract Map<String, zzbhq<?, ?>> zza();
  
  protected boolean zza(zzbhq paramZzbhq)
  {
    if (zzc == 11)
    {
      if (zzd)
      {
        paramZzbhq = zze;
        throw new UnsupportedOperationException("Concrete type arrays not supported");
      }
      paramZzbhq = zze;
      throw new UnsupportedOperationException("Concrete types not supported");
    }
    return zzb(zze);
  }
  
  protected Object zzb(zzbhq paramZzbhq)
  {
    Object localObject = zze;
    if (zzg != null)
    {
      zza(zze);
      zzbq.zza(true, "Concrete field shouldn't be value object: %s", new Object[] { zze });
      boolean bool = zzd;
      try
      {
        char c = Character.toUpperCase(((String)localObject).charAt(0));
        paramZzbhq = ((String)localObject).substring(1);
        localObject = new StringBuilder(4 + String.valueOf(paramZzbhq).length());
        ((StringBuilder)localObject).append("get");
        ((StringBuilder)localObject).append(c);
        ((StringBuilder)localObject).append(paramZzbhq);
        paramZzbhq = ((StringBuilder)localObject).toString();
        paramZzbhq = getClass().getMethod(paramZzbhq, new Class[0]).invoke(this, new Object[0]);
        return paramZzbhq;
      }
      catch (Exception paramZzbhq)
      {
        throw new RuntimeException(paramZzbhq);
      }
    }
    return zza(zze);
  }
  
  protected abstract boolean zzb(String paramString);
}

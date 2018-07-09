package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

final class zzfhq<FieldDescriptorType extends zzfhs<FieldDescriptorType>>
{
  private static final zzfhq zzd = new zzfhq(true);
  private final zzfjy<FieldDescriptorType, Object> zza;
  private boolean zzb;
  private boolean zzc = false;
  
  private zzfhq()
  {
    zza = zzfjy.zza(16);
  }
  
  private zzfhq(boolean paramBoolean)
  {
    zza = zzfjy.zza(0);
    if (!zzb)
    {
      zza.zza();
      zzb = true;
    }
  }
  
  static int zza(zzfky paramZzfky, int paramInt, Object paramObject)
  {
    int i = zzfhg.zzf(paramInt);
    paramInt = i;
    if (paramZzfky == zzfky.zzj)
    {
      zzfhz.zza((zzfjc)paramObject);
      paramInt = i << 1;
    }
    return paramInt + zzb(paramZzfky, paramObject);
  }
  
  private static int zza(Map.Entry<FieldDescriptorType, Object> paramEntry)
  {
    zzfhs localZzfhs = (zzfhs)paramEntry.getKey();
    Object localObject = paramEntry.getValue();
    if ((localZzfhs.zzc() == zzfld.zzi) && (!localZzfhs.zzd()) && (!localZzfhs.zze()))
    {
      if ((localObject instanceof zzfig)) {
        return zzfhg.zzb(((zzfhs)paramEntry.getKey()).zza(), (zzfig)localObject);
      }
      return zzfhg.zzd(((zzfhs)paramEntry.getKey()).zza(), (zzfjc)localObject);
    }
    return zzb(localZzfhs, localObject);
  }
  
  public static <T extends zzfhs<T>> zzfhq<T> zza()
  {
    return zzd;
  }
  
  public static Object zza(zzfhb paramZzfhb, zzfky paramZzfky, boolean paramBoolean)
    throws IOException
  {
    zzfle localZzfle = zzfle.zza;
    switch (zzfkx.zza[paramZzfky.ordinal()])
    {
    default: 
      throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
    case 18: 
      throw new IllegalArgumentException("readPrimitiveField() cannot handle enums.");
    case 17: 
      throw new IllegalArgumentException("readPrimitiveField() cannot handle embedded messages.");
    case 16: 
      throw new IllegalArgumentException("readPrimitiveField() cannot handle nested groups.");
    case 15: 
      return localZzfle.zza(paramZzfhb);
    case 14: 
      return Long.valueOf(paramZzfhb.zzr());
    case 13: 
      return Integer.valueOf(paramZzfhb.zzq());
    case 12: 
      return Long.valueOf(paramZzfhb.zzp());
    case 11: 
      return Integer.valueOf(paramZzfhb.zzo());
    case 10: 
      return Integer.valueOf(paramZzfhb.zzm());
    case 9: 
      return paramZzfhb.zzl();
    case 8: 
      return Boolean.valueOf(paramZzfhb.zzi());
    case 7: 
      return Integer.valueOf(paramZzfhb.zzh());
    case 6: 
      return Long.valueOf(paramZzfhb.zzg());
    case 5: 
      return Integer.valueOf(paramZzfhb.zzf());
    case 4: 
      return Long.valueOf(paramZzfhb.zzd());
    case 3: 
      return Long.valueOf(paramZzfhb.zze());
    case 2: 
      return Float.valueOf(paramZzfhb.zzc());
    }
    return Double.valueOf(paramZzfhb.zzb());
  }
  
  static void zza(zzfhg paramZzfhg, zzfky paramZzfky, int paramInt, Object paramObject)
    throws IOException
  {
    if (paramZzfky == zzfky.zzj)
    {
      paramZzfky = (zzfjc)paramObject;
      zzfhz.zza(paramZzfky);
      paramZzfhg.zze(paramInt, paramZzfky);
      return;
    }
    paramZzfhg.zza(paramInt, paramZzfky.zzb());
    switch (zzfhr.zzb[paramZzfky.ordinal()])
    {
    default: 
      return;
    case 18: 
      if ((paramObject instanceof zzfia))
      {
        paramZzfhg.zzb(((zzfia)paramObject).zza());
        return;
      }
      paramZzfhg.zzb(((Integer)paramObject).intValue());
      return;
    case 17: 
      paramZzfhg.zzb(((Long)paramObject).longValue());
      return;
    case 16: 
      paramZzfhg.zzd(((Integer)paramObject).intValue());
      return;
    case 15: 
      paramZzfhg.zzc(((Long)paramObject).longValue());
      return;
    case 14: 
      paramZzfhg.zze(((Integer)paramObject).intValue());
      return;
    case 13: 
      paramZzfhg.zzc(((Integer)paramObject).intValue());
      return;
    case 12: 
      if ((paramObject instanceof zzfgs))
      {
        paramZzfhg.zza((zzfgs)paramObject);
        return;
      }
      paramZzfky = (byte[])paramObject;
      paramZzfhg.zzd(paramZzfky, 0, paramZzfky.length);
      return;
    case 11: 
      if ((paramObject instanceof zzfgs))
      {
        paramZzfhg.zza((zzfgs)paramObject);
        return;
      }
      paramZzfhg.zza((String)paramObject);
      return;
    case 10: 
      paramZzfhg.zza((zzfjc)paramObject);
      return;
    case 9: 
      ((zzfjc)paramObject).zza(paramZzfhg);
      return;
    case 8: 
      paramZzfhg.zza(((Boolean)paramObject).booleanValue());
      return;
    case 7: 
      paramZzfhg.zze(((Integer)paramObject).intValue());
      return;
    case 6: 
      paramZzfhg.zzc(((Long)paramObject).longValue());
      return;
    case 5: 
      paramZzfhg.zzb(((Integer)paramObject).intValue());
      return;
    case 4: 
      paramZzfhg.zza(((Long)paramObject).longValue());
      return;
    case 3: 
      paramZzfhg.zza(((Long)paramObject).longValue());
      return;
    case 2: 
      paramZzfhg.zza(((Float)paramObject).floatValue());
      return;
    }
    paramZzfhg.zza(((Double)paramObject).doubleValue());
  }
  
  private void zza(FieldDescriptorType paramFieldDescriptorType, Object paramObject)
  {
    if (paramFieldDescriptorType.zzd())
    {
      if (!(paramObject instanceof List)) {
        throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
      }
      ArrayList localArrayList = new ArrayList();
      localArrayList.addAll((List)paramObject);
      paramObject = (ArrayList)localArrayList;
      int j = paramObject.size();
      int i = 0;
      while (i < j)
      {
        Object localObject = paramObject.get(i);
        i += 1;
        zza(paramFieldDescriptorType.zzb(), localObject);
      }
      paramObject = localArrayList;
    }
    else
    {
      zza(paramFieldDescriptorType.zzb(), paramObject);
    }
    if ((paramObject instanceof zzfig)) {
      zzc = true;
    }
    zza.zza(paramFieldDescriptorType, paramObject);
  }
  
  private static void zza(zzfky paramZzfky, Object paramObject)
  {
    zzfhz.zza(paramObject);
    int i = zzfhr.zza[paramZzfky.zza().ordinal()];
    boolean bool2 = true;
    boolean bool3 = false;
    boolean bool1;
    switch (i)
    {
    default: 
      bool1 = bool3;
      break;
    case 9: 
      bool1 = bool2;
      if (!(paramObject instanceof zzfjc))
      {
        bool1 = bool3;
        if (!(paramObject instanceof zzfig)) {
          break;
        }
        bool1 = bool2;
      }
      break;
    case 8: 
      bool1 = bool2;
      if (!(paramObject instanceof Integer))
      {
        bool1 = bool3;
        if (!(paramObject instanceof zzfia)) {
          break;
        }
        bool1 = bool2;
      }
      break;
    }
    for (;;)
    {
      break;
      bool1 = bool2;
      if (!(paramObject instanceof zzfgs))
      {
        bool1 = bool3;
        if (!(paramObject instanceof byte[])) {
          break;
        }
        bool1 = bool2;
        continue;
        bool1 = paramObject instanceof String;
        continue;
        bool1 = paramObject instanceof Boolean;
        continue;
        bool1 = paramObject instanceof Double;
        continue;
        bool1 = paramObject instanceof Float;
        continue;
        bool1 = paramObject instanceof Long;
        continue;
        bool1 = paramObject instanceof Integer;
      }
    }
    if (!bool1) {
      throw new IllegalArgumentException("Wrong object type used with protocol message reflection.");
    }
  }
  
  private static int zzb(zzfhs<?> paramZzfhs, Object paramObject)
  {
    zzfky localZzfky = paramZzfhs.zzb();
    int k = paramZzfhs.zza();
    if (paramZzfhs.zzd())
    {
      boolean bool = paramZzfhs.zze();
      int j = 0;
      int i = 0;
      if (bool)
      {
        paramZzfhs = ((List)paramObject).iterator();
        while (paramZzfhs.hasNext()) {
          i += zzb(localZzfky, paramZzfhs.next());
        }
        return zzfhg.zzf(k) + i + zzfhg.zzn(i);
      }
      paramZzfhs = ((List)paramObject).iterator();
      i = j;
      while (paramZzfhs.hasNext()) {
        i += zza(localZzfky, k, paramZzfhs.next());
      }
      return i;
    }
    return zza(localZzfky, k, paramObject);
  }
  
  private static int zzb(zzfky paramZzfky, Object paramObject)
  {
    switch (zzfhr.zzb[paramZzfky.ordinal()])
    {
    default: 
      throw new RuntimeException("There is no way to get here, but the compiler thinks otherwise.");
    case 18: 
      if ((paramObject instanceof zzfia)) {
        return zzfhg.zzl(((zzfia)paramObject).zza());
      }
      return zzfhg.zzl(((Integer)paramObject).intValue());
    case 17: 
      return zzfhg.zzf(((Long)paramObject).longValue());
    case 16: 
      return zzfhg.zzi(((Integer)paramObject).intValue());
    case 15: 
      return zzfhg.zzh(((Long)paramObject).longValue());
    case 14: 
      return zzfhg.zzk(((Integer)paramObject).intValue());
    case 13: 
      return zzfhg.zzh(((Integer)paramObject).intValue());
    case 12: 
      if ((paramObject instanceof zzfgs)) {
        return zzfhg.zzb((zzfgs)paramObject);
      }
      return zzfhg.zzb((byte[])paramObject);
    case 11: 
      if ((paramObject instanceof zzfgs)) {
        return zzfhg.zzb((zzfgs)paramObject);
      }
      return zzfhg.zzb((String)paramObject);
    case 10: 
      if ((paramObject instanceof zzfig)) {
        return zzfhg.zza((zzfig)paramObject);
      }
      return zzfhg.zzb((zzfjc)paramObject);
    case 9: 
      return zzfhg.zzc((zzfjc)paramObject);
    case 8: 
      return zzfhg.zzb(((Boolean)paramObject).booleanValue());
    case 7: 
      return zzfhg.zzj(((Integer)paramObject).intValue());
    case 6: 
      return zzfhg.zzg(((Long)paramObject).longValue());
    case 5: 
      return zzfhg.zzg(((Integer)paramObject).intValue());
    case 4: 
      return zzfhg.zze(((Long)paramObject).longValue());
    case 3: 
      return zzfhg.zzd(((Long)paramObject).longValue());
    case 2: 
      return zzfhg.zzb(((Float)paramObject).floatValue());
    }
    return zzfhg.zzb(((Double)paramObject).doubleValue());
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzfhq)) {
      return false;
    }
    paramObject = (zzfhq)paramObject;
    return zza.equals(zza);
  }
  
  public final int hashCode()
  {
    return zza.hashCode();
  }
  
  public final Iterator<Map.Entry<FieldDescriptorType, Object>> zzb()
  {
    if (zzc) {
      return new zzfij(zza.entrySet().iterator());
    }
    return zza.entrySet().iterator();
  }
  
  public final int zzc()
  {
    int j = 0;
    int i = 0;
    while (j < zza.zzc())
    {
      i += zza(zza.zzb(j));
      j += 1;
    }
    Iterator localIterator = zza.zzd().iterator();
    while (localIterator.hasNext()) {
      i += zza((Map.Entry)localIterator.next());
    }
    return i;
  }
}

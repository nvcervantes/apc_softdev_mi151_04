package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

final class zzflp
  implements Cloneable
{
  private zzfln<?, ?> zza;
  private Object zzb;
  private List<zzflu> zzc = new ArrayList();
  
  zzflp() {}
  
  private final byte[] zzb()
    throws IOException
  {
    byte[] arrayOfByte = new byte[zza()];
    zza(zzflk.zza(arrayOfByte));
    return arrayOfByte;
  }
  
  private zzflp zzc()
  {
    zzflp localZzflp = new zzflp();
    try
    {
      zza = zza;
      if (zzc == null) {
        zzc = null;
      } else {
        zzc.addAll(zzc);
      }
      if (zzb != null)
      {
        Object localObject1;
        if ((zzb instanceof zzfls)) {
          localObject1 = (zzfls)((zzfls)zzb).clone();
        }
        int j;
        int i;
        Object localObject2;
        for (;;)
        {
          zzb = localObject1;
          return localZzflp;
          if ((zzb instanceof byte[]))
          {
            localObject1 = ((byte[])zzb).clone();
          }
          else
          {
            boolean bool = zzb instanceof byte[][];
            j = 0;
            i = 0;
            if (bool)
            {
              localObject1 = (byte[][])zzb;
              localObject2 = new byte[localObject1.length][];
              zzb = localObject2;
              while (i < localObject1.length)
              {
                localObject2[i] = ((byte[])localObject1[i].clone());
                i += 1;
              }
            }
            if ((zzb instanceof boolean[]))
            {
              localObject1 = ((boolean[])zzb).clone();
            }
            else if ((zzb instanceof int[]))
            {
              localObject1 = ((int[])zzb).clone();
            }
            else if ((zzb instanceof long[]))
            {
              localObject1 = ((long[])zzb).clone();
            }
            else if ((zzb instanceof float[]))
            {
              localObject1 = ((float[])zzb).clone();
            }
            else
            {
              if (!(zzb instanceof double[])) {
                break;
              }
              localObject1 = ((double[])zzb).clone();
            }
          }
        }
        if ((zzb instanceof zzfls[]))
        {
          localObject1 = (zzfls[])zzb;
          localObject2 = new zzfls[localObject1.length];
          zzb = localObject2;
          i = j;
          while (i < localObject1.length)
          {
            localObject2[i] = ((zzfls)localObject1[i].clone());
            i += 1;
          }
        }
      }
      return localZzflp;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzflp)) {
      return false;
    }
    paramObject = (zzflp)paramObject;
    if ((zzb != null) && (zzb != null))
    {
      if (zza != zza) {
        return false;
      }
      if (!zza.zza.isArray()) {
        return zzb.equals(zzb);
      }
      if ((zzb instanceof byte[])) {
        return Arrays.equals((byte[])zzb, (byte[])zzb);
      }
      if ((zzb instanceof int[])) {
        return Arrays.equals((int[])zzb, (int[])zzb);
      }
      if ((zzb instanceof long[])) {
        return Arrays.equals((long[])zzb, (long[])zzb);
      }
      if ((zzb instanceof float[])) {
        return Arrays.equals((float[])zzb, (float[])zzb);
      }
      if ((zzb instanceof double[])) {
        return Arrays.equals((double[])zzb, (double[])zzb);
      }
      if ((zzb instanceof boolean[])) {
        return Arrays.equals((boolean[])zzb, (boolean[])zzb);
      }
      return Arrays.deepEquals((Object[])zzb, (Object[])zzb);
    }
    if ((zzc != null) && (zzc != null)) {
      return zzc.equals(zzc);
    }
    try
    {
      boolean bool = Arrays.equals(zzb(), paramObject.zzb());
      return bool;
    }
    catch (IOException paramObject)
    {
      throw new IllegalStateException(paramObject);
    }
  }
  
  public final int hashCode()
  {
    try
    {
      int i = Arrays.hashCode(zzb());
      return 527 + i;
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
  }
  
  final int zza()
  {
    Object localObject1 = zzb;
    int j = 0;
    Object localObject2;
    int k;
    if (localObject1 != null)
    {
      localObject1 = zza;
      localObject2 = zzb;
      if (zzc)
      {
        int m = Array.getLength(localObject2);
        for (i = 0;; i = k)
        {
          k = i;
          if (j >= m) {
            break;
          }
          k = i;
          if (Array.get(localObject2, j) != null) {
            k = i + ((zzfln)localObject1).zza(Array.get(localObject2, j));
          }
          j += 1;
        }
      }
      return ((zzfln)localObject1).zza(localObject2);
    }
    localObject1 = zzc.iterator();
    int i = 0;
    for (;;)
    {
      k = i;
      if (!((Iterator)localObject1).hasNext()) {
        break;
      }
      localObject2 = (zzflu)((Iterator)localObject1).next();
      i += zzflk.zzd(zza) + 0 + zzb.length;
    }
    return k;
  }
  
  final <T> T zza(zzfln<?, T> paramZzfln)
  {
    if (zzb != null)
    {
      if (!zza.equals(paramZzfln)) {
        throw new IllegalStateException("Tried to getExtension with a different Extension.");
      }
    }
    else
    {
      zza = paramZzfln;
      zzb = paramZzfln.zza(zzc);
      zzc = null;
    }
    return zzb;
  }
  
  final void zza(zzflk paramZzflk)
    throws IOException
  {
    Object localObject2;
    if (zzb != null)
    {
      localObject1 = zza;
      localObject2 = zzb;
      if (zzc)
      {
        int j = Array.getLength(localObject2);
        int i = 0;
        while (i < j)
        {
          Object localObject3 = Array.get(localObject2, i);
          if (localObject3 != null) {
            ((zzfln)localObject1).zza(localObject3, paramZzflk);
          }
          i += 1;
        }
        return;
      }
      ((zzfln)localObject1).zza(localObject2, paramZzflk);
      return;
    }
    Object localObject1 = zzc.iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject2 = (zzflu)((Iterator)localObject1).next();
      paramZzflk.zzc(zza);
      paramZzflk.zzc(zzb);
    }
  }
  
  final void zza(zzflu paramZzflu)
  {
    zzc.add(paramZzflu);
  }
}

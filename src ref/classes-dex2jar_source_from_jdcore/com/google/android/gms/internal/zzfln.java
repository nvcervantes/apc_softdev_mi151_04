package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public final class zzfln<M extends zzflm<M>, T>
{
  protected final Class<T> zza;
  public final int zzb;
  protected final boolean zzc;
  private int zzd;
  private zzfhu<?, ?> zze;
  
  private zzfln(int paramInt1, Class<T> paramClass, int paramInt2, boolean paramBoolean)
  {
    this(11, paramClass, null, paramInt2, false);
  }
  
  private zzfln(int paramInt1, Class<T> paramClass, zzfhu<?, ?> paramZzfhu, int paramInt2, boolean paramBoolean)
  {
    zzd = paramInt1;
    zza = paramClass;
    zzb = paramInt2;
    zzc = false;
    zze = null;
  }
  
  public static <M extends zzflm<M>, T extends zzfls> zzfln<M, T> zza(int paramInt, Class<T> paramClass, long paramLong)
  {
    return new zzfln(11, paramClass, (int)paramLong, false);
  }
  
  private final Object zza(zzflj paramZzflj)
  {
    Object localObject1;
    if (zzc) {
      localObject1 = zza.getComponentType();
    } else {
      localObject1 = zza;
    }
    for (;;)
    {
      try
      {
        switch (zzd)
        {
        case 11: 
          continue;
          localObject2 = (zzfls)((Class)localObject1).newInstance();
          paramZzflj.zza((zzfls)localObject2);
          return localObject2;
        case 10: 
          localObject2 = (zzfls)((Class)localObject1).newInstance();
          paramZzflj.zza((zzfls)localObject2, zzb >>> 3);
          return localObject2;
          int i = zzd;
          paramZzflj = new StringBuilder(24);
          paramZzflj.append("Unknown type ");
          paramZzflj.append(i);
          throw new IllegalArgumentException(paramZzflj.toString());
        }
      }
      catch (IOException paramZzflj)
      {
        throw new IllegalArgumentException("Error reading extension field", paramZzflj);
      }
      catch (IllegalAccessException paramZzflj)
      {
        localObject1 = String.valueOf(localObject1);
        localObject2 = new StringBuilder(33 + String.valueOf(localObject1).length());
        ((StringBuilder)localObject2).append("Error creating instance of class ");
        ((StringBuilder)localObject2).append((String)localObject1);
        throw new IllegalArgumentException(((StringBuilder)localObject2).toString(), paramZzflj);
      }
      catch (InstantiationException paramZzflj)
      {
        localObject1 = String.valueOf(localObject1);
        Object localObject2 = new StringBuilder(33 + String.valueOf(localObject1).length());
        ((StringBuilder)localObject2).append("Error creating instance of class ");
        ((StringBuilder)localObject2).append((String)localObject1);
        throw new IllegalArgumentException(((StringBuilder)localObject2).toString(), paramZzflj);
      }
    }
  }
  
  public final boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof zzfln)) {
      return false;
    }
    paramObject = (zzfln)paramObject;
    return (zzd == zzd) && (zza == zza) && (zzb == zzb) && (zzc == zzc);
  }
  
  public final int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e2expr(TypeTransformer.java:632)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:716)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  protected final int zza(Object paramObject)
  {
    int i = zzb >>> 3;
    switch (zzd)
    {
    default: 
      i = zzd;
      paramObject = new StringBuilder(24);
      paramObject.append("Unknown type ");
      paramObject.append(i);
      throw new IllegalArgumentException(paramObject.toString());
    case 11: 
      return zzflk.zzb(i, (zzfls)paramObject);
    }
    paramObject = (zzfls)paramObject;
    return (zzflk.zzb(i) << 1) + paramObject.zzf();
  }
  
  final T zza(List<zzflu> paramList)
  {
    if (paramList == null) {
      return null;
    }
    if (zzc)
    {
      ArrayList localArrayList = new ArrayList();
      int j = 0;
      int i = 0;
      while (i < paramList.size())
      {
        zzflu localZzflu = (zzflu)paramList.get(i);
        if (zzb.length != 0) {
          localArrayList.add(zza(zzflj.zza(zzb)));
        }
        i += 1;
      }
      int k = localArrayList.size();
      if (k == 0) {
        return null;
      }
      paramList = zza.cast(Array.newInstance(zza.getComponentType(), k));
      i = j;
      while (i < k)
      {
        Array.set(paramList, i, localArrayList.get(i));
        i += 1;
      }
      return paramList;
    }
    if (paramList.isEmpty()) {
      return null;
    }
    paramList = (zzflu)paramList.get(paramList.size() - 1);
    return zza.cast(zza(zzflj.zza(zzb)));
  }
  
  protected final void zza(Object paramObject, zzflk paramZzflk)
  {
    for (;;)
    {
      try
      {
        paramZzflk.zzc(zzb);
        switch (zzd)
        {
        case 11: 
          continue;
          paramZzflk.zza((zzfls)paramObject);
          return;
        case 10: 
          int i = zzb;
          ((zzfls)paramObject).zza(paramZzflk);
          paramZzflk.zzc(i >>> 3, 4);
          return;
          i = zzd;
          paramObject = new StringBuilder(24);
          paramObject.append("Unknown type ");
          paramObject.append(i);
          throw new IllegalArgumentException(paramObject.toString());
        }
      }
      catch (IOException paramObject)
      {
        throw new IllegalStateException(paramObject);
      }
    }
  }
}

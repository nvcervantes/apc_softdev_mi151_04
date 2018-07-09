package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public abstract class zzfhu<MessageType extends zzfhu<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>>
  extends zzfgj<MessageType, BuilderType>
{
  private static Map<Object, zzfhu<?, ?>> zzd = new ConcurrentHashMap();
  protected zzfko zzb = zzfko.zza();
  protected int zzc = -1;
  
  public zzfhu() {}
  
  protected static <T extends zzfhu<T, ?>> T zza(T paramT, zzfgs paramZzfgs)
    throws zzfie
  {
    paramT = zza(paramT, paramZzfgs, zzfhm.zza());
    int m = 0;
    int k = 0;
    boolean bool;
    int i;
    int j;
    if (paramT != null)
    {
      bool = Boolean.TRUE.booleanValue();
      i = ((Byte)paramT.zza(zzg.zzc, null, null)).byteValue();
      if (i == 1)
      {
        j = 1;
      }
      else if (i == 0)
      {
        j = 0;
      }
      else
      {
        if (paramT.zza(zzg.zza, Boolean.FALSE, null) != null) {
          i = 1;
        } else {
          i = 0;
        }
        j = i;
        if (bool)
        {
          j = zzg.zzd;
          if (i != 0) {
            paramZzfgs = paramT;
          } else {
            paramZzfgs = null;
          }
          paramT.zza(j, paramZzfgs, null);
          j = i;
        }
      }
      if (j == 0) {
        throw new zzfkm(paramT).zza().zza(paramT);
      }
    }
    if (paramT != null)
    {
      bool = Boolean.TRUE.booleanValue();
      i = ((Byte)paramT.zza(zzg.zzc, null, null)).byteValue();
      if (i == 1)
      {
        j = 1;
      }
      else if (i == 0)
      {
        j = m;
      }
      else
      {
        i = k;
        if (paramT.zza(zzg.zza, Boolean.FALSE, null) != null) {
          i = 1;
        }
        j = i;
        if (bool)
        {
          j = zzg.zzd;
          if (i != 0) {
            paramZzfgs = paramT;
          } else {
            paramZzfgs = null;
          }
          paramT.zza(j, paramZzfgs, null);
          j = i;
        }
      }
      if (j == 0) {
        throw new zzfkm(paramT).zza().zza(paramT);
      }
    }
    return paramT;
  }
  
  /* Error */
  private static <T extends zzfhu<T, ?>> T zza(T paramT, zzfgs paramZzfgs, zzfhm paramZzfhm)
    throws zzfie
  {
    // Byte code:
    //   0: aload_1
    //   1: invokevirtual 110	com/google/android/gms/internal/zzfgs:zzd	()Lcom/google/android/gms/internal/zzfhb;
    //   4: astore_1
    //   5: aload_0
    //   6: aload_1
    //   7: aload_2
    //   8: invokestatic 113	com/google/android/gms/internal/zzfhu:zza	(Lcom/google/android/gms/internal/zzfhu;Lcom/google/android/gms/internal/zzfhb;Lcom/google/android/gms/internal/zzfhm;)Lcom/google/android/gms/internal/zzfhu;
    //   11: astore_0
    //   12: aload_1
    //   13: iconst_0
    //   14: invokevirtual 118	com/google/android/gms/internal/zzfhb:zza	(I)V
    //   17: aload_0
    //   18: areturn
    //   19: astore_1
    //   20: aload_1
    //   21: aload_0
    //   22: invokevirtual 102	com/google/android/gms/internal/zzfie:zza	(Lcom/google/android/gms/internal/zzfjc;)Lcom/google/android/gms/internal/zzfie;
    //   25: athrow
    //   26: astore_0
    //   27: aload_0
    //   28: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	29	0	paramT	T
    //   0	29	1	paramZzfgs	zzfgs
    //   0	29	2	paramZzfhm	zzfhm
    // Exception table:
    //   from	to	target	type
    //   12	17	19	com/google/android/gms/internal/zzfie
    //   0	12	26	com/google/android/gms/internal/zzfie
    //   20	26	26	com/google/android/gms/internal/zzfie
  }
  
  static <T extends zzfhu<T, ?>> T zza(T paramT, zzfhb paramZzfhb, zzfhm paramZzfhm)
    throws zzfie
  {
    paramT = (zzfhu)paramT.zza(zzg.zzg, null, null);
    try
    {
      paramT.zza(zzg.zze, paramZzfhb, paramZzfhm);
      paramT.zza(zzg.zzf, null, null);
      zzb.zzc();
      return paramT;
    }
    catch (RuntimeException paramT)
    {
      if ((paramT.getCause() instanceof zzfie)) {
        throw ((zzfie)paramT.getCause());
      }
      throw paramT;
    }
  }
  
  protected static <T extends zzfhu<T, ?>> T zza(T paramT, byte[] paramArrayOfByte)
    throws zzfie
  {
    paramArrayOfByte = zza(paramT, paramArrayOfByte, zzfhm.zza());
    if (paramArrayOfByte != null)
    {
      boolean bool = Boolean.TRUE.booleanValue();
      int k = ((Byte)paramArrayOfByte.zza(zzg.zzc, null, null)).byteValue();
      int j = 0;
      int i = 0;
      if (k == 1)
      {
        j = 1;
      }
      else if (k != 0)
      {
        if (paramArrayOfByte.zza(zzg.zza, Boolean.FALSE, null) != null) {
          i = 1;
        }
        j = i;
        if (bool)
        {
          j = zzg.zzd;
          if (i != 0) {
            paramT = paramArrayOfByte;
          } else {
            paramT = null;
          }
          paramArrayOfByte.zza(j, paramT, null);
          j = i;
        }
      }
      if (j == 0) {
        throw new zzfkm(paramArrayOfByte).zza().zza(paramArrayOfByte);
      }
    }
    return paramArrayOfByte;
  }
  
  /* Error */
  private static <T extends zzfhu<T, ?>> T zza(T paramT, byte[] paramArrayOfByte, zzfhm paramZzfhm)
    throws zzfie
  {
    // Byte code:
    //   0: aload_1
    //   1: invokestatic 142	com/google/android/gms/internal/zzfhb:zza	([B)Lcom/google/android/gms/internal/zzfhb;
    //   4: astore_1
    //   5: aload_0
    //   6: aload_1
    //   7: aload_2
    //   8: invokestatic 113	com/google/android/gms/internal/zzfhu:zza	(Lcom/google/android/gms/internal/zzfhu;Lcom/google/android/gms/internal/zzfhb;Lcom/google/android/gms/internal/zzfhm;)Lcom/google/android/gms/internal/zzfhu;
    //   11: astore_0
    //   12: aload_1
    //   13: iconst_0
    //   14: invokevirtual 118	com/google/android/gms/internal/zzfhb:zza	(I)V
    //   17: aload_0
    //   18: areturn
    //   19: astore_1
    //   20: aload_1
    //   21: aload_0
    //   22: invokevirtual 102	com/google/android/gms/internal/zzfie:zza	(Lcom/google/android/gms/internal/zzfjc;)Lcom/google/android/gms/internal/zzfie;
    //   25: athrow
    //   26: astore_0
    //   27: aload_0
    //   28: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	29	0	paramT	T
    //   0	29	1	paramArrayOfByte	byte[]
    //   0	29	2	paramZzfhm	zzfhm
    // Exception table:
    //   from	to	target	type
    //   12	17	19	com/google/android/gms/internal/zzfie
    //   0	12	26	com/google/android/gms/internal/zzfie
    //   20	26	26	com/google/android/gms/internal/zzfie
  }
  
  static Object zza(Method paramMethod, Object paramObject, Object... paramVarArgs)
  {
    try
    {
      paramMethod = paramMethod.invoke(paramObject, paramVarArgs);
      return paramMethod;
    }
    catch (InvocationTargetException paramMethod)
    {
      paramMethod = paramMethod.getCause();
      if ((paramMethod instanceof RuntimeException)) {
        throw ((RuntimeException)paramMethod);
      }
      if ((paramMethod instanceof Error)) {
        throw ((Error)paramMethod);
      }
      throw new RuntimeException("Unexpected exception thrown by generated accessor method.", paramMethod);
    }
    catch (IllegalAccessException paramMethod)
    {
      throw new RuntimeException("Couldn't use Java reflection to implement protocol message reflection.", paramMethod);
    }
  }
  
  protected static final <T extends zzfhu<T, ?>> boolean zza(T paramT, boolean paramBoolean)
  {
    int i = ((Byte)paramT.zza(zzg.zzc, null, null)).byteValue();
    if (i == 1) {
      return true;
    }
    if (i == 0) {
      return false;
    }
    return paramT.zza(zzg.zza, Boolean.FALSE, null) != null;
  }
  
  protected static zzfic zzt()
  {
    return zzfhy.zzd();
  }
  
  protected static <E> zzfid<E> zzu()
  {
    return zzfjo.zzd();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!((zzfhu)zza(zzg.zzi, null, null)).getClass().isInstance(paramObject)) {
      return false;
    }
    try
    {
      zzc localZzc = zzc.zza;
      paramObject = (zzfhu)paramObject;
      zza(zzg.zzb, localZzc, paramObject);
      zzb = localZzc.zza(zzb, zzb);
      return true;
    }
    catch (zzfhv paramObject) {}
    return false;
  }
  
  public int hashCode()
  {
    if (zza != 0) {
      return zza;
    }
    zze localZze = new zze();
    zza(zzg.zzb, localZze, this);
    zzb = localZze.zza(zzb, zzb);
    zza = zza;
    return zza;
  }
  
  public String toString()
  {
    return zzfjf.zza(this, super.toString());
  }
  
  public int zza()
  {
    if (zzc == -1) {
      zzc = zzfjn.zza().zza(getClass()).zza(this);
    }
    return zzc;
  }
  
  protected abstract Object zza(int paramInt, Object paramObject1, Object paramObject2);
  
  public void zza(zzfhg paramZzfhg)
    throws IOException
  {
    zzfjn.zza().zza(getClass()).zza(this, zzfhi.zza(paramZzfhg));
  }
  
  protected final boolean zza(int paramInt, zzfhb paramZzfhb)
    throws IOException
  {
    if ((paramInt & 0x7) == 4) {
      return false;
    }
    if (zzb == zzfko.zza()) {
      zzb = zzfko.zzb();
    }
    return zzb.zza(paramInt, paramZzfhb);
  }
  
  public final zzfjl<MessageType> zzr()
  {
    return (zzfjl)zza(zzg.zzj, null, null);
  }
  
  public final boolean zzs()
  {
    boolean bool2 = Boolean.TRUE.booleanValue();
    int i = ((Byte)zza(zzg.zzc, null, null)).byteValue();
    boolean bool1 = true;
    if (i == 1) {
      return true;
    }
    if (i == 0) {
      return false;
    }
    if (zza(zzg.zza, Boolean.FALSE, null) == null) {
      bool1 = false;
    }
    if (bool2)
    {
      i = zzg.zzd;
      zzfhu localZzfhu;
      if (bool1) {
        localZzfhu = this;
      } else {
        localZzfhu = null;
      }
      zza(i, localZzfhu, null);
    }
    return bool1;
  }
  
  public static class zza<MessageType extends zzfhu<MessageType, BuilderType>, BuilderType extends zza<MessageType, BuilderType>>
    extends zzfgk<MessageType, BuilderType>
  {
    protected MessageType zza;
    private final MessageType zzb;
    private boolean zzc;
    
    protected zza(MessageType paramMessageType)
    {
      zzb = paramMessageType;
      zza = ((zzfhu)paramMessageType.zza(zzfhu.zzg.zzg, null, null));
      zzc = false;
    }
    
    private static void zza(MessageType paramMessageType1, MessageType paramMessageType2)
    {
      zzfhu.zzf localZzf = zzfhu.zzf.zza;
      paramMessageType1.zza(zzfhu.zzg.zzb, localZzf, paramMessageType2);
      zzb = localZzf.zza(zzb, zzb);
    }
    
    private final BuilderType zzc(zzfhb paramZzfhb, zzfhm paramZzfhm)
      throws IOException
    {
      zzb();
      try
      {
        zza.zza(zzfhu.zzg.zze, paramZzfhb, paramZzfhm);
        return this;
      }
      catch (RuntimeException paramZzfhb)
      {
        if ((paramZzfhb.getCause() instanceof IOException)) {
          throw ((IOException)paramZzfhb.getCause());
        }
        throw paramZzfhb;
      }
    }
    
    public final BuilderType zza(MessageType paramMessageType)
    {
      zzb();
      zza(zza, paramMessageType);
      return this;
    }
    
    protected final void zzb()
    {
      if (zzc)
      {
        zzfhu localZzfhu = (zzfhu)zza.zza(zzfhu.zzg.zzg, null, null);
        zza(localZzfhu, zza);
        zza = localZzfhu;
        zzc = false;
      }
    }
    
    public final MessageType zzc()
    {
      if (zzc) {
        return zza;
      }
      zzfhu localZzfhu = zza;
      localZzfhu.zza(zzfhu.zzg.zzf, null, null);
      zzb.zzc();
      zzc = true;
      return zza;
    }
    
    public final MessageType zzd()
    {
      boolean bool = zzc;
      int j = 1;
      int i = 1;
      if (bool) {}
      Object localObject;
      for (;;)
      {
        localObject = zza;
        break;
        localObject = zza;
        ((zzfhu)localObject).zza(zzfhu.zzg.zzf, null, null);
        zzb.zzc();
        zzc = true;
      }
      zzfhu localZzfhu = (zzfhu)localObject;
      bool = Boolean.TRUE.booleanValue();
      int k = ((Byte)localZzfhu.zza(zzfhu.zzg.zzc, null, null)).byteValue();
      if (k != 1) {
        if (k == 0)
        {
          j = 0;
        }
        else
        {
          if (localZzfhu.zza(zzfhu.zzg.zza, Boolean.FALSE, null) == null) {
            i = 0;
          }
          j = i;
          if (bool)
          {
            j = zzfhu.zzg.zzd;
            if (i != 0) {
              localObject = localZzfhu;
            } else {
              localObject = null;
            }
            localZzfhu.zza(j, localObject, null);
            j = i;
          }
        }
      }
      if (j == 0) {
        throw new zzfkm(localZzfhu);
      }
      return localZzfhu;
    }
    
    public final boolean zzs()
    {
      return zzfhu.zza(zza, false);
    }
  }
  
  public static final class zzb<T extends zzfhu<T, ?>>
    extends zzfgm<T>
  {
    private T zza;
    
    public zzb(T paramT)
    {
      zza = paramT;
    }
  }
  
  static final class zzc
    implements zzfhu.zzh
  {
    static final zzc zza = new zzc();
    private static zzfhv zzb = new zzfhv();
    
    private zzc() {}
    
    public final double zza(boolean paramBoolean1, double paramDouble1, boolean paramBoolean2, double paramDouble2)
    {
      if ((paramBoolean1 == paramBoolean2) && (paramDouble1 == paramDouble2)) {
        return paramDouble1;
      }
      throw zzb;
    }
    
    public final int zza(boolean paramBoolean1, int paramInt1, boolean paramBoolean2, int paramInt2)
    {
      if ((paramBoolean1 == paramBoolean2) && (paramInt1 == paramInt2)) {
        return paramInt1;
      }
      throw zzb;
    }
    
    public final long zza(boolean paramBoolean1, long paramLong1, boolean paramBoolean2, long paramLong2)
    {
      if ((paramBoolean1 == paramBoolean2) && (paramLong1 == paramLong2)) {
        return paramLong1;
      }
      throw zzb;
    }
    
    public final zzfgs zza(boolean paramBoolean1, zzfgs paramZzfgs1, boolean paramBoolean2, zzfgs paramZzfgs2)
    {
      if ((paramBoolean1 == paramBoolean2) && (paramZzfgs1.equals(paramZzfgs2))) {
        return paramZzfgs1;
      }
      throw zzb;
    }
    
    public final zzfic zza(zzfic paramZzfic1, zzfic paramZzfic2)
    {
      if (!paramZzfic1.equals(paramZzfic2)) {
        throw zzb;
      }
      return paramZzfic1;
    }
    
    public final <T> zzfid<T> zza(zzfid<T> paramZzfid1, zzfid<T> paramZzfid2)
    {
      if (!paramZzfid1.equals(paramZzfid2)) {
        throw zzb;
      }
      return paramZzfid1;
    }
    
    public final <K, V> zzfiw<K, V> zza(zzfiw<K, V> paramZzfiw1, zzfiw<K, V> paramZzfiw2)
    {
      if (!paramZzfiw1.equals(paramZzfiw2)) {
        throw zzb;
      }
      return paramZzfiw1;
    }
    
    public final <T extends zzfjc> T zza(T paramT1, T paramT2)
    {
      if ((paramT1 == null) && (paramT2 == null)) {
        return null;
      }
      if ((paramT1 != null) && (paramT2 != null))
      {
        zzfhu localZzfhu = (zzfhu)paramT1;
        if ((localZzfhu != paramT2) && (((zzfhu)localZzfhu.zza(zzfhu.zzg.zzi, null, null)).getClass().isInstance(paramT2)))
        {
          paramT2 = (zzfhu)paramT2;
          localZzfhu.zza(zzfhu.zzg.zzb, this, paramT2);
          zzb = zza(zzb, zzb);
        }
        return paramT1;
      }
      throw zzb;
    }
    
    public final zzfko zza(zzfko paramZzfko1, zzfko paramZzfko2)
    {
      if (!paramZzfko1.equals(paramZzfko2)) {
        throw zzb;
      }
      return paramZzfko1;
    }
    
    public final Object zza(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      if ((paramBoolean) && (paramObject1.equals(paramObject2))) {
        return paramObject1;
      }
      throw zzb;
    }
    
    public final String zza(boolean paramBoolean1, String paramString1, boolean paramBoolean2, String paramString2)
    {
      if ((paramBoolean1 == paramBoolean2) && (paramString1.equals(paramString2))) {
        return paramString1;
      }
      throw zzb;
    }
    
    public final void zza(boolean paramBoolean)
    {
      if (paramBoolean) {
        throw zzb;
      }
    }
    
    public final boolean zza(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
    {
      if ((paramBoolean1 == paramBoolean3) && (paramBoolean2 == paramBoolean4)) {
        return paramBoolean2;
      }
      throw zzb;
    }
    
    public final Object zzb(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      if ((paramBoolean) && (paramObject1.equals(paramObject2))) {
        return paramObject1;
      }
      throw zzb;
    }
    
    public final Object zzc(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      if ((paramBoolean) && (paramObject1.equals(paramObject2))) {
        return paramObject1;
      }
      throw zzb;
    }
    
    public final Object zzd(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      if ((paramBoolean) && (paramObject1.equals(paramObject2))) {
        return paramObject1;
      }
      throw zzb;
    }
    
    public final Object zze(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      if ((paramBoolean) && (paramObject1.equals(paramObject2))) {
        return paramObject1;
      }
      throw zzb;
    }
    
    public final Object zzf(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      if ((paramBoolean) && (paramObject1.equals(paramObject2))) {
        return paramObject1;
      }
      throw zzb;
    }
    
    public final Object zzg(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      if (paramBoolean)
      {
        zzfhu localZzfhu = (zzfhu)paramObject1;
        paramObject2 = (zzfjc)paramObject2;
        int i = 1;
        if (localZzfhu != paramObject2) {
          if (!((zzfhu)localZzfhu.zza(zzfhu.zzg.zzi, null, null)).getClass().isInstance(paramObject2))
          {
            i = 0;
          }
          else
          {
            paramObject2 = (zzfhu)paramObject2;
            localZzfhu.zza(zzfhu.zzg.zzb, this, paramObject2);
            zzb = zza(zzb, zzb);
          }
        }
        if (i != 0) {
          return paramObject1;
        }
      }
      throw zzb;
    }
  }
  
  public static abstract class zzd<MessageType extends zzd<MessageType, BuilderType>, BuilderType>
    extends zzfhu<MessageType, BuilderType>
    implements zzfje
  {
    protected zzfhq<Object> zzd = zzfhq.zza();
    
    public zzd() {}
  }
  
  static final class zze
    implements zzfhu.zzh
  {
    int zza = 0;
    
    zze() {}
    
    public final double zza(boolean paramBoolean1, double paramDouble1, boolean paramBoolean2, double paramDouble2)
    {
      zza = (53 * zza + zzfhz.zza(Double.doubleToLongBits(paramDouble1)));
      return paramDouble1;
    }
    
    public final int zza(boolean paramBoolean1, int paramInt1, boolean paramBoolean2, int paramInt2)
    {
      zza = (53 * zza + paramInt1);
      return paramInt1;
    }
    
    public final long zza(boolean paramBoolean1, long paramLong1, boolean paramBoolean2, long paramLong2)
    {
      zza = (53 * zza + zzfhz.zza(paramLong1));
      return paramLong1;
    }
    
    public final zzfgs zza(boolean paramBoolean1, zzfgs paramZzfgs1, boolean paramBoolean2, zzfgs paramZzfgs2)
    {
      zza = (53 * zza + paramZzfgs1.hashCode());
      return paramZzfgs1;
    }
    
    public final zzfic zza(zzfic paramZzfic1, zzfic paramZzfic2)
    {
      zza = (53 * zza + paramZzfic1.hashCode());
      return paramZzfic1;
    }
    
    public final <T> zzfid<T> zza(zzfid<T> paramZzfid1, zzfid<T> paramZzfid2)
    {
      zza = (53 * zza + paramZzfid1.hashCode());
      return paramZzfid1;
    }
    
    public final <K, V> zzfiw<K, V> zza(zzfiw<K, V> paramZzfiw1, zzfiw<K, V> paramZzfiw2)
    {
      zza = (53 * zza + paramZzfiw1.hashCode());
      return paramZzfiw1;
    }
    
    public final <T extends zzfjc> T zza(T paramT1, T paramT2)
    {
      int i;
      if (paramT1 != null)
      {
        if ((paramT1 instanceof zzfhu))
        {
          paramT2 = (zzfhu)paramT1;
          if (zza == 0)
          {
            i = zza;
            zza = 0;
            paramT2.zza(zzfhu.zzg.zzb, this, paramT2);
            zzb = zza(zzb, zzb);
            zza = zza;
            zza = i;
          }
          i = zza;
        }
        else
        {
          i = paramT1.hashCode();
        }
      }
      else {
        i = 37;
      }
      zza = (53 * zza + i);
      return paramT1;
    }
    
    public final zzfko zza(zzfko paramZzfko1, zzfko paramZzfko2)
    {
      zza = (53 * zza + paramZzfko1.hashCode());
      return paramZzfko1;
    }
    
    public final Object zza(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      zza = (53 * zza + zzfhz.zza(((Boolean)paramObject1).booleanValue()));
      return paramObject1;
    }
    
    public final String zza(boolean paramBoolean1, String paramString1, boolean paramBoolean2, String paramString2)
    {
      zza = (53 * zza + paramString1.hashCode());
      return paramString1;
    }
    
    public final void zza(boolean paramBoolean)
    {
      if (paramBoolean) {
        throw new IllegalStateException();
      }
    }
    
    public final boolean zza(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
    {
      zza = (53 * zza + zzfhz.zza(paramBoolean2));
      return paramBoolean2;
    }
    
    public final Object zzb(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      zza = (53 * zza + ((Integer)paramObject1).intValue());
      return paramObject1;
    }
    
    public final Object zzc(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      zza = (53 * zza + zzfhz.zza(Double.doubleToLongBits(((Double)paramObject1).doubleValue())));
      return paramObject1;
    }
    
    public final Object zzd(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      zza = (53 * zza + zzfhz.zza(((Long)paramObject1).longValue()));
      return paramObject1;
    }
    
    public final Object zze(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      zza = (53 * zza + paramObject1.hashCode());
      return paramObject1;
    }
    
    public final Object zzf(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      zza = (53 * zza + paramObject1.hashCode());
      return paramObject1;
    }
    
    public final Object zzg(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      return zza((zzfjc)paramObject1, (zzfjc)paramObject2);
    }
  }
  
  public static final class zzf
    implements zzfhu.zzh
  {
    public static final zzf zza = new zzf();
    
    private zzf() {}
    
    public final double zza(boolean paramBoolean1, double paramDouble1, boolean paramBoolean2, double paramDouble2)
    {
      if (paramBoolean2) {
        return paramDouble2;
      }
      return paramDouble1;
    }
    
    public final int zza(boolean paramBoolean1, int paramInt1, boolean paramBoolean2, int paramInt2)
    {
      if (paramBoolean2) {
        return paramInt2;
      }
      return paramInt1;
    }
    
    public final long zza(boolean paramBoolean1, long paramLong1, boolean paramBoolean2, long paramLong2)
    {
      if (paramBoolean2) {
        return paramLong2;
      }
      return paramLong1;
    }
    
    public final zzfgs zza(boolean paramBoolean1, zzfgs paramZzfgs1, boolean paramBoolean2, zzfgs paramZzfgs2)
    {
      if (paramBoolean2) {
        return paramZzfgs2;
      }
      return paramZzfgs1;
    }
    
    public final zzfic zza(zzfic paramZzfic1, zzfic paramZzfic2)
    {
      int i = paramZzfic1.size();
      int j = paramZzfic2.size();
      zzfic localZzfic = paramZzfic1;
      if (i > 0)
      {
        localZzfic = paramZzfic1;
        if (j > 0)
        {
          localZzfic = paramZzfic1;
          if (!paramZzfic1.zza()) {
            localZzfic = paramZzfic1.zza(j + i);
          }
          localZzfic.addAll(paramZzfic2);
        }
      }
      if (i > 0) {
        return localZzfic;
      }
      return paramZzfic2;
    }
    
    public final <T> zzfid<T> zza(zzfid<T> paramZzfid1, zzfid<T> paramZzfid2)
    {
      int i = paramZzfid1.size();
      int j = paramZzfid2.size();
      Object localObject = paramZzfid1;
      if (i > 0)
      {
        localObject = paramZzfid1;
        if (j > 0)
        {
          localObject = paramZzfid1;
          if (!paramZzfid1.zza()) {
            localObject = paramZzfid1.zzd(j + i);
          }
          ((zzfid)localObject).addAll(paramZzfid2);
        }
      }
      if (i > 0) {
        return localObject;
      }
      return paramZzfid2;
    }
    
    public final <K, V> zzfiw<K, V> zza(zzfiw<K, V> paramZzfiw1, zzfiw<K, V> paramZzfiw2)
    {
      Object localObject = paramZzfiw1;
      if (!paramZzfiw2.isEmpty())
      {
        localObject = paramZzfiw1;
        if (!paramZzfiw1.zzd()) {
          localObject = paramZzfiw1.zzb();
        }
        ((zzfiw)localObject).zza(paramZzfiw2);
      }
      return localObject;
    }
    
    public final <T extends zzfjc> T zza(T paramT1, T paramT2)
    {
      if ((paramT1 != null) && (paramT2 != null)) {
        return paramT1.zzv().zza(paramT2).zzf();
      }
      if (paramT1 != null) {
        return paramT1;
      }
      return paramT2;
    }
    
    public final zzfko zza(zzfko paramZzfko1, zzfko paramZzfko2)
    {
      if (paramZzfko2 == zzfko.zza()) {
        return paramZzfko1;
      }
      return zzfko.zza(paramZzfko1, paramZzfko2);
    }
    
    public final Object zza(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      return paramObject2;
    }
    
    public final String zza(boolean paramBoolean1, String paramString1, boolean paramBoolean2, String paramString2)
    {
      if (paramBoolean2) {
        return paramString2;
      }
      return paramString1;
    }
    
    public final void zza(boolean paramBoolean) {}
    
    public final boolean zza(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
    {
      if (paramBoolean3) {
        return paramBoolean4;
      }
      return paramBoolean2;
    }
    
    public final Object zzb(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      return paramObject2;
    }
    
    public final Object zzc(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      return paramObject2;
    }
    
    public final Object zzd(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      return paramObject2;
    }
    
    public final Object zze(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      return paramObject2;
    }
    
    public final Object zzf(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      return paramObject2;
    }
    
    public final Object zzg(boolean paramBoolean, Object paramObject1, Object paramObject2)
    {
      if (paramBoolean) {
        return zza((zzfjc)paramObject1, (zzfjc)paramObject2);
      }
      return paramObject2;
    }
  }
  
  public static enum zzg
  {
    public static int[] values$50KLMJ33DTMIUPRFDTJMOP9FE1P6UT3FC9QMCBQ7CLN6ASJ1EHIM8JB5EDPM2PR59HKN8P949LIN8Q3FCHA6UIBEEPNMMP9R0()
    {
      return (int[])zzn.clone();
    }
  }
  
  public static abstract interface zzh
  {
    public abstract double zza(boolean paramBoolean1, double paramDouble1, boolean paramBoolean2, double paramDouble2);
    
    public abstract int zza(boolean paramBoolean1, int paramInt1, boolean paramBoolean2, int paramInt2);
    
    public abstract long zza(boolean paramBoolean1, long paramLong1, boolean paramBoolean2, long paramLong2);
    
    public abstract zzfgs zza(boolean paramBoolean1, zzfgs paramZzfgs1, boolean paramBoolean2, zzfgs paramZzfgs2);
    
    public abstract zzfic zza(zzfic paramZzfic1, zzfic paramZzfic2);
    
    public abstract <T> zzfid<T> zza(zzfid<T> paramZzfid1, zzfid<T> paramZzfid2);
    
    public abstract <K, V> zzfiw<K, V> zza(zzfiw<K, V> paramZzfiw1, zzfiw<K, V> paramZzfiw2);
    
    public abstract <T extends zzfjc> T zza(T paramT1, T paramT2);
    
    public abstract zzfko zza(zzfko paramZzfko1, zzfko paramZzfko2);
    
    public abstract Object zza(boolean paramBoolean, Object paramObject1, Object paramObject2);
    
    public abstract String zza(boolean paramBoolean1, String paramString1, boolean paramBoolean2, String paramString2);
    
    public abstract void zza(boolean paramBoolean);
    
    public abstract boolean zza(boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4);
    
    public abstract Object zzb(boolean paramBoolean, Object paramObject1, Object paramObject2);
    
    public abstract Object zzc(boolean paramBoolean, Object paramObject1, Object paramObject2);
    
    public abstract Object zzd(boolean paramBoolean, Object paramObject1, Object paramObject2);
    
    public abstract Object zze(boolean paramBoolean, Object paramObject1, Object paramObject2);
    
    public abstract Object zzf(boolean paramBoolean, Object paramObject1, Object paramObject2);
    
    public abstract Object zzg(boolean paramBoolean, Object paramObject1, Object paramObject2);
  }
}

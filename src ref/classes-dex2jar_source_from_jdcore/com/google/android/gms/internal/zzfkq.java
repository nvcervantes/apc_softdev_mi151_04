package com.google.android.gms.internal;

import java.lang.reflect.Field;
import java.nio.Buffer;
import java.nio.ByteOrder;
import java.security.AccessController;
import java.util.logging.Level;
import java.util.logging.Logger;
import sun.misc.Unsafe;

final class zzfkq
{
  private static final Logger zza = Logger.getLogger(zzfkq.class.getName());
  private static final Unsafe zzb = zzd();
  private static final Class<?> zzc = zzfgo.zzb();
  private static final boolean zzd = zzc(Long.TYPE);
  private static final boolean zze = zzc(Integer.TYPE);
  private static final zzd zzf;
  private static final boolean zzg;
  private static final boolean zzh;
  private static final long zzi;
  private static final long zzj;
  private static final long zzk;
  private static final long zzl;
  private static final long zzm;
  private static final long zzn;
  private static final long zzo;
  private static final long zzp;
  private static final long zzq;
  private static final long zzr;
  private static final long zzs;
  private static final long zzt;
  private static final long zzu;
  private static final long zzv;
  private static final boolean zzw;
  
  static
  {
    Unsafe localUnsafe = zzb;
    Object localObject = null;
    if (localUnsafe != null) {
      if (zzfgo.zza())
      {
        if (zzd) {
          localObject = new zzb(zzb);
        } else if (zze) {
          localObject = new zza(zzb);
        }
      }
      else {
        localObject = new zzc(zzb);
      }
    }
    zzf = (zzd)localObject;
    zzg = zzf();
    zzh = zze();
    zzi = zza([B.class);
    zzj = zza([Z.class);
    zzk = zzb([Z.class);
    zzl = zza([I.class);
    zzm = zzb([I.class);
    zzn = zza([J.class);
    zzo = zzb([J.class);
    zzp = zza([F.class);
    zzq = zzb([F.class);
    zzr = zza([D.class);
    zzs = zzb([D.class);
    zzt = zza([Ljava.lang.Object.class);
    zzu = zzb([Ljava.lang.Object.class);
    if (zzfgo.zza())
    {
      localObject = zza(Buffer.class, "effectiveDirectAddress");
      if (localObject != null) {}
    }
    else
    {
      localObject = zza(Buffer.class, "address");
    }
    long l;
    if ((localObject != null) && (zzf != null)) {
      l = zzfzza.objectFieldOffset((Field)localObject);
    } else {
      l = -1L;
    }
    zzv = l;
    boolean bool;
    if (ByteOrder.nativeOrder() == ByteOrder.BIG_ENDIAN) {
      bool = true;
    } else {
      bool = false;
    }
    zzw = bool;
  }
  
  private zzfkq() {}
  
  static byte zza(byte[] paramArrayOfByte, long paramLong)
  {
    return zzf.zza(paramArrayOfByte, zzi + paramLong);
  }
  
  private static int zza(Class<?> paramClass)
  {
    if (zzh) {
      return zzfzza.arrayBaseOffset(paramClass);
    }
    return -1;
  }
  
  static int zza(Object paramObject, long paramLong)
  {
    return zzf.zzb(paramObject, paramLong);
  }
  
  private static Field zza(Class<?> paramClass, String paramString)
  {
    try
    {
      paramClass = paramClass.getDeclaredField(paramString);
      paramClass.setAccessible(true);
      return paramClass;
    }
    catch (Throwable paramClass)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static void zza(Object paramObject, long paramLong, int paramInt)
  {
    zzfzza.putInt(paramObject, paramLong, paramInt);
  }
  
  static void zza(byte[] paramArrayOfByte, long paramLong, byte paramByte)
  {
    zzf.zza(paramArrayOfByte, zzi + paramLong, paramByte);
  }
  
  static boolean zza()
  {
    return zzh;
  }
  
  private static int zzb(Class<?> paramClass)
  {
    if (zzh) {
      return zzfzza.arrayIndexScale(paramClass);
    }
    return -1;
  }
  
  static boolean zzb()
  {
    return zzg;
  }
  
  private static void zzc(Object paramObject, long paramLong, byte paramByte)
  {
    long l = paramLong & 0xFFFFFFFFFFFFFFFC;
    int i = zza(paramObject, l);
    int j = (((int)paramLong ^ 0xFFFFFFFF) & 0x3) << 3;
    zza(paramObject, l, (0xFF & paramByte) << j | i & (255 << j ^ 0xFFFFFFFF));
  }
  
  private static boolean zzc(Class<?> paramClass)
  {
    if (!zzfgo.zza()) {
      return false;
    }
    try
    {
      Class localClass = zzc;
      localClass.getMethod("peekLong", new Class[] { paramClass, Boolean.TYPE });
      localClass.getMethod("pokeLong", new Class[] { paramClass, Long.TYPE, Boolean.TYPE });
      localClass.getMethod("pokeInt", new Class[] { paramClass, Integer.TYPE, Boolean.TYPE });
      localClass.getMethod("peekInt", new Class[] { paramClass, Boolean.TYPE });
      localClass.getMethod("pokeByte", new Class[] { paramClass, Byte.TYPE });
      localClass.getMethod("peekByte", new Class[] { paramClass });
      localClass.getMethod("pokeByteArray", new Class[] { paramClass, [B.class, Integer.TYPE, Integer.TYPE });
      localClass.getMethod("peekByteArray", new Class[] { paramClass, [B.class, Integer.TYPE, Integer.TYPE });
      return true;
    }
    catch (Throwable paramClass) {}
    return false;
  }
  
  private static byte zzd(Object paramObject, long paramLong)
  {
    return (byte)(zza(paramObject, paramLong & 0xFFFFFFFFFFFFFFFC) >>> (int)(((paramLong ^ 0xFFFFFFFFFFFFFFFF) & 0x3) << 3));
  }
  
  private static Unsafe zzd()
  {
    try
    {
      Unsafe localUnsafe = (Unsafe)AccessController.doPrivileged(new zzfkr());
      return localUnsafe;
    }
    catch (Throwable localThrowable)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static void zzd(Object paramObject, long paramLong, byte paramByte)
  {
    long l = paramLong & 0xFFFFFFFFFFFFFFFC;
    int i = zza(paramObject, l);
    int j = ((int)paramLong & 0x3) << 3;
    zza(paramObject, l, (0xFF & paramByte) << j | i & (255 << j ^ 0xFFFFFFFF));
  }
  
  private static byte zze(Object paramObject, long paramLong)
  {
    return (byte)(zza(paramObject, paramLong & 0xFFFFFFFFFFFFFFFC) >>> (int)((paramLong & 0x3) << 3));
  }
  
  private static boolean zze()
  {
    if (zzb == null) {
      return false;
    }
    try
    {
      localObject = zzb.getClass();
      ((Class)localObject).getMethod("objectFieldOffset", new Class[] { Field.class });
      ((Class)localObject).getMethod("arrayBaseOffset", new Class[] { Class.class });
      ((Class)localObject).getMethod("arrayIndexScale", new Class[] { Class.class });
      ((Class)localObject).getMethod("getInt", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putInt", new Class[] { Object.class, Long.TYPE, Integer.TYPE });
      ((Class)localObject).getMethod("getLong", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putLong", new Class[] { Object.class, Long.TYPE, Long.TYPE });
      ((Class)localObject).getMethod("getObject", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putObject", new Class[] { Object.class, Long.TYPE, Object.class });
      if (zzfgo.zza()) {
        return true;
      }
      ((Class)localObject).getMethod("getByte", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putByte", new Class[] { Object.class, Long.TYPE, Byte.TYPE });
      ((Class)localObject).getMethod("getBoolean", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putBoolean", new Class[] { Object.class, Long.TYPE, Boolean.TYPE });
      ((Class)localObject).getMethod("getFloat", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putFloat", new Class[] { Object.class, Long.TYPE, Float.TYPE });
      ((Class)localObject).getMethod("getDouble", new Class[] { Object.class, Long.TYPE });
      ((Class)localObject).getMethod("putDouble", new Class[] { Object.class, Long.TYPE, Double.TYPE });
      return true;
    }
    catch (Throwable localThrowable)
    {
      Object localObject = zza;
      Level localLevel = Level.WARNING;
      String str = String.valueOf(localThrowable);
      StringBuilder localStringBuilder = new StringBuilder(71 + String.valueOf(str).length());
      localStringBuilder.append("platform method missing - proto runtime falling back to safer methods: ");
      localStringBuilder.append(str);
      ((Logger)localObject).logp(localLevel, "com.google.protobuf.UnsafeUtil", "supportsUnsafeArrayOperations", localStringBuilder.toString());
    }
    return false;
  }
  
  private static boolean zzf()
  {
    if (zzb == null) {
      return false;
    }
    try
    {
      localObject = zzb.getClass();
      ((Class)localObject).getMethod("objectFieldOffset", new Class[] { Field.class });
      ((Class)localObject).getMethod("getLong", new Class[] { Object.class, Long.TYPE });
      if (zzfgo.zza()) {
        return true;
      }
      ((Class)localObject).getMethod("getByte", new Class[] { Long.TYPE });
      ((Class)localObject).getMethod("putByte", new Class[] { Long.TYPE, Byte.TYPE });
      ((Class)localObject).getMethod("getInt", new Class[] { Long.TYPE });
      ((Class)localObject).getMethod("putInt", new Class[] { Long.TYPE, Integer.TYPE });
      ((Class)localObject).getMethod("getLong", new Class[] { Long.TYPE });
      ((Class)localObject).getMethod("putLong", new Class[] { Long.TYPE, Long.TYPE });
      ((Class)localObject).getMethod("copyMemory", new Class[] { Long.TYPE, Long.TYPE, Long.TYPE });
      ((Class)localObject).getMethod("copyMemory", new Class[] { Object.class, Long.TYPE, Object.class, Long.TYPE, Long.TYPE });
      return true;
    }
    catch (Throwable localThrowable)
    {
      Object localObject = zza;
      Level localLevel = Level.WARNING;
      String str = String.valueOf(localThrowable);
      StringBuilder localStringBuilder = new StringBuilder(71 + String.valueOf(str).length());
      localStringBuilder.append("platform method missing - proto runtime falling back to safer methods: ");
      localStringBuilder.append(str);
      ((Logger)localObject).logp(localLevel, "com.google.protobuf.UnsafeUtil", "supportsUnsafeByteBufferOperations", localStringBuilder.toString());
    }
    return false;
  }
  
  static final class zza
    extends zzfkq.zzd
  {
    zza(Unsafe paramUnsafe)
    {
      super();
    }
    
    public final byte zza(Object paramObject, long paramLong)
    {
      if (zzfkq.zzc()) {
        return zzfkq.zzb(paramObject, paramLong);
      }
      return zzfkq.zzc(paramObject, paramLong);
    }
    
    public final void zza(Object paramObject, long paramLong, byte paramByte)
    {
      if (zzfkq.zzc())
      {
        zzfkq.zza(paramObject, paramLong, paramByte);
        return;
      }
      zzfkq.zzb(paramObject, paramLong, paramByte);
    }
  }
  
  static final class zzb
    extends zzfkq.zzd
  {
    zzb(Unsafe paramUnsafe)
    {
      super();
    }
    
    public final byte zza(Object paramObject, long paramLong)
    {
      if (zzfkq.zzc()) {
        return zzfkq.zzb(paramObject, paramLong);
      }
      return zzfkq.zzc(paramObject, paramLong);
    }
    
    public final void zza(Object paramObject, long paramLong, byte paramByte)
    {
      if (zzfkq.zzc())
      {
        zzfkq.zza(paramObject, paramLong, paramByte);
        return;
      }
      zzfkq.zzb(paramObject, paramLong, paramByte);
    }
  }
  
  static final class zzc
    extends zzfkq.zzd
  {
    zzc(Unsafe paramUnsafe)
    {
      super();
    }
    
    public final byte zza(Object paramObject, long paramLong)
    {
      return zza.getByte(paramObject, paramLong);
    }
    
    public final void zza(Object paramObject, long paramLong, byte paramByte)
    {
      zza.putByte(paramObject, paramLong, paramByte);
    }
  }
  
  static abstract class zzd
  {
    Unsafe zza;
    
    zzd(Unsafe paramUnsafe)
    {
      zza = paramUnsafe;
    }
    
    public abstract byte zza(Object paramObject, long paramLong);
    
    public abstract void zza(Object paramObject, long paramLong, byte paramByte);
    
    public final int zzb(Object paramObject, long paramLong)
    {
      return zza.getInt(paramObject, paramLong);
    }
  }
}

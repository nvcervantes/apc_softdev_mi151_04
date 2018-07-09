package com.google.android.gms.common.internal;

import java.util.ArrayList;

@Hide
public abstract class zzi<TListener>
{
  private TListener zza;
  private boolean zzb;
  
  public zzi(TListener paramTListener)
  {
    Object localObject;
    zza = localObject;
    zzb = false;
  }
  
  protected abstract void zza(TListener paramTListener);
  
  protected abstract void zzb();
  
  /* Error */
  public final void zzc()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 22	com/google/android/gms/common/internal/zzi:zza	Ljava/lang/Object;
    //   6: astore_1
    //   7: aload_0
    //   8: getfield 24	com/google/android/gms/common/internal/zzi:zzb	Z
    //   11: ifeq +56 -> 67
    //   14: aload_0
    //   15: invokestatic 36	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   18: astore_2
    //   19: new 38	java/lang/StringBuilder
    //   22: dup
    //   23: bipush 47
    //   25: aload_2
    //   26: invokestatic 36	java/lang/String:valueOf	(Ljava/lang/Object;)Ljava/lang/String;
    //   29: invokevirtual 42	java/lang/String:length	()I
    //   32: iadd
    //   33: invokespecial 45	java/lang/StringBuilder:<init>	(I)V
    //   36: astore_3
    //   37: aload_3
    //   38: ldc 47
    //   40: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   43: pop
    //   44: aload_3
    //   45: aload_2
    //   46: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   49: pop
    //   50: aload_3
    //   51: ldc 53
    //   53: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: pop
    //   57: ldc 55
    //   59: aload_3
    //   60: invokevirtual 59	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   63: invokestatic 65	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   66: pop
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_1
    //   70: ifnull +18 -> 88
    //   73: aload_0
    //   74: aload_1
    //   75: invokevirtual 67	com/google/android/gms/common/internal/zzi:zza	(Ljava/lang/Object;)V
    //   78: goto +14 -> 92
    //   81: astore_1
    //   82: aload_0
    //   83: invokevirtual 69	com/google/android/gms/common/internal/zzi:zzb	()V
    //   86: aload_1
    //   87: athrow
    //   88: aload_0
    //   89: invokevirtual 69	com/google/android/gms/common/internal/zzi:zzb	()V
    //   92: aload_0
    //   93: monitorenter
    //   94: aload_0
    //   95: iconst_1
    //   96: putfield 24	com/google/android/gms/common/internal/zzi:zzb	Z
    //   99: aload_0
    //   100: monitorexit
    //   101: aload_0
    //   102: invokevirtual 72	com/google/android/gms/common/internal/zzi:zzd	()V
    //   105: return
    //   106: astore_1
    //   107: aload_0
    //   108: monitorexit
    //   109: aload_1
    //   110: athrow
    //   111: astore_1
    //   112: aload_0
    //   113: monitorexit
    //   114: aload_1
    //   115: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	116	0	this	zzi
    //   6	69	1	localObject1	Object
    //   81	6	1	localRuntimeException	RuntimeException
    //   106	4	1	localObject2	Object
    //   111	4	1	localObject3	Object
    //   18	28	2	str	String
    //   36	24	3	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   73	78	81	java/lang/RuntimeException
    //   94	101	106	finally
    //   107	109	106	finally
    //   2	67	111	finally
    //   67	69	111	finally
    //   112	114	111	finally
  }
  
  public final void zzd()
  {
    zze();
    synchronized (zzd.zzf(zzc))
    {
      zzd.zzf(zzc).remove(this);
      return;
    }
  }
  
  public final void zze()
  {
    try
    {
      zza = null;
      return;
    }
    finally {}
  }
}

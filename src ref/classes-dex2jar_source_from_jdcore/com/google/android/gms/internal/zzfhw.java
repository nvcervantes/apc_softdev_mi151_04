package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfhw
  extends zzfhu<zzfhw, zza>
  implements zzfje
{
  private static final zzfhw zze;
  private static volatile zzfjl<zzfhw> zzf;
  private int zzd;
  
  static
  {
    zzfhw localZzfhw = new zzfhw();
    zze = localZzfhw;
    localZzfhw.zza(zzfhu.zzg.zzf, null, null);
    zzb.zzc();
  }
  
  private zzfhw() {}
  
  private final void zza(int paramInt)
  {
    zzd = paramInt;
  }
  
  public static zza zzc()
  {
    return (zza)zze.zza(zzfhu.zzg.zzh, null, null);
  }
  
  public static zzfhw zzd()
  {
    return zze;
  }
  
  public final int zza()
  {
    int i = zzc;
    if (i != -1) {
      return i;
    }
    int j = zzd;
    i = 0;
    if (j != 0) {
      i = 0 + zzfhg.zze(1, zzd);
    }
    i += zzb.zze();
    zzc = i;
    return i;
  }
  
  /* Error */
  protected final Object zza(int paramInt, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: getstatic 75	com/google/android/gms/internal/zzfhx:zza	[I
    //   3: astore 7
    //   5: iconst_1
    //   6: istore 6
    //   8: aload 7
    //   10: iload_1
    //   11: iconst_1
    //   12: isub
    //   13: iaload
    //   14: tableswitch	default:+54->68, 1:+348->362, 2:+344->358, 3:+342->356, 4:+333->347, 5:+268->282, 6:+113->127, 7:+264->278, 8:+69->83, 9:+64->78, 10:+62->76
    //   68: new 77	java/lang/UnsupportedOperationException
    //   71: dup
    //   72: invokespecial 78	java/lang/UnsupportedOperationException:<init>	()V
    //   75: athrow
    //   76: aconst_null
    //   77: areturn
    //   78: iconst_1
    //   79: invokestatic 84	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
    //   82: areturn
    //   83: getstatic 86	com/google/android/gms/internal/zzfhw:zzf	Lcom/google/android/gms/internal/zzfjl;
    //   86: ifnonnull +37 -> 123
    //   89: ldc 2
    //   91: monitorenter
    //   92: getstatic 86	com/google/android/gms/internal/zzfhw:zzf	Lcom/google/android/gms/internal/zzfjl;
    //   95: ifnonnull +16 -> 111
    //   98: new 88	com/google/android/gms/internal/zzfhu$zzb
    //   101: dup
    //   102: getstatic 24	com/google/android/gms/internal/zzfhw:zze	Lcom/google/android/gms/internal/zzfhw;
    //   105: invokespecial 91	com/google/android/gms/internal/zzfhu$zzb:<init>	(Lcom/google/android/gms/internal/zzfhu;)V
    //   108: putstatic 86	com/google/android/gms/internal/zzfhw:zzf	Lcom/google/android/gms/internal/zzfjl;
    //   111: ldc 2
    //   113: monitorexit
    //   114: goto +9 -> 123
    //   117: astore_2
    //   118: ldc 2
    //   120: monitorexit
    //   121: aload_2
    //   122: athrow
    //   123: getstatic 86	com/google/android/gms/internal/zzfhw:zzf	Lcom/google/android/gms/internal/zzfjl;
    //   126: areturn
    //   127: aload_2
    //   128: checkcast 93	com/google/android/gms/internal/zzfhb
    //   131: astore_2
    //   132: aload_3
    //   133: checkcast 95	com/google/android/gms/internal/zzfhm
    //   136: ifnonnull +11 -> 147
    //   139: new 97	java/lang/NullPointerException
    //   142: dup
    //   143: invokespecial 98	java/lang/NullPointerException:<init>	()V
    //   146: athrow
    //   147: iconst_0
    //   148: istore_1
    //   149: iload_1
    //   150: ifne +128 -> 278
    //   153: aload_2
    //   154: invokevirtual 100	com/google/android/gms/internal/zzfhb:zza	()I
    //   157: istore 4
    //   159: iload 4
    //   161: ifeq +68 -> 229
    //   164: iload 4
    //   166: bipush 8
    //   168: if_icmpeq +50 -> 218
    //   171: iload 4
    //   173: bipush 7
    //   175: iand
    //   176: iconst_4
    //   177: if_icmpne +9 -> 186
    //   180: iconst_0
    //   181: istore 5
    //   183: goto +187 -> 370
    //   186: aload_0
    //   187: getfield 35	com/google/android/gms/internal/zzfhu:zzb	Lcom/google/android/gms/internal/zzfko;
    //   190: invokestatic 103	com/google/android/gms/internal/zzfko:zza	()Lcom/google/android/gms/internal/zzfko;
    //   193: if_acmpne +10 -> 203
    //   196: aload_0
    //   197: invokestatic 105	com/google/android/gms/internal/zzfko:zzb	()Lcom/google/android/gms/internal/zzfko;
    //   200: putfield 35	com/google/android/gms/internal/zzfhu:zzb	Lcom/google/android/gms/internal/zzfko;
    //   203: aload_0
    //   204: getfield 35	com/google/android/gms/internal/zzfhu:zzb	Lcom/google/android/gms/internal/zzfko;
    //   207: iload 4
    //   209: aload_2
    //   210: invokevirtual 108	com/google/android/gms/internal/zzfko:zza	(ILcom/google/android/gms/internal/zzfhb;)Z
    //   213: istore 5
    //   215: goto +155 -> 370
    //   218: aload_0
    //   219: aload_2
    //   220: invokevirtual 110	com/google/android/gms/internal/zzfhb:zzf	()I
    //   223: putfield 45	com/google/android/gms/internal/zzfhw:zzd	I
    //   226: goto -77 -> 149
    //   229: iconst_1
    //   230: istore_1
    //   231: goto -82 -> 149
    //   234: astore_2
    //   235: goto +41 -> 276
    //   238: astore_2
    //   239: new 112	java/lang/RuntimeException
    //   242: dup
    //   243: new 68	com/google/android/gms/internal/zzfie
    //   246: dup
    //   247: aload_2
    //   248: invokevirtual 116	java/io/IOException:getMessage	()Ljava/lang/String;
    //   251: invokespecial 119	com/google/android/gms/internal/zzfie:<init>	(Ljava/lang/String;)V
    //   254: aload_0
    //   255: invokevirtual 122	com/google/android/gms/internal/zzfie:zza	(Lcom/google/android/gms/internal/zzfjc;)Lcom/google/android/gms/internal/zzfie;
    //   258: invokespecial 125	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   261: athrow
    //   262: astore_2
    //   263: new 112	java/lang/RuntimeException
    //   266: dup
    //   267: aload_2
    //   268: aload_0
    //   269: invokevirtual 122	com/google/android/gms/internal/zzfie:zza	(Lcom/google/android/gms/internal/zzfjc;)Lcom/google/android/gms/internal/zzfie;
    //   272: invokespecial 125	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   275: athrow
    //   276: aload_2
    //   277: athrow
    //   278: getstatic 24	com/google/android/gms/internal/zzfhw:zze	Lcom/google/android/gms/internal/zzfhw;
    //   281: areturn
    //   282: aload_2
    //   283: checkcast 127	com/google/android/gms/internal/zzfhu$zzh
    //   286: astore_2
    //   287: aload_3
    //   288: checkcast 2	com/google/android/gms/internal/zzfhw
    //   291: astore_3
    //   292: aload_0
    //   293: getfield 45	com/google/android/gms/internal/zzfhw:zzd	I
    //   296: ifeq +9 -> 305
    //   299: iconst_1
    //   300: istore 5
    //   302: goto +6 -> 308
    //   305: iconst_0
    //   306: istore 5
    //   308: aload_0
    //   309: getfield 45	com/google/android/gms/internal/zzfhw:zzd	I
    //   312: istore_1
    //   313: aload_3
    //   314: getfield 45	com/google/android/gms/internal/zzfhw:zzd	I
    //   317: ifeq +6 -> 323
    //   320: goto +6 -> 326
    //   323: iconst_0
    //   324: istore 6
    //   326: aload_0
    //   327: aload_2
    //   328: iload 5
    //   330: iload_1
    //   331: iload 6
    //   333: aload_3
    //   334: getfield 45	com/google/android/gms/internal/zzfhw:zzd	I
    //   337: invokeinterface 130 5 0
    //   342: putfield 45	com/google/android/gms/internal/zzfhw:zzd	I
    //   345: aload_0
    //   346: areturn
    //   347: new 9	com/google/android/gms/internal/zzfhw$zza
    //   350: dup
    //   351: aconst_null
    //   352: invokespecial 133	com/google/android/gms/internal/zzfhw$zza:<init>	(Lcom/google/android/gms/internal/zzfhx;)V
    //   355: areturn
    //   356: aconst_null
    //   357: areturn
    //   358: getstatic 24	com/google/android/gms/internal/zzfhw:zze	Lcom/google/android/gms/internal/zzfhw;
    //   361: areturn
    //   362: new 2	com/google/android/gms/internal/zzfhw
    //   365: dup
    //   366: invokespecial 22	com/google/android/gms/internal/zzfhw:<init>	()V
    //   369: areturn
    //   370: iload 5
    //   372: ifne -223 -> 149
    //   375: goto -146 -> 229
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	378	0	this	zzfhw
    //   0	378	1	paramInt	int
    //   0	378	2	paramObject1	Object
    //   0	378	3	paramObject2	Object
    //   157	51	4	i	int
    //   181	190	5	bool1	boolean
    //   6	326	6	bool2	boolean
    //   3	6	7	arrayOfInt	int[]
    // Exception table:
    //   from	to	target	type
    //   92	111	117	finally
    //   111	114	117	finally
    //   118	121	117	finally
    //   153	159	234	finally
    //   186	203	234	finally
    //   203	215	234	finally
    //   218	226	234	finally
    //   239	262	234	finally
    //   263	276	234	finally
    //   153	159	238	java/io/IOException
    //   186	203	238	java/io/IOException
    //   203	215	238	java/io/IOException
    //   218	226	238	java/io/IOException
    //   153	159	262	com/google/android/gms/internal/zzfie
    //   186	203	262	com/google/android/gms/internal/zzfie
    //   203	215	262	com/google/android/gms/internal/zzfie
    //   218	226	262	com/google/android/gms/internal/zzfie
  }
  
  public final void zza(zzfhg paramZzfhg)
    throws IOException
  {
    if (zzd != 0) {
      paramZzfhg.zzb(1, zzd);
    }
    zzb.zza(paramZzfhg);
  }
  
  public final int zzb()
  {
    return zzd;
  }
  
  public static final class zza
    extends zzfhu.zza<zzfhw, zza>
    implements zzfje
  {
    private zza()
    {
      super();
    }
    
    public final zza zza(int paramInt)
    {
      zzb();
      zzfhw.zza((zzfhw)zza, paramInt);
      return this;
    }
  }
}

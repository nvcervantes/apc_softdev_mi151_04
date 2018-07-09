package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfgp
  extends zzfhu<zzfgp, zza>
  implements zzfje
{
  private static final zzfgp zzf;
  private static volatile zzfjl<zzfgp> zzg;
  private String zzd = "";
  private zzfgs zze = zzfgs.zza;
  
  static
  {
    zzfgp localZzfgp = new zzfgp();
    zzf = localZzfgp;
    localZzfgp.zza(zzfhu.zzg.zzf, null, null);
    zzb.zzc();
  }
  
  private zzfgp() {}
  
  public static zzfgp zzb()
  {
    return zzf;
  }
  
  public final int zza()
  {
    int i = zzc;
    if (i != -1) {
      return i;
    }
    boolean bool = zzd.isEmpty();
    i = 0;
    if (!bool) {
      i = 0 + zzfhg.zzb(1, zzd);
    }
    int j = i;
    if (!zze.zzb()) {
      j = i + zzfhg.zzc(2, zze);
    }
    i = j + zzb.zze();
    zzc = i;
    return i;
  }
  
  /* Error */
  protected final Object zza(int paramInt, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: getstatic 87	com/google/android/gms/internal/zzfgq:zza	[I
    //   3: astore 7
    //   5: iconst_1
    //   6: istore 6
    //   8: aload 7
    //   10: iload_1
    //   11: iconst_1
    //   12: isub
    //   13: iaload
    //   14: tableswitch	default:+54->68, 1:+410->424, 2:+406->420, 3:+404->418, 4:+395->409, 5:+286->300, 6:+113->127, 7:+282->296, 8:+69->83, 9:+64->78, 10:+62->76
    //   68: new 89	java/lang/UnsupportedOperationException
    //   71: dup
    //   72: invokespecial 90	java/lang/UnsupportedOperationException:<init>	()V
    //   75: athrow
    //   76: aconst_null
    //   77: areturn
    //   78: iconst_1
    //   79: invokestatic 96	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
    //   82: areturn
    //   83: getstatic 98	com/google/android/gms/internal/zzfgp:zzg	Lcom/google/android/gms/internal/zzfjl;
    //   86: ifnonnull +37 -> 123
    //   89: ldc 2
    //   91: monitorenter
    //   92: getstatic 98	com/google/android/gms/internal/zzfgp:zzg	Lcom/google/android/gms/internal/zzfjl;
    //   95: ifnonnull +16 -> 111
    //   98: new 100	com/google/android/gms/internal/zzfhu$zzb
    //   101: dup
    //   102: getstatic 26	com/google/android/gms/internal/zzfgp:zzf	Lcom/google/android/gms/internal/zzfgp;
    //   105: invokespecial 103	com/google/android/gms/internal/zzfhu$zzb:<init>	(Lcom/google/android/gms/internal/zzfhu;)V
    //   108: putstatic 98	com/google/android/gms/internal/zzfgp:zzg	Lcom/google/android/gms/internal/zzfjl;
    //   111: ldc 2
    //   113: monitorexit
    //   114: goto +9 -> 123
    //   117: astore_2
    //   118: ldc 2
    //   120: monitorexit
    //   121: aload_2
    //   122: athrow
    //   123: getstatic 98	com/google/android/gms/internal/zzfgp:zzg	Lcom/google/android/gms/internal/zzfjl;
    //   126: areturn
    //   127: aload_2
    //   128: checkcast 105	com/google/android/gms/internal/zzfhb
    //   131: astore_2
    //   132: aload_3
    //   133: checkcast 107	com/google/android/gms/internal/zzfhm
    //   136: ifnonnull +11 -> 147
    //   139: new 109	java/lang/NullPointerException
    //   142: dup
    //   143: invokespecial 110	java/lang/NullPointerException:<init>	()V
    //   146: athrow
    //   147: iconst_0
    //   148: istore_1
    //   149: iload_1
    //   150: ifne +146 -> 296
    //   153: aload_2
    //   154: invokevirtual 112	com/google/android/gms/internal/zzfhb:zza	()I
    //   157: istore 4
    //   159: iload 4
    //   161: ifeq +86 -> 247
    //   164: iload 4
    //   166: bipush 10
    //   168: if_icmpeq +68 -> 236
    //   171: iload 4
    //   173: bipush 18
    //   175: if_icmpeq +50 -> 225
    //   178: iload 4
    //   180: bipush 7
    //   182: iand
    //   183: iconst_4
    //   184: if_icmpne +9 -> 193
    //   187: iconst_0
    //   188: istore 5
    //   190: goto +242 -> 432
    //   193: aload_0
    //   194: getfield 38	com/google/android/gms/internal/zzfhu:zzb	Lcom/google/android/gms/internal/zzfko;
    //   197: invokestatic 115	com/google/android/gms/internal/zzfko:zza	()Lcom/google/android/gms/internal/zzfko;
    //   200: if_acmpne +10 -> 210
    //   203: aload_0
    //   204: invokestatic 117	com/google/android/gms/internal/zzfko:zzb	()Lcom/google/android/gms/internal/zzfko;
    //   207: putfield 38	com/google/android/gms/internal/zzfhu:zzb	Lcom/google/android/gms/internal/zzfko;
    //   210: aload_0
    //   211: getfield 38	com/google/android/gms/internal/zzfhu:zzb	Lcom/google/android/gms/internal/zzfko;
    //   214: iload 4
    //   216: aload_2
    //   217: invokevirtual 120	com/google/android/gms/internal/zzfko:zza	(ILcom/google/android/gms/internal/zzfhb;)Z
    //   220: istore 5
    //   222: goto +210 -> 432
    //   225: aload_0
    //   226: aload_2
    //   227: invokevirtual 124	com/google/android/gms/internal/zzfhb:zzl	()Lcom/google/android/gms/internal/zzfgs;
    //   230: putfield 55	com/google/android/gms/internal/zzfgp:zze	Lcom/google/android/gms/internal/zzfgs;
    //   233: goto -84 -> 149
    //   236: aload_0
    //   237: aload_2
    //   238: invokevirtual 128	com/google/android/gms/internal/zzfhb:zzk	()Ljava/lang/String;
    //   241: putfield 49	com/google/android/gms/internal/zzfgp:zzd	Ljava/lang/String;
    //   244: goto -95 -> 149
    //   247: iconst_1
    //   248: istore_1
    //   249: goto -100 -> 149
    //   252: astore_2
    //   253: goto +41 -> 294
    //   256: astore_2
    //   257: new 130	java/lang/RuntimeException
    //   260: dup
    //   261: new 80	com/google/android/gms/internal/zzfie
    //   264: dup
    //   265: aload_2
    //   266: invokevirtual 133	java/io/IOException:getMessage	()Ljava/lang/String;
    //   269: invokespecial 136	com/google/android/gms/internal/zzfie:<init>	(Ljava/lang/String;)V
    //   272: aload_0
    //   273: invokevirtual 139	com/google/android/gms/internal/zzfie:zza	(Lcom/google/android/gms/internal/zzfjc;)Lcom/google/android/gms/internal/zzfie;
    //   276: invokespecial 142	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   279: athrow
    //   280: astore_2
    //   281: new 130	java/lang/RuntimeException
    //   284: dup
    //   285: aload_2
    //   286: aload_0
    //   287: invokevirtual 139	com/google/android/gms/internal/zzfie:zza	(Lcom/google/android/gms/internal/zzfjc;)Lcom/google/android/gms/internal/zzfie;
    //   290: invokespecial 142	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   293: athrow
    //   294: aload_2
    //   295: athrow
    //   296: getstatic 26	com/google/android/gms/internal/zzfgp:zzf	Lcom/google/android/gms/internal/zzfgp;
    //   299: areturn
    //   300: aload_2
    //   301: checkcast 144	com/google/android/gms/internal/zzfhu$zzh
    //   304: astore_2
    //   305: aload_3
    //   306: checkcast 2	com/google/android/gms/internal/zzfgp
    //   309: astore_3
    //   310: aload_0
    //   311: aload_2
    //   312: aload_0
    //   313: getfield 49	com/google/android/gms/internal/zzfgp:zzd	Ljava/lang/String;
    //   316: invokevirtual 65	java/lang/String:isEmpty	()Z
    //   319: iconst_1
    //   320: ixor
    //   321: aload_0
    //   322: getfield 49	com/google/android/gms/internal/zzfgp:zzd	Ljava/lang/String;
    //   325: aload_3
    //   326: getfield 49	com/google/android/gms/internal/zzfgp:zzd	Ljava/lang/String;
    //   329: invokevirtual 65	java/lang/String:isEmpty	()Z
    //   332: iconst_1
    //   333: ixor
    //   334: aload_3
    //   335: getfield 49	com/google/android/gms/internal/zzfgp:zzd	Ljava/lang/String;
    //   338: invokeinterface 147 5 0
    //   343: putfield 49	com/google/android/gms/internal/zzfgp:zzd	Ljava/lang/String;
    //   346: aload_0
    //   347: getfield 55	com/google/android/gms/internal/zzfgp:zze	Lcom/google/android/gms/internal/zzfgs;
    //   350: getstatic 53	com/google/android/gms/internal/zzfgs:zza	Lcom/google/android/gms/internal/zzfgs;
    //   353: if_acmpeq +9 -> 362
    //   356: iconst_1
    //   357: istore 5
    //   359: goto +6 -> 365
    //   362: iconst_0
    //   363: istore 5
    //   365: aload_0
    //   366: getfield 55	com/google/android/gms/internal/zzfgp:zze	Lcom/google/android/gms/internal/zzfgs;
    //   369: astore 7
    //   371: aload_3
    //   372: getfield 55	com/google/android/gms/internal/zzfgp:zze	Lcom/google/android/gms/internal/zzfgs;
    //   375: getstatic 53	com/google/android/gms/internal/zzfgs:zza	Lcom/google/android/gms/internal/zzfgs;
    //   378: if_acmpeq +6 -> 384
    //   381: goto +6 -> 387
    //   384: iconst_0
    //   385: istore 6
    //   387: aload_0
    //   388: aload_2
    //   389: iload 5
    //   391: aload 7
    //   393: iload 6
    //   395: aload_3
    //   396: getfield 55	com/google/android/gms/internal/zzfgp:zze	Lcom/google/android/gms/internal/zzfgs;
    //   399: invokeinterface 150 5 0
    //   404: putfield 55	com/google/android/gms/internal/zzfgp:zze	Lcom/google/android/gms/internal/zzfgs;
    //   407: aload_0
    //   408: areturn
    //   409: new 9	com/google/android/gms/internal/zzfgp$zza
    //   412: dup
    //   413: aconst_null
    //   414: invokespecial 153	com/google/android/gms/internal/zzfgp$zza:<init>	(Lcom/google/android/gms/internal/zzfgq;)V
    //   417: areturn
    //   418: aconst_null
    //   419: areturn
    //   420: getstatic 26	com/google/android/gms/internal/zzfgp:zzf	Lcom/google/android/gms/internal/zzfgp;
    //   423: areturn
    //   424: new 2	com/google/android/gms/internal/zzfgp
    //   427: dup
    //   428: invokespecial 24	com/google/android/gms/internal/zzfgp:<init>	()V
    //   431: areturn
    //   432: iload 5
    //   434: ifne -285 -> 149
    //   437: goto -190 -> 247
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	440	0	this	zzfgp
    //   0	440	1	paramInt	int
    //   0	440	2	paramObject1	Object
    //   0	440	3	paramObject2	Object
    //   157	58	4	i	int
    //   188	245	5	bool1	boolean
    //   6	388	6	bool2	boolean
    //   3	389	7	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   92	111	117	finally
    //   111	114	117	finally
    //   118	121	117	finally
    //   153	159	252	finally
    //   193	210	252	finally
    //   210	222	252	finally
    //   225	233	252	finally
    //   236	244	252	finally
    //   257	280	252	finally
    //   281	294	252	finally
    //   153	159	256	java/io/IOException
    //   193	210	256	java/io/IOException
    //   210	222	256	java/io/IOException
    //   225	233	256	java/io/IOException
    //   236	244	256	java/io/IOException
    //   153	159	280	com/google/android/gms/internal/zzfie
    //   193	210	280	com/google/android/gms/internal/zzfie
    //   210	222	280	com/google/android/gms/internal/zzfie
    //   225	233	280	com/google/android/gms/internal/zzfie
    //   236	244	280	com/google/android/gms/internal/zzfie
  }
  
  public final void zza(zzfhg paramZzfhg)
    throws IOException
  {
    if (!zzd.isEmpty()) {
      paramZzfhg.zza(1, zzd);
    }
    if (!zze.zzb()) {
      paramZzfhg.zza(2, zze);
    }
    zzb.zza(paramZzfhg);
  }
  
  public static final class zza
    extends zzfhu.zza<zzfgp, zza>
    implements zzfje
  {
    private zza()
    {
      super();
    }
  }
}

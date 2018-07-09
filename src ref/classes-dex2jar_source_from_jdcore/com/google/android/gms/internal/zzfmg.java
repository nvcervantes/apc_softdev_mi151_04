package com.google.android.gms.internal;

import java.io.IOException;

public final class zzfmg
  extends zzfhu<zzfmg, zza>
  implements zzfje
{
  private static final zzfmg zzh;
  private static volatile zzfjl<zzfmg> zzi;
  private int zzd;
  private int zze;
  private String zzf = "";
  private zzfid<zzfgp> zzg = zzu();
  
  static
  {
    zzfmg localZzfmg = new zzfmg();
    zzh = localZzfmg;
    localZzfmg.zza(zzfhu.zzg.zzf, null, null);
    zzb.zzc();
  }
  
  private zzfmg() {}
  
  public static zzfmg zzd()
  {
    return zzh;
  }
  
  public final int zza()
  {
    int i = zzc;
    if (i != -1) {
      return i;
    }
    i = zze;
    int m = 0;
    int j;
    if (i != 0) {
      j = zzfhg.zze(1, zze) + 0;
    } else {
      j = 0;
    }
    i = j;
    int k = m;
    if (!zzf.isEmpty())
    {
      i = j + zzfhg.zzb(2, zzf);
      k = m;
    }
    while (k < zzg.size())
    {
      i += zzfhg.zzc(3, (zzfjc)zzg.get(k));
      k += 1;
    }
    i += zzb.zze();
    zzc = i;
    return i;
  }
  
  /* Error */
  protected final Object zza(int paramInt, Object paramObject1, Object paramObject2)
  {
    // Byte code:
    //   0: getstatic 104	com/google/android/gms/internal/zzfmh:zza	[I
    //   3: iload_1
    //   4: iconst_1
    //   5: isub
    //   6: iaload
    //   7: istore 4
    //   9: iconst_0
    //   10: istore 6
    //   12: iconst_0
    //   13: istore_1
    //   14: iload 4
    //   16: tableswitch	default:+56->72, 1:+497->513, 2:+493->509, 3:+482->498, 4:+473->489, 5:+337->353, 6:+115->131, 7:+333->349, 8:+71->87, 9:+66->82, 10:+64->80
    //   72: new 106	java/lang/UnsupportedOperationException
    //   75: dup
    //   76: invokespecial 107	java/lang/UnsupportedOperationException:<init>	()V
    //   79: athrow
    //   80: aconst_null
    //   81: areturn
    //   82: iconst_1
    //   83: invokestatic 113	java/lang/Byte:valueOf	(B)Ljava/lang/Byte;
    //   86: areturn
    //   87: getstatic 115	com/google/android/gms/internal/zzfmg:zzi	Lcom/google/android/gms/internal/zzfjl;
    //   90: ifnonnull +37 -> 127
    //   93: ldc 2
    //   95: monitorenter
    //   96: getstatic 115	com/google/android/gms/internal/zzfmg:zzi	Lcom/google/android/gms/internal/zzfjl;
    //   99: ifnonnull +16 -> 115
    //   102: new 117	com/google/android/gms/internal/zzfhu$zzb
    //   105: dup
    //   106: getstatic 30	com/google/android/gms/internal/zzfmg:zzh	Lcom/google/android/gms/internal/zzfmg;
    //   109: invokespecial 120	com/google/android/gms/internal/zzfhu$zzb:<init>	(Lcom/google/android/gms/internal/zzfhu;)V
    //   112: putstatic 115	com/google/android/gms/internal/zzfmg:zzi	Lcom/google/android/gms/internal/zzfjl;
    //   115: ldc 2
    //   117: monitorexit
    //   118: goto +9 -> 127
    //   121: astore_2
    //   122: ldc 2
    //   124: monitorexit
    //   125: aload_2
    //   126: athrow
    //   127: getstatic 115	com/google/android/gms/internal/zzfmg:zzi	Lcom/google/android/gms/internal/zzfjl;
    //   130: areturn
    //   131: aload_2
    //   132: checkcast 122	com/google/android/gms/internal/zzfhb
    //   135: astore_2
    //   136: aload_3
    //   137: checkcast 124	com/google/android/gms/internal/zzfhm
    //   140: astore_3
    //   141: aload_3
    //   142: ifnonnull +11 -> 153
    //   145: new 126	java/lang/NullPointerException
    //   148: dup
    //   149: invokespecial 127	java/lang/NullPointerException:<init>	()V
    //   152: athrow
    //   153: iload_1
    //   154: ifne +195 -> 349
    //   157: aload_2
    //   158: invokevirtual 129	com/google/android/gms/internal/zzfhb:zza	()I
    //   161: istore 4
    //   163: iload 4
    //   165: ifeq +135 -> 300
    //   168: iload 4
    //   170: bipush 8
    //   172: if_icmpeq +117 -> 289
    //   175: iload 4
    //   177: bipush 18
    //   179: if_icmpeq +99 -> 278
    //   182: iload 4
    //   184: bipush 26
    //   186: if_icmpeq +16 -> 202
    //   189: aload_0
    //   190: iload 4
    //   192: aload_2
    //   193: invokevirtual 132	com/google/android/gms/internal/zzfhu:zza	(ILcom/google/android/gms/internal/zzfhb;)Z
    //   196: ifne -43 -> 153
    //   199: goto +101 -> 300
    //   202: aload_0
    //   203: getfield 58	com/google/android/gms/internal/zzfmg:zzg	Lcom/google/android/gms/internal/zzfid;
    //   206: invokeinterface 134 1 0
    //   211: ifne +43 -> 254
    //   214: aload_0
    //   215: getfield 58	com/google/android/gms/internal/zzfmg:zzg	Lcom/google/android/gms/internal/zzfid;
    //   218: astore 7
    //   220: aload 7
    //   222: invokeinterface 83 1 0
    //   227: istore 4
    //   229: iload 4
    //   231: ifne +290 -> 521
    //   234: bipush 10
    //   236: istore 4
    //   238: goto +3 -> 241
    //   241: aload_0
    //   242: aload 7
    //   244: iload 4
    //   246: invokeinterface 137 2 0
    //   251: putfield 58	com/google/android/gms/internal/zzfmg:zzg	Lcom/google/android/gms/internal/zzfid;
    //   254: aload_0
    //   255: getfield 58	com/google/android/gms/internal/zzfmg:zzg	Lcom/google/android/gms/internal/zzfid;
    //   258: aload_2
    //   259: invokestatic 142	com/google/android/gms/internal/zzfgp:zzb	()Lcom/google/android/gms/internal/zzfgp;
    //   262: aload_3
    //   263: invokevirtual 145	com/google/android/gms/internal/zzfhb:zza	(Lcom/google/android/gms/internal/zzfhu;Lcom/google/android/gms/internal/zzfhm;)Lcom/google/android/gms/internal/zzfhu;
    //   266: checkcast 139	com/google/android/gms/internal/zzfgp
    //   269: invokeinterface 149 2 0
    //   274: pop
    //   275: goto -122 -> 153
    //   278: aload_0
    //   279: aload_2
    //   280: invokevirtual 153	com/google/android/gms/internal/zzfhb:zzk	()Ljava/lang/String;
    //   283: putfield 52	com/google/android/gms/internal/zzfmg:zzf	Ljava/lang/String;
    //   286: goto -133 -> 153
    //   289: aload_0
    //   290: aload_2
    //   291: invokevirtual 155	com/google/android/gms/internal/zzfhb:zzf	()I
    //   294: putfield 64	com/google/android/gms/internal/zzfmg:zze	I
    //   297: goto -144 -> 153
    //   300: iconst_1
    //   301: istore_1
    //   302: goto -149 -> 153
    //   305: astore_2
    //   306: goto +41 -> 347
    //   309: astore_2
    //   310: new 157	java/lang/RuntimeException
    //   313: dup
    //   314: new 97	com/google/android/gms/internal/zzfie
    //   317: dup
    //   318: aload_2
    //   319: invokevirtual 160	java/io/IOException:getMessage	()Ljava/lang/String;
    //   322: invokespecial 163	com/google/android/gms/internal/zzfie:<init>	(Ljava/lang/String;)V
    //   325: aload_0
    //   326: invokevirtual 166	com/google/android/gms/internal/zzfie:zza	(Lcom/google/android/gms/internal/zzfjc;)Lcom/google/android/gms/internal/zzfie;
    //   329: invokespecial 169	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   332: athrow
    //   333: astore_2
    //   334: new 157	java/lang/RuntimeException
    //   337: dup
    //   338: aload_2
    //   339: aload_0
    //   340: invokevirtual 166	com/google/android/gms/internal/zzfie:zza	(Lcom/google/android/gms/internal/zzfjc;)Lcom/google/android/gms/internal/zzfie;
    //   343: invokespecial 169	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   346: athrow
    //   347: aload_2
    //   348: athrow
    //   349: getstatic 30	com/google/android/gms/internal/zzfmg:zzh	Lcom/google/android/gms/internal/zzfmg;
    //   352: areturn
    //   353: aload_2
    //   354: checkcast 171	com/google/android/gms/internal/zzfhu$zzh
    //   357: astore_2
    //   358: aload_3
    //   359: checkcast 2	com/google/android/gms/internal/zzfmg
    //   362: astore_3
    //   363: aload_0
    //   364: getfield 64	com/google/android/gms/internal/zzfmg:zze	I
    //   367: ifeq +9 -> 376
    //   370: iconst_1
    //   371: istore 5
    //   373: goto +6 -> 379
    //   376: iconst_0
    //   377: istore 5
    //   379: aload_0
    //   380: getfield 64	com/google/android/gms/internal/zzfmg:zze	I
    //   383: istore_1
    //   384: aload_3
    //   385: getfield 64	com/google/android/gms/internal/zzfmg:zze	I
    //   388: ifeq +6 -> 394
    //   391: iconst_1
    //   392: istore 6
    //   394: aload_0
    //   395: aload_2
    //   396: iload 5
    //   398: iload_1
    //   399: iload 6
    //   401: aload_3
    //   402: getfield 64	com/google/android/gms/internal/zzfmg:zze	I
    //   405: invokeinterface 174 5 0
    //   410: putfield 64	com/google/android/gms/internal/zzfmg:zze	I
    //   413: aload_0
    //   414: aload_2
    //   415: aload_0
    //   416: getfield 52	com/google/android/gms/internal/zzfmg:zzf	Ljava/lang/String;
    //   419: invokevirtual 75	java/lang/String:isEmpty	()Z
    //   422: iconst_1
    //   423: ixor
    //   424: aload_0
    //   425: getfield 52	com/google/android/gms/internal/zzfmg:zzf	Ljava/lang/String;
    //   428: iconst_1
    //   429: aload_3
    //   430: getfield 52	com/google/android/gms/internal/zzfmg:zzf	Ljava/lang/String;
    //   433: invokevirtual 75	java/lang/String:isEmpty	()Z
    //   436: ixor
    //   437: aload_3
    //   438: getfield 52	com/google/android/gms/internal/zzfmg:zzf	Ljava/lang/String;
    //   441: invokeinterface 177 5 0
    //   446: putfield 52	com/google/android/gms/internal/zzfmg:zzf	Ljava/lang/String;
    //   449: aload_0
    //   450: aload_2
    //   451: aload_0
    //   452: getfield 58	com/google/android/gms/internal/zzfmg:zzg	Lcom/google/android/gms/internal/zzfid;
    //   455: aload_3
    //   456: getfield 58	com/google/android/gms/internal/zzfmg:zzg	Lcom/google/android/gms/internal/zzfid;
    //   459: invokeinterface 180 3 0
    //   464: putfield 58	com/google/android/gms/internal/zzfmg:zzg	Lcom/google/android/gms/internal/zzfid;
    //   467: aload_2
    //   468: getstatic 185	com/google/android/gms/internal/zzfhu$zzf:zza	Lcom/google/android/gms/internal/zzfhu$zzf;
    //   471: if_acmpne +16 -> 487
    //   474: aload_0
    //   475: aload_0
    //   476: getfield 187	com/google/android/gms/internal/zzfmg:zzd	I
    //   479: aload_3
    //   480: getfield 187	com/google/android/gms/internal/zzfmg:zzd	I
    //   483: ior
    //   484: putfield 187	com/google/android/gms/internal/zzfmg:zzd	I
    //   487: aload_0
    //   488: areturn
    //   489: new 9	com/google/android/gms/internal/zzfmg$zza
    //   492: dup
    //   493: aconst_null
    //   494: invokespecial 190	com/google/android/gms/internal/zzfmg$zza:<init>	(Lcom/google/android/gms/internal/zzfmh;)V
    //   497: areturn
    //   498: aload_0
    //   499: getfield 58	com/google/android/gms/internal/zzfmg:zzg	Lcom/google/android/gms/internal/zzfid;
    //   502: invokeinterface 192 1 0
    //   507: aconst_null
    //   508: areturn
    //   509: getstatic 30	com/google/android/gms/internal/zzfmg:zzh	Lcom/google/android/gms/internal/zzfmg;
    //   512: areturn
    //   513: new 2	com/google/android/gms/internal/zzfmg
    //   516: dup
    //   517: invokespecial 28	com/google/android/gms/internal/zzfmg:<init>	()V
    //   520: areturn
    //   521: iload 4
    //   523: iconst_1
    //   524: ishl
    //   525: istore 4
    //   527: goto -286 -> 241
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	530	0	this	zzfmg
    //   0	530	1	paramInt	int
    //   0	530	2	paramObject1	Object
    //   0	530	3	paramObject2	Object
    //   7	519	4	i	int
    //   371	26	5	bool1	boolean
    //   10	390	6	bool2	boolean
    //   218	25	7	localZzfid	zzfid
    // Exception table:
    //   from	to	target	type
    //   96	115	121	finally
    //   115	118	121	finally
    //   122	125	121	finally
    //   157	163	305	finally
    //   189	199	305	finally
    //   202	229	305	finally
    //   241	254	305	finally
    //   254	275	305	finally
    //   278	286	305	finally
    //   289	297	305	finally
    //   310	333	305	finally
    //   334	347	305	finally
    //   157	163	309	java/io/IOException
    //   189	199	309	java/io/IOException
    //   202	229	309	java/io/IOException
    //   241	254	309	java/io/IOException
    //   254	275	309	java/io/IOException
    //   278	286	309	java/io/IOException
    //   289	297	309	java/io/IOException
    //   157	163	333	com/google/android/gms/internal/zzfie
    //   189	199	333	com/google/android/gms/internal/zzfie
    //   202	229	333	com/google/android/gms/internal/zzfie
    //   241	254	333	com/google/android/gms/internal/zzfie
    //   254	275	333	com/google/android/gms/internal/zzfie
    //   278	286	333	com/google/android/gms/internal/zzfie
    //   289	297	333	com/google/android/gms/internal/zzfie
  }
  
  public final void zza(zzfhg paramZzfhg)
    throws IOException
  {
    if (zze != 0) {
      paramZzfhg.zzb(1, zze);
    }
    if (!zzf.isEmpty()) {
      paramZzfhg.zza(2, zzf);
    }
    int i = 0;
    while (i < zzg.size())
    {
      paramZzfhg.zza(3, (zzfjc)zzg.get(i));
      i += 1;
    }
    zzb.zza(paramZzfhg);
  }
  
  public final int zzb()
  {
    return zze;
  }
  
  public final String zzc()
  {
    return zzf;
  }
  
  public static final class zza
    extends zzfhu.zza<zzfmg, zza>
    implements zzfje
  {
    private zza()
    {
      super();
    }
  }
}

package com.google.android.gms.internal;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class zzam
  implements zzb
{
  private final Map<String, zzan> zza = new LinkedHashMap(16, 0.75F, true);
  private long zzb = 0L;
  private final File zzc;
  private final int zzd;
  
  public zzam(File paramFile)
  {
    this(paramFile, 5242880);
  }
  
  private zzam(File paramFile, int paramInt)
  {
    zzc = paramFile;
    zzd = 5242880;
  }
  
  static int zza(InputStream paramInputStream)
    throws IOException
  {
    int i = zzc(paramInputStream);
    int j = zzc(paramInputStream);
    int k = zzc(paramInputStream);
    return zzc(paramInputStream) << 24 | i | 0x0 | j << 8 | k << 16;
  }
  
  private static InputStream zza(File paramFile)
    throws FileNotFoundException
  {
    return new FileInputStream(paramFile);
  }
  
  static String zza(zzao paramZzao)
    throws IOException
  {
    return new String(zza(paramZzao, zzb(paramZzao)), "UTF-8");
  }
  
  static void zza(OutputStream paramOutputStream, int paramInt)
    throws IOException
  {
    paramOutputStream.write(paramInt & 0xFF);
    paramOutputStream.write(paramInt >> 8 & 0xFF);
    paramOutputStream.write(paramInt >> 16 & 0xFF);
    paramOutputStream.write(paramInt >>> 24);
  }
  
  static void zza(OutputStream paramOutputStream, long paramLong)
    throws IOException
  {
    paramOutputStream.write((byte)(int)paramLong);
    paramOutputStream.write((byte)(int)(paramLong >>> 8));
    paramOutputStream.write((byte)(int)(paramLong >>> 16));
    paramOutputStream.write((byte)(int)(paramLong >>> 24));
    paramOutputStream.write((byte)(int)(paramLong >>> 32));
    paramOutputStream.write((byte)(int)(paramLong >>> 40));
    paramOutputStream.write((byte)(int)(paramLong >>> 48));
    paramOutputStream.write((byte)(int)(paramLong >>> 56));
  }
  
  static void zza(OutputStream paramOutputStream, String paramString)
    throws IOException
  {
    paramString = paramString.getBytes("UTF-8");
    zza(paramOutputStream, paramString.length);
    paramOutputStream.write(paramString, 0, paramString.length);
  }
  
  private final void zza(String paramString, zzan paramZzan)
  {
    if (!zza.containsKey(paramString))
    {
      zzb += zza;
    }
    else
    {
      zzan localZzan = (zzan)zza.get(paramString);
      zzb += zza - zza;
    }
    zza.put(paramString, paramZzan);
  }
  
  private static byte[] zza(zzao paramZzao, long paramLong)
    throws IOException
  {
    long l = paramZzao.zza();
    if ((paramLong >= 0L) && (paramLong <= l))
    {
      int i = (int)paramLong;
      if (i == paramLong)
      {
        byte[] arrayOfByte = new byte[i];
        new DataInputStream(paramZzao).readFully(arrayOfByte);
        return arrayOfByte;
      }
    }
    paramZzao = new StringBuilder(73);
    paramZzao.append("streamToBytes length=");
    paramZzao.append(paramLong);
    paramZzao.append(", maxLength=");
    paramZzao.append(l);
    throw new IOException(paramZzao.toString());
  }
  
  static long zzb(InputStream paramInputStream)
    throws IOException
  {
    return 0L | zzc(paramInputStream) & 0xFF | (zzc(paramInputStream) & 0xFF) << 8 | (zzc(paramInputStream) & 0xFF) << 16 | (zzc(paramInputStream) & 0xFF) << 24 | (zzc(paramInputStream) & 0xFF) << 32 | (zzc(paramInputStream) & 0xFF) << 40 | (zzc(paramInputStream) & 0xFF) << 48 | (zzc(paramInputStream) & 0xFF) << 56;
  }
  
  static List<zzl> zzb(zzao paramZzao)
    throws IOException
  {
    int j = zza(paramZzao);
    Object localObject;
    if (j == 0) {
      localObject = Collections.emptyList();
    } else {
      localObject = new ArrayList(j);
    }
    int i = 0;
    while (i < j)
    {
      ((List)localObject).add(new zzl(zza(paramZzao).intern(), zza(paramZzao).intern()));
      i += 1;
    }
    return localObject;
  }
  
  private final void zzb(String paramString)
  {
    try
    {
      boolean bool = zzd(paramString).delete();
      zze(paramString);
      if (!bool) {
        zzaf.zzb("Could not delete cache entry for key=%s, filename=%s", new Object[] { paramString, zzc(paramString) });
      }
      return;
    }
    finally {}
  }
  
  private static int zzc(InputStream paramInputStream)
    throws IOException
  {
    int i = paramInputStream.read();
    if (i == -1) {
      throw new EOFException();
    }
    return i;
  }
  
  private static String zzc(String paramString)
  {
    int i = paramString.length() / 2;
    String str = String.valueOf(String.valueOf(paramString.substring(0, i).hashCode()));
    paramString = String.valueOf(String.valueOf(paramString.substring(i).hashCode()));
    if (paramString.length() != 0) {
      return str.concat(paramString);
    }
    return new String(str);
  }
  
  private final File zzd(String paramString)
  {
    return new File(zzc, zzc(paramString));
  }
  
  private final void zze(String paramString)
  {
    paramString = (zzan)zza.remove(paramString);
    if (paramString != null) {
      zzb -= zza;
    }
  }
  
  /* Error */
  public final zzc zza(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 33	com/google/android/gms/internal/zzam:zza	Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface 99 2 0
    //   12: checkcast 93	com/google/android/gms/internal/zzan
    //   15: astore 4
    //   17: aload 4
    //   19: ifnonnull +7 -> 26
    //   22: aload_0
    //   23: monitorexit
    //   24: aconst_null
    //   25: areturn
    //   26: aload_0
    //   27: aload_1
    //   28: invokespecial 173	com/google/android/gms/internal/zzam:zzd	(Ljava/lang/String;)Ljava/io/File;
    //   31: astore_2
    //   32: new 105	com/google/android/gms/internal/zzao
    //   35: dup
    //   36: new 233	java/io/BufferedInputStream
    //   39: dup
    //   40: aload_2
    //   41: invokestatic 235	com/google/android/gms/internal/zzam:zza	(Ljava/io/File;)Ljava/io/InputStream;
    //   44: invokespecial 236	java/io/BufferedInputStream:<init>	(Ljava/io/InputStream;)V
    //   47: aload_2
    //   48: invokevirtual 238	java/io/File:length	()J
    //   51: invokespecial 241	com/google/android/gms/internal/zzao:<init>	(Ljava/io/InputStream;J)V
    //   54: astore_3
    //   55: aload_3
    //   56: invokestatic 244	com/google/android/gms/internal/zzan:zza	(Lcom/google/android/gms/internal/zzao;)Lcom/google/android/gms/internal/zzan;
    //   59: astore 5
    //   61: aload_1
    //   62: aload 5
    //   64: getfield 247	com/google/android/gms/internal/zzan:zzb	Ljava/lang/String;
    //   67: invokestatic 253	android/text/TextUtils:equals	(Ljava/lang/CharSequence;Ljava/lang/CharSequence;)Z
    //   70: ifne +44 -> 114
    //   73: ldc -1
    //   75: iconst_3
    //   76: anewarray 4	java/lang/Object
    //   79: dup
    //   80: iconst_0
    //   81: aload_2
    //   82: invokevirtual 258	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   85: aastore
    //   86: dup
    //   87: iconst_1
    //   88: aload_1
    //   89: aastore
    //   90: dup
    //   91: iconst_2
    //   92: aload 5
    //   94: getfield 247	com/google/android/gms/internal/zzan:zzb	Ljava/lang/String;
    //   97: aastore
    //   98: invokestatic 192	com/google/android/gms/internal/zzaf:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   101: aload_0
    //   102: aload_1
    //   103: invokespecial 182	com/google/android/gms/internal/zzam:zze	(Ljava/lang/String;)V
    //   106: aload_3
    //   107: invokevirtual 261	com/google/android/gms/internal/zzao:close	()V
    //   110: aload_0
    //   111: monitorexit
    //   112: aconst_null
    //   113: areturn
    //   114: aload_3
    //   115: aload_3
    //   116: invokevirtual 108	com/google/android/gms/internal/zzao:zza	()J
    //   119: invokestatic 61	com/google/android/gms/internal/zzam:zza	(Lcom/google/android/gms/internal/zzao;J)[B
    //   122: astore 5
    //   124: new 263	com/google/android/gms/internal/zzc
    //   127: dup
    //   128: invokespecial 264	com/google/android/gms/internal/zzc:<init>	()V
    //   131: astore 6
    //   133: aload 6
    //   135: aload 5
    //   137: putfield 267	com/google/android/gms/internal/zzc:zza	[B
    //   140: aload 6
    //   142: aload 4
    //   144: getfield 269	com/google/android/gms/internal/zzan:zzc	Ljava/lang/String;
    //   147: putfield 270	com/google/android/gms/internal/zzc:zzb	Ljava/lang/String;
    //   150: aload 6
    //   152: aload 4
    //   154: getfield 272	com/google/android/gms/internal/zzan:zzd	J
    //   157: putfield 274	com/google/android/gms/internal/zzc:zzc	J
    //   160: aload 6
    //   162: aload 4
    //   164: getfield 276	com/google/android/gms/internal/zzan:zze	J
    //   167: putfield 277	com/google/android/gms/internal/zzc:zzd	J
    //   170: aload 6
    //   172: aload 4
    //   174: getfield 280	com/google/android/gms/internal/zzan:zzf	J
    //   177: putfield 281	com/google/android/gms/internal/zzc:zze	J
    //   180: aload 6
    //   182: aload 4
    //   184: getfield 284	com/google/android/gms/internal/zzan:zzg	J
    //   187: putfield 285	com/google/android/gms/internal/zzc:zzf	J
    //   190: aload 6
    //   192: aload 4
    //   194: getfield 289	com/google/android/gms/internal/zzan:zzh	Ljava/util/List;
    //   197: invokestatic 294	com/google/android/gms/internal/zzap:zza	(Ljava/util/List;)Ljava/util/Map;
    //   200: putfield 296	com/google/android/gms/internal/zzc:zzg	Ljava/util/Map;
    //   203: aload 6
    //   205: aload 4
    //   207: getfield 289	com/google/android/gms/internal/zzan:zzh	Ljava/util/List;
    //   210: invokestatic 300	java/util/Collections:unmodifiableList	(Ljava/util/List;)Ljava/util/List;
    //   213: putfield 301	com/google/android/gms/internal/zzc:zzh	Ljava/util/List;
    //   216: aload_3
    //   217: invokevirtual 261	com/google/android/gms/internal/zzao:close	()V
    //   220: aload_0
    //   221: monitorexit
    //   222: aload 6
    //   224: areturn
    //   225: astore 4
    //   227: aload_3
    //   228: invokevirtual 261	com/google/android/gms/internal/zzao:close	()V
    //   231: aload 4
    //   233: athrow
    //   234: astore_3
    //   235: ldc_w 303
    //   238: iconst_2
    //   239: anewarray 4	java/lang/Object
    //   242: dup
    //   243: iconst_0
    //   244: aload_2
    //   245: invokevirtual 258	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   248: aastore
    //   249: dup
    //   250: iconst_1
    //   251: aload_3
    //   252: invokevirtual 304	java/io/IOException:toString	()Ljava/lang/String;
    //   255: aastore
    //   256: invokestatic 192	com/google/android/gms/internal/zzaf:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   259: aload_0
    //   260: aload_1
    //   261: invokespecial 306	com/google/android/gms/internal/zzam:zzb	(Ljava/lang/String;)V
    //   264: aload_0
    //   265: monitorexit
    //   266: aconst_null
    //   267: areturn
    //   268: astore_1
    //   269: aload_0
    //   270: monitorexit
    //   271: aload_1
    //   272: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	273	0	this	zzam
    //   0	273	1	paramString	String
    //   31	214	2	localFile	File
    //   54	174	3	localZzao	zzao
    //   234	18	3	localIOException	IOException
    //   15	191	4	localZzan	zzan
    //   225	7	4	localObject1	Object
    //   59	77	5	localObject2	Object
    //   131	92	6	localZzc	zzc
    // Exception table:
    //   from	to	target	type
    //   55	106	225	finally
    //   114	216	225	finally
    //   32	55	234	java/io/IOException
    //   106	110	234	java/io/IOException
    //   216	220	234	java/io/IOException
    //   227	234	234	java/io/IOException
    //   2	17	268	finally
    //   26	32	268	finally
    //   32	55	268	finally
    //   106	110	268	finally
    //   216	220	268	finally
    //   227	234	268	finally
    //   235	264	268	finally
  }
  
  public final void zza()
  {
    for (;;)
    {
      int i;
      File localFile;
      long l;
      zzao localZzao;
      zzan localZzan;
      try
      {
        boolean bool = zzc.exists();
        i = 0;
        if (!bool)
        {
          if (!zzc.mkdirs()) {
            zzaf.zzc("Unable to create cache dir %s", new Object[] { zzc.getAbsolutePath() });
          }
          return;
        }
        File[] arrayOfFile = zzc.listFiles();
        if (arrayOfFile == null) {
          return;
        }
        int j = arrayOfFile.length;
        if (i < j) {
          localFile = arrayOfFile[i];
        }
      }
      finally {}
      try
      {
        l = localFile.length();
        localZzao = new zzao(new BufferedInputStream(zza(localFile)), l);
      }
      catch (IOException localIOException)
      {
        continue;
      }
      try
      {
        localZzan = zzan.zza(localZzao);
        zza = l;
        zza(zzb, localZzan);
        localZzao.close();
      }
      finally
      {
        localZzao.close();
      }
      i += 1;
    }
  }
  
  /* Error */
  public final void zza(String paramString, zzc paramZzc)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_2
    //   3: getfield 267	com/google/android/gms/internal/zzc:zza	[B
    //   6: arraylength
    //   7: istore_3
    //   8: aload_0
    //   9: getfield 35	com/google/android/gms/internal/zzam:zzb	J
    //   12: lstore 4
    //   14: iload_3
    //   15: i2l
    //   16: lstore 6
    //   18: lload 4
    //   20: lload 6
    //   22: ladd
    //   23: aload_0
    //   24: getfield 39	com/google/android/gms/internal/zzam:zzd	I
    //   27: i2l
    //   28: lcmp
    //   29: iflt +224 -> 253
    //   32: getstatic 326	com/google/android/gms/internal/zzaf:zza	Z
    //   35: ifeq +13 -> 48
    //   38: ldc_w 328
    //   41: iconst_0
    //   42: anewarray 4	java/lang/Object
    //   45: invokestatic 330	com/google/android/gms/internal/zzaf:zza	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   48: aload_0
    //   49: getfield 35	com/google/android/gms/internal/zzam:zzb	J
    //   52: lstore 8
    //   54: invokestatic 335	android/os/SystemClock:elapsedRealtime	()J
    //   57: lstore 4
    //   59: aload_0
    //   60: getfield 33	com/google/android/gms/internal/zzam:zza	Ljava/util/Map;
    //   63: invokeinterface 339 1 0
    //   68: invokeinterface 345 1 0
    //   73: astore 10
    //   75: iconst_0
    //   76: istore_3
    //   77: aload 10
    //   79: invokeinterface 350 1 0
    //   84: ifeq +314 -> 398
    //   87: aload 10
    //   89: invokeinterface 354 1 0
    //   94: checkcast 356	java/util/Map$Entry
    //   97: invokeinterface 359 1 0
    //   102: checkcast 93	com/google/android/gms/internal/zzan
    //   105: astore 11
    //   107: aload_0
    //   108: aload 11
    //   110: getfield 247	com/google/android/gms/internal/zzan:zzb	Ljava/lang/String;
    //   113: invokespecial 173	com/google/android/gms/internal/zzam:zzd	(Ljava/lang/String;)Ljava/io/File;
    //   116: invokevirtual 179	java/io/File:delete	()Z
    //   119: ifeq +20 -> 139
    //   122: aload_0
    //   123: aload_0
    //   124: getfield 35	com/google/android/gms/internal/zzam:zzb	J
    //   127: aload 11
    //   129: getfield 95	com/google/android/gms/internal/zzan:zza	J
    //   132: lsub
    //   133: putfield 35	com/google/android/gms/internal/zzam:zzb	J
    //   136: goto +256 -> 392
    //   139: ldc -72
    //   141: iconst_2
    //   142: anewarray 4	java/lang/Object
    //   145: dup
    //   146: iconst_0
    //   147: aload 11
    //   149: getfield 247	com/google/android/gms/internal/zzan:zzb	Ljava/lang/String;
    //   152: aastore
    //   153: dup
    //   154: iconst_1
    //   155: aload 11
    //   157: getfield 247	com/google/android/gms/internal/zzan:zzb	Ljava/lang/String;
    //   160: invokestatic 187	com/google/android/gms/internal/zzam:zzc	(Ljava/lang/String;)Ljava/lang/String;
    //   163: aastore
    //   164: invokestatic 192	com/google/android/gms/internal/zzaf:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   167: goto +225 -> 392
    //   170: aload 10
    //   172: invokeinterface 361 1 0
    //   177: iload_3
    //   178: iconst_1
    //   179: iadd
    //   180: istore_3
    //   181: aload_0
    //   182: getfield 35	com/google/android/gms/internal/zzam:zzb	J
    //   185: lload 6
    //   187: ladd
    //   188: l2f
    //   189: aload_0
    //   190: getfield 39	com/google/android/gms/internal/zzam:zzd	I
    //   193: i2f
    //   194: ldc_w 362
    //   197: fmul
    //   198: fcmpg
    //   199: ifge +196 -> 395
    //   202: goto +3 -> 205
    //   205: getstatic 326	com/google/android/gms/internal/zzaf:zza	Z
    //   208: ifeq +45 -> 253
    //   211: ldc_w 364
    //   214: iconst_3
    //   215: anewarray 4	java/lang/Object
    //   218: dup
    //   219: iconst_0
    //   220: iload_3
    //   221: invokestatic 369	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   224: aastore
    //   225: dup
    //   226: iconst_1
    //   227: aload_0
    //   228: getfield 35	com/google/android/gms/internal/zzam:zzb	J
    //   231: lload 8
    //   233: lsub
    //   234: invokestatic 374	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   237: aastore
    //   238: dup
    //   239: iconst_2
    //   240: invokestatic 335	android/os/SystemClock:elapsedRealtime	()J
    //   243: lload 4
    //   245: lsub
    //   246: invokestatic 374	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   249: aastore
    //   250: invokestatic 330	com/google/android/gms/internal/zzaf:zza	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   253: aload_0
    //   254: aload_1
    //   255: invokespecial 173	com/google/android/gms/internal/zzam:zzd	(Ljava/lang/String;)Ljava/io/File;
    //   258: astore 10
    //   260: new 376	java/io/BufferedOutputStream
    //   263: dup
    //   264: new 378	java/io/FileOutputStream
    //   267: dup
    //   268: aload 10
    //   270: invokespecial 379	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   273: invokespecial 382	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   276: astore 11
    //   278: new 93	com/google/android/gms/internal/zzan
    //   281: dup
    //   282: aload_1
    //   283: aload_2
    //   284: invokespecial 384	com/google/android/gms/internal/zzan:<init>	(Ljava/lang/String;Lcom/google/android/gms/internal/zzc;)V
    //   287: astore 12
    //   289: aload 12
    //   291: aload 11
    //   293: invokevirtual 387	com/google/android/gms/internal/zzan:zza	(Ljava/io/OutputStream;)Z
    //   296: ifne +34 -> 330
    //   299: aload 11
    //   301: invokevirtual 388	java/io/BufferedOutputStream:close	()V
    //   304: ldc_w 390
    //   307: iconst_1
    //   308: anewarray 4	java/lang/Object
    //   311: dup
    //   312: iconst_0
    //   313: aload 10
    //   315: invokevirtual 258	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   318: aastore
    //   319: invokestatic 192	com/google/android/gms/internal/zzaf:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   322: new 42	java/io/IOException
    //   325: dup
    //   326: invokespecial 391	java/io/IOException:<init>	()V
    //   329: athrow
    //   330: aload 11
    //   332: aload_2
    //   333: getfield 267	com/google/android/gms/internal/zzc:zza	[B
    //   336: invokevirtual 393	java/io/BufferedOutputStream:write	([B)V
    //   339: aload 11
    //   341: invokevirtual 388	java/io/BufferedOutputStream:close	()V
    //   344: aload_0
    //   345: aload_1
    //   346: aload 12
    //   348: invokespecial 322	com/google/android/gms/internal/zzam:zza	(Ljava/lang/String;Lcom/google/android/gms/internal/zzan;)V
    //   351: aload_0
    //   352: monitorexit
    //   353: return
    //   354: aload 10
    //   356: invokevirtual 179	java/io/File:delete	()Z
    //   359: ifne +21 -> 380
    //   362: ldc_w 395
    //   365: iconst_1
    //   366: anewarray 4	java/lang/Object
    //   369: dup
    //   370: iconst_0
    //   371: aload 10
    //   373: invokevirtual 258	java/io/File:getAbsolutePath	()Ljava/lang/String;
    //   376: aastore
    //   377: invokestatic 192	com/google/android/gms/internal/zzaf:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   380: aload_0
    //   381: monitorexit
    //   382: return
    //   383: astore_1
    //   384: aload_0
    //   385: monitorexit
    //   386: aload_1
    //   387: athrow
    //   388: astore_1
    //   389: goto -35 -> 354
    //   392: goto -222 -> 170
    //   395: goto -318 -> 77
    //   398: goto -193 -> 205
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	401	0	this	zzam
    //   0	401	1	paramString	String
    //   0	401	2	paramZzc	zzc
    //   7	214	3	i	int
    //   12	232	4	l1	long
    //   16	170	6	l2	long
    //   52	180	8	l3	long
    //   73	299	10	localObject1	Object
    //   105	235	11	localObject2	Object
    //   287	60	12	localZzan	zzan
    // Exception table:
    //   from	to	target	type
    //   2	14	383	finally
    //   18	48	383	finally
    //   48	75	383	finally
    //   77	136	383	finally
    //   139	167	383	finally
    //   170	177	383	finally
    //   181	202	383	finally
    //   205	253	383	finally
    //   253	260	383	finally
    //   260	330	383	finally
    //   330	351	383	finally
    //   354	380	383	finally
    //   260	330	388	java/io/IOException
    //   330	351	388	java/io/IOException
  }
}

package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000z\n\000\n\002\020\002\n\002\030\002\n\000\n\002\020\022\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\b\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020 \n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\n\002\030\002\n\000\032\022\020\000\032\0020\001*\0020\0022\006\020\003\032\0020\004\032\034\020\005\032\0020\001*\0020\0022\006\020\006\032\0020\0072\b\b\002\020\b\032\0020\t\032!\020\n\032\0020\013*\0020\0022\b\b\002\020\b\032\0020\t2\b\b\002\020\f\032\0020\rH\b\032!\020\016\032\0020\017*\0020\0022\b\b\002\020\b\032\0020\t2\b\b\002\020\f\032\0020\rH\b\032B\020\020\032\0020\001*\0020\00226\020\021\0322\022\023\022\0210\004¢\006\f\b\023\022\b\b\024\022\004\b\b(\025\022\023\022\0210\r¢\006\f\b\023\022\b\b\024\022\004\b\b(\026\022\004\022\0020\0010\022\032J\020\020\032\0020\001*\0020\0022\006\020\027\032\0020\r26\020\021\0322\022\023\022\0210\004¢\006\f\b\023\022\b\b\024\022\004\b\b(\025\022\023\022\0210\r¢\006\f\b\023\022\b\b\024\022\004\b\b(\026\022\004\022\0020\0010\022\0327\020\030\032\0020\001*\0020\0022\b\b\002\020\b\032\0020\t2!\020\021\032\035\022\023\022\0210\007¢\006\f\b\023\022\b\b\024\022\004\b\b(\032\022\004\022\0020\0010\031\032\r\020\033\032\0020\034*\0020\002H\b\032\r\020\035\032\0020\036*\0020\002H\b\032\027\020\037\032\0020 *\0020\0022\b\b\002\020\b\032\0020\tH\b\032\n\020!\032\0020\004*\0020\002\032\032\020\"\032\b\022\004\022\0020\0070#*\0020\0022\b\b\002\020\b\032\0020\t\032\024\020$\032\0020\007*\0020\0022\b\b\002\020\b\032\0020\t\032\027\020%\032\0020&*\0020\0022\b\b\002\020\b\032\0020\tH\b\032?\020'\032\002H(\"\004\b\000\020(*\0020\0022\b\b\002\020\b\032\0020\t2\030\020)\032\024\022\n\022\b\022\004\022\0020\0070*\022\004\022\002H(0\031H\bø\001\000¢\006\002\020,\032\022\020-\032\0020\001*\0020\0022\006\020\003\032\0020\004\032\034\020.\032\0020\001*\0020\0022\006\020\006\032\0020\0072\b\b\002\020\b\032\0020\t\032\027\020/\032\00200*\0020\0022\b\b\002\020\b\032\0020\tH\b\002\b\n\006\b\021(+0\001¨\0061"}, d2={"appendBytes", "", "Ljava/io/File;", "array", "", "appendText", "text", "", "charset", "Ljava/nio/charset/Charset;", "bufferedReader", "Ljava/io/BufferedReader;", "bufferSize", "", "bufferedWriter", "Ljava/io/BufferedWriter;", "forEachBlock", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "buffer", "bytesRead", "blockSize", "forEachLine", "Lkotlin/Function1;", "line", "inputStream", "Ljava/io/FileInputStream;", "outputStream", "Ljava/io/FileOutputStream;", "printWriter", "Ljava/io/PrintWriter;", "readBytes", "readLines", "", "readText", "reader", "Ljava/io/InputStreamReader;", "useLines", "T", "block", "Lkotlin/sequences/Sequence;", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/File;Ljava/nio/charset/Charset;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "writeBytes", "writeText", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/io/FilesKt")
class FilesKt__FileReadWriteKt
  extends FilesKt__FilePathComponentsKt
{
  public FilesKt__FileReadWriteKt() {}
  
  /* Error */
  public static final void appendBytes(@NotNull File paramFile, @NotNull byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 78
    //   3: invokestatic 84	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc 85
    //   9: invokestatic 84	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   12: new 87	java/io/FileOutputStream
    //   15: dup
    //   16: aload_0
    //   17: iconst_1
    //   18: invokespecial 90	java/io/FileOutputStream:<init>	(Ljava/io/File;Z)V
    //   21: checkcast 92	java/io/Closeable
    //   24: astore_3
    //   25: aconst_null
    //   26: checkcast 76	java/lang/Throwable
    //   29: astore_2
    //   30: aload_2
    //   31: astore_0
    //   32: aload_3
    //   33: checkcast 87	java/io/FileOutputStream
    //   36: aload_1
    //   37: invokevirtual 96	java/io/FileOutputStream:write	([B)V
    //   40: aload_2
    //   41: astore_0
    //   42: getstatic 102	kotlin/Unit:INSTANCE	Lkotlin/Unit;
    //   45: astore_1
    //   46: aload_3
    //   47: aload_2
    //   48: invokestatic 108	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   51: return
    //   52: astore_1
    //   53: goto +8 -> 61
    //   56: astore_1
    //   57: aload_1
    //   58: astore_0
    //   59: aload_1
    //   60: athrow
    //   61: aload_3
    //   62: aload_0
    //   63: invokestatic 108	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   66: aload_1
    //   67: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	68	0	paramFile	File
    //   0	68	1	paramArrayOfByte	byte[]
    //   29	19	2	localThrowable	Throwable
    //   24	38	3	localCloseable	java.io.Closeable
    // Exception table:
    //   from	to	target	type
    //   32	40	52	finally
    //   42	46	52	finally
    //   59	61	52	finally
    //   32	40	56	java/lang/Throwable
    //   42	46	56	java/lang/Throwable
  }
  
  public static final void appendText(@NotNull File paramFile, @NotNull String paramString, @NotNull Charset paramCharset)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString, "text");
    Intrinsics.checkParameterIsNotNull(paramCharset, "charset");
    paramString = paramString.getBytes(paramCharset);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).getBytes(charset)");
    FilesKt.appendBytes(paramFile, paramString);
  }
  
  @InlineOnly
  private static final BufferedReader bufferedReader(@NotNull File paramFile, Charset paramCharset, int paramInt)
  {
    paramFile = (Reader)new InputStreamReader((InputStream)new FileInputStream(paramFile), paramCharset);
    if ((paramFile instanceof BufferedReader)) {
      return (BufferedReader)paramFile;
    }
    return new BufferedReader(paramFile, paramInt);
  }
  
  @InlineOnly
  private static final BufferedWriter bufferedWriter(@NotNull File paramFile, Charset paramCharset, int paramInt)
  {
    paramFile = (Writer)new OutputStreamWriter((OutputStream)new FileOutputStream(paramFile), paramCharset);
    if ((paramFile instanceof BufferedWriter)) {
      return (BufferedWriter)paramFile;
    }
    return new BufferedWriter(paramFile, paramInt);
  }
  
  /* Error */
  public static final void forEachBlock(@NotNull File paramFile, int paramInt, @NotNull Function2<? super byte[], ? super Integer, Unit> paramFunction2)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 78
    //   3: invokestatic 84	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_2
    //   7: ldc -77
    //   9: invokestatic 84	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   12: iload_1
    //   13: sipush 512
    //   16: invokestatic 185	kotlin/ranges/RangesKt:coerceAtLeast	(II)I
    //   19: newarray byte
    //   21: astore 5
    //   23: new 141	java/io/FileInputStream
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 144	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   31: checkcast 92	java/io/Closeable
    //   34: astore 4
    //   36: aconst_null
    //   37: checkcast 76	java/lang/Throwable
    //   40: astore_3
    //   41: aload_3
    //   42: astore_0
    //   43: aload 4
    //   45: checkcast 141	java/io/FileInputStream
    //   48: astore 6
    //   50: aload_3
    //   51: astore_0
    //   52: aload 6
    //   54: aload 5
    //   56: invokevirtual 189	java/io/FileInputStream:read	([B)I
    //   59: istore_1
    //   60: iload_1
    //   61: ifgt +16 -> 77
    //   64: aload_3
    //   65: astore_0
    //   66: getstatic 102	kotlin/Unit:INSTANCE	Lkotlin/Unit;
    //   69: astore_2
    //   70: aload 4
    //   72: aload_3
    //   73: invokestatic 108	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   76: return
    //   77: aload_3
    //   78: astore_0
    //   79: aload_2
    //   80: aload 5
    //   82: iload_1
    //   83: invokestatic 195	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   86: invokeinterface 201 3 0
    //   91: pop
    //   92: goto -42 -> 50
    //   95: astore_2
    //   96: goto +8 -> 104
    //   99: astore_2
    //   100: aload_2
    //   101: astore_0
    //   102: aload_2
    //   103: athrow
    //   104: aload 4
    //   106: aload_0
    //   107: invokestatic 108	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   110: aload_2
    //   111: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	paramFile	File
    //   0	112	1	paramInt	int
    //   0	112	2	paramFunction2	Function2<? super byte[], ? super Integer, Unit>
    //   40	38	3	localThrowable	Throwable
    //   34	71	4	localCloseable	java.io.Closeable
    //   21	60	5	arrayOfByte	byte[]
    //   48	5	6	localFileInputStream	FileInputStream
    // Exception table:
    //   from	to	target	type
    //   43	50	95	finally
    //   52	60	95	finally
    //   66	70	95	finally
    //   79	92	95	finally
    //   102	104	95	finally
    //   43	50	99	java/lang/Throwable
    //   52	60	99	java/lang/Throwable
    //   66	70	99	java/lang/Throwable
    //   79	92	99	java/lang/Throwable
  }
  
  public static final void forEachBlock(@NotNull File paramFile, @NotNull Function2<? super byte[], ? super Integer, Unit> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "action");
    FilesKt.forEachBlock(paramFile, 4096, paramFunction2);
  }
  
  public static final void forEachLine(@NotNull File paramFile, @NotNull Charset paramCharset, @NotNull Function1<? super String, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharset, "charset");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "action");
    TextStreamsKt.forEachLine((Reader)new BufferedReader((Reader)new InputStreamReader((InputStream)new FileInputStream(paramFile), paramCharset)), paramFunction1);
  }
  
  @InlineOnly
  private static final FileInputStream inputStream(@NotNull File paramFile)
  {
    return new FileInputStream(paramFile);
  }
  
  @InlineOnly
  private static final FileOutputStream outputStream(@NotNull File paramFile)
  {
    return new FileOutputStream(paramFile);
  }
  
  @InlineOnly
  private static final PrintWriter printWriter(@NotNull File paramFile, Charset paramCharset)
  {
    paramFile = (Writer)new OutputStreamWriter((OutputStream)new FileOutputStream(paramFile), paramCharset);
    if ((paramFile instanceof BufferedWriter)) {
      paramFile = (BufferedWriter)paramFile;
    } else {
      paramFile = new BufferedWriter(paramFile, 8192);
    }
    return new PrintWriter((Writer)paramFile);
  }
  
  /* Error */
  @NotNull
  public static final byte[] readBytes(@NotNull File paramFile)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 78
    //   3: invokestatic 84	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: new 141	java/io/FileInputStream
    //   9: dup
    //   10: aload_0
    //   11: invokespecial 144	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   14: checkcast 92	java/io/Closeable
    //   17: astore 8
    //   19: aconst_null
    //   20: checkcast 76	java/lang/Throwable
    //   23: astore 7
    //   25: aload 7
    //   27: astore 6
    //   29: aload 8
    //   31: checkcast 141	java/io/FileInputStream
    //   34: astore 9
    //   36: iconst_0
    //   37: istore_1
    //   38: aload 7
    //   40: astore 6
    //   42: aload_0
    //   43: invokevirtual 238	java/io/File:length	()J
    //   46: lstore 4
    //   48: lload 4
    //   50: ldc -17
    //   52: i2l
    //   53: lcmp
    //   54: ifle +96 -> 150
    //   57: aload 7
    //   59: astore 6
    //   61: new 241	java/lang/StringBuilder
    //   64: dup
    //   65: invokespecial 242	java/lang/StringBuilder:<init>	()V
    //   68: astore 9
    //   70: aload 7
    //   72: astore 6
    //   74: aload 9
    //   76: ldc -12
    //   78: invokevirtual 248	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   81: pop
    //   82: aload 7
    //   84: astore 6
    //   86: aload 9
    //   88: aload_0
    //   89: invokevirtual 251	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   92: pop
    //   93: aload 7
    //   95: astore 6
    //   97: aload 9
    //   99: ldc -3
    //   101: invokevirtual 248	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   104: pop
    //   105: aload 7
    //   107: astore 6
    //   109: aload 9
    //   111: lload 4
    //   113: invokevirtual 256	java/lang/StringBuilder:append	(J)Ljava/lang/StringBuilder;
    //   116: pop
    //   117: aload 7
    //   119: astore 6
    //   121: aload 9
    //   123: ldc_w 258
    //   126: invokevirtual 248	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   129: pop
    //   130: aload 7
    //   132: astore 6
    //   134: new 260	java/lang/OutOfMemoryError
    //   137: dup
    //   138: aload 9
    //   140: invokevirtual 264	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   143: invokespecial 267	java/lang/OutOfMemoryError:<init>	(Ljava/lang/String;)V
    //   146: checkcast 76	java/lang/Throwable
    //   149: athrow
    //   150: lload 4
    //   152: l2i
    //   153: istore_2
    //   154: aload 7
    //   156: astore 6
    //   158: iload_2
    //   159: newarray byte
    //   161: astore_0
    //   162: iload_2
    //   163: ifle +83 -> 246
    //   166: aload 7
    //   168: astore 6
    //   170: aload 9
    //   172: aload_0
    //   173: iload_1
    //   174: iload_2
    //   175: invokevirtual 270	java/io/FileInputStream:read	([BII)I
    //   178: istore_3
    //   179: iload_3
    //   180: ifge +55 -> 235
    //   183: goto +63 -> 246
    //   186: aload 7
    //   188: astore 6
    //   190: aload_0
    //   191: iload_1
    //   192: invokestatic 276	java/util/Arrays:copyOf	([BI)[B
    //   195: astore_0
    //   196: aload 7
    //   198: astore 6
    //   200: aload_0
    //   201: ldc_w 278
    //   204: invokestatic 123	kotlin/jvm/internal/Intrinsics:checkExpressionValueIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   207: aload 8
    //   209: aload 7
    //   211: invokestatic 108	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   214: aload_0
    //   215: areturn
    //   216: astore_0
    //   217: goto +9 -> 226
    //   220: astore_0
    //   221: aload_0
    //   222: astore 6
    //   224: aload_0
    //   225: athrow
    //   226: aload 8
    //   228: aload 6
    //   230: invokestatic 108	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   233: aload_0
    //   234: athrow
    //   235: iload_2
    //   236: iload_3
    //   237: isub
    //   238: istore_2
    //   239: iload_1
    //   240: iload_3
    //   241: iadd
    //   242: istore_1
    //   243: goto -81 -> 162
    //   246: iload_2
    //   247: ifne -61 -> 186
    //   250: goto -43 -> 207
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	253	0	paramFile	File
    //   37	206	1	i	int
    //   153	94	2	j	int
    //   178	64	3	k	int
    //   46	105	4	l	long
    //   27	202	6	localObject1	Object
    //   23	187	7	localThrowable	Throwable
    //   17	210	8	localCloseable	java.io.Closeable
    //   34	137	9	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   29	36	216	finally
    //   42	48	216	finally
    //   61	70	216	finally
    //   74	82	216	finally
    //   86	93	216	finally
    //   97	105	216	finally
    //   109	117	216	finally
    //   121	130	216	finally
    //   134	150	216	finally
    //   158	162	216	finally
    //   170	179	216	finally
    //   190	196	216	finally
    //   200	207	216	finally
    //   224	226	216	finally
    //   29	36	220	java/lang/Throwable
    //   42	48	220	java/lang/Throwable
    //   61	70	220	java/lang/Throwable
    //   74	82	220	java/lang/Throwable
    //   86	93	220	java/lang/Throwable
    //   97	105	220	java/lang/Throwable
    //   109	117	220	java/lang/Throwable
    //   121	130	220	java/lang/Throwable
    //   134	150	220	java/lang/Throwable
    //   158	162	220	java/lang/Throwable
    //   170	179	220	java/lang/Throwable
    //   190	196	220	java/lang/Throwable
    //   200	207	220	java/lang/Throwable
  }
  
  @NotNull
  public static final List<String> readLines(@NotNull File paramFile, @NotNull Charset paramCharset)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharset, "charset");
    ArrayList localArrayList = new ArrayList();
    FilesKt.forEachLine(paramFile, paramCharset, (Function1)new Lambda(localArrayList)
    {
      public final void invoke(@NotNull String paramAnonymousString)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousString, "it");
        $result.add(paramAnonymousString);
      }
    });
    return (List)localArrayList;
  }
  
  @NotNull
  public static final String readText(@NotNull File paramFile, @NotNull Charset paramCharset)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramCharset, "charset");
    return new String(FilesKt.readBytes(paramFile), paramCharset);
  }
  
  @InlineOnly
  private static final InputStreamReader reader(@NotNull File paramFile, Charset paramCharset)
  {
    return new InputStreamReader((InputStream)new FileInputStream(paramFile), paramCharset);
  }
  
  /* Error */
  public static final <T> T useLines(@NotNull File paramFile, @NotNull Charset paramCharset, @NotNull Function1<? super kotlin.sequences.Sequence<String>, ? extends T> paramFunction1)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 78
    //   3: invokestatic 84	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc 112
    //   9: invokestatic 84	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   12: aload_2
    //   13: ldc_w 308
    //   16: invokestatic 84	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   19: new 139	java/io/InputStreamReader
    //   22: dup
    //   23: new 141	java/io/FileInputStream
    //   26: dup
    //   27: aload_0
    //   28: invokespecial 144	java/io/FileInputStream:<init>	(Ljava/io/File;)V
    //   31: checkcast 146	java/io/InputStream
    //   34: aload_1
    //   35: invokespecial 149	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/nio/charset/Charset;)V
    //   38: checkcast 151	java/io/Reader
    //   41: astore_0
    //   42: aload_0
    //   43: instanceof 153
    //   46: ifeq +11 -> 57
    //   49: aload_0
    //   50: checkcast 153	java/io/BufferedReader
    //   53: astore_0
    //   54: goto +15 -> 69
    //   57: new 153	java/io/BufferedReader
    //   60: dup
    //   61: aload_0
    //   62: sipush 8192
    //   65: invokespecial 156	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   68: astore_0
    //   69: aload_0
    //   70: checkcast 92	java/io/Closeable
    //   73: astore_3
    //   74: aconst_null
    //   75: checkcast 76	java/lang/Throwable
    //   78: astore_1
    //   79: aload_1
    //   80: astore_0
    //   81: aload_2
    //   82: aload_3
    //   83: checkcast 153	java/io/BufferedReader
    //   86: invokestatic 312	kotlin/io/TextStreamsKt:lineSequence	(Ljava/io/BufferedReader;)Lkotlin/sequences/Sequence;
    //   89: invokeinterface 315 2 0
    //   94: astore_2
    //   95: iconst_1
    //   96: invokestatic 321	kotlin/jvm/internal/InlineMarker:finallyStart	(I)V
    //   99: iconst_1
    //   100: iconst_1
    //   101: iconst_0
    //   102: invokestatic 327	kotlin/internal/PlatformImplementationsKt:apiVersionIsAtLeast	(III)Z
    //   105: ifeq +11 -> 116
    //   108: aload_3
    //   109: aload_1
    //   110: invokestatic 108	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   113: goto +9 -> 122
    //   116: aload_3
    //   117: invokeinterface 330 1 0
    //   122: iconst_1
    //   123: invokestatic 333	kotlin/jvm/internal/InlineMarker:finallyEnd	(I)V
    //   126: aload_2
    //   127: areturn
    //   128: astore_1
    //   129: goto +8 -> 137
    //   132: astore_1
    //   133: aload_1
    //   134: astore_0
    //   135: aload_1
    //   136: athrow
    //   137: iconst_1
    //   138: invokestatic 321	kotlin/jvm/internal/InlineMarker:finallyStart	(I)V
    //   141: iconst_1
    //   142: iconst_1
    //   143: iconst_0
    //   144: invokestatic 327	kotlin/internal/PlatformImplementationsKt:apiVersionIsAtLeast	(III)Z
    //   147: ifeq +11 -> 158
    //   150: aload_3
    //   151: aload_0
    //   152: invokestatic 108	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   155: goto +22 -> 177
    //   158: aload_0
    //   159: ifnonnull +12 -> 171
    //   162: aload_3
    //   163: invokeinterface 330 1 0
    //   168: goto +9 -> 177
    //   171: aload_3
    //   172: invokeinterface 330 1 0
    //   177: iconst_1
    //   178: invokestatic 333	kotlin/jvm/internal/InlineMarker:finallyEnd	(I)V
    //   181: aload_1
    //   182: athrow
    //   183: astore_0
    //   184: goto -7 -> 177
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	187	0	paramFile	File
    //   0	187	1	paramCharset	Charset
    //   0	187	2	paramFunction1	Function1<? super kotlin.sequences.Sequence<String>, ? extends T>
    //   73	99	3	localCloseable	java.io.Closeable
    // Exception table:
    //   from	to	target	type
    //   81	95	128	finally
    //   135	137	128	finally
    //   81	95	132	java/lang/Throwable
    //   171	177	183	java/lang/Throwable
  }
  
  /* Error */
  public static final void writeBytes(@NotNull File paramFile, @NotNull byte[] paramArrayOfByte)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 78
    //   3: invokestatic 84	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc 85
    //   9: invokestatic 84	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   12: new 87	java/io/FileOutputStream
    //   15: dup
    //   16: aload_0
    //   17: invokespecial 163	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   20: checkcast 92	java/io/Closeable
    //   23: astore_3
    //   24: aconst_null
    //   25: checkcast 76	java/lang/Throwable
    //   28: astore_2
    //   29: aload_2
    //   30: astore_0
    //   31: aload_3
    //   32: checkcast 87	java/io/FileOutputStream
    //   35: aload_1
    //   36: invokevirtual 96	java/io/FileOutputStream:write	([B)V
    //   39: aload_2
    //   40: astore_0
    //   41: getstatic 102	kotlin/Unit:INSTANCE	Lkotlin/Unit;
    //   44: astore_1
    //   45: aload_3
    //   46: aload_2
    //   47: invokestatic 108	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   50: return
    //   51: astore_1
    //   52: goto +8 -> 60
    //   55: astore_1
    //   56: aload_1
    //   57: astore_0
    //   58: aload_1
    //   59: athrow
    //   60: aload_3
    //   61: aload_0
    //   62: invokestatic 108	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   65: aload_1
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	paramFile	File
    //   0	67	1	paramArrayOfByte	byte[]
    //   28	19	2	localThrowable	Throwable
    //   23	38	3	localCloseable	java.io.Closeable
    // Exception table:
    //   from	to	target	type
    //   31	39	51	finally
    //   41	45	51	finally
    //   58	60	51	finally
    //   31	39	55	java/lang/Throwable
    //   41	45	55	java/lang/Throwable
  }
  
  public static final void writeText(@NotNull File paramFile, @NotNull String paramString, @NotNull Charset paramCharset)
  {
    Intrinsics.checkParameterIsNotNull(paramFile, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramString, "text");
    Intrinsics.checkParameterIsNotNull(paramCharset, "charset");
    paramString = paramString.getBytes(paramCharset);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).getBytes(charset)");
    FilesKt.writeBytes(paramFile, paramString);
  }
  
  @InlineOnly
  private static final OutputStreamWriter writer(@NotNull File paramFile, Charset paramCharset)
  {
    return new OutputStreamWriter((OutputStream)new FileOutputStream(paramFile), paramCharset);
  }
}

package kotlin.io;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.Reader;
import java.io.StringReader;
import java.io.StringWriter;
import java.io.Writer;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000X\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\002\020\016\n\000\n\002\030\002\n\000\n\002\020\022\n\002\030\002\n\000\n\002\020 \n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\006\032\027\020\000\032\0020\001*\0020\0022\b\b\002\020\003\032\0020\004H\b\032\027\020\000\032\0020\005*\0020\0062\b\b\002\020\003\032\0020\004H\b\032\034\020\007\032\0020\b*\0020\0022\006\020\t\032\0020\0062\b\b\002\020\003\032\0020\004\032\036\020\n\032\0020\013*\0020\0022\022\020\f\032\016\022\004\022\0020\016\022\004\022\0020\0130\r\032\020\020\017\032\b\022\004\022\0020\0160\020*\0020\001\032\n\020\021\032\0020\022*\0020\023\032\020\020\024\032\b\022\004\022\0020\0160\025*\0020\002\032\n\020\026\032\0020\016*\0020\002\032\027\020\026\032\0020\016*\0020\0232\b\b\002\020\027\032\0020\030H\b\032\r\020\031\032\0020\032*\0020\016H\b\0325\020\033\032\002H\034\"\004\b\000\020\034*\0020\0022\030\020\035\032\024\022\n\022\b\022\004\022\0020\0160\020\022\004\022\002H\0340\rH\bø\001\000¢\006\002\020\037\002\b\n\006\b\021(\0360\001¨\006 "}, d2={"buffered", "Ljava/io/BufferedReader;", "Ljava/io/Reader;", "bufferSize", "", "Ljava/io/BufferedWriter;", "Ljava/io/Writer;", "copyTo", "", "out", "forEachLine", "", "action", "Lkotlin/Function1;", "", "lineSequence", "Lkotlin/sequences/Sequence;", "readBytes", "", "Ljava/net/URL;", "readLines", "", "readText", "charset", "Ljava/nio/charset/Charset;", "reader", "Ljava/io/StringReader;", "useLines", "T", "block", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/Reader;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="TextStreamsKt")
public final class TextStreamsKt
{
  @InlineOnly
  private static final BufferedReader buffered(@NotNull Reader paramReader, int paramInt)
  {
    if ((paramReader instanceof BufferedReader)) {
      return (BufferedReader)paramReader;
    }
    return new BufferedReader(paramReader, paramInt);
  }
  
  @InlineOnly
  private static final BufferedWriter buffered(@NotNull Writer paramWriter, int paramInt)
  {
    if ((paramWriter instanceof BufferedWriter)) {
      return (BufferedWriter)paramWriter;
    }
    return new BufferedWriter(paramWriter, paramInt);
  }
  
  public static final long copyTo(@NotNull Reader paramReader, @NotNull Writer paramWriter, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramReader, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramWriter, "out");
    char[] arrayOfChar = new char[paramInt];
    paramInt = paramReader.read(arrayOfChar);
    long l2;
    for (long l1 = 0L; paramInt >= 0; l1 += l2)
    {
      paramWriter.write(arrayOfChar, 0, paramInt);
      l2 = paramInt;
      paramInt = paramReader.read(arrayOfChar);
    }
    return l1;
  }
  
  /* Error */
  public static final void forEachLine(@NotNull Reader paramReader, @NotNull Function1<? super String, Unit> paramFunction1)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 72
    //   3: invokestatic 78	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc 99
    //   9: invokestatic 78	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   12: aload_0
    //   13: instanceof 53
    //   16: ifeq +11 -> 27
    //   19: aload_0
    //   20: checkcast 53	java/io/BufferedReader
    //   23: astore_0
    //   24: goto +15 -> 39
    //   27: new 53	java/io/BufferedReader
    //   30: dup
    //   31: aload_0
    //   32: sipush 8192
    //   35: invokespecial 57	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   38: astore_0
    //   39: aload_0
    //   40: checkcast 101	java/io/Closeable
    //   43: astore_3
    //   44: aconst_null
    //   45: checkcast 98	java/lang/Throwable
    //   48: astore_2
    //   49: aload_2
    //   50: astore_0
    //   51: aload_3
    //   52: checkcast 53	java/io/BufferedReader
    //   55: invokestatic 104	kotlin/io/TextStreamsKt:lineSequence	(Ljava/io/BufferedReader;)Lkotlin/sequences/Sequence;
    //   58: invokeinterface 110 1 0
    //   63: astore 4
    //   65: aload_2
    //   66: astore_0
    //   67: aload 4
    //   69: invokeinterface 116 1 0
    //   74: ifeq +22 -> 96
    //   77: aload_2
    //   78: astore_0
    //   79: aload_1
    //   80: aload 4
    //   82: invokeinterface 120 1 0
    //   87: invokeinterface 126 2 0
    //   92: pop
    //   93: goto -28 -> 65
    //   96: aload_2
    //   97: astore_0
    //   98: getstatic 132	kotlin/Unit:INSTANCE	Lkotlin/Unit;
    //   101: astore_1
    //   102: aload_3
    //   103: aload_2
    //   104: invokestatic 138	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   107: return
    //   108: astore_1
    //   109: goto +8 -> 117
    //   112: astore_1
    //   113: aload_1
    //   114: astore_0
    //   115: aload_1
    //   116: athrow
    //   117: aload_3
    //   118: aload_0
    //   119: invokestatic 138	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   122: aload_1
    //   123: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	paramReader	Reader
    //   0	124	1	paramFunction1	Function1<? super String, Unit>
    //   48	56	2	localThrowable	Throwable
    //   43	75	3	localCloseable	java.io.Closeable
    //   63	18	4	localIterator	java.util.Iterator
    // Exception table:
    //   from	to	target	type
    //   51	65	108	finally
    //   67	77	108	finally
    //   79	93	108	finally
    //   98	102	108	finally
    //   115	117	108	finally
    //   51	65	112	java/lang/Throwable
    //   67	77	112	java/lang/Throwable
    //   79	93	112	java/lang/Throwable
    //   98	102	112	java/lang/Throwable
  }
  
  @NotNull
  public static final Sequence<String> lineSequence(@NotNull BufferedReader paramBufferedReader)
  {
    Intrinsics.checkParameterIsNotNull(paramBufferedReader, "$receiver");
    return SequencesKt.constrainOnce((Sequence)new LinesSequence(paramBufferedReader));
  }
  
  /* Error */
  @NotNull
  public static final byte[] readBytes(@NotNull URL paramURL)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 72
    //   3: invokestatic 78	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_0
    //   7: invokevirtual 159	java/net/URL:openStream	()Ljava/io/InputStream;
    //   10: checkcast 101	java/io/Closeable
    //   13: astore_2
    //   14: aconst_null
    //   15: checkcast 98	java/lang/Throwable
    //   18: astore_1
    //   19: aload_1
    //   20: astore_0
    //   21: aload_2
    //   22: checkcast 161	java/io/InputStream
    //   25: astore_3
    //   26: aload_1
    //   27: astore_0
    //   28: aload_3
    //   29: ldc -93
    //   31: invokestatic 166	kotlin/jvm/internal/Intrinsics:checkExpressionValueIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   34: aload_1
    //   35: astore_0
    //   36: aload_3
    //   37: iconst_0
    //   38: iconst_1
    //   39: aconst_null
    //   40: invokestatic 172	kotlin/io/ByteStreamsKt:readBytes$default	(Ljava/io/InputStream;IILjava/lang/Object;)[B
    //   43: astore_3
    //   44: aload_2
    //   45: aload_1
    //   46: invokestatic 138	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   49: aload_3
    //   50: areturn
    //   51: astore_1
    //   52: goto +8 -> 60
    //   55: astore_1
    //   56: aload_1
    //   57: astore_0
    //   58: aload_1
    //   59: athrow
    //   60: aload_2
    //   61: aload_0
    //   62: invokestatic 138	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   65: aload_1
    //   66: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	67	0	paramURL	URL
    //   18	28	1	localThrowable1	Throwable
    //   51	1	1	localObject1	Object
    //   55	11	1	localThrowable2	Throwable
    //   13	48	2	localCloseable	java.io.Closeable
    //   25	25	3	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   21	26	51	finally
    //   28	34	51	finally
    //   36	44	51	finally
    //   58	60	51	finally
    //   21	26	55	java/lang/Throwable
    //   28	34	55	java/lang/Throwable
    //   36	44	55	java/lang/Throwable
  }
  
  @NotNull
  public static final List<String> readLines(@NotNull Reader paramReader)
  {
    Intrinsics.checkParameterIsNotNull(paramReader, "$receiver");
    ArrayList localArrayList = new ArrayList();
    forEachLine(paramReader, (Function1)new Lambda(localArrayList)
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
  public static final String readText(@NotNull Reader paramReader)
  {
    Intrinsics.checkParameterIsNotNull(paramReader, "$receiver");
    StringWriter localStringWriter = new StringWriter();
    copyTo$default(paramReader, (Writer)localStringWriter, 0, 2, null);
    paramReader = localStringWriter.toString();
    Intrinsics.checkExpressionValueIsNotNull(paramReader, "buffer.toString()");
    return paramReader;
  }
  
  @InlineOnly
  private static final String readText(@NotNull URL paramURL, Charset paramCharset)
  {
    return new String(readBytes(paramURL), paramCharset);
  }
  
  @InlineOnly
  private static final StringReader reader(@NotNull String paramString)
  {
    return new StringReader(paramString);
  }
  
  /* Error */
  public static final <T> T useLines(@NotNull Reader paramReader, @NotNull Function1<? super Sequence<String>, ? extends T> paramFunction1)
  {
    // Byte code:
    //   0: aload_0
    //   1: ldc 72
    //   3: invokestatic 78	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   6: aload_1
    //   7: ldc -36
    //   9: invokestatic 78	kotlin/jvm/internal/Intrinsics:checkParameterIsNotNull	(Ljava/lang/Object;Ljava/lang/String;)V
    //   12: aload_0
    //   13: instanceof 53
    //   16: ifeq +11 -> 27
    //   19: aload_0
    //   20: checkcast 53	java/io/BufferedReader
    //   23: astore_0
    //   24: goto +15 -> 39
    //   27: new 53	java/io/BufferedReader
    //   30: dup
    //   31: aload_0
    //   32: sipush 8192
    //   35: invokespecial 57	java/io/BufferedReader:<init>	(Ljava/io/Reader;I)V
    //   38: astore_0
    //   39: aload_0
    //   40: checkcast 101	java/io/Closeable
    //   43: astore_3
    //   44: aconst_null
    //   45: checkcast 98	java/lang/Throwable
    //   48: astore_2
    //   49: aload_2
    //   50: astore_0
    //   51: aload_1
    //   52: aload_3
    //   53: checkcast 53	java/io/BufferedReader
    //   56: invokestatic 104	kotlin/io/TextStreamsKt:lineSequence	(Ljava/io/BufferedReader;)Lkotlin/sequences/Sequence;
    //   59: invokeinterface 126 2 0
    //   64: astore_1
    //   65: iconst_1
    //   66: invokestatic 226	kotlin/jvm/internal/InlineMarker:finallyStart	(I)V
    //   69: iconst_1
    //   70: iconst_1
    //   71: iconst_0
    //   72: invokestatic 232	kotlin/internal/PlatformImplementationsKt:apiVersionIsAtLeast	(III)Z
    //   75: ifeq +11 -> 86
    //   78: aload_3
    //   79: aload_2
    //   80: invokestatic 138	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   83: goto +9 -> 92
    //   86: aload_3
    //   87: invokeinterface 235 1 0
    //   92: iconst_1
    //   93: invokestatic 238	kotlin/jvm/internal/InlineMarker:finallyEnd	(I)V
    //   96: aload_1
    //   97: areturn
    //   98: astore_1
    //   99: goto +8 -> 107
    //   102: astore_1
    //   103: aload_1
    //   104: astore_0
    //   105: aload_1
    //   106: athrow
    //   107: iconst_1
    //   108: invokestatic 226	kotlin/jvm/internal/InlineMarker:finallyStart	(I)V
    //   111: iconst_1
    //   112: iconst_1
    //   113: iconst_0
    //   114: invokestatic 232	kotlin/internal/PlatformImplementationsKt:apiVersionIsAtLeast	(III)Z
    //   117: ifeq +11 -> 128
    //   120: aload_3
    //   121: aload_0
    //   122: invokestatic 138	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   125: goto +22 -> 147
    //   128: aload_0
    //   129: ifnonnull +12 -> 141
    //   132: aload_3
    //   133: invokeinterface 235 1 0
    //   138: goto +9 -> 147
    //   141: aload_3
    //   142: invokeinterface 235 1 0
    //   147: iconst_1
    //   148: invokestatic 238	kotlin/jvm/internal/InlineMarker:finallyEnd	(I)V
    //   151: aload_1
    //   152: athrow
    //   153: astore_0
    //   154: goto -7 -> 147
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	paramReader	Reader
    //   0	157	1	paramFunction1	Function1<? super Sequence<String>, ? extends T>
    //   48	32	2	localThrowable	Throwable
    //   43	99	3	localCloseable	java.io.Closeable
    // Exception table:
    //   from	to	target	type
    //   51	65	98	finally
    //   105	107	98	finally
    //   51	65	102	java/lang/Throwable
    //   141	147	153	java/lang/Throwable
  }
}

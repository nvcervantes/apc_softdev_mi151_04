package kotlin.io;

import java.io.Closeable;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.jvm.JvmName;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\000\n\002\020\002\n\002\030\002\n\000\n\002\020\003\n\002\b\004\n\002\030\002\n\002\b\003\032\030\020\000\032\0020\001*\004\030\0010\0022\b\020\003\032\004\030\0010\004H\001\032;\020\005\032\002H\006\"\n\b\000\020\007*\004\030\0010\002\"\004\b\001\020\006*\002H\0072\022\020\b\032\016\022\004\022\002H\007\022\004\022\002H\0060\tH\bø\001\000¢\006\002\020\013\002\b\n\006\b\021(\n0\001¨\006\f"}, d2={"closeFinally", "", "Ljava/io/Closeable;", "cause", "", "use", "R", "T", "block", "Lkotlin/Function1;", "Requires newer compiler version to be inlined correctly.", "(Ljava/io/Closeable;Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="CloseableKt")
public final class CloseableKt
{
  @PublishedApi
  @SinceKotlin(version="1.1")
  public static final void closeFinally(@Nullable Closeable paramCloseable, @Nullable Throwable paramThrowable)
  {
    if (paramCloseable == null) {
      return;
    }
    if (paramThrowable == null)
    {
      paramCloseable.close();
      return;
    }
    try
    {
      paramCloseable.close();
      return;
    }
    catch (Throwable paramCloseable)
    {
      ExceptionsKt.addSuppressed(paramThrowable, paramCloseable);
    }
  }
  
  /* Error */
  @kotlin.internal.InlineOnly
  private static final <T extends Closeable, R> R use(T paramT, kotlin.jvm.functions.Function1<? super T, ? extends R> paramFunction1)
  {
    // Byte code:
    //   0: aconst_null
    //   1: checkcast 38	java/lang/Throwable
    //   4: astore_3
    //   5: aload_3
    //   6: astore_2
    //   7: aload_1
    //   8: aload_0
    //   9: invokeinterface 60 2 0
    //   14: astore_1
    //   15: iconst_1
    //   16: invokestatic 66	kotlin/jvm/internal/InlineMarker:finallyStart	(I)V
    //   19: iconst_1
    //   20: iconst_1
    //   21: iconst_0
    //   22: invokestatic 72	kotlin/internal/PlatformImplementationsKt:apiVersionIsAtLeast	(III)Z
    //   25: ifeq +11 -> 36
    //   28: aload_0
    //   29: aload_3
    //   30: invokestatic 74	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   33: goto +16 -> 49
    //   36: aload_0
    //   37: ifnonnull +6 -> 43
    //   40: goto +9 -> 49
    //   43: aload_0
    //   44: invokeinterface 44 1 0
    //   49: iconst_1
    //   50: invokestatic 77	kotlin/jvm/internal/InlineMarker:finallyEnd	(I)V
    //   53: aload_1
    //   54: areturn
    //   55: astore_1
    //   56: goto +8 -> 64
    //   59: astore_1
    //   60: aload_1
    //   61: astore_2
    //   62: aload_1
    //   63: athrow
    //   64: iconst_1
    //   65: invokestatic 66	kotlin/jvm/internal/InlineMarker:finallyStart	(I)V
    //   68: iconst_1
    //   69: iconst_1
    //   70: iconst_0
    //   71: invokestatic 72	kotlin/internal/PlatformImplementationsKt:apiVersionIsAtLeast	(III)Z
    //   74: ifeq +11 -> 85
    //   77: aload_0
    //   78: aload_2
    //   79: invokestatic 74	kotlin/io/CloseableKt:closeFinally	(Ljava/io/Closeable;Ljava/lang/Throwable;)V
    //   82: goto +29 -> 111
    //   85: aload_0
    //   86: ifnonnull +6 -> 92
    //   89: goto +22 -> 111
    //   92: aload_2
    //   93: ifnonnull +12 -> 105
    //   96: aload_0
    //   97: invokeinterface 44 1 0
    //   102: goto +9 -> 111
    //   105: aload_0
    //   106: invokeinterface 44 1 0
    //   111: iconst_1
    //   112: invokestatic 77	kotlin/jvm/internal/InlineMarker:finallyEnd	(I)V
    //   115: aload_1
    //   116: athrow
    //   117: astore_0
    //   118: goto -7 -> 111
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	121	0	paramT	T
    //   0	121	1	paramFunction1	kotlin.jvm.functions.Function1<? super T, ? extends R>
    //   6	87	2	localObject	Object
    //   4	26	3	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   7	15	55	finally
    //   62	64	55	finally
    //   7	15	59	java/lang/Throwable
    //   105	111	117	java/lang/Throwable
  }
}

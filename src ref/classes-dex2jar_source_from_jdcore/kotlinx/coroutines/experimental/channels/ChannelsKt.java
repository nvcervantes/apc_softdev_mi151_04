package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\000\n\002\020\016\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\003\0325\020\002\032\0020\003\"\004\b\000\020\004*\b\022\004\022\002H\0040\0052\022\020\006\032\016\022\004\022\002H\004\022\004\022\0020\0030\007HHø\001\000¢\006\002\020\b\032E\020\002\032\0020\003\"\004\b\000\020\004*\b\022\004\022\002H\0040\0052\"\020\006\032\036\b\001\022\004\022\002H\004\022\n\022\b\022\004\022\0020\0030\n\022\006\022\004\030\0010\0130\tH@ø\001\000¢\006\002\020\f\0325\020\002\032\0020\003\"\004\b\000\020\004*\b\022\004\022\002H\0040\r2\022\020\006\032\016\022\004\022\002H\004\022\004\022\0020\0030\007HHø\001\000¢\006\002\020\016\032E\020\002\032\0020\003\"\004\b\000\020\004*\b\022\004\022\002H\0040\r2\"\020\006\032\036\b\001\022\004\022\002H\004\022\n\022\b\022\004\022\0020\0030\n\022\006\022\004\030\0010\0130\tH@ø\001\000¢\006\002\020\017\"\016\020\000\032\0020\001XT¢\006\002\n\000\002\004\n\002\b\t¨\006\020"}, d2={"DEFAULT_CLOSE_MESSAGE", "", "consumeEach", "", "E", "Lkotlinx/coroutines/experimental/channels/BroadcastChannel;", "action", "Lkotlin/Function1;", "(Lkotlinx/coroutines/experimental/channels/BroadcastChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/Continuation;", "", "(Lkotlinx/coroutines/experimental/channels/BroadcastChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "Lkotlinx/coroutines/experimental/channels/ReceiveChannel;", "(Lkotlinx/coroutines/experimental/channels/ReceiveChannel;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "(Lkotlinx/coroutines/experimental/channels/ReceiveChannel;Lkotlin/jvm/functions/Function2;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class ChannelsKt
{
  @NotNull
  public static final String DEFAULT_CLOSE_MESSAGE = "Channel was closed";
  
  /* Error */
  private static final <E> Object consumeEach(@NotNull BroadcastChannel<E> paramBroadcastChannel, Function1<? super E, Unit> paramFunction1, Continuation<? super Unit> paramContinuation)
  {
    // Byte code:
    //   0: aload_0
    //   1: invokeinterface 46 1 0
    //   6: checkcast 48	java/io/Closeable
    //   9: astore 4
    //   11: iconst_0
    //   12: istore_3
    //   13: aload 4
    //   15: checkcast 50	kotlinx/coroutines/experimental/channels/SubscriptionReceiveChannel
    //   18: invokeinterface 54 1 0
    //   23: astore_0
    //   24: iconst_0
    //   25: invokestatic 60	kotlin/jvm/internal/InlineMarker:mark	(I)V
    //   28: aload_0
    //   29: aload_2
    //   30: invokeinterface 66 2 0
    //   35: astore 5
    //   37: iconst_1
    //   38: invokestatic 60	kotlin/jvm/internal/InlineMarker:mark	(I)V
    //   41: aload 5
    //   43: checkcast 68	java/lang/Boolean
    //   46: invokevirtual 72	java/lang/Boolean:booleanValue	()Z
    //   49: ifeq +32 -> 81
    //   52: iconst_0
    //   53: invokestatic 60	kotlin/jvm/internal/InlineMarker:mark	(I)V
    //   56: aload_0
    //   57: aload_2
    //   58: invokeinterface 75 2 0
    //   63: astore 5
    //   65: iconst_1
    //   66: invokestatic 60	kotlin/jvm/internal/InlineMarker:mark	(I)V
    //   69: aload_1
    //   70: aload 5
    //   72: invokeinterface 81 2 0
    //   77: pop
    //   78: goto -54 -> 24
    //   81: getstatic 87	kotlin/Unit:INSTANCE	Lkotlin/Unit;
    //   84: astore_0
    //   85: iconst_1
    //   86: invokestatic 90	kotlin/jvm/internal/InlineMarker:finallyStart	(I)V
    //   89: aload 4
    //   91: ifnull +10 -> 101
    //   94: aload 4
    //   96: invokeinterface 94 1 0
    //   101: iconst_1
    //   102: invokestatic 97	kotlin/jvm/internal/InlineMarker:finallyEnd	(I)V
    //   105: getstatic 87	kotlin/Unit:INSTANCE	Lkotlin/Unit;
    //   108: areturn
    //   109: astore_0
    //   110: goto +30 -> 140
    //   113: astore_0
    //   114: aload 4
    //   116: ifnull +19 -> 135
    //   119: aload 4
    //   121: invokeinterface 94 1 0
    //   126: goto +9 -> 135
    //   129: astore_0
    //   130: iconst_1
    //   131: istore_3
    //   132: goto +8 -> 140
    //   135: aload_0
    //   136: checkcast 99	java/lang/Throwable
    //   139: athrow
    //   140: iconst_1
    //   141: invokestatic 90	kotlin/jvm/internal/InlineMarker:finallyStart	(I)V
    //   144: iload_3
    //   145: ifne +15 -> 160
    //   148: aload 4
    //   150: ifnull +10 -> 160
    //   153: aload 4
    //   155: invokeinterface 94 1 0
    //   160: iconst_1
    //   161: invokestatic 97	kotlin/jvm/internal/InlineMarker:finallyEnd	(I)V
    //   164: aload_0
    //   165: athrow
    //   166: astore_1
    //   167: goto -32 -> 135
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	170	0	paramBroadcastChannel	BroadcastChannel<E>
    //   0	170	1	paramFunction1	Function1<? super E, Unit>
    //   0	170	2	paramContinuation	Continuation<? super Unit>
    //   12	133	3	i	int
    //   9	145	4	localCloseable	java.io.Closeable
    //   35	36	5	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   13	24	109	finally
    //   24	78	109	finally
    //   81	85	109	finally
    //   13	24	113	java/lang/Exception
    //   24	78	113	java/lang/Exception
    //   81	85	113	java/lang/Exception
    //   119	126	129	finally
    //   135	140	129	finally
    //   119	126	166	java/lang/Exception
  }
  
  private static final <E> Object consumeEach(@NotNull ReceiveChannel<? extends E> paramReceiveChannel, Function1<? super E, Unit> paramFunction1, Continuation<? super Unit> paramContinuation)
  {
    paramReceiveChannel = paramReceiveChannel.iterator();
    for (;;)
    {
      InlineMarker.mark(0);
      Object localObject = paramReceiveChannel.hasNext(paramContinuation);
      InlineMarker.mark(1);
      if (!((Boolean)localObject).booleanValue()) {
        break;
      }
      InlineMarker.mark(0);
      localObject = paramReceiveChannel.next(paramContinuation);
      InlineMarker.mark(1);
      paramFunction1.invoke(localObject);
    }
    return Unit.INSTANCE;
  }
}

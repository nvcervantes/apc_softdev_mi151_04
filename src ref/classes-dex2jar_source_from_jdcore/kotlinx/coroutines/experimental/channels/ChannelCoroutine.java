package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.AbstractCoroutine;
import kotlinx.coroutines.experimental.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.experimental.JobSupport.CompletedExceptionally;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000Z\n\002\030\002\n\000\n\002\030\002\n\002\020\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\n\n\002\020\000\n\000\n\002\020\b\n\002\b\002\n\002\020\003\n\000\n\002\030\002\n\002\b\r\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\b\020\030\000*\004\b\000\020\0012\b\022\004\022\0020\0030\0022\b\022\004\022\002H\0010\004B#\022\006\020\005\032\0020\006\022\f\020\007\032\b\022\004\022\0028\0000\004\022\006\020\b\032\0020\t¢\006\002\020\nJ\032\020\022\032\0020\0032\b\020\023\032\004\030\0010\0242\006\020\025\032\0020\026H\024J\023\020\027\032\0020\t2\b\020\030\032\004\030\0010\031H\001J\017\020\032\032\b\022\004\022\0028\0000\033H\003J\026\020\034\032\0020\t2\006\020\035\032\0028\000H\001¢\006\002\020\036J\020\020\037\032\004\030\0018\000H\001¢\006\002\020 J\023\020!\032\0028\000HAø\001\000¢\006\004\b\"\020#J\025\020$\032\004\030\0018\000HAø\001\000¢\006\004\b%\020#JI\020&\032\0020\003\"\004\b\001\020'2\f\020(\032\b\022\004\022\002H'0)2\"\020*\032\036\b\001\022\004\022\0028\000\022\n\022\b\022\004\022\002H'0,\022\006\022\004\030\0010\0240+H\001ø\001\000¢\006\002\020-JK\020.\032\0020\003\"\004\b\001\020'2\f\020(\032\b\022\004\022\002H'0)2$\020*\032 \b\001\022\006\022\004\030\0018\000\022\n\022\b\022\004\022\002H'0,\022\006\022\004\030\0010\0240+H\001ø\001\000¢\006\002\020-JK\020/\032\0020\003\"\004\b\001\020'2\f\020(\032\b\022\004\022\002H'0)2\006\020\035\032\0028\0002\034\020*\032\030\b\001\022\n\022\b\022\004\022\002H'0,\022\006\022\004\030\0010\02400H\001ø\001\000¢\006\002\0201J\033\0202\032\0020\0032\006\020\035\032\0028\000HAø\001\000¢\006\004\b3\0204R\032\020\007\032\b\022\004\022\0028\0000\004X\004¢\006\b\n\000\032\004\b\013\020\fR\022\020\r\032\0020\tX\005¢\006\006\032\004\b\r\020\016R\022\020\017\032\0020\tX\005¢\006\006\032\004\b\017\020\016R\022\020\020\032\0020\tX\005¢\006\006\032\004\b\020\020\016R\022\020\021\032\0020\tX\005¢\006\006\032\004\b\021\020\016\002\004\n\002\b\t¨\0065"}, d2={"Lkotlinx/coroutines/experimental/channels/ChannelCoroutine;", "E", "Lkotlinx/coroutines/experimental/AbstractCoroutine;", "", "Lkotlinx/coroutines/experimental/channels/Channel;", "parentContext", "Lkotlin/coroutines/experimental/CoroutineContext;", "channel", "active", "", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlinx/coroutines/experimental/channels/Channel;Z)V", "getChannel", "()Lkotlinx/coroutines/experimental/channels/Channel;", "isClosedForReceive", "()Z", "isClosedForSend", "isEmpty", "isFull", "afterCompletion", "state", "", "mode", "", "close", "cause", "", "iterator", "Lkotlinx/coroutines/experimental/channels/ChannelIterator;", "offer", "element", "(Ljava/lang/Object;)Z", "poll", "()Ljava/lang/Object;", "receive", "receive$suspendImpl", "(Lkotlinx/coroutines/experimental/channels/ChannelCoroutine;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "receiveOrNull", "receiveOrNull$suspendImpl", "registerSelectReceive", "R", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectReceiveOrNull", "registerSelectSend", "Lkotlin/Function1;", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "send", "send$suspendImpl", "(Lkotlinx/coroutines/experimental/channels/ChannelCoroutine;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public class ChannelCoroutine<E>
  extends AbstractCoroutine<Unit>
  implements Channel<E>
{
  @NotNull
  private final Channel<E> channel;
  
  public ChannelCoroutine(@NotNull CoroutineContext paramCoroutineContext, @NotNull Channel<E> paramChannel, boolean paramBoolean)
  {
    super(paramCoroutineContext, paramBoolean);
    channel = paramChannel;
  }
  
  protected void afterCompletion(@Nullable Object paramObject, int paramInt)
  {
    boolean bool = paramObject instanceof JobSupport.CompletedExceptionally;
    Object localObject = null;
    if (!bool) {
      paramObject = null;
    }
    JobSupport.CompletedExceptionally localCompletedExceptionally = (JobSupport.CompletedExceptionally)paramObject;
    paramObject = localObject;
    if (localCompletedExceptionally != null) {
      paramObject = cause;
    }
    if ((!getChannel().close(paramObject)) && (paramObject != null)) {
      CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), paramObject);
    }
  }
  
  public boolean close(@Nullable Throwable paramThrowable)
  {
    return channel.close(paramThrowable);
  }
  
  @NotNull
  public Channel<E> getChannel()
  {
    return channel;
  }
  
  public boolean isClosedForReceive()
  {
    return channel.isClosedForReceive();
  }
  
  public boolean isClosedForSend()
  {
    return channel.isClosedForSend();
  }
  
  public boolean isEmpty()
  {
    return channel.isEmpty();
  }
  
  public boolean isFull()
  {
    return channel.isFull();
  }
  
  @NotNull
  public ChannelIterator<E> iterator()
  {
    return channel.iterator();
  }
  
  public boolean offer(E paramE)
  {
    return channel.offer(paramE);
  }
  
  @Nullable
  public E poll()
  {
    return channel.poll();
  }
  
  public Object receive(Continuation<? super E> paramContinuation)
  {
    return receive$suspendImpl(this, paramContinuation);
  }
  
  public Object receiveOrNull(Continuation<? super E> paramContinuation)
  {
    return receiveOrNull$suspendImpl(this, paramContinuation);
  }
  
  public <R> void registerSelectReceive(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    channel.registerSelectReceive(paramSelectInstance, paramFunction2);
  }
  
  public <R> void registerSelectReceiveOrNull(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    channel.registerSelectReceiveOrNull(paramSelectInstance, paramFunction2);
  }
  
  public <R> void registerSelectSend(@NotNull SelectInstance<? super R> paramSelectInstance, E paramE, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    channel.registerSelectSend(paramSelectInstance, paramE, paramFunction1);
  }
  
  public Object send(E paramE, Continuation<? super Unit> paramContinuation)
  {
    return send$suspendImpl(this, paramE, paramContinuation);
  }
}

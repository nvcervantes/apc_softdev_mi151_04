package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.CancellableKt;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000H\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\002\n\002\020\000\n\002\030\002\n\002\b\005\n\002\020\013\n\002\b\006\n\002\030\002\n\002\030\002\n\002\b\004\b\002\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\002BM\022\006\020\003\032\0020\004\022\f\020\005\032\b\022\004\022\0028\0000\006\022-\020\007\032)\b\001\022\n\022\b\022\004\022\0028\0000\t\022\n\022\b\022\004\022\0020\0130\n\022\006\022\004\030\0010\f0\b¢\006\002\b\rø\001\000¢\006\002\020\016J\025\020\022\032\0020\0232\006\020\024\032\0028\000H\026¢\006\002\020\025J\b\020\026\032\0020\013H\024JJ\020\027\032\0020\013\"\004\b\001\020\0302\f\020\031\032\b\022\004\022\002H\0300\0322\006\020\024\032\0028\0002\034\020\007\032\030\b\001\022\n\022\b\022\004\022\002H\0300\n\022\006\022\004\030\0010\f0\033H\026ø\001\000¢\006\002\020\034J\031\020\035\032\0020\0132\006\020\024\032\0028\000H@ø\001\000¢\006\002\020\036R:\020\007\032)\b\001\022\n\022\b\022\004\022\0028\0000\t\022\n\022\b\022\004\022\0020\0130\n\022\006\022\004\030\0010\f0\b¢\006\002\b\rX\004ø\001\000¢\006\004\n\002\020\017R\032\020\005\032\b\022\004\022\0028\0000\0068VX\004¢\006\006\032\004\b\020\020\021\002\004\n\002\b\t¨\006\037"}, d2={"Lkotlinx/coroutines/experimental/channels/LazyActorCoroutine;", "E", "Lkotlinx/coroutines/experimental/channels/ActorCoroutine;", "parentContext", "Lkotlin/coroutines/experimental/CoroutineContext;", "channel", "Lkotlinx/coroutines/experimental/channels/Channel;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/experimental/channels/ActorScope;", "Lkotlin/coroutines/experimental/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlinx/coroutines/experimental/channels/Channel;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "getChannel", "()Lkotlinx/coroutines/experimental/channels/Channel;", "offer", "", "element", "(Ljava/lang/Object;)Z", "onStart", "registerSelectSend", "R", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "Lkotlin/Function1;", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "send", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class LazyActorCoroutine<E>
  extends ActorCoroutine<E>
{
  private final Function2<ActorScope<E>, Continuation<? super Unit>, Object> block;
  
  public LazyActorCoroutine(@NotNull CoroutineContext paramCoroutineContext, @NotNull Channel<E> paramChannel, @NotNull Function2<? super ActorScope<E>, ? super Continuation<? super Unit>, ? extends Object> paramFunction2)
  {
    super(paramCoroutineContext, paramChannel, false);
    block = paramFunction2;
  }
  
  @NotNull
  public Channel<E> getChannel()
  {
    return (Channel)this;
  }
  
  public boolean offer(E paramE)
  {
    start();
    return super.offer(paramE);
  }
  
  protected void onStart()
  {
    CancellableKt.startCoroutineCancellable(block, this, (Continuation)this);
  }
  
  public <R> void registerSelectSend(@NotNull SelectInstance<? super R> paramSelectInstance, E paramE, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    start();
    super.registerSelectSend(paramSelectInstance, paramE, paramFunction1);
  }
  
  @Nullable
  public Object send(E paramE, @NotNull Continuation<? super Unit> paramContinuation)
  {
    start();
    return super.send(paramE, paramContinuation);
  }
}

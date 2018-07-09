package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.CoroutineContextKt;
import kotlinx.coroutines.experimental.CoroutineStart;
import kotlinx.coroutines.experimental.Job;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0008\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\b\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\002\n\002\020\000\n\002\030\002\n\002\b\002\032e\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\003\032\0020\0042\b\b\002\020\005\032\0020\0062\b\b\002\020\007\032\0020\b2-\020\t\032)\b\001\022\n\022\b\022\004\022\002H\0020\013\022\n\022\b\022\004\022\0020\r0\f\022\006\022\004\030\0010\0160\n¢\006\002\b\017ø\001\000¢\006\002\020\020\002\004\n\002\b\t¨\006\021"}, d2={"actor", "Lkotlinx/coroutines/experimental/channels/ActorJob;", "E", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "capacity", "", "start", "Lkotlinx/coroutines/experimental/CoroutineStart;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/experimental/channels/ActorScope;", "Lkotlin/coroutines/experimental/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/experimental/CoroutineContext;ILkotlinx/coroutines/experimental/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/experimental/channels/ActorJob;", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class ActorKt
{
  @NotNull
  public static final <E> ActorJob<E> actor(@NotNull CoroutineContext paramCoroutineContext, int paramInt, @NotNull CoroutineStart paramCoroutineStart, @NotNull Function2<? super ActorScope<E>, ? super Continuation<? super Unit>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramCoroutineStart, "start");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    Object localObject = CoroutineContextKt.newCoroutineContext(paramCoroutineContext);
    Channel localChannel = ChannelKt.Channel(paramInt);
    if (paramCoroutineStart.isLazy()) {
      localObject = (ActorCoroutine)new LazyActorCoroutine((CoroutineContext)localObject, localChannel, paramFunction2);
    } else {
      localObject = new ActorCoroutine((CoroutineContext)localObject, localChannel, true);
    }
    ((ActorCoroutine)localObject).initParentJob((Job)paramCoroutineContext.get((CoroutineContext.Key)Job.Key));
    paramCoroutineStart.invoke(paramFunction2, localObject, (Continuation)localObject);
    return (ActorJob)localObject;
  }
}

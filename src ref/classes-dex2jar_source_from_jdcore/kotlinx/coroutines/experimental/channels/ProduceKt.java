package kotlinx.coroutines.experimental.channels;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.coroutines.experimental.CoroutinesKt;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.CoroutineContextKt;
import kotlinx.coroutines.experimental.Job;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000B\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\b\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\002\n\002\020\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\007\032]\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\003\032\0020\0042\b\b\002\020\005\032\0020\0062-\020\007\032)\b\001\022\n\022\b\022\004\022\002H\0020\t\022\n\022\b\022\004\022\0020\0130\n\022\006\022\004\030\0010\f0\b¢\006\002\b\rH\007ø\001\000¢\006\002\020\016\032[\020\017\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\003\032\0020\0042\b\b\002\020\005\032\0020\0062-\020\007\032)\b\001\022\n\022\b\022\004\022\002H\0020\t\022\n\022\b\022\004\022\0020\0130\n\022\006\022\004\030\0010\f0\b¢\006\002\b\rø\001\000¢\006\002\020\016*J\b\007\020\020\032\004\b\000\020\002\"\b\022\004\022\002H\0020\t2\b\022\004\022\002H\0020\tB*\b\021\022\b\b\022\022\004\b\b(\023\022\034\b\024\022\030\b\013B\024\b\025\022\b\b\026\022\004\b\b(\027\022\006\b\030\022\002\b\f*J\b\007\020\031\032\004\b\000\020\002\"\b\022\004\022\002H\0020\0012\b\022\004\022\002H\0020\001B*\b\021\022\b\b\022\022\004\b\b(\032\022\034\b\024\022\030\b\013B\024\b\025\022\b\b\026\022\004\b\b(\033\022\006\b\030\022\002\b\f\002\004\n\002\b\t¨\006\034"}, d2={"buildChannel", "Lkotlinx/coroutines/experimental/channels/ProducerJob;", "E", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "capacity", "", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/experimental/channels/ProducerScope;", "Lkotlin/coroutines/experimental/Continuation;", "", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/experimental/CoroutineContext;ILkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/experimental/channels/ProducerJob;", "produce", "ChannelBuilder", "Lkotlin/Deprecated;", "message", "Renamed to `ProducerScope`", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "ProducerScope", "imports", "ChannelJob", "Renamed to `ProducerJob`", "ProducerJob", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class ProduceKt
{
  @Deprecated(message="Renamed to `produce`", replaceWith=@ReplaceWith(expression="produce(context, capacity, block)", imports={}))
  @NotNull
  public static final <E> ProducerJob<E> buildChannel(@NotNull CoroutineContext paramCoroutineContext, int paramInt, @NotNull Function2<? super ProducerScope<? super E>, ? super Continuation<? super Unit>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    return produce(paramCoroutineContext, paramInt, paramFunction2);
  }
  
  @NotNull
  public static final <E> ProducerJob<E> produce(@NotNull CoroutineContext paramCoroutineContext, int paramInt, @NotNull Function2<? super ProducerScope<? super E>, ? super Continuation<? super Unit>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    Object localObject = ChannelKt.Channel(paramInt);
    localObject = new ProducerCoroutine(CoroutineContextKt.newCoroutineContext(paramCoroutineContext), (Channel)localObject);
    ((ProducerCoroutine)localObject).initParentJob((Job)paramCoroutineContext.get((CoroutineContext.Key)Job.Key));
    CoroutinesKt.startCoroutine(paramFunction2, localObject, (Continuation)localObject);
    return (ProducerJob)localObject;
  }
}

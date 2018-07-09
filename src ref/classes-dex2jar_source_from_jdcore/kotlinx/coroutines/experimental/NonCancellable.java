package kotlinx.coroutines.experimental;

import java.util.concurrent.CancellationException;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.AbstractCoroutineContextElement;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000P\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\005\n\002\020\003\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\020\002\n\002\030\002\n\002\b\006\n\002\030\002\n\000\n\002\030\002\n\002\020\000\n\002\b\003\bÆ\002\030\0002\0020\0012\0020\002B\007\b\002¢\006\002\020\003J\022\020\t\032\0020\0052\b\020\n\032\004\030\0010\013H\026J\f\020\f\032\0060\rj\002`\016H\026J\"\020\017\032\0020\0202\030\020\021\032\024\022\006\022\004\030\0010\013\022\004\022\0020\0230\022j\002`\024H\026J*\020\017\032\0020\0202\030\020\021\032\024\022\006\022\004\030\0010\013\022\004\022\0020\0230\022j\002`\0242\006\020\025\032\0020\005H\026J\021\020\026\032\0020\023H@ø\001\000¢\006\002\020\027JB\020\030\032\0020\023\"\004\b\000\020\0312\f\020\032\032\b\022\004\022\002H\0310\0332\034\020\034\032\030\b\001\022\n\022\b\022\004\022\002H\0310\035\022\006\022\004\030\0010\0360\022H\026ø\001\000¢\006\002\020\037J\b\020 \032\0020\005H\026R\024\020\004\032\0020\0058VX\004¢\006\006\032\004\b\004\020\006R\024\020\007\032\0020\0058VX\004¢\006\006\032\004\b\007\020\006R\024\020\b\032\0020\0058VX\004¢\006\006\032\004\b\b\020\006\002\004\n\002\b\t¨\006!"}, d2={"Lkotlinx/coroutines/experimental/NonCancellable;", "Lkotlin/coroutines/experimental/AbstractCoroutineContextElement;", "Lkotlinx/coroutines/experimental/Job;", "()V", "isActive", "", "()Z", "isCancelled", "isCompleted", "cancel", "cause", "", "getCompletionException", "Ljava/util/concurrent/CancellationException;", "Lkotlinx/coroutines/experimental/CancellationException;", "invokeOnCompletion", "Lkotlinx/coroutines/experimental/DisposableHandle;", "handler", "Lkotlin/Function1;", "", "Lkotlinx/coroutines/experimental/CompletionHandler;", "onCancelling", "join", "(Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "registerSelectJoin", "R", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/coroutines/experimental/Continuation;", "", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "start", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class NonCancellable
  extends AbstractCoroutineContextElement
  implements Job
{
  public static final NonCancellable INSTANCE;
  
  static
  {
    new NonCancellable();
  }
  
  private NonCancellable()
  {
    super((CoroutineContext.Key)Job.Key);
    INSTANCE = (NonCancellable)this;
  }
  
  public boolean cancel(@Nullable Throwable paramThrowable)
  {
    return false;
  }
  
  @NotNull
  public CancellationException getCompletionException()
  {
    throw ((Throwable)new IllegalStateException("This job is always active"));
  }
  
  @NotNull
  public DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "handler");
    return (DisposableHandle)NonDisposableHandle.INSTANCE;
  }
  
  @NotNull
  public DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> paramFunction1, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "handler");
    return (DisposableHandle)NonDisposableHandle.INSTANCE;
  }
  
  public boolean isActive()
  {
    return true;
  }
  
  public boolean isCancelled()
  {
    return false;
  }
  
  public boolean isCompleted()
  {
    return false;
  }
  
  @Nullable
  public Object join(@NotNull Continuation<? super Unit> paramContinuation)
  {
    throw ((Throwable)new UnsupportedOperationException("This job is always active"));
  }
  
  @Deprecated(level=DeprecationLevel.ERROR, message="Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
  @NotNull
  public Job plus(@NotNull Job paramJob)
  {
    Intrinsics.checkParameterIsNotNull(paramJob, "other");
    return Job.DefaultImpls.plus(this, paramJob);
  }
  
  public <R> void registerSelectJoin(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    throw ((Throwable)new UnsupportedOperationException("This job is always active"));
  }
  
  public boolean start()
  {
    return false;
  }
}

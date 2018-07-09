package kotlinx.coroutines.experimental;

import java.util.concurrent.Future;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000`\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\020\003\n\000\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\004\032\022\020\007\032\0020\b2\n\b\002\020\t\032\004\030\0010\b\032\022\020\n\032\0020\0132\b\020\f\032\004\030\0010\rH\000\032\026\020\016\032\0020\017*\0020\b2\n\020\020\032\006\022\002\b\0030\021\032\022\020\022\032\0020\017*\0020\b2\006\020\023\032\0020\017\032\025\020\024\032\0020\025*\0020\bH@ø\001\000¢\006\002\020\026\032\024\020\027\032\0020\017*\0020\b2\006\020\030\032\0020\017H\007\"\016\020\000\032\0020\001X\004¢\006\002\n\000\"\016\020\002\032\0020\001X\004¢\006\002\n\000\"\016\020\003\032\0020\004XT¢\006\002\n\000\"\016\020\005\032\0020\004XT¢\006\002\n\000\"\016\020\006\032\0020\004XT¢\006\002\n\000*\n\020\031\"\0020\0322\0020\032*&\020\033\"\020\022\006\022\004\030\0010\035\022\004\022\0020\0250\0342\020\022\006\022\004\030\0010\035\022\004\022\0020\0250\034*8\b\007\020\036\"\0020\0372\0020\037B*\b \022\b\b!\022\004\b\b(\"\022\034\b#\022\030\b\013B\024\b$\022\b\b%\022\004\b\b(&\022\006\b'\022\002\b\f\002\004\n\002\b\t¨\006("}, d2={"EmptyActive", "Lkotlinx/coroutines/experimental/Empty;", "EmptyNew", "FALSE", "", "RETRY", "TRUE", "Job", "Lkotlinx/coroutines/experimental/Job;", "parent", "stateToString", "", "state", "", "cancelFutureOnCompletion", "Lkotlinx/coroutines/experimental/DisposableHandle;", "future", "Ljava/util/concurrent/Future;", "disposeOnCompletion", "handle", "join", "", "(Lkotlinx/coroutines/experimental/Job;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "unregisterOnCompletion", "registration", "CancellationException", "Ljava/util/concurrent/CancellationException;", "CompletionHandler", "Lkotlin/Function1;", "", "EmptyRegistration", "Lkotlinx/coroutines/experimental/NonDisposableHandle;", "Lkotlin/Deprecated;", "message", "Replace with `NonDisposableHandle`", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "NonDisposableHandle", "imports", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class JobKt
{
  private static final Empty EmptyActive = new Empty(true);
  private static final Empty EmptyNew = new Empty(false);
  private static final int FALSE = 0;
  private static final int RETRY = -1;
  private static final int TRUE = 1;
  
  @NotNull
  public static final Job Job(@Nullable Job paramJob)
  {
    return (Job)new JobImpl(paramJob);
  }
  
  @NotNull
  public static final DisposableHandle cancelFutureOnCompletion(@NotNull Job paramJob, @NotNull Future<?> paramFuture)
  {
    Intrinsics.checkParameterIsNotNull(paramJob, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFuture, "future");
    return paramJob.invokeOnCompletion((Function1)new CancelFutureOnCompletion(paramJob, paramFuture));
  }
  
  @NotNull
  public static final DisposableHandle disposeOnCompletion(@NotNull Job paramJob, @NotNull DisposableHandle paramDisposableHandle)
  {
    Intrinsics.checkParameterIsNotNull(paramJob, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramDisposableHandle, "handle");
    return paramJob.invokeOnCompletion((Function1)new DisposeOnCompletion(paramJob, paramDisposableHandle));
  }
  
  @Deprecated(message="`join` is now a member function of `Job`")
  @Nullable
  public static final Object join(@NotNull Job paramJob, @NotNull Continuation<? super Unit> paramContinuation)
  {
    return paramJob.join(paramContinuation);
  }
  
  @NotNull
  public static final String stateToString(@Nullable Object paramObject)
  {
    if ((paramObject instanceof JobSupport.Incomplete))
    {
      if (((JobSupport.Incomplete)paramObject).isActive()) {
        return "Active";
      }
      return "New";
    }
    return "Completed";
  }
  
  @Deprecated(message="Renamed to `disposeOnCompletion`", replaceWith=@ReplaceWith(expression="disposeOnCompletion(registration)", imports={}))
  @NotNull
  public static final DisposableHandle unregisterOnCompletion(@NotNull Job paramJob, @NotNull DisposableHandle paramDisposableHandle)
  {
    Intrinsics.checkParameterIsNotNull(paramJob, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramDisposableHandle, "registration");
    return paramJob.invokeOnCompletion((Function1)new DisposeOnCompletion(paramJob, paramDisposableHandle));
  }
}

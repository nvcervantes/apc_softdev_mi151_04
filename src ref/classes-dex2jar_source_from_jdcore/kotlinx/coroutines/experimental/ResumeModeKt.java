package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\036\n\000\n\002\020\b\n\002\b\t\n\002\020\002\n\000\n\002\030\002\n\002\b\005\n\002\020\003\n\000\032+\020\n\032\0020\013\"\004\b\000\020\f*\b\022\004\022\002H\f0\r2\006\020\016\032\002H\f2\006\020\017\032\0020\001¢\006\002\020\020\032&\020\021\032\0020\013\"\004\b\000\020\f*\b\022\004\022\002H\f0\r2\006\020\022\032\0020\0232\006\020\017\032\0020\001\"\026\020\000\032\0020\0018\000XT¢\006\b\n\000\022\004\b\002\020\003\"\026\020\004\032\0020\0018\000XT¢\006\b\n\000\022\004\b\005\020\003\"\026\020\006\032\0020\0018\000XT¢\006\b\n\000\022\004\b\007\020\003\"\026\020\b\032\0020\0018\000XT¢\006\b\n\000\022\004\b\t\020\003¨\006\024"}, d2={"MODE_ATOMIC_DEFAULT", "", "MODE_ATOMIC_DEFAULT$annotations", "()V", "MODE_CANCELLABLE", "MODE_CANCELLABLE$annotations", "MODE_DIRECT", "MODE_DIRECT$annotations", "MODE_UNDISPATCHED", "MODE_UNDISPATCHED$annotations", "resumeMode", "", "T", "Lkotlin/coroutines/experimental/Continuation;", "value", "mode", "(Lkotlin/coroutines/experimental/Continuation;Ljava/lang/Object;I)V", "resumeWithExceptionMode", "exception", "", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class ResumeModeKt
{
  public static final int MODE_ATOMIC_DEFAULT = 0;
  public static final int MODE_CANCELLABLE = 1;
  public static final int MODE_DIRECT = 2;
  public static final int MODE_UNDISPATCHED = 3;
  
  public static final <T> void resumeMode(@NotNull Continuation<? super T> paramContinuation, T paramT, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramContinuation, "$receiver");
    switch (paramInt)
    {
    default: 
      paramContinuation = new StringBuilder();
      paramContinuation.append("Invalid mode ");
      paramContinuation.append(paramInt);
      throw ((Throwable)new IllegalStateException(paramContinuation.toString().toString()));
    case 3: 
      DispatchedContinuation localDispatchedContinuation = (DispatchedContinuation)paramContinuation;
      paramContinuation = CoroutineContextKt.updateContext(localDispatchedContinuation.getContext());
      try
      {
        continuation.resume(paramT);
        paramT = Unit.INSTANCE;
        return;
      }
      finally
      {
        CoroutineContextKt.restoreContext(paramContinuation);
      }
    case 2: 
      CoroutineDispatcherKt.resumeDirect(paramContinuation, paramT);
      return;
    case 1: 
      CoroutineDispatcherKt.resumeCancellable(paramContinuation, paramT);
      return;
    }
    paramContinuation.resume(paramT);
  }
  
  public static final <T> void resumeWithExceptionMode(@NotNull Continuation<? super T> paramContinuation, @NotNull Throwable paramThrowable, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramContinuation, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    switch (paramInt)
    {
    default: 
      paramContinuation = new StringBuilder();
      paramContinuation.append("Invalid mode ");
      paramContinuation.append(paramInt);
      throw ((Throwable)new IllegalStateException(paramContinuation.toString().toString()));
    case 3: 
      DispatchedContinuation localDispatchedContinuation = (DispatchedContinuation)paramContinuation;
      paramContinuation = CoroutineContextKt.updateContext(localDispatchedContinuation.getContext());
      try
      {
        continuation.resumeWithException(paramThrowable);
        paramThrowable = Unit.INSTANCE;
        return;
      }
      finally
      {
        CoroutineContextKt.restoreContext(paramContinuation);
      }
    case 2: 
      CoroutineDispatcherKt.resumeDirectWithException(paramContinuation, paramThrowable);
      return;
    case 1: 
      CoroutineDispatcherKt.resumeCancellableWithException(paramContinuation, paramThrowable);
      return;
    }
    paramContinuation.resumeWithException(paramThrowable);
  }
}

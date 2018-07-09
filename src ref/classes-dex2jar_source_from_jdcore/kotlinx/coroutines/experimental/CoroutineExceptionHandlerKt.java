package kotlinx.coroutines.experimental;

import java.util.concurrent.CancellationException;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.AbstractCoroutineContextElement;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\020\003\n\002\020\002\n\002\b\004\032%\020\000\032\0020\0012\032\b\004\020\002\032\024\022\004\022\0020\004\022\004\022\0020\005\022\004\022\0020\0060\003H\b\032\026\020\007\032\0020\0062\006\020\b\032\0020\0042\006\020\t\032\0020\005¨\006\n"}, d2={"CoroutineExceptionHandler", "Lkotlinx/coroutines/experimental/CoroutineExceptionHandler;", "handler", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/CoroutineContext;", "", "", "handleCoroutineException", "context", "exception", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class CoroutineExceptionHandlerKt
{
  @NotNull
  public static final CoroutineExceptionHandler CoroutineExceptionHandler(@NotNull Function2<? super CoroutineContext, ? super Throwable, Unit> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction2, "handler");
    (CoroutineExceptionHandler)new AbstractCoroutineContextElement(paramFunction2)
    {
      public void handleException(@NotNull CoroutineContext paramAnonymousCoroutineContext, @NotNull Throwable paramAnonymousThrowable)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousCoroutineContext, "context");
        Intrinsics.checkParameterIsNotNull(paramAnonymousThrowable, "exception");
        $handler.invoke(paramAnonymousCoroutineContext, paramAnonymousThrowable);
      }
    };
  }
  
  public static final void handleCoroutineException(@NotNull CoroutineContext paramCoroutineContext, @NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    CoroutineExceptionHandler localCoroutineExceptionHandler = (CoroutineExceptionHandler)paramCoroutineContext.get((CoroutineContext.Key)CoroutineExceptionHandler.Key);
    if (localCoroutineExceptionHandler != null)
    {
      localCoroutineExceptionHandler.handleException(paramCoroutineContext, paramThrowable);
      return;
    }
    if ((paramThrowable instanceof CancellationException)) {
      return;
    }
    paramCoroutineContext = (Job)paramCoroutineContext.get((CoroutineContext.Key)Job.Key);
    boolean bool;
    if (paramCoroutineContext != null) {
      bool = paramCoroutineContext.cancel(paramThrowable);
    } else {
      bool = false;
    }
    if (bool) {
      return;
    }
    paramCoroutineContext = Thread.currentThread();
    paramCoroutineContext.getUncaughtExceptionHandler().uncaughtException(paramCoroutineContext, paramThrowable);
  }
}

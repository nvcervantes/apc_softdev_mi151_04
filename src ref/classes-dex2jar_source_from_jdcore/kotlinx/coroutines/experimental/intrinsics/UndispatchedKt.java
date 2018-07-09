package kotlinx.coroutines.experimental.intrinsics;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000 \n\000\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\020\000\n\002\b\003\n\002\030\002\n\002\b\003\032>\020\000\032\0020\001\"\004\b\000\020\002*\030\b\001\022\n\022\b\022\004\022\002H\0020\004\022\006\022\004\030\0010\0050\0032\f\020\006\032\b\022\004\022\002H\0020\004H\001ø\001\000¢\006\002\020\007\032R\020\000\032\0020\001\"\004\b\000\020\b\"\004\b\001\020\002*\036\b\001\022\004\022\002H\b\022\n\022\b\022\004\022\002H\0020\004\022\006\022\004\030\0010\0050\t2\006\020\n\032\002H\b2\f\020\006\032\b\022\004\022\002H\0020\004H\001ø\001\000¢\006\002\020\013\002\004\n\002\b\t¨\006\f"}, d2={"startCoroutineUndispatched", "", "T", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "", "completion", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)V", "R", "Lkotlin/Function2;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)V", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class UndispatchedKt
{
  public static final <T> void startCoroutineUndispatched(@NotNull Function1<? super Continuation<? super T>, ? extends Object> paramFunction1, @NotNull Continuation<? super T> paramContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramContinuation, "completion");
    try
    {
      paramFunction1 = ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(paramFunction1, 1)).invoke(paramContinuation);
      if (paramFunction1 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        paramContinuation.resume(paramFunction1);
      }
      return;
    }
    catch (Throwable paramFunction1)
    {
      paramContinuation.resumeWithException(paramFunction1);
    }
  }
  
  public static final <R, T> void startCoroutineUndispatched(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> paramFunction2, R paramR, @NotNull Continuation<? super T> paramContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction2, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramContinuation, "completion");
    try
    {
      paramFunction2 = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(paramFunction2, 2)).invoke(paramR, paramContinuation);
      if (paramFunction2 != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        paramContinuation.resume(paramFunction2);
      }
      return;
    }
    catch (Throwable paramFunction2)
    {
      paramContinuation.resumeWithException(paramFunction2);
    }
  }
}

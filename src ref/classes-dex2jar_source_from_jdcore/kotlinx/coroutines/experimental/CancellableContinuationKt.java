package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000.\n\002\b\003\n\002\020\000\n\002\b\003\n\002\020\013\n\000\n\002\030\002\n\002\030\002\n\002\020\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\032\035\020\000\032\002H\001\"\004\b\000\020\0012\b\020\002\032\004\030\0010\003H\003¢\006\002\020\004\032=\020\005\032\002H\001\"\004\b\000\020\0012\b\b\002\020\006\032\0020\0072\032\b\004\020\b\032\024\022\n\022\b\022\004\022\002H\0010\n\022\004\022\0020\0130\tHHø\001\000¢\006\002\020\f\032=\020\r\032\002H\001\"\004\b\000\020\0012\b\b\002\020\006\032\0020\0072\032\b\004\020\b\032\024\022\n\022\b\022\004\022\002H\0010\n\022\004\022\0020\0130\tHHø\001\000¢\006\002\020\f\032\026\020\016\032\0020\017*\006\022\002\b\0030\n2\006\020\020\032\0020\021\002\004\n\002\b\t¨\006\022"}, d2={"getSuccessfulResult", "T", "state", "", "(Ljava/lang/Object;)Ljava/lang/Object;", "suspendAtomicCancellableCoroutine", "holdCancellability", "", "block", "Lkotlin/Function1;", "Lkotlinx/coroutines/experimental/CancellableContinuation;", "", "(ZLkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "suspendCancellableCoroutine", "removeOnCancel", "Lkotlinx/coroutines/experimental/DisposableHandle;", "node", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class CancellableContinuationKt
{
  private static final <T> T getSuccessfulResult(Object paramObject)
  {
    Object localObject = paramObject;
    if ((paramObject instanceof CompletedIdempotentResult)) {
      localObject = result;
    }
    return localObject;
  }
  
  @NotNull
  public static final DisposableHandle removeOnCancel(@NotNull CancellableContinuation<?> paramCancellableContinuation, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode)
  {
    Intrinsics.checkParameterIsNotNull(paramCancellableContinuation, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "node");
    return paramCancellableContinuation.invokeOnCompletion((Function1)new RemoveOnCancel(paramCancellableContinuation, paramLockFreeLinkedListNode));
  }
  
  private static final <T> Object suspendAtomicCancellableCoroutine(boolean paramBoolean, Function1<? super CancellableContinuation<? super T>, Unit> paramFunction1, Continuation<? super T> paramContinuation)
  {
    InlineMarker.mark(0);
    paramContinuation = new CancellableContinuationImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation), 0);
    if (!paramBoolean) {
      paramContinuation.initCancellability();
    }
    paramFunction1.invoke(paramContinuation);
    paramFunction1 = paramContinuation.getResult();
    InlineMarker.mark(1);
    return paramFunction1;
  }
  
  private static final <T> Object suspendCancellableCoroutine(boolean paramBoolean, Function1<? super CancellableContinuation<? super T>, Unit> paramFunction1, Continuation<? super T> paramContinuation)
  {
    InlineMarker.mark(0);
    paramContinuation = new CancellableContinuationImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation), 1);
    if (!paramBoolean) {
      paramContinuation.initCancellability();
    }
    paramFunction1.invoke(paramContinuation);
    paramFunction1 = paramContinuation.getResult();
    InlineMarker.mark(1);
    return paramFunction1;
  }
}

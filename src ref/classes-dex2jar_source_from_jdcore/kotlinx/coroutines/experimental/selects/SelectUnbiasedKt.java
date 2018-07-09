package kotlinx.coroutines.experimental.selects;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;

@Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\b\003\n\002\030\002\n\002\030\002\n\002\020\002\n\002\030\002\n\002\b\002\0328\020\000\032\002H\001\"\004\b\000\020\0012\037\b\004\020\002\032\031\022\n\022\b\022\004\022\002H\0010\004\022\004\022\0020\0050\003¢\006\002\b\006HHø\001\000¢\006\002\020\007\002\004\n\002\b\t¨\006\b"}, d2={"selectUnbiased", "R", "builder", "Lkotlin/Function1;", "Lkotlinx/coroutines/experimental/selects/SelectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class SelectUnbiasedKt
{
  private static final <R> Object selectUnbiased(Function1<? super SelectBuilder<? super R>, Unit> paramFunction1, Continuation<? super R> paramContinuation)
  {
    InlineMarker.mark(0);
    paramContinuation = new UnbiasedSelectBuilderImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation));
    try
    {
      paramFunction1.invoke(paramContinuation);
    }
    catch (Throwable paramFunction1)
    {
      paramContinuation.handleBuilderException(paramFunction1);
    }
    paramFunction1 = paramContinuation.initSelectResult();
    InlineMarker.mark(1);
    return paramFunction1;
  }
}

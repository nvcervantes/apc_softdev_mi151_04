package kotlinx.coroutines.experimental.selects;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlinx.coroutines.experimental.internal.Symbol;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\036\n\000\n\002\020\000\n\002\b\007\n\002\030\002\n\002\030\002\n\002\020\002\n\002\030\002\n\002\b\002\0328\020\006\032\002H\007\"\004\b\000\020\0072\037\b\004\020\b\032\031\022\n\022\b\022\004\022\002H\0070\n\022\004\022\0020\0130\t¢\006\002\b\fHHø\001\000¢\006\002\020\r\"\024\020\000\032\0020\001X\004¢\006\b\n\000\032\004\b\002\020\003\"\016\020\004\032\0020\001X\004¢\006\002\n\000\"\016\020\005\032\0020\001X\004¢\006\002\n\000\002\004\n\002\b\t¨\006\016"}, d2={"ALREADY_SELECTED", "", "getALREADY_SELECTED", "()Ljava/lang/Object;", "RESUMED", "UNDECIDED", "select", "R", "builder", "Lkotlin/Function1;", "Lkotlinx/coroutines/experimental/selects/SelectBuilder;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class SelectKt
{
  @NotNull
  private static final Object ALREADY_SELECTED = new Symbol("ALREADY_SELECTED");
  private static final Object RESUMED = new Symbol("RESUMED");
  private static final Object UNDECIDED = new Symbol("UNDECIDED");
  
  @NotNull
  public static final Object getALREADY_SELECTED()
  {
    return ALREADY_SELECTED;
  }
  
  private static final <R> Object select(Function1<? super SelectBuilder<? super R>, Unit> paramFunction1, Continuation<? super R> paramContinuation)
  {
    InlineMarker.mark(0);
    paramContinuation = new SelectBuilderImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation));
    try
    {
      paramFunction1.invoke(paramContinuation);
    }
    catch (Throwable paramFunction1)
    {
      paramContinuation.handleBuilderException(paramFunction1);
    }
    paramFunction1 = paramContinuation.getResult();
    InlineMarker.mark(1);
    return paramFunction1;
  }
}

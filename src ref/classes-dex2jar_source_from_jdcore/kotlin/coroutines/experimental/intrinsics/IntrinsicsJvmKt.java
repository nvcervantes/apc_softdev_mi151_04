package kotlin.coroutines.experimental.intrinsics;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.coroutines.experimental.Continuation;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000 \n\000\n\002\020\000\n\000\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\003\032A\020\000\032\004\030\0010\001\"\004\b\000\020\002*\030\b\001\022\n\022\b\022\004\022\002H\0020\004\022\006\022\004\030\0010\0010\0032\f\020\005\032\b\022\004\022\002H\0020\004H\bø\001\000¢\006\002\020\006\032Z\020\000\032\004\030\0010\001\"\004\b\000\020\007\"\004\b\001\020\002*#\b\001\022\004\022\002H\007\022\n\022\b\022\004\022\002H\0020\004\022\006\022\004\030\0010\0010\b¢\006\002\b\t2\006\020\n\032\002H\0072\f\020\005\032\b\022\004\022\002H\0020\004H\bø\001\000¢\006\002\020\013\002\004\n\002\b\t¨\006\f"}, d2={"startCoroutineUninterceptedOrReturn", "", "T", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "completion", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
public final class IntrinsicsJvmKt
{
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <T> Object startCoroutineUninterceptedOrReturn(@NotNull Function1<? super Continuation<? super T>, ? extends Object> paramFunction1, Continuation<? super T> paramContinuation)
  {
    if (paramFunction1 == null) {
      throw new TypeCastException("null cannot be cast to non-null type (kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
    }
    return ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(paramFunction1, 1)).invoke(paramContinuation);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <R, T> Object startCoroutineUninterceptedOrReturn(@NotNull Function2<? super R, ? super Continuation<? super T>, ? extends Object> paramFunction2, R paramR, Continuation<? super T> paramContinuation)
  {
    if (paramFunction2 == null) {
      throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
    }
    return ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(paramFunction2, 2)).invoke(paramR, paramContinuation);
  }
}

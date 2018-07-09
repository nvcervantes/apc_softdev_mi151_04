package kotlin.coroutines.experimental.intrinsics;

import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.jvm.internal.CoroutineImpl;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0008\n\000\n\002\020\000\n\002\b\005\n\002\030\002\n\002\b\004\n\002\030\002\n\002\020\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\002\030\002\n\002\b\004\0325\020\013\032\b\022\004\022\0020\r0\f\"\004\b\000\020\0162\f\020\017\032\b\022\004\022\002H\0160\f2\020\b\004\020\020\032\n\022\006\022\004\030\0010\0010\021H\b\0325\020\022\032\002H\016\"\004\b\000\020\0162\034\b\004\020\020\032\026\022\n\022\b\022\004\022\002H\0160\f\022\006\022\004\030\0010\0010\023HHø\001\000¢\006\002\020\024\0325\020\025\032\002H\016\"\004\b\000\020\0162\034\b\004\020\020\032\026\022\n\022\b\022\004\022\002H\0160\f\022\006\022\004\030\0010\0010\023HHø\001\000¢\006\002\020\024\032D\020\026\032\b\022\004\022\0020\r0\f\"\004\b\000\020\016*\030\b\001\022\n\022\b\022\004\022\002H\0160\f\022\006\022\004\030\0010\0010\0232\f\020\017\032\b\022\004\022\002H\0160\fH\007ø\001\000¢\006\002\020\027\032]\020\026\032\b\022\004\022\0020\r0\f\"\004\b\000\020\030\"\004\b\001\020\016*#\b\001\022\004\022\002H\030\022\n\022\b\022\004\022\002H\0160\f\022\006\022\004\030\0010\0010\031¢\006\002\b\0322\006\020\033\032\002H\0302\f\020\017\032\b\022\004\022\002H\0160\fH\007ø\001\000¢\006\002\020\034\032\037\020\035\032\b\022\004\022\002H\0160\f\"\004\b\000\020\016*\b\022\004\022\002H\0160\fH\b\"\034\020\000\032\0020\0018\006X\004¢\006\016\n\000\022\004\b\002\020\003\032\004\b\004\020\005\"\033\020\006\032\0020\0078Æ\002X\004¢\006\f\022\004\b\b\020\003\032\004\b\t\020\n\002\004\n\002\b\t¨\006\036"}, d2={"COROUTINE_SUSPENDED", "", "COROUTINE_SUSPENDED$annotations", "()V", "getCOROUTINE_SUSPENDED", "()Ljava/lang/Object;", "coroutineContext", "Lkotlin/coroutines/experimental/CoroutineContext;", "coroutineContext$annotations", "getCoroutineContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "buildContinuationByInvokeCall", "Lkotlin/coroutines/experimental/Continuation;", "", "T", "completion", "block", "Lkotlin/Function0;", "suspendCoroutineOrReturn", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "suspendCoroutineUninterceptedOrReturn", "createCoroutineUnchecked", "(Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "R", "Lkotlin/Function2;", "Lkotlin/ExtensionFunctionType;", "receiver", "(Lkotlin/jvm/functions/Function2;Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Lkotlin/coroutines/experimental/Continuation;", "intercepted", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="IntrinsicsKt")
public final class IntrinsicsKt
{
  @NotNull
  private static final Object COROUTINE_SUSPENDED = new Object();
  
  private static final <T> Continuation<Unit> buildContinuationByInvokeCall(Continuation<? super T> paramContinuation, final Function0<? extends Object> paramFunction0)
  {
    paramFunction0 = new Continuation()
    {
      @NotNull
      public CoroutineContext getContext()
      {
        return $completion.getContext();
      }
      
      public void resume(@NotNull Unit paramAnonymousUnit)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousUnit, "value");
        paramAnonymousUnit = $completion;
        try
        {
          Object localObject = paramFunction0.invoke();
          if (localObject != IntrinsicsKt.getCOROUTINE_SUSPENDED())
          {
            if (paramAnonymousUnit == null) {
              throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
            }
            paramAnonymousUnit.resume(localObject);
            return;
          }
        }
        catch (Throwable localThrowable)
        {
          paramAnonymousUnit.resumeWithException(localThrowable);
        }
      }
      
      public void resumeWithException(@NotNull Throwable paramAnonymousThrowable)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousThrowable, "exception");
        $completion.resumeWithException(paramAnonymousThrowable);
      }
    };
    return CoroutineIntrinsics.interceptContinuationIfNeeded(paramContinuation.getContext(), (Continuation)paramFunction0);
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T> Continuation<Unit> createCoroutineUnchecked(@NotNull final Function1<? super Continuation<? super T>, ? extends Object> paramFunction1, @NotNull final Continuation<? super T> paramContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramContinuation, "completion");
    if (!(paramFunction1 instanceof CoroutineImpl))
    {
      paramFunction1 = new Continuation()
      {
        @NotNull
        public CoroutineContext getContext()
        {
          return $completion.getContext();
        }
        
        public void resume(@NotNull Unit paramAnonymousUnit)
        {
          Intrinsics.checkParameterIsNotNull(paramAnonymousUnit, "value");
          paramAnonymousUnit = $completion;
          try
          {
            Object localObject = paramFunction1;
            if (localObject == null) {
              throw new TypeCastException("null cannot be cast to non-null type (kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
            }
            localObject = ((Function1)TypeIntrinsics.beforeCheckcastToFunctionOfArity(localObject, 1)).invoke(paramContinuation);
            if (localObject != IntrinsicsKt.getCOROUTINE_SUSPENDED())
            {
              if (paramAnonymousUnit == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
              }
              paramAnonymousUnit.resume(localObject);
              return;
            }
          }
          catch (Throwable localThrowable)
          {
            paramAnonymousUnit.resumeWithException(localThrowable);
          }
        }
        
        public void resumeWithException(@NotNull Throwable paramAnonymousThrowable)
        {
          Intrinsics.checkParameterIsNotNull(paramAnonymousThrowable, "exception");
          $completion.resumeWithException(paramAnonymousThrowable);
        }
      };
      return CoroutineIntrinsics.interceptContinuationIfNeeded(paramContinuation.getContext(), (Continuation)paramFunction1);
    }
    paramFunction1 = ((CoroutineImpl)paramFunction1).create(paramContinuation);
    if (paramFunction1 == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
    }
    return ((CoroutineImpl)paramFunction1).getFacade();
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <R, T> Continuation<Unit> createCoroutineUnchecked(@NotNull final Function2<? super R, ? super Continuation<? super T>, ? extends Object> paramFunction2, final R paramR, @NotNull final Continuation<? super T> paramContinuation)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction2, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramContinuation, "completion");
    if (!(paramFunction2 instanceof CoroutineImpl))
    {
      paramFunction2 = new Continuation()
      {
        @NotNull
        public CoroutineContext getContext()
        {
          return $completion.getContext();
        }
        
        public void resume(@NotNull Unit paramAnonymousUnit)
        {
          Intrinsics.checkParameterIsNotNull(paramAnonymousUnit, "value");
          paramAnonymousUnit = $completion;
          try
          {
            Object localObject = paramFunction2;
            if (localObject == null) {
              throw new TypeCastException("null cannot be cast to non-null type (R, kotlin.coroutines.experimental.Continuation<T>) -> kotlin.Any?");
            }
            localObject = ((Function2)TypeIntrinsics.beforeCheckcastToFunctionOfArity(localObject, 2)).invoke(paramR, paramContinuation);
            if (localObject != IntrinsicsKt.getCOROUTINE_SUSPENDED())
            {
              if (paramAnonymousUnit == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.Continuation<kotlin.Any?>");
              }
              paramAnonymousUnit.resume(localObject);
              return;
            }
          }
          catch (Throwable localThrowable)
          {
            paramAnonymousUnit.resumeWithException(localThrowable);
          }
        }
        
        public void resumeWithException(@NotNull Throwable paramAnonymousThrowable)
        {
          Intrinsics.checkParameterIsNotNull(paramAnonymousThrowable, "exception");
          $completion.resumeWithException(paramAnonymousThrowable);
        }
      };
      return CoroutineIntrinsics.interceptContinuationIfNeeded(paramContinuation.getContext(), (Continuation)paramFunction2);
    }
    paramFunction2 = ((CoroutineImpl)paramFunction2).create(paramR, paramContinuation);
    if (paramFunction2 == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.coroutines.experimental.jvm.internal.CoroutineImpl");
    }
    return ((CoroutineImpl)paramFunction2).getFacade();
  }
  
  @NotNull
  public static final Object getCOROUTINE_SUSPENDED()
  {
    return COROUTINE_SUSPENDED;
  }
  
  @NotNull
  public static final CoroutineContext getCoroutineContext()
  {
    throw ((Throwable)new NotImplementedError("Implemented as intrinsic"));
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final <T> Continuation<T> intercepted(@NotNull Continuation<? super T> paramContinuation)
  {
    throw ((Throwable)new NotImplementedError("Implementation of intercepted is intrinsic"));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <T> Object suspendCoroutineOrReturn(Function1<? super Continuation<? super T>, ? extends Object> paramFunction1, Continuation<? super T> paramContinuation)
  {
    InlineMarker.mark(0);
    paramFunction1 = paramFunction1.invoke(CoroutineIntrinsics.normalizeContinuation(paramContinuation));
    InlineMarker.mark(1);
    return paramFunction1;
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final <T> Object suspendCoroutineUninterceptedOrReturn(Function1<? super Continuation<? super T>, ? extends Object> paramFunction1, Continuation<? super T> paramContinuation)
  {
    throw ((Throwable)new NotImplementedError("Implementation of suspendCoroutineUninterceptedOrReturn is intrinsic"));
  }
}

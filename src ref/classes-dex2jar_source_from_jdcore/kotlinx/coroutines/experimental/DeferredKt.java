package kotlinx.coroutines.experimental;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0004\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\032U\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\003\032\0020\0042\006\020\005\032\0020\0062'\020\007\032#\b\001\022\004\022\0020\t\022\n\022\b\022\004\022\002H\0020\n\022\006\022\004\030\0010\0130\b¢\006\002\b\fH\007ø\001\000¢\006\002\020\r\032U\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\003\032\0020\0042\b\b\002\020\005\032\0020\0162'\020\007\032#\b\001\022\004\022\0020\t\022\n\022\b\022\004\022\002H\0020\n\022\006\022\004\030\0010\0130\b¢\006\002\b\fø\001\000¢\006\002\020\017\032M\020\020\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\003\032\0020\0042'\020\007\032#\b\001\022\004\022\0020\t\022\n\022\b\022\004\022\002H\0020\n\022\006\022\004\030\0010\0130\b¢\006\002\b\fH\007ø\001\000¢\006\002\020\021\002\004\n\002\b\t¨\006\022"}, d2={"async", "Lkotlinx/coroutines/experimental/Deferred;", "T", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "start", "", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/experimental/CoroutineScope;", "Lkotlin/coroutines/experimental/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/experimental/CoroutineContext;ZLkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/experimental/Deferred;", "Lkotlinx/coroutines/experimental/CoroutineStart;", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlinx/coroutines/experimental/CoroutineStart;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/experimental/Deferred;", "defer", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/experimental/Deferred;", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class DeferredKt
{
  @NotNull
  public static final <T> Deferred<T> async(@NotNull CoroutineContext paramCoroutineContext, @NotNull CoroutineStart paramCoroutineStart, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramCoroutineStart, "start");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    Object localObject = CoroutineContextKt.newCoroutineContext(paramCoroutineContext);
    if (paramCoroutineStart.isLazy()) {
      localObject = (DeferredCoroutine)new LazyDeferredCoroutine((CoroutineContext)localObject, paramFunction2);
    } else {
      localObject = new DeferredCoroutine((CoroutineContext)localObject, true);
    }
    ((DeferredCoroutine)localObject).initParentJob((Job)paramCoroutineContext.get((CoroutineContext.Key)Job.Key));
    paramCoroutineStart.invoke(paramFunction2, localObject, (Continuation)localObject);
    return (Deferred)localObject;
  }
  
  @Deprecated(message="Use `start = CoroutineStart.XXX` parameter", replaceWith=@ReplaceWith(expression="async(context, if (start) CoroutineStart.DEFAULT else CoroutineStart.LAZY, block)", imports={}))
  @NotNull
  public static final <T> Deferred<T> async(@NotNull CoroutineContext paramCoroutineContext, boolean paramBoolean, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    CoroutineStart localCoroutineStart;
    if (paramBoolean) {
      localCoroutineStart = CoroutineStart.DEFAULT;
    } else {
      localCoroutineStart = CoroutineStart.LAZY;
    }
    return async(paramCoroutineContext, localCoroutineStart, paramFunction2);
  }
  
  @Deprecated(level=DeprecationLevel.WARNING, message="`defer` was renamed to `async`", replaceWith=@ReplaceWith(expression="async(context, block = block)", imports={}))
  @NotNull
  public static final <T> Deferred<T> defer(@NotNull CoroutineContext paramCoroutineContext, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    return async$default(paramCoroutineContext, null, paramFunction2, 2, null);
  }
}

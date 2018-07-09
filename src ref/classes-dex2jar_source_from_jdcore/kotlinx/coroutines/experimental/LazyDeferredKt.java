package kotlinx.coroutines.experimental;

import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000@\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\032M\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\003\032\0020\0042'\020\005\032#\b\001\022\004\022\0020\007\022\n\022\b\022\004\022\002H\0020\b\022\006\022\004\030\0010\t0\006¢\006\002\b\nH\007ø\001\000¢\006\002\020\013*V\b\007\020\f\032\004\b\000\020\002\"\b\022\004\022\002H\0020\0012\b\022\004\022\002H\0020\001B6\b\r\022\b\b\016\022\004\b\b(\017\022\n\b\020\022\006\b\n0\0218\022\022\034\b\023\022\030\b\013B\024\b\024\022\b\b\025\022\004\b\b(\026\022\006\b\027\022\002\b\f\002\004\n\002\b\t¨\006\030"}, d2={"lazyDefer", "Lkotlinx/coroutines/experimental/Deferred;", "T", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "block", "Lkotlin/Function2;", "Lkotlinx/coroutines/experimental/CoroutineScope;", "Lkotlin/coroutines/experimental/Continuation;", "", "Lkotlin/ExtensionFunctionType;", "(Lkotlin/coroutines/experimental/CoroutineContext;Lkotlin/jvm/functions/Function2;)Lkotlinx/coroutines/experimental/Deferred;", "LazyDeferred", "Lkotlin/Deprecated;", "message", "`Deferred` incorporates functionality of `LazyDeferred`", "level", "Lkotlin/DeprecationLevel;", "WARNING", "replaceWith", "Lkotlin/ReplaceWith;", "expression", "Deferred", "imports", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class LazyDeferredKt
{
  @Deprecated(level=DeprecationLevel.WARNING, message="This functionality is incorporated into `async", replaceWith=@ReplaceWith(expression="async(context, start = false, block = block)", imports={}))
  @NotNull
  public static final <T> Deferred<T> lazyDefer(@NotNull CoroutineContext paramCoroutineContext, @NotNull Function2<? super CoroutineScope, ? super Continuation<? super T>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    return DeferredKt.async(paramCoroutineContext, false, paramFunction2);
  }
}

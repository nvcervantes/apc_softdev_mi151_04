package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Element;
import kotlin.coroutines.experimental.CoroutineContext.Element.DefaultImpls;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\036\n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\003\n\002\b\002\bf\030\000 \b2\0020\001:\001\bJ\030\020\002\032\0020\0032\006\020\004\032\0020\0052\006\020\006\032\0020\007H&¨\006\t"}, d2={"Lkotlinx/coroutines/experimental/CoroutineExceptionHandler;", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "handleException", "", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "exception", "", "Key", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface CoroutineExceptionHandler
  extends CoroutineContext.Element
{
  public static final Key Key = new Key(null);
  
  public abstract void handleException(@NotNull CoroutineContext paramCoroutineContext, @NotNull Throwable paramThrowable);
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls
  {
    public static <R> R fold(CoroutineExceptionHandler paramCoroutineExceptionHandler, @NotNull R paramR, Function2<? super R, ? super CoroutineContext.Element, ? extends R> paramFunction2)
    {
      Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
      return CoroutineContext.Element.DefaultImpls.fold((CoroutineContext.Element)paramCoroutineExceptionHandler, paramR, paramFunction2);
    }
    
    @Nullable
    public static <E extends CoroutineContext.Element> E get(@NotNull CoroutineExceptionHandler paramCoroutineExceptionHandler, CoroutineContext.Key<E> paramKey)
    {
      Intrinsics.checkParameterIsNotNull(paramKey, "key");
      return CoroutineContext.Element.DefaultImpls.get((CoroutineContext.Element)paramCoroutineExceptionHandler, paramKey);
    }
    
    @NotNull
    public static CoroutineContext minusKey(@NotNull CoroutineExceptionHandler paramCoroutineExceptionHandler, CoroutineContext.Key<?> paramKey)
    {
      Intrinsics.checkParameterIsNotNull(paramKey, "key");
      return CoroutineContext.Element.DefaultImpls.minusKey((CoroutineContext.Element)paramCoroutineExceptionHandler, paramKey);
    }
    
    @NotNull
    public static CoroutineContext plus(@NotNull CoroutineExceptionHandler paramCoroutineExceptionHandler, CoroutineContext paramCoroutineContext)
    {
      Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
      return CoroutineContext.Element.DefaultImpls.plus((CoroutineContext.Element)paramCoroutineExceptionHandler, paramCoroutineContext);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\030\002\n\002\020\003\n\002\020\002\n\000\b\003\030\0002\b\022\004\022\0020\0020\001B\007\b\002¢\006\002\020\003J%\020\004\032\0020\0022\032\b\004\020\005\032\024\022\004\022\0020\007\022\004\022\0020\b\022\004\022\0020\t0\006H\n¨\006\n"}, d2={"Lkotlinx/coroutines/experimental/CoroutineExceptionHandler$Key;", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "Lkotlinx/coroutines/experimental/CoroutineExceptionHandler;", "()V", "invoke", "handler", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/CoroutineContext;", "", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static final class Key
    implements CoroutineContext.Key<CoroutineExceptionHandler>
  {
    private Key() {}
  }
}

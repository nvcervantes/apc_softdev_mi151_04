package kotlin.coroutines.experimental;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\004\b'\030\0002\0020\001B\021\022\n\020\002\032\006\022\002\b\0030\003¢\006\002\020\004R\030\020\002\032\006\022\002\b\0030\003X\004¢\006\b\n\000\032\004\b\005\020\006¨\006\007"}, d2={"Lkotlin/coroutines/experimental/AbstractCoroutineContextElement;", "Lkotlin/coroutines/experimental/CoroutineContext$Element;", "key", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "(Lkotlin/coroutines/experimental/CoroutineContext$Key;)V", "getKey", "()Lkotlin/coroutines/experimental/CoroutineContext$Key;", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
@SinceKotlin(version="1.1")
public abstract class AbstractCoroutineContextElement
  implements CoroutineContext.Element
{
  @NotNull
  private final CoroutineContext.Key<?> key;
  
  public AbstractCoroutineContextElement(@NotNull CoroutineContext.Key<?> paramKey)
  {
    key = paramKey;
  }
  
  public <R> R fold(R paramR, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
    return CoroutineContext.Element.DefaultImpls.fold(this, paramR, paramFunction2);
  }
  
  @Nullable
  public <E extends CoroutineContext.Element> E get(@NotNull CoroutineContext.Key<E> paramKey)
  {
    Intrinsics.checkParameterIsNotNull(paramKey, "key");
    return CoroutineContext.Element.DefaultImpls.get(this, paramKey);
  }
  
  @NotNull
  public CoroutineContext.Key<?> getKey()
  {
    return key;
  }
  
  @NotNull
  public CoroutineContext minusKey(@NotNull CoroutineContext.Key<?> paramKey)
  {
    Intrinsics.checkParameterIsNotNull(paramKey, "key");
    return CoroutineContext.Element.DefaultImpls.minusKey(this, paramKey);
  }
  
  @NotNull
  public CoroutineContext plus(@NotNull CoroutineContext paramCoroutineContext)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    return CoroutineContext.Element.DefaultImpls.plus(this, paramCoroutineContext);
  }
}

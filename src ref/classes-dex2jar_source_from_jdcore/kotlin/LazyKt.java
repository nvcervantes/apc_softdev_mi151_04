package kotlin;

import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.reflect.KProperty;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\000\n\000\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\002\032\"\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\f\020\003\032\b\022\004\022\002H\0020\004H\007\032,\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\b\020\005\032\004\030\0010\0062\f\020\003\032\b\022\004\022\002H\0020\004H\007\032*\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\007\032\0020\b2\f\020\003\032\b\022\004\022\002H\0020\004H\007\032\037\020\t\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\n\032\002H\002¢\006\002\020\013\0324\020\f\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0012\b\020\r\032\004\030\0010\0062\n\020\016\032\006\022\002\b\0030\017H\n¢\006\002\020\020¨\006\021"}, d2={"lazy", "Lkotlin/Lazy;", "T", "initializer", "Lkotlin/Function0;", "lock", "", "mode", "Lkotlin/LazyThreadSafetyMode;", "lazyOf", "value", "(Ljava/lang/Object;)Lkotlin/Lazy;", "getValue", "thisRef", "property", "Lkotlin/reflect/KProperty;", "(Lkotlin/Lazy;Ljava/lang/Object;Lkotlin/reflect/KProperty;)Ljava/lang/Object;", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="LazyKt")
public final class LazyKt
{
  @InlineOnly
  private static final <T> T getValue(@NotNull Lazy<? extends T> paramLazy, Object paramObject, KProperty<?> paramKProperty)
  {
    Intrinsics.checkParameterIsNotNull(paramLazy, "$receiver");
    return paramLazy.getValue();
  }
  
  @NotNull
  public static final <T> Lazy<T> lazy(@Nullable Object paramObject, @NotNull Function0<? extends T> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction0, "initializer");
    return (Lazy)new SynchronizedLazyImpl(paramFunction0, paramObject);
  }
  
  @NotNull
  public static final <T> Lazy<T> lazy(@NotNull LazyThreadSafetyMode paramLazyThreadSafetyMode, @NotNull Function0<? extends T> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramLazyThreadSafetyMode, "mode");
    Intrinsics.checkParameterIsNotNull(paramFunction0, "initializer");
    switch (LazyKt.WhenMappings.$EnumSwitchMapping$0[paramLazyThreadSafetyMode.ordinal()])
    {
    default: 
      throw new NoWhenBranchMatchedException();
    case 3: 
      return (Lazy)new UnsafeLazyImpl(paramFunction0);
    case 2: 
      return (Lazy)new SafePublicationLazyImpl(paramFunction0);
    }
    return (Lazy)new SynchronizedLazyImpl(paramFunction0, null, 2, null);
  }
  
  @NotNull
  public static final <T> Lazy<T> lazy(@NotNull Function0<? extends T> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction0, "initializer");
    return (Lazy)new SynchronizedLazyImpl(paramFunction0, null, 2, null);
  }
  
  @NotNull
  public static final <T> Lazy<T> lazyOf(T paramT)
  {
    return (Lazy)new InitializedLazyImpl(paramT);
  }
}

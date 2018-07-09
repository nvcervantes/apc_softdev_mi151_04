package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\000\n\002\b\006\n\002\020\013\n\000\n\002\020\016\n\002\b\002\b\003\030\000*\006\b\000\020\001 \0012\b\022\004\022\002H\0010\0022\0060\003j\002`\004B\037\022\f\020\005\032\b\022\004\022\0028\0000\006\022\n\b\002\020\007\032\004\030\0010\b¢\006\002\020\tJ\b\020\016\032\0020\017H\026J\b\020\020\032\0020\021H\026J\b\020\022\032\0020\bH\002R\024\020\n\032\004\030\0010\b8\002@\002X\016¢\006\002\n\000R\026\020\005\032\n\022\004\022\0028\000\030\0010\006X\016¢\006\002\n\000R\016\020\007\032\0020\bX\004¢\006\002\n\000R\024\020\013\032\0028\0008VX\004¢\006\006\032\004\b\f\020\r¨\006\023"}, d2={"Lkotlin/SynchronizedLazyImpl;", "T", "Lkotlin/Lazy;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "initializer", "Lkotlin/Function0;", "lock", "", "(Lkotlin/jvm/functions/Function0;Ljava/lang/Object;)V", "_value", "value", "getValue", "()Ljava/lang/Object;", "isInitialized", "", "toString", "", "writeReplace", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
final class SynchronizedLazyImpl<T>
  implements Lazy<T>, Serializable
{
  private volatile Object _value;
  private Function0<? extends T> initializer;
  private final Object lock;
  
  public SynchronizedLazyImpl(@NotNull Function0<? extends T> paramFunction0, @Nullable Object paramObject)
  {
    initializer = paramFunction0;
    _value = UNINITIALIZED_VALUE.INSTANCE;
    if (paramObject != null) {
      paramFunction0 = paramObject;
    } else {
      paramFunction0 = this;
    }
    lock = paramFunction0;
  }
  
  private final Object writeReplace()
  {
    return new InitializedLazyImpl(getValue());
  }
  
  public T getValue()
  {
    Object localObject1 = _value;
    if (localObject1 != UNINITIALIZED_VALUE.INSTANCE) {
      return localObject1;
    }
    synchronized (lock)
    {
      localObject1 = _value;
      if (localObject1 == UNINITIALIZED_VALUE.INSTANCE)
      {
        localObject1 = initializer;
        if (localObject1 == null) {
          Intrinsics.throwNpe();
        }
        localObject1 = ((Function0)localObject1).invoke();
        _value = localObject1;
        initializer = ((Function0)null);
      }
      return localObject1;
    }
  }
  
  public boolean isInitialized()
  {
    return _value != UNINITIALIZED_VALUE.INSTANCE;
  }
  
  @NotNull
  public String toString()
  {
    if (isInitialized()) {
      return String.valueOf(getValue());
    }
    return "Lazy value not initialized yet.";
  }
}

package kotlin;

import java.io.Serializable;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\000\n\002\b\004\n\002\020\013\n\000\n\002\020\016\n\002\b\002\b\000\030\000*\006\b\000\020\001 \0012\b\022\004\022\002H\0010\0022\0060\003j\002`\004B\023\022\f\020\005\032\b\022\004\022\0028\0000\006¢\006\002\020\007J\b\020\r\032\0020\016H\026J\b\020\017\032\0020\020H\026J\b\020\021\032\0020\tH\002R\020\020\b\032\004\030\0010\tX\016¢\006\002\n\000R\026\020\005\032\n\022\004\022\0028\000\030\0010\006X\016¢\006\002\n\000R\024\020\n\032\0028\0008VX\004¢\006\006\032\004\b\013\020\f¨\006\022"}, d2={"Lkotlin/UnsafeLazyImpl;", "T", "Lkotlin/Lazy;", "Ljava/io/Serializable;", "Lkotlin/io/Serializable;", "initializer", "Lkotlin/Function0;", "(Lkotlin/jvm/functions/Function0;)V", "_value", "", "value", "getValue", "()Ljava/lang/Object;", "isInitialized", "", "toString", "", "writeReplace", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public final class UnsafeLazyImpl<T>
  implements Lazy<T>, Serializable
{
  private Object _value;
  private Function0<? extends T> initializer;
  
  public UnsafeLazyImpl(@NotNull Function0<? extends T> paramFunction0)
  {
    initializer = paramFunction0;
    _value = UNINITIALIZED_VALUE.INSTANCE;
  }
  
  private final Object writeReplace()
  {
    return new InitializedLazyImpl(getValue());
  }
  
  public T getValue()
  {
    if (_value == UNINITIALIZED_VALUE.INSTANCE)
    {
      Function0 localFunction0 = initializer;
      if (localFunction0 == null) {
        Intrinsics.throwNpe();
      }
      _value = localFunction0.invoke();
      initializer = ((Function0)null);
    }
    return _value;
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

package kotlinx.coroutines.experimental;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\020\016\n\000\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\003\032\0020\004H\026J\b\020\005\032\0020\006H\026¨\006\007"}, d2={"Lkotlinx/coroutines/experimental/NonDisposableHandle;", "Lkotlinx/coroutines/experimental/DisposableHandle;", "()V", "dispose", "", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class NonDisposableHandle
  implements DisposableHandle
{
  public static final NonDisposableHandle INSTANCE;
  
  static
  {
    new NonDisposableHandle();
  }
  
  private NonDisposableHandle()
  {
    INSTANCE = (NonDisposableHandle)this;
  }
  
  public void dispose() {}
  
  @NotNull
  public String toString()
  {
    return "NonDisposableHandle";
  }
  
  @Deprecated(message="Replace with `dispose`", replaceWith=@ReplaceWith(expression="dispose()", imports={}))
  public void unregister()
  {
    DisposableHandle.DefaultImpls.unregister(this);
  }
}

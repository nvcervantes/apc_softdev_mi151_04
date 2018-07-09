package kotlinx.coroutines.experimental;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\002\b\002\bg\030\0002\0020\001J\b\020\002\032\0020\003H&J\b\020\004\032\0020\003H\027¨\006\005"}, d2={"Lkotlinx/coroutines/experimental/DisposableHandle;", "Lkotlinx/coroutines/experimental/Job$Registration;", "dispose", "", "unregister", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface DisposableHandle
  extends Job.Registration
{
  public abstract void dispose();
  
  @Deprecated(message="Replace with `dispose`", replaceWith=@ReplaceWith(expression="dispose()", imports={}))
  public abstract void unregister();
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 7})
  public static final class DefaultImpls
  {
    @Deprecated(message="Replace with `dispose`", replaceWith=@ReplaceWith(expression="dispose()", imports={}))
    public static void unregister(DisposableHandle paramDisposableHandle)
    {
      paramDisposableHandle.dispose();
    }
  }
}

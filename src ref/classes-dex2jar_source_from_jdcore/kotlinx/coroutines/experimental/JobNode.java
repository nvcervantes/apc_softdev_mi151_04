package kotlinx.coroutines.experimental;

import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020\003\n\002\020\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\006\b \030\000*\n\b\000\020\001 \001*\0020\0022\0020\0032\0020\0042\024\022\006\022\004\030\0010\006\022\004\022\0020\0070\005j\002`\b2\0020\tB\r\022\006\020\n\032\0028\000¢\006\002\020\013J\006\020\020\032\0020\007J\023\020\021\032\0020\0072\b\020\022\032\004\030\0010\006H¦\002R\021\020\f\032\0020\r8F¢\006\006\032\004\b\f\020\016R\022\020\n\032\0028\0008\006X\004¢\006\004\n\002\020\017¨\006\023"}, d2={"Lkotlinx/coroutines/experimental/JobNode;", "J", "Lkotlinx/coroutines/experimental/Job;", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/DisposableHandle;", "Lkotlin/Function1;", "", "", "Lkotlinx/coroutines/experimental/CompletionHandler;", "Lkotlinx/coroutines/experimental/JobSupport$Incomplete;", "job", "(Lkotlinx/coroutines/experimental/Job;)V", "isActive", "", "()Z", "Lkotlinx/coroutines/experimental/Job;", "dispose", "invoke", "reason", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract class JobNode<J extends Job>
  extends LockFreeLinkedListNode
  implements DisposableHandle, Function1<Throwable, Unit>, JobSupport.Incomplete
{
  @JvmField
  @NotNull
  public final J job;
  
  public JobNode(@NotNull J paramJ)
  {
    job = paramJ;
  }
  
  public final void dispose()
  {
    Job localJob = job;
    if (localJob == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.JobSupport");
    }
    ((JobSupport)localJob).removeNode$kotlinx_coroutines_core(this);
  }
  
  public abstract void invoke(@Nullable Throwable paramThrowable);
  
  public final boolean isActive()
  {
    return true;
  }
  
  @Deprecated(message="Replace with `dispose`", replaceWith=@ReplaceWith(expression="dispose()", imports={}))
  public void unregister()
  {
    DisposableHandle.DefaultImpls.unregister(this);
  }
}

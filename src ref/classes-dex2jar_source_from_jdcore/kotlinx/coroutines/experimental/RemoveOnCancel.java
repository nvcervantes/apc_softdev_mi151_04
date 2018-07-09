package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\002\n\000\n\002\020\003\n\000\n\002\020\016\n\000\b\002\030\0002\f\022\b\022\006\022\002\b\0030\0020\001B\031\022\n\020\003\032\006\022\002\b\0030\002\022\006\020\004\032\0020\005¢\006\002\020\006J\023\020\t\032\0020\n2\b\020\013\032\004\030\0010\fH\002J\b\020\r\032\0020\016H\026R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\007\020\b¨\006\017"}, d2={"Lkotlinx/coroutines/experimental/RemoveOnCancel;", "Lkotlinx/coroutines/experimental/JobNode;", "Lkotlinx/coroutines/experimental/CancellableContinuation;", "cont", "node", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "(Lkotlinx/coroutines/experimental/CancellableContinuation;Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;)V", "getNode", "()Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "invoke", "", "reason", "", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class RemoveOnCancel
  extends JobNode<CancellableContinuation<?>>
{
  @NotNull
  private final LockFreeLinkedListNode node;
  
  public RemoveOnCancel(@NotNull CancellableContinuation<?> paramCancellableContinuation, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode)
  {
    super((Job)paramCancellableContinuation);
    node = paramLockFreeLinkedListNode;
  }
  
  @NotNull
  public final LockFreeLinkedListNode getNode()
  {
    return node;
  }
  
  public void invoke(@Nullable Throwable paramThrowable)
  {
    if (((CancellableContinuation)job).isCancelled()) {
      node.remove();
    }
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("RemoveOnCancel[");
    localStringBuilder.append(node);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}

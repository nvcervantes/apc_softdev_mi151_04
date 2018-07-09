package kotlinx.coroutines.experimental.internal;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\016\n\000\b\002\030\0002\0020\001B\021\022\n\020\002\032\0060\003j\002`\004¢\006\002\020\005J\b\020\006\032\0020\007H\026R\024\020\002\032\0060\003j\002`\0048\006X\004¢\006\002\n\000¨\006\b"}, d2={"Lkotlinx/coroutines/experimental/internal/Removed;", "", "ref", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/internal/Node;", "(Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;)V", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class Removed
{
  @JvmField
  @NotNull
  public final LockFreeLinkedListNode ref;
  
  public Removed(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode)
  {
    ref = paramLockFreeLinkedListNode;
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Removed[");
    localStringBuilder.append(ref);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}

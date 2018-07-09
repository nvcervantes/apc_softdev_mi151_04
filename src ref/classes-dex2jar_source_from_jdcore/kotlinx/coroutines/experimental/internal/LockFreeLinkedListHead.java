package kotlinx.coroutines.experimental.internal;

import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0004\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\002\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\001\n\002\b\003\b\026\030\0002\0020\001B\005¢\006\002\020\002J\b\020\006\032\004\030\0010\007J-\020\b\032\0020\t\"\016\b\000\020\n\030\001*\0060\001j\002`\0132\022\020\f\032\016\022\004\022\002H\n\022\004\022\0020\t0\rH\bJ\006\020\016\032\0020\017J\r\020\020\032\0020\tH\000¢\006\002\b\021R\021\020\003\032\0020\0048F¢\006\006\032\004\b\003\020\005¨\006\022"}, d2={"Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "()V", "isEmpty", "", "()Z", "describeRemove", "Lkotlinx/coroutines/experimental/internal/AtomicDesc;", "forEach", "", "T", "Lkotlinx/coroutines/experimental/internal/Node;", "block", "Lkotlin/Function1;", "remove", "", "validate", "validate$kotlinx_coroutines_core", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public class LockFreeLinkedListHead
  extends LockFreeLinkedListNode
{
  public LockFreeLinkedListHead() {}
  
  private final <T extends LockFreeLinkedListNode> void forEach(Function1<? super T, Unit> paramFunction1)
  {
    Object localObject = getNext();
    if (localObject == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
    }
    for (localObject = (LockFreeLinkedListNode)localObject; (Intrinsics.areEqual(localObject, (LockFreeLinkedListHead)this) ^ true); localObject = LockFreeLinkedListKt.unwrap(((LockFreeLinkedListNode)localObject).getNext()))
    {
      Intrinsics.reifiedOperationMarker(3, "T");
      if ((localObject instanceof LockFreeLinkedListNode)) {
        paramFunction1.invoke(localObject);
      }
    }
  }
  
  @Nullable
  public final AtomicDesc describeRemove()
  {
    throw ((Throwable)new UnsupportedOperationException());
  }
  
  public final boolean isEmpty()
  {
    return getNext() == (LockFreeLinkedListHead)this;
  }
  
  @NotNull
  public final Void remove()
  {
    throw ((Throwable)new UnsupportedOperationException());
  }
  
  public final void validate$kotlinx_coroutines_core()
  {
    Object localObject2 = (LockFreeLinkedListNode)this;
    Object localObject1 = getNext();
    if (localObject1 == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
    }
    LockFreeLinkedListNode localLockFreeLinkedListNode;
    for (localObject1 = (LockFreeLinkedListNode)localObject1; (Intrinsics.areEqual(localObject1, (LockFreeLinkedListHead)this) ^ true); localObject1 = localLockFreeLinkedListNode)
    {
      localLockFreeLinkedListNode = LockFreeLinkedListKt.unwrap(((LockFreeLinkedListNode)localObject1).getNext());
      ((LockFreeLinkedListNode)localObject1).validateNode$kotlinx_coroutines_core((LockFreeLinkedListNode)localObject2, localLockFreeLinkedListNode);
      localObject2 = localObject1;
    }
    localObject1 = getNext();
    if (localObject1 == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
    }
    validateNode$kotlinx_coroutines_core((LockFreeLinkedListNode)localObject2, (LockFreeLinkedListNode)localObject1);
  }
}

package kotlinx.coroutines.experimental.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.TypeCastException;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000r\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020\013\n\002\b\b\n\002\020\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\b\n\002\020\016\n\000\n\002\020\b\n\002\b\b\b\027\030\0002\0020\001:\004<=>?B\005¢\006\002\020\002J\022\020\021\032\0020\0222\n\020\023\032\0060\000j\002`\024J%\020\025\032\0020\t2\n\020\023\032\0060\000j\002`\0242\016\b\004\020\026\032\b\022\004\022\0020\t0\027H\bJ-\020\030\032\0020\t2\n\020\023\032\0060\000j\002`\0242\026\020\031\032\022\022\b\022\0060\000j\002`\024\022\004\022\0020\t0\032H\bJ=\020\033\032\0020\t2\n\020\023\032\0060\000j\002`\0242\026\020\031\032\022\022\b\022\0060\000j\002`\024\022\004\022\0020\t0\0322\016\b\004\020\026\032\b\022\004\022\0020\t0\027H\bJ \020\034\032\0020\t2\n\020\023\032\0060\000j\002`\0242\n\020\f\032\0060\000j\002`\024H\001J\022\020\035\032\0020\t2\n\020\023\032\0060\000j\002`\024J&\020\036\032\n\030\0010\000j\004\030\001`\0242\n\020\005\032\0060\000j\002`\0242\b\020\037\032\004\030\0010 H\002J'\020!\032\b\022\004\022\002H#0\"\"\f\b\000\020#*\0060\000j\002`\0242\006\020\023\032\002H#¢\006\002\020$J\n\020%\032\004\030\0010&H\026J\020\020'\032\f\022\b\022\0060\000j\002`\0240(J\024\020)\032\0020\0222\n\020\f\032\0060\000j\002`\024H\002J\024\020*\032\0020\0222\n\020\f\032\0060\000j\002`\024H\002J\b\020+\032\0020\022H\001J%\020,\032\0020-2\n\020\023\032\0060\000j\002`\0242\016\b\004\020\026\032\b\022\004\022\0020\t0\027H\bJ\f\020.\032\0060\000j\002`\024H\002J\b\020/\032\0020\tH\026J\030\0200\032\004\030\001H#\"\006\b\000\020#\030\001H\b¢\006\002\020\016J,\0201\032\004\030\001H#\"\006\b\000\020#\030\0012\022\020\031\032\016\022\004\022\002H#\022\004\022\0020\t0\032H\b¢\006\002\0202J\016\0203\032\n\030\0010\000j\004\030\001`\024J\b\0204\032\0020\007H\002J\b\0205\032\00206H\026J(\0207\032\002082\n\020\023\032\0060\000j\002`\0242\n\020\f\032\0060\000j\002`\0242\006\0209\032\0020-H\001J%\020:\032\0020\0222\n\020\017\032\0060\000j\002`\0242\n\020\f\032\0060\000j\002`\024H\000¢\006\002\b;R\024\020\003\032\b\022\004\022\0020\0010\004X\004¢\006\002\n\000R\024\020\005\032\b\022\004\022\0020\0010\004X\004¢\006\002\n\000R\026\020\006\032\n\022\006\022\004\030\0010\0070\004X\004¢\006\002\n\000R\021\020\b\032\0020\t8F¢\006\006\032\004\b\b\020\nR\021\020\013\032\0020\t8F¢\006\006\032\004\b\013\020\nR\021\020\f\032\0020\0018F¢\006\006\032\004\b\r\020\016R\021\020\017\032\0020\0018F¢\006\006\032\004\b\020\020\016¨\006@"}, d2={"Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "", "()V", "_next", "Lkotlinx/atomicfu/AtomicRef;", "_prev", "_removedRef", "Lkotlinx/coroutines/experimental/internal/Removed;", "isFresh", "", "()Z", "isRemoved", "next", "getNext", "()Ljava/lang/Object;", "prev", "getPrev", "addLast", "", "node", "Lkotlinx/coroutines/experimental/internal/Node;", "addLastIf", "condition", "Lkotlin/Function0;", "addLastIfPrev", "predicate", "Lkotlin/Function1;", "addLastIfPrevAndIf", "addNext", "addOneIfEmpty", "correctPrev", "op", "Lkotlinx/coroutines/experimental/internal/OpDescriptor;", "describeAddLast", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AddLastDesc;", "T", "(Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;)Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AddLastDesc;", "describeRemove", "Lkotlinx/coroutines/experimental/internal/AtomicDesc;", "describeRemoveFirst", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "finishAdd", "finishRemove", "helpDelete", "makeCondAddOp", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$CondAddOp;", "markPrev", "remove", "removeFirstIfIsInstanceOf", "removeFirstIfIsInstanceOfOrPeekIf", "(Lkotlin/jvm/functions/Function1;)Ljava/lang/Object;", "removeFirstOrNull", "removed", "toString", "", "tryCondAddNext", "", "condAdd", "validateNode", "validateNode$kotlinx_coroutines_core", "AbstractAtomicDesc", "AddLastDesc", "CondAddOp", "RemoveFirstDesc", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public class LockFreeLinkedListNode
{
  static final AtomicReferenceFieldUpdater _next$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_next");
  static final AtomicReferenceFieldUpdater _prev$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_prev");
  private static final AtomicReferenceFieldUpdater _removedRef$FU = AtomicReferenceFieldUpdater.newUpdater(LockFreeLinkedListNode.class, Object.class, "_removedRef");
  volatile Object _next = this;
  volatile Object _prev = this;
  private volatile Object _removedRef = null;
  
  public LockFreeLinkedListNode() {}
  
  private final LockFreeLinkedListNode correctPrev(LockFreeLinkedListNode paramLockFreeLinkedListNode, OpDescriptor paramOpDescriptor)
  {
    LockFreeLinkedListNode localLockFreeLinkedListNode2 = (LockFreeLinkedListNode)null;
    LockFreeLinkedListNode localLockFreeLinkedListNode1 = localLockFreeLinkedListNode2;
    Object localObject2;
    label149:
    do
    {
      for (;;)
      {
        Object localObject1 = _next;
        if (localObject1 == paramOpDescriptor) {
          return paramLockFreeLinkedListNode;
        }
        if ((localObject1 instanceof OpDescriptor))
        {
          ((OpDescriptor)localObject1).perform(paramLockFreeLinkedListNode);
        }
        else
        {
          if ((localObject1 instanceof Removed))
          {
            if (localLockFreeLinkedListNode1 != null)
            {
              paramLockFreeLinkedListNode.markPrev();
              _next$FU.compareAndSet(localLockFreeLinkedListNode1, paramLockFreeLinkedListNode, ref);
              paramLockFreeLinkedListNode = localLockFreeLinkedListNode1;
              break;
            }
            paramLockFreeLinkedListNode = LockFreeLinkedListKt.unwrap(_prev);
            continue;
          }
          localObject2 = _prev;
          if ((localObject2 instanceof Removed)) {
            return null;
          }
          if (localObject1 == (LockFreeLinkedListNode)this) {
            break label149;
          }
          if (localObject1 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
          }
          localObject1 = (LockFreeLinkedListNode)localObject1;
          localLockFreeLinkedListNode1 = paramLockFreeLinkedListNode;
          paramLockFreeLinkedListNode = (LockFreeLinkedListNode)localObject1;
        }
      }
      if (localObject2 == paramLockFreeLinkedListNode) {
        return null;
      }
    } while ((!_prev$FU.compareAndSet(this, localObject2, paramLockFreeLinkedListNode)) || ((_prev instanceof Removed)));
    return null;
  }
  
  private final void finishAdd(LockFreeLinkedListNode paramLockFreeLinkedListNode)
  {
    Object localObject;
    do
    {
      localObject = _prev;
      if ((localObject instanceof Removed)) {
        break;
      }
      if (getNext() != paramLockFreeLinkedListNode) {
        return;
      }
    } while (!_prev$FU.compareAndSet(paramLockFreeLinkedListNode, localObject, this));
    if ((getNext() instanceof Removed))
    {
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      paramLockFreeLinkedListNode.correctPrev((LockFreeLinkedListNode)localObject, null);
    }
    return;
  }
  
  private final void finishRemove(LockFreeLinkedListNode paramLockFreeLinkedListNode)
  {
    helpDelete();
    paramLockFreeLinkedListNode.correctPrev(LockFreeLinkedListKt.unwrap(_prev), null);
  }
  
  private final LockFreeLinkedListNode markPrev()
  {
    Object localObject;
    LockFreeLinkedListNode localLockFreeLinkedListNode;
    Removed localRemoved;
    do
    {
      localObject = _prev;
      if ((localObject instanceof Removed)) {
        return ref;
      }
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      localLockFreeLinkedListNode = (LockFreeLinkedListNode)localObject;
      localRemoved = localLockFreeLinkedListNode.removed();
    } while (!_prev$FU.compareAndSet(this, localObject, localRemoved));
    return localLockFreeLinkedListNode;
  }
  
  private final <T> T removeFirstIfIsInstanceOf()
  {
    for (;;)
    {
      Object localObject = getNext();
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      localObject = (LockFreeLinkedListNode)localObject;
      if (localObject == (LockFreeLinkedListNode)this) {
        return null;
      }
      Intrinsics.reifiedOperationMarker(3, "T");
      if (!(localObject instanceof Object)) {
        return null;
      }
      if (((LockFreeLinkedListNode)localObject).remove()) {
        return localObject;
      }
      ((LockFreeLinkedListNode)localObject).helpDelete();
    }
  }
  
  private final <T> T removeFirstIfIsInstanceOfOrPeekIf(Function1<? super T, Boolean> paramFunction1)
  {
    for (;;)
    {
      Object localObject = getNext();
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      localObject = (LockFreeLinkedListNode)localObject;
      if (localObject == (LockFreeLinkedListNode)this) {
        return null;
      }
      Intrinsics.reifiedOperationMarker(3, "T");
      if (!(localObject instanceof Object)) {
        return null;
      }
      if (((Boolean)paramFunction1.invoke(localObject)).booleanValue()) {
        return localObject;
      }
      if (((LockFreeLinkedListNode)localObject).remove()) {
        return localObject;
      }
      ((LockFreeLinkedListNode)localObject).helpDelete();
    }
  }
  
  private final Removed removed()
  {
    Removed localRemoved = (Removed)_removedRef;
    if (localRemoved != null) {
      return localRemoved;
    }
    localRemoved = new Removed(this);
    _removedRef$FU.lazySet(this, localRemoved);
    return localRemoved;
  }
  
  public final void addLast(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode)
  {
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "node");
    Object localObject;
    do
    {
      localObject = getPrev();
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
    } while (!((LockFreeLinkedListNode)localObject).addNext(paramLockFreeLinkedListNode, this));
  }
  
  public final boolean addLastIf(@NotNull final LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Function0<Boolean> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "node");
    Intrinsics.checkParameterIsNotNull(paramFunction0, "condition");
    paramFunction0 = (CondAddOp)new CondAddOp(paramFunction0)
    {
      @Nullable
      public Object prepare(@NotNull LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousLockFreeLinkedListNode, "affected");
        if (((Boolean)$condition.invoke()).booleanValue()) {
          return null;
        }
        return LockFreeLinkedListKt.getCONDITION_FALSE();
      }
    };
    for (;;)
    {
      Object localObject = getPrev();
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      switch (((LockFreeLinkedListNode)localObject).tryCondAddNext(paramLockFreeLinkedListNode, this, paramFunction0))
      {
      }
    }
    return false;
    return true;
  }
  
  public final boolean addLastIfPrev(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Function1<? super LockFreeLinkedListNode, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "node");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    Object localObject;
    do
    {
      localObject = getPrev();
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      localObject = (LockFreeLinkedListNode)localObject;
      if (!((Boolean)paramFunction1.invoke(localObject)).booleanValue()) {
        return false;
      }
    } while (!((LockFreeLinkedListNode)localObject).addNext(paramLockFreeLinkedListNode, this));
    return true;
  }
  
  public final boolean addLastIfPrevAndIf(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Function1<? super LockFreeLinkedListNode, Boolean> paramFunction1, @NotNull Function0<Boolean> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "node");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    Intrinsics.checkParameterIsNotNull(paramFunction0, "condition");
    paramFunction0 = (CondAddOp)new CondAddOp(paramFunction0)
    {
      @Nullable
      public Object prepare(@NotNull LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousLockFreeLinkedListNode, "affected");
        if (((Boolean)$condition.invoke()).booleanValue()) {
          return null;
        }
        return LockFreeLinkedListKt.getCONDITION_FALSE();
      }
    };
    for (;;)
    {
      Object localObject = getPrev();
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      localObject = (LockFreeLinkedListNode)localObject;
      if (!((Boolean)paramFunction1.invoke(localObject)).booleanValue()) {
        return false;
      }
      switch (((LockFreeLinkedListNode)localObject).tryCondAddNext(paramLockFreeLinkedListNode, this, paramFunction0))
      {
      }
    }
    return false;
    return true;
  }
  
  @PublishedApi
  public final boolean addNext(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
  {
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "node");
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
    _prev$FU.lazySet(paramLockFreeLinkedListNode1, this);
    _next$FU.lazySet(paramLockFreeLinkedListNode1, paramLockFreeLinkedListNode2);
    if (!_next$FU.compareAndSet(this, paramLockFreeLinkedListNode2, paramLockFreeLinkedListNode1)) {
      return false;
    }
    paramLockFreeLinkedListNode1.finishAdd(paramLockFreeLinkedListNode2);
    return true;
  }
  
  public final boolean addOneIfEmpty(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode)
  {
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "node");
    _prev$FU.lazySet(paramLockFreeLinkedListNode, this);
    _next$FU.lazySet(paramLockFreeLinkedListNode, this);
    do
    {
      if (getNext() != (LockFreeLinkedListNode)this) {
        return false;
      }
    } while (!_next$FU.compareAndSet(this, this, paramLockFreeLinkedListNode));
    paramLockFreeLinkedListNode.finishAdd(this);
    return true;
  }
  
  @NotNull
  public final <T extends LockFreeLinkedListNode> AddLastDesc<T> describeAddLast(@NotNull T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramT, "node");
    return new AddLastDesc(this, paramT);
  }
  
  @Nullable
  public AtomicDesc describeRemove()
  {
    if (isRemoved()) {
      return null;
    }
    (AtomicDesc)new AbstractAtomicDesc()
    {
      private static final AtomicReferenceFieldUpdater _originalNext$FU = AtomicReferenceFieldUpdater.newUpdater(1.class, Object.class, "_originalNext");
      private volatile Object _originalNext = null;
      
      @Nullable
      protected Object failure(@NotNull LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode, @NotNull Object paramAnonymousObject)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousLockFreeLinkedListNode, "affected");
        Intrinsics.checkParameterIsNotNull(paramAnonymousObject, "next");
        if ((paramAnonymousObject instanceof Removed)) {
          return LockFreeLinkedListKt.getALREADY_REMOVED();
        }
        return null;
      }
      
      protected void finishOnSuccess(@NotNull LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode2)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousLockFreeLinkedListNode1, "affected");
        Intrinsics.checkParameterIsNotNull(paramAnonymousLockFreeLinkedListNode2, "next");
        LockFreeLinkedListNode.access$finishRemove(this$0, paramAnonymousLockFreeLinkedListNode2);
      }
      
      @Nullable
      protected LockFreeLinkedListNode getAffectedNode()
      {
        return this$0;
      }
      
      @Nullable
      protected LockFreeLinkedListNode getOriginalNext()
      {
        return (LockFreeLinkedListNode)_originalNext;
      }
      
      @Nullable
      protected Object onPrepare(@NotNull LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode2)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousLockFreeLinkedListNode1, "affected");
        Intrinsics.checkParameterIsNotNull(paramAnonymousLockFreeLinkedListNode2, "next");
        _originalNext$FU.compareAndSet(this, null, paramAnonymousLockFreeLinkedListNode2);
        return null;
      }
      
      @NotNull
      protected Removed updatedNext(@NotNull LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode2)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousLockFreeLinkedListNode1, "affected");
        Intrinsics.checkParameterIsNotNull(paramAnonymousLockFreeLinkedListNode2, "next");
        return LockFreeLinkedListNode.access$removed(paramAnonymousLockFreeLinkedListNode2);
      }
    };
  }
  
  @NotNull
  public final RemoveFirstDesc<LockFreeLinkedListNode> describeRemoveFirst()
  {
    return new RemoveFirstDesc(this);
  }
  
  @NotNull
  public final Object getNext()
  {
    for (;;)
    {
      Object localObject = _next;
      if (!(localObject instanceof OpDescriptor)) {
        return localObject;
      }
      ((OpDescriptor)localObject).perform(this);
    }
  }
  
  @NotNull
  public final Object getPrev()
  {
    for (;;)
    {
      Object localObject = _prev;
      if ((localObject instanceof Removed)) {
        return localObject;
      }
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      LockFreeLinkedListNode localLockFreeLinkedListNode = (LockFreeLinkedListNode)localObject;
      if (localLockFreeLinkedListNode.getNext() == (LockFreeLinkedListNode)this) {
        return localObject;
      }
      correctPrev(localLockFreeLinkedListNode, null);
    }
  }
  
  @PublishedApi
  public final void helpDelete()
  {
    LockFreeLinkedListNode localLockFreeLinkedListNode2 = (LockFreeLinkedListNode)null;
    Object localObject1 = markPrev();
    Object localObject2 = _next;
    if (localObject2 == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Removed");
    }
    LockFreeLinkedListNode localLockFreeLinkedListNode1 = ref;
    localObject2 = localLockFreeLinkedListNode2;
    label174:
    do
    {
      for (;;)
      {
        Object localObject3 = localLockFreeLinkedListNode1.getNext();
        if ((localObject3 instanceof Removed))
        {
          localLockFreeLinkedListNode1.markPrev();
          localLockFreeLinkedListNode1 = ref;
        }
        else
        {
          localObject3 = ((LockFreeLinkedListNode)localObject1).getNext();
          if ((localObject3 instanceof Removed))
          {
            if (localObject2 != null)
            {
              ((LockFreeLinkedListNode)localObject1).markPrev();
              _next$FU.compareAndSet(localObject2, localObject1, ref);
              localObject1 = localObject2;
              break;
            }
            localObject1 = LockFreeLinkedListKt.unwrap(_prev);
            continue;
          }
          if (localObject3 == (LockFreeLinkedListNode)this) {
            break label174;
          }
          if (localObject3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
          }
          localObject3 = (LockFreeLinkedListNode)localObject3;
          if (localObject3 == localLockFreeLinkedListNode1) {
            return;
          }
          localObject2 = localObject1;
          localObject1 = localObject3;
        }
      }
    } while (!_next$FU.compareAndSet(localObject1, this, localLockFreeLinkedListNode1));
  }
  
  public final boolean isFresh()
  {
    return _next == (LockFreeLinkedListNode)this;
  }
  
  public final boolean isRemoved()
  {
    return getNext() instanceof Removed;
  }
  
  @PublishedApi
  @NotNull
  public final CondAddOp makeCondAddOp(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Function0<Boolean> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "node");
    Intrinsics.checkParameterIsNotNull(paramFunction0, "condition");
    (CondAddOp)new CondAddOp(paramFunction0)
    {
      @Nullable
      public Object prepare(@NotNull LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousLockFreeLinkedListNode, "affected");
        if (((Boolean)$condition.invoke()).booleanValue()) {
          return null;
        }
        return LockFreeLinkedListKt.getCONDITION_FALSE();
      }
    };
  }
  
  public boolean remove()
  {
    Object localObject;
    LockFreeLinkedListNode localLockFreeLinkedListNode;
    Removed localRemoved;
    do
    {
      localObject = getNext();
      if ((localObject instanceof Removed)) {
        return false;
      }
      if (localObject == (LockFreeLinkedListNode)this) {
        return false;
      }
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      localLockFreeLinkedListNode = (LockFreeLinkedListNode)localObject;
      localRemoved = localLockFreeLinkedListNode.removed();
    } while (!_next$FU.compareAndSet(this, localObject, localRemoved));
    finishRemove(localLockFreeLinkedListNode);
    return true;
  }
  
  @Nullable
  public final LockFreeLinkedListNode removeFirstOrNull()
  {
    for (;;)
    {
      Object localObject = getNext();
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      localObject = (LockFreeLinkedListNode)localObject;
      if (localObject == (LockFreeLinkedListNode)this) {
        return null;
      }
      if (((LockFreeLinkedListNode)localObject).remove()) {
        return localObject;
      }
      ((LockFreeLinkedListNode)localObject).helpDelete();
    }
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(getClass().getSimpleName());
    localStringBuilder.append('@');
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    return localStringBuilder.toString();
  }
  
  @PublishedApi
  public final int tryCondAddNext(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2, @NotNull CondAddOp paramCondAddOp)
  {
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "node");
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
    Intrinsics.checkParameterIsNotNull(paramCondAddOp, "condAdd");
    _prev$FU.lazySet(paramLockFreeLinkedListNode1, this);
    _next$FU.lazySet(paramLockFreeLinkedListNode1, paramLockFreeLinkedListNode2);
    oldNext = paramLockFreeLinkedListNode2;
    if (!_next$FU.compareAndSet(this, paramLockFreeLinkedListNode2, paramCondAddOp)) {
      return 0;
    }
    if (paramCondAddOp.perform(this) == null) {
      return 1;
    }
    return 2;
  }
  
  public final void validateNode$kotlinx_coroutines_core(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
  {
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "prev");
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
    Object localObject = _prev;
    int j = 0;
    if (paramLockFreeLinkedListNode1 == localObject) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalStateException("Check failed.".toString()));
    }
    int i = j;
    if (paramLockFreeLinkedListNode2 == _next) {
      i = 1;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalStateException("Check failed.".toString()));
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000:\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\005\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\000\n\002\b\006\n\002\020\013\n\000\n\002\030\002\n\002\b\003\b&\030\0002\0020\001:\001\032B\005¢\006\002\020\002J\034\020\n\032\0020\0132\n\020\f\032\006\022\002\b\0030\r2\b\020\016\032\004\030\0010\017J\036\020\016\032\004\030\0010\0172\n\020\020\032\0060\004j\002`\0052\006\020\021\032\0020\017H\024J \020\022\032\0020\0132\n\020\020\032\0060\004j\002`\0052\n\020\021\032\0060\004j\002`\005H$J\"\020\023\032\004\030\0010\0172\n\020\020\032\0060\004j\002`\0052\n\020\021\032\0060\004j\002`\005H$J\026\020\024\032\004\030\0010\0172\n\020\f\032\006\022\002\b\0030\rH\007J\034\020\025\032\0020\0262\n\020\020\032\0060\004j\002`\0052\006\020\021\032\0020\017H\024J\024\020\027\032\0060\004j\002`\0052\006\020\f\032\0020\030H\024J \020\031\032\0020\0172\n\020\020\032\0060\004j\002`\0052\n\020\021\032\0060\004j\002`\005H$R\032\020\003\032\n\030\0010\004j\004\030\001`\005X¤\004¢\006\006\032\004\b\006\020\007R\032\020\b\032\n\030\0010\004j\004\030\001`\005X¤\004¢\006\006\032\004\b\t\020\007¨\006\033"}, d2={"Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "Lkotlinx/coroutines/experimental/internal/AtomicDesc;", "()V", "affectedNode", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/internal/Node;", "getAffectedNode", "()Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "originalNext", "getOriginalNext", "complete", "", "op", "Lkotlinx/coroutines/experimental/internal/AtomicOp;", "failure", "", "affected", "next", "finishOnSuccess", "onPrepare", "prepare", "retry", "", "takeAffectedNode", "Lkotlinx/coroutines/experimental/internal/OpDescriptor;", "updatedNext", "PrepareOp", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static abstract class AbstractAtomicDesc
    extends AtomicDesc
  {
    public AbstractAtomicDesc() {}
    
    public final void complete(@NotNull AtomicOp<?> paramAtomicOp, @Nullable Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramAtomicOp, "op");
      int i;
      if (paramObject == null) {
        i = 1;
      } else {
        i = 0;
      }
      LockFreeLinkedListNode localLockFreeLinkedListNode2 = getAffectedNode();
      if (localLockFreeLinkedListNode2 != null)
      {
        LockFreeLinkedListNode localLockFreeLinkedListNode1 = getOriginalNext();
        if (localLockFreeLinkedListNode1 != null)
        {
          if (i != 0) {
            paramObject = updatedNext(localLockFreeLinkedListNode2, localLockFreeLinkedListNode1);
          } else {
            paramObject = localLockFreeLinkedListNode1;
          }
          if ((LockFreeLinkedListNode._next$FU.compareAndSet(localLockFreeLinkedListNode2, paramAtomicOp, paramObject)) && (i != 0)) {
            finishOnSuccess(localLockFreeLinkedListNode2, localLockFreeLinkedListNode1);
          }
          return;
        }
        paramAtomicOp = (AbstractAtomicDesc)this;
        if ((i ^ 0x1) == 0) {
          throw ((Throwable)new IllegalStateException("Check failed.".toString()));
        }
        return;
      }
      paramAtomicOp = (AbstractAtomicDesc)this;
      if ((i ^ 0x1) == 0) {
        throw ((Throwable)new IllegalStateException("Check failed.".toString()));
      }
    }
    
    @Nullable
    protected Object failure(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "affected");
      Intrinsics.checkParameterIsNotNull(paramObject, "next");
      return null;
    }
    
    protected abstract void finishOnSuccess(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2);
    
    @Nullable
    protected abstract LockFreeLinkedListNode getAffectedNode();
    
    @Nullable
    protected abstract LockFreeLinkedListNode getOriginalNext();
    
    @Nullable
    protected abstract Object onPrepare(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2);
    
    @Nullable
    public final Object prepare(@NotNull AtomicOp<?> paramAtomicOp)
    {
      Intrinsics.checkParameterIsNotNull(paramAtomicOp, "op");
      Object localObject1;
      do
      {
        Object localObject2;
        Object localObject3;
        do
        {
          do
          {
            for (;;)
            {
              localObject1 = takeAffectedNode((OpDescriptor)paramAtomicOp);
              localObject2 = _next;
              if (localObject2 == paramAtomicOp) {
                return null;
              }
              if (paramAtomicOp.isDecided()) {
                return null;
              }
              if (!(localObject2 instanceof OpDescriptor)) {
                break;
              }
              ((OpDescriptor)localObject2).perform(localObject1);
            }
            localObject3 = failure((LockFreeLinkedListNode)localObject1, localObject2);
            if (localObject3 != null) {
              return localObject3;
            }
          } while (retry((LockFreeLinkedListNode)localObject1, localObject2));
          if (localObject2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
          }
          localObject3 = new PrepareOp((LockFreeLinkedListNode)localObject2, paramAtomicOp, this);
        } while (!LockFreeLinkedListNode._next$FU.compareAndSet(localObject1, localObject2, localObject3));
        localObject1 = ((PrepareOp)localObject3).perform(localObject1);
      } while (localObject1 == LockFreeLinkedListKt.access$getREMOVE_PREPARED$p());
      return localObject1;
    }
    
    protected boolean retry(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "affected");
      Intrinsics.checkParameterIsNotNull(paramObject, "next");
      return false;
    }
    
    @NotNull
    protected LockFreeLinkedListNode takeAffectedNode(@NotNull OpDescriptor paramOpDescriptor)
    {
      Intrinsics.checkParameterIsNotNull(paramOpDescriptor, "op");
      paramOpDescriptor = getAffectedNode();
      if (paramOpDescriptor == null) {
        Intrinsics.throwNpe();
      }
      return paramOpDescriptor;
    }
    
    @NotNull
    protected abstract Object updatedNext(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2);
    
    @Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\000\n\002\b\002\b\002\030\0002\0020\001B+\022\n\020\002\032\0060\003j\002`\004\022\020\020\005\032\f\022\b\022\0060\003j\002`\0040\006\022\006\020\007\032\0020\b¢\006\002\020\tJ\024\020\n\032\004\030\0010\0132\b\020\f\032\004\030\0010\013H\026R\020\020\007\032\0020\b8\006X\004¢\006\002\n\000R\024\020\002\032\0060\003j\002`\0048\006X\004¢\006\002\n\000R\032\020\005\032\f\022\b\022\0060\003j\002`\0040\0068\006X\004¢\006\002\n\000¨\006\r"}, d2={"Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AbstractAtomicDesc$PrepareOp;", "Lkotlinx/coroutines/experimental/internal/OpDescriptor;", "next", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/internal/Node;", "op", "Lkotlinx/coroutines/experimental/internal/AtomicOp;", "desc", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "(Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/experimental/internal/AtomicOp;Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AbstractAtomicDesc;)V", "perform", "", "affected", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
    private static final class PrepareOp
      extends OpDescriptor
    {
      @JvmField
      @NotNull
      public final LockFreeLinkedListNode.AbstractAtomicDesc desc;
      @JvmField
      @NotNull
      public final LockFreeLinkedListNode next;
      @JvmField
      @NotNull
      public final AtomicOp<LockFreeLinkedListNode> op;
      
      public PrepareOp(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull AtomicOp<? super LockFreeLinkedListNode> paramAtomicOp, @NotNull LockFreeLinkedListNode.AbstractAtomicDesc paramAbstractAtomicDesc)
      {
        next = paramLockFreeLinkedListNode;
        op = paramAtomicOp;
        desc = paramAbstractAtomicDesc;
      }
      
      @Nullable
      public Object perform(@Nullable Object paramObject)
      {
        if (paramObject == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
        }
        LockFreeLinkedListNode localLockFreeLinkedListNode = (LockFreeLinkedListNode)paramObject;
        paramObject = desc.onPrepare(localLockFreeLinkedListNode, next);
        if (paramObject != null)
        {
          if (paramObject == LockFreeLinkedListKt.access$getREMOVE_PREPARED$p())
          {
            Removed localRemoved = LockFreeLinkedListNode.access$removed(next);
            if (LockFreeLinkedListNode._next$FU.compareAndSet(localLockFreeLinkedListNode, this, localRemoved))
            {
              localLockFreeLinkedListNode.helpDelete();
              return paramObject;
            }
          }
          else
          {
            op.tryDecide(paramObject);
            LockFreeLinkedListNode._next$FU.compareAndSet(localLockFreeLinkedListNode, this, next);
          }
          return paramObject;
        }
        if (op.isDecided()) {
          paramObject = next;
        } else {
          paramObject = op;
        }
        LockFreeLinkedListNode._next$FU.compareAndSet(localLockFreeLinkedListNode, this, paramObject);
        return null;
      }
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000<\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\007\n\002\020\002\n\002\b\003\n\002\020\000\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\002\b\026\030\000*\016\b\000\020\001 \001*\0060\002j\002`\0032\0020\004B\031\022\n\020\005\032\0060\002j\002`\003\022\006\020\006\032\0028\000¢\006\002\020\007J \020\020\032\0020\0212\n\020\022\032\0060\002j\002`\0032\n\020\023\032\0060\002j\002`\003H\024J\"\020\024\032\004\030\0010\0252\n\020\022\032\0060\002j\002`\0032\n\020\023\032\0060\002j\002`\003H\024J\034\020\026\032\0020\0272\n\020\022\032\0060\002j\002`\0032\006\020\023\032\0020\025H\024J\024\020\030\032\0060\002j\002`\0032\006\020\031\032\0020\032H\004J \020\033\032\0020\0252\n\020\022\032\0060\002j\002`\0032\n\020\023\032\0060\002j\002`\003H\024R\034\020\b\032\020\022\f\022\n\030\0010\002j\004\030\001`\0030\tX\004¢\006\002\n\000R\034\020\n\032\n\030\0010\002j\004\030\001`\0038DX\004¢\006\006\032\004\b\013\020\fR\022\020\006\032\0028\0008\006X\004¢\006\004\n\002\020\rR\034\020\016\032\n\030\0010\002j\004\030\001`\0038DX\004¢\006\006\032\004\b\017\020\fR\024\020\005\032\0060\002j\002`\0038\006X\004¢\006\002\n\000¨\006\034"}, d2={"Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AddLastDesc;", "T", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/internal/Node;", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "queue", "node", "(Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;)V", "_affectedNode", "Lkotlinx/atomicfu/AtomicRef;", "affectedNode", "getAffectedNode", "()Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "originalNext", "getOriginalNext", "finishOnSuccess", "", "affected", "next", "onPrepare", "", "retry", "", "takeAffectedNode", "op", "Lkotlinx/coroutines/experimental/internal/OpDescriptor;", "updatedNext", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static class AddLastDesc<T extends LockFreeLinkedListNode>
    extends LockFreeLinkedListNode.AbstractAtomicDesc
  {
    private static final AtomicReferenceFieldUpdater _affectedNode$FU = AtomicReferenceFieldUpdater.newUpdater(AddLastDesc.class, Object.class, "_affectedNode");
    private volatile Object _affectedNode;
    @JvmField
    @NotNull
    public final T node;
    @JvmField
    @NotNull
    public final LockFreeLinkedListNode queue;
    
    public AddLastDesc(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull T paramT)
    {
      queue = paramLockFreeLinkedListNode;
      node = paramT;
      int i;
      if ((node._next == node) && (node._prev == node)) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 0) {
        throw ((Throwable)new IllegalStateException("Check failed.".toString()));
      }
      _affectedNode = null;
    }
    
    protected void finishOnSuccess(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "affected");
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
      LockFreeLinkedListNode.access$finishAdd(node, queue);
    }
    
    @Nullable
    protected final LockFreeLinkedListNode getAffectedNode()
    {
      return (LockFreeLinkedListNode)_affectedNode;
    }
    
    @Nullable
    protected final LockFreeLinkedListNode getOriginalNext()
    {
      return queue;
    }
    
    @Nullable
    protected Object onPrepare(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "affected");
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
      _affectedNode$FU.compareAndSet(this, null, paramLockFreeLinkedListNode1);
      return null;
    }
    
    protected boolean retry(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "affected");
      Intrinsics.checkParameterIsNotNull(paramObject, "next");
      return paramObject != queue;
    }
    
    @NotNull
    protected final LockFreeLinkedListNode takeAffectedNode(@NotNull OpDescriptor paramOpDescriptor)
    {
      Intrinsics.checkParameterIsNotNull(paramOpDescriptor, "op");
      Object localObject1;
      do
      {
        for (;;)
        {
          localObject1 = queue._prev;
          if (localObject1 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
          }
          localObject1 = (LockFreeLinkedListNode)localObject1;
          Object localObject2 = _next;
          if (localObject2 == queue) {
            return localObject1;
          }
          if (localObject2 == paramOpDescriptor) {
            return localObject1;
          }
          if (!(localObject2 instanceof OpDescriptor)) {
            break;
          }
          ((OpDescriptor)localObject2).perform(localObject1);
        }
        localObject1 = LockFreeLinkedListNode.access$correctPrev(queue, (LockFreeLinkedListNode)localObject1, paramOpDescriptor);
      } while (localObject1 == null);
      return localObject1;
    }
    
    @NotNull
    protected Object updatedNext(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "affected");
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
      paramLockFreeLinkedListNode2 = node;
      LockFreeLinkedListNode._prev$FU.compareAndSet(paramLockFreeLinkedListNode2, node, paramLockFreeLinkedListNode1);
      paramLockFreeLinkedListNode1 = node;
      LockFreeLinkedListNode._next$FU.compareAndSet(paramLockFreeLinkedListNode1, node, queue);
      return node;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\002\n\002\020\000\n\000\b!\030\0002\f\022\b\022\0060\002j\002`\0030\001B\021\022\n\020\004\032\0060\002j\002`\003¢\006\002\020\005J\036\020\007\032\0020\b2\n\020\t\032\0060\002j\002`\0032\b\020\n\032\004\030\0010\013H\026R\024\020\004\032\0060\002j\002`\0038\006X\004¢\006\002\n\000R\032\020\006\032\n\030\0010\002j\004\030\001`\0038\006@\006X\016¢\006\002\n\000¨\006\f"}, d2={"Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$CondAddOp;", "Lkotlinx/coroutines/experimental/internal/AtomicOp;", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/internal/Node;", "newNode", "(Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;)V", "oldNext", "complete", "", "affected", "failure", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  @PublishedApi
  public static abstract class CondAddOp
    extends AtomicOp<LockFreeLinkedListNode>
  {
    @JvmField
    @NotNull
    public final LockFreeLinkedListNode newNode;
    @JvmField
    @Nullable
    public LockFreeLinkedListNode oldNext;
    
    public CondAddOp(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode)
    {
      newNode = paramLockFreeLinkedListNode;
    }
    
    public void complete(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @Nullable Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "affected");
      int i;
      if (paramObject == null) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        paramObject = newNode;
      } else {
        paramObject = oldNext;
      }
      if ((paramObject != null) && (LockFreeLinkedListNode._next$FU.compareAndSet(paramLockFreeLinkedListNode, this, paramObject)) && (i != 0))
      {
        paramLockFreeLinkedListNode = newNode;
        paramObject = oldNext;
        if (paramObject == null) {
          Intrinsics.throwNpe();
        }
        LockFreeLinkedListNode.access$finishAdd(paramLockFreeLinkedListNode, paramObject);
      }
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000@\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\f\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\002\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\005\b\026\030\000*\004\b\000\020\0012\0020\002B\021\022\n\020\003\032\0060\004j\002`\005¢\006\002\020\006J\036\020\024\032\004\030\0010\0252\n\020\026\032\0060\004j\002`\0052\006\020\027\032\0020\025H\024J \020\030\032\0020\0312\n\020\026\032\0060\004j\002`\0052\n\020\027\032\0060\004j\002`\005H\004J\"\020\032\032\004\030\0010\0252\n\020\026\032\0060\004j\002`\0052\n\020\027\032\0060\004j\002`\005H\005J\034\020\033\032\0020\0342\n\020\026\032\0060\004j\002`\0052\006\020\027\032\0020\025H\004J\024\020\035\032\0060\004j\002`\0052\006\020\036\032\0020\037H\004J \020 \032\0020\0252\n\020\026\032\0060\004j\002`\0052\n\020\027\032\0060\004j\002`\005H\004J\025\020!\032\0020\0342\006\020\"\032\0028\000H\024¢\006\002\020#R\034\020\007\032\020\022\f\022\n\030\0010\004j\004\030\001`\0050\bX\004¢\006\002\n\000R\034\020\t\032\020\022\f\022\n\030\0010\004j\004\030\001`\0050\bX\004¢\006\002\n\000R\034\020\n\032\n\030\0010\004j\004\030\001`\0058DX\004¢\006\006\032\004\b\013\020\fR\034\020\r\032\n\030\0010\004j\004\030\001`\0058DX\004¢\006\006\032\004\b\016\020\fR\024\020\003\032\0060\004j\002`\0058\006X\004¢\006\002\n\000R\032\020\017\032\0028\0008FX\004¢\006\f\022\004\b\020\020\021\032\004\b\022\020\023¨\006$"}, d2={"Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "T", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AbstractAtomicDesc;", "queue", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/internal/Node;", "(Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;)V", "_affectedNode", "Lkotlinx/atomicfu/AtomicRef;", "_originalNext", "affectedNode", "getAffectedNode", "()Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "originalNext", "getOriginalNext", "result", "result$annotations", "()V", "getResult", "()Ljava/lang/Object;", "failure", "", "affected", "next", "finishOnSuccess", "", "onPrepare", "retry", "", "takeAffectedNode", "op", "Lkotlinx/coroutines/experimental/internal/OpDescriptor;", "updatedNext", "validatePrepared", "node", "(Ljava/lang/Object;)Z", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static class RemoveFirstDesc<T>
    extends LockFreeLinkedListNode.AbstractAtomicDesc
  {
    private static final AtomicReferenceFieldUpdater _affectedNode$FU = AtomicReferenceFieldUpdater.newUpdater(RemoveFirstDesc.class, Object.class, "_affectedNode");
    private static final AtomicReferenceFieldUpdater _originalNext$FU = AtomicReferenceFieldUpdater.newUpdater(RemoveFirstDesc.class, Object.class, "_originalNext");
    private volatile Object _affectedNode;
    private volatile Object _originalNext;
    @JvmField
    @NotNull
    public final LockFreeLinkedListNode queue;
    
    public RemoveFirstDesc(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode)
    {
      queue = paramLockFreeLinkedListNode;
      _affectedNode = null;
      _originalNext = null;
    }
    
    @Nullable
    protected Object failure(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "affected");
      Intrinsics.checkParameterIsNotNull(paramObject, "next");
      if (paramLockFreeLinkedListNode == queue) {
        return LockFreeLinkedListKt.getLIST_EMPTY();
      }
      return null;
    }
    
    protected final void finishOnSuccess(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "affected");
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
      LockFreeLinkedListNode.access$finishRemove(paramLockFreeLinkedListNode1, paramLockFreeLinkedListNode2);
    }
    
    @Nullable
    protected final LockFreeLinkedListNode getAffectedNode()
    {
      return (LockFreeLinkedListNode)_affectedNode;
    }
    
    @Nullable
    protected final LockFreeLinkedListNode getOriginalNext()
    {
      return (LockFreeLinkedListNode)_originalNext;
    }
    
    public final T getResult()
    {
      LockFreeLinkedListNode localLockFreeLinkedListNode = getAffectedNode();
      if (localLockFreeLinkedListNode == null) {
        Intrinsics.throwNpe();
      }
      return (Object)localLockFreeLinkedListNode;
    }
    
    @Nullable
    protected final Object onPrepare(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "affected");
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
      int i;
      if (!(paramLockFreeLinkedListNode1 instanceof LockFreeLinkedListHead)) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 0) {
        throw ((Throwable)new IllegalStateException("Check failed.".toString()));
      }
      if (!validatePrepared((Object)paramLockFreeLinkedListNode1)) {
        return LockFreeLinkedListKt.access$getREMOVE_PREPARED$p();
      }
      _affectedNode$FU.compareAndSet(this, null, paramLockFreeLinkedListNode1);
      _originalNext$FU.compareAndSet(this, null, paramLockFreeLinkedListNode2);
      return null;
    }
    
    protected final boolean retry(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "affected");
      Intrinsics.checkParameterIsNotNull(paramObject, "next");
      if (!(paramObject instanceof Removed)) {
        return false;
      }
      paramLockFreeLinkedListNode.helpDelete();
      return true;
    }
    
    @NotNull
    protected final LockFreeLinkedListNode takeAffectedNode(@NotNull OpDescriptor paramOpDescriptor)
    {
      Intrinsics.checkParameterIsNotNull(paramOpDescriptor, "op");
      paramOpDescriptor = queue.getNext();
      if (paramOpDescriptor == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      return (LockFreeLinkedListNode)paramOpDescriptor;
    }
    
    @NotNull
    protected final Object updatedNext(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "affected");
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
      return LockFreeLinkedListNode.access$removed(paramLockFreeLinkedListNode2);
    }
    
    protected boolean validatePrepared(T paramT)
    {
      return true;
    }
  }
}

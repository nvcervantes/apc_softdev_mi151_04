package kotlinx.coroutines.experimental;

import java.util.concurrent.CancellationException;
import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.ExceptionsKt;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Element;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.experimental.internal.OpDescriptor;
import kotlinx.coroutines.experimental.intrinsics.UndispatchedKt;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\001\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\020\000\n\002\b\b\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\006\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\020\b\n\002\b\005\n\002\020\003\n\002\b\005\n\002\030\002\n\002\b\t\n\002\030\002\n\002\030\002\n\002\b\006\n\002\020\001\n\002\b\f\n\002\030\002\n\002\b\004\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\n\n\002\020\016\n\002\b\003\n\002\030\002\n\002\b\007\b\026\030\0002\0020\001:\006cdefghB\r\022\006\020\002\032\0020\003¢\006\002\020\004J$\020\030\032\0020\0032\006\020\031\032\0020\0072\006\020\032\032\0020\0332\n\020\034\032\006\022\002\b\0030\035H\002J\032\020\036\032\0020\0372\b\020\025\032\004\030\0010\0072\006\020 \032\0020!H\024J\023\020\"\032\004\030\0010\007H@ø\001\000¢\006\002\020#J\023\020$\032\004\030\0010\007H@ø\001\000¢\006\002\020#J\020\020%\032\0020\0032\b\020&\032\004\030\0010'J\034\020(\032\004\030\0010\0072\006\020\031\032\0020\0072\b\020)\032\004\030\0010\007H\002J\"\020*\032\0020\0372\006\020\031\032\0020\0072\b\020+\032\004\030\0010\0072\006\020 \032\0020!H\004J\032\020,\032\0020\0032\006\020\031\032\0020-2\b\020)\032\004\030\0010\007H\002J\n\020.\032\004\030\0010\007H\004J\n\020/\032\004\030\0010'H\004J\006\0200\032\0020'J\020\0201\032\0020\0372\006\0202\032\0020'H\024J\020\0203\032\0020\0372\b\0204\032\004\030\0010\001J*\0205\032\0020\0242\030\0206\032\024\022\006\022\004\030\0010'\022\004\022\0020\03707j\002`82\006\0209\032\0020\003H\002J \020:\032\0020\0242\030\0206\032\024\022\006\022\004\030\0010'\022\004\022\0020\03707j\002`8J(\020:\032\0020\0242\030\0206\032\024\022\006\022\004\030\0010'\022\004\022\0020\03707j\002`82\006\0209\032\0020\003J\021\020;\032\0020\037H@ø\001\000¢\006\002\020#J\b\020<\032\0020\003H\002J\021\020=\032\0020\037H@ø\001\000¢\006\002\020#J\037\020>\032\0020?2\024\020@\032\020\022\006\022\004\030\0010\007\022\004\022\0020\03707H\bJ\022\020A\032\0020\0032\b\020&\032\004\030\0010'H\002J\022\020B\032\0020\0032\b\020&\032\004\030\0010'H\002J.\020C\032\006\022\002\b\0030\0352\030\0206\032\024\022\006\022\004\030\0010'\022\004\022\0020\03707j\002`82\006\0209\032\0020\003H\002J\032\020D\032\0020\0372\006\020\032\032\0020\0332\b\020&\032\004\030\0010'H\002J\032\020E\032\0020\0372\006\020\032\032\0020\0332\b\020&\032\004\030\0010'H\002J+\020F\032\0020\037\"\016\b\000\020G\030\001*\006\022\002\b\0030\0352\006\020\032\032\0020\0332\b\020&\032\004\030\0010'H\bJ\b\020H\032\0020\037H\024J\022\020I\032\0020\0372\b\020&\032\004\030\0010'H\024J\b\020J\032\0020\037H\024J\020\020K\032\0020\0372\006\020\025\032\0020LH\002J\024\020M\032\0020\0372\n\020\025\032\006\022\002\b\0030\035H\002JJ\020N\032\0020\037\"\004\b\000\020O2\f\020P\032\b\022\004\022\002HO0Q2$\020@\032 \b\001\022\006\022\004\030\0010\007\022\n\022\b\022\004\022\002HO0S\022\006\022\004\030\0010\0070RH\004ø\001\000¢\006\002\020TJB\020U\032\0020\037\"\004\b\000\020O2\f\020P\032\b\022\004\022\002HO0Q2\034\020@\032\030\b\001\022\n\022\b\022\004\022\002HO0S\022\006\022\004\030\0010\00707H\026ø\001\000¢\006\002\020VJ\031\020W\032\0020\0372\n\020\034\032\006\022\002\b\0030\035H\000¢\006\002\bXJL\020Y\032\0020\037\"\004\b\000\020O2\f\020P\032\b\022\004\022\002HO0Q2$\020@\032 \b\001\022\006\022\004\030\0010\007\022\n\022\b\022\004\022\002HO0S\022\006\022\004\030\0010\0070RH\000ø\001\000¢\006\004\bZ\020TJ\006\020[\032\0020\003J\022\020\\\032\0020!2\b\020\025\032\004\030\0010\007H\002J\b\020]\032\0020^H\026J\032\020_\032\0020\0032\006\020\031\032\0020\0072\b\020+\032\004\030\0010\007H\004J\"\020`\032\0020\0032\006\020\031\032\0020\0072\b\020)\032\004\030\0010\0072\006\020 \032\0020!H\004J\032\020a\032\0020\0032\006\020\025\032\0020b2\b\020&\032\004\030\0010'H\002R\026\020\005\032\n\022\006\022\004\030\0010\0070\006X\004¢\006\002\n\000R\024\020\b\032\0020\0038TX\004¢\006\006\032\004\b\t\020\nR\021\020\013\032\0020\0038F¢\006\006\032\004\b\013\020\nR\021\020\f\032\0020\0038F¢\006\006\032\004\b\f\020\nR\021\020\r\032\0020\0038F¢\006\006\032\004\b\r\020\nR\021\020\016\032\0020\0038F¢\006\006\032\004\b\016\020\nR\030\020\017\032\006\022\002\b\0030\0208VX\004¢\006\006\032\004\b\021\020\022R\024\020\023\032\004\030\0010\0248\002@\002X\016¢\006\002\n\000R\026\020\025\032\004\030\0010\0078DX\004¢\006\006\032\004\b\026\020\027\002\004\n\002\b\t¨\006i"}, d2={"Lkotlinx/coroutines/experimental/JobSupport;", "Lkotlinx/coroutines/experimental/Job;", "active", "", "(Z)V", "_state", "Lkotlinx/atomicfu/AtomicRef;", "", "hasCancellingState", "getHasCancellingState", "()Z", "isActive", "isCancelled", "isCompleted", "isCompletedExceptionally", "key", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "getKey", "()Lkotlin/coroutines/experimental/CoroutineContext$Key;", "parentHandle", "Lkotlinx/coroutines/experimental/DisposableHandle;", "state", "getState", "()Ljava/lang/Object;", "addLastAtomic", "expect", "list", "Lkotlinx/coroutines/experimental/JobSupport$NodeList;", "node", "Lkotlinx/coroutines/experimental/JobNode;", "afterCompletion", "", "mode", "", "awaitInternal", "(Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "awaitSuspend", "cancel", "cause", "", "coerceProposedUpdate", "proposedUpdate", "completeUpdateState", "update", "correspondinglyCancelled", "Lkotlinx/coroutines/experimental/JobSupport$Cancelling;", "getCompletedInternal", "getCompletionCause", "getCompletionException", "handleException", "exception", "initParentJob", "parent", "installHandler", "handler", "Lkotlin/Function1;", "Lkotlinx/coroutines/experimental/CompletionHandler;", "onCancelling", "invokeOnCompletion", "join", "joinInternal", "joinSuspend", "loopOnState", "", "block", "makeCancelled", "makeCancelling", "makeNode", "notifyCancellation", "notifyCompletion", "notifyHandlers", "T", "onCancellation", "onParentCancellation", "onStart", "promoteEmptyToNodeList", "Lkotlinx/coroutines/experimental/Empty;", "promoteSingleToNodeList", "registerSelectAwaitInternal", "R", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectJoin", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "removeNode", "removeNode$kotlinx_coroutines_core", "selectAwaitCompletion", "selectAwaitCompletion$kotlinx_coroutines_core", "start", "startInternal", "toString", "", "tryUpdateState", "updateState", "updateStateCancelled", "Lkotlinx/coroutines/experimental/JobSupport$Incomplete;", "Cancelled", "Cancelling", "CompletedExceptionally", "Incomplete", "NodeList", "ParentOnCancellation", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public class JobSupport
  implements Job
{
  private static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(JobSupport.class, Object.class, "_state");
  private volatile Object _state;
  private volatile DisposableHandle parentHandle;
  
  public JobSupport(boolean paramBoolean)
  {
    Empty localEmpty;
    if (paramBoolean) {
      localEmpty = JobKt.access$getEmptyActive$p();
    } else {
      localEmpty = JobKt.access$getEmptyNew$p();
    }
    _state = localEmpty;
  }
  
  private final boolean addLastAtomic(final Object paramObject, NodeList paramNodeList, JobNode<?> paramJobNode)
  {
    paramJobNode = (LockFreeLinkedListNode)paramJobNode;
    paramObject = (LockFreeLinkedListNode.CondAddOp)new LockFreeLinkedListNode.CondAddOp(paramJobNode)
    {
      public Object prepare(LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode)
      {
        int i;
        if (jdField_this.getState() == paramObject) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0) {
          return null;
        }
        return LockFreeLinkedListKt.getCONDITION_FALSE();
      }
    };
    for (;;)
    {
      Object localObject = paramNodeList.getPrev();
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      switch (((LockFreeLinkedListNode)localObject).tryCondAddNext(paramJobNode, paramNodeList, paramObject))
      {
      }
    }
    return false;
    return true;
  }
  
  private final Object coerceProposedUpdate(Object paramObject1, Object paramObject2)
  {
    Object localObject = paramObject2;
    if ((paramObject1 instanceof Cancelling))
    {
      paramObject1 = (Cancelling)paramObject1;
      localObject = paramObject2;
      if (!correspondinglyCancelled(paramObject1, paramObject2)) {
        localObject = cancelled;
      }
    }
    return localObject;
  }
  
  private final boolean correspondinglyCancelled(Cancelling paramCancelling, Object paramObject)
  {
    boolean bool1 = paramObject instanceof Cancelled;
    boolean bool2 = false;
    if (!bool1) {
      return false;
    }
    paramObject = (Cancelled)paramObject;
    if (cause != cancelled.cause)
    {
      bool1 = bool2;
      if ((cause instanceof CancellationException))
      {
        bool1 = bool2;
        if (cancelled.cause != null) {}
      }
    }
    else
    {
      bool1 = true;
    }
    return bool1;
  }
  
  private final DisposableHandle installHandler(Function1<? super Throwable, Unit> paramFunction1, boolean paramBoolean)
  {
    Object localObject5 = null;
    Object localObject4 = null;
    Object localObject1 = (JobNode)null;
    Object localObject3;
    do
    {
      do
      {
        for (;;)
        {
          localObject2 = access$getState$p(this);
          if ((localObject2 instanceof Empty))
          {
            localObject3 = (Empty)localObject2;
            if (((Empty)localObject3).isActive())
            {
              if (localObject1 != null) {
                localObject3 = localObject1;
              } else {
                localObject3 = makeNode(paramFunction1, paramBoolean);
              }
              localObject1 = localObject3;
              if (_state$FU.compareAndSet(this, localObject2, localObject3)) {
                return (DisposableHandle)localObject3;
              }
            }
            else
            {
              promoteEmptyToNodeList((Empty)localObject3);
            }
          }
          else
          {
            if (!(localObject2 instanceof JobNode)) {
              break;
            }
            promoteSingleToNodeList((JobNode)localObject2);
          }
        }
        if (!(localObject2 instanceof NodeList)) {
          break;
        }
        if (localObject1 != null) {
          localObject3 = localObject1;
        } else {
          localObject3 = makeNode(paramFunction1, paramBoolean);
        }
        localObject1 = localObject3;
      } while (!addLastAtomic(localObject2, (NodeList)localObject2, (JobNode)localObject3));
      return (DisposableHandle)localObject3;
      if (!(localObject2 instanceof Cancelling)) {
        break;
      }
      if (paramBoolean)
      {
        localObject1 = localObject2;
        if (!(localObject2 instanceof CompletedExceptionally)) {
          localObject1 = null;
        }
        localObject2 = (CompletedExceptionally)localObject1;
        localObject1 = localObject4;
        if (localObject2 != null) {
          localObject1 = ((CompletedExceptionally)localObject2).getException();
        }
        paramFunction1.invoke(localObject1);
        return (DisposableHandle)NonDisposableHandle.INSTANCE;
      }
      if (localObject1 != null) {
        localObject3 = localObject1;
      } else {
        localObject3 = makeNode(paramFunction1, paramBoolean);
      }
      localObject1 = localObject3;
    } while (!addLastAtomic(localObject2, list, (JobNode)localObject3));
    return (DisposableHandle)localObject3;
    localObject1 = localObject2;
    if (!(localObject2 instanceof CompletedExceptionally)) {
      localObject1 = null;
    }
    Object localObject2 = (CompletedExceptionally)localObject1;
    localObject1 = localObject5;
    if (localObject2 != null) {
      localObject1 = ((CompletedExceptionally)localObject2).getException();
    }
    paramFunction1.invoke(localObject1);
    return (DisposableHandle)NonDisposableHandle.INSTANCE;
  }
  
  private final boolean joinInternal()
  {
    Object localObject;
    do
    {
      localObject = access$getState$p(this);
      if (!(localObject instanceof Incomplete)) {
        return false;
      }
    } while (startInternal(localObject) < 0);
    return true;
  }
  
  private final boolean makeCancelled(Throwable paramThrowable)
  {
    Object localObject;
    do
    {
      localObject = access$getState$p(this);
      if (!(localObject instanceof Incomplete)) {
        return false;
      }
    } while (!updateStateCancelled((Incomplete)localObject, paramThrowable));
    return true;
  }
  
  private final boolean makeCancelling(Throwable paramThrowable)
  {
    Object localObject1;
    do
    {
      Object localObject2;
      do
      {
        for (;;)
        {
          localObject1 = access$getState$p(this);
          if ((localObject1 instanceof Empty))
          {
            localObject2 = (Empty)localObject1;
            if (((Empty)localObject2).isActive()) {
              promoteEmptyToNodeList((Empty)localObject2);
            } else if (updateStateCancelled((Incomplete)localObject1, paramThrowable)) {
              return true;
            }
          }
          else
          {
            if (!(localObject1 instanceof JobNode)) {
              break;
            }
            promoteSingleToNodeList((JobNode)localObject1);
          }
        }
        if (!(localObject1 instanceof NodeList)) {
          break label136;
        }
        localObject2 = (NodeList)localObject1;
        if (!((NodeList)localObject2).isActive()) {
          break;
        }
      } while (!_state$FU.compareAndSet(this, localObject1, new Cancelling((NodeList)localObject2, new Cancelled(paramThrowable))));
      notifyCancellation((NodeList)localObject2, paramThrowable);
      onCancellation();
      return true;
    } while (!updateStateCancelled((Incomplete)localObject1, paramThrowable));
    return true;
    label136:
    return false;
  }
  
  private final JobNode<?> makeNode(Function1<? super Throwable, Unit> paramFunction1, boolean paramBoolean)
  {
    int j = 0;
    int i = 0;
    Object localObject2 = null;
    Object localObject1 = null;
    if (paramBoolean)
    {
      if ((paramFunction1 instanceof JobCancellationNode)) {
        localObject1 = paramFunction1;
      }
      localObject1 = (JobCancellationNode)localObject1;
      if (localObject1 != null)
      {
        if (job == (JobSupport)this) {
          i = 1;
        }
        if (i == 0) {
          throw ((Throwable)new IllegalArgumentException("Failed requirement.".toString()));
        }
        if (localObject1 != null) {
          return (JobNode)localObject1;
        }
      }
      return (JobNode)new InvokeOnCancellation((Job)this, paramFunction1);
    }
    if (!(paramFunction1 instanceof JobNode)) {
      localObject1 = localObject2;
    } else {
      localObject1 = paramFunction1;
    }
    localObject1 = (JobNode)localObject1;
    if (localObject1 != null)
    {
      i = j;
      if (job == (JobSupport)this) {
        if (getHasCancellingState())
        {
          i = j;
          if ((localObject1 instanceof JobCancellationNode)) {}
        }
        else
        {
          i = 1;
        }
      }
      if (i == 0) {
        throw ((Throwable)new IllegalArgumentException("Failed requirement.".toString()));
      }
      if (localObject1 != null) {
        return localObject1;
      }
    }
    return (JobNode)new InvokeOnCompletion((Job)this, paramFunction1);
  }
  
  private final void notifyCancellation(NodeList paramNodeList, Throwable paramThrowable)
  {
    Object localObject2 = (Throwable)null;
    Object localObject1 = paramNodeList.getNext();
    if (localObject1 == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
    }
    localObject1 = (LockFreeLinkedListNode)localObject1;
    while ((Intrinsics.areEqual(localObject1, (LockFreeLinkedListHead)paramNodeList) ^ true))
    {
      Object localObject3 = localObject2;
      Object localObject4;
      if ((localObject1 instanceof JobCancellationNode))
      {
        localObject3 = (JobNode)localObject1;
        try
        {
          ((JobNode)localObject3).invoke(paramThrowable);
          localObject3 = localObject2;
        }
        catch (Throwable localThrowable)
        {
          if (localObject2 != null)
          {
            ExceptionsKt.addSuppressed((Throwable)localObject2, localThrowable);
            if (localObject2 != null)
            {
              localObject4 = localObject2;
              break label110;
            }
          }
          localObject2 = (JobSupport)this;
          localObject2 = Unit.INSTANCE;
        }
      }
      label110:
      localObject1 = LockFreeLinkedListKt.unwrap(((LockFreeLinkedListNode)localObject1).getNext());
      localObject2 = localObject4;
    }
    if (localObject2 != null) {
      access$handleException(this, (Throwable)localObject2);
    }
  }
  
  private final void notifyCompletion(NodeList paramNodeList, Throwable paramThrowable)
  {
    Object localObject2 = (Throwable)null;
    Object localObject1 = paramNodeList.getNext();
    if (localObject1 == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
    }
    localObject1 = (LockFreeLinkedListNode)localObject1;
    while ((Intrinsics.areEqual(localObject1, (LockFreeLinkedListHead)paramNodeList) ^ true))
    {
      Object localObject3 = localObject2;
      Object localObject4;
      if ((localObject1 instanceof JobNode))
      {
        localObject3 = (JobNode)localObject1;
        try
        {
          ((JobNode)localObject3).invoke(paramThrowable);
          localObject3 = localObject2;
        }
        catch (Throwable localThrowable)
        {
          if (localObject2 != null)
          {
            ExceptionsKt.addSuppressed((Throwable)localObject2, localThrowable);
            if (localObject2 != null)
            {
              localObject4 = localObject2;
              break label110;
            }
          }
          localObject2 = (JobSupport)this;
          localObject2 = Unit.INSTANCE;
        }
      }
      label110:
      localObject1 = LockFreeLinkedListKt.unwrap(((LockFreeLinkedListNode)localObject1).getNext());
      localObject2 = localObject4;
    }
    if (localObject2 != null) {
      access$handleException(this, (Throwable)localObject2);
    }
  }
  
  private final <T extends JobNode<?>> void notifyHandlers(NodeList paramNodeList, Throwable paramThrowable)
  {
    Object localObject2 = (Throwable)null;
    Object localObject1 = paramNodeList.getNext();
    if (localObject1 == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
    }
    localObject1 = (LockFreeLinkedListNode)localObject1;
    while ((Intrinsics.areEqual(localObject1, (LockFreeLinkedListHead)paramNodeList) ^ true))
    {
      Intrinsics.reifiedOperationMarker(3, "T");
      Object localObject3 = localObject2;
      Object localObject4;
      if ((localObject1 instanceof LockFreeLinkedListNode))
      {
        localObject3 = (JobNode)localObject1;
        try
        {
          ((JobNode)localObject3).invoke(paramThrowable);
          localObject3 = localObject2;
        }
        catch (Throwable localThrowable)
        {
          if (localObject2 != null)
          {
            ExceptionsKt.addSuppressed((Throwable)localObject2, localThrowable);
            if (localObject2 != null)
            {
              localObject4 = localObject2;
              break label117;
            }
          }
          localObject2 = (JobSupport)this;
          localObject2 = Unit.INSTANCE;
        }
      }
      label117:
      localObject1 = LockFreeLinkedListKt.unwrap(((LockFreeLinkedListNode)localObject1).getNext());
      localObject2 = localObject4;
    }
    if (localObject2 != null) {
      access$handleException(this, (Throwable)localObject2);
    }
  }
  
  private final void promoteEmptyToNodeList(Empty paramEmpty)
  {
    _state$FU.compareAndSet(this, paramEmpty, new NodeList(paramEmpty.isActive()));
  }
  
  private final void promoteSingleToNodeList(JobNode<?> paramJobNode)
  {
    paramJobNode.addOneIfEmpty((LockFreeLinkedListNode)new NodeList(true));
    Object localObject = paramJobNode.getNext();
    _state$FU.compareAndSet(this, paramJobNode, localObject);
  }
  
  private final int startInternal(Object paramObject)
  {
    if ((paramObject instanceof Empty))
    {
      if (((Empty)paramObject).isActive()) {
        return 0;
      }
      if (!_state$FU.compareAndSet(this, paramObject, JobKt.access$getEmptyActive$p())) {
        return -1;
      }
      onStart();
      return 1;
    }
    if ((paramObject instanceof NodeList))
    {
      paramObject = (NodeList)paramObject;
      if (_active != 0) {
        return 0;
      }
      if (!NodeList._active$FU.compareAndSet(paramObject, 0, 1)) {
        return -1;
      }
      onStart();
      return 1;
    }
    return 0;
  }
  
  private final boolean updateStateCancelled(Incomplete paramIncomplete, Throwable paramThrowable)
  {
    return updateState(paramIncomplete, new Cancelled(paramThrowable), 0);
  }
  
  protected void afterCompletion(@Nullable Object paramObject, int paramInt) {}
  
  @Nullable
  protected final Object awaitInternal(@NotNull Continuation<Object> paramContinuation)
  {
    Object localObject;
    do
    {
      localObject = getState();
      if (!(localObject instanceof Incomplete))
      {
        if ((localObject instanceof CompletedExceptionally)) {
          throw ((CompletedExceptionally)localObject).getException();
        }
        return localObject;
      }
    } while (startInternal(localObject) < 0);
    return awaitSuspend(paramContinuation);
  }
  
  @Nullable
  final Object awaitSuspend(@NotNull Continuation<Object> paramContinuation)
  {
    paramContinuation = new CancellableContinuationImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation), 1);
    paramContinuation.initCancellability();
    CancellableContinuation localCancellableContinuation = (CancellableContinuation)paramContinuation;
    JobKt.disposeOnCompletion((Job)localCancellableContinuation, invokeOnCompletion((Function1)new Lambda(localCancellableContinuation)
    {
      public final void invoke(Throwable paramAnonymousThrowable)
      {
        paramAnonymousThrowable = jdField_this.getState();
        int i;
        if (!(paramAnonymousThrowable instanceof JobSupport.Incomplete)) {
          i = 1;
        } else {
          i = 0;
        }
        if (i == 0) {
          throw ((Throwable)new IllegalStateException("Check failed.".toString()));
        }
        if ((paramAnonymousThrowable instanceof JobSupport.CompletedExceptionally))
        {
          $cont.resumeWithException(((JobSupport.CompletedExceptionally)paramAnonymousThrowable).getException());
          return;
        }
        $cont.resume(paramAnonymousThrowable);
      }
    }));
    return paramContinuation.getResult();
  }
  
  public final boolean cancel(@Nullable Throwable paramThrowable)
  {
    if (getHasCancellingState()) {
      return makeCancelling(paramThrowable);
    }
    return makeCancelled(paramThrowable);
  }
  
  protected final void completeUpdateState(@NotNull Object paramObject1, @Nullable Object paramObject2, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramObject1, "expect");
    boolean bool = paramObject2 instanceof CompletedExceptionally;
    Object localObject2 = null;
    if (!bool) {
      localObject1 = null;
    } else {
      localObject1 = paramObject2;
    }
    CompletedExceptionally localCompletedExceptionally = (CompletedExceptionally)localObject1;
    Object localObject1 = localObject2;
    if (localCompletedExceptionally != null) {
      localObject1 = cause;
    }
    if ((paramObject1 instanceof JobNode)) {
      try
      {
        ((JobNode)paramObject1).invoke((Throwable)localObject1);
      }
      catch (Throwable localThrowable)
      {
        handleException(localThrowable);
      }
    } else if ((paramObject1 instanceof NodeList)) {
      notifyCompletion((NodeList)paramObject1, localThrowable);
    } else if ((paramObject1 instanceof Cancelling)) {
      notifyCompletion(list, localThrowable);
    } else if (!(paramObject1 instanceof Empty)) {
      throw ((Throwable)new IllegalStateException("Check failed.".toString()));
    }
    if (!(paramObject1 instanceof Cancelling)) {
      onCancellation();
    }
    afterCompletion(paramObject2, paramInt);
  }
  
  public <R> R fold(R paramR, @NotNull Function2<? super R, ? super CoroutineContext.Element, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
    return Job.DefaultImpls.fold(this, paramR, paramFunction2);
  }
  
  @Nullable
  public <E extends CoroutineContext.Element> E get(@NotNull CoroutineContext.Key<E> paramKey)
  {
    Intrinsics.checkParameterIsNotNull(paramKey, "key");
    return Job.DefaultImpls.get(this, paramKey);
  }
  
  @Nullable
  protected final Object getCompletedInternal()
  {
    Object localObject = getState();
    int i;
    if (!(localObject instanceof Incomplete)) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalStateException("This job has not completed yet".toString()));
    }
    if ((localObject instanceof CompletedExceptionally)) {
      throw ((CompletedExceptionally)localObject).getException();
    }
    return localObject;
  }
  
  @Nullable
  protected final Throwable getCompletionCause()
  {
    Object localObject = getState();
    if ((localObject instanceof Cancelling)) {
      return cancelled.cause;
    }
    if ((localObject instanceof Incomplete)) {
      throw ((Throwable)new IllegalStateException("Job was not completed or cancelled yet".toString()));
    }
    if ((localObject instanceof CompletedExceptionally)) {
      return cause;
    }
    return null;
  }
  
  @NotNull
  public final Throwable getCompletionException()
  {
    Object localObject = getState();
    if ((localObject instanceof Cancelling)) {
      return cancelled.getException();
    }
    if ((localObject instanceof Incomplete)) {
      throw ((Throwable)new IllegalStateException("Job was not completed or cancelled yet".toString()));
    }
    if ((localObject instanceof CompletedExceptionally)) {
      return ((CompletedExceptionally)localObject).getException();
    }
    return (Throwable)new CancellationException("Job has completed normally");
  }
  
  protected boolean getHasCancellingState()
  {
    return false;
  }
  
  @NotNull
  public CoroutineContext.Key<?> getKey()
  {
    return (CoroutineContext.Key)Job.Key;
  }
  
  @Nullable
  protected final Object getState()
  {
    for (;;)
    {
      Object localObject = _state;
      if (!(localObject instanceof OpDescriptor)) {
        return localObject;
      }
      ((OpDescriptor)localObject).perform(this);
    }
  }
  
  protected void handleException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    throw paramThrowable;
  }
  
  public final void initParentJob(@Nullable Job paramJob)
  {
    int i;
    if (parentHandle == null) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalStateException("Check failed.".toString()));
    }
    if (paramJob == null)
    {
      parentHandle = ((DisposableHandle)NonDisposableHandle.INSTANCE);
      return;
    }
    paramJob.start();
    paramJob = paramJob.invokeOnCompletion((Function1)new ParentOnCancellation(paramJob), true);
    parentHandle = paramJob;
    if (isCompleted()) {
      paramJob.dispose();
    }
  }
  
  @NotNull
  public final DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "handler");
    return installHandler(paramFunction1, false);
  }
  
  @NotNull
  public final DisposableHandle invokeOnCompletion(@NotNull Function1<? super Throwable, Unit> paramFunction1, boolean paramBoolean)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "handler");
    if ((paramBoolean) && (getHasCancellingState())) {
      paramBoolean = true;
    } else {
      paramBoolean = false;
    }
    return installHandler(paramFunction1, paramBoolean);
  }
  
  public final boolean isActive()
  {
    Object localObject = getState();
    return ((localObject instanceof Incomplete)) && (((Incomplete)localObject).isActive());
  }
  
  public final boolean isCancelled()
  {
    Object localObject = getState();
    return ((localObject instanceof Cancelled)) || ((localObject instanceof Cancelling));
  }
  
  public final boolean isCompleted()
  {
    return !(getState() instanceof Incomplete);
  }
  
  public final boolean isCompletedExceptionally()
  {
    return getState() instanceof CompletedExceptionally;
  }
  
  @Nullable
  public final Object join(@NotNull Continuation<? super Unit> paramContinuation)
  {
    if (!joinInternal()) {
      return Unit.INSTANCE;
    }
    return joinSuspend(paramContinuation);
  }
  
  @Nullable
  final Object joinSuspend(@NotNull Continuation<? super Unit> paramContinuation)
  {
    paramContinuation = new CancellableContinuationImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation), 1);
    paramContinuation.initCancellability();
    CancellableContinuation localCancellableContinuation = (CancellableContinuation)paramContinuation;
    JobKt.disposeOnCompletion((Job)localCancellableContinuation, invokeOnCompletion((Function1)new ResumeOnCompletion((Job)this, (Continuation)localCancellableContinuation)));
    return paramContinuation.getResult();
  }
  
  @NotNull
  protected final Void loopOnState(@NotNull Function1<Object, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    for (;;)
    {
      paramFunction1.invoke(access$getState$p(this));
    }
  }
  
  @NotNull
  public CoroutineContext minusKey(@NotNull CoroutineContext.Key<?> paramKey)
  {
    Intrinsics.checkParameterIsNotNull(paramKey, "key");
    return Job.DefaultImpls.minusKey(this, paramKey);
  }
  
  protected void onCancellation() {}
  
  protected void onParentCancellation(@Nullable Throwable paramThrowable)
  {
    Throwable localThrowable = paramThrowable;
    if (!(paramThrowable instanceof CancellationException)) {
      localThrowable = null;
    }
    cancel((Throwable)localThrowable);
  }
  
  protected void onStart() {}
  
  @NotNull
  public CoroutineContext plus(@NotNull CoroutineContext paramCoroutineContext)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    return Job.DefaultImpls.plus(this, paramCoroutineContext);
  }
  
  @Deprecated(level=DeprecationLevel.ERROR, message="Operator '+' on two Job objects is meaningless. Job is a coroutine context element and `+` is a set-sum operator for coroutine contexts. The job to the right of `+` just replaces the job the left of `+`.")
  @NotNull
  public Job plus(@NotNull Job paramJob)
  {
    Intrinsics.checkParameterIsNotNull(paramJob, "other");
    return Job.DefaultImpls.plus(this, paramJob);
  }
  
  protected final <R> void registerSelectAwaitInternal(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function2<Object, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    Object localObject;
    do
    {
      localObject = access$getState$p(this);
      if (paramSelectInstance.isSelected()) {
        return;
      }
      if (!(localObject instanceof Incomplete))
      {
        if (paramSelectInstance.trySelect(null))
        {
          if ((localObject instanceof CompletedExceptionally))
          {
            paramSelectInstance.resumeSelectCancellableWithException(((CompletedExceptionally)localObject).getException());
            return;
          }
          UndispatchedKt.startCoroutineUndispatched(paramFunction2, localObject, paramSelectInstance.getCompletion());
        }
        return;
      }
    } while (startInternal(localObject) != 0);
    paramSelectInstance.disposeOnSelect(invokeOnCompletion((Function1)new SelectAwaitOnCompletion(this, paramSelectInstance, paramFunction2)));
  }
  
  public <R> void registerSelectJoin(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    Object localObject;
    do
    {
      localObject = access$getState$p(this);
      if (paramSelectInstance.isSelected()) {
        return;
      }
      if (!(localObject instanceof Incomplete))
      {
        if (paramSelectInstance.trySelect(null)) {
          UndispatchedKt.startCoroutineUndispatched(paramFunction1, paramSelectInstance.getCompletion());
        }
        return;
      }
    } while (startInternal(localObject) != 0);
    paramSelectInstance.disposeOnSelect(invokeOnCompletion((Function1)new SelectJoinOnCompletion(this, paramSelectInstance, paramFunction1)));
  }
  
  public final void removeNode$kotlinx_coroutines_core(@NotNull JobNode<?> paramJobNode)
  {
    Intrinsics.checkParameterIsNotNull(paramJobNode, "node");
    Object localObject;
    do
    {
      localObject = access$getState$p(this);
      if (!(localObject instanceof JobNode)) {
        break;
      }
      if (localObject != paramJobNode) {
        return;
      }
    } while (!_state$FU.compareAndSet(this, localObject, JobKt.access$getEmptyActive$p()));
    return;
    if (((localObject instanceof NodeList)) || ((localObject instanceof Cancelling)))
    {
      paramJobNode.remove();
      return;
    }
  }
  
  public final <R> void selectAwaitCompletion$kotlinx_coroutines_core(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function2<Object, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    Object localObject = getState();
    if ((localObject instanceof CompletedExceptionally))
    {
      paramSelectInstance.resumeSelectCancellableWithException(((CompletedExceptionally)localObject).getException());
      return;
    }
    CancellableKt.startCoroutineCancellable(paramFunction2, localObject, paramSelectInstance.getCompletion());
  }
  
  public final boolean start()
  {
    for (;;)
    {
      switch (startInternal(access$getState$p(this)))
      {
      }
    }
    return true;
    return false;
  }
  
  @NotNull
  public String toString()
  {
    Object localObject2 = getState();
    Object localObject1;
    if ((localObject2 instanceof Incomplete))
    {
      localObject1 = "";
    }
    else
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append('[');
      ((StringBuilder)localObject1).append(localObject2);
      ((StringBuilder)localObject1).append(']');
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(getClass().getSimpleName());
    localStringBuilder.append('{');
    localStringBuilder.append(JobKt.stateToString(localObject2));
    localStringBuilder.append('}');
    localStringBuilder.append((String)localObject1);
    localStringBuilder.append('@');
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    return localStringBuilder.toString();
  }
  
  protected final boolean tryUpdateState(@NotNull Object paramObject1, @Nullable Object paramObject2)
  {
    Intrinsics.checkParameterIsNotNull(paramObject1, "expect");
    int i;
    if (((paramObject1 instanceof Incomplete)) && (!(paramObject2 instanceof Incomplete))) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalArgumentException("Failed requirement.".toString()));
    }
    if (!_state$FU.compareAndSet(this, paramObject1, paramObject2)) {
      return false;
    }
    paramObject1 = parentHandle;
    if (paramObject1 != null) {
      paramObject1.dispose();
    }
    return true;
  }
  
  protected final boolean updateState(@NotNull Object paramObject1, @Nullable Object paramObject2, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramObject1, "expect");
    Object localObject = coerceProposedUpdate(paramObject1, paramObject2);
    if (!tryUpdateState(paramObject1, localObject)) {
      return false;
    }
    completeUpdateState(paramObject1, localObject, paramInt);
    if ((paramObject2 != localObject) && ((paramObject2 instanceof CompletedExceptionally)))
    {
      paramObject1 = (CompletedExceptionally)paramObject2;
      if (cause != null) {
        handleException(cause);
      }
    }
    return true;
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\030\002\n\000\n\002\020\003\n\002\b\002\030\0002\0020\001B\017\022\b\020\002\032\004\030\0010\003¢\006\002\020\004¨\006\005"}, d2={"Lkotlinx/coroutines/experimental/JobSupport$Cancelled;", "Lkotlinx/coroutines/experimental/JobSupport$CompletedExceptionally;", "cause", "", "(Ljava/lang/Throwable;)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static final class Cancelled
    extends JobSupport.CompletedExceptionally
  {
    public Cancelled(@Nullable Throwable paramThrowable)
    {
      super();
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000 \n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\002\b\002\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006R\020\020\004\032\0020\0058\006X\004¢\006\002\n\000R\024\020\007\032\0020\b8VX\004¢\006\006\032\004\b\007\020\tR\020\020\002\032\0020\0038\006X\004¢\006\002\n\000¨\006\n"}, d2={"Lkotlinx/coroutines/experimental/JobSupport$Cancelling;", "Lkotlinx/coroutines/experimental/JobSupport$Incomplete;", "list", "Lkotlinx/coroutines/experimental/JobSupport$NodeList;", "cancelled", "Lkotlinx/coroutines/experimental/JobSupport$Cancelled;", "(Lkotlinx/coroutines/experimental/JobSupport$NodeList;Lkotlinx/coroutines/experimental/JobSupport$Cancelled;)V", "isActive", "", "()Z", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class Cancelling
    implements JobSupport.Incomplete
  {
    @JvmField
    @NotNull
    public final JobSupport.Cancelled cancelled;
    @JvmField
    @NotNull
    public final JobSupport.NodeList list;
    
    public Cancelling(@NotNull JobSupport.NodeList paramNodeList, @NotNull JobSupport.Cancelled paramCancelled)
    {
      list = paramNodeList;
      cancelled = paramCancelled;
    }
    
    public boolean isActive()
    {
      return false;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\020\000\n\000\n\002\020\003\n\002\b\006\n\002\020\016\n\000\b\026\030\0002\0020\001B\017\022\b\020\002\032\004\030\0010\003¢\006\002\020\004J\b\020\t\032\0020\nH\026R\024\020\005\032\004\030\0010\0038\002@\002X\016¢\006\002\n\000R\022\020\002\032\004\030\0010\0038\006X\004¢\006\002\n\000R\021\020\006\032\0020\0038F¢\006\006\032\004\b\007\020\b¨\006\013"}, d2={"Lkotlinx/coroutines/experimental/JobSupport$CompletedExceptionally;", "", "cause", "", "(Ljava/lang/Throwable;)V", "_exception", "exception", "getException", "()Ljava/lang/Throwable;", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static class CompletedExceptionally
  {
    private volatile Throwable _exception;
    @JvmField
    @Nullable
    public final Throwable cause;
    
    public CompletedExceptionally(@Nullable Throwable paramThrowable)
    {
      cause = paramThrowable;
      _exception = cause;
    }
    
    @NotNull
    public final Throwable getException()
    {
      Throwable localThrowable = _exception;
      if (localThrowable != null) {
        return localThrowable;
      }
      localThrowable = (Throwable)new CancellationException("Job was cancelled");
      _exception = localThrowable;
      return localThrowable;
    }
    
    @NotNull
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("");
      localStringBuilder.append(getClass().getSimpleName());
      localStringBuilder.append('[');
      localStringBuilder.append(getException());
      localStringBuilder.append(']');
      return localStringBuilder.toString();
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\020\013\n\002\b\002\bf\030\0002\0020\001R\022\020\002\032\0020\003X¦\004¢\006\006\032\004\b\002\020\004¨\006\005"}, d2={"Lkotlinx/coroutines/experimental/JobSupport$Incomplete;", "", "isActive", "", "()Z", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static abstract interface Incomplete
  {
    public abstract boolean isActive();
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020\016\n\000\b\002\030\0002\0020\0012\0020\002B\r\022\006\020\003\032\0020\004¢\006\002\020\005J\b\020\f\032\0020\rH\026R\021\020\006\032\0020\007¢\006\b\n\000\032\004\b\b\020\tR\024\020\n\032\0020\0048VX\004¢\006\006\032\004\b\n\020\013¨\006\016"}, d2={"Lkotlinx/coroutines/experimental/JobSupport$NodeList;", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/experimental/JobSupport$Incomplete;", "active", "", "(Z)V", "_active", "Lkotlinx/atomicfu/AtomicInt;", "get_active", "()Lkotlinx/atomicfu/AtomicInt;", "isActive", "()Z", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class NodeList
    extends LockFreeLinkedListHead
    implements JobSupport.Incomplete
  {
    static final AtomicIntegerFieldUpdater _active$FU = AtomicIntegerFieldUpdater.newUpdater(NodeList.class, "_active");
    volatile int _active;
    
    public NodeList(boolean paramBoolean) {}
    
    public boolean isActive()
    {
      return _active != 0;
    }
    
    @NotNull
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("List");
      if (isActive()) {
        localObject = "{Active}";
      } else {
        localObject = "{New}";
      }
      localStringBuilder.append((String)localObject);
      localStringBuilder.append("[");
      Object localObject = getNext();
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      localObject = (LockFreeLinkedListNode)localObject;
      int j;
      for (int i = 1; (Intrinsics.areEqual(localObject, (LockFreeLinkedListHead)this) ^ true); i = j)
      {
        j = i;
        if ((localObject instanceof JobNode))
        {
          JobNode localJobNode = (JobNode)localObject;
          if (i != 0) {
            i = 0;
          } else {
            localStringBuilder.append(", ");
          }
          localStringBuilder.append(localJobNode);
          j = i;
        }
        localObject = LockFreeLinkedListKt.unwrap(((LockFreeLinkedListNode)localObject).getNext());
      }
      localStringBuilder.append("]");
      localObject = localStringBuilder.toString();
      Intrinsics.checkExpressionValueIsNotNull(localObject, "StringBuilder().apply(builderAction).toString()");
      return localObject;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\020\003\n\000\n\002\020\016\n\000\b\004\030\0002\b\022\004\022\0020\0020\001B\r\022\006\020\003\032\0020\002¢\006\002\020\004J\022\020\005\032\0020\0062\b\020\007\032\004\030\0010\bH\026J\b\020\t\032\0020\nH\026¨\006\013"}, d2={"Lkotlinx/coroutines/experimental/JobSupport$ParentOnCancellation;", "Lkotlinx/coroutines/experimental/JobCancellationNode;", "Lkotlinx/coroutines/experimental/Job;", "parent", "(Lkotlinx/coroutines/experimental/JobSupport;Lkotlinx/coroutines/experimental/Job;)V", "invokeOnce", "", "reason", "", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private final class ParentOnCancellation
    extends JobCancellationNode<Job>
  {
    public ParentOnCancellation()
    {
      super();
    }
    
    public void invokeOnce(@Nullable Throwable paramThrowable)
    {
      onParentCancellation(paramThrowable);
    }
    
    @NotNull
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ParentOnCancellation[");
      localStringBuilder.append(JobSupport.this);
      localStringBuilder.append(']');
      return localStringBuilder.toString();
    }
  }
}

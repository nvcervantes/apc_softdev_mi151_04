package kotlinx.coroutines.experimental.sync;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutinesKt;
import kotlin.coroutines.experimental.jvm.internal.CoroutineIntrinsics;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.CancellableContinuation;
import kotlinx.coroutines.experimental.CancellableContinuation.DefaultImpls;
import kotlinx.coroutines.experimental.CancellableContinuationImpl;
import kotlinx.coroutines.experimental.CancellableContinuationKt;
import kotlinx.coroutines.experimental.DisposableHandle;
import kotlinx.coroutines.experimental.DisposableHandle.DefaultImpls;
import kotlinx.coroutines.experimental.internal.AtomicDesc;
import kotlinx.coroutines.experimental.internal.AtomicOp;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode.AddLastDesc;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.experimental.internal.OpDescriptor;
import kotlinx.coroutines.experimental.internal.Symbol;
import kotlinx.coroutines.experimental.intrinsics.UndispatchedKt;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import kotlinx.coroutines.experimental.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000H\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\002\020\000\n\002\b\006\n\002\020\002\n\002\b\b\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\013\b\000\030\0002\0020\001:\b$%&'()*+B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\b\020\r\032\0020\016H\002J\020\020\017\032\0020\0032\006\020\020\032\0020\007H\026J\033\020\021\032\0020\0162\b\020\020\032\004\030\0010\007H@ø\001\000¢\006\002\020\022J\033\020\023\032\0020\0162\b\020\020\032\004\030\0010\007H@ø\001\000¢\006\002\020\022JL\020\024\032\0020\016\"\004\b\000\020\0252\f\020\026\032\b\022\004\022\002H\0250\0272\b\020\020\032\004\030\0010\0072\034\020\030\032\030\b\001\022\n\022\b\022\004\022\002H\0250\032\022\006\022\004\030\0010\0070\031H\026ø\001\000¢\006\002\020\033J\030\020\034\032\0020\0032\006\020\035\032\0020\0362\006\020\037\032\0020\007H\002J\b\020 \032\0020!H\026J\022\020\"\032\0020\0032\b\020\020\032\004\030\0010\007H\026J\022\020#\032\0020\0162\b\020\020\032\004\030\0010\007H\026R\024\020\005\032\b\022\004\022\0020\0070\006X\004¢\006\002\n\000R\026\020\b\032\n\022\006\022\004\030\0010\0070\006X\004¢\006\002\n\000R\024\020\t\032\0020\0038VX\004¢\006\006\032\004\b\t\020\nR\024\020\013\032\0020\0038@X\004¢\006\006\032\004\b\f\020\n\002\004\n\002\b\t¨\006,"}, d2={"Lkotlinx/coroutines/experimental/sync/MutexImpl;", "Lkotlinx/coroutines/experimental/sync/Mutex;", "locked", "", "(Z)V", "_resumeNext", "Lkotlinx/atomicfu/AtomicRef;", "", "_state", "isLocked", "()Z", "isLockedEmptyQueueState", "isLockedEmptyQueueState$kotlinx_coroutines_core", "finishResumeNext", "", "holdsLock", "owner", "lock", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "lockSuspend", "registerSelectLock", "R", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "startResumeNext", "waiter", "Lkotlinx/coroutines/experimental/sync/MutexImpl$LockWaiter;", "token", "toString", "", "tryLock", "unlock", "LockCont", "LockSelect", "LockWaiter", "LockedQueue", "ResumeReq", "TryEnqueueLockDesc", "TryLockDesc", "UnlockOp", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class MutexImpl
  implements Mutex
{
  private static final AtomicReferenceFieldUpdater _resumeNext$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "_resumeNext");
  static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(MutexImpl.class, Object.class, "_state");
  private volatile Object _resumeNext;
  volatile Object _state;
  
  public MutexImpl(boolean paramBoolean)
  {
    Empty localEmpty;
    if (paramBoolean) {
      localEmpty = MutexKt.access$getEmptyLocked$p();
    } else {
      localEmpty = MutexKt.access$getEmptyUnlocked$p();
    }
    _state = localEmpty;
    _resumeNext = MutexKt.access$getRESUME_QUIESCENT$p();
  }
  
  private final void finishResumeNext()
  {
    for (;;)
    {
      Object localObject = _resumeNext;
      if (localObject == MutexKt.access$getRESUME_ACTIVE$p())
      {
        if (!_resumeNext$FU.compareAndSet(this, localObject, MutexKt.access$getRESUME_QUIESCENT$p())) {}
      }
      else
      {
        if (!(localObject instanceof ResumeReq)) {
          break;
        }
        _resumeNext = MutexKt.access$getRESUME_ACTIVE$p();
        localObject = (ResumeReq)localObject;
        waiter.completeResumeLockWaiter(token);
      }
    }
    throw ((Throwable)new IllegalStateException("Cannot happen".toString()));
  }
  
  private final boolean startResumeNext(LockWaiter paramLockWaiter, Object paramObject)
  {
    Object localObject;
    do
    {
      localObject = _resumeNext;
      if (localObject == MutexKt.access$getRESUME_QUIESCENT$p())
      {
        _resumeNext = MutexKt.access$getRESUME_ACTIVE$p();
        return true;
      }
      if (localObject != MutexKt.access$getRESUME_ACTIVE$p()) {
        break;
      }
    } while (!_resumeNext$FU.compareAndSet(this, localObject, new ResumeReq(paramLockWaiter, paramObject)));
    return false;
    throw ((Throwable)new IllegalStateException("Cannot happen".toString()));
  }
  
  public boolean holdsLock(@NotNull Object paramObject)
  {
    Intrinsics.checkParameterIsNotNull(paramObject, "owner");
    Object localObject = _state;
    if ((localObject instanceof Empty))
    {
      if (locked != paramObject) {}
    }
    else {
      while (((localObject instanceof LockedQueue)) && (owner == paramObject)) {
        return true;
      }
    }
    return false;
  }
  
  public boolean isLocked()
  {
    Object localObject;
    for (;;)
    {
      localObject = _state;
      if ((localObject instanceof Empty)) {
        return locked != MutexKt.access$getUNLOCKED$p();
      }
      if ((localObject instanceof LockedQueue)) {
        return true;
      }
      if (!(localObject instanceof OpDescriptor)) {
        break;
      }
      ((OpDescriptor)localObject).perform(this);
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Illegal state ");
    localStringBuilder.append(localObject);
    throw ((Throwable)new IllegalStateException(localStringBuilder.toString().toString()));
  }
  
  public final boolean isLockedEmptyQueueState$kotlinx_coroutines_core()
  {
    Object localObject = _state;
    return ((localObject instanceof LockedQueue)) && (((LockedQueue)localObject).isEmpty());
  }
  
  @Nullable
  public Object lock(@Nullable Object paramObject, @NotNull Continuation<? super Unit> paramContinuation)
  {
    if (tryLock(paramObject)) {
      return Unit.INSTANCE;
    }
    return lockSuspend(paramObject, paramContinuation);
  }
  
  @Nullable
  final Object lockSuspend(@Nullable final Object paramObject, @NotNull Continuation<? super Unit> paramContinuation)
  {
    CancellableContinuationImpl localCancellableContinuationImpl = new CancellableContinuationImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation), 0);
    final CancellableContinuation localCancellableContinuation = (CancellableContinuation)localCancellableContinuationImpl;
    final LockCont localLockCont = new LockCont(paramObject, localCancellableContinuation);
    final Object localObject1;
    label334:
    label352:
    for (;;)
    {
      localObject1 = _state;
      if ((localObject1 instanceof Empty))
      {
        paramContinuation = (Empty)localObject1;
        if (locked != MutexKt.access$getUNLOCKED$p())
        {
          _state$FU.compareAndSet(this, localObject1, new LockedQueue(locked));
          break label352;
        }
        if (paramObject == null) {
          paramContinuation = MutexKt.access$getEmptyLocked$p();
        } else {
          paramContinuation = new Empty(paramObject);
        }
        if (!_state$FU.compareAndSet(this, localObject1, paramContinuation)) {
          break label352;
        }
        localCancellableContinuation.resume(Unit.INSTANCE);
      }
      else
      {
        if (!(localObject1 instanceof LockedQueue)) {
          break label334;
        }
        paramContinuation = (LockedQueue)localObject1;
        Object localObject2 = owner;
        int j = 1;
        if (localObject2 != paramObject) {
          i = 1;
        } else {
          i = 0;
        }
        if (i == 0)
        {
          paramContinuation = new StringBuilder();
          paramContinuation.append("Already locked by ");
          paramContinuation.append(paramObject);
          throw ((Throwable)new IllegalStateException(paramContinuation.toString().toString()));
        }
        localObject2 = (LockFreeLinkedListNode)localLockCont;
        localObject1 = (LockFreeLinkedListNode.CondAddOp)new LockFreeLinkedListNode.CondAddOp((LockFreeLinkedListNode)localObject2)
        {
          public Object prepare(LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode)
          {
            int i;
            if (jdField_this_state == localObject1) {
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
          Object localObject3 = paramContinuation.getPrev();
          if (localObject3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
          }
          i = j;
          switch (((LockFreeLinkedListNode)localObject3).tryCondAddNext((LockFreeLinkedListNode)localObject2, paramContinuation, (LockFreeLinkedListNode.CondAddOp)localObject1))
          {
          }
        }
        int i = 0;
        if (i == 0) {
          break label352;
        }
        localCancellableContinuation.initCancellability();
        CancellableContinuationKt.removeOnCancel(localCancellableContinuation, (LockFreeLinkedListNode)localObject2);
      }
      return localCancellableContinuationImpl.getResult();
      if (!(localObject1 instanceof OpDescriptor)) {
        break;
      }
      ((OpDescriptor)localObject1).perform(this);
    }
    paramObject = new StringBuilder();
    paramObject.append("Illegal state ");
    paramObject.append(localObject1);
    throw ((Throwable)new IllegalStateException(paramObject.toString().toString()));
  }
  
  public <R> void registerSelectLock(@NotNull SelectInstance<? super R> paramSelectInstance, @Nullable Object paramObject, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    Object localObject1;
    for (;;)
    {
      if (paramSelectInstance.isSelected()) {
        return;
      }
      localObject1 = _state;
      Object localObject2;
      if ((localObject1 instanceof Empty))
      {
        localObject2 = (Empty)localObject1;
        if (locked != MutexKt.access$getUNLOCKED$p())
        {
          _state$FU.compareAndSet(this, localObject1, new LockedQueue(locked));
        }
        else
        {
          localObject1 = paramSelectInstance.performAtomicTrySelect((AtomicDesc)new TryLockDesc(this, paramObject));
          if (localObject1 == null)
          {
            UndispatchedKt.startCoroutineUndispatched(paramFunction1, paramSelectInstance.getCompletion());
            return;
          }
          if (localObject1 == SelectKt.getALREADY_SELECTED()) {
            return;
          }
          if (localObject1 != MutexKt.access$getLOCK_FAIL$p())
          {
            paramSelectInstance = new StringBuilder();
            paramSelectInstance.append("performAtomicTrySelect(TryLockDesc) returned ");
            paramSelectInstance.append(localObject1);
            throw ((Throwable)new IllegalStateException(paramSelectInstance.toString().toString()));
          }
        }
      }
      else if ((localObject1 instanceof LockedQueue))
      {
        localObject1 = (LockedQueue)localObject1;
        int i;
        if (owner != paramObject) {
          i = 1;
        } else {
          i = 0;
        }
        if (i == 0)
        {
          paramSelectInstance = new StringBuilder();
          paramSelectInstance.append("Already locked by ");
          paramSelectInstance.append(paramObject);
          throw ((Throwable)new IllegalStateException(paramSelectInstance.toString().toString()));
        }
        localObject2 = new TryEnqueueLockDesc(this, paramObject, (LockedQueue)localObject1, paramSelectInstance, paramFunction1);
        localObject1 = paramSelectInstance.performAtomicIfNotSelected((AtomicDesc)localObject2);
        if (localObject1 == null)
        {
          paramSelectInstance.disposeOnSelect((DisposableHandle)node);
          return;
        }
        if (localObject1 == SelectKt.getALREADY_SELECTED()) {
          return;
        }
        if (localObject1 != MutexKt.access$getENQUEUE_FAIL$p())
        {
          paramSelectInstance = new StringBuilder();
          paramSelectInstance.append("performAtomicIfNotSelected(TryEnqueueLockDesc) returned ");
          paramSelectInstance.append(localObject1);
          throw ((Throwable)new IllegalStateException(paramSelectInstance.toString().toString()));
        }
      }
      else
      {
        if (!(localObject1 instanceof OpDescriptor)) {
          break;
        }
        ((OpDescriptor)localObject1).perform(this);
      }
    }
    paramSelectInstance = new StringBuilder();
    paramSelectInstance.append("Illegal state ");
    paramSelectInstance.append(localObject1);
    throw ((Throwable)new IllegalStateException(paramSelectInstance.toString().toString()));
  }
  
  @NotNull
  public String toString()
  {
    Object localObject;
    for (;;)
    {
      localObject = _state;
      if ((localObject instanceof Empty))
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Mutex[");
        localStringBuilder.append(locked);
        localStringBuilder.append(']');
        return localStringBuilder.toString();
      }
      if (!(localObject instanceof OpDescriptor)) {
        break;
      }
      ((OpDescriptor)localObject).perform(this);
    }
    if ((localObject instanceof LockedQueue))
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Mutex[");
      localStringBuilder.append(owner);
      localStringBuilder.append(']');
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Illegal state ");
    localStringBuilder.append(localObject);
    throw ((Throwable)new IllegalStateException(localStringBuilder.toString().toString()));
  }
  
  public boolean tryLock(@Nullable Object paramObject)
  {
    Object localObject2;
    for (;;)
    {
      localObject2 = _state;
      boolean bool = localObject2 instanceof Empty;
      int i = 1;
      Object localObject1;
      if (bool)
      {
        if (locked != MutexKt.access$getUNLOCKED$p()) {
          return false;
        }
        if (paramObject == null) {
          localObject1 = MutexKt.access$getEmptyLocked$p();
        } else {
          localObject1 = new Empty(paramObject);
        }
        if (_state$FU.compareAndSet(this, localObject2, localObject1)) {
          return true;
        }
      }
      else
      {
        if ((localObject2 instanceof LockedQueue))
        {
          if (owner == paramObject) {
            i = 0;
          }
          if (i == 0)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Already locked by ");
            ((StringBuilder)localObject1).append(paramObject);
            throw ((Throwable)new IllegalStateException(((StringBuilder)localObject1).toString().toString()));
          }
          return false;
        }
        if (!(localObject2 instanceof OpDescriptor)) {
          break;
        }
        ((OpDescriptor)localObject2).perform(this);
      }
    }
    paramObject = new StringBuilder();
    paramObject.append("Illegal state ");
    paramObject.append(localObject2);
    throw ((Throwable)new IllegalStateException(paramObject.toString().toString()));
  }
  
  public void unlock(@Nullable Object paramObject)
  {
    Object localObject2;
    Object localObject1;
    Object localObject3;
    do
    {
      do
      {
        int k;
        int i;
        for (;;)
        {
          localObject2 = _state;
          boolean bool = localObject2 instanceof Empty;
          int j = 0;
          k = 0;
          i = 0;
          if (bool)
          {
            if (paramObject == null)
            {
              if (locked != MutexKt.access$getUNLOCKED$p()) {
                i = 1;
              }
              if (i == 0) {
                throw ((Throwable)new IllegalStateException("Mutex is not locked".toString()));
              }
            }
            else
            {
              localObject1 = (Empty)localObject2;
              i = j;
              if (locked == paramObject) {
                i = 1;
              }
              if (i == 0)
              {
                localObject2 = new StringBuilder();
                ((StringBuilder)localObject2).append("Mutex is locked by ");
                ((StringBuilder)localObject2).append(locked);
                ((StringBuilder)localObject2).append(" but expected ");
                ((StringBuilder)localObject2).append(paramObject);
                throw ((Throwable)new IllegalStateException(((StringBuilder)localObject2).toString().toString()));
              }
            }
            if (!_state$FU.compareAndSet(this, localObject2, MutexKt.access$getEmptyUnlocked$p())) {}
          }
          else
          {
            if (!(localObject2 instanceof OpDescriptor)) {
              break;
            }
            ((OpDescriptor)localObject2).perform(this);
          }
        }
        if (!(localObject2 instanceof LockedQueue)) {
          break label411;
        }
        if (paramObject != null)
        {
          localObject1 = (LockedQueue)localObject2;
          i = k;
          if (owner == paramObject) {
            i = 1;
          }
          if (i == 0)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Mutex is locked by ");
            ((StringBuilder)localObject2).append(owner);
            ((StringBuilder)localObject2).append(" but expected ");
            ((StringBuilder)localObject2).append(paramObject);
            throw ((Throwable)new IllegalStateException(((StringBuilder)localObject2).toString().toString()));
          }
        }
        localObject1 = (LockedQueue)localObject2;
        localObject3 = ((LockedQueue)localObject1).removeFirstOrNull();
        if (localObject3 != null) {
          break;
        }
        localObject1 = new UnlockOp((LockedQueue)localObject1);
      } while ((!_state$FU.compareAndSet(this, localObject2, localObject1)) || (((UnlockOp)localObject1).perform(this) != null));
      return;
      localObject2 = (LockWaiter)localObject3;
      localObject3 = ((LockWaiter)localObject2).tryResumeLockWaiter();
    } while (localObject3 == null);
    paramObject = owner;
    if (paramObject == null) {
      paramObject = MutexKt.access$getLOCKED$p();
    }
    owner = paramObject;
    if (startResumeNext((LockWaiter)localObject2, localObject3))
    {
      ((LockWaiter)localObject2).completeResumeLockWaiter(localObject3);
      finishResumeNext();
    }
    return;
    label411:
    paramObject = new StringBuilder();
    paramObject.append("Illegal state ");
    paramObject.append(localObject2);
    throw ((Throwable)new IllegalStateException(paramObject.toString().toString()));
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\002\030\002\n\000\n\002\020\000\n\000\n\002\030\002\n\002\020\002\n\002\b\004\n\002\020\016\n\002\b\002\b\002\030\0002\0020\001B\035\022\b\020\002\032\004\030\0010\003\022\f\020\004\032\b\022\004\022\0020\0060\005¢\006\002\020\007J\020\020\b\032\0020\0062\006\020\t\032\0020\003H\026J\b\020\n\032\0020\013H\026J\n\020\f\032\004\030\0010\003H\026R\026\020\004\032\b\022\004\022\0020\0060\0058\006X\004¢\006\002\n\000¨\006\r"}, d2={"Lkotlinx/coroutines/experimental/sync/MutexImpl$LockCont;", "Lkotlinx/coroutines/experimental/sync/MutexImpl$LockWaiter;", "owner", "", "cont", "Lkotlinx/coroutines/experimental/CancellableContinuation;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/experimental/CancellableContinuation;)V", "completeResumeLockWaiter", "token", "toString", "", "tryResumeLockWaiter", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class LockCont
    extends MutexImpl.LockWaiter
  {
    @JvmField
    @NotNull
    public final CancellableContinuation<Unit> cont;
    
    public LockCont(@Nullable Object paramObject, @NotNull CancellableContinuation<? super Unit> paramCancellableContinuation)
    {
      super();
      cont = paramCancellableContinuation;
    }
    
    public void completeResumeLockWaiter(@NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramObject, "token");
      cont.completeResume(paramObject);
    }
    
    @NotNull
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("LockCont[");
      localStringBuilder.append(owner);
      localStringBuilder.append(", ");
      localStringBuilder.append(cont);
      localStringBuilder.append(']');
      return localStringBuilder.toString();
    }
    
    @Nullable
    public Object tryResumeLockWaiter()
    {
      return CancellableContinuation.DefaultImpls.tryResume$default(cont, Unit.INSTANCE, null, 2, null);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\0004\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\002\b\002\n\002\020\016\n\002\b\002\b\002\030\000*\004\b\000\020\0012\0020\002B>\022\b\020\003\032\004\030\0010\004\022\f\020\005\032\b\022\004\022\0028\0000\006\022\034\020\007\032\030\b\001\022\n\022\b\022\004\022\0028\0000\t\022\006\022\004\030\0010\0040\bø\001\000¢\006\002\020\nJ\020\020\f\032\0020\r2\006\020\016\032\0020\004H\026J\b\020\017\032\0020\020H\026J\n\020\021\032\004\030\0010\004H\026R+\020\007\032\030\b\001\022\n\022\b\022\004\022\0028\0000\t\022\006\022\004\030\0010\0040\b8\006X\004ø\001\000¢\006\004\n\002\020\013R\026\020\005\032\b\022\004\022\0028\0000\0068\006X\004¢\006\002\n\000\002\004\n\002\b\t¨\006\022"}, d2={"Lkotlinx/coroutines/experimental/sync/MutexImpl$LockSelect;", "R", "Lkotlinx/coroutines/experimental/sync/MutexImpl$LockWaiter;", "owner", "", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "Lkotlin/jvm/functions/Function1;", "completeResumeLockWaiter", "", "token", "toString", "", "tryResumeLockWaiter", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class LockSelect<R>
    extends MutexImpl.LockWaiter
  {
    @JvmField
    @NotNull
    public final Function1<Continuation<? super R>, Object> block;
    @JvmField
    @NotNull
    public final SelectInstance<R> select;
    
    public LockSelect(@Nullable Object paramObject, @NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
    {
      super();
      select = paramSelectInstance;
      block = paramFunction1;
    }
    
    public void completeResumeLockWaiter(@NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramObject, "token");
      int i;
      if (paramObject == MutexKt.access$getSELECT_SUCCESS$p()) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 0) {
        throw ((Throwable)new IllegalStateException("Check failed.".toString()));
      }
      CoroutinesKt.startCoroutine(block, select.getCompletion());
    }
    
    @NotNull
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("LockSelect[");
      localStringBuilder.append(owner);
      localStringBuilder.append(", ");
      localStringBuilder.append(select);
      localStringBuilder.append(']');
      return localStringBuilder.toString();
    }
    
    @Nullable
    public Object tryResumeLockWaiter()
    {
      SelectInstance localSelectInstance = select;
      Symbol localSymbol = null;
      if (localSelectInstance.trySelect(null)) {
        localSymbol = MutexKt.access$getSELECT_SUCCESS$p();
      }
      return localSymbol;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\036\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\000\n\002\b\002\n\002\020\002\n\002\b\004\b\"\030\0002\0020\0012\0020\002B\017\022\b\020\003\032\004\030\0010\004¢\006\002\020\005J\020\020\006\032\0020\0072\006\020\b\032\0020\004H&J\006\020\t\032\0020\007J\n\020\n\032\004\030\0010\004H&R\022\020\003\032\004\030\0010\0048\006X\004¢\006\002\n\000¨\006\013"}, d2={"Lkotlinx/coroutines/experimental/sync/MutexImpl$LockWaiter;", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/DisposableHandle;", "owner", "", "(Ljava/lang/Object;)V", "completeResumeLockWaiter", "", "token", "dispose", "tryResumeLockWaiter", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static abstract class LockWaiter
    extends LockFreeLinkedListNode
    implements DisposableHandle
  {
    @JvmField
    @Nullable
    public final Object owner;
    
    public LockWaiter(@Nullable Object paramObject)
    {
      owner = paramObject;
    }
    
    public abstract void completeResumeLockWaiter(@NotNull Object paramObject);
    
    public final void dispose()
    {
      remove();
    }
    
    @Nullable
    public abstract Object tryResumeLockWaiter();
    
    @Deprecated(message="Replace with `dispose`", replaceWith=@ReplaceWith(expression="dispose()", imports={}))
    public void unregister()
    {
      DisposableHandle.DefaultImpls.unregister(this);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\030\002\n\000\n\002\020\000\n\002\b\002\n\002\020\016\n\000\b\002\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\b\020\005\032\0020\006H\026R\022\020\002\032\0020\0038\006@\006X\016¢\006\002\n\000¨\006\007"}, d2={"Lkotlinx/coroutines/experimental/sync/MutexImpl$LockedQueue;", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;", "owner", "", "(Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class LockedQueue
    extends LockFreeLinkedListHead
  {
    @JvmField
    @NotNull
    public Object owner;
    
    public LockedQueue(@NotNull Object paramObject)
    {
      owner = paramObject;
    }
    
    @NotNull
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("LockedQueue[");
      localStringBuilder.append(owner);
      localStringBuilder.append(']');
      return localStringBuilder.toString();
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\002\b\003\b\002\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\001¢\006\002\020\005R\020\020\004\032\0020\0018\006X\004¢\006\002\n\000R\020\020\002\032\0020\0038\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lkotlinx/coroutines/experimental/sync/MutexImpl$ResumeReq;", "", "waiter", "Lkotlinx/coroutines/experimental/sync/MutexImpl$LockWaiter;", "token", "(Lkotlinx/coroutines/experimental/sync/MutexImpl$LockWaiter;Ljava/lang/Object;)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class ResumeReq
  {
    @JvmField
    @NotNull
    public final Object token;
    @JvmField
    @NotNull
    public final MutexImpl.LockWaiter waiter;
    
    public ResumeReq(@NotNull MutexImpl.LockWaiter paramLockWaiter, @NotNull Object paramObject)
    {
      waiter = paramLockWaiter;
      token = paramObject;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000@\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\b\002\030\000*\004\b\000\020\0012\036\022\n\022\b\022\004\022\002H\0010\0030\002j\016\022\n\022\b\022\004\022\002H\0010\003`\004BN\022\006\020\005\032\0020\006\022\b\020\007\032\004\030\0010\b\022\006\020\t\032\0020\n\022\f\020\013\032\b\022\004\022\0028\0000\f\022\034\020\r\032\030\b\001\022\n\022\b\022\004\022\0028\0000\017\022\006\022\004\030\0010\b0\016ø\001\000¢\006\002\020\020J\032\020\021\032\004\030\0010\b2\006\020\022\032\0020\0232\006\020\024\032\0020\023H\024R\020\020\005\032\0020\0068\006X\004¢\006\002\n\000\002\004\n\002\b\t¨\006\025"}, d2={"Lkotlinx/coroutines/experimental/sync/MutexImpl$TryEnqueueLockDesc;", "R", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/experimental/sync/MutexImpl$LockSelect;", "Lkotlinx/coroutines/experimental/internal/AddLastDesc;", "mutex", "Lkotlinx/coroutines/experimental/sync/MutexImpl;", "owner", "", "queue", "Lkotlinx/coroutines/experimental/sync/MutexImpl$LockedQueue;", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlinx/coroutines/experimental/sync/MutexImpl;Ljava/lang/Object;Lkotlinx/coroutines/experimental/sync/MutexImpl$LockedQueue;Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "onPrepare", "affected", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "next", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class TryEnqueueLockDesc<R>
    extends LockFreeLinkedListNode.AddLastDesc<MutexImpl.LockSelect<R>>
  {
    @JvmField
    @NotNull
    public final MutexImpl mutex;
    
    public TryEnqueueLockDesc(@NotNull MutexImpl paramMutexImpl, @Nullable Object paramObject, @NotNull MutexImpl.LockedQueue paramLockedQueue, @NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
    {
      super((LockFreeLinkedListNode)new MutexImpl.LockSelect(paramObject, paramSelectInstance, paramFunction1));
      mutex = paramMutexImpl;
    }
    
    @Nullable
    protected Object onPrepare(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "affected");
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
      if (mutex._state != queue) {
        return MutexKt.access$getENQUEUE_FAIL$p();
      }
      return super.onPrepare(paramLockFreeLinkedListNode1, paramLockFreeLinkedListNode2);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\000\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\002\b\004\b\002\030\0002\0020\001:\001\rB\027\022\006\020\002\032\0020\003\022\b\020\004\032\004\030\0010\005¢\006\002\020\006J\036\020\007\032\0020\b2\n\020\t\032\006\022\002\b\0030\n2\b\020\013\032\004\030\0010\005H\026J\026\020\f\032\004\030\0010\0052\n\020\t\032\006\022\002\b\0030\nH\026R\020\020\002\032\0020\0038\006X\004¢\006\002\n\000R\022\020\004\032\004\030\0010\0058\006X\004¢\006\002\n\000¨\006\016"}, d2={"Lkotlinx/coroutines/experimental/sync/MutexImpl$TryLockDesc;", "Lkotlinx/coroutines/experimental/internal/AtomicDesc;", "mutex", "Lkotlinx/coroutines/experimental/sync/MutexImpl;", "owner", "", "(Lkotlinx/coroutines/experimental/sync/MutexImpl;Ljava/lang/Object;)V", "complete", "", "op", "Lkotlinx/coroutines/experimental/internal/AtomicOp;", "failure", "prepare", "PrepareOp", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class TryLockDesc
    extends AtomicDesc
  {
    @JvmField
    @NotNull
    public final MutexImpl mutex;
    @JvmField
    @Nullable
    public final Object owner;
    
    public TryLockDesc(@NotNull MutexImpl paramMutexImpl, @Nullable Object paramObject)
    {
      mutex = paramMutexImpl;
      owner = paramObject;
    }
    
    public void complete(@NotNull AtomicOp<?> paramAtomicOp, @Nullable Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramAtomicOp, "op");
      if (paramObject != null) {
        paramObject = MutexKt.access$getEmptyUnlocked$p();
      } else if (owner == null) {
        paramObject = MutexKt.access$getEmptyLocked$p();
      } else {
        paramObject = new Empty(owner);
      }
      MutexImpl localMutexImpl = mutex;
      MutexImpl._state$FU.compareAndSet(localMutexImpl, paramAtomicOp, paramObject);
    }
    
    @Nullable
    public Object prepare(@NotNull AtomicOp<?> paramAtomicOp)
    {
      Intrinsics.checkParameterIsNotNull(paramAtomicOp, "op");
      paramAtomicOp = new PrepareOp(paramAtomicOp);
      MutexImpl localMutexImpl = mutex;
      if (!MutexImpl._state$FU.compareAndSet(localMutexImpl, MutexKt.access$getEmptyUnlocked$p(), paramAtomicOp)) {
        return MutexKt.access$getLOCK_FAIL$p();
      }
      return paramAtomicOp.perform(mutex);
    }
    
    @Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\000\n\002\b\002\b\004\030\0002\0020\001B\021\022\n\020\002\032\006\022\002\b\0030\003¢\006\002\020\004J\024\020\005\032\004\030\0010\0062\b\020\007\032\004\030\0010\006H\026R\022\020\002\032\006\022\002\b\0030\003X\004¢\006\002\n\000¨\006\b"}, d2={"Lkotlinx/coroutines/experimental/sync/MutexImpl$TryLockDesc$PrepareOp;", "Lkotlinx/coroutines/experimental/internal/OpDescriptor;", "op", "Lkotlinx/coroutines/experimental/internal/AtomicOp;", "(Lkotlinx/coroutines/experimental/sync/MutexImpl$TryLockDesc;Lkotlinx/coroutines/experimental/internal/AtomicOp;)V", "perform", "", "affected", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
    private final class PrepareOp
      extends OpDescriptor
    {
      private final AtomicOp<?> op;
      
      public PrepareOp()
      {
        op = localObject;
      }
      
      @Nullable
      public Object perform(@Nullable Object paramObject)
      {
        Object localObject;
        if (op.isDecided()) {
          localObject = MutexKt.access$getEmptyUnlocked$p();
        } else {
          localObject = op;
        }
        if (paramObject == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.sync.MutexImpl");
        }
        paramObject = (MutexImpl)paramObject;
        MutexImpl._state$FU.compareAndSet(paramObject, this, localObject);
        return null;
      }
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\000\n\002\b\002\b\002\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\024\020\005\032\004\030\0010\0062\b\020\007\032\004\030\0010\006H\026R\020\020\002\032\0020\0038\006X\004¢\006\002\n\000¨\006\b"}, d2={"Lkotlinx/coroutines/experimental/sync/MutexImpl$UnlockOp;", "Lkotlinx/coroutines/experimental/internal/OpDescriptor;", "queue", "Lkotlinx/coroutines/experimental/sync/MutexImpl$LockedQueue;", "(Lkotlinx/coroutines/experimental/sync/MutexImpl$LockedQueue;)V", "perform", "", "affected", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class UnlockOp
    extends OpDescriptor
  {
    @JvmField
    @NotNull
    public final MutexImpl.LockedQueue queue;
    
    public UnlockOp(@NotNull MutexImpl.LockedQueue paramLockedQueue)
    {
      queue = paramLockedQueue;
    }
    
    @Nullable
    public Object perform(@Nullable Object paramObject)
    {
      Object localObject;
      if (queue.isEmpty()) {
        localObject = MutexKt.access$getEmptyUnlocked$p();
      } else {
        localObject = queue;
      }
      if (paramObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.sync.MutexImpl");
      }
      paramObject = (MutexImpl)paramObject;
      MutexImpl._state$FU.compareAndSet(paramObject, this, localObject);
      if (_state == queue) {
        return MutexKt.access$getUNLOCK_FAIL$p();
      }
      return null;
    }
  }
}

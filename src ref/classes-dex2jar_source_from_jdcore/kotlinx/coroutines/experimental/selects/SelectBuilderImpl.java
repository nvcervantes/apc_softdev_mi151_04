package kotlinx.coroutines.experimental.selects;

import java.util.concurrent.CancellationException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.coroutines.experimental.intrinsics.IntrinsicsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.CancellableKt;
import kotlinx.coroutines.experimental.CoroutineDispatcherKt;
import kotlinx.coroutines.experimental.CoroutineExceptionHandlerKt;
import kotlinx.coroutines.experimental.Deferred;
import kotlinx.coroutines.experimental.Delay;
import kotlinx.coroutines.experimental.DelayKt;
import kotlinx.coroutines.experimental.DisposableHandle;
import kotlinx.coroutines.experimental.Job;
import kotlinx.coroutines.experimental.JobCancellationNode;
import kotlinx.coroutines.experimental.channels.ReceiveChannel;
import kotlinx.coroutines.experimental.channels.SendChannel;
import kotlinx.coroutines.experimental.internal.AtomicDesc;
import kotlinx.coroutines.experimental.internal.AtomicOp;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.experimental.internal.OpDescriptor;
import kotlinx.coroutines.experimental.intrinsics.UndispatchedKt;
import kotlinx.coroutines.experimental.sync.Mutex;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\001\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\020\000\n\002\b\005\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\002\n\002\030\002\n\002\b\004\n\002\020\002\n\002\b\004\n\002\030\002\n\002\b\004\n\002\020\003\n\002\b\003\n\002\020\t\n\000\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\n\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\007\b\001\030\000*\006\b\000\020\001 \0002\0020\0022\b\022\004\022\002H\0010\0032\b\022\004\022\002H\0010\0042\b\022\004\022\002H\0010\005:\004OPQRB\023\022\f\020\006\032\b\022\004\022\0028\0000\005¢\006\002\020\007J\020\020\033\032\0020\0342\006\020\035\032\0020\027H\026J\b\020\036\032\0020\034H\002J'\020\037\032\0020\0342\016\020 \032\n\022\006\022\004\030\0010\n0!2\f\020\"\032\b\022\004\022\0020\0340!H\bJ\n\020#\032\004\030\0010\nH\001J\020\020$\032\0020\0342\006\020%\032\0020&H\001J\b\020'\032\0020\034H\002J>\020(\032\0020\0342\006\020)\032\0020*2\006\020+\032\0020,2\034\020\"\032\030\b\001\022\n\022\b\022\004\022\0028\0000\005\022\006\022\004\030\0010\n0-H\026ø\001\000¢\006\002\020.J\022\020/\032\004\030\0010\n2\006\0200\032\00201H\026J\022\0202\032\004\030\0010\n2\006\0200\032\00201H\026J\025\0203\032\0020\0342\006\020 \032\0028\000H\026¢\006\002\0204J\020\0205\032\0020\0342\006\0206\032\0020&H\026J\020\0207\032\0020\0342\006\0206\032\0020&H\026J\022\0208\032\0020\0242\b\0209\032\004\030\0010\nH\026JD\020:\032\0020\034\"\004\b\001\020;*\b\022\004\022\002H;0<2\"\020\"\032\036\b\001\022\004\022\002H;\022\n\022\b\022\004\022\0028\0000\005\022\006\022\004\030\0010\n0=H\026ø\001\000¢\006\002\020>J2\020?\032\0020\034*\0020@2\034\020\"\032\030\b\001\022\n\022\b\022\004\022\0028\0000\005\022\006\022\004\030\0010\n0-H\026ø\001\000¢\006\002\020AJ<\020B\032\0020\034*\0020C2\b\020D\032\004\030\0010\n2\034\020\"\032\030\b\001\022\n\022\b\022\004\022\0028\0000\005\022\006\022\004\030\0010\n0-H\026ø\001\000¢\006\002\020EJD\020F\032\0020\034\"\004\b\001\020G*\b\022\004\022\002HG0H2\"\020\"\032\036\b\001\022\004\022\002HG\022\n\022\b\022\004\022\0028\0000\005\022\006\022\004\030\0010\n0=H\026ø\001\000¢\006\002\020IJF\020J\032\0020\034\"\004\b\001\020G*\b\022\004\022\002HG0H2$\020\"\032 \b\001\022\006\022\004\030\001HG\022\n\022\b\022\004\022\0028\0000\005\022\006\022\004\030\0010\n0=H\026ø\001\000¢\006\002\020IJF\020K\032\0020\034\"\004\b\001\020G*\b\022\004\022\002HG0L2\006\020M\032\002HG2\034\020\"\032\030\b\001\022\n\022\b\022\004\022\0028\0000\005\022\006\022\004\030\0010\n0-H\026ø\001\000¢\006\002\020NR\026\020\b\032\n\022\006\022\004\030\0010\n0\tX\004¢\006\002\n\000R\026\020\013\032\n\022\006\022\004\030\0010\n0\tX\004¢\006\002\n\000R\032\020\f\032\b\022\004\022\0028\0000\0058VX\004¢\006\006\032\004\b\r\020\016R\024\020\017\032\0020\0208VX\004¢\006\006\032\004\b\021\020\022R\024\020\006\032\b\022\004\022\0028\0000\005X\004¢\006\002\n\000R\024\020\023\032\0020\0248VX\004¢\006\006\032\004\b\023\020\025R\024\020\026\032\004\030\0010\0278\002@\002X\016¢\006\002\n\000R\026\020\030\032\004\030\0010\n8BX\004¢\006\006\032\004\b\031\020\032\002\004\n\002\b\t¨\006S"}, d2={"Lkotlinx/coroutines/experimental/selects/SelectBuilderImpl;", "R", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;", "Lkotlinx/coroutines/experimental/selects/SelectBuilder;", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "Lkotlin/coroutines/experimental/Continuation;", "delegate", "(Lkotlin/coroutines/experimental/Continuation;)V", "_result", "Lkotlinx/atomicfu/AtomicRef;", "", "_state", "completion", "getCompletion", "()Lkotlin/coroutines/experimental/Continuation;", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "isSelected", "", "()Z", "parentHandle", "Lkotlinx/coroutines/experimental/DisposableHandle;", "state", "getState", "()Ljava/lang/Object;", "disposeOnSelect", "", "handle", "doAfterSelect", "doResume", "value", "Lkotlin/Function0;", "block", "getResult", "handleBuilderException", "e", "", "initCancellability", "onTimeout", "time", "", "unit", "Ljava/util/concurrent/TimeUnit;", "Lkotlin/Function1;", "(JLjava/util/concurrent/TimeUnit;Lkotlin/jvm/functions/Function1;)V", "performAtomicIfNotSelected", "desc", "Lkotlinx/coroutines/experimental/internal/AtomicDesc;", "performAtomicTrySelect", "resume", "(Ljava/lang/Object;)V", "resumeSelectCancellableWithException", "exception", "resumeWithException", "trySelect", "idempotent", "onAwait", "T", "Lkotlinx/coroutines/experimental/Deferred;", "Lkotlin/Function2;", "(Lkotlinx/coroutines/experimental/Deferred;Lkotlin/jvm/functions/Function2;)V", "onJoin", "Lkotlinx/coroutines/experimental/Job;", "(Lkotlinx/coroutines/experimental/Job;Lkotlin/jvm/functions/Function1;)V", "onLock", "Lkotlinx/coroutines/experimental/sync/Mutex;", "owner", "(Lkotlinx/coroutines/experimental/sync/Mutex;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "onReceive", "E", "Lkotlinx/coroutines/experimental/channels/ReceiveChannel;", "(Lkotlinx/coroutines/experimental/channels/ReceiveChannel;Lkotlin/jvm/functions/Function2;)V", "onReceiveOrNull", "onSend", "Lkotlinx/coroutines/experimental/channels/SendChannel;", "element", "(Lkotlinx/coroutines/experimental/channels/SendChannel;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "AtomicSelectOp", "DisposeNode", "Fail", "SelectOnCancellation", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
@PublishedApi
public final class SelectBuilderImpl<R>
  extends LockFreeLinkedListHead
  implements SelectBuilder<R>, SelectInstance<R>, Continuation<R>
{
  static final AtomicReferenceFieldUpdater _result$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_result");
  static final AtomicReferenceFieldUpdater _state$FU = AtomicReferenceFieldUpdater.newUpdater(SelectBuilderImpl.class, Object.class, "_state");
  volatile Object _result;
  volatile Object _state;
  private final Continuation<R> delegate;
  private volatile DisposableHandle parentHandle;
  
  public SelectBuilderImpl(@NotNull Continuation<? super R> paramContinuation)
  {
    delegate = paramContinuation;
    _state = this;
    _result = SelectKt.access$getUNDECIDED$p();
  }
  
  private final void doAfterSelect()
  {
    Object localObject = parentHandle;
    if (localObject != null) {
      ((DisposableHandle)localObject).dispose();
    }
    localObject = getNext();
    if (localObject == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
    }
    for (localObject = (LockFreeLinkedListNode)localObject; (Intrinsics.areEqual(localObject, (LockFreeLinkedListHead)this) ^ true); localObject = LockFreeLinkedListKt.unwrap(((LockFreeLinkedListNode)localObject).getNext())) {
      if ((localObject instanceof DisposeNode)) {
        handle.dispose();
      }
    }
  }
  
  private final void doResume(Function0<? extends Object> paramFunction0, Function0<Unit> paramFunction01)
  {
    if (!isSelected()) {
      throw ((Throwable)new IllegalStateException("Must be selected first".toString()));
    }
    do
    {
      Object localObject;
      do
      {
        localObject = _result;
        if (localObject != SelectKt.access$getUNDECIDED$p()) {
          break;
        }
      } while (!_result$FU.compareAndSet(this, SelectKt.access$getUNDECIDED$p(), paramFunction0.invoke()));
      return;
      if (localObject != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        break;
      }
    } while (!_result$FU.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.access$getRESUMED$p()));
    paramFunction01.invoke();
    return;
    throw ((Throwable)new IllegalStateException("Already resumed"));
  }
  
  private final Object getState()
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
  
  private final void initCancellability()
  {
    Object localObject = (Job)getContext().get((CoroutineContext.Key)Job.Key);
    if (localObject != null)
    {
      localObject = ((Job)localObject).invokeOnCompletion((Function1)new SelectOnCancellation((Job)localObject), true);
      parentHandle = ((DisposableHandle)localObject);
      if (isSelected()) {
        ((DisposableHandle)localObject).dispose();
      }
      return;
    }
  }
  
  public void disposeOnSelect(@NotNull DisposableHandle paramDisposableHandle)
  {
    Intrinsics.checkParameterIsNotNull(paramDisposableHandle, "handle");
    DisposeNode localDisposeNode = new DisposeNode(paramDisposableHandle);
    while (getState() == (SelectBuilderImpl)this)
    {
      LockFreeLinkedListNode localLockFreeLinkedListNode = (LockFreeLinkedListNode)localDisposeNode;
      LockFreeLinkedListNode.CondAddOp localCondAddOp = (LockFreeLinkedListNode.CondAddOp)new LockFreeLinkedListNode.CondAddOp(localLockFreeLinkedListNode)
      {
        public Object prepare(LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode)
        {
          int i;
          if (SelectBuilderImpl.access$getState$p(jdField_this) == jdField_this) {
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
        Object localObject = getPrev();
        if (localObject == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
        }
        switch (((LockFreeLinkedListNode)localObject).tryCondAddNext(localLockFreeLinkedListNode, this, localCondAddOp))
        {
        }
      }
      int i = 0;
      break label118;
      i = 1;
      label118:
      if (i != 0) {
        return;
      }
    }
    paramDisposableHandle.dispose();
  }
  
  @NotNull
  public Continuation<R> getCompletion()
  {
    return (Continuation)this;
  }
  
  @NotNull
  public CoroutineContext getContext()
  {
    return delegate.getContext();
  }
  
  @PublishedApi
  @Nullable
  public final Object getResult()
  {
    if (!isSelected()) {
      initCancellability();
    }
    Object localObject2 = _result;
    Object localObject1 = localObject2;
    if (localObject2 == SelectKt.access$getUNDECIDED$p())
    {
      if (_result$FU.compareAndSet(this, SelectKt.access$getUNDECIDED$p(), IntrinsicsKt.getCOROUTINE_SUSPENDED())) {
        return IntrinsicsKt.getCOROUTINE_SUSPENDED();
      }
      localObject1 = _result;
    }
    if (localObject1 == SelectKt.access$getRESUMED$p()) {
      throw ((Throwable)new IllegalStateException("Already resumed"));
    }
    if ((localObject1 instanceof Fail)) {
      throw exception;
    }
    return localObject1;
  }
  
  @PublishedApi
  public final void handleBuilderException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "e");
    if (trySelect(null))
    {
      resumeWithException(paramThrowable);
      return;
    }
    CoroutineExceptionHandlerKt.handleCoroutineException(getContext(), paramThrowable);
  }
  
  public boolean isSelected()
  {
    return getState() != (SelectBuilderImpl)this;
  }
  
  public <T> void onAwait(@NotNull Deferred<? extends T> paramDeferred, @NotNull Function2<? super T, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramDeferred, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    paramDeferred.registerSelectAwait((SelectInstance)this, paramFunction2);
  }
  
  public void onJoin(@NotNull Job paramJob, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramJob, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    paramJob.registerSelectJoin((SelectInstance)this, paramFunction1);
  }
  
  public void onLock(@NotNull Mutex paramMutex, @Nullable Object paramObject, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMutex, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    paramMutex.registerSelectLock((SelectInstance)this, paramObject, paramFunction1);
  }
  
  public <E> void onReceive(@NotNull ReceiveChannel<? extends E> paramReceiveChannel, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramReceiveChannel, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    paramReceiveChannel.registerSelectReceive((SelectInstance)this, paramFunction2);
  }
  
  public <E> void onReceiveOrNull(@NotNull ReceiveChannel<? extends E> paramReceiveChannel, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramReceiveChannel, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    paramReceiveChannel.registerSelectReceiveOrNull((SelectInstance)this, paramFunction2);
  }
  
  public <E> void onSend(@NotNull SendChannel<? super E> paramSendChannel, E paramE, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSendChannel, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    paramSendChannel.registerSelectSend((SelectInstance)this, paramE, paramFunction1);
  }
  
  public void onTimeout(long paramLong, @NotNull TimeUnit paramTimeUnit, @NotNull final Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramTimeUnit, "unit");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    int i = 0;
    if (paramLong >= 0) {
      i = 1;
    }
    if (i == 0)
    {
      paramTimeUnit = new StringBuilder();
      paramTimeUnit.append("Timeout time ");
      paramTimeUnit.append(paramLong);
      paramTimeUnit.append(" cannot be negative");
      throw ((Throwable)new IllegalArgumentException(paramTimeUnit.toString().toString()));
    }
    if (paramLong == 0L)
    {
      if (trySelect(null)) {
        UndispatchedKt.startCoroutineUndispatched(paramFunction1, getCompletion());
      }
      return;
    }
    paramFunction1 = (Runnable)new Runnable()
    {
      public final void run()
      {
        if (this$0.trySelect(null)) {
          CancellableKt.startCoroutineCancellable(paramFunction1, this$0.getCompletion());
        }
      }
    };
    disposeOnSelect(DelayKt.getDelay(getContext()).invokeOnTimeout(paramLong, paramTimeUnit, paramFunction1));
  }
  
  @Nullable
  public Object performAtomicIfNotSelected(@NotNull AtomicDesc paramAtomicDesc)
  {
    Intrinsics.checkParameterIsNotNull(paramAtomicDesc, "desc");
    return new AtomicSelectOp(paramAtomicDesc, false).perform(null);
  }
  
  @Nullable
  public Object performAtomicTrySelect(@NotNull AtomicDesc paramAtomicDesc)
  {
    Intrinsics.checkParameterIsNotNull(paramAtomicDesc, "desc");
    return new AtomicSelectOp(paramAtomicDesc, true).perform(null);
  }
  
  public void resume(R paramR)
  {
    if (!isSelected()) {
      throw ((Throwable)new IllegalStateException("Must be selected first".toString()));
    }
    do
    {
      Object localObject;
      do
      {
        localObject = _result;
        if (localObject != SelectKt.access$getUNDECIDED$p()) {
          break;
        }
        localObject = SelectKt.access$getUNDECIDED$p();
      } while (!_result$FU.compareAndSet(this, localObject, paramR));
      return;
      if (localObject != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        break;
      }
    } while (!_result$FU.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.access$getRESUMED$p()));
    CoroutineDispatcherKt.resumeDirect(delegate, paramR);
    return;
    throw ((Throwable)new IllegalStateException("Already resumed"));
  }
  
  public void resumeSelectCancellableWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    if (!isSelected()) {
      throw ((Throwable)new IllegalStateException("Must be selected first".toString()));
    }
    do
    {
      Object localObject;
      Fail localFail;
      do
      {
        localObject = _result;
        if (localObject != SelectKt.access$getUNDECIDED$p()) {
          break;
        }
        localObject = SelectKt.access$getUNDECIDED$p();
        localFail = new Fail(paramThrowable);
      } while (!_result$FU.compareAndSet(this, localObject, localFail));
      return;
      if (localObject != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        break;
      }
    } while (!_result$FU.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.access$getRESUMED$p()));
    CoroutineDispatcherKt.resumeCancellableWithException(delegate, paramThrowable);
    return;
    throw ((Throwable)new IllegalStateException("Already resumed"));
  }
  
  public void resumeWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    if (!isSelected()) {
      throw ((Throwable)new IllegalStateException("Must be selected first".toString()));
    }
    do
    {
      Object localObject;
      Fail localFail;
      do
      {
        localObject = _result;
        if (localObject != SelectKt.access$getUNDECIDED$p()) {
          break;
        }
        localObject = SelectKt.access$getUNDECIDED$p();
        localFail = new Fail(paramThrowable);
      } while (!_result$FU.compareAndSet(this, localObject, localFail));
      return;
      if (localObject != IntrinsicsKt.getCOROUTINE_SUSPENDED()) {
        break;
      }
    } while (!_result$FU.compareAndSet(this, IntrinsicsKt.getCOROUTINE_SUSPENDED(), SelectKt.access$getRESUMED$p()));
    CoroutineDispatcherKt.resumeDirectWithException(delegate, paramThrowable);
    return;
    throw ((Throwable)new IllegalStateException("Already resumed"));
  }
  
  public boolean trySelect(@Nullable Object paramObject)
  {
    int i;
    if (!(paramObject instanceof OpDescriptor)) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalStateException("cannot use OpDescriptor as idempotent marker".toString()));
    }
    Object localObject;
    do
    {
      localObject = getState();
      if (localObject != (SelectBuilderImpl)this) {
        break;
      }
    } while (!_state$FU.compareAndSet(this, this, paramObject));
    doAfterSelect();
    return true;
    if (paramObject == null) {
      return false;
    }
    return localObject == paramObject;
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\020\002\n\002\b\006\b\004\030\0002\n\022\006\022\004\030\0010\0020\001B\025\022\006\020\003\032\0020\004\022\006\020\005\032\0020\006¢\006\002\020\007J\034\020\b\032\0020\t2\b\020\n\032\004\030\0010\0022\b\020\013\032\004\030\0010\002H\026J\022\020\f\032\0020\t2\b\020\013\032\004\030\0010\002H\002J\024\020\r\032\004\030\0010\0022\b\020\n\032\004\030\0010\002H\026J\b\020\016\032\004\030\0010\002R\020\020\003\032\0020\0048\006X\004¢\006\002\n\000R\020\020\005\032\0020\0068\006X\004¢\006\002\n\000¨\006\017"}, d2={"Lkotlinx/coroutines/experimental/selects/SelectBuilderImpl$AtomicSelectOp;", "Lkotlinx/coroutines/experimental/internal/AtomicOp;", "", "desc", "Lkotlinx/coroutines/experimental/internal/AtomicDesc;", "select", "", "(Lkotlinx/coroutines/experimental/selects/SelectBuilderImpl;Lkotlinx/coroutines/experimental/internal/AtomicDesc;Z)V", "complete", "", "affected", "failure", "completeSelect", "prepare", "prepareIfNotSelected", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private final class AtomicSelectOp
    extends AtomicOp<Object>
  {
    @JvmField
    @NotNull
    public final AtomicDesc desc;
    @JvmField
    public final boolean select;
    
    public AtomicSelectOp(boolean paramBoolean)
    {
      desc = paramBoolean;
      boolean bool;
      select = bool;
    }
    
    private final void completeSelect(Object paramObject)
    {
      int i;
      if ((select) && (paramObject == null)) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        paramObject = null;
      } else {
        paramObject = SelectBuilderImpl.this;
      }
      SelectBuilderImpl localSelectBuilderImpl = SelectBuilderImpl.this;
      if ((SelectBuilderImpl._state$FU.compareAndSet(localSelectBuilderImpl, this, paramObject)) && (i != 0)) {
        SelectBuilderImpl.access$doAfterSelect(SelectBuilderImpl.this);
      }
    }
    
    public void complete(@Nullable Object paramObject1, @Nullable Object paramObject2)
    {
      completeSelect(paramObject2);
      desc.complete((AtomicOp)this, paramObject2);
    }
    
    @Nullable
    public Object prepare(@Nullable Object paramObject)
    {
      if (paramObject == null)
      {
        paramObject = prepareIfNotSelected();
        if (paramObject != null) {
          return paramObject;
        }
      }
      return desc.prepare((AtomicOp)this);
    }
    
    @Nullable
    public final Object prepareIfNotSelected()
    {
      SelectBuilderImpl localSelectBuilderImpl = SelectBuilderImpl.this;
      Object localObject;
      do
      {
        for (;;)
        {
          localObject = _state;
          if (localObject == (AtomicSelectOp)this) {
            return null;
          }
          if (!(localObject instanceof OpDescriptor)) {
            break;
          }
          ((OpDescriptor)localObject).perform(SelectBuilderImpl.this);
        }
        if (localObject != SelectBuilderImpl.this) {
          break;
        }
        localObject = SelectBuilderImpl.this;
      } while (!SelectBuilderImpl._state$FU.compareAndSet(localObject, SelectBuilderImpl.this, this));
      return null;
      return SelectKt.getALREADY_SELECTED();
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\b\002\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004R\020\020\002\032\0020\0038\006X\004¢\006\002\n\000¨\006\005"}, d2={"Lkotlinx/coroutines/experimental/selects/SelectBuilderImpl$DisposeNode;", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "handle", "Lkotlinx/coroutines/experimental/DisposableHandle;", "(Lkotlinx/coroutines/experimental/DisposableHandle;)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class DisposeNode
    extends LockFreeLinkedListNode
  {
    @JvmField
    @NotNull
    public final DisposableHandle handle;
    
    public DisposeNode(@NotNull DisposableHandle paramDisposableHandle)
    {
      handle = paramDisposableHandle;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\020\003\n\002\b\002\b\002\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004R\020\020\002\032\0020\0038\006X\004¢\006\002\n\000¨\006\005"}, d2={"Lkotlinx/coroutines/experimental/selects/SelectBuilderImpl$Fail;", "", "exception", "", "(Ljava/lang/Throwable;)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class Fail
  {
    @JvmField
    @NotNull
    public final Throwable exception;
    
    public Fail(@NotNull Throwable paramThrowable)
    {
      exception = paramThrowable;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\020\003\n\000\n\002\020\016\n\000\b\004\030\0002\b\022\004\022\0020\0020\001B\r\022\006\020\003\032\0020\002¢\006\002\020\004J\022\020\005\032\0020\0062\b\020\007\032\004\030\0010\bH\026J\b\020\t\032\0020\nH\026¨\006\013"}, d2={"Lkotlinx/coroutines/experimental/selects/SelectBuilderImpl$SelectOnCancellation;", "Lkotlinx/coroutines/experimental/JobCancellationNode;", "Lkotlinx/coroutines/experimental/Job;", "job", "(Lkotlinx/coroutines/experimental/selects/SelectBuilderImpl;Lkotlinx/coroutines/experimental/Job;)V", "invokeOnce", "", "reason", "", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private final class SelectOnCancellation
    extends JobCancellationNode<Job>
  {
    public SelectOnCancellation()
    {
      super();
    }
    
    public void invokeOnce(@Nullable Throwable paramThrowable)
    {
      if (trySelect(null))
      {
        SelectBuilderImpl localSelectBuilderImpl = SelectBuilderImpl.this;
        if (paramThrowable == null) {
          paramThrowable = (Throwable)new CancellationException("Select was cancelled");
        }
        localSelectBuilderImpl.resumeSelectCancellableWithException(paramThrowable);
      }
    }
    
    @NotNull
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("SelectOnCancellation[");
      localStringBuilder.append(SelectBuilderImpl.this);
      localStringBuilder.append(']');
      return localStringBuilder.toString();
    }
  }
}

package kotlinx.coroutines.experimental.channels;

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
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlinx.coroutines.experimental.CancellableContinuation;
import kotlinx.coroutines.experimental.CancellableContinuation.DefaultImpls;
import kotlinx.coroutines.experimental.CancellableContinuationImpl;
import kotlinx.coroutines.experimental.DisposableHandle;
import kotlinx.coroutines.experimental.DisposableHandle.DefaultImpls;
import kotlinx.coroutines.experimental.internal.AtomicDesc;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListHead;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListKt;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode.AddLastDesc;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode.CondAddOp;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode.RemoveFirstDesc;
import kotlinx.coroutines.experimental.intrinsics.UndispatchedKt;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import kotlinx.coroutines.experimental.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000Z\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\007\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\002\b\004\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\013\n\002\030\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\b\b&\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\0022\b\022\004\022\002H\0010\003:\0070123456B\005¢\006\002\020\004J\016\020\r\032\b\022\004\022\0028\0000\016H\004J\026\020\017\032\0020\0062\f\020\020\032\b\022\004\022\0028\0000\021H\002J\017\020\022\032\b\022\004\022\0028\0000\023H\002J\b\020\024\032\0020\025H\024J\b\020\026\032\0020\025H\024J\017\020\027\032\004\030\0018\000H\007¢\006\002\020\030J\n\020\031\032\004\030\0010\032H\024J\026\020\033\032\004\030\0010\0322\n\020\034\032\006\022\002\b\0030\035H\024J\021\020\020\032\0028\000H@ø\001\000¢\006\002\020\036J\023\020\037\032\004\030\0018\000H@ø\001\000¢\006\002\020\036J\031\020 \032\004\030\0018\0002\b\020!\032\004\030\0010\032H\003¢\006\002\020\"J\023\020#\032\004\030\0018\000H@ø\001\000¢\006\002\020\036J\027\020$\032\0028\0002\b\020!\032\004\030\0010\032H\003¢\006\002\020\"J\021\020%\032\0028\000H@ø\001\000¢\006\002\020\036JH\020&\032\0020\025\"\004\b\001\020'2\f\020\034\032\b\022\004\022\002H'0\0352\"\020(\032\036\b\001\022\004\022\0028\000\022\n\022\b\022\004\022\002H'0*\022\006\022\004\030\0010\0320)H\027ø\001\000¢\006\002\020+JJ\020,\032\0020\025\"\004\b\001\020'2\f\020\034\032\b\022\004\022\002H'0\0352$\020(\032 \b\001\022\006\022\004\030\0018\000\022\n\022\b\022\004\022\002H'0*\022\006\022\004\030\0010\0320)H\027ø\001\000¢\006\002\020+J \020-\032\0020\0252\n\020.\032\006\022\002\b\0030/2\n\020\020\032\006\022\002\b\0030\021H\002R\024\020\005\032\0020\0068DX\004¢\006\006\032\004\b\007\020\bR\022\020\t\032\0020\006X¤\004¢\006\006\032\004\b\t\020\bR\022\020\n\032\0020\006X¤\004¢\006\006\032\004\b\n\020\bR\021\020\013\032\0020\0068F¢\006\006\032\004\b\013\020\bR\021\020\f\032\0020\0068F¢\006\006\032\004\b\f\020\b\002\004\n\002\b\t¨\0067"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractChannel;", "E", "Lkotlinx/coroutines/experimental/channels/AbstractSendChannel;", "Lkotlinx/coroutines/experimental/channels/Channel;", "()V", "hasReceiveOrClosed", "", "getHasReceiveOrClosed", "()Z", "isBufferAlwaysEmpty", "isBufferEmpty", "isClosedForReceive", "isEmpty", "describeTryPoll", "Lkotlinx/coroutines/experimental/channels/AbstractChannel$TryPollDesc;", "enqueueReceive", "receive", "Lkotlinx/coroutines/experimental/channels/Receive;", "iterator", "Lkotlinx/coroutines/experimental/channels/ChannelIterator;", "onCancelledReceive", "", "onEnqueuedReceive", "poll", "()Ljava/lang/Object;", "pollInternal", "", "pollSelectInternal", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "(Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "receiveOrNull", "receiveOrNullResult", "result", "(Ljava/lang/Object;)Ljava/lang/Object;", "receiveOrNullSuspend", "receiveResult", "receiveSuspend", "registerSelectReceive", "R", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "registerSelectReceiveOrNull", "removeReceiveOnCancel", "cont", "Lkotlinx/coroutines/experimental/CancellableContinuation;", "IdempotentTokenValue", "Itr", "ReceiveElement", "ReceiveHasNext", "ReceiveSelect", "TryEnqueueReceiveDesc", "TryPollDesc", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract class AbstractChannel<E>
  extends AbstractSendChannel<E>
  implements Channel<E>
{
  public AbstractChannel() {}
  
  private final boolean enqueueReceive(Receive<? super E> paramReceive)
  {
    boolean bool1 = isBufferAlwaysEmpty();
    boolean bool2 = true;
    LockFreeLinkedListHead localLockFreeLinkedListHead;
    Object localObject1;
    int i;
    if (bool1)
    {
      localLockFreeLinkedListHead = getQueue();
      do
      {
        localObject1 = localLockFreeLinkedListHead.getPrev();
        if (localObject1 == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
        }
        localObject1 = (LockFreeLinkedListNode)localObject1;
        if (!(localObject1 instanceof Send)) {
          i = 1;
        } else {
          i = 0;
        }
        if (i == 0) {
          break;
        }
      } while (!((LockFreeLinkedListNode)localObject1).addNext((LockFreeLinkedListNode)paramReceive, localLockFreeLinkedListHead));
      bool1 = bool2;
    }
    else
    {
      localLockFreeLinkedListHead = getQueue();
      paramReceive = (LockFreeLinkedListNode)paramReceive;
      localObject1 = (LockFreeLinkedListNode.CondAddOp)new LockFreeLinkedListNode.CondAddOp(paramReceive)
      {
        public Object prepare(LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode)
        {
          if (jdField_this.isBufferEmpty()) {
            return null;
          }
          return LockFreeLinkedListKt.getCONDITION_FALSE();
        }
      };
      for (;;)
      {
        Object localObject2 = localLockFreeLinkedListHead.getPrev();
        if (localObject2 == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
        }
        localObject2 = (LockFreeLinkedListNode)localObject2;
        if (!(localObject2 instanceof Send)) {
          i = 1;
        } else {
          i = 0;
        }
        if (i == 0)
        {
          bool1 = false;
          break;
        }
        bool1 = bool2;
        switch (((LockFreeLinkedListNode)localObject2).tryCondAddNext(paramReceive, localLockFreeLinkedListHead, (LockFreeLinkedListNode.CondAddOp)localObject1))
        {
        }
      }
    }
    if (bool1) {
      onEnqueuedReceive();
    }
    return bool1;
  }
  
  private final E receiveOrNullResult(Object paramObject)
  {
    if ((paramObject instanceof Closed))
    {
      paramObject = (Closed)paramObject;
      if (closeCause != null) {
        throw closeCause;
      }
      return null;
    }
    return paramObject;
  }
  
  private final E receiveResult(Object paramObject)
  {
    if ((paramObject instanceof Closed)) {
      throw ((Closed)paramObject).getReceiveException();
    }
    return paramObject;
  }
  
  private final void removeReceiveOnCancel(final CancellableContinuation<?> paramCancellableContinuation, final Receive<?> paramReceive)
  {
    paramCancellableContinuation.invokeOnCompletion((Function1)new Lambda(paramCancellableContinuation)
    {
      public final void invoke(@Nullable Throwable paramAnonymousThrowable)
      {
        if ((paramCancellableContinuation.isCancelled()) && (paramReceive.remove())) {
          this$0.onCancelledReceive();
        }
      }
    });
  }
  
  @NotNull
  protected final TryPollDesc<E> describeTryPoll()
  {
    return new TryPollDesc(getQueue());
  }
  
  protected final boolean getHasReceiveOrClosed()
  {
    return getQueue().getNext() instanceof ReceiveOrClosed;
  }
  
  protected abstract boolean isBufferAlwaysEmpty();
  
  protected abstract boolean isBufferEmpty();
  
  public final boolean isClosedForReceive()
  {
    return (getClosedForReceive() != null) && (isBufferEmpty());
  }
  
  public final boolean isEmpty()
  {
    return (!(getQueue().getNext() instanceof Send)) && (isBufferEmpty());
  }
  
  @NotNull
  public final ChannelIterator<E> iterator()
  {
    return (ChannelIterator)new Itr(this);
  }
  
  protected void onCancelledReceive() {}
  
  protected void onEnqueuedReceive() {}
  
  @Nullable
  public final E poll()
  {
    Object localObject = pollInternal();
    if (localObject == AbstractChannelKt.POLL_FAILED) {
      return null;
    }
    return receiveOrNullResult(localObject);
  }
  
  @Nullable
  protected Object pollInternal()
  {
    Send localSend;
    Object localObject;
    do
    {
      localSend = takeFirstSendOrPeekClosed();
      if (localSend == null) {
        break;
      }
      localObject = localSend.tryResumeSend(null);
    } while (localObject == null);
    localSend.completeResumeSend(localObject);
    return localSend.getPollResult();
    return AbstractChannelKt.POLL_FAILED;
  }
  
  @Nullable
  protected Object pollSelectInternal(@NotNull SelectInstance<?> paramSelectInstance)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    TryPollDesc localTryPollDesc = describeTryPoll();
    paramSelectInstance = paramSelectInstance.performAtomicTrySelect((AtomicDesc)localTryPollDesc);
    if (paramSelectInstance != null) {
      return paramSelectInstance;
    }
    paramSelectInstance = (Send)localTryPollDesc.getResult();
    Object localObject = resumeToken;
    if (localObject == null) {
      Intrinsics.throwNpe();
    }
    paramSelectInstance.completeResumeSend(localObject);
    return pollResult;
  }
  
  @Nullable
  public final Object receive(@NotNull Continuation<? super E> paramContinuation)
  {
    Object localObject = pollInternal();
    if (localObject != AbstractChannelKt.POLL_FAILED) {
      return receiveResult(localObject);
    }
    return receiveSuspend(paramContinuation);
  }
  
  @Nullable
  public final Object receiveOrNull(@NotNull Continuation<? super E> paramContinuation)
  {
    Object localObject = pollInternal();
    if (localObject != AbstractChannelKt.POLL_FAILED) {
      return receiveOrNullResult(localObject);
    }
    return receiveOrNullSuspend(paramContinuation);
  }
  
  @Nullable
  final Object receiveOrNullSuspend(@NotNull Continuation<? super E> paramContinuation)
  {
    paramContinuation = new CancellableContinuationImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation), 0);
    CancellableContinuation localCancellableContinuation = (CancellableContinuation)paramContinuation;
    Object localObject1 = new ReceiveElement(localCancellableContinuation, true);
    Object localObject2;
    do
    {
      localObject2 = (Receive)localObject1;
      if (access$enqueueReceive(this, (Receive)localObject2))
      {
        localCancellableContinuation.initCancellability();
        access$removeReceiveOnCancel(this, localCancellableContinuation, (Receive)localObject2);
        break;
      }
      localObject2 = pollInternal();
      if ((localObject2 instanceof Closed))
      {
        localObject1 = (Closed)localObject2;
        if (closeCause == null)
        {
          localCancellableContinuation.resume(null);
          break;
        }
        localCancellableContinuation.resumeWithException(closeCause);
        break;
      }
    } while (localObject2 == AbstractChannelKt.POLL_FAILED);
    localCancellableContinuation.resume(localObject2);
    return paramContinuation.getResult();
  }
  
  @Nullable
  final Object receiveSuspend(@NotNull Continuation<? super E> paramContinuation)
  {
    paramContinuation = new CancellableContinuationImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation), 0);
    CancellableContinuation localCancellableContinuation = (CancellableContinuation)paramContinuation;
    ReceiveElement localReceiveElement = new ReceiveElement(localCancellableContinuation, false);
    Object localObject;
    do
    {
      localObject = (Receive)localReceiveElement;
      if (access$enqueueReceive(this, (Receive)localObject))
      {
        localCancellableContinuation.initCancellability();
        access$removeReceiveOnCancel(this, localCancellableContinuation, (Receive)localObject);
        break;
      }
      localObject = pollInternal();
      if ((localObject instanceof Closed))
      {
        localCancellableContinuation.resumeWithException(((Closed)localObject).getReceiveException());
        break;
      }
    } while (localObject == AbstractChannelKt.POLL_FAILED);
    localCancellableContinuation.resume(localObject);
    return paramContinuation.getResult();
  }
  
  public <R> void registerSelectReceive(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    Object localObject;
    label114:
    do
    {
      do
      {
        if (paramSelectInstance.isSelected()) {
          return;
        }
        if (!isEmpty()) {
          break label114;
        }
        localObject = paramSelectInstance.performAtomicIfNotSelected((AtomicDesc)new TryEnqueueReceiveDesc(paramSelectInstance, paramFunction2, false));
        if (localObject == null) {
          break;
        }
        if (localObject == SelectKt.getALREADY_SELECTED()) {
          return;
        }
      } while (localObject == AbstractChannelKt.ENQUEUE_FAILED);
      paramSelectInstance = new StringBuilder();
      paramSelectInstance.append("performAtomicIfNotSelected(TryEnqueueReceiveDesc) returned ");
      paramSelectInstance.append(localObject);
      throw ((Throwable)new IllegalStateException(paramSelectInstance.toString().toString()));
      return;
      localObject = pollSelectInternal(paramSelectInstance);
      if (localObject == SelectKt.getALREADY_SELECTED()) {
        return;
      }
    } while (localObject == AbstractChannelKt.POLL_FAILED);
    if ((localObject instanceof Closed)) {
      throw ((Closed)localObject).getReceiveException();
    }
    UndispatchedKt.startCoroutineUndispatched(paramFunction2, localObject, paramSelectInstance.getCompletion());
  }
  
  public <R> void registerSelectReceiveOrNull(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "block");
    Object localObject;
    label114:
    do
    {
      do
      {
        if (paramSelectInstance.isSelected()) {
          return;
        }
        if (!isEmpty()) {
          break label114;
        }
        localObject = paramSelectInstance.performAtomicIfNotSelected((AtomicDesc)new TryEnqueueReceiveDesc(paramSelectInstance, paramFunction2, true));
        if (localObject == null) {
          break;
        }
        if (localObject == SelectKt.getALREADY_SELECTED()) {
          return;
        }
      } while (localObject == AbstractChannelKt.ENQUEUE_FAILED);
      paramSelectInstance = new StringBuilder();
      paramSelectInstance.append("performAtomicIfNotSelected(TryEnqueueReceiveDesc) returned ");
      paramSelectInstance.append(localObject);
      throw ((Throwable)new IllegalStateException(paramSelectInstance.toString().toString()));
      return;
      localObject = pollSelectInternal(paramSelectInstance);
      if (localObject == SelectKt.getALREADY_SELECTED()) {
        return;
      }
    } while (localObject == AbstractChannelKt.POLL_FAILED);
    if ((localObject instanceof Closed))
    {
      localObject = (Closed)localObject;
      if (closeCause == null)
      {
        if (paramSelectInstance.trySelect(null)) {
          UndispatchedKt.startCoroutineUndispatched(paramFunction2, null, paramSelectInstance.getCompletion());
        }
        return;
      }
      throw closeCause;
    }
    UndispatchedKt.startCoroutineUndispatched(paramFunction2, localObject, paramSelectInstance.getCompletion());
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\016\n\002\030\002\n\000\n\002\020\000\n\002\b\005\b\002\030\000*\006\b\001\020\001 \0012\0020\002B\025\022\006\020\003\032\0020\002\022\006\020\004\032\0028\001¢\006\002\020\005R\020\020\003\032\0020\0028\006X\004¢\006\002\n\000R\022\020\004\032\0028\0018\006X\004¢\006\004\n\002\020\006¨\006\007"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractChannel$IdempotentTokenValue;", "E", "", "token", "value", "(Ljava/lang/Object;Ljava/lang/Object;)V", "Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class IdempotentTokenValue<E>
  {
    @JvmField
    @NotNull
    public final Object token;
    @JvmField
    public final E value;
    
    public IdempotentTokenValue(@NotNull Object paramObject, E paramE)
    {
      token = paramObject;
      value = paramE;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\000\n\002\b\005\n\002\020\013\n\002\b\005\b\002\030\000*\004\b\001\020\0012\b\022\004\022\002H\0010\002B\023\022\f\020\003\032\b\022\004\022\0028\0010\004¢\006\002\020\005J\021\020\016\032\0020\017HBø\001\000¢\006\002\020\020J\022\020\021\032\0020\0172\b\020\b\032\004\030\0010\tH\002J\021\020\022\032\0020\017H@ø\001\000¢\006\002\020\020J\021\020\023\032\0028\001HBø\001\000¢\006\002\020\020R\027\020\003\032\b\022\004\022\0028\0010\004¢\006\b\n\000\032\004\b\006\020\007R\034\020\b\032\004\030\0010\tX\016¢\006\016\n\000\032\004\b\n\020\013\"\004\b\f\020\r\002\004\n\002\b\t¨\006\024"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractChannel$Itr;", "E", "Lkotlinx/coroutines/experimental/channels/ChannelIterator;", "channel", "Lkotlinx/coroutines/experimental/channels/AbstractChannel;", "(Lkotlinx/coroutines/experimental/channels/AbstractChannel;)V", "getChannel", "()Lkotlinx/coroutines/experimental/channels/AbstractChannel;", "result", "", "getResult", "()Ljava/lang/Object;", "setResult", "(Ljava/lang/Object;)V", "hasNext", "", "(Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "hasNextResult", "hasNextSuspend", "next", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class Itr<E>
    implements ChannelIterator<E>
  {
    @NotNull
    private final AbstractChannel<E> channel;
    @Nullable
    private Object result;
    
    public Itr(@NotNull AbstractChannel<E> paramAbstractChannel)
    {
      channel = paramAbstractChannel;
      result = AbstractChannelKt.POLL_FAILED;
    }
    
    private final boolean hasNextResult(Object paramObject)
    {
      if ((paramObject instanceof Closed))
      {
        paramObject = (Closed)paramObject;
        if (closeCause != null) {
          throw paramObject.getReceiveException();
        }
        return false;
      }
      return true;
    }
    
    @NotNull
    public final AbstractChannel<E> getChannel()
    {
      return channel;
    }
    
    @Nullable
    public final Object getResult()
    {
      return result;
    }
    
    @Nullable
    public Object hasNext(@NotNull Continuation<? super Boolean> paramContinuation)
    {
      if (result != AbstractChannelKt.POLL_FAILED) {
        return Boolean.valueOf(hasNextResult(result));
      }
      result = channel.pollInternal();
      if (result != AbstractChannelKt.POLL_FAILED) {
        return Boolean.valueOf(hasNextResult(result));
      }
      return hasNextSuspend(paramContinuation);
    }
    
    @Nullable
    final Object hasNextSuspend(@NotNull Continuation<? super Boolean> paramContinuation)
    {
      paramContinuation = new CancellableContinuationImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation), 0);
      CancellableContinuation localCancellableContinuation = (CancellableContinuation)paramContinuation;
      Object localObject1 = new AbstractChannel.ReceiveHasNext(this, localCancellableContinuation);
      Object localObject2;
      do
      {
        localObject2 = getChannel();
        Receive localReceive = (Receive)localObject1;
        if (AbstractChannel.access$enqueueReceive((AbstractChannel)localObject2, localReceive))
        {
          localCancellableContinuation.initCancellability();
          AbstractChannel.access$removeReceiveOnCancel(getChannel(), localCancellableContinuation, localReceive);
          break;
        }
        localObject2 = getChannel().pollInternal();
        setResult(localObject2);
        if ((localObject2 instanceof Closed))
        {
          localObject1 = (Closed)localObject2;
          if (closeCause == null)
          {
            localCancellableContinuation.resume(Boolean.valueOf(false));
            break;
          }
          localCancellableContinuation.resumeWithException(((Closed)localObject1).getReceiveException());
          break;
        }
      } while (localObject2 == AbstractChannelKt.POLL_FAILED);
      localCancellableContinuation.resume(Boolean.valueOf(true));
      return paramContinuation.getResult();
    }
    
    @Nullable
    public Object next(@NotNull Continuation<? super E> paramContinuation)
    {
      Object localObject = result;
      if ((localObject instanceof Closed)) {
        throw ((Closed)localObject).getReceiveException();
      }
      if (localObject != AbstractChannelKt.POLL_FAILED)
      {
        result = AbstractChannelKt.POLL_FAILED;
        return localObject;
      }
      return channel.receive(paramContinuation);
    }
    
    public final void setResult(@Nullable Object paramObject)
    {
      result = paramObject;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\0006\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\020\002\n\000\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\002\b\005\b\002\030\000*\006\b\001\020\001 \0002\b\022\004\022\002H\0010\002B\035\022\016\020\003\032\n\022\006\022\004\030\0018\0010\004\022\006\020\005\032\0020\006¢\006\002\020\007J\020\020\b\032\0020\t2\006\020\n\032\0020\013H\026J\024\020\f\032\0020\t2\n\020\r\032\006\022\002\b\0030\016H\026J\b\020\017\032\0020\020H\026J!\020\021\032\004\030\0010\0132\006\020\022\032\0028\0012\b\020\023\032\004\030\0010\013H\026¢\006\002\020\024R\030\020\003\032\n\022\006\022\004\030\0018\0010\0048\006X\004¢\006\002\n\000R\020\020\005\032\0020\0068\006X\004¢\006\002\n\000¨\006\025"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractChannel$ReceiveElement;", "E", "Lkotlinx/coroutines/experimental/channels/Receive;", "cont", "Lkotlinx/coroutines/experimental/CancellableContinuation;", "nullOnClose", "", "(Lkotlinx/coroutines/experimental/CancellableContinuation;Z)V", "completeResumeReceive", "", "token", "", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/experimental/channels/Closed;", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class ReceiveElement<E>
    extends Receive<E>
  {
    @JvmField
    @NotNull
    public final CancellableContinuation<E> cont;
    @JvmField
    public final boolean nullOnClose;
    
    public ReceiveElement(@NotNull CancellableContinuation<? super E> paramCancellableContinuation, boolean paramBoolean)
    {
      cont = paramCancellableContinuation;
      nullOnClose = paramBoolean;
    }
    
    public void completeResumeReceive(@NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramObject, "token");
      cont.completeResume(paramObject);
    }
    
    public void resumeReceiveClosed(@NotNull Closed<?> paramClosed)
    {
      Intrinsics.checkParameterIsNotNull(paramClosed, "closed");
      if ((closeCause == null) && (nullOnClose))
      {
        cont.resume(null);
        return;
      }
      cont.resumeWithException(paramClosed.getReceiveException());
    }
    
    @NotNull
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ReceiveElement[");
      localStringBuilder.append(cont);
      localStringBuilder.append(",nullOnClose=");
      localStringBuilder.append(nullOnClose);
      localStringBuilder.append(']');
      return localStringBuilder.toString();
    }
    
    @Nullable
    public Object tryResumeReceive(E paramE, @Nullable Object paramObject)
    {
      return cont.tryResume(paramE, paramObject);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000:\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\020\013\n\002\b\002\n\002\020\002\n\000\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\002\b\005\b\002\030\000*\004\b\001\020\0012\b\022\004\022\002H\0010\002B!\022\f\020\003\032\b\022\004\022\0028\0010\004\022\f\020\005\032\b\022\004\022\0020\0070\006¢\006\002\020\bJ\020\020\t\032\0020\n2\006\020\013\032\0020\fH\026J\024\020\r\032\0020\n2\n\020\016\032\006\022\002\b\0030\017H\026J\b\020\020\032\0020\021H\026J!\020\022\032\004\030\0010\f2\006\020\023\032\0028\0012\b\020\024\032\004\030\0010\fH\026¢\006\002\020\025R\026\020\005\032\b\022\004\022\0020\0070\0068\006X\004¢\006\002\n\000R\026\020\003\032\b\022\004\022\0028\0010\0048\006X\004¢\006\002\n\000¨\006\026"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractChannel$ReceiveHasNext;", "E", "Lkotlinx/coroutines/experimental/channels/Receive;", "iterator", "Lkotlinx/coroutines/experimental/channels/AbstractChannel$Itr;", "cont", "Lkotlinx/coroutines/experimental/CancellableContinuation;", "", "(Lkotlinx/coroutines/experimental/channels/AbstractChannel$Itr;Lkotlinx/coroutines/experimental/CancellableContinuation;)V", "completeResumeReceive", "", "token", "", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/experimental/channels/Closed;", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class ReceiveHasNext<E>
    extends Receive<E>
  {
    @JvmField
    @NotNull
    public final CancellableContinuation<Boolean> cont;
    @JvmField
    @NotNull
    public final AbstractChannel.Itr<E> iterator;
    
    public ReceiveHasNext(@NotNull AbstractChannel.Itr<E> paramItr, @NotNull CancellableContinuation<? super Boolean> paramCancellableContinuation)
    {
      iterator = paramItr;
      cont = paramCancellableContinuation;
    }
    
    public void completeResumeReceive(@NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramObject, "token");
      if ((paramObject instanceof AbstractChannel.IdempotentTokenValue))
      {
        AbstractChannel.Itr localItr = iterator;
        paramObject = (AbstractChannel.IdempotentTokenValue)paramObject;
        localItr.setResult(value);
        cont.completeResume(token);
        return;
      }
      cont.completeResume(paramObject);
    }
    
    public void resumeReceiveClosed(@NotNull Closed<?> paramClosed)
    {
      Intrinsics.checkParameterIsNotNull(paramClosed, "closed");
      Object localObject;
      if (closeCause == null) {
        localObject = CancellableContinuation.DefaultImpls.tryResume$default(cont, Boolean.valueOf(false), null, 2, null);
      } else {
        localObject = cont.tryResumeWithException(paramClosed.getReceiveException());
      }
      if (localObject != null)
      {
        iterator.setResult(paramClosed);
        cont.completeResume(localObject);
      }
    }
    
    @NotNull
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ReceiveHasNext[");
      localStringBuilder.append(cont);
      localStringBuilder.append(']');
      return localStringBuilder.toString();
    }
    
    @Nullable
    public Object tryResumeReceive(E paramE, @Nullable Object paramObject)
    {
      Object localObject = cont.tryResume(Boolean.valueOf(true), paramObject);
      if (localObject != null)
      {
        if (paramObject != null) {
          return new AbstractChannel.IdempotentTokenValue(localObject, paramE);
        }
        iterator.setResult(paramE);
      }
      return localObject;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000D\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\020\000\n\000\n\002\020\013\n\002\b\003\n\002\020\002\n\002\b\005\n\002\030\002\n\000\n\002\020\016\n\002\b\005\b\004\030\000*\004\b\001\020\001*\006\b\002\020\002 \0002\b\022\004\022\002H\0020\0032\0020\004BD\022\f\020\005\032\b\022\004\022\0028\0010\006\022$\020\007\032 \b\001\022\006\022\004\030\0018\002\022\n\022\b\022\004\022\0028\0010\t\022\006\022\004\030\0010\n0\b\022\006\020\013\032\0020\fø\001\000¢\006\002\020\rJ\020\020\017\032\0020\0202\006\020\021\032\0020\nH\027J\b\020\022\032\0020\020H\026J\006\020\023\032\0020\020J\024\020\024\032\0020\0202\n\020\025\032\006\022\002\b\0030\026H\026J\b\020\027\032\0020\030H\026J!\020\031\032\004\030\0010\n2\006\020\032\032\0028\0022\b\020\033\032\004\030\0010\nH\026¢\006\002\020\034R3\020\007\032 \b\001\022\006\022\004\030\0018\002\022\n\022\b\022\004\022\0028\0010\t\022\006\022\004\030\0010\n0\b8\006X\004ø\001\000¢\006\004\n\002\020\016R\020\020\013\032\0020\f8\006X\004¢\006\002\n\000R\026\020\005\032\b\022\004\022\0028\0010\0068\006X\004¢\006\002\n\000\002\004\n\002\b\t¨\006\035"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractChannel$ReceiveSelect;", "R", "E", "Lkotlinx/coroutines/experimental/channels/Receive;", "Lkotlinx/coroutines/experimental/DisposableHandle;", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/Continuation;", "", "nullOnClose", "", "(Lkotlinx/coroutines/experimental/channels/AbstractChannel;Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function2;Z)V", "Lkotlin/jvm/functions/Function2;", "completeResumeReceive", "", "token", "dispose", "removeOnSelectCompletion", "resumeReceiveClosed", "closed", "Lkotlinx/coroutines/experimental/channels/Closed;", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private final class ReceiveSelect<R, E>
    extends Receive<E>
    implements DisposableHandle
  {
    @JvmField
    @NotNull
    public final Function2<E, Continuation<? super R>, Object> block;
    @JvmField
    public final boolean nullOnClose;
    @JvmField
    @NotNull
    public final SelectInstance<R> select;
    
    public ReceiveSelect(@NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2, boolean paramBoolean)
    {
      select = paramFunction2;
      block = paramBoolean;
      boolean bool;
      nullOnClose = bool;
    }
    
    public void completeResumeReceive(@NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramObject, "token");
      Object localObject = paramObject;
      if (paramObject == AbstractChannelKt.NULL_VALUE) {
        localObject = null;
      }
      CoroutinesKt.startCoroutine(block, localObject, select.getCompletion());
    }
    
    public void dispose()
    {
      if (remove()) {
        onCancelledReceive();
      }
    }
    
    public final void removeOnSelectCompletion()
    {
      select.disposeOnSelect((DisposableHandle)this);
    }
    
    public void resumeReceiveClosed(@NotNull Closed<?> paramClosed)
    {
      Intrinsics.checkParameterIsNotNull(paramClosed, "closed");
      if (select.trySelect(null))
      {
        if ((closeCause == null) && (nullOnClose))
        {
          CoroutinesKt.startCoroutine(block, null, select.getCompletion());
          return;
        }
        select.resumeSelectCancellableWithException(paramClosed.getReceiveException());
      }
    }
    
    @NotNull
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ReceiveSelect[");
      localStringBuilder.append(select);
      localStringBuilder.append(",nullOnClose=");
      localStringBuilder.append(nullOnClose);
      localStringBuilder.append(']');
      return localStringBuilder.toString();
    }
    
    @Nullable
    public Object tryResumeReceive(E paramE, @Nullable Object paramObject)
    {
      if (select.trySelect(paramObject))
      {
        if (paramE != null) {
          return paramE;
        }
        return AbstractChannelKt.NULL_VALUE;
      }
      return null;
    }
    
    @Deprecated(message="Replace with `dispose`", replaceWith=@ReplaceWith(expression="dispose()", imports={}))
    public void unregister()
    {
      DisposableHandle.DefaultImpls.unregister(this);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000F\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\020\000\n\000\n\002\020\013\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\b\004\030\000*\006\b\001\020\001 \000*\004\b\002\020\0022>\022\032\022\030\022\004\022\002H\002\022\004\022\002H\0010\004R\b\022\004\022\0028\0000\0050\003j\036\022\032\022\030\022\004\022\002H\002\022\004\022\002H\0010\004R\b\022\004\022\0028\0000\005`\006BD\022\f\020\007\032\b\022\004\022\0028\0020\b\022$\020\t\032 \b\001\022\006\022\004\030\0018\001\022\n\022\b\022\004\022\0028\0020\013\022\006\022\004\030\0010\f0\n\022\006\020\r\032\0020\016ø\001\000¢\006\002\020\017J\032\020\020\032\004\030\0010\f2\006\020\021\032\0020\0222\006\020\023\032\0020\fH\024J\030\020\024\032\0020\0252\006\020\021\032\0020\0222\006\020\023\032\0020\022H\024J\032\020\026\032\004\030\0010\f2\006\020\021\032\0020\0222\006\020\023\032\0020\022H\024\002\004\n\002\b\t¨\006\027"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractChannel$TryEnqueueReceiveDesc;", "E", "R", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/experimental/channels/AbstractChannel$ReceiveSelect;", "Lkotlinx/coroutines/experimental/channels/AbstractChannel;", "Lkotlinx/coroutines/experimental/internal/AddLastDesc;", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function2;", "Lkotlin/coroutines/experimental/Continuation;", "", "nullOnClose", "", "(Lkotlinx/coroutines/experimental/channels/AbstractChannel;Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function2;Z)V", "failure", "affected", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "next", "finishOnSuccess", "", "onPrepare", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private final class TryEnqueueReceiveDesc<E, R>
    extends LockFreeLinkedListNode.AddLastDesc<AbstractChannel<E>.ReceiveSelect<R, ? super E>>
  {
    public TryEnqueueReceiveDesc(@NotNull Function2<? super E, ? super Continuation<? super R>, ? extends Object> paramFunction2, boolean paramBoolean)
    {
      super((LockFreeLinkedListNode)new AbstractChannel.ReceiveSelect(AbstractChannel.this, paramFunction2, paramBoolean, bool));
    }
    
    @Nullable
    protected Object failure(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "affected");
      Intrinsics.checkParameterIsNotNull(paramObject, "next");
      if ((paramLockFreeLinkedListNode instanceof Send)) {
        return AbstractChannelKt.ENQUEUE_FAILED;
      }
      return null;
    }
    
    protected void finishOnSuccess(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "affected");
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
      super.finishOnSuccess(paramLockFreeLinkedListNode1, paramLockFreeLinkedListNode2);
      onEnqueuedReceive();
      ((AbstractChannel.ReceiveSelect)node).removeOnSelectCompletion();
    }
    
    @Nullable
    protected Object onPrepare(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "affected");
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
      if (!isBufferEmpty()) {
        return AbstractChannelKt.ENQUEUE_FAILED;
      }
      return super.onPrepare(paramLockFreeLinkedListNode1, paramLockFreeLinkedListNode2);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\0004\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\002\b\004\030\000*\004\b\001\020\0012\022\022\004\022\0020\0030\002j\b\022\004\022\0020\003`\004B\r\022\006\020\005\032\0020\006¢\006\002\020\007J\032\020\f\032\004\030\0010\0132\006\020\r\032\0020\0162\006\020\017\032\0020\013H\024J\020\020\020\032\0020\0212\006\020\022\032\0020\003H\025R\026\020\b\032\004\030\0018\0018\006@\006X\016¢\006\004\n\002\020\tR\024\020\n\032\004\030\0010\0138\006@\006X\016¢\006\002\n\000¨\006\023"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractChannel$TryPollDesc;", "E", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/experimental/channels/Send;", "Lkotlinx/coroutines/experimental/internal/RemoveFirstDesc;", "queue", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;", "(Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;)V", "pollResult", "Ljava/lang/Object;", "resumeToken", "", "failure", "affected", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "next", "validatePrepared", "", "node", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  protected static final class TryPollDesc<E>
    extends LockFreeLinkedListNode.RemoveFirstDesc<Send>
  {
    @JvmField
    @Nullable
    public E pollResult;
    @JvmField
    @Nullable
    public Object resumeToken;
    
    public TryPollDesc(@NotNull LockFreeLinkedListHead paramLockFreeLinkedListHead)
    {
      super();
    }
    
    @Nullable
    protected Object failure(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "affected");
      Intrinsics.checkParameterIsNotNull(paramObject, "next");
      if ((paramLockFreeLinkedListNode instanceof Closed)) {
        return paramLockFreeLinkedListNode;
      }
      if (!(paramLockFreeLinkedListNode instanceof Send)) {
        return AbstractChannelKt.POLL_FAILED;
      }
      return null;
    }
    
    protected boolean validatePrepared(@NotNull Send paramSend)
    {
      Intrinsics.checkParameterIsNotNull(paramSend, "node");
      Object localObject = paramSend.tryResumeSend(this);
      if (localObject != null)
      {
        resumeToken = localObject;
        pollResult = paramSend.getPollResult();
        return true;
      }
      return false;
    }
  }
}

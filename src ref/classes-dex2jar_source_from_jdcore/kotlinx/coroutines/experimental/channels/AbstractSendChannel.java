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
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.CancellableContinuation;
import kotlinx.coroutines.experimental.CancellableContinuationImpl;
import kotlinx.coroutines.experimental.CancellableContinuationKt;
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

@Metadata(bv={1, 0, 2}, d1={"\000\001\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020\013\n\002\b\005\n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\020\003\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\020\000\n\000\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\006\n\002\030\002\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\007\b&\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\002:\006BCDEFGB\005¢\006\002\020\003J\022\020\024\032\0020\0252\b\020\026\032\004\030\0010\027H\024J\022\020\030\032\0020\0132\b\020\026\032\004\030\0010\027H\026J\020\020\031\032\0020\0252\006\020\032\032\0020\033H\004J!\020\034\032\016\022\002\b\0030\035j\006\022\002\b\003`\0362\006\020\037\032\0028\000H\004¢\006\002\020 J!\020!\032\016\022\002\b\0030\035j\006\022\002\b\003`\0362\006\020\037\032\0028\000H\004¢\006\002\020 J\033\020\"\032\b\022\004\022\0028\0000#2\006\020\037\032\0028\000H\004¢\006\002\020$J\022\020%\032\004\030\0010&2\006\020'\032\0020(H\002J\023\020)\032\0020\0132\006\020\037\032\0028\000¢\006\002\020*J\025\020+\032\0020&2\006\020\037\032\0028\000H\024¢\006\002\020,J!\020-\032\0020&2\006\020\037\032\0028\0002\n\020.\032\006\022\002\b\0030/H\024¢\006\002\0200J\026\0201\032\0020\0252\f\0202\032\b\022\004\022\0028\0000\005H\024JJ\0203\032\0020\025\"\004\b\001\02042\f\020.\032\b\022\004\022\002H40/2\006\020\037\032\0028\0002\034\0205\032\030\b\001\022\n\022\b\022\004\022\002H407\022\006\022\004\030\0010&06H\026ø\001\000¢\006\002\0208J\031\020'\032\0020\0252\006\020\037\032\0028\000H@ø\001\000¢\006\002\0209J\033\020:\032\b\022\002\b\003\030\0010;2\006\020\037\032\0028\000H\004¢\006\002\020<J\033\020=\032\b\022\002\b\003\030\0010;2\006\020\037\032\0028\000H\004¢\006\002\020<J\031\020>\032\0020\0252\006\020\037\032\0028\000H@ø\001\000¢\006\002\0209J\020\020?\032\n\022\004\022\0028\000\030\0010;H\004J\n\020@\032\004\030\0010AH\004R\032\020\004\032\b\022\002\b\003\030\0010\0058DX\004¢\006\006\032\004\b\006\020\007R\032\020\b\032\b\022\002\b\003\030\0010\0058DX\004¢\006\006\032\004\b\t\020\007R\022\020\n\032\0020\013X¤\004¢\006\006\032\004\b\n\020\fR\022\020\r\032\0020\013X¤\004¢\006\006\032\004\b\r\020\fR\021\020\016\032\0020\0138F¢\006\006\032\004\b\016\020\fR\021\020\017\032\0020\0138F¢\006\006\032\004\b\017\020\fR\024\020\020\032\0020\021X\004¢\006\b\n\000\032\004\b\022\020\023\002\004\n\002\b\t¨\006H"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractSendChannel;", "E", "Lkotlinx/coroutines/experimental/channels/SendChannel;", "()V", "closedForReceive", "Lkotlinx/coroutines/experimental/channels/Closed;", "getClosedForReceive", "()Lkotlinx/coroutines/experimental/channels/Closed;", "closedForSend", "getClosedForSend", "isBufferAlwaysFull", "", "()Z", "isBufferFull", "isClosedForSend", "isFull", "queue", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;", "getQueue", "()Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;", "afterClose", "", "cause", "", "close", "conflatePreviousSendBuffered", "node", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "describeSendBuffered", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/experimental/internal/AddLastDesc;", "element", "(Ljava/lang/Object;)Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AddLastDesc;", "describeSendConflated", "describeTryOffer", "Lkotlinx/coroutines/experimental/channels/AbstractSendChannel$TryOfferDesc;", "(Ljava/lang/Object;)Lkotlinx/coroutines/experimental/channels/AbstractSendChannel$TryOfferDesc;", "enqueueSend", "", "send", "Lkotlinx/coroutines/experimental/channels/SendElement;", "offer", "(Ljava/lang/Object;)Z", "offerInternal", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/experimental/selects/SelectInstance;)Ljava/lang/Object;", "onClosed", "closed", "registerSelectSend", "R", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "sendBuffered", "Lkotlinx/coroutines/experimental/channels/ReceiveOrClosed;", "(Ljava/lang/Object;)Lkotlinx/coroutines/experimental/channels/ReceiveOrClosed;", "sendConflated", "sendSuspend", "takeFirstReceiveOrPeekClosed", "takeFirstSendOrPeekClosed", "Lkotlinx/coroutines/experimental/channels/Send;", "SendBuffered", "SendBufferedDesc", "SendConflatedDesc", "SendSelect", "TryEnqueueSendDesc", "TryOfferDesc", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract class AbstractSendChannel<E>
  implements SendChannel<E>
{
  @NotNull
  private final LockFreeLinkedListHead queue = new LockFreeLinkedListHead();
  
  public AbstractSendChannel() {}
  
  private final Object enqueueSend(SendElement paramSendElement)
  {
    LockFreeLinkedListHead localLockFreeLinkedListHead;
    Object localObject1;
    if (isBufferAlwaysFull())
    {
      localLockFreeLinkedListHead = queue;
      do
      {
        localObject1 = localLockFreeLinkedListHead.getPrev();
        if (localObject1 == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
        }
        localObject1 = (LockFreeLinkedListNode)localObject1;
        if ((localObject1 instanceof ReceiveOrClosed)) {
          return localObject1;
        }
      } while (!((LockFreeLinkedListNode)localObject1).addNext((LockFreeLinkedListNode)paramSendElement, localLockFreeLinkedListHead));
    }
    else
    {
      localLockFreeLinkedListHead = queue;
      paramSendElement = (LockFreeLinkedListNode)paramSendElement;
      localObject1 = (LockFreeLinkedListNode.CondAddOp)new LockFreeLinkedListNode.CondAddOp(paramSendElement)
      {
        public Object prepare(LockFreeLinkedListNode paramAnonymousLockFreeLinkedListNode)
        {
          if (jdField_this.isBufferFull()) {
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
        if ((localObject2 instanceof ReceiveOrClosed)) {
          return localObject2;
        }
        switch (((LockFreeLinkedListNode)localObject2).tryCondAddNext(paramSendElement, localLockFreeLinkedListHead, (LockFreeLinkedListNode.CondAddOp)localObject1))
        {
        }
      }
      int i = 0;
      break label174;
      i = 1;
      label174:
      if (i == 0) {
        return AbstractChannelKt.ENQUEUE_FAILED;
      }
    }
    return null;
  }
  
  protected void afterClose(@Nullable Throwable paramThrowable) {}
  
  public boolean close(@Nullable Throwable paramThrowable)
  {
    Closed localClosed = new Closed(paramThrowable);
    for (;;)
    {
      Object localObject1 = takeFirstReceiveOrPeekClosed();
      int j = 0;
      if (localObject1 == null)
      {
        localObject1 = queue;
        Object localObject2;
        do
        {
          localObject2 = ((LockFreeLinkedListNode)localObject1).getPrev();
          if (localObject2 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
          }
          localObject2 = (LockFreeLinkedListNode)localObject2;
          if ((localObject2 instanceof Closed)) {
            return false;
          }
          if (!(localObject2 instanceof ReceiveOrClosed)) {
            i = 1;
          } else {
            i = 0;
          }
          if (i == 0)
          {
            i = j;
            break;
          }
        } while (!((LockFreeLinkedListNode)localObject2).addNext((LockFreeLinkedListNode)localClosed, (LockFreeLinkedListNode)localObject1));
        int i = 1;
        if (i != 0)
        {
          onClosed(localClosed);
          afterClose(paramThrowable);
          return true;
        }
      }
      else
      {
        if ((localObject1 instanceof Closed)) {
          return false;
        }
        ((Receive)localObject1).resumeReceiveClosed(localClosed);
      }
    }
  }
  
  protected final void conflatePreviousSendBuffered(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode)
  {
    Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "node");
    paramLockFreeLinkedListNode = paramLockFreeLinkedListNode.getPrev();
    if ((paramLockFreeLinkedListNode instanceof SendBuffered)) {
      ((SendBuffered)paramLockFreeLinkedListNode).remove();
    }
  }
  
  @NotNull
  protected final LockFreeLinkedListNode.AddLastDesc<?> describeSendBuffered(E paramE)
  {
    return (LockFreeLinkedListNode.AddLastDesc)new SendBufferedDesc(queue, paramE);
  }
  
  @NotNull
  protected final LockFreeLinkedListNode.AddLastDesc<?> describeSendConflated(E paramE)
  {
    return (LockFreeLinkedListNode.AddLastDesc)new SendConflatedDesc(queue, paramE);
  }
  
  @NotNull
  protected final TryOfferDesc<E> describeTryOffer(E paramE)
  {
    return new TryOfferDesc(paramE, queue);
  }
  
  @Nullable
  protected final Closed<?> getClosedForReceive()
  {
    Object localObject2 = queue.getNext();
    Object localObject1 = localObject2;
    if (!(localObject2 instanceof Closed)) {
      localObject1 = null;
    }
    return (Closed)localObject1;
  }
  
  @Nullable
  protected final Closed<?> getClosedForSend()
  {
    Object localObject2 = queue.getPrev();
    Object localObject1 = localObject2;
    if (!(localObject2 instanceof Closed)) {
      localObject1 = null;
    }
    return (Closed)localObject1;
  }
  
  @NotNull
  protected final LockFreeLinkedListHead getQueue()
  {
    return queue;
  }
  
  protected abstract boolean isBufferAlwaysFull();
  
  protected abstract boolean isBufferFull();
  
  public final boolean isClosedForSend()
  {
    return getClosedForSend() != null;
  }
  
  public final boolean isFull()
  {
    return (!(queue.getNext() instanceof ReceiveOrClosed)) && (isBufferFull());
  }
  
  public final boolean offer(E paramE)
  {
    paramE = offerInternal(paramE);
    if (paramE == AbstractChannelKt.OFFER_SUCCESS) {
      return true;
    }
    if (paramE == AbstractChannelKt.OFFER_FAILED) {
      return false;
    }
    if ((paramE instanceof Closed)) {
      throw ((Closed)paramE).getSendException();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("offerInternal returned ");
    localStringBuilder.append(paramE);
    throw ((Throwable)new IllegalStateException(localStringBuilder.toString().toString()));
  }
  
  @NotNull
  protected Object offerInternal(E paramE)
  {
    ReceiveOrClosed localReceiveOrClosed;
    Object localObject;
    do
    {
      localReceiveOrClosed = takeFirstReceiveOrPeekClosed();
      if (localReceiveOrClosed == null) {
        break;
      }
      localObject = localReceiveOrClosed.tryResumeReceive(paramE, null);
    } while (localObject == null);
    localReceiveOrClosed.completeResumeReceive(localObject);
    return localReceiveOrClosed.getOfferResult();
    return AbstractChannelKt.OFFER_FAILED;
  }
  
  @NotNull
  protected Object offerSelectInternal(E paramE, @NotNull SelectInstance<?> paramSelectInstance)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    paramE = describeTryOffer(paramE);
    paramSelectInstance = paramSelectInstance.performAtomicTrySelect((AtomicDesc)paramE);
    if (paramSelectInstance != null) {
      return paramSelectInstance;
    }
    paramSelectInstance = (ReceiveOrClosed)paramE.getResult();
    paramE = resumeToken;
    if (paramE == null) {
      Intrinsics.throwNpe();
    }
    paramSelectInstance.completeResumeReceive(paramE);
    return paramSelectInstance.getOfferResult();
  }
  
  protected void onClosed(@NotNull Closed<? super E> paramClosed)
  {
    Intrinsics.checkParameterIsNotNull(paramClosed, "closed");
  }
  
  public <R> void registerSelectSend(@NotNull SelectInstance<? super R> paramSelectInstance, E paramE, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    Object localObject;
    label136:
    do
    {
      do
      {
        if (paramSelectInstance.isSelected()) {
          return;
        }
        if (!isFull()) {
          break label136;
        }
        localObject = paramSelectInstance.performAtomicIfNotSelected((AtomicDesc)new TryEnqueueSendDesc(paramE, paramSelectInstance, paramFunction1));
        if (localObject == null) {
          break;
        }
        if (localObject == SelectKt.getALREADY_SELECTED()) {
          return;
        }
      } while (localObject == AbstractChannelKt.ENQUEUE_FAILED);
      if ((localObject instanceof Closed)) {
        throw ((Closed)localObject).getSendException();
      }
      paramSelectInstance = new StringBuilder();
      paramSelectInstance.append("performAtomicIfNotSelected(TryEnqueueSendDesc) returned ");
      paramSelectInstance.append(localObject);
      throw ((Throwable)new IllegalStateException(paramSelectInstance.toString().toString()));
      return;
      localObject = offerSelectInternal(paramE, paramSelectInstance);
      if (localObject == SelectKt.getALREADY_SELECTED()) {
        return;
      }
    } while (localObject == AbstractChannelKt.OFFER_FAILED);
    if (localObject == AbstractChannelKt.OFFER_SUCCESS)
    {
      UndispatchedKt.startCoroutineUndispatched(paramFunction1, paramSelectInstance.getCompletion());
      return;
    }
    if ((localObject instanceof Closed)) {
      throw ((Closed)localObject).getSendException();
    }
    paramSelectInstance = new StringBuilder();
    paramSelectInstance.append("offerSelectInternal returned ");
    paramSelectInstance.append(localObject);
    throw ((Throwable)new IllegalStateException(paramSelectInstance.toString().toString()));
  }
  
  @Nullable
  public final Object send(E paramE, @NotNull Continuation<? super Unit> paramContinuation)
  {
    if (offer(paramE)) {
      return Unit.INSTANCE;
    }
    return sendSuspend(paramE, paramContinuation);
  }
  
  @Nullable
  protected final ReceiveOrClosed<?> sendBuffered(E paramE)
  {
    LockFreeLinkedListHead localLockFreeLinkedListHead = queue;
    paramE = (LockFreeLinkedListNode)new SendBuffered(paramE);
    Object localObject;
    do
    {
      localObject = localLockFreeLinkedListHead.getPrev();
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      localObject = (LockFreeLinkedListNode)localObject;
      if ((localObject instanceof ReceiveOrClosed)) {
        return (ReceiveOrClosed)localObject;
      }
    } while (!((LockFreeLinkedListNode)localObject).addNext(paramE, localLockFreeLinkedListHead));
    return null;
  }
  
  @Nullable
  protected final ReceiveOrClosed<?> sendConflated(E paramE)
  {
    paramE = new SendBuffered(paramE);
    LockFreeLinkedListHead localLockFreeLinkedListHead = queue;
    Object localObject;
    do
    {
      localObject = localLockFreeLinkedListHead.getPrev();
      if (localObject == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      localObject = (LockFreeLinkedListNode)localObject;
      if ((localObject instanceof ReceiveOrClosed)) {
        return (ReceiveOrClosed)localObject;
      }
    } while (!((LockFreeLinkedListNode)localObject).addNext((LockFreeLinkedListNode)paramE, localLockFreeLinkedListHead));
    conflatePreviousSendBuffered((LockFreeLinkedListNode)paramE);
    return null;
  }
  
  @Nullable
  final Object sendSuspend(E paramE, @NotNull Continuation<? super Unit> paramContinuation)
  {
    paramContinuation = new CancellableContinuationImpl(CoroutineIntrinsics.normalizeContinuation(paramContinuation), 0);
    CancellableContinuation localCancellableContinuation = (CancellableContinuation)paramContinuation;
    SendElement localSendElement = new SendElement(paramE, localCancellableContinuation);
    Object localObject;
    do
    {
      localObject = access$enqueueSend(this, localSendElement);
      if (Intrinsics.areEqual(localObject, null))
      {
        localCancellableContinuation.initCancellability();
        CancellableContinuationKt.removeOnCancel(localCancellableContinuation, (LockFreeLinkedListNode)localSendElement);
        break;
      }
      if ((localObject instanceof Closed))
      {
        localCancellableContinuation.resumeWithException(((Closed)localObject).getSendException());
        break;
      }
      localObject = offerInternal(paramE);
      if (localObject == AbstractChannelKt.OFFER_SUCCESS)
      {
        localCancellableContinuation.resume(Unit.INSTANCE);
        break;
      }
    } while (localObject == AbstractChannelKt.OFFER_FAILED);
    if ((localObject instanceof Closed))
    {
      localCancellableContinuation.resumeWithException(((Closed)localObject).getSendException());
      return paramContinuation.getResult();
    }
    paramE = new StringBuilder();
    paramE.append("offerInternal returned ");
    paramE.append(localObject);
    throw ((Throwable)new IllegalStateException(paramE.toString().toString()));
  }
  
  @Nullable
  protected final ReceiveOrClosed<E> takeFirstReceiveOrPeekClosed()
  {
    LockFreeLinkedListHead localLockFreeLinkedListHead = queue;
    for (;;)
    {
      Object localObject1 = localLockFreeLinkedListHead.getNext();
      if (localObject1 == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      localObject1 = (LockFreeLinkedListNode)localObject1;
      LockFreeLinkedListNode localLockFreeLinkedListNode = (LockFreeLinkedListNode)localLockFreeLinkedListHead;
      Object localObject2 = null;
      if (localObject1 == localLockFreeLinkedListNode) {
        localObject1 = localObject2;
      } else if (!(localObject1 instanceof ReceiveOrClosed)) {
        localObject1 = localObject2;
      } else {
        if ((!((ReceiveOrClosed)localObject1 instanceof Closed)) && (!((LockFreeLinkedListNode)localObject1).remove())) {
          break label85;
        }
      }
      return (ReceiveOrClosed)localObject1;
      label85:
      ((LockFreeLinkedListNode)localObject1).helpDelete();
    }
  }
  
  @Nullable
  protected final Send takeFirstSendOrPeekClosed()
  {
    LockFreeLinkedListHead localLockFreeLinkedListHead = queue;
    for (;;)
    {
      Object localObject1 = localLockFreeLinkedListHead.getNext();
      if (localObject1 == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.internal.Node /* = kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode */");
      }
      localObject1 = (LockFreeLinkedListNode)localObject1;
      LockFreeLinkedListNode localLockFreeLinkedListNode = (LockFreeLinkedListNode)localLockFreeLinkedListHead;
      Object localObject2 = null;
      if (localObject1 == localLockFreeLinkedListNode) {
        localObject1 = localObject2;
      } else if (!(localObject1 instanceof Send)) {
        localObject1 = localObject2;
      } else {
        if ((!((Send)localObject1 instanceof Closed)) && (!((LockFreeLinkedListNode)localObject1).remove())) {
          break label85;
        }
      }
      return (Send)localObject1;
      label85:
      ((LockFreeLinkedListNode)localObject1).helpDelete();
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\004\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\002\030\000*\006\b\001\020\001 \0012\0020\0022\0020\003B\r\022\006\020\004\032\0028\001¢\006\002\020\005J\020\020\013\032\0020\f2\006\020\r\032\0020\bH\026J\024\020\016\032\004\030\0010\b2\b\020\017\032\004\030\0010\bH\026R\022\020\004\032\0028\0018\006X\004¢\006\004\n\002\020\006R\026\020\007\032\004\030\0010\b8VX\004¢\006\006\032\004\b\t\020\n¨\006\020"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractSendChannel$SendBuffered;", "E", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/channels/Send;", "element", "(Ljava/lang/Object;)V", "Ljava/lang/Object;", "pollResult", "", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "token", "tryResumeSend", "idempotent", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class SendBuffered<E>
    extends LockFreeLinkedListNode
    implements Send
  {
    @JvmField
    public final E element;
    
    public SendBuffered(E paramE)
    {
      element = paramE;
    }
    
    public void completeResumeSend(@NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramObject, "token");
      int i;
      if (paramObject == AbstractChannelKt.SEND_RESUMED) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 0) {
        throw ((Throwable)new IllegalStateException("Check failed.".toString()));
      }
    }
    
    @Nullable
    public Object getPollResult()
    {
      return element;
    }
    
    @Nullable
    public Object tryResumeSend(@Nullable Object paramObject)
    {
      return AbstractChannelKt.SEND_RESUMED;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\000\n\000\n\002\030\002\n\002\b\002\b\022\030\000*\006\b\001\020\001 \0012\036\022\n\022\b\022\004\022\002H\0010\0030\002j\016\022\n\022\b\022\004\022\002H\0010\003`\004B\025\022\006\020\005\032\0020\006\022\006\020\007\032\0028\001¢\006\002\020\bJ\032\020\t\032\004\030\0010\n2\006\020\013\032\0020\f2\006\020\r\032\0020\nH\024¨\006\016"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractSendChannel$SendBufferedDesc;", "E", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/experimental/channels/AbstractSendChannel$SendBuffered;", "Lkotlinx/coroutines/experimental/internal/AddLastDesc;", "queue", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "failure", "", "affected", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "next", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static class SendBufferedDesc<E>
    extends LockFreeLinkedListNode.AddLastDesc<AbstractSendChannel.SendBuffered<? extends E>>
  {
    public SendBufferedDesc(@NotNull LockFreeLinkedListHead paramLockFreeLinkedListHead, E paramE)
    {
      super((LockFreeLinkedListNode)new AbstractSendChannel.SendBuffered(paramE));
    }
    
    @Nullable
    protected Object failure(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "affected");
      Intrinsics.checkParameterIsNotNull(paramObject, "next");
      if ((paramLockFreeLinkedListNode instanceof ReceiveOrClosed)) {
        return AbstractChannelKt.OFFER_FAILED;
      }
      return null;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\030\002\n\002\b\002\b\002\030\000*\006\b\001\020\001 \0012\b\022\004\022\002H\0010\002B\025\022\006\020\003\032\0020\004\022\006\020\005\032\0028\001¢\006\002\020\006J\030\020\007\032\0020\b2\006\020\t\032\0020\n2\006\020\013\032\0020\nH\024¨\006\f"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractSendChannel$SendConflatedDesc;", "E", "Lkotlinx/coroutines/experimental/channels/AbstractSendChannel$SendBufferedDesc;", "queue", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;", "element", "(Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;Ljava/lang/Object;)V", "finishOnSuccess", "", "affected", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "next", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class SendConflatedDesc<E>
    extends AbstractSendChannel.SendBufferedDesc<E>
  {
    public SendConflatedDesc(@NotNull LockFreeLinkedListHead paramLockFreeLinkedListHead, E paramE)
    {
      super(paramE);
    }
    
    protected void finishOnSuccess(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "affected");
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
      super.finishOnSuccess(paramLockFreeLinkedListNode1, paramLockFreeLinkedListNode2);
      if ((paramLockFreeLinkedListNode1 instanceof AbstractSendChannel.SendBuffered)) {
        paramLockFreeLinkedListNode1.remove();
      }
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000<\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\004\n\002\020\016\n\002\b\003\b\002\030\000*\004\b\001\020\0012\0020\0022\0020\0032\0020\004B>\022\b\020\005\032\004\030\0010\006\022\f\020\007\032\b\022\004\022\0028\0010\b\022\034\020\t\032\030\b\001\022\n\022\b\022\004\022\0028\0010\013\022\006\022\004\030\0010\0060\nø\001\000¢\006\002\020\fJ\020\020\020\032\0020\0212\006\020\022\032\0020\006H\026J\b\020\023\032\0020\021H\026J\006\020\024\032\0020\021J\b\020\025\032\0020\026H\026J\024\020\027\032\004\030\0010\0062\b\020\030\032\004\030\0010\006H\026R+\020\t\032\030\b\001\022\n\022\b\022\004\022\0028\0010\013\022\006\022\004\030\0010\0060\n8\006X\004ø\001\000¢\006\004\n\002\020\rR\026\020\005\032\004\030\0010\006X\004¢\006\b\n\000\032\004\b\016\020\017R\026\020\007\032\b\022\004\022\0028\0010\b8\006X\004¢\006\002\n\000\002\004\n\002\b\t¨\006\031"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractSendChannel$SendSelect;", "R", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/channels/Send;", "Lkotlinx/coroutines/experimental/DisposableHandle;", "pollResult", "", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "(Ljava/lang/Object;Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "Lkotlin/jvm/functions/Function1;", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "", "token", "dispose", "disposeOnSelect", "toString", "", "tryResumeSend", "idempotent", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class SendSelect<R>
    extends LockFreeLinkedListNode
    implements Send, DisposableHandle
  {
    @JvmField
    @NotNull
    public final Function1<Continuation<? super R>, Object> block;
    @Nullable
    private final Object pollResult;
    @JvmField
    @NotNull
    public final SelectInstance<R> select;
    
    public SendSelect(@Nullable Object paramObject, @NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
    {
      pollResult = paramObject;
      select = paramSelectInstance;
      block = paramFunction1;
    }
    
    public void completeResumeSend(@NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramObject, "token");
      int i;
      if (paramObject == AbstractChannelKt.SELECT_STARTED) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 0) {
        throw ((Throwable)new IllegalStateException("Check failed.".toString()));
      }
      CoroutinesKt.startCoroutine(block, select.getCompletion());
    }
    
    public void dispose()
    {
      remove();
    }
    
    public final void disposeOnSelect()
    {
      select.disposeOnSelect((DisposableHandle)this);
    }
    
    @Nullable
    public Object getPollResult()
    {
      return pollResult;
    }
    
    @NotNull
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("SendSelect(");
      localStringBuilder.append(getPollResult());
      localStringBuilder.append(")[");
      localStringBuilder.append(select);
      localStringBuilder.append(']');
      return localStringBuilder.toString();
    }
    
    @Nullable
    public Object tryResumeSend(@Nullable Object paramObject)
    {
      if (select.trySelect(paramObject)) {
        return AbstractChannelKt.SELECT_STARTED;
      }
      return null;
    }
    
    @Deprecated(message="Replace with `dispose`", replaceWith=@ReplaceWith(expression="dispose()", imports={}))
    public void unregister()
    {
      DisposableHandle.DefaultImpls.unregister(this);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000>\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\020\000\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\b\004\030\000*\004\b\001\020\001*\004\b\002\020\0022\036\022\n\022\b\022\004\022\002H\0020\0040\003j\016\022\n\022\b\022\004\022\002H\0020\004`\005B<\022\006\020\006\032\0028\001\022\f\020\007\032\b\022\004\022\0028\0020\b\022\034\020\t\032\030\b\001\022\n\022\b\022\004\022\0028\0020\013\022\006\022\004\030\0010\f0\nø\001\000¢\006\002\020\rJ\032\020\016\032\004\030\0010\f2\006\020\017\032\0020\0202\006\020\021\032\0020\fH\024J\030\020\022\032\0020\0232\006\020\017\032\0020\0202\006\020\021\032\0020\020H\024J\032\020\024\032\004\030\0010\f2\006\020\017\032\0020\0202\006\020\021\032\0020\020H\024\002\004\n\002\b\t¨\006\025"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractSendChannel$TryEnqueueSendDesc;", "E", "R", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$AddLastDesc;", "Lkotlinx/coroutines/experimental/channels/AbstractSendChannel$SendSelect;", "Lkotlinx/coroutines/experimental/internal/AddLastDesc;", "element", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "", "(Lkotlinx/coroutines/experimental/channels/AbstractSendChannel;Ljava/lang/Object;Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "failure", "affected", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "next", "finishOnSuccess", "", "onPrepare", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private final class TryEnqueueSendDesc<E, R>
    extends LockFreeLinkedListNode.AddLastDesc<AbstractSendChannel.SendSelect<R>>
  {
    public TryEnqueueSendDesc(@NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
    {
      super((LockFreeLinkedListNode)new AbstractSendChannel.SendSelect(paramSelectInstance, paramFunction1, localObject));
    }
    
    @Nullable
    protected Object failure(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "affected");
      Intrinsics.checkParameterIsNotNull(paramObject, "next");
      if ((paramLockFreeLinkedListNode instanceof ReceiveOrClosed))
      {
        paramObject = paramLockFreeLinkedListNode;
        if (!(paramLockFreeLinkedListNode instanceof Closed)) {
          paramObject = null;
        }
        paramLockFreeLinkedListNode = (Closed)paramObject;
        if (paramLockFreeLinkedListNode != null) {
          return paramLockFreeLinkedListNode;
        }
        return AbstractChannelKt.ENQUEUE_FAILED;
      }
      return null;
    }
    
    protected void finishOnSuccess(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "affected");
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
      super.finishOnSuccess(paramLockFreeLinkedListNode1, paramLockFreeLinkedListNode2);
      ((AbstractSendChannel.SendSelect)node).disposeOnSelect();
    }
    
    @Nullable
    protected Object onPrepare(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode1, @NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode2)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode1, "affected");
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode2, "next");
      if (!isBufferFull()) {
        return AbstractChannelKt.ENQUEUE_FAILED;
      }
      return super.onPrepare(paramLockFreeLinkedListNode1, paramLockFreeLinkedListNode2);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\0006\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\002\b\004\030\000*\004\b\001\020\0012\036\022\n\022\b\022\004\022\002H\0010\0030\002j\016\022\n\022\b\022\004\022\002H\0010\003`\004B\025\022\006\020\005\032\0028\001\022\006\020\006\032\0020\007¢\006\002\020\bJ\032\020\f\032\004\030\0010\0132\006\020\r\032\0020\0162\006\020\017\032\0020\013H\024J\026\020\020\032\0020\0212\f\020\022\032\b\022\004\022\0028\0010\003H\024R\022\020\005\032\0028\0018\006X\004¢\006\004\n\002\020\tR\024\020\n\032\004\030\0010\0138\006@\006X\016¢\006\002\n\000¨\006\023"}, d2={"Lkotlinx/coroutines/experimental/channels/AbstractSendChannel$TryOfferDesc;", "E", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode$RemoveFirstDesc;", "Lkotlinx/coroutines/experimental/channels/ReceiveOrClosed;", "Lkotlinx/coroutines/experimental/internal/RemoveFirstDesc;", "element", "queue", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;", "(Ljava/lang/Object;Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListHead;)V", "Ljava/lang/Object;", "resumeToken", "", "failure", "affected", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "next", "validatePrepared", "", "node", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  protected static final class TryOfferDesc<E>
    extends LockFreeLinkedListNode.RemoveFirstDesc<ReceiveOrClosed<? super E>>
  {
    @JvmField
    public final E element;
    @JvmField
    @Nullable
    public Object resumeToken;
    
    public TryOfferDesc(E paramE, @NotNull LockFreeLinkedListHead paramLockFreeLinkedListHead)
    {
      super();
      element = paramE;
    }
    
    @Nullable
    protected Object failure(@NotNull LockFreeLinkedListNode paramLockFreeLinkedListNode, @NotNull Object paramObject)
    {
      Intrinsics.checkParameterIsNotNull(paramLockFreeLinkedListNode, "affected");
      Intrinsics.checkParameterIsNotNull(paramObject, "next");
      if (!(paramLockFreeLinkedListNode instanceof ReceiveOrClosed)) {
        return AbstractChannelKt.OFFER_FAILED;
      }
      if ((paramLockFreeLinkedListNode instanceof Closed)) {
        return paramLockFreeLinkedListNode;
      }
      return null;
    }
    
    protected boolean validatePrepared(@NotNull ReceiveOrClosed<? super E> paramReceiveOrClosed)
    {
      Intrinsics.checkParameterIsNotNull(paramReceiveOrClosed, "node");
      paramReceiveOrClosed = paramReceiveOrClosed.tryResumeReceive(element, this);
      if (paramReceiveOrClosed != null)
      {
        resumeToken = paramReceiveOrClosed;
        return true;
      }
      return false;
    }
  }
}

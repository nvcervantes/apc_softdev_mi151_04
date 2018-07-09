package kotlinx.coroutines.experimental.channels;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.internal.AtomicDesc;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import kotlinx.coroutines.experimental.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0008\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\020\021\n\002\020\000\n\002\b\005\n\002\020\013\n\002\b\005\n\002\030\002\n\002\b\006\n\002\030\002\n\002\b\004\b\026\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\002B\r\022\006\020\003\032\0020\004¢\006\002\020\005J\025\020\026\032\0020\b2\006\020\027\032\0028\000H\024¢\006\002\020\030J!\020\031\032\0020\b2\006\020\027\032\0028\0002\n\020\032\032\006\022\002\b\0030\033H\024¢\006\002\020\034J\n\020\035\032\004\030\0010\bH\024J\026\020\036\032\004\030\0010\b2\n\020\032\032\006\022\002\b\0030\033H\024R\030\020\006\032\n\022\006\022\004\030\0010\b0\007X\004¢\006\004\n\002\020\tR\021\020\003\032\0020\004¢\006\b\n\000\032\004\b\n\020\013R\016\020\f\032\0020\004X\016¢\006\002\n\000R\024\020\r\032\0020\0168DX\004¢\006\006\032\004\b\r\020\017R\024\020\020\032\0020\0168DX\004¢\006\006\032\004\b\020\020\017R\024\020\021\032\0020\0168DX\004¢\006\006\032\004\b\021\020\017R\024\020\022\032\0020\0168DX\004¢\006\006\032\004\b\022\020\017R\016\020\023\032\0020\024X\004¢\006\002\n\000R\022\020\025\032\0020\0048\002@\002X\016¢\006\002\n\000¨\006\037"}, d2={"Lkotlinx/coroutines/experimental/channels/ArrayChannel;", "E", "Lkotlinx/coroutines/experimental/channels/AbstractChannel;", "capacity", "", "(I)V", "buffer", "", "", "[Ljava/lang/Object;", "getCapacity", "()I", "head", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "size", "offerInternal", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/experimental/selects/SelectInstance;)Ljava/lang/Object;", "pollInternal", "pollSelectInternal", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public class ArrayChannel<E>
  extends AbstractChannel<E>
{
  private final Object[] buffer;
  private final int capacity;
  private int head;
  private final ReentrantLock lock;
  private volatile int size;
  
  public ArrayChannel(int paramInt)
  {
    capacity = paramInt;
    int i = capacity;
    paramInt = 1;
    if (i < 1) {
      paramInt = 0;
    }
    if (paramInt == 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ArrayChannel capacity must be at least 1, but ");
      localStringBuilder.append(capacity);
      localStringBuilder.append(" was specified");
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString().toString()));
    }
    lock = new ReentrantLock();
    buffer = new Object[capacity];
  }
  
  public final int getCapacity()
  {
    return capacity;
  }
  
  protected final boolean isBufferAlwaysEmpty()
  {
    return false;
  }
  
  protected final boolean isBufferAlwaysFull()
  {
    return false;
  }
  
  protected final boolean isBufferEmpty()
  {
    return size == 0;
  }
  
  protected final boolean isBufferFull()
  {
    return size == capacity;
  }
  
  @NotNull
  protected Object offerInternal(E paramE)
  {
    Object localObject1 = (ReceiveOrClosed)null;
    localObject1 = (Lock)lock;
    ((Lock)localObject1).lock();
    try
    {
      int i = size;
      Object localObject2 = getClosedForSend();
      if (localObject2 != null) {
        return localObject2;
      }
      if (i < capacity)
      {
        size = (i + 1);
        if (i == 0)
        {
          Object localObject3;
          do
          {
            localObject2 = takeFirstReceiveOrPeekClosed();
            if (localObject2 == null) {
              break;
            }
            if ((localObject2 instanceof Closed))
            {
              size = i;
              if (localObject2 == null) {
                Intrinsics.throwNpe();
              }
              return localObject2;
            }
            if (localObject2 == null) {
              Intrinsics.throwNpe();
            }
            localObject3 = ((ReceiveOrClosed)localObject2).tryResumeReceive(paramE, null);
          } while (localObject3 == null);
          size = i;
          paramE = Unit.INSTANCE;
          ((Lock)localObject1).unlock();
          if (localObject2 == null) {
            Intrinsics.throwNpe();
          }
          ((ReceiveOrClosed)localObject2).completeResumeReceive(localObject3);
          if (localObject2 == null) {
            Intrinsics.throwNpe();
          }
          return ((ReceiveOrClosed)localObject2).getOfferResult();
        }
        buffer[((head + i) % capacity)] = paramE;
        paramE = AbstractChannelKt.OFFER_SUCCESS;
        return paramE;
      }
      paramE = AbstractChannelKt.OFFER_FAILED;
      return paramE;
    }
    finally
    {
      ((Lock)localObject1).unlock();
    }
  }
  
  @NotNull
  protected Object offerSelectInternal(E paramE, @NotNull SelectInstance<?> paramSelectInstance)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Object localObject1 = (ReceiveOrClosed)null;
    localObject1 = (Lock)lock;
    ((Lock)localObject1).lock();
    for (;;)
    {
      try
      {
        i = size;
        Object localObject2 = getClosedForSend();
        if (localObject2 != null) {
          return localObject2;
        }
        if (i < capacity)
        {
          size = (i + 1);
          if (i == 0)
          {
            localObject2 = describeTryOffer(paramE);
            Object localObject3 = paramSelectInstance.performAtomicTrySelect((AtomicDesc)localObject2);
            if (localObject3 == null)
            {
              size = i;
              paramE = (ReceiveOrClosed)((AbstractSendChannel.TryOfferDesc)localObject2).getResult();
              paramSelectInstance = resumeToken;
              if (paramSelectInstance == null) {
                break label360;
              }
              i = 1;
              if (i == 0) {
                throw ((Throwable)new IllegalStateException("Check failed.".toString()));
              }
              localObject2 = Unit.INSTANCE;
              ((Lock)localObject1).unlock();
              if (paramE == null) {
                Intrinsics.throwNpe();
              }
              if (paramSelectInstance == null) {
                Intrinsics.throwNpe();
              }
              paramE.completeResumeReceive(paramSelectInstance);
              if (paramE == null) {
                Intrinsics.throwNpe();
              }
              return paramE.getOfferResult();
            }
            if (localObject3 != AbstractChannelKt.OFFER_FAILED)
            {
              if ((localObject3 != SelectKt.getALREADY_SELECTED()) && (!(localObject3 instanceof Closed)))
              {
                paramE = new StringBuilder();
                paramE.append("performAtomicTrySelect(describeTryOffer) returned ");
                paramE.append(localObject3);
                throw ((Throwable)new IllegalStateException(paramE.toString().toString()));
              }
              size = i;
              return localObject3;
            }
          }
          if (!paramSelectInstance.trySelect(null))
          {
            size = i;
            paramE = SelectKt.getALREADY_SELECTED();
            return paramE;
          }
          buffer[((head + i) % capacity)] = paramE;
          paramE = AbstractChannelKt.OFFER_SUCCESS;
          return paramE;
        }
        else
        {
          paramE = AbstractChannelKt.OFFER_FAILED;
          return paramE;
        }
      }
      finally
      {
        ((Lock)localObject1).unlock();
      }
      label360:
      int i = 0;
    }
  }
  
  @Nullable
  protected Object pollInternal()
  {
    Object localObject4 = (Send)null;
    Lock localLock = (Lock)lock;
    localLock.lock();
    for (;;)
    {
      Object localObject7;
      Object localObject5;
      try
      {
        int i = size;
        if (i == 0)
        {
          localObject1 = getClosedForSend();
          if (localObject1 == null) {
            localObject1 = AbstractChannelKt.POLL_FAILED;
          }
          return localObject1;
        }
        Object localObject8 = buffer[head];
        buffer[head] = null;
        size = (i - 1);
        localObject7 = AbstractChannelKt.POLL_FAILED;
        if (i != capacity) {
          break label260;
        }
        Object localObject1 = null;
        localObject5 = takeFirstSendOrPeekClosed();
        localObject6 = localObject7;
        if (localObject5 != null)
        {
          if (localObject5 == null) {
            Intrinsics.throwNpe();
          }
          localObject1 = ((Send)localObject5).tryResumeSend(null);
          if (localObject1 == null) {
            break label254;
          }
          if (localObject5 == null) {
            Intrinsics.throwNpe();
          }
          localObject6 = ((Send)localObject5).getPollResult();
          localObject4 = localObject5;
        }
        if ((localObject6 != AbstractChannelKt.POLL_FAILED) && (!(localObject6 instanceof Closed)))
        {
          size = i;
          buffer[((head + i) % capacity)] = localObject6;
        }
        head = ((head + 1) % capacity);
        localObject5 = Unit.INSTANCE;
        localLock.unlock();
        if (localObject1 != null)
        {
          if (localObject4 == null) {
            Intrinsics.throwNpe();
          }
          ((Send)localObject4).completeResumeSend(localObject1);
        }
        return localObject8;
      }
      finally
      {
        localLock.unlock();
      }
      label254:
      localObject4 = localObject5;
      continue;
      label260:
      Object localObject3 = null;
      Object localObject6 = localObject7;
    }
  }
  
  @Nullable
  protected Object pollSelectInternal(@NotNull SelectInstance<?> paramSelectInstance)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Send localSend = (Send)null;
    Lock localLock = (Lock)lock;
    localLock.lock();
    for (;;)
    {
      Object localObject3;
      try
      {
        int j = size;
        if (j == 0)
        {
          paramSelectInstance = getClosedForSend();
          if (paramSelectInstance == null) {
            paramSelectInstance = AbstractChannelKt.POLL_FAILED;
          }
          return paramSelectInstance;
        }
        Object localObject4 = buffer[head];
        buffer[head] = null;
        size = (j - 1);
        localObject3 = AbstractChannelKt.POLL_FAILED;
        if (j != capacity) {
          break label455;
        }
        localObject1 = describeTryPoll();
        localObject2 = paramSelectInstance.performAtomicTrySelect((AtomicDesc)localObject1);
        if (localObject2 == null)
        {
          localSend = (Send)((AbstractChannel.TryPollDesc)localObject1).getResult();
          localObject1 = resumeToken;
          if (localObject1 == null) {
            break label450;
          }
          i = 1;
          if (i == 0) {
            throw ((Throwable)new IllegalStateException("Check failed.".toString()));
          }
          if (localSend == null) {
            Intrinsics.throwNpe();
          }
          localObject2 = localSend.getPollResult();
        }
        else
        {
          if (localObject2 == AbstractChannelKt.POLL_FAILED) {
            break label455;
          }
          if (localObject2 == SelectKt.getALREADY_SELECTED())
          {
            size = j;
            buffer[head] = localObject4;
            return localObject2;
          }
          if ((localObject2 instanceof Closed))
          {
            localSend = (Send)localObject2;
            localObject1 = ((Closed)localObject2).tryResumeSend(null);
          }
          else
          {
            paramSelectInstance = new StringBuilder();
            paramSelectInstance.append("performAtomicTrySelect(describeTryOffer) returned ");
            paramSelectInstance.append(localObject2);
            throw ((Throwable)new IllegalStateException(paramSelectInstance.toString().toString()));
          }
        }
        if ((localObject2 != AbstractChannelKt.POLL_FAILED) && (!(localObject2 instanceof Closed)))
        {
          size = j;
          buffer[((head + j) % capacity)] = localObject2;
        }
        else if (!paramSelectInstance.trySelect(null))
        {
          size = j;
          buffer[head] = localObject4;
          paramSelectInstance = SelectKt.getALREADY_SELECTED();
          return paramSelectInstance;
        }
        head = ((head + 1) % capacity);
        paramSelectInstance = Unit.INSTANCE;
        localLock.unlock();
        if (localObject1 != null)
        {
          if (localSend == null) {
            Intrinsics.throwNpe();
          }
          localSend.completeResumeSend(localObject1);
        }
        return localObject4;
      }
      finally
      {
        localLock.unlock();
      }
      label450:
      int i = 0;
      continue;
      label455:
      Object localObject1 = null;
      Object localObject2 = localObject3;
    }
  }
}

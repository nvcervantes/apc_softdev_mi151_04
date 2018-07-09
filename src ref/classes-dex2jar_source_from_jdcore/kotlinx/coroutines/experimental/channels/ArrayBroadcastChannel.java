package kotlinx.coroutines.experimental.channels;

import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.RangesKt;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import kotlinx.coroutines.experimental.selects.SelectKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000f\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\020\021\n\002\020\000\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\t\n\000\n\002\020\013\n\002\b\004\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\020\003\n\002\b\013\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\0022\b\022\004\022\002H\0010\003:\001/B\r\022\006\020\004\032\0020\005¢\006\002\020\006J\b\020\032\032\0020\033H\002J\022\020\034\032\0020\0222\b\020\035\032\004\030\0010\036H\026J\026\020\037\032\0020\0332\f\020 \032\b\022\004\022\0028\0000\030H\002J\b\020!\032\0020\020H\002J\025\020\"\032\0028\0002\006\020#\032\0020\020H\003¢\006\002\020$J\025\020%\032\0020\t2\006\020&\032\0028\000H\024¢\006\002\020'J!\020(\032\0020\t2\006\020&\032\0028\0002\n\020)\032\006\022\002\b\0030*H\024¢\006\002\020+J\016\020,\032\b\022\004\022\0028\0000-H\026J\b\020.\032\0020\033H\002R\030\020\007\032\n\022\006\022\004\030\0010\t0\bX\004¢\006\004\n\002\020\nR\016\020\013\032\0020\fX\004¢\006\002\n\000R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\r\020\016R\022\020\017\032\0020\0208\002@\002X\016¢\006\002\n\000R\024\020\021\032\0020\0228TX\004¢\006\006\032\004\b\021\020\023R\024\020\024\032\0020\0228TX\004¢\006\006\032\004\b\024\020\023R\022\020\025\032\0020\0058\002@\002X\016¢\006\002\n\000R\032\020\026\032\016\022\n\022\b\022\004\022\0028\0000\0300\027X\004¢\006\002\n\000R\022\020\031\032\0020\0208\002@\002X\016¢\006\002\n\000¨\0060"}, d2={"Lkotlinx/coroutines/experimental/channels/ArrayBroadcastChannel;", "E", "Lkotlinx/coroutines/experimental/channels/AbstractSendChannel;", "Lkotlinx/coroutines/experimental/channels/BroadcastChannel;", "capacity", "", "(I)V", "buffer", "", "", "[Ljava/lang/Object;", "bufferLock", "Ljava/util/concurrent/locks/ReentrantLock;", "getCapacity", "()I", "head", "", "isBufferAlwaysFull", "", "()Z", "isBufferFull", "size", "subs", "Ljava/util/concurrent/CopyOnWriteArrayList;", "Lkotlinx/coroutines/experimental/channels/ArrayBroadcastChannel$Subscriber;", "tail", "checkSubOffers", "", "close", "cause", "", "closeSubscriber", "sub", "computeMinHead", "elementAt", "index", "(J)Ljava/lang/Object;", "offerInternal", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "offerSelectInternal", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "(Ljava/lang/Object;Lkotlinx/coroutines/experimental/selects/SelectInstance;)Ljava/lang/Object;", "openSubscription", "Lkotlinx/coroutines/experimental/channels/SubscriptionReceiveChannel;", "updateHead", "Subscriber", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class ArrayBroadcastChannel<E>
  extends AbstractSendChannel<E>
  implements BroadcastChannel<E>
{
  private final Object[] buffer;
  private final ReentrantLock bufferLock;
  private final int capacity;
  private volatile long head;
  private volatile int size;
  private final CopyOnWriteArrayList<Subscriber<E>> subs;
  private volatile long tail;
  
  public ArrayBroadcastChannel(int paramInt)
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
      localStringBuilder.append("ArrayBroadcastChannel capacity must be at least 1, but ");
      localStringBuilder.append(capacity);
      localStringBuilder.append(" was specified");
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString().toString()));
    }
    bufferLock = new ReentrantLock();
    buffer = new Object[capacity];
    subs = new CopyOnWriteArrayList();
  }
  
  private final void checkSubOffers()
  {
    Iterator localIterator = subs.iterator();
    int i = 0;
    for (int j = 0; localIterator.hasNext(); j = 1) {
      if (((Subscriber)localIterator.next()).checkOffer()) {
        i = 1;
      }
    }
    if ((i != 0) || (j == 0)) {
      updateHead();
    }
  }
  
  private final void closeSubscriber(Subscriber<E> paramSubscriber)
  {
    subs.remove(paramSubscriber);
    if (head == subHead) {
      updateHead();
    }
  }
  
  private final long computeMinHead()
  {
    Iterator localIterator = subs.iterator();
    for (long l = Long.MAX_VALUE; localIterator.hasNext(); l = RangesKt.coerceAtMost(l, nextsubHead)) {}
    return l;
  }
  
  private final E elementAt(long paramLong)
  {
    return buffer[((int)(paramLong % capacity))];
  }
  
  private final void updateHead()
  {
    long l2 = computeMinHead();
    for (;;)
    {
      Object localObject1 = (Send)null;
      localObject1 = (Lock)bufferLock;
      ((Lock)localObject1).lock();
      Object localObject2 = localObject1;
      Object localObject5;
      try
      {
        long l3 = tail;
        localObject2 = localObject1;
        long l1 = head;
        localObject2 = localObject1;
        long l4 = RangesKt.coerceAtMost(l2, l3);
        if (l4 <= l1)
        {
          ((Lock)localObject1).unlock();
          return;
        }
        localObject2 = localObject1;
        int i = size;
        if (l1 < l4)
        {
          localObject2 = localObject1;
          buffer[((int)(l1 % capacity))] = null;
          localObject2 = localObject1;
          int j = capacity;
          if (i >= j) {
            j = 1;
          } else {
            j = 0;
          }
          l1 += 1L;
          try
          {
            head = l1;
            i -= 1;
            size = i;
            if (j != 0)
            {
              Object localObject6;
              do
              {
                localObject2 = takeFirstSendOrPeekClosed();
                if (localObject2 == null) {
                  break;
                }
                boolean bool = localObject2 instanceof Closed;
                if (bool) {
                  break;
                }
                if (localObject2 == null) {
                  try
                  {
                    Intrinsics.throwNpe();
                  }
                  finally {}
                }
                localObject6 = localObject3.tryResumeSend(null);
              } while (localObject6 == null);
              Object localObject8 = buffer;
              l1 = capacity;
              j = (int)(l3 % l1);
              if (localObject3 == null) {
                throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.channels.Send");
              }
              localObject8[j] = localObject3.getPollResult();
              size = (i + 1);
              tail = (l3 + 1);
              localObject8 = Unit.INSTANCE;
              ((Lock)localObject1).unlock();
              if (localObject3 == null) {
                Intrinsics.throwNpe();
              }
              localObject3.completeResumeSend(localObject6);
              checkSubOffers();
              continue;
            }
          }
          finally
          {
            break label331;
          }
        }
        ((Lock)localObject1).unlock();
        return;
      }
      finally
      {
        localObject1 = localObject4;
        localObject5 = localObject7;
        label331:
        ((Lock)localObject1).unlock();
      }
    }
  }
  
  public boolean close(@Nullable Throwable paramThrowable)
  {
    if (!super.close(paramThrowable)) {
      return false;
    }
    checkSubOffers();
    return true;
  }
  
  public final int getCapacity()
  {
    return capacity;
  }
  
  protected boolean isBufferAlwaysFull()
  {
    return false;
  }
  
  protected boolean isBufferFull()
  {
    return size >= capacity;
  }
  
  @NotNull
  protected Object offerInternal(E paramE)
  {
    Lock localLock = (Lock)bufferLock;
    localLock.lock();
    try
    {
      Closed localClosed = getClosedForSend();
      if (localClosed != null) {
        return localClosed;
      }
      int i = size;
      if (i >= capacity)
      {
        paramE = AbstractChannelKt.OFFER_FAILED;
        return paramE;
      }
      long l = tail;
      buffer[((int)(l % capacity))] = paramE;
      size = (i + 1);
      tail = (l + 1);
      paramE = Unit.INSTANCE;
      localLock.unlock();
      checkSubOffers();
      return AbstractChannelKt.OFFER_SUCCESS;
    }
    finally
    {
      localLock.unlock();
    }
  }
  
  @NotNull
  protected Object offerSelectInternal(E paramE, @NotNull SelectInstance<?> paramSelectInstance)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Lock localLock = (Lock)bufferLock;
    localLock.lock();
    try
    {
      Closed localClosed = getClosedForSend();
      if (localClosed != null) {
        return localClosed;
      }
      int i = size;
      if (i >= capacity)
      {
        paramE = AbstractChannelKt.OFFER_FAILED;
        return paramE;
      }
      if (!paramSelectInstance.trySelect(null))
      {
        paramE = SelectKt.getALREADY_SELECTED();
        return paramE;
      }
      long l = tail;
      buffer[((int)(l % capacity))] = paramE;
      size = (i + 1);
      tail = (l + 1);
      paramE = Unit.INSTANCE;
      localLock.unlock();
      checkSubOffers();
      return AbstractChannelKt.OFFER_SUCCESS;
    }
    finally
    {
      localLock.unlock();
    }
  }
  
  @Deprecated(message="Renamed to `openSubscription`", replaceWith=@ReplaceWith(expression="openSubscription()", imports={}))
  @NotNull
  public SubscriptionReceiveChannel<E> open()
  {
    return BroadcastChannel.DefaultImpls.open(this);
  }
  
  @NotNull
  public SubscriptionReceiveChannel<E> openSubscription()
  {
    Subscriber localSubscriber = new Subscriber(this, head);
    subs.add(localSubscriber);
    long l = head;
    if (l != subHead)
    {
      subHead = l;
      updateHead();
    }
    return (SubscriptionReceiveChannel)localSubscriber;
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000D\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\t\n\002\b\002\n\002\020\013\n\002\b\005\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\002\n\002\020\000\n\002\b\003\n\002\030\002\n\000\b\002\030\000*\004\b\001\020\0012\b\022\004\022\002H\0010\0022\b\022\004\022\002H\0010\003B\033\022\f\020\004\032\b\022\004\022\0028\0010\005\022\006\020\006\032\0020\007¢\006\002\020\bJ\b\020\021\032\0020\nH\007J\b\020\022\032\0020\023H\026J\b\020\024\032\0020\nH\002J\n\020\025\032\004\030\0010\026H\002J\n\020\027\032\004\030\0010\026H\024J\026\020\030\032\004\030\0010\0262\n\020\031\032\006\022\002\b\0030\032H\024R\024\020\004\032\b\022\004\022\0028\0010\005X\004¢\006\002\n\000R\024\020\t\032\0020\n8TX\004¢\006\006\032\004\b\t\020\013R\024\020\f\032\0020\n8TX\004¢\006\006\032\004\b\f\020\013R\024\020\r\032\0020\n8TX\004¢\006\006\032\004\b\r\020\013R\024\020\016\032\0020\n8TX\004¢\006\006\032\004\b\016\020\013R\016\020\017\032\0020\020X\004¢\006\002\n\000R\022\020\006\032\0020\0078\006@\006X\016¢\006\002\n\000¨\006\033"}, d2={"Lkotlinx/coroutines/experimental/channels/ArrayBroadcastChannel$Subscriber;", "E", "Lkotlinx/coroutines/experimental/channels/AbstractChannel;", "Lkotlinx/coroutines/experimental/channels/SubscriptionReceiveChannel;", "broadcastChannel", "Lkotlinx/coroutines/experimental/channels/ArrayBroadcastChannel;", "subHead", "", "(Lkotlinx/coroutines/experimental/channels/ArrayBroadcastChannel;J)V", "isBufferAlwaysEmpty", "", "()Z", "isBufferAlwaysFull", "isBufferEmpty", "isBufferFull", "lock", "Ljava/util/concurrent/locks/ReentrantLock;", "checkOffer", "close", "", "needsToCheckOfferWithoutLock", "peekUnderLock", "", "pollInternal", "pollSelectInternal", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class Subscriber<E>
    extends AbstractChannel<E>
    implements SubscriptionReceiveChannel<E>
  {
    private final ArrayBroadcastChannel<E> broadcastChannel;
    private final ReentrantLock lock;
    @JvmField
    public volatile long subHead;
    
    public Subscriber(@NotNull ArrayBroadcastChannel<E> paramArrayBroadcastChannel, long paramLong)
    {
      broadcastChannel = paramArrayBroadcastChannel;
      subHead = paramLong;
      lock = new ReentrantLock();
    }
    
    private final boolean needsToCheckOfferWithoutLock()
    {
      if (getClosedForReceive() != null) {
        return false;
      }
      return (!isBufferEmpty()) || (broadcastChannel.getClosedForReceive() != null);
    }
    
    private final Object peekUnderLock()
    {
      long l = subHead;
      Object localObject = broadcastChannel.getClosedForReceive();
      if (l >= ArrayBroadcastChannel.access$getTail$p(broadcastChannel))
      {
        if (localObject != null) {
          return localObject;
        }
        return AbstractChannelKt.POLL_FAILED;
      }
      localObject = ArrayBroadcastChannel.access$elementAt(broadcastChannel, l);
      Closed localClosed = getClosedForReceive();
      if (localClosed != null) {
        return localClosed;
      }
      return localObject;
    }
    
    public final boolean checkOffer()
    {
      Closed localClosed = (Closed)null;
      boolean bool = false;
      for (;;)
      {
        Object localObject1 = localClosed;
        if (needsToCheckOfferWithoutLock()) {
          if (!lock.tryLock()) {
            localObject1 = localClosed;
          } else {
            try
            {
              Object localObject3 = peekUnderLock();
              localObject1 = AbstractChannelKt.POLL_FAILED;
              if (localObject3 == localObject1) {}
              ReceiveOrClosed localReceiveOrClosed;
              do
              {
                lock.unlock();
                break;
                if ((localObject3 instanceof Closed)) {
                  localObject1 = (Closed)localObject3;
                }
                for (;;)
                {
                  lock.unlock();
                  break label174;
                  localReceiveOrClosed = takeFirstReceiveOrPeekClosed();
                  localObject1 = localClosed;
                  if (localReceiveOrClosed != null)
                  {
                    if (!(localReceiveOrClosed instanceof Closed)) {
                      break;
                    }
                    localObject1 = localClosed;
                  }
                }
                localObject1 = localReceiveOrClosed.tryResumeReceive(localObject3, null);
              } while (localObject1 == null);
              subHead += 1;
              lock.unlock();
              if (localReceiveOrClosed == null) {
                Intrinsics.throwNpe();
              }
              localReceiveOrClosed.completeResumeReceive(localObject1);
              bool = true;
            }
            finally
            {
              lock.unlock();
            }
          }
        }
      }
      label174:
      if (localObject2 != null) {
        close(closeCause);
      }
      return bool;
    }
    
    public void close()
    {
      if (close(null)) {
        ArrayBroadcastChannel.access$closeSubscriber(broadcastChannel, this);
      }
    }
    
    protected boolean isBufferAlwaysEmpty()
    {
      return false;
    }
    
    protected boolean isBufferAlwaysFull()
    {
      throw ((Throwable)new IllegalStateException("Should not be used".toString()));
    }
    
    protected boolean isBufferEmpty()
    {
      return subHead >= ArrayBroadcastChannel.access$getTail$p(broadcastChannel);
    }
    
    protected boolean isBufferFull()
    {
      throw ((Throwable)new IllegalStateException("Should not be used".toString()));
    }
    
    @Nullable
    protected Object pollInternal()
    {
      lock.lock();
      for (;;)
      {
        try
        {
          Object localObject3 = peekUnderLock();
          if ((!(localObject3 instanceof Closed)) && (localObject3 != AbstractChannelKt.POLL_FAILED))
          {
            subHead += 1;
            i = 1;
            lock.unlock();
            if (!(localObject3 instanceof Closed)) {
              localObject1 = null;
            } else {
              localObject1 = localObject3;
            }
            Object localObject1 = (Closed)localObject1;
            if (localObject1 != null) {
              close(closeCause);
            }
            if (checkOffer()) {
              i = 1;
            }
            if (i != 0) {
              ArrayBroadcastChannel.access$updateHead(broadcastChannel);
            }
            return localObject3;
          }
        }
        finally
        {
          lock.unlock();
        }
        int i = 0;
      }
    }
    
    @Nullable
    protected Object pollSelectInternal(@NotNull SelectInstance<?> paramSelectInstance)
    {
      Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
      lock.lock();
      try
      {
        Object localObject2 = peekUnderLock();
        boolean bool = localObject2 instanceof Closed;
        Object localObject1 = null;
        int j = 1;
        int i = 0;
        if (bool)
        {
          paramSelectInstance = localObject2;
        }
        else if (localObject2 == AbstractChannelKt.POLL_FAILED)
        {
          paramSelectInstance = localObject2;
        }
        else if (!paramSelectInstance.trySelect(null))
        {
          paramSelectInstance = SelectKt.getALREADY_SELECTED();
        }
        else
        {
          subHead += 1;
          i = 1;
          paramSelectInstance = localObject2;
        }
        lock.unlock();
        if ((paramSelectInstance instanceof Closed)) {
          localObject1 = paramSelectInstance;
        }
        localObject1 = (Closed)localObject1;
        if (localObject1 != null) {
          close(closeCause);
        }
        if (checkOffer()) {
          i = j;
        }
        if (i != 0) {
          ArrayBroadcastChannel.access$updateHead(broadcastChannel);
        }
        return paramSelectInstance;
      }
      finally
      {
        lock.unlock();
      }
    }
  }
}

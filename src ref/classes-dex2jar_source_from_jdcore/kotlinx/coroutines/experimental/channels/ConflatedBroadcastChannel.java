package kotlinx.coroutines.experimental.channels;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;
import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Deprecated;
import kotlin.Metadata;
import kotlin.ReplaceWith;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.ArraysKt;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.internal.Symbol;
import kotlinx.coroutines.experimental.intrinsics.UndispatchedKt;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000b\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\t\n\002\020\021\n\002\030\002\n\002\b\005\n\002\020\003\n\000\n\002\020\002\n\002\b\004\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\t\030\000 4*\004\b\000\020\0012\b\022\004\022\002H\0010\002:\0043456B\017\b\026\022\006\020\003\032\0028\000¢\006\002\020\004B\005¢\006\002\020\005J=\020\025\032\016\022\n\022\b\022\004\022\0028\0000\0270\0262\024\020\030\032\020\022\n\022\b\022\004\022\0028\0000\027\030\0010\0262\f\020\031\032\b\022\004\022\0028\0000\027H\002¢\006\002\020\032J\022\020\033\032\0020\f2\b\020\034\032\004\030\0010\035H\027J\026\020\036\032\0020\0372\f\020\031\032\b\022\004\022\0028\0000\027H\003J\025\020 \032\0020\f2\006\020!\032\0028\000H\026¢\006\002\020\"J\027\020#\032\004\030\0010$2\006\020!\032\0028\000H\003¢\006\002\020%J\016\020&\032\b\022\004\022\0028\0000'H\027JJ\020(\032\0020\037\"\004\b\001\020)2\f\020*\032\b\022\004\022\002H)0+2\006\020!\032\0028\0002\034\020,\032\030\b\001\022\n\022\b\022\004\022\002H)0.\022\006\022\004\030\0010\b0-H\026ø\001\000¢\006\002\020/J=\0200\032\020\022\n\022\b\022\004\022\0028\0000\027\030\0010\0262\022\020\030\032\016\022\n\022\b\022\004\022\0028\0000\0270\0262\f\020\031\032\b\022\004\022\0028\0000\027H\003¢\006\002\020\032J\031\0201\032\0020\0372\006\020!\032\0028\000H@ø\001\000¢\006\002\0202R\024\020\006\032\b\022\004\022\0020\b0\007X\004¢\006\002\n\000R\016\020\t\032\0020\nX\004¢\006\002\n\000R\024\020\013\032\0020\f8VX\004¢\006\006\032\004\b\013\020\rR\024\020\016\032\0020\f8VX\004¢\006\006\032\004\b\016\020\rR\032\020\003\032\0028\0008FX\004¢\006\f\022\004\b\017\020\005\032\004\b\020\020\021R\034\020\022\032\004\030\0018\0008FX\004¢\006\f\022\004\b\023\020\005\032\004\b\024\020\021\002\004\n\002\b\t¨\0067"}, d2={"Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel;", "E", "Lkotlinx/coroutines/experimental/channels/BroadcastChannel;", "value", "(Ljava/lang/Object;)V", "()V", "_state", "Lkotlinx/atomicfu/AtomicRef;", "", "_updating", "Lkotlinx/atomicfu/AtomicInt;", "isClosedForSend", "", "()Z", "isFull", "value$annotations", "getValue", "()Ljava/lang/Object;", "valueOrNull", "valueOrNull$annotations", "getValueOrNull", "addSubscriber", "", "Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Subscriber;", "list", "subscriber", "([Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Subscriber;Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Subscriber;)[Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Subscriber;", "close", "cause", "", "closeSubscriber", "", "offer", "element", "(Ljava/lang/Object;)Z", "offerInternal", "Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Closed;", "(Ljava/lang/Object;)Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Closed;", "openSubscription", "Lkotlinx/coroutines/experimental/channels/SubscriptionReceiveChannel;", "registerSelectSend", "R", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlinx/coroutines/experimental/selects/SelectInstance;Ljava/lang/Object;Lkotlin/jvm/functions/Function1;)V", "removeSubscriber", "send", "(Ljava/lang/Object;Lkotlin/coroutines/experimental/Continuation;)Ljava/lang/Object;", "Closed", "Companion", "State", "Subscriber", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class ConflatedBroadcastChannel<E>
  implements BroadcastChannel<E>
{
  @JvmField
  @NotNull
  public static final Closed CLOSED;
  public static final Companion Companion;
  @JvmField
  @NotNull
  public static final State<Object> INITIAL_STATE;
  @JvmField
  @NotNull
  public static final Symbol UNDEFINED;
  private static final AtomicReferenceFieldUpdater _state$FU;
  private static final AtomicIntegerFieldUpdater _updating$FU;
  private volatile Object _state = INITIAL_STATE;
  private volatile int _updating = 0;
  
  static
  {
    Companion = new Companion(null);
    CLOSED = new Closed(null);
    UNDEFINED = new Symbol("UNDEFINED");
    INITIAL_STATE = new State(UNDEFINED, null);
    _state$FU = AtomicReferenceFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, Object.class, "_state");
    _updating$FU = AtomicIntegerFieldUpdater.newUpdater(ConflatedBroadcastChannel.class, "_updating");
  }
  
  public ConflatedBroadcastChannel() {}
  
  public ConflatedBroadcastChannel(E paramE)
  {
    this();
    _state$FU.lazySet(this, new State(paramE, null));
  }
  
  private final Subscriber<E>[] addSubscriber(Subscriber<E>[] paramArrayOfSubscriber, Subscriber<E> paramSubscriber)
  {
    if (paramArrayOfSubscriber == null) {
      return new Subscriber[] { paramSubscriber };
    }
    return (Subscriber[])ArraysKt.plus((Object[])paramArrayOfSubscriber, paramSubscriber);
  }
  
  private final void closeSubscriber(Subscriber<E> paramSubscriber)
  {
    Object localObject1;
    Object localObject2;
    do
    {
      localObject1 = _state;
      if ((localObject1 instanceof Closed)) {
        return;
      }
      if (!(localObject1 instanceof State)) {
        break;
      }
      Object localObject3 = (State)localObject1;
      localObject2 = value;
      if (localObject1 == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel.State<E>");
      }
      localObject3 = subscribers;
      if (localObject3 == null) {
        Intrinsics.throwNpe();
      }
      localObject2 = new State(localObject2, removeSubscriber((Subscriber[])localObject3, paramSubscriber));
    } while (!_state$FU.compareAndSet(this, localObject1, localObject2));
    return;
    paramSubscriber = new StringBuilder();
    paramSubscriber.append("Invalid state ");
    paramSubscriber.append(localObject1);
    throw ((Throwable)new IllegalStateException(paramSubscriber.toString().toString()));
  }
  
  private final Closed offerInternal(E paramE)
  {
    if (!_updating$FU.compareAndSet(this, 0, 1)) {
      return null;
    }
    try
    {
      State localState;
      do
      {
        localObject = _state;
        if ((localObject instanceof Closed))
        {
          paramE = (Closed)localObject;
          return paramE;
        }
        if (!(localObject instanceof State)) {
          break;
        }
        if (localObject == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel.State<E>");
        }
        localState = new State(paramE, subscribers);
      } while (!_state$FU.compareAndSet(this, localObject, localState));
      Object localObject = subscribers;
      if (localObject != null)
      {
        localObject = (Object[])localObject;
        int i = 0;
        while (i < localObject.length)
        {
          ((Subscriber)localObject[i]).offerInternal(paramE);
          i += 1;
        }
      }
      return null;
      paramE = new StringBuilder();
      paramE.append("Invalid state ");
      paramE.append(localObject);
      throw ((Throwable)new IllegalStateException(paramE.toString().toString()));
    }
    finally
    {
      _updating = 0;
    }
  }
  
  private final Subscriber<E>[] removeSubscriber(Subscriber<E>[] paramArrayOfSubscriber, Subscriber<E> paramSubscriber)
  {
    Object[] arrayOfObject = (Object[])paramArrayOfSubscriber;
    int j = arrayOfObject.length;
    int k = ArraysKt.indexOf(arrayOfObject, paramSubscriber);
    int i;
    if (k >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalStateException("Check failed.".toString()));
    }
    if (j == 1) {
      return null;
    }
    paramSubscriber = new Subscriber[j - 1];
    System.arraycopy(paramArrayOfSubscriber, 0, paramSubscriber, 0, k);
    System.arraycopy(paramArrayOfSubscriber, k + 1, paramSubscriber, k, j - k - 1);
    return paramSubscriber;
  }
  
  public boolean close(@Nullable Throwable paramThrowable)
  {
    Object localObject2;
    int i;
    do
    {
      localObject2 = _state;
      boolean bool = localObject2 instanceof Closed;
      i = 0;
      if (bool) {
        return false;
      }
      if (!(localObject2 instanceof State)) {
        break;
      }
      if (paramThrowable == null) {
        localObject1 = CLOSED;
      } else {
        localObject1 = new Closed(paramThrowable);
      }
    } while (!_state$FU.compareAndSet(this, localObject2, localObject1));
    if (localObject2 == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel.State<E>");
    }
    Object localObject1 = subscribers;
    if (localObject1 != null)
    {
      localObject1 = (Object[])localObject1;
      while (i < localObject1.length)
      {
        ((Subscriber)localObject1[i]).close(paramThrowable);
        i += 1;
      }
    }
    return true;
    paramThrowable = new StringBuilder();
    paramThrowable.append("Invalid state ");
    paramThrowable.append(localObject2);
    throw ((Throwable)new IllegalStateException(paramThrowable.toString().toString()));
  }
  
  public final E getValue()
  {
    Object localObject = _state;
    if ((localObject instanceof Closed)) {
      throw ((Closed)localObject).getValueException();
    }
    if ((localObject instanceof State))
    {
      localObject = (State)localObject;
      if (value == UNDEFINED) {
        throw ((Throwable)new IllegalStateException("No value"));
      }
      return value;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Invalid state ");
    localStringBuilder.append(localObject);
    throw ((Throwable)new IllegalStateException(localStringBuilder.toString().toString()));
  }
  
  @Nullable
  public final E getValueOrNull()
  {
    Object localObject = _state;
    if ((localObject instanceof Closed)) {
      return null;
    }
    if ((localObject instanceof State))
    {
      localObject = (State)localObject;
      if (value == UNDEFINED) {
        return null;
      }
      return value;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Invalid state ");
    localStringBuilder.append(localObject);
    throw ((Throwable)new IllegalStateException(localStringBuilder.toString().toString()));
  }
  
  public boolean isClosedForSend()
  {
    return _state instanceof Closed;
  }
  
  public boolean isFull()
  {
    return false;
  }
  
  public boolean offer(E paramE)
  {
    paramE = offerInternal(paramE);
    if (paramE != null) {
      throw paramE.getSendException();
    }
    return true;
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
    Object localObject2 = new Subscriber(this);
    Object localObject1;
    State localState;
    do
    {
      localObject1 = _state;
      if ((localObject1 instanceof Closed))
      {
        ((Subscriber)localObject2).close(closeCause);
        return (SubscriptionReceiveChannel)localObject2;
      }
      if (!(localObject1 instanceof State)) {
        break;
      }
      localState = (State)localObject1;
      if (value != UNDEFINED) {
        ((Subscriber)localObject2).offerInternal(value);
      }
      Object localObject3 = value;
      if (localObject1 == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlinx.coroutines.experimental.channels.ConflatedBroadcastChannel.State<E>");
      }
      localState = new State(localObject3, addSubscriber(subscribers, (Subscriber)localObject2));
    } while (!_state$FU.compareAndSet(this, localObject1, localState));
    return (SubscriptionReceiveChannel)localObject2;
    localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append("Invalid state ");
    ((StringBuilder)localObject2).append(localObject1);
    throw ((Throwable)new IllegalStateException(((StringBuilder)localObject2).toString().toString()));
  }
  
  public <R> void registerSelectSend(@NotNull SelectInstance<? super R> paramSelectInstance, E paramE, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramSelectInstance, "select");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "block");
    if (!paramSelectInstance.trySelect(null)) {
      return;
    }
    paramE = offerInternal(paramE);
    if (paramE != null)
    {
      paramSelectInstance.resumeSelectCancellableWithException(paramE.getSendException());
      return;
    }
    UndispatchedKt.startCoroutineUndispatched(paramFunction1, paramSelectInstance.getCompletion());
  }
  
  @Nullable
  public Object send(E paramE, @NotNull Continuation<? super Unit> paramContinuation)
  {
    paramE = offerInternal(paramE);
    if (paramE != null) {
      throw paramE.getSendException();
    }
    return Unit.INSTANCE;
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\020\003\n\002\b\007\b\002\030\0002\0020\001B\017\022\b\020\002\032\004\030\0010\003¢\006\002\020\004R\022\020\002\032\004\030\0010\0038\006X\004¢\006\002\n\000R\021\020\005\032\0020\0038F¢\006\006\032\004\b\006\020\007R\021\020\b\032\0020\0038F¢\006\006\032\004\b\t\020\007¨\006\n"}, d2={"Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Closed;", "", "closeCause", "", "(Ljava/lang/Throwable;)V", "sendException", "getSendException", "()Ljava/lang/Throwable;", "valueException", "getValueException", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class Closed
  {
    @JvmField
    @Nullable
    public final Throwable closeCause;
    
    public Closed(@Nullable Throwable paramThrowable)
    {
      closeCause = paramThrowable;
    }
    
    @NotNull
    public final Throwable getSendException()
    {
      Throwable localThrowable = closeCause;
      if (localThrowable != null) {
        return localThrowable;
      }
      return (Throwable)new ClosedSendChannelException("Channel was closed");
    }
    
    @NotNull
    public final Throwable getValueException()
    {
      Throwable localThrowable = closeCause;
      if (localThrowable != null) {
        return localThrowable;
      }
      return (Throwable)new IllegalStateException("Channel was closed");
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\036\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\020\020\003\032\0020\0048\006X\004¢\006\002\n\000R\030\020\005\032\n\022\006\022\004\030\0010\0010\0068\006X\004¢\006\002\n\000R\020\020\007\032\0020\b8\006X\004¢\006\002\n\000¨\006\t"}, d2={"Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Companion;", "", "()V", "CLOSED", "Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Closed;", "INITIAL_STATE", "Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$State;", "UNDEFINED", "Lkotlinx/coroutines/experimental/internal/Symbol;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class Companion
  {
    private Companion() {}
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\000\n\002\020\000\n\002\b\002\n\002\020\021\n\002\030\002\n\002\b\003\b\002\030\000*\004\b\001\020\0012\0020\002B%\022\b\020\003\032\004\030\0010\002\022\024\020\004\032\020\022\n\022\b\022\004\022\0028\0010\006\030\0010\005¢\006\002\020\007R \020\004\032\020\022\n\022\b\022\004\022\0028\0010\006\030\0010\0058\006X\004¢\006\004\n\002\020\bR\022\020\003\032\004\030\0010\0028\006X\004¢\006\002\n\000¨\006\t"}, d2={"Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$State;", "E", "", "value", "subscribers", "", "Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Subscriber;", "(Ljava/lang/Object;[Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Subscriber;)V", "[Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Subscriber;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class State<E>
  {
    @JvmField
    @Nullable
    public final ConflatedBroadcastChannel.Subscriber<E>[] subscribers;
    @JvmField
    @Nullable
    public final Object value;
    
    public State(@Nullable Object paramObject, @Nullable ConflatedBroadcastChannel.Subscriber<E>[] paramArrayOfSubscriber)
    {
      value = paramObject;
      subscribers = paramArrayOfSubscriber;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\020\000\n\002\b\003\b\002\030\000*\004\b\001\020\0012\b\022\004\022\002H\0010\0022\b\022\004\022\002H\0010\003B\023\022\f\020\004\032\b\022\004\022\0028\0010\005¢\006\002\020\006J\b\020\007\032\0020\bH\026J\025\020\t\032\0020\n2\006\020\013\032\0028\001H\026¢\006\002\020\fR\024\020\004\032\b\022\004\022\0028\0010\005X\004¢\006\002\n\000¨\006\r"}, d2={"Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel$Subscriber;", "E", "Lkotlinx/coroutines/experimental/channels/ConflatedChannel;", "Lkotlinx/coroutines/experimental/channels/SubscriptionReceiveChannel;", "broadcastChannel", "Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel;", "(Lkotlinx/coroutines/experimental/channels/ConflatedBroadcastChannel;)V", "close", "", "offerInternal", "", "element", "(Ljava/lang/Object;)Ljava/lang/Object;", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  private static final class Subscriber<E>
    extends ConflatedChannel<E>
    implements SubscriptionReceiveChannel<E>
  {
    private final ConflatedBroadcastChannel<E> broadcastChannel;
    
    public Subscriber(@NotNull ConflatedBroadcastChannel<E> paramConflatedBroadcastChannel)
    {
      broadcastChannel = paramConflatedBroadcastChannel;
    }
    
    public void close()
    {
      if (close(null)) {
        ConflatedBroadcastChannel.access$closeSubscriber(broadcastChannel, this);
      }
    }
    
    @NotNull
    public Object offerInternal(E paramE)
    {
      return super.offerInternal(paramE);
    }
  }
}

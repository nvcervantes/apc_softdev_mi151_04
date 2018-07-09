package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\003\n\002\b\f\n\002\020\002\n\000\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\006\030\000*\006\b\000\020\001 \0002\0020\0022\0020\0032\b\022\004\022\002H\0010\004B\017\022\b\020\005\032\004\030\0010\006¢\006\002\020\007J\020\020\022\032\0020\0232\006\020\024\032\0020\025H\026J\020\020\026\032\0020\0232\006\020\024\032\0020\025H\026J\b\020\027\032\0020\030H\026J!\020\031\032\004\030\0010\0252\006\020\032\032\0028\0002\b\020\033\032\004\030\0010\025H\026¢\006\002\020\034J\024\020\035\032\004\030\0010\0252\b\020\033\032\004\030\0010\025H\026R\022\020\005\032\004\030\0010\0068\006X\004¢\006\002\n\000R\032\020\b\032\b\022\004\022\0028\0000\0008VX\004¢\006\006\032\004\b\t\020\nR\032\020\013\032\b\022\004\022\0028\0000\0008VX\004¢\006\006\032\004\b\f\020\nR\021\020\r\032\0020\0068F¢\006\006\032\004\b\016\020\017R\021\020\020\032\0020\0068F¢\006\006\032\004\b\021\020\017¨\006\036"}, d2={"Lkotlinx/coroutines/experimental/channels/Closed;", "E", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/channels/Send;", "Lkotlinx/coroutines/experimental/channels/ReceiveOrClosed;", "closeCause", "", "(Ljava/lang/Throwable;)V", "offerResult", "getOfferResult", "()Lkotlinx/coroutines/experimental/channels/Closed;", "pollResult", "getPollResult", "receiveException", "getReceiveException", "()Ljava/lang/Throwable;", "sendException", "getSendException", "completeResumeReceive", "", "token", "", "completeResumeSend", "toString", "", "tryResumeReceive", "value", "idempotent", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "tryResumeSend", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class Closed<E>
  extends LockFreeLinkedListNode
  implements Send, ReceiveOrClosed<E>
{
  @JvmField
  @Nullable
  public final Throwable closeCause;
  
  public Closed(@Nullable Throwable paramThrowable)
  {
    closeCause = paramThrowable;
  }
  
  public void completeResumeReceive(@NotNull Object paramObject)
  {
    Intrinsics.checkParameterIsNotNull(paramObject, "token");
    int i;
    if (paramObject == AbstractChannelKt.CLOSE_RESUMED) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalStateException("Check failed.".toString()));
    }
  }
  
  public void completeResumeSend(@NotNull Object paramObject)
  {
    Intrinsics.checkParameterIsNotNull(paramObject, "token");
    int i;
    if (paramObject == AbstractChannelKt.CLOSE_RESUMED) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalStateException("Check failed.".toString()));
    }
  }
  
  @NotNull
  public Closed<E> getOfferResult()
  {
    return this;
  }
  
  @NotNull
  public Closed<E> getPollResult()
  {
    return this;
  }
  
  @NotNull
  public final Throwable getReceiveException()
  {
    Throwable localThrowable = closeCause;
    if (localThrowable != null) {
      return localThrowable;
    }
    return (Throwable)new ClosedReceiveChannelException("Channel was closed");
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
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Closed[");
    localStringBuilder.append(closeCause);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  @Nullable
  public Object tryResumeReceive(E paramE, @Nullable Object paramObject)
  {
    return AbstractChannelKt.CLOSE_RESUMED;
  }
  
  @Nullable
  public Object tryResumeSend(@Nullable Object paramObject)
  {
    return AbstractChannelKt.CLOSE_RESUMED;
  }
}

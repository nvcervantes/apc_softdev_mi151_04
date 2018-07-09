package kotlinx.coroutines.experimental.channels;

import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import kotlinx.coroutines.experimental.CancellableContinuation;
import kotlinx.coroutines.experimental.internal.LockFreeLinkedListNode;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\000\n\000\n\002\030\002\n\002\020\002\n\002\b\006\n\002\020\016\n\002\b\003\b\007\030\0002\0020\0012\0020\002B\035\022\b\020\003\032\004\030\0010\004\022\f\020\005\032\b\022\004\022\0020\0070\006¢\006\002\020\bJ\020\020\013\032\0020\0072\006\020\f\032\0020\004H\026J\b\020\r\032\0020\016H\026J\024\020\017\032\004\030\0010\0042\b\020\020\032\004\030\0010\004H\026R\026\020\005\032\b\022\004\022\0020\0070\0068\006X\004¢\006\002\n\000R\026\020\003\032\004\030\0010\004X\004¢\006\b\n\000\032\004\b\t\020\n¨\006\021"}, d2={"Lkotlinx/coroutines/experimental/channels/SendElement;", "Lkotlinx/coroutines/experimental/internal/LockFreeLinkedListNode;", "Lkotlinx/coroutines/experimental/channels/Send;", "pollResult", "", "cont", "Lkotlinx/coroutines/experimental/CancellableContinuation;", "", "(Ljava/lang/Object;Lkotlinx/coroutines/experimental/CancellableContinuation;)V", "getPollResult", "()Ljava/lang/Object;", "completeResumeSend", "token", "toString", "", "tryResumeSend", "idempotent", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class SendElement
  extends LockFreeLinkedListNode
  implements Send
{
  @JvmField
  @NotNull
  public final CancellableContinuation<Unit> cont;
  @Nullable
  private final Object pollResult;
  
  public SendElement(@Nullable Object paramObject, @NotNull CancellableContinuation<? super Unit> paramCancellableContinuation)
  {
    pollResult = paramObject;
    cont = paramCancellableContinuation;
  }
  
  public void completeResumeSend(@NotNull Object paramObject)
  {
    Intrinsics.checkParameterIsNotNull(paramObject, "token");
    cont.completeResume(paramObject);
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
    localStringBuilder.append("SendElement(");
    localStringBuilder.append(getPollResult());
    localStringBuilder.append(")[");
    localStringBuilder.append(cont);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
  
  @Nullable
  public Object tryResumeSend(@Nullable Object paramObject)
  {
    return cont.tryResume(Unit.INSTANCE, paramObject);
  }
}

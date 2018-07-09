package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.functions.Function1;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000:\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\020\000\n\002\b\003\n\002\020\002\n\000\n\002\020\003\n\000\n\002\020\016\n\000\b\002\030\000*\004\b\000\020\0012\b\022\004\022\0020\0030\002B<\022\006\020\004\032\0020\003\022\f\020\005\032\b\022\004\022\0028\0000\006\022\034\020\007\032\030\b\001\022\n\022\b\022\004\022\0028\0000\t\022\006\022\004\030\0010\n0\bø\001\000¢\006\002\020\013J\023\020\r\032\0020\0162\b\020\017\032\004\030\0010\020H\002J\b\020\021\032\0020\022H\026R)\020\007\032\030\b\001\022\n\022\b\022\004\022\0028\0000\t\022\006\022\004\030\0010\n0\bX\004ø\001\000¢\006\004\n\002\020\fR\024\020\005\032\b\022\004\022\0028\0000\006X\004¢\006\002\n\000\002\004\n\002\b\t¨\006\023"}, d2={"Lkotlinx/coroutines/experimental/SelectJoinOnCompletion;", "R", "Lkotlinx/coroutines/experimental/JobNode;", "Lkotlinx/coroutines/experimental/JobSupport;", "job", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function1;", "Lkotlin/coroutines/experimental/Continuation;", "", "(Lkotlinx/coroutines/experimental/JobSupport;Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function1;)V", "Lkotlin/jvm/functions/Function1;", "invoke", "", "reason", "", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class SelectJoinOnCompletion<R>
  extends JobNode<JobSupport>
{
  private final Function1<Continuation<? super R>, Object> block;
  private final SelectInstance<R> select;
  
  public SelectJoinOnCompletion(@NotNull JobSupport paramJobSupport, @NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function1<? super Continuation<? super R>, ? extends Object> paramFunction1)
  {
    super((Job)paramJobSupport);
    select = paramSelectInstance;
    block = paramFunction1;
  }
  
  public void invoke(@Nullable Throwable paramThrowable)
  {
    if (select.trySelect(null)) {
      CancellableKt.startCoroutineCancellable(block, select.getCompletion());
    }
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SelectJoinOnCompletion[");
    localStringBuilder.append(select);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}

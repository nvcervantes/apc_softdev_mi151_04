package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.jvm.functions.Function2;
import kotlinx.coroutines.experimental.selects.SelectInstance;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000:\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\020\000\n\002\030\002\n\002\b\003\n\002\020\002\n\000\n\002\020\003\n\000\n\002\020\016\n\000\b\002\030\000*\004\b\000\020\0012\b\022\004\022\0020\0030\002BD\022\006\020\004\032\0020\003\022\f\020\005\032\b\022\004\022\0028\0000\006\022$\020\007\032 \b\001\022\006\022\004\030\0010\t\022\n\022\b\022\004\022\0028\0000\n\022\006\022\004\030\0010\t0\bø\001\000¢\006\002\020\013J\023\020\r\032\0020\0162\b\020\017\032\004\030\0010\020H\002J\b\020\021\032\0020\022H\026R1\020\007\032 \b\001\022\006\022\004\030\0010\t\022\n\022\b\022\004\022\0028\0000\n\022\006\022\004\030\0010\t0\bX\004ø\001\000¢\006\004\n\002\020\fR\024\020\005\032\b\022\004\022\0028\0000\006X\004¢\006\002\n\000\002\004\n\002\b\t¨\006\023"}, d2={"Lkotlinx/coroutines/experimental/SelectAwaitOnCompletion;", "R", "Lkotlinx/coroutines/experimental/JobNode;", "Lkotlinx/coroutines/experimental/JobSupport;", "job", "select", "Lkotlinx/coroutines/experimental/selects/SelectInstance;", "block", "Lkotlin/Function2;", "", "Lkotlin/coroutines/experimental/Continuation;", "(Lkotlinx/coroutines/experimental/JobSupport;Lkotlinx/coroutines/experimental/selects/SelectInstance;Lkotlin/jvm/functions/Function2;)V", "Lkotlin/jvm/functions/Function2;", "invoke", "", "reason", "", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class SelectAwaitOnCompletion<R>
  extends JobNode<JobSupport>
{
  private final Function2<Object, Continuation<? super R>, Object> block;
  private final SelectInstance<R> select;
  
  public SelectAwaitOnCompletion(@NotNull JobSupport paramJobSupport, @NotNull SelectInstance<? super R> paramSelectInstance, @NotNull Function2<Object, ? super Continuation<? super R>, ? extends Object> paramFunction2)
  {
    super((Job)paramJobSupport);
    select = paramSelectInstance;
    block = paramFunction2;
  }
  
  public void invoke(@Nullable Throwable paramThrowable)
  {
    if (select.trySelect(null)) {
      ((JobSupport)job).selectAwaitCompletion$kotlinx_coroutines_core(select, block);
    }
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SelectAwaitOnCompletion[");
    localStringBuilder.append(select);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}

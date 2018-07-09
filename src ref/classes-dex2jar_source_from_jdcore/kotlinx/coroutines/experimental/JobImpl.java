package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\b\002\030\0002\0020\001B\021\022\n\b\002\020\002\032\004\030\0010\003¢\006\002\020\004¨\006\005"}, d2={"Lkotlinx/coroutines/experimental/JobImpl;", "Lkotlinx/coroutines/experimental/JobSupport;", "parent", "Lkotlinx/coroutines/experimental/Job;", "(Lkotlinx/coroutines/experimental/Job;)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class JobImpl
  extends JobSupport
{
  public JobImpl()
  {
    this(null, 1, null);
  }
  
  public JobImpl(@Nullable Job paramJob)
  {
    super(true);
    initParentJob(paramJob);
  }
}

package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\000\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020\016\n\000\n\002\030\002\n\002\b\002\032\"\020\000\032\0020\0012\006\020\002\032\0020\0032\006\020\004\032\0020\0052\n\b\002\020\006\032\004\030\0010\007\032\032\020\b\032\0020\0012\006\020\004\032\0020\0052\n\b\002\020\006\032\004\030\0010\007¨\006\t"}, d2={"newFixedThreadPoolContext", "Lkotlin/coroutines/experimental/CoroutineContext;", "nThreads", "", "name", "", "parent", "Lkotlinx/coroutines/experimental/Job;", "newSingleThreadContext", "kotlinx-coroutines-core"}, k=2, mv={1, 1, 7})
public final class ThreadPoolDispatcherKt
{
  @NotNull
  public static final CoroutineContext newFixedThreadPoolContext(int paramInt, @NotNull String paramString, @Nullable Job paramJob)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "name");
    int i = 1;
    if (paramInt < 1) {
      i = 0;
    }
    if (i == 0)
    {
      paramString = new StringBuilder();
      paramString.append("Expected at least one thread, but ");
      paramString.append(paramInt);
      paramString.append(" specified");
      throw ((Throwable)new IllegalArgumentException(paramString.toString().toString()));
    }
    paramJob = JobKt.Job(paramJob);
    return paramJob.plus((CoroutineContext)new ThreadPoolDispatcher(paramInt, paramString, paramJob));
  }
  
  @NotNull
  public static final CoroutineContext newSingleThreadContext(@NotNull String paramString, @Nullable Job paramJob)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "name");
    return newFixedThreadPoolContext(1, paramString, paramJob);
  }
}

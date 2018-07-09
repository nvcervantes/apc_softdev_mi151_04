package kotlinx.coroutines.experimental;

import java.util.concurrent.TimeUnit;
import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000<\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\n\002\020\002\n\002\b\004\n\002\020\003\n\002\b\002\b\022\030\000*\004\b\000\020\001*\n\b\001\020\002 \000*\002H\0012\0020\0032\0020\0042\b\022\004\022\002H\0020\005B#\022\006\020\006\032\0020\007\022\006\020\b\032\0020\t\022\f\020\n\032\b\022\004\022\0028\0000\005¢\006\002\020\013J\025\020\022\032\0020\0232\006\020\024\032\0028\001H\026¢\006\002\020\025J\020\020\026\032\0020\0232\006\020\027\032\0020\030H\026J\b\020\031\032\0020\023H\026R\026\020\n\032\b\022\004\022\0028\0000\0058\004X\004¢\006\002\n\000R\034\020\f\032\0020\r8\026X\004¢\006\016\n\000\022\004\b\016\020\017\032\004\b\020\020\021R\016\020\006\032\0020\007X\004¢\006\002\n\000R\016\020\b\032\0020\tX\004¢\006\002\n\000¨\006\032"}, d2={"Lkotlinx/coroutines/experimental/TimeoutCompletion;", "U", "T", "Lkotlinx/coroutines/experimental/JobSupport;", "Ljava/lang/Runnable;", "Lkotlin/coroutines/experimental/Continuation;", "time", "", "unit", "Ljava/util/concurrent/TimeUnit;", "cont", "(JLjava/util/concurrent/TimeUnit;Lkotlin/coroutines/experimental/Continuation;)V", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "context$annotations", "()V", "getContext", "()Lkotlin/coroutines/experimental/CoroutineContext;", "resume", "", "value", "(Ljava/lang/Object;)V", "resumeWithException", "exception", "", "run", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
class TimeoutCompletion<U, T extends U>
  extends JobSupport
  implements Runnable, Continuation<T>
{
  @JvmField
  @NotNull
  protected final Continuation<U> cont;
  @NotNull
  private final CoroutineContext context;
  private final long time;
  private final TimeUnit unit;
  
  public TimeoutCompletion(long paramLong, @NotNull TimeUnit paramTimeUnit, @NotNull Continuation<? super U> paramContinuation)
  {
    super(true);
    time = paramLong;
    unit = paramTimeUnit;
    cont = paramContinuation;
    context = cont.getContext().plus((CoroutineContext)this);
  }
  
  @NotNull
  public CoroutineContext getContext()
  {
    return context;
  }
  
  public void resume(T paramT)
  {
    CoroutineDispatcherKt.resumeDirect(cont, paramT);
  }
  
  public void resumeWithException(@NotNull Throwable paramThrowable)
  {
    Intrinsics.checkParameterIsNotNull(paramThrowable, "exception");
    CoroutineDispatcherKt.resumeDirectWithException(cont, paramThrowable);
  }
  
  public void run()
  {
    cancel((Throwable)ScheduledKt.access$TimeoutException(time, unit, (Job)this));
  }
}

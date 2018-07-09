package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.CoroutineContext;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\013\n\000\n\002\020\016\n\000\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\030\020\003\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\bH\026J\020\020\t\032\0020\n2\006\020\005\032\0020\006H\026J\b\020\013\032\0020\fH\026¨\006\r"}, d2={"Lkotlinx/coroutines/experimental/Unconfined;", "Lkotlinx/coroutines/experimental/CoroutineDispatcher;", "()V", "dispatch", "", "context", "Lkotlin/coroutines/experimental/CoroutineContext;", "block", "Ljava/lang/Runnable;", "isDispatchNeeded", "", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class Unconfined
  extends CoroutineDispatcher
{
  public static final Unconfined INSTANCE;
  
  static
  {
    new Unconfined();
  }
  
  private Unconfined()
  {
    INSTANCE = (Unconfined)this;
  }
  
  public void dispatch(@NotNull CoroutineContext paramCoroutineContext, @NotNull Runnable paramRunnable)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    Intrinsics.checkParameterIsNotNull(paramRunnable, "block");
    throw ((Throwable)new UnsupportedOperationException());
  }
  
  public boolean isDispatchNeeded(@NotNull CoroutineContext paramCoroutineContext)
  {
    Intrinsics.checkParameterIsNotNull(paramCoroutineContext, "context");
    return false;
  }
  
  @NotNull
  public String toString()
  {
    return "Unconfined";
  }
}

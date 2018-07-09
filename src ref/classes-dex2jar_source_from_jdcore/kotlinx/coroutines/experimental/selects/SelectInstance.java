package kotlinx.coroutines.experimental.selects;

import kotlin.Metadata;
import kotlin.coroutines.experimental.Continuation;
import kotlinx.coroutines.experimental.DisposableHandle;
import kotlinx.coroutines.experimental.internal.AtomicDesc;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000:\n\002\030\002\n\000\n\002\020\000\n\000\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\003\n\002\020\003\n\002\b\003\bf\030\000*\006\b\000\020\001 \0002\0020\002J\020\020\n\032\0020\0132\006\020\f\032\0020\rH&J\022\020\016\032\004\030\0010\0022\006\020\017\032\0020\020H&J\022\020\021\032\004\030\0010\0022\006\020\017\032\0020\020H&J\020\020\022\032\0020\0132\006\020\023\032\0020\024H&J\022\020\025\032\0020\b2\b\020\026\032\004\030\0010\002H&R\030\020\003\032\b\022\004\022\0028\0000\004X¦\004¢\006\006\032\004\b\005\020\006R\022\020\007\032\0020\bX¦\004¢\006\006\032\004\b\007\020\t¨\006\027"}, d2={"Lkotlinx/coroutines/experimental/selects/SelectInstance;", "R", "", "completion", "Lkotlin/coroutines/experimental/Continuation;", "getCompletion", "()Lkotlin/coroutines/experimental/Continuation;", "isSelected", "", "()Z", "disposeOnSelect", "", "handle", "Lkotlinx/coroutines/experimental/DisposableHandle;", "performAtomicIfNotSelected", "desc", "Lkotlinx/coroutines/experimental/internal/AtomicDesc;", "performAtomicTrySelect", "resumeSelectCancellableWithException", "exception", "", "trySelect", "idempotent", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface SelectInstance<R>
{
  public abstract void disposeOnSelect(@NotNull DisposableHandle paramDisposableHandle);
  
  @NotNull
  public abstract Continuation<R> getCompletion();
  
  public abstract boolean isSelected();
  
  @Nullable
  public abstract Object performAtomicIfNotSelected(@NotNull AtomicDesc paramAtomicDesc);
  
  @Nullable
  public abstract Object performAtomicTrySelect(@NotNull AtomicDesc paramAtomicDesc);
  
  public abstract void resumeSelectCancellableWithException(@NotNull Throwable paramThrowable);
  
  public abstract boolean trySelect(@Nullable Object paramObject);
}

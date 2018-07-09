package kotlinx.coroutines.experimental.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\002\b\003\b&\030\0002\0020\001B\005¢\006\002\020\002J\036\020\003\032\0020\0042\n\020\005\032\006\022\002\b\0030\0062\b\020\007\032\004\030\0010\001H&J\026\020\b\032\004\030\0010\0012\n\020\005\032\006\022\002\b\0030\006H&¨\006\t"}, d2={"Lkotlinx/coroutines/experimental/internal/AtomicDesc;", "", "()V", "complete", "", "op", "Lkotlinx/coroutines/experimental/internal/AtomicOp;", "failure", "prepare", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract class AtomicDesc
{
  public AtomicDesc() {}
  
  public abstract void complete(@NotNull AtomicOp<?> paramAtomicOp, @Nullable Object paramObject);
  
  @Nullable
  public abstract Object prepare(@NotNull AtomicOp<?> paramAtomicOp);
}

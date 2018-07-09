package kotlinx.coroutines.experimental.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\020\000\n\002\b\004\b&\030\0002\0020\001B\005¢\006\002\020\002J\024\020\003\032\004\030\0010\0012\b\020\004\032\004\030\0010\001H&¨\006\005"}, d2={"Lkotlinx/coroutines/experimental/internal/OpDescriptor;", "", "()V", "perform", "affected", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract class OpDescriptor
{
  public OpDescriptor() {}
  
  @Nullable
  public abstract Object perform(@Nullable Object paramObject);
}

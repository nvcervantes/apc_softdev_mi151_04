package kotlinx.coroutines.experimental.sync;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\002\b\003\n\002\020\016\n\000\b\002\030\0002\0020\001B\r\022\006\020\002\032\0020\001¢\006\002\020\003J\b\020\004\032\0020\005H\026R\020\020\002\032\0020\0018\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lkotlinx/coroutines/experimental/sync/Empty;", "", "locked", "(Ljava/lang/Object;)V", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class Empty
{
  @JvmField
  @NotNull
  public final Object locked;
  
  public Empty(@NotNull Object paramObject)
  {
    locked = paramObject;
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Empty[");
    localStringBuilder.append(locked);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}

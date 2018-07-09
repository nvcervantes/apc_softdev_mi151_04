package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\003\n\002\020\016\n\000\b\002\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\b\020\006\032\0020\007H\026R\024\020\002\032\0020\003X\004¢\006\b\n\000\032\004\b\002\020\005¨\006\b"}, d2={"Lkotlinx/coroutines/experimental/Empty;", "Lkotlinx/coroutines/experimental/JobSupport$Incomplete;", "isActive", "", "(Z)V", "()Z", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class Empty
  implements JobSupport.Incomplete
{
  private final boolean isActive;
  
  public Empty(boolean paramBoolean)
  {
    isActive = paramBoolean;
  }
  
  public boolean isActive()
  {
    return isActive;
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Empty{");
    String str;
    if (isActive()) {
      str = "Active";
    } else {
      str = "New";
    }
    localStringBuilder.append(str);
    localStringBuilder.append('}');
    return localStringBuilder.toString();
  }
}

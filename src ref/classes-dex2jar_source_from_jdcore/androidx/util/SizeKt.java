package androidx.util;

import android.support.annotation.RequiresApi;
import android.util.Size;
import android.util.SizeF;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\026\n\000\n\002\020\b\n\002\030\002\n\002\020\007\n\002\030\002\n\002\b\002\032\r\020\000\032\0020\001*\0020\002H\n\032\r\020\000\032\0020\003*\0020\004H\n\032\r\020\005\032\0020\001*\0020\002H\n\032\r\020\005\032\0020\003*\0020\004H\n¨\006\006"}, d2={"component1", "", "Landroid/util/Size;", "", "Landroid/util/SizeF;", "component2", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class SizeKt
{
  @RequiresApi(21)
  public static final float component1(@NotNull SizeF paramSizeF)
  {
    return paramSizeF.getWidth();
  }
  
  @RequiresApi(21)
  public static final int component1(@NotNull Size paramSize)
  {
    return paramSize.getWidth();
  }
  
  @RequiresApi(21)
  public static final float component2(@NotNull SizeF paramSizeF)
  {
    return paramSizeF.getHeight();
  }
  
  @RequiresApi(21)
  public static final int component2(@NotNull Size paramSize)
  {
    return paramSize.getHeight();
  }
}

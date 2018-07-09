package androidx.graphics;

import android.graphics.Point;
import android.graphics.PointF;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\026\n\000\n\002\020\b\n\002\030\002\n\002\020\007\n\002\030\002\n\002\b\t\032\r\020\000\032\0020\001*\0020\002H\n\032\r\020\000\032\0020\003*\0020\004H\n\032\r\020\005\032\0020\001*\0020\002H\n\032\r\020\005\032\0020\003*\0020\004H\n\032\025\020\006\032\0020\002*\0020\0022\006\020\007\032\0020\002H\n\032\025\020\006\032\0020\002*\0020\0022\006\020\b\032\0020\001H\n\032\025\020\006\032\0020\004*\0020\0042\006\020\007\032\0020\004H\n\032\025\020\006\032\0020\004*\0020\0042\006\020\b\032\0020\003H\n\032\025\020\t\032\0020\002*\0020\0022\006\020\007\032\0020\002H\n\032\025\020\t\032\0020\002*\0020\0022\006\020\b\032\0020\001H\n\032\025\020\t\032\0020\004*\0020\0042\006\020\007\032\0020\004H\n\032\025\020\t\032\0020\004*\0020\0042\006\020\b\032\0020\003H\n\032\r\020\n\032\0020\002*\0020\004H\b\032\r\020\013\032\0020\004*\0020\002H\b\032\r\020\f\032\0020\002*\0020\002H\n\032\r\020\f\032\0020\004*\0020\004H\n¨\006\r"}, d2={"component1", "", "Landroid/graphics/Point;", "", "Landroid/graphics/PointF;", "component2", "minus", "p", "xy", "plus", "toPoint", "toPointF", "unaryMinus", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class PointKt
{
  public static final float component1(@NotNull PointF paramPointF)
  {
    return x;
  }
  
  public static final int component1(@NotNull Point paramPoint)
  {
    return x;
  }
  
  public static final float component2(@NotNull PointF paramPointF)
  {
    return y;
  }
  
  public static final int component2(@NotNull Point paramPoint)
  {
    return y;
  }
  
  @NotNull
  public static final Point minus(@NotNull Point paramPoint, int paramInt)
  {
    paramPoint = new Point(x, y);
    paramInt = -paramInt;
    paramPoint.offset(paramInt, paramInt);
    return paramPoint;
  }
  
  @NotNull
  public static final Point minus(@NotNull Point paramPoint1, @NotNull Point paramPoint2)
  {
    paramPoint1 = new Point(x, y);
    paramPoint1.offset(-x, -y);
    return paramPoint1;
  }
  
  @NotNull
  public static final PointF minus(@NotNull PointF paramPointF, float paramFloat)
  {
    paramPointF = new PointF(x, y);
    paramFloat = -paramFloat;
    paramPointF.offset(paramFloat, paramFloat);
    return paramPointF;
  }
  
  @NotNull
  public static final PointF minus(@NotNull PointF paramPointF1, @NotNull PointF paramPointF2)
  {
    paramPointF1 = new PointF(x, y);
    paramPointF1.offset(-x, -y);
    return paramPointF1;
  }
  
  @NotNull
  public static final Point plus(@NotNull Point paramPoint, int paramInt)
  {
    paramPoint = new Point(x, y);
    paramPoint.offset(paramInt, paramInt);
    return paramPoint;
  }
  
  @NotNull
  public static final Point plus(@NotNull Point paramPoint1, @NotNull Point paramPoint2)
  {
    paramPoint1 = new Point(x, y);
    paramPoint1.offset(x, y);
    return paramPoint1;
  }
  
  @NotNull
  public static final PointF plus(@NotNull PointF paramPointF, float paramFloat)
  {
    paramPointF = new PointF(x, y);
    paramPointF.offset(paramFloat, paramFloat);
    return paramPointF;
  }
  
  @NotNull
  public static final PointF plus(@NotNull PointF paramPointF1, @NotNull PointF paramPointF2)
  {
    paramPointF1 = new PointF(x, y);
    paramPointF1.offset(x, y);
    return paramPointF1;
  }
  
  @NotNull
  public static final Point toPoint(@NotNull PointF paramPointF)
  {
    return new Point((int)x, (int)y);
  }
  
  @NotNull
  public static final PointF toPointF(@NotNull Point paramPoint)
  {
    return new PointF(paramPoint);
  }
  
  @NotNull
  public static final Point unaryMinus(@NotNull Point paramPoint)
  {
    return new Point(-x, -y);
  }
  
  @NotNull
  public static final PointF unaryMinus(@NotNull PointF paramPointF)
  {
    return new PointF(-x, -y);
  }
}

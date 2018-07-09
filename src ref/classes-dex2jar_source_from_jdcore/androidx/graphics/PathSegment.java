package androidx.graphics;

import android.graphics.PointF;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\020\007\n\002\b\017\n\002\020\013\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B%\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\003\022\006\020\007\032\0020\005¢\006\002\020\bJ\t\020\017\032\0020\003HÆ\003J\t\020\020\032\0020\005HÆ\003J\t\020\021\032\0020\003HÆ\003J\t\020\022\032\0020\005HÆ\003J1\020\023\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\0052\b\b\002\020\006\032\0020\0032\b\b\002\020\007\032\0020\005HÆ\001J\023\020\024\032\0020\0252\b\020\026\032\004\030\0010\001HÖ\003J\t\020\027\032\0020\030HÖ\001J\t\020\031\032\0020\032HÖ\001R\021\020\006\032\0020\003¢\006\b\n\000\032\004\b\t\020\nR\021\020\007\032\0020\005¢\006\b\n\000\032\004\b\013\020\fR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\r\020\nR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\016\020\f¨\006\033"}, d2={"Landroidx/graphics/PathSegment;", "", "start", "Landroid/graphics/PointF;", "startFraction", "", "end", "endFraction", "(Landroid/graphics/PointF;FLandroid/graphics/PointF;F)V", "getEnd", "()Landroid/graphics/PointF;", "getEndFraction", "()F", "getStart", "getStartFraction", "component1", "component2", "component3", "component4", "copy", "equals", "", "other", "hashCode", "", "toString", "", "core-ktx_release"}, k=1, mv={1, 1, 9})
public final class PathSegment
{
  @NotNull
  private final PointF end;
  private final float endFraction;
  @NotNull
  private final PointF start;
  private final float startFraction;
  
  public PathSegment(@NotNull PointF paramPointF1, float paramFloat1, @NotNull PointF paramPointF2, float paramFloat2)
  {
    start = paramPointF1;
    startFraction = paramFloat1;
    end = paramPointF2;
    endFraction = paramFloat2;
  }
  
  @NotNull
  public final PointF component1()
  {
    return start;
  }
  
  public final float component2()
  {
    return startFraction;
  }
  
  @NotNull
  public final PointF component3()
  {
    return end;
  }
  
  public final float component4()
  {
    return endFraction;
  }
  
  @NotNull
  public final PathSegment copy(@NotNull PointF paramPointF1, float paramFloat1, @NotNull PointF paramPointF2, float paramFloat2)
  {
    return new PathSegment(paramPointF1, paramFloat1, paramPointF2, paramFloat2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof PathSegment))
      {
        paramObject = (PathSegment)paramObject;
        if ((Intrinsics.areEqual(start, start)) && (Float.compare(startFraction, startFraction) == 0) && (Intrinsics.areEqual(end, end)) && (Float.compare(endFraction, endFraction) == 0)) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final PointF getEnd()
  {
    return end;
  }
  
  public final float getEndFraction()
  {
    return endFraction;
  }
  
  @NotNull
  public final PointF getStart()
  {
    return start;
  }
  
  public final float getStartFraction()
  {
    return startFraction;
  }
  
  public int hashCode()
  {
    PointF localPointF = start;
    int j = 0;
    int i;
    if (localPointF != null) {
      i = localPointF.hashCode();
    } else {
      i = 0;
    }
    int k = Float.floatToIntBits(startFraction);
    localPointF = end;
    if (localPointF != null) {
      j = localPointF.hashCode();
    }
    return ((i * 31 + k) * 31 + j) * 31 + Float.floatToIntBits(endFraction);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PathSegment(start=");
    localStringBuilder.append(start);
    localStringBuilder.append(", startFraction=");
    localStringBuilder.append(startFraction);
    localStringBuilder.append(", end=");
    localStringBuilder.append(end);
    localStringBuilder.append(", endFraction=");
    localStringBuilder.append(endFraction);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

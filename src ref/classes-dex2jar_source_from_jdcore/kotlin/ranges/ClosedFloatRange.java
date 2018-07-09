package kotlin.ranges;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\002\030\002\n\002\020\007\n\002\b\t\n\002\020\013\n\002\b\003\n\002\020\000\n\000\n\002\020\b\n\002\b\005\n\002\020\016\n\000\b\003\030\0002\b\022\004\022\0020\0020\001B\025\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002¢\006\002\020\005J\021\020\013\032\0020\f2\006\020\r\032\0020\002H\002J\023\020\016\032\0020\f2\b\020\017\032\004\030\0010\020H\002J\b\020\021\032\0020\022H\026J\b\020\023\032\0020\fH\026J\030\020\024\032\0020\f2\006\020\025\032\0020\0022\006\020\026\032\0020\002H\026J\b\020\027\032\0020\030H\026R\016\020\006\032\0020\002X\004¢\006\002\n\000R\016\020\007\032\0020\002X\004¢\006\002\n\000R\024\020\004\032\0020\0028VX\004¢\006\006\032\004\b\b\020\tR\024\020\003\032\0020\0028VX\004¢\006\006\032\004\b\n\020\t¨\006\031"}, d2={"Lkotlin/ranges/ClosedFloatRange;", "Lkotlin/ranges/ClosedFloatingPointRange;", "", "start", "endInclusive", "(FF)V", "_endInclusive", "_start", "getEndInclusive", "()Ljava/lang/Float;", "getStart", "contains", "", "value", "equals", "other", "", "hashCode", "", "isEmpty", "lessThanOrEquals", "a", "b", "toString", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
final class ClosedFloatRange
  implements ClosedFloatingPointRange<Float>
{
  private final float _endInclusive;
  private final float _start;
  
  public ClosedFloatRange(float paramFloat1, float paramFloat2)
  {
    _start = paramFloat1;
    _endInclusive = paramFloat2;
  }
  
  public boolean contains(float paramFloat)
  {
    return (paramFloat >= _start) && (paramFloat <= _endInclusive);
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    if ((paramObject instanceof ClosedFloatRange)) {
      if ((!isEmpty()) || (!((ClosedFloatRange)paramObject).isEmpty()))
      {
        float f = _start;
        paramObject = (ClosedFloatRange)paramObject;
        if ((f != _start) || (_endInclusive != _endInclusive)) {}
      }
      else
      {
        return true;
      }
    }
    return false;
  }
  
  @NotNull
  public Float getEndInclusive()
  {
    return Float.valueOf(_endInclusive);
  }
  
  @NotNull
  public Float getStart()
  {
    return Float.valueOf(_start);
  }
  
  public int hashCode()
  {
    if (isEmpty()) {
      return -1;
    }
    return 31 * Float.valueOf(_start).hashCode() + Float.valueOf(_endInclusive).hashCode();
  }
  
  public boolean isEmpty()
  {
    return _start > _endInclusive;
  }
  
  public boolean lessThanOrEquals(float paramFloat1, float paramFloat2)
  {
    return paramFloat1 <= paramFloat2;
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(_start);
    localStringBuilder.append("..");
    localStringBuilder.append(_endInclusive);
    return localStringBuilder.toString();
  }
}

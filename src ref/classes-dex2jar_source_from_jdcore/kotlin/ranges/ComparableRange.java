package kotlin.ranges;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\000\n\002\020\017\n\002\030\002\n\002\b\b\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\022\030\000*\016\b\000\020\001*\b\022\004\022\002H\0010\0022\b\022\004\022\002H\0010\003B\025\022\006\020\004\032\0028\000\022\006\020\005\032\0028\000¢\006\002\020\006J\023\020\013\032\0020\f2\b\020\r\032\004\030\0010\016H\002J\b\020\017\032\0020\020H\026J\b\020\021\032\0020\022H\026R\026\020\005\032\0028\000X\004¢\006\n\n\002\020\t\032\004\b\007\020\bR\026\020\004\032\0028\000X\004¢\006\n\n\002\020\t\032\004\b\n\020\b¨\006\023"}, d2={"Lkotlin/ranges/ComparableRange;", "T", "", "Lkotlin/ranges/ClosedRange;", "start", "endInclusive", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)V", "getEndInclusive", "()Ljava/lang/Comparable;", "Ljava/lang/Comparable;", "getStart", "equals", "", "other", "", "hashCode", "", "toString", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
class ComparableRange<T extends Comparable<? super T>>
  implements ClosedRange<T>
{
  @NotNull
  private final T endInclusive;
  @NotNull
  private final T start;
  
  public ComparableRange(@NotNull T paramT1, @NotNull T paramT2)
  {
    start = paramT1;
    endInclusive = paramT2;
  }
  
  public boolean contains(@NotNull T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramT, "value");
    return ClosedRange.DefaultImpls.contains(this, paramT);
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    if ((paramObject instanceof ComparableRange)) {
      if ((!isEmpty()) || (!((ComparableRange)paramObject).isEmpty()))
      {
        Comparable localComparable = getStart();
        paramObject = (ComparableRange)paramObject;
        if ((!Intrinsics.areEqual(localComparable, paramObject.getStart())) || (!Intrinsics.areEqual(getEndInclusive(), paramObject.getEndInclusive()))) {}
      }
      else
      {
        return true;
      }
    }
    return false;
  }
  
  @NotNull
  public T getEndInclusive()
  {
    return endInclusive;
  }
  
  @NotNull
  public T getStart()
  {
    return start;
  }
  
  public int hashCode()
  {
    if (isEmpty()) {
      return -1;
    }
    return 31 * getStart().hashCode() + getEndInclusive().hashCode();
  }
  
  public boolean isEmpty()
  {
    return ClosedRange.DefaultImpls.isEmpty(this);
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(getStart());
    localStringBuilder.append("..");
    localStringBuilder.append(getEndInclusive());
    return localStringBuilder.toString();
  }
}

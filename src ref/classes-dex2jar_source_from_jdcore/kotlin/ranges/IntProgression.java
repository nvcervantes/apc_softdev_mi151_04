package kotlin.ranges;

import kotlin.Metadata;
import kotlin.collections.IntIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\002\020\034\n\002\020\b\n\002\b\013\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\030\002\n\000\n\002\020\016\n\002\b\002\b\026\030\000 \0272\b\022\004\022\0020\0020\001:\001\027B\037\b\000\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002\022\006\020\005\032\0020\002¢\006\002\020\006J\023\020\r\032\0020\0162\b\020\017\032\004\030\0010\020H\002J\b\020\021\032\0020\002H\026J\b\020\022\032\0020\016H\026J\t\020\023\032\0020\024H\002J\b\020\025\032\0020\026H\026R\021\020\007\032\0020\002¢\006\b\n\000\032\004\b\b\020\tR\021\020\n\032\0020\002¢\006\b\n\000\032\004\b\013\020\tR\021\020\005\032\0020\002¢\006\b\n\000\032\004\b\f\020\t¨\006\030"}, d2={"Lkotlin/ranges/IntProgression;", "", "", "start", "endInclusive", "step", "(III)V", "first", "getFirst", "()I", "last", "getLast", "getStep", "equals", "", "other", "", "hashCode", "isEmpty", "iterator", "Lkotlin/collections/IntIterator;", "toString", "", "Companion", "kotlin-runtime"}, k=1, mv={1, 1, 9})
public class IntProgression
  implements Iterable<Integer>, KMappedMarker
{
  public static final Companion Companion = new Companion(null);
  private final int first;
  private final int last;
  private final int step;
  
  public IntProgression(int paramInt1, int paramInt2, int paramInt3)
  {
    if (paramInt3 == 0) {
      throw ((Throwable)new IllegalArgumentException("Step must be non-zero"));
    }
    first = paramInt1;
    last = ProgressionUtilKt.getProgressionLastElement(paramInt1, paramInt2, paramInt3);
    step = paramInt3;
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    if ((paramObject instanceof IntProgression)) {
      if ((!isEmpty()) || (!((IntProgression)paramObject).isEmpty()))
      {
        int i = first;
        paramObject = (IntProgression)paramObject;
        if ((i != first) || (last != last) || (step != step)) {}
      }
      else
      {
        return true;
      }
    }
    return false;
  }
  
  public final int getFirst()
  {
    return first;
  }
  
  public final int getLast()
  {
    return last;
  }
  
  public final int getStep()
  {
    return step;
  }
  
  public int hashCode()
  {
    if (isEmpty()) {
      return -1;
    }
    int i = first;
    int j = last;
    return step + 31 * (i * 31 + j);
  }
  
  public boolean isEmpty()
  {
    int i = step;
    boolean bool = false;
    if (i > 0 ? first > last : first < last) {
      bool = true;
    }
    return bool;
  }
  
  @NotNull
  public IntIterator iterator()
  {
    return (IntIterator)new IntProgressionIterator(first, last, step);
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder;
    if (step > 0)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("");
      localStringBuilder.append(first);
      localStringBuilder.append("..");
      localStringBuilder.append(last);
      localStringBuilder.append(" step ");
    }
    for (int i = step;; i = -step)
    {
      localStringBuilder.append(i);
      return localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("");
      localStringBuilder.append(first);
      localStringBuilder.append(" downTo ");
      localStringBuilder.append(last);
      localStringBuilder.append(" step ");
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\b\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\036\020\003\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\0062\006\020\b\032\0020\006¨\006\t"}, d2={"Lkotlin/ranges/IntProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/IntProgression;", "rangeStart", "", "rangeEnd", "step", "kotlin-runtime"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    @NotNull
    public final IntProgression fromClosedRange(int paramInt1, int paramInt2, int paramInt3)
    {
      return new IntProgression(paramInt1, paramInt2, paramInt3);
    }
  }
}

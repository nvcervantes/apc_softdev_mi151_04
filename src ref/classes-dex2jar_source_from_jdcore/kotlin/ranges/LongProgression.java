package kotlin.ranges;

import kotlin.Metadata;
import kotlin.collections.LongIterator;
import kotlin.internal.ProgressionUtilKt;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\002\020\034\n\002\020\t\n\002\b\013\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\002\b\002\b\026\030\000 \0302\b\022\004\022\0020\0020\001:\001\030B\037\b\000\022\006\020\003\032\0020\002\022\006\020\004\032\0020\002\022\006\020\005\032\0020\002¢\006\002\020\006J\023\020\r\032\0020\0162\b\020\017\032\004\030\0010\020H\002J\b\020\021\032\0020\022H\026J\b\020\023\032\0020\016H\026J\t\020\024\032\0020\025H\002J\b\020\026\032\0020\027H\026R\021\020\007\032\0020\002¢\006\b\n\000\032\004\b\b\020\tR\021\020\n\032\0020\002¢\006\b\n\000\032\004\b\013\020\tR\021\020\005\032\0020\002¢\006\b\n\000\032\004\b\f\020\t¨\006\031"}, d2={"Lkotlin/ranges/LongProgression;", "", "", "start", "endInclusive", "step", "(JJJ)V", "first", "getFirst", "()J", "last", "getLast", "getStep", "equals", "", "other", "", "hashCode", "", "isEmpty", "iterator", "Lkotlin/collections/LongIterator;", "toString", "", "Companion", "kotlin-runtime"}, k=1, mv={1, 1, 9})
public class LongProgression
  implements Iterable<Long>, KMappedMarker
{
  public static final Companion Companion = new Companion(null);
  private final long first;
  private final long last;
  private final long step;
  
  public LongProgression(long paramLong1, long paramLong2, long paramLong3)
  {
    if (paramLong3 == 0L) {
      throw ((Throwable)new IllegalArgumentException("Step must be non-zero"));
    }
    first = paramLong1;
    last = ProgressionUtilKt.getProgressionLastElement(paramLong1, paramLong2, paramLong3);
    step = paramLong3;
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    if ((paramObject instanceof LongProgression)) {
      if ((!isEmpty()) || (!((LongProgression)paramObject).isEmpty()))
      {
        long l = first;
        paramObject = (LongProgression)paramObject;
        if ((l != first) || (last != last) || (step != step)) {}
      }
      else
      {
        return true;
      }
    }
    return false;
  }
  
  public final long getFirst()
  {
    return first;
  }
  
  public final long getLast()
  {
    return last;
  }
  
  public final long getStep()
  {
    return step;
  }
  
  public int hashCode()
  {
    if (isEmpty()) {
      return -1;
    }
    long l = 31;
    return (int)(l * ((first ^ first >>> 32) * l + (last ^ last >>> 32)) + (step ^ step >>> 32));
  }
  
  public boolean isEmpty()
  {
    long l = step;
    boolean bool = false;
    if (l > 0L ? first > last : first < last) {
      bool = true;
    }
    return bool;
  }
  
  @NotNull
  public LongIterator iterator()
  {
    return (LongIterator)new LongProgressionIterator(first, last, step);
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder;
    if (step > 0L)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("");
      localStringBuilder.append(first);
      localStringBuilder.append("..");
      localStringBuilder.append(last);
      localStringBuilder.append(" step ");
    }
    for (long l = step;; l = -step)
    {
      localStringBuilder.append(l);
      return localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("");
      localStringBuilder.append(first);
      localStringBuilder.append(" downTo ");
      localStringBuilder.append(last);
      localStringBuilder.append(" step ");
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\t\n\002\b\003\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\036\020\003\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\0062\006\020\b\032\0020\006¨\006\t"}, d2={"Lkotlin/ranges/LongProgression$Companion;", "", "()V", "fromClosedRange", "Lkotlin/ranges/LongProgression;", "rangeStart", "", "rangeEnd", "step", "kotlin-runtime"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    @NotNull
    public final LongProgression fromClosedRange(long paramLong1, long paramLong2, long paramLong3)
    {
      return new LongProgression(paramLong1, paramLong2, paramLong3);
    }
  }
}

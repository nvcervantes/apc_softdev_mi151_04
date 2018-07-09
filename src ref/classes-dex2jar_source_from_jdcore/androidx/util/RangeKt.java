package androidx.util;

import android.support.annotation.RequiresApi;
import android.util.Range;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.ClosedRange;
import kotlin.ranges.ClosedRange.DefaultImpls;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\030\n\000\n\002\030\002\n\000\n\002\020\017\n\002\b\b\n\002\030\002\n\002\b\002\0327\020\000\032\b\022\004\022\002H\0020\001\"\016\b\000\020\002*\b\022\004\022\002H\0020\003*\b\022\004\022\002H\0020\0012\f\020\004\032\b\022\004\022\002H\0020\001H\f\0326\020\005\032\b\022\004\022\002H\0020\001\"\016\b\000\020\002*\b\022\004\022\002H\0020\003*\b\022\004\022\002H\0020\0012\006\020\006\032\002H\002H\n¢\006\002\020\007\0327\020\005\032\b\022\004\022\002H\0020\001\"\016\b\000\020\002*\b\022\004\022\002H\0020\003*\b\022\004\022\002H\0020\0012\f\020\004\032\b\022\004\022\002H\0020\001H\n\0320\020\b\032\b\022\004\022\002H\0020\001\"\016\b\000\020\002*\b\022\004\022\002H\0020\003*\002H\0022\006\020\t\032\002H\002H\f¢\006\002\020\n\032(\020\013\032\b\022\004\022\002H\0020\f\"\016\b\000\020\002*\b\022\004\022\002H\0020\003*\b\022\004\022\002H\0020\001H\007\032(\020\r\032\b\022\004\022\002H\0020\001\"\016\b\000\020\002*\b\022\004\022\002H\0020\003*\b\022\004\022\002H\0020\fH\007¨\006\016"}, d2={"and", "Landroid/util/Range;", "T", "", "other", "plus", "value", "(Landroid/util/Range;Ljava/lang/Comparable;)Landroid/util/Range;", "rangeTo", "that", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Landroid/util/Range;", "toClosedRange", "Lkotlin/ranges/ClosedRange;", "toRange", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class RangeKt
{
  @RequiresApi(21)
  @NotNull
  public static final <T extends Comparable<? super T>> Range<T> and(@NotNull Range<T> paramRange1, @NotNull Range<T> paramRange2)
  {
    paramRange1 = paramRange1.intersect(paramRange2);
    Intrinsics.checkExpressionValueIsNotNull(paramRange1, "intersect(other)");
    return paramRange1;
  }
  
  @RequiresApi(21)
  @NotNull
  public static final <T extends Comparable<? super T>> Range<T> plus(@NotNull Range<T> paramRange1, @NotNull Range<T> paramRange2)
  {
    paramRange1 = paramRange1.extend(paramRange2);
    Intrinsics.checkExpressionValueIsNotNull(paramRange1, "extend(other)");
    return paramRange1;
  }
  
  @RequiresApi(21)
  @NotNull
  public static final <T extends Comparable<? super T>> Range<T> plus(@NotNull Range<T> paramRange, @NotNull T paramT)
  {
    paramRange = paramRange.extend(paramT);
    Intrinsics.checkExpressionValueIsNotNull(paramRange, "extend(value)");
    return paramRange;
  }
  
  @RequiresApi(21)
  @NotNull
  public static final <T extends Comparable<? super T>> Range<T> rangeTo(@NotNull T paramT1, @NotNull T paramT2)
  {
    return new Range(paramT1, paramT2);
  }
  
  @RequiresApi(21)
  @NotNull
  public static final <T extends Comparable<? super T>> ClosedRange<T> toClosedRange(@NotNull Range<T> paramRange)
  {
    (ClosedRange)new ClosedRange()
    {
      public boolean contains(@NotNull T paramAnonymousT)
      {
        return ClosedRange.DefaultImpls.contains(this, paramAnonymousT);
      }
      
      public T getEndInclusive()
      {
        return receiver$0.getUpper();
      }
      
      public T getStart()
      {
        return receiver$0.getLower();
      }
      
      public boolean isEmpty()
      {
        return ClosedRange.DefaultImpls.isEmpty(this);
      }
    };
  }
  
  @RequiresApi(21)
  @NotNull
  public static final <T extends Comparable<? super T>> Range<T> toRange(@NotNull ClosedRange<T> paramClosedRange)
  {
    return new Range(paramClosedRange.getStart(), paramClosedRange.getEndInclusive());
  }
}

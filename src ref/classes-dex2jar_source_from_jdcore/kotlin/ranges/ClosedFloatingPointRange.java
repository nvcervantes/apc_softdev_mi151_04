package kotlin.ranges;

import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\000\n\002\020\017\n\002\030\002\n\000\n\002\020\013\n\002\b\b\bg\030\000*\016\b\000\020\001*\b\022\004\022\002H\0010\0022\b\022\004\022\002H\0010\003J\026\020\004\032\0020\0052\006\020\006\032\0028\000H\002¢\006\002\020\007J\b\020\b\032\0020\005H\026J\035\020\t\032\0020\0052\006\020\n\032\0028\0002\006\020\013\032\0028\000H&¢\006\002\020\f¨\006\r"}, d2={"Lkotlin/ranges/ClosedFloatingPointRange;", "T", "", "Lkotlin/ranges/ClosedRange;", "contains", "", "value", "(Ljava/lang/Comparable;)Z", "isEmpty", "lessThanOrEquals", "a", "b", "(Ljava/lang/Comparable;Ljava/lang/Comparable;)Z", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
@SinceKotlin(version="1.1")
public abstract interface ClosedFloatingPointRange<T extends Comparable<? super T>>
  extends ClosedRange<T>
{
  public abstract boolean contains(@NotNull T paramT);
  
  public abstract boolean isEmpty();
  
  public abstract boolean lessThanOrEquals(@NotNull T paramT1, @NotNull T paramT2);
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 9})
  public static final class DefaultImpls
  {
    public static <T extends Comparable<? super T>> boolean contains(ClosedFloatingPointRange<T> paramClosedFloatingPointRange, @NotNull T paramT)
    {
      Intrinsics.checkParameterIsNotNull(paramT, "value");
      return (paramClosedFloatingPointRange.lessThanOrEquals(paramClosedFloatingPointRange.getStart(), paramT)) && (paramClosedFloatingPointRange.lessThanOrEquals(paramT, paramClosedFloatingPointRange.getEndInclusive()));
    }
    
    public static <T extends Comparable<? super T>> boolean isEmpty(ClosedFloatingPointRange<T> paramClosedFloatingPointRange)
    {
      return paramClosedFloatingPointRange.lessThanOrEquals(paramClosedFloatingPointRange.getStart(), paramClosedFloatingPointRange.getEndInclusive()) ^ true;
    }
  }
}

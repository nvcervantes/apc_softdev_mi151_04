package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000 \n\002\030\002\n\002\030\002\n\002\020\017\n\002\020\000\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\004\bÂ\002\030\0002\036\022\n\022\b\022\004\022\0020\0030\0020\001j\016\022\n\022\b\022\004\022\0020\0030\002`\004B\007\b\002¢\006\002\020\005J$\020\006\032\0020\0072\f\020\b\032\b\022\004\022\0020\0030\0022\f\020\t\032\b\022\004\022\0020\0030\002H\026J$\020\n\032\036\022\n\022\b\022\004\022\0020\0030\0020\001j\016\022\n\022\b\022\004\022\0020\0030\002`\004H\007¨\006\013"}, d2={"Lkotlin/comparisons/ReverseOrderComparator;", "Ljava/util/Comparator;", "", "", "Lkotlin/Comparator;", "()V", "compare", "", "a", "b", "reversed", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
final class ReverseOrderComparator
  implements Comparator<Comparable<? super Object>>
{
  public static final ReverseOrderComparator INSTANCE = new ReverseOrderComparator();
  
  private ReverseOrderComparator() {}
  
  public int compare(@NotNull Comparable<Object> paramComparable1, @NotNull Comparable<Object> paramComparable2)
  {
    Intrinsics.checkParameterIsNotNull(paramComparable1, "a");
    Intrinsics.checkParameterIsNotNull(paramComparable2, "b");
    return paramComparable2.compareTo(paramComparable1);
  }
  
  @NotNull
  public final Comparator<Comparable<Object>> reversed()
  {
    return (Comparator)NaturalOrderComparator.INSTANCE;
  }
}

package kotlin.comparisons;

import java.util.Comparator;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\005\n\002\020\b\n\002\b\005\b\002\030\000*\004\b\000\020\0012\022\022\004\022\002H\0010\002j\b\022\004\022\002H\001`\003B\035\022\026\020\004\032\022\022\004\022\0028\0000\002j\b\022\004\022\0028\000`\003¢\006\002\020\005J\035\020\b\032\0020\t2\006\020\n\032\0028\0002\006\020\013\032\0028\000H\026¢\006\002\020\fJ\030\020\r\032\022\022\004\022\0028\0000\002j\b\022\004\022\0028\000`\003H\007R!\020\004\032\022\022\004\022\0028\0000\002j\b\022\004\022\0028\000`\003¢\006\b\n\000\032\004\b\006\020\007¨\006\016"}, d2={"Lkotlin/comparisons/ReversedComparator;", "T", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "comparator", "(Ljava/util/Comparator;)V", "getComparator", "()Ljava/util/Comparator;", "compare", "", "a", "b", "(Ljava/lang/Object;Ljava/lang/Object;)I", "reversed", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
final class ReversedComparator<T>
  implements Comparator<T>
{
  @NotNull
  private final Comparator<T> comparator;
  
  public ReversedComparator(@NotNull Comparator<T> paramComparator)
  {
    comparator = paramComparator;
  }
  
  public int compare(T paramT1, T paramT2)
  {
    return comparator.compare(paramT2, paramT1);
  }
  
  @NotNull
  public final Comparator<T> getComparator()
  {
    return comparator;
  }
  
  @NotNull
  public final Comparator<T> reversed()
  {
    return comparator;
  }
}

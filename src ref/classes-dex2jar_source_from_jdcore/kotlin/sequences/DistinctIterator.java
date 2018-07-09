package kotlin.sequences;

import java.util.HashSet;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.collections.AbstractIterator;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020(\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\000\n\002\020\002\n\000\b\002\030\000*\004\b\000\020\001*\004\b\001\020\0022\b\022\004\022\002H\0010\003B'\022\f\020\004\032\b\022\004\022\0028\0000\005\022\022\020\006\032\016\022\004\022\0028\000\022\004\022\0028\0010\007¢\006\002\020\bJ\b\020\f\032\0020\rH\024R\032\020\006\032\016\022\004\022\0028\000\022\004\022\0028\0010\007X\004¢\006\002\n\000R\036\020\t\032\022\022\004\022\0028\0010\nj\b\022\004\022\0028\001`\013X\004¢\006\002\n\000R\024\020\004\032\b\022\004\022\0028\0000\005X\004¢\006\002\n\000¨\006\016"}, d2={"Lkotlin/sequences/DistinctIterator;", "T", "K", "Lkotlin/collections/AbstractIterator;", "source", "", "keySelector", "Lkotlin/Function1;", "(Ljava/util/Iterator;Lkotlin/jvm/functions/Function1;)V", "observed", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "computeNext", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
final class DistinctIterator<T, K>
  extends AbstractIterator<T>
{
  private final Function1<T, K> keySelector;
  private final HashSet<K> observed;
  private final Iterator<T> source;
  
  public DistinctIterator(@NotNull Iterator<? extends T> paramIterator, @NotNull Function1<? super T, ? extends K> paramFunction1)
  {
    source = paramIterator;
    keySelector = paramFunction1;
    observed = new HashSet();
  }
  
  protected void computeNext()
  {
    while (source.hasNext())
    {
      Object localObject1 = source.next();
      Object localObject2 = keySelector.invoke(localObject1);
      if (observed.add(localObject2))
      {
        setNext(localObject1);
        return;
      }
    }
    done();
  }
}

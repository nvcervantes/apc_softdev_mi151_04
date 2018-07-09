package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\020\b\n\002\b\002\n\002\020(\n\000\b\000\030\000*\004\b\000\020\001*\004\b\001\020\0022\b\022\004\022\002H\0020\003B-\022\f\020\004\032\b\022\004\022\0028\0000\003\022\030\020\005\032\024\022\004\022\0020\007\022\004\022\0028\000\022\004\022\0028\0010\006¢\006\002\020\bJ\017\020\t\032\b\022\004\022\0028\0010\nH\002R\024\020\004\032\b\022\004\022\0028\0000\003X\004¢\006\002\n\000R \020\005\032\024\022\004\022\0020\007\022\004\022\0028\000\022\004\022\0028\0010\006X\004¢\006\002\n\000¨\006\013"}, d2={"Lkotlin/sequences/TransformingIndexedSequence;", "T", "R", "Lkotlin/sequences/Sequence;", "sequence", "transformer", "Lkotlin/Function2;", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function2;)V", "iterator", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public final class TransformingIndexedSequence<T, R>
  implements Sequence<R>
{
  private final Sequence<T> sequence;
  private final Function2<Integer, T, R> transformer;
  
  public TransformingIndexedSequence(@NotNull Sequence<? extends T> paramSequence, @NotNull Function2<? super Integer, ? super T, ? extends R> paramFunction2)
  {
    sequence = paramSequence;
    transformer = paramFunction2;
  }
  
  @NotNull
  public Iterator<R> iterator()
  {
    (Iterator)new Iterator()
    {
      private int index;
      @NotNull
      private final Iterator<T> iterator;
      
      public final int getIndex()
      {
        return index;
      }
      
      @NotNull
      public final Iterator<T> getIterator()
      {
        return iterator;
      }
      
      public boolean hasNext()
      {
        return iterator.hasNext();
      }
      
      public R next()
      {
        Function2 localFunction2 = TransformingIndexedSequence.access$getTransformer$p(this$0);
        int i = index;
        index = (i + 1);
        return localFunction2.invoke(Integer.valueOf(i), iterator.next());
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
      
      public final void setIndex(int paramAnonymousInt)
      {
        index = paramAnonymousInt;
      }
    };
  }
}

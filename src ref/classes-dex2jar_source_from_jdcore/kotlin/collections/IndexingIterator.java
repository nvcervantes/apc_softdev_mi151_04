package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000 \n\002\030\002\n\000\n\002\020(\n\002\030\002\n\002\b\003\n\002\020\b\n\000\n\002\020\013\n\002\b\002\b\000\030\000*\006\b\000\020\001 \0012\016\022\n\022\b\022\004\022\002H\0010\0030\002B\023\022\f\020\004\032\b\022\004\022\0028\0000\002¢\006\002\020\005J\t\020\b\032\0020\tH\002J\017\020\n\032\b\022\004\022\0028\0000\003H\002R\016\020\006\032\0020\007X\016¢\006\002\n\000R\024\020\004\032\b\022\004\022\0028\0000\002X\004¢\006\002\n\000¨\006\013"}, d2={"Lkotlin/collections/IndexingIterator;", "T", "", "Lkotlin/collections/IndexedValue;", "iterator", "(Ljava/util/Iterator;)V", "index", "", "hasNext", "", "next", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public final class IndexingIterator<T>
  implements Iterator<IndexedValue<? extends T>>, KMappedMarker
{
  private int index;
  private final Iterator<T> iterator;
  
  public IndexingIterator(@NotNull Iterator<? extends T> paramIterator)
  {
    iterator = paramIterator;
  }
  
  public final boolean hasNext()
  {
    return iterator.hasNext();
  }
  
  @NotNull
  public final IndexedValue<T> next()
  {
    int i = index;
    index = (i + 1);
    return new IndexedValue(i, iterator.next());
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Operation is not supported for read-only collection");
  }
}

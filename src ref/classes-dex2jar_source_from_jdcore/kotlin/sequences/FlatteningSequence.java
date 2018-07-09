package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\036\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\020(\n\002\b\002\b\000\030\000*\004\b\000\020\001*\004\b\001\020\002*\004\b\002\020\0032\b\022\004\022\002H\0030\004BA\022\f\020\005\032\b\022\004\022\0028\0000\004\022\022\020\006\032\016\022\004\022\0028\000\022\004\022\0028\0010\007\022\030\020\b\032\024\022\004\022\0028\001\022\n\022\b\022\004\022\0028\0020\t0\007¢\006\002\020\nJ\017\020\b\032\b\022\004\022\0028\0020\tH\002R \020\b\032\024\022\004\022\0028\001\022\n\022\b\022\004\022\0028\0020\t0\007X\004¢\006\002\n\000R\024\020\005\032\b\022\004\022\0028\0000\004X\004¢\006\002\n\000R\032\020\006\032\016\022\004\022\0028\000\022\004\022\0028\0010\007X\004¢\006\002\n\000¨\006\013"}, d2={"Lkotlin/sequences/FlatteningSequence;", "T", "R", "E", "Lkotlin/sequences/Sequence;", "sequence", "transformer", "Lkotlin/Function1;", "iterator", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;Lkotlin/jvm/functions/Function1;)V", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public final class FlatteningSequence<T, R, E>
  implements Sequence<E>
{
  private final Function1<R, Iterator<E>> iterator;
  private final Sequence<T> sequence;
  private final Function1<T, R> transformer;
  
  public FlatteningSequence(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, ? extends R> paramFunction1, @NotNull Function1<? super R, ? extends Iterator<? extends E>> paramFunction11)
  {
    sequence = paramSequence;
    transformer = paramFunction1;
    iterator = paramFunction11;
  }
  
  @NotNull
  public Iterator<E> iterator()
  {
    (Iterator)new Iterator()
    {
      @Nullable
      private Iterator<? extends E> itemIterator;
      @NotNull
      private final Iterator<T> iterator;
      
      private final boolean ensureItemIterator()
      {
        Object localObject = itemIterator;
        if ((localObject != null) && (!((Iterator)localObject).hasNext())) {
          itemIterator = ((Iterator)null);
        }
        while (itemIterator == null)
        {
          if (!iterator.hasNext()) {
            return false;
          }
          localObject = iterator.next();
          localObject = (Iterator)FlatteningSequence.access$getIterator$p(this$0).invoke(FlatteningSequence.access$getTransformer$p(this$0).invoke(localObject));
          if (((Iterator)localObject).hasNext())
          {
            itemIterator = ((Iterator)localObject);
            return true;
          }
        }
        return true;
      }
      
      @Nullable
      public final Iterator<E> getItemIterator()
      {
        return itemIterator;
      }
      
      @NotNull
      public final Iterator<T> getIterator()
      {
        return iterator;
      }
      
      public boolean hasNext()
      {
        return ensureItemIterator();
      }
      
      public E next()
      {
        if (!ensureItemIterator()) {
          throw ((Throwable)new NoSuchElementException());
        }
        Iterator localIterator = itemIterator;
        if (localIterator == null) {
          Intrinsics.throwNpe();
        }
        return localIterator.next();
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
      
      public final void setItemIterator(@Nullable Iterator<? extends E> paramAnonymousIterator)
      {
        itemIterator = paramAnonymousIterator;
      }
    };
  }
}

package kotlin.sequences;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000 \n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\020\013\n\002\b\002\n\002\020(\n\000\b\000\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\002B'\022\f\020\003\032\b\022\004\022\0028\0000\002\022\022\020\004\032\016\022\004\022\0028\000\022\004\022\0020\0060\005¢\006\002\020\007J\017\020\b\032\b\022\004\022\0028\0000\tH\002R\032\020\004\032\016\022\004\022\0028\000\022\004\022\0020\0060\005X\004¢\006\002\n\000R\024\020\003\032\b\022\004\022\0028\0000\002X\004¢\006\002\n\000¨\006\n"}, d2={"Lkotlin/sequences/DropWhileSequence;", "T", "Lkotlin/sequences/Sequence;", "sequence", "predicate", "Lkotlin/Function1;", "", "(Lkotlin/sequences/Sequence;Lkotlin/jvm/functions/Function1;)V", "iterator", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public final class DropWhileSequence<T>
  implements Sequence<T>
{
  private final Function1<T, Boolean> predicate;
  private final Sequence<T> sequence;
  
  public DropWhileSequence(@NotNull Sequence<? extends T> paramSequence, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    sequence = paramSequence;
    predicate = paramFunction1;
  }
  
  @NotNull
  public Iterator<T> iterator()
  {
    (Iterator)new Iterator()
    {
      private int dropState;
      @NotNull
      private final Iterator<T> iterator;
      @Nullable
      private T nextItem;
      
      private final void drop()
      {
        while (iterator.hasNext())
        {
          Object localObject = iterator.next();
          if (!((Boolean)DropWhileSequence.access$getPredicate$p(this$0).invoke(localObject)).booleanValue())
          {
            nextItem = localObject;
            dropState = 1;
            return;
          }
        }
        dropState = 0;
      }
      
      public final int getDropState()
      {
        return dropState;
      }
      
      @NotNull
      public final Iterator<T> getIterator()
      {
        return iterator;
      }
      
      @Nullable
      public final T getNextItem()
      {
        return nextItem;
      }
      
      public boolean hasNext()
      {
        if (dropState == -1) {
          drop();
        }
        int i = dropState;
        boolean bool = true;
        if (i != 1)
        {
          if (iterator.hasNext()) {
            return true;
          }
          bool = false;
        }
        return bool;
      }
      
      public T next()
      {
        if (dropState == -1) {
          drop();
        }
        if (dropState == 1)
        {
          Object localObject = nextItem;
          nextItem = null;
          dropState = 0;
          return localObject;
        }
        return iterator.next();
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
      
      public final void setDropState(int paramAnonymousInt)
      {
        dropState = paramAnonymousInt;
      }
      
      public final void setNextItem(@Nullable T paramAnonymousT)
      {
        nextItem = paramAnonymousT;
      }
    };
  }
}

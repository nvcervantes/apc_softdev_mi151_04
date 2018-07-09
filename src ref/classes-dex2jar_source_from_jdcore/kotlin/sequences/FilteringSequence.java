package kotlin.sequences;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\000\n\002\030\002\n\002\b\002\n\002\020(\n\000\b\000\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\002B1\022\f\020\003\032\b\022\004\022\0028\0000\002\022\b\b\002\020\004\032\0020\005\022\022\020\006\032\016\022\004\022\0028\000\022\004\022\0020\0050\007¢\006\002\020\bJ\017\020\t\032\b\022\004\022\0028\0000\nH\002R\032\020\006\032\016\022\004\022\0028\000\022\004\022\0020\0050\007X\004¢\006\002\n\000R\016\020\004\032\0020\005X\004¢\006\002\n\000R\024\020\003\032\b\022\004\022\0028\0000\002X\004¢\006\002\n\000¨\006\013"}, d2={"Lkotlin/sequences/FilteringSequence;", "T", "Lkotlin/sequences/Sequence;", "sequence", "sendWhen", "", "predicate", "Lkotlin/Function1;", "(Lkotlin/sequences/Sequence;ZLkotlin/jvm/functions/Function1;)V", "iterator", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public final class FilteringSequence<T>
  implements Sequence<T>
{
  private final Function1<T, Boolean> predicate;
  private final boolean sendWhen;
  private final Sequence<T> sequence;
  
  public FilteringSequence(@NotNull Sequence<? extends T> paramSequence, boolean paramBoolean, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    sequence = paramSequence;
    sendWhen = paramBoolean;
    predicate = paramFunction1;
  }
  
  @NotNull
  public Iterator<T> iterator()
  {
    (Iterator)new Iterator()
    {
      @NotNull
      private final Iterator<T> iterator;
      @Nullable
      private T nextItem;
      private int nextState;
      
      private final void calcNext()
      {
        while (iterator.hasNext())
        {
          Object localObject = iterator.next();
          if (((Boolean)FilteringSequence.access$getPredicate$p(this$0).invoke(localObject)).booleanValue() == FilteringSequence.access$getSendWhen$p(this$0))
          {
            nextItem = localObject;
            nextState = 1;
            return;
          }
        }
        nextState = 0;
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
      
      public final int getNextState()
      {
        return nextState;
      }
      
      public boolean hasNext()
      {
        if (nextState == -1) {
          calcNext();
        }
        return nextState == 1;
      }
      
      public T next()
      {
        if (nextState == -1) {
          calcNext();
        }
        if (nextState == 0) {
          throw ((Throwable)new NoSuchElementException());
        }
        Object localObject = nextItem;
        nextItem = null;
        nextState = -1;
        return localObject;
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
      
      public final void setNextItem(@Nullable T paramAnonymousT)
      {
        nextItem = paramAnonymousT;
      }
      
      public final void setNextState(int paramAnonymousInt)
      {
        nextState = paramAnonymousInt;
      }
    };
  }
}

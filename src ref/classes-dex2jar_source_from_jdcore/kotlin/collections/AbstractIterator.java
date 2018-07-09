package kotlin.collections;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;

@Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\000\n\002\020(\n\002\b\004\n\002\030\002\n\000\n\002\020\002\n\002\b\002\n\002\020\013\n\002\b\007\b&\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\002B\005¢\006\002\020\003J\b\020\b\032\0020\tH$J\b\020\n\032\0020\tH\004J\t\020\013\032\0020\fH\002J\016\020\r\032\0028\000H\002¢\006\002\020\016J\025\020\017\032\0020\t2\006\020\020\032\0028\000H\004¢\006\002\020\021J\b\020\022\032\0020\fH\002R\022\020\004\032\004\030\0018\000X\016¢\006\004\n\002\020\005R\016\020\006\032\0020\007X\016¢\006\002\n\000¨\006\023"}, d2={"Lkotlin/collections/AbstractIterator;", "T", "", "()V", "nextValue", "Ljava/lang/Object;", "state", "Lkotlin/collections/State;", "computeNext", "", "done", "hasNext", "", "next", "()Ljava/lang/Object;", "setNext", "value", "(Ljava/lang/Object;)V", "tryToComputeNext", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
public abstract class AbstractIterator<T>
  implements Iterator<T>, KMappedMarker
{
  private T nextValue;
  private State state = State.NotReady;
  
  public AbstractIterator() {}
  
  private final boolean tryToComputeNext()
  {
    state = State.Failed;
    computeNext();
    return state == State.Ready;
  }
  
  protected abstract void computeNext();
  
  protected final void done()
  {
    state = State.Done;
  }
  
  public boolean hasNext()
  {
    State localState1 = state;
    State localState2 = State.Failed;
    boolean bool = false;
    int i;
    if (localState1 != localState2) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalArgumentException("Failed requirement.".toString()));
    }
    localState1 = state;
    switch (AbstractIterator.WhenMappings.$EnumSwitchMapping$0[localState1.ordinal()])
    {
    default: 
      return tryToComputeNext();
    case 2: 
      bool = true;
    }
    return bool;
  }
  
  public T next()
  {
    if (!hasNext()) {
      throw ((Throwable)new NoSuchElementException());
    }
    state = State.NotReady;
    return nextValue;
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Operation is not supported for read-only collection");
  }
  
  protected final void setNext(T paramT)
  {
    nextValue = paramT;
    state = State.Ready;
  }
}

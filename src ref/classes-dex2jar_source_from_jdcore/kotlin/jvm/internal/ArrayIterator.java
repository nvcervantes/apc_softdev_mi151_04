package kotlin.jvm.internal;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\000\n\002\020(\n\000\n\002\020\021\n\002\b\005\n\002\020\b\n\000\n\002\020\013\n\002\b\003\b\002\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\002B\023\022\f\020\003\032\b\022\004\022\0028\0000\004¢\006\002\020\005J\t\020\013\032\0020\fH\002J\016\020\r\032\0028\000H\002¢\006\002\020\016R\031\020\003\032\b\022\004\022\0028\0000\004¢\006\n\n\002\020\b\032\004\b\006\020\007R\016\020\t\032\0020\nX\016¢\006\002\n\000¨\006\017"}, d2={"Lkotlin/jvm/internal/ArrayIterator;", "T", "", "array", "", "([Ljava/lang/Object;)V", "getArray", "()[Ljava/lang/Object;", "[Ljava/lang/Object;", "index", "", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-runtime"}, k=1, mv={1, 1, 9})
final class ArrayIterator<T>
  implements Iterator<T>, KMappedMarker
{
  @NotNull
  private final T[] array;
  private int index;
  
  public ArrayIterator(@NotNull T[] paramArrayOfT)
  {
    array = paramArrayOfT;
  }
  
  @NotNull
  public final T[] getArray()
  {
    return array;
  }
  
  public boolean hasNext()
  {
    return index < array.length;
  }
  
  public T next()
  {
    try
    {
      Object localObject = array;
      int i = index;
      index = (i + 1);
      localObject = localObject[i];
      return localObject;
    }
    catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
    {
      index -= 1;
      throw ((Throwable)new NoSuchElementException(localArrayIndexOutOfBoundsException.getMessage()));
    }
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Operation is not supported for read-only collection");
  }
}

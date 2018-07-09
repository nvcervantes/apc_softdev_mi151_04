package kotlin.jvm.internal;

import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.collections.FloatIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\002\030\002\n\000\n\002\020\024\n\002\b\002\n\002\020\b\n\000\n\002\020\013\n\000\n\002\020\007\n\000\b\002\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\t\020\007\032\0020\bH\002J\b\020\t\032\0020\nH\026R\016\020\002\032\0020\003X\004¢\006\002\n\000R\016\020\005\032\0020\006X\016¢\006\002\n\000¨\006\013"}, d2={"Lkotlin/jvm/internal/ArrayFloatIterator;", "Lkotlin/collections/FloatIterator;", "array", "", "([F)V", "index", "", "hasNext", "", "nextFloat", "", "kotlin-runtime"}, k=1, mv={1, 1, 9})
final class ArrayFloatIterator
  extends FloatIterator
{
  private final float[] array;
  private int index;
  
  public ArrayFloatIterator(@NotNull float[] paramArrayOfFloat)
  {
    array = paramArrayOfFloat;
  }
  
  public boolean hasNext()
  {
    return index < array.length;
  }
  
  public float nextFloat()
  {
    try
    {
      float[] arrayOfFloat = array;
      int i = index;
      index = (i + 1);
      float f = arrayOfFloat[i];
      return f;
    }
    catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
    {
      index -= 1;
      throw ((Throwable)new NoSuchElementException(localArrayIndexOutOfBoundsException.getMessage()));
    }
  }
}

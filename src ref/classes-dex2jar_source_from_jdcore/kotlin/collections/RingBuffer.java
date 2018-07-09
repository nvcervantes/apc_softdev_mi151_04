package kotlin.collections;

import java.util.Arrays;
import java.util.Iterator;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000>\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\002\b\002\n\002\020\021\n\002\020\000\n\002\b\t\n\002\020\002\n\002\b\006\n\002\020\013\n\000\n\002\020(\n\002\b\f\b\002\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\0022\0060\003j\002`\004B\r\022\006\020\005\032\0020\006¢\006\002\020\007J\023\020\023\032\0020\0242\006\020\025\032\0028\000¢\006\002\020\026J\026\020\027\032\0028\0002\006\020\030\032\0020\006H\002¢\006\002\020\031J\006\020\032\032\0020\033J\017\020\034\032\b\022\004\022\0028\0000\035H\002J\016\020\036\032\0020\0242\006\020\037\032\0020\006J\025\020 \032\n\022\006\022\004\030\0010\n0\tH\024¢\006\002\020!J'\020 \032\b\022\004\022\002H\0010\t\"\004\b\001\020\0012\f\020\"\032\b\022\004\022\002H\0010\tH\025¢\006\002\020#J9\020$\032\0020\024\"\004\b\001\020\001*\b\022\004\022\002H\0010\t2\006\020\025\032\002H\0012\b\b\002\020%\032\0020\0062\b\b\002\020&\032\0020\006H\002¢\006\002\020'J\025\020(\032\0020\006*\0020\0062\006\020\037\032\0020\006H\bR\030\020\b\032\n\022\006\022\004\030\0010\n0\tX\004¢\006\004\n\002\020\013R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\f\020\rR$\020\017\032\0020\0062\006\020\016\032\0020\006@RX\016¢\006\016\n\000\032\004\b\020\020\r\"\004\b\021\020\007R\016\020\022\032\0020\006X\016¢\006\002\n\000¨\006)"}, d2={"Lkotlin/collections/RingBuffer;", "T", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "capacity", "", "(I)V", "buffer", "", "", "[Ljava/lang/Object;", "getCapacity", "()I", "<set-?>", "size", "getSize", "setSize", "startIndex", "add", "", "element", "(Ljava/lang/Object;)V", "get", "index", "(I)Ljava/lang/Object;", "isFull", "", "iterator", "", "removeFirst", "n", "toArray", "()[Ljava/lang/Object;", "array", "([Ljava/lang/Object;)[Ljava/lang/Object;", "fill", "fromIndex", "toIndex", "([Ljava/lang/Object;Ljava/lang/Object;II)V", "forward", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
final class RingBuffer<T>
  extends AbstractList<T>
  implements RandomAccess
{
  private final Object[] buffer;
  private final int capacity;
  private int size;
  private int startIndex;
  
  public RingBuffer(int paramInt)
  {
    capacity = paramInt;
    if (capacity >= 0) {
      paramInt = 1;
    } else {
      paramInt = 0;
    }
    if (paramInt == 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("ring buffer capacity should not be negative but it is ");
      localStringBuilder.append(capacity);
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString().toString()));
    }
    buffer = new Object[capacity];
  }
  
  private final <T> void fill(@NotNull T[] paramArrayOfT, T paramT, int paramInt1, int paramInt2)
  {
    while (paramInt1 < paramInt2)
    {
      paramArrayOfT[paramInt1] = paramT;
      paramInt1 += 1;
    }
  }
  
  private final int forward(int paramInt1, int paramInt2)
  {
    return (paramInt1 + paramInt2) % getCapacity();
  }
  
  private void setSize(int paramInt)
  {
    size = paramInt;
  }
  
  public final void add(T paramT)
  {
    if (isFull()) {
      throw ((Throwable)new IllegalStateException("ring buffer is full"));
    }
    buffer[((startIndex + size()) % getCapacity())] = paramT;
    setSize(size() + 1);
  }
  
  public T get(int paramInt)
  {
    AbstractList.Companion.checkElementIndex$kotlin_stdlib(paramInt, size());
    return buffer[((startIndex + paramInt) % getCapacity())];
  }
  
  public final int getCapacity()
  {
    return capacity;
  }
  
  public int getSize()
  {
    return size;
  }
  
  public final boolean isFull()
  {
    return size() == capacity;
  }
  
  @NotNull
  public Iterator<T> iterator()
  {
    (Iterator)new AbstractIterator()
    {
      private int count;
      private int index;
      
      protected void computeNext()
      {
        if (count == 0)
        {
          done();
          return;
        }
        setNext(RingBuffer.access$getBuffer$p(this$0)[index]);
        RingBuffer localRingBuffer = this$0;
        index = ((index + 1) % localRingBuffer.getCapacity());
        count -= 1;
      }
    };
  }
  
  public final void removeFirst(int paramInt)
  {
    int j = 1;
    int i;
    if (paramInt >= 0) {
      i = 1;
    } else {
      i = 0;
    }
    StringBuilder localStringBuilder;
    if (i == 0)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("n shouldn't be negative but it is ");
      localStringBuilder.append(paramInt);
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString().toString()));
    }
    if (paramInt <= size()) {
      i = j;
    } else {
      i = 0;
    }
    if (i == 0)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("n shouldn't be greater than the buffer size: n = ");
      localStringBuilder.append(paramInt);
      localStringBuilder.append(", size = ");
      localStringBuilder.append(size());
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString().toString()));
    }
    if (paramInt > 0)
    {
      i = startIndex;
      j = (i + paramInt) % getCapacity();
      if (i > j)
      {
        fill(buffer, null, i, capacity);
        fill(buffer, null, 0, j);
      }
      else
      {
        fill(buffer, null, i, j);
      }
      startIndex = j;
      setSize(size() - paramInt);
    }
  }
  
  @NotNull
  public Object[] toArray()
  {
    return toArray(new Object[size()]);
  }
  
  @NotNull
  public <T> T[] toArray(@NotNull T[] paramArrayOfT)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfT, "array");
    Object localObject = paramArrayOfT;
    if (paramArrayOfT.length < size())
    {
      localObject = Arrays.copyOf(paramArrayOfT, size());
      Intrinsics.checkExpressionValueIsNotNull(localObject, "java.util.Arrays.copyOf(this, newSize)");
    }
    int i1 = size();
    int j = startIndex;
    int n = 0;
    int i = 0;
    int k;
    int m;
    for (;;)
    {
      k = i;
      m = n;
      if (i >= i1) {
        break;
      }
      k = i;
      m = n;
      if (j >= capacity) {
        break;
      }
      localObject[i] = buffer[j];
      i += 1;
      j += 1;
    }
    while (k < i1)
    {
      localObject[k] = buffer[m];
      k += 1;
      m += 1;
    }
    if (localObject.length > size()) {
      localObject[size()] = null;
    }
    if (localObject == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
    return localObject;
  }
}

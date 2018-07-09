package kotlin.collections;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.NoSuchElementException;
import java.util.RandomAccess;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000@\n\002\030\002\n\000\n\002\030\002\n\002\020 \n\002\b\002\n\002\020\b\n\002\b\003\n\002\020\013\n\000\n\002\020\000\n\002\b\006\n\002\030\002\n\002\b\002\n\002\020(\n\002\b\002\n\002\020*\n\002\b\b\b'\030\000 \035*\006\b\000\020\001 \0012\b\022\004\022\002H\0010\0022\b\022\004\022\002H\0010\003:\004\035\036\037 B\007\b\004¢\006\002\020\004J\023\020\t\032\0020\n2\b\020\013\032\004\030\0010\fH\002J\026\020\r\032\0028\0002\006\020\016\032\0020\006H¦\002¢\006\002\020\017J\b\020\020\032\0020\006H\026J\032\020\021\032\0020\0062\013\020\022\032\0078\000¢\006\002\b\023H\026¢\006\002\020\024J\017\020\025\032\b\022\004\022\0028\0000\026H\002J\032\020\027\032\0020\0062\013\020\022\032\0078\000¢\006\002\b\023H\026¢\006\002\020\024J\016\020\030\032\b\022\004\022\0028\0000\031H\026J\026\020\030\032\b\022\004\022\0028\0000\0312\006\020\016\032\0020\006H\026J\036\020\032\032\b\022\004\022\0028\0000\0032\006\020\033\032\0020\0062\006\020\034\032\0020\006H\026R\022\020\005\032\0020\006X¦\004¢\006\006\032\004\b\007\020\b¨\006!"}, d2={"Lkotlin/collections/AbstractList;", "E", "Lkotlin/collections/AbstractCollection;", "", "()V", "size", "", "getSize", "()I", "equals", "", "other", "", "get", "index", "(I)Ljava/lang/Object;", "hashCode", "indexOf", "element", "Lkotlin/UnsafeVariance;", "(Ljava/lang/Object;)I", "iterator", "", "lastIndexOf", "listIterator", "", "subList", "fromIndex", "toIndex", "Companion", "IteratorImpl", "ListIteratorImpl", "SubList", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
@SinceKotlin(version="1.1")
public abstract class AbstractList<E>
  extends AbstractCollection<E>
  implements List<E>, KMappedMarker
{
  public static final Companion Companion = new Companion(null);
  
  protected AbstractList() {}
  
  public void add(int paramInt, E paramE)
  {
    throw new UnsupportedOperationException("Operation is not supported for read-only collection");
  }
  
  public boolean addAll(int paramInt, Collection<? extends E> paramCollection)
  {
    throw new UnsupportedOperationException("Operation is not supported for read-only collection");
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    if (paramObject == (AbstractList)this) {
      return true;
    }
    if (!(paramObject instanceof List)) {
      return false;
    }
    return Companion.orderedEquals$kotlin_stdlib((Collection)this, (Collection)paramObject);
  }
  
  public abstract E get(int paramInt);
  
  public abstract int getSize();
  
  public int hashCode()
  {
    return Companion.orderedHashCode$kotlin_stdlib((Collection)this);
  }
  
  public int indexOf(Object paramObject)
  {
    Iterator localIterator = iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      if (Intrinsics.areEqual(localIterator.next(), paramObject)) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  @NotNull
  public Iterator<E> iterator()
  {
    return (Iterator)new IteratorImpl();
  }
  
  public int lastIndexOf(Object paramObject)
  {
    ListIterator localListIterator = listIterator(size());
    while (localListIterator.hasPrevious()) {
      if (Intrinsics.areEqual(localListIterator.previous(), paramObject)) {
        return localListIterator.nextIndex();
      }
    }
    return -1;
  }
  
  @NotNull
  public ListIterator<E> listIterator()
  {
    return (ListIterator)new ListIteratorImpl(0);
  }
  
  @NotNull
  public ListIterator<E> listIterator(int paramInt)
  {
    return (ListIterator)new ListIteratorImpl(paramInt);
  }
  
  public E remove(int paramInt)
  {
    throw new UnsupportedOperationException("Operation is not supported for read-only collection");
  }
  
  public E set(int paramInt, E paramE)
  {
    throw new UnsupportedOperationException("Operation is not supported for read-only collection");
  }
  
  @NotNull
  public List<E> subList(int paramInt1, int paramInt2)
  {
    return (List)new SubList(this, paramInt1, paramInt2);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\002\n\000\n\002\020\b\n\002\b\t\n\002\020\013\n\000\n\002\020\036\n\002\b\005\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\035\020\003\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\006H\000¢\006\002\b\bJ\035\020\t\032\0020\0042\006\020\005\032\0020\0062\006\020\007\032\0020\006H\000¢\006\002\b\nJ%\020\013\032\0020\0042\006\020\f\032\0020\0062\006\020\r\032\0020\0062\006\020\007\032\0020\006H\000¢\006\002\b\016J%\020\017\032\0020\0202\n\020\021\032\006\022\002\b\0030\0222\n\020\023\032\006\022\002\b\0030\022H\000¢\006\002\b\024J\031\020\025\032\0020\0062\n\020\021\032\006\022\002\b\0030\022H\000¢\006\002\b\026¨\006\027"}, d2={"Lkotlin/collections/AbstractList$Companion;", "", "()V", "checkElementIndex", "", "index", "", "size", "checkElementIndex$kotlin_stdlib", "checkPositionIndex", "checkPositionIndex$kotlin_stdlib", "checkRangeIndexes", "fromIndex", "toIndex", "checkRangeIndexes$kotlin_stdlib", "orderedEquals", "", "c", "", "other", "orderedEquals$kotlin_stdlib", "orderedHashCode", "orderedHashCode$kotlin_stdlib", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    public final void checkElementIndex$kotlin_stdlib(int paramInt1, int paramInt2)
    {
      if ((paramInt1 >= 0) && (paramInt1 < paramInt2)) {
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("index: ");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append(", size: ");
      localStringBuilder.append(paramInt2);
      throw ((Throwable)new IndexOutOfBoundsException(localStringBuilder.toString()));
    }
    
    public final void checkPositionIndex$kotlin_stdlib(int paramInt1, int paramInt2)
    {
      if ((paramInt1 >= 0) && (paramInt1 <= paramInt2)) {
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("index: ");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append(", size: ");
      localStringBuilder.append(paramInt2);
      throw ((Throwable)new IndexOutOfBoundsException(localStringBuilder.toString()));
    }
    
    public final void checkRangeIndexes$kotlin_stdlib(int paramInt1, int paramInt2, int paramInt3)
    {
      if ((paramInt1 >= 0) && (paramInt2 <= paramInt3))
      {
        if (paramInt1 > paramInt2)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("fromIndex: ");
          localStringBuilder.append(paramInt1);
          localStringBuilder.append(" > toIndex: ");
          localStringBuilder.append(paramInt2);
          throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString()));
        }
        return;
      }
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("fromIndex: ");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append(", toIndex: ");
      localStringBuilder.append(paramInt2);
      localStringBuilder.append(", size: ");
      localStringBuilder.append(paramInt3);
      throw ((Throwable)new IndexOutOfBoundsException(localStringBuilder.toString()));
    }
    
    public final boolean orderedEquals$kotlin_stdlib(@NotNull Collection<?> paramCollection1, @NotNull Collection<?> paramCollection2)
    {
      Intrinsics.checkParameterIsNotNull(paramCollection1, "c");
      Intrinsics.checkParameterIsNotNull(paramCollection2, "other");
      if (paramCollection1.size() != paramCollection2.size()) {
        return false;
      }
      paramCollection2 = paramCollection2.iterator();
      paramCollection1 = paramCollection1.iterator();
      while (paramCollection1.hasNext()) {
        if ((Intrinsics.areEqual(paramCollection1.next(), paramCollection2.next()) ^ true)) {
          return false;
        }
      }
      return true;
    }
    
    public final int orderedHashCode$kotlin_stdlib(@NotNull Collection<?> paramCollection)
    {
      Intrinsics.checkParameterIsNotNull(paramCollection, "c");
      paramCollection = paramCollection.iterator();
      int j;
      for (int i = 1; paramCollection.hasNext(); i = j + 31 * i)
      {
        Object localObject = paramCollection.next();
        if (localObject != null) {
          j = localObject.hashCode();
        } else {
          j = 0;
        }
      }
      return i;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\034\n\002\030\002\n\002\020(\n\002\b\002\n\002\020\b\n\002\b\005\n\002\020\013\n\002\b\003\b\004\030\0002\b\022\004\022\0028\0000\001B\005¢\006\002\020\002J\t\020\t\032\0020\nH\002J\016\020\013\032\0028\000H\002¢\006\002\020\fR\032\020\003\032\0020\004X\016¢\006\016\n\000\032\004\b\005\020\006\"\004\b\007\020\b¨\006\r"}, d2={"Lkotlin/collections/AbstractList$IteratorImpl;", "", "(Lkotlin/collections/AbstractList;)V", "index", "", "getIndex", "()I", "setIndex", "(I)V", "hasNext", "", "next", "()Ljava/lang/Object;", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  private class IteratorImpl
    implements Iterator<E>, KMappedMarker
  {
    private int index;
    
    public IteratorImpl() {}
    
    protected final int getIndex()
    {
      return index;
    }
    
    public boolean hasNext()
    {
      return index < this$0.size();
    }
    
    public E next()
    {
      if (!hasNext()) {
        throw ((Throwable)new NoSuchElementException());
      }
      AbstractList localAbstractList = this$0;
      int i = index;
      index = (i + 1);
      return localAbstractList.get(i);
    }
    
    public void remove()
    {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
    
    protected final void setIndex(int paramInt)
    {
      index = paramInt;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\002\030\002\n\002\030\002\n\002\020*\n\000\n\002\020\b\n\002\b\002\n\002\020\013\n\002\b\005\b\004\030\0002\f0\001R\b\022\004\022\0028\0000\0022\b\022\004\022\0028\0000\003B\r\022\006\020\004\032\0020\005¢\006\002\020\006J\b\020\007\032\0020\bH\026J\b\020\t\032\0020\005H\026J\r\020\n\032\0028\000H\026¢\006\002\020\013J\b\020\f\032\0020\005H\026¨\006\r"}, d2={"Lkotlin/collections/AbstractList$ListIteratorImpl;", "Lkotlin/collections/AbstractList$IteratorImpl;", "Lkotlin/collections/AbstractList;", "", "index", "", "(Lkotlin/collections/AbstractList;I)V", "hasPrevious", "", "nextIndex", "previous", "()Ljava/lang/Object;", "previousIndex", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  private class ListIteratorImpl
    extends AbstractList<E>.IteratorImpl
    implements ListIterator<E>, KMappedMarker
  {
    public ListIteratorImpl()
    {
      super();
      int i;
      AbstractList.Companion.checkPositionIndex$kotlin_stdlib(i, size());
      setIndex(i);
    }
    
    public void add(E paramE)
    {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
    
    public boolean hasPrevious()
    {
      return getIndex() > 0;
    }
    
    public int nextIndex()
    {
      return getIndex();
    }
    
    public E previous()
    {
      if (!hasPrevious()) {
        throw ((Throwable)new NoSuchElementException());
      }
      AbstractList localAbstractList = AbstractList.this;
      setIndex(getIndex() - 1);
      return localAbstractList.get(getIndex());
    }
    
    public int previousIndex()
    {
      return getIndex() - 1;
    }
    
    public void set(E paramE)
    {
      throw new UnsupportedOperationException("Operation is not supported for read-only collection");
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\036\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\n\b\002\030\000*\006\b\001\020\001 \0012\b\022\004\022\002H\0010\0022\0060\003j\002`\004B#\022\f\020\005\032\b\022\004\022\0028\0010\002\022\006\020\006\032\0020\007\022\006\020\b\032\0020\007¢\006\002\020\tJ\026\020\016\032\0028\0012\006\020\017\032\0020\007H\002¢\006\002\020\020R\016\020\n\032\0020\007X\016¢\006\002\n\000R\016\020\006\032\0020\007X\004¢\006\002\n\000R\024\020\005\032\b\022\004\022\0028\0010\002X\004¢\006\002\n\000R\024\020\013\032\0020\0078VX\004¢\006\006\032\004\b\f\020\r¨\006\021"}, d2={"Lkotlin/collections/AbstractList$SubList;", "E", "Lkotlin/collections/AbstractList;", "Ljava/util/RandomAccess;", "Lkotlin/collections/RandomAccess;", "list", "fromIndex", "", "toIndex", "(Lkotlin/collections/AbstractList;II)V", "_size", "size", "getSize", "()I", "get", "index", "(I)Ljava/lang/Object;", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
  private static final class SubList<E>
    extends AbstractList<E>
    implements RandomAccess
  {
    private int _size;
    private final int fromIndex;
    private final AbstractList<E> list;
    
    public SubList(@NotNull AbstractList<? extends E> paramAbstractList, int paramInt1, int paramInt2)
    {
      list = paramAbstractList;
      fromIndex = paramInt1;
      AbstractList.Companion.checkRangeIndexes$kotlin_stdlib(fromIndex, paramInt2, list.size());
      _size = (paramInt2 - fromIndex);
    }
    
    public E get(int paramInt)
    {
      AbstractList.Companion.checkElementIndex$kotlin_stdlib(paramInt, _size);
      return list.get(fromIndex + paramInt);
    }
    
    public int getSize()
    {
      return _size;
    }
  }
}

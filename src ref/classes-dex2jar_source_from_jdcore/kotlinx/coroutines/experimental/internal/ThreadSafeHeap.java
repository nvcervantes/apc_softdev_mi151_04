package kotlinx.coroutines.experimental.internal;

import java.util.Arrays;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.Unit;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000F\n\002\030\002\n\000\n\002\030\002\n\002\020\017\n\002\020\000\n\002\b\002\n\002\020\021\n\002\b\002\n\002\020\013\n\002\b\002\n\002\020\b\n\002\b\002\n\002\020\002\n\002\b\005\n\002\030\002\n\002\b\r\n\002\030\002\n\002\b\006\030\000*\022\b\000\020\001*\0020\002*\b\022\004\022\002H\0010\0032\0020\004B\005¢\006\002\020\005J\025\020\017\032\0020\0202\006\020\021\032\0028\000H\001¢\006\002\020\022J\023\020\023\032\0020\0202\006\020\021\032\0028\000¢\006\002\020\022J!\020\024\032\0020\n2\006\020\021\032\0028\0002\f\020\025\032\b\022\004\022\0020\n0\026¢\006\002\020\027J\017\020\030\032\004\030\0018\000H\001¢\006\002\020\031J\r\020\032\032\004\030\0018\000¢\006\002\020\031J\025\020\033\032\n\022\006\022\004\030\0018\0000\007H\003¢\006\002\020\034J\023\020\035\032\0020\n2\006\020\021\032\0028\000¢\006\002\020\036J\025\020\037\032\0028\0002\006\020 \032\0020\rH\001¢\006\002\020!J$\020\"\032\004\030\0018\0002\022\020#\032\016\022\004\022\0028\000\022\004\022\0020\n0$H\b¢\006\002\020%J\r\020&\032\004\030\0018\000¢\006\002\020\031J\030\020'\032\0020\0202\006\020(\032\0020\r2\006\020)\032\0020\rH\002R\032\020\006\032\f\022\006\022\004\030\0018\000\030\0010\007X\016¢\006\004\n\002\020\bR\021\020\t\032\0020\n8F¢\006\006\032\004\b\t\020\013R\030\020\f\032\0020\r8\000@\000X\016¢\006\b\n\000\022\004\b\016\020\005¨\006*"}, d2={"Lkotlinx/coroutines/experimental/internal/ThreadSafeHeap;", "T", "Lkotlinx/coroutines/experimental/internal/ThreadSafeHeapNode;", "", "", "()V", "a", "", "[Lkotlinx/coroutines/experimental/internal/ThreadSafeHeapNode;", "isEmpty", "", "()Z", "size", "", "size$annotations", "addImpl", "", "node", "(Lkotlinx/coroutines/experimental/internal/ThreadSafeHeapNode;)V", "addLast", "addLastIf", "cond", "Lkotlin/Function0;", "(Lkotlinx/coroutines/experimental/internal/ThreadSafeHeapNode;Lkotlin/jvm/functions/Function0;)Z", "firstImpl", "()Lkotlinx/coroutines/experimental/internal/ThreadSafeHeapNode;", "peek", "realloc", "()[Lkotlinx/coroutines/experimental/internal/ThreadSafeHeapNode;", "remove", "(Lkotlinx/coroutines/experimental/internal/ThreadSafeHeapNode;)Z", "removeAtImpl", "index", "(I)Lkotlinx/coroutines/experimental/internal/ThreadSafeHeapNode;", "removeFirstIf", "predicate", "Lkotlin/Function1;", "(Lkotlin/jvm/functions/Function1;)Lkotlinx/coroutines/experimental/internal/ThreadSafeHeapNode;", "removeFirstOrNull", "swap", "i", "j", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class ThreadSafeHeap<T extends ThreadSafeHeapNode,  extends Comparable<? super T>>
{
  private T[] a;
  @JvmField
  public volatile int size;
  
  public ThreadSafeHeap() {}
  
  private final T[] realloc()
  {
    Object localObject = a;
    if (localObject == null)
    {
      localObject = new ThreadSafeHeapNode[4];
      a = ((ThreadSafeHeapNode[])localObject);
      return localObject;
    }
    int i = size;
    Object[] arrayOfObject = (Object[])localObject;
    if (i >= arrayOfObject.length)
    {
      localObject = Arrays.copyOf(arrayOfObject, size * 2);
      Intrinsics.checkExpressionValueIsNotNull(localObject, "java.util.Arrays.copyOf(this, newSize)");
      localObject = (ThreadSafeHeapNode[])localObject;
      a = ((ThreadSafeHeapNode[])localObject);
    }
    return localObject;
  }
  
  private final void swap(int paramInt1, int paramInt2)
  {
    ThreadSafeHeapNode[] arrayOfThreadSafeHeapNode = a;
    if (arrayOfThreadSafeHeapNode == null) {
      Intrinsics.throwNpe();
    }
    ThreadSafeHeapNode localThreadSafeHeapNode1 = arrayOfThreadSafeHeapNode[paramInt2];
    if (localThreadSafeHeapNode1 == null) {
      Intrinsics.throwNpe();
    }
    ThreadSafeHeapNode localThreadSafeHeapNode2 = arrayOfThreadSafeHeapNode[paramInt1];
    if (localThreadSafeHeapNode2 == null) {
      Intrinsics.throwNpe();
    }
    arrayOfThreadSafeHeapNode[paramInt1] = localThreadSafeHeapNode1;
    arrayOfThreadSafeHeapNode[paramInt2] = localThreadSafeHeapNode2;
    localThreadSafeHeapNode1.setIndex(paramInt1);
    localThreadSafeHeapNode2.setIndex(paramInt2);
  }
  
  @PublishedApi
  public final void addImpl(@NotNull T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramT, "node");
    ThreadSafeHeapNode[] arrayOfThreadSafeHeapNode = realloc();
    int i = size;
    size = (i + 1);
    arrayOfThreadSafeHeapNode[i] = paramT;
    paramT.setIndex(i);
    while (i > 0)
    {
      int j = (i - 1) / 2;
      paramT = arrayOfThreadSafeHeapNode[j];
      if (paramT == null) {
        Intrinsics.throwNpe();
      }
      paramT = (Comparable)paramT;
      ThreadSafeHeapNode localThreadSafeHeapNode = arrayOfThreadSafeHeapNode[i];
      if (localThreadSafeHeapNode == null) {
        Intrinsics.throwNpe();
      }
      if (paramT.compareTo(localThreadSafeHeapNode) <= 0) {
        return;
      }
      swap(i, j);
      i = j;
    }
  }
  
  public final void addLast(@NotNull T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramT, "node");
    try
    {
      addImpl(paramT);
      paramT = Unit.INSTANCE;
      return;
    }
    finally
    {
      paramT = finally;
      throw paramT;
    }
  }
  
  public final boolean addLastIf(@NotNull T paramT, @NotNull Function0<Boolean> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramT, "node");
    Intrinsics.checkParameterIsNotNull(paramFunction0, "cond");
    try
    {
      boolean bool;
      if (((Boolean)paramFunction0.invoke()).booleanValue())
      {
        addImpl(paramT);
        bool = true;
      }
      else
      {
        bool = false;
      }
      return bool;
    }
    finally
    {
      paramT = finally;
      throw paramT;
    }
  }
  
  @PublishedApi
  @Nullable
  public final T firstImpl()
  {
    ThreadSafeHeapNode[] arrayOfThreadSafeHeapNode = a;
    if (arrayOfThreadSafeHeapNode != null) {
      return (ThreadSafeHeapNode)((Object[])arrayOfThreadSafeHeapNode)[0];
    }
    return null;
  }
  
  public final boolean isEmpty()
  {
    return size == 0;
  }
  
  @Nullable
  public final T peek()
  {
    try
    {
      ThreadSafeHeapNode localThreadSafeHeapNode = firstImpl();
      return localThreadSafeHeapNode;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
  
  public final boolean remove(@NotNull T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramT, "node");
    try
    {
      boolean bool;
      if (paramT.getIndex() < 0)
      {
        bool = false;
      }
      else
      {
        removeAtImpl(paramT.getIndex());
        bool = true;
      }
      return bool;
    }
    finally {}
  }
  
  @PublishedApi
  @NotNull
  public final T removeAtImpl(int paramInt)
  {
    int i;
    if (size > 0) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalStateException("Check failed.".toString()));
    }
    ThreadSafeHeapNode[] arrayOfThreadSafeHeapNode = a;
    if (arrayOfThreadSafeHeapNode == null) {
      Intrinsics.throwNpe();
    }
    size -= 1;
    if (paramInt < size)
    {
      swap(paramInt, size);
      for (i = paramInt;; i = paramInt)
      {
        int j = 2 * i + 1;
        if (j >= size) {
          break;
        }
        int k = j + 1;
        paramInt = j;
        if (k < size)
        {
          localObject = arrayOfThreadSafeHeapNode[k];
          if (localObject == null) {
            Intrinsics.throwNpe();
          }
          localObject = (Comparable)localObject;
          localThreadSafeHeapNode = arrayOfThreadSafeHeapNode[j];
          if (localThreadSafeHeapNode == null) {
            Intrinsics.throwNpe();
          }
          paramInt = j;
          if (((Comparable)localObject).compareTo(localThreadSafeHeapNode) < 0) {
            paramInt = k;
          }
        }
        localObject = arrayOfThreadSafeHeapNode[i];
        if (localObject == null) {
          Intrinsics.throwNpe();
        }
        localObject = (Comparable)localObject;
        ThreadSafeHeapNode localThreadSafeHeapNode = arrayOfThreadSafeHeapNode[paramInt];
        if (localThreadSafeHeapNode == null) {
          Intrinsics.throwNpe();
        }
        if (((Comparable)localObject).compareTo(localThreadSafeHeapNode) <= 0) {
          break;
        }
        swap(i, paramInt);
      }
    }
    Object localObject = arrayOfThreadSafeHeapNode[size];
    if (localObject == null) {
      Intrinsics.throwNpe();
    }
    ((ThreadSafeHeapNode)localObject).setIndex(-1);
    arrayOfThreadSafeHeapNode[size] = ((ThreadSafeHeapNode)null);
    return localObject;
  }
  
  @Nullable
  public final T removeFirstIf(@NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    try
    {
      ThreadSafeHeapNode localThreadSafeHeapNode = firstImpl();
      Object localObject2 = null;
      Object localObject1 = localObject2;
      if (localThreadSafeHeapNode != null)
      {
        localObject1 = localObject2;
        if (((Boolean)paramFunction1.invoke(localThreadSafeHeapNode)).booleanValue()) {
          localObject1 = removeAtImpl(0);
        }
      }
      return localObject1;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      InlineMarker.finallyEnd(1);
    }
  }
  
  @Nullable
  public final T removeFirstOrNull()
  {
    try
    {
      ThreadSafeHeapNode localThreadSafeHeapNode;
      if (size > 0) {
        localThreadSafeHeapNode = removeAtImpl(0);
      } else {
        localThreadSafeHeapNode = null;
      }
      return localThreadSafeHeapNode;
    }
    finally
    {
      localObject = finally;
      throw localObject;
    }
  }
}

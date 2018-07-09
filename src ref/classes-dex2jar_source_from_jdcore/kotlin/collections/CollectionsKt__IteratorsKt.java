package kotlin.collections;

import java.util.Enumeration;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000 \n\000\n\002\020\002\n\000\n\002\020(\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\032-\020\000\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\022\020\004\032\016\022\004\022\002H\002\022\004\022\0020\0010\005H\b\032\037\020\006\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\007H\002\032\037\020\006\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\003H\n\032\"\020\b\032\016\022\n\022\b\022\004\022\002H\0020\t0\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\003¨\006\n"}, d2={"forEach", "", "T", "", "operation", "Lkotlin/Function1;", "iterator", "Ljava/util/Enumeration;", "withIndex", "Lkotlin/collections/IndexedValue;", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/collections/CollectionsKt")
class CollectionsKt__IteratorsKt
  extends CollectionsKt__IterablesKt
{
  public CollectionsKt__IteratorsKt() {}
  
  public static final <T> void forEach(@NotNull Iterator<? extends T> paramIterator, @NotNull Function1<? super T, Unit> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramIterator, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "operation");
    while (paramIterator.hasNext()) {
      paramFunction1.invoke(paramIterator.next());
    }
  }
  
  @NotNull
  public static final <T> Iterator<T> iterator(@NotNull Enumeration<T> paramEnumeration)
  {
    Intrinsics.checkParameterIsNotNull(paramEnumeration, "$receiver");
    (Iterator)new Iterator()
    {
      public boolean hasNext()
      {
        return receiver$0.hasMoreElements();
      }
      
      public T next()
      {
        return receiver$0.nextElement();
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
    };
  }
  
  @InlineOnly
  private static final <T> Iterator<T> iterator(@NotNull Iterator<? extends T> paramIterator)
  {
    Intrinsics.checkParameterIsNotNull(paramIterator, "$receiver");
    return paramIterator;
  }
  
  @NotNull
  public static final <T> Iterator<IndexedValue<T>> withIndex(@NotNull Iterator<? extends T> paramIterator)
  {
    Intrinsics.checkParameterIsNotNull(paramIterator, "$receiver");
    return (Iterator)new IndexingIterator(paramIterator);
  }
}

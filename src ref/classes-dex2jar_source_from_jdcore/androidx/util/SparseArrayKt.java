package androidx.util;

import android.util.SparseArray;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000@\n\000\n\002\020\b\n\000\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\006\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\b\n\002\020(\n\000\032!\020\006\032\0020\007\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\b\032\0020\001H\n\032!\020\t\032\0020\007\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\b\032\0020\001H\b\032&\020\n\032\0020\007\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\013\032\002H\002H\b¢\006\002\020\f\032Q\020\r\032\0020\016\"\004\b\000\020\002*\b\022\004\022\002H\0020\00326\020\017\0322\022\023\022\0210\001¢\006\f\b\021\022\b\b\022\022\004\b\b(\b\022\023\022\021H\002¢\006\f\b\021\022\b\b\022\022\004\b\b(\013\022\004\022\0020\0160\020H\b\032.\020\023\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\b\032\0020\0012\006\020\024\032\002H\002H\b¢\006\002\020\025\0324\020\026\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\b\032\0020\0012\f\020\024\032\b\022\004\022\002H\0020\027H\b¢\006\002\020\030\032\031\020\031\032\0020\007\"\004\b\000\020\002*\b\022\004\022\002H\0020\003H\b\032\031\020\032\032\0020\007\"\004\b\000\020\002*\b\022\004\022\002H\0020\003H\b\032\026\020\033\032\0020\034\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\032-\020\035\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\f\020\036\032\b\022\004\022\002H\0020\003H\002\032$\020\037\032\0020\016\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\f\020\036\032\b\022\004\022\002H\0020\003\032+\020 \032\0020\007\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\b\032\0020\0012\006\020\013\032\002H\002¢\006\002\020!\032.\020\"\032\0020\016\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\b\032\0020\0012\006\020\013\032\002H\002H\n¢\006\002\020#\032\034\020$\032\b\022\004\022\002H\0020%\"\004\b\000\020\002*\b\022\004\022\002H\0020\003\"\"\020\000\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0038Æ\002¢\006\006\032\004\b\004\020\005¨\006&"}, d2={"size", "", "T", "Landroid/util/SparseArray;", "getSize", "(Landroid/util/SparseArray;)I", "contains", "", "key", "containsKey", "containsValue", "value", "(Landroid/util/SparseArray;Ljava/lang/Object;)Z", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "(Landroid/util/SparseArray;ILjava/lang/Object;)Ljava/lang/Object;", "getOrElse", "Lkotlin/Function0;", "(Landroid/util/SparseArray;ILkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/IntIterator;", "plus", "other", "putAll", "remove", "(Landroid/util/SparseArray;ILjava/lang/Object;)Z", "set", "(Landroid/util/SparseArray;ILjava/lang/Object;)V", "valueIterator", "", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class SparseArrayKt
{
  public static final <T> boolean contains(@NotNull SparseArray<T> paramSparseArray, int paramInt)
  {
    return paramSparseArray.indexOfKey(paramInt) >= 0;
  }
  
  public static final <T> boolean containsKey(@NotNull SparseArray<T> paramSparseArray, int paramInt)
  {
    return paramSparseArray.indexOfKey(paramInt) >= 0;
  }
  
  public static final <T> boolean containsValue(@NotNull SparseArray<T> paramSparseArray, T paramT)
  {
    return paramSparseArray.indexOfValue(paramT) != -1;
  }
  
  public static final <T> void forEach(@NotNull SparseArray<T> paramSparseArray, @NotNull Function2<? super Integer, ? super T, Unit> paramFunction2)
  {
    int j = paramSparseArray.size();
    int i = 0;
    while (i < j)
    {
      paramFunction2.invoke(Integer.valueOf(paramSparseArray.keyAt(i)), paramSparseArray.valueAt(i));
      i += 1;
    }
  }
  
  public static final <T> T getOrDefault(@NotNull SparseArray<T> paramSparseArray, int paramInt, T paramT)
  {
    paramSparseArray = paramSparseArray.get(paramInt);
    if (paramSparseArray != null) {
      return paramSparseArray;
    }
    return paramT;
  }
  
  public static final <T> T getOrElse(@NotNull SparseArray<T> paramSparseArray, int paramInt, @NotNull Function0<? extends T> paramFunction0)
  {
    paramSparseArray = paramSparseArray.get(paramInt);
    if (paramSparseArray != null) {
      return paramSparseArray;
    }
    return paramFunction0.invoke();
  }
  
  public static final <T> int getSize(@NotNull SparseArray<T> paramSparseArray)
  {
    return paramSparseArray.size();
  }
  
  public static final <T> boolean isEmpty(@NotNull SparseArray<T> paramSparseArray)
  {
    return paramSparseArray.size() == 0;
  }
  
  public static final <T> boolean isNotEmpty(@NotNull SparseArray<T> paramSparseArray)
  {
    return paramSparseArray.size() != 0;
  }
  
  @NotNull
  public static final <T> IntIterator keyIterator(@NotNull SparseArray<T> paramSparseArray)
  {
    (IntIterator)new IntIterator()
    {
      private int index;
      
      public final int getIndex()
      {
        return index;
      }
      
      public boolean hasNext()
      {
        return index < receiver$0.size();
      }
      
      public int nextInt()
      {
        SparseArray localSparseArray = receiver$0;
        int i = index;
        index = (i + 1);
        return localSparseArray.keyAt(i);
      }
      
      public final void setIndex(int paramAnonymousInt)
      {
        index = paramAnonymousInt;
      }
    };
  }
  
  @NotNull
  public static final <T> SparseArray<T> plus(@NotNull SparseArray<T> paramSparseArray1, @NotNull SparseArray<T> paramSparseArray2)
  {
    SparseArray localSparseArray = new SparseArray(paramSparseArray1.size() + paramSparseArray2.size());
    putAll(localSparseArray, paramSparseArray1);
    putAll(localSparseArray, paramSparseArray2);
    return localSparseArray;
  }
  
  public static final <T> void putAll(@NotNull SparseArray<T> paramSparseArray1, @NotNull SparseArray<T> paramSparseArray2)
  {
    int j = paramSparseArray2.size();
    int i = 0;
    while (i < j)
    {
      paramSparseArray1.put(paramSparseArray2.keyAt(i), paramSparseArray2.valueAt(i));
      i += 1;
    }
  }
  
  public static final <T> boolean remove(@NotNull SparseArray<T> paramSparseArray, int paramInt, T paramT)
  {
    paramInt = paramSparseArray.indexOfKey(paramInt);
    if ((paramInt != -1) && (Intrinsics.areEqual(paramT, paramSparseArray.valueAt(paramInt))))
    {
      paramSparseArray.removeAt(paramInt);
      return true;
    }
    return false;
  }
  
  public static final <T> void set(@NotNull SparseArray<T> paramSparseArray, int paramInt, T paramT)
  {
    paramSparseArray.put(paramInt, paramT);
  }
  
  @NotNull
  public static final <T> Iterator<T> valueIterator(@NotNull SparseArray<T> paramSparseArray)
  {
    (Iterator)new Iterator()
    {
      private int index;
      
      public final int getIndex()
      {
        return index;
      }
      
      public boolean hasNext()
      {
        return index < receiver$0.size();
      }
      
      public T next()
      {
        SparseArray localSparseArray = receiver$0;
        int i = index;
        index = (i + 1);
        return localSparseArray.valueAt(i);
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
      
      public final void setIndex(int paramAnonymousInt)
      {
        index = paramAnonymousInt;
      }
    };
  }
}

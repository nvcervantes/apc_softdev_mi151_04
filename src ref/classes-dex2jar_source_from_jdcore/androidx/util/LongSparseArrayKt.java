package androidx.util;

import android.support.annotation.RequiresApi;
import android.util.LongSparseArray;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.LongIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000F\n\000\n\002\020\b\n\000\n\002\030\002\n\002\b\003\n\002\020\013\n\000\n\002\020\t\n\002\b\005\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\b\n\002\020(\n\000\032!\020\006\032\0020\007\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\b\032\0020\tH\n\032!\020\n\032\0020\007\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\b\032\0020\tH\b\032&\020\013\032\0020\007\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\f\032\002H\002H\b¢\006\002\020\r\032Q\020\016\032\0020\017\"\004\b\000\020\002*\b\022\004\022\002H\0020\00326\020\020\0322\022\023\022\0210\t¢\006\f\b\022\022\b\b\023\022\004\b\b(\b\022\023\022\021H\002¢\006\f\b\022\022\b\b\023\022\004\b\b(\f\022\004\022\0020\0170\021H\b\032.\020\024\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\b\032\0020\t2\006\020\025\032\002H\002H\b¢\006\002\020\026\0324\020\027\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\b\032\0020\t2\f\020\025\032\b\022\004\022\002H\0020\030H\b¢\006\002\020\031\032\031\020\032\032\0020\007\"\004\b\000\020\002*\b\022\004\022\002H\0020\003H\b\032\031\020\033\032\0020\007\"\004\b\000\020\002*\b\022\004\022\002H\0020\003H\b\032\030\020\034\032\0020\035\"\004\b\000\020\002*\b\022\004\022\002H\0020\003H\007\032-\020\036\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\f\020\037\032\b\022\004\022\002H\0020\003H\002\032&\020 \032\0020\017\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\f\020\037\032\b\022\004\022\002H\0020\003H\007\032-\020!\032\0020\007\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\b\032\0020\t2\006\020\f\032\002H\002H\007¢\006\002\020\"\032.\020#\032\0020\017\"\004\b\000\020\002*\b\022\004\022\002H\0020\0032\006\020\b\032\0020\t2\006\020\f\032\002H\002H\n¢\006\002\020$\032\036\020%\032\b\022\004\022\002H\0020&\"\004\b\000\020\002*\b\022\004\022\002H\0020\003H\007\"\"\020\000\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0038Ç\002¢\006\006\032\004\b\004\020\005¨\006'"}, d2={"size", "", "T", "Landroid/util/LongSparseArray;", "getSize", "(Landroid/util/LongSparseArray;)I", "contains", "", "key", "", "containsKey", "containsValue", "value", "(Landroid/util/LongSparseArray;Ljava/lang/Object;)Z", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)Ljava/lang/Object;", "getOrElse", "Lkotlin/Function0;", "(Landroid/util/LongSparseArray;JLkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/LongIterator;", "plus", "other", "putAll", "remove", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)Z", "set", "(Landroid/util/LongSparseArray;JLjava/lang/Object;)V", "valueIterator", "", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class LongSparseArrayKt
{
  @RequiresApi(16)
  public static final <T> boolean contains(@NotNull LongSparseArray<T> paramLongSparseArray, long paramLong)
  {
    return paramLongSparseArray.indexOfKey(paramLong) >= 0;
  }
  
  @RequiresApi(16)
  public static final <T> boolean containsKey(@NotNull LongSparseArray<T> paramLongSparseArray, long paramLong)
  {
    return paramLongSparseArray.indexOfKey(paramLong) >= 0;
  }
  
  @RequiresApi(16)
  public static final <T> boolean containsValue(@NotNull LongSparseArray<T> paramLongSparseArray, T paramT)
  {
    return paramLongSparseArray.indexOfValue(paramT) != -1;
  }
  
  @RequiresApi(16)
  public static final <T> void forEach(@NotNull LongSparseArray<T> paramLongSparseArray, @NotNull Function2<? super Long, ? super T, Unit> paramFunction2)
  {
    int j = paramLongSparseArray.size();
    int i = 0;
    while (i < j)
    {
      paramFunction2.invoke(Long.valueOf(paramLongSparseArray.keyAt(i)), paramLongSparseArray.valueAt(i));
      i += 1;
    }
  }
  
  @RequiresApi(16)
  public static final <T> T getOrDefault(@NotNull LongSparseArray<T> paramLongSparseArray, long paramLong, T paramT)
  {
    paramLongSparseArray = paramLongSparseArray.get(paramLong);
    if (paramLongSparseArray != null) {
      return paramLongSparseArray;
    }
    return paramT;
  }
  
  @RequiresApi(16)
  public static final <T> T getOrElse(@NotNull LongSparseArray<T> paramLongSparseArray, long paramLong, @NotNull Function0<? extends T> paramFunction0)
  {
    paramLongSparseArray = paramLongSparseArray.get(paramLong);
    if (paramLongSparseArray != null) {
      return paramLongSparseArray;
    }
    return paramFunction0.invoke();
  }
  
  @RequiresApi(16)
  public static final <T> int getSize(@NotNull LongSparseArray<T> paramLongSparseArray)
  {
    return paramLongSparseArray.size();
  }
  
  @RequiresApi(16)
  public static final <T> boolean isEmpty(@NotNull LongSparseArray<T> paramLongSparseArray)
  {
    return paramLongSparseArray.size() == 0;
  }
  
  @RequiresApi(16)
  public static final <T> boolean isNotEmpty(@NotNull LongSparseArray<T> paramLongSparseArray)
  {
    return paramLongSparseArray.size() != 0;
  }
  
  @RequiresApi(16)
  @NotNull
  public static final <T> LongIterator keyIterator(@NotNull LongSparseArray<T> paramLongSparseArray)
  {
    (LongIterator)new LongIterator()
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
      
      public long nextLong()
      {
        LongSparseArray localLongSparseArray = receiver$0;
        int i = index;
        index = (i + 1);
        return localLongSparseArray.keyAt(i);
      }
      
      public final void setIndex(int paramAnonymousInt)
      {
        index = paramAnonymousInt;
      }
    };
  }
  
  @RequiresApi(16)
  @NotNull
  public static final <T> LongSparseArray<T> plus(@NotNull LongSparseArray<T> paramLongSparseArray1, @NotNull LongSparseArray<T> paramLongSparseArray2)
  {
    LongSparseArray localLongSparseArray = new LongSparseArray(paramLongSparseArray1.size() + paramLongSparseArray2.size());
    putAll(localLongSparseArray, paramLongSparseArray1);
    putAll(localLongSparseArray, paramLongSparseArray2);
    return localLongSparseArray;
  }
  
  @RequiresApi(16)
  public static final <T> void putAll(@NotNull LongSparseArray<T> paramLongSparseArray1, @NotNull LongSparseArray<T> paramLongSparseArray2)
  {
    int j = paramLongSparseArray2.size();
    int i = 0;
    while (i < j)
    {
      paramLongSparseArray1.put(paramLongSparseArray2.keyAt(i), paramLongSparseArray2.valueAt(i));
      i += 1;
    }
  }
  
  @RequiresApi(16)
  public static final <T> boolean remove(@NotNull LongSparseArray<T> paramLongSparseArray, long paramLong, T paramT)
  {
    int i = paramLongSparseArray.indexOfKey(paramLong);
    if ((i != -1) && (Intrinsics.areEqual(paramT, paramLongSparseArray.valueAt(i))))
    {
      paramLongSparseArray.removeAt(i);
      return true;
    }
    return false;
  }
  
  @RequiresApi(16)
  public static final <T> void set(@NotNull LongSparseArray<T> paramLongSparseArray, long paramLong, T paramT)
  {
    paramLongSparseArray.put(paramLong, paramT);
  }
  
  @RequiresApi(16)
  @NotNull
  public static final <T> Iterator<T> valueIterator(@NotNull LongSparseArray<T> paramLongSparseArray)
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
        LongSparseArray localLongSparseArray = receiver$0;
        int i = index;
        index = (i + 1);
        return localLongSparseArray.valueAt(i);
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

package androidx.util;

import android.support.annotation.RequiresApi;
import android.util.SparseLongArray;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.collections.LongIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000D\n\000\n\002\020\b\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\004\n\002\020\t\n\000\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\006\n\002\030\002\n\000\032\025\020\005\032\0020\006*\0020\0022\006\020\007\032\0020\001H\n\032\025\020\b\032\0020\006*\0020\0022\006\020\007\032\0020\001H\b\032\025\020\t\032\0020\006*\0020\0022\006\020\n\032\0020\013H\b\032E\020\f\032\0020\r*\0020\00226\020\016\0322\022\023\022\0210\001¢\006\f\b\020\022\b\b\021\022\004\b\b(\007\022\023\022\0210\013¢\006\f\b\020\022\b\b\021\022\004\b\b(\n\022\004\022\0020\r0\017H\b\032\035\020\022\032\0020\013*\0020\0022\006\020\007\032\0020\0012\006\020\023\032\0020\013H\b\032#\020\024\032\0020\013*\0020\0022\006\020\007\032\0020\0012\f\020\023\032\b\022\004\022\0020\0130\025H\b\032\r\020\026\032\0020\006*\0020\002H\b\032\r\020\027\032\0020\006*\0020\002H\b\032\f\020\030\032\0020\031*\0020\002H\007\032\025\020\032\032\0020\002*\0020\0022\006\020\033\032\0020\002H\002\032\024\020\034\032\0020\r*\0020\0022\006\020\033\032\0020\002H\007\032\034\020\035\032\0020\006*\0020\0022\006\020\007\032\0020\0012\006\020\n\032\0020\013H\007\032\035\020\036\032\0020\r*\0020\0022\006\020\007\032\0020\0012\006\020\n\032\0020\013H\n\032\f\020\037\032\0020 *\0020\002H\007\"\026\020\000\032\0020\001*\0020\0028Ç\002¢\006\006\032\004\b\003\020\004¨\006!"}, d2={"size", "", "Landroid/util/SparseLongArray;", "getSize", "(Landroid/util/SparseLongArray;)I", "contains", "", "key", "containsKey", "containsValue", "value", "", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "getOrElse", "Lkotlin/Function0;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/IntIterator;", "plus", "other", "putAll", "remove", "set", "valueIterator", "Lkotlin/collections/LongIterator;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class SparseLongArrayKt
{
  @RequiresApi(18)
  public static final boolean contains(@NotNull SparseLongArray paramSparseLongArray, int paramInt)
  {
    return paramSparseLongArray.indexOfKey(paramInt) >= 0;
  }
  
  @RequiresApi(18)
  public static final boolean containsKey(@NotNull SparseLongArray paramSparseLongArray, int paramInt)
  {
    return paramSparseLongArray.indexOfKey(paramInt) >= 0;
  }
  
  @RequiresApi(18)
  public static final boolean containsValue(@NotNull SparseLongArray paramSparseLongArray, long paramLong)
  {
    return paramSparseLongArray.indexOfValue(paramLong) != -1;
  }
  
  @RequiresApi(18)
  public static final void forEach(@NotNull SparseLongArray paramSparseLongArray, @NotNull Function2<? super Integer, ? super Long, Unit> paramFunction2)
  {
    int j = paramSparseLongArray.size();
    int i = 0;
    while (i < j)
    {
      paramFunction2.invoke(Integer.valueOf(paramSparseLongArray.keyAt(i)), Long.valueOf(paramSparseLongArray.valueAt(i)));
      i += 1;
    }
  }
  
  @RequiresApi(18)
  public static final long getOrDefault(@NotNull SparseLongArray paramSparseLongArray, int paramInt, long paramLong)
  {
    return paramSparseLongArray.get(paramInt, paramLong);
  }
  
  @RequiresApi(18)
  public static final long getOrElse(@NotNull SparseLongArray paramSparseLongArray, int paramInt, @NotNull Function0<Long> paramFunction0)
  {
    paramInt = paramSparseLongArray.indexOfKey(paramInt);
    if (paramInt != -1) {
      return paramSparseLongArray.valueAt(paramInt);
    }
    return ((Number)paramFunction0.invoke()).longValue();
  }
  
  @RequiresApi(18)
  public static final int getSize(@NotNull SparseLongArray paramSparseLongArray)
  {
    return paramSparseLongArray.size();
  }
  
  @RequiresApi(18)
  public static final boolean isEmpty(@NotNull SparseLongArray paramSparseLongArray)
  {
    return paramSparseLongArray.size() == 0;
  }
  
  @RequiresApi(18)
  public static final boolean isNotEmpty(@NotNull SparseLongArray paramSparseLongArray)
  {
    return paramSparseLongArray.size() != 0;
  }
  
  @RequiresApi(18)
  @NotNull
  public static final IntIterator keyIterator(@NotNull SparseLongArray paramSparseLongArray)
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
        SparseLongArray localSparseLongArray = receiver$0;
        int i = index;
        index = (i + 1);
        return localSparseLongArray.keyAt(i);
      }
      
      public final void setIndex(int paramAnonymousInt)
      {
        index = paramAnonymousInt;
      }
    };
  }
  
  @RequiresApi(18)
  @NotNull
  public static final SparseLongArray plus(@NotNull SparseLongArray paramSparseLongArray1, @NotNull SparseLongArray paramSparseLongArray2)
  {
    SparseLongArray localSparseLongArray = new SparseLongArray(paramSparseLongArray1.size() + paramSparseLongArray2.size());
    putAll(localSparseLongArray, paramSparseLongArray1);
    putAll(localSparseLongArray, paramSparseLongArray2);
    return localSparseLongArray;
  }
  
  @RequiresApi(18)
  public static final void putAll(@NotNull SparseLongArray paramSparseLongArray1, @NotNull SparseLongArray paramSparseLongArray2)
  {
    int j = paramSparseLongArray2.size();
    int i = 0;
    while (i < j)
    {
      paramSparseLongArray1.put(paramSparseLongArray2.keyAt(i), paramSparseLongArray2.valueAt(i));
      i += 1;
    }
  }
  
  @RequiresApi(18)
  public static final boolean remove(@NotNull SparseLongArray paramSparseLongArray, int paramInt, long paramLong)
  {
    paramInt = paramSparseLongArray.indexOfKey(paramInt);
    if ((paramInt != -1) && (paramLong == paramSparseLongArray.valueAt(paramInt)))
    {
      paramSparseLongArray.removeAt(paramInt);
      return true;
    }
    return false;
  }
  
  @RequiresApi(18)
  public static final void set(@NotNull SparseLongArray paramSparseLongArray, int paramInt, long paramLong)
  {
    paramSparseLongArray.put(paramInt, paramLong);
  }
  
  @RequiresApi(18)
  @NotNull
  public static final LongIterator valueIterator(@NotNull SparseLongArray paramSparseLongArray)
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
        SparseLongArray localSparseLongArray = receiver$0;
        int i = index;
        index = (i + 1);
        return localSparseLongArray.valueAt(i);
      }
      
      public final void setIndex(int paramAnonymousInt)
      {
        index = paramAnonymousInt;
      }
    };
  }
}

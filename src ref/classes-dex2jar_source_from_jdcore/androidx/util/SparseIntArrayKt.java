package androidx.util;

import android.util.SparseIntArray;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0008\n\000\n\002\020\b\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\005\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\007\032\025\020\005\032\0020\006*\0020\0022\006\020\007\032\0020\001H\n\032\025\020\b\032\0020\006*\0020\0022\006\020\007\032\0020\001H\b\032\025\020\t\032\0020\006*\0020\0022\006\020\n\032\0020\001H\b\032E\020\013\032\0020\f*\0020\00226\020\r\0322\022\023\022\0210\001¢\006\f\b\017\022\b\b\020\022\004\b\b(\007\022\023\022\0210\001¢\006\f\b\017\022\b\b\020\022\004\b\b(\n\022\004\022\0020\f0\016H\b\032\035\020\021\032\0020\001*\0020\0022\006\020\007\032\0020\0012\006\020\022\032\0020\001H\b\032#\020\023\032\0020\001*\0020\0022\006\020\007\032\0020\0012\f\020\022\032\b\022\004\022\0020\0010\024H\b\032\r\020\025\032\0020\006*\0020\002H\b\032\r\020\026\032\0020\006*\0020\002H\b\032\n\020\027\032\0020\030*\0020\002\032\025\020\031\032\0020\002*\0020\0022\006\020\032\032\0020\002H\002\032\022\020\033\032\0020\f*\0020\0022\006\020\032\032\0020\002\032\032\020\034\032\0020\006*\0020\0022\006\020\007\032\0020\0012\006\020\n\032\0020\001\032\035\020\035\032\0020\f*\0020\0022\006\020\007\032\0020\0012\006\020\n\032\0020\001H\n\032\n\020\036\032\0020\030*\0020\002\"\026\020\000\032\0020\001*\0020\0028Æ\002¢\006\006\032\004\b\003\020\004¨\006\037"}, d2={"size", "", "Landroid/util/SparseIntArray;", "getSize", "(Landroid/util/SparseIntArray;)I", "contains", "", "key", "containsKey", "containsValue", "value", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "getOrElse", "Lkotlin/Function0;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/IntIterator;", "plus", "other", "putAll", "remove", "set", "valueIterator", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class SparseIntArrayKt
{
  public static final boolean contains(@NotNull SparseIntArray paramSparseIntArray, int paramInt)
  {
    return paramSparseIntArray.indexOfKey(paramInt) >= 0;
  }
  
  public static final boolean containsKey(@NotNull SparseIntArray paramSparseIntArray, int paramInt)
  {
    return paramSparseIntArray.indexOfKey(paramInt) >= 0;
  }
  
  public static final boolean containsValue(@NotNull SparseIntArray paramSparseIntArray, int paramInt)
  {
    return paramSparseIntArray.indexOfValue(paramInt) != -1;
  }
  
  public static final void forEach(@NotNull SparseIntArray paramSparseIntArray, @NotNull Function2<? super Integer, ? super Integer, Unit> paramFunction2)
  {
    int j = paramSparseIntArray.size();
    int i = 0;
    while (i < j)
    {
      paramFunction2.invoke(Integer.valueOf(paramSparseIntArray.keyAt(i)), Integer.valueOf(paramSparseIntArray.valueAt(i)));
      i += 1;
    }
  }
  
  public static final int getOrDefault(@NotNull SparseIntArray paramSparseIntArray, int paramInt1, int paramInt2)
  {
    return paramSparseIntArray.get(paramInt1, paramInt2);
  }
  
  public static final int getOrElse(@NotNull SparseIntArray paramSparseIntArray, int paramInt, @NotNull Function0<Integer> paramFunction0)
  {
    paramInt = paramSparseIntArray.indexOfKey(paramInt);
    if (paramInt != -1) {
      return paramSparseIntArray.valueAt(paramInt);
    }
    return ((Number)paramFunction0.invoke()).intValue();
  }
  
  public static final int getSize(@NotNull SparseIntArray paramSparseIntArray)
  {
    return paramSparseIntArray.size();
  }
  
  public static final boolean isEmpty(@NotNull SparseIntArray paramSparseIntArray)
  {
    return paramSparseIntArray.size() == 0;
  }
  
  public static final boolean isNotEmpty(@NotNull SparseIntArray paramSparseIntArray)
  {
    return paramSparseIntArray.size() != 0;
  }
  
  @NotNull
  public static final IntIterator keyIterator(@NotNull SparseIntArray paramSparseIntArray)
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
        SparseIntArray localSparseIntArray = receiver$0;
        int i = index;
        index = (i + 1);
        return localSparseIntArray.keyAt(i);
      }
      
      public final void setIndex(int paramAnonymousInt)
      {
        index = paramAnonymousInt;
      }
    };
  }
  
  @NotNull
  public static final SparseIntArray plus(@NotNull SparseIntArray paramSparseIntArray1, @NotNull SparseIntArray paramSparseIntArray2)
  {
    SparseIntArray localSparseIntArray = new SparseIntArray(paramSparseIntArray1.size() + paramSparseIntArray2.size());
    putAll(localSparseIntArray, paramSparseIntArray1);
    putAll(localSparseIntArray, paramSparseIntArray2);
    return localSparseIntArray;
  }
  
  public static final void putAll(@NotNull SparseIntArray paramSparseIntArray1, @NotNull SparseIntArray paramSparseIntArray2)
  {
    int j = paramSparseIntArray2.size();
    int i = 0;
    while (i < j)
    {
      paramSparseIntArray1.put(paramSparseIntArray2.keyAt(i), paramSparseIntArray2.valueAt(i));
      i += 1;
    }
  }
  
  public static final boolean remove(@NotNull SparseIntArray paramSparseIntArray, int paramInt1, int paramInt2)
  {
    paramInt1 = paramSparseIntArray.indexOfKey(paramInt1);
    if ((paramInt1 != -1) && (paramInt2 == paramSparseIntArray.valueAt(paramInt1)))
    {
      paramSparseIntArray.removeAt(paramInt1);
      return true;
    }
    return false;
  }
  
  public static final void set(@NotNull SparseIntArray paramSparseIntArray, int paramInt1, int paramInt2)
  {
    paramSparseIntArray.put(paramInt1, paramInt2);
  }
  
  @NotNull
  public static final IntIterator valueIterator(@NotNull SparseIntArray paramSparseIntArray)
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
        SparseIntArray localSparseIntArray = receiver$0;
        int i = index;
        index = (i + 1);
        return localSparseIntArray.valueAt(i);
      }
      
      public final void setIndex(int paramAnonymousInt)
      {
        index = paramAnonymousInt;
      }
    };
  }
}

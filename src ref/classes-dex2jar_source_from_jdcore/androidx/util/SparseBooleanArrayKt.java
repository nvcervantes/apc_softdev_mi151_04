package androidx.util;

import android.util.SparseBooleanArray;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.collections.BooleanIterator;
import kotlin.collections.IntIterator;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function2;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000>\n\000\n\002\020\b\n\002\030\002\n\002\b\003\n\002\020\013\n\002\b\005\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\b\n\002\030\002\n\000\032\025\020\005\032\0020\006*\0020\0022\006\020\007\032\0020\001H\n\032\025\020\b\032\0020\006*\0020\0022\006\020\007\032\0020\001H\b\032\025\020\t\032\0020\006*\0020\0022\006\020\n\032\0020\006H\b\032E\020\013\032\0020\f*\0020\00226\020\r\0322\022\023\022\0210\001¢\006\f\b\017\022\b\b\020\022\004\b\b(\007\022\023\022\0210\006¢\006\f\b\017\022\b\b\020\022\004\b\b(\n\022\004\022\0020\f0\016H\b\032\035\020\021\032\0020\006*\0020\0022\006\020\007\032\0020\0012\006\020\022\032\0020\006H\b\032#\020\023\032\0020\006*\0020\0022\006\020\007\032\0020\0012\f\020\022\032\b\022\004\022\0020\0060\024H\b\032\r\020\025\032\0020\006*\0020\002H\b\032\r\020\026\032\0020\006*\0020\002H\b\032\n\020\027\032\0020\030*\0020\002\032\025\020\031\032\0020\002*\0020\0022\006\020\032\032\0020\002H\002\032\022\020\033\032\0020\f*\0020\0022\006\020\032\032\0020\002\032\032\020\034\032\0020\006*\0020\0022\006\020\007\032\0020\0012\006\020\n\032\0020\006\032\022\020\035\032\0020\f*\0020\0022\006\020\036\032\0020\001\032\035\020\037\032\0020\f*\0020\0022\006\020\007\032\0020\0012\006\020\n\032\0020\006H\n\032\n\020 \032\0020!*\0020\002\"\026\020\000\032\0020\001*\0020\0028Æ\002¢\006\006\032\004\b\003\020\004¨\006\""}, d2={"size", "", "Landroid/util/SparseBooleanArray;", "getSize", "(Landroid/util/SparseBooleanArray;)I", "contains", "", "key", "containsKey", "containsValue", "value", "forEach", "", "action", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "getOrDefault", "defaultValue", "getOrElse", "Lkotlin/Function0;", "isEmpty", "isNotEmpty", "keyIterator", "Lkotlin/collections/IntIterator;", "plus", "other", "putAll", "remove", "removeAt", "index", "set", "valueIterator", "Lkotlin/collections/BooleanIterator;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class SparseBooleanArrayKt
{
  public static final boolean contains(@NotNull SparseBooleanArray paramSparseBooleanArray, int paramInt)
  {
    return paramSparseBooleanArray.indexOfKey(paramInt) >= 0;
  }
  
  public static final boolean containsKey(@NotNull SparseBooleanArray paramSparseBooleanArray, int paramInt)
  {
    return paramSparseBooleanArray.indexOfKey(paramInt) >= 0;
  }
  
  public static final boolean containsValue(@NotNull SparseBooleanArray paramSparseBooleanArray, boolean paramBoolean)
  {
    return paramSparseBooleanArray.indexOfValue(paramBoolean) != -1;
  }
  
  public static final void forEach(@NotNull SparseBooleanArray paramSparseBooleanArray, @NotNull Function2<? super Integer, ? super Boolean, Unit> paramFunction2)
  {
    int j = paramSparseBooleanArray.size();
    int i = 0;
    while (i < j)
    {
      paramFunction2.invoke(Integer.valueOf(paramSparseBooleanArray.keyAt(i)), Boolean.valueOf(paramSparseBooleanArray.valueAt(i)));
      i += 1;
    }
  }
  
  public static final boolean getOrDefault(@NotNull SparseBooleanArray paramSparseBooleanArray, int paramInt, boolean paramBoolean)
  {
    return paramSparseBooleanArray.get(paramInt, paramBoolean);
  }
  
  public static final boolean getOrElse(@NotNull SparseBooleanArray paramSparseBooleanArray, int paramInt, @NotNull Function0<Boolean> paramFunction0)
  {
    paramInt = paramSparseBooleanArray.indexOfKey(paramInt);
    if (paramInt != -1) {
      return paramSparseBooleanArray.valueAt(paramInt);
    }
    return ((Boolean)paramFunction0.invoke()).booleanValue();
  }
  
  public static final int getSize(@NotNull SparseBooleanArray paramSparseBooleanArray)
  {
    return paramSparseBooleanArray.size();
  }
  
  public static final boolean isEmpty(@NotNull SparseBooleanArray paramSparseBooleanArray)
  {
    return paramSparseBooleanArray.size() == 0;
  }
  
  public static final boolean isNotEmpty(@NotNull SparseBooleanArray paramSparseBooleanArray)
  {
    return paramSparseBooleanArray.size() != 0;
  }
  
  @NotNull
  public static final IntIterator keyIterator(@NotNull SparseBooleanArray paramSparseBooleanArray)
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
        SparseBooleanArray localSparseBooleanArray = receiver$0;
        int i = index;
        index = (i + 1);
        return localSparseBooleanArray.keyAt(i);
      }
      
      public final void setIndex(int paramAnonymousInt)
      {
        index = paramAnonymousInt;
      }
    };
  }
  
  @NotNull
  public static final SparseBooleanArray plus(@NotNull SparseBooleanArray paramSparseBooleanArray1, @NotNull SparseBooleanArray paramSparseBooleanArray2)
  {
    SparseBooleanArray localSparseBooleanArray = new SparseBooleanArray(paramSparseBooleanArray1.size() + paramSparseBooleanArray2.size());
    putAll(localSparseBooleanArray, paramSparseBooleanArray1);
    putAll(localSparseBooleanArray, paramSparseBooleanArray2);
    return localSparseBooleanArray;
  }
  
  public static final void putAll(@NotNull SparseBooleanArray paramSparseBooleanArray1, @NotNull SparseBooleanArray paramSparseBooleanArray2)
  {
    int j = paramSparseBooleanArray2.size();
    int i = 0;
    while (i < j)
    {
      paramSparseBooleanArray1.put(paramSparseBooleanArray2.keyAt(i), paramSparseBooleanArray2.valueAt(i));
      i += 1;
    }
  }
  
  public static final boolean remove(@NotNull SparseBooleanArray paramSparseBooleanArray, int paramInt, boolean paramBoolean)
  {
    int i = paramSparseBooleanArray.indexOfKey(paramInt);
    if ((i != -1) && (paramBoolean == paramSparseBooleanArray.valueAt(i)))
    {
      paramSparseBooleanArray.delete(paramInt);
      return true;
    }
    return false;
  }
  
  public static final void removeAt(@NotNull SparseBooleanArray paramSparseBooleanArray, int paramInt)
  {
    paramSparseBooleanArray.delete(paramSparseBooleanArray.keyAt(paramInt));
  }
  
  public static final void set(@NotNull SparseBooleanArray paramSparseBooleanArray, int paramInt, boolean paramBoolean)
  {
    paramSparseBooleanArray.put(paramInt, paramBoolean);
  }
  
  @NotNull
  public static final BooleanIterator valueIterator(@NotNull SparseBooleanArray paramSparseBooleanArray)
  {
    (BooleanIterator)new BooleanIterator()
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
      
      public boolean nextBoolean()
      {
        SparseBooleanArray localSparseBooleanArray = receiver$0;
        int i = index;
        index = (i + 1);
        return localSparseBooleanArray.valueAt(i);
      }
      
      public final void setIndex(int paramAnonymousInt)
      {
        index = paramAnonymousInt;
      }
    };
  }
}

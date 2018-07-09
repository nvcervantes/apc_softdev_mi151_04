package kotlin.jvm.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\006\n\002\020\021\n\002\b\004\n\002\020\002\n\002\b\t\b&\030\000*\b\b\000\020\001*\0020\0022\0020\002B\r\022\006\020\003\032\0020\004¢\006\002\020\005J\023\020\017\032\0020\0202\006\020\021\032\0028\000¢\006\002\020\022J\b\020\003\032\0020\004H\004J\035\020\023\032\0028\0002\006\020\024\032\0028\0002\006\020\025\032\0028\000H\004¢\006\002\020\026J\021\020\027\032\0020\004*\0028\000H$¢\006\002\020\030R\032\020\006\032\0020\004X\016¢\006\016\n\000\032\004\b\007\020\b\"\004\b\t\020\005R\016\020\003\032\0020\004X\004¢\006\002\n\000R \020\n\032\n\022\006\022\004\030\0018\0000\0138\002X\004¢\006\n\n\002\020\016\022\004\b\f\020\r¨\006\031"}, d2={"Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "T", "", "size", "", "(I)V", "position", "getPosition", "()I", "setPosition", "spreads", "", "spreads$annotations", "()V", "[Ljava/lang/Object;", "addSpread", "", "spreadArgument", "(Ljava/lang/Object;)V", "toArray", "values", "result", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "getSize", "(Ljava/lang/Object;)I", "kotlin-runtime"}, k=1, mv={1, 1, 9})
public abstract class PrimitiveSpreadBuilder<T>
{
  private int position;
  private final int size;
  private final T[] spreads;
  
  public PrimitiveSpreadBuilder(int paramInt)
  {
    size = paramInt;
    spreads = new Object[size];
  }
  
  public final void addSpread(@NotNull T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramT, "spreadArgument");
    Object[] arrayOfObject = spreads;
    int i = position;
    position = (i + 1);
    arrayOfObject[i] = paramT;
  }
  
  protected final int getPosition()
  {
    return position;
  }
  
  protected abstract int getSize(@NotNull T paramT);
  
  protected final void setPosition(int paramInt)
  {
    position = paramInt;
  }
  
  protected final int size()
  {
    int m = size - 1;
    int k = 0;
    int j = 0;
    if (m >= 0)
    {
      int i = 0;
      for (;;)
      {
        Object localObject = spreads[i];
        if (localObject != null) {
          k = getSize(localObject);
        } else {
          k = 1;
        }
        j += k;
        k = j;
        if (i == m) {
          break;
        }
        i += 1;
      }
    }
    return k;
  }
  
  @NotNull
  protected final T toArray(@NotNull T paramT1, @NotNull T paramT2)
  {
    Intrinsics.checkParameterIsNotNull(paramT1, "values");
    Intrinsics.checkParameterIsNotNull(paramT2, "result");
    int i1 = size - 1;
    int i = 0;
    int j;
    if (i1 >= 0)
    {
      int m = 0;
      j = m;
      i = j;
      int n = j;
      int k;
      for (;;)
      {
        Object localObject = spreads[m];
        k = n;
        j = i;
        if (localObject != null)
        {
          j = i;
          if (n < m)
          {
            j = m - n;
            System.arraycopy(paramT1, n, paramT2, i, j);
            j = i + j;
          }
          i = getSize(localObject);
          System.arraycopy(localObject, 0, paramT2, j, i);
          j += i;
          k = m + 1;
        }
        if (m == i1) {
          break;
        }
        m += 1;
        n = k;
        i = j;
      }
      i = k;
    }
    else
    {
      j = 0;
    }
    if (i < size) {
      System.arraycopy(paramT1, i, paramT2, j, size - i);
    }
    return paramT2;
  }
}

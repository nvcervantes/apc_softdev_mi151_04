package kotlin.jvm.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\002\030\002\n\002\020\024\n\000\n\002\020\b\n\002\b\003\n\002\020\002\n\000\n\002\020\007\n\002\b\003\030\0002\b\022\004\022\0020\0020\001B\r\022\006\020\003\032\0020\004¢\006\002\020\005J\016\020\007\032\0020\b2\006\020\t\032\0020\nJ\006\020\013\032\0020\002J\f\020\f\032\0020\004*\0020\002H\024R\016\020\006\032\0020\002X\004¢\006\002\n\000¨\006\r"}, d2={"Lkotlin/jvm/internal/FloatSpreadBuilder;", "Lkotlin/jvm/internal/PrimitiveSpreadBuilder;", "", "size", "", "(I)V", "values", "add", "", "value", "", "toArray", "getSize", "kotlin-runtime"}, k=1, mv={1, 1, 9})
public final class FloatSpreadBuilder
  extends PrimitiveSpreadBuilder<float[]>
{
  private final float[] values;
  
  public FloatSpreadBuilder(int paramInt)
  {
    super(paramInt);
    values = new float[paramInt];
  }
  
  public final void add(float paramFloat)
  {
    float[] arrayOfFloat = values;
    int i = getPosition();
    setPosition(i + 1);
    arrayOfFloat[i] = paramFloat;
  }
  
  protected int getSize(@NotNull float[] paramArrayOfFloat)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfFloat, "$receiver");
    return paramArrayOfFloat.length;
  }
  
  @NotNull
  public final float[] toArray()
  {
    return (float[])toArray(values, new float[size()]);
  }
}

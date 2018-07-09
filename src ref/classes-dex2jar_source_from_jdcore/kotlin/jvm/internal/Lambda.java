package kotlin.jvm.internal;

import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\002\b\003\n\002\020\016\n\002\b\002\b&\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\b\020\005\032\0020\003H\026J\020\020\006\032\n \b*\004\030\0010\0070\007H\026R\016\020\002\032\0020\003X\004¢\006\002\n\000¨\006\t"}, d2={"Lkotlin/jvm/internal/Lambda;", "Lkotlin/jvm/internal/FunctionBase;", "arity", "", "(I)V", "getArity", "toString", "", "kotlin.jvm.PlatformType", "kotlin-runtime"}, k=1, mv={1, 1, 9})
public abstract class Lambda
  implements FunctionBase
{
  private final int arity;
  
  public Lambda(int paramInt)
  {
    arity = paramInt;
  }
  
  public int getArity()
  {
    return arity;
  }
  
  public String toString()
  {
    return Reflection.renderLambdaToString(this);
  }
}

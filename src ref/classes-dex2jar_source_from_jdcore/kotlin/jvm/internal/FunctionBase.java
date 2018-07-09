package kotlin.jvm.internal;

import java.io.Serializable;
import kotlin.Function;

public abstract interface FunctionBase
  extends Function, Serializable
{
  public abstract int getArity();
}

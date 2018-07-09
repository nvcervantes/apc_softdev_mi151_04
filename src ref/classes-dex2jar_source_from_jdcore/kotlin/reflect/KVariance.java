package kotlin.reflect;

import kotlin.Metadata;
import kotlin.SinceKotlin;

@Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\020\020\n\002\b\005\b\001\030\0002\b\022\004\022\0020\0000\001B\007\b\002¢\006\002\020\002j\002\b\003j\002\b\004j\002\b\005¨\006\006"}, d2={"Lkotlin/reflect/KVariance;", "", "(Ljava/lang/String;I)V", "INVARIANT", "IN", "OUT", "kotlin-runtime"}, k=1, mv={1, 1, 9})
@SinceKotlin(version="1.1")
public enum KVariance
{
  static
  {
    KVariance localKVariance1 = new KVariance("INVARIANT", 0);
    INVARIANT = localKVariance1;
    KVariance localKVariance2 = new KVariance("IN", 1);
    IN = localKVariance2;
    KVariance localKVariance3 = new KVariance("OUT", 2);
    OUT = localKVariance3;
    $VALUES = new KVariance[] { localKVariance1, localKVariance2, localKVariance3 };
  }
  
  protected KVariance() {}
}

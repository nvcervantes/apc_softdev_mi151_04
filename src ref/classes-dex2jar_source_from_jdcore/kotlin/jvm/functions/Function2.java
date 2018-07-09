package kotlin.jvm.functions;

import kotlin.Function;
import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\020\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\005\bf\030\000*\006\b\000\020\001 \000*\006\b\001\020\002 \000*\006\b\002\020\003 \0012\b\022\004\022\002H\0030\004J\036\020\005\032\0028\0022\006\020\006\032\0028\0002\006\020\007\032\0028\001H¦\002¢\006\002\020\b¨\006\t"}, d2={"Lkotlin/jvm/functions/Function2;", "P1", "P2", "R", "Lkotlin/Function;", "invoke", "p1", "p2", "(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;", "kotlin-runtime"}, k=1, mv={1, 1, 9})
public abstract interface Function2<P1, P2, R>
  extends Function<R>
{
  public abstract R invoke(P1 paramP1, P2 paramP2);
}

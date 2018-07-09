package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.AbstractCoroutineContextElement;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\030\002\n\000\n\002\020\t\n\002\b\004\n\002\020\016\n\002\b\002\b\002\030\000 \t2\0020\001:\001\tB\r\022\006\020\002\032\0020\003¢\006\002\020\004J\b\020\007\032\0020\bH\026R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\n"}, d2={"Lkotlinx/coroutines/experimental/CoroutineId;", "Lkotlin/coroutines/experimental/AbstractCoroutineContextElement;", "id", "", "(J)V", "getId", "()J", "toString", "", "Key", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class CoroutineId
  extends AbstractCoroutineContextElement
{
  public static final Key Key = new Key(null);
  private final long id;
  
  public CoroutineId(long paramLong)
  {
    super((CoroutineContext.Key)Key);
    id = paramLong;
  }
  
  public final long getId()
  {
    return id;
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("CoroutineId(");
    localStringBuilder.append(id);
    localStringBuilder.append(')');
    return localStringBuilder.toString();
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\020\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\b\003\030\0002\b\022\004\022\0020\0020\001B\007\b\002¢\006\002\020\003¨\006\004"}, d2={"Lkotlinx/coroutines/experimental/CoroutineId$Key;", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "Lkotlinx/coroutines/experimental/CoroutineId;", "()V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static final class Key
    implements CoroutineContext.Key<CoroutineId>
  {
    private Key() {}
  }
}

package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.coroutines.experimental.AbstractCoroutineContextElement;
import kotlin.coroutines.experimental.CoroutineContext.Key;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\006\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\003\b\b\030\000 \0202\0020\001:\001\020B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\t\020\007\032\0020\003HÆ\003J\023\020\b\032\0020\0002\b\b\002\020\002\032\0020\003HÆ\001J\023\020\t\032\0020\n2\b\020\013\032\004\030\0010\fHÖ\003J\t\020\r\032\0020\016HÖ\001J\b\020\017\032\0020\003H\026R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\021"}, d2={"Lkotlinx/coroutines/experimental/CoroutineName;", "Lkotlin/coroutines/experimental/AbstractCoroutineContextElement;", "name", "", "(Ljava/lang/String;)V", "getName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "Key", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class CoroutineName
  extends AbstractCoroutineContextElement
{
  public static final Key Key = new Key(null);
  @NotNull
  private final String name;
  
  public CoroutineName(@NotNull String paramString)
  {
    super((CoroutineContext.Key)Key);
    name = paramString;
  }
  
  @NotNull
  public final String component1()
  {
    return name;
  }
  
  @NotNull
  public final CoroutineName copy(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "name");
    return new CoroutineName(paramString);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof CoroutineName))
      {
        paramObject = (CoroutineName)paramObject;
        if (Intrinsics.areEqual(name, name)) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final String getName()
  {
    return name;
  }
  
  public int hashCode()
  {
    String str = name;
    if (str != null) {
      return str.hashCode();
    }
    return 0;
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("CoroutineName(");
    localStringBuilder.append(name);
    localStringBuilder.append(')');
    return localStringBuilder.toString();
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\020\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\b\003\030\0002\b\022\004\022\0020\0020\001B\007\b\002¢\006\002\020\003¨\006\004"}, d2={"Lkotlinx/coroutines/experimental/CoroutineName$Key;", "Lkotlin/coroutines/experimental/CoroutineContext$Key;", "Lkotlinx/coroutines/experimental/CoroutineName;", "()V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
  public static final class Key
    implements CoroutineContext.Key<CoroutineName>
  {
    private Key() {}
  }
}

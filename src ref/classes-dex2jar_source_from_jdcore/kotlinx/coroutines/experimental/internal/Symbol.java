package kotlinx.coroutines.experimental.internal;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\005\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\b\020\007\032\0020\003H\026R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\b"}, d2={"Lkotlinx/coroutines/experimental/internal/Symbol;", "", "symbol", "", "(Ljava/lang/String;)V", "getSymbol", "()Ljava/lang/String;", "toString", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public final class Symbol
{
  @NotNull
  private final String symbol;
  
  public Symbol(@NotNull String paramString)
  {
    symbol = paramString;
  }
  
  @NotNull
  public final String getSymbol()
  {
    return symbol;
  }
  
  @NotNull
  public String toString()
  {
    return symbol;
  }
}

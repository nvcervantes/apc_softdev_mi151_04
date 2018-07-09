package com.byimplication.sakay.store;

import com.byimplication.sakay.model.Terminal;
import com.byimplication.sakay.model.TerminalType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\n\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005¢\006\002\020\006J\n\020\013\032\004\030\0010\001H\026J\t\020\f\032\0020\003HÆ\003J\t\020\r\032\0020\005HÆ\003J\035\020\016\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\005HÆ\001J\023\020\017\032\0020\0202\b\020\021\032\004\030\0010\022HÖ\003J\t\020\023\032\0020\024HÖ\001J\t\020\025\032\0020\026HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\007\020\bR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\t\020\n¨\006\027"}, d2={"Lcom/byimplication/sakay/store/TemporaryTerminal;", "Lcom/byimplication/sakay/store/StoreData;", "terminal", "Lcom/byimplication/sakay/model/Terminal;", "type", "Lcom/byimplication/sakay/model/TerminalType;", "(Lcom/byimplication/sakay/model/Terminal;Lcom/byimplication/sakay/model/TerminalType;)V", "getTerminal", "()Lcom/byimplication/sakay/model/Terminal;", "getType", "()Lcom/byimplication/sakay/model/TerminalType;", "clone", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class TemporaryTerminal
  implements StoreData
{
  @NotNull
  private final Terminal terminal;
  @NotNull
  private final TerminalType type;
  
  public TemporaryTerminal(@NotNull Terminal paramTerminal, @NotNull TerminalType paramTerminalType)
  {
    terminal = paramTerminal;
    type = paramTerminalType;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, null, null, 3, null);
  }
  
  @NotNull
  public final Terminal component1()
  {
    return terminal;
  }
  
  @NotNull
  public final TerminalType component2()
  {
    return type;
  }
  
  @NotNull
  public final TemporaryTerminal copy(@NotNull Terminal paramTerminal, @NotNull TerminalType paramTerminalType)
  {
    Intrinsics.checkParameterIsNotNull(paramTerminal, "terminal");
    Intrinsics.checkParameterIsNotNull(paramTerminalType, "type");
    return new TemporaryTerminal(paramTerminal, paramTerminalType);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof TemporaryTerminal))
      {
        paramObject = (TemporaryTerminal)paramObject;
        if ((Intrinsics.areEqual(terminal, terminal)) && (Intrinsics.areEqual(type, type))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final Terminal getTerminal()
  {
    return terminal;
  }
  
  @NotNull
  public final TerminalType getType()
  {
    return type;
  }
  
  public int hashCode()
  {
    Object localObject = terminal;
    int j = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = type;
    if (localObject != null) {
      j = localObject.hashCode();
    }
    return i * 31 + j;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("TemporaryTerminal(terminal=");
    localStringBuilder.append(terminal);
    localStringBuilder.append(", type=");
    localStringBuilder.append(type);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

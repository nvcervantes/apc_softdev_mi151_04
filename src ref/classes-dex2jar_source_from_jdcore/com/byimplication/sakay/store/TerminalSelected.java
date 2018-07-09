package com.byimplication.sakay.store;

import com.byimplication.sakay.model.TerminalType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\007\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\n\020\007\032\004\030\0010\001H\026J\t\020\b\032\0020\003HÆ\003J\023\020\t\032\0020\0002\b\b\002\020\002\032\0020\003HÆ\001J\023\020\n\032\0020\0132\b\020\f\032\004\030\0010\rHÖ\003J\t\020\016\032\0020\017HÖ\001J\t\020\020\032\0020\021HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\022"}, d2={"Lcom/byimplication/sakay/store/TerminalSelected;", "Lcom/byimplication/sakay/store/StoreData;", "which", "Lcom/byimplication/sakay/model/TerminalType;", "(Lcom/byimplication/sakay/model/TerminalType;)V", "getWhich", "()Lcom/byimplication/sakay/model/TerminalType;", "clone", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class TerminalSelected
  implements StoreData
{
  @NotNull
  private final TerminalType which;
  
  public TerminalSelected(@NotNull TerminalType paramTerminalType)
  {
    which = paramTerminalType;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, null, 1, null);
  }
  
  @NotNull
  public final TerminalType component1()
  {
    return which;
  }
  
  @NotNull
  public final TerminalSelected copy(@NotNull TerminalType paramTerminalType)
  {
    Intrinsics.checkParameterIsNotNull(paramTerminalType, "which");
    return new TerminalSelected(paramTerminalType);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof TerminalSelected))
      {
        paramObject = (TerminalSelected)paramObject;
        if (Intrinsics.areEqual(which, which)) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final TerminalType getWhich()
  {
    return which;
  }
  
  public int hashCode()
  {
    TerminalType localTerminalType = which;
    if (localTerminalType != null) {
      return localTerminalType.hashCode();
    }
    return 0;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("TerminalSelected(which=");
    localStringBuilder.append(which);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

package com.byimplication.sakay.store;

import com.byimplication.sakay.model.Terminal;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\007\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\n\020\007\032\004\030\0010\001H\026J\t\020\b\032\0020\003HÆ\003J\023\020\t\032\0020\0002\b\b\002\020\002\032\0020\003HÆ\001J\023\020\n\032\0020\0132\b\020\f\032\004\030\0010\rHÖ\003J\t\020\016\032\0020\017HÖ\001J\t\020\020\032\0020\021HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\022"}, d2={"Lcom/byimplication/sakay/store/HomeUpdated;", "Lcom/byimplication/sakay/store/StoreData;", "home", "Lcom/byimplication/sakay/model/Terminal;", "(Lcom/byimplication/sakay/model/Terminal;)V", "getHome", "()Lcom/byimplication/sakay/model/Terminal;", "clone", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class HomeUpdated
  implements StoreData
{
  @NotNull
  private final Terminal home;
  
  public HomeUpdated(@NotNull Terminal paramTerminal)
  {
    home = paramTerminal;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, null, 1, null);
  }
  
  @NotNull
  public final Terminal component1()
  {
    return home;
  }
  
  @NotNull
  public final HomeUpdated copy(@NotNull Terminal paramTerminal)
  {
    Intrinsics.checkParameterIsNotNull(paramTerminal, "home");
    return new HomeUpdated(paramTerminal);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof HomeUpdated))
      {
        paramObject = (HomeUpdated)paramObject;
        if (Intrinsics.areEqual(home, home)) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final Terminal getHome()
  {
    return home;
  }
  
  public int hashCode()
  {
    Terminal localTerminal = home;
    if (localTerminal != null) {
      return localTerminal.hashCode();
    }
    return 0;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("HomeUpdated(home=");
    localStringBuilder.append(home);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

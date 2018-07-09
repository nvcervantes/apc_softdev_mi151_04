package com.byimplication.sakay.action;

import com.byimplication.sakay.model.TerminalType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000.\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\002\b\013\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B!\022\b\020\002\032\004\030\0010\003\022\b\020\004\032\004\030\0010\003\022\006\020\005\032\0020\006¢\006\002\020\007J\013\020\r\032\004\030\0010\003HÆ\003J\013\020\016\032\004\030\0010\003HÆ\003J\t\020\017\032\0020\006HÆ\003J+\020\020\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\n\b\002\020\004\032\004\030\0010\0032\b\b\002\020\005\032\0020\006HÆ\001J\023\020\021\032\0020\0222\b\020\023\032\004\030\0010\024HÖ\003J\t\020\025\032\0020\026HÖ\001J\t\020\027\032\0020\003HÖ\001R\023\020\004\032\004\030\0010\003¢\006\b\n\000\032\004\b\b\020\tR\023\020\002\032\004\030\0010\003¢\006\b\n\000\032\004\b\n\020\tR\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\013\020\f¨\006\030"}, d2={"Lcom/byimplication/sakay/action/TerminalByPlaceId;", "Lcom/byimplication/sakay/action/Action;", "placeId", "", "description", "which", "Lcom/byimplication/sakay/model/TerminalType;", "(Ljava/lang/String;Ljava/lang/String;Lcom/byimplication/sakay/model/TerminalType;)V", "getDescription", "()Ljava/lang/String;", "getPlaceId", "getWhich", "()Lcom/byimplication/sakay/model/TerminalType;", "component1", "component2", "component3", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class TerminalByPlaceId
  implements Action
{
  @Nullable
  private final String description;
  @Nullable
  private final String placeId;
  @NotNull
  private final TerminalType which;
  
  public TerminalByPlaceId(@Nullable String paramString1, @Nullable String paramString2, @NotNull TerminalType paramTerminalType)
  {
    placeId = paramString1;
    description = paramString2;
    which = paramTerminalType;
  }
  
  @Nullable
  public final String component1()
  {
    return placeId;
  }
  
  @Nullable
  public final String component2()
  {
    return description;
  }
  
  @NotNull
  public final TerminalType component3()
  {
    return which;
  }
  
  @NotNull
  public final TerminalByPlaceId copy(@Nullable String paramString1, @Nullable String paramString2, @NotNull TerminalType paramTerminalType)
  {
    Intrinsics.checkParameterIsNotNull(paramTerminalType, "which");
    return new TerminalByPlaceId(paramString1, paramString2, paramTerminalType);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof TerminalByPlaceId))
      {
        paramObject = (TerminalByPlaceId)paramObject;
        if ((Intrinsics.areEqual(placeId, placeId)) && (Intrinsics.areEqual(description, description)) && (Intrinsics.areEqual(which, which))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final String getDescription()
  {
    return description;
  }
  
  @Nullable
  public final String getPlaceId()
  {
    return placeId;
  }
  
  @NotNull
  public final TerminalType getWhich()
  {
    return which;
  }
  
  public int hashCode()
  {
    Object localObject = placeId;
    int k = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = description;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = which;
    if (localObject != null) {
      k = localObject.hashCode();
    }
    return (i * 31 + j) * 31 + k;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("TerminalByPlaceId(placeId=");
    localStringBuilder.append(placeId);
    localStringBuilder.append(", description=");
    localStringBuilder.append(description);
    localStringBuilder.append(", which=");
    localStringBuilder.append(which);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

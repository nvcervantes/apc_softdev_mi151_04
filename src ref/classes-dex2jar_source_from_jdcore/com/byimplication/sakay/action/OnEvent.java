package com.byimplication.sakay.action;

import java.util.Map;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000,\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020$\n\002\b\t\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B!\022\006\020\002\032\0020\003\022\022\020\004\032\016\022\004\022\0020\003\022\004\022\0020\0030\005¢\006\002\020\006J\t\020\013\032\0020\003HÆ\003J\025\020\f\032\016\022\004\022\0020\003\022\004\022\0020\0030\005HÆ\003J)\020\r\032\0020\0002\b\b\002\020\002\032\0020\0032\024\b\002\020\004\032\016\022\004\022\0020\003\022\004\022\0020\0030\005HÆ\001J\023\020\016\032\0020\0172\b\020\020\032\004\030\0010\021HÖ\003J\t\020\022\032\0020\023HÖ\001J\t\020\024\032\0020\003HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\007\020\bR\035\020\004\032\016\022\004\022\0020\003\022\004\022\0020\0030\005¢\006\b\n\000\032\004\b\t\020\n¨\006\025"}, d2={"Lcom/byimplication/sakay/action/OnEvent;", "Lcom/byimplication/sakay/action/Action;", "eventName", "", "params", "", "(Ljava/lang/String;Ljava/util/Map;)V", "getEventName", "()Ljava/lang/String;", "getParams", "()Ljava/util/Map;", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class OnEvent
  implements Action
{
  @NotNull
  private final String eventName;
  @NotNull
  private final Map<String, String> params;
  
  public OnEvent(@NotNull String paramString, @NotNull Map<String, String> paramMap)
  {
    eventName = paramString;
    params = paramMap;
  }
  
  @NotNull
  public final String component1()
  {
    return eventName;
  }
  
  @NotNull
  public final Map<String, String> component2()
  {
    return params;
  }
  
  @NotNull
  public final OnEvent copy(@NotNull String paramString, @NotNull Map<String, String> paramMap)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "eventName");
    Intrinsics.checkParameterIsNotNull(paramMap, "params");
    return new OnEvent(paramString, paramMap);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof OnEvent))
      {
        paramObject = (OnEvent)paramObject;
        if ((Intrinsics.areEqual(eventName, eventName)) && (Intrinsics.areEqual(params, params))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final String getEventName()
  {
    return eventName;
  }
  
  @NotNull
  public final Map<String, String> getParams()
  {
    return params;
  }
  
  public int hashCode()
  {
    Object localObject = eventName;
    int j = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = params;
    if (localObject != null) {
      j = localObject.hashCode();
    }
    return i * 31 + j;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OnEvent(eventName=");
    localStringBuilder.append(eventName);
    localStringBuilder.append(", params=");
    localStringBuilder.append(params);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

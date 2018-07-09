package com.byimplication.sakay.action;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\006\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\002\b\002\b\b\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\t\020\007\032\0020\003HÆ\003J\023\020\b\032\0020\0002\b\b\002\020\002\032\0020\003HÆ\001J\023\020\t\032\0020\n2\b\020\013\032\004\030\0010\fHÖ\003J\t\020\r\032\0020\016HÖ\001J\t\020\017\032\0020\003HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\020"}, d2={"Lcom/byimplication/sakay/action/OnSimpleEvent;", "Lcom/byimplication/sakay/action/Action;", "eventName", "", "(Ljava/lang/String;)V", "getEventName", "()Ljava/lang/String;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class OnSimpleEvent
  implements Action
{
  @NotNull
  private final String eventName;
  
  public OnSimpleEvent(@NotNull String paramString)
  {
    eventName = paramString;
  }
  
  @NotNull
  public final String component1()
  {
    return eventName;
  }
  
  @NotNull
  public final OnSimpleEvent copy(@NotNull String paramString)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "eventName");
    return new OnSimpleEvent(paramString);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof OnSimpleEvent))
      {
        paramObject = (OnSimpleEvent)paramObject;
        if (Intrinsics.areEqual(eventName, eventName)) {}
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
  
  public int hashCode()
  {
    String str = eventName;
    if (str != null) {
      return str.hashCode();
    }
    return 0;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("OnSimpleEvent(eventName=");
    localStringBuilder.append(eventName);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

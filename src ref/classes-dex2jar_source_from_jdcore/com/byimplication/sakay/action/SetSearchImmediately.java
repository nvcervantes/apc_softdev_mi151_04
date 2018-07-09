package com.byimplication.sakay.action;

import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\030\002\n\000\n\002\020\013\n\002\b\004\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\007"}, d2={"Lcom/byimplication/sakay/action/SetSearchImmediately;", "Lcom/byimplication/sakay/action/Action;", "shouldSearch", "", "(Z)V", "getShouldSearch", "()Z", "app_release"}, k=1, mv={1, 1, 9})
public final class SetSearchImmediately
  implements Action
{
  private final boolean shouldSearch;
  
  public SetSearchImmediately(boolean paramBoolean)
  {
    shouldSearch = paramBoolean;
  }
  
  public final boolean getShouldSearch()
  {
    return shouldSearch;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
}

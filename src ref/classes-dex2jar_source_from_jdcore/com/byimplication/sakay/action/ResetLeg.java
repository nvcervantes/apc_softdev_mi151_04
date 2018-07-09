package com.byimplication.sakay.action;

import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\030\002\n\002\b\002\030\0002\0020\001B\005¢\006\002\020\002¨\006\003"}, d2={"Lcom/byimplication/sakay/action/ResetLeg;", "Lcom/byimplication/sakay/action/Action;", "()V", "app_release"}, k=1, mv={1, 1, 9})
public final class ResetLeg
  implements Action
{
  public ResetLeg() {}
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
}

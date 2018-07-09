package com.byimplication.sakay.action;

import com.byimplication.sakay.store.Dispatcher;
import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\020\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\bf\030\0002\0020\001J\b\020\002\032\0020\003H\026¨\006\004"}, d2={"Lcom/byimplication/sakay/action/Action;", "", "post", "", "app_release"}, k=1, mv={1, 1, 9})
public abstract interface Action
{
  public abstract void post();
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 9})
  public static final class DefaultImpls
  {
    public static void post(Action paramAction)
    {
      Dispatcher.INSTANCE.post(paramAction);
    }
  }
}

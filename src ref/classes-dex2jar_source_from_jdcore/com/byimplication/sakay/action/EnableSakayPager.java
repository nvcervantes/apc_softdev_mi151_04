package com.byimplication.sakay.action;

import android.view.MotionEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\006\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\021\022\n\b\002\020\002\032\004\030\0010\003¢\006\002\020\004J\013\020\007\032\004\030\0010\003HÆ\003J\025\020\b\032\0020\0002\n\b\002\020\002\032\004\030\0010\003HÆ\001J\023\020\t\032\0020\n2\b\020\013\032\004\030\0010\fHÖ\003J\t\020\r\032\0020\016HÖ\001J\t\020\017\032\0020\020HÖ\001R\023\020\002\032\004\030\0010\003¢\006\b\n\000\032\004\b\005\020\006¨\006\021"}, d2={"Lcom/byimplication/sakay/action/EnableSakayPager;", "Lcom/byimplication/sakay/action/Action;", "ev", "Landroid/view/MotionEvent;", "(Landroid/view/MotionEvent;)V", "getEv", "()Landroid/view/MotionEvent;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class EnableSakayPager
  implements Action
{
  @Nullable
  private final MotionEvent ev;
  
  public EnableSakayPager()
  {
    this(null, 1, null);
  }
  
  public EnableSakayPager(@Nullable MotionEvent paramMotionEvent)
  {
    ev = paramMotionEvent;
  }
  
  @Nullable
  public final MotionEvent component1()
  {
    return ev;
  }
  
  @NotNull
  public final EnableSakayPager copy(@Nullable MotionEvent paramMotionEvent)
  {
    return new EnableSakayPager(paramMotionEvent);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof EnableSakayPager))
      {
        paramObject = (EnableSakayPager)paramObject;
        if (Intrinsics.areEqual(ev, ev)) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final MotionEvent getEv()
  {
    return ev;
  }
  
  public int hashCode()
  {
    MotionEvent localMotionEvent = ev;
    if (localMotionEvent != null) {
      return localMotionEvent.hashCode();
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
    localStringBuilder.append("EnableSakayPager(ev=");
    localStringBuilder.append(ev);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

package com.byimplication.sakay.action;

import android.view.DragEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\006\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\t\020\007\032\0020\003HÆ\003J\023\020\b\032\0020\0002\b\b\002\020\002\032\0020\003HÆ\001J\023\020\t\032\0020\n2\b\020\013\032\004\030\0010\fHÖ\003J\t\020\r\032\0020\016HÖ\001J\t\020\017\032\0020\020HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\021"}, d2={"Lcom/byimplication/sakay/action/DragMap;", "Lcom/byimplication/sakay/action/Action;", "ev", "Landroid/view/DragEvent;", "(Landroid/view/DragEvent;)V", "getEv", "()Landroid/view/DragEvent;", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class DragMap
  implements Action
{
  @NotNull
  private final DragEvent ev;
  
  public DragMap(@NotNull DragEvent paramDragEvent)
  {
    ev = paramDragEvent;
  }
  
  @NotNull
  public final DragEvent component1()
  {
    return ev;
  }
  
  @NotNull
  public final DragMap copy(@NotNull DragEvent paramDragEvent)
  {
    Intrinsics.checkParameterIsNotNull(paramDragEvent, "ev");
    return new DragMap(paramDragEvent);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof DragMap))
      {
        paramObject = (DragMap)paramObject;
        if (Intrinsics.areEqual(ev, ev)) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final DragEvent getEv()
  {
    return ev;
  }
  
  public int hashCode()
  {
    DragEvent localDragEvent = ev;
    if (localDragEvent != null) {
      return localDragEvent.hashCode();
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
    localStringBuilder.append("DragMap(ev=");
    localStringBuilder.append(ev);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

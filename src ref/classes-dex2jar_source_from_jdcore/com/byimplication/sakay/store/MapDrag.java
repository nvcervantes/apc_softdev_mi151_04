package com.byimplication.sakay.store;

import android.view.DragEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\007\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\n\020\007\032\004\030\0010\001H\026J\t\020\b\032\0020\003HÆ\003J\023\020\t\032\0020\0002\b\b\002\020\002\032\0020\003HÆ\001J\023\020\n\032\0020\0132\b\020\f\032\004\030\0010\rHÖ\003J\t\020\016\032\0020\017HÖ\001J\t\020\020\032\0020\021HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\022"}, d2={"Lcom/byimplication/sakay/store/MapDrag;", "Lcom/byimplication/sakay/store/StoreData;", "ev", "Landroid/view/DragEvent;", "(Landroid/view/DragEvent;)V", "getEv", "()Landroid/view/DragEvent;", "clone", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class MapDrag
  implements StoreData
{
  @NotNull
  private final DragEvent ev;
  
  public MapDrag(@NotNull DragEvent paramDragEvent)
  {
    ev = paramDragEvent;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, null, 1, null);
  }
  
  @NotNull
  public final DragEvent component1()
  {
    return ev;
  }
  
  @NotNull
  public final MapDrag copy(@NotNull DragEvent paramDragEvent)
  {
    Intrinsics.checkParameterIsNotNull(paramDragEvent, "ev");
    return new MapDrag(paramDragEvent);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof MapDrag))
      {
        paramObject = (MapDrag)paramObject;
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
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("MapDrag(ev=");
    localStringBuilder.append(ev);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

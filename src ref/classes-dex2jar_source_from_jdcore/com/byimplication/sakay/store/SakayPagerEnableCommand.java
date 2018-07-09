package com.byimplication.sakay.store;

import android.view.MotionEvent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000*\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\007\n\002\020\013\n\000\n\002\020\000\n\000\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B\021\022\n\b\002\020\002\032\004\030\0010\003¢\006\002\020\004J\n\020\007\032\004\030\0010\001H\026J\013\020\b\032\004\030\0010\003HÆ\003J\025\020\t\032\0020\0002\n\b\002\020\002\032\004\030\0010\003HÆ\001J\023\020\n\032\0020\0132\b\020\f\032\004\030\0010\rHÖ\003J\t\020\016\032\0020\017HÖ\001J\t\020\020\032\0020\021HÖ\001R\023\020\002\032\004\030\0010\003¢\006\b\n\000\032\004\b\005\020\006¨\006\022"}, d2={"Lcom/byimplication/sakay/store/SakayPagerEnableCommand;", "Lcom/byimplication/sakay/store/StoreData;", "motionEvent", "Landroid/view/MotionEvent;", "(Landroid/view/MotionEvent;)V", "getMotionEvent", "()Landroid/view/MotionEvent;", "clone", "component1", "copy", "equals", "", "other", "", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class SakayPagerEnableCommand
  implements StoreData
{
  @Nullable
  private final MotionEvent motionEvent;
  
  public SakayPagerEnableCommand()
  {
    this(null, 1, null);
  }
  
  public SakayPagerEnableCommand(@Nullable MotionEvent paramMotionEvent)
  {
    motionEvent = paramMotionEvent;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, null, 1, null);
  }
  
  @Nullable
  public final MotionEvent component1()
  {
    return motionEvent;
  }
  
  @NotNull
  public final SakayPagerEnableCommand copy(@Nullable MotionEvent paramMotionEvent)
  {
    return new SakayPagerEnableCommand(paramMotionEvent);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof SakayPagerEnableCommand))
      {
        paramObject = (SakayPagerEnableCommand)paramObject;
        if (Intrinsics.areEqual(motionEvent, motionEvent)) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final MotionEvent getMotionEvent()
  {
    return motionEvent;
  }
  
  public int hashCode()
  {
    MotionEvent localMotionEvent = motionEvent;
    if (localMotionEvent != null) {
      return localMotionEvent.hashCode();
    }
    return 0;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("SakayPagerEnableCommand(motionEvent=");
    localStringBuilder.append(motionEvent);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

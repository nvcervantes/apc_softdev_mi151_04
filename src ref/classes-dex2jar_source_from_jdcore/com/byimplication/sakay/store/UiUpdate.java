package com.byimplication.sakay.store;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\002\b\n\n\002\020\013\n\000\n\002\020\000\n\002\b\002\n\002\020\016\n\000\b\b\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003¢\006\002\020\005J\n\020\t\032\004\030\0010\001H\026J\t\020\n\032\0020\003HÆ\003J\t\020\013\032\0020\003HÆ\003J\035\020\f\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\003HÆ\001J\023\020\r\032\0020\0162\b\020\017\032\004\030\0010\020HÖ\003J\t\020\021\032\0020\003HÖ\001J\t\020\022\032\0020\023HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\006\020\007R\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\b\020\007¨\006\024"}, d2={"Lcom/byimplication/sakay/store/UiUpdate;", "Lcom/byimplication/sakay/store/StoreData;", "currentItinerary", "", "currentLeg", "(II)V", "getCurrentItinerary", "()I", "getCurrentLeg", "clone", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class UiUpdate
  implements StoreData
{
  private final int currentItinerary;
  private final int currentLeg;
  
  public UiUpdate(int paramInt1, int paramInt2)
  {
    currentItinerary = paramInt1;
    currentLeg = paramInt2;
  }
  
  @Nullable
  public StoreData clone()
  {
    return (StoreData)copy$default(this, 0, 0, 3, null);
  }
  
  public final int component1()
  {
    return currentItinerary;
  }
  
  public final int component2()
  {
    return currentLeg;
  }
  
  @NotNull
  public final UiUpdate copy(int paramInt1, int paramInt2)
  {
    return new UiUpdate(paramInt1, paramInt2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof UiUpdate))
      {
        paramObject = (UiUpdate)paramObject;
        int i;
        if (currentItinerary == currentItinerary) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0)
        {
          if (currentLeg == currentLeg) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0) {
            return true;
          }
        }
      }
      return false;
    }
    return true;
  }
  
  public final int getCurrentItinerary()
  {
    return currentItinerary;
  }
  
  public final int getCurrentLeg()
  {
    return currentLeg;
  }
  
  public int hashCode()
  {
    return currentItinerary * 31 + currentLeg;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("UiUpdate(currentItinerary=");
    localStringBuilder.append(currentItinerary);
    localStringBuilder.append(", currentLeg=");
    localStringBuilder.append(currentLeg);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

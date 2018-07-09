package com.byimplication.sakay.action;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\002\b\t\n\002\020\013\n\000\n\002\020\000\n\002\b\002\n\002\020\016\n\000\b\b\030\0002\0020\001B\025\022\006\020\002\032\0020\003\022\006\020\004\032\0020\003¢\006\002\020\005J\t\020\t\032\0020\003HÆ\003J\t\020\n\032\0020\003HÆ\003J\035\020\013\032\0020\0002\b\b\002\020\002\032\0020\0032\b\b\002\020\004\032\0020\003HÆ\001J\023\020\f\032\0020\r2\b\020\016\032\004\030\0010\017HÖ\003J\t\020\020\032\0020\003HÖ\001J\t\020\021\032\0020\022HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\006\020\007R\021\020\004\032\0020\003¢\006\b\n\000\032\004\b\b\020\007¨\006\023"}, d2={"Lcom/byimplication/sakay/action/UpdateUi;", "Lcom/byimplication/sakay/action/Action;", "newItinerary", "", "newLeg", "(II)V", "getNewItinerary", "()I", "getNewLeg", "component1", "component2", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class UpdateUi
  implements Action
{
  private final int newItinerary;
  private final int newLeg;
  
  public UpdateUi(int paramInt1, int paramInt2)
  {
    newItinerary = paramInt1;
    newLeg = paramInt2;
  }
  
  public final int component1()
  {
    return newItinerary;
  }
  
  public final int component2()
  {
    return newLeg;
  }
  
  @NotNull
  public final UpdateUi copy(int paramInt1, int paramInt2)
  {
    return new UpdateUi(paramInt1, paramInt2);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof UpdateUi))
      {
        paramObject = (UpdateUi)paramObject;
        int i;
        if (newItinerary == newItinerary) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0)
        {
          if (newLeg == newLeg) {
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
  
  public final int getNewItinerary()
  {
    return newItinerary;
  }
  
  public final int getNewLeg()
  {
    return newLeg;
  }
  
  public int hashCode()
  {
    return newItinerary * 31 + newLeg;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("UpdateUi(newItinerary=");
    localStringBuilder.append(newItinerary);
    localStringBuilder.append(", newLeg=");
    localStringBuilder.append(newLeg);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

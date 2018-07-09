package com.byimplication.sakay.action;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\002\b\006\n\002\020\013\n\000\n\002\020\000\n\002\b\002\n\002\020\016\n\000\b\b\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004J\t\020\007\032\0020\003HÆ\003J\023\020\b\032\0020\0002\b\b\002\020\002\032\0020\003HÆ\001J\023\020\t\032\0020\n2\b\020\013\032\004\030\0010\fHÖ\003J\t\020\r\032\0020\003HÖ\001J\t\020\016\032\0020\017HÖ\001R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\005\020\006¨\006\020"}, d2={"Lcom/byimplication/sakay/action/UpdateLeg;", "Lcom/byimplication/sakay/action/Action;", "newLeg", "", "(I)V", "getNewLeg", "()I", "component1", "copy", "equals", "", "other", "", "hashCode", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class UpdateLeg
  implements Action
{
  private final int newLeg;
  
  public UpdateLeg(int paramInt)
  {
    newLeg = paramInt;
  }
  
  public final int component1()
  {
    return newLeg;
  }
  
  @NotNull
  public final UpdateLeg copy(int paramInt)
  {
    return new UpdateLeg(paramInt);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof UpdateLeg))
      {
        paramObject = (UpdateLeg)paramObject;
        int i;
        if (newLeg == newLeg) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0) {
          return true;
        }
      }
      return false;
    }
    return true;
  }
  
  public final int getNewLeg()
  {
    return newLeg;
  }
  
  public int hashCode()
  {
    return newLeg;
  }
  
  public void post()
  {
    Action.DefaultImpls.post(this);
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("UpdateLeg(newLeg=");
    localStringBuilder.append(newLeg);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

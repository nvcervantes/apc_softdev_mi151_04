package com.google.common.geometry;

public final class S2Edge
{
  private final S2Point end;
  private final S2Point start;
  
  public S2Edge(S2Point paramS2Point1, S2Point paramS2Point2)
  {
    start = paramS2Point1;
    end = paramS2Point2;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    if (paramObject != null)
    {
      if (!(paramObject instanceof S2Edge)) {
        return false;
      }
      paramObject = (S2Edge)paramObject;
      boolean bool1 = bool2;
      if (getStart().equals(paramObject.getStart()))
      {
        bool1 = bool2;
        if (getEnd().equals(paramObject.getEnd())) {
          bool1 = true;
        }
      }
      return bool1;
    }
    return false;
  }
  
  public S2Point getEnd()
  {
    return end;
  }
  
  public S2Point getStart()
  {
    return start;
  }
  
  public int hashCode()
  {
    return getStart().hashCode() - getEnd().hashCode();
  }
  
  public String toString()
  {
    return String.format("Edge: (%s -> %s)\n   or [%s -> %s]", new Object[] { start.toDegreesString(), end.toDegreesString(), start, end });
  }
}

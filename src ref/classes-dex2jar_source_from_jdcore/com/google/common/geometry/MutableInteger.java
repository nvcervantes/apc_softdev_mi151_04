package com.google.common.geometry;

public class MutableInteger
{
  private Integer cachedIntegerValue = null;
  private int value;
  
  public MutableInteger(int paramInt)
  {
    value = paramInt;
  }
  
  public void add(int paramInt)
  {
    setValue(value + paramInt);
  }
  
  public void decrement()
  {
    subtract(1);
  }
  
  public boolean equals(Object paramObject)
  {
    return ((paramObject instanceof MutableInteger)) && (value == value);
  }
  
  public int hashCode()
  {
    return integerValue().hashCode();
  }
  
  public void increment()
  {
    add(1);
  }
  
  public int intValue()
  {
    return value;
  }
  
  public Integer integerValue()
  {
    if (cachedIntegerValue == null) {
      cachedIntegerValue = Integer.valueOf(intValue());
    }
    return cachedIntegerValue;
  }
  
  public void setValue(int paramInt)
  {
    value = paramInt;
    cachedIntegerValue = null;
  }
  
  public void subtract(int paramInt)
  {
    add(paramInt * -1);
  }
  
  public String toString()
  {
    return String.valueOf(value);
  }
}

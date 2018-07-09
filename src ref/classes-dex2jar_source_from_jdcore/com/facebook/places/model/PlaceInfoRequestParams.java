package com.facebook.places.model;

import java.util.HashSet;
import java.util.Set;

public final class PlaceInfoRequestParams
{
  private final Set<String> fields = new HashSet();
  private final String placeId;
  
  private PlaceInfoRequestParams(Builder paramBuilder)
  {
    placeId = placeId;
    fields.addAll(fields);
  }
  
  public Set<String> getFields()
  {
    return fields;
  }
  
  public String getPlaceId()
  {
    return placeId;
  }
  
  public static class Builder
  {
    private final Set<String> fields = new HashSet();
    private String placeId;
    
    public Builder() {}
    
    public Builder addField(String paramString)
    {
      fields.add(paramString);
      return this;
    }
    
    public Builder addFields(String[] paramArrayOfString)
    {
      int i = 0;
      int j = paramArrayOfString.length;
      while (i < j)
      {
        String str = paramArrayOfString[i];
        fields.add(str);
        i += 1;
      }
      return this;
    }
    
    public PlaceInfoRequestParams build()
    {
      return new PlaceInfoRequestParams(this, null);
    }
    
    public Builder setPlaceId(String paramString)
    {
      placeId = paramString;
      return this;
    }
  }
}

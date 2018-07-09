package com.facebook.places.model;

import java.util.HashSet;
import java.util.Set;

public final class PlaceSearchRequestParams
{
  private final Set<String> categories = new HashSet();
  private final int distance;
  private final Set<String> fields = new HashSet();
  private final int limit;
  private final String searchText;
  
  private PlaceSearchRequestParams(Builder paramBuilder)
  {
    distance = distance;
    limit = limit;
    searchText = searchText;
    categories.addAll(categories);
    fields.addAll(fields);
  }
  
  public Set<String> getCategories()
  {
    return categories;
  }
  
  public int getDistance()
  {
    return distance;
  }
  
  public Set<String> getFields()
  {
    return fields;
  }
  
  public int getLimit()
  {
    return limit;
  }
  
  public String getSearchText()
  {
    return searchText;
  }
  
  public static class Builder
  {
    private final Set<String> categories = new HashSet();
    private int distance;
    private final Set<String> fields = new HashSet();
    private int limit;
    private String searchText;
    
    public Builder() {}
    
    public Builder addCategory(String paramString)
    {
      categories.add(paramString);
      return this;
    }
    
    public Builder addField(String paramString)
    {
      fields.add(paramString);
      return this;
    }
    
    public PlaceSearchRequestParams build()
    {
      return new PlaceSearchRequestParams(this, null);
    }
    
    public Builder setDistance(int paramInt)
    {
      distance = paramInt;
      return this;
    }
    
    public Builder setLimit(int paramInt)
    {
      limit = paramInt;
      return this;
    }
    
    public Builder setSearchText(String paramString)
    {
      searchText = paramString;
      return this;
    }
  }
}

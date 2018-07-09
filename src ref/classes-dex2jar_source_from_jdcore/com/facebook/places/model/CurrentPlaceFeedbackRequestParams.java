package com.facebook.places.model;

public class CurrentPlaceFeedbackRequestParams
{
  private final String placeId;
  private final String tracking;
  private final Boolean wasHere;
  
  private CurrentPlaceFeedbackRequestParams(Builder paramBuilder)
  {
    tracking = tracking;
    placeId = placeId;
    wasHere = wasHere;
  }
  
  public String getPlaceId()
  {
    return placeId;
  }
  
  public String getTracking()
  {
    return tracking;
  }
  
  public Boolean wasHere()
  {
    return wasHere;
  }
  
  public static class Builder
  {
    private String placeId;
    private String tracking;
    private Boolean wasHere;
    
    public Builder() {}
    
    public CurrentPlaceFeedbackRequestParams build()
    {
      return new CurrentPlaceFeedbackRequestParams(this, null);
    }
    
    public Builder setPlaceId(String paramString)
    {
      placeId = paramString;
      return this;
    }
    
    public Builder setTracking(String paramString)
    {
      tracking = paramString;
      return this;
    }
    
    public Builder setWasHere(boolean paramBoolean)
    {
      wasHere = Boolean.valueOf(paramBoolean);
      return this;
    }
  }
}

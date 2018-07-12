package com.google.android.gms.location.places;

import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;

public class PlacesStatusCodes
  extends CommonStatusCodes
{
  public static final int ACCESS_NOT_CONFIGURED = 9003;
  public static final int DEVICE_RATE_LIMIT_EXCEEDED = 9006;
  public static final int INVALID_APP = 9008;
  public static final int INVALID_ARGUMENT = 9004;
  public static final int KEY_EXPIRED = 9007;
  public static final int KEY_INVALID = 9002;
  public static final int RATE_LIMIT_EXCEEDED = 9005;
  public static final int USAGE_LIMIT_EXCEEDED = 9001;
  
  private PlacesStatusCodes() {}
  
  public static String getStatusCodeString(int paramInt)
  {
    if (paramInt != 9051)
    {
      if (paramInt != 9150)
      {
        switch (paramInt)
        {
        default: 
          switch (paramInt)
          {
          default: 
            switch (paramInt)
            {
            default: 
              return CommonStatusCodes.getStatusCodeString(paramInt);
            case 9202: 
              return "PLACES_API_PERSONALIZED_DATA_ACCESS_REJECTED";
            }
            return "PLACES_API_PERSONALIZED_DATA_ACCESS_APPROVED";
          case 9102: 
            return "NEARBY_ALERTS_NOT_AVAILABLE";
          }
          return "PLACE_PROXIMITY_UNKNOWN";
        case 9008: 
          return "PLACES_API_INVALID_APP";
        case 9007: 
          return "PLACES_API_KEY_EXPIRED";
        case 9006: 
          return "PLACES_API_DEVICE_RATE_LIMIT_EXCEEDED";
        case 9005: 
          return "PLACES_API_RATE_LIMIT_EXCEEDED";
        case 9004: 
          return "PLACES_API_INVALID_ARGUMENT";
        case 9003: 
          return "PLACES_API_ACCESS_NOT_CONFIGURED";
        case 9002: 
          return "PLACES_API_KEY_INVALID";
        case 9001: 
          return "PLACES_API_USAGE_LIMIT_EXCEEDED";
        }
        return "PLACES_API_QUOTA_FAILED";
      }
      return "PLACEFENCING_NOT_AVAILABLE";
    }
    return "PLACE_ALIAS_NOT_FOUND";
  }
  
  @Hide
  public static Status zza(int paramInt)
  {
    String str = getStatusCodeString(paramInt);
    zzbq.zza(str);
    return new Status(paramInt, str);
  }
}
package com.facebook;

public class FacebookGraphResponseException
  extends FacebookException
{
  private final GraphResponse graphResponse;
  
  public FacebookGraphResponseException(GraphResponse paramGraphResponse, String paramString)
  {
    super(paramString);
    graphResponse = paramGraphResponse;
  }
  
  public final GraphResponse getGraphResponse()
  {
    return graphResponse;
  }
  
  public final String toString()
  {
    FacebookRequestError localFacebookRequestError;
    if (graphResponse != null) {
      localFacebookRequestError = graphResponse.getError();
    } else {
      localFacebookRequestError = null;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("{FacebookGraphResponseException: ");
    String str = getMessage();
    if (str != null)
    {
      localStringBuilder.append(str);
      localStringBuilder.append(" ");
    }
    if (localFacebookRequestError != null)
    {
      localStringBuilder.append("httpResponseCode: ");
      localStringBuilder.append(localFacebookRequestError.getRequestStatusCode());
      localStringBuilder.append(", facebookErrorCode: ");
      localStringBuilder.append(localFacebookRequestError.getErrorCode());
      localStringBuilder.append(", facebookErrorType: ");
      localStringBuilder.append(localFacebookRequestError.getErrorType());
      localStringBuilder.append(", message: ");
      localStringBuilder.append(localFacebookRequestError.getErrorMessage());
      localStringBuilder.append("}");
    }
    return localStringBuilder.toString();
  }
}

package com.facebook;

public class FacebookSdkNotInitializedException
  extends FacebookException
{
  static final long serialVersionUID = 1L;
  
  public FacebookSdkNotInitializedException() {}
  
  public FacebookSdkNotInitializedException(String paramString)
  {
    super(paramString);
  }
  
  public FacebookSdkNotInitializedException(String paramString, Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public FacebookSdkNotInitializedException(Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

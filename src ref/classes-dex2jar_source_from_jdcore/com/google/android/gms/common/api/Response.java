package com.google.android.gms.common.api;

import android.support.annotation.NonNull;

public class Response<T extends Result>
{
  private T zza;
  
  public Response() {}
  
  protected Response(@NonNull T paramT)
  {
    zza = paramT;
  }
  
  @NonNull
  protected T getResult()
  {
    return zza;
  }
  
  public void setResult(@NonNull T paramT)
  {
    zza = paramT;
  }
}

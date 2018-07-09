package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import com.android.volley.VolleyLog;
import java.io.UnsupportedEncodingException;

public abstract class JsonRequest<T>
  extends Request<T>
{
  protected static final String PROTOCOL_CHARSET = "utf-8";
  private static final String PROTOCOL_CONTENT_TYPE = String.format("application/json; charset=%s", new Object[] { "utf-8" });
  private final Response.Listener<T> mListener;
  private final String mRequestBody;
  
  public JsonRequest(int paramInt, String paramString1, String paramString2, Response.Listener<T> paramListener, Response.ErrorListener paramErrorListener)
  {
    super(paramInt, paramString1, paramErrorListener);
    mListener = paramListener;
    mRequestBody = paramString2;
  }
  
  public JsonRequest(String paramString1, String paramString2, Response.Listener<T> paramListener, Response.ErrorListener paramErrorListener)
  {
    this(-1, paramString1, paramString2, paramListener, paramErrorListener);
  }
  
  protected void deliverResponse(T paramT)
  {
    mListener.onResponse(paramT);
  }
  
  public byte[] getBody()
  {
    try
    {
      if (mRequestBody == null) {
        return null;
      }
      byte[] arrayOfByte = mRequestBody.getBytes("utf-8");
      return arrayOfByte;
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      for (;;) {}
    }
    VolleyLog.wtf("Unsupported Encoding while trying to get the bytes of %s using %s", new Object[] { mRequestBody, "utf-8" });
    return null;
  }
  
  public String getBodyContentType()
  {
    return PROTOCOL_CONTENT_TYPE;
  }
  
  public byte[] getPostBody()
  {
    return getBody();
  }
  
  public String getPostBodyContentType()
  {
    return getBodyContentType();
  }
  
  protected abstract Response<T> parseNetworkResponse(NetworkResponse paramNetworkResponse);
}

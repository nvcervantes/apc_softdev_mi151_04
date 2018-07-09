package com.android.volley.toolbox;

import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.Response.ErrorListener;
import com.android.volley.Response.Listener;
import java.io.UnsupportedEncodingException;

public class StringRequest
  extends Request<String>
{
  private final Response.Listener<String> mListener;
  
  public StringRequest(int paramInt, String paramString, Response.Listener<String> paramListener, Response.ErrorListener paramErrorListener)
  {
    super(paramInt, paramString, paramErrorListener);
    mListener = paramListener;
  }
  
  public StringRequest(String paramString, Response.Listener<String> paramListener, Response.ErrorListener paramErrorListener)
  {
    this(0, paramString, paramListener, paramErrorListener);
  }
  
  protected void deliverResponse(String paramString)
  {
    mListener.onResponse(paramString);
  }
  
  protected Response<String> parseNetworkResponse(NetworkResponse paramNetworkResponse)
  {
    try
    {
      str = new String(data, HttpHeaderParser.parseCharset(headers));
    }
    catch (UnsupportedEncodingException localUnsupportedEncodingException)
    {
      String str;
      for (;;) {}
    }
    str = new String(data);
    return Response.success(str, HttpHeaderParser.parseCacheHeaders(paramNetworkResponse));
  }
}

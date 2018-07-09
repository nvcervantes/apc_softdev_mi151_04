package com.android.volley;

import java.util.Collections;
import java.util.Map;

public class NetworkResponse
{
  public final byte[] data;
  public final Map<String, String> headers;
  public final long networkTimeMs;
  public final boolean notModified;
  public final int statusCode;
  
  public NetworkResponse(int paramInt, byte[] paramArrayOfByte, Map<String, String> paramMap, boolean paramBoolean)
  {
    this(paramInt, paramArrayOfByte, paramMap, paramBoolean, 0L);
  }
  
  public NetworkResponse(int paramInt, byte[] paramArrayOfByte, Map<String, String> paramMap, boolean paramBoolean, long paramLong)
  {
    statusCode = paramInt;
    data = paramArrayOfByte;
    headers = paramMap;
    notModified = paramBoolean;
    networkTimeMs = paramLong;
  }
  
  public NetworkResponse(byte[] paramArrayOfByte)
  {
    this(200, paramArrayOfByte, Collections.emptyMap(), false, 0L);
  }
  
  public NetworkResponse(byte[] paramArrayOfByte, Map<String, String> paramMap)
  {
    this(200, paramArrayOfByte, paramMap, false, 0L);
  }
}

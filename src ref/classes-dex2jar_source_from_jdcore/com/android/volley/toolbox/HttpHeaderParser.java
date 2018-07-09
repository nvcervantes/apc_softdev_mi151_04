package com.android.volley.toolbox;

import com.android.volley.Cache.Entry;
import com.android.volley.NetworkResponse;
import java.util.Date;
import java.util.Map;
import org.apache.http.impl.cookie.DateParseException;
import org.apache.http.impl.cookie.DateUtils;

public class HttpHeaderParser
{
  public HttpHeaderParser() {}
  
  public static Cache.Entry parseCacheHeaders(NetworkResponse paramNetworkResponse)
  {
    long l6 = System.currentTimeMillis();
    Map localMap = headers;
    Object localObject1 = (String)localMap.get("Date");
    long l3;
    if (localObject1 != null) {
      l3 = parseDateAsEpoch((String)localObject1);
    } else {
      l3 = 0L;
    }
    localObject1 = (String)localMap.get("Cache-Control");
    int j = 0;
    if (localObject1 != null)
    {
      localObject1 = ((String)localObject1).split(",");
      i = 0;
      l2 = 0L;
      l1 = 0L;
      for (;;)
      {
        label80:
        if (j < localObject1.length)
        {
          localObject2 = localObject1[j].trim();
          if ((!((String)localObject2).equals("no-cache")) && (!((String)localObject2).equals("no-store")) && (!((String)localObject2).startsWith("max-age="))) {
            break;
          }
        }
      }
    }
    try
    {
      l4 = Long.parseLong(((String)localObject2).substring(8));
      l5 = l1;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        long l4 = l2;
        long l5 = l1;
      }
    }
    if (((String)localObject2).startsWith("stale-while-revalidate="))
    {
      l5 = Long.parseLong(((String)localObject2).substring(23));
      l4 = l2;
    }
    else if (!((String)localObject2).equals("must-revalidate"))
    {
      l4 = l2;
      l5 = l1;
      if (!((String)localObject2).equals("proxy-revalidate")) {}
    }
    else
    {
      i = 1;
      l5 = l1;
      l4 = l2;
    }
    j += 1;
    l2 = l4;
    l1 = l5;
    break label80;
    return null;
    j = 1;
    break label242;
    int i = 0;
    j = i;
    l2 = 0L;
    l1 = 0L;
    label242:
    localObject1 = (String)localMap.get("Expires");
    if (localObject1 != null) {
      l5 = parseDateAsEpoch((String)localObject1);
    } else {
      l5 = 0L;
    }
    localObject1 = (String)localMap.get("Last-Modified");
    if (localObject1 != null) {
      l4 = parseDateAsEpoch((String)localObject1);
    } else {
      l4 = 0L;
    }
    localObject1 = (String)localMap.get("ETag");
    if (j != 0)
    {
      l2 = l6 + l2 * 1000L;
      if (i != 0) {
        l1 = l2;
      } else {
        l1 = l2 + l1 * 1000L;
      }
    }
    else if ((l3 > 0L) && (l5 >= l3))
    {
      l2 = l6 + (l5 - l3);
      l1 = l2;
    }
    else
    {
      l1 = 0L;
      l2 = l1;
    }
    Object localObject2 = new Cache.Entry();
    data = data;
    etag = ((String)localObject1);
    softTtl = l2;
    ttl = l1;
    serverDate = l3;
    lastModified = l4;
    responseHeaders = localMap;
    return localObject2;
  }
  
  public static String parseCharset(Map<String, String> paramMap)
  {
    return parseCharset(paramMap, "ISO-8859-1");
  }
  
  public static String parseCharset(Map<String, String> paramMap, String paramString)
  {
    paramMap = (String)paramMap.get("Content-Type");
    if (paramMap != null)
    {
      paramMap = paramMap.split(";");
      int i = 1;
      while (i < paramMap.length)
      {
        String[] arrayOfString = paramMap[i].trim().split("=");
        if ((arrayOfString.length == 2) && (arrayOfString[0].equals("charset"))) {
          return arrayOfString[1];
        }
        i += 1;
      }
    }
    return paramString;
  }
  
  public static long parseDateAsEpoch(String paramString)
  {
    try
    {
      long l = DateUtils.parseDate(paramString).getTime();
      return l;
    }
    catch (DateParseException paramString)
    {
      for (;;) {}
    }
    return 0L;
  }
}

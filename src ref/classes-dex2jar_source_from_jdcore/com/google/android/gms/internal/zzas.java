package com.google.android.gms.internal;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import javax.net.ssl.SSLSocketFactory;

public final class zzas
  extends zzai
{
  private final zzat zza = null;
  private final SSLSocketFactory zzb = null;
  
  public zzas()
  {
    this(null);
  }
  
  private zzas(zzat paramZzat)
  {
    this(null, null);
  }
  
  private zzas(zzat paramZzat, SSLSocketFactory paramSSLSocketFactory) {}
  
  private static InputStream zza(HttpURLConnection paramHttpURLConnection)
  {
    try
    {
      InputStream localInputStream = paramHttpURLConnection.getInputStream();
      return localInputStream;
    }
    catch (IOException localIOException)
    {
      for (;;) {}
    }
    return paramHttpURLConnection.getErrorStream();
  }
  
  private static List<zzl> zza(Map<String, List<String>> paramMap)
  {
    ArrayList localArrayList = new ArrayList(paramMap.size());
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      if (localEntry.getKey() != null)
      {
        Iterator localIterator = ((List)localEntry.getValue()).iterator();
        while (localIterator.hasNext())
        {
          String str = (String)localIterator.next();
          localArrayList.add(new zzl((String)localEntry.getKey(), str));
        }
      }
    }
    return localArrayList;
  }
  
  private static void zza(HttpURLConnection paramHttpURLConnection, zzr<?> paramZzr)
    throws IOException, zza
  {
    byte[] arrayOfByte = paramZzr.zzg();
    if (arrayOfByte != null)
    {
      paramHttpURLConnection.setDoOutput(true);
      paramZzr = String.valueOf("UTF-8");
      if (paramZzr.length() != 0) {
        paramZzr = "application/x-www-form-urlencoded; charset=".concat(paramZzr);
      } else {
        paramZzr = new String("application/x-www-form-urlencoded; charset=");
      }
      paramHttpURLConnection.addRequestProperty("Content-Type", paramZzr);
      paramHttpURLConnection = new DataOutputStream(paramHttpURLConnection.getOutputStream());
      paramHttpURLConnection.write(arrayOfByte);
      paramHttpURLConnection.close();
    }
  }
  
  public final zzaq zza(zzr<?> paramZzr, Map<String, String> paramMap)
    throws IOException, zza
  {
    Object localObject = paramZzr.zzc();
    HashMap localHashMap = new HashMap();
    localHashMap.putAll(paramZzr.zzf());
    localHashMap.putAll(paramMap);
    String str;
    if (zza != null)
    {
      str = zza.zza((String)localObject);
      paramMap = str;
      if (str == null)
      {
        paramZzr = String.valueOf(localObject);
        if (paramZzr.length() != 0) {
          paramZzr = "URL blocked by rewriter: ".concat(paramZzr);
        } else {
          paramZzr = new String("URL blocked by rewriter: ");
        }
        throw new IOException(paramZzr);
      }
    }
    else
    {
      paramMap = (Map<String, String>)localObject;
    }
    paramMap = new URL(paramMap);
    localObject = (HttpURLConnection)paramMap.openConnection();
    ((HttpURLConnection)localObject).setInstanceFollowRedirects(HttpURLConnection.getFollowRedirects());
    int i = paramZzr.zzi();
    ((HttpURLConnection)localObject).setConnectTimeout(i);
    ((HttpURLConnection)localObject).setReadTimeout(i);
    int j = 0;
    ((HttpURLConnection)localObject).setUseCaches(false);
    ((HttpURLConnection)localObject).setDoInput(true);
    "https".equals(paramMap.getProtocol());
    paramMap = localHashMap.keySet().iterator();
    while (paramMap.hasNext())
    {
      str = (String)paramMap.next();
      ((HttpURLConnection)localObject).addRequestProperty(str, (String)localHashMap.get(str));
    }
    switch (paramZzr.zza())
    {
    default: 
      throw new IllegalStateException("Unknown method type.");
    case 7: 
      paramMap = "PATCH";
      break;
    case 6: 
      paramMap = "TRACE";
      break;
    case 5: 
      paramMap = "OPTIONS";
      break;
    case 4: 
      paramMap = "HEAD";
      break;
    case 3: 
      paramMap = "DELETE";
      break;
    case 2: 
      paramMap = "PUT";
      break;
    case 1: 
      paramMap = "POST";
      ((HttpURLConnection)localObject).setRequestMethod(paramMap);
      zza((HttpURLConnection)localObject, paramZzr);
      break;
    case 0: 
      paramMap = "GET";
      ((HttpURLConnection)localObject).setRequestMethod(paramMap);
    }
    int k = ((HttpURLConnection)localObject).getResponseCode();
    if (k == -1) {
      throw new IOException("Could not retrieve response code from HttpUrlConnection.");
    }
    i = j;
    if (paramZzr.zza() != 4) {
      if (100 <= k)
      {
        i = j;
        if (k < 200) {}
      }
      else
      {
        i = j;
        if (k != 204)
        {
          i = j;
          if (k != 304) {
            i = 1;
          }
        }
      }
    }
    if (i == 0) {
      return new zzaq(k, zza(((HttpURLConnection)localObject).getHeaderFields()));
    }
    return new zzaq(k, zza(((HttpURLConnection)localObject).getHeaderFields()), ((HttpURLConnection)localObject).getContentLength(), zza((HttpURLConnection)localObject));
  }
}

package com.google.android.gms.internal;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.conn.ConnectTimeoutException;

final class zzah
  extends zzai
{
  private final zzar zza;
  
  zzah(zzar paramZzar)
  {
    zza = paramZzar;
  }
  
  public final zzaq zza(zzr<?> paramZzr, Map<String, String> paramMap)
    throws IOException, zza
  {
    try
    {
      paramZzr = zza.zzb(paramZzr, paramMap);
      int j = paramZzr.getStatusLine().getStatusCode();
      paramMap = paramZzr.getAllHeaders();
      ArrayList localArrayList = new ArrayList(paramMap.length);
      int k = paramMap.length;
      int i = 0;
      while (i < k)
      {
        Object localObject = paramMap[i];
        localArrayList.add(new zzl(localObject.getName(), localObject.getValue()));
        i += 1;
      }
      if (paramZzr.getEntity() == null) {
        return new zzaq(j, localArrayList);
      }
      long l = paramZzr.getEntity().getContentLength();
      if ((int)l != l)
      {
        paramZzr = new StringBuilder(40);
        paramZzr.append("Response too large: ");
        paramZzr.append(l);
        throw new IOException(paramZzr.toString());
      }
      return new zzaq(j, localArrayList, (int)paramZzr.getEntity().getContentLength(), paramZzr.getEntity().getContent());
    }
    catch (ConnectTimeoutException paramZzr)
    {
      throw new SocketTimeoutException(paramZzr.getMessage());
    }
  }
}

package com.google.android.gms.internal;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.ProtocolVersion;
import org.apache.http.entity.BasicHttpEntity;
import org.apache.http.message.BasicHeader;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.message.BasicStatusLine;

public abstract class zzai
  implements zzar
{
  public zzai() {}
  
  public abstract zzaq zza(zzr<?> paramZzr, Map<String, String> paramMap)
    throws IOException, zza;
  
  @Deprecated
  public final HttpResponse zzb(zzr<?> paramZzr, Map<String, String> paramMap)
    throws IOException, zza
  {
    paramZzr = zza(paramZzr, paramMap);
    paramMap = new BasicHttpResponse(new BasicStatusLine(new ProtocolVersion("HTTP", 1, 1), paramZzr.zza(), ""));
    Object localObject1 = new ArrayList();
    Object localObject2 = paramZzr.zzb().iterator();
    while (((Iterator)localObject2).hasNext())
    {
      zzl localZzl = (zzl)((Iterator)localObject2).next();
      ((List)localObject1).add(new BasicHeader(localZzl.zza(), localZzl.zzb()));
    }
    paramMap.setHeaders((Header[])((List)localObject1).toArray(new Header[((List)localObject1).size()]));
    localObject1 = paramZzr.zzd();
    if (localObject1 != null)
    {
      localObject2 = new BasicHttpEntity();
      ((BasicHttpEntity)localObject2).setContent((InputStream)localObject1);
      ((BasicHttpEntity)localObject2).setContentLength(paramZzr.zzc());
      paramMap.setEntity((HttpEntity)localObject2);
    }
    return paramMap;
  }
}

package com.google.android.gms.internal;

import android.os.SystemClock;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.SocketTimeoutException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

public class zzaj
  implements zzm
{
  private static boolean zza = zzaf.zza;
  @Deprecated
  private zzar zzb;
  private final zzai zzc;
  private zzak zzd;
  
  public zzaj(zzai paramZzai)
  {
    this(paramZzai, new zzak(4096));
  }
  
  private zzaj(zzai paramZzai, zzak paramZzak)
  {
    zzc = paramZzai;
    zzb = paramZzai;
    zzd = paramZzak;
  }
  
  @Deprecated
  public zzaj(zzar paramZzar)
  {
    this(paramZzar, new zzak(4096));
  }
  
  @Deprecated
  private zzaj(zzar paramZzar, zzak paramZzak)
  {
    zzb = paramZzar;
    zzc = new zzah(paramZzar);
    zzd = paramZzak;
  }
  
  private static List<zzl> zza(List<zzl> paramList, zzc paramZzc)
  {
    TreeSet localTreeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
    Object localObject;
    if (!paramList.isEmpty())
    {
      localObject = paramList.iterator();
      while (((Iterator)localObject).hasNext()) {
        localTreeSet.add(((zzl)((Iterator)localObject).next()).zza());
      }
    }
    paramList = new ArrayList(paramList);
    if (zzh != null)
    {
      if (!zzh.isEmpty())
      {
        paramZzc = zzh.iterator();
        while (paramZzc.hasNext())
        {
          localObject = (zzl)paramZzc.next();
          if (!localTreeSet.contains(((zzl)localObject).zza())) {
            paramList.add(localObject);
          }
        }
      }
    }
    else if (!zzg.isEmpty())
    {
      paramZzc = zzg.entrySet().iterator();
      while (paramZzc.hasNext())
      {
        localObject = (Map.Entry)paramZzc.next();
        if (!localTreeSet.contains(((Map.Entry)localObject).getKey())) {
          paramList.add(new zzl((String)((Map.Entry)localObject).getKey(), (String)((Map.Entry)localObject).getValue()));
        }
      }
    }
    return paramList;
  }
  
  private static void zza(String paramString, zzr<?> paramZzr, zzae paramZzae)
    throws zzae
  {
    zzab localZzab = paramZzr.zzj();
    int i = paramZzr.zzi();
    try
    {
      localZzab.zza(paramZzae);
      paramZzr.zza(String.format("%s-retry [timeout=%s]", new Object[] { paramString, Integer.valueOf(i) }));
      return;
    }
    catch (zzae paramZzae)
    {
      paramZzr.zza(String.format("%s-timeout-giveup [timeout=%s]", new Object[] { paramString, Integer.valueOf(i) }));
      throw paramZzae;
    }
  }
  
  private final byte[] zza(InputStream paramInputStream, int paramInt)
    throws IOException, zzac
  {
    zzau localZzau = new zzau(zzd, paramInt);
    byte[] arrayOfByte = null;
    if (paramInputStream == null) {}
    try
    {
      throw new zzac();
    }
    finally
    {
      for (;;)
      {
        Object localObject1 = localObject2;
        Object localObject3 = localObject4;
      }
    }
    localObject1 = zzd.zza(1024);
    for (;;)
    {
      try
      {
        paramInt = paramInputStream.read((byte[])localObject1);
        if (paramInt != -1)
        {
          localZzau.write((byte[])localObject1, 0, paramInt);
          continue;
        }
        arrayOfByte = localZzau.toByteArray();
        if (paramInputStream == null) {}
      }
      finally {}
      try
      {
        paramInputStream.close();
      }
      catch (IOException paramInputStream) {}
    }
    zzaf.zza("Error occurred when closing InputStream", new Object[0]);
    zzd.zza((byte[])localObject1);
    localZzau.close();
    return arrayOfByte;
    if (paramInputStream != null) {}
    try
    {
      paramInputStream.close();
    }
    catch (IOException paramInputStream)
    {
      for (;;) {}
    }
    zzaf.zza("Error occurred when closing InputStream", new Object[0]);
    zzd.zza((byte[])localObject1);
    localZzau.close();
    throw localObject2;
  }
  
  public zzp zza(zzr<?> paramZzr)
    throws zzae
  {
    long l1 = SystemClock.elapsedRealtime();
    for (;;)
    {
      Object localObject3 = Collections.emptyList();
      try
      {
        zzaq localZzaq;
        Object localObject7;
        try
        {
          Object localObject6 = paramZzr.zzd();
          Object localObject1;
          if (localObject6 == null)
          {
            localObject1 = Collections.emptyMap();
          }
          else
          {
            localObject1 = new HashMap();
            if (zzb != null) {
              ((Map)localObject1).put("If-None-Match", zzb);
            }
            if (zzd <= 0L) {
              break label711;
            }
            ((Map)localObject1).put("If-Modified-Since", zzap.zza(zzd));
            break label711;
          }
          localZzaq = zzc.zza(paramZzr, (Map)localObject1);
          try
          {
            i = localZzaq.zza();
            localObject1 = localZzaq.zzb();
            Object localObject4;
            if (i == 304) {
              try
              {
                localObject3 = paramZzr.zzd();
                if (localObject3 == null) {
                  return new zzp(304, null, true, SystemClock.elapsedRealtime() - l1, (List)localObject1);
                }
                localObject6 = zza((List)localObject1, (zzc)localObject3);
                localObject3 = new zzp(304, zza, true, SystemClock.elapsedRealtime() - l1, (List)localObject6);
                return localObject3;
              }
              catch (IOException localIOException3)
              {
                Object localObject8 = null;
                localObject6 = localObject1;
                localObject1 = localIOException3;
                localObject4 = localObject8;
              }
            }
            try
            {
              localObject4 = localZzaq.zzd();
              if (localObject4 != null) {
                localObject4 = zza((InputStream)localObject4, localZzaq.zzc());
              } else {
                localObject4 = new byte[0];
              }
              try
              {
                l2 = SystemClock.elapsedRealtime() - l1;
                if ((!zza) && (l2 <= 3000L)) {
                  break label714;
                }
                if (localObject4 != null) {
                  try
                  {
                    localObject6 = Integer.valueOf(localObject4.length);
                  }
                  catch (IOException localIOException7)
                  {
                    localObject6 = localObject1;
                    localObject1 = localIOException7;
                    break label450;
                  }
                } else {
                  localObject6 = "null";
                }
                zzaf.zzb("HTTP response for request=<%s> [lifetime=%d], [size=%s], [rc=%d], [retryCount=%s]", new Object[] { paramZzr, Long.valueOf(l2), localObject6, Integer.valueOf(i), Integer.valueOf(paramZzr.zzj().zzb()) });
              }
              catch (IOException localIOException6) {}
              long l2 = SystemClock.elapsedRealtime();
              try
              {
                return new zzp(i, (byte[])localObject4, false, l2 - l1, (List)localObject1);
              }
              catch (IOException localIOException5) {}
              throw new IOException();
            }
            catch (IOException localIOException4)
            {
              localObject7 = localObject1;
              Object localObject9 = null;
              localObject1 = localIOException4;
              localObject5 = localObject9;
            }
            localObject9 = localObject1;
            localObject1 = localIOException6;
            localObject7 = localObject9;
          }
          catch (IOException localIOException1)
          {
            localObject7 = localObject5;
            localObject5 = null;
          }
          if (localZzaq == null) {
            break label621;
          }
        }
        catch (IOException localIOException2)
        {
          localObject7 = localObject5;
          localZzaq = null;
          localObject5 = localZzaq;
        }
        label450:
        i = localZzaq.zza();
        zzaf.zzc("Unexpected response code %d for %s", new Object[] { Integer.valueOf(i), paramZzr.zzc() });
        if (localObject5 != null)
        {
          localObject2 = new zzp(i, (byte[])localObject5, false, SystemClock.elapsedRealtime() - l1, localObject7);
          if ((i != 401) && (i != 403))
          {
            if ((i >= 400) && (i <= 499)) {
              throw new zzg((zzp)localObject2);
            }
            if ((i >= 500) && (i <= 599)) {
              throw new zzac((zzp)localObject2);
            }
            throw new zzac((zzp)localObject2);
          }
          zza("auth", paramZzr, new zza((zzp)localObject2));
          continue;
        }
        Object localObject2 = "network";
        localObject5 = new zzo();
        break label695;
        label621:
        throw new zzq((Throwable)localObject2);
      }
      catch (MalformedURLException localMalformedURLException)
      {
        paramZzr = String.valueOf(paramZzr.zzc());
        if (paramZzr.length() != 0) {
          paramZzr = "Bad URL ".concat(paramZzr);
        } else {
          paramZzr = new String("Bad URL ");
        }
        throw new RuntimeException(paramZzr, localMalformedURLException);
        String str = "socket";
        Object localObject5 = new zzad();
        zza(str, paramZzr, (zzae)localObject5);
      }
      catch (SocketTimeoutException localSocketTimeoutException)
      {
        for (;;)
        {
          int i;
          label695:
          continue;
          label711:
          continue;
          label714:
          if (i >= 200) {
            if (i <= 299) {}
          }
        }
      }
    }
  }
}

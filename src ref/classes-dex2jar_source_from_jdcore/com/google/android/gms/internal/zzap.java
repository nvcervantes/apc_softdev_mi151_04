package com.google.android.gms.internal;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TimeZone;
import java.util.TreeMap;

public final class zzap
{
  private static long zza(String paramString)
  {
    try
    {
      long l = zza().parse(paramString).getTime();
      return l;
    }
    catch (ParseException localParseException)
    {
      zzaf.zza(localParseException, "Unable to parse dateStr: %s, falling back to 0", new Object[] { paramString });
    }
    return 0L;
  }
  
  public static zzc zza(zzp paramZzp)
  {
    long l6 = System.currentTimeMillis();
    Map localMap = zzc;
    Object localObject1 = (String)localMap.get("Date");
    long l3;
    if (localObject1 != null) {
      l3 = zza((String)localObject1);
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
      l5 = zza((String)localObject1);
    } else {
      l5 = 0L;
    }
    localObject1 = (String)localMap.get("Last-Modified");
    if (localObject1 != null) {
      l4 = zza((String)localObject1);
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
    Object localObject2 = new zzc();
    zza = zzb;
    zzb = ((String)localObject1);
    zzf = l2;
    zze = l1;
    zzc = l3;
    zzd = l4;
    zzg = localMap;
    zzh = zzd;
    return localObject2;
  }
  
  static String zza(long paramLong)
  {
    return zza().format(new Date(paramLong));
  }
  
  public static String zza(Map<String, String> paramMap)
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
    return "ISO-8859-1";
  }
  
  private static SimpleDateFormat zza()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss zzz", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("GMT"));
    return localSimpleDateFormat;
  }
  
  static Map<String, String> zza(List<zzl> paramList)
  {
    TreeMap localTreeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      zzl localZzl = (zzl)paramList.next();
      localTreeMap.put(localZzl.zza(), localZzl.zzb());
    }
    return localTreeMap;
  }
  
  static List<zzl> zzb(Map<String, String> paramMap)
  {
    ArrayList localArrayList = new ArrayList(paramMap.size());
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)paramMap.next();
      localArrayList.add(new zzl((String)localEntry.getKey(), (String)localEntry.getValue()));
    }
    return localArrayList;
  }
}

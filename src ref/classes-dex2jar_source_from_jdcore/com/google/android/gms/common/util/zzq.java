package com.google.android.gms.common.util;

import android.text.TextUtils;
import java.util.Iterator;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public final class zzq
{
  private static final Pattern zza = Pattern.compile("\\\\.");
  private static final Pattern zzb = Pattern.compile("[\\\\\"/\b\f\n\r\t]");
  
  public static String zza(String paramString)
  {
    Object localObject1 = paramString;
    if (!TextUtils.isEmpty(paramString))
    {
      Matcher localMatcher = zzb.matcher(paramString);
      localObject1 = null;
      while (localMatcher.find())
      {
        Object localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new StringBuffer();
        }
        int i = localMatcher.group().charAt(0);
        if (i != 34) {
          if (i != 47) {
            if (i != 92) {
              switch (i)
              {
              default: 
                switch (i)
                {
                default: 
                  localObject1 = localObject2;
                  break;
                case 13: 
                  localObject1 = "\\\\r";
                  break;
                case 12: 
                  localObject1 = "\\\\f";
                }
                break;
              case 10: 
                localObject1 = "\\\\n";
                break;
              case 9: 
                localObject1 = "\\\\t";
              }
            }
          }
        }
        for (;;)
        {
          localMatcher.appendReplacement((StringBuffer)localObject2, (String)localObject1);
          localObject1 = localObject2;
          break;
          localObject1 = "\\\\b";
          continue;
          localObject1 = "\\\\\\\\";
          continue;
          localObject1 = "\\\\/";
          continue;
          localObject1 = "\\\\\\\"";
        }
      }
      if (localObject1 == null) {
        return paramString;
      }
      localMatcher.appendTail((StringBuffer)localObject1);
      localObject1 = ((StringBuffer)localObject1).toString();
    }
    return localObject1;
  }
  
  public static boolean zza(Object paramObject1, Object paramObject2)
  {
    if ((paramObject1 == null) && (paramObject2 == null)) {
      return true;
    }
    Iterator localIterator;
    if (paramObject1 != null)
    {
      if (paramObject2 == null) {
        return false;
      }
      if (((paramObject1 instanceof JSONObject)) && ((paramObject2 instanceof JSONObject)))
      {
        paramObject1 = (JSONObject)paramObject1;
        paramObject2 = (JSONObject)paramObject2;
        if (paramObject1.length() != paramObject2.length()) {
          return false;
        }
        localIterator = paramObject1.keys();
      }
    }
    for (;;)
    {
      String str;
      if (localIterator.hasNext())
      {
        str = (String)localIterator.next();
        if (!paramObject2.has(str)) {
          return false;
        }
      }
      try
      {
        bool = zza(paramObject1.get(str), paramObject2.get(str));
        if (!bool) {
          return false;
        }
      }
      catch (JSONException paramObject1)
      {
        boolean bool;
        int i;
        return false;
      }
    }
    return true;
    if (((paramObject1 instanceof JSONArray)) && ((paramObject2 instanceof JSONArray)))
    {
      paramObject1 = (JSONArray)paramObject1;
      paramObject2 = (JSONArray)paramObject2;
      if (paramObject1.length() != paramObject2.length()) {
        return false;
      }
      i = 0;
    }
    for (;;)
    {
      if (i < paramObject1.length()) {}
      try
      {
        bool = zza(paramObject1.get(i), paramObject2.get(i));
        if (!bool) {
          return false;
        }
        i += 1;
      }
      catch (JSONException paramObject1) {}
    }
    return true;
    return paramObject1.equals(paramObject2);
    return false;
    return false;
  }
}

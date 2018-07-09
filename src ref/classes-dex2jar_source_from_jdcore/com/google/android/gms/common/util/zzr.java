package com.google.android.gms.common.util;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

public final class zzr
{
  public static void zza(StringBuilder paramStringBuilder, HashMap<String, String> paramHashMap)
  {
    paramStringBuilder.append("{");
    Iterator localIterator = paramHashMap.keySet().iterator();
    int i = 1;
    if (localIterator.hasNext())
    {
      String str1 = (String)localIterator.next();
      if (i == 0) {
        paramStringBuilder.append(",");
      } else {
        i = 0;
      }
      String str2 = (String)paramHashMap.get(str1);
      paramStringBuilder.append("\"");
      paramStringBuilder.append(str1);
      paramStringBuilder.append("\":");
      if (str2 == null) {}
      for (str1 = "null";; str1 = "\"")
      {
        paramStringBuilder.append(str1);
        break;
        paramStringBuilder.append("\"");
        paramStringBuilder.append(str2);
      }
    }
    paramStringBuilder.append("}");
  }
}

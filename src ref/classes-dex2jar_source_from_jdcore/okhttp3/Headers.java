package okhttp3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okhttp3.internal.http.HttpDate;

public final class Headers
{
  private final String[] namesAndValues;
  
  Headers(Builder paramBuilder)
  {
    namesAndValues = ((String[])namesAndValues.toArray(new String[namesAndValues.size()]));
  }
  
  private Headers(String[] paramArrayOfString)
  {
    namesAndValues = paramArrayOfString;
  }
  
  private static String get(String[] paramArrayOfString, String paramString)
  {
    int i = paramArrayOfString.length - 2;
    while (i >= 0)
    {
      if (paramString.equalsIgnoreCase(paramArrayOfString[i])) {
        return paramArrayOfString[(i + 1)];
      }
      i -= 2;
    }
    return null;
  }
  
  public static Headers of(Map<String, String> paramMap)
  {
    if (paramMap == null) {
      throw new NullPointerException("headers == null");
    }
    Object localObject1 = new String[paramMap.size() * 2];
    Iterator localIterator = paramMap.entrySet().iterator();
    int i = 0;
    while (localIterator.hasNext())
    {
      Object localObject2 = (Map.Entry)localIterator.next();
      if ((((Map.Entry)localObject2).getKey() != null) && (((Map.Entry)localObject2).getValue() != null))
      {
        paramMap = ((String)((Map.Entry)localObject2).getKey()).trim();
        localObject2 = ((String)((Map.Entry)localObject2).getValue()).trim();
        if ((paramMap.length() != 0) && (paramMap.indexOf(0) == -1) && (((String)localObject2).indexOf(0) == -1))
        {
          localObject1[i] = paramMap;
          localObject1[(i + 1)] = localObject2;
          i += 2;
        }
        else
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Unexpected header: ");
          ((StringBuilder)localObject1).append(paramMap);
          ((StringBuilder)localObject1).append(": ");
          ((StringBuilder)localObject1).append((String)localObject2);
          throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
        }
      }
      else
      {
        throw new IllegalArgumentException("Headers cannot be null");
      }
    }
    return new Headers((String[])localObject1);
  }
  
  public static Headers of(String... paramVarArgs)
  {
    if (paramVarArgs == null) {
      throw new NullPointerException("namesAndValues == null");
    }
    if (paramVarArgs.length % 2 != 0) {
      throw new IllegalArgumentException("Expected alternating header names and values");
    }
    Object localObject = (String[])paramVarArgs.clone();
    int i = 0;
    while (i < localObject.length)
    {
      if (localObject[i] == null) {
        throw new IllegalArgumentException("Headers cannot be null");
      }
      localObject[i] = localObject[i].trim();
      i += 1;
    }
    i = 0;
    while (i < localObject.length)
    {
      paramVarArgs = localObject[i];
      String str = localObject[(i + 1)];
      if ((paramVarArgs.length() != 0) && (paramVarArgs.indexOf(0) == -1) && (str.indexOf(0) == -1))
      {
        i += 2;
      }
      else
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Unexpected header: ");
        ((StringBuilder)localObject).append(paramVarArgs);
        ((StringBuilder)localObject).append(": ");
        ((StringBuilder)localObject).append(str);
        throw new IllegalArgumentException(((StringBuilder)localObject).toString());
      }
    }
    return new Headers((String[])localObject);
  }
  
  public long byteCount()
  {
    String[] arrayOfString = namesAndValues;
    int i = 0;
    long l1 = arrayOfString.length * 2;
    int j = namesAndValues.length;
    while (i < j)
    {
      long l2 = namesAndValues[i].length();
      i += 1;
      l1 += l2;
    }
    return l1;
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    return ((paramObject instanceof Headers)) && (Arrays.equals(namesAndValues, namesAndValues));
  }
  
  @Nullable
  public String get(String paramString)
  {
    return get(namesAndValues, paramString);
  }
  
  @Nullable
  public Date getDate(String paramString)
  {
    paramString = get(paramString);
    if (paramString != null) {
      return HttpDate.parse(paramString);
    }
    return null;
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(namesAndValues);
  }
  
  public String name(int paramInt)
  {
    return namesAndValues[(paramInt * 2)];
  }
  
  public Set<String> names()
  {
    TreeSet localTreeSet = new TreeSet(String.CASE_INSENSITIVE_ORDER);
    int j = size();
    int i = 0;
    while (i < j)
    {
      localTreeSet.add(name(i));
      i += 1;
    }
    return Collections.unmodifiableSet(localTreeSet);
  }
  
  public Builder newBuilder()
  {
    Builder localBuilder = new Builder();
    Collections.addAll(namesAndValues, namesAndValues);
    return localBuilder;
  }
  
  public int size()
  {
    return namesAndValues.length / 2;
  }
  
  public Map<String, List<String>> toMultimap()
  {
    TreeMap localTreeMap = new TreeMap(String.CASE_INSENSITIVE_ORDER);
    int j = size();
    int i = 0;
    while (i < j)
    {
      String str = name(i).toLowerCase(Locale.US);
      List localList = (List)localTreeMap.get(str);
      Object localObject = localList;
      if (localList == null)
      {
        localObject = new ArrayList(2);
        localTreeMap.put(str, localObject);
      }
      ((List)localObject).add(value(i));
      i += 1;
    }
    return localTreeMap;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int j = size();
    int i = 0;
    while (i < j)
    {
      localStringBuilder.append(name(i));
      localStringBuilder.append(": ");
      localStringBuilder.append(value(i));
      localStringBuilder.append("\n");
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public String value(int paramInt)
  {
    return namesAndValues[(paramInt * 2 + 1)];
  }
  
  public List<String> values(String paramString)
  {
    int j = size();
    Object localObject1 = null;
    int i = 0;
    while (i < j)
    {
      Object localObject2 = localObject1;
      if (paramString.equalsIgnoreCase(name(i)))
      {
        localObject2 = localObject1;
        if (localObject1 == null) {
          localObject2 = new ArrayList(2);
        }
        ((List)localObject2).add(value(i));
      }
      i += 1;
      localObject1 = localObject2;
    }
    if (localObject1 != null) {
      return Collections.unmodifiableList(localObject1);
    }
    return Collections.emptyList();
  }
  
  public static final class Builder
  {
    final List<String> namesAndValues = new ArrayList(20);
    
    public Builder() {}
    
    private void checkNameAndValue(String paramString1, String paramString2)
    {
      if (paramString1 == null) {
        throw new NullPointerException("name == null");
      }
      if (paramString1.isEmpty()) {
        throw new IllegalArgumentException("name is empty");
      }
      int j = paramString1.length();
      int i = 0;
      int k;
      while (i < j)
      {
        k = paramString1.charAt(i);
        if ((k > 32) && (k < 127)) {
          i += 1;
        } else {
          throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in header name: %s", new Object[] { Integer.valueOf(k), Integer.valueOf(i), paramString1 }));
        }
      }
      if (paramString2 == null)
      {
        paramString2 = new StringBuilder();
        paramString2.append("value for name ");
        paramString2.append(paramString1);
        paramString2.append(" == null");
        throw new NullPointerException(paramString2.toString());
      }
      j = paramString2.length();
      i = 0;
      while (i < j)
      {
        k = paramString2.charAt(i);
        if (((k <= 31) && (k != 9)) || (k >= 127)) {
          throw new IllegalArgumentException(Util.format("Unexpected char %#04x at %d in %s value: %s", new Object[] { Integer.valueOf(k), Integer.valueOf(i), paramString1, paramString2 }));
        }
        i += 1;
      }
    }
    
    public Builder add(String paramString)
    {
      int i = paramString.indexOf(":");
      if (i == -1)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unexpected header: ");
        localStringBuilder.append(paramString);
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      return add(paramString.substring(0, i).trim(), paramString.substring(i + 1));
    }
    
    public Builder add(String paramString1, String paramString2)
    {
      checkNameAndValue(paramString1, paramString2);
      return addLenient(paramString1, paramString2);
    }
    
    Builder addLenient(String paramString)
    {
      int i = paramString.indexOf(":", 1);
      if (i != -1) {
        return addLenient(paramString.substring(0, i), paramString.substring(i + 1));
      }
      if (paramString.startsWith(":")) {
        return addLenient("", paramString.substring(1));
      }
      return addLenient("", paramString);
    }
    
    Builder addLenient(String paramString1, String paramString2)
    {
      namesAndValues.add(paramString1);
      namesAndValues.add(paramString2.trim());
      return this;
    }
    
    public Headers build()
    {
      return new Headers(this);
    }
    
    public String get(String paramString)
    {
      int i = namesAndValues.size() - 2;
      while (i >= 0)
      {
        if (paramString.equalsIgnoreCase((String)namesAndValues.get(i))) {
          return (String)namesAndValues.get(i + 1);
        }
        i -= 2;
      }
      return null;
    }
    
    public Builder removeAll(String paramString)
    {
      int j;
      for (int i = 0; i < namesAndValues.size(); i = j + 2)
      {
        j = i;
        if (paramString.equalsIgnoreCase((String)namesAndValues.get(i)))
        {
          namesAndValues.remove(i);
          namesAndValues.remove(i);
          j = i - 2;
        }
      }
      return this;
    }
    
    public Builder set(String paramString1, String paramString2)
    {
      checkNameAndValue(paramString1, paramString2);
      removeAll(paramString1);
      addLenient(paramString1, paramString2);
      return this;
    }
  }
}

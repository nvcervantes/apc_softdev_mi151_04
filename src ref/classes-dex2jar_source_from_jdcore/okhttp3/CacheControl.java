package okhttp3;

import java.util.concurrent.TimeUnit;
import javax.annotation.Nullable;
import okhttp3.internal.http.HttpHeaders;

public final class CacheControl
{
  public static final CacheControl FORCE_CACHE = new Builder().onlyIfCached().maxStale(Integer.MAX_VALUE, TimeUnit.SECONDS).build();
  public static final CacheControl FORCE_NETWORK = new Builder().noCache().build();
  @Nullable
  String headerValue;
  private final boolean immutable;
  private final boolean isPrivate;
  private final boolean isPublic;
  private final int maxAgeSeconds;
  private final int maxStaleSeconds;
  private final int minFreshSeconds;
  private final boolean mustRevalidate;
  private final boolean noCache;
  private final boolean noStore;
  private final boolean noTransform;
  private final boolean onlyIfCached;
  private final int sMaxAgeSeconds;
  
  CacheControl(Builder paramBuilder)
  {
    noCache = noCache;
    noStore = noStore;
    maxAgeSeconds = maxAgeSeconds;
    sMaxAgeSeconds = -1;
    isPrivate = false;
    isPublic = false;
    mustRevalidate = false;
    maxStaleSeconds = maxStaleSeconds;
    minFreshSeconds = minFreshSeconds;
    onlyIfCached = onlyIfCached;
    noTransform = noTransform;
    immutable = immutable;
  }
  
  private CacheControl(boolean paramBoolean1, boolean paramBoolean2, int paramInt1, int paramInt2, boolean paramBoolean3, boolean paramBoolean4, boolean paramBoolean5, int paramInt3, int paramInt4, boolean paramBoolean6, boolean paramBoolean7, boolean paramBoolean8, @Nullable String paramString)
  {
    noCache = paramBoolean1;
    noStore = paramBoolean2;
    maxAgeSeconds = paramInt1;
    sMaxAgeSeconds = paramInt2;
    isPrivate = paramBoolean3;
    isPublic = paramBoolean4;
    mustRevalidate = paramBoolean5;
    maxStaleSeconds = paramInt3;
    minFreshSeconds = paramInt4;
    onlyIfCached = paramBoolean6;
    noTransform = paramBoolean7;
    immutable = paramBoolean8;
    headerValue = paramString;
  }
  
  private String headerValue()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (noCache) {
      localStringBuilder.append("no-cache, ");
    }
    if (noStore) {
      localStringBuilder.append("no-store, ");
    }
    if (maxAgeSeconds != -1)
    {
      localStringBuilder.append("max-age=");
      localStringBuilder.append(maxAgeSeconds);
      localStringBuilder.append(", ");
    }
    if (sMaxAgeSeconds != -1)
    {
      localStringBuilder.append("s-maxage=");
      localStringBuilder.append(sMaxAgeSeconds);
      localStringBuilder.append(", ");
    }
    if (isPrivate) {
      localStringBuilder.append("private, ");
    }
    if (isPublic) {
      localStringBuilder.append("public, ");
    }
    if (mustRevalidate) {
      localStringBuilder.append("must-revalidate, ");
    }
    if (maxStaleSeconds != -1)
    {
      localStringBuilder.append("max-stale=");
      localStringBuilder.append(maxStaleSeconds);
      localStringBuilder.append(", ");
    }
    if (minFreshSeconds != -1)
    {
      localStringBuilder.append("min-fresh=");
      localStringBuilder.append(minFreshSeconds);
      localStringBuilder.append(", ");
    }
    if (onlyIfCached) {
      localStringBuilder.append("only-if-cached, ");
    }
    if (noTransform) {
      localStringBuilder.append("no-transform, ");
    }
    if (immutable) {
      localStringBuilder.append("immutable, ");
    }
    if (localStringBuilder.length() == 0) {
      return "";
    }
    localStringBuilder.delete(localStringBuilder.length() - 2, localStringBuilder.length());
    return localStringBuilder.toString();
  }
  
  public static CacheControl parse(Headers paramHeaders)
  {
    int i8 = paramHeaders.size();
    int i2 = 0;
    int k = 1;
    Object localObject1 = null;
    boolean bool5 = false;
    boolean bool4 = false;
    int i1 = -1;
    int n = -1;
    boolean bool3 = false;
    boolean bool2 = false;
    boolean bool1 = false;
    int i = -1;
    int m = -1;
    boolean bool8 = false;
    boolean bool6 = false;
    boolean bool16;
    for (boolean bool7 = false; i2 < i8; bool7 = bool16)
    {
      String str2 = paramHeaders.name(i2);
      String str1 = paramHeaders.value(i2);
      if (str2.equalsIgnoreCase("Cache-Control")) {
        if (localObject1 == null) {}
      }
      int i7;
      Object localObject2;
      boolean bool9;
      boolean bool10;
      int j;
      int i3;
      boolean bool11;
      boolean bool12;
      boolean bool13;
      int i4;
      int i5;
      boolean bool14;
      boolean bool15;
      for (;;)
      {
        k = 0;
        break;
        localObject1 = str1;
        break;
        i7 = k;
        localObject2 = localObject1;
        bool9 = bool5;
        bool10 = bool4;
        j = i1;
        i3 = n;
        bool11 = bool3;
        bool12 = bool2;
        bool13 = bool1;
        i4 = i;
        i5 = m;
        bool14 = bool8;
        bool15 = bool6;
        bool16 = bool7;
        if (!str2.equalsIgnoreCase("Pragma")) {
          break label968;
        }
      }
      int i6 = 0;
      for (;;)
      {
        i7 = k;
        localObject2 = localObject1;
        bool9 = bool5;
        bool10 = bool4;
        j = i1;
        i3 = n;
        bool11 = bool3;
        bool12 = bool2;
        bool13 = bool1;
        i4 = i;
        i5 = m;
        bool14 = bool8;
        bool15 = bool6;
        bool16 = bool7;
        if (i6 >= str1.length()) {
          break;
        }
        j = HttpHeaders.skipUntil(str1, i6, "=,;");
        str2 = str1.substring(i6, j).trim();
        if ((j != str1.length()) && (str1.charAt(j) != ',') && (str1.charAt(j) != ';'))
        {
          i3 = HttpHeaders.skipWhitespace(str1, j + 1);
          if ((i3 < str1.length()) && (str1.charAt(i3) == '"'))
          {
            j = i3 + 1;
            i3 = HttpHeaders.skipUntil(str1, j, "\"");
            localObject2 = str1.substring(j, i3);
            j = i3 + 1;
          }
          else
          {
            j = HttpHeaders.skipUntil(str1, i3, ",;");
            localObject2 = str1.substring(i3, j).trim();
          }
        }
        else
        {
          j += 1;
          localObject2 = null;
        }
        if ("no-cache".equalsIgnoreCase(str2)) {
          bool5 = true;
        }
        for (;;)
        {
          bool9 = bool5;
          bool10 = bool4;
          i3 = i1;
          i4 = n;
          bool11 = bool3;
          bool12 = bool2;
          bool13 = bool1;
          i5 = i;
          i7 = m;
          bool14 = bool8;
          bool15 = bool6;
          break label919;
          if ("no-store".equalsIgnoreCase(str2))
          {
            bool4 = true;
          }
          else
          {
            if ("max-age".equalsIgnoreCase(str2))
            {
              i3 = HttpHeaders.parseSeconds((String)localObject2, -1);
              bool9 = bool5;
              bool10 = bool4;
              i4 = n;
              bool11 = bool3;
              bool12 = bool2;
              bool13 = bool1;
              i5 = i;
              i7 = m;
              bool14 = bool8;
              bool15 = bool6;
              break label919;
            }
            if ("s-maxage".equalsIgnoreCase(str2))
            {
              i4 = HttpHeaders.parseSeconds((String)localObject2, -1);
              bool9 = bool5;
              bool10 = bool4;
              i3 = i1;
              bool11 = bool3;
              bool12 = bool2;
              bool13 = bool1;
              i5 = i;
              i7 = m;
              bool14 = bool8;
              bool15 = bool6;
              break label919;
            }
            if ("private".equalsIgnoreCase(str2))
            {
              bool3 = true;
            }
            else if ("public".equalsIgnoreCase(str2))
            {
              bool2 = true;
            }
            else if ("must-revalidate".equalsIgnoreCase(str2))
            {
              bool1 = true;
            }
            else
            {
              if (!"max-stale".equalsIgnoreCase(str2)) {
                break;
              }
              i = HttpHeaders.parseSeconds((String)localObject2, Integer.MAX_VALUE);
            }
          }
        }
        if ("min-fresh".equalsIgnoreCase(str2))
        {
          i7 = HttpHeaders.parseSeconds((String)localObject2, -1);
          bool9 = bool5;
          bool10 = bool4;
          i3 = i1;
          i4 = n;
          bool11 = bool3;
          bool12 = bool2;
          bool13 = bool1;
          i5 = i;
          bool14 = bool8;
          bool15 = bool6;
        }
        else if ("only-if-cached".equalsIgnoreCase(str2))
        {
          bool14 = true;
          bool9 = bool5;
          bool10 = bool4;
          i3 = i1;
          i4 = n;
          bool11 = bool3;
          bool12 = bool2;
          bool13 = bool1;
          i5 = i;
          i7 = m;
          bool15 = bool6;
        }
        else if ("no-transform".equalsIgnoreCase(str2))
        {
          bool15 = true;
          bool9 = bool5;
          bool10 = bool4;
          i3 = i1;
          i4 = n;
          bool11 = bool3;
          bool12 = bool2;
          bool13 = bool1;
          i5 = i;
          i7 = m;
          bool14 = bool8;
        }
        else
        {
          bool9 = bool5;
          bool10 = bool4;
          i3 = i1;
          i4 = n;
          bool11 = bool3;
          bool12 = bool2;
          bool13 = bool1;
          i5 = i;
          i7 = m;
          bool14 = bool8;
          bool15 = bool6;
          if ("immutable".equalsIgnoreCase(str2))
          {
            bool7 = true;
            bool15 = bool6;
            bool14 = bool8;
            i7 = m;
            i5 = i;
            bool13 = bool1;
            bool12 = bool2;
            bool11 = bool3;
            i4 = n;
            i3 = i1;
            bool10 = bool4;
            bool9 = bool5;
          }
        }
        label919:
        i6 = j;
        bool5 = bool9;
        bool4 = bool10;
        i1 = i3;
        n = i4;
        bool3 = bool11;
        bool2 = bool12;
        bool1 = bool13;
        i = i5;
        m = i7;
        bool8 = bool14;
        bool6 = bool15;
      }
      label968:
      i2 += 1;
      k = i7;
      localObject1 = localObject2;
      bool5 = bool9;
      bool4 = bool10;
      i1 = j;
      n = i3;
      bool3 = bool11;
      bool2 = bool12;
      bool1 = bool13;
      i = i4;
      m = i5;
      bool8 = bool14;
      bool6 = bool15;
    }
    if (k == 0) {
      localObject1 = null;
    }
    return new CacheControl(bool5, bool4, i1, n, bool3, bool2, bool1, i, m, bool8, bool6, bool7, localObject1);
  }
  
  public boolean immutable()
  {
    return immutable;
  }
  
  public boolean isPrivate()
  {
    return isPrivate;
  }
  
  public boolean isPublic()
  {
    return isPublic;
  }
  
  public int maxAgeSeconds()
  {
    return maxAgeSeconds;
  }
  
  public int maxStaleSeconds()
  {
    return maxStaleSeconds;
  }
  
  public int minFreshSeconds()
  {
    return minFreshSeconds;
  }
  
  public boolean mustRevalidate()
  {
    return mustRevalidate;
  }
  
  public boolean noCache()
  {
    return noCache;
  }
  
  public boolean noStore()
  {
    return noStore;
  }
  
  public boolean noTransform()
  {
    return noTransform;
  }
  
  public boolean onlyIfCached()
  {
    return onlyIfCached;
  }
  
  public int sMaxAgeSeconds()
  {
    return sMaxAgeSeconds;
  }
  
  public String toString()
  {
    String str = headerValue;
    if (str != null) {
      return str;
    }
    str = headerValue();
    headerValue = str;
    return str;
  }
  
  public static final class Builder
  {
    boolean immutable;
    int maxAgeSeconds = -1;
    int maxStaleSeconds = -1;
    int minFreshSeconds = -1;
    boolean noCache;
    boolean noStore;
    boolean noTransform;
    boolean onlyIfCached;
    
    public Builder() {}
    
    public CacheControl build()
    {
      return new CacheControl(this);
    }
    
    public Builder immutable()
    {
      immutable = true;
      return this;
    }
    
    public Builder maxAge(int paramInt, TimeUnit paramTimeUnit)
    {
      if (paramInt < 0)
      {
        paramTimeUnit = new StringBuilder();
        paramTimeUnit.append("maxAge < 0: ");
        paramTimeUnit.append(paramInt);
        throw new IllegalArgumentException(paramTimeUnit.toString());
      }
      long l = paramTimeUnit.toSeconds(paramInt);
      if (l > 2147483647L) {
        paramInt = Integer.MAX_VALUE;
      } else {
        paramInt = (int)l;
      }
      maxAgeSeconds = paramInt;
      return this;
    }
    
    public Builder maxStale(int paramInt, TimeUnit paramTimeUnit)
    {
      if (paramInt < 0)
      {
        paramTimeUnit = new StringBuilder();
        paramTimeUnit.append("maxStale < 0: ");
        paramTimeUnit.append(paramInt);
        throw new IllegalArgumentException(paramTimeUnit.toString());
      }
      long l = paramTimeUnit.toSeconds(paramInt);
      if (l > 2147483647L) {
        paramInt = Integer.MAX_VALUE;
      } else {
        paramInt = (int)l;
      }
      maxStaleSeconds = paramInt;
      return this;
    }
    
    public Builder minFresh(int paramInt, TimeUnit paramTimeUnit)
    {
      if (paramInt < 0)
      {
        paramTimeUnit = new StringBuilder();
        paramTimeUnit.append("minFresh < 0: ");
        paramTimeUnit.append(paramInt);
        throw new IllegalArgumentException(paramTimeUnit.toString());
      }
      long l = paramTimeUnit.toSeconds(paramInt);
      if (l > 2147483647L) {
        paramInt = Integer.MAX_VALUE;
      } else {
        paramInt = (int)l;
      }
      minFreshSeconds = paramInt;
      return this;
    }
    
    public Builder noCache()
    {
      noCache = true;
      return this;
    }
    
    public Builder noStore()
    {
      noStore = true;
      return this;
    }
    
    public Builder noTransform()
    {
      noTransform = true;
      return this;
    }
    
    public Builder onlyIfCached()
    {
      onlyIfCached = true;
      return this;
    }
  }
}

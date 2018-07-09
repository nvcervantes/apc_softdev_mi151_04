package okhttp3;

import java.nio.charset.Charset;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nullable;

public final class MediaType
{
  private static final Pattern PARAMETER = Pattern.compile(";\\s*(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)=(?:([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)|\"([^\"]*)\"))?");
  private static final String QUOTED = "\"([^\"]*)\"";
  private static final String TOKEN = "([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)";
  private static final Pattern TYPE_SUBTYPE = Pattern.compile("([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)/([a-zA-Z0-9-!#$%&'*+.^_`{|}~]+)");
  @Nullable
  private final String charset;
  private final String mediaType;
  private final String subtype;
  private final String type;
  
  private MediaType(String paramString1, String paramString2, String paramString3, @Nullable String paramString4)
  {
    mediaType = paramString1;
    type = paramString2;
    subtype = paramString3;
    charset = paramString4;
  }
  
  @Nullable
  public static MediaType parse(String paramString)
  {
    Object localObject1 = TYPE_SUBTYPE.matcher(paramString);
    if (!((Matcher)localObject1).lookingAt()) {
      return null;
    }
    String str2 = ((Matcher)localObject1).group(1).toLowerCase(Locale.US);
    String str3 = ((Matcher)localObject1).group(2).toLowerCase(Locale.US);
    Matcher localMatcher = PARAMETER.matcher(paramString);
    int i = ((Matcher)localObject1).end();
    for (Object localObject2 = null; i < paramString.length(); localObject2 = localObject1)
    {
      localMatcher.region(i, paramString.length());
      if (!localMatcher.lookingAt()) {
        return null;
      }
      String str1 = localMatcher.group(1);
      localObject1 = localObject2;
      if (str1 != null) {
        if (!str1.equalsIgnoreCase("charset"))
        {
          localObject1 = localObject2;
        }
        else
        {
          str1 = localMatcher.group(2);
          if (str1 != null)
          {
            localObject1 = str1;
            if (str1.startsWith("'"))
            {
              localObject1 = str1;
              if (str1.endsWith("'"))
              {
                localObject1 = str1;
                if (str1.length() > 2) {
                  localObject1 = str1.substring(1, str1.length() - 1);
                }
              }
            }
          }
          else
          {
            localObject1 = localMatcher.group(3);
          }
          if ((localObject2 != null) && (!((String)localObject1).equalsIgnoreCase((String)localObject2))) {
            return null;
          }
        }
      }
      i = localMatcher.end();
    }
    return new MediaType(paramString, str2, str3, (String)localObject2);
  }
  
  @Nullable
  public Charset charset()
  {
    return charset(null);
  }
  
  @Nullable
  public Charset charset(@Nullable Charset paramCharset)
  {
    Charset localCharset = paramCharset;
    try
    {
      if (charset != null) {
        localCharset = Charset.forName(charset);
      }
      return localCharset;
    }
    catch (IllegalArgumentException localIllegalArgumentException) {}
    return paramCharset;
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    return ((paramObject instanceof MediaType)) && (mediaType.equals(mediaType));
  }
  
  public int hashCode()
  {
    return mediaType.hashCode();
  }
  
  public String subtype()
  {
    return subtype;
  }
  
  public String toString()
  {
    return mediaType;
  }
  
  public String type()
  {
    return type;
  }
}

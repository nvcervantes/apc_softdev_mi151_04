package okhttp3;

import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.Util;

public final class Challenge
{
  private final Charset charset;
  private final String realm;
  private final String scheme;
  
  public Challenge(String paramString1, String paramString2)
  {
    this(paramString1, paramString2, Util.ISO_8859_1);
  }
  
  private Challenge(String paramString1, String paramString2, Charset paramCharset)
  {
    if (paramString1 == null) {
      throw new NullPointerException("scheme == null");
    }
    if (paramString2 == null) {
      throw new NullPointerException("realm == null");
    }
    if (paramCharset == null) {
      throw new NullPointerException("charset == null");
    }
    scheme = paramString1;
    realm = paramString2;
    charset = paramCharset;
  }
  
  public Charset charset()
  {
    return charset;
  }
  
  public boolean equals(@Nullable Object paramObject)
  {
    if ((paramObject instanceof Challenge))
    {
      paramObject = (Challenge)paramObject;
      if ((scheme.equals(scheme)) && (realm.equals(realm)) && (charset.equals(charset))) {
        return true;
      }
    }
    return false;
  }
  
  public int hashCode()
  {
    return 31 * ((899 + realm.hashCode()) * 31 + scheme.hashCode()) + charset.hashCode();
  }
  
  public String realm()
  {
    return realm;
  }
  
  public String scheme()
  {
    return scheme;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(scheme);
    localStringBuilder.append(" realm=\"");
    localStringBuilder.append(realm);
    localStringBuilder.append("\" charset=\"");
    localStringBuilder.append(charset);
    localStringBuilder.append("\"");
    return localStringBuilder.toString();
  }
  
  public Challenge withCharset(Charset paramCharset)
  {
    return new Challenge(scheme, realm, paramCharset);
  }
}

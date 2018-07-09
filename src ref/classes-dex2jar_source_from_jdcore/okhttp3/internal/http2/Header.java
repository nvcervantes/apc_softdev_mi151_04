package okhttp3.internal.http2;

import okhttp3.internal.Util;
import okio.ByteString;

public final class Header
{
  public static final ByteString PSEUDO_PREFIX = ByteString.encodeUtf8(":");
  public static final ByteString RESPONSE_STATUS = ByteString.encodeUtf8(":status");
  public static final ByteString TARGET_AUTHORITY = ByteString.encodeUtf8(":authority");
  public static final ByteString TARGET_METHOD = ByteString.encodeUtf8(":method");
  public static final ByteString TARGET_PATH = ByteString.encodeUtf8(":path");
  public static final ByteString TARGET_SCHEME = ByteString.encodeUtf8(":scheme");
  final int hpackSize;
  public final ByteString name;
  public final ByteString value;
  
  public Header(String paramString1, String paramString2)
  {
    this(ByteString.encodeUtf8(paramString1), ByteString.encodeUtf8(paramString2));
  }
  
  public Header(ByteString paramByteString, String paramString)
  {
    this(paramByteString, ByteString.encodeUtf8(paramString));
  }
  
  public Header(ByteString paramByteString1, ByteString paramByteString2)
  {
    name = paramByteString1;
    value = paramByteString2;
    hpackSize = (32 + paramByteString1.size() + paramByteString2.size());
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof Header;
    boolean bool2 = false;
    if (bool1)
    {
      paramObject = (Header)paramObject;
      bool1 = bool2;
      if (name.equals(name))
      {
        bool1 = bool2;
        if (value.equals(value)) {
          bool1 = true;
        }
      }
      return bool1;
    }
    return false;
  }
  
  public int hashCode()
  {
    return 31 * (527 + name.hashCode()) + value.hashCode();
  }
  
  public String toString()
  {
    return Util.format("%s: %s", new Object[] { name.utf8(), value.utf8() });
  }
}

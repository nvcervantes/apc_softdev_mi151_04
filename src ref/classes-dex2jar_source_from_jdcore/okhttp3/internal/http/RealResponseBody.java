package okhttp3.internal.http;

import javax.annotation.Nullable;
import okhttp3.MediaType;
import okhttp3.ResponseBody;
import okio.BufferedSource;

public final class RealResponseBody
  extends ResponseBody
{
  private final long contentLength;
  @Nullable
  private final String contentTypeString;
  private final BufferedSource source;
  
  public RealResponseBody(@Nullable String paramString, long paramLong, BufferedSource paramBufferedSource)
  {
    contentTypeString = paramString;
    contentLength = paramLong;
    source = paramBufferedSource;
  }
  
  public long contentLength()
  {
    return contentLength;
  }
  
  public MediaType contentType()
  {
    if (contentTypeString != null) {
      return MediaType.parse(contentTypeString);
    }
    return null;
  }
  
  public BufferedSource source()
  {
    return source;
  }
}

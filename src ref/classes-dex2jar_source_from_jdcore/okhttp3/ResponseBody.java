package okhttp3;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;

public abstract class ResponseBody
  implements Closeable
{
  private Reader reader;
  
  public ResponseBody() {}
  
  private Charset charset()
  {
    MediaType localMediaType = contentType();
    if (localMediaType != null) {
      return localMediaType.charset(Util.UTF_8);
    }
    return Util.UTF_8;
  }
  
  public static ResponseBody create(@Nullable MediaType paramMediaType, final long paramLong, BufferedSource paramBufferedSource)
  {
    if (paramBufferedSource == null) {
      throw new NullPointerException("source == null");
    }
    new ResponseBody()
    {
      public long contentLength()
      {
        return paramLong;
      }
      
      @Nullable
      public MediaType contentType()
      {
        return ResponseBody.this;
      }
      
      public BufferedSource source()
      {
        return val$content;
      }
    };
  }
  
  public static ResponseBody create(@Nullable MediaType paramMediaType, String paramString)
  {
    Object localObject1 = Util.UTF_8;
    Object localObject2 = paramMediaType;
    if (paramMediaType != null)
    {
      Charset localCharset = paramMediaType.charset();
      localObject1 = localCharset;
      localObject2 = paramMediaType;
      if (localCharset == null)
      {
        localObject1 = Util.UTF_8;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append(paramMediaType);
        ((StringBuilder)localObject2).append("; charset=utf-8");
        localObject2 = MediaType.parse(((StringBuilder)localObject2).toString());
      }
    }
    paramMediaType = new Buffer().writeString(paramString, (Charset)localObject1);
    return create((MediaType)localObject2, paramMediaType.size(), paramMediaType);
  }
  
  public static ResponseBody create(@Nullable MediaType paramMediaType, byte[] paramArrayOfByte)
  {
    Buffer localBuffer = new Buffer().write(paramArrayOfByte);
    return create(paramMediaType, paramArrayOfByte.length, localBuffer);
  }
  
  public final InputStream byteStream()
  {
    return source().inputStream();
  }
  
  public final byte[] bytes()
    throws IOException
  {
    long l = contentLength();
    if (l > 2147483647L)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Cannot buffer entire body for content length: ");
      ((StringBuilder)localObject1).append(l);
      throw new IOException(((StringBuilder)localObject1).toString());
    }
    Object localObject1 = source();
    try
    {
      byte[] arrayOfByte = ((BufferedSource)localObject1).readByteArray();
      Util.closeQuietly((Closeable)localObject1);
      if ((l != -1L) && (l != arrayOfByte.length))
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Content-Length (");
        ((StringBuilder)localObject1).append(l);
        ((StringBuilder)localObject1).append(") and stream length (");
        ((StringBuilder)localObject1).append(arrayOfByte.length);
        ((StringBuilder)localObject1).append(") disagree");
        throw new IOException(((StringBuilder)localObject1).toString());
      }
      return arrayOfByte;
    }
    finally
    {
      Util.closeQuietly((Closeable)localObject1);
    }
  }
  
  public final Reader charStream()
  {
    Object localObject = reader;
    if (localObject != null) {
      return localObject;
    }
    localObject = new BomAwareReader(source(), charset());
    reader = ((Reader)localObject);
    return localObject;
  }
  
  public void close()
  {
    Util.closeQuietly(source());
  }
  
  public abstract long contentLength();
  
  @Nullable
  public abstract MediaType contentType();
  
  public abstract BufferedSource source();
  
  public final String string()
    throws IOException
  {
    BufferedSource localBufferedSource = source();
    try
    {
      String str = localBufferedSource.readString(Util.bomAwareCharset(localBufferedSource, charset()));
      return str;
    }
    finally
    {
      Util.closeQuietly(localBufferedSource);
    }
  }
  
  static final class BomAwareReader
    extends Reader
  {
    private final Charset charset;
    private boolean closed;
    private Reader delegate;
    private final BufferedSource source;
    
    BomAwareReader(BufferedSource paramBufferedSource, Charset paramCharset)
    {
      source = paramBufferedSource;
      charset = paramCharset;
    }
    
    public void close()
      throws IOException
    {
      closed = true;
      if (delegate != null)
      {
        delegate.close();
        return;
      }
      source.close();
    }
    
    public int read(char[] paramArrayOfChar, int paramInt1, int paramInt2)
      throws IOException
    {
      if (closed) {
        throw new IOException("Stream closed");
      }
      Reader localReader = delegate;
      Object localObject = localReader;
      if (localReader == null)
      {
        localObject = Util.bomAwareCharset(source, charset);
        localObject = new InputStreamReader(source.inputStream(), (Charset)localObject);
        delegate = ((Reader)localObject);
      }
      return ((Reader)localObject).read(paramArrayOfChar, paramInt1, paramInt2);
    }
  }
}

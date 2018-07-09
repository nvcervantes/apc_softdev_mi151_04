package okhttp3;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.BufferedSink;
import okio.ByteString;

public abstract class RequestBody
{
  public RequestBody() {}
  
  public static RequestBody create(@Nullable MediaType paramMediaType, final File paramFile)
  {
    if (paramFile == null) {
      throw new NullPointerException("content == null");
    }
    new RequestBody()
    {
      public long contentLength()
      {
        return paramFile.length();
      }
      
      @Nullable
      public MediaType contentType()
      {
        return RequestBody.this;
      }
      
      /* Error */
      public void writeTo(BufferedSink paramAnonymousBufferedSink)
        throws IOException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore_3
        //   2: aload_0
        //   3: getfield 17	okhttp3/RequestBody$3:val$file	Ljava/io/File;
        //   6: invokestatic 42	okio/Okio:source	(Ljava/io/File;)Lokio/Source;
        //   9: astore_2
        //   10: aload_1
        //   11: aload_2
        //   12: invokeinterface 48 2 0
        //   17: pop2
        //   18: aload_2
        //   19: invokestatic 54	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
        //   22: return
        //   23: astore_3
        //   24: aload_2
        //   25: astore_1
        //   26: aload_3
        //   27: astore_2
        //   28: goto +6 -> 34
        //   31: astore_2
        //   32: aload_3
        //   33: astore_1
        //   34: aload_1
        //   35: invokestatic 54	okhttp3/internal/Util:closeQuietly	(Ljava/io/Closeable;)V
        //   38: aload_2
        //   39: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	40	0	this	3
        //   0	40	1	paramAnonymousBufferedSink	BufferedSink
        //   9	19	2	localObject1	Object
        //   31	8	2	localObject2	Object
        //   1	1	3	localObject3	Object
        //   23	10	3	localObject4	Object
        // Exception table:
        //   from	to	target	type
        //   10	18	23	finally
        //   2	10	31	finally
      }
    };
  }
  
  public static RequestBody create(@Nullable MediaType paramMediaType, String paramString)
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
    return create((MediaType)localObject2, paramString.getBytes((Charset)localObject1));
  }
  
  public static RequestBody create(@Nullable MediaType paramMediaType, final ByteString paramByteString)
  {
    new RequestBody()
    {
      public long contentLength()
        throws IOException
      {
        return paramByteString.size();
      }
      
      @Nullable
      public MediaType contentType()
      {
        return RequestBody.this;
      }
      
      public void writeTo(BufferedSink paramAnonymousBufferedSink)
        throws IOException
      {
        paramAnonymousBufferedSink.write(paramByteString);
      }
    };
  }
  
  public static RequestBody create(@Nullable MediaType paramMediaType, byte[] paramArrayOfByte)
  {
    return create(paramMediaType, paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public static RequestBody create(@Nullable MediaType paramMediaType, final byte[] paramArrayOfByte, final int paramInt1, final int paramInt2)
  {
    if (paramArrayOfByte == null) {
      throw new NullPointerException("content == null");
    }
    Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
    new RequestBody()
    {
      public long contentLength()
      {
        return paramInt2;
      }
      
      @Nullable
      public MediaType contentType()
      {
        return RequestBody.this;
      }
      
      public void writeTo(BufferedSink paramAnonymousBufferedSink)
        throws IOException
      {
        paramAnonymousBufferedSink.write(paramArrayOfByte, paramInt1, paramInt2);
      }
    };
  }
  
  public long contentLength()
    throws IOException
  {
    return -1L;
  }
  
  @Nullable
  public abstract MediaType contentType();
  
  public abstract void writeTo(BufferedSink paramBufferedSink)
    throws IOException;
}

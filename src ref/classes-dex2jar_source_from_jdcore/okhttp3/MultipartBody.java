package okhttp3;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

public final class MultipartBody
  extends RequestBody
{
  public static final MediaType ALTERNATIVE;
  private static final byte[] COLONSPACE = { 58, 32 };
  private static final byte[] CRLF = { 13, 10 };
  private static final byte[] DASHDASH = { 45, 45 };
  public static final MediaType DIGEST;
  public static final MediaType FORM;
  public static final MediaType MIXED = MediaType.parse("multipart/mixed");
  public static final MediaType PARALLEL;
  private final ByteString boundary;
  private long contentLength = -1L;
  private final MediaType contentType;
  private final MediaType originalType;
  private final List<Part> parts;
  
  static
  {
    ALTERNATIVE = MediaType.parse("multipart/alternative");
    DIGEST = MediaType.parse("multipart/digest");
    PARALLEL = MediaType.parse("multipart/parallel");
    FORM = MediaType.parse("multipart/form-data");
  }
  
  MultipartBody(ByteString paramByteString, MediaType paramMediaType, List<Part> paramList)
  {
    boundary = paramByteString;
    originalType = paramMediaType;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramMediaType);
    localStringBuilder.append("; boundary=");
    localStringBuilder.append(paramByteString.utf8());
    contentType = MediaType.parse(localStringBuilder.toString());
    parts = Util.immutableList(paramList);
  }
  
  static StringBuilder appendQuotedString(StringBuilder paramStringBuilder, String paramString)
  {
    paramStringBuilder.append('"');
    int j = paramString.length();
    int i = 0;
    while (i < j)
    {
      char c = paramString.charAt(i);
      if (c != '\n')
      {
        if (c != '\r')
        {
          if (c != '"') {
            paramStringBuilder.append(c);
          } else {
            paramStringBuilder.append("%22");
          }
        }
        else {
          paramStringBuilder.append("%0D");
        }
      }
      else {
        paramStringBuilder.append("%0A");
      }
      i += 1;
    }
    paramStringBuilder.append('"');
    return paramStringBuilder;
  }
  
  private long writeOrCountBytes(@Nullable BufferedSink paramBufferedSink, boolean paramBoolean)
    throws IOException
  {
    Object localObject1;
    Object localObject2;
    if (paramBoolean)
    {
      localObject1 = new Buffer();
      paramBufferedSink = (BufferedSink)localObject1;
    }
    else
    {
      localObject2 = null;
      localObject1 = paramBufferedSink;
      paramBufferedSink = (BufferedSink)localObject2;
    }
    int k = parts.size();
    long l1 = 0L;
    int i = 0;
    long l2;
    while (i < k)
    {
      Object localObject3 = (Part)parts.get(i);
      localObject2 = headers;
      localObject3 = body;
      ((BufferedSink)localObject1).write(DASHDASH);
      ((BufferedSink)localObject1).write(boundary);
      ((BufferedSink)localObject1).write(CRLF);
      if (localObject2 != null)
      {
        int m = ((Headers)localObject2).size();
        int j = 0;
        while (j < m)
        {
          ((BufferedSink)localObject1).writeUtf8(((Headers)localObject2).name(j)).write(COLONSPACE).writeUtf8(((Headers)localObject2).value(j)).write(CRLF);
          j += 1;
        }
      }
      localObject2 = ((RequestBody)localObject3).contentType();
      if (localObject2 != null) {
        ((BufferedSink)localObject1).writeUtf8("Content-Type: ").writeUtf8(((MediaType)localObject2).toString()).write(CRLF);
      }
      l2 = ((RequestBody)localObject3).contentLength();
      if (l2 != -1L)
      {
        ((BufferedSink)localObject1).writeUtf8("Content-Length: ").writeDecimalLong(l2).write(CRLF);
      }
      else if (paramBoolean)
      {
        paramBufferedSink.clear();
        return -1L;
      }
      ((BufferedSink)localObject1).write(CRLF);
      if (paramBoolean) {
        l1 += l2;
      } else {
        ((RequestBody)localObject3).writeTo((BufferedSink)localObject1);
      }
      ((BufferedSink)localObject1).write(CRLF);
      i += 1;
    }
    ((BufferedSink)localObject1).write(DASHDASH);
    ((BufferedSink)localObject1).write(boundary);
    ((BufferedSink)localObject1).write(DASHDASH);
    ((BufferedSink)localObject1).write(CRLF);
    if (paramBoolean)
    {
      l2 = paramBufferedSink.size();
      paramBufferedSink.clear();
      return l1 + l2;
    }
    return l1;
  }
  
  public String boundary()
  {
    return boundary.utf8();
  }
  
  public long contentLength()
    throws IOException
  {
    long l = contentLength;
    if (l != -1L) {
      return l;
    }
    l = writeOrCountBytes(null, true);
    contentLength = l;
    return l;
  }
  
  public MediaType contentType()
  {
    return contentType;
  }
  
  public Part part(int paramInt)
  {
    return (Part)parts.get(paramInt);
  }
  
  public List<Part> parts()
  {
    return parts;
  }
  
  public int size()
  {
    return parts.size();
  }
  
  public MediaType type()
  {
    return originalType;
  }
  
  public void writeTo(BufferedSink paramBufferedSink)
    throws IOException
  {
    writeOrCountBytes(paramBufferedSink, false);
  }
  
  public static final class Builder
  {
    private final ByteString boundary;
    private final List<MultipartBody.Part> parts = new ArrayList();
    private MediaType type = MultipartBody.MIXED;
    
    public Builder()
    {
      this(UUID.randomUUID().toString());
    }
    
    public Builder(String paramString)
    {
      boundary = ByteString.encodeUtf8(paramString);
    }
    
    public Builder addFormDataPart(String paramString1, String paramString2)
    {
      return addPart(MultipartBody.Part.createFormData(paramString1, paramString2));
    }
    
    public Builder addFormDataPart(String paramString1, @Nullable String paramString2, RequestBody paramRequestBody)
    {
      return addPart(MultipartBody.Part.createFormData(paramString1, paramString2, paramRequestBody));
    }
    
    public Builder addPart(@Nullable Headers paramHeaders, RequestBody paramRequestBody)
    {
      return addPart(MultipartBody.Part.create(paramHeaders, paramRequestBody));
    }
    
    public Builder addPart(MultipartBody.Part paramPart)
    {
      if (paramPart == null) {
        throw new NullPointerException("part == null");
      }
      parts.add(paramPart);
      return this;
    }
    
    public Builder addPart(RequestBody paramRequestBody)
    {
      return addPart(MultipartBody.Part.create(paramRequestBody));
    }
    
    public MultipartBody build()
    {
      if (parts.isEmpty()) {
        throw new IllegalStateException("Multipart body must have at least one part.");
      }
      return new MultipartBody(boundary, type, parts);
    }
    
    public Builder setType(MediaType paramMediaType)
    {
      if (paramMediaType == null) {
        throw new NullPointerException("type == null");
      }
      if (!paramMediaType.type().equals("multipart"))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("multipart != ");
        localStringBuilder.append(paramMediaType);
        throw new IllegalArgumentException(localStringBuilder.toString());
      }
      type = paramMediaType;
      return this;
    }
  }
  
  public static final class Part
  {
    final RequestBody body;
    @Nullable
    final Headers headers;
    
    private Part(@Nullable Headers paramHeaders, RequestBody paramRequestBody)
    {
      headers = paramHeaders;
      body = paramRequestBody;
    }
    
    public static Part create(@Nullable Headers paramHeaders, RequestBody paramRequestBody)
    {
      if (paramRequestBody == null) {
        throw new NullPointerException("body == null");
      }
      if ((paramHeaders != null) && (paramHeaders.get("Content-Type") != null)) {
        throw new IllegalArgumentException("Unexpected header: Content-Type");
      }
      if ((paramHeaders != null) && (paramHeaders.get("Content-Length") != null)) {
        throw new IllegalArgumentException("Unexpected header: Content-Length");
      }
      return new Part(paramHeaders, paramRequestBody);
    }
    
    public static Part create(RequestBody paramRequestBody)
    {
      return create(null, paramRequestBody);
    }
    
    public static Part createFormData(String paramString1, String paramString2)
    {
      return createFormData(paramString1, null, RequestBody.create(null, paramString2));
    }
    
    public static Part createFormData(String paramString1, @Nullable String paramString2, RequestBody paramRequestBody)
    {
      if (paramString1 == null) {
        throw new NullPointerException("name == null");
      }
      StringBuilder localStringBuilder = new StringBuilder("form-data; name=");
      MultipartBody.appendQuotedString(localStringBuilder, paramString1);
      if (paramString2 != null)
      {
        localStringBuilder.append("; filename=");
        MultipartBody.appendQuotedString(localStringBuilder, paramString2);
      }
      return create(Headers.of(new String[] { "Content-Disposition", localStringBuilder.toString() }), paramRequestBody);
    }
    
    public RequestBody body()
    {
      return body;
    }
    
    @Nullable
    public Headers headers()
    {
      return headers;
    }
  }
}

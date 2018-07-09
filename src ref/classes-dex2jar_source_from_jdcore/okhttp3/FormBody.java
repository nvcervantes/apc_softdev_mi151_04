package okhttp3;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.Nullable;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSink;

public final class FormBody
  extends RequestBody
{
  private static final MediaType CONTENT_TYPE = MediaType.parse("application/x-www-form-urlencoded");
  private final List<String> encodedNames;
  private final List<String> encodedValues;
  
  FormBody(List<String> paramList1, List<String> paramList2)
  {
    encodedNames = Util.immutableList(paramList1);
    encodedValues = Util.immutableList(paramList2);
  }
  
  private long writeOrCountBytes(@Nullable BufferedSink paramBufferedSink, boolean paramBoolean)
  {
    if (paramBoolean) {
      paramBufferedSink = new Buffer();
    } else {
      paramBufferedSink = paramBufferedSink.buffer();
    }
    int i = 0;
    int j = encodedNames.size();
    while (i < j)
    {
      if (i > 0) {
        paramBufferedSink.writeByte(38);
      }
      paramBufferedSink.writeUtf8((String)encodedNames.get(i));
      paramBufferedSink.writeByte(61);
      paramBufferedSink.writeUtf8((String)encodedValues.get(i));
      i += 1;
    }
    if (paramBoolean)
    {
      long l = paramBufferedSink.size();
      paramBufferedSink.clear();
      return l;
    }
    return 0L;
  }
  
  public long contentLength()
  {
    return writeOrCountBytes(null, true);
  }
  
  public MediaType contentType()
  {
    return CONTENT_TYPE;
  }
  
  public String encodedName(int paramInt)
  {
    return (String)encodedNames.get(paramInt);
  }
  
  public String encodedValue(int paramInt)
  {
    return (String)encodedValues.get(paramInt);
  }
  
  public String name(int paramInt)
  {
    return HttpUrl.percentDecode(encodedName(paramInt), true);
  }
  
  public int size()
  {
    return encodedNames.size();
  }
  
  public String value(int paramInt)
  {
    return HttpUrl.percentDecode(encodedValue(paramInt), true);
  }
  
  public void writeTo(BufferedSink paramBufferedSink)
    throws IOException
  {
    writeOrCountBytes(paramBufferedSink, false);
  }
  
  public static final class Builder
  {
    private final Charset charset;
    private final List<String> names = new ArrayList();
    private final List<String> values = new ArrayList();
    
    public Builder()
    {
      this(null);
    }
    
    public Builder(Charset paramCharset)
    {
      charset = paramCharset;
    }
    
    public Builder add(String paramString1, String paramString2)
    {
      names.add(HttpUrl.canonicalize(paramString1, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, charset));
      values.add(HttpUrl.canonicalize(paramString2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", false, false, true, true, charset));
      return this;
    }
    
    public Builder addEncoded(String paramString1, String paramString2)
    {
      names.add(HttpUrl.canonicalize(paramString1, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, charset));
      values.add(HttpUrl.canonicalize(paramString2, " \"':;<=>@[]^`{}|/\\?#&!$(),~", true, false, true, true, charset));
      return this;
    }
    
    public FormBody build()
    {
      return new FormBody(names, values);
    }
  }
}

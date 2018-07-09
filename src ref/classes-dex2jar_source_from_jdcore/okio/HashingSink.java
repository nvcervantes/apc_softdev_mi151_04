package okio;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class HashingSink
  extends ForwardingSink
{
  @Nullable
  private final Mac mac;
  @Nullable
  private final MessageDigest messageDigest;
  
  private HashingSink(Sink paramSink, String paramString)
  {
    super(paramSink);
    try
    {
      messageDigest = MessageDigest.getInstance(paramString);
      mac = null;
      return;
    }
    catch (NoSuchAlgorithmException paramSink)
    {
      for (;;) {}
    }
    throw new AssertionError();
  }
  
  private HashingSink(Sink paramSink, ByteString paramByteString, String paramString)
  {
    super(paramSink);
    try
    {
      mac = Mac.getInstance(paramString);
      mac.init(new SecretKeySpec(paramByteString.toByteArray(), paramString));
      messageDigest = null;
      return;
    }
    catch (InvalidKeyException paramSink)
    {
      throw new IllegalArgumentException(paramSink);
      throw new AssertionError();
    }
    catch (NoSuchAlgorithmException paramSink)
    {
      for (;;) {}
    }
  }
  
  public static HashingSink hmacSha1(Sink paramSink, ByteString paramByteString)
  {
    return new HashingSink(paramSink, paramByteString, "HmacSHA1");
  }
  
  public static HashingSink hmacSha256(Sink paramSink, ByteString paramByteString)
  {
    return new HashingSink(paramSink, paramByteString, "HmacSHA256");
  }
  
  public static HashingSink hmacSha512(Sink paramSink, ByteString paramByteString)
  {
    return new HashingSink(paramSink, paramByteString, "HmacSHA512");
  }
  
  public static HashingSink md5(Sink paramSink)
  {
    return new HashingSink(paramSink, "MD5");
  }
  
  public static HashingSink sha1(Sink paramSink)
  {
    return new HashingSink(paramSink, "SHA-1");
  }
  
  public static HashingSink sha256(Sink paramSink)
  {
    return new HashingSink(paramSink, "SHA-256");
  }
  
  public static HashingSink sha512(Sink paramSink)
  {
    return new HashingSink(paramSink, "SHA-512");
  }
  
  public ByteString hash()
  {
    byte[] arrayOfByte;
    if (messageDigest != null) {
      arrayOfByte = messageDigest.digest();
    } else {
      arrayOfByte = mac.doFinal();
    }
    return ByteString.of(arrayOfByte);
  }
  
  public void write(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    Util.checkOffsetAndCount(size, 0L, paramLong);
    Segment localSegment = head;
    long l2;
    for (long l1 = 0L; l1 < paramLong; l1 += l2)
    {
      int i = (int)Math.min(paramLong - l1, limit - pos);
      if (messageDigest != null) {
        messageDigest.update(data, pos, i);
      } else {
        mac.update(data, pos, i);
      }
      l2 = i;
      localSegment = next;
    }
    super.write(paramBuffer, paramLong);
  }
}

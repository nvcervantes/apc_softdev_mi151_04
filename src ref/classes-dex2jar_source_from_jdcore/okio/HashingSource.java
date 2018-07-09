package okio;

import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class HashingSource
  extends ForwardingSource
{
  private final Mac mac;
  private final MessageDigest messageDigest;
  
  private HashingSource(Source paramSource, String paramString)
  {
    super(paramSource);
    try
    {
      messageDigest = MessageDigest.getInstance(paramString);
      mac = null;
      return;
    }
    catch (NoSuchAlgorithmException paramSource)
    {
      for (;;) {}
    }
    throw new AssertionError();
  }
  
  private HashingSource(Source paramSource, ByteString paramByteString, String paramString)
  {
    super(paramSource);
    try
    {
      mac = Mac.getInstance(paramString);
      mac.init(new SecretKeySpec(paramByteString.toByteArray(), paramString));
      messageDigest = null;
      return;
    }
    catch (InvalidKeyException paramSource)
    {
      throw new IllegalArgumentException(paramSource);
      throw new AssertionError();
    }
    catch (NoSuchAlgorithmException paramSource)
    {
      for (;;) {}
    }
  }
  
  public static HashingSource hmacSha1(Source paramSource, ByteString paramByteString)
  {
    return new HashingSource(paramSource, paramByteString, "HmacSHA1");
  }
  
  public static HashingSource hmacSha256(Source paramSource, ByteString paramByteString)
  {
    return new HashingSource(paramSource, paramByteString, "HmacSHA256");
  }
  
  public static HashingSource md5(Source paramSource)
  {
    return new HashingSource(paramSource, "MD5");
  }
  
  public static HashingSource sha1(Source paramSource)
  {
    return new HashingSource(paramSource, "SHA-1");
  }
  
  public static HashingSource sha256(Source paramSource)
  {
    return new HashingSource(paramSource, "SHA-256");
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
  
  public long read(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    long l4 = super.read(paramBuffer, paramLong);
    if (l4 != -1L)
    {
      long l3 = size - l4;
      paramLong = size;
      Segment localSegment1 = head;
      long l1;
      long l2;
      Segment localSegment2;
      for (;;)
      {
        l1 = paramLong;
        l2 = l3;
        localSegment2 = localSegment1;
        if (paramLong <= l3) {
          break;
        }
        localSegment1 = prev;
        paramLong -= limit - pos;
      }
      while (l1 < size)
      {
        int i = (int)(pos + l2 - l1);
        if (messageDigest != null) {
          messageDigest.update(data, i, limit - i);
        } else {
          mac.update(data, i, limit - i);
        }
        paramLong = limit - pos;
        localSegment2 = next;
        l1 += paramLong;
        l2 = l1;
      }
    }
    return l4;
  }
}

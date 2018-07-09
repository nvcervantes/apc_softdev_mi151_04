package okio;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.util.Arrays;

final class SegmentedByteString
  extends ByteString
{
  final transient int[] directory;
  final transient byte[][] segments;
  
  SegmentedByteString(Buffer paramBuffer, int paramInt)
  {
    super(null);
    Util.checkOffsetAndCount(size, 0L, paramInt);
    Segment localSegment = head;
    int k = 0;
    int j = 0;
    int i = j;
    while (j < paramInt)
    {
      if (limit == pos) {
        throw new AssertionError("s.limit == s.pos");
      }
      j += limit - pos;
      i += 1;
      localSegment = next;
    }
    segments = new byte[i][];
    directory = new int[i * 2];
    paramBuffer = head;
    j = 0;
    i = k;
    while (i < paramInt)
    {
      segments[j] = data;
      k = i + (limit - pos);
      i = k;
      if (k > paramInt) {
        i = paramInt;
      }
      directory[j] = i;
      directory[(segments.length + j)] = pos;
      shared = true;
      j += 1;
      paramBuffer = next;
    }
  }
  
  private int segment(int paramInt)
  {
    paramInt = Arrays.binarySearch(directory, 0, segments.length, paramInt + 1);
    if (paramInt >= 0) {
      return paramInt;
    }
    return paramInt ^ 0xFFFFFFFF;
  }
  
  private ByteString toByteString()
  {
    return new ByteString(toByteArray());
  }
  
  private Object writeReplace()
  {
    return toByteString();
  }
  
  public ByteBuffer asByteBuffer()
  {
    return ByteBuffer.wrap(toByteArray()).asReadOnlyBuffer();
  }
  
  public String base64()
  {
    return toByteString().base64();
  }
  
  public String base64Url()
  {
    return toByteString().base64Url();
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if ((paramObject instanceof ByteString))
    {
      paramObject = (ByteString)paramObject;
      if ((paramObject.size() == size()) && (rangeEquals(0, paramObject, 0, size()))) {
        return true;
      }
    }
    return false;
  }
  
  public byte getByte(int paramInt)
  {
    Util.checkOffsetAndCount(directory[(segments.length - 1)], paramInt, 1L);
    int j = segment(paramInt);
    int i;
    if (j == 0) {
      i = 0;
    } else {
      i = directory[(j - 1)];
    }
    int k = directory[(segments.length + j)];
    return segments[j][(paramInt - i + k)];
  }
  
  public int hashCode()
  {
    int i = hashCode;
    if (i != 0) {
      return i;
    }
    Object localObject = segments;
    int j = 0;
    int i2 = localObject.length;
    int m = 1;
    int n;
    for (int k = 0; j < i2; k = n)
    {
      localObject = segments[j];
      int i1 = directory[(i2 + j)];
      n = directory[j];
      i = i1;
      while (i < n - k + i1)
      {
        m = localObject[i] + 31 * m;
        i += 1;
      }
      j += 1;
    }
    hashCode = m;
    return m;
  }
  
  public String hex()
  {
    return toByteString().hex();
  }
  
  public ByteString hmacSha1(ByteString paramByteString)
  {
    return toByteString().hmacSha1(paramByteString);
  }
  
  public ByteString hmacSha256(ByteString paramByteString)
  {
    return toByteString().hmacSha256(paramByteString);
  }
  
  public int indexOf(byte[] paramArrayOfByte, int paramInt)
  {
    return toByteString().indexOf(paramArrayOfByte, paramInt);
  }
  
  byte[] internalArray()
  {
    return toByteArray();
  }
  
  public int lastIndexOf(byte[] paramArrayOfByte, int paramInt)
  {
    return toByteString().lastIndexOf(paramArrayOfByte, paramInt);
  }
  
  public ByteString md5()
  {
    return toByteString().md5();
  }
  
  public boolean rangeEquals(int paramInt1, ByteString paramByteString, int paramInt2, int paramInt3)
  {
    if (paramInt1 >= 0)
    {
      if (paramInt1 > size() - paramInt3) {
        return false;
      }
      int j = segment(paramInt1);
      int i = paramInt1;
      paramInt1 = j;
      while (paramInt3 > 0)
      {
        if (paramInt1 == 0) {
          j = 0;
        } else {
          j = directory[(paramInt1 - 1)];
        }
        int k = Math.min(paramInt3, directory[paramInt1] - j + j - i);
        int m = directory[(segments.length + paramInt1)];
        if (!paramByteString.rangeEquals(paramInt2, segments[paramInt1], i - j + m, k)) {
          return false;
        }
        i += k;
        paramInt2 += k;
        paramInt3 -= k;
        paramInt1 += 1;
      }
      return true;
    }
    return false;
  }
  
  public boolean rangeEquals(int paramInt1, byte[] paramArrayOfByte, int paramInt2, int paramInt3)
  {
    if ((paramInt1 >= 0) && (paramInt1 <= size() - paramInt3) && (paramInt2 >= 0))
    {
      if (paramInt2 > paramArrayOfByte.length - paramInt3) {
        return false;
      }
      int j = segment(paramInt1);
      int i = paramInt1;
      paramInt1 = j;
      while (paramInt3 > 0)
      {
        if (paramInt1 == 0) {
          j = 0;
        } else {
          j = directory[(paramInt1 - 1)];
        }
        int k = Math.min(paramInt3, directory[paramInt1] - j + j - i);
        int m = directory[(segments.length + paramInt1)];
        if (!Util.arrayRangeEquals(segments[paramInt1], i - j + m, paramArrayOfByte, paramInt2, k)) {
          return false;
        }
        i += k;
        paramInt2 += k;
        paramInt3 -= k;
        paramInt1 += 1;
      }
      return true;
    }
    return false;
  }
  
  public ByteString sha1()
  {
    return toByteString().sha1();
  }
  
  public ByteString sha256()
  {
    return toByteString().sha256();
  }
  
  public int size()
  {
    return directory[(segments.length - 1)];
  }
  
  public String string(Charset paramCharset)
  {
    return toByteString().string(paramCharset);
  }
  
  public ByteString substring(int paramInt)
  {
    return toByteString().substring(paramInt);
  }
  
  public ByteString substring(int paramInt1, int paramInt2)
  {
    return toByteString().substring(paramInt1, paramInt2);
  }
  
  public ByteString toAsciiLowercase()
  {
    return toByteString().toAsciiLowercase();
  }
  
  public ByteString toAsciiUppercase()
  {
    return toByteString().toAsciiUppercase();
  }
  
  public byte[] toByteArray()
  {
    Object localObject = directory;
    byte[][] arrayOfByte = segments;
    int i = 0;
    localObject = new byte[localObject[(arrayOfByte.length - 1)]];
    int m = segments.length;
    int k;
    for (int j = 0; i < m; j = k)
    {
      int n = directory[(m + i)];
      k = directory[i];
      System.arraycopy(segments[i], n, localObject, j, k - j);
      i += 1;
    }
    return localObject;
  }
  
  public String toString()
  {
    return toByteString().toString();
  }
  
  public String utf8()
  {
    return toByteString().utf8();
  }
  
  public void write(OutputStream paramOutputStream)
    throws IOException
  {
    if (paramOutputStream == null) {
      throw new IllegalArgumentException("out == null");
    }
    byte[][] arrayOfByte = segments;
    int i = 0;
    int m = arrayOfByte.length;
    int k;
    for (int j = 0; i < m; j = k)
    {
      int n = directory[(m + i)];
      k = directory[i];
      paramOutputStream.write(segments[i], n, k - j);
      i += 1;
    }
  }
  
  void write(Buffer paramBuffer)
  {
    Object localObject = segments;
    int i = 0;
    int m = localObject.length;
    int k;
    for (int j = 0; i < m; j = k)
    {
      int n = directory[(m + i)];
      k = directory[i];
      localObject = new Segment(segments[i], n, n + k - j);
      if (head == null)
      {
        prev = ((Segment)localObject);
        next = ((Segment)localObject);
        head = ((Segment)localObject);
      }
      else
      {
        head.prev.push((Segment)localObject);
      }
      i += 1;
    }
    size += j;
  }
}

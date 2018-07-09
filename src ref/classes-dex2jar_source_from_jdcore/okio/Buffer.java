package okio;

import java.io.EOFException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.Charset;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.annotation.Nullable;
import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;

public final class Buffer
  implements BufferedSource, BufferedSink, Cloneable
{
  private static final byte[] DIGITS = { 48, 49, 50, 51, 52, 53, 54, 55, 56, 57, 97, 98, 99, 100, 101, 102 };
  static final int REPLACEMENT_CHARACTER = 65533;
  @Nullable
  Segment head;
  long size;
  
  public Buffer() {}
  
  private ByteString digest(String paramString)
  {
    try
    {
      MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
      if (head != null)
      {
        localMessageDigest.update(head.data, head.pos, head.limit - head.pos);
        paramString = head;
        for (;;)
        {
          paramString = next;
          if (paramString == head) {
            break;
          }
          localMessageDigest.update(data, pos, limit - pos);
        }
      }
      paramString = ByteString.of(localMessageDigest.digest());
      return paramString;
    }
    catch (NoSuchAlgorithmException paramString)
    {
      for (;;) {}
    }
    throw new AssertionError();
  }
  
  private ByteString hmac(String paramString, ByteString paramByteString)
  {
    try
    {
      Mac localMac = Mac.getInstance(paramString);
      localMac.init(new SecretKeySpec(paramByteString.toByteArray(), paramString));
      if (head != null)
      {
        localMac.update(head.data, head.pos, head.limit - head.pos);
        paramString = head;
        for (;;)
        {
          paramString = next;
          if (paramString == head) {
            break;
          }
          localMac.update(data, pos, limit - pos);
        }
      }
      paramString = ByteString.of(localMac.doFinal());
      return paramString;
    }
    catch (InvalidKeyException paramString)
    {
      throw new IllegalArgumentException(paramString);
      throw new AssertionError();
    }
    catch (NoSuchAlgorithmException paramString)
    {
      for (;;) {}
    }
  }
  
  private boolean rangeEquals(Segment paramSegment, int paramInt1, ByteString paramByteString, int paramInt2, int paramInt3)
  {
    int i = limit;
    Object localObject = data;
    while (paramInt2 < paramInt3)
    {
      int j = i;
      Segment localSegment = paramSegment;
      int k = paramInt1;
      if (paramInt1 == i)
      {
        localSegment = next;
        paramSegment = data;
        k = pos;
        j = limit;
        localObject = paramSegment;
      }
      if (localObject[k] != paramByteString.getByte(paramInt2)) {
        return false;
      }
      paramInt1 = k + 1;
      paramInt2 += 1;
      i = j;
      paramSegment = localSegment;
    }
    return true;
  }
  
  private void readFrom(InputStream paramInputStream, long paramLong, boolean paramBoolean)
    throws IOException
  {
    if (paramInputStream == null) {
      throw new IllegalArgumentException("in == null");
    }
    for (;;)
    {
      if ((paramLong <= 0L) && (!paramBoolean)) {
        return;
      }
      Segment localSegment = writableSegment(1);
      int i = (int)Math.min(paramLong, 8192 - limit);
      i = paramInputStream.read(data, limit, i);
      if (i == -1)
      {
        if (paramBoolean) {
          return;
        }
        throw new EOFException();
      }
      limit += i;
      long l1 = size;
      long l2 = i;
      size = (l1 + l2);
      paramLong -= l2;
    }
  }
  
  public Buffer buffer()
  {
    return this;
  }
  
  public void clear()
  {
    try
    {
      skip(size);
      return;
    }
    catch (EOFException localEOFException)
    {
      throw new AssertionError(localEOFException);
    }
  }
  
  public Buffer clone()
  {
    Buffer localBuffer = new Buffer();
    if (size == 0L) {
      return localBuffer;
    }
    head = new Segment(head);
    Segment localSegment1 = head;
    Segment localSegment2 = head;
    Segment localSegment3 = head;
    prev = localSegment3;
    next = localSegment3;
    localSegment1 = head;
    for (;;)
    {
      localSegment1 = next;
      if (localSegment1 == head) {
        break;
      }
      head.prev.push(new Segment(localSegment1));
    }
    size = size;
    return localBuffer;
  }
  
  public void close() {}
  
  public long completeSegmentByteCount()
  {
    long l2 = size;
    if (l2 == 0L) {
      return 0L;
    }
    Segment localSegment = head.prev;
    long l1 = l2;
    if (limit < 8192)
    {
      l1 = l2;
      if (owner) {
        l1 = l2 - (limit - pos);
      }
    }
    return l1;
  }
  
  public Buffer copyTo(OutputStream paramOutputStream)
    throws IOException
  {
    return copyTo(paramOutputStream, 0L, size);
  }
  
  public Buffer copyTo(OutputStream paramOutputStream, long paramLong1, long paramLong2)
    throws IOException
  {
    if (paramOutputStream == null) {
      throw new IllegalArgumentException("out == null");
    }
    Util.checkOffsetAndCount(size, paramLong1, paramLong2);
    if (paramLong2 == 0L) {
      return this;
    }
    Segment localSegment1 = head;
    Segment localSegment2;
    long l2;
    long l1;
    for (;;)
    {
      localSegment2 = localSegment1;
      l2 = paramLong1;
      l1 = paramLong2;
      if (paramLong1 < limit - pos) {
        break;
      }
      l1 = limit - pos;
      localSegment1 = next;
      paramLong1 -= l1;
    }
    while (l1 > 0L)
    {
      int i = (int)(pos + l2);
      int j = (int)Math.min(limit - i, l1);
      paramOutputStream.write(data, i, j);
      paramLong1 = j;
      localSegment2 = next;
      l2 = 0L;
      l1 -= paramLong1;
    }
    return this;
  }
  
  public Buffer copyTo(Buffer paramBuffer, long paramLong1, long paramLong2)
  {
    if (paramBuffer == null) {
      throw new IllegalArgumentException("out == null");
    }
    Util.checkOffsetAndCount(size, paramLong1, paramLong2);
    if (paramLong2 == 0L) {
      return this;
    }
    size += paramLong2;
    Segment localSegment1 = head;
    Segment localSegment2;
    long l2;
    long l1;
    for (;;)
    {
      localSegment2 = localSegment1;
      l2 = paramLong1;
      l1 = paramLong2;
      if (paramLong1 < limit - pos) {
        break;
      }
      l1 = limit - pos;
      localSegment1 = next;
      paramLong1 -= l1;
    }
    while (l1 > 0L)
    {
      localSegment1 = new Segment(localSegment2);
      pos = ((int)(pos + l2));
      limit = Math.min(pos + (int)l1, limit);
      if (head == null)
      {
        prev = localSegment1;
        next = localSegment1;
        head = localSegment1;
      }
      else
      {
        head.prev.push(localSegment1);
      }
      paramLong1 = limit - pos;
      localSegment2 = next;
      l2 = 0L;
      l1 -= paramLong1;
    }
    return this;
  }
  
  public BufferedSink emit()
  {
    return this;
  }
  
  public Buffer emitCompleteSegments()
  {
    return this;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof Buffer)) {
      return false;
    }
    paramObject = (Buffer)paramObject;
    if (size != size) {
      return false;
    }
    long l2 = size;
    long l1 = 0L;
    if (l2 == 0L) {
      return true;
    }
    Segment localSegment = head;
    paramObject = head;
    int j = pos;
    int i = pos;
    while (l1 < size)
    {
      l2 = Math.min(limit - j, limit - i);
      int k = 0;
      while (k < l2)
      {
        if (data[j] != data[i]) {
          return false;
        }
        k += 1;
        j += 1;
        i += 1;
      }
      if (j == limit)
      {
        localSegment = next;
        j = pos;
      }
      if (i == limit)
      {
        paramObject = next;
        i = pos;
      }
      l1 += l2;
    }
    return true;
  }
  
  public boolean exhausted()
  {
    return size == 0L;
  }
  
  public void flush() {}
  
  public byte getByte(long paramLong)
  {
    Util.checkOffsetAndCount(size, paramLong, 1L);
    Segment localSegment = head;
    for (;;)
    {
      long l = limit - pos;
      if (paramLong < l) {
        return data[(pos + (int)paramLong)];
      }
      localSegment = next;
      paramLong -= l;
    }
  }
  
  public int hashCode()
  {
    Object localObject = head;
    if (localObject == null) {
      return 0;
    }
    int i = 1;
    int j;
    Segment localSegment;
    do
    {
      int k = pos;
      int m = limit;
      j = i;
      while (k < m)
      {
        j = data[k] + 31 * j;
        k += 1;
      }
      localSegment = next;
      localObject = localSegment;
      i = j;
    } while (localSegment != head);
    return j;
  }
  
  public ByteString hmacSha1(ByteString paramByteString)
  {
    return hmac("HmacSHA1", paramByteString);
  }
  
  public ByteString hmacSha256(ByteString paramByteString)
  {
    return hmac("HmacSHA256", paramByteString);
  }
  
  public ByteString hmacSha512(ByteString paramByteString)
  {
    return hmac("HmacSHA512", paramByteString);
  }
  
  public long indexOf(byte paramByte)
  {
    return indexOf(paramByte, 0L, Long.MAX_VALUE);
  }
  
  public long indexOf(byte paramByte, long paramLong)
  {
    return indexOf(paramByte, paramLong, Long.MAX_VALUE);
  }
  
  public long indexOf(byte paramByte, long paramLong1, long paramLong2)
  {
    long l1 = 0L;
    if ((paramLong1 >= 0L) && (paramLong2 >= paramLong1))
    {
      long l2;
      if (paramLong2 > size) {
        l2 = size;
      } else {
        l2 = paramLong2;
      }
      if (paramLong1 == l2) {
        return -1L;
      }
      Object localObject1 = head;
      if (localObject1 == null) {
        return -1L;
      }
      paramLong2 = l1;
      Object localObject2 = localObject1;
      if (size - paramLong1 < paramLong1)
      {
        l1 = size;
        localObject2 = localObject1;
        for (;;)
        {
          paramLong2 = l1;
          localObject1 = localObject2;
          if (l1 <= paramLong1) {
            break;
          }
          localObject2 = prev;
          l1 -= limit - pos;
        }
      }
      else
      {
        for (;;)
        {
          l1 = paramLong2 + (limit - pos);
          localObject1 = localObject2;
          if (l1 >= paramLong1) {
            break;
          }
          localObject2 = next;
          paramLong2 = l1;
        }
      }
      while (paramLong2 < l2)
      {
        localObject2 = data;
        int j = (int)Math.min(limit, pos + l2 - paramLong2);
        int i = (int)(pos + paramLong1 - paramLong2);
        while (i < j)
        {
          if (localObject2[i] == paramByte) {
            return i - pos + paramLong2;
          }
          i += 1;
        }
        paramLong1 = limit - pos;
        localObject1 = next;
        paramLong1 = paramLong2 + paramLong1;
        paramLong2 = paramLong1;
      }
      return -1L;
    }
    throw new IllegalArgumentException(String.format("size=%s fromIndex=%s toIndex=%s", new Object[] { Long.valueOf(size), Long.valueOf(paramLong1), Long.valueOf(paramLong2) }));
  }
  
  public long indexOf(ByteString paramByteString)
    throws IOException
  {
    return indexOf(paramByteString, 0L);
  }
  
  public long indexOf(ByteString paramByteString, long paramLong)
    throws IOException
  {
    Object localObject3 = this;
    if (paramByteString.size() == 0) {
      throw new IllegalArgumentException("bytes is empty");
    }
    long l1 = 0L;
    if (paramLong < 0L) {
      throw new IllegalArgumentException("fromIndex < 0");
    }
    Object localObject2 = head;
    if (localObject2 == null) {
      return -1L;
    }
    Object localObject1 = localObject2;
    if (size - paramLong < paramLong)
    {
      l2 = size;
      localObject1 = localObject2;
      for (;;)
      {
        l1 = l2;
        localObject2 = localObject1;
        if (l2 <= paramLong) {
          break;
        }
        localObject1 = prev;
        l2 -= limit - pos;
      }
    }
    for (;;)
    {
      l2 = l1 + (limit - pos);
      localObject2 = localObject1;
      if (l2 >= paramLong) {
        break;
      }
      localObject1 = next;
      l1 = l2;
    }
    int j = paramByteString.getByte(0);
    int k = paramByteString.size();
    long l2 = size - k + 1L;
    localObject1 = localObject2;
    while (l1 < l2)
    {
      localObject3 = data;
      int m = (int)Math.min(limit, pos + l2 - l1);
      int i = (int)(pos + paramLong - l1);
      localObject2 = localObject1;
      localObject1 = localObject3;
      while (i < m)
      {
        if ((localObject1[i] == j) && (rangeEquals((Segment)localObject2, i + 1, paramByteString, 1, k))) {
          return i - pos + l1;
        }
        i += 1;
      }
      paramLong = limit - pos;
      localObject1 = next;
      paramLong = l1 + paramLong;
      l1 = paramLong;
    }
    return -1L;
  }
  
  public long indexOfElement(ByteString paramByteString)
  {
    return indexOfElement(paramByteString, 0L);
  }
  
  public long indexOfElement(ByteString paramByteString, long paramLong)
  {
    long l1 = 0L;
    if (paramLong < 0L) {
      throw new IllegalArgumentException("fromIndex < 0");
    }
    Object localObject1 = head;
    if (localObject1 == null) {
      return -1L;
    }
    Object localObject2 = localObject1;
    long l2;
    if (size - paramLong < paramLong)
    {
      l2 = size;
      localObject2 = localObject1;
      for (;;)
      {
        l1 = l2;
        localObject1 = localObject2;
        if (l2 <= paramLong) {
          break;
        }
        localObject2 = prev;
        l2 -= limit - pos;
      }
    }
    for (;;)
    {
      l2 = l1 + (limit - pos);
      localObject1 = localObject2;
      if (l2 >= paramLong) {
        break;
      }
      localObject2 = next;
      l1 = l2;
    }
    int j;
    int k;
    int i;
    int m;
    int n;
    if (paramByteString.size() == 2)
    {
      j = paramByteString.getByte(0);
      k = paramByteString.getByte(1);
      while (l1 < size)
      {
        paramByteString = data;
        i = (int)(pos + paramLong - l1);
        m = limit;
        while (i < m)
        {
          n = paramByteString[i];
          if ((n != j) && (n != k)) {
            i += 1;
          } else {
            return i - pos + l1;
          }
        }
        paramLong = limit - pos;
        localObject1 = next;
        paramLong = l1 + paramLong;
        l1 = paramLong;
      }
    }
    paramByteString = paramByteString.internalArray();
    while (l1 < size)
    {
      localObject2 = data;
      i = (int)(pos + paramLong - l1);
      k = limit;
      while (i < k)
      {
        m = localObject2[i];
        n = paramByteString.length;
        j = 0;
        while (j < n)
        {
          if (m == paramByteString[j]) {
            return i - pos + l1;
          }
          j += 1;
        }
        i += 1;
      }
      paramLong = limit - pos;
      localObject1 = next;
      paramLong = l1 + paramLong;
      l1 = paramLong;
    }
    return -1L;
  }
  
  public InputStream inputStream()
  {
    new InputStream()
    {
      public int available()
      {
        return (int)Math.min(size, 2147483647L);
      }
      
      public void close() {}
      
      public int read()
      {
        if (size > 0L) {
          return readByte() & 0xFF;
        }
        return -1;
      }
      
      public int read(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        return Buffer.this.read(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
      }
      
      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(Buffer.this);
        localStringBuilder.append(".inputStream()");
        return localStringBuilder.toString();
      }
    };
  }
  
  public ByteString md5()
  {
    return digest("MD5");
  }
  
  public OutputStream outputStream()
  {
    new OutputStream()
    {
      public void close() {}
      
      public void flush() {}
      
      public String toString()
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(Buffer.this);
        localStringBuilder.append(".outputStream()");
        return localStringBuilder.toString();
      }
      
      public void write(int paramAnonymousInt)
      {
        writeByte((byte)paramAnonymousInt);
      }
      
      public void write(byte[] paramAnonymousArrayOfByte, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        write(paramAnonymousArrayOfByte, paramAnonymousInt1, paramAnonymousInt2);
      }
    };
  }
  
  public boolean rangeEquals(long paramLong, ByteString paramByteString)
  {
    return rangeEquals(paramLong, paramByteString, 0, paramByteString.size());
  }
  
  public boolean rangeEquals(long paramLong, ByteString paramByteString, int paramInt1, int paramInt2)
  {
    if ((paramLong >= 0L) && (paramInt1 >= 0) && (paramInt2 >= 0) && (size - paramLong >= paramInt2))
    {
      if (paramByteString.size() - paramInt1 < paramInt2) {
        return false;
      }
      int i = 0;
      while (i < paramInt2)
      {
        if (getByte(paramLong + i) != paramByteString.getByte(paramInt1 + i)) {
          return false;
        }
        i += 1;
      }
      return true;
    }
    return false;
  }
  
  public int read(byte[] paramArrayOfByte)
  {
    return read(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public int read(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    Util.checkOffsetAndCount(paramArrayOfByte.length, paramInt1, paramInt2);
    Segment localSegment = head;
    if (localSegment == null) {
      return -1;
    }
    paramInt2 = Math.min(paramInt2, limit - pos);
    System.arraycopy(data, pos, paramArrayOfByte, paramInt1, paramInt2);
    pos += paramInt2;
    size -= paramInt2;
    if (pos == limit)
    {
      head = localSegment.pop();
      SegmentPool.recycle(localSegment);
    }
    return paramInt2;
  }
  
  public long read(Buffer paramBuffer, long paramLong)
  {
    if (paramBuffer == null) {
      throw new IllegalArgumentException("sink == null");
    }
    if (paramLong < 0L)
    {
      paramBuffer = new StringBuilder();
      paramBuffer.append("byteCount < 0: ");
      paramBuffer.append(paramLong);
      throw new IllegalArgumentException(paramBuffer.toString());
    }
    if (size == 0L) {
      return -1L;
    }
    long l = paramLong;
    if (paramLong > size) {
      l = size;
    }
    paramBuffer.write(this, l);
    return l;
  }
  
  public long readAll(Sink paramSink)
    throws IOException
  {
    long l = size;
    if (l > 0L) {
      paramSink.write(this, l);
    }
    return l;
  }
  
  public byte readByte()
  {
    if (size == 0L) {
      throw new IllegalStateException("size == 0");
    }
    Segment localSegment = head;
    int i = pos;
    int j = limit;
    byte[] arrayOfByte = data;
    int k = i + 1;
    byte b = arrayOfByte[i];
    size -= 1L;
    if (k == j)
    {
      head = localSegment.pop();
      SegmentPool.recycle(localSegment);
      return b;
    }
    pos = k;
    return b;
  }
  
  public byte[] readByteArray()
  {
    try
    {
      byte[] arrayOfByte = readByteArray(size);
      return arrayOfByte;
    }
    catch (EOFException localEOFException)
    {
      throw new AssertionError(localEOFException);
    }
  }
  
  public byte[] readByteArray(long paramLong)
    throws EOFException
  {
    Util.checkOffsetAndCount(size, 0L, paramLong);
    if (paramLong > 2147483647L)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("byteCount > Integer.MAX_VALUE: ");
      ((StringBuilder)localObject).append(paramLong);
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    Object localObject = new byte[(int)paramLong];
    readFully((byte[])localObject);
    return localObject;
  }
  
  public ByteString readByteString()
  {
    return new ByteString(readByteArray());
  }
  
  public ByteString readByteString(long paramLong)
    throws EOFException
  {
    return new ByteString(readByteArray(paramLong));
  }
  
  public long readDecimalLong()
  {
    long l1 = size;
    long l3 = 0L;
    if (l1 == 0L) {
      throw new IllegalStateException("size == 0");
    }
    int n = 0;
    long l2 = -7L;
    int i1 = 0;
    int i = i1;
    int j;
    int k;
    label313:
    do
    {
      Object localObject1 = head;
      Object localObject2 = data;
      int m = pos;
      int i2 = limit;
      j = n;
      k = i1;
      l1 = l3;
      for (;;)
      {
        n = i;
        if (m >= i2) {
          break label313;
        }
        n = localObject2[m];
        if ((n >= 48) && (n <= 57))
        {
          i1 = 48 - n;
          if ((l1 >= -922337203685477580L) && ((l1 != -922337203685477580L) || (i1 >= l2)))
          {
            l1 = l1 * 10L + i1;
          }
          else
          {
            localObject1 = new Buffer().writeDecimalLong(l1).writeByte(n);
            if (k == 0) {
              ((Buffer)localObject1).readByte();
            }
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Number too large: ");
            ((StringBuilder)localObject2).append(((Buffer)localObject1).readUtf8());
            throw new NumberFormatException(((StringBuilder)localObject2).toString());
          }
        }
        else
        {
          if ((n != 45) || (j != 0)) {
            break;
          }
          l2 -= 1L;
          k = 1;
        }
        m += 1;
        j += 1;
      }
      if (j == 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Expected leading [0-9] or '-' character but was 0x");
        ((StringBuilder)localObject1).append(Integer.toHexString(n));
        throw new NumberFormatException(((StringBuilder)localObject1).toString());
      }
      n = 1;
      if (m == i2)
      {
        head = ((Segment)localObject1).pop();
        SegmentPool.recycle((Segment)localObject1);
      }
      else
      {
        pos = m;
      }
      if (n != 0) {
        break;
      }
      l3 = l1;
      i1 = k;
      i = n;
      n = j;
    } while (head != null);
    size -= j;
    if (k != 0) {
      return l1;
    }
    return -l1;
  }
  
  public Buffer readFrom(InputStream paramInputStream)
    throws IOException
  {
    readFrom(paramInputStream, Long.MAX_VALUE, true);
    return this;
  }
  
  public Buffer readFrom(InputStream paramInputStream, long paramLong)
    throws IOException
  {
    if (paramLong < 0L)
    {
      paramInputStream = new StringBuilder();
      paramInputStream.append("byteCount < 0: ");
      paramInputStream.append(paramLong);
      throw new IllegalArgumentException(paramInputStream.toString());
    }
    readFrom(paramInputStream, paramLong, false);
    return this;
  }
  
  public void readFully(Buffer paramBuffer, long paramLong)
    throws EOFException
  {
    if (size < paramLong)
    {
      paramBuffer.write(this, size);
      throw new EOFException();
    }
    paramBuffer.write(this, paramLong);
  }
  
  public void readFully(byte[] paramArrayOfByte)
    throws EOFException
  {
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = read(paramArrayOfByte, i, paramArrayOfByte.length - i);
      if (j == -1) {
        throw new EOFException();
      }
      i += j;
    }
  }
  
  public long readHexadecimalUnsignedLong()
  {
    if (size == 0L) {
      throw new IllegalStateException("size == 0");
    }
    int i = 0;
    int j = 0;
    long l2 = 0L;
    long l1;
    int k;
    label296:
    do
    {
      Object localObject1 = head;
      Object localObject2 = data;
      int m = pos;
      int i1 = limit;
      l1 = l2;
      k = i;
      for (;;)
      {
        n = j;
        if (m >= i1) {
          break label296;
        }
        n = localObject2[m];
        if ((n >= 48) && (n <= 57))
        {
          i = n - 48;
        }
        else if ((n >= 97) && (n <= 102))
        {
          i = n - 97 + 10;
        }
        else
        {
          if ((n < 65) || (n > 70)) {
            break;
          }
          i = n - 65 + 10;
        }
        if ((l1 & 0xF000000000000000) != 0L)
        {
          localObject1 = new Buffer().writeHexadecimalUnsignedLong(l1).writeByte(n);
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Number too large: ");
          ((StringBuilder)localObject2).append(((Buffer)localObject1).readUtf8());
          throw new NumberFormatException(((StringBuilder)localObject2).toString());
        }
        l2 = i;
        m += 1;
        k += 1;
        l1 = l1 << 4 | l2;
      }
      if (k == 0)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Expected leading [0-9a-fA-F] character but was 0x");
        ((StringBuilder)localObject1).append(Integer.toHexString(n));
        throw new NumberFormatException(((StringBuilder)localObject1).toString());
      }
      int n = 1;
      if (m == i1)
      {
        head = ((Segment)localObject1).pop();
        SegmentPool.recycle((Segment)localObject1);
      }
      else
      {
        pos = m;
      }
      if (n != 0) {
        break;
      }
      i = k;
      j = n;
      l2 = l1;
    } while (head != null);
    size -= k;
    return l1;
  }
  
  public int readInt()
  {
    if (size < 4L)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("size < 4: ");
      ((StringBuilder)localObject).append(size);
      throw new IllegalStateException(((StringBuilder)localObject).toString());
    }
    Object localObject = head;
    int j = pos;
    int i = limit;
    if (i - j < 4) {
      return (readByte() & 0xFF) << 24 | (readByte() & 0xFF) << 16 | (readByte() & 0xFF) << 8 | readByte() & 0xFF;
    }
    byte[] arrayOfByte = data;
    int k = j + 1;
    j = arrayOfByte[j];
    int n = k + 1;
    k = arrayOfByte[k];
    int m = n + 1;
    int i1 = arrayOfByte[n];
    n = m + 1;
    j = (j & 0xFF) << 24 | (k & 0xFF) << 16 | (i1 & 0xFF) << 8 | arrayOfByte[m] & 0xFF;
    size -= 4L;
    if (n == i)
    {
      head = ((Segment)localObject).pop();
      SegmentPool.recycle((Segment)localObject);
      return j;
    }
    pos = n;
    return j;
  }
  
  public int readIntLe()
  {
    return Util.reverseBytesInt(readInt());
  }
  
  public long readLong()
  {
    if (size < 8L)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("size < 8: ");
      ((StringBuilder)localObject).append(size);
      throw new IllegalStateException(((StringBuilder)localObject).toString());
    }
    Object localObject = head;
    int k = pos;
    int i = limit;
    if (i - k < 8) {
      return (readInt() & 0xFFFFFFFF) << 32 | readInt() & 0xFFFFFFFF;
    }
    byte[] arrayOfByte = data;
    int j = k + 1;
    long l1 = arrayOfByte[k];
    k = j + 1;
    long l2 = arrayOfByte[j];
    j = k + 1;
    long l3 = arrayOfByte[k];
    k = j + 1;
    long l4 = arrayOfByte[j];
    j = k + 1;
    long l5 = arrayOfByte[k];
    k = j + 1;
    long l6 = arrayOfByte[j];
    j = k + 1;
    long l7 = arrayOfByte[k];
    k = j + 1;
    l1 = (l1 & 0xFF) << 56 | (l2 & 0xFF) << 48 | (l3 & 0xFF) << 40 | (l4 & 0xFF) << 32 | (l5 & 0xFF) << 24 | (l6 & 0xFF) << 16 | (l7 & 0xFF) << 8 | arrayOfByte[j] & 0xFF;
    size -= 8L;
    if (k == i)
    {
      head = ((Segment)localObject).pop();
      SegmentPool.recycle((Segment)localObject);
      return l1;
    }
    pos = k;
    return l1;
  }
  
  public long readLongLe()
  {
    return Util.reverseBytesLong(readLong());
  }
  
  public short readShort()
  {
    if (size < 2L)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("size < 2: ");
      ((StringBuilder)localObject).append(size);
      throw new IllegalStateException(((StringBuilder)localObject).toString());
    }
    Object localObject = head;
    int k = pos;
    int i = limit;
    if (i - k < 2) {
      return (short)((readByte() & 0xFF) << 8 | readByte() & 0xFF);
    }
    byte[] arrayOfByte = data;
    int j = k + 1;
    k = arrayOfByte[k];
    int m = j + 1;
    j = arrayOfByte[j];
    size -= 2L;
    if (m == i)
    {
      head = ((Segment)localObject).pop();
      SegmentPool.recycle((Segment)localObject);
    }
    else
    {
      pos = m;
    }
    return (short)((k & 0xFF) << 8 | j & 0xFF);
  }
  
  public short readShortLe()
  {
    return Util.reverseBytesShort(readShort());
  }
  
  public String readString(long paramLong, Charset paramCharset)
    throws EOFException
  {
    Util.checkOffsetAndCount(size, 0L, paramLong);
    if (paramCharset == null) {
      throw new IllegalArgumentException("charset == null");
    }
    if (paramLong > 2147483647L)
    {
      paramCharset = new StringBuilder();
      paramCharset.append("byteCount > Integer.MAX_VALUE: ");
      paramCharset.append(paramLong);
      throw new IllegalArgumentException(paramCharset.toString());
    }
    if (paramLong == 0L) {
      return "";
    }
    Segment localSegment = head;
    if (pos + paramLong > limit) {
      return new String(readByteArray(paramLong), paramCharset);
    }
    paramCharset = new String(data, pos, (int)paramLong, paramCharset);
    pos = ((int)(pos + paramLong));
    size -= paramLong;
    if (pos == limit)
    {
      head = localSegment.pop();
      SegmentPool.recycle(localSegment);
    }
    return paramCharset;
  }
  
  public String readString(Charset paramCharset)
  {
    try
    {
      paramCharset = readString(size, paramCharset);
      return paramCharset;
    }
    catch (EOFException paramCharset)
    {
      throw new AssertionError(paramCharset);
    }
  }
  
  public String readUtf8()
  {
    try
    {
      String str = readString(size, Util.UTF_8);
      return str;
    }
    catch (EOFException localEOFException)
    {
      throw new AssertionError(localEOFException);
    }
  }
  
  public String readUtf8(long paramLong)
    throws EOFException
  {
    return readString(paramLong, Util.UTF_8);
  }
  
  public int readUtf8CodePoint()
    throws EOFException
  {
    if (size == 0L) {
      throw new EOFException();
    }
    int n = getByte(0L);
    int m = 1;
    int i;
    int k;
    int j;
    if ((n & 0x80) == 0)
    {
      i = n & 0x7F;
      k = 0;
      j = 1;
    }
    else if ((n & 0xE0) == 192)
    {
      i = n & 0x1F;
      j = 2;
      k = 128;
    }
    else if ((n & 0xF0) == 224)
    {
      i = n & 0xF;
      j = 3;
      k = 2048;
    }
    else
    {
      if ((n & 0xF8) != 240) {
        break label334;
      }
      i = n & 0x7;
      j = 4;
      k = 65536;
    }
    long l2 = size;
    long l1 = j;
    if (l2 < l1)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("size < ");
      localStringBuilder.append(j);
      localStringBuilder.append(": ");
      localStringBuilder.append(size);
      localStringBuilder.append(" (to read code point prefixed 0x");
      localStringBuilder.append(Integer.toHexString(n));
      localStringBuilder.append(")");
      throw new EOFException(localStringBuilder.toString());
    }
    while (m < j)
    {
      l2 = m;
      n = getByte(l2);
      if ((n & 0xC0) == 128)
      {
        i = i << 6 | n & 0x3F;
        m += 1;
      }
      else
      {
        skip(l2);
        return 65533;
      }
    }
    skip(l1);
    if (i > 1114111) {
      return 65533;
    }
    if ((i >= 55296) && (i <= 57343)) {
      return 65533;
    }
    if (i < k) {
      return 65533;
    }
    return i;
    label334:
    skip(1L);
    return 65533;
  }
  
  @Nullable
  public String readUtf8Line()
    throws EOFException
  {
    long l = indexOf((byte)10);
    if (l == -1L)
    {
      if (size != 0L) {
        return readUtf8(size);
      }
      return null;
    }
    return readUtf8Line(l);
  }
  
  String readUtf8Line(long paramLong)
    throws EOFException
  {
    if (paramLong > 0L)
    {
      long l = paramLong - 1L;
      if (getByte(l) == 13)
      {
        str = readUtf8(l);
        skip(2L);
        return str;
      }
    }
    String str = readUtf8(paramLong);
    skip(1L);
    return str;
  }
  
  public String readUtf8LineStrict()
    throws EOFException
  {
    return readUtf8LineStrict(Long.MAX_VALUE);
  }
  
  public String readUtf8LineStrict(long paramLong)
    throws EOFException
  {
    if (paramLong < 0L)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("limit < 0: ");
      ((StringBuilder)localObject).append(paramLong);
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    long l1 = Long.MAX_VALUE;
    if (paramLong != Long.MAX_VALUE) {
      l1 = paramLong + 1L;
    }
    long l2 = indexOf((byte)10, 0L, l1);
    if (l2 != -1L) {
      return readUtf8Line(l2);
    }
    if ((l1 < size()) && (getByte(l1 - 1L) == 13) && (getByte(l1) == 10)) {
      return readUtf8Line(l1);
    }
    Object localObject = new Buffer();
    copyTo((Buffer)localObject, 0L, Math.min(32L, size()));
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\\n not found: limit=");
    localStringBuilder.append(Math.min(size(), paramLong));
    localStringBuilder.append(" content=");
    localStringBuilder.append(((Buffer)localObject).readByteString().hex());
    localStringBuilder.append('…');
    throw new EOFException(localStringBuilder.toString());
  }
  
  public boolean request(long paramLong)
  {
    return size >= paramLong;
  }
  
  public void require(long paramLong)
    throws EOFException
  {
    if (size < paramLong) {
      throw new EOFException();
    }
  }
  
  List<Integer> segmentSizes()
  {
    if (head == null) {
      return Collections.emptyList();
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(Integer.valueOf(head.limit - head.pos));
    Segment localSegment = head;
    for (;;)
    {
      localSegment = next;
      if (localSegment == head) {
        break;
      }
      localArrayList.add(Integer.valueOf(limit - pos));
    }
    return localArrayList;
  }
  
  public int select(Options paramOptions)
  {
    Segment localSegment = head;
    if (localSegment == null) {
      return paramOptions.indexOf(ByteString.EMPTY);
    }
    paramOptions = byteStrings;
    int j = paramOptions.length;
    int i = 0;
    while (i < j)
    {
      ByteString localByteString = paramOptions[i];
      if ((size >= localByteString.size()) && (rangeEquals(localSegment, pos, localByteString, 0, localByteString.size()))) {
        try
        {
          skip(localByteString.size());
          return i;
        }
        catch (EOFException paramOptions)
        {
          throw new AssertionError(paramOptions);
        }
      }
      i += 1;
    }
    return -1;
  }
  
  int selectPrefix(Options paramOptions)
  {
    Segment localSegment = head;
    paramOptions = byteStrings;
    int j = paramOptions.length;
    int i = 0;
    while (i < j)
    {
      ByteString localByteString = paramOptions[i];
      int k = (int)Math.min(size, localByteString.size());
      if (k != 0)
      {
        if (rangeEquals(localSegment, pos, localByteString, 0, k)) {
          return i;
        }
        i += 1;
      }
      else
      {
        return i;
      }
    }
    return -1;
  }
  
  public ByteString sha1()
  {
    return digest("SHA-1");
  }
  
  public ByteString sha256()
  {
    return digest("SHA-256");
  }
  
  public ByteString sha512()
  {
    return digest("SHA-512");
  }
  
  public long size()
  {
    return size;
  }
  
  public void skip(long paramLong)
    throws EOFException
  {
    while (paramLong > 0L)
    {
      if (head == null) {
        throw new EOFException();
      }
      int i = (int)Math.min(paramLong, head.limit - head.pos);
      long l1 = size;
      long l2 = i;
      size = (l1 - l2);
      Segment localSegment = head;
      pos += i;
      if (head.pos == head.limit)
      {
        localSegment = head;
        head = localSegment.pop();
        SegmentPool.recycle(localSegment);
      }
      paramLong -= l2;
    }
  }
  
  public ByteString snapshot()
  {
    if (size > 2147483647L)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("size > Integer.MAX_VALUE: ");
      localStringBuilder.append(size);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return snapshot((int)size);
  }
  
  public ByteString snapshot(int paramInt)
  {
    if (paramInt == 0) {
      return ByteString.EMPTY;
    }
    return new SegmentedByteString(this, paramInt);
  }
  
  public Timeout timeout()
  {
    return Timeout.NONE;
  }
  
  public String toString()
  {
    return snapshot().toString();
  }
  
  Segment writableSegment(int paramInt)
  {
    if ((paramInt >= 1) && (paramInt <= 8192))
    {
      Object localObject;
      if (head == null)
      {
        head = SegmentPool.take();
        localObject = head;
        localSegment1 = head;
        Segment localSegment2 = head;
        prev = localSegment2;
        next = localSegment2;
        return localSegment2;
      }
      Segment localSegment1 = head.prev;
      if (limit + paramInt <= 8192)
      {
        localObject = localSegment1;
        if (owner) {}
      }
      else
      {
        localObject = localSegment1.push(SegmentPool.take());
      }
      return localObject;
    }
    throw new IllegalArgumentException();
  }
  
  public Buffer write(ByteString paramByteString)
  {
    if (paramByteString == null) {
      throw new IllegalArgumentException("byteString == null");
    }
    paramByteString.write(this);
    return this;
  }
  
  public Buffer write(byte[] paramArrayOfByte)
  {
    if (paramArrayOfByte == null) {
      throw new IllegalArgumentException("source == null");
    }
    return write(paramArrayOfByte, 0, paramArrayOfByte.length);
  }
  
  public Buffer write(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    if (paramArrayOfByte == null) {
      throw new IllegalArgumentException("source == null");
    }
    long l1 = paramArrayOfByte.length;
    long l2 = paramInt1;
    long l3 = paramInt2;
    Util.checkOffsetAndCount(l1, l2, l3);
    paramInt2 += paramInt1;
    while (paramInt1 < paramInt2)
    {
      Segment localSegment = writableSegment(1);
      int i = Math.min(paramInt2 - paramInt1, 8192 - limit);
      System.arraycopy(paramArrayOfByte, paramInt1, data, limit, i);
      paramInt1 += i;
      limit += i;
    }
    size += l3;
    return this;
  }
  
  public BufferedSink write(Source paramSource, long paramLong)
    throws IOException
  {
    while (paramLong > 0L)
    {
      long l = paramSource.read(this, paramLong);
      if (l == -1L) {
        throw new EOFException();
      }
      paramLong -= l;
    }
    return this;
  }
  
  public void write(Buffer paramBuffer, long paramLong)
  {
    if (paramBuffer == null) {
      throw new IllegalArgumentException("source == null");
    }
    if (paramBuffer == this) {
      throw new IllegalArgumentException("source == this");
    }
    Util.checkOffsetAndCount(size, 0L, paramLong);
    while (paramLong > 0L)
    {
      if (paramLong < head.limit - head.pos)
      {
        if (head != null) {
          localSegment1 = head.prev;
        } else {
          localSegment1 = null;
        }
        if ((localSegment1 != null) && (owner))
        {
          l = limit;
          int i;
          if (shared) {
            i = 0;
          } else {
            i = pos;
          }
          if (paramLong + l - i <= 8192L)
          {
            head.writeTo(localSegment1, (int)paramLong);
            size -= paramLong;
            size += paramLong;
            return;
          }
        }
        head = head.split((int)paramLong);
      }
      Segment localSegment1 = head;
      long l = limit - pos;
      head = localSegment1.pop();
      if (head == null)
      {
        head = localSegment1;
        localSegment1 = head;
        Segment localSegment2 = head;
        Segment localSegment3 = head;
        prev = localSegment3;
        next = localSegment3;
      }
      else
      {
        head.prev.push(localSegment1).compact();
      }
      size -= l;
      size += l;
      paramLong -= l;
    }
  }
  
  public long writeAll(Source paramSource)
    throws IOException
  {
    if (paramSource == null) {
      throw new IllegalArgumentException("source == null");
    }
    long l2;
    for (long l1 = 0L;; l1 += l2)
    {
      l2 = paramSource.read(this, 8192L);
      if (l2 == -1L) {
        break;
      }
    }
    return l1;
  }
  
  public Buffer writeByte(int paramInt)
  {
    Segment localSegment = writableSegment(1);
    byte[] arrayOfByte = data;
    int i = limit;
    limit = (i + 1);
    arrayOfByte[i] = ((byte)paramInt);
    size += 1L;
    return this;
  }
  
  public Buffer writeDecimalLong(long paramLong)
  {
    if (paramLong == 0L) {
      return writeByte(48);
    }
    int j = 0;
    int i = 1;
    long l = paramLong;
    if (paramLong < 0L)
    {
      l = -paramLong;
      if (l < 0L) {
        return writeUtf8("-9223372036854775808");
      }
      j = 1;
    }
    if (l < 100000000L)
    {
      if (l < 10000L)
      {
        if (l < 100L)
        {
          if (l >= 10L) {
            i = 2;
          }
        }
        else if (l < 1000L) {
          i = 3;
        } else {
          i = 4;
        }
      }
      else if (l < 1000000L)
      {
        if (l < 100000L) {
          i = 5;
        } else {
          i = 6;
        }
      }
      else if (l < 10000000L) {
        i = 7;
      } else {
        i = 8;
      }
    }
    else if (l < 1000000000000L)
    {
      if (l < 10000000000L)
      {
        if (l < 1000000000L) {
          i = 9;
        } else {
          i = 10;
        }
      }
      else if (l < 100000000000L) {
        i = 11;
      } else {
        i = 12;
      }
    }
    else if (l < 1000000000000000L)
    {
      if (l < 10000000000000L) {
        i = 13;
      } else if (l < 100000000000000L) {
        i = 14;
      } else {
        i = 15;
      }
    }
    else if (l < 100000000000000000L)
    {
      if (l < 10000000000000000L) {
        i = 16;
      } else {
        i = 17;
      }
    }
    else if (l < 1000000000000000000L) {
      i = 18;
    } else {
      i = 19;
    }
    int k = i;
    if (j != 0) {
      k = i + 1;
    }
    Segment localSegment = writableSegment(k);
    byte[] arrayOfByte = data;
    i = limit + k;
    while (l != 0L)
    {
      int m = (int)(l % 10L);
      i -= 1;
      arrayOfByte[i] = DIGITS[m];
      l /= 10L;
    }
    if (j != 0) {
      arrayOfByte[(i - 1)] = 45;
    }
    limit += k;
    size += k;
    return this;
  }
  
  public Buffer writeHexadecimalUnsignedLong(long paramLong)
  {
    if (paramLong == 0L) {
      return writeByte(48);
    }
    int j = Long.numberOfTrailingZeros(Long.highestOneBit(paramLong)) / 4 + 1;
    Segment localSegment = writableSegment(j);
    byte[] arrayOfByte = data;
    int i = limit + j - 1;
    int k = limit;
    while (i >= k)
    {
      arrayOfByte[i] = DIGITS[((int)(paramLong & 0xF))];
      paramLong >>>= 4;
      i -= 1;
    }
    limit += j;
    size += j;
    return this;
  }
  
  public Buffer writeInt(int paramInt)
  {
    Segment localSegment = writableSegment(4);
    byte[] arrayOfByte = data;
    int j = limit;
    int i = j + 1;
    arrayOfByte[j] = ((byte)(paramInt >>> 24 & 0xFF));
    j = i + 1;
    arrayOfByte[i] = ((byte)(paramInt >>> 16 & 0xFF));
    i = j + 1;
    arrayOfByte[j] = ((byte)(paramInt >>> 8 & 0xFF));
    arrayOfByte[i] = ((byte)(paramInt & 0xFF));
    limit = (i + 1);
    size += 4L;
    return this;
  }
  
  public Buffer writeIntLe(int paramInt)
  {
    return writeInt(Util.reverseBytesInt(paramInt));
  }
  
  public Buffer writeLong(long paramLong)
  {
    Segment localSegment = writableSegment(8);
    byte[] arrayOfByte = data;
    int j = limit;
    int i = j + 1;
    arrayOfByte[j] = ((byte)(int)(paramLong >>> 56 & 0xFF));
    j = i + 1;
    arrayOfByte[i] = ((byte)(int)(paramLong >>> 48 & 0xFF));
    i = j + 1;
    arrayOfByte[j] = ((byte)(int)(paramLong >>> 40 & 0xFF));
    j = i + 1;
    arrayOfByte[i] = ((byte)(int)(paramLong >>> 32 & 0xFF));
    i = j + 1;
    arrayOfByte[j] = ((byte)(int)(paramLong >>> 24 & 0xFF));
    j = i + 1;
    arrayOfByte[i] = ((byte)(int)(paramLong >>> 16 & 0xFF));
    i = j + 1;
    arrayOfByte[j] = ((byte)(int)(paramLong >>> 8 & 0xFF));
    arrayOfByte[i] = ((byte)(int)(paramLong & 0xFF));
    limit = (i + 1);
    size += 8L;
    return this;
  }
  
  public Buffer writeLongLe(long paramLong)
  {
    return writeLong(Util.reverseBytesLong(paramLong));
  }
  
  public Buffer writeShort(int paramInt)
  {
    Segment localSegment = writableSegment(2);
    byte[] arrayOfByte = data;
    int i = limit;
    int j = i + 1;
    arrayOfByte[i] = ((byte)(paramInt >>> 8 & 0xFF));
    arrayOfByte[j] = ((byte)(paramInt & 0xFF));
    limit = (j + 1);
    size += 2L;
    return this;
  }
  
  public Buffer writeShortLe(int paramInt)
  {
    return writeShort(Util.reverseBytesShort((short)paramInt));
  }
  
  public Buffer writeString(String paramString, int paramInt1, int paramInt2, Charset paramCharset)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("string == null");
    }
    if (paramInt1 < 0)
    {
      paramString = new StringBuilder();
      paramString.append("beginIndex < 0: ");
      paramString.append(paramInt1);
      throw new IllegalAccessError(paramString.toString());
    }
    if (paramInt2 < paramInt1)
    {
      paramString = new StringBuilder();
      paramString.append("endIndex < beginIndex: ");
      paramString.append(paramInt2);
      paramString.append(" < ");
      paramString.append(paramInt1);
      throw new IllegalArgumentException(paramString.toString());
    }
    if (paramInt2 > paramString.length())
    {
      paramCharset = new StringBuilder();
      paramCharset.append("endIndex > string.length: ");
      paramCharset.append(paramInt2);
      paramCharset.append(" > ");
      paramCharset.append(paramString.length());
      throw new IllegalArgumentException(paramCharset.toString());
    }
    if (paramCharset == null) {
      throw new IllegalArgumentException("charset == null");
    }
    if (paramCharset.equals(Util.UTF_8)) {
      return writeUtf8(paramString, paramInt1, paramInt2);
    }
    paramString = paramString.substring(paramInt1, paramInt2).getBytes(paramCharset);
    return write(paramString, 0, paramString.length);
  }
  
  public Buffer writeString(String paramString, Charset paramCharset)
  {
    return writeString(paramString, 0, paramString.length(), paramCharset);
  }
  
  public Buffer writeTo(OutputStream paramOutputStream)
    throws IOException
  {
    return writeTo(paramOutputStream, size);
  }
  
  public Buffer writeTo(OutputStream paramOutputStream, long paramLong)
    throws IOException
  {
    if (paramOutputStream == null) {
      throw new IllegalArgumentException("out == null");
    }
    Util.checkOffsetAndCount(size, 0L, paramLong);
    Object localObject2;
    for (Object localObject1 = head; paramLong > 0L; localObject1 = localObject2)
    {
      int i = (int)Math.min(paramLong, limit - pos);
      paramOutputStream.write(data, pos, i);
      pos += i;
      long l1 = size;
      long l2 = i;
      size = (l1 - l2);
      localObject2 = localObject1;
      if (pos == limit)
      {
        localObject2 = ((Segment)localObject1).pop();
        head = ((Segment)localObject2);
        SegmentPool.recycle((Segment)localObject1);
      }
      paramLong -= l2;
    }
    return this;
  }
  
  public Buffer writeUtf8(String paramString)
  {
    return writeUtf8(paramString, 0, paramString.length());
  }
  
  public Buffer writeUtf8(String paramString, int paramInt1, int paramInt2)
  {
    if (paramString == null) {
      throw new IllegalArgumentException("string == null");
    }
    if (paramInt1 < 0)
    {
      paramString = new StringBuilder();
      paramString.append("beginIndex < 0: ");
      paramString.append(paramInt1);
      throw new IllegalArgumentException(paramString.toString());
    }
    if (paramInt2 < paramInt1)
    {
      paramString = new StringBuilder();
      paramString.append("endIndex < beginIndex: ");
      paramString.append(paramInt2);
      paramString.append(" < ");
      paramString.append(paramInt1);
      throw new IllegalArgumentException(paramString.toString());
    }
    Object localObject;
    if (paramInt2 > paramString.length())
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("endIndex > string.length: ");
      ((StringBuilder)localObject).append(paramInt2);
      ((StringBuilder)localObject).append(" > ");
      ((StringBuilder)localObject).append(paramString.length());
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    while (paramInt1 < paramInt2)
    {
      int k = paramString.charAt(paramInt1);
      int j;
      int i;
      if (k < 128)
      {
        localObject = writableSegment(1);
        byte[] arrayOfByte = data;
        j = limit - paramInt1;
        int m = Math.min(paramInt2, 8192 - j);
        i = paramInt1 + 1;
        arrayOfByte[(paramInt1 + j)] = ((byte)k);
        paramInt1 = i;
        while (paramInt1 < m)
        {
          i = paramString.charAt(paramInt1);
          if (i >= 128) {
            break;
          }
          arrayOfByte[(paramInt1 + j)] = ((byte)i);
          paramInt1 += 1;
        }
        i = j + paramInt1 - limit;
        limit += i;
        size += i;
      }
      else if (k < 2048)
      {
        writeByte(k >> 6 | 0xC0);
        writeByte(k & 0x3F | 0x80);
        paramInt1 += 1;
      }
      else if ((k >= 55296) && (k <= 57343))
      {
        j = paramInt1 + 1;
        if (j < paramInt2) {
          i = paramString.charAt(j);
        } else {
          i = 0;
        }
        if ((k <= 56319) && (i >= 56320) && (i <= 57343))
        {
          i = 65536 + ((k & 0xFFFF27FF) << 10 | 0xFFFF23FF & i);
          writeByte(i >> 18 | 0xF0);
          writeByte(i >> 12 & 0x3F | 0x80);
          writeByte(i >> 6 & 0x3F | 0x80);
          writeByte(i & 0x3F | 0x80);
          paramInt1 += 2;
        }
        else
        {
          writeByte(63);
          paramInt1 = j;
        }
      }
      else
      {
        writeByte(k >> 12 | 0xE0);
        writeByte(k >> 6 & 0x3F | 0x80);
        writeByte(k & 0x3F | 0x80);
        paramInt1 += 1;
      }
    }
    return this;
  }
  
  public Buffer writeUtf8CodePoint(int paramInt)
  {
    if (paramInt < 128)
    {
      writeByte(paramInt);
      return this;
    }
    if (paramInt < 2048)
    {
      writeByte(paramInt >> 6 | 0xC0);
      writeByte(paramInt & 0x3F | 0x80);
      return this;
    }
    if (paramInt < 65536)
    {
      if ((paramInt >= 55296) && (paramInt <= 57343))
      {
        writeByte(63);
        return this;
      }
      writeByte(paramInt >> 12 | 0xE0);
      writeByte(paramInt >> 6 & 0x3F | 0x80);
      writeByte(paramInt & 0x3F | 0x80);
      return this;
    }
    if (paramInt <= 1114111)
    {
      writeByte(paramInt >> 18 | 0xF0);
      writeByte(paramInt >> 12 & 0x3F | 0x80);
      writeByte(paramInt >> 6 & 0x3F | 0x80);
      writeByte(paramInt & 0x3F | 0x80);
      return this;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Unexpected code point: ");
    localStringBuilder.append(Integer.toHexString(paramInt));
    throw new IllegalArgumentException(localStringBuilder.toString());
  }
}

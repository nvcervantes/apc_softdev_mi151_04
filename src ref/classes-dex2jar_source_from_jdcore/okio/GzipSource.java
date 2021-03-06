package okio;

import java.io.EOFException;
import java.io.IOException;
import java.util.zip.CRC32;
import java.util.zip.Inflater;

public final class GzipSource
  implements Source
{
  private static final byte FCOMMENT = 4;
  private static final byte FEXTRA = 2;
  private static final byte FHCRC = 1;
  private static final byte FNAME = 3;
  private static final byte SECTION_BODY = 1;
  private static final byte SECTION_DONE = 3;
  private static final byte SECTION_HEADER = 0;
  private static final byte SECTION_TRAILER = 2;
  private final CRC32 crc = new CRC32();
  private final Inflater inflater;
  private final InflaterSource inflaterSource;
  private int section = 0;
  private final BufferedSource source;
  
  public GzipSource(Source paramSource)
  {
    if (paramSource == null) {
      throw new IllegalArgumentException("source == null");
    }
    inflater = new Inflater(true);
    source = Okio.buffer(paramSource);
    inflaterSource = new InflaterSource(source, inflater);
  }
  
  private void checkEqual(String paramString, int paramInt1, int paramInt2)
    throws IOException
  {
    if (paramInt2 != paramInt1) {
      throw new IOException(String.format("%s: actual 0x%08x != expected 0x%08x", new Object[] { paramString, Integer.valueOf(paramInt2), Integer.valueOf(paramInt1) }));
    }
  }
  
  private void consumeHeader()
    throws IOException
  {
    source.require(10L);
    int j = source.buffer().getByte(3L);
    int i;
    if ((j >> 1 & 0x1) == 1) {
      i = 1;
    } else {
      i = 0;
    }
    if (i != 0) {
      updateCrc(source.buffer(), 0L, 10L);
    }
    checkEqual("ID1ID2", 8075, source.readShort());
    source.skip(8L);
    long l;
    if ((j >> 2 & 0x1) == 1)
    {
      source.require(2L);
      if (i != 0) {
        updateCrc(source.buffer(), 0L, 2L);
      }
      int k = source.buffer().readShortLe();
      BufferedSource localBufferedSource = source;
      l = k;
      localBufferedSource.require(l);
      if (i != 0) {
        updateCrc(source.buffer(), 0L, l);
      }
      source.skip(l);
    }
    if ((j >> 3 & 0x1) == 1)
    {
      l = source.indexOf((byte)0);
      if (l == -1L) {
        throw new EOFException();
      }
      if (i != 0) {
        updateCrc(source.buffer(), 0L, l + 1L);
      }
      source.skip(l + 1L);
    }
    if ((j >> 4 & 0x1) == 1)
    {
      l = source.indexOf((byte)0);
      if (l == -1L) {
        throw new EOFException();
      }
      if (i != 0) {
        updateCrc(source.buffer(), 0L, l + 1L);
      }
      source.skip(l + 1L);
    }
    if (i != 0)
    {
      checkEqual("FHCRC", source.readShortLe(), (short)(int)crc.getValue());
      crc.reset();
    }
  }
  
  private void consumeTrailer()
    throws IOException
  {
    checkEqual("CRC", source.readIntLe(), (int)crc.getValue());
    checkEqual("ISIZE", source.readIntLe(), (int)inflater.getBytesWritten());
  }
  
  private void updateCrc(Buffer paramBuffer, long paramLong1, long paramLong2)
  {
    paramBuffer = head;
    long l;
    while (paramLong1 >= limit - pos)
    {
      l = limit - pos;
      paramBuffer = next;
      paramLong1 -= l;
    }
    while (paramLong2 > 0L)
    {
      int i = (int)(pos + paramLong1);
      int j = (int)Math.min(limit - i, paramLong2);
      crc.update(data, i, j);
      l = j;
      paramBuffer = next;
      paramLong1 = 0L;
      paramLong2 -= l;
    }
  }
  
  public void close()
    throws IOException
  {
    inflaterSource.close();
  }
  
  public long read(Buffer paramBuffer, long paramLong)
    throws IOException
  {
    if (paramLong < 0L)
    {
      paramBuffer = new StringBuilder();
      paramBuffer.append("byteCount < 0: ");
      paramBuffer.append(paramLong);
      throw new IllegalArgumentException(paramBuffer.toString());
    }
    if (paramLong == 0L) {
      return 0L;
    }
    if (section == 0)
    {
      consumeHeader();
      section = 1;
    }
    if (section == 1)
    {
      long l = size;
      paramLong = inflaterSource.read(paramBuffer, paramLong);
      if (paramLong != -1L)
      {
        updateCrc(paramBuffer, l, paramLong);
        return paramLong;
      }
      section = 2;
    }
    if (section == 2)
    {
      consumeTrailer();
      section = 3;
      if (!source.exhausted()) {
        throw new IOException("gzip finished without exhausting source");
      }
    }
    return -1L;
  }
  
  public Timeout timeout()
  {
    return source.timeout();
  }
}

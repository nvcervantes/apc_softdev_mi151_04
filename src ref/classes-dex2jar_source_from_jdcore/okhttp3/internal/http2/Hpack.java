package okhttp3.internal.http2;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okhttp3.internal.Util;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;

final class Hpack
{
  static final Map<ByteString, Integer> NAME_TO_FIRST_INDEX = nameToFirstIndex();
  private static final int PREFIX_4_BITS = 15;
  private static final int PREFIX_5_BITS = 31;
  private static final int PREFIX_6_BITS = 63;
  private static final int PREFIX_7_BITS = 127;
  static final Header[] STATIC_HEADER_TABLE = { new Header(Header.TARGET_AUTHORITY, ""), new Header(Header.TARGET_METHOD, "GET"), new Header(Header.TARGET_METHOD, "POST"), new Header(Header.TARGET_PATH, "/"), new Header(Header.TARGET_PATH, "/index.html"), new Header(Header.TARGET_SCHEME, "http"), new Header(Header.TARGET_SCHEME, "https"), new Header(Header.RESPONSE_STATUS, "200"), new Header(Header.RESPONSE_STATUS, "204"), new Header(Header.RESPONSE_STATUS, "206"), new Header(Header.RESPONSE_STATUS, "304"), new Header(Header.RESPONSE_STATUS, "400"), new Header(Header.RESPONSE_STATUS, "404"), new Header(Header.RESPONSE_STATUS, "500"), new Header("accept-charset", ""), new Header("accept-encoding", "gzip, deflate"), new Header("accept-language", ""), new Header("accept-ranges", ""), new Header("accept", ""), new Header("access-control-allow-origin", ""), new Header("age", ""), new Header("allow", ""), new Header("authorization", ""), new Header("cache-control", ""), new Header("content-disposition", ""), new Header("content-encoding", ""), new Header("content-language", ""), new Header("content-length", ""), new Header("content-location", ""), new Header("content-range", ""), new Header("content-type", ""), new Header("cookie", ""), new Header("date", ""), new Header("etag", ""), new Header("expect", ""), new Header("expires", ""), new Header("from", ""), new Header("host", ""), new Header("if-match", ""), new Header("if-modified-since", ""), new Header("if-none-match", ""), new Header("if-range", ""), new Header("if-unmodified-since", ""), new Header("last-modified", ""), new Header("link", ""), new Header("location", ""), new Header("max-forwards", ""), new Header("proxy-authenticate", ""), new Header("proxy-authorization", ""), new Header("range", ""), new Header("referer", ""), new Header("refresh", ""), new Header("retry-after", ""), new Header("server", ""), new Header("set-cookie", ""), new Header("strict-transport-security", ""), new Header("transfer-encoding", ""), new Header("user-agent", ""), new Header("vary", ""), new Header("via", ""), new Header("www-authenticate", "") };
  
  private Hpack() {}
  
  static ByteString checkLowercase(ByteString paramByteString)
    throws IOException
  {
    int j = paramByteString.size();
    int i = 0;
    while (i < j)
    {
      int k = paramByteString.getByte(i);
      if ((k >= 65) && (k <= 90))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("PROTOCOL_ERROR response malformed: mixed case name: ");
        localStringBuilder.append(paramByteString.utf8());
        throw new IOException(localStringBuilder.toString());
      }
      i += 1;
    }
    return paramByteString;
  }
  
  private static Map<ByteString, Integer> nameToFirstIndex()
  {
    Object localObject = STATIC_HEADER_TABLE;
    int i = 0;
    localObject = new LinkedHashMap(localObject.length);
    while (i < STATIC_HEADER_TABLE.length)
    {
      if (!((Map)localObject).containsKey(STATIC_HEADER_TABLEname)) {
        ((Map)localObject).put(STATIC_HEADER_TABLEname, Integer.valueOf(i));
      }
      i += 1;
    }
    return Collections.unmodifiableMap((Map)localObject);
  }
  
  static final class Reader
  {
    Header[] dynamicTable = new Header[8];
    int dynamicTableByteCount = 0;
    int headerCount = 0;
    private final List<Header> headerList = new ArrayList();
    private final int headerTableSizeSetting;
    private int maxDynamicTableByteCount;
    int nextHeaderIndex = dynamicTable.length - 1;
    private final BufferedSource source;
    
    Reader(int paramInt1, int paramInt2, Source paramSource)
    {
      headerTableSizeSetting = paramInt1;
      maxDynamicTableByteCount = paramInt2;
      source = Okio.buffer(paramSource);
    }
    
    Reader(int paramInt, Source paramSource)
    {
      this(paramInt, paramInt, paramSource);
    }
    
    private void adjustDynamicTableByteCount()
    {
      if (maxDynamicTableByteCount < dynamicTableByteCount)
      {
        if (maxDynamicTableByteCount == 0)
        {
          clearDynamicTable();
          return;
        }
        evictToRecoverBytes(dynamicTableByteCount - maxDynamicTableByteCount);
      }
    }
    
    private void clearDynamicTable()
    {
      Arrays.fill(dynamicTable, null);
      nextHeaderIndex = (dynamicTable.length - 1);
      headerCount = 0;
      dynamicTableByteCount = 0;
    }
    
    private int dynamicTableIndex(int paramInt)
    {
      return nextHeaderIndex + 1 + paramInt;
    }
    
    private int evictToRecoverBytes(int paramInt)
    {
      int i = 0;
      int k = 0;
      if (paramInt > 0)
      {
        i = dynamicTable.length - 1;
        int j = paramInt;
        paramInt = k;
        while ((i >= nextHeaderIndex) && (j > 0))
        {
          j -= dynamicTable[i].hpackSize;
          dynamicTableByteCount -= dynamicTable[i].hpackSize;
          headerCount -= 1;
          paramInt += 1;
          i -= 1;
        }
        System.arraycopy(dynamicTable, nextHeaderIndex + 1, dynamicTable, nextHeaderIndex + 1 + paramInt, headerCount);
        nextHeaderIndex += paramInt;
        i = paramInt;
      }
      return i;
    }
    
    private ByteString getName(int paramInt)
    {
      if (isStaticHeader(paramInt)) {
        return STATIC_HEADER_TABLEname;
      }
      return dynamicTable[dynamicTableIndex(paramInt - Hpack.STATIC_HEADER_TABLE.length)].name;
    }
    
    private void insertIntoDynamicTable(int paramInt, Header paramHeader)
    {
      headerList.add(paramHeader);
      int j = hpackSize;
      int i = j;
      if (paramInt != -1) {
        i = j - dynamicTable[dynamicTableIndex(paramInt)].hpackSize;
      }
      if (i > maxDynamicTableByteCount)
      {
        clearDynamicTable();
        return;
      }
      j = evictToRecoverBytes(dynamicTableByteCount + i - maxDynamicTableByteCount);
      if (paramInt == -1)
      {
        if (headerCount + 1 > dynamicTable.length)
        {
          Header[] arrayOfHeader = new Header[dynamicTable.length * 2];
          System.arraycopy(dynamicTable, 0, arrayOfHeader, dynamicTable.length, dynamicTable.length);
          nextHeaderIndex = (dynamicTable.length - 1);
          dynamicTable = arrayOfHeader;
        }
        paramInt = nextHeaderIndex;
        nextHeaderIndex = (paramInt - 1);
        dynamicTable[paramInt] = paramHeader;
        headerCount += 1;
      }
      else
      {
        int k = dynamicTableIndex(paramInt);
        dynamicTable[(paramInt + (k + j))] = paramHeader;
      }
      dynamicTableByteCount += i;
    }
    
    private boolean isStaticHeader(int paramInt)
    {
      return (paramInt >= 0) && (paramInt <= Hpack.STATIC_HEADER_TABLE.length - 1);
    }
    
    private int readByte()
      throws IOException
    {
      return source.readByte() & 0xFF;
    }
    
    private void readIndexedHeader(int paramInt)
      throws IOException
    {
      if (isStaticHeader(paramInt))
      {
        localObject = Hpack.STATIC_HEADER_TABLE[paramInt];
        headerList.add(localObject);
        return;
      }
      int i = dynamicTableIndex(paramInt - Hpack.STATIC_HEADER_TABLE.length);
      if ((i >= 0) && (i <= dynamicTable.length - 1))
      {
        headerList.add(dynamicTable[i]);
        return;
      }
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Header index too large ");
      ((StringBuilder)localObject).append(paramInt + 1);
      throw new IOException(((StringBuilder)localObject).toString());
    }
    
    private void readLiteralHeaderWithIncrementalIndexingIndexedName(int paramInt)
      throws IOException
    {
      insertIntoDynamicTable(-1, new Header(getName(paramInt), readByteString()));
    }
    
    private void readLiteralHeaderWithIncrementalIndexingNewName()
      throws IOException
    {
      insertIntoDynamicTable(-1, new Header(Hpack.checkLowercase(readByteString()), readByteString()));
    }
    
    private void readLiteralHeaderWithoutIndexingIndexedName(int paramInt)
      throws IOException
    {
      ByteString localByteString1 = getName(paramInt);
      ByteString localByteString2 = readByteString();
      headerList.add(new Header(localByteString1, localByteString2));
    }
    
    private void readLiteralHeaderWithoutIndexingNewName()
      throws IOException
    {
      ByteString localByteString1 = Hpack.checkLowercase(readByteString());
      ByteString localByteString2 = readByteString();
      headerList.add(new Header(localByteString1, localByteString2));
    }
    
    public List<Header> getAndResetHeaderList()
    {
      ArrayList localArrayList = new ArrayList(headerList);
      headerList.clear();
      return localArrayList;
    }
    
    int maxDynamicTableByteCount()
    {
      return maxDynamicTableByteCount;
    }
    
    ByteString readByteString()
      throws IOException
    {
      int j = readByte();
      int i;
      if ((j & 0x80) == 128) {
        i = 1;
      } else {
        i = 0;
      }
      j = readInt(j, 127);
      if (i != 0) {
        return ByteString.of(Huffman.get().decode(source.readByteArray(j)));
      }
      return source.readByteString(j);
    }
    
    void readHeaders()
      throws IOException
    {
      while (!source.exhausted())
      {
        int i = source.readByte() & 0xFF;
        if (i == 128) {
          throw new IOException("index == 0");
        }
        if ((i & 0x80) == 128)
        {
          readIndexedHeader(readInt(i, 127) - 1);
        }
        else if (i == 64)
        {
          readLiteralHeaderWithIncrementalIndexingNewName();
        }
        else if ((i & 0x40) == 64)
        {
          readLiteralHeaderWithIncrementalIndexingIndexedName(readInt(i, 63) - 1);
        }
        else if ((i & 0x20) == 32)
        {
          maxDynamicTableByteCount = readInt(i, 31);
          if ((maxDynamicTableByteCount >= 0) && (maxDynamicTableByteCount <= headerTableSizeSetting))
          {
            adjustDynamicTableByteCount();
          }
          else
          {
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("Invalid dynamic table size update ");
            localStringBuilder.append(maxDynamicTableByteCount);
            throw new IOException(localStringBuilder.toString());
          }
        }
        else if ((i != 16) && (i != 0))
        {
          readLiteralHeaderWithoutIndexingIndexedName(readInt(i, 15) - 1);
        }
        else
        {
          readLiteralHeaderWithoutIndexingNewName();
        }
      }
    }
    
    int readInt(int paramInt1, int paramInt2)
      throws IOException
    {
      paramInt1 &= paramInt2;
      if (paramInt1 < paramInt2) {
        return paramInt1;
      }
      paramInt1 = 0;
      int i;
      for (;;)
      {
        i = readByte();
        if ((i & 0x80) == 0) {
          break;
        }
        paramInt2 += ((i & 0x7F) << paramInt1);
        paramInt1 += 7;
      }
      return paramInt2 + (i << paramInt1);
    }
  }
  
  static final class Writer
  {
    private static final int SETTINGS_HEADER_TABLE_SIZE = 4096;
    private static final int SETTINGS_HEADER_TABLE_SIZE_LIMIT = 16384;
    Header[] dynamicTable = new Header[8];
    int dynamicTableByteCount = 0;
    private boolean emitDynamicTableSizeUpdate;
    int headerCount = 0;
    int headerTableSizeSetting;
    int maxDynamicTableByteCount;
    int nextHeaderIndex = dynamicTable.length - 1;
    private final Buffer out;
    private int smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
    private final boolean useCompression;
    
    Writer(int paramInt, boolean paramBoolean, Buffer paramBuffer)
    {
      headerTableSizeSetting = paramInt;
      maxDynamicTableByteCount = paramInt;
      useCompression = paramBoolean;
      out = paramBuffer;
    }
    
    Writer(Buffer paramBuffer)
    {
      this(4096, true, paramBuffer);
    }
    
    private void adjustDynamicTableByteCount()
    {
      if (maxDynamicTableByteCount < dynamicTableByteCount)
      {
        if (maxDynamicTableByteCount == 0)
        {
          clearDynamicTable();
          return;
        }
        evictToRecoverBytes(dynamicTableByteCount - maxDynamicTableByteCount);
      }
    }
    
    private void clearDynamicTable()
    {
      Arrays.fill(dynamicTable, null);
      nextHeaderIndex = (dynamicTable.length - 1);
      headerCount = 0;
      dynamicTableByteCount = 0;
    }
    
    private int evictToRecoverBytes(int paramInt)
    {
      int i = 0;
      int k = 0;
      if (paramInt > 0)
      {
        i = dynamicTable.length - 1;
        int j = paramInt;
        paramInt = k;
        while ((i >= nextHeaderIndex) && (j > 0))
        {
          j -= dynamicTable[i].hpackSize;
          dynamicTableByteCount -= dynamicTable[i].hpackSize;
          headerCount -= 1;
          paramInt += 1;
          i -= 1;
        }
        System.arraycopy(dynamicTable, nextHeaderIndex + 1, dynamicTable, nextHeaderIndex + 1 + paramInt, headerCount);
        Arrays.fill(dynamicTable, nextHeaderIndex + 1, nextHeaderIndex + 1 + paramInt, null);
        nextHeaderIndex += paramInt;
        i = paramInt;
      }
      return i;
    }
    
    private void insertIntoDynamicTable(Header paramHeader)
    {
      int i = hpackSize;
      if (i > maxDynamicTableByteCount)
      {
        clearDynamicTable();
        return;
      }
      evictToRecoverBytes(dynamicTableByteCount + i - maxDynamicTableByteCount);
      if (headerCount + 1 > dynamicTable.length)
      {
        Header[] arrayOfHeader = new Header[dynamicTable.length * 2];
        System.arraycopy(dynamicTable, 0, arrayOfHeader, dynamicTable.length, dynamicTable.length);
        nextHeaderIndex = (dynamicTable.length - 1);
        dynamicTable = arrayOfHeader;
      }
      int j = nextHeaderIndex;
      nextHeaderIndex = (j - 1);
      dynamicTable[j] = paramHeader;
      headerCount += 1;
      dynamicTableByteCount += i;
    }
    
    void setHeaderTableSizeSetting(int paramInt)
    {
      headerTableSizeSetting = paramInt;
      paramInt = Math.min(paramInt, 16384);
      if (maxDynamicTableByteCount == paramInt) {
        return;
      }
      if (paramInt < maxDynamicTableByteCount) {
        smallestHeaderTableSizeSetting = Math.min(smallestHeaderTableSizeSetting, paramInt);
      }
      emitDynamicTableSizeUpdate = true;
      maxDynamicTableByteCount = paramInt;
      adjustDynamicTableByteCount();
    }
    
    void writeByteString(ByteString paramByteString)
      throws IOException
    {
      if ((useCompression) && (Huffman.get().encodedLength(paramByteString) < paramByteString.size()))
      {
        Buffer localBuffer = new Buffer();
        Huffman.get().encode(paramByteString, localBuffer);
        paramByteString = localBuffer.readByteString();
        writeInt(paramByteString.size(), 127, 128);
        out.write(paramByteString);
        return;
      }
      writeInt(paramByteString.size(), 127, 0);
      out.write(paramByteString);
    }
    
    void writeHeaders(List<Header> paramList)
      throws IOException
    {
      if (emitDynamicTableSizeUpdate)
      {
        if (smallestHeaderTableSizeSetting < maxDynamicTableByteCount) {
          writeInt(smallestHeaderTableSizeSetting, 31, 32);
        }
        emitDynamicTableSizeUpdate = false;
        smallestHeaderTableSizeSetting = Integer.MAX_VALUE;
        writeInt(maxDynamicTableByteCount, 31, 32);
      }
      int i2 = paramList.size();
      int k = 0;
      while (k < i2)
      {
        Header localHeader = (Header)paramList.get(k);
        ByteString localByteString1 = name.toAsciiLowercase();
        ByteString localByteString2 = value;
        Integer localInteger = (Integer)Hpack.NAME_TO_FIRST_INDEX.get(localByteString1);
        int j;
        if (localInteger != null)
        {
          j = localInteger.intValue() + 1;
          if ((j > 1) && (j < 8))
          {
            if (Util.equal(STATIC_HEADER_TABLE1value, localByteString2)) {
              break label195;
            }
            if (Util.equal(STATIC_HEADER_TABLEvalue, localByteString2))
            {
              i = j;
              j += 1;
              break label197;
            }
          }
          i = j;
          j = -1;
          break label197;
        }
        else
        {
          j = -1;
        }
        label195:
        int i = j;
        label197:
        int n = j;
        int i1 = i;
        if (j == -1)
        {
          int m = nextHeaderIndex + 1;
          int i3 = dynamicTable.length;
          for (;;)
          {
            n = j;
            i1 = i;
            if (m >= i3) {
              break;
            }
            n = i;
            if (Util.equal(dynamicTable[m].name, localByteString1))
            {
              if (Util.equal(dynamicTable[m].value, localByteString2))
              {
                j = nextHeaderIndex;
                n = Hpack.STATIC_HEADER_TABLE.length + (m - j);
                i1 = i;
                break;
              }
              n = i;
              if (i == -1) {
                n = m - nextHeaderIndex + Hpack.STATIC_HEADER_TABLE.length;
              }
            }
            m += 1;
            i = n;
          }
        }
        if (n != -1)
        {
          writeInt(n, 127, 128);
        }
        else if (i1 == -1)
        {
          out.writeByte(64);
          writeByteString(localByteString1);
          writeByteString(localByteString2);
          insertIntoDynamicTable(localHeader);
        }
        else if ((localByteString1.startsWith(Header.PSEUDO_PREFIX)) && (!Header.TARGET_AUTHORITY.equals(localByteString1)))
        {
          writeInt(i1, 15, 0);
          writeByteString(localByteString2);
        }
        else
        {
          writeInt(i1, 63, 64);
          writeByteString(localByteString2);
          insertIntoDynamicTable(localHeader);
        }
        k += 1;
      }
    }
    
    void writeInt(int paramInt1, int paramInt2, int paramInt3)
    {
      if (paramInt1 < paramInt2)
      {
        out.writeByte(paramInt1 | paramInt3);
        return;
      }
      out.writeByte(paramInt3 | paramInt2);
      paramInt1 -= paramInt2;
      while (paramInt1 >= 128)
      {
        out.writeByte(0x80 | paramInt1 & 0x7F);
        paramInt1 >>>= 7;
      }
      out.writeByte(paramInt1);
    }
  }
}

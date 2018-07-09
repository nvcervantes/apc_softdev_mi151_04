package okio;

import java.nio.charset.Charset;

final class Util
{
  public static final Charset UTF_8 = Charset.forName("UTF-8");
  
  private Util() {}
  
  public static boolean arrayRangeEquals(byte[] paramArrayOfByte1, int paramInt1, byte[] paramArrayOfByte2, int paramInt2, int paramInt3)
  {
    int i = 0;
    while (i < paramInt3)
    {
      if (paramArrayOfByte1[(i + paramInt1)] != paramArrayOfByte2[(i + paramInt2)]) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public static void checkOffsetAndCount(long paramLong1, long paramLong2, long paramLong3)
  {
    if (((paramLong2 | paramLong3) >= 0L) && (paramLong2 <= paramLong1) && (paramLong1 - paramLong2 >= paramLong3)) {
      return;
    }
    throw new ArrayIndexOutOfBoundsException(String.format("size=%s offset=%s byteCount=%s", new Object[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2), Long.valueOf(paramLong3) }));
  }
  
  public static int reverseBytesInt(int paramInt)
  {
    return (paramInt & 0xFF) << 24 | (0xFF000000 & paramInt) >>> 24 | (0xFF0000 & paramInt) >>> 8 | (0xFF00 & paramInt) << 8;
  }
  
  public static long reverseBytesLong(long paramLong)
  {
    return (paramLong & 0xFF00000000000000) >>> 56 | (paramLong & 0xFF000000000000) >>> 40 | (paramLong & 0xFF0000000000) >>> 24 | (paramLong & 0xFF00000000) >>> 8 | (paramLong & 0xFF000000) << 8 | (paramLong & 0xFF0000) << 24 | (paramLong & 0xFF00) << 40 | (paramLong & 0xFF) << 56;
  }
  
  public static short reverseBytesShort(short paramShort)
  {
    paramShort &= 0xFFFF;
    return (short)((paramShort & 0xFF) << 8 | (0xFF00 & paramShort) >>> 8);
  }
  
  public static void sneakyRethrow(Throwable paramThrowable)
  {
    sneakyThrow2(paramThrowable);
  }
  
  private static <T extends Throwable> void sneakyThrow2(Throwable paramThrowable)
    throws Throwable
  {
    throw paramThrowable;
  }
}

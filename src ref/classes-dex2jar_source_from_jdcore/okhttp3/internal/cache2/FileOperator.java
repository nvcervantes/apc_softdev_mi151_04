package okhttp3.internal.cache2;

import java.io.EOFException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import okio.Buffer;

final class FileOperator
{
  private static final int BUFFER_SIZE = 8192;
  private final byte[] byteArray = new byte[' '];
  private final ByteBuffer byteBuffer = ByteBuffer.wrap(byteArray);
  private final FileChannel fileChannel;
  
  FileOperator(FileChannel paramFileChannel)
  {
    fileChannel = paramFileChannel;
  }
  
  public void read(long paramLong1, Buffer paramBuffer, long paramLong2)
    throws IOException
  {
    long l = paramLong2;
    if (paramLong2 < 0L) {
      throw new IndexOutOfBoundsException();
    }
    while (l > 0L) {
      try
      {
        byteBuffer.limit((int)Math.min(8192L, l));
        if (fileChannel.read(byteBuffer, paramLong1) == -1) {
          throw new EOFException();
        }
        int i = byteBuffer.position();
        paramBuffer.write(byteArray, 0, i);
        paramLong2 = i;
        byteBuffer.clear();
        l -= paramLong2;
        paramLong1 += paramLong2;
      }
      finally
      {
        byteBuffer.clear();
      }
    }
  }
  
  public void write(long paramLong1, Buffer paramBuffer, long paramLong2)
    throws IOException
  {
    if (paramLong2 >= 0L)
    {
      long l = paramLong2;
      if (paramLong2 <= paramBuffer.size()) {
        while (l > 0L) {
          try
          {
            int i = (int)Math.min(8192L, l);
            paramBuffer.read(byteArray, 0, i);
            byteBuffer.limit(i);
            for (;;)
            {
              paramLong1 += fileChannel.write(byteBuffer, paramLong1);
              boolean bool = byteBuffer.hasRemaining();
              if (!bool)
              {
                paramLong2 = i;
                byteBuffer.clear();
                l -= paramLong2;
                break;
              }
            }
            return;
          }
          finally
          {
            byteBuffer.clear();
          }
        }
      }
    }
    throw new IndexOutOfBoundsException();
  }
}

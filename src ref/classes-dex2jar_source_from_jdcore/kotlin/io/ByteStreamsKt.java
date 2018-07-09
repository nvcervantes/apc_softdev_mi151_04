package kotlin.io;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.Charset;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.collections.ByteIterator;
import kotlin.internal.InlineOnly;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000Z\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\020\016\n\000\n\002\020\t\n\002\b\002\n\002\020\022\n\002\b\003\n\002\030\002\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\032\027\020\000\032\0020\001*\0020\0022\b\b\002\020\003\032\0020\004H\b\032\027\020\000\032\0020\005*\0020\0062\b\b\002\020\003\032\0020\004H\b\032\027\020\007\032\0020\b*\0020\0022\b\b\002\020\t\032\0020\nH\b\032\027\020\013\032\0020\f*\0020\0062\b\b\002\020\t\032\0020\nH\b\032\027\020\r\032\0020\016*\0020\0172\b\b\002\020\t\032\0020\nH\b\032\034\020\020\032\0020\021*\0020\0022\006\020\022\032\0020\0062\b\b\002\020\003\032\0020\004\032\r\020\023\032\0020\016*\0020\024H\b\032\035\020\023\032\0020\016*\0020\0242\006\020\025\032\0020\0042\006\020\026\032\0020\004H\b\032\r\020\027\032\0020\030*\0020\001H\002\032\024\020\031\032\0020\024*\0020\0022\b\b\002\020\032\032\0020\004\032\027\020\033\032\0020\034*\0020\0022\b\b\002\020\t\032\0020\nH\b\032\027\020\035\032\0020\036*\0020\0062\b\b\002\020\t\032\0020\nH\b¨\006\037"}, d2={"buffered", "Ljava/io/BufferedInputStream;", "Ljava/io/InputStream;", "bufferSize", "", "Ljava/io/BufferedOutputStream;", "Ljava/io/OutputStream;", "bufferedReader", "Ljava/io/BufferedReader;", "charset", "Ljava/nio/charset/Charset;", "bufferedWriter", "Ljava/io/BufferedWriter;", "byteInputStream", "Ljava/io/ByteArrayInputStream;", "", "copyTo", "", "out", "inputStream", "", "offset", "length", "iterator", "Lkotlin/collections/ByteIterator;", "readBytes", "estimatedSize", "reader", "Ljava/io/InputStreamReader;", "writer", "Ljava/io/OutputStreamWriter;", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="ByteStreamsKt")
public final class ByteStreamsKt
{
  @InlineOnly
  private static final BufferedInputStream buffered(@NotNull InputStream paramInputStream, int paramInt)
  {
    if ((paramInputStream instanceof BufferedInputStream)) {
      return (BufferedInputStream)paramInputStream;
    }
    return new BufferedInputStream(paramInputStream, paramInt);
  }
  
  @InlineOnly
  private static final BufferedOutputStream buffered(@NotNull OutputStream paramOutputStream, int paramInt)
  {
    if ((paramOutputStream instanceof BufferedOutputStream)) {
      return (BufferedOutputStream)paramOutputStream;
    }
    return new BufferedOutputStream(paramOutputStream, paramInt);
  }
  
  @InlineOnly
  private static final BufferedReader bufferedReader(@NotNull InputStream paramInputStream, Charset paramCharset)
  {
    paramInputStream = (Reader)new InputStreamReader(paramInputStream, paramCharset);
    if ((paramInputStream instanceof BufferedReader)) {
      return (BufferedReader)paramInputStream;
    }
    return new BufferedReader(paramInputStream, 8192);
  }
  
  @InlineOnly
  private static final BufferedWriter bufferedWriter(@NotNull OutputStream paramOutputStream, Charset paramCharset)
  {
    paramOutputStream = (Writer)new OutputStreamWriter(paramOutputStream, paramCharset);
    if ((paramOutputStream instanceof BufferedWriter)) {
      return (BufferedWriter)paramOutputStream;
    }
    return new BufferedWriter(paramOutputStream, 8192);
  }
  
  @InlineOnly
  private static final ByteArrayInputStream byteInputStream(@NotNull String paramString, Charset paramCharset)
  {
    if (paramString == null) {
      throw new TypeCastException("null cannot be cast to non-null type java.lang.String");
    }
    paramString = paramString.getBytes(paramCharset);
    Intrinsics.checkExpressionValueIsNotNull(paramString, "(this as java.lang.String).getBytes(charset)");
    return new ByteArrayInputStream(paramString);
  }
  
  public static final long copyTo(@NotNull InputStream paramInputStream, @NotNull OutputStream paramOutputStream, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramInputStream, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramOutputStream, "out");
    byte[] arrayOfByte = new byte[paramInt];
    paramInt = paramInputStream.read(arrayOfByte);
    long l2;
    for (long l1 = 0L; paramInt >= 0; l1 += l2)
    {
      paramOutputStream.write(arrayOfByte, 0, paramInt);
      l2 = paramInt;
      paramInt = paramInputStream.read(arrayOfByte);
    }
    return l1;
  }
  
  @InlineOnly
  private static final ByteArrayInputStream inputStream(@NotNull byte[] paramArrayOfByte)
  {
    return new ByteArrayInputStream(paramArrayOfByte);
  }
  
  @InlineOnly
  private static final ByteArrayInputStream inputStream(@NotNull byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    return new ByteArrayInputStream(paramArrayOfByte, paramInt1, paramInt2);
  }
  
  @NotNull
  public static final ByteIterator iterator(@NotNull BufferedInputStream paramBufferedInputStream)
  {
    Intrinsics.checkParameterIsNotNull(paramBufferedInputStream, "$receiver");
    (ByteIterator)new ByteIterator()
    {
      private boolean finished;
      private int nextByte = -1;
      private boolean nextPrepared;
      
      private final void prepareNext()
      {
        if ((!nextPrepared) && (!finished))
        {
          nextByte = receiver$0.read();
          boolean bool = true;
          nextPrepared = true;
          if (nextByte != -1) {
            bool = false;
          }
          finished = bool;
        }
      }
      
      public final boolean getFinished()
      {
        return finished;
      }
      
      public final int getNextByte()
      {
        return nextByte;
      }
      
      public final boolean getNextPrepared()
      {
        return nextPrepared;
      }
      
      public boolean hasNext()
      {
        prepareNext();
        return finished ^ true;
      }
      
      public byte nextByte()
      {
        prepareNext();
        if (finished) {
          throw ((Throwable)new NoSuchElementException("Input stream is over."));
        }
        byte b = (byte)nextByte;
        nextPrepared = false;
        return b;
      }
      
      public final void setFinished(boolean paramAnonymousBoolean)
      {
        finished = paramAnonymousBoolean;
      }
      
      public final void setNextByte(int paramAnonymousInt)
      {
        nextByte = paramAnonymousInt;
      }
      
      public final void setNextPrepared(boolean paramAnonymousBoolean)
      {
        nextPrepared = paramAnonymousBoolean;
      }
    };
  }
  
  @NotNull
  public static final byte[] readBytes(@NotNull InputStream paramInputStream, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramInputStream, "$receiver");
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(Math.max(paramInt, paramInputStream.available()));
    copyTo$default(paramInputStream, (OutputStream)localByteArrayOutputStream, 0, 2, null);
    paramInputStream = localByteArrayOutputStream.toByteArray();
    Intrinsics.checkExpressionValueIsNotNull(paramInputStream, "buffer.toByteArray()");
    return paramInputStream;
  }
  
  @InlineOnly
  private static final InputStreamReader reader(@NotNull InputStream paramInputStream, Charset paramCharset)
  {
    return new InputStreamReader(paramInputStream, paramCharset);
  }
  
  @InlineOnly
  private static final OutputStreamWriter writer(@NotNull OutputStream paramOutputStream, Charset paramCharset)
  {
    return new OutputStreamWriter(paramOutputStream, paramCharset);
  }
}

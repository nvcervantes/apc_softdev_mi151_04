package com.google.android.gms.common.util;

import android.os.ParcelFileDescriptor;
import java.io.ByteArrayOutputStream;
import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public final class zzp
{
  public static long zza(InputStream paramInputStream, OutputStream paramOutputStream, boolean paramBoolean)
    throws IOException
  {
    return zza(paramInputStream, paramOutputStream, paramBoolean, 1024);
  }
  
  public static long zza(InputStream paramInputStream, OutputStream paramOutputStream, boolean paramBoolean, int paramInt)
    throws IOException
  {
    byte[] arrayOfByte = new byte[paramInt];
    long l1 = 0L;
    try
    {
      for (;;)
      {
        paramInt = paramInputStream.read(arrayOfByte, 0, arrayOfByte.length);
        if (paramInt == -1) {
          break;
        }
        long l2 = paramInt;
        paramOutputStream.write(arrayOfByte, 0, paramInt);
        l1 += l2;
      }
      return l1;
    }
    finally
    {
      if (paramBoolean)
      {
        zza(paramInputStream);
        zza(paramOutputStream);
      }
    }
  }
  
  public static void zza(ParcelFileDescriptor paramParcelFileDescriptor)
  {
    if (paramParcelFileDescriptor != null) {}
    try
    {
      paramParcelFileDescriptor.close();
      return;
    }
    catch (IOException paramParcelFileDescriptor) {}
  }
  
  public static void zza(Closeable paramCloseable)
  {
    if (paramCloseable != null) {}
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable) {}
  }
  
  public static byte[] zza(InputStream paramInputStream, boolean paramBoolean)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
    zza(paramInputStream, localByteArrayOutputStream, paramBoolean);
    return localByteArrayOutputStream.toByteArray();
  }
}

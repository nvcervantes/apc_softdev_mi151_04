package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.ReflectedParcelable;
import com.google.android.gms.internal.zzbgl;
import java.io.Closeable;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

@Hide
public class BitmapTeleporter
  extends zzbgl
  implements ReflectedParcelable
{
  public static final Parcelable.Creator<BitmapTeleporter> CREATOR = new zza();
  private int zza;
  private ParcelFileDescriptor zzb;
  private int zzc;
  private Bitmap zzd;
  private boolean zze;
  private File zzf;
  
  BitmapTeleporter(int paramInt1, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt2)
  {
    zza = paramInt1;
    zzb = paramParcelFileDescriptor;
    zzc = paramInt2;
    zzd = null;
    zze = false;
  }
  
  public BitmapTeleporter(Bitmap paramBitmap)
  {
    zza = 1;
    zzb = null;
    zzc = 0;
    zzd = paramBitmap;
    zze = true;
  }
  
  private static void zza(Closeable paramCloseable)
  {
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable)
    {
      Log.w("BitmapTeleporter", "Could not close stream", paramCloseable);
    }
  }
  
  private final FileOutputStream zzc()
  {
    if (zzf == null) {
      throw new IllegalStateException("setTempDir() must be called before writing this object to a parcel");
    }
    try
    {
      localFile = File.createTempFile("teleporter", ".tmp", zzf);
    }
    catch (IOException localIOException)
    {
      File localFile;
      FileOutputStream localFileOutputStream;
      label55:
      throw new IllegalStateException("Could not create temporary file", localIOException);
    }
    try
    {
      localFileOutputStream = new FileOutputStream(localFile);
      zzb = ParcelFileDescriptor.open(localFile, 268435456);
      localFile.delete();
      return localFileOutputStream;
    }
    catch (FileNotFoundException localFileNotFoundException)
    {
      break label55;
    }
    throw new IllegalStateException("Temporary file is somehow already deleted");
  }
  
  /* Error */
  public void writeToParcel(android.os.Parcel paramParcel, int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 37	com/google/android/gms/common/data/BitmapTeleporter:zzb	Landroid/os/ParcelFileDescriptor;
    //   4: ifnonnull +138 -> 142
    //   7: aload_0
    //   8: getfield 41	com/google/android/gms/common/data/BitmapTeleporter:zzd	Landroid/graphics/Bitmap;
    //   11: astore 5
    //   13: aload 5
    //   15: invokevirtual 115	android/graphics/Bitmap:getRowBytes	()I
    //   18: aload 5
    //   20: invokevirtual 118	android/graphics/Bitmap:getHeight	()I
    //   23: imul
    //   24: invokestatic 124	java/nio/ByteBuffer:allocate	(I)Ljava/nio/ByteBuffer;
    //   27: astore 4
    //   29: aload 5
    //   31: aload 4
    //   33: invokevirtual 128	android/graphics/Bitmap:copyPixelsToBuffer	(Ljava/nio/Buffer;)V
    //   36: aload 4
    //   38: invokevirtual 132	java/nio/ByteBuffer:array	()[B
    //   41: astore 6
    //   43: new 134	java/io/DataOutputStream
    //   46: dup
    //   47: new 136	java/io/BufferedOutputStream
    //   50: dup
    //   51: aload_0
    //   52: invokespecial 138	com/google/android/gms/common/data/BitmapTeleporter:zzc	()Ljava/io/FileOutputStream;
    //   55: invokespecial 141	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   58: invokespecial 142	java/io/DataOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   61: astore 4
    //   63: aload 4
    //   65: aload 6
    //   67: arraylength
    //   68: invokevirtual 146	java/io/DataOutputStream:writeInt	(I)V
    //   71: aload 4
    //   73: aload 5
    //   75: invokevirtual 149	android/graphics/Bitmap:getWidth	()I
    //   78: invokevirtual 146	java/io/DataOutputStream:writeInt	(I)V
    //   81: aload 4
    //   83: aload 5
    //   85: invokevirtual 118	android/graphics/Bitmap:getHeight	()I
    //   88: invokevirtual 146	java/io/DataOutputStream:writeInt	(I)V
    //   91: aload 4
    //   93: aload 5
    //   95: invokevirtual 153	android/graphics/Bitmap:getConfig	()Landroid/graphics/Bitmap$Config;
    //   98: invokevirtual 159	android/graphics/Bitmap$Config:toString	()Ljava/lang/String;
    //   101: invokevirtual 162	java/io/DataOutputStream:writeUTF	(Ljava/lang/String;)V
    //   104: aload 4
    //   106: aload 6
    //   108: invokevirtual 166	java/io/DataOutputStream:write	([B)V
    //   111: aload 4
    //   113: invokestatic 168	com/google/android/gms/common/data/BitmapTeleporter:zza	(Ljava/io/Closeable;)V
    //   116: goto +26 -> 142
    //   119: astore_1
    //   120: goto +15 -> 135
    //   123: astore_1
    //   124: new 69	java/lang/IllegalStateException
    //   127: dup
    //   128: ldc -86
    //   130: aload_1
    //   131: invokespecial 107	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   134: athrow
    //   135: aload 4
    //   137: invokestatic 168	com/google/android/gms/common/data/BitmapTeleporter:zza	(Ljava/io/Closeable;)V
    //   140: aload_1
    //   141: athrow
    //   142: aload_1
    //   143: invokestatic 175	com/google/android/gms/internal/zzbgo:zza	(Landroid/os/Parcel;)I
    //   146: istore_3
    //   147: aload_1
    //   148: iconst_1
    //   149: aload_0
    //   150: getfield 35	com/google/android/gms/common/data/BitmapTeleporter:zza	I
    //   153: invokestatic 178	com/google/android/gms/internal/zzbgo:zza	(Landroid/os/Parcel;II)V
    //   156: aload_1
    //   157: iconst_2
    //   158: aload_0
    //   159: getfield 37	com/google/android/gms/common/data/BitmapTeleporter:zzb	Landroid/os/ParcelFileDescriptor;
    //   162: iload_2
    //   163: iconst_1
    //   164: ior
    //   165: iconst_0
    //   166: invokestatic 181	com/google/android/gms/internal/zzbgo:zza	(Landroid/os/Parcel;ILandroid/os/Parcelable;IZ)V
    //   169: aload_1
    //   170: iconst_3
    //   171: aload_0
    //   172: getfield 39	com/google/android/gms/common/data/BitmapTeleporter:zzc	I
    //   175: invokestatic 178	com/google/android/gms/internal/zzbgo:zza	(Landroid/os/Parcel;II)V
    //   178: aload_1
    //   179: iload_3
    //   180: invokestatic 183	com/google/android/gms/internal/zzbgo:zza	(Landroid/os/Parcel;I)V
    //   183: aload_0
    //   184: aconst_null
    //   185: putfield 37	com/google/android/gms/common/data/BitmapTeleporter:zzb	Landroid/os/ParcelFileDescriptor;
    //   188: return
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	189	0	this	BitmapTeleporter
    //   0	189	1	paramParcel	android.os.Parcel
    //   0	189	2	paramInt	int
    //   146	34	3	i	int
    //   27	109	4	localObject	Object
    //   11	83	5	localBitmap	Bitmap
    //   41	66	6	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   63	111	119	finally
    //   124	135	119	finally
    //   63	111	123	java/io/IOException
  }
  
  /* Error */
  public final Bitmap zza()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 43	com/google/android/gms/common/data/BitmapTeleporter:zze	Z
    //   4: ifne +121 -> 125
    //   7: new 186	java/io/DataInputStream
    //   10: dup
    //   11: new 188	android/os/ParcelFileDescriptor$AutoCloseInputStream
    //   14: dup
    //   15: aload_0
    //   16: getfield 37	com/google/android/gms/common/data/BitmapTeleporter:zzb	Landroid/os/ParcelFileDescriptor;
    //   19: invokespecial 191	android/os/ParcelFileDescriptor$AutoCloseInputStream:<init>	(Landroid/os/ParcelFileDescriptor;)V
    //   22: invokespecial 194	java/io/DataInputStream:<init>	(Ljava/io/InputStream;)V
    //   25: astore_3
    //   26: aload_3
    //   27: invokevirtual 197	java/io/DataInputStream:readInt	()I
    //   30: newarray byte
    //   32: astore 5
    //   34: aload_3
    //   35: invokevirtual 197	java/io/DataInputStream:readInt	()I
    //   38: istore_1
    //   39: aload_3
    //   40: invokevirtual 197	java/io/DataInputStream:readInt	()I
    //   43: istore_2
    //   44: aload_3
    //   45: invokevirtual 200	java/io/DataInputStream:readUTF	()Ljava/lang/String;
    //   48: invokestatic 204	android/graphics/Bitmap$Config:valueOf	(Ljava/lang/String;)Landroid/graphics/Bitmap$Config;
    //   51: astore 4
    //   53: aload_3
    //   54: aload 5
    //   56: invokevirtual 208	java/io/DataInputStream:read	([B)I
    //   59: pop
    //   60: aload_3
    //   61: invokestatic 168	com/google/android/gms/common/data/BitmapTeleporter:zza	(Ljava/io/Closeable;)V
    //   64: aload 5
    //   66: invokestatic 212	java/nio/ByteBuffer:wrap	([B)Ljava/nio/ByteBuffer;
    //   69: astore_3
    //   70: iload_1
    //   71: iload_2
    //   72: aload 4
    //   74: invokestatic 216	android/graphics/Bitmap:createBitmap	(IILandroid/graphics/Bitmap$Config;)Landroid/graphics/Bitmap;
    //   77: astore 4
    //   79: aload 4
    //   81: aload_3
    //   82: invokevirtual 219	android/graphics/Bitmap:copyPixelsFromBuffer	(Ljava/nio/Buffer;)V
    //   85: aload_0
    //   86: aload 4
    //   88: putfield 41	com/google/android/gms/common/data/BitmapTeleporter:zzd	Landroid/graphics/Bitmap;
    //   91: aload_0
    //   92: iconst_1
    //   93: putfield 43	com/google/android/gms/common/data/BitmapTeleporter:zze	Z
    //   96: goto +29 -> 125
    //   99: astore 4
    //   101: goto +17 -> 118
    //   104: astore 4
    //   106: new 69	java/lang/IllegalStateException
    //   109: dup
    //   110: ldc -35
    //   112: aload 4
    //   114: invokespecial 107	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   117: athrow
    //   118: aload_3
    //   119: invokestatic 168	com/google/android/gms/common/data/BitmapTeleporter:zza	(Ljava/io/Closeable;)V
    //   122: aload 4
    //   124: athrow
    //   125: aload_0
    //   126: getfield 41	com/google/android/gms/common/data/BitmapTeleporter:zzd	Landroid/graphics/Bitmap;
    //   129: areturn
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	130	0	this	BitmapTeleporter
    //   38	33	1	i	int
    //   43	29	2	j	int
    //   25	94	3	localObject1	Object
    //   51	36	4	localObject2	Object
    //   99	1	4	localObject3	Object
    //   104	19	4	localIOException	IOException
    //   32	33	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   26	60	99	finally
    //   106	118	99	finally
    //   26	60	104	java/io/IOException
  }
  
  public final void zza(File paramFile)
  {
    if (paramFile == null) {
      throw new NullPointerException("Cannot set null temp directory");
    }
    zzf = paramFile;
  }
  
  public final void zzb()
  {
    if (!zze) {
      try
      {
        zzb.close();
        return;
      }
      catch (IOException localIOException)
      {
        Log.w("BitmapTeleporter", "Could not close PFD", localIOException);
      }
    }
  }
}

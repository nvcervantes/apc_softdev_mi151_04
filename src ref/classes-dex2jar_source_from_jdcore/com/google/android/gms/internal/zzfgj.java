package com.google.android.gms.internal;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

public abstract class zzfgj<MessageType extends zzfgj<MessageType, BuilderType>, BuilderType extends zzfgk<MessageType, BuilderType>>
  implements zzfjc
{
  private static boolean zzb = false;
  protected int zza = 0;
  
  public zzfgj() {}
  
  protected static <T> void zza(Iterable<T> paramIterable, List<? super T> paramList)
  {
    zzfgk.zza(paramIterable, paramList);
  }
  
  public final void zza(OutputStream paramOutputStream)
    throws IOException
  {
    paramOutputStream = zzfhg.zza(paramOutputStream, zzfhg.zza(zza()));
    zza(paramOutputStream);
    paramOutputStream.zza();
  }
  
  public final zzfgs zzp()
  {
    try
    {
      Object localObject = zzfgs.zzb(zza());
      zza(((zzfgx)localObject).zzb());
      localObject = ((zzfgx)localObject).zza();
      return localObject;
    }
    catch (IOException localIOException)
    {
      String str = getClass().getName();
      StringBuilder localStringBuilder = new StringBuilder(62 + String.valueOf(str).length() + String.valueOf("ByteString").length());
      localStringBuilder.append("Serializing ");
      localStringBuilder.append(str);
      localStringBuilder.append(" to a ");
      localStringBuilder.append("ByteString");
      localStringBuilder.append(" threw an IOException (should never happen).");
      throw new RuntimeException(localStringBuilder.toString(), localIOException);
    }
  }
  
  public final byte[] zzq()
  {
    try
    {
      byte[] arrayOfByte = new byte[zza()];
      localObject = zzfhg.zza(arrayOfByte);
      zza((zzfhg)localObject);
      ((zzfhg)localObject).zzc();
      return arrayOfByte;
    }
    catch (IOException localIOException)
    {
      Object localObject = getClass().getName();
      StringBuilder localStringBuilder = new StringBuilder(62 + String.valueOf(localObject).length() + String.valueOf("byte array").length());
      localStringBuilder.append("Serializing ");
      localStringBuilder.append((String)localObject);
      localStringBuilder.append(" to a ");
      localStringBuilder.append("byte array");
      localStringBuilder.append(" threw an IOException (should never happen).");
      throw new RuntimeException(localStringBuilder.toString(), localIOException);
    }
  }
}

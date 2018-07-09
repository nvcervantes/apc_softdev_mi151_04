package com.google.android.gms.internal;

import java.io.IOException;

public final class zzflv
{
  public static final int[] zza = new int[0];
  public static final long[] zzb = new long[0];
  public static final float[] zzc = new float[0];
  public static final double[] zzd = new double[0];
  public static final boolean[] zze = new boolean[0];
  public static final String[] zzf = new String[0];
  public static final byte[][] zzg = new byte[0][];
  public static final byte[] zzh = new byte[0];
  private static int zzi = 11;
  private static int zzj = 12;
  private static int zzk = 16;
  private static int zzl = 26;
  
  public static final int zza(zzflj paramZzflj, int paramInt)
    throws IOException
  {
    int j = paramZzflj.zzm();
    paramZzflj.zzb(paramInt);
    int i = 1;
    while (paramZzflj.zza() == paramInt)
    {
      paramZzflj.zzb(paramInt);
      i += 1;
    }
    paramZzflj.zzb(j, paramInt);
    return i;
  }
}

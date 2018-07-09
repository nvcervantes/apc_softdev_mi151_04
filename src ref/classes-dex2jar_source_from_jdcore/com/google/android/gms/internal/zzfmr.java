package com.google.android.gms.internal;

import java.io.IOException;
import java.util.Arrays;

public final class zzfmr
  extends zzflm<zzfmr>
  implements Cloneable
{
  public long zza = 0L;
  public long zzb = 0L;
  public byte[] zzc = zzflv.zzh;
  public long zzd = 180000L;
  public byte[] zze = zzflv.zzh;
  private long zzf = 0L;
  private String zzg = "";
  private int zzh = 0;
  private int zzi = 0;
  private boolean zzj = false;
  private zzfms[] zzk = zzfms.zzb();
  private byte[] zzl = zzflv.zzh;
  private zzfmp zzm = null;
  private String zzn = "";
  private String zzo = "";
  private zzfmo zzp = null;
  private String zzq = "";
  private zzfmq zzr = null;
  private String zzs = "";
  private int zzt = 0;
  private int[] zzu = zzflv.zza;
  private long zzv = 0L;
  private zzfmt zzw = null;
  private boolean zzx = false;
  
  public zzfmr()
  {
    zzax = null;
    zzay = -1;
  }
  
  private final zzfmr zzb()
  {
    try
    {
      zzfmr localZzfmr = (zzfmr)super.zzc();
      if ((zzk != null) && (zzk.length > 0))
      {
        zzk = new zzfms[zzk.length];
        int i = 0;
        while (i < zzk.length)
        {
          if (zzk[i] != null) {
            zzk[i] = ((zzfms)zzk[i].clone());
          }
          i += 1;
        }
      }
      if (zzm != null) {
        zzm = ((zzfmp)zzm.clone());
      }
      if (zzp != null) {
        zzp = ((zzfmo)zzp.clone());
      }
      if (zzr != null) {
        zzr = ((zzfmq)zzr.clone());
      }
      if ((zzu != null) && (zzu.length > 0)) {
        zzu = ((int[])zzu.clone());
      }
      if (zzw != null) {
        zzw = ((zzfmt)zzw.clone());
      }
      return localZzfmr;
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
  }
  
  private final zzfmr zzb(zzflj paramZzflj)
    throws IOException
  {
    for (;;)
    {
      int i = paramZzflj.zza();
      int k;
      switch (i)
      {
      default: 
        if (super.zza(paramZzflj, i)) {
          continue;
        }
        return this;
      case 200: 
        zzx = paramZzflj.zzd();
        break;
      case 194: 
        zzs = paramZzflj.zze();
        break;
      case 186: 
        if (zzw == null) {
          zzw = new zzfmt();
        }
        localObject = zzw;
        break;
      case 176: 
        zzv = paramZzflj.zzb();
        break;
      case 168: 
        zzf = paramZzflj.zzb();
        break;
      case 162: 
        k = paramZzflj.zzc(paramZzflj.zzh());
        i = paramZzflj.zzm();
        j = 0;
        while (paramZzflj.zzl() > 0)
        {
          paramZzflj.zzc();
          j += 1;
        }
        paramZzflj.zze(i);
        if (zzu == null) {
          i = 0;
        } else {
          i = zzu.length;
        }
        localObject = new int[j + i];
        j = i;
        if (i != 0)
        {
          System.arraycopy(zzu, 0, localObject, 0, i);
          j = i;
        }
        while (j < localObject.length)
        {
          localObject[j] = paramZzflj.zzc();
          j += 1;
        }
        zzu = ((int[])localObject);
        paramZzflj.zzd(k);
        break;
      case 160: 
        j = zzflv.zza(paramZzflj, 160);
        if (zzu == null) {
          i = 0;
        } else {
          i = zzu.length;
        }
        localObject = new int[j + i];
        j = i;
        if (i != 0)
        {
          System.arraycopy(zzu, 0, localObject, 0, i);
          j = i;
        }
        while (j < localObject.length - 1)
        {
          localObject[j] = paramZzflj.zzc();
          paramZzflj.zza();
          j += 1;
        }
        localObject[j] = paramZzflj.zzc();
        zzu = ((int[])localObject);
        break;
      case 152: 
        j = paramZzflj.zzm();
      }
      try
      {
        k = paramZzflj.zzc();
        switch (k)
        {
        }
      }
      catch (IllegalArgumentException localIllegalArgumentException)
      {
        for (;;) {}
      }
      zzt = k;
      continue;
      Object localObject = new StringBuilder(45);
      ((StringBuilder)localObject).append(k);
      ((StringBuilder)localObject).append(" is not a valid enum InternalEvent");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
      paramZzflj.zze(j);
      zza(paramZzflj, i);
      continue;
      zze = paramZzflj.zzf();
      continue;
      zzb = paramZzflj.zzb();
      continue;
      if (zzr == null) {
        zzr = new zzfmq();
      }
      localObject = zzr;
      break label812;
      zzd = paramZzflj.zzg();
      continue;
      zzq = paramZzflj.zze();
      continue;
      zzo = paramZzflj.zze();
      continue;
      zzi = paramZzflj.zzc();
      continue;
      zzh = paramZzflj.zzc();
      continue;
      zzj = paramZzflj.zzd();
      continue;
      if (zzm == null) {
        zzm = new zzfmp();
      }
      localObject = zzm;
      break label812;
      zzn = paramZzflj.zze();
      continue;
      if (zzp == null) {
        zzp = new zzfmo();
      }
      localObject = zzp;
      label812:
      paramZzflj.zza((zzfls)localObject);
      continue;
      zzc = paramZzflj.zzf();
      continue;
      zzl = paramZzflj.zzf();
      continue;
      int j = zzflv.zza(paramZzflj, 26);
      if (zzk == null) {
        i = 0;
      } else {
        i = zzk.length;
      }
      localObject = new zzfms[j + i];
      j = i;
      if (i != 0)
      {
        System.arraycopy(zzk, 0, localObject, 0, i);
        j = i;
      }
      while (j < localObject.length - 1)
      {
        localObject[j] = new zzfms();
        paramZzflj.zza(localObject[j]);
        paramZzflj.zza();
        j += 1;
      }
      localObject[j] = new zzfms();
      paramZzflj.zza(localObject[j]);
      zzk = ((zzfms[])localObject);
      continue;
      zzg = paramZzflj.zze();
      continue;
      zza = paramZzflj.zzb();
    }
    return this;
  }
  
  public final boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof zzfmr)) {
      return false;
    }
    paramObject = (zzfmr)paramObject;
    if (zza != zza) {
      return false;
    }
    if (zzb != zzb) {
      return false;
    }
    if (zzf != zzf) {
      return false;
    }
    if (zzg == null)
    {
      if (zzg != null) {
        return false;
      }
    }
    else if (!zzg.equals(zzg)) {
      return false;
    }
    if (zzh != zzh) {
      return false;
    }
    if (zzi != zzi) {
      return false;
    }
    if (zzj != zzj) {
      return false;
    }
    if (!zzflq.zza(zzk, zzk)) {
      return false;
    }
    if (!Arrays.equals(zzl, zzl)) {
      return false;
    }
    if (zzm == null)
    {
      if (zzm != null) {
        return false;
      }
    }
    else if (!zzm.equals(zzm)) {
      return false;
    }
    if (!Arrays.equals(zzc, zzc)) {
      return false;
    }
    if (zzn == null)
    {
      if (zzn != null) {
        return false;
      }
    }
    else if (!zzn.equals(zzn)) {
      return false;
    }
    if (zzo == null)
    {
      if (zzo != null) {
        return false;
      }
    }
    else if (!zzo.equals(zzo)) {
      return false;
    }
    if (zzp == null)
    {
      if (zzp != null) {
        return false;
      }
    }
    else if (!zzp.equals(zzp)) {
      return false;
    }
    if (zzq == null)
    {
      if (zzq != null) {
        return false;
      }
    }
    else if (!zzq.equals(zzq)) {
      return false;
    }
    if (zzd != zzd) {
      return false;
    }
    if (zzr == null)
    {
      if (zzr != null) {
        return false;
      }
    }
    else if (!zzr.equals(zzr)) {
      return false;
    }
    if (!Arrays.equals(zze, zze)) {
      return false;
    }
    if (zzs == null)
    {
      if (zzs != null) {
        return false;
      }
    }
    else if (!zzs.equals(zzs)) {
      return false;
    }
    if (zzt != zzt) {
      return false;
    }
    if (!zzflq.zza(zzu, zzu)) {
      return false;
    }
    if (zzv != zzv) {
      return false;
    }
    if (zzw == null)
    {
      if (zzw != null) {
        return false;
      }
    }
    else if (!zzw.equals(zzw)) {
      return false;
    }
    if (zzx != zzx) {
      return false;
    }
    if ((zzax != null) && (!zzax.zzb())) {
      return zzax.equals(zzax);
    }
    if (zzax != null) {
      return zzax.zzb();
    }
    return true;
  }
  
  public final int hashCode()
  {
    int i9 = getClass().getName().hashCode();
    int i10 = (int)(zza ^ zza >>> 32);
    int i11 = (int)(zzb ^ zzb >>> 32);
    int i12 = (int)(zzf ^ zzf >>> 32);
    Object localObject = zzg;
    int i8 = 0;
    int i;
    if (localObject == null) {
      i = 0;
    } else {
      i = zzg.hashCode();
    }
    int i13 = zzh;
    int i14 = zzi;
    boolean bool = zzj;
    int i6 = 1237;
    int j;
    if (bool) {
      j = 1231;
    } else {
      j = 1237;
    }
    int i15 = zzflq.zza(zzk);
    int i16 = Arrays.hashCode(zzl);
    localObject = zzm;
    int k;
    if (localObject == null) {
      k = 0;
    } else {
      k = ((zzfmp)localObject).hashCode();
    }
    int i17 = Arrays.hashCode(zzc);
    int m;
    if (zzn == null) {
      m = 0;
    } else {
      m = zzn.hashCode();
    }
    int n;
    if (zzo == null) {
      n = 0;
    } else {
      n = zzo.hashCode();
    }
    localObject = zzp;
    int i1;
    if (localObject == null) {
      i1 = 0;
    } else {
      i1 = ((zzfmo)localObject).hashCode();
    }
    int i2;
    if (zzq == null) {
      i2 = 0;
    } else {
      i2 = zzq.hashCode();
    }
    int i18 = (int)(zzd ^ zzd >>> 32);
    localObject = zzr;
    int i3;
    if (localObject == null) {
      i3 = 0;
    } else {
      i3 = ((zzfmq)localObject).hashCode();
    }
    int i19 = Arrays.hashCode(zze);
    int i4;
    if (zzs == null) {
      i4 = 0;
    } else {
      i4 = zzs.hashCode();
    }
    int i20 = zzt;
    int i21 = zzflq.zza(zzu);
    int i22 = (int)(zzv ^ zzv >>> 32);
    localObject = zzw;
    int i5;
    if (localObject == null) {
      i5 = 0;
    } else {
      i5 = ((zzfmt)localObject).hashCode();
    }
    if (zzx) {
      i6 = 1231;
    }
    int i7 = i8;
    if (zzax != null) {
      if (zzax.zzb()) {
        i7 = i8;
      } else {
        i7 = zzax.hashCode();
      }
    }
    return (((((((((((((((((((((((((527 + i9) * 31 + i10) * 31 + i11) * 31 + i12) * 31 + i) * 31 + i13) * 31 + i14) * 31 + j) * 31 + i15) * 31 + i16) * 31 + k) * 31 + i17) * 31 + m) * 31 + n) * 31 + i1) * 31 + i2) * 31 + i18) * 31 + i3) * 31 + i19) * 31 + i4) * 31 + i20) * 31 + i21) * 31 + i22) * 31 + i5) * 31 + i6) * 31 + i7;
  }
  
  protected final int zza()
  {
    int i = super.zza();
    int j = i;
    if (zza != 0L) {
      j = i + zzflk.zze(1, zza);
    }
    i = j;
    if (zzg != null)
    {
      i = j;
      if (!zzg.equals("")) {
        i = j + zzflk.zzb(2, zzg);
      }
    }
    Object localObject = zzk;
    int m = 0;
    j = i;
    int k;
    if (localObject != null)
    {
      j = i;
      if (zzk.length > 0)
      {
        j = 0;
        while (j < zzk.length)
        {
          localObject = zzk[j];
          k = i;
          if (localObject != null) {
            k = i + zzflk.zzb(3, (zzfls)localObject);
          }
          j += 1;
          i = k;
        }
        j = i;
      }
    }
    i = j;
    if (!Arrays.equals(zzl, zzflv.zzh)) {
      i = j + zzflk.zzb(4, zzl);
    }
    j = i;
    if (!Arrays.equals(zzc, zzflv.zzh)) {
      j = i + zzflk.zzb(6, zzc);
    }
    i = j;
    if (zzp != null) {
      i = j + zzflk.zzb(7, zzp);
    }
    j = i;
    if (zzn != null)
    {
      j = i;
      if (!zzn.equals("")) {
        j = i + zzflk.zzb(8, zzn);
      }
    }
    i = j;
    if (zzm != null) {
      i = j + zzflk.zzb(9, zzm);
    }
    j = i;
    if (zzj) {
      j = i + (zzflk.zzb(10) + 1);
    }
    i = j;
    if (zzh != 0) {
      i = j + zzflk.zzb(11, zzh);
    }
    j = i;
    if (zzi != 0) {
      j = i + zzflk.zzb(12, zzi);
    }
    i = j;
    if (zzo != null)
    {
      i = j;
      if (!zzo.equals("")) {
        i = j + zzflk.zzb(13, zzo);
      }
    }
    j = i;
    if (zzq != null)
    {
      j = i;
      if (!zzq.equals("")) {
        j = i + zzflk.zzb(14, zzq);
      }
    }
    i = j;
    if (zzd != 180000L) {
      i = j + zzflk.zzf(15, zzd);
    }
    j = i;
    if (zzr != null) {
      j = i + zzflk.zzb(16, zzr);
    }
    i = j;
    if (zzb != 0L) {
      i = j + zzflk.zze(17, zzb);
    }
    j = i;
    if (!Arrays.equals(zze, zzflv.zzh)) {
      j = i + zzflk.zzb(18, zze);
    }
    i = j;
    if (zzt != 0) {
      i = j + zzflk.zzb(19, zzt);
    }
    j = i;
    if (zzu != null)
    {
      j = i;
      if (zzu.length > 0)
      {
        k = 0;
        j = m;
        while (j < zzu.length)
        {
          k += zzflk.zza(zzu[j]);
          j += 1;
        }
        j = i + k + 2 * zzu.length;
      }
    }
    i = j;
    if (zzf != 0L) {
      i = j + zzflk.zze(21, zzf);
    }
    j = i;
    if (zzv != 0L) {
      j = i + zzflk.zze(22, zzv);
    }
    i = j;
    if (zzw != null) {
      i = j + zzflk.zzb(23, zzw);
    }
    j = i;
    if (zzs != null)
    {
      j = i;
      if (!zzs.equals("")) {
        j = i + zzflk.zzb(24, zzs);
      }
    }
    i = j;
    if (zzx) {
      i = j + (zzflk.zzb(25) + 1);
    }
    return i;
  }
  
  public final void zza(zzflk paramZzflk)
    throws IOException
  {
    if (zza != 0L) {
      paramZzflk.zzb(1, zza);
    }
    if ((zzg != null) && (!zzg.equals(""))) {
      paramZzflk.zza(2, zzg);
    }
    Object localObject = zzk;
    int j = 0;
    int i;
    if ((localObject != null) && (zzk.length > 0))
    {
      i = 0;
      while (i < zzk.length)
      {
        localObject = zzk[i];
        if (localObject != null) {
          paramZzflk.zza(3, (zzfls)localObject);
        }
        i += 1;
      }
    }
    if (!Arrays.equals(zzl, zzflv.zzh)) {
      paramZzflk.zza(4, zzl);
    }
    if (!Arrays.equals(zzc, zzflv.zzh)) {
      paramZzflk.zza(6, zzc);
    }
    if (zzp != null) {
      paramZzflk.zza(7, zzp);
    }
    if ((zzn != null) && (!zzn.equals(""))) {
      paramZzflk.zza(8, zzn);
    }
    if (zzm != null) {
      paramZzflk.zza(9, zzm);
    }
    if (zzj) {
      paramZzflk.zza(10, zzj);
    }
    if (zzh != 0) {
      paramZzflk.zza(11, zzh);
    }
    if (zzi != 0) {
      paramZzflk.zza(12, zzi);
    }
    if ((zzo != null) && (!zzo.equals(""))) {
      paramZzflk.zza(13, zzo);
    }
    if ((zzq != null) && (!zzq.equals(""))) {
      paramZzflk.zza(14, zzq);
    }
    if (zzd != 180000L) {
      paramZzflk.zzd(15, zzd);
    }
    if (zzr != null) {
      paramZzflk.zza(16, zzr);
    }
    if (zzb != 0L) {
      paramZzflk.zzb(17, zzb);
    }
    if (!Arrays.equals(zze, zzflv.zzh)) {
      paramZzflk.zza(18, zze);
    }
    if (zzt != 0) {
      paramZzflk.zza(19, zzt);
    }
    if ((zzu != null) && (zzu.length > 0))
    {
      i = j;
      while (i < zzu.length)
      {
        paramZzflk.zza(20, zzu[i]);
        i += 1;
      }
    }
    if (zzf != 0L) {
      paramZzflk.zzb(21, zzf);
    }
    if (zzv != 0L) {
      paramZzflk.zzb(22, zzv);
    }
    if (zzw != null) {
      paramZzflk.zza(23, zzw);
    }
    if ((zzs != null) && (!zzs.equals(""))) {
      paramZzflk.zza(24, zzs);
    }
    if (zzx) {
      paramZzflk.zza(25, zzx);
    }
    super.zza(paramZzflk);
  }
}

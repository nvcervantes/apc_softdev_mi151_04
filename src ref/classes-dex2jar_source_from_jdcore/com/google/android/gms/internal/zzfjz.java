package com.google.android.gms.internal;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

final class zzfjz
  extends zzfjy<FieldDescriptorType, Object>
{
  zzfjz(int paramInt)
  {
    super(paramInt, null);
  }
  
  public final void zza()
  {
    if (!zzb())
    {
      int i = 0;
      while (i < zzc())
      {
        localObject = zzb(i);
        if (((zzfhs)((Map.Entry)localObject).getKey()).zzd()) {
          ((Map.Entry)localObject).setValue(Collections.unmodifiableList((List)((Map.Entry)localObject).getValue()));
        }
        i += 1;
      }
      Object localObject = zzd().iterator();
      while (((Iterator)localObject).hasNext())
      {
        Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
        if (((zzfhs)localEntry.getKey()).zzd()) {
          localEntry.setValue(Collections.unmodifiableList((List)localEntry.getValue()));
        }
      }
    }
    super.zza();
  }
}

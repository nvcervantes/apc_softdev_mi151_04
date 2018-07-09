package com.google.android.gms.common.data;

import android.content.ContentValues;
import android.database.CharArrayBuffer;
import android.database.CursorIndexOutOfBoundsException;
import android.database.CursorWindow;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.common.internal.zzbq;
import com.google.android.gms.common.internal.zzc;
import com.google.android.gms.internal.zzbgl;
import com.google.android.gms.internal.zzbgo;
import java.io.Closeable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

@KeepName
@Hide
public final class DataHolder
  extends zzbgl
  implements Closeable
{
  public static final Parcelable.Creator<DataHolder> CREATOR = new zzf();
  private static final zza zzk = new zze(new String[0], null);
  int zza;
  private int zzb;
  private final String[] zzc;
  private Bundle zzd;
  private final CursorWindow[] zze;
  private final int zzf;
  private final Bundle zzg;
  private int[] zzh;
  private boolean zzi = false;
  private boolean zzj = true;
  
  DataHolder(int paramInt1, String[] paramArrayOfString, CursorWindow[] paramArrayOfCursorWindow, int paramInt2, Bundle paramBundle)
  {
    zzb = paramInt1;
    zzc = paramArrayOfString;
    zze = paramArrayOfCursorWindow;
    zzf = paramInt2;
    zzg = paramBundle;
  }
  
  private DataHolder(zza paramZza, int paramInt, Bundle paramBundle)
  {
    this(zza.zza(paramZza), zza(paramZza, -1), paramInt, null);
  }
  
  private DataHolder(String[] paramArrayOfString, CursorWindow[] paramArrayOfCursorWindow, int paramInt, Bundle paramBundle)
  {
    zzb = 1;
    zzc = ((String[])zzbq.zza(paramArrayOfString));
    zze = ((CursorWindow[])zzbq.zza(paramArrayOfCursorWindow));
    zzf = paramInt;
    zzg = paramBundle;
    zza();
  }
  
  public static zza zza(String[] paramArrayOfString)
  {
    return new zza(paramArrayOfString, null, null);
  }
  
  private final void zza(String paramString, int paramInt)
  {
    if ((zzd != null) && (zzd.containsKey(paramString)))
    {
      if (zze()) {
        throw new IllegalArgumentException("Buffer is closed.");
      }
      if ((paramInt >= 0) && (paramInt < zza)) {
        return;
      }
      throw new CursorIndexOutOfBoundsException(paramInt, zza);
    }
    paramString = String.valueOf(paramString);
    if (paramString.length() != 0) {
      paramString = "No such column: ".concat(paramString);
    } else {
      paramString = new String("No such column: ");
    }
    throw new IllegalArgumentException(paramString);
  }
  
  private static CursorWindow[] zza(zza paramZza, int paramInt)
  {
    Object localObject1 = zza.zza(paramZza);
    int k = 0;
    if (localObject1.length == 0) {
      return new CursorWindow[0];
    }
    ArrayList localArrayList2 = zza.zzb(paramZza);
    int m = localArrayList2.size();
    Object localObject2 = new CursorWindow(false);
    ArrayList localArrayList1 = new ArrayList();
    localArrayList1.add(localObject2);
    ((CursorWindow)localObject2).setNumColumns(zza.zza(paramZza).length);
    int i = 0;
    paramInt = i;
    if (paramInt < m) {
      localObject1 = localObject2;
    }
    for (;;)
    {
      int j;
      try
      {
        if (!((CursorWindow)localObject2).allocRow())
        {
          localObject1 = new StringBuilder(72);
          ((StringBuilder)localObject1).append("Allocating additional cursor window for large data set (row ");
          ((StringBuilder)localObject1).append(paramInt);
          ((StringBuilder)localObject1).append(")");
          Log.d("DataHolder", ((StringBuilder)localObject1).toString());
          localObject2 = new CursorWindow(false);
          ((CursorWindow)localObject2).setStartPosition(paramInt);
          ((CursorWindow)localObject2).setNumColumns(zza.zza(paramZza).length);
          localArrayList1.add(localObject2);
          localObject1 = localObject2;
          if (!((CursorWindow)localObject2).allocRow())
          {
            Log.e("DataHolder", "Unable to allocate row to hold data.");
            localArrayList1.remove(localObject2);
            return (CursorWindow[])localArrayList1.toArray(new CursorWindow[localArrayList1.size()]);
          }
        }
        Map localMap = (Map)localArrayList2.get(paramInt);
        j = 0;
        boolean bool = true;
        if ((j < zza.zza(paramZza).length) && (bool))
        {
          localObject2 = zza.zza(paramZza)[j];
          Object localObject3 = localMap.get(localObject2);
          if (localObject3 == null)
          {
            bool = ((CursorWindow)localObject1).putNull(paramInt, j);
            break label749;
          }
          if ((localObject3 instanceof String))
          {
            bool = ((CursorWindow)localObject1).putString((String)localObject3, paramInt, j);
            break label749;
          }
          if ((localObject3 instanceof Long))
          {
            l = ((Long)localObject3).longValue();
            bool = ((CursorWindow)localObject1).putLong(l, paramInt, j);
            break label749;
          }
          if ((localObject3 instanceof Integer))
          {
            bool = ((CursorWindow)localObject1).putLong(((Integer)localObject3).intValue(), paramInt, j);
            break label749;
          }
          if ((localObject3 instanceof Boolean))
          {
            if (!((Boolean)localObject3).booleanValue()) {
              break label743;
            }
            l = 1L;
            continue;
          }
          if ((localObject3 instanceof byte[]))
          {
            bool = ((CursorWindow)localObject1).putBlob((byte[])localObject3, paramInt, j);
            break label749;
          }
          if ((localObject3 instanceof Double))
          {
            bool = ((CursorWindow)localObject1).putDouble(((Double)localObject3).doubleValue(), paramInt, j);
            break label749;
          }
          if ((localObject3 instanceof Float))
          {
            bool = ((CursorWindow)localObject1).putDouble(((Float)localObject3).floatValue(), paramInt, j);
            break label749;
          }
          paramZza = String.valueOf(localObject3);
          localObject1 = new StringBuilder(32 + String.valueOf(localObject2).length() + String.valueOf(paramZza).length());
          ((StringBuilder)localObject1).append("Unsupported object for column ");
          ((StringBuilder)localObject1).append((String)localObject2);
          ((StringBuilder)localObject1).append(": ");
          ((StringBuilder)localObject1).append(paramZza);
          throw new IllegalArgumentException(((StringBuilder)localObject1).toString());
        }
        if (!bool)
        {
          if (i != 0) {
            throw new zzb("Could not add the value to a new CursorWindow. The size of value may be larger than what a CursorWindow can handle.");
          }
          localObject2 = new StringBuilder(74);
          ((StringBuilder)localObject2).append("Couldn't populate window data for row ");
          ((StringBuilder)localObject2).append(paramInt);
          ((StringBuilder)localObject2).append(" - allocating new window.");
          Log.d("DataHolder", ((StringBuilder)localObject2).toString());
          ((CursorWindow)localObject1).freeLastRow();
          localObject1 = new CursorWindow(false);
          ((CursorWindow)localObject1).setStartPosition(paramInt);
          ((CursorWindow)localObject1).setNumColumns(zza.zza(paramZza).length);
          localArrayList1.add(localObject1);
          paramInt -= 1;
          i = 1;
        }
        else
        {
          i = 0;
        }
        paramInt += 1;
        localObject2 = localObject1;
      }
      catch (RuntimeException paramZza)
      {
        i = localArrayList1.size();
        paramInt = k;
        if (paramInt < i)
        {
          ((CursorWindow)localArrayList1.get(paramInt)).close();
          paramInt += 1;
          continue;
        }
        throw paramZza;
      }
      return (CursorWindow[])localArrayList1.toArray(new CursorWindow[localArrayList1.size()]);
      label743:
      long l = 0L;
      continue;
      label749:
      j += 1;
    }
  }
  
  public static DataHolder zzb(int paramInt)
  {
    return new DataHolder(zzk, paramInt, null);
  }
  
  public final void close()
  {
    try
    {
      if (!zzi)
      {
        zzi = true;
        int i = 0;
        while (i < zze.length)
        {
          zze[i].close();
          i += 1;
        }
      }
      return;
    }
    finally {}
  }
  
  protected final void finalize()
    throws Throwable
  {
    try
    {
      if ((zzj) && (zze.length > 0) && (!zze()))
      {
        close();
        String str = toString();
        StringBuilder localStringBuilder = new StringBuilder(178 + String.valueOf(str).length());
        localStringBuilder.append("Internal data leak within a DataBuffer object detected!  Be sure to explicitly call release() on all DataBuffer extending objects when you are done with them. (internal object: ");
        localStringBuilder.append(str);
        localStringBuilder.append(")");
        Log.e("DataBuffer", localStringBuilder.toString());
      }
      return;
    }
    finally
    {
      super.finalize();
    }
  }
  
  public final void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = zzbgo.zza(paramParcel);
    zzbgo.zza(paramParcel, 1, zzc, false);
    zzbgo.zza(paramParcel, 2, zze, paramInt, false);
    zzbgo.zza(paramParcel, 3, zzf);
    zzbgo.zza(paramParcel, 4, zzg, false);
    zzbgo.zza(paramParcel, 1000, zzb);
    zzbgo.zza(paramParcel, i);
    if ((paramInt & 0x1) != 0) {
      close();
    }
  }
  
  @Hide
  public final int zza(int paramInt)
  {
    int j = 0;
    boolean bool;
    if ((paramInt >= 0) && (paramInt < zza)) {
      bool = true;
    } else {
      bool = false;
    }
    zzbq.zza(bool);
    int i;
    for (;;)
    {
      i = j;
      if (j >= zzh.length) {
        break;
      }
      if (paramInt < zzh[j])
      {
        i = j - 1;
        break;
      }
      j += 1;
    }
    paramInt = i;
    if (i == zzh.length) {
      paramInt = i - 1;
    }
    return paramInt;
  }
  
  public final long zza(String paramString, int paramInt1, int paramInt2)
  {
    zza(paramString, paramInt1);
    return zze[paramInt2].getLong(paramInt1, zzd.getInt(paramString));
  }
  
  public final void zza()
  {
    zzd = new Bundle();
    int k = 0;
    int i = 0;
    while (i < zzc.length)
    {
      zzd.putInt(zzc[i], i);
      i += 1;
    }
    zzh = new int[zze.length];
    int j = 0;
    i = k;
    while (i < zze.length)
    {
      zzh[i] = j;
      k = zze[i].getStartPosition();
      j += zze[i].getNumRows() - (j - k);
      i += 1;
    }
    zza = j;
  }
  
  public final void zza(String paramString, int paramInt1, int paramInt2, CharArrayBuffer paramCharArrayBuffer)
  {
    zza(paramString, paramInt1);
    zze[paramInt2].copyStringToBuffer(paramInt1, zzd.getInt(paramString), paramCharArrayBuffer);
  }
  
  public final boolean zza(String paramString)
  {
    return zzd.containsKey(paramString);
  }
  
  public final int zzb()
  {
    return zzf;
  }
  
  public final int zzb(String paramString, int paramInt1, int paramInt2)
  {
    zza(paramString, paramInt1);
    return zze[paramInt2].getInt(paramInt1, zzd.getInt(paramString));
  }
  
  @Hide
  public final Bundle zzc()
  {
    return zzg;
  }
  
  public final String zzc(String paramString, int paramInt1, int paramInt2)
  {
    zza(paramString, paramInt1);
    return zze[paramInt2].getString(paramInt1, zzd.getInt(paramString));
  }
  
  public final int zzd()
  {
    return zza;
  }
  
  public final boolean zzd(String paramString, int paramInt1, int paramInt2)
  {
    zza(paramString, paramInt1);
    return Long.valueOf(zze[paramInt2].getLong(paramInt1, zzd.getInt(paramString))).longValue() == 1L;
  }
  
  public final float zze(String paramString, int paramInt1, int paramInt2)
  {
    zza(paramString, paramInt1);
    return zze[paramInt2].getFloat(paramInt1, zzd.getInt(paramString));
  }
  
  public final boolean zze()
  {
    try
    {
      boolean bool = zzi;
      return bool;
    }
    finally {}
  }
  
  public final byte[] zzf(String paramString, int paramInt1, int paramInt2)
  {
    zza(paramString, paramInt1);
    return zze[paramInt2].getBlob(paramInt1, zzd.getInt(paramString));
  }
  
  public final boolean zzg(String paramString, int paramInt1, int paramInt2)
  {
    zza(paramString, paramInt1);
    return zze[paramInt2].isNull(paramInt1, zzd.getInt(paramString));
  }
  
  public static class zza
  {
    private final String[] zza;
    private final ArrayList<HashMap<String, Object>> zzb;
    private final String zzc;
    private final HashMap<Object, Integer> zzd;
    private boolean zze;
    private String zzf;
    
    private zza(String[] paramArrayOfString, String paramString)
    {
      zza = ((String[])zzbq.zza(paramArrayOfString));
      zzb = new ArrayList();
      zzc = paramString;
      zzd = new HashMap();
      zze = false;
      zzf = null;
    }
    
    public zza zza(ContentValues paramContentValues)
    {
      zzc.zza(paramContentValues);
      HashMap localHashMap = new HashMap(paramContentValues.size());
      paramContentValues = paramContentValues.valueSet().iterator();
      while (paramContentValues.hasNext())
      {
        Map.Entry localEntry = (Map.Entry)paramContentValues.next();
        localHashMap.put((String)localEntry.getKey(), localEntry.getValue());
      }
      return zza(localHashMap);
    }
    
    public zza zza(HashMap<String, Object> paramHashMap)
    {
      zzc.zza(paramHashMap);
      if (zzc == null) {}
      Integer localInteger;
      for (;;)
      {
        i = -1;
        break label78;
        Object localObject = paramHashMap.get(zzc);
        if (localObject != null)
        {
          localInteger = (Integer)zzd.get(localObject);
          if (localInteger != null) {
            break;
          }
          zzd.put(localObject, Integer.valueOf(zzb.size()));
        }
      }
      int i = localInteger.intValue();
      label78:
      if (i == -1)
      {
        zzb.add(paramHashMap);
      }
      else
      {
        zzb.remove(i);
        zzb.add(i, paramHashMap);
      }
      zze = false;
      return this;
    }
    
    public final DataHolder zza(int paramInt)
    {
      return new DataHolder(this, 0, null, null);
    }
  }
  
  public static final class zzb
    extends RuntimeException
  {
    public zzb(String paramString)
    {
      super();
    }
  }
}

package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmField;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000P\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\000\n\002\020\t\n\000\n\002\020\b\n\002\b\n\n\002\020\002\n\002\b\006\n\002\020\013\n\000\n\002\020\000\n\002\b\004\n\002\020\006\n\002\b\004\n\002\020\016\n\002\b\005\b\b\030\000 -2\0020\001:\001-B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B#\022\f\020\005\032\b\022\004\022\0020\0070\006\022\006\020\b\032\0020\t\022\006\020\n\032\0020\013¢\006\002\020\fJ\006\020\025\032\0020\026J\017\020\027\032\b\022\004\022\0020\0070\006HÆ\003J\t\020\030\032\0020\tHÆ\003J\t\020\031\032\0020\013HÆ\003J-\020\032\032\0020\0002\016\b\002\020\005\032\b\022\004\022\0020\0070\0062\b\b\002\020\b\032\0020\t2\b\b\002\020\n\032\0020\013HÆ\001J\b\020\033\032\0020\013H\026J\023\020\034\032\0020\0352\b\020\036\032\004\030\0010\037HÖ\003J\020\020 \032\004\030\0010\0072\006\020!\032\0020\013J\030\020\"\032\004\030\0010\0072\006\020#\032\0020$2\006\020%\032\0020\013J\t\020&\032\0020\013HÖ\001J\f\020'\032\b\022\004\022\0020\0070\006J\t\020(\032\0020)HÖ\001J\030\020*\032\0020\0262\006\020+\032\0020\0032\006\020,\032\0020\013H\026R \020\005\032\b\022\004\022\0020\0070\006X\016¢\006\016\n\000\032\004\b\r\020\016\"\004\b\017\020\020R\021\020\b\032\0020\t¢\006\b\n\000\032\004\b\021\020\022R\021\020\n\032\0020\013¢\006\b\n\000\032\004\b\023\020\024¨\006."}, d2={"Lcom/byimplication/sakay/model/CongestionModel;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "data", "", "Lcom/byimplication/sakay/model/CongestionItem;", "lastUpdated", "", "status", "", "(Ljava/util/List;JI)V", "getData", "()Ljava/util/List;", "setData", "(Ljava/util/List;)V", "getLastUpdated", "()J", "getStatus", "()I", "clampData", "", "component1", "component2", "component3", "copy", "describeContents", "equals", "", "other", "", "getNearestDataOn", "chosenMod", "getNextDifferentData", "mean", "", "mod", "hashCode", "sortData", "toString", "", "writeToParcel", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class CongestionModel
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<CongestionModel> CREATOR = (Parcelable.Creator)new CongestionModel.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  public static final int NO_ERROR = 0;
  public static final int NO_INFO_FOR_THIS_STOP = -2;
  public static final int NO_INFO_FOR_THIS_TIME = -1;
  public static final int UPSTREAM_NOT_AVAIL = -3;
  @NotNull
  private List<CongestionItem> data;
  private final long lastUpdated;
  private final int status;
  
  public CongestionModel(@NotNull Parcel paramParcel)
  {
    this((List)localArrayList, paramParcel.readLong(), paramParcel.readInt());
  }
  
  public CongestionModel(@NotNull List<CongestionItem> paramList, long paramLong, int paramInt)
  {
    data = paramList;
    lastUpdated = paramLong;
    status = paramInt;
  }
  
  public final void clampData()
  {
    int j = data.size();
    int i = 0;
    while (i < j)
    {
      if (((CongestionItem)data.get(i)).getMean() != null)
      {
        Double localDouble = ((CongestionItem)data.get(i)).getMean();
        if (localDouble == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.Double");
        }
        d = localDouble.doubleValue();
      }
      else
      {
        d = 0.0D;
      }
      double d = Math.max(Math.min(d, 1.0D), 0.0D);
      ((CongestionItem)data.get(i)).setMean(Double.valueOf(d));
      i += 1;
    }
  }
  
  @NotNull
  public final List<CongestionItem> component1()
  {
    return data;
  }
  
  public final long component2()
  {
    return lastUpdated;
  }
  
  public final int component3()
  {
    return status;
  }
  
  @NotNull
  public final CongestionModel copy(@NotNull List<CongestionItem> paramList, long paramLong, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "data");
    return new CongestionModel(paramList, paramLong, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof CongestionModel))
      {
        paramObject = (CongestionModel)paramObject;
        if (Intrinsics.areEqual(data, data))
        {
          int i;
          if (lastUpdated == lastUpdated) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            if (status == status) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 0) {
              return true;
            }
          }
        }
      }
      return false;
    }
    return true;
  }
  
  @NotNull
  public final List<CongestionItem> getData()
  {
    return data;
  }
  
  public final long getLastUpdated()
  {
    return lastUpdated;
  }
  
  @Nullable
  public final CongestionItem getNearestDataOn(int paramInt)
  {
    int j = data.size();
    int i = 0;
    if (j == 1) {
      return (CongestionItem)data.get(0);
    }
    j = data.size();
    while (i < j - 1)
    {
      CongestionItem localCongestionItem = (CongestionItem)data.get(i);
      Object localObject = data;
      i += 1;
      localObject = (CongestionItem)((List)localObject).get(i);
      if ((localCongestionItem.getMod() != null) && (localCongestionItem.getMod().intValue() >= paramInt)) {
        return localCongestionItem;
      }
      if (((CongestionItem)localObject).getMod() != null)
      {
        Integer localInteger = ((CongestionItem)localObject).getMod();
        if ((localInteger != null) && (localInteger.intValue() == paramInt)) {
          return localObject;
        }
      }
      if ((localCongestionItem.getMod() != null) && (localCongestionItem.getMod().intValue() < paramInt) && (((CongestionItem)localObject).getMod() != null) && (((CongestionItem)localObject).getMod().intValue() > paramInt))
      {
        if (paramInt - localCongestionItem.getMod().intValue() < ((CongestionItem)localObject).getMod().intValue() - paramInt) {
          return localCongestionItem;
        }
        return localObject;
      }
      if (((CongestionItem)localObject).getMod() == null) {
        return localObject;
      }
    }
    return null;
  }
  
  @Nullable
  public final CongestionItem getNextDifferentData(double paramDouble, int paramInt)
  {
    Object localObject1 = (Function0)new Lambda(paramDouble)
    {
      public final int invoke()
      {
        double d1 = $mean;
        double d2 = 4.0F;
        if (d1 < d2) {
          return 0;
        }
        if (($mean >= d2) && ($mean < 8.0F)) {
          return 1;
        }
        return 2;
      }
    };
    Object localObject2 = (Iterable)data;
    Collection localCollection = (Collection)new ArrayList(CollectionsKt.collectionSizeOrDefault((Iterable)localObject2, 10));
    localObject2 = ((Iterable)localObject2).iterator();
    while (((Iterator)localObject2).hasNext())
    {
      CongestionItem localCongestionItem = (CongestionItem)((Iterator)localObject2).next();
      if (localCongestionItem.getMean() != null)
      {
        Double localDouble = localCongestionItem.getMean();
        if (localDouble == null) {
          Intrinsics.throwNpe();
        }
        long l1 = Math.round(localDouble.doubleValue() * 10.0F);
        long l2 = 3;
        int i;
        if ((0L <= l1) && (l2 >= l1))
        {
          i = 0;
        }
        else
        {
          l2 = 7;
          if ((4 <= l1) && (l2 >= l1)) {
            i = 1;
          } else {
            i = 2;
          }
        }
        if ((((Number)((Function0)localObject1).invoke()).intValue() != i) && (localCongestionItem.getMod() != null) && (Intrinsics.compare(paramInt, localCongestionItem.getMod().intValue()) < 0)) {
          return localCongestionItem;
        }
      }
      localCollection.add(Unit.INSTANCE);
    }
    localObject1 = (List)localCollection;
    return null;
  }
  
  public final int getStatus()
  {
    return status;
  }
  
  public int hashCode()
  {
    List localList = data;
    int i;
    if (localList != null) {
      i = localList.hashCode();
    } else {
      i = 0;
    }
    long l = lastUpdated;
    return (i * 31 + (int)(l ^ l >>> 32)) * 31 + status;
  }
  
  public final void setData(@NotNull List<CongestionItem> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "<set-?>");
    data = paramList;
  }
  
  @NotNull
  public final List<CongestionItem> sortData()
  {
    Comparator local1 = new Comparator()
    {
      public int compare(@NotNull CongestionItem paramAnonymousCongestionItem1, @NotNull CongestionItem paramAnonymousCongestionItem2)
      {
        Intrinsics.checkParameterIsNotNull(paramAnonymousCongestionItem1, "ci1");
        Intrinsics.checkParameterIsNotNull(paramAnonymousCongestionItem2, "ci2");
        if ((paramAnonymousCongestionItem1.getMod() != null) && (paramAnonymousCongestionItem2.getMod() != null)) {
          return paramAnonymousCongestionItem1.getMod().intValue() - paramAnonymousCongestionItem2.getMod().intValue();
        }
        if ((paramAnonymousCongestionItem1.getMod() == null) && (paramAnonymousCongestionItem2.getMod() != null)) {
          return -1;
        }
        return 0;
      }
    };
    data = CollectionsKt.sortedWith((Iterable)data, (Comparator)local1);
    return data;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("CongestionModel(data=");
    localStringBuilder.append(data);
    localStringBuilder.append(", lastUpdated=");
    localStringBuilder.append(lastUpdated);
    localStringBuilder.append(", status=");
    localStringBuilder.append(status);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeTypedList(data);
    paramParcel.writeLong(lastUpdated);
    paramParcel.writeInt(status);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\036\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\n\002\020\b\n\002\b\004\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000R\016\020\006\032\0020\007XT¢\006\002\n\000R\016\020\b\032\0020\007XT¢\006\002\n\000R\016\020\t\032\0020\007XT¢\006\002\n\000R\016\020\n\032\0020\007XT¢\006\002\n\000¨\006\013"}, d2={"Lcom/byimplication/sakay/model/CongestionModel$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/CongestionModel;", "NO_ERROR", "", "NO_INFO_FOR_THIS_STOP", "NO_INFO_FOR_THIS_TIME", "UPSTREAM_NOT_AVAIL", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

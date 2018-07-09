package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000N\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020 \n\002\030\002\n\000\n\002\020\006\n\002\b\004\n\002\020\t\n\002\b\033\n\002\020\b\n\000\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\b\030\000 72\0020\001:\0017B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B_\022\006\020\005\032\0020\006\022\f\020\007\032\b\022\004\022\0020\t0\b\022\006\020\n\032\0020\013\022\006\020\f\032\0020\006\022\006\020\r\032\0020\013\022\f\020\016\032\b\022\004\022\0020\0060\b\022\006\020\017\032\0020\020\022\f\020\021\032\b\022\004\022\0020\0130\b\022\006\020\022\032\0020\006¢\006\002\020\023J\t\020!\032\0020\006HÆ\003J\017\020\"\032\b\022\004\022\0020\t0\bHÆ\003J\t\020#\032\0020\013HÆ\003J\t\020$\032\0020\006HÆ\003J\t\020%\032\0020\013HÆ\003J\017\020&\032\b\022\004\022\0020\0060\bHÆ\003J\t\020'\032\0020\020HÆ\003J\017\020(\032\b\022\004\022\0020\0130\bHÆ\003J\t\020)\032\0020\006HÆ\003Ju\020*\032\0020\0002\b\b\002\020\005\032\0020\0062\016\b\002\020\007\032\b\022\004\022\0020\t0\b2\b\b\002\020\n\032\0020\0132\b\b\002\020\f\032\0020\0062\b\b\002\020\r\032\0020\0132\016\b\002\020\016\032\b\022\004\022\0020\0060\b2\b\b\002\020\017\032\0020\0202\016\b\002\020\021\032\b\022\004\022\0020\0130\b2\b\b\002\020\022\032\0020\006HÆ\001J\b\020+\032\0020,H\026J\023\020-\032\0020.2\b\020/\032\004\030\00100HÖ\003J\t\0201\032\0020,HÖ\001J\t\0202\032\0020\006HÖ\001J\030\0203\032\002042\006\0205\032\0020\0032\006\0206\032\0020,H\026R\021\020\r\032\0020\013¢\006\b\n\000\032\004\b\024\020\025R\021\020\022\032\0020\006¢\006\b\n\000\032\004\b\026\020\027R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\030\020\027R\021\020\017\032\0020\020¢\006\b\n\000\032\004\b\031\020\032R\021\020\f\032\0020\006¢\006\b\n\000\032\004\b\033\020\027R\021\020\n\032\0020\013¢\006\b\n\000\032\004\b\034\020\025R\027\020\016\032\b\022\004\022\0020\0060\b¢\006\b\n\000\032\004\b\035\020\036R\027\020\021\032\b\022\004\022\0020\0130\b¢\006\b\n\000\032\004\b\037\020\036R\027\020\007\032\b\022\004\022\0020\t0\b¢\006\b\n\000\032\004\b \020\036¨\0068"}, d2={"Lcom/byimplication/sakay/model/Incident;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "description", "", "mapping", "", "Lcom/byimplication/sakay/model/Mapping;", "last_updated", "", "incidentType", "created", "likes", "id", "", "loc", "created_by", "(Ljava/lang/String;Ljava/util/List;DLjava/lang/String;DLjava/util/List;JLjava/util/List;Ljava/lang/String;)V", "getCreated", "()D", "getCreated_by", "()Ljava/lang/String;", "getDescription", "getId", "()J", "getIncidentType", "getLast_updated", "getLikes", "()Ljava/util/List;", "getLoc", "getMapping", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class Incident
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<Incident> CREATOR = (Parcelable.Creator)new Incident.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  private final double created;
  @NotNull
  private final String created_by;
  @NotNull
  private final String description;
  private final long id;
  @NotNull
  private final String incidentType;
  private final double last_updated;
  @NotNull
  private final List<String> likes;
  @NotNull
  private final List<Double> loc;
  @NotNull
  private final List<Mapping> mapping;
  
  public Incident(@NotNull Parcel paramParcel)
  {
    this(str1, (List)localObject1, d1, str2, d2, (List)localObject2, l, localList, paramParcel);
  }
  
  public Incident(@NotNull String paramString1, @NotNull List<Mapping> paramList, double paramDouble1, @NotNull String paramString2, double paramDouble2, @NotNull List<String> paramList1, long paramLong, @NotNull List<Double> paramList2, @NotNull String paramString3)
  {
    description = paramString1;
    mapping = paramList;
    last_updated = paramDouble1;
    incidentType = paramString2;
    created = paramDouble2;
    likes = paramList1;
    id = paramLong;
    loc = paramList2;
    created_by = paramString3;
  }
  
  @NotNull
  public final String component1()
  {
    return description;
  }
  
  @NotNull
  public final List<Mapping> component2()
  {
    return mapping;
  }
  
  public final double component3()
  {
    return last_updated;
  }
  
  @NotNull
  public final String component4()
  {
    return incidentType;
  }
  
  public final double component5()
  {
    return created;
  }
  
  @NotNull
  public final List<String> component6()
  {
    return likes;
  }
  
  public final long component7()
  {
    return id;
  }
  
  @NotNull
  public final List<Double> component8()
  {
    return loc;
  }
  
  @NotNull
  public final String component9()
  {
    return created_by;
  }
  
  @NotNull
  public final Incident copy(@NotNull String paramString1, @NotNull List<Mapping> paramList, double paramDouble1, @NotNull String paramString2, double paramDouble2, @NotNull List<String> paramList1, long paramLong, @NotNull List<Double> paramList2, @NotNull String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "description");
    Intrinsics.checkParameterIsNotNull(paramList, "mapping");
    Intrinsics.checkParameterIsNotNull(paramString2, "incidentType");
    Intrinsics.checkParameterIsNotNull(paramList1, "likes");
    Intrinsics.checkParameterIsNotNull(paramList2, "loc");
    Intrinsics.checkParameterIsNotNull(paramString3, "created_by");
    return new Incident(paramString1, paramList, paramDouble1, paramString2, paramDouble2, paramList1, paramLong, paramList2, paramString3);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof Incident))
      {
        paramObject = (Incident)paramObject;
        if ((Intrinsics.areEqual(description, description)) && (Intrinsics.areEqual(mapping, mapping)) && (Double.compare(last_updated, last_updated) == 0) && (Intrinsics.areEqual(incidentType, incidentType)) && (Double.compare(created, created) == 0) && (Intrinsics.areEqual(likes, likes)))
        {
          int i;
          if (id == id) {
            i = 1;
          } else {
            i = 0;
          }
          if ((i != 0) && (Intrinsics.areEqual(loc, loc)) && (Intrinsics.areEqual(created_by, created_by))) {
            return true;
          }
        }
      }
      return false;
    }
    return true;
  }
  
  public final double getCreated()
  {
    return created;
  }
  
  @NotNull
  public final String getCreated_by()
  {
    return created_by;
  }
  
  @NotNull
  public final String getDescription()
  {
    return description;
  }
  
  public final long getId()
  {
    return id;
  }
  
  @NotNull
  public final String getIncidentType()
  {
    return incidentType;
  }
  
  public final double getLast_updated()
  {
    return last_updated;
  }
  
  @NotNull
  public final List<String> getLikes()
  {
    return likes;
  }
  
  @NotNull
  public final List<Double> getLoc()
  {
    return loc;
  }
  
  @NotNull
  public final List<Mapping> getMapping()
  {
    return mapping;
  }
  
  public int hashCode()
  {
    Object localObject = description;
    int i1 = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = mapping;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    long l = Double.doubleToLongBits(last_updated);
    int i2 = (int)(l ^ l >>> 32);
    localObject = incidentType;
    int k;
    if (localObject != null) {
      k = localObject.hashCode();
    } else {
      k = 0;
    }
    l = Double.doubleToLongBits(created);
    int i3 = (int)(l ^ l >>> 32);
    localObject = likes;
    int m;
    if (localObject != null) {
      m = localObject.hashCode();
    } else {
      m = 0;
    }
    l = id;
    int i4 = (int)(l ^ l >>> 32);
    localObject = loc;
    int n;
    if (localObject != null) {
      n = localObject.hashCode();
    } else {
      n = 0;
    }
    localObject = created_by;
    if (localObject != null) {
      i1 = localObject.hashCode();
    }
    return (((((((i * 31 + j) * 31 + i2) * 31 + k) * 31 + i3) * 31 + m) * 31 + i4) * 31 + n) * 31 + i1;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Incident(description=");
    localStringBuilder.append(description);
    localStringBuilder.append(", mapping=");
    localStringBuilder.append(mapping);
    localStringBuilder.append(", last_updated=");
    localStringBuilder.append(last_updated);
    localStringBuilder.append(", incidentType=");
    localStringBuilder.append(incidentType);
    localStringBuilder.append(", created=");
    localStringBuilder.append(created);
    localStringBuilder.append(", likes=");
    localStringBuilder.append(likes);
    localStringBuilder.append(", id=");
    localStringBuilder.append(id);
    localStringBuilder.append(", loc=");
    localStringBuilder.append(loc);
    localStringBuilder.append(", created_by=");
    localStringBuilder.append(created_by);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeString(description);
    paramParcel.writeTypedList(mapping);
    paramParcel.writeDouble(last_updated);
    paramParcel.writeString(incidentType);
    paramParcel.writeDouble(created);
    paramParcel.writeStringList(likes);
    paramParcel.writeLong(id);
    paramParcel.writeList(loc);
    paramParcel.writeString(created_by);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/Incident$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/Incident;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

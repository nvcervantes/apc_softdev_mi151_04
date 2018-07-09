package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0006\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\n\n\002\020\013\n\000\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\002\n\002\b\004\b\b\030\000 \0332\0020\001:\001\033B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B\025\022\006\020\005\032\0020\006\022\006\020\007\032\0020\006¢\006\002\020\bJ\t\020\f\032\0020\006HÆ\003J\t\020\r\032\0020\006HÆ\003J\035\020\016\032\0020\0002\b\b\002\020\005\032\0020\0062\b\b\002\020\007\032\0020\006HÆ\001J\b\020\017\032\0020\006H\026J\023\020\020\032\0020\0212\b\020\022\032\004\030\0010\023HÖ\003J\t\020\024\032\0020\006HÖ\001J\t\020\025\032\0020\026HÖ\001J\030\020\027\032\0020\0302\006\020\031\032\0020\0032\006\020\032\032\0020\006H\026R\021\020\007\032\0020\006¢\006\b\n\000\032\004\b\t\020\nR\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\013\020\n¨\006\034"}, d2={"Lcom/byimplication/sakay/model/Mapping;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "route_index", "", "leg_index", "(II)V", "getLeg_index", "()I", "getRoute_index", "component1", "component2", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class Mapping
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<Mapping> CREATOR = (Parcelable.Creator)new Mapping.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  private final int leg_index;
  private final int route_index;
  
  public Mapping(int paramInt1, int paramInt2)
  {
    route_index = paramInt1;
    leg_index = paramInt2;
  }
  
  public Mapping(@NotNull Parcel paramParcel)
  {
    this(paramParcel.readInt(), paramParcel.readInt());
  }
  
  public final int component1()
  {
    return route_index;
  }
  
  public final int component2()
  {
    return leg_index;
  }
  
  @NotNull
  public final Mapping copy(int paramInt1, int paramInt2)
  {
    return new Mapping(paramInt1, paramInt2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof Mapping))
      {
        paramObject = (Mapping)paramObject;
        int i;
        if (route_index == route_index) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0)
        {
          if (leg_index == leg_index) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0) {
            return true;
          }
        }
      }
      return false;
    }
    return true;
  }
  
  public final int getLeg_index()
  {
    return leg_index;
  }
  
  public final int getRoute_index()
  {
    return route_index;
  }
  
  public int hashCode()
  {
    return route_index * 31 + leg_index;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Mapping(route_index=");
    localStringBuilder.append(route_index);
    localStringBuilder.append(", leg_index=");
    localStringBuilder.append(leg_index);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeInt(route_index);
    paramParcel.writeInt(leg_index);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/Mapping$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/Mapping;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\0006\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020\b\n\002\b\n\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\b\030\000 \0342\0020\001:\001\034B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B\025\022\006\020\005\032\0020\006\022\006\020\007\032\0020\b¢\006\002\020\tJ\t\020\016\032\0020\006HÆ\003J\t\020\017\032\0020\bHÆ\003J\035\020\020\032\0020\0002\b\b\002\020\005\032\0020\0062\b\b\002\020\007\032\0020\bHÆ\001J\b\020\021\032\0020\bH\026J\023\020\022\032\0020\0232\b\020\024\032\004\030\0010\025HÖ\003J\t\020\026\032\0020\bHÖ\001J\t\020\027\032\0020\006HÖ\001J\030\020\030\032\0020\0312\006\020\032\032\0020\0032\006\020\033\032\0020\bH\026R\021\020\007\032\0020\b¢\006\b\n\000\032\004\b\n\020\013R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\f\020\r¨\006\035"}, d2={"Lcom/byimplication/sakay/model/LegGeometry;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "points", "", "length", "", "(Ljava/lang/String;I)V", "getLength", "()I", "getPoints", "()Ljava/lang/String;", "component1", "component2", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class LegGeometry
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<LegGeometry> CREATOR = (Parcelable.Creator)new LegGeometry.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  private final int length;
  @NotNull
  private final String points;
  
  public LegGeometry(@NotNull Parcel paramParcel)
  {
    this(str, paramParcel.readInt());
  }
  
  public LegGeometry(@NotNull String paramString, int paramInt)
  {
    points = paramString;
    length = paramInt;
  }
  
  @NotNull
  public final String component1()
  {
    return points;
  }
  
  public final int component2()
  {
    return length;
  }
  
  @NotNull
  public final LegGeometry copy(@NotNull String paramString, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramString, "points");
    return new LegGeometry(paramString, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof LegGeometry))
      {
        paramObject = (LegGeometry)paramObject;
        if (Intrinsics.areEqual(points, points))
        {
          int i;
          if (length == length) {
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
  
  public final int getLength()
  {
    return length;
  }
  
  @NotNull
  public final String getPoints()
  {
    return points;
  }
  
  public int hashCode()
  {
    String str = points;
    int i;
    if (str != null) {
      i = str.hashCode();
    } else {
      i = 0;
    }
    return i * 31 + length;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("LegGeometry(points=");
    localStringBuilder.append(points);
    localStringBuilder.append(", length=");
    localStringBuilder.append(length);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeString(points);
    paramParcel.writeInt(length);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/LegGeometry$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/LegGeometry;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

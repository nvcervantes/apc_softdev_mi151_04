package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0006\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\f\n\002\020\b\n\000\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\b\030\000 \0362\0020\001:\001\036B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B!\022\006\020\005\032\0020\006\022\b\020\007\032\004\030\0010\006\022\b\020\b\032\004\030\0010\006¢\006\002\020\tJ\t\020\016\032\0020\006HÆ\003J\013\020\017\032\004\030\0010\006HÆ\003J\013\020\020\032\004\030\0010\006HÆ\003J+\020\021\032\0020\0002\b\b\002\020\005\032\0020\0062\n\b\002\020\007\032\004\030\0010\0062\n\b\002\020\b\032\004\030\0010\006HÆ\001J\b\020\022\032\0020\023H\026J\023\020\024\032\0020\0252\b\020\026\032\004\030\0010\027HÖ\003J\t\020\030\032\0020\023HÖ\001J\t\020\031\032\0020\006HÖ\001J\030\020\032\032\0020\0332\006\020\034\032\0020\0032\006\020\035\032\0020\023H\026R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\n\020\013R\023\020\b\032\004\030\0010\006¢\006\b\n\000\032\004\b\f\020\013R\023\020\007\032\004\030\0010\006¢\006\b\n\000\032\004\b\r\020\013¨\006\037"}, d2={"Lcom/byimplication/sakay/model/Alternative;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "id", "", "shortName", "longName", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getId", "()Ljava/lang/String;", "getLongName", "getShortName", "component1", "component2", "component3", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class Alternative
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<Alternative> CREATOR = (Parcelable.Creator)new Alternative.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  @NotNull
  private final String id;
  @Nullable
  private final String longName;
  @Nullable
  private final String shortName;
  
  public Alternative(@NotNull Parcel paramParcel)
  {
    this(str, paramParcel.readString(), paramParcel.readString());
  }
  
  public Alternative(@NotNull String paramString1, @Nullable String paramString2, @Nullable String paramString3)
  {
    id = paramString1;
    shortName = paramString2;
    longName = paramString3;
  }
  
  @NotNull
  public final String component1()
  {
    return id;
  }
  
  @Nullable
  public final String component2()
  {
    return shortName;
  }
  
  @Nullable
  public final String component3()
  {
    return longName;
  }
  
  @NotNull
  public final Alternative copy(@NotNull String paramString1, @Nullable String paramString2, @Nullable String paramString3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "id");
    return new Alternative(paramString1, paramString2, paramString3);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof Alternative))
      {
        paramObject = (Alternative)paramObject;
        if ((Intrinsics.areEqual(id, id)) && (Intrinsics.areEqual(shortName, shortName)) && (Intrinsics.areEqual(longName, longName))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final String getId()
  {
    return id;
  }
  
  @Nullable
  public final String getLongName()
  {
    return longName;
  }
  
  @Nullable
  public final String getShortName()
  {
    return shortName;
  }
  
  public int hashCode()
  {
    String str = id;
    int k = 0;
    int i;
    if (str != null) {
      i = str.hashCode();
    } else {
      i = 0;
    }
    str = shortName;
    int j;
    if (str != null) {
      j = str.hashCode();
    } else {
      j = 0;
    }
    str = longName;
    if (str != null) {
      k = str.hashCode();
    }
    return (i * 31 + j) * 31 + k;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Alternative(id=");
    localStringBuilder.append(id);
    localStringBuilder.append(", shortName=");
    localStringBuilder.append(shortName);
    localStringBuilder.append(", longName=");
    localStringBuilder.append(longName);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeString(id);
    paramParcel.writeString(shortName);
    paramParcel.writeString(longName);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/Alternative$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/Alternative;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

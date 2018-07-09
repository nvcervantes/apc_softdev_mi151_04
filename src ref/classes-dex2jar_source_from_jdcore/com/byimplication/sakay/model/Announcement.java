package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000<\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\t\n\000\n\002\020\016\n\002\b\020\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\b\030\000 $2\0020\001:\001$B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B%\022\006\020\005\032\0020\006\022\006\020\007\032\0020\b\022\006\020\t\032\0020\n\022\006\020\013\032\0020\n¢\006\002\020\fJ\t\020\024\032\0020\006HÆ\003J\t\020\025\032\0020\bHÆ\003J\t\020\026\032\0020\nHÆ\003J\t\020\027\032\0020\nHÆ\003J1\020\030\032\0020\0002\b\b\002\020\005\032\0020\0062\b\b\002\020\007\032\0020\b2\b\b\002\020\t\032\0020\n2\b\b\002\020\013\032\0020\nHÆ\001J\b\020\031\032\0020\006H\026J\023\020\032\032\0020\0332\b\020\034\032\004\030\0010\035HÖ\003J\t\020\036\032\0020\006HÖ\001J\t\020\037\032\0020\nHÖ\001J\030\020 \032\0020!2\006\020\"\032\0020\0032\006\020#\032\0020\006H\026R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\r\020\016R\021\020\013\032\0020\n¢\006\b\n\000\032\004\b\017\020\020R\021\020\007\032\0020\b¢\006\b\n\000\032\004\b\021\020\022R\021\020\t\032\0020\n¢\006\b\n\000\032\004\b\023\020\020¨\006%"}, d2={"Lcom/byimplication/sakay/model/Announcement;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "id", "", "timestamp", "", "title", "", "message", "(IJLjava/lang/String;Ljava/lang/String;)V", "getId", "()I", "getMessage", "()Ljava/lang/String;", "getTimestamp", "()J", "getTitle", "component1", "component2", "component3", "component4", "copy", "describeContents", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class Announcement
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<Announcement> CREATOR = (Parcelable.Creator)new Announcement.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  private final int id;
  @NotNull
  private final String message;
  private final long timestamp;
  @NotNull
  private final String title;
  
  public Announcement(int paramInt, long paramLong, @NotNull String paramString1, @NotNull String paramString2)
  {
    id = paramInt;
    timestamp = paramLong;
    title = paramString1;
    message = paramString2;
  }
  
  public Announcement(@NotNull Parcel paramParcel)
  {
    this(i, l, str, paramParcel);
  }
  
  public final int component1()
  {
    return id;
  }
  
  public final long component2()
  {
    return timestamp;
  }
  
  @NotNull
  public final String component3()
  {
    return title;
  }
  
  @NotNull
  public final String component4()
  {
    return message;
  }
  
  @NotNull
  public final Announcement copy(int paramInt, long paramLong, @NotNull String paramString1, @NotNull String paramString2)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "title");
    Intrinsics.checkParameterIsNotNull(paramString2, "message");
    return new Announcement(paramInt, paramLong, paramString1, paramString2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof Announcement))
      {
        paramObject = (Announcement)paramObject;
        int i;
        if (id == id) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0)
        {
          if (timestamp == timestamp) {
            i = 1;
          } else {
            i = 0;
          }
          if ((i != 0) && (Intrinsics.areEqual(title, title)) && (Intrinsics.areEqual(message, message))) {
            return true;
          }
        }
      }
      return false;
    }
    return true;
  }
  
  public final int getId()
  {
    return id;
  }
  
  @NotNull
  public final String getMessage()
  {
    return message;
  }
  
  public final long getTimestamp()
  {
    return timestamp;
  }
  
  @NotNull
  public final String getTitle()
  {
    return title;
  }
  
  public int hashCode()
  {
    int k = id;
    long l = timestamp;
    int m = (int)(l ^ l >>> 32);
    String str = title;
    int j = 0;
    int i;
    if (str != null) {
      i = str.hashCode();
    } else {
      i = 0;
    }
    str = message;
    if (str != null) {
      j = str.hashCode();
    }
    return ((k * 31 + m) * 31 + i) * 31 + j;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Announcement(id=");
    localStringBuilder.append(id);
    localStringBuilder.append(", timestamp=");
    localStringBuilder.append(timestamp);
    localStringBuilder.append(", title=");
    localStringBuilder.append(title);
    localStringBuilder.append(", message=");
    localStringBuilder.append(message);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeInt(id);
    paramParcel.writeLong(timestamp);
    paramParcel.writeString(title);
    paramParcel.writeString(message);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/Announcement$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/Announcement;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000@\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\002\b\006\n\002\020\b\n\000\n\002\020\013\n\000\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\020\002\n\002\b\004\b\b\030\000 \0322\0020\001:\001\032B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B\023\022\f\020\005\032\b\022\004\022\0020\0070\006¢\006\002\020\bJ\017\020\013\032\b\022\004\022\0020\0070\006HÆ\003J\031\020\f\032\0020\0002\016\b\002\020\005\032\b\022\004\022\0020\0070\006HÆ\001J\b\020\r\032\0020\016H\026J\023\020\017\032\0020\0202\b\020\021\032\004\030\0010\022HÖ\003J\t\020\023\032\0020\016HÖ\001J\t\020\024\032\0020\025HÖ\001J\030\020\026\032\0020\0272\006\020\030\032\0020\0032\006\020\031\032\0020\016H\026R\027\020\005\032\b\022\004\022\0020\0070\006¢\006\b\n\000\032\004\b\t\020\n¨\006\033"}, d2={"Lcom/byimplication/sakay/model/AnnouncementList;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "announcements", "", "Lcom/byimplication/sakay/model/Announcement;", "(Ljava/util/List;)V", "getAnnouncements", "()Ljava/util/List;", "component1", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class AnnouncementList
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<AnnouncementList> CREATOR = (Parcelable.Creator)new AnnouncementList.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  @NotNull
  private final List<Announcement> announcements;
  
  public AnnouncementList(@NotNull Parcel paramParcel)
  {
    this((List)paramParcel);
  }
  
  public AnnouncementList(@NotNull List<Announcement> paramList)
  {
    announcements = paramList;
  }
  
  @NotNull
  public final List<Announcement> component1()
  {
    return announcements;
  }
  
  @NotNull
  public final AnnouncementList copy(@NotNull List<Announcement> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "announcements");
    return new AnnouncementList(paramList);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof AnnouncementList))
      {
        paramObject = (AnnouncementList)paramObject;
        if (Intrinsics.areEqual(announcements, announcements)) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final List<Announcement> getAnnouncements()
  {
    return announcements;
  }
  
  public int hashCode()
  {
    List localList = announcements;
    if (localList != null) {
      return localList.hashCode();
    }
    return 0;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("AnnouncementList(announcements=");
    localStringBuilder.append(announcements);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeTypedList(announcements);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/AnnouncementList$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/AnnouncementList;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

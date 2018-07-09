package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0008\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\002\b\002\n\002\020\013\n\002\b\023\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\b\030\000 &2\0020\001:\001&B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B-\022\b\020\005\032\004\030\0010\006\022\b\020\007\032\004\030\0010\b\022\b\020\t\032\004\030\0010\b\022\b\020\n\032\004\030\0010\013¢\006\002\020\fJ\020\020\026\032\004\030\0010\006HÆ\003¢\006\002\020\016J\013\020\027\032\004\030\0010\bHÆ\003J\013\020\030\032\004\030\0010\bHÆ\003J\020\020\031\032\004\030\0010\013HÆ\003¢\006\002\020\024J>\020\032\032\0020\0002\n\b\002\020\005\032\004\030\0010\0062\n\b\002\020\007\032\004\030\0010\b2\n\b\002\020\t\032\004\030\0010\b2\n\b\002\020\n\032\004\030\0010\013HÆ\001¢\006\002\020\033J\b\020\034\032\0020\006H\026J\023\020\035\032\0020\0132\b\020\036\032\004\030\0010\037HÖ\003J\t\020 \032\0020\006HÖ\001J\t\020!\032\0020\bHÖ\001J\030\020\"\032\0020#2\006\020$\032\0020\0032\006\020%\032\0020\006H\026R\025\020\005\032\004\030\0010\006¢\006\n\n\002\020\017\032\004\b\r\020\016R\023\020\t\032\004\030\0010\b¢\006\b\n\000\032\004\b\020\020\021R\023\020\007\032\004\030\0010\b¢\006\b\n\000\032\004\b\022\020\021R\025\020\n\032\004\030\0010\013¢\006\n\n\002\020\025\032\004\b\023\020\024¨\006'"}, d2={"Lcom/byimplication/sakay/model/PlanError;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "id", "", "msg", "", "message", "noPath", "", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)V", "getId", "()Ljava/lang/Integer;", "Ljava/lang/Integer;", "getMessage", "()Ljava/lang/String;", "getMsg", "getNoPath", "()Ljava/lang/Boolean;", "Ljava/lang/Boolean;", "component1", "component2", "component3", "component4", "copy", "(Ljava/lang/Integer;Ljava/lang/String;Ljava/lang/String;Ljava/lang/Boolean;)Lcom/byimplication/sakay/model/PlanError;", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class PlanError
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<PlanError> CREATOR = (Parcelable.Creator)new PlanError.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  @Nullable
  private final Integer id;
  @Nullable
  private final String message;
  @Nullable
  private final String msg;
  @Nullable
  private final Boolean noPath;
  
  public PlanError(@NotNull Parcel paramParcel)
  {
    this((Integer)paramParcel.readValue(Integer.TYPE.getClassLoader()), paramParcel.readString(), paramParcel.readString(), (Boolean)paramParcel.readValue(Boolean.TYPE.getClassLoader()));
  }
  
  public PlanError(@Nullable Integer paramInteger, @Nullable String paramString1, @Nullable String paramString2, @Nullable Boolean paramBoolean)
  {
    id = paramInteger;
    msg = paramString1;
    message = paramString2;
    noPath = paramBoolean;
  }
  
  @Nullable
  public final Integer component1()
  {
    return id;
  }
  
  @Nullable
  public final String component2()
  {
    return msg;
  }
  
  @Nullable
  public final String component3()
  {
    return message;
  }
  
  @Nullable
  public final Boolean component4()
  {
    return noPath;
  }
  
  @NotNull
  public final PlanError copy(@Nullable Integer paramInteger, @Nullable String paramString1, @Nullable String paramString2, @Nullable Boolean paramBoolean)
  {
    return new PlanError(paramInteger, paramString1, paramString2, paramBoolean);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof PlanError))
      {
        paramObject = (PlanError)paramObject;
        if ((Intrinsics.areEqual(id, id)) && (Intrinsics.areEqual(msg, msg)) && (Intrinsics.areEqual(message, message)) && (Intrinsics.areEqual(noPath, noPath))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @Nullable
  public final Integer getId()
  {
    return id;
  }
  
  @Nullable
  public final String getMessage()
  {
    return message;
  }
  
  @Nullable
  public final String getMsg()
  {
    return msg;
  }
  
  @Nullable
  public final Boolean getNoPath()
  {
    return noPath;
  }
  
  public int hashCode()
  {
    Object localObject = id;
    int m = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = msg;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = message;
    int k;
    if (localObject != null) {
      k = localObject.hashCode();
    } else {
      k = 0;
    }
    localObject = noPath;
    if (localObject != null) {
      m = localObject.hashCode();
    }
    return ((i * 31 + j) * 31 + k) * 31 + m;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PlanError(id=");
    localStringBuilder.append(id);
    localStringBuilder.append(", msg=");
    localStringBuilder.append(msg);
    localStringBuilder.append(", message=");
    localStringBuilder.append(message);
    localStringBuilder.append(", noPath=");
    localStringBuilder.append(noPath);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    Intrinsics.checkParameterIsNotNull(paramParcel, "dest");
    paramParcel.writeValue(id);
    paramParcel.writeString(msg);
    paramParcel.writeString(message);
    paramParcel.writeValue(noPath);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002R\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\006"}, d2={"Lcom/byimplication/sakay/model/PlanError$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/PlanError;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
  }
}

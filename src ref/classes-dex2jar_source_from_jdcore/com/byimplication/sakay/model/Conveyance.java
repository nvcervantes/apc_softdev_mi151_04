package com.byimplication.sakay.model;

import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0006\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\t\n\002\020\b\n\000\n\002\020\013\n\000\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\b\030\000 \0332\0020\001:\001\033B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B\027\022\006\020\005\032\0020\006\022\b\020\007\032\004\030\0010\006¢\006\002\020\bJ\t\020\f\032\0020\006HÆ\003J\013\020\r\032\004\030\0010\006HÆ\003J\037\020\016\032\0020\0002\b\b\002\020\005\032\0020\0062\n\b\002\020\007\032\004\030\0010\006HÆ\001J\b\020\017\032\0020\020H\026J\023\020\021\032\0020\0222\b\020\023\032\004\030\0010\024HÖ\003J\t\020\025\032\0020\020HÖ\001J\t\020\026\032\0020\006HÖ\001J\032\020\027\032\0020\0302\b\020\031\032\004\030\0010\0032\006\020\032\032\0020\020H\026R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\t\020\nR\023\020\007\032\004\030\0010\006¢\006\b\n\000\032\004\b\013\020\n¨\006\034"}, d2={"Lcom/byimplication/sakay/model/Conveyance;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "primary", "", "secondary", "(Ljava/lang/String;Ljava/lang/String;)V", "getPrimary", "()Ljava/lang/String;", "getSecondary", "component1", "component2", "copy", "describeContents", "", "equals", "", "other", "", "hashCode", "toString", "writeToParcel", "", "out", "p1", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class Conveyance
  implements Parcelable
{
  @NotNull
  public static final String BUS = "BUS";
  @JvmField
  @NotNull
  public static final Parcelable.Creator<Conveyance> CREATOR = (Parcelable.Creator)new Conveyance.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  @NotNull
  public static final String EJEEP = "EJEEP";
  @NotNull
  public static final String FERRY = "FERRY";
  @NotNull
  public static final String JEEP = "JEEP";
  @NotNull
  public static final String LRT1 = "LRT1";
  @NotNull
  public static final String LRT2 = "LRT2";
  @NotNull
  public static final String MRT3 = "MRT3";
  @NotNull
  public static final String P2P = "P2P";
  @NotNull
  public static final String PNR = "PNR";
  @NotNull
  public static final String RAIL = "RAIL";
  @NotNull
  public static final String TAXI = "TAXI";
  @NotNull
  public static final String TRICYCLE = "TRICYCLE";
  @NotNull
  public static final String UV = "UV";
  @NotNull
  public static final String WALK = "WALK";
  @NotNull
  private final String primary;
  @Nullable
  private final String secondary;
  
  public Conveyance(@NotNull Parcel paramParcel)
  {
    this(str, paramParcel.readString());
  }
  
  public Conveyance(@NotNull String paramString1, @Nullable String paramString2)
  {
    primary = paramString1;
    secondary = paramString2;
  }
  
  @NotNull
  public final String component1()
  {
    return primary;
  }
  
  @Nullable
  public final String component2()
  {
    return secondary;
  }
  
  @NotNull
  public final Conveyance copy(@NotNull String paramString1, @Nullable String paramString2)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "primary");
    return new Conveyance(paramString1, paramString2);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof Conveyance))
      {
        paramObject = (Conveyance)paramObject;
        if ((Intrinsics.areEqual(primary, primary)) && (Intrinsics.areEqual(secondary, secondary))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final String getPrimary()
  {
    return primary;
  }
  
  @Nullable
  public final String getSecondary()
  {
    return secondary;
  }
  
  public int hashCode()
  {
    String str = primary;
    int j = 0;
    int i;
    if (str != null) {
      i = str.hashCode();
    } else {
      i = 0;
    }
    str = secondary;
    if (str != null) {
      j = str.hashCode();
    }
    return i * 31 + j;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Conveyance(primary=");
    localStringBuilder.append(primary);
    localStringBuilder.append(", secondary=");
    localStringBuilder.append(secondary);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@Nullable Parcel paramParcel, int paramInt)
  {
    if (paramParcel != null) {
      paramParcel.writeString(primary);
    }
    if (paramParcel != null) {
      paramParcel.writeString(secondary);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\002\030\002\n\002\b\016\n\002\020\b\n\002\b\004\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\025\032\0020\0262\006\020\027\032\0020\007J\016\020\030\032\0020\0262\006\020\027\032\0020\007J\016\020\031\032\0020\0262\006\020\027\032\0020\007R\016\020\003\032\0020\004XT¢\006\002\n\000R\026\020\005\032\b\022\004\022\0020\0070\0068\006X\004¢\006\002\n\000R\016\020\b\032\0020\004XT¢\006\002\n\000R\016\020\t\032\0020\004XT¢\006\002\n\000R\016\020\n\032\0020\004XT¢\006\002\n\000R\016\020\013\032\0020\004XT¢\006\002\n\000R\016\020\f\032\0020\004XT¢\006\002\n\000R\016\020\r\032\0020\004XT¢\006\002\n\000R\016\020\016\032\0020\004XT¢\006\002\n\000R\016\020\017\032\0020\004XT¢\006\002\n\000R\016\020\020\032\0020\004XT¢\006\002\n\000R\016\020\021\032\0020\004XT¢\006\002\n\000R\016\020\022\032\0020\004XT¢\006\002\n\000R\016\020\023\032\0020\004XT¢\006\002\n\000R\016\020\024\032\0020\004XT¢\006\002\n\000¨\006\032"}, d2={"Lcom/byimplication/sakay/model/Conveyance$Companion;", "", "()V", "BUS", "", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/Conveyance;", "EJEEP", "FERRY", "JEEP", "LRT1", "LRT2", "MRT3", "P2P", "PNR", "RAIL", "TAXI", "TRICYCLE", "UV", "WALK", "getIconResource", "", "conveyance", "translateConveyanceRes", "translateConveyanceResShort", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    public final int getIconResource(@NotNull Conveyance paramConveyance)
    {
      Intrinsics.checkParameterIsNotNull(paramConveyance, "conveyance");
      String str = paramConveyance.getPrimary();
      int i = str.hashCode();
      int j = 2131558421;
      switch (i)
      {
      default: 
        return 2131558421;
      case 66783482: 
        i = j;
        if (str.equals("FERRY")) {
          return 2131558425;
        }
        break;
      case 65996011: 
        i = j;
        if (str.equals("EJEEP")) {
          return 2131558423;
        }
        break;
      case 2656713: 
        i = j;
        if (str.equals("WALK")) {
          return 2131558434;
        }
        break;
      case 2507666: 
        i = j;
        if (str.equals("RAIL")) {
          return 2131558431;
        }
        break;
      case 2273062: 
        i = j;
        if (str.equals("JEEP")) {
          return 2131558427;
        }
        break;
      case 66144: 
        i = j;
        if (str.equals("BUS"))
        {
          paramConveyance = paramConveyance.getSecondary();
          if (paramConveyance == null) {
            return 2131558421;
          }
          if (paramConveyance.hashCode() != 78510) {
            return 2131558421;
          }
          i = j;
          if (paramConveyance.equals("P2P")) {
            return 2131558429;
          }
        }
        break;
      case 2721: 
        i = j;
        if (str.equals("UV")) {
          i = 2131558433;
        }
        break;
      }
      return i;
    }
    
    public final int translateConveyanceRes(@NotNull Conveyance paramConveyance)
    {
      Intrinsics.checkParameterIsNotNull(paramConveyance, "conveyance");
      String str = paramConveyance.getPrimary();
      switch (str.hashCode())
      {
      default: 
        break;
      case 66783482: 
        if (str.equals("FERRY")) {
          return 2131689659;
        }
        break;
      case 65996011: 
        if (str.equals("EJEEP")) {
          return 2131689657;
        }
        break;
      case 2656713: 
        if (str.equals("WALK")) {
          return 2131689683;
        }
        break;
      case 2567710: 
        if (str.equals("TAXI")) {
          return 2131689675;
        }
        break;
      case 2507666: 
        if (str.equals("RAIL"))
        {
          paramConveyance = paramConveyance.getSecondary();
          if (paramConveyance != null) {
            switch (paramConveyance.hashCode())
            {
            default: 
              break;
            case 2375364: 
              if (paramConveyance.equals("MRT3")) {
                return 2131689666;
              }
              break;
            case 2345572: 
              if (paramConveyance.equals("LRT2")) {
                return 2131689664;
              }
              break;
            case 2345571: 
              if (paramConveyance.equals("LRT1")) {
                return 2131689662;
              }
              break;
            case 79380: 
              if (paramConveyance.equals("PNR")) {
                return 2131689671;
              }
              break;
            }
          }
          return 2131689676;
        }
        break;
      case 2273062: 
        if (str.equals("JEEP")) {
          return 2131689660;
        }
        break;
      case 66144: 
        if (str.equals("BUS"))
        {
          paramConveyance = paramConveyance.getSecondary();
          if ((paramConveyance != null) && (paramConveyance.hashCode() == 78510) && (paramConveyance.equals("P2P"))) {
            return 2131689669;
          }
          return 2131689653;
        }
        break;
      case 2721: 
        if (str.equals("UV")) {
          return 2131689682;
        }
        break;
      case -2012407269: 
        if (str.equals("TRICYCLE")) {
          return 2131689678;
        }
        break;
      }
      return 2131689680;
    }
    
    public final int translateConveyanceResShort(@NotNull Conveyance paramConveyance)
    {
      Intrinsics.checkParameterIsNotNull(paramConveyance, "conveyance");
      String str = paramConveyance.getPrimary();
      switch (str.hashCode())
      {
      default: 
        break;
      case 66783482: 
        if (str.equals("FERRY")) {
          return 2131689659;
        }
        break;
      case 65996011: 
        if (str.equals("EJEEP")) {
          return 2131689658;
        }
        break;
      case 2656713: 
        if (str.equals("WALK")) {
          return 2131689683;
        }
        break;
      case 2567710: 
        if (str.equals("TAXI")) {
          return 2131689675;
        }
        break;
      case 2507666: 
        if (str.equals("RAIL"))
        {
          paramConveyance = paramConveyance.getSecondary();
          if (paramConveyance != null) {
            switch (paramConveyance.hashCode())
            {
            default: 
              break;
            case 2375364: 
              if (paramConveyance.equals("MRT3")) {
                return 2131689667;
              }
              break;
            case 2345572: 
              if (paramConveyance.equals("LRT2")) {
                return 2131689665;
              }
              break;
            case 2345571: 
              if (paramConveyance.equals("LRT1")) {
                return 2131689663;
              }
              break;
            case 79380: 
              if (paramConveyance.equals("PNR")) {
                return 2131689671;
              }
              break;
            }
          }
          return 2131689677;
        }
        break;
      case 2273062: 
        if (str.equals("JEEP")) {
          return 2131689661;
        }
        break;
      case 66144: 
        if (str.equals("BUS"))
        {
          paramConveyance = paramConveyance.getSecondary();
          if ((paramConveyance != null) && (paramConveyance.hashCode() == 78510) && (paramConveyance.equals("P2P"))) {
            return 2131689670;
          }
          return 2131689653;
        }
        break;
      case 2721: 
        if (str.equals("UV")) {
          return 2131689682;
        }
        break;
      case -2012407269: 
        if (str.equals("TRICYCLE")) {
          return 2131689679;
        }
        break;
      }
      return 2131689681;
    }
  }
}

package com.byimplication.sakay.model;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmField;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000B\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\020 \n\002\020\b\n\002\b\003\n\002\020\013\n\000\n\002\020\006\n\002\b!\n\002\020\000\n\002\b\003\n\002\020\002\n\002\b\004\b\b\030\000 82\0020\001:\0018B\017\b\026\022\006\020\002\032\0020\003¢\006\002\020\004B[\022\006\020\005\032\0020\006\022\f\020\007\032\b\022\004\022\0020\t0\b\022\006\020\n\032\0020\006\022\006\020\013\032\0020\006\022\006\020\f\032\0020\r\022\006\020\016\032\0020\017\022\006\020\020\032\0020\017\022\006\020\021\032\0020\r\022\006\020\022\032\0020\r\022\006\020\023\032\0020\017¢\006\002\020\024J\t\020#\032\0020\006HÆ\003J\t\020$\032\0020\017HÆ\003J\017\020%\032\b\022\004\022\0020\t0\bHÆ\003J\t\020&\032\0020\006HÆ\003J\t\020'\032\0020\006HÆ\003J\t\020(\032\0020\rHÆ\003J\t\020)\032\0020\017HÆ\003J\t\020*\032\0020\017HÆ\003J\t\020+\032\0020\rHÆ\003J\t\020,\032\0020\rHÆ\003Js\020-\032\0020\0002\b\b\002\020\005\032\0020\0062\016\b\002\020\007\032\b\022\004\022\0020\t0\b2\b\b\002\020\n\032\0020\0062\b\b\002\020\013\032\0020\0062\b\b\002\020\f\032\0020\r2\b\b\002\020\016\032\0020\0172\b\b\002\020\020\032\0020\0172\b\b\002\020\021\032\0020\r2\b\b\002\020\022\032\0020\r2\b\b\002\020\023\032\0020\017HÆ\001J\b\020.\032\0020\tH\026J\023\020/\032\0020\r2\b\0200\032\004\030\00101HÖ\003J\t\0202\032\0020\tHÖ\001J\t\0203\032\0020\006HÖ\001J\030\0204\032\002052\006\0206\032\0020\0032\006\0207\032\0020\tH\026R\021\020\013\032\0020\006¢\006\b\n\000\032\004\b\025\020\026R\021\020\022\032\0020\r¢\006\b\n\000\032\004\b\027\020\030R\021\020\f\032\0020\r¢\006\b\n\000\032\004\b\031\020\030R\021\020\023\032\0020\017¢\006\b\n\000\032\004\b\032\020\033R\027\020\007\032\b\022\004\022\0020\t0\b¢\006\b\n\000\032\004\b\034\020\035R\021\020\020\032\0020\017¢\006\b\n\000\032\004\b\036\020\033R\021\020\016\032\0020\017¢\006\b\n\000\032\004\b\037\020\033R\021\020\n\032\0020\006¢\006\b\n\000\032\004\b \020\026R\021\020\021\032\0020\r¢\006\b\n\000\032\004\b!\020\030R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\"\020\026¨\0069"}, d2={"Lcom/byimplication/sakay/model/TripStep;", "Landroid/os/Parcelable;", "source", "Landroid/os/Parcel;", "(Landroid/os/Parcel;)V", "streetName", "", "elevation", "", "", "relativeDirection", "absoluteDirection", "bogusName", "", "lon", "", "lat", "stayOn", "area", "distance", "(Ljava/lang/String;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZDDZZD)V", "getAbsoluteDirection", "()Ljava/lang/String;", "getArea", "()Z", "getBogusName", "getDistance", "()D", "getElevation", "()Ljava/util/List;", "getLat", "getLon", "getRelativeDirection", "getStayOn", "getStreetName", "component1", "component10", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "describeContents", "equals", "other", "", "hashCode", "toString", "writeToParcel", "", "dest", "flags", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class TripStep
  implements Parcelable
{
  @JvmField
  @NotNull
  public static final Parcelable.Creator<TripStep> CREATOR = (Parcelable.Creator)new TripStep.Companion.CREATOR.1();
  public static final Companion Companion = new Companion(null);
  @NotNull
  private final String absoluteDirection;
  private final boolean area;
  private final boolean bogusName;
  private final double distance;
  @NotNull
  private final List<Integer> elevation;
  private final double lat;
  private final double lon;
  @NotNull
  private final String relativeDirection;
  private final boolean stayOn;
  @NotNull
  private final String streetName;
  
  public TripStep(@NotNull Parcel paramParcel)
  {
    this(str1, localList, str2, str3, bool1, d1, d2, bool2, bool3, paramParcel.readDouble());
  }
  
  public TripStep(@NotNull String paramString1, @NotNull List<Integer> paramList, @NotNull String paramString2, @NotNull String paramString3, boolean paramBoolean1, double paramDouble1, double paramDouble2, boolean paramBoolean2, boolean paramBoolean3, double paramDouble3)
  {
    streetName = paramString1;
    elevation = paramList;
    relativeDirection = paramString2;
    absoluteDirection = paramString3;
    bogusName = paramBoolean1;
    lon = paramDouble1;
    lat = paramDouble2;
    stayOn = paramBoolean2;
    area = paramBoolean3;
    distance = paramDouble3;
  }
  
  @NotNull
  public final String component1()
  {
    return streetName;
  }
  
  public final double component10()
  {
    return distance;
  }
  
  @NotNull
  public final List<Integer> component2()
  {
    return elevation;
  }
  
  @NotNull
  public final String component3()
  {
    return relativeDirection;
  }
  
  @NotNull
  public final String component4()
  {
    return absoluteDirection;
  }
  
  public final boolean component5()
  {
    return bogusName;
  }
  
  public final double component6()
  {
    return lon;
  }
  
  public final double component7()
  {
    return lat;
  }
  
  public final boolean component8()
  {
    return stayOn;
  }
  
  public final boolean component9()
  {
    return area;
  }
  
  @NotNull
  public final TripStep copy(@NotNull String paramString1, @NotNull List<Integer> paramList, @NotNull String paramString2, @NotNull String paramString3, boolean paramBoolean1, double paramDouble1, double paramDouble2, boolean paramBoolean2, boolean paramBoolean3, double paramDouble3)
  {
    Intrinsics.checkParameterIsNotNull(paramString1, "streetName");
    Intrinsics.checkParameterIsNotNull(paramList, "elevation");
    Intrinsics.checkParameterIsNotNull(paramString2, "relativeDirection");
    Intrinsics.checkParameterIsNotNull(paramString3, "absoluteDirection");
    return new TripStep(paramString1, paramList, paramString2, paramString3, paramBoolean1, paramDouble1, paramDouble2, paramBoolean2, paramBoolean3, paramDouble3);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof TripStep))
      {
        paramObject = (TripStep)paramObject;
        if ((Intrinsics.areEqual(streetName, streetName)) && (Intrinsics.areEqual(elevation, elevation)) && (Intrinsics.areEqual(relativeDirection, relativeDirection)) && (Intrinsics.areEqual(absoluteDirection, absoluteDirection)))
        {
          int i;
          if (bogusName == bogusName) {
            i = 1;
          } else {
            i = 0;
          }
          if ((i != 0) && (Double.compare(lon, lon) == 0) && (Double.compare(lat, lat) == 0))
          {
            if (stayOn == stayOn) {
              i = 1;
            } else {
              i = 0;
            }
            if (i != 0)
            {
              if (area == area) {
                i = 1;
              } else {
                i = 0;
              }
              if ((i != 0) && (Double.compare(distance, distance) == 0)) {
                return true;
              }
            }
          }
        }
      }
      return false;
    }
    return true;
  }
  
  @NotNull
  public final String getAbsoluteDirection()
  {
    return absoluteDirection;
  }
  
  public final boolean getArea()
  {
    return area;
  }
  
  public final boolean getBogusName()
  {
    return bogusName;
  }
  
  public final double getDistance()
  {
    return distance;
  }
  
  @NotNull
  public final List<Integer> getElevation()
  {
    return elevation;
  }
  
  public final double getLat()
  {
    return lat;
  }
  
  public final double getLon()
  {
    return lon;
  }
  
  @NotNull
  public final String getRelativeDirection()
  {
    return relativeDirection;
  }
  
  public final boolean getStayOn()
  {
    return stayOn;
  }
  
  @NotNull
  public final String getStreetName()
  {
    return streetName;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("TripStep(streetName=");
    localStringBuilder.append(streetName);
    localStringBuilder.append(", elevation=");
    localStringBuilder.append(elevation);
    localStringBuilder.append(", relativeDirection=");
    localStringBuilder.append(relativeDirection);
    localStringBuilder.append(", absoluteDirection=");
    localStringBuilder.append(absoluteDirection);
    localStringBuilder.append(", bogusName=");
    localStringBuilder.append(bogusName);
    localStringBuilder.append(", lon=");
    localStringBuilder.append(lon);
    localStringBuilder.append(", lat=");
    localStringBuilder.append(lat);
    localStringBuilder.append(", stayOn=");
    localStringBuilder.append(stayOn);
    localStringBuilder.append(", area=");
    localStringBuilder.append(area);
    localStringBuilder.append(", distance=");
    localStringBuilder.append(distance);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(@NotNull Parcel paramParcel, int paramInt)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\002\030\002\n\000\n\002\020\016\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\026\020\006\032\0020\0072\006\020\b\032\0020\0052\006\020\t\032\0020\nR\026\020\003\032\b\022\004\022\0020\0050\0048\006X\004¢\006\002\n\000¨\006\013"}, d2={"Lcom/byimplication/sakay/model/TripStep$Companion;", "", "()V", "CREATOR", "Landroid/os/Parcelable$Creator;", "Lcom/byimplication/sakay/model/TripStep;", "translateTripStep", "", "tripStep", "context", "Landroid/content/Context;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    @NotNull
    public final String translateTripStep(@NotNull TripStep paramTripStep, @NotNull Context paramContext)
    {
      Intrinsics.checkParameterIsNotNull(paramTripStep, "tripStep");
      Intrinsics.checkParameterIsNotNull(paramContext, "context");
      String str = paramTripStep.getRelativeDirection();
      int i = str.hashCode();
      if (i != -1012507059)
      {
        if (i != -309938794)
        {
          if (i != 2332679)
          {
            if ((i == 77974012) && (str.equals("RIGHT")))
            {
              str = paramContext.getResources().getString(2131689747);
              Intrinsics.checkExpressionValueIsNotNull(str, "context.resources.getString(R.string.right)");
              break label193;
            }
          }
          else if (str.equals("LEFT"))
          {
            str = paramContext.getResources().getString(2131689639);
            Intrinsics.checkExpressionValueIsNotNull(str, "context.resources.getString(R.string.left)");
            break label193;
          }
        }
        else if (str.equals("SLIGHTLY_LEFT"))
        {
          str = paramContext.getResources().getString(2131689776);
          Intrinsics.checkExpressionValueIsNotNull(str, "context.resources.getStr…g(R.string.slightly_left)");
          break label193;
        }
      }
      else if (str.equals("SLIGHTLY_RIGHT"))
      {
        str = paramContext.getResources().getString(2131689777);
        Intrinsics.checkExpressionValueIsNotNull(str, "context.resources.getStr…(R.string.slightly_right)");
        break label193;
      }
      str = paramContext.getResources().getString(2131689587);
      Intrinsics.checkExpressionValueIsNotNull(str, "context.resources.getString(R.string.depart)");
      label193:
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(" ");
      localStringBuilder.append(paramContext.getResources().getString(2131689712));
      localStringBuilder.append(" <b>");
      localStringBuilder.append(paramTripStep.getStreetName());
      localStringBuilder.append("</b>.");
      return localStringBuilder.toString();
    }
  }
}

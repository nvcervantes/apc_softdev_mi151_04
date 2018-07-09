package com.byimplication.sakay.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\020\020\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\002\b\b\b\001\030\000 \r2\b\022\004\022\0020\0000\001:\001\rB\007\b\002¢\006\002\020\002J\b\020\003\032\0020\004H&J\b\020\005\032\0020\006H&j\002\b\007j\002\b\bj\002\b\tj\002\b\nj\002\b\013j\002\b\f¨\006\016"}, d2={"Lcom/byimplication/sakay/model/IssueType;", "", "(Ljava/lang/String;I)V", "getStringRes", "", "toString", "", "DISCONTINUED", "FARE", "ROUTE_PATH", "SCHEDULE", "STOP_LOCATION", "OTHER", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public enum IssueType
{
  public static final Companion Companion = new Companion(null);
  
  static
  {
    DISCONTINUED localDISCONTINUED = new DISCONTINUED("DISCONTINUED", 0);
    DISCONTINUED = localDISCONTINUED;
    FARE localFARE = new FARE("FARE", 1);
    FARE = localFARE;
    ROUTE_PATH localROUTE_PATH = new ROUTE_PATH("ROUTE_PATH", 2);
    ROUTE_PATH = localROUTE_PATH;
    SCHEDULE localSCHEDULE = new SCHEDULE("SCHEDULE", 3);
    SCHEDULE = localSCHEDULE;
    STOP_LOCATION localSTOP_LOCATION = new STOP_LOCATION("STOP_LOCATION", 4);
    STOP_LOCATION = localSTOP_LOCATION;
    OTHER localOTHER = new OTHER("OTHER", 5);
    OTHER = localOTHER;
    $VALUES = new IssueType[] { localDISCONTINUED, localFARE, localROUTE_PATH, localSCHEDULE, localSTOP_LOCATION, localOTHER };
  }
  
  protected IssueType() {}
  
  public abstract int getStringRes();
  
  @NotNull
  public abstract String toString();
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\n\002\020\016\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\003\032\0020\0042\006\020\005\032\0020\006¨\006\007"}, d2={"Lcom/byimplication/sakay/model/IssueType$Companion;", "", "()V", "fromString", "Lcom/byimplication/sakay/model/IssueType;", "issueTypeString", "", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    @NotNull
    public final IssueType fromString(@NotNull String paramString)
    {
      Intrinsics.checkParameterIsNotNull(paramString, "issueTypeString");
      switch (paramString.hashCode())
      {
      default: 
        break;
      case 1039986195: 
        if (paramString.equals("Stop Location")) {
          return IssueType.STOP_LOCATION;
        }
        break;
      case 2182222: 
        if (paramString.equals("Fare")) {
          return IssueType.FARE;
        }
        break;
      case -159490376: 
        if (paramString.equals("Discontinued Route")) {
          return IssueType.DISCONTINUED;
        }
        break;
      case -633276745: 
        if (paramString.equals("Schedule")) {
          return IssueType.SCHEDULE;
        }
        break;
      case -1448020004: 
        if (paramString.equals("Route Path")) {
          return IssueType.ROUTE_PATH;
        }
        break;
      }
      return IssueType.OTHER;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\bÆ\001\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\003\032\0020\004H\026J\b\020\005\032\0020\006H\026¨\006\007"}, d2={"Lcom/byimplication/sakay/model/IssueType$DISCONTINUED;", "Lcom/byimplication/sakay/model/IssueType;", "(Ljava/lang/String;I)V", "getStringRes", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
  public static final class DISCONTINUED
    extends IssueType
  {
    DISCONTINUED()
    {
      super(i);
    }
    
    public int getStringRes()
    {
      return 2131689591;
    }
    
    @NotNull
    public String toString()
    {
      return "Discontinued Route";
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\bÆ\001\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\003\032\0020\004H\026J\b\020\005\032\0020\006H\026¨\006\007"}, d2={"Lcom/byimplication/sakay/model/IssueType$FARE;", "Lcom/byimplication/sakay/model/IssueType;", "(Ljava/lang/String;I)V", "getStringRes", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
  public static final class FARE
    extends IssueType
  {
    FARE()
    {
      super(i);
    }
    
    public int getStringRes()
    {
      return 2131689603;
    }
    
    @NotNull
    public String toString()
    {
      return "Fare";
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\bÆ\001\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\003\032\0020\004H\026J\b\020\005\032\0020\006H\026¨\006\007"}, d2={"Lcom/byimplication/sakay/model/IssueType$OTHER;", "Lcom/byimplication/sakay/model/IssueType;", "(Ljava/lang/String;I)V", "getStringRes", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
  public static final class OTHER
    extends IssueType
  {
    OTHER()
    {
      super(i);
    }
    
    public int getStringRes()
    {
      return 2131689720;
    }
    
    @NotNull
    public String toString()
    {
      return "Other";
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\bÆ\001\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\003\032\0020\004H\026J\b\020\005\032\0020\006H\026¨\006\007"}, d2={"Lcom/byimplication/sakay/model/IssueType$ROUTE_PATH;", "Lcom/byimplication/sakay/model/IssueType;", "(Ljava/lang/String;I)V", "getStringRes", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
  public static final class ROUTE_PATH
    extends IssueType
  {
    ROUTE_PATH()
    {
      super(i);
    }
    
    public int getStringRes()
    {
      return 2131689749;
    }
    
    @NotNull
    public String toString()
    {
      return "Route Path";
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\bÆ\001\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\003\032\0020\004H\026J\b\020\005\032\0020\006H\026¨\006\007"}, d2={"Lcom/byimplication/sakay/model/IssueType$SCHEDULE;", "Lcom/byimplication/sakay/model/IssueType;", "(Ljava/lang/String;I)V", "getStringRes", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
  public static final class SCHEDULE
    extends IssueType
  {
    SCHEDULE()
    {
      super(i);
    }
    
    public int getStringRes()
    {
      return 2131689758;
    }
    
    @NotNull
    public String toString()
    {
      return "Schedule";
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\bÆ\001\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\003\032\0020\004H\026J\b\020\005\032\0020\006H\026¨\006\007"}, d2={"Lcom/byimplication/sakay/model/IssueType$STOP_LOCATION;", "Lcom/byimplication/sakay/model/IssueType;", "(Ljava/lang/String;I)V", "getStringRes", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
  public static final class STOP_LOCATION
    extends IssueType
  {
    STOP_LOCATION()
    {
      super(i);
    }
    
    public int getStringRes()
    {
      return 2131689786;
    }
    
    @NotNull
    public String toString()
    {
      return "Stop Location";
    }
  }
}

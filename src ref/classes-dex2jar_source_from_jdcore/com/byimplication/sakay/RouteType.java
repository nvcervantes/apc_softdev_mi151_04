package com.byimplication.sakay;

import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\020\020\n\002\b\005\b\001\030\0002\b\022\004\022\0020\0000\001B\007\b\002¢\006\002\020\002j\002\b\003j\002\b\004j\002\b\005¨\006\006"}, d2={"Lcom/byimplication/sakay/RouteType;", "", "(Ljava/lang/String;I)V", "SAKAY", "UBER", "GRAB", "app_release"}, k=1, mv={1, 1, 9})
public enum RouteType
{
  static
  {
    RouteType localRouteType1 = new RouteType("SAKAY", 0);
    SAKAY = localRouteType1;
    RouteType localRouteType2 = new RouteType("UBER", 1);
    UBER = localRouteType2;
    RouteType localRouteType3 = new RouteType("GRAB", 2);
    GRAB = localRouteType3;
    $VALUES = new RouteType[] { localRouteType1, localRouteType2, localRouteType3 };
  }
  
  protected RouteType() {}
}

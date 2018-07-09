package com.byimplication.sakay.model;

import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\020\020\n\002\b\004\b\001\030\0002\b\022\004\022\0020\0000\001B\007\b\002¢\006\002\020\002j\002\b\003j\002\b\004¨\006\005"}, d2={"Lcom/byimplication/sakay/model/ScheduleType;", "", "(Ljava/lang/String;I)V", "DEPARTURE", "ARRIVAL", "app_release"}, k=1, mv={1, 1, 9})
public enum ScheduleType
{
  static
  {
    ScheduleType localScheduleType1 = new ScheduleType("DEPARTURE", 0);
    DEPARTURE = localScheduleType1;
    ScheduleType localScheduleType2 = new ScheduleType("ARRIVAL", 1);
    ARRIVAL = localScheduleType2;
    $VALUES = new ScheduleType[] { localScheduleType1, localScheduleType2 };
  }
  
  protected ScheduleType() {}
}

package com.byimplication.sakay.model;

import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\020\020\n\002\b\005\b\001\030\0002\b\022\004\022\0020\0000\001B\007\b\002¢\006\002\020\002j\002\b\003j\002\b\004j\002\b\005¨\006\006"}, d2={"Lcom/byimplication/sakay/model/IncidentType;", "", "(Ljava/lang/String;I)V", "TRAFFIC", "LONGLINES", "OTHER", "app_release"}, k=1, mv={1, 1, 9})
public enum IncidentType
{
  static
  {
    IncidentType localIncidentType1 = new IncidentType("TRAFFIC", 0);
    TRAFFIC = localIncidentType1;
    IncidentType localIncidentType2 = new IncidentType("LONGLINES", 1);
    LONGLINES = localIncidentType2;
    IncidentType localIncidentType3 = new IncidentType("OTHER", 2);
    OTHER = localIncidentType3;
    $VALUES = new IncidentType[] { localIncidentType1, localIncidentType2, localIncidentType3 };
  }
  
  protected IncidentType() {}
}

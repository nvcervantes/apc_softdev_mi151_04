package com.byimplication.sakay.model;

import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\020\020\n\002\b\006\b\001\030\0002\b\022\004\022\0020\0000\001B\007\b\002¢\006\002\020\002j\002\b\003j\002\b\004j\002\b\005j\002\b\006¨\006\007"}, d2={"Lcom/byimplication/sakay/model/TerminalType;", "", "(Ljava/lang/String;I)V", "ORIGIN", "DESTINATION", "HOME", "WORK", "app_release"}, k=1, mv={1, 1, 9})
public enum TerminalType
{
  static
  {
    TerminalType localTerminalType1 = new TerminalType("ORIGIN", 0);
    ORIGIN = localTerminalType1;
    TerminalType localTerminalType2 = new TerminalType("DESTINATION", 1);
    DESTINATION = localTerminalType2;
    TerminalType localTerminalType3 = new TerminalType("HOME", 2);
    HOME = localTerminalType3;
    TerminalType localTerminalType4 = new TerminalType("WORK", 3);
    WORK = localTerminalType4;
    $VALUES = new TerminalType[] { localTerminalType1, localTerminalType2, localTerminalType3, localTerminalType4 };
  }
  
  protected TerminalType() {}
}

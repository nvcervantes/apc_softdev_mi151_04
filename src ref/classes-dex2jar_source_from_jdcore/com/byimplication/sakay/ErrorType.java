package com.byimplication.sakay;

import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\020\020\n\002\b\007\b\001\030\0002\b\022\004\022\0020\0000\001B\007\b\002¢\006\002\020\002j\002\b\003j\002\b\004j\002\b\005j\002\b\006j\002\b\007¨\006\b"}, d2={"Lcom/byimplication/sakay/ErrorType;", "", "(Ljava/lang/String;I)V", "ROUTE", "STOP", "FARE", "NAME", "OTHER", "app_release"}, k=1, mv={1, 1, 9})
public enum ErrorType
{
  static
  {
    ErrorType localErrorType1 = new ErrorType("ROUTE", 0);
    ROUTE = localErrorType1;
    ErrorType localErrorType2 = new ErrorType("STOP", 1);
    STOP = localErrorType2;
    ErrorType localErrorType3 = new ErrorType("FARE", 2);
    FARE = localErrorType3;
    ErrorType localErrorType4 = new ErrorType("NAME", 3);
    NAME = localErrorType4;
    ErrorType localErrorType5 = new ErrorType("OTHER", 4);
    OTHER = localErrorType5;
    $VALUES = new ErrorType[] { localErrorType1, localErrorType2, localErrorType3, localErrorType4, localErrorType5 };
  }
  
  protected ErrorType() {}
}

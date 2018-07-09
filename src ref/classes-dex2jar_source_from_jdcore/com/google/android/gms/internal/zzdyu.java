package com.google.android.gms.internal;

import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

final class zzdyu
  extends zzdyr
{
  private final zzdys zza = new zzdys();
  
  zzdyu() {}
  
  public final void zza(Throwable paramThrowable, PrintStream paramPrintStream)
  {
    paramThrowable.printStackTrace(paramPrintStream);
    paramThrowable = zza.zza(paramThrowable, false);
    if (paramThrowable == null) {
      return;
    }
    try
    {
      Iterator localIterator = paramThrowable.iterator();
      while (localIterator.hasNext())
      {
        Throwable localThrowable = (Throwable)localIterator.next();
        paramPrintStream.print("Suppressed: ");
        localThrowable.printStackTrace(paramPrintStream);
      }
      return;
    }
    finally {}
  }
  
  public final void zza(Throwable paramThrowable, PrintWriter paramPrintWriter)
  {
    paramThrowable.printStackTrace(paramPrintWriter);
    paramThrowable = zza.zza(paramThrowable, false);
    if (paramThrowable == null) {
      return;
    }
    try
    {
      Iterator localIterator = paramThrowable.iterator();
      while (localIterator.hasNext())
      {
        Throwable localThrowable = (Throwable)localIterator.next();
        paramPrintWriter.print("Suppressed: ");
        localThrowable.printStackTrace(paramPrintWriter);
      }
      return;
    }
    finally {}
  }
}

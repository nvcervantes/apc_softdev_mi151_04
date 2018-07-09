package okhttp3.internal.connection;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class RouteException
  extends RuntimeException
{
  private static final Method addSuppressedExceptionMethod;
  private IOException lastException;
  
  static
  {
    try
    {
      localMethod = Throwable.class.getDeclaredMethod("addSuppressed", new Class[] { Throwable.class });
    }
    catch (Exception localException)
    {
      Method localMethod;
      for (;;) {}
    }
    localMethod = null;
    addSuppressedExceptionMethod = localMethod;
  }
  
  public RouteException(IOException paramIOException)
  {
    super(paramIOException);
    lastException = paramIOException;
  }
  
  private void addSuppressedIfPossible(IOException paramIOException1, IOException paramIOException2)
  {
    if (addSuppressedExceptionMethod != null) {}
    try
    {
      addSuppressedExceptionMethod.invoke(paramIOException1, new Object[] { paramIOException2 });
      return;
    }
    catch (InvocationTargetException|IllegalAccessException paramIOException1) {}
  }
  
  public void addConnectException(IOException paramIOException)
  {
    addSuppressedIfPossible(paramIOException, lastException);
    lastException = paramIOException;
  }
  
  public IOException getLastConnectException()
  {
    return lastException;
  }
}

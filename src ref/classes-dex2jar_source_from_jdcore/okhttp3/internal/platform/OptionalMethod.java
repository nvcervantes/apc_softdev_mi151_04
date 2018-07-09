package okhttp3.internal.platform;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class OptionalMethod<T>
{
  private final String methodName;
  private final Class[] methodParams;
  private final Class<?> returnType;
  
  OptionalMethod(Class<?> paramClass, String paramString, Class... paramVarArgs)
  {
    returnType = paramClass;
    methodName = paramString;
    methodParams = paramVarArgs;
  }
  
  private Method getMethod(Class<?> paramClass)
  {
    String str = methodName;
    Method localMethod = null;
    if (str != null)
    {
      localMethod = getPublicMethod(paramClass, methodName, methodParams);
      if ((localMethod != null) && (returnType != null) && (!returnType.isAssignableFrom(localMethod.getReturnType()))) {
        return null;
      }
    }
    return localMethod;
  }
  
  private static Method getPublicMethod(Class<?> paramClass, String paramString, Class[] paramArrayOfClass)
  {
    try
    {
      paramClass = paramClass.getMethod(paramString, paramArrayOfClass);
      int i;
      return paramClass;
    }
    catch (NoSuchMethodException paramClass)
    {
      for (;;)
      {
        try
        {
          i = paramClass.getModifiers();
          if ((i & 0x1) == 0) {
            paramClass = null;
          }
          return paramClass;
        }
        catch (NoSuchMethodException paramString) {}
        paramClass = paramClass;
      }
    }
  }
  
  public Object invoke(T paramT, Object... paramVarArgs)
    throws InvocationTargetException
  {
    Method localMethod = getMethod(paramT.getClass());
    if (localMethod == null)
    {
      paramVarArgs = new StringBuilder();
      paramVarArgs.append("Method ");
      paramVarArgs.append(methodName);
      paramVarArgs.append(" not supported for object ");
      paramVarArgs.append(paramT);
      throw new AssertionError(paramVarArgs.toString());
    }
    try
    {
      paramT = localMethod.invoke(paramT, paramVarArgs);
      return paramT;
    }
    catch (IllegalAccessException paramT)
    {
      paramVarArgs = new StringBuilder();
      paramVarArgs.append("Unexpectedly could not call: ");
      paramVarArgs.append(localMethod);
      paramVarArgs = new AssertionError(paramVarArgs.toString());
      paramVarArgs.initCause(paramT);
      throw paramVarArgs;
    }
  }
  
  public Object invokeOptional(T paramT, Object... paramVarArgs)
    throws InvocationTargetException
  {
    Method localMethod = getMethod(paramT.getClass());
    if (localMethod == null) {
      return null;
    }
    try
    {
      paramT = localMethod.invoke(paramT, paramVarArgs);
      return paramT;
    }
    catch (IllegalAccessException paramT) {}
    return null;
  }
  
  public Object invokeOptionalWithoutCheckedException(T paramT, Object... paramVarArgs)
  {
    try
    {
      paramT = invokeOptional(paramT, paramVarArgs);
      return paramT;
    }
    catch (InvocationTargetException paramT)
    {
      paramT = paramT.getTargetException();
      if ((paramT instanceof RuntimeException)) {
        throw ((RuntimeException)paramT);
      }
      paramVarArgs = new AssertionError("Unexpected exception");
      paramVarArgs.initCause(paramT);
      throw paramVarArgs;
    }
  }
  
  public Object invokeWithoutCheckedException(T paramT, Object... paramVarArgs)
  {
    try
    {
      paramT = invoke(paramT, paramVarArgs);
      return paramT;
    }
    catch (InvocationTargetException paramT)
    {
      paramT = paramT.getTargetException();
      if ((paramT instanceof RuntimeException)) {
        throw ((RuntimeException)paramT);
      }
      paramVarArgs = new AssertionError("Unexpected exception");
      paramVarArgs.initCause(paramT);
      throw paramVarArgs;
    }
  }
  
  public boolean isSupported(T paramT)
  {
    return getMethod(paramT.getClass()) != null;
  }
}

package kotlin.jvm.internal;

import java.util.Arrays;
import java.util.List;
import kotlin.KotlinNullPointerException;
import kotlin.SinceKotlin;
import kotlin.UninitializedPropertyAccessException;

public class Intrinsics
{
  private Intrinsics() {}
  
  @SinceKotlin(version="1.1")
  public static boolean areEqual(double paramDouble, Double paramDouble1)
  {
    return (paramDouble1 != null) && (paramDouble == paramDouble1.doubleValue());
  }
  
  @SinceKotlin(version="1.1")
  public static boolean areEqual(float paramFloat, Float paramFloat1)
  {
    return (paramFloat1 != null) && (paramFloat == paramFloat1.floatValue());
  }
  
  @SinceKotlin(version="1.1")
  public static boolean areEqual(Double paramDouble, double paramDouble1)
  {
    return (paramDouble != null) && (paramDouble.doubleValue() == paramDouble1);
  }
  
  @SinceKotlin(version="1.1")
  public static boolean areEqual(Double paramDouble1, Double paramDouble2)
  {
    if (paramDouble1 == null)
    {
      if (paramDouble2 != null) {}
    }
    else {
      while ((paramDouble2 != null) && (paramDouble1.doubleValue() == paramDouble2.doubleValue())) {
        return true;
      }
    }
    return false;
  }
  
  @SinceKotlin(version="1.1")
  public static boolean areEqual(Float paramFloat, float paramFloat1)
  {
    return (paramFloat != null) && (paramFloat.floatValue() == paramFloat1);
  }
  
  @SinceKotlin(version="1.1")
  public static boolean areEqual(Float paramFloat1, Float paramFloat2)
  {
    if (paramFloat1 == null)
    {
      if (paramFloat2 != null) {}
    }
    else {
      while ((paramFloat2 != null) && (paramFloat1.floatValue() == paramFloat2.floatValue())) {
        return true;
      }
    }
    return false;
  }
  
  public static boolean areEqual(Object paramObject1, Object paramObject2)
  {
    if (paramObject1 == null) {
      return paramObject2 == null;
    }
    return paramObject1.equals(paramObject2);
  }
  
  public static void checkExpressionValueIsNotNull(Object paramObject, String paramString)
  {
    if (paramObject == null)
    {
      paramObject = new StringBuilder();
      paramObject.append(paramString);
      paramObject.append(" must not be null");
      throw ((IllegalStateException)sanitizeStackTrace(new IllegalStateException(paramObject.toString())));
    }
  }
  
  public static void checkFieldIsNotNull(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throw ((IllegalStateException)sanitizeStackTrace(new IllegalStateException(paramString)));
    }
  }
  
  public static void checkFieldIsNotNull(Object paramObject, String paramString1, String paramString2)
  {
    if (paramObject == null)
    {
      paramObject = new StringBuilder();
      paramObject.append("Field specified as non-null is null: ");
      paramObject.append(paramString1);
      paramObject.append(".");
      paramObject.append(paramString2);
      throw ((IllegalStateException)sanitizeStackTrace(new IllegalStateException(paramObject.toString())));
    }
  }
  
  public static void checkHasClass(String paramString)
    throws ClassNotFoundException
  {
    paramString = paramString.replace('/', '.');
    try
    {
      Class.forName(paramString);
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Class ");
      localStringBuilder.append(paramString);
      localStringBuilder.append(" is not found. Please update the Kotlin runtime to the latest version");
      throw ((ClassNotFoundException)sanitizeStackTrace(new ClassNotFoundException(localStringBuilder.toString(), localClassNotFoundException)));
    }
  }
  
  public static void checkHasClass(String paramString1, String paramString2)
    throws ClassNotFoundException
  {
    paramString1 = paramString1.replace('/', '.');
    try
    {
      Class.forName(paramString1);
      return;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Class ");
      localStringBuilder.append(paramString1);
      localStringBuilder.append(" is not found: this code requires the Kotlin runtime of version at least ");
      localStringBuilder.append(paramString2);
      throw ((ClassNotFoundException)sanitizeStackTrace(new ClassNotFoundException(localStringBuilder.toString(), localClassNotFoundException)));
    }
  }
  
  public static void checkNotNull(Object paramObject)
  {
    if (paramObject == null) {
      throwNpe();
    }
  }
  
  public static void checkNotNull(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throwNpe(paramString);
    }
  }
  
  public static void checkNotNullExpressionValue(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throw ((IllegalStateException)sanitizeStackTrace(new IllegalStateException(paramString)));
    }
  }
  
  public static void checkNotNullParameter(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throw ((IllegalArgumentException)sanitizeStackTrace(new IllegalArgumentException(paramString)));
    }
  }
  
  public static void checkParameterIsNotNull(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throwParameterIsNullException(paramString);
    }
  }
  
  public static void checkReturnedValueIsNotNull(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      throw ((IllegalStateException)sanitizeStackTrace(new IllegalStateException(paramString)));
    }
  }
  
  public static void checkReturnedValueIsNotNull(Object paramObject, String paramString1, String paramString2)
  {
    if (paramObject == null)
    {
      paramObject = new StringBuilder();
      paramObject.append("Method specified as non-null returned null: ");
      paramObject.append(paramString1);
      paramObject.append(".");
      paramObject.append(paramString2);
      throw ((IllegalStateException)sanitizeStackTrace(new IllegalStateException(paramObject.toString())));
    }
  }
  
  public static int compare(int paramInt1, int paramInt2)
  {
    if (paramInt1 < paramInt2) {
      return -1;
    }
    if (paramInt1 == paramInt2) {
      return 0;
    }
    return 1;
  }
  
  public static int compare(long paramLong1, long paramLong2)
  {
    if (paramLong1 < paramLong2) {
      return -1;
    }
    if (paramLong1 == paramLong2) {
      return 0;
    }
    return 1;
  }
  
  public static void needClassReification() {}
  
  public static void needClassReification(String paramString)
  {
    throwUndefinedForReified(paramString);
  }
  
  public static void reifiedOperationMarker(int paramInt, String paramString) {}
  
  public static void reifiedOperationMarker(int paramInt, String paramString1, String paramString2)
  {
    throwUndefinedForReified(paramString2);
  }
  
  private static <T extends Throwable> T sanitizeStackTrace(T paramT)
  {
    return sanitizeStackTrace(paramT, Intrinsics.class.getName());
  }
  
  static <T extends Throwable> T sanitizeStackTrace(T paramT, String paramString)
  {
    StackTraceElement[] arrayOfStackTraceElement = paramT.getStackTrace();
    int j = -1;
    int i = 0;
    int k = arrayOfStackTraceElement.length;
    while (i < k)
    {
      if (paramString.equals(arrayOfStackTraceElement[i].getClassName())) {
        j = i;
      }
      i += 1;
    }
    paramString = Arrays.asList(arrayOfStackTraceElement).subList(j + 1, k);
    paramT.setStackTrace((StackTraceElement[])paramString.toArray(new StackTraceElement[paramString.size()]));
    return paramT;
  }
  
  public static String stringPlus(String paramString, Object paramObject)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(paramString);
    localStringBuilder.append(paramObject);
    return localStringBuilder.toString();
  }
  
  public static void throwAssert()
  {
    throw ((AssertionError)sanitizeStackTrace(new AssertionError()));
  }
  
  public static void throwAssert(String paramString)
  {
    throw ((AssertionError)sanitizeStackTrace(new AssertionError(paramString)));
  }
  
  public static void throwIllegalArgument()
  {
    throw ((IllegalArgumentException)sanitizeStackTrace(new IllegalArgumentException()));
  }
  
  public static void throwIllegalArgument(String paramString)
  {
    throw ((IllegalArgumentException)sanitizeStackTrace(new IllegalArgumentException(paramString)));
  }
  
  public static void throwIllegalState()
  {
    throw ((IllegalStateException)sanitizeStackTrace(new IllegalStateException()));
  }
  
  public static void throwIllegalState(String paramString)
  {
    throw ((IllegalStateException)sanitizeStackTrace(new IllegalStateException(paramString)));
  }
  
  public static void throwNpe()
  {
    throw ((KotlinNullPointerException)sanitizeStackTrace(new KotlinNullPointerException()));
  }
  
  public static void throwNpe(String paramString)
  {
    throw ((KotlinNullPointerException)sanitizeStackTrace(new KotlinNullPointerException(paramString)));
  }
  
  private static void throwParameterIsNullException(String paramString)
  {
    Object localObject = Thread.currentThread().getStackTrace()[3];
    String str = ((StackTraceElement)localObject).getClassName();
    localObject = ((StackTraceElement)localObject).getMethodName();
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Parameter specified as non-null is null: method ");
    localStringBuilder.append(str);
    localStringBuilder.append(".");
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(", parameter ");
    localStringBuilder.append(paramString);
    throw ((IllegalArgumentException)sanitizeStackTrace(new IllegalArgumentException(localStringBuilder.toString())));
  }
  
  public static void throwUndefinedForReified()
  {
    throwUndefinedForReified("This function has a reified type parameter and thus can only be inlined at compilation time, not called directly.");
  }
  
  public static void throwUndefinedForReified(String paramString)
  {
    throw new UnsupportedOperationException(paramString);
  }
  
  public static void throwUninitializedProperty(String paramString)
  {
    throw ((UninitializedPropertyAccessException)sanitizeStackTrace(new UninitializedPropertyAccessException(paramString)));
  }
  
  public static void throwUninitializedPropertyAccessException(String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("lateinit property ");
    localStringBuilder.append(paramString);
    localStringBuilder.append(" has not been initialized");
    throwUninitializedProperty(localStringBuilder.toString());
  }
}

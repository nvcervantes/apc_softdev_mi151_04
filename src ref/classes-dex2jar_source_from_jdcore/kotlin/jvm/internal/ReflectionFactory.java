package kotlin.jvm.internal;

import kotlin.SinceKotlin;
import kotlin.reflect.KClass;
import kotlin.reflect.KDeclarationContainer;
import kotlin.reflect.KFunction;
import kotlin.reflect.KMutableProperty0;
import kotlin.reflect.KMutableProperty1;
import kotlin.reflect.KMutableProperty2;
import kotlin.reflect.KProperty0;
import kotlin.reflect.KProperty1;
import kotlin.reflect.KProperty2;

public class ReflectionFactory
{
  private static final String KOTLIN_JVM_FUNCTIONS = "kotlin.jvm.functions.";
  
  public ReflectionFactory() {}
  
  public KClass createKotlinClass(Class paramClass)
  {
    return new ClassReference(paramClass);
  }
  
  public KClass createKotlinClass(Class paramClass, String paramString)
  {
    return new ClassReference(paramClass);
  }
  
  public KFunction function(FunctionReference paramFunctionReference)
  {
    return paramFunctionReference;
  }
  
  public KClass getOrCreateKotlinClass(Class paramClass)
  {
    return new ClassReference(paramClass);
  }
  
  public KClass getOrCreateKotlinClass(Class paramClass, String paramString)
  {
    return new ClassReference(paramClass);
  }
  
  public KDeclarationContainer getOrCreateKotlinPackage(Class paramClass, String paramString)
  {
    return new PackageReference(paramClass, paramString);
  }
  
  public KMutableProperty0 mutableProperty0(MutablePropertyReference0 paramMutablePropertyReference0)
  {
    return paramMutablePropertyReference0;
  }
  
  public KMutableProperty1 mutableProperty1(MutablePropertyReference1 paramMutablePropertyReference1)
  {
    return paramMutablePropertyReference1;
  }
  
  public KMutableProperty2 mutableProperty2(MutablePropertyReference2 paramMutablePropertyReference2)
  {
    return paramMutablePropertyReference2;
  }
  
  public KProperty0 property0(PropertyReference0 paramPropertyReference0)
  {
    return paramPropertyReference0;
  }
  
  public KProperty1 property1(PropertyReference1 paramPropertyReference1)
  {
    return paramPropertyReference1;
  }
  
  public KProperty2 property2(PropertyReference2 paramPropertyReference2)
  {
    return paramPropertyReference2;
  }
  
  @SinceKotlin(version="1.1")
  public String renderLambdaToString(Lambda paramLambda)
  {
    String str = paramLambda.getClass().getGenericInterfaces()[0].toString();
    paramLambda = str;
    if (str.startsWith("kotlin.jvm.functions.")) {
      paramLambda = str.substring("kotlin.jvm.functions.".length());
    }
    return paramLambda;
  }
}

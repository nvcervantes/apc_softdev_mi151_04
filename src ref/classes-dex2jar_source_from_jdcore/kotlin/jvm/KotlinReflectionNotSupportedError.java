package kotlin.jvm;

import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\020\003\n\002\b\003\b\026\030\0002\0020\001B\007\b\026¢\006\002\020\002B\021\b\026\022\b\020\003\032\004\030\0010\004¢\006\002\020\005B\033\b\026\022\b\020\003\032\004\030\0010\004\022\b\020\006\032\004\030\0010\007¢\006\002\020\bB\021\b\026\022\b\020\006\032\004\030\0010\007¢\006\002\020\t¨\006\n"}, d2={"Lkotlin/jvm/KotlinReflectionNotSupportedError;", "Ljava/lang/Error;", "()V", "message", "", "(Ljava/lang/String;)V", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "(Ljava/lang/Throwable;)V", "kotlin-runtime"}, k=1, mv={1, 1, 9})
public class KotlinReflectionNotSupportedError
  extends Error
{
  public KotlinReflectionNotSupportedError()
  {
    super("Kotlin reflection implementation is not found at runtime. Make sure you have kotlin-reflect.jar in the classpath");
  }
  
  public KotlinReflectionNotSupportedError(@Nullable String paramString)
  {
    super(paramString);
  }
  
  public KotlinReflectionNotSupportedError(@Nullable String paramString, @Nullable Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public KotlinReflectionNotSupportedError(@Nullable Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

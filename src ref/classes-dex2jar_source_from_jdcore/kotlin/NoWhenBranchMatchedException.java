package kotlin;

import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000 \n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\020\003\n\002\b\003\b\026\030\0002\0060\001j\002`\002B\007\b\026¢\006\002\020\003B\021\b\026\022\b\020\004\032\004\030\0010\005¢\006\002\020\006B\033\b\026\022\b\020\004\032\004\030\0010\005\022\b\020\007\032\004\030\0010\b¢\006\002\020\tB\021\b\026\022\b\020\007\032\004\030\0010\b¢\006\002\020\n¨\006\013"}, d2={"Lkotlin/NoWhenBranchMatchedException;", "Ljava/lang/RuntimeException;", "Lkotlin/RuntimeException;", "()V", "message", "", "(Ljava/lang/String;)V", "cause", "", "(Ljava/lang/String;Ljava/lang/Throwable;)V", "(Ljava/lang/Throwable;)V", "kotlin-runtime"}, k=1, mv={1, 1, 9})
public class NoWhenBranchMatchedException
  extends RuntimeException
{
  public NoWhenBranchMatchedException() {}
  
  public NoWhenBranchMatchedException(@Nullable String paramString)
  {
    super(paramString);
  }
  
  public NoWhenBranchMatchedException(@Nullable String paramString, @Nullable Throwable paramThrowable)
  {
    super(paramString, paramThrowable);
  }
  
  public NoWhenBranchMatchedException(@Nullable Throwable paramThrowable)
  {
    super(paramThrowable);
  }
}

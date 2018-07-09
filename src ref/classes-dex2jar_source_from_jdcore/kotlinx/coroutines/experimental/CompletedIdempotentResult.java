package kotlinx.coroutines.experimental;

import kotlin.Metadata;
import kotlin.jvm.JvmField;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\032\n\002\030\002\n\002\020\000\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\016\n\000\b\002\030\0002\0020\001B!\022\b\020\002\032\004\030\0010\001\022\b\020\003\032\004\030\0010\001\022\006\020\004\032\0020\005¢\006\002\020\006J\b\020\007\032\0020\bH\026R\022\020\002\032\004\030\0010\0018\006X\004¢\006\002\n\000R\022\020\003\032\004\030\0010\0018\006X\004¢\006\002\n\000R\020\020\004\032\0020\0058\006X\004¢\006\002\n\000¨\006\t"}, d2={"Lkotlinx/coroutines/experimental/CompletedIdempotentResult;", "", "idempotentResume", "result", "token", "Lkotlinx/coroutines/experimental/JobSupport$Incomplete;", "(Ljava/lang/Object;Ljava/lang/Object;Lkotlinx/coroutines/experimental/JobSupport$Incomplete;)V", "toString", "", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
final class CompletedIdempotentResult
{
  @JvmField
  @Nullable
  public final Object idempotentResume;
  @JvmField
  @Nullable
  public final Object result;
  @JvmField
  @NotNull
  public final JobSupport.Incomplete token;
  
  public CompletedIdempotentResult(@Nullable Object paramObject1, @Nullable Object paramObject2, @NotNull JobSupport.Incomplete paramIncomplete)
  {
    idempotentResume = paramObject1;
    result = paramObject2;
    token = paramIncomplete;
  }
  
  @NotNull
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("CompletedIdempotentResult[");
    localStringBuilder.append(result);
    localStringBuilder.append(']');
    return localStringBuilder.toString();
  }
}

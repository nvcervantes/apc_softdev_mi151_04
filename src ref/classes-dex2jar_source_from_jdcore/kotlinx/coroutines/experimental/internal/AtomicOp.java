package kotlinx.coroutines.experimental.internal;

import java.util.concurrent.atomic.AtomicReferenceFieldUpdater;
import kotlin.Metadata;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\020\000\n\000\n\002\020\013\n\002\b\002\n\002\020\002\n\002\b\n\b&\030\000*\006\b\000\020\001 \0002\0020\002B\005¢\006\002\020\003J\037\020\n\032\0020\0132\006\020\f\032\0028\0002\b\020\r\032\004\030\0010\006H&¢\006\002\020\016J\024\020\017\032\004\030\0010\0062\b\020\020\032\004\030\0010\006H\002J\024\020\021\032\004\030\0010\0062\b\020\f\032\004\030\0010\006H\007J\027\020\022\032\004\030\0010\0062\006\020\f\032\0028\000H&¢\006\002\020\023J\020\020\024\032\0020\b2\b\020\020\032\004\030\0010\006R\026\020\004\032\n\022\006\022\004\030\0010\0060\005X\004¢\006\002\n\000R\021\020\007\032\0020\b8F¢\006\006\032\004\b\007\020\t¨\006\025"}, d2={"Lkotlinx/coroutines/experimental/internal/AtomicOp;", "T", "Lkotlinx/coroutines/experimental/internal/OpDescriptor;", "()V", "_consensus", "Lkotlinx/atomicfu/AtomicRef;", "", "isDecided", "", "()Z", "complete", "", "affected", "failure", "(Ljava/lang/Object;Ljava/lang/Object;)V", "decide", "decision", "perform", "prepare", "(Ljava/lang/Object;)Ljava/lang/Object;", "tryDecide", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract class AtomicOp<T>
  extends OpDescriptor
{
  private static final AtomicReferenceFieldUpdater _consensus$FU = AtomicReferenceFieldUpdater.newUpdater(AtomicOp.class, Object.class, "_consensus");
  private volatile Object _consensus = AtomicKt.access$getNO_DECISION$p();
  
  public AtomicOp() {}
  
  private final Object decide(Object paramObject)
  {
    if (tryDecide(paramObject)) {
      return paramObject;
    }
    return _consensus;
  }
  
  public abstract void complete(T paramT, @Nullable Object paramObject);
  
  public final boolean isDecided()
  {
    return _consensus != AtomicKt.access$getNO_DECISION$p();
  }
  
  @Nullable
  public final Object perform(@Nullable Object paramObject)
  {
    Object localObject2 = _consensus;
    Object localObject1 = localObject2;
    if (localObject2 == AtomicKt.access$getNO_DECISION$p()) {
      localObject1 = decide(prepare(paramObject));
    }
    complete(paramObject, localObject1);
    return localObject1;
  }
  
  @Nullable
  public abstract Object prepare(T paramT);
  
  public final boolean tryDecide(@Nullable Object paramObject)
  {
    int i;
    if (paramObject != AtomicKt.access$getNO_DECISION$p()) {
      i = 1;
    } else {
      i = 0;
    }
    if (i == 0) {
      throw ((Throwable)new IllegalStateException("Check failed.".toString()));
    }
    return _consensus$FU.compareAndSet(this, AtomicKt.access$getNO_DECISION$p(), paramObject);
  }
}

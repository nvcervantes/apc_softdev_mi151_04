package kotlin.sequences;

import java.util.Iterator;
import java.util.concurrent.atomic.AtomicReference;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\002\030\002\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020(\n\000\b\003\030\000*\004\b\000\020\0012\b\022\004\022\002H\0010\002B\023\022\f\020\003\032\b\022\004\022\0028\0000\002¢\006\002\020\004J\017\020\b\032\b\022\004\022\0028\0000\tH\002R(\020\005\032\034\022\030\022\026\022\004\022\0028\000 \007*\n\022\004\022\0028\000\030\0010\0020\0020\006X\004¢\006\002\n\000¨\006\n"}, d2={"Lkotlin/sequences/ConstrainedOnceSequence;", "T", "Lkotlin/sequences/Sequence;", "sequence", "(Lkotlin/sequences/Sequence;)V", "sequenceRef", "Ljava/util/concurrent/atomic/AtomicReference;", "kotlin.jvm.PlatformType", "iterator", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
final class ConstrainedOnceSequence<T>
  implements Sequence<T>
{
  private final AtomicReference<Sequence<T>> sequenceRef;
  
  public ConstrainedOnceSequence(@NotNull Sequence<? extends T> paramSequence)
  {
    sequenceRef = new AtomicReference(paramSequence);
  }
  
  @NotNull
  public Iterator<T> iterator()
  {
    Sequence localSequence = (Sequence)sequenceRef.getAndSet(null);
    if (localSequence != null) {
      return localSequence.iterator();
    }
    throw ((Throwable)new IllegalStateException("This sequence can be consumed only once."));
  }
}

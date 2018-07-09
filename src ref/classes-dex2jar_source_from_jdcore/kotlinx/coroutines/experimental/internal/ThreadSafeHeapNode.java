package kotlinx.coroutines.experimental.internal;

import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\000\n\002\020\b\n\002\b\005\bf\030\0002\0020\001R\030\020\002\032\0020\003X¦\016¢\006\f\032\004\b\004\020\005\"\004\b\006\020\007¨\006\b"}, d2={"Lkotlinx/coroutines/experimental/internal/ThreadSafeHeapNode;", "", "index", "", "getIndex", "()I", "setIndex", "(I)V", "kotlinx-coroutines-core"}, k=1, mv={1, 1, 7})
public abstract interface ThreadSafeHeapNode
{
  public abstract int getIndex();
  
  public abstract void setIndex(int paramInt);
}

package kotlin.collections;

import java.util.Iterator;
import kotlin.Metadata;
import kotlin.jvm.internal.markers.KMappedMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\020\n\002\030\002\n\002\020(\n\002\020\005\n\002\b\005\b&\030\0002\b\022\004\022\0020\0020\001B\005¢\006\002\020\003J\016\020\004\032\0020\002H\002¢\006\002\020\005J\b\020\006\032\0020\002H&¨\006\007"}, d2={"Lkotlin/collections/ByteIterator;", "", "", "()V", "next", "()Ljava/lang/Byte;", "nextByte", "kotlin-runtime"}, k=1, mv={1, 1, 9})
public abstract class ByteIterator
  implements Iterator<Byte>, KMappedMarker
{
  public ByteIterator() {}
  
  @NotNull
  public final Byte next()
  {
    return Byte.valueOf(nextByte());
  }
  
  public abstract byte nextByte();
  
  public void remove()
  {
    throw new UnsupportedOperationException("Operation is not supported for read-only collection");
  }
}

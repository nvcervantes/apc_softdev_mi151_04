package kotlin.io;

import java.io.BufferedReader;
import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\002\030\002\n\002\030\002\n\002\020\016\n\000\n\002\030\002\n\002\b\002\n\002\020(\n\000\b\002\030\0002\b\022\004\022\0020\0020\001B\r\022\006\020\003\032\0020\004¢\006\002\020\005J\017\020\006\032\b\022\004\022\0020\0020\007H\002R\016\020\003\032\0020\004X\004¢\006\002\n\000¨\006\b"}, d2={"Lkotlin/io/LinesSequence;", "Lkotlin/sequences/Sequence;", "", "reader", "Ljava/io/BufferedReader;", "(Ljava/io/BufferedReader;)V", "iterator", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
final class LinesSequence
  implements Sequence<String>
{
  private final BufferedReader reader;
  
  public LinesSequence(@NotNull BufferedReader paramBufferedReader)
  {
    reader = paramBufferedReader;
  }
  
  @NotNull
  public Iterator<String> iterator()
  {
    (Iterator)new Iterator()
    {
      private boolean done;
      private String nextValue;
      
      public boolean hasNext()
      {
        if ((nextValue == null) && (!done))
        {
          nextValue = LinesSequence.access$getReader$p(this$0).readLine();
          if (nextValue == null) {
            done = true;
          }
        }
        return nextValue != null;
      }
      
      @NotNull
      public String next()
      {
        if (!hasNext()) {
          throw ((Throwable)new NoSuchElementException());
        }
        String str = nextValue;
        nextValue = ((String)null);
        if (str == null) {
          Intrinsics.throwNpe();
        }
        return str;
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
    };
  }
}

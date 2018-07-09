package kotlin.text;

import java.util.Iterator;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.markers.KMappedMarker;
import kotlin.ranges.IntRange;
import kotlin.ranges.RangesKt;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0002\n\002\030\002\n\002\030\002\n\002\030\002\n\000\n\002\020\r\n\000\n\002\020\b\n\002\b\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020(\n\000\b\002\030\0002\b\022\004\022\0020\0020\001BJ\022\006\020\003\032\0020\004\022\006\020\005\032\0020\006\022\006\020\007\032\0020\006\022+\020\b\032'\022\004\022\0020\004\022\004\022\0020\006\022\022\022\020\022\004\022\0020\006\022\004\022\0020\006\030\0010\n0\t¢\006\002\b\013¢\006\002\020\fJ\017\020\r\032\b\022\004\022\0020\0020\016H\002R3\020\b\032'\022\004\022\0020\004\022\004\022\0020\006\022\022\022\020\022\004\022\0020\006\022\004\022\0020\006\030\0010\n0\t¢\006\002\b\013X\004¢\006\002\n\000R\016\020\003\032\0020\004X\004¢\006\002\n\000R\016\020\007\032\0020\006X\004¢\006\002\n\000R\016\020\005\032\0020\006X\004¢\006\002\n\000¨\006\017"}, d2={"Lkotlin/text/DelimitedRangesSequence;", "Lkotlin/sequences/Sequence;", "Lkotlin/ranges/IntRange;", "input", "", "startIndex", "", "limit", "getNextMatch", "Lkotlin/Function2;", "Lkotlin/Pair;", "Lkotlin/ExtensionFunctionType;", "(Ljava/lang/CharSequence;IILkotlin/jvm/functions/Function2;)V", "iterator", "", "kotlin-stdlib"}, k=1, mv={1, 1, 9})
final class DelimitedRangesSequence
  implements Sequence<IntRange>
{
  private final Function2<CharSequence, Integer, Pair<Integer, Integer>> getNextMatch;
  private final CharSequence input;
  private final int limit;
  private final int startIndex;
  
  public DelimitedRangesSequence(@NotNull CharSequence paramCharSequence, int paramInt1, int paramInt2, @NotNull Function2<? super CharSequence, ? super Integer, Pair<Integer, Integer>> paramFunction2)
  {
    input = paramCharSequence;
    startIndex = paramInt1;
    limit = paramInt2;
    getNextMatch = paramFunction2;
  }
  
  @NotNull
  public Iterator<IntRange> iterator()
  {
    (Iterator)new Iterator()
    {
      private int counter;
      private int currentStartIndex;
      @Nullable
      private IntRange nextItem;
      private int nextSearchIndex;
      private int nextState = -1;
      
      private final void calcNext()
      {
        int j = nextSearchIndex;
        int i = 0;
        if (j < 0)
        {
          nextState = 0;
          nextItem = ((IntRange)null);
          return;
        }
        if (DelimitedRangesSequence.access$getLimit$p(this$0) > 0)
        {
          counter += 1;
          if (counter >= DelimitedRangesSequence.access$getLimit$p(this$0)) {}
        }
        else
        {
          if (nextSearchIndex <= DelimitedRangesSequence.access$getInput$p(this$0).length()) {
            break label111;
          }
        }
        nextItem = new IntRange(currentStartIndex, StringsKt.getLastIndex(DelimitedRangesSequence.access$getInput$p(this$0)));
        nextSearchIndex = -1;
        break label241;
        label111:
        Pair localPair = (Pair)DelimitedRangesSequence.access$getGetNextMatch$p(this$0).invoke(DelimitedRangesSequence.access$getInput$p(this$0), Integer.valueOf(nextSearchIndex));
        if (localPair == null)
        {
          nextItem = new IntRange(currentStartIndex, StringsKt.getLastIndex(DelimitedRangesSequence.access$getInput$p(this$0)));
          nextSearchIndex = -1;
        }
        else
        {
          int k = ((Number)localPair.component1()).intValue();
          j = ((Number)localPair.component2()).intValue();
          nextItem = RangesKt.until(currentStartIndex, k);
          currentStartIndex = (k + j);
          k = currentStartIndex;
          if (j == 0) {
            i = 1;
          }
          nextSearchIndex = (k + i);
        }
        label241:
        nextState = 1;
      }
      
      public final int getCounter()
      {
        return counter;
      }
      
      public final int getCurrentStartIndex()
      {
        return currentStartIndex;
      }
      
      @Nullable
      public final IntRange getNextItem()
      {
        return nextItem;
      }
      
      public final int getNextSearchIndex()
      {
        return nextSearchIndex;
      }
      
      public final int getNextState()
      {
        return nextState;
      }
      
      public boolean hasNext()
      {
        if (nextState == -1) {
          calcNext();
        }
        return nextState == 1;
      }
      
      @NotNull
      public IntRange next()
      {
        if (nextState == -1) {
          calcNext();
        }
        if (nextState == 0) {
          throw ((Throwable)new NoSuchElementException());
        }
        IntRange localIntRange = nextItem;
        if (localIntRange == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.ranges.IntRange");
        }
        nextItem = ((IntRange)null);
        nextState = -1;
        return localIntRange;
      }
      
      public void remove()
      {
        throw new UnsupportedOperationException("Operation is not supported for read-only collection");
      }
      
      public final void setCounter(int paramAnonymousInt)
      {
        counter = paramAnonymousInt;
      }
      
      public final void setCurrentStartIndex(int paramAnonymousInt)
      {
        currentStartIndex = paramAnonymousInt;
      }
      
      public final void setNextItem(@Nullable IntRange paramAnonymousIntRange)
      {
        nextItem = paramAnonymousIntRange;
      }
      
      public final void setNextSearchIndex(int paramAnonymousInt)
      {
        nextSearchIndex = paramAnonymousInt;
      }
      
      public final void setNextState(int paramAnonymousInt)
      {
        nextState = paramAnonymousInt;
      }
    };
  }
}

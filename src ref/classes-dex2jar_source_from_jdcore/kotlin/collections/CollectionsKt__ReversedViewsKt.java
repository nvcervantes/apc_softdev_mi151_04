package kotlin.collections;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\030\n\000\n\002\020 \n\000\n\002\020!\n\002\b\002\n\002\020\b\n\002\b\005\032\034\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\001\032#\020\000\032\b\022\004\022\002H\0020\003\"\004\b\000\020\002*\b\022\004\022\002H\0020\003H\007¢\006\002\b\004\032\035\020\005\032\0020\006*\006\022\002\b\0030\0012\006\020\007\032\0020\006H\002¢\006\002\b\b\032\035\020\t\032\0020\006*\006\022\002\b\0030\0012\006\020\007\032\0020\006H\002¢\006\002\b\n¨\006\013"}, d2={"asReversed", "", "T", "", "asReversedMutable", "reverseElementIndex", "", "index", "reverseElementIndex$CollectionsKt__ReversedViewsKt", "reversePositionIndex", "reversePositionIndex$CollectionsKt__ReversedViewsKt", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/collections/CollectionsKt")
class CollectionsKt__ReversedViewsKt
  extends CollectionsKt__MutableCollectionsKt
{
  public CollectionsKt__ReversedViewsKt() {}
  
  @NotNull
  public static final <T> List<T> asReversed(@NotNull List<? extends T> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "$receiver");
    return (List)new ReversedListReadOnly(paramList);
  }
  
  @JvmName(name="asReversedMutable")
  @NotNull
  public static final <T> List<T> asReversedMutable(@NotNull List<T> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "$receiver");
    return (List)new ReversedList(paramList);
  }
  
  private static final int reverseElementIndex$CollectionsKt__ReversedViewsKt(@NotNull List<?> paramList, int paramInt)
  {
    int i = CollectionsKt.getLastIndex(paramList);
    if ((paramInt >= 0) && (i >= paramInt)) {
      return CollectionsKt.getLastIndex(paramList) - paramInt;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Element index ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" must be in range [");
    localStringBuilder.append(new IntRange(0, CollectionsKt.getLastIndex(paramList)));
    localStringBuilder.append("].");
    throw ((Throwable)new IndexOutOfBoundsException(localStringBuilder.toString()));
  }
  
  private static final int reversePositionIndex$CollectionsKt__ReversedViewsKt(@NotNull List<?> paramList, int paramInt)
  {
    int i = paramList.size();
    if ((paramInt >= 0) && (i >= paramInt)) {
      return paramList.size() - paramInt;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Position index ");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" must be in range [");
    localStringBuilder.append(new IntRange(0, paramList.size()));
    localStringBuilder.append("].");
    throw ((Throwable)new IndexOutOfBoundsException(localStringBuilder.toString()));
  }
}

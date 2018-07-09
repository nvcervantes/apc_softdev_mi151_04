package kotlin;

import java.util.List;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.JvmName;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\026\n\000\n\002\030\002\n\002\b\005\n\002\020 \n\000\n\002\030\002\n\000\0322\020\000\032\016\022\004\022\002H\002\022\004\022\002H\0030\001\"\004\b\000\020\002\"\004\b\001\020\003*\002H\0022\006\020\004\032\002H\003H\004¢\006\002\020\005\032\"\020\006\032\b\022\004\022\002H\b0\007\"\004\b\000\020\b*\016\022\004\022\002H\b\022\004\022\002H\b0\001\032(\020\006\032\b\022\004\022\002H\b0\007\"\004\b\000\020\b*\024\022\004\022\002H\b\022\004\022\002H\b\022\004\022\002H\b0\t¨\006\n"}, d2={"to", "Lkotlin/Pair;", "A", "B", "that", "(Ljava/lang/Object;Ljava/lang/Object;)Lkotlin/Pair;", "toList", "", "T", "Lkotlin/Triple;", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
@JvmName(name="TuplesKt")
public final class TuplesKt
{
  @NotNull
  public static final <A, B> Pair<A, B> to(A paramA, B paramB)
  {
    return new Pair(paramA, paramB);
  }
  
  @NotNull
  public static final <T> List<T> toList(@NotNull Pair<? extends T, ? extends T> paramPair)
  {
    Intrinsics.checkParameterIsNotNull(paramPair, "$receiver");
    return CollectionsKt.listOf(new Object[] { paramPair.getFirst(), paramPair.getSecond() });
  }
  
  @NotNull
  public static final <T> List<T> toList(@NotNull Triple<? extends T, ? extends T, ? extends T> paramTriple)
  {
    Intrinsics.checkParameterIsNotNull(paramTriple, "$receiver");
    return CollectionsKt.listOf(new Object[] { paramTriple.getFirst(), paramTriple.getSecond(), paramTriple.getThird() });
  }
}

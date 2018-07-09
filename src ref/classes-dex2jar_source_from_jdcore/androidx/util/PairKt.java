package androidx.util;

import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\024\n\002\b\004\n\002\030\002\n\002\b\003\n\002\030\002\n\002\b\002\0322\020\000\032\n \002*\004\030\001H\001H\001\"\004\b\000\020\001\"\004\b\001\020\003*\016\022\004\022\002H\001\022\004\022\002H\0030\004H\n¢\006\002\020\005\0322\020\006\032\n \002*\004\030\001H\003H\003\"\004\b\000\020\001\"\004\b\001\020\003*\016\022\004\022\002H\001\022\004\022\002H\0030\004H\n¢\006\002\020\005\0321\020\007\032\016\022\004\022\002H\001\022\004\022\002H\0030\004\"\004\b\000\020\001\"\004\b\001\020\003*\016\022\004\022\002H\001\022\004\022\002H\0030\bH\b\032A\020\t\032\036\022\f\022\n \002*\004\030\001H\001H\001\022\f\022\n \002*\004\030\001H\003H\0030\b\"\004\b\000\020\001\"\004\b\001\020\003*\016\022\004\022\002H\001\022\004\022\002H\0030\004H\b¨\006\n"}, d2={"component1", "F", "kotlin.jvm.PlatformType", "S", "Landroid/util/Pair;", "(Landroid/util/Pair;)Ljava/lang/Object;", "component2", "toAndroidPair", "Lkotlin/Pair;", "toKotlinPair", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class PairKt
{
  public static final <F, S> F component1(@NotNull android.util.Pair<F, S> paramPair)
  {
    return first;
  }
  
  public static final <F, S> S component2(@NotNull android.util.Pair<F, S> paramPair)
  {
    return second;
  }
  
  @NotNull
  public static final <F, S> android.util.Pair<F, S> toAndroidPair(@NotNull kotlin.Pair<? extends F, ? extends S> paramPair)
  {
    return new android.util.Pair(paramPair.getFirst(), paramPair.getSecond());
  }
  
  @NotNull
  public static final <F, S> kotlin.Pair<F, S> toKotlinPair(@NotNull android.util.Pair<F, S> paramPair)
  {
    return new kotlin.Pair(first, second);
  }
}

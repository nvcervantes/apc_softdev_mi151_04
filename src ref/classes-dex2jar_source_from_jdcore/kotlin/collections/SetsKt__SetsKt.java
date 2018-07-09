package kotlin.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.TreeSet;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.internal.InlineOnly;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000H\n\000\n\002\020\"\n\002\b\002\n\002\030\002\n\002\030\002\n\000\n\002\020\021\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020#\n\002\b\005\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\030\002\n\002\b\004\032\022\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002\032\037\020\003\032\022\022\004\022\002H\0020\004j\b\022\004\022\002H\002`\005\"\004\b\000\020\002H\b\0325\020\003\032\022\022\004\022\002H\0020\004j\b\022\004\022\002H\002`\005\"\004\b\000\020\0022\022\020\006\032\n\022\006\b\001\022\002H\0020\007\"\002H\002¢\006\002\020\b\032\037\020\t\032\022\022\004\022\002H\0020\nj\b\022\004\022\002H\002`\013\"\004\b\000\020\002H\b\0325\020\t\032\022\022\004\022\002H\0020\nj\b\022\004\022\002H\002`\013\"\004\b\000\020\0022\022\020\006\032\n\022\006\b\001\022\002H\0020\007\"\002H\002¢\006\002\020\f\032\025\020\r\032\b\022\004\022\002H\0020\016\"\004\b\000\020\002H\b\032+\020\r\032\b\022\004\022\002H\0020\016\"\004\b\000\020\0022\022\020\006\032\n\022\006\b\001\022\002H\0020\007\"\002H\002¢\006\002\020\017\032\025\020\020\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002H\b\032!\020\020\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\006\020\021\032\002H\002H\007¢\006\002\020\022\032+\020\020\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\022\020\006\032\n\022\006\b\001\022\002H\0020\007\"\002H\002¢\006\002\020\017\0327\020\023\032\022\022\004\022\002H\0020\024j\b\022\004\022\002H\002`\025\"\004\b\000\020\0022\022\020\006\032\n\022\006\b\001\022\002H\0020\007\"\002H\002H\007¢\006\002\020\026\032S\020\023\032\022\022\004\022\002H\0020\024j\b\022\004\022\002H\002`\025\"\004\b\000\020\0022\032\020\027\032\026\022\006\b\000\022\002H\0020\030j\n\022\006\b\000\022\002H\002`\0312\022\020\006\032\n\022\006\b\001\022\002H\0020\007\"\002H\002H\007¢\006\002\020\032\032\036\020\033\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\001H\000\032!\020\034\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002*\n\022\004\022\002H\002\030\0010\001H\b¨\006\035"}, d2={"emptySet", "", "T", "hashSetOf", "Ljava/util/HashSet;", "Lkotlin/collections/HashSet;", "elements", "", "([Ljava/lang/Object;)Ljava/util/HashSet;", "linkedSetOf", "Ljava/util/LinkedHashSet;", "Lkotlin/collections/LinkedHashSet;", "([Ljava/lang/Object;)Ljava/util/LinkedHashSet;", "mutableSetOf", "", "([Ljava/lang/Object;)Ljava/util/Set;", "setOf", "element", "(Ljava/lang/Object;)Ljava/util/Set;", "sortedSetOf", "Ljava/util/TreeSet;", "Lkotlin/collections/TreeSet;", "([Ljava/lang/Object;)Ljava/util/TreeSet;", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/Comparator;[Ljava/lang/Object;)Ljava/util/TreeSet;", "optimizeReadOnlySet", "orEmpty", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/collections/SetsKt")
class SetsKt__SetsKt
{
  public SetsKt__SetsKt() {}
  
  @NotNull
  public static final <T> Set<T> emptySet()
  {
    return (Set)EmptySet.INSTANCE;
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <T> HashSet<T> hashSetOf()
  {
    return new HashSet();
  }
  
  @NotNull
  public static final <T> HashSet<T> hashSetOf(@NotNull T... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "elements");
    return (HashSet)ArraysKt.toCollection(paramVarArgs, (Collection)new HashSet(MapsKt.mapCapacity(paramVarArgs.length)));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <T> LinkedHashSet<T> linkedSetOf()
  {
    return new LinkedHashSet();
  }
  
  @NotNull
  public static final <T> LinkedHashSet<T> linkedSetOf(@NotNull T... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "elements");
    return (LinkedHashSet)ArraysKt.toCollection(paramVarArgs, (Collection)new LinkedHashSet(MapsKt.mapCapacity(paramVarArgs.length)));
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <T> Set<T> mutableSetOf()
  {
    return (Set)new LinkedHashSet();
  }
  
  @NotNull
  public static final <T> Set<T> mutableSetOf(@NotNull T... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "elements");
    return (Set)ArraysKt.toCollection(paramVarArgs, (Collection)new LinkedHashSet(MapsKt.mapCapacity(paramVarArgs.length)));
  }
  
  @NotNull
  public static final <T> Set<T> optimizeReadOnlySet(@NotNull Set<? extends T> paramSet)
  {
    Intrinsics.checkParameterIsNotNull(paramSet, "$receiver");
    switch (paramSet.size())
    {
    default: 
      return paramSet;
    case 1: 
      return SetsKt.setOf(paramSet.iterator().next());
    }
    return SetsKt.emptySet();
  }
  
  @InlineOnly
  private static final <T> Set<T> orEmpty(@Nullable Set<? extends T> paramSet)
  {
    if (paramSet != null) {
      return paramSet;
    }
    return SetsKt.emptySet();
  }
  
  @InlineOnly
  private static final <T> Set<T> setOf()
  {
    return SetsKt.emptySet();
  }
  
  @NotNull
  public static final <T> Set<T> setOf(T paramT)
  {
    paramT = Collections.singleton(paramT);
    Intrinsics.checkExpressionValueIsNotNull(paramT, "java.util.Collections.singleton(element)");
    return paramT;
  }
  
  @NotNull
  public static final <T> Set<T> setOf(@NotNull T... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "elements");
    if (paramVarArgs.length > 0) {
      return ArraysKt.toSet(paramVarArgs);
    }
    return SetsKt.emptySet();
  }
  
  @NotNull
  public static final <T> TreeSet<T> sortedSetOf(@NotNull Comparator<? super T> paramComparator, @NotNull T... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "elements");
    return (TreeSet)ArraysKt.toCollection(paramVarArgs, (Collection)new TreeSet(paramComparator));
  }
  
  @NotNull
  public static final <T> TreeSet<T> sortedSetOf(@NotNull T... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "elements");
    return (TreeSet)ArraysKt.toCollection(paramVarArgs, (Collection)new TreeSet());
  }
}

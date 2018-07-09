package kotlin.collections;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Enumeration;
import java.util.List;
import kotlin.Metadata;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.comparisons.ComparisonsKt;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.CollectionToArray;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.ranges.IntRange;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000v\n\000\n\002\030\002\n\002\020\036\n\002\b\003\n\002\020\b\n\000\n\002\020 \n\002\b\005\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020!\n\000\n\002\030\002\n\002\030\002\n\000\n\002\020\021\n\002\b\002\n\002\020\000\n\002\b\f\n\002\020\002\n\002\b\007\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\017\n\002\b\007\n\002\020\013\n\002\030\002\n\002\b\b\n\002\030\002\n\000\032@\020\013\032\b\022\004\022\002H\0070\b\"\004\b\000\020\0072\006\020\f\032\0020\0062!\020\r\032\035\022\023\022\0210\006¢\006\f\b\017\022\b\b\020\022\004\b\b(\021\022\004\022\002H\0070\016H\b\032@\020\022\032\b\022\004\022\002H\0070\023\"\004\b\000\020\0072\006\020\f\032\0020\0062!\020\r\032\035\022\023\022\0210\006¢\006\f\b\017\022\b\b\020\022\004\b\b(\021\022\004\022\002H\0070\016H\b\032\037\020\024\032\022\022\004\022\002H\0070\025j\b\022\004\022\002H\007`\026\"\004\b\000\020\007H\b\0325\020\024\032\022\022\004\022\002H\0070\025j\b\022\004\022\002H\007`\026\"\004\b\000\020\0072\022\020\027\032\n\022\006\b\001\022\002H\0070\030\"\002H\007¢\006\002\020\031\032\"\020\032\032\n\022\006\022\004\030\0010\0330\0302\n\020\034\032\006\022\002\b\0030\002H\b¢\006\002\020\035\0324\020\032\032\b\022\004\022\002H\0070\030\"\004\b\000\020\0072\n\020\034\032\006\022\002\b\0030\0022\f\020\036\032\b\022\004\022\002H\0070\030H\b¢\006\002\020\037\032\022\020 \032\b\022\004\022\002H\0070\b\"\004\b\000\020\007\032\025\020!\032\b\022\004\022\002H\0070\b\"\004\b\000\020\007H\b\032!\020!\032\b\022\004\022\002H\0070\b\"\004\b\000\020\0072\006\020\"\032\002H\007H\007¢\006\002\020#\032+\020!\032\b\022\004\022\002H\0070\b\"\004\b\000\020\0072\022\020\027\032\n\022\006\b\001\022\002H\0070\030\"\002H\007¢\006\002\020$\032%\020%\032\b\022\004\022\002H\0070\b\"\b\b\000\020\007*\0020\0332\b\020\"\032\004\030\001H\007¢\006\002\020#\0323\020%\032\b\022\004\022\002H\0070\b\"\b\b\000\020\007*\0020\0332\026\020\027\032\f\022\b\b\001\022\004\030\001H\0070\030\"\004\030\001H\007¢\006\002\020$\032\025\020&\032\b\022\004\022\002H\0070\023\"\004\b\000\020\007H\b\032+\020&\032\b\022\004\022\002H\0070\023\"\004\b\000\020\0072\022\020\027\032\n\022\006\b\001\022\002H\0070\030\"\002H\007¢\006\002\020$\032%\020'\032\0020(2\006\020\f\032\0020\0062\006\020)\032\0020\0062\006\020*\032\0020\006H\002¢\006\002\b+\032%\020,\032\b\022\004\022\002H\0070\002\"\004\b\000\020\007*\n\022\006\b\001\022\002H\0070\030H\000¢\006\002\020-\032S\020.\032\0020\006\"\004\b\000\020\007*\b\022\004\022\002H\0070\b2\006\020\"\032\002H\0072\032\020/\032\026\022\006\b\000\022\002H\00700j\n\022\006\b\000\022\002H\007`12\b\b\002\020)\032\0020\0062\b\b\002\020*\032\0020\006¢\006\002\0202\032>\020.\032\0020\006\"\004\b\000\020\007*\b\022\004\022\002H\0070\b2\b\b\002\020)\032\0020\0062\b\b\002\020*\032\0020\0062\022\0203\032\016\022\004\022\002H\007\022\004\022\0020\0060\016\032E\020.\032\0020\006\"\016\b\000\020\007*\b\022\004\022\002H\00704*\n\022\006\022\004\030\001H\0070\b2\b\020\"\032\004\030\001H\0072\b\b\002\020)\032\0020\0062\b\b\002\020*\032\0020\006¢\006\002\0205\032d\0206\032\0020\006\"\004\b\000\020\007\"\016\b\001\0207*\b\022\004\022\002H704*\b\022\004\022\002H\0070\b2\b\0208\032\004\030\001H72\b\b\002\020)\032\0020\0062\b\b\002\020*\032\0020\0062\026\b\004\0209\032\020\022\004\022\002H\007\022\006\022\004\030\001H70\016H\b¢\006\002\020:\032,\020;\032\0020<\"\t\b\000\020\007¢\006\002\b=*\b\022\004\022\002H\0070\0022\f\020\027\032\b\022\004\022\002H\0070\002H\b\0321\020>\032\n\022\006\022\004\030\0010\0330\030\"\004\b\000\020\007*\n\022\006\b\001\022\002H\0070\0302\006\020?\032\0020<H\003¢\006\004\b@\020A\032\031\020B\032\0020<\"\004\b\000\020\007*\b\022\004\022\002H\0070\002H\b\032\036\020C\032\b\022\004\022\002H\0070\b\"\004\b\000\020\007*\b\022\004\022\002H\0070\bH\000\032!\020D\032\b\022\004\022\002H\0070\002\"\004\b\000\020\007*\n\022\004\022\002H\007\030\0010\002H\b\032!\020D\032\b\022\004\022\002H\0070\b\"\004\b\000\020\007*\n\022\004\022\002H\007\030\0010\bH\b\032\037\020E\032\b\022\004\022\002H\0070\b\"\004\b\000\020\007*\b\022\004\022\002H\0070FH\b\"\031\020\000\032\0020\001*\006\022\002\b\0030\0028F¢\006\006\032\004\b\003\020\004\"!\020\005\032\0020\006\"\004\b\000\020\007*\b\022\004\022\002H\0070\b8F¢\006\006\032\004\b\t\020\n¨\006G"}, d2={"indices", "Lkotlin/ranges/IntRange;", "", "getIndices", "(Ljava/util/Collection;)Lkotlin/ranges/IntRange;", "lastIndex", "", "T", "", "getLastIndex", "(Ljava/util/List;)I", "List", "size", "init", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "index", "MutableList", "", "arrayListOf", "Ljava/util/ArrayList;", "Lkotlin/collections/ArrayList;", "elements", "", "([Ljava/lang/Object;)Ljava/util/ArrayList;", "copyToArrayImpl", "", "collection", "(Ljava/util/Collection;)[Ljava/lang/Object;", "array", "(Ljava/util/Collection;[Ljava/lang/Object;)[Ljava/lang/Object;", "emptyList", "listOf", "element", "(Ljava/lang/Object;)Ljava/util/List;", "([Ljava/lang/Object;)Ljava/util/List;", "listOfNotNull", "mutableListOf", "rangeCheck", "", "fromIndex", "toIndex", "rangeCheck$CollectionsKt__CollectionsKt", "asCollection", "([Ljava/lang/Object;)Ljava/util/Collection;", "binarySearch", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "(Ljava/util/List;Ljava/lang/Object;Ljava/util/Comparator;II)I", "comparison", "", "(Ljava/util/List;Ljava/lang/Comparable;II)I", "binarySearchBy", "K", "key", "selector", "(Ljava/util/List;Ljava/lang/Comparable;IILkotlin/jvm/functions/Function1;)I", "containsAll", "", "Lkotlin/internal/OnlyInputTypes;", "copyToArrayOfAny", "isVarargs", "copyToArrayOfAny$CollectionsKt__CollectionsKt", "([Ljava/lang/Object;Z)[Ljava/lang/Object;", "isNotEmpty", "optimizeReadOnlyList", "orEmpty", "toList", "Ljava/util/Enumeration;", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/collections/CollectionsKt")
class CollectionsKt__CollectionsKt
{
  public CollectionsKt__CollectionsKt() {}
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <T> List<T> List(int paramInt, Function1<? super Integer, ? extends T> paramFunction1)
  {
    ArrayList localArrayList = new ArrayList(paramInt);
    int i = 0;
    while (i < paramInt)
    {
      localArrayList.add(paramFunction1.invoke(Integer.valueOf(i)));
      i += 1;
    }
    return (List)localArrayList;
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <T> List<T> MutableList(int paramInt, Function1<? super Integer, ? extends T> paramFunction1)
  {
    ArrayList localArrayList = new ArrayList(paramInt);
    int i = 0;
    while (i < paramInt)
    {
      localArrayList.add(paramFunction1.invoke(Integer.valueOf(i)));
      i += 1;
    }
    return (List)localArrayList;
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <T> ArrayList<T> arrayListOf()
  {
    return new ArrayList();
  }
  
  @NotNull
  public static final <T> ArrayList<T> arrayListOf(@NotNull T... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "elements");
    if (paramVarArgs.length == 0) {
      return new ArrayList();
    }
    return new ArrayList((Collection)new ArrayAsCollection(paramVarArgs, true));
  }
  
  @NotNull
  public static final <T> Collection<T> asCollection(@NotNull T[] paramArrayOfT)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfT, "$receiver");
    return (Collection)new ArrayAsCollection(paramArrayOfT, false);
  }
  
  public static final <T> int binarySearch(@NotNull List<? extends T> paramList, int paramInt1, int paramInt2, @NotNull Function1<? super T, Integer> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "comparison");
    rangeCheck$CollectionsKt__CollectionsKt(paramList.size(), paramInt1, paramInt2);
    paramInt2 -= 1;
    while (paramInt1 <= paramInt2)
    {
      int i = paramInt1 + paramInt2 >>> 1;
      int j = ((Number)paramFunction1.invoke(paramList.get(i))).intValue();
      if (j < 0) {
        paramInt1 = i + 1;
      } else if (j > 0) {
        paramInt2 = i - 1;
      } else {
        return i;
      }
    }
    return -(paramInt1 + 1);
  }
  
  public static final <T extends Comparable<? super T>> int binarySearch(@NotNull List<? extends T> paramList, @Nullable T paramT, int paramInt1, int paramInt2)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "$receiver");
    rangeCheck$CollectionsKt__CollectionsKt(paramList.size(), paramInt1, paramInt2);
    paramInt2 -= 1;
    while (paramInt1 <= paramInt2)
    {
      int i = paramInt1 + paramInt2 >>> 1;
      int j = ComparisonsKt.compareValues((Comparable)paramList.get(i), paramT);
      if (j < 0) {
        paramInt1 = i + 1;
      } else if (j > 0) {
        paramInt2 = i - 1;
      } else {
        return i;
      }
    }
    return -(paramInt1 + 1);
  }
  
  public static final <T> int binarySearch(@NotNull List<? extends T> paramList, T paramT, @NotNull Comparator<? super T> paramComparator, int paramInt1, int paramInt2)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    rangeCheck$CollectionsKt__CollectionsKt(paramList.size(), paramInt1, paramInt2);
    paramInt2 -= 1;
    while (paramInt1 <= paramInt2)
    {
      int i = paramInt1 + paramInt2 >>> 1;
      int j = paramComparator.compare(paramList.get(i), paramT);
      if (j < 0) {
        paramInt1 = i + 1;
      } else if (j > 0) {
        paramInt2 = i - 1;
      } else {
        return i;
      }
    }
    return -(paramInt1 + 1);
  }
  
  public static final <T, K extends Comparable<? super K>> int binarySearchBy(@NotNull List<? extends T> paramList, @Nullable final K paramK, int paramInt1, int paramInt2, @NotNull Function1<? super T, ? extends K> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "selector");
    CollectionsKt.binarySearch(paramList, paramInt1, paramInt2, (Function1)new Lambda(paramFunction1)
    {
      public final int invoke(T paramAnonymousT)
      {
        return ComparisonsKt.compareValues((Comparable)$selector.invoke(paramAnonymousT), paramK);
      }
    });
  }
  
  @InlineOnly
  private static final <T> boolean containsAll(@NotNull Collection<? extends T> paramCollection1, Collection<? extends T> paramCollection2)
  {
    return paramCollection1.containsAll(paramCollection2);
  }
  
  @InlineOnly
  private static final Object[] copyToArrayImpl(Collection<?> paramCollection)
  {
    return CollectionToArray.toArray(paramCollection);
  }
  
  @InlineOnly
  private static final <T> T[] copyToArrayImpl(Collection<?> paramCollection, T[] paramArrayOfT)
  {
    if (paramArrayOfT == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
    }
    paramCollection = CollectionToArray.toArray(paramCollection, paramArrayOfT);
    if (paramCollection == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
    return paramCollection;
  }
  
  private static final <T> Object[] copyToArrayOfAny$CollectionsKt__CollectionsKt(@NotNull T[] paramArrayOfT, boolean paramBoolean)
  {
    Object localObject;
    if ((paramBoolean) && (Intrinsics.areEqual(paramArrayOfT.getClass(), [Ljava.lang.Object.class)))
    {
      localObject = paramArrayOfT;
      if (paramArrayOfT == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.Any?>");
      }
    }
    else
    {
      localObject = Arrays.copyOf(paramArrayOfT, paramArrayOfT.length, [Ljava.lang.Object.class);
      Intrinsics.checkExpressionValueIsNotNull(localObject, "java.util.Arrays.copyOf(… Array<Any?>::class.java)");
    }
    return localObject;
  }
  
  @NotNull
  public static final <T> List<T> emptyList()
  {
    return (List)EmptyList.INSTANCE;
  }
  
  @NotNull
  public static final IntRange getIndices(@NotNull Collection<?> paramCollection)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    return new IntRange(0, paramCollection.size() - 1);
  }
  
  public static final <T> int getLastIndex(@NotNull List<? extends T> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "$receiver");
    return paramList.size() - 1;
  }
  
  @InlineOnly
  private static final <T> boolean isNotEmpty(@NotNull Collection<? extends T> paramCollection)
  {
    return paramCollection.isEmpty() ^ true;
  }
  
  @InlineOnly
  private static final <T> List<T> listOf()
  {
    return CollectionsKt.emptyList();
  }
  
  @NotNull
  public static final <T> List<T> listOf(T paramT)
  {
    paramT = Collections.singletonList(paramT);
    Intrinsics.checkExpressionValueIsNotNull(paramT, "java.util.Collections.singletonList(element)");
    return paramT;
  }
  
  @NotNull
  public static final <T> List<T> listOf(@NotNull T... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "elements");
    if (paramVarArgs.length > 0) {
      return ArraysKt.asList(paramVarArgs);
    }
    return CollectionsKt.emptyList();
  }
  
  @NotNull
  public static final <T> List<T> listOfNotNull(@Nullable T paramT)
  {
    if (paramT != null) {
      return CollectionsKt.listOf(paramT);
    }
    return CollectionsKt.emptyList();
  }
  
  @NotNull
  public static final <T> List<T> listOfNotNull(@NotNull T... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "elements");
    return ArraysKt.filterNotNull(paramVarArgs);
  }
  
  @SinceKotlin(version="1.1")
  @InlineOnly
  private static final <T> List<T> mutableListOf()
  {
    return (List)new ArrayList();
  }
  
  @NotNull
  public static final <T> List<T> mutableListOf(@NotNull T... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "elements");
    if (paramVarArgs.length == 0) {
      return (List)new ArrayList();
    }
    return (List)new ArrayList((Collection)new ArrayAsCollection(paramVarArgs, true));
  }
  
  @NotNull
  public static final <T> List<T> optimizeReadOnlyList(@NotNull List<? extends T> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "$receiver");
    switch (paramList.size())
    {
    default: 
      return paramList;
    case 1: 
      return CollectionsKt.listOf(paramList.get(0));
    }
    return CollectionsKt.emptyList();
  }
  
  @InlineOnly
  private static final <T> Collection<T> orEmpty(@Nullable Collection<? extends T> paramCollection)
  {
    if (paramCollection != null) {
      return paramCollection;
    }
    return (Collection)CollectionsKt.emptyList();
  }
  
  @InlineOnly
  private static final <T> List<T> orEmpty(@Nullable List<? extends T> paramList)
  {
    if (paramList != null) {
      return paramList;
    }
    return CollectionsKt.emptyList();
  }
  
  private static final void rangeCheck$CollectionsKt__CollectionsKt(int paramInt1, int paramInt2, int paramInt3)
  {
    StringBuilder localStringBuilder;
    if (paramInt2 > paramInt3)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("fromIndex (");
      localStringBuilder.append(paramInt2);
      localStringBuilder.append(") is greater than toIndex (");
      localStringBuilder.append(paramInt3);
      localStringBuilder.append(").");
      throw ((Throwable)new IllegalArgumentException(localStringBuilder.toString()));
    }
    if (paramInt2 < 0)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("fromIndex (");
      localStringBuilder.append(paramInt2);
      localStringBuilder.append(") is less than zero.");
      throw ((Throwable)new IndexOutOfBoundsException(localStringBuilder.toString()));
    }
    if (paramInt3 > paramInt1)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("toIndex (");
      localStringBuilder.append(paramInt3);
      localStringBuilder.append(") is greater than size (");
      localStringBuilder.append(paramInt1);
      localStringBuilder.append(").");
      throw ((Throwable)new IndexOutOfBoundsException(localStringBuilder.toString()));
    }
  }
  
  @InlineOnly
  private static final <T> List<T> toList(@NotNull Enumeration<T> paramEnumeration)
  {
    paramEnumeration = Collections.list(paramEnumeration);
    Intrinsics.checkExpressionValueIsNotNull(paramEnumeration, "java.util.Collections.list(this)");
    return (List)paramEnumeration;
  }
}

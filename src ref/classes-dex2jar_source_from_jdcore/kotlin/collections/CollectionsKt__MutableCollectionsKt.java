package kotlin.collections;

import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.RandomAccess;
import kotlin.Deprecated;
import kotlin.DeprecationLevel;
import kotlin.Metadata;
import kotlin.NotImplementedError;
import kotlin.ReplaceWith;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.TypeIntrinsics;
import kotlin.sequences.Sequence;
import kotlin.sequences.SequencesKt;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000t\n\000\n\002\020\013\n\000\n\002\020\037\n\000\n\002\020\021\n\000\n\002\020\034\n\002\030\002\n\000\n\002\020\002\n\002\020!\n\002\b\003\n\002\020\035\n\000\n\002\030\002\n\002\b\b\n\002\030\002\n\002\b\002\n\002\020\b\n\002\b\002\n\002\020\036\n\002\b\005\n\002\030\002\n\000\n\002\020 \n\000\n\002\020\017\n\000\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\032-\020\000\032\0020\001\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\016\020\004\032\n\022\006\b\001\022\002H\0020\005¢\006\002\020\006\032&\020\000\032\0020\001\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020\007\032&\020\000\032\0020\001\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020\b\032&\020\t\032\0020\n\"\004\b\000\020\002*\b\022\004\022\002H\0020\0132\006\020\f\032\002H\002H\b¢\006\002\020\r\0329\020\016\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0172\022\020\020\032\016\022\004\022\002H\002\022\004\022\0020\0010\0212\006\020\022\032\0020\001H\002¢\006\002\b\023\0329\020\016\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0132\022\020\020\032\016\022\004\022\002H\002\022\004\022\0020\0010\0212\006\020\022\032\0020\001H\002¢\006\002\b\023\032(\020\024\032\0020\n\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\006\020\025\032\002H\002H\n¢\006\002\020\026\032.\020\024\032\0020\n\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020\005H\n¢\006\002\020\027\032)\020\024\032\0020\n\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020\007H\n\032)\020\024\032\0020\n\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020\bH\n\032(\020\030\032\0020\n\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\006\020\025\032\002H\002H\n¢\006\002\020\026\032.\020\030\032\0020\n\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020\005H\n¢\006\002\020\027\032)\020\030\032\0020\n\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020\007H\n\032)\020\030\032\0020\n\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020\bH\n\032-\020\031\032\0020\001\"\t\b\000\020\002¢\006\002\b\032*\n\022\006\b\001\022\002H\0020\0032\006\020\025\032\002H\002H\b¢\006\002\020\033\032&\020\031\032\002H\002\"\004\b\000\020\002*\b\022\004\022\002H\0020\0132\006\020\034\032\0020\035H\b¢\006\002\020\036\032-\020\037\032\0020\001\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\016\020\004\032\n\022\006\b\001\022\002H\0020\005¢\006\002\020\006\032&\020\037\032\0020\001\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020\007\032&\020\037\032\0020\001\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020\b\032.\020\037\032\0020\001\"\t\b\000\020\002¢\006\002\b\032*\n\022\006\b\001\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020 H\b\032*\020\037\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0172\022\020\020\032\016\022\004\022\002H\002\022\004\022\0020\0010\021\032*\020\037\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0132\022\020\020\032\016\022\004\022\002H\002\022\004\022\0020\0010\021\032-\020!\032\0020\001\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\016\020\004\032\n\022\006\b\001\022\002H\0020\005¢\006\002\020\006\032&\020!\032\0020\001\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020\007\032&\020!\032\0020\001\"\004\b\000\020\002*\n\022\006\b\000\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020\b\032.\020!\032\0020\001\"\t\b\000\020\002¢\006\002\b\032*\n\022\006\b\001\022\002H\0020\0032\f\020\004\032\b\022\004\022\002H\0020 H\b\032*\020!\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0172\022\020\020\032\016\022\004\022\002H\002\022\004\022\0020\0010\021\032*\020!\032\0020\001\"\004\b\000\020\002*\b\022\004\022\002H\0020\0132\022\020\020\032\016\022\004\022\002H\002\022\004\022\0020\0010\021\032\025\020\"\032\0020\001*\006\022\002\b\0030\003H\002¢\006\002\b#\032\031\020$\032\0020\n\"\004\b\000\020\002*\b\022\004\022\002H\0020\013H\b\032!\020$\032\0020\n\"\004\b\000\020\002*\b\022\004\022\002H\0020\0132\006\020%\032\0020&H\b\032\036\020'\032\b\022\004\022\002H\0020(\"\004\b\000\020\002*\b\022\004\022\002H\0020\007H\007\032&\020'\032\b\022\004\022\002H\0020(\"\004\b\000\020\002*\b\022\004\022\002H\0020\0072\006\020%\032\0020&H\007\032\"\020)\032\0020\n\"\016\b\000\020\002*\b\022\004\022\002H\0020**\b\022\004\022\002H\0020\013H\007\0323\020)\032\0020\n\"\004\b\000\020\002*\b\022\004\022\002H\0020\0132\030\020+\032\024\022\004\022\002H\002\022\004\022\002H\002\022\004\022\0020\0350,H\b\0325\020)\032\0020\n\"\004\b\000\020\002*\b\022\004\022\002H\0020\0132\032\020-\032\026\022\006\b\000\022\002H\0020.j\n\022\006\b\000\022\002H\002`/H\b\0324\0200\032\0020\n\"\004\b\000\020\002*\b\022\004\022\002H\0020\0132\032\020-\032\026\022\006\b\000\022\002H\0020.j\n\022\006\b\000\022\002H\002`/H\007¨\0061"}, d2={"addAll", "", "T", "", "elements", "", "(Ljava/util/Collection;[Ljava/lang/Object;)Z", "", "Lkotlin/sequences/Sequence;", "fill", "", "", "value", "(Ljava/util/List;Ljava/lang/Object;)V", "filterInPlace", "", "predicate", "Lkotlin/Function1;", "predicateResultToRemove", "filterInPlace$CollectionsKt__MutableCollectionsKt", "minusAssign", "element", "(Ljava/util/Collection;Ljava/lang/Object;)V", "(Ljava/util/Collection;[Ljava/lang/Object;)V", "plusAssign", "remove", "Lkotlin/internal/OnlyInputTypes;", "(Ljava/util/Collection;Ljava/lang/Object;)Z", "index", "", "(Ljava/util/List;I)Ljava/lang/Object;", "removeAll", "", "retainAll", "retainNothing", "retainNothing$CollectionsKt__MutableCollectionsKt", "shuffle", "random", "Ljava/util/Random;", "shuffled", "", "sort", "", "comparison", "Lkotlin/Function2;", "comparator", "Ljava/util/Comparator;", "Lkotlin/Comparator;", "sortWith", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/collections/CollectionsKt")
class CollectionsKt__MutableCollectionsKt
  extends CollectionsKt__IteratorsKt
{
  public CollectionsKt__MutableCollectionsKt() {}
  
  public static final <T> boolean addAll(@NotNull Collection<? super T> paramCollection, @NotNull Iterable<? extends T> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIterable, "elements");
    if ((paramIterable instanceof Collection)) {
      return paramCollection.addAll((Collection)paramIterable);
    }
    boolean bool = false;
    paramIterable = paramIterable.iterator();
    while (paramIterable.hasNext()) {
      if (paramCollection.add(paramIterable.next())) {
        bool = true;
      }
    }
    return bool;
  }
  
  public static final <T> boolean addAll(@NotNull Collection<? super T> paramCollection, @NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramSequence, "elements");
    paramSequence = paramSequence.iterator();
    boolean bool = false;
    while (paramSequence.hasNext()) {
      if (paramCollection.add(paramSequence.next())) {
        bool = true;
      }
    }
    return bool;
  }
  
  public static final <T> boolean addAll(@NotNull Collection<? super T> paramCollection, @NotNull T[] paramArrayOfT)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfT, "elements");
    return paramCollection.addAll((Collection)ArraysKt.asList(paramArrayOfT));
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final <T> void fill(@NotNull List<T> paramList, T paramT)
  {
    Collections.fill(paramList, paramT);
  }
  
  private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(@NotNull Iterable<? extends T> paramIterable, Function1<? super T, Boolean> paramFunction1, boolean paramBoolean)
  {
    paramIterable = paramIterable.iterator();
    boolean bool = false;
    while (paramIterable.hasNext()) {
      if (((Boolean)paramFunction1.invoke(paramIterable.next())).booleanValue() == paramBoolean)
      {
        paramIterable.remove();
        bool = true;
      }
    }
    return bool;
  }
  
  private static final <T> boolean filterInPlace$CollectionsKt__MutableCollectionsKt(@NotNull List<T> paramList, Function1<? super T, Boolean> paramFunction1, boolean paramBoolean)
  {
    if (!(paramList instanceof RandomAccess))
    {
      if (paramList == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableIterable<T>");
      }
      return filterInPlace$CollectionsKt__MutableCollectionsKt(TypeIntrinsics.asMutableIterable(paramList), paramFunction1, paramBoolean);
    }
    int m = CollectionsKt.getLastIndex(paramList);
    int i;
    if (m >= 0)
    {
      int k = 0;
      i = k;
      for (;;)
      {
        Object localObject = paramList.get(k);
        if (((Boolean)paramFunction1.invoke(localObject)).booleanValue() != paramBoolean)
        {
          if (i != k) {
            paramList.set(i, localObject);
          }
          i += 1;
        }
        j = i;
        if (k == m) {
          break;
        }
        k += 1;
      }
    }
    int j = 0;
    if (j < paramList.size())
    {
      i = CollectionsKt.getLastIndex(paramList);
      if (i >= j) {
        for (;;)
        {
          paramList.remove(i);
          if (i == j) {
            break;
          }
          i -= 1;
        }
      }
      return true;
    }
    return false;
  }
  
  @InlineOnly
  private static final <T> void minusAssign(@NotNull Collection<? super T> paramCollection, Iterable<? extends T> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    CollectionsKt.removeAll(paramCollection, paramIterable);
  }
  
  @InlineOnly
  private static final <T> void minusAssign(@NotNull Collection<? super T> paramCollection, T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    paramCollection.remove(paramT);
  }
  
  @InlineOnly
  private static final <T> void minusAssign(@NotNull Collection<? super T> paramCollection, Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    CollectionsKt.removeAll(paramCollection, paramSequence);
  }
  
  @InlineOnly
  private static final <T> void minusAssign(@NotNull Collection<? super T> paramCollection, T[] paramArrayOfT)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    CollectionsKt.removeAll(paramCollection, paramArrayOfT);
  }
  
  @InlineOnly
  private static final <T> void plusAssign(@NotNull Collection<? super T> paramCollection, Iterable<? extends T> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    CollectionsKt.addAll(paramCollection, paramIterable);
  }
  
  @InlineOnly
  private static final <T> void plusAssign(@NotNull Collection<? super T> paramCollection, T paramT)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    paramCollection.add(paramT);
  }
  
  @InlineOnly
  private static final <T> void plusAssign(@NotNull Collection<? super T> paramCollection, Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    CollectionsKt.addAll(paramCollection, paramSequence);
  }
  
  @InlineOnly
  private static final <T> void plusAssign(@NotNull Collection<? super T> paramCollection, T[] paramArrayOfT)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    CollectionsKt.addAll(paramCollection, paramArrayOfT);
  }
  
  @Deprecated(level=DeprecationLevel.ERROR, message="Use removeAt(index) instead.", replaceWith=@ReplaceWith(expression="removeAt(index)", imports={}))
  @InlineOnly
  private static final <T> T remove(@NotNull List<T> paramList, int paramInt)
  {
    return paramList.remove(paramInt);
  }
  
  @InlineOnly
  private static final <T> boolean remove(@NotNull Collection<? extends T> paramCollection, T paramT)
  {
    if (paramCollection == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }
    return TypeIntrinsics.asMutableCollection(paramCollection).remove(paramT);
  }
  
  public static final <T> boolean removeAll(@NotNull Iterable<? extends T> paramIterable, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramIterable, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    return filterInPlace$CollectionsKt__MutableCollectionsKt(paramIterable, paramFunction1, true);
  }
  
  public static final <T> boolean removeAll(@NotNull Collection<? super T> paramCollection, @NotNull Iterable<? extends T> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIterable, "elements");
    paramIterable = CollectionsKt.convertToSetForSetOperationWith(paramIterable, (Iterable)paramCollection);
    return TypeIntrinsics.asMutableCollection(paramCollection).removeAll(paramIterable);
  }
  
  @InlineOnly
  private static final <T> boolean removeAll(@NotNull Collection<? extends T> paramCollection1, Collection<? extends T> paramCollection2)
  {
    if (paramCollection1 == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }
    return TypeIntrinsics.asMutableCollection(paramCollection1).removeAll(paramCollection2);
  }
  
  public static final <T> boolean removeAll(@NotNull Collection<? super T> paramCollection, @NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramSequence, "elements");
    paramSequence = (Collection)SequencesKt.toHashSet(paramSequence);
    return ((paramSequence.isEmpty() ^ true)) && (paramCollection.removeAll(paramSequence));
  }
  
  public static final <T> boolean removeAll(@NotNull Collection<? super T> paramCollection, @NotNull T[] paramArrayOfT)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfT, "elements");
    boolean bool2 = false;
    int i;
    if (paramArrayOfT.length == 0) {
      i = 1;
    } else {
      i = 0;
    }
    boolean bool1 = bool2;
    if ((i ^ 0x1) != 0)
    {
      bool1 = bool2;
      if (paramCollection.removeAll((Collection)ArraysKt.toHashSet(paramArrayOfT))) {
        bool1 = true;
      }
    }
    return bool1;
  }
  
  public static final <T> boolean removeAll(@NotNull List<T> paramList, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    return filterInPlace$CollectionsKt__MutableCollectionsKt(paramList, paramFunction1, true);
  }
  
  public static final <T> boolean retainAll(@NotNull Iterable<? extends T> paramIterable, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramIterable, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    return filterInPlace$CollectionsKt__MutableCollectionsKt(paramIterable, paramFunction1, false);
  }
  
  public static final <T> boolean retainAll(@NotNull Collection<? super T> paramCollection, @NotNull Iterable<? extends T> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramIterable, "elements");
    paramIterable = CollectionsKt.convertToSetForSetOperationWith(paramIterable, (Iterable)paramCollection);
    return TypeIntrinsics.asMutableCollection(paramCollection).retainAll(paramIterable);
  }
  
  @InlineOnly
  private static final <T> boolean retainAll(@NotNull Collection<? extends T> paramCollection1, Collection<? extends T> paramCollection2)
  {
    if (paramCollection1 == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableCollection<T>");
    }
    return TypeIntrinsics.asMutableCollection(paramCollection1).retainAll(paramCollection2);
  }
  
  public static final <T> boolean retainAll(@NotNull Collection<? super T> paramCollection, @NotNull Sequence<? extends T> paramSequence)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramSequence, "elements");
    paramSequence = (Collection)SequencesKt.toHashSet(paramSequence);
    if ((paramSequence.isEmpty() ^ true)) {
      return paramCollection.retainAll(paramSequence);
    }
    return retainNothing$CollectionsKt__MutableCollectionsKt(paramCollection);
  }
  
  public static final <T> boolean retainAll(@NotNull Collection<? super T> paramCollection, @NotNull T[] paramArrayOfT)
  {
    Intrinsics.checkParameterIsNotNull(paramCollection, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramArrayOfT, "elements");
    int i;
    if (paramArrayOfT.length == 0) {
      i = 1;
    } else {
      i = 0;
    }
    if ((0x1 ^ i) != 0) {
      return paramCollection.retainAll((Collection)ArraysKt.toHashSet(paramArrayOfT));
    }
    return retainNothing$CollectionsKt__MutableCollectionsKt(paramCollection);
  }
  
  public static final <T> boolean retainAll(@NotNull List<T> paramList, @NotNull Function1<? super T, Boolean> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "predicate");
    return filterInPlace$CollectionsKt__MutableCollectionsKt(paramList, paramFunction1, false);
  }
  
  private static final boolean retainNothing$CollectionsKt__MutableCollectionsKt(@NotNull Collection<?> paramCollection)
  {
    boolean bool = paramCollection.isEmpty();
    paramCollection.clear();
    return bool ^ true;
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final <T> void shuffle(@NotNull List<T> paramList)
  {
    Collections.shuffle(paramList);
  }
  
  @SinceKotlin(version="1.2")
  @InlineOnly
  private static final <T> void shuffle(@NotNull List<T> paramList, Random paramRandom)
  {
    Collections.shuffle(paramList, paramRandom);
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> paramIterable)
  {
    Intrinsics.checkParameterIsNotNull(paramIterable, "$receiver");
    paramIterable = CollectionsKt.toMutableList(paramIterable);
    Collections.shuffle(paramIterable);
    return paramIterable;
  }
  
  @SinceKotlin(version="1.2")
  @NotNull
  public static final <T> List<T> shuffled(@NotNull Iterable<? extends T> paramIterable, @NotNull Random paramRandom)
  {
    Intrinsics.checkParameterIsNotNull(paramIterable, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramRandom, "random");
    paramIterable = CollectionsKt.toMutableList(paramIterable);
    Collections.shuffle(paramIterable, paramRandom);
    return paramIterable;
  }
  
  public static final <T extends Comparable<? super T>> void sort(@NotNull List<T> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "$receiver");
    if (paramList.size() > 1) {
      Collections.sort(paramList);
    }
  }
  
  @Deprecated(level=DeprecationLevel.ERROR, message="Use sortWith(comparator) instead.", replaceWith=@ReplaceWith(expression="this.sortWith(comparator)", imports={}))
  @InlineOnly
  private static final <T> void sort(@NotNull List<T> paramList, Comparator<? super T> paramComparator)
  {
    throw ((Throwable)new NotImplementedError(null, 1, null));
  }
  
  @Deprecated(level=DeprecationLevel.ERROR, message="Use sortWith(Comparator(comparison)) instead.", replaceWith=@ReplaceWith(expression="this.sortWith(Comparator(comparison))", imports={}))
  @InlineOnly
  private static final <T> void sort(@NotNull List<T> paramList, Function2<? super T, ? super T, Integer> paramFunction2)
  {
    throw ((Throwable)new NotImplementedError(null, 1, null));
  }
  
  public static final <T> void sortWith(@NotNull List<T> paramList, @NotNull Comparator<? super T> paramComparator)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    if (paramList.size() > 1) {
      Collections.sort(paramList, paramComparator);
    }
  }
}

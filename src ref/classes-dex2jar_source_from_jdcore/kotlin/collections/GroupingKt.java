package kotlin.collections;

import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.SinceKotlin;
import kotlin.TypeCastException;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function3;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref.IntRef;
import kotlin.jvm.internal.TypeIntrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000L\n\000\n\002\020$\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\004\n\002\020\013\n\002\b\003\n\002\020%\n\002\b\003\n\002\020\b\n\002\b\004\n\002\030\002\n\002\030\002\n\002\b\b\n\002\030\002\n\002\020&\n\002\b\005\032\001\020\000\032\016\022\004\022\002H\002\022\004\022\002H\0030\001\"\004\b\000\020\004\"\004\b\001\020\002\"\004\b\002\020\003*\016\022\004\022\002H\004\022\004\022\002H\0020\0052b\020\006\032^\022\023\022\021H\002¢\006\f\b\b\022\b\b\t\022\004\b\b(\n\022\025\022\023\030\001H\003¢\006\f\b\b\022\b\b\t\022\004\b\b(\013\022\023\022\021H\004¢\006\f\b\b\022\b\b\t\022\004\b\b(\f\022\023\022\0210\r¢\006\f\b\b\022\b\b\t\022\004\b\b(\016\022\004\022\002H\0030\007H\b\032´\001\020\017\032\002H\020\"\004\b\000\020\004\"\004\b\001\020\002\"\004\b\002\020\003\"\026\b\003\020\020*\020\022\006\b\000\022\002H\002\022\004\022\002H\0030\021*\016\022\004\022\002H\004\022\004\022\002H\0020\0052\006\020\022\032\002H\0202b\020\006\032^\022\023\022\021H\002¢\006\f\b\b\022\b\b\t\022\004\b\b(\n\022\025\022\023\030\001H\003¢\006\f\b\b\022\b\b\t\022\004\b\b(\013\022\023\022\021H\004¢\006\f\b\b\022\b\b\t\022\004\b\b(\f\022\023\022\0210\r¢\006\f\b\b\022\b\b\t\022\004\b\b(\016\022\004\022\002H\0030\007H\b¢\006\002\020\023\0320\020\024\032\016\022\004\022\002H\002\022\004\022\0020\0250\001\"\004\b\000\020\004\"\004\b\001\020\002*\016\022\004\022\002H\004\022\004\022\002H\0020\005H\007\032I\020\026\032\002H\020\"\004\b\000\020\004\"\004\b\001\020\002\"\026\b\002\020\020*\020\022\006\b\000\022\002H\002\022\004\022\0020\0250\021*\016\022\004\022\002H\004\022\004\022\002H\0020\0052\006\020\022\032\002H\020H\007¢\006\002\020\027\032¼\001\020\030\032\016\022\004\022\002H\002\022\004\022\002H\0030\001\"\004\b\000\020\004\"\004\b\001\020\002\"\004\b\002\020\003*\016\022\004\022\002H\004\022\004\022\002H\0020\00526\020\031\0322\022\023\022\021H\002¢\006\f\b\b\022\b\b\t\022\004\b\b(\n\022\023\022\021H\004¢\006\f\b\b\022\b\b\t\022\004\b\b(\f\022\004\022\002H\0030\0322K\020\006\032G\022\023\022\021H\002¢\006\f\b\b\022\b\b\t\022\004\b\b(\n\022\023\022\021H\003¢\006\f\b\b\022\b\b\t\022\004\b\b(\013\022\023\022\021H\004¢\006\f\b\b\022\b\b\t\022\004\b\b(\f\022\004\022\002H\0030\033H\b\032|\020\030\032\016\022\004\022\002H\002\022\004\022\002H\0030\001\"\004\b\000\020\004\"\004\b\001\020\002\"\004\b\002\020\003*\016\022\004\022\002H\004\022\004\022\002H\0020\0052\006\020\034\032\002H\00326\020\006\0322\022\023\022\021H\003¢\006\f\b\b\022\b\b\t\022\004\b\b(\013\022\023\022\021H\004¢\006\f\b\b\022\b\b\t\022\004\b\b(\f\022\004\022\002H\0030\032H\b¢\006\002\020\035\032Õ\001\020\036\032\002H\020\"\004\b\000\020\004\"\004\b\001\020\002\"\004\b\002\020\003\"\026\b\003\020\020*\020\022\006\b\000\022\002H\002\022\004\022\002H\0030\021*\016\022\004\022\002H\004\022\004\022\002H\0020\0052\006\020\022\032\002H\02026\020\031\0322\022\023\022\021H\002¢\006\f\b\b\022\b\b\t\022\004\b\b(\n\022\023\022\021H\004¢\006\f\b\b\022\b\b\t\022\004\b\b(\f\022\004\022\002H\0030\0322K\020\006\032G\022\023\022\021H\002¢\006\f\b\b\022\b\b\t\022\004\b\b(\n\022\023\022\021H\003¢\006\f\b\b\022\b\b\t\022\004\b\b(\013\022\023\022\021H\004¢\006\f\b\b\022\b\b\t\022\004\b\b(\f\022\004\022\002H\0030\033H\b¢\006\002\020\037\032\001\020\036\032\002H\020\"\004\b\000\020\004\"\004\b\001\020\002\"\004\b\002\020\003\"\026\b\003\020\020*\020\022\006\b\000\022\002H\002\022\004\022\002H\0030\021*\016\022\004\022\002H\004\022\004\022\002H\0020\0052\006\020\022\032\002H\0202\006\020\034\032\002H\00326\020\006\0322\022\023\022\021H\003¢\006\f\b\b\022\b\b\t\022\004\b\b(\013\022\023\022\021H\004¢\006\f\b\b\022\b\b\t\022\004\b\b(\f\022\004\022\002H\0030\032H\b¢\006\002\020 \032W\020!\032\016\022\004\022\002H\002\022\004\022\002H\0030\021\"\004\b\000\020\002\"\004\b\001\020\"\"\004\b\002\020\003*\016\022\004\022\002H\002\022\004\022\002H\"0\0212\036\020#\032\032\022\020\022\016\022\004\022\002H\002\022\004\022\002H\"0%\022\004\022\002H\0030$H\b\032\001\020&\032\016\022\004\022\002H\002\022\004\022\002H'0\001\"\004\b\000\020'\"\b\b\001\020\004*\002H'\"\004\b\002\020\002*\016\022\004\022\002H\004\022\004\022\002H\0020\0052K\020\006\032G\022\023\022\021H\002¢\006\f\b\b\022\b\b\t\022\004\b\b(\n\022\023\022\021H'¢\006\f\b\b\022\b\b\t\022\004\b\b(\013\022\023\022\021H\004¢\006\f\b\b\022\b\b\t\022\004\b\b(\f\022\004\022\002H'0\033H\b\032¡\001\020(\032\002H\020\"\004\b\000\020'\"\b\b\001\020\004*\002H'\"\004\b\002\020\002\"\026\b\003\020\020*\020\022\006\b\000\022\002H\002\022\004\022\002H'0\021*\016\022\004\022\002H\004\022\004\022\002H\0020\0052\006\020\022\032\002H\0202K\020\006\032G\022\023\022\021H\002¢\006\f\b\b\022\b\b\t\022\004\b\b(\n\022\023\022\021H'¢\006\f\b\b\022\b\b\t\022\004\b\b(\013\022\023\022\021H\004¢\006\f\b\b\022\b\b\t\022\004\b\b(\f\022\004\022\002H'0\033H\b¢\006\002\020)¨\006*"}, d2={"aggregate", "", "K", "R", "T", "Lkotlin/collections/Grouping;", "operation", "Lkotlin/Function4;", "Lkotlin/ParameterName;", "name", "key", "accumulator", "element", "", "first", "aggregateTo", "M", "", "destination", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function4;)Ljava/util/Map;", "eachCount", "", "eachCountTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;)Ljava/util/Map;", "fold", "initialValueSelector", "Lkotlin/Function2;", "Lkotlin/Function3;", "initialValue", "(Lkotlin/collections/Grouping;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "foldTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function2;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "(Lkotlin/collections/Grouping;Ljava/util/Map;Ljava/lang/Object;Lkotlin/jvm/functions/Function2;)Ljava/util/Map;", "mapValuesInPlace", "V", "f", "Lkotlin/Function1;", "", "reduce", "S", "reduceTo", "(Lkotlin/collections/Grouping;Ljava/util/Map;Lkotlin/jvm/functions/Function3;)Ljava/util/Map;", "kotlin-stdlib"}, k=2, mv={1, 1, 9})
public final class GroupingKt
{
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T, K, R> Map<K, R> aggregate(@NotNull Grouping<T, ? extends K> paramGrouping, @NotNull Function4<? super K, ? super R, ? super T, ? super Boolean, ? extends R> paramFunction4)
  {
    Intrinsics.checkParameterIsNotNull(paramGrouping, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction4, "operation");
    Map localMap = (Map)new LinkedHashMap();
    Iterator localIterator = paramGrouping.sourceIterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      Object localObject2 = paramGrouping.keyOf(localObject1);
      Object localObject3 = localMap.get(localObject2);
      boolean bool;
      if ((localObject3 == null) && (!localMap.containsKey(localObject2))) {
        bool = true;
      } else {
        bool = false;
      }
      localMap.put(localObject2, paramFunction4.invoke(localObject2, localObject3, localObject1, Boolean.valueOf(bool)));
    }
    return localMap;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T, K, R, M extends Map<? super K, R>> M aggregateTo(@NotNull Grouping<T, ? extends K> paramGrouping, @NotNull M paramM, @NotNull Function4<? super K, ? super R, ? super T, ? super Boolean, ? extends R> paramFunction4)
  {
    Intrinsics.checkParameterIsNotNull(paramGrouping, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction4, "operation");
    Iterator localIterator = paramGrouping.sourceIterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      Object localObject2 = paramGrouping.keyOf(localObject1);
      Object localObject3 = paramM.get(localObject2);
      boolean bool;
      if ((localObject3 == null) && (!paramM.containsKey(localObject2))) {
        bool = true;
      } else {
        bool = false;
      }
      paramM.put(localObject2, paramFunction4.invoke(localObject2, localObject3, localObject1, Boolean.valueOf(bool)));
    }
    return paramM;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T, K> Map<K, Integer> eachCount(@NotNull Grouping<T, ? extends K> paramGrouping)
  {
    Intrinsics.checkParameterIsNotNull(paramGrouping, "$receiver");
    Map localMap = (Map)new LinkedHashMap();
    Iterator localIterator = paramGrouping.sourceIterator();
    Object localObject1;
    while (localIterator.hasNext())
    {
      Object localObject2 = paramGrouping.keyOf(localIterator.next());
      localObject1 = localMap.get(localObject2);
      int i;
      if ((localObject1 == null) && (!localMap.containsKey(localObject2))) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        localObject1 = new Ref.IntRef();
      }
      localObject1 = (Ref.IntRef)localObject1;
      element += 1;
      localMap.put(localObject2, localObject1);
    }
    paramGrouping = ((Iterable)localMap.entrySet()).iterator();
    while (paramGrouping.hasNext())
    {
      localObject1 = (Map.Entry)paramGrouping.next();
      if (localObject1 == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
      }
      TypeIntrinsics.asMutableMapEntry(localObject1).setValue(Integer.valueOf(getValueelement));
    }
    return TypeIntrinsics.asMutableMap(localMap);
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T, K, M extends Map<? super K, Integer>> M eachCountTo(@NotNull Grouping<T, ? extends K> paramGrouping, @NotNull M paramM)
  {
    Intrinsics.checkParameterIsNotNull(paramGrouping, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Iterator localIterator = paramGrouping.sourceIterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = paramGrouping.keyOf(localIterator.next());
      Object localObject1 = paramM.get(localObject2);
      int i;
      if ((localObject1 == null) && (!paramM.containsKey(localObject2))) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        localObject1 = Integer.valueOf(0);
      }
      paramM.put(localObject2, Integer.valueOf(((Number)localObject1).intValue() + 1));
    }
    return paramM;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T, K, R> Map<K, R> fold(@NotNull Grouping<T, ? extends K> paramGrouping, R paramR, @NotNull Function2<? super R, ? super T, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramGrouping, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
    Map localMap = (Map)new LinkedHashMap();
    Iterator localIterator = paramGrouping.sourceIterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      Object localObject3 = paramGrouping.keyOf(localObject2);
      Object localObject1 = localMap.get(localObject3);
      int i;
      if ((localObject1 == null) && (!localMap.containsKey(localObject3))) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        localObject1 = paramR;
      }
      localMap.put(localObject3, paramFunction2.invoke(localObject1, localObject2));
    }
    return localMap;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T, K, R> Map<K, R> fold(@NotNull Grouping<T, ? extends K> paramGrouping, @NotNull Function2<? super K, ? super T, ? extends R> paramFunction2, @NotNull Function3<? super K, ? super R, ? super T, ? extends R> paramFunction3)
  {
    Intrinsics.checkParameterIsNotNull(paramGrouping, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "initialValueSelector");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "operation");
    Map localMap = (Map)new LinkedHashMap();
    Iterator localIterator = paramGrouping.sourceIterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      Object localObject3 = paramGrouping.keyOf(localObject2);
      Object localObject1 = localMap.get(localObject3);
      int i;
      if ((localObject1 == null) && (!localMap.containsKey(localObject3))) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        localObject1 = paramFunction2.invoke(localObject3, localObject2);
      }
      localMap.put(localObject3, paramFunction3.invoke(localObject3, localObject1, localObject2));
    }
    return localMap;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T, K, R, M extends Map<? super K, R>> M foldTo(@NotNull Grouping<T, ? extends K> paramGrouping, @NotNull M paramM, R paramR, @NotNull Function2<? super R, ? super T, ? extends R> paramFunction2)
  {
    Intrinsics.checkParameterIsNotNull(paramGrouping, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "operation");
    Iterator localIterator = paramGrouping.sourceIterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      Object localObject3 = paramGrouping.keyOf(localObject2);
      Object localObject1 = paramM.get(localObject3);
      int i;
      if ((localObject1 == null) && (!paramM.containsKey(localObject3))) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        localObject1 = paramR;
      }
      paramM.put(localObject3, paramFunction2.invoke(localObject1, localObject2));
    }
    return paramM;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <T, K, R, M extends Map<? super K, R>> M foldTo(@NotNull Grouping<T, ? extends K> paramGrouping, @NotNull M paramM, @NotNull Function2<? super K, ? super T, ? extends R> paramFunction2, @NotNull Function3<? super K, ? super R, ? super T, ? extends R> paramFunction3)
  {
    Intrinsics.checkParameterIsNotNull(paramGrouping, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction2, "initialValueSelector");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "operation");
    Iterator localIterator = paramGrouping.sourceIterator();
    while (localIterator.hasNext())
    {
      Object localObject2 = localIterator.next();
      Object localObject3 = paramGrouping.keyOf(localObject2);
      Object localObject1 = paramM.get(localObject3);
      int i;
      if ((localObject1 == null) && (!paramM.containsKey(localObject3))) {
        i = 1;
      } else {
        i = 0;
      }
      if (i != 0) {
        localObject1 = paramFunction2.invoke(localObject3, localObject2);
      }
      paramM.put(localObject3, paramFunction3.invoke(localObject3, localObject1, localObject2));
    }
    return paramM;
  }
  
  @PublishedApi
  @InlineOnly
  private static final <K, V, R> Map<K, R> mapValuesInPlace(@NotNull Map<K, V> paramMap, Function1<? super Map.Entry<? extends K, ? extends V>, ? extends R> paramFunction1)
  {
    Iterator localIterator = ((Iterable)paramMap.entrySet()).iterator();
    while (localIterator.hasNext())
    {
      Map.Entry localEntry = (Map.Entry)localIterator.next();
      if (localEntry == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap.MutableEntry<K, R>");
      }
      TypeIntrinsics.asMutableMapEntry(localEntry).setValue(paramFunction1.invoke(localEntry));
    }
    if (paramMap == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.collections.MutableMap<K, R>");
    }
    return TypeIntrinsics.asMutableMap(paramMap);
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <S, T extends S, K> Map<K, S> reduce(@NotNull Grouping<T, ? extends K> paramGrouping, @NotNull Function3<? super K, ? super S, ? super T, ? extends S> paramFunction3)
  {
    Intrinsics.checkParameterIsNotNull(paramGrouping, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "operation");
    Map localMap = (Map)new LinkedHashMap();
    Iterator localIterator = paramGrouping.sourceIterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      Object localObject2 = paramGrouping.keyOf(localObject1);
      Object localObject3 = localMap.get(localObject2);
      int i;
      if ((localObject3 == null) && (!localMap.containsKey(localObject2))) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 0) {
        localObject1 = paramFunction3.invoke(localObject2, localObject3, localObject1);
      }
      localMap.put(localObject2, localObject1);
    }
    return localMap;
  }
  
  @SinceKotlin(version="1.1")
  @NotNull
  public static final <S, T extends S, K, M extends Map<? super K, S>> M reduceTo(@NotNull Grouping<T, ? extends K> paramGrouping, @NotNull M paramM, @NotNull Function3<? super K, ? super S, ? super T, ? extends S> paramFunction3)
  {
    Intrinsics.checkParameterIsNotNull(paramGrouping, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramM, "destination");
    Intrinsics.checkParameterIsNotNull(paramFunction3, "operation");
    Iterator localIterator = paramGrouping.sourceIterator();
    while (localIterator.hasNext())
    {
      Object localObject1 = localIterator.next();
      Object localObject2 = paramGrouping.keyOf(localObject1);
      Object localObject3 = paramM.get(localObject2);
      int i;
      if ((localObject3 == null) && (!paramM.containsKey(localObject2))) {
        i = 1;
      } else {
        i = 0;
      }
      if (i == 0) {
        localObject1 = paramFunction3.invoke(localObject2, localObject3, localObject1);
      }
      paramM.put(localObject2, localObject1);
    }
    return paramM;
  }
}

package kotlin.collections;

import java.util.Comparator;
import java.util.Map;
import java.util.Properties;
import java.util.SortedMap;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.internal.InlineOnly;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000B\n\000\n\002\030\002\n\002\b\002\n\002\020\017\n\000\n\002\020\021\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\020$\n\002\020\016\n\002\b\002\n\002\030\002\n\000\032Y\020\000\032\016\022\004\022\002H\002\022\004\022\002H\0030\001\"\016\b\000\020\002*\b\022\004\022\002H\0020\004\"\004\b\001\020\0032*\020\005\032\026\022\022\b\001\022\016\022\004\022\002H\002\022\004\022\002H\0030\0070\006\"\016\022\004\022\002H\002\022\004\022\002H\0030\007¢\006\002\020\b\032@\020\t\032\002H\003\"\004\b\000\020\002\"\004\b\001\020\003*\016\022\004\022\002H\002\022\004\022\002H\0030\n2\006\020\013\032\002H\0022\f\020\f\032\b\022\004\022\002H\0030\rH\b¢\006\002\020\016\032\031\020\017\032\0020\020*\016\022\004\022\0020\022\022\004\022\0020\0220\021H\b\032:\020\023\032\016\022\004\022\002H\002\022\004\022\002H\0030\001\"\016\b\000\020\002*\b\022\004\022\002H\0020\004\"\004\b\001\020\003*\020\022\006\b\001\022\002H\002\022\004\022\002H\0030\021\032@\020\023\032\016\022\004\022\002H\002\022\004\022\002H\0030\001\"\004\b\000\020\002\"\004\b\001\020\003*\020\022\006\b\001\022\002H\002\022\004\022\002H\0030\0212\016\020\024\032\n\022\006\b\000\022\002H\0020\025¨\006\026"}, d2={"sortedMapOf", "Ljava/util/SortedMap;", "K", "V", "", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Ljava/util/SortedMap;", "getOrPut", "Ljava/util/concurrent/ConcurrentMap;", "key", "defaultValue", "Lkotlin/Function0;", "(Ljava/util/concurrent/ConcurrentMap;Ljava/lang/Object;Lkotlin/jvm/functions/Function0;)Ljava/lang/Object;", "toProperties", "Ljava/util/Properties;", "", "", "toSortedMap", "comparator", "Ljava/util/Comparator;", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/collections/MapsKt")
class MapsKt__MapsJVMKt
  extends MapsKt__MapWithDefaultKt
{
  public MapsKt__MapsJVMKt() {}
  
  public static final <K, V> V getOrPut(@NotNull ConcurrentMap<K, V> paramConcurrentMap, K paramK, @NotNull Function0<? extends V> paramFunction0)
  {
    Intrinsics.checkParameterIsNotNull(paramConcurrentMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction0, "defaultValue");
    Object localObject = paramConcurrentMap.get(paramK);
    if (localObject != null) {
      return localObject;
    }
    paramFunction0 = paramFunction0.invoke();
    paramK = paramConcurrentMap.putIfAbsent(paramK, paramFunction0);
    paramConcurrentMap = paramFunction0;
    if (paramK != null) {
      paramConcurrentMap = paramK;
    }
    return paramConcurrentMap;
  }
  
  @NotNull
  public static final <K extends Comparable<? super K>, V> SortedMap<K, V> sortedMapOf(@NotNull Pair<? extends K, ? extends V>... paramVarArgs)
  {
    Intrinsics.checkParameterIsNotNull(paramVarArgs, "pairs");
    TreeMap localTreeMap = new TreeMap();
    MapsKt.putAll((Map)localTreeMap, paramVarArgs);
    return (SortedMap)localTreeMap;
  }
  
  @InlineOnly
  private static final Properties toProperties(@NotNull Map<String, String> paramMap)
  {
    Properties localProperties = new Properties();
    localProperties.putAll(paramMap);
    return localProperties;
  }
  
  @NotNull
  public static final <K extends Comparable<? super K>, V> SortedMap<K, V> toSortedMap(@NotNull Map<? extends K, ? extends V> paramMap)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    return (SortedMap)new TreeMap(paramMap);
  }
  
  @NotNull
  public static final <K, V> SortedMap<K, V> toSortedMap(@NotNull Map<? extends K, ? extends V> paramMap, @NotNull Comparator<? super K> paramComparator)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramComparator, "comparator");
    paramComparator = new TreeMap(paramComparator);
    paramComparator.putAll(paramMap);
    return (SortedMap)paramComparator;
  }
}

package kotlin.collections;

import java.util.Map;
import java.util.NoSuchElementException;
import kotlin.Metadata;
import kotlin.PublishedApi;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\036\n\002\b\003\n\002\020$\n\002\b\005\n\002\030\002\n\002\030\002\n\000\n\002\020%\n\002\b\002\0323\020\000\032\002H\001\"\004\b\000\020\002\"\004\b\001\020\001*\016\022\004\022\002H\002\022\004\022\002H\0010\0032\006\020\004\032\002H\002H\001¢\006\004\b\005\020\006\032Q\020\007\032\016\022\004\022\002H\002\022\004\022\002H\0010\003\"\004\b\000\020\002\"\004\b\001\020\001*\016\022\004\022\002H\002\022\004\022\002H\0010\0032!\020\b\032\035\022\023\022\021H\002¢\006\f\b\n\022\b\b\013\022\004\b\b(\004\022\004\022\002H\0010\t\032X\020\007\032\016\022\004\022\002H\002\022\004\022\002H\0010\f\"\004\b\000\020\002\"\004\b\001\020\001*\016\022\004\022\002H\002\022\004\022\002H\0010\f2!\020\b\032\035\022\023\022\021H\002¢\006\f\b\n\022\b\b\013\022\004\b\b(\004\022\004\022\002H\0010\tH\007¢\006\002\b\r¨\006\016"}, d2={"getOrImplicitDefault", "V", "K", "", "key", "getOrImplicitDefaultNullable", "(Ljava/util/Map;Ljava/lang/Object;)Ljava/lang/Object;", "withDefault", "defaultValue", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "", "withDefaultMutable", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/collections/MapsKt")
class MapsKt__MapWithDefaultKt
{
  public MapsKt__MapWithDefaultKt() {}
  
  @PublishedApi
  @JvmName(name="getOrImplicitDefaultNullable")
  public static final <K, V> V getOrImplicitDefaultNullable(@NotNull Map<K, ? extends V> paramMap, K paramK)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    if ((paramMap instanceof MapWithDefault)) {
      return ((MapWithDefault)paramMap).getOrImplicitDefault(paramK);
    }
    Object localObject = paramMap.get(paramK);
    if ((localObject == null) && (!paramMap.containsKey(paramK)))
    {
      paramMap = new StringBuilder();
      paramMap.append("Key ");
      paramMap.append(paramK);
      paramMap.append(" is missing in the map.");
      throw ((Throwable)new NoSuchElementException(paramMap.toString()));
    }
    return localObject;
  }
  
  @NotNull
  public static final <K, V> Map<K, V> withDefault(@NotNull Map<K, ? extends V> paramMap, @NotNull Function1<? super K, ? extends V> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "defaultValue");
    if ((paramMap instanceof MapWithDefault)) {
      return MapsKt.withDefault(((MapWithDefault)paramMap).getMap(), paramFunction1);
    }
    return (Map)new MapWithDefaultImpl(paramMap, paramFunction1);
  }
  
  @JvmName(name="withDefaultMutable")
  @NotNull
  public static final <K, V> Map<K, V> withDefaultMutable(@NotNull Map<K, V> paramMap, @NotNull Function1<? super K, ? extends V> paramFunction1)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "$receiver");
    Intrinsics.checkParameterIsNotNull(paramFunction1, "defaultValue");
    if ((paramMap instanceof MutableMapWithDefault)) {
      return MapsKt.withDefaultMutable(((MutableMapWithDefault)paramMap).getMap(), paramFunction1);
    }
    return (Map)new MutableMapWithDefaultImpl(paramMap, paramFunction1);
  }
}

package kotlin.collections;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TuplesKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\030\n\000\n\002\020 \n\000\n\002\020\021\n\002\b\002\n\002\030\002\n\002\b\003\032+\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002*\022\022\016\b\001\022\n\022\006\b\001\022\002H\0020\0030\003¢\006\002\020\004\032G\020\005\032\032\022\n\022\b\022\004\022\002H\0020\001\022\n\022\b\022\004\022\002H\0070\0010\006\"\004\b\000\020\002\"\004\b\001\020\007*\026\022\022\b\001\022\016\022\004\022\002H\002\022\004\022\002H\0070\0060\003¢\006\002\020\b¨\006\t"}, d2={"flatten", "", "T", "", "([[Ljava/lang/Object;)Ljava/util/List;", "unzip", "Lkotlin/Pair;", "R", "([Lkotlin/Pair;)Lkotlin/Pair;", "kotlin-stdlib"}, k=5, mv={1, 1, 9}, xi=1, xs="kotlin/collections/ArraysKt")
class ArraysKt__ArraysKt
  extends ArraysKt__ArraysJVMKt
{
  public ArraysKt__ArraysKt() {}
  
  @NotNull
  public static final <T> List<T> flatten(@NotNull T[][] paramArrayOfT)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfT, "$receiver");
    Object localObject = (Object[])paramArrayOfT;
    int k = 0;
    int m = localObject.length;
    int i = 0;
    int j = i;
    while (i < m)
    {
      j += ((Object[])localObject[i]).length;
      i += 1;
    }
    localObject = new ArrayList(j);
    j = paramArrayOfT.length;
    i = k;
    while (i < j)
    {
      T[] arrayOfT = paramArrayOfT[i];
      CollectionsKt.addAll((Collection)localObject, arrayOfT);
      i += 1;
    }
    return (List)localObject;
  }
  
  @NotNull
  public static final <T, R> Pair<List<T>, List<R>> unzip(@NotNull Pair<? extends T, ? extends R>[] paramArrayOfPair)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfPair, "$receiver");
    Object localObject = (Object[])paramArrayOfPair;
    int i = 0;
    ArrayList localArrayList = new ArrayList(localObject.length);
    localObject = new ArrayList(localObject.length);
    int j = paramArrayOfPair.length;
    while (i < j)
    {
      Pair<? extends T, ? extends R> localPair = paramArrayOfPair[i];
      localArrayList.add(localPair.getFirst());
      ((ArrayList)localObject).add(localPair.getSecond());
      i += 1;
    }
    return TuplesKt.to(localArrayList, localObject);
  }
}

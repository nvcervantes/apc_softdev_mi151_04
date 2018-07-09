package androidx.util;

import android.support.annotation.RequiresApi;
import android.util.ArrayMap;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\026\n\000\n\002\030\002\n\002\b\003\n\002\020\021\n\002\030\002\n\002\b\002\032!\020\000\032\016\022\004\022\002H\002\022\004\022\002H\0030\001\"\004\b\000\020\002\"\004\b\001\020\003H\b\032Q\020\000\032\016\022\004\022\002H\002\022\004\022\002H\0030\001\"\004\b\000\020\002\"\004\b\001\020\0032*\020\004\032\026\022\022\b\001\022\016\022\004\022\002H\002\022\004\022\002H\0030\0060\005\"\016\022\004\022\002H\002\022\004\022\002H\0030\006H\007¢\006\002\020\007¨\006\b"}, d2={"arrayMapOf", "Landroid/util/ArrayMap;", "K", "V", "pairs", "", "Lkotlin/Pair;", "([Lkotlin/Pair;)Landroid/util/ArrayMap;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class ArrayMapKt
{
  @RequiresApi(19)
  @NotNull
  public static final <K, V> ArrayMap<K, V> arrayMapOf()
  {
    return new ArrayMap();
  }
  
  @RequiresApi(19)
  @NotNull
  public static final <K, V> ArrayMap<K, V> arrayMapOf(@NotNull Pair<? extends K, ? extends V>... paramVarArgs)
  {
    Object localObject = (Object[])paramVarArgs;
    int i = 0;
    localObject = new ArrayMap(localObject.length);
    int j = paramVarArgs.length;
    while (i < j)
    {
      Pair<? extends K, ? extends V> localPair = paramVarArgs[i];
      ((ArrayMap)localObject).put(localPair.getFirst(), localPair.getSecond());
      i += 1;
    }
    return localObject;
  }
}

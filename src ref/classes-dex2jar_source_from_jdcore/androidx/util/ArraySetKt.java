package androidx.util;

import android.support.annotation.RequiresApi;
import android.util.ArraySet;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\022\n\000\n\002\030\002\n\002\b\002\n\002\020\021\n\002\b\002\032\025\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\002H\b\032-\020\000\032\b\022\004\022\002H\0020\001\"\004\b\000\020\0022\022\020\003\032\n\022\006\b\001\022\002H\0020\004\"\002H\002H\007¢\006\002\020\005¨\006\006"}, d2={"arraySetOf", "Landroid/util/ArraySet;", "T", "values", "", "([Ljava/lang/Object;)Landroid/util/ArraySet;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class ArraySetKt
{
  @RequiresApi(23)
  @NotNull
  public static final <T> ArraySet<T> arraySetOf()
  {
    return new ArraySet();
  }
  
  @RequiresApi(23)
  @NotNull
  public static final <T> ArraySet<T> arraySetOf(@NotNull T... paramVarArgs)
  {
    int i = 0;
    ArraySet localArraySet = new ArraySet(paramVarArgs.length);
    int j = paramVarArgs.length;
    while (i < j)
    {
      localArraySet.add(paramVarArgs[i]);
      i += 1;
    }
    return localArraySet;
  }
}

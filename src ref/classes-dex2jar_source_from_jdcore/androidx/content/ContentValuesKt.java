package androidx.content;

import android.content.ContentValues;
import kotlin.Metadata;
import kotlin.Pair;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\000\n\002\030\002\n\000\n\002\020\021\n\002\030\002\n\002\020\016\n\002\020\000\n\002\b\002\032;\020\000\032\0020\0012.\020\002\032\030\022\024\b\001\022\020\022\004\022\0020\005\022\006\022\004\030\0010\0060\0040\003\"\020\022\004\022\0020\005\022\006\022\004\030\0010\0060\004¢\006\002\020\007¨\006\b"}, d2={"contentValuesOf", "Landroid/content/ContentValues;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/content/ContentValues;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class ContentValuesKt
{
  @NotNull
  public static final ContentValues contentValuesOf(@NotNull Pair<String, ? extends Object>... paramVarArgs)
  {
    Object localObject1 = (Object[])paramVarArgs;
    int i = 0;
    Object localObject2 = new ContentValues(localObject1.length);
    int j = paramVarArgs.length;
    while (i < j)
    {
      Object localObject3 = paramVarArgs[i];
      localObject1 = (String)((Pair)localObject3).component1();
      localObject3 = ((Pair)localObject3).component2();
      if (localObject3 == null)
      {
        ((ContentValues)localObject2).putNull((String)localObject1);
      }
      else if ((localObject3 instanceof String))
      {
        ((ContentValues)localObject2).put((String)localObject1, (String)localObject3);
      }
      else if ((localObject3 instanceof Integer))
      {
        ((ContentValues)localObject2).put((String)localObject1, (Integer)localObject3);
      }
      else if ((localObject3 instanceof Long))
      {
        ((ContentValues)localObject2).put((String)localObject1, (Long)localObject3);
      }
      else if ((localObject3 instanceof Boolean))
      {
        ((ContentValues)localObject2).put((String)localObject1, (Boolean)localObject3);
      }
      else if ((localObject3 instanceof Float))
      {
        ((ContentValues)localObject2).put((String)localObject1, (Float)localObject3);
      }
      else if ((localObject3 instanceof Double))
      {
        ((ContentValues)localObject2).put((String)localObject1, (Double)localObject3);
      }
      else if ((localObject3 instanceof byte[]))
      {
        ((ContentValues)localObject2).put((String)localObject1, (byte[])localObject3);
      }
      else if ((localObject3 instanceof Byte))
      {
        ((ContentValues)localObject2).put((String)localObject1, (Byte)localObject3);
      }
      else
      {
        if (!(localObject3 instanceof Short)) {
          break label263;
        }
        ((ContentValues)localObject2).put((String)localObject1, (Short)localObject3);
      }
      i += 1;
      continue;
      label263:
      paramVarArgs = localObject3.getClass().getCanonicalName();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Illegal value type ");
      ((StringBuilder)localObject2).append(paramVarArgs);
      ((StringBuilder)localObject2).append(" for key \"");
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append('"');
      throw ((Throwable)new IllegalArgumentException(((StringBuilder)localObject2).toString()));
    }
    return localObject2;
  }
}

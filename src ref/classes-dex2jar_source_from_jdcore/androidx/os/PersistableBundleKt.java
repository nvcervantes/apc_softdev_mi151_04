package androidx.os;

import android.os.Build.VERSION;
import android.os.PersistableBundle;
import android.support.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\000\n\002\030\002\n\000\n\002\020\021\n\002\030\002\n\002\020\016\n\002\020\000\n\002\b\002\032=\020\000\032\0020\0012.\020\002\032\030\022\024\b\001\022\020\022\004\022\0020\005\022\006\022\004\030\0010\0060\0040\003\"\020\022\004\022\0020\005\022\006\022\004\030\0010\0060\004H\007¢\006\002\020\007¨\006\b"}, d2={"persistableBundleOf", "Landroid/os/PersistableBundle;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/PersistableBundle;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class PersistableBundleKt
{
  @RequiresApi(21)
  @NotNull
  public static final PersistableBundle persistableBundleOf(@NotNull Pair<String, ? extends Object>... paramVarArgs)
  {
    Object localObject1 = (Object[])paramVarArgs;
    int i = 0;
    Object localObject2 = new PersistableBundle(localObject1.length);
    int j = paramVarArgs.length;
    while (i < j)
    {
      Object localObject3 = paramVarArgs[i];
      localObject1 = (String)((Pair)localObject3).component1();
      localObject3 = ((Pair)localObject3).component2();
      Class localClass;
      if (localObject3 == null)
      {
        ((PersistableBundle)localObject2).putString((String)localObject1, null);
      }
      else if ((localObject3 instanceof Boolean))
      {
        if (Build.VERSION.SDK_INT >= 22)
        {
          ((PersistableBundle)localObject2).putBoolean((String)localObject1, ((Boolean)localObject3).booleanValue());
        }
        else
        {
          paramVarArgs = new StringBuilder();
          paramVarArgs.append("Illegal value type boolean for key \"");
          paramVarArgs.append((String)localObject1);
          paramVarArgs.append('"');
          throw ((Throwable)new IllegalArgumentException(paramVarArgs.toString()));
        }
      }
      else if ((localObject3 instanceof Double))
      {
        ((PersistableBundle)localObject2).putDouble((String)localObject1, ((Number)localObject3).doubleValue());
      }
      else if ((localObject3 instanceof Integer))
      {
        ((PersistableBundle)localObject2).putInt((String)localObject1, ((Number)localObject3).intValue());
      }
      else if ((localObject3 instanceof Long))
      {
        ((PersistableBundle)localObject2).putLong((String)localObject1, ((Number)localObject3).longValue());
      }
      else if ((localObject3 instanceof String))
      {
        ((PersistableBundle)localObject2).putString((String)localObject1, (String)localObject3);
      }
      else if ((localObject3 instanceof boolean[]))
      {
        if (Build.VERSION.SDK_INT >= 22)
        {
          ((PersistableBundle)localObject2).putBooleanArray((String)localObject1, (boolean[])localObject3);
        }
        else
        {
          paramVarArgs = new StringBuilder();
          paramVarArgs.append("Illegal value type boolean[] for key \"");
          paramVarArgs.append((String)localObject1);
          paramVarArgs.append('"');
          throw ((Throwable)new IllegalArgumentException(paramVarArgs.toString()));
        }
      }
      else if ((localObject3 instanceof double[]))
      {
        ((PersistableBundle)localObject2).putDoubleArray((String)localObject1, (double[])localObject3);
      }
      else if ((localObject3 instanceof int[]))
      {
        ((PersistableBundle)localObject2).putIntArray((String)localObject1, (int[])localObject3);
      }
      else if ((localObject3 instanceof long[]))
      {
        ((PersistableBundle)localObject2).putLongArray((String)localObject1, (long[])localObject3);
      }
      else
      {
        if (!(localObject3 instanceof Object[])) {
          break label511;
        }
        localClass = localObject3.getClass().getComponentType();
        if (!String.class.isAssignableFrom(localClass)) {
          break label435;
        }
        if (localObject3 == null) {
          throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
        }
        ((PersistableBundle)localObject2).putStringArray((String)localObject1, (String[])localObject3);
      }
      i += 1;
      continue;
      label435:
      Intrinsics.checkExpressionValueIsNotNull(localClass, "componentType");
      paramVarArgs = localClass.getCanonicalName();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("Illegal value array type ");
      ((StringBuilder)localObject2).append(paramVarArgs);
      ((StringBuilder)localObject2).append(" for key \"");
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append('"');
      throw ((Throwable)new IllegalArgumentException(((StringBuilder)localObject2).toString()));
      label511:
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

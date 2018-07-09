package androidx.os;

import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import java.io.Serializable;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\034\n\000\n\002\030\002\n\000\n\002\020\021\n\002\030\002\n\002\020\016\n\002\020\000\n\002\b\002\032;\020\000\032\0020\0012.\020\002\032\030\022\024\b\001\022\020\022\004\022\0020\005\022\006\022\004\030\0010\0060\0040\003\"\020\022\004\022\0020\005\022\006\022\004\030\0010\0060\004¢\006\002\020\007¨\006\b"}, d2={"bundleOf", "Landroid/os/Bundle;", "pairs", "", "Lkotlin/Pair;", "", "", "([Lkotlin/Pair;)Landroid/os/Bundle;", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class BundleKt
{
  @NotNull
  public static final Bundle bundleOf(@NotNull Pair<String, ? extends Object>... paramVarArgs)
  {
    Object localObject1 = (Object[])paramVarArgs;
    int i = 0;
    Object localObject2 = new Bundle(localObject1.length);
    int j = paramVarArgs.length;
    while (i < j)
    {
      Object localObject3 = paramVarArgs[i];
      localObject1 = (String)((Pair)localObject3).component1();
      localObject3 = ((Pair)localObject3).component2();
      if (localObject3 == null)
      {
        ((Bundle)localObject2).putString((String)localObject1, null);
      }
      else if ((localObject3 instanceof Boolean))
      {
        ((Bundle)localObject2).putBoolean((String)localObject1, ((Boolean)localObject3).booleanValue());
      }
      else if ((localObject3 instanceof Byte))
      {
        ((Bundle)localObject2).putByte((String)localObject1, ((Number)localObject3).byteValue());
      }
      else if ((localObject3 instanceof Character))
      {
        ((Bundle)localObject2).putChar((String)localObject1, ((Character)localObject3).charValue());
      }
      else if ((localObject3 instanceof Double))
      {
        ((Bundle)localObject2).putDouble((String)localObject1, ((Number)localObject3).doubleValue());
      }
      else if ((localObject3 instanceof Float))
      {
        ((Bundle)localObject2).putFloat((String)localObject1, ((Number)localObject3).floatValue());
      }
      else if ((localObject3 instanceof Integer))
      {
        ((Bundle)localObject2).putInt((String)localObject1, ((Number)localObject3).intValue());
      }
      else if ((localObject3 instanceof Long))
      {
        ((Bundle)localObject2).putLong((String)localObject1, ((Number)localObject3).longValue());
      }
      else if ((localObject3 instanceof Short))
      {
        ((Bundle)localObject2).putShort((String)localObject1, ((Number)localObject3).shortValue());
      }
      else if ((localObject3 instanceof Bundle))
      {
        ((Bundle)localObject2).putBundle((String)localObject1, (Bundle)localObject3);
      }
      else if ((localObject3 instanceof CharSequence))
      {
        ((Bundle)localObject2).putCharSequence((String)localObject1, (CharSequence)localObject3);
      }
      else if ((localObject3 instanceof Parcelable))
      {
        ((Bundle)localObject2).putParcelable((String)localObject1, (Parcelable)localObject3);
      }
      else if ((localObject3 instanceof boolean[]))
      {
        ((Bundle)localObject2).putBooleanArray((String)localObject1, (boolean[])localObject3);
      }
      else if ((localObject3 instanceof byte[]))
      {
        ((Bundle)localObject2).putByteArray((String)localObject1, (byte[])localObject3);
      }
      else if ((localObject3 instanceof char[]))
      {
        ((Bundle)localObject2).putCharArray((String)localObject1, (char[])localObject3);
      }
      else if ((localObject3 instanceof double[]))
      {
        ((Bundle)localObject2).putDoubleArray((String)localObject1, (double[])localObject3);
      }
      else if ((localObject3 instanceof float[]))
      {
        ((Bundle)localObject2).putFloatArray((String)localObject1, (float[])localObject3);
      }
      else if ((localObject3 instanceof int[]))
      {
        ((Bundle)localObject2).putIntArray((String)localObject1, (int[])localObject3);
      }
      else if ((localObject3 instanceof long[]))
      {
        ((Bundle)localObject2).putLongArray((String)localObject1, (long[])localObject3);
      }
      else if ((localObject3 instanceof short[]))
      {
        ((Bundle)localObject2).putShortArray((String)localObject1, (short[])localObject3);
      }
      else if ((localObject3 instanceof Object[]))
      {
        Class localClass = localObject3.getClass().getComponentType();
        if (Parcelable.class.isAssignableFrom(localClass))
        {
          if (localObject3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<android.os.Parcelable>");
          }
          ((Bundle)localObject2).putParcelableArray((String)localObject1, (Parcelable[])localObject3);
        }
        else if (String.class.isAssignableFrom(localClass))
        {
          if (localObject3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.String>");
          }
          ((Bundle)localObject2).putStringArray((String)localObject1, (String[])localObject3);
        }
        else if (CharSequence.class.isAssignableFrom(localClass))
        {
          if (localObject3 == null) {
            throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<kotlin.CharSequence>");
          }
          ((Bundle)localObject2).putCharSequenceArray((String)localObject1, (CharSequence[])localObject3);
        }
        else if (Serializable.class.isAssignableFrom(localClass))
        {
          ((Bundle)localObject2).putSerializable((String)localObject1, (Serializable)localObject3);
        }
        else
        {
          Intrinsics.checkExpressionValueIsNotNull(localClass, "componentType");
          paramVarArgs = localClass.getCanonicalName();
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append("Illegal value array type ");
          ((StringBuilder)localObject2).append(paramVarArgs);
          ((StringBuilder)localObject2).append(" for key \"");
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append('"');
          throw ((Throwable)new IllegalArgumentException(((StringBuilder)localObject2).toString()));
        }
      }
      else if ((localObject3 instanceof Serializable))
      {
        ((Bundle)localObject2).putSerializable((String)localObject1, (Serializable)localObject3);
      }
      else if ((Build.VERSION.SDK_INT >= 18) && ((localObject3 instanceof Binder)))
      {
        ((Bundle)localObject2).putBinder((String)localObject1, (IBinder)localObject3);
      }
      else if ((Build.VERSION.SDK_INT >= 21) && ((localObject3 instanceof Size)))
      {
        ((Bundle)localObject2).putSize((String)localObject1, (Size)localObject3);
      }
      else
      {
        if ((Build.VERSION.SDK_INT < 21) || (!(localObject3 instanceof SizeF))) {
          break label857;
        }
        ((Bundle)localObject2).putSizeF((String)localObject1, (SizeF)localObject3);
      }
      i += 1;
      continue;
      label857:
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

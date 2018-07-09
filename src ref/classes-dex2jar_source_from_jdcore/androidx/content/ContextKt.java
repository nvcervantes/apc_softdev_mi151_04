package androidx.content;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.RequiresApi;
import android.support.annotation.StyleRes;
import android.util.AttributeSet;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0006\n\002\b\003\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\025\n\000\n\002\020\b\n\002\b\002\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\032\"\020\000\032\n \002*\004\030\001H\001H\001\"\006\b\000\020\001\030\001*\0020\003H\b¢\006\002\020\004\032N\020\005\032\0020\006*\0020\0032\n\b\002\020\007\032\004\030\0010\b2\006\020\t\032\0020\n2\b\b\003\020\013\032\0020\f2\b\b\003\020\r\032\0020\f2\027\020\016\032\023\022\004\022\0020\020\022\004\022\0020\0060\017¢\006\002\b\021H\b\0328\020\005\032\0020\006*\0020\0032\b\b\001\020\022\032\0020\f2\006\020\t\032\0020\n2\027\020\016\032\023\022\004\022\0020\020\022\004\022\0020\0060\017¢\006\002\b\021H\b¨\006\023"}, d2={"systemService", "T", "kotlin.jvm.PlatformType", "Landroid/content/Context;", "(Landroid/content/Context;)Ljava/lang/Object;", "withStyledAttributes", "", "set", "Landroid/util/AttributeSet;", "attrs", "", "defStyleAttr", "", "defStyleRes", "block", "Lkotlin/Function1;", "Landroid/content/res/TypedArray;", "Lkotlin/ExtensionFunctionType;", "resourceId", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class ContextKt
{
  @RequiresApi(23)
  private static final <T> T systemService(@NotNull Context paramContext)
  {
    Intrinsics.reifiedOperationMarker(4, "T");
    return paramContext.getSystemService(Object.class);
  }
  
  public static final void withStyledAttributes(@NotNull Context paramContext, @StyleRes int paramInt, @NotNull int[] paramArrayOfInt, @NotNull Function1<? super TypedArray, Unit> paramFunction1)
  {
    paramContext = paramContext.obtainStyledAttributes(paramInt, paramArrayOfInt);
    try
    {
      Intrinsics.checkExpressionValueIsNotNull(paramContext, "typedArray");
      paramFunction1.invoke(paramContext);
      return;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      paramContext.recycle();
      InlineMarker.finallyEnd(1);
    }
  }
  
  public static final void withStyledAttributes(@NotNull Context paramContext, @Nullable AttributeSet paramAttributeSet, @NotNull int[] paramArrayOfInt, @AttrRes int paramInt1, @StyleRes int paramInt2, @NotNull Function1<? super TypedArray, Unit> paramFunction1)
  {
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, paramArrayOfInt, paramInt1, paramInt2);
    try
    {
      Intrinsics.checkExpressionValueIsNotNull(paramContext, "typedArray");
      paramFunction1.invoke(paramContext);
      return;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      paramContext.recycle();
      InlineMarker.finallyEnd(1);
    }
  }
}

package androidx.graphics;

import android.graphics.Canvas;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.InlineMarker;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000 \n\000\n\002\020\002\n\002\030\002\n\000\n\002\020\007\n\002\b\003\n\002\030\002\n\002\030\002\n\002\b\007\032D\020\000\032\0020\001*\0020\0022\b\b\002\020\003\032\0020\0042\b\b\002\020\005\032\0020\0042\b\b\002\020\006\032\0020\0042\027\020\007\032\023\022\004\022\0020\002\022\004\022\0020\0010\b¢\006\002\b\tH\b\032&\020\n\032\0020\001*\0020\0022\027\020\007\032\023\022\004\022\0020\002\022\004\022\0020\0010\b¢\006\002\b\tH\b\032N\020\013\032\0020\001*\0020\0022\b\b\002\020\f\032\0020\0042\b\b\002\020\r\032\0020\0042\b\b\002\020\005\032\0020\0042\b\b\002\020\006\032\0020\0042\027\020\007\032\023\022\004\022\0020\002\022\004\022\0020\0010\b¢\006\002\b\tH\b\032:\020\016\032\0020\001*\0020\0022\b\b\002\020\f\032\0020\0042\b\b\002\020\r\032\0020\0042\027\020\007\032\023\022\004\022\0020\002\022\004\022\0020\0010\b¢\006\002\b\tH\b\032:\020\017\032\0020\001*\0020\0022\b\b\002\020\f\032\0020\0042\b\b\002\020\r\032\0020\0042\027\020\007\032\023\022\004\022\0020\002\022\004\022\0020\0010\b¢\006\002\b\tH\b¨\006\020"}, d2={"withRotation", "", "Landroid/graphics/Canvas;", "degrees", "", "pivotX", "pivotY", "block", "Lkotlin/Function1;", "Lkotlin/ExtensionFunctionType;", "withSave", "withScale", "x", "y", "withSkew", "withTranslation", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class CanvasKt
{
  public static final void withRotation(@NotNull Canvas paramCanvas, float paramFloat1, float paramFloat2, float paramFloat3, @NotNull Function1<? super Canvas, Unit> paramFunction1)
  {
    int i = paramCanvas.save();
    paramCanvas.rotate(paramFloat1, paramFloat2, paramFloat3);
    try
    {
      paramFunction1.invoke(paramCanvas);
      return;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      paramCanvas.restoreToCount(i);
      InlineMarker.finallyEnd(1);
    }
  }
  
  public static final void withSave(@NotNull Canvas paramCanvas, @NotNull Function1<? super Canvas, Unit> paramFunction1)
  {
    int i = paramCanvas.save();
    try
    {
      paramFunction1.invoke(paramCanvas);
      return;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      paramCanvas.restoreToCount(i);
      InlineMarker.finallyEnd(1);
    }
  }
  
  public static final void withScale(@NotNull Canvas paramCanvas, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, @NotNull Function1<? super Canvas, Unit> paramFunction1)
  {
    int i = paramCanvas.save();
    paramCanvas.scale(paramFloat1, paramFloat2, paramFloat3, paramFloat4);
    try
    {
      paramFunction1.invoke(paramCanvas);
      return;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      paramCanvas.restoreToCount(i);
      InlineMarker.finallyEnd(1);
    }
  }
  
  public static final void withSkew(@NotNull Canvas paramCanvas, float paramFloat1, float paramFloat2, @NotNull Function1<? super Canvas, Unit> paramFunction1)
  {
    int i = paramCanvas.save();
    paramCanvas.skew(paramFloat1, paramFloat2);
    try
    {
      paramFunction1.invoke(paramCanvas);
      return;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      paramCanvas.restoreToCount(i);
      InlineMarker.finallyEnd(1);
    }
  }
  
  public static final void withTranslation(@NotNull Canvas paramCanvas, float paramFloat1, float paramFloat2, @NotNull Function1<? super Canvas, Unit> paramFunction1)
  {
    int i = paramCanvas.save();
    paramCanvas.translate(paramFloat1, paramFloat2);
    try
    {
      paramFunction1.invoke(paramCanvas);
      return;
    }
    finally
    {
      InlineMarker.finallyStart(1);
      paramCanvas.restoreToCount(i);
      InlineMarker.finallyEnd(1);
    }
  }
}

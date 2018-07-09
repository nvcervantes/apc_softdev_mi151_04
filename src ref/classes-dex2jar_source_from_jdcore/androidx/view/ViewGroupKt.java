package androidx.view;

import android.support.annotation.Px;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.Unit;
import kotlin.jvm.JvmName;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000Z\n\000\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\003\n\002\020\b\n\002\b\003\n\002\020\013\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020)\n\002\b\003\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\n\032\025\020\n\032\0020\013*\0020\0032\006\020\f\032\0020\002H\n\0320\020\r\032\0020\016*\0020\0032!\020\017\032\035\022\023\022\0210\002¢\006\f\b\021\022\b\b\022\022\004\b\b(\f\022\004\022\0020\0160\020H\b\032E\020\023\032\0020\016*\0020\00326\020\017\0322\022\023\022\0210\007¢\006\f\b\021\022\b\b\022\022\004\b\b(\025\022\023\022\0210\002¢\006\f\b\021\022\b\b\022\022\004\b\b(\f\022\004\022\0020\0160\024H\b\032\025\020\026\032\0020\002*\0020\0032\006\020\025\032\0020\007H\002\032\r\020\027\032\0020\013*\0020\003H\b\032\r\020\030\032\0020\013*\0020\003H\b\032\023\020\031\032\b\022\004\022\0020\0020\032*\0020\003H\002\032\025\020\033\032\0020\016*\0020\0032\006\020\f\032\0020\002H\n\032\025\020\034\032\0020\016*\0020\0032\006\020\f\032\0020\002H\n\032\027\020\035\032\0020\016*\0020\0362\b\b\001\020\006\032\0020\007H\b\0327\020\037\032\0020\016\"\n\b\000\020 \030\001*\0020!*\0020\0032\027\020\"\032\023\022\004\022\002H \022\004\022\0020\0160\020¢\006\002\b#H\b¢\006\002\b$\032&\020\037\032\0020\016*\0020\0032\027\020\"\032\023\022\004\022\0020!\022\004\022\0020\0160\020¢\006\002\b#H\b\0325\020%\032\0020\016*\0020\0362\b\b\003\020&\032\0020\0072\b\b\003\020'\032\0020\0072\b\b\003\020(\032\0020\0072\b\b\003\020)\032\0020\007H\b\0325\020*\032\0020\016*\0020\0362\b\b\003\020+\032\0020\0072\b\b\003\020'\032\0020\0072\b\b\003\020,\032\0020\0072\b\b\003\020)\032\0020\007H\b\"\033\020\000\032\b\022\004\022\0020\0020\001*\0020\0038F¢\006\006\032\004\b\004\020\005\"\026\020\006\032\0020\007*\0020\0038Æ\002¢\006\006\032\004\b\b\020\t¨\006-"}, d2={"children", "Lkotlin/sequences/Sequence;", "Landroid/view/View;", "Landroid/view/ViewGroup;", "getChildren", "(Landroid/view/ViewGroup;)Lkotlin/sequences/Sequence;", "size", "", "getSize", "(Landroid/view/ViewGroup;)I", "contains", "", "view", "forEach", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "forEachIndexed", "Lkotlin/Function2;", "index", "get", "isEmpty", "isNotEmpty", "iterator", "", "minusAssign", "plusAssign", "setMargins", "Landroid/view/ViewGroup$MarginLayoutParams;", "updateLayoutParams", "T", "Landroid/view/ViewGroup$LayoutParams;", "block", "Lkotlin/ExtensionFunctionType;", "updateLayoutParamsTyped", "updateMargins", "left", "top", "right", "bottom", "updateMarginsRelative", "start", "end", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class ViewGroupKt
{
  public static final boolean contains(@NotNull ViewGroup paramViewGroup, @NotNull View paramView)
  {
    return paramViewGroup.indexOfChild(paramView) != -1;
  }
  
  public static final void forEach(@NotNull ViewGroup paramViewGroup, @NotNull Function1<? super View, Unit> paramFunction1)
  {
    int j = paramViewGroup.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = paramViewGroup.getChildAt(i);
      Intrinsics.checkExpressionValueIsNotNull(localView, "getChildAt(index)");
      paramFunction1.invoke(localView);
      i += 1;
    }
  }
  
  public static final void forEachIndexed(@NotNull ViewGroup paramViewGroup, @NotNull Function2<? super Integer, ? super View, Unit> paramFunction2)
  {
    int j = paramViewGroup.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = paramViewGroup.getChildAt(i);
      Intrinsics.checkExpressionValueIsNotNull(localView, "getChildAt(index)");
      paramFunction2.invoke(Integer.valueOf(i), localView);
      i += 1;
    }
  }
  
  @NotNull
  public static final View get(@NotNull ViewGroup paramViewGroup, int paramInt)
  {
    Object localObject = paramViewGroup.getChildAt(paramInt);
    if (localObject != null) {
      return localObject;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Index: ");
    ((StringBuilder)localObject).append(paramInt);
    ((StringBuilder)localObject).append(", Size: ");
    ((StringBuilder)localObject).append(paramViewGroup.getChildCount());
    throw ((Throwable)new IndexOutOfBoundsException(((StringBuilder)localObject).toString()));
  }
  
  @NotNull
  public static final Sequence<View> getChildren(@NotNull ViewGroup paramViewGroup)
  {
    (Sequence)new Sequence()
    {
      @NotNull
      public Iterator<View> iterator()
      {
        return ViewGroupKt.iterator(receiver$0);
      }
    };
  }
  
  public static final int getSize(@NotNull ViewGroup paramViewGroup)
  {
    return paramViewGroup.getChildCount();
  }
  
  public static final boolean isEmpty(@NotNull ViewGroup paramViewGroup)
  {
    return paramViewGroup.getChildCount() == 0;
  }
  
  public static final boolean isNotEmpty(@NotNull ViewGroup paramViewGroup)
  {
    return paramViewGroup.getChildCount() != 0;
  }
  
  @NotNull
  public static final Iterator<View> iterator(@NotNull ViewGroup paramViewGroup)
  {
    (Iterator)new Iterator()
    {
      private int index;
      
      public boolean hasNext()
      {
        return index < receiver$0.getChildCount();
      }
      
      @NotNull
      public View next()
      {
        Object localObject = receiver$0;
        int i = index;
        index = (i + 1);
        localObject = ((ViewGroup)localObject).getChildAt(i);
        if (localObject != null) {
          return localObject;
        }
        throw ((Throwable)new IndexOutOfBoundsException());
      }
      
      public void remove()
      {
        ViewGroup localViewGroup = receiver$0;
        index -= 1;
        localViewGroup.removeViewAt(index);
      }
    };
  }
  
  public static final void minusAssign(@NotNull ViewGroup paramViewGroup, @NotNull View paramView)
  {
    paramViewGroup.removeView(paramView);
  }
  
  public static final void plusAssign(@NotNull ViewGroup paramViewGroup, @NotNull View paramView)
  {
    paramViewGroup.addView(paramView);
  }
  
  public static final void setMargins(@NotNull ViewGroup.MarginLayoutParams paramMarginLayoutParams, @Px int paramInt)
  {
    paramMarginLayoutParams.setMargins(paramInt, paramInt, paramInt, paramInt);
  }
  
  public static final void updateLayoutParams(@NotNull ViewGroup paramViewGroup, @NotNull Function1<? super ViewGroup.LayoutParams, Unit> paramFunction1)
  {
    ViewGroup.LayoutParams localLayoutParams = paramViewGroup.getLayoutParams();
    if (localLayoutParams == null) {
      throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup.LayoutParams");
    }
    paramFunction1.invoke(localLayoutParams);
    paramViewGroup.setLayoutParams(localLayoutParams);
  }
  
  @JvmName(name="updateLayoutParamsTyped")
  private static final <T extends ViewGroup.LayoutParams> void updateLayoutParamsTyped(@NotNull ViewGroup paramViewGroup, Function1<? super T, Unit> paramFunction1)
  {
    ViewGroup.LayoutParams localLayoutParams = paramViewGroup.getLayoutParams();
    Intrinsics.reifiedOperationMarker(1, "T");
    localLayoutParams = (ViewGroup.LayoutParams)localLayoutParams;
    paramFunction1.invoke(localLayoutParams);
    paramViewGroup.setLayoutParams(localLayoutParams);
  }
  
  public static final void updateMargins(@NotNull ViewGroup.MarginLayoutParams paramMarginLayoutParams, @Px int paramInt1, @Px int paramInt2, @Px int paramInt3, @Px int paramInt4)
  {
    paramMarginLayoutParams.setMargins(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  @RequiresApi(17)
  public static final void updateMarginsRelative(@NotNull ViewGroup.MarginLayoutParams paramMarginLayoutParams, @Px int paramInt1, @Px int paramInt2, @Px int paramInt3, @Px int paramInt4)
  {
    paramMarginLayoutParams.setMarginStart(paramInt1);
    topMargin = paramInt2;
    paramMarginLayoutParams.setMarginEnd(paramInt3);
    bottomMargin = paramInt4;
  }
}

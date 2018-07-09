package androidx.view;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.support.annotation.Px;
import android.support.annotation.RequiresApi;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.View.OnLayoutChangeListener;
import android.view.ViewTreeObserver;
import android.view.ViewTreeObserver.OnPreDrawListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000H\n\000\n\002\020\013\n\000\n\002\030\002\n\002\b\b\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\020\t\n\002\030\002\n\002\b\003\n\002\020\b\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\t\0322\020\013\032\0020\f*\0020\0032#\b\004\020\r\032\035\022\023\022\0210\003¢\006\f\b\017\022\b\b\020\022\004\b\b(\021\022\004\022\0020\f0\016H\b\0322\020\022\032\0020\f*\0020\0032#\b\004\020\r\032\035\022\023\022\0210\003¢\006\f\b\017\022\b\b\020\022\004\b\b(\021\022\004\022\0020\f0\016H\b\0322\020\023\032\0020\f*\0020\0032#\b\004\020\r\032\035\022\023\022\0210\003¢\006\f\b\017\022\b\b\020\022\004\b\b(\021\022\004\022\0020\f0\016H\b\032%\020\024\032\0020\025*\0020\0032\006\020\026\032\0020\0272\016\b\004\020\r\032\b\022\004\022\0020\f0\030H\b\032%\020\031\032\0020\025*\0020\0032\006\020\026\032\0020\0272\016\b\004\020\r\032\b\022\004\022\0020\f0\030H\b\032\027\020\032\032\0020\f*\0020\0032\b\b\001\020\033\032\0020\034H\b\032\024\020\035\032\0020\036*\0020\0032\b\b\002\020\037\032\0020 \0325\020!\032\0020\f*\0020\0032\b\b\003\020\"\032\0020\0342\b\b\003\020#\032\0020\0342\b\b\003\020$\032\0020\0342\b\b\003\020%\032\0020\034H\b\0325\020&\032\0020\f*\0020\0032\b\b\003\020'\032\0020\0342\b\b\003\020#\032\0020\0342\b\b\003\020(\032\0020\0342\b\b\003\020%\032\0020\034H\b\"*\020\002\032\0020\001*\0020\0032\006\020\000\032\0020\0018Æ\002@Æ\002X\016¢\006\f\032\004\b\002\020\004\"\004\b\005\020\006\"*\020\007\032\0020\001*\0020\0032\006\020\000\032\0020\0018Æ\002@Æ\002X\016¢\006\f\032\004\b\007\020\004\"\004\b\b\020\006\"*\020\t\032\0020\001*\0020\0032\006\020\000\032\0020\0018Æ\002@Æ\002X\016¢\006\f\032\004\b\t\020\004\"\004\b\n\020\006¨\006)"}, d2={"value", "", "isGone", "Landroid/view/View;", "(Landroid/view/View;)Z", "setGone", "(Landroid/view/View;Z)V", "isInvisible", "setInvisible", "isVisible", "setVisible", "doOnLayout", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "view", "doOnNextLayout", "doOnPreDraw", "postDelayed", "Ljava/lang/Runnable;", "delayInMillis", "", "Lkotlin/Function0;", "postOnAnimationDelayed", "setPadding", "size", "", "toBitmap", "Landroid/graphics/Bitmap;", "config", "Landroid/graphics/Bitmap$Config;", "updatePadding", "left", "top", "right", "bottom", "updatePaddingRelative", "start", "end", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class ViewKt
{
  public static final void doOnLayout(@NotNull View paramView, @NotNull Function1<? super View, Unit> paramFunction1)
  {
    if ((ViewCompat.isLaidOut(paramView)) && (!paramView.isLayoutRequested()))
    {
      paramFunction1.invoke(paramView);
      return;
    }
    paramView.addOnLayoutChangeListener((View.OnLayoutChangeListener)new View.OnLayoutChangeListener()
    {
      public void onLayoutChange(@NotNull View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4, int paramAnonymousInt5, int paramAnonymousInt6, int paramAnonymousInt7, int paramAnonymousInt8)
      {
        paramAnonymousView.removeOnLayoutChangeListener((View.OnLayoutChangeListener)this);
        $action$inlined.invoke(paramAnonymousView);
      }
    });
  }
  
  public static final void doOnNextLayout(@NotNull View paramView, @NotNull Function1<? super View, Unit> paramFunction1)
  {
    paramView.addOnLayoutChangeListener((View.OnLayoutChangeListener)new View.OnLayoutChangeListener()
    {
      public void onLayoutChange(@NotNull View paramAnonymousView, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3, int paramAnonymousInt4, int paramAnonymousInt5, int paramAnonymousInt6, int paramAnonymousInt7, int paramAnonymousInt8)
      {
        paramAnonymousView.removeOnLayoutChangeListener((View.OnLayoutChangeListener)this);
        $action.invoke(paramAnonymousView);
      }
    });
  }
  
  public static final void doOnPreDraw(@NotNull View paramView, @NotNull final Function1<? super View, Unit> paramFunction1)
  {
    final ViewTreeObserver localViewTreeObserver = paramView.getViewTreeObserver();
    localViewTreeObserver.addOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)new ViewTreeObserver.OnPreDrawListener()
    {
      public boolean onPreDraw()
      {
        paramFunction1.invoke(receiver$0);
        ViewTreeObserver localViewTreeObserver = localViewTreeObserver;
        Intrinsics.checkExpressionValueIsNotNull(localViewTreeObserver, "vto");
        if (localViewTreeObserver.isAlive()) {
          localViewTreeObserver.removeOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this);
        } else {
          receiver$0.getViewTreeObserver().removeOnPreDrawListener((ViewTreeObserver.OnPreDrawListener)this);
        }
        return true;
      }
    });
  }
  
  public static final boolean isGone(@NotNull View paramView)
  {
    return paramView.getVisibility() == 8;
  }
  
  public static final boolean isInvisible(@NotNull View paramView)
  {
    return paramView.getVisibility() == 4;
  }
  
  public static final boolean isVisible(@NotNull View paramView)
  {
    return paramView.getVisibility() == 0;
  }
  
  @NotNull
  public static final Runnable postDelayed(@NotNull View paramView, long paramLong, @NotNull Function0<Unit> paramFunction0)
  {
    paramFunction0 = (Runnable)new Runnable()
    {
      public final void run()
      {
        $action.invoke();
      }
    };
    paramView.postDelayed(paramFunction0, paramLong);
    return paramFunction0;
  }
  
  @RequiresApi(16)
  @NotNull
  public static final Runnable postOnAnimationDelayed(@NotNull View paramView, long paramLong, @NotNull Function0<Unit> paramFunction0)
  {
    paramFunction0 = (Runnable)new Runnable()
    {
      public final void run()
      {
        $action.invoke();
      }
    };
    paramView.postOnAnimationDelayed(paramFunction0, paramLong);
    return paramFunction0;
  }
  
  public static final void setGone(@NotNull View paramView, boolean paramBoolean)
  {
    int i;
    if (paramBoolean) {
      i = 8;
    } else {
      i = 0;
    }
    paramView.setVisibility(i);
  }
  
  public static final void setInvisible(@NotNull View paramView, boolean paramBoolean)
  {
    int i;
    if (paramBoolean) {
      i = 4;
    } else {
      i = 0;
    }
    paramView.setVisibility(i);
  }
  
  public static final void setPadding(@NotNull View paramView, @Px int paramInt)
  {
    paramView.setPadding(paramInt, paramInt, paramInt, paramInt);
  }
  
  public static final void setVisible(@NotNull View paramView, boolean paramBoolean)
  {
    int i;
    if (paramBoolean) {
      i = 0;
    } else {
      i = 8;
    }
    paramView.setVisibility(i);
  }
  
  @NotNull
  public static final Bitmap toBitmap(@NotNull View paramView, @NotNull Bitmap.Config paramConfig)
  {
    if (!ViewCompat.isLaidOut(paramView)) {
      throw ((Throwable)new IllegalStateException("View needs to be laid out before calling toBitmap()"));
    }
    paramConfig = Bitmap.createBitmap(paramView.getWidth(), paramView.getHeight(), paramConfig);
    Intrinsics.checkExpressionValueIsNotNull(paramConfig, "Bitmap.createBitmap(width, height, config)");
    paramView.draw(new Canvas(paramConfig));
    return paramConfig;
  }
  
  public static final void updatePadding(@NotNull View paramView, @Px int paramInt1, @Px int paramInt2, @Px int paramInt3, @Px int paramInt4)
  {
    paramView.setPadding(paramInt1, paramInt2, paramInt3, paramInt4);
  }
  
  @RequiresApi(17)
  public static final void updatePaddingRelative(@NotNull View paramView, @Px int paramInt1, @Px int paramInt2, @Px int paramInt3, @Px int paramInt4)
  {
    paramView.setPaddingRelative(paramInt1, paramInt2, paramInt3, paramInt4);
  }
}

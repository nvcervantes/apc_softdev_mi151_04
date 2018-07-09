package androidx.animation;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.animation.Animator.AnimatorPauseListener;
import android.support.annotation.RequiresApi;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000(\n\000\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\002\b\004\n\002\030\002\n\002\b\n\032¦\001\020\000\032\0020\001*\0020\0022%\b\002\020\003\032\037\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\b\030\0010\0042%\b\002\020\t\032\037\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\b\030\0010\0042%\b\002\020\n\032\037\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\b\030\0010\0042%\b\002\020\013\032\037\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\b\030\0010\004\032Z\020\f\032\0020\r*\0020\0022%\b\002\020\016\032\037\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\b\030\0010\0042%\b\002\020\017\032\037\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\b\030\0010\004H\007\032-\020\020\032\0020\001*\0020\0022!\020\021\032\035\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\b0\004\032-\020\022\032\0020\001*\0020\0022!\020\021\032\035\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\b0\004\032/\020\023\032\0020\r*\0020\0022!\020\021\032\035\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\b0\004H\007\032-\020\024\032\0020\001*\0020\0022!\020\021\032\035\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\b0\004\032/\020\025\032\0020\r*\0020\0022!\020\021\032\035\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\b0\004H\007\032-\020\026\032\0020\001*\0020\0022!\020\021\032\035\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\b0\004¨\006\027"}, d2={"addListener", "Landroid/animation/Animator$AnimatorListener;", "Landroid/animation/Animator;", "onEnd", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "animator", "", "onStart", "onCancel", "onRepeat", "addPauseListener", "Landroid/animation/Animator$AnimatorPauseListener;", "onResume", "onPause", "doOnCancel", "action", "doOnEnd", "doOnPause", "doOnRepeat", "doOnResume", "doOnStart", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class AnimatorKt
{
  @NotNull
  public static final Animator.AnimatorListener addListener(@NotNull Animator paramAnimator, @Nullable final Function1<? super Animator, Unit> paramFunction11, @Nullable final Function1<? super Animator, Unit> paramFunction12, @Nullable final Function1<? super Animator, Unit> paramFunction13, @Nullable Function1<? super Animator, Unit> paramFunction14)
  {
    paramFunction11 = (Animator.AnimatorListener)new Animator.AnimatorListener()
    {
      public void onAnimationCancel(@NotNull Animator paramAnonymousAnimator)
      {
        Function1 localFunction1 = paramFunction13;
        if (localFunction1 != null) {
          paramAnonymousAnimator = (Unit)localFunction1.invoke(paramAnonymousAnimator);
        }
      }
      
      public void onAnimationEnd(@NotNull Animator paramAnonymousAnimator)
      {
        Function1 localFunction1 = paramFunction11;
        if (localFunction1 != null) {
          paramAnonymousAnimator = (Unit)localFunction1.invoke(paramAnonymousAnimator);
        }
      }
      
      public void onAnimationRepeat(@NotNull Animator paramAnonymousAnimator)
      {
        Function1 localFunction1 = $onRepeat;
        if (localFunction1 != null) {
          paramAnonymousAnimator = (Unit)localFunction1.invoke(paramAnonymousAnimator);
        }
      }
      
      public void onAnimationStart(@NotNull Animator paramAnonymousAnimator)
      {
        Function1 localFunction1 = paramFunction12;
        if (localFunction1 != null) {
          paramAnonymousAnimator = (Unit)localFunction1.invoke(paramAnonymousAnimator);
        }
      }
    };
    paramAnimator.addListener(paramFunction11);
    return paramFunction11;
  }
  
  @RequiresApi(19)
  @NotNull
  public static final Animator.AnimatorPauseListener addPauseListener(@NotNull Animator paramAnimator, @Nullable final Function1<? super Animator, Unit> paramFunction11, @Nullable Function1<? super Animator, Unit> paramFunction12)
  {
    paramFunction11 = (Animator.AnimatorPauseListener)new Animator.AnimatorPauseListener()
    {
      public void onAnimationPause(@NotNull Animator paramAnonymousAnimator)
      {
        Function1 localFunction1 = $onPause;
        if (localFunction1 != null) {
          paramAnonymousAnimator = (Unit)localFunction1.invoke(paramAnonymousAnimator);
        }
      }
      
      public void onAnimationResume(@NotNull Animator paramAnonymousAnimator)
      {
        Function1 localFunction1 = paramFunction11;
        if (localFunction1 != null) {
          paramAnonymousAnimator = (Unit)localFunction1.invoke(paramAnonymousAnimator);
        }
      }
    };
    paramAnimator.addPauseListener(paramFunction11);
    return paramFunction11;
  }
  
  @NotNull
  public static final Animator.AnimatorListener doOnCancel(@NotNull Animator paramAnimator, @NotNull Function1<? super Animator, Unit> paramFunction1)
  {
    return addListener$default(paramAnimator, null, null, paramFunction1, null, 11, null);
  }
  
  @NotNull
  public static final Animator.AnimatorListener doOnEnd(@NotNull Animator paramAnimator, @NotNull Function1<? super Animator, Unit> paramFunction1)
  {
    return addListener$default(paramAnimator, paramFunction1, null, null, null, 14, null);
  }
  
  @RequiresApi(19)
  @NotNull
  public static final Animator.AnimatorPauseListener doOnPause(@NotNull Animator paramAnimator, @NotNull Function1<? super Animator, Unit> paramFunction1)
  {
    return addPauseListener$default(paramAnimator, null, paramFunction1, 1, null);
  }
  
  @NotNull
  public static final Animator.AnimatorListener doOnRepeat(@NotNull Animator paramAnimator, @NotNull Function1<? super Animator, Unit> paramFunction1)
  {
    return addListener$default(paramAnimator, null, null, null, paramFunction1, 7, null);
  }
  
  @RequiresApi(19)
  @NotNull
  public static final Animator.AnimatorPauseListener doOnResume(@NotNull Animator paramAnimator, @NotNull Function1<? super Animator, Unit> paramFunction1)
  {
    return addPauseListener$default(paramAnimator, paramFunction1, null, 2, null);
  }
  
  @NotNull
  public static final Animator.AnimatorListener doOnStart(@NotNull Animator paramAnimator, @NotNull Function1<? super Animator, Unit> paramFunction1)
  {
    return addListener$default(paramAnimator, null, paramFunction1, null, null, 13, null);
  }
}

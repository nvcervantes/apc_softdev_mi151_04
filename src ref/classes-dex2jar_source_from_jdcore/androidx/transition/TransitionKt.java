package androidx.transition;

import android.support.annotation.RequiresApi;
import android.transition.Transition;
import android.transition.Transition.TransitionListener;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000\030\n\000\n\002\020\002\n\002\030\002\n\000\n\002\030\002\n\002\030\002\n\002\b\r\032Ï\001\020\000\032\0020\001*\0020\0022%\b\002\020\003\032\037\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\001\030\0010\0042%\b\002\020\b\032\037\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\001\030\0010\0042%\b\002\020\t\032\037\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\001\030\0010\0042%\b\002\020\n\032\037\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\001\030\0010\0042%\b\002\020\013\032\037\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\001\030\0010\004H\007\032/\020\f\032\0020\001*\0020\0022!\020\r\032\035\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\0010\004H\007\032/\020\016\032\0020\001*\0020\0022!\020\r\032\035\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\0010\004H\007\032/\020\017\032\0020\001*\0020\0022!\020\r\032\035\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\0010\004H\007\032/\020\020\032\0020\001*\0020\0022!\020\r\032\035\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\0010\004H\007\032/\020\021\032\0020\001*\0020\0022!\020\r\032\035\022\023\022\0210\002¢\006\f\b\005\022\b\b\006\022\004\b\b(\007\022\004\022\0020\0010\004H\007¨\006\022"}, d2={"addListener", "", "Landroid/transition/Transition;", "onEnd", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "transition", "onStart", "onCancel", "onResume", "onPause", "doOnCancel", "action", "doOnEnd", "doOnPause", "doOnResume", "doOnStart", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class TransitionKt
{
  @RequiresApi(19)
  public static final void addListener(@NotNull Transition paramTransition, @Nullable Function1<? super Transition, Unit> paramFunction11, @Nullable final Function1<? super Transition, Unit> paramFunction12, @Nullable final Function1<? super Transition, Unit> paramFunction13, @Nullable final Function1<? super Transition, Unit> paramFunction14, @Nullable final Function1<? super Transition, Unit> paramFunction15)
  {
    paramTransition.addListener((Transition.TransitionListener)new Transition.TransitionListener()
    {
      public void onTransitionCancel(@NotNull Transition paramAnonymousTransition)
      {
        Function1 localFunction1 = paramFunction13;
        if (localFunction1 != null) {
          paramAnonymousTransition = (Unit)localFunction1.invoke(paramAnonymousTransition);
        }
      }
      
      public void onTransitionEnd(@NotNull Transition paramAnonymousTransition)
      {
        Function1 localFunction1 = $onEnd;
        if (localFunction1 != null) {
          paramAnonymousTransition = (Unit)localFunction1.invoke(paramAnonymousTransition);
        }
      }
      
      public void onTransitionPause(@NotNull Transition paramAnonymousTransition)
      {
        Function1 localFunction1 = paramFunction15;
        if (localFunction1 != null) {
          paramAnonymousTransition = (Unit)localFunction1.invoke(paramAnonymousTransition);
        }
      }
      
      public void onTransitionResume(@NotNull Transition paramAnonymousTransition)
      {
        Function1 localFunction1 = paramFunction14;
        if (localFunction1 != null) {
          paramAnonymousTransition = (Unit)localFunction1.invoke(paramAnonymousTransition);
        }
      }
      
      public void onTransitionStart(@NotNull Transition paramAnonymousTransition)
      {
        Function1 localFunction1 = paramFunction12;
        if (localFunction1 != null) {
          paramAnonymousTransition = (Unit)localFunction1.invoke(paramAnonymousTransition);
        }
      }
    });
  }
  
  @RequiresApi(19)
  public static final void doOnCancel(@NotNull Transition paramTransition, @NotNull Function1<? super Transition, Unit> paramFunction1)
  {
    addListener$default(paramTransition, null, null, paramFunction1, null, null, 27, null);
  }
  
  @RequiresApi(19)
  public static final void doOnEnd(@NotNull Transition paramTransition, @NotNull Function1<? super Transition, Unit> paramFunction1)
  {
    addListener$default(paramTransition, paramFunction1, null, null, null, null, 30, null);
  }
  
  @RequiresApi(19)
  public static final void doOnPause(@NotNull Transition paramTransition, @NotNull Function1<? super Transition, Unit> paramFunction1)
  {
    addListener$default(paramTransition, null, null, null, null, paramFunction1, 15, null);
  }
  
  @RequiresApi(19)
  public static final void doOnResume(@NotNull Transition paramTransition, @NotNull Function1<? super Transition, Unit> paramFunction1)
  {
    addListener$default(paramTransition, null, null, null, paramFunction1, null, 23, null);
  }
  
  @RequiresApi(19)
  public static final void doOnStart(@NotNull Transition paramTransition, @NotNull Function1<? super Transition, Unit> paramFunction1)
  {
    addListener$default(paramTransition, null, paramFunction1, null, null, null, 29, null);
  }
}

package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;

abstract interface AnimatorUtilsImpl
{
  public abstract void addPauseListener(@NonNull Animator paramAnimator, @NonNull AnimatorListenerAdapter paramAnimatorListenerAdapter);
  
  public abstract void pause(@NonNull Animator paramAnimator);
  
  public abstract void resume(@NonNull Animator paramAnimator);
}

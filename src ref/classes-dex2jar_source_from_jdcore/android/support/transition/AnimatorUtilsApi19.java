package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;

@RequiresApi(19)
class AnimatorUtilsApi19
  implements AnimatorUtilsImpl
{
  AnimatorUtilsApi19() {}
  
  public void addPauseListener(@NonNull Animator paramAnimator, @NonNull AnimatorListenerAdapter paramAnimatorListenerAdapter)
  {
    paramAnimator.addPauseListener(paramAnimatorListenerAdapter);
  }
  
  public void pause(@NonNull Animator paramAnimator)
  {
    paramAnimator.pause();
  }
  
  public void resume(@NonNull Animator paramAnimator)
  {
    paramAnimator.resume();
  }
}

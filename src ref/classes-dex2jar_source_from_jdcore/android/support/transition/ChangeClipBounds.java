package android.support.transition;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.Map;

public class ChangeClipBounds
  extends Transition
{
  private static final String PROPNAME_BOUNDS = "android:clipBounds:bounds";
  private static final String PROPNAME_CLIP = "android:clipBounds:clip";
  private static final String[] sTransitionProperties = { "android:clipBounds:clip" };
  
  public ChangeClipBounds() {}
  
  public ChangeClipBounds(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
  }
  
  private void captureValues(TransitionValues paramTransitionValues)
  {
    Object localObject = view;
    if (((View)localObject).getVisibility() == 8) {
      return;
    }
    Rect localRect = ViewCompat.getClipBounds((View)localObject);
    values.put("android:clipBounds:clip", localRect);
    if (localRect == null)
    {
      localObject = new Rect(0, 0, ((View)localObject).getWidth(), ((View)localObject).getHeight());
      values.put("android:clipBounds:bounds", localObject);
    }
  }
  
  public void captureEndValues(@NonNull TransitionValues paramTransitionValues)
  {
    captureValues(paramTransitionValues);
  }
  
  public void captureStartValues(@NonNull TransitionValues paramTransitionValues)
  {
    captureValues(paramTransitionValues);
  }
  
  public Animator createAnimator(@NonNull ViewGroup paramViewGroup, TransitionValues paramTransitionValues1, TransitionValues paramTransitionValues2)
  {
    if ((paramTransitionValues1 != null) && (paramTransitionValues2 != null) && (values.containsKey("android:clipBounds:clip")))
    {
      if (!values.containsKey("android:clipBounds:clip")) {
        return null;
      }
      Object localObject = (Rect)values.get("android:clipBounds:clip");
      Rect localRect = (Rect)values.get("android:clipBounds:clip");
      int i;
      if (localRect == null) {
        i = 1;
      } else {
        i = 0;
      }
      if ((localObject == null) && (localRect == null)) {
        return null;
      }
      if (localObject == null)
      {
        paramViewGroup = (Rect)values.get("android:clipBounds:bounds");
        paramTransitionValues1 = localRect;
      }
      else
      {
        paramViewGroup = (ViewGroup)localObject;
        paramTransitionValues1 = localRect;
        if (localRect == null)
        {
          paramTransitionValues1 = (Rect)values.get("android:clipBounds:bounds");
          paramViewGroup = (ViewGroup)localObject;
        }
      }
      if (paramViewGroup.equals(paramTransitionValues1)) {
        return null;
      }
      ViewCompat.setClipBounds(view, paramViewGroup);
      localObject = new RectEvaluator(new Rect());
      paramViewGroup = ObjectAnimator.ofObject(view, ViewUtils.CLIP_BOUNDS, (TypeEvaluator)localObject, new Rect[] { paramViewGroup, paramTransitionValues1 });
      if (i != 0) {
        paramViewGroup.addListener(new AnimatorListenerAdapter()
        {
          public void onAnimationEnd(Animator paramAnonymousAnimator)
          {
            ViewCompat.setClipBounds(val$endView, null);
          }
        });
      }
      return paramViewGroup;
    }
    return null;
  }
  
  public String[] getTransitionProperties()
  {
    return sTransitionProperties;
  }
}

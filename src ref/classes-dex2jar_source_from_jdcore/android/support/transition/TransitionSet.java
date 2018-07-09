package android.support.transition;

import android.animation.TimeInterpolator;
import android.content.Context;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RestrictTo;
import android.support.v4.content.res.TypedArrayUtils;
import android.util.AndroidRuntimeException;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import java.util.ArrayList;
import java.util.Iterator;

public class TransitionSet
  extends Transition
{
  public static final int ORDERING_SEQUENTIAL = 1;
  public static final int ORDERING_TOGETHER = 0;
  private int mCurrentListeners;
  private boolean mPlayTogether = true;
  private boolean mStarted = false;
  private ArrayList<Transition> mTransitions = new ArrayList();
  
  public TransitionSet() {}
  
  public TransitionSet(Context paramContext, AttributeSet paramAttributeSet)
  {
    super(paramContext, paramAttributeSet);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, Styleable.TRANSITION_SET);
    setOrdering(TypedArrayUtils.getNamedInt(paramContext, (XmlResourceParser)paramAttributeSet, "transitionOrdering", 0, 0));
    paramContext.recycle();
  }
  
  private void setupStartEndListeners()
  {
    TransitionSetListener localTransitionSetListener = new TransitionSetListener(this);
    Iterator localIterator = mTransitions.iterator();
    while (localIterator.hasNext()) {
      ((Transition)localIterator.next()).addListener(localTransitionSetListener);
    }
    mCurrentListeners = mTransitions.size();
  }
  
  @NonNull
  public TransitionSet addListener(@NonNull Transition.TransitionListener paramTransitionListener)
  {
    return (TransitionSet)super.addListener(paramTransitionListener);
  }
  
  @NonNull
  public TransitionSet addTarget(@IdRes int paramInt)
  {
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).addTarget(paramInt);
      i += 1;
    }
    return (TransitionSet)super.addTarget(paramInt);
  }
  
  @NonNull
  public TransitionSet addTarget(@NonNull View paramView)
  {
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).addTarget(paramView);
      i += 1;
    }
    return (TransitionSet)super.addTarget(paramView);
  }
  
  @NonNull
  public TransitionSet addTarget(@NonNull Class paramClass)
  {
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).addTarget(paramClass);
      i += 1;
    }
    return (TransitionSet)super.addTarget(paramClass);
  }
  
  @NonNull
  public TransitionSet addTarget(@NonNull String paramString)
  {
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).addTarget(paramString);
      i += 1;
    }
    return (TransitionSet)super.addTarget(paramString);
  }
  
  @NonNull
  public TransitionSet addTransition(@NonNull Transition paramTransition)
  {
    mTransitions.add(paramTransition);
    mParent = this;
    if (mDuration >= 0L) {
      paramTransition.setDuration(mDuration);
    }
    return this;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void cancel()
  {
    super.cancel();
    int j = mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((Transition)mTransitions.get(i)).cancel();
      i += 1;
    }
  }
  
  public void captureEndValues(@NonNull TransitionValues paramTransitionValues)
  {
    if (isValidTarget(view))
    {
      Iterator localIterator = mTransitions.iterator();
      while (localIterator.hasNext())
      {
        Transition localTransition = (Transition)localIterator.next();
        if (localTransition.isValidTarget(view))
        {
          localTransition.captureEndValues(paramTransitionValues);
          mTargetedTransitions.add(localTransition);
        }
      }
    }
  }
  
  void capturePropagationValues(TransitionValues paramTransitionValues)
  {
    super.capturePropagationValues(paramTransitionValues);
    int j = mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((Transition)mTransitions.get(i)).capturePropagationValues(paramTransitionValues);
      i += 1;
    }
  }
  
  public void captureStartValues(@NonNull TransitionValues paramTransitionValues)
  {
    if (isValidTarget(view))
    {
      Iterator localIterator = mTransitions.iterator();
      while (localIterator.hasNext())
      {
        Transition localTransition = (Transition)localIterator.next();
        if (localTransition.isValidTarget(view))
        {
          localTransition.captureStartValues(paramTransitionValues);
          mTargetedTransitions.add(localTransition);
        }
      }
    }
  }
  
  public Transition clone()
  {
    TransitionSet localTransitionSet = (TransitionSet)super.clone();
    mTransitions = new ArrayList();
    int j = mTransitions.size();
    int i = 0;
    while (i < j)
    {
      localTransitionSet.addTransition(((Transition)mTransitions.get(i)).clone());
      i += 1;
    }
    return localTransitionSet;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void createAnimators(ViewGroup paramViewGroup, TransitionValuesMaps paramTransitionValuesMaps1, TransitionValuesMaps paramTransitionValuesMaps2, ArrayList<TransitionValues> paramArrayList1, ArrayList<TransitionValues> paramArrayList2)
  {
    long l1 = getStartDelay();
    int j = mTransitions.size();
    int i = 0;
    while (i < j)
    {
      Transition localTransition = (Transition)mTransitions.get(i);
      if ((l1 > 0L) && ((mPlayTogether) || (i == 0)))
      {
        long l2 = localTransition.getStartDelay();
        if (l2 > 0L) {
          localTransition.setStartDelay(l1 + l2);
        } else {
          localTransition.setStartDelay(l1);
        }
      }
      localTransition.createAnimators(paramViewGroup, paramTransitionValuesMaps1, paramTransitionValuesMaps2, paramArrayList1, paramArrayList2);
      i += 1;
    }
  }
  
  @NonNull
  public Transition excludeTarget(int paramInt, boolean paramBoolean)
  {
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).excludeTarget(paramInt, paramBoolean);
      i += 1;
    }
    return super.excludeTarget(paramInt, paramBoolean);
  }
  
  @NonNull
  public Transition excludeTarget(@NonNull View paramView, boolean paramBoolean)
  {
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).excludeTarget(paramView, paramBoolean);
      i += 1;
    }
    return super.excludeTarget(paramView, paramBoolean);
  }
  
  @NonNull
  public Transition excludeTarget(@NonNull Class paramClass, boolean paramBoolean)
  {
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).excludeTarget(paramClass, paramBoolean);
      i += 1;
    }
    return super.excludeTarget(paramClass, paramBoolean);
  }
  
  @NonNull
  public Transition excludeTarget(@NonNull String paramString, boolean paramBoolean)
  {
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).excludeTarget(paramString, paramBoolean);
      i += 1;
    }
    return super.excludeTarget(paramString, paramBoolean);
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  void forceToEnd(ViewGroup paramViewGroup)
  {
    super.forceToEnd(paramViewGroup);
    int j = mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((Transition)mTransitions.get(i)).forceToEnd(paramViewGroup);
      i += 1;
    }
  }
  
  public int getOrdering()
  {
    return mPlayTogether ^ true;
  }
  
  public Transition getTransitionAt(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < mTransitions.size())) {
      return (Transition)mTransitions.get(paramInt);
    }
    return null;
  }
  
  public int getTransitionCount()
  {
    return mTransitions.size();
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void pause(View paramView)
  {
    super.pause(paramView);
    int j = mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((Transition)mTransitions.get(i)).pause(paramView);
      i += 1;
    }
  }
  
  @NonNull
  public TransitionSet removeListener(@NonNull Transition.TransitionListener paramTransitionListener)
  {
    return (TransitionSet)super.removeListener(paramTransitionListener);
  }
  
  @NonNull
  public TransitionSet removeTarget(@IdRes int paramInt)
  {
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).removeTarget(paramInt);
      i += 1;
    }
    return (TransitionSet)super.removeTarget(paramInt);
  }
  
  @NonNull
  public TransitionSet removeTarget(@NonNull View paramView)
  {
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).removeTarget(paramView);
      i += 1;
    }
    return (TransitionSet)super.removeTarget(paramView);
  }
  
  @NonNull
  public TransitionSet removeTarget(@NonNull Class paramClass)
  {
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).removeTarget(paramClass);
      i += 1;
    }
    return (TransitionSet)super.removeTarget(paramClass);
  }
  
  @NonNull
  public TransitionSet removeTarget(@NonNull String paramString)
  {
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).removeTarget(paramString);
      i += 1;
    }
    return (TransitionSet)super.removeTarget(paramString);
  }
  
  @NonNull
  public TransitionSet removeTransition(@NonNull Transition paramTransition)
  {
    mTransitions.remove(paramTransition);
    mParent = null;
    return this;
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  public void resume(View paramView)
  {
    super.resume(paramView);
    int j = mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((Transition)mTransitions.get(i)).resume(paramView);
      i += 1;
    }
  }
  
  @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
  protected void runAnimators()
  {
    if (mTransitions.isEmpty())
    {
      start();
      end();
      return;
    }
    setupStartEndListeners();
    Object localObject;
    if (!mPlayTogether)
    {
      int i = 1;
      while (i < mTransitions.size())
      {
        ((Transition)mTransitions.get(i - 1)).addListener(new TransitionListenerAdapter()
        {
          public void onTransitionEnd(@NonNull Transition paramAnonymousTransition)
          {
            val$nextTransition.runAnimators();
            paramAnonymousTransition.removeListener(this);
          }
        });
        i += 1;
      }
      localObject = (Transition)mTransitions.get(0);
      if (localObject != null) {
        ((Transition)localObject).runAnimators();
      }
    }
    else
    {
      localObject = mTransitions.iterator();
      while (((Iterator)localObject).hasNext()) {
        ((Transition)((Iterator)localObject).next()).runAnimators();
      }
    }
  }
  
  void setCanRemoveViews(boolean paramBoolean)
  {
    super.setCanRemoveViews(paramBoolean);
    int j = mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((Transition)mTransitions.get(i)).setCanRemoveViews(paramBoolean);
      i += 1;
    }
  }
  
  @NonNull
  public TransitionSet setDuration(long paramLong)
  {
    super.setDuration(paramLong);
    if (mDuration >= 0L)
    {
      int j = mTransitions.size();
      int i = 0;
      while (i < j)
      {
        ((Transition)mTransitions.get(i)).setDuration(paramLong);
        i += 1;
      }
    }
    return this;
  }
  
  public void setEpicenterCallback(Transition.EpicenterCallback paramEpicenterCallback)
  {
    super.setEpicenterCallback(paramEpicenterCallback);
    int j = mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((Transition)mTransitions.get(i)).setEpicenterCallback(paramEpicenterCallback);
      i += 1;
    }
  }
  
  @NonNull
  public TransitionSet setInterpolator(@Nullable TimeInterpolator paramTimeInterpolator)
  {
    return (TransitionSet)super.setInterpolator(paramTimeInterpolator);
  }
  
  @NonNull
  public TransitionSet setOrdering(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Invalid parameter for TransitionSet ordering: ");
      localStringBuilder.append(paramInt);
      throw new AndroidRuntimeException(localStringBuilder.toString());
    case 1: 
      mPlayTogether = false;
      return this;
    }
    mPlayTogether = true;
    return this;
  }
  
  public void setPathMotion(PathMotion paramPathMotion)
  {
    super.setPathMotion(paramPathMotion);
    int i = 0;
    while (i < mTransitions.size())
    {
      ((Transition)mTransitions.get(i)).setPathMotion(paramPathMotion);
      i += 1;
    }
  }
  
  public void setPropagation(TransitionPropagation paramTransitionPropagation)
  {
    super.setPropagation(paramTransitionPropagation);
    int j = mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((Transition)mTransitions.get(i)).setPropagation(paramTransitionPropagation);
      i += 1;
    }
  }
  
  TransitionSet setSceneRoot(ViewGroup paramViewGroup)
  {
    super.setSceneRoot(paramViewGroup);
    int j = mTransitions.size();
    int i = 0;
    while (i < j)
    {
      ((Transition)mTransitions.get(i)).setSceneRoot(paramViewGroup);
      i += 1;
    }
    return this;
  }
  
  @NonNull
  public TransitionSet setStartDelay(long paramLong)
  {
    return (TransitionSet)super.setStartDelay(paramLong);
  }
  
  String toString(String paramString)
  {
    Object localObject = super.toString(paramString);
    int i = 0;
    while (i < mTransitions.size())
    {
      StringBuilder localStringBuilder1 = new StringBuilder();
      localStringBuilder1.append((String)localObject);
      localStringBuilder1.append("\n");
      localObject = (Transition)mTransitions.get(i);
      StringBuilder localStringBuilder2 = new StringBuilder();
      localStringBuilder2.append(paramString);
      localStringBuilder2.append("  ");
      localStringBuilder1.append(((Transition)localObject).toString(localStringBuilder2.toString()));
      localObject = localStringBuilder1.toString();
      i += 1;
    }
    return localObject;
  }
  
  static class TransitionSetListener
    extends TransitionListenerAdapter
  {
    TransitionSet mTransitionSet;
    
    TransitionSetListener(TransitionSet paramTransitionSet)
    {
      mTransitionSet = paramTransitionSet;
    }
    
    public void onTransitionEnd(@NonNull Transition paramTransition)
    {
      TransitionSet.access$106(mTransitionSet);
      if (mTransitionSet.mCurrentListeners == 0)
      {
        TransitionSet.access$002(mTransitionSet, false);
        mTransitionSet.end();
      }
      paramTransition.removeListener(this);
    }
    
    public void onTransitionStart(@NonNull Transition paramTransition)
    {
      if (!mTransitionSet.mStarted)
      {
        mTransitionSet.start();
        TransitionSet.access$002(mTransitionSet, true);
      }
    }
  }
}

package android.support.v4.app;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.arch.lifecycle.ViewModelStore;
import android.content.Context;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.v4.util.ArraySet;
import android.support.v4.util.DebugUtils;
import android.support.v4.util.LogWriter;
import android.support.v4.util.Pair;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater.Factory2;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.ScaleAnimation;
import android.view.animation.Transformation;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

final class FragmentManagerImpl
  extends FragmentManager
  implements LayoutInflater.Factory2
{
  static final Interpolator ACCELERATE_CUBIC = new AccelerateInterpolator(1.5F);
  static final Interpolator ACCELERATE_QUINT;
  static final int ANIM_DUR = 220;
  public static final int ANIM_STYLE_CLOSE_ENTER = 3;
  public static final int ANIM_STYLE_CLOSE_EXIT = 4;
  public static final int ANIM_STYLE_FADE_ENTER = 5;
  public static final int ANIM_STYLE_FADE_EXIT = 6;
  public static final int ANIM_STYLE_OPEN_ENTER = 1;
  public static final int ANIM_STYLE_OPEN_EXIT = 2;
  static boolean DEBUG = false;
  static final Interpolator DECELERATE_CUBIC;
  static final Interpolator DECELERATE_QUINT = new DecelerateInterpolator(2.5F);
  static final String TAG = "FragmentManager";
  static final String TARGET_REQUEST_CODE_STATE_TAG = "android:target_req_state";
  static final String TARGET_STATE_TAG = "android:target_state";
  static final String USER_VISIBLE_HINT_TAG = "android:user_visible_hint";
  static final String VIEW_STATE_TAG = "android:view_state";
  static Field sAnimationListenerField;
  SparseArray<Fragment> mActive;
  final ArrayList<Fragment> mAdded = new ArrayList();
  ArrayList<Integer> mAvailBackStackIndices;
  ArrayList<BackStackRecord> mBackStack;
  ArrayList<FragmentManager.OnBackStackChangedListener> mBackStackChangeListeners;
  ArrayList<BackStackRecord> mBackStackIndices;
  FragmentContainer mContainer;
  ArrayList<Fragment> mCreatedMenus;
  int mCurState = 0;
  boolean mDestroyed;
  Runnable mExecCommit = new Runnable()
  {
    public void run()
    {
      execPendingActions();
    }
  };
  boolean mExecutingActions;
  boolean mHavePendingDeferredStart;
  FragmentHostCallback mHost;
  private final CopyOnWriteArrayList<Pair<FragmentManager.FragmentLifecycleCallbacks, Boolean>> mLifecycleCallbacks = new CopyOnWriteArrayList();
  boolean mNeedMenuInvalidate;
  int mNextFragmentIndex = 0;
  String mNoTransactionsBecause;
  Fragment mParent;
  ArrayList<OpGenerator> mPendingActions;
  ArrayList<StartEnterTransitionListener> mPostponedTransactions;
  Fragment mPrimaryNav;
  FragmentManagerNonConfig mSavedNonConfig;
  SparseArray<Parcelable> mStateArray = null;
  Bundle mStateBundle = null;
  boolean mStateSaved;
  ArrayList<Fragment> mTmpAddedFragments;
  ArrayList<Boolean> mTmpIsPop;
  ArrayList<BackStackRecord> mTmpRecords;
  
  static
  {
    DECELERATE_CUBIC = new DecelerateInterpolator(1.5F);
    ACCELERATE_QUINT = new AccelerateInterpolator(2.5F);
  }
  
  FragmentManagerImpl() {}
  
  private void addAddedFragments(ArraySet<Fragment> paramArraySet)
  {
    if (mCurState < 1) {
      return;
    }
    int j = Math.min(mCurState, 4);
    int k = mAdded.size();
    int i = 0;
    while (i < k)
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (mState < j)
      {
        moveToState(localFragment, j, localFragment.getNextAnim(), localFragment.getNextTransition(), false);
        if ((mView != null) && (!mHidden) && (mIsNewlyAdded)) {
          paramArraySet.add(localFragment);
        }
      }
      i += 1;
    }
  }
  
  private void animateRemoveFragment(@NonNull final Fragment paramFragment, @NonNull AnimationOrAnimator paramAnimationOrAnimator, int paramInt)
  {
    final View localView = mView;
    final ViewGroup localViewGroup = mContainer;
    localViewGroup.startViewTransition(localView);
    paramFragment.setStateAfterAnimating(paramInt);
    if (animation != null)
    {
      localObject = new EndViewTransitionAnimator(animation, localViewGroup, localView);
      paramFragment.setAnimatingAway(mView);
      ((Animation)localObject).setAnimationListener(new AnimationListenerWrapper(getAnimationListener((Animation)localObject), localViewGroup)
      {
        public void onAnimationEnd(Animation paramAnonymousAnimation)
        {
          super.onAnimationEnd(paramAnonymousAnimation);
          localViewGroup.post(new Runnable()
          {
            public void run()
            {
              if (val$fragment.getAnimatingAway() != null)
              {
                val$fragment.setAnimatingAway(null);
                moveToState(val$fragment, val$fragment.getStateAfterAnimating(), 0, 0, false);
              }
            }
          });
        }
      });
      setHWLayerAnimListenerIfAlpha(localView, paramAnimationOrAnimator);
      mView.startAnimation((Animation)localObject);
      return;
    }
    Object localObject = animator;
    paramFragment.setAnimator(animator);
    ((Animator)localObject).addListener(new AnimatorListenerAdapter()
    {
      public void onAnimationEnd(Animator paramAnonymousAnimator)
      {
        localViewGroup.endViewTransition(localView);
        paramAnonymousAnimator = paramFragment.getAnimator();
        paramFragment.setAnimator(null);
        if ((paramAnonymousAnimator != null) && (localViewGroup.indexOfChild(localView) < 0)) {
          moveToState(paramFragment, paramFragment.getStateAfterAnimating(), 0, 0, false);
        }
      }
    });
    ((Animator)localObject).setTarget(mView);
    setHWLayerAnimListenerIfAlpha(mView, paramAnimationOrAnimator);
    ((Animator)localObject).start();
  }
  
  private void burpActive()
  {
    if (mActive != null)
    {
      int i = mActive.size() - 1;
      while (i >= 0)
      {
        if (mActive.valueAt(i) == null) {
          mActive.delete(mActive.keyAt(i));
        }
        i -= 1;
      }
    }
  }
  
  private void checkStateLoss()
  {
    if (mStateSaved) {
      throw new IllegalStateException("Can not perform this action after onSaveInstanceState");
    }
    if (mNoTransactionsBecause != null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Can not perform this action inside of ");
      localStringBuilder.append(mNoTransactionsBecause);
      throw new IllegalStateException(localStringBuilder.toString());
    }
  }
  
  private void cleanupExec()
  {
    mExecutingActions = false;
    mTmpIsPop.clear();
    mTmpRecords.clear();
  }
  
  private void completeExecute(BackStackRecord paramBackStackRecord, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    if (paramBoolean1) {
      paramBackStackRecord.executePopOps(paramBoolean3);
    } else {
      paramBackStackRecord.executeOps();
    }
    Object localObject = new ArrayList(1);
    ArrayList localArrayList = new ArrayList(1);
    ((ArrayList)localObject).add(paramBackStackRecord);
    localArrayList.add(Boolean.valueOf(paramBoolean1));
    if (paramBoolean2) {
      FragmentTransition.startTransitions(this, (ArrayList)localObject, localArrayList, 0, 1, true);
    }
    if (paramBoolean3) {
      moveToState(mCurState, true);
    }
    if (mActive != null)
    {
      int j = mActive.size();
      int i = 0;
      while (i < j)
      {
        localObject = (Fragment)mActive.valueAt(i);
        if ((localObject != null) && (mView != null) && (mIsNewlyAdded) && (paramBackStackRecord.interactsWith(mContainerId)))
        {
          if (mPostponedAlpha > 0.0F) {
            mView.setAlpha(mPostponedAlpha);
          }
          if (paramBoolean3)
          {
            mPostponedAlpha = 0.0F;
          }
          else
          {
            mPostponedAlpha = -1.0F;
            mIsNewlyAdded = false;
          }
        }
        i += 1;
      }
    }
  }
  
  private void dispatchStateChange(int paramInt)
  {
    try
    {
      mExecutingActions = true;
      moveToState(paramInt, false);
      mExecutingActions = false;
      execPendingActions();
      return;
    }
    finally
    {
      mExecutingActions = false;
    }
  }
  
  private void endAnimatingAwayFragments()
  {
    Object localObject = mActive;
    int j = 0;
    int i;
    if (localObject == null) {
      i = 0;
    } else {
      i = mActive.size();
    }
    while (j < i)
    {
      localObject = (Fragment)mActive.valueAt(j);
      if (localObject != null) {
        if (((Fragment)localObject).getAnimatingAway() != null)
        {
          int k = ((Fragment)localObject).getStateAfterAnimating();
          View localView = ((Fragment)localObject).getAnimatingAway();
          Animation localAnimation = localView.getAnimation();
          if (localAnimation != null)
          {
            localAnimation.cancel();
            localView.clearAnimation();
          }
          ((Fragment)localObject).setAnimatingAway(null);
          moveToState((Fragment)localObject, k, 0, 0, false);
        }
        else if (((Fragment)localObject).getAnimator() != null)
        {
          ((Fragment)localObject).getAnimator().end();
        }
      }
      j += 1;
    }
  }
  
  private void ensureExecReady(boolean paramBoolean)
  {
    if (mExecutingActions) {
      throw new IllegalStateException("FragmentManager is already executing transactions");
    }
    if (mHost == null) {
      throw new IllegalStateException("Fragment host has been destroyed");
    }
    if (Looper.myLooper() != mHost.getHandler().getLooper()) {
      throw new IllegalStateException("Must be called from main thread of fragment host");
    }
    if (!paramBoolean) {
      checkStateLoss();
    }
    if (mTmpRecords == null)
    {
      mTmpRecords = new ArrayList();
      mTmpIsPop = new ArrayList();
    }
    mExecutingActions = true;
    try
    {
      executePostponedTransaction(null, null);
      return;
    }
    finally
    {
      mExecutingActions = false;
    }
  }
  
  private static void executeOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2)
  {
    while (paramInt1 < paramInt2)
    {
      BackStackRecord localBackStackRecord = (BackStackRecord)paramArrayList.get(paramInt1);
      boolean bool2 = ((Boolean)paramArrayList1.get(paramInt1)).booleanValue();
      boolean bool1 = true;
      if (bool2)
      {
        localBackStackRecord.bumpBackStackNesting(-1);
        if (paramInt1 != paramInt2 - 1) {
          bool1 = false;
        }
        localBackStackRecord.executePopOps(bool1);
      }
      else
      {
        localBackStackRecord.bumpBackStackNesting(1);
        localBackStackRecord.executeOps();
      }
      paramInt1 += 1;
    }
  }
  
  private void executeOpsTogether(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2)
  {
    int i = paramInt1;
    boolean bool = getmReorderingAllowed;
    if (mTmpAddedFragments == null) {
      mTmpAddedFragments = new ArrayList();
    } else {
      mTmpAddedFragments.clear();
    }
    mTmpAddedFragments.addAll(mAdded);
    Object localObject = getPrimaryNavigationFragment();
    int j = 0;
    int k = i;
    while (k < paramInt2)
    {
      BackStackRecord localBackStackRecord = (BackStackRecord)paramArrayList.get(k);
      if (!((Boolean)paramArrayList1.get(k)).booleanValue()) {
        localObject = localBackStackRecord.expandOps(mTmpAddedFragments, (Fragment)localObject);
      } else {
        localObject = localBackStackRecord.trackAddedFragmentsInPop(mTmpAddedFragments, (Fragment)localObject);
      }
      if ((j == 0) && (!mAddToBackStack)) {
        j = 0;
      } else {
        j = 1;
      }
      k += 1;
    }
    mTmpAddedFragments.clear();
    if (!bool) {
      FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, i, paramInt2, false);
    }
    executeOps(paramArrayList, paramArrayList1, paramInt1, paramInt2);
    if (bool)
    {
      localObject = new ArraySet();
      addAddedFragments((ArraySet)localObject);
      paramInt1 = postponePostponableTransactions(paramArrayList, paramArrayList1, i, paramInt2, (ArraySet)localObject);
      makeRemovedFragmentsInvisible((ArraySet)localObject);
    }
    else
    {
      paramInt1 = paramInt2;
    }
    k = i;
    if (paramInt1 != i)
    {
      k = i;
      if (bool)
      {
        FragmentTransition.startTransitions(this, paramArrayList, paramArrayList1, i, paramInt1, true);
        moveToState(mCurState, true);
        k = i;
      }
    }
    while (k < paramInt2)
    {
      localObject = (BackStackRecord)paramArrayList.get(k);
      if ((((Boolean)paramArrayList1.get(k)).booleanValue()) && (mIndex >= 0))
      {
        freeBackStackIndex(mIndex);
        mIndex = -1;
      }
      ((BackStackRecord)localObject).runOnCommitRunnables();
      k += 1;
    }
    if (j != 0) {
      reportBackStackChanged();
    }
  }
  
  private void executePostponedTransaction(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1)
  {
    if (mPostponedTransactions == null) {
      i = 0;
    } else {
      i = mPostponedTransactions.size();
    }
    int k = 0;
    int j = i;
    int i = k;
    while (i < j)
    {
      StartEnterTransitionListener localStartEnterTransitionListener = (StartEnterTransitionListener)mPostponedTransactions.get(i);
      int m;
      if ((paramArrayList != null) && (!mIsBack))
      {
        k = paramArrayList.indexOf(mRecord);
        if ((k != -1) && (((Boolean)paramArrayList1.get(k)).booleanValue()))
        {
          localStartEnterTransitionListener.cancelTransaction();
          m = i;
          k = j;
          break label224;
        }
      }
      if (!localStartEnterTransitionListener.isReady())
      {
        m = i;
        k = j;
        if (paramArrayList != null)
        {
          m = i;
          k = j;
          if (!mRecord.interactsWith(paramArrayList, 0, paramArrayList.size())) {}
        }
      }
      else
      {
        mPostponedTransactions.remove(i);
        m = i - 1;
        k = j - 1;
        if ((paramArrayList != null) && (!mIsBack))
        {
          i = paramArrayList.indexOf(mRecord);
          if ((i != -1) && (((Boolean)paramArrayList1.get(i)).booleanValue()))
          {
            localStartEnterTransitionListener.cancelTransaction();
            break label224;
          }
        }
        localStartEnterTransitionListener.completeTransaction();
      }
      label224:
      i = m + 1;
      j = k;
    }
  }
  
  private Fragment findFragmentUnder(Fragment paramFragment)
  {
    ViewGroup localViewGroup = mContainer;
    View localView = mView;
    if (localViewGroup != null)
    {
      if (localView == null) {
        return null;
      }
      int i = mAdded.indexOf(paramFragment) - 1;
      while (i >= 0)
      {
        paramFragment = (Fragment)mAdded.get(i);
        if ((mContainer == localViewGroup) && (mView != null)) {
          return paramFragment;
        }
        i -= 1;
      }
      return null;
    }
    return null;
  }
  
  private void forcePostponedTransactions()
  {
    if (mPostponedTransactions != null) {
      while (!mPostponedTransactions.isEmpty()) {
        ((StartEnterTransitionListener)mPostponedTransactions.remove(0)).completeTransaction();
      }
    }
  }
  
  private boolean generateOpsForPendingActions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1)
  {
    try
    {
      ArrayList localArrayList = mPendingActions;
      int i = 0;
      if ((localArrayList != null) && (mPendingActions.size() != 0))
      {
        int j = mPendingActions.size();
        boolean bool = false;
        while (i < j)
        {
          bool |= ((OpGenerator)mPendingActions.get(i)).generateOps(paramArrayList, paramArrayList1);
          i += 1;
        }
        mPendingActions.clear();
        mHost.getHandler().removeCallbacks(mExecCommit);
        return bool;
      }
      return false;
    }
    finally {}
  }
  
  private static Animation.AnimationListener getAnimationListener(Animation paramAnimation)
  {
    try
    {
      if (sAnimationListenerField == null)
      {
        sAnimationListenerField = Animation.class.getDeclaredField("mListener");
        sAnimationListenerField.setAccessible(true);
      }
      paramAnimation = (Animation.AnimationListener)sAnimationListenerField.get(paramAnimation);
      return paramAnimation;
    }
    catch (IllegalAccessException paramAnimation)
    {
      Log.e("FragmentManager", "Cannot access Animation's mListener field", paramAnimation);
    }
    catch (NoSuchFieldException paramAnimation)
    {
      Log.e("FragmentManager", "No field with the name mListener is found in Animation class", paramAnimation);
    }
    return null;
  }
  
  static AnimationOrAnimator makeFadeAnimation(Context paramContext, float paramFloat1, float paramFloat2)
  {
    paramContext = new AlphaAnimation(paramFloat1, paramFloat2);
    paramContext.setInterpolator(DECELERATE_CUBIC);
    paramContext.setDuration(220L);
    return new AnimationOrAnimator(paramContext, null);
  }
  
  static AnimationOrAnimator makeOpenCloseAnimation(Context paramContext, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4)
  {
    paramContext = new AnimationSet(false);
    Object localObject = new ScaleAnimation(paramFloat1, paramFloat2, paramFloat1, paramFloat2, 1, 0.5F, 1, 0.5F);
    ((ScaleAnimation)localObject).setInterpolator(DECELERATE_QUINT);
    ((ScaleAnimation)localObject).setDuration(220L);
    paramContext.addAnimation((Animation)localObject);
    localObject = new AlphaAnimation(paramFloat3, paramFloat4);
    ((AlphaAnimation)localObject).setInterpolator(DECELERATE_CUBIC);
    ((AlphaAnimation)localObject).setDuration(220L);
    paramContext.addAnimation((Animation)localObject);
    return new AnimationOrAnimator(paramContext, null);
  }
  
  private void makeRemovedFragmentsInvisible(ArraySet<Fragment> paramArraySet)
  {
    int j = paramArraySet.size();
    int i = 0;
    while (i < j)
    {
      Fragment localFragment = (Fragment)paramArraySet.valueAt(i);
      if (!mAdded)
      {
        View localView = localFragment.getView();
        mPostponedAlpha = localView.getAlpha();
        localView.setAlpha(0.0F);
      }
      i += 1;
    }
  }
  
  static boolean modifiesAlpha(Animator paramAnimator)
  {
    if (paramAnimator == null) {
      return false;
    }
    int i;
    if ((paramAnimator instanceof ValueAnimator))
    {
      paramAnimator = ((ValueAnimator)paramAnimator).getValues();
      i = 0;
      while (i < paramAnimator.length)
      {
        if ("alpha".equals(paramAnimator[i].getPropertyName())) {
          return true;
        }
        i += 1;
      }
    }
    if ((paramAnimator instanceof AnimatorSet))
    {
      paramAnimator = ((AnimatorSet)paramAnimator).getChildAnimations();
      i = 0;
      while (i < paramAnimator.size())
      {
        if (modifiesAlpha((Animator)paramAnimator.get(i))) {
          return true;
        }
        i += 1;
      }
    }
    return false;
  }
  
  static boolean modifiesAlpha(AnimationOrAnimator paramAnimationOrAnimator)
  {
    if ((animation instanceof AlphaAnimation)) {
      return true;
    }
    if ((animation instanceof AnimationSet))
    {
      paramAnimationOrAnimator = ((AnimationSet)animation).getAnimations();
      int i = 0;
      while (i < paramAnimationOrAnimator.size())
      {
        if ((paramAnimationOrAnimator.get(i) instanceof AlphaAnimation)) {
          return true;
        }
        i += 1;
      }
      return false;
    }
    return modifiesAlpha(animator);
  }
  
  private boolean popBackStackImmediate(String paramString, int paramInt1, int paramInt2)
  {
    execPendingActions();
    ensureExecReady(true);
    if ((mPrimaryNav != null) && (paramInt1 < 0) && (paramString == null))
    {
      FragmentManager localFragmentManager = mPrimaryNav.peekChildFragmentManager();
      if ((localFragmentManager != null) && (localFragmentManager.popBackStackImmediate())) {
        return true;
      }
    }
    boolean bool = popBackStackState(mTmpRecords, mTmpIsPop, paramString, paramInt1, paramInt2);
    if (bool) {
      mExecutingActions = true;
    }
    try
    {
      removeRedundantOperationsAndExecute(mTmpRecords, mTmpIsPop);
      cleanupExec();
    }
    finally
    {
      cleanupExec();
    }
    burpActive();
    return bool;
  }
  
  private int postponePostponableTransactions(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, ArraySet<Fragment> paramArraySet)
  {
    int i = paramInt2 - 1;
    int k;
    for (int j = paramInt2; i >= paramInt1; j = k)
    {
      BackStackRecord localBackStackRecord = (BackStackRecord)paramArrayList.get(i);
      boolean bool = ((Boolean)paramArrayList1.get(i)).booleanValue();
      int m;
      if ((localBackStackRecord.isPostponed()) && (!localBackStackRecord.interactsWith(paramArrayList, i + 1, paramInt2))) {
        m = 1;
      } else {
        m = 0;
      }
      k = j;
      if (m != 0)
      {
        if (mPostponedTransactions == null) {
          mPostponedTransactions = new ArrayList();
        }
        StartEnterTransitionListener localStartEnterTransitionListener = new StartEnterTransitionListener(localBackStackRecord, bool);
        mPostponedTransactions.add(localStartEnterTransitionListener);
        localBackStackRecord.setOnStartPostponedListener(localStartEnterTransitionListener);
        if (bool) {
          localBackStackRecord.executeOps();
        } else {
          localBackStackRecord.executePopOps(false);
        }
        k = j - 1;
        if (i != k)
        {
          paramArrayList.remove(i);
          paramArrayList.add(k, localBackStackRecord);
        }
        addAddedFragments(paramArraySet);
      }
      i -= 1;
    }
    return j;
  }
  
  private void removeRedundantOperationsAndExecute(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1)
  {
    if (paramArrayList != null)
    {
      if (paramArrayList.isEmpty()) {
        return;
      }
      if ((paramArrayList1 != null) && (paramArrayList.size() == paramArrayList1.size()))
      {
        executePostponedTransaction(paramArrayList, paramArrayList1);
        int n = paramArrayList.size();
        int i = 0;
        int j;
        for (int k = 0; i < n; k = j)
        {
          int m = i;
          j = k;
          if (!getmReorderingAllowed)
          {
            if (k != i) {
              executeOpsTogether(paramArrayList, paramArrayList1, k, i);
            }
            k = i + 1;
            j = k;
            if (((Boolean)paramArrayList1.get(i)).booleanValue()) {
              for (;;)
              {
                j = k;
                if (k >= n) {
                  break;
                }
                j = k;
                if (!((Boolean)paramArrayList1.get(k)).booleanValue()) {
                  break;
                }
                j = k;
                if (getmReorderingAllowed) {
                  break;
                }
                k += 1;
              }
            }
            executeOpsTogether(paramArrayList, paramArrayList1, i, j);
            m = j - 1;
          }
          i = m + 1;
        }
        if (k != n) {
          executeOpsTogether(paramArrayList, paramArrayList1, k, n);
        }
        return;
      }
      throw new IllegalStateException("Internal error with the back stack records");
    }
  }
  
  public static int reverseTransit(int paramInt)
  {
    int i = 8194;
    if (paramInt != 4097)
    {
      if (paramInt != 4099)
      {
        if (paramInt != 8194) {
          return 0;
        }
        return 4097;
      }
      i = 4099;
    }
    return i;
  }
  
  private void scheduleCommit()
  {
    for (;;)
    {
      int j;
      try
      {
        ArrayList localArrayList = mPostponedTransactions;
        int k = 0;
        if ((localArrayList == null) || (mPostponedTransactions.isEmpty())) {
          break label96;
        }
        i = 1;
        j = k;
        if (mPendingActions == null) {
          break label101;
        }
        j = k;
        if (mPendingActions.size() != 1) {
          break label101;
        }
        j = 1;
      }
      finally {}
      mHost.getHandler().removeCallbacks(mExecCommit);
      mHost.getHandler().post(mExecCommit);
      return;
      label96:
      int i = 0;
      continue;
      label101:
      if (i == 0) {
        if (j == 0) {}
      }
    }
  }
  
  private static void setHWLayerAnimListenerIfAlpha(View paramView, AnimationOrAnimator paramAnimationOrAnimator)
  {
    if (paramView != null)
    {
      if (paramAnimationOrAnimator == null) {
        return;
      }
      if (shouldRunOnHWLayer(paramView, paramAnimationOrAnimator))
      {
        if (animator != null)
        {
          animator.addListener(new AnimatorOnHWLayerIfNeededListener(paramView));
          return;
        }
        Animation.AnimationListener localAnimationListener = getAnimationListener(animation);
        paramView.setLayerType(2, null);
        animation.setAnimationListener(new AnimateOnHWLayerIfNeededListener(paramView, localAnimationListener));
      }
      return;
    }
  }
  
  private static void setRetaining(FragmentManagerNonConfig paramFragmentManagerNonConfig)
  {
    if (paramFragmentManagerNonConfig == null) {
      return;
    }
    Object localObject = paramFragmentManagerNonConfig.getFragments();
    if (localObject != null)
    {
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext()) {
        nextmRetaining = true;
      }
    }
    paramFragmentManagerNonConfig = paramFragmentManagerNonConfig.getChildNonConfigs();
    if (paramFragmentManagerNonConfig != null)
    {
      paramFragmentManagerNonConfig = paramFragmentManagerNonConfig.iterator();
      while (paramFragmentManagerNonConfig.hasNext()) {
        setRetaining((FragmentManagerNonConfig)paramFragmentManagerNonConfig.next());
      }
    }
  }
  
  static boolean shouldRunOnHWLayer(View paramView, AnimationOrAnimator paramAnimationOrAnimator)
  {
    boolean bool2 = false;
    if (paramView != null)
    {
      if (paramAnimationOrAnimator == null) {
        return false;
      }
      boolean bool1 = bool2;
      if (Build.VERSION.SDK_INT >= 19)
      {
        bool1 = bool2;
        if (paramView.getLayerType() == 0)
        {
          bool1 = bool2;
          if (ViewCompat.hasOverlappingRendering(paramView))
          {
            bool1 = bool2;
            if (modifiesAlpha(paramAnimationOrAnimator)) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    return false;
  }
  
  private void throwException(RuntimeException paramRuntimeException)
  {
    Log.e("FragmentManager", paramRuntimeException.getMessage());
    Log.e("FragmentManager", "Activity state:");
    PrintWriter localPrintWriter = new PrintWriter(new LogWriter("FragmentManager"));
    if (mHost != null) {
      try
      {
        mHost.onDump("  ", null, localPrintWriter, new String[0]);
      }
      catch (Exception localException1)
      {
        Log.e("FragmentManager", "Failed dumping state", localException1);
      }
    } else {
      try
      {
        dump("  ", null, localException1, new String[0]);
      }
      catch (Exception localException2)
      {
        Log.e("FragmentManager", "Failed dumping state", localException2);
      }
    }
    throw paramRuntimeException;
  }
  
  public static int transitToStyleIndex(int paramInt, boolean paramBoolean)
  {
    if (paramInt != 4097)
    {
      if (paramInt != 4099)
      {
        if (paramInt != 8194) {
          return -1;
        }
        if (paramBoolean) {
          return 3;
        }
        return 4;
      }
      if (paramBoolean) {
        return 5;
      }
      return 6;
    }
    if (paramBoolean) {
      return 1;
    }
    return 2;
  }
  
  void addBackStackState(BackStackRecord paramBackStackRecord)
  {
    if (mBackStack == null) {
      mBackStack = new ArrayList();
    }
    mBackStack.add(paramBackStackRecord);
  }
  
  public void addFragment(Fragment paramFragment, boolean paramBoolean)
  {
    if (DEBUG)
    {
      ??? = new StringBuilder();
      ((StringBuilder)???).append("add: ");
      ((StringBuilder)???).append(paramFragment);
      Log.v("FragmentManager", ((StringBuilder)???).toString());
    }
    makeActive(paramFragment);
    if (!mDetached)
    {
      if (mAdded.contains(paramFragment))
      {
        ??? = new StringBuilder();
        ((StringBuilder)???).append("Fragment already added: ");
        ((StringBuilder)???).append(paramFragment);
        throw new IllegalStateException(((StringBuilder)???).toString());
      }
      synchronized (mAdded)
      {
        mAdded.add(paramFragment);
        mAdded = true;
        mRemoving = false;
        if (mView == null) {
          mHiddenChanged = false;
        }
        if ((mHasMenu) && (mMenuVisible)) {
          mNeedMenuInvalidate = true;
        }
        if (paramBoolean)
        {
          moveToState(paramFragment);
          return;
        }
      }
    }
  }
  
  public void addOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener)
  {
    if (mBackStackChangeListeners == null) {
      mBackStackChangeListeners = new ArrayList();
    }
    mBackStackChangeListeners.add(paramOnBackStackChangedListener);
  }
  
  public int allocBackStackIndex(BackStackRecord paramBackStackRecord)
  {
    try
    {
      StringBuilder localStringBuilder;
      if ((mAvailBackStackIndices != null) && (mAvailBackStackIndices.size() > 0))
      {
        i = ((Integer)mAvailBackStackIndices.remove(mAvailBackStackIndices.size() - 1)).intValue();
        if (DEBUG)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Adding back stack index ");
          localStringBuilder.append(i);
          localStringBuilder.append(" with ");
          localStringBuilder.append(paramBackStackRecord);
          Log.v("FragmentManager", localStringBuilder.toString());
        }
        mBackStackIndices.set(i, paramBackStackRecord);
        return i;
      }
      if (mBackStackIndices == null) {
        mBackStackIndices = new ArrayList();
      }
      int i = mBackStackIndices.size();
      if (DEBUG)
      {
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Setting back stack index ");
        localStringBuilder.append(i);
        localStringBuilder.append(" to ");
        localStringBuilder.append(paramBackStackRecord);
        Log.v("FragmentManager", localStringBuilder.toString());
      }
      mBackStackIndices.add(paramBackStackRecord);
      return i;
    }
    finally {}
  }
  
  public void attachController(FragmentHostCallback paramFragmentHostCallback, FragmentContainer paramFragmentContainer, Fragment paramFragment)
  {
    if (mHost != null) {
      throw new IllegalStateException("Already attached");
    }
    mHost = paramFragmentHostCallback;
    mContainer = paramFragmentContainer;
    mParent = paramFragment;
  }
  
  public void attachFragment(Fragment paramFragment)
  {
    if (DEBUG)
    {
      ??? = new StringBuilder();
      ((StringBuilder)???).append("attach: ");
      ((StringBuilder)???).append(paramFragment);
      Log.v("FragmentManager", ((StringBuilder)???).toString());
    }
    if (mDetached)
    {
      mDetached = false;
      if (!mAdded)
      {
        if (mAdded.contains(paramFragment))
        {
          ??? = new StringBuilder();
          ((StringBuilder)???).append("Fragment already added: ");
          ((StringBuilder)???).append(paramFragment);
          throw new IllegalStateException(((StringBuilder)???).toString());
        }
        if (DEBUG)
        {
          ??? = new StringBuilder();
          ((StringBuilder)???).append("add from attach: ");
          ((StringBuilder)???).append(paramFragment);
          Log.v("FragmentManager", ((StringBuilder)???).toString());
        }
        synchronized (mAdded)
        {
          mAdded.add(paramFragment);
          mAdded = true;
          if ((mHasMenu) && (mMenuVisible))
          {
            mNeedMenuInvalidate = true;
            return;
          }
        }
      }
    }
  }
  
  public FragmentTransaction beginTransaction()
  {
    return new BackStackRecord(this);
  }
  
  void completeShowHideFragment(final Fragment paramFragment)
  {
    if (mView != null)
    {
      AnimationOrAnimator localAnimationOrAnimator = loadAnimation(paramFragment, paramFragment.getNextTransition(), mHidden ^ true, paramFragment.getNextTransitionStyle());
      if ((localAnimationOrAnimator != null) && (animator != null))
      {
        animator.setTarget(mView);
        if (mHidden)
        {
          if (paramFragment.isHideReplaced())
          {
            paramFragment.setHideReplaced(false);
          }
          else
          {
            final ViewGroup localViewGroup = mContainer;
            final View localView = mView;
            localViewGroup.startViewTransition(localView);
            animator.addListener(new AnimatorListenerAdapter()
            {
              public void onAnimationEnd(Animator paramAnonymousAnimator)
              {
                localViewGroup.endViewTransition(localView);
                paramAnonymousAnimator.removeListener(this);
                if (paramFragmentmView != null) {
                  paramFragmentmView.setVisibility(8);
                }
              }
            });
          }
        }
        else {
          mView.setVisibility(0);
        }
        setHWLayerAnimListenerIfAlpha(mView, localAnimationOrAnimator);
        animator.start();
      }
      else
      {
        if (localAnimationOrAnimator != null)
        {
          setHWLayerAnimListenerIfAlpha(mView, localAnimationOrAnimator);
          mView.startAnimation(animation);
          animation.start();
        }
        int i;
        if ((mHidden) && (!paramFragment.isHideReplaced())) {
          i = 8;
        } else {
          i = 0;
        }
        mView.setVisibility(i);
        if (paramFragment.isHideReplaced()) {
          paramFragment.setHideReplaced(false);
        }
      }
    }
    if ((mAdded) && (mHasMenu) && (mMenuVisible)) {
      mNeedMenuInvalidate = true;
    }
    mHiddenChanged = false;
    paramFragment.onHiddenChanged(mHidden);
  }
  
  public void detachFragment(Fragment paramFragment)
  {
    if (DEBUG)
    {
      ??? = new StringBuilder();
      ((StringBuilder)???).append("detach: ");
      ((StringBuilder)???).append(paramFragment);
      Log.v("FragmentManager", ((StringBuilder)???).toString());
    }
    if (!mDetached)
    {
      mDetached = true;
      if (mAdded)
      {
        if (DEBUG)
        {
          ??? = new StringBuilder();
          ((StringBuilder)???).append("remove from detach: ");
          ((StringBuilder)???).append(paramFragment);
          Log.v("FragmentManager", ((StringBuilder)???).toString());
        }
        synchronized (mAdded)
        {
          mAdded.remove(paramFragment);
          if ((mHasMenu) && (mMenuVisible)) {
            mNeedMenuInvalidate = true;
          }
          mAdded = false;
          return;
        }
      }
    }
  }
  
  public void dispatchActivityCreated()
  {
    mStateSaved = false;
    dispatchStateChange(2);
  }
  
  public void dispatchConfigurationChanged(Configuration paramConfiguration)
  {
    int i = 0;
    while (i < mAdded.size())
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (localFragment != null) {
        localFragment.performConfigurationChanged(paramConfiguration);
      }
      i += 1;
    }
  }
  
  public boolean dispatchContextItemSelected(MenuItem paramMenuItem)
  {
    if (mCurState < 1) {
      return false;
    }
    int i = 0;
    while (i < mAdded.size())
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if ((localFragment != null) && (localFragment.performContextItemSelected(paramMenuItem))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public void dispatchCreate()
  {
    mStateSaved = false;
    dispatchStateChange(1);
  }
  
  public boolean dispatchCreateOptionsMenu(Menu paramMenu, MenuInflater paramMenuInflater)
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge Z and I\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public void dispatchDestroy()
  {
    mDestroyed = true;
    execPendingActions();
    dispatchStateChange(0);
    mHost = null;
    mContainer = null;
    mParent = null;
  }
  
  public void dispatchDestroyView()
  {
    dispatchStateChange(1);
  }
  
  public void dispatchLowMemory()
  {
    int i = 0;
    while (i < mAdded.size())
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (localFragment != null) {
        localFragment.performLowMemory();
      }
      i += 1;
    }
  }
  
  public void dispatchMultiWindowModeChanged(boolean paramBoolean)
  {
    int i = mAdded.size() - 1;
    while (i >= 0)
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (localFragment != null) {
        localFragment.performMultiWindowModeChanged(paramBoolean);
      }
      i -= 1;
    }
  }
  
  void dispatchOnFragmentActivityCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentActivityCreated(paramFragment, paramBundle, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentActivityCreated(this, paramFragment, paramBundle);
      }
    }
  }
  
  void dispatchOnFragmentAttached(Fragment paramFragment, Context paramContext, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentAttached(paramFragment, paramContext, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentAttached(this, paramFragment, paramContext);
      }
    }
  }
  
  void dispatchOnFragmentCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentCreated(paramFragment, paramBundle, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentCreated(this, paramFragment, paramBundle);
      }
    }
  }
  
  void dispatchOnFragmentDestroyed(Fragment paramFragment, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentDestroyed(paramFragment, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentDestroyed(this, paramFragment);
      }
    }
  }
  
  void dispatchOnFragmentDetached(Fragment paramFragment, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentDetached(paramFragment, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentDetached(this, paramFragment);
      }
    }
  }
  
  void dispatchOnFragmentPaused(Fragment paramFragment, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentPaused(paramFragment, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentPaused(this, paramFragment);
      }
    }
  }
  
  void dispatchOnFragmentPreAttached(Fragment paramFragment, Context paramContext, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentPreAttached(paramFragment, paramContext, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentPreAttached(this, paramFragment, paramContext);
      }
    }
  }
  
  void dispatchOnFragmentPreCreated(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentPreCreated(paramFragment, paramBundle, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentPreCreated(this, paramFragment, paramBundle);
      }
    }
  }
  
  void dispatchOnFragmentResumed(Fragment paramFragment, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentResumed(paramFragment, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentResumed(this, paramFragment);
      }
    }
  }
  
  void dispatchOnFragmentSaveInstanceState(Fragment paramFragment, Bundle paramBundle, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentSaveInstanceState(paramFragment, paramBundle, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentSaveInstanceState(this, paramFragment, paramBundle);
      }
    }
  }
  
  void dispatchOnFragmentStarted(Fragment paramFragment, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentStarted(paramFragment, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentStarted(this, paramFragment);
      }
    }
  }
  
  void dispatchOnFragmentStopped(Fragment paramFragment, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentStopped(paramFragment, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentStopped(this, paramFragment);
      }
    }
  }
  
  void dispatchOnFragmentViewCreated(Fragment paramFragment, View paramView, Bundle paramBundle, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentViewCreated(paramFragment, paramView, paramBundle, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentViewCreated(this, paramFragment, paramView, paramBundle);
      }
    }
  }
  
  void dispatchOnFragmentViewDestroyed(Fragment paramFragment, boolean paramBoolean)
  {
    if (mParent != null)
    {
      localObject = mParent.getFragmentManager();
      if ((localObject instanceof FragmentManagerImpl)) {
        ((FragmentManagerImpl)localObject).dispatchOnFragmentViewDestroyed(paramFragment, true);
      }
    }
    Object localObject = mLifecycleCallbacks.iterator();
    while (((Iterator)localObject).hasNext())
    {
      Pair localPair = (Pair)((Iterator)localObject).next();
      if ((!paramBoolean) || (((Boolean)second).booleanValue())) {
        ((FragmentManager.FragmentLifecycleCallbacks)first).onFragmentViewDestroyed(this, paramFragment);
      }
    }
  }
  
  public boolean dispatchOptionsItemSelected(MenuItem paramMenuItem)
  {
    if (mCurState < 1) {
      return false;
    }
    int i = 0;
    while (i < mAdded.size())
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if ((localFragment != null) && (localFragment.performOptionsItemSelected(paramMenuItem))) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public void dispatchOptionsMenuClosed(Menu paramMenu)
  {
    if (mCurState < 1) {
      return;
    }
    int i = 0;
    while (i < mAdded.size())
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (localFragment != null) {
        localFragment.performOptionsMenuClosed(paramMenu);
      }
      i += 1;
    }
  }
  
  public void dispatchPause()
  {
    dispatchStateChange(4);
  }
  
  public void dispatchPictureInPictureModeChanged(boolean paramBoolean)
  {
    int i = mAdded.size() - 1;
    while (i >= 0)
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (localFragment != null) {
        localFragment.performPictureInPictureModeChanged(paramBoolean);
      }
      i -= 1;
    }
  }
  
  public boolean dispatchPrepareOptionsMenu(Menu paramMenu)
  {
    int j = mCurState;
    int i = 0;
    if (j < 1) {
      return false;
    }
    boolean bool2;
    for (boolean bool1 = false; i < mAdded.size(); bool1 = bool2)
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      bool2 = bool1;
      if (localFragment != null)
      {
        bool2 = bool1;
        if (localFragment.performPrepareOptionsMenu(paramMenu)) {
          bool2 = true;
        }
      }
      i += 1;
    }
    return bool1;
  }
  
  public void dispatchReallyStop()
  {
    dispatchStateChange(2);
  }
  
  public void dispatchResume()
  {
    mStateSaved = false;
    dispatchStateChange(5);
  }
  
  public void dispatchStart()
  {
    mStateSaved = false;
    dispatchStateChange(4);
  }
  
  public void dispatchStop()
  {
    mStateSaved = true;
    dispatchStateChange(3);
  }
  
  void doPendingDeferredStart()
  {
    if (mHavePendingDeferredStart)
    {
      int i = 0;
      int m;
      for (int j = i; i < mActive.size(); j = m)
      {
        Fragment localFragment = (Fragment)mActive.valueAt(i);
        int k = j;
        if (localFragment != null)
        {
          k = j;
          if (mLoaderManager != null) {
            m = j | mLoaderManager.hasRunningLoaders();
          }
        }
        i += 1;
      }
      if (j == 0)
      {
        mHavePendingDeferredStart = false;
        startPendingDeferredFragments();
      }
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append(paramString);
    ((StringBuilder)localObject1).append("    ");
    localObject1 = ((StringBuilder)localObject1).toString();
    Object localObject2 = mActive;
    int j = 0;
    int i;
    if (localObject2 != null)
    {
      k = mActive.size();
      if (k > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("Active Fragments in ");
        paramPrintWriter.print(Integer.toHexString(System.identityHashCode(this)));
        paramPrintWriter.println(":");
        i = 0;
        while (i < k)
        {
          localObject2 = (Fragment)mActive.valueAt(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(localObject2);
          if (localObject2 != null) {
            ((Fragment)localObject2).dump((String)localObject1, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
          }
          i += 1;
        }
      }
    }
    int k = mAdded.size();
    if (k > 0)
    {
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("Added Fragments:");
      i = 0;
      while (i < k)
      {
        localObject2 = (Fragment)mAdded.get(i);
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  #");
        paramPrintWriter.print(i);
        paramPrintWriter.print(": ");
        paramPrintWriter.println(((Fragment)localObject2).toString());
        i += 1;
      }
    }
    if (mCreatedMenus != null)
    {
      k = mCreatedMenus.size();
      if (k > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Fragments Created Menus:");
        i = 0;
        while (i < k)
        {
          localObject2 = (Fragment)mCreatedMenus.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((Fragment)localObject2).toString());
          i += 1;
        }
      }
    }
    if (mBackStack != null)
    {
      k = mBackStack.size();
      if (k > 0)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.println("Back Stack:");
        i = 0;
        while (i < k)
        {
          localObject2 = (BackStackRecord)mBackStack.get(i);
          paramPrintWriter.print(paramString);
          paramPrintWriter.print("  #");
          paramPrintWriter.print(i);
          paramPrintWriter.print(": ");
          paramPrintWriter.println(((BackStackRecord)localObject2).toString());
          ((BackStackRecord)localObject2).dump((String)localObject1, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
          i += 1;
        }
      }
    }
    try
    {
      if (mBackStackIndices != null)
      {
        k = mBackStackIndices.size();
        if (k > 0)
        {
          paramPrintWriter.print(paramString);
          paramPrintWriter.println("Back Stack Indices:");
          i = 0;
          while (i < k)
          {
            paramFileDescriptor = (BackStackRecord)mBackStackIndices.get(i);
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("  #");
            paramPrintWriter.print(i);
            paramPrintWriter.print(": ");
            paramPrintWriter.println(paramFileDescriptor);
            i += 1;
          }
        }
      }
      if ((mAvailBackStackIndices != null) && (mAvailBackStackIndices.size() > 0))
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("mAvailBackStackIndices: ");
        paramPrintWriter.println(Arrays.toString(mAvailBackStackIndices.toArray()));
      }
      if (mPendingActions != null)
      {
        k = mPendingActions.size();
        if (k > 0)
        {
          paramPrintWriter.print(paramString);
          paramPrintWriter.println("Pending Actions:");
          i = j;
          while (i < k)
          {
            paramFileDescriptor = (OpGenerator)mPendingActions.get(i);
            paramPrintWriter.print(paramString);
            paramPrintWriter.print("  #");
            paramPrintWriter.print(i);
            paramPrintWriter.print(": ");
            paramPrintWriter.println(paramFileDescriptor);
            i += 1;
          }
        }
      }
      paramPrintWriter.print(paramString);
      paramPrintWriter.println("FragmentManager misc state:");
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mHost=");
      paramPrintWriter.println(mHost);
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mContainer=");
      paramPrintWriter.println(mContainer);
      if (mParent != null)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  mParent=");
        paramPrintWriter.println(mParent);
      }
      paramPrintWriter.print(paramString);
      paramPrintWriter.print("  mCurState=");
      paramPrintWriter.print(mCurState);
      paramPrintWriter.print(" mStateSaved=");
      paramPrintWriter.print(mStateSaved);
      paramPrintWriter.print(" mDestroyed=");
      paramPrintWriter.println(mDestroyed);
      if (mNeedMenuInvalidate)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  mNeedMenuInvalidate=");
        paramPrintWriter.println(mNeedMenuInvalidate);
      }
      if (mNoTransactionsBecause != null)
      {
        paramPrintWriter.print(paramString);
        paramPrintWriter.print("  mNoTransactionsBecause=");
        paramPrintWriter.println(mNoTransactionsBecause);
      }
      return;
    }
    finally {}
  }
  
  public void enqueueAction(OpGenerator paramOpGenerator, boolean paramBoolean)
  {
    if (!paramBoolean) {
      checkStateLoss();
    }
    try
    {
      if ((!mDestroyed) && (mHost != null))
      {
        if (mPendingActions == null) {
          mPendingActions = new ArrayList();
        }
        mPendingActions.add(paramOpGenerator);
        scheduleCommit();
        return;
      }
      if (paramBoolean) {
        return;
      }
      throw new IllegalStateException("Activity has been destroyed");
    }
    finally {}
  }
  
  void ensureInflatedFragmentView(Fragment paramFragment)
  {
    if ((mFromLayout) && (!mPerformedCreateView))
    {
      mView = paramFragment.performCreateView(paramFragment.performGetLayoutInflater(mSavedFragmentState), null, mSavedFragmentState);
      if (mView != null)
      {
        mInnerView = mView;
        mView.setSaveFromParentEnabled(false);
        if (mHidden) {
          mView.setVisibility(8);
        }
        paramFragment.onViewCreated(mView, mSavedFragmentState);
        dispatchOnFragmentViewCreated(paramFragment, mView, mSavedFragmentState, false);
        return;
      }
      mInnerView = null;
    }
  }
  
  public boolean execPendingActions()
  {
    ensureExecReady(true);
    boolean bool = false;
    for (;;)
    {
      if (generateOpsForPendingActions(mTmpRecords, mTmpIsPop)) {
        mExecutingActions = true;
      }
      try
      {
        removeRedundantOperationsAndExecute(mTmpRecords, mTmpIsPop);
        cleanupExec();
        bool = true;
      }
      finally
      {
        cleanupExec();
      }
    }
    burpActive();
    return bool;
  }
  
  public void execSingleAction(OpGenerator paramOpGenerator, boolean paramBoolean)
  {
    if ((paramBoolean) && ((mHost == null) || (mDestroyed))) {
      return;
    }
    ensureExecReady(paramBoolean);
    if (paramOpGenerator.generateOps(mTmpRecords, mTmpIsPop)) {
      mExecutingActions = true;
    }
    try
    {
      removeRedundantOperationsAndExecute(mTmpRecords, mTmpIsPop);
      cleanupExec();
    }
    finally
    {
      cleanupExec();
    }
    burpActive();
  }
  
  public boolean executePendingTransactions()
  {
    boolean bool = execPendingActions();
    forcePostponedTransactions();
    return bool;
  }
  
  public Fragment findFragmentById(int paramInt)
  {
    int i = mAdded.size() - 1;
    Fragment localFragment;
    while (i >= 0)
    {
      localFragment = (Fragment)mAdded.get(i);
      if ((localFragment != null) && (mFragmentId == paramInt)) {
        return localFragment;
      }
      i -= 1;
    }
    if (mActive != null)
    {
      i = mActive.size() - 1;
      while (i >= 0)
      {
        localFragment = (Fragment)mActive.valueAt(i);
        if ((localFragment != null) && (mFragmentId == paramInt)) {
          return localFragment;
        }
        i -= 1;
      }
    }
    return null;
  }
  
  public Fragment findFragmentByTag(String paramString)
  {
    int i;
    Fragment localFragment;
    if (paramString != null)
    {
      i = mAdded.size() - 1;
      while (i >= 0)
      {
        localFragment = (Fragment)mAdded.get(i);
        if ((localFragment != null) && (paramString.equals(mTag))) {
          return localFragment;
        }
        i -= 1;
      }
    }
    if ((mActive != null) && (paramString != null))
    {
      i = mActive.size() - 1;
      while (i >= 0)
      {
        localFragment = (Fragment)mActive.valueAt(i);
        if ((localFragment != null) && (paramString.equals(mTag))) {
          return localFragment;
        }
        i -= 1;
      }
    }
    return null;
  }
  
  public Fragment findFragmentByWho(String paramString)
  {
    if ((mActive != null) && (paramString != null))
    {
      int i = mActive.size() - 1;
      while (i >= 0)
      {
        Fragment localFragment = (Fragment)mActive.valueAt(i);
        if (localFragment != null)
        {
          localFragment = localFragment.findFragmentByWho(paramString);
          if (localFragment != null) {
            return localFragment;
          }
        }
        i -= 1;
      }
    }
    return null;
  }
  
  public void freeBackStackIndex(int paramInt)
  {
    try
    {
      mBackStackIndices.set(paramInt, null);
      if (mAvailBackStackIndices == null) {
        mAvailBackStackIndices = new ArrayList();
      }
      if (DEBUG)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Freeing back stack index ");
        localStringBuilder.append(paramInt);
        Log.v("FragmentManager", localStringBuilder.toString());
      }
      mAvailBackStackIndices.add(Integer.valueOf(paramInt));
      return;
    }
    finally {}
  }
  
  int getActiveFragmentCount()
  {
    if (mActive == null) {
      return 0;
    }
    return mActive.size();
  }
  
  List<Fragment> getActiveFragments()
  {
    if (mActive == null) {
      return null;
    }
    int j = mActive.size();
    ArrayList localArrayList = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      localArrayList.add(mActive.valueAt(i));
      i += 1;
    }
    return localArrayList;
  }
  
  public FragmentManager.BackStackEntry getBackStackEntryAt(int paramInt)
  {
    return (FragmentManager.BackStackEntry)mBackStack.get(paramInt);
  }
  
  public int getBackStackEntryCount()
  {
    if (mBackStack != null) {
      return mBackStack.size();
    }
    return 0;
  }
  
  public Fragment getFragment(Bundle paramBundle, String paramString)
  {
    int i = paramBundle.getInt(paramString, -1);
    if (i == -1) {
      return null;
    }
    paramBundle = (Fragment)mActive.get(i);
    if (paramBundle == null)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment no longer exists for key ");
      localStringBuilder.append(paramString);
      localStringBuilder.append(": index ");
      localStringBuilder.append(i);
      throwException(new IllegalStateException(localStringBuilder.toString()));
    }
    return paramBundle;
  }
  
  public List<Fragment> getFragments()
  {
    if (mAdded.isEmpty()) {
      return Collections.EMPTY_LIST;
    }
    synchronized (mAdded)
    {
      List localList = (List)mAdded.clone();
      return localList;
    }
  }
  
  LayoutInflater.Factory2 getLayoutInflaterFactory()
  {
    return this;
  }
  
  public Fragment getPrimaryNavigationFragment()
  {
    return mPrimaryNav;
  }
  
  public void hideFragment(Fragment paramFragment)
  {
    if (DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("hide: ");
      localStringBuilder.append(paramFragment);
      Log.v("FragmentManager", localStringBuilder.toString());
    }
    if (!mHidden)
    {
      mHidden = true;
      mHiddenChanged = (true ^ mHiddenChanged);
    }
  }
  
  public boolean isDestroyed()
  {
    return mDestroyed;
  }
  
  boolean isStateAtLeast(int paramInt)
  {
    return mCurState >= paramInt;
  }
  
  public boolean isStateSaved()
  {
    return mStateSaved;
  }
  
  AnimationOrAnimator loadAnimation(Fragment paramFragment, int paramInt1, boolean paramBoolean, int paramInt2)
  {
    int k = paramFragment.getNextAnim();
    Animation localAnimation = paramFragment.onCreateAnimation(paramInt1, paramBoolean, k);
    if (localAnimation != null) {
      return new AnimationOrAnimator(localAnimation, null);
    }
    paramFragment = paramFragment.onCreateAnimator(paramInt1, paramBoolean, k);
    if (paramFragment != null) {
      return new AnimationOrAnimator(paramFragment, null);
    }
    boolean bool;
    if (k != 0)
    {
      bool = "anim".equals(mHost.getContext().getResources().getResourceTypeName(k));
      j = 0;
      i = j;
      if (!bool) {}
    }
    try
    {
      try
      {
        paramFragment = AnimationUtils.loadAnimation(mHost.getContext(), k);
        if (paramFragment != null)
        {
          paramFragment = new AnimationOrAnimator(paramFragment, null);
          return paramFragment;
        }
        i = 1;
      }
      catch (Resources.NotFoundException paramFragment)
      {
        throw paramFragment;
      }
    }
    catch (RuntimeException paramFragment)
    {
      for (;;)
      {
        i = j;
      }
    }
    if (i == 0) {
      try
      {
        paramFragment = AnimatorInflater.loadAnimator(mHost.getContext(), k);
        if (paramFragment != null)
        {
          paramFragment = new AnimationOrAnimator(paramFragment, null);
          return paramFragment;
        }
      }
      catch (RuntimeException paramFragment)
      {
        if (bool) {
          throw paramFragment;
        }
        paramFragment = AnimationUtils.loadAnimation(mHost.getContext(), k);
        if (paramFragment != null) {
          return new AnimationOrAnimator(paramFragment, null);
        }
      }
    }
    if (paramInt1 == 0) {
      return null;
    }
    paramInt1 = transitToStyleIndex(paramInt1, paramBoolean);
    if (paramInt1 < 0) {
      return null;
    }
    switch (paramInt1)
    {
    default: 
      paramInt1 = paramInt2;
      if (paramInt2 == 0)
      {
        paramInt1 = paramInt2;
        if (mHost.onHasWindowAnimations()) {
          paramInt1 = mHost.onGetWindowAnimations();
        }
      }
      break;
    case 6: 
      return makeFadeAnimation(mHost.getContext(), 1.0F, 0.0F);
    case 5: 
      return makeFadeAnimation(mHost.getContext(), 0.0F, 1.0F);
    case 4: 
      return makeOpenCloseAnimation(mHost.getContext(), 1.0F, 1.075F, 1.0F, 0.0F);
    case 3: 
      return makeOpenCloseAnimation(mHost.getContext(), 0.975F, 1.0F, 0.0F, 1.0F);
    case 2: 
      return makeOpenCloseAnimation(mHost.getContext(), 1.0F, 0.975F, 1.0F, 0.0F);
    case 1: 
      return makeOpenCloseAnimation(mHost.getContext(), 1.125F, 1.0F, 0.0F, 1.0F);
    }
    if (paramInt1 == 0) {
      return null;
    }
    return null;
  }
  
  void makeActive(Fragment paramFragment)
  {
    if (mIndex >= 0) {
      return;
    }
    int i = mNextFragmentIndex;
    mNextFragmentIndex = (i + 1);
    paramFragment.setIndex(i, mParent);
    if (mActive == null) {
      mActive = new SparseArray();
    }
    mActive.put(mIndex, paramFragment);
    if (DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Allocated fragment index ");
      localStringBuilder.append(paramFragment);
      Log.v("FragmentManager", localStringBuilder.toString());
    }
  }
  
  void makeInactive(Fragment paramFragment)
  {
    if (mIndex < 0) {
      return;
    }
    if (DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Freeing fragment index ");
      localStringBuilder.append(paramFragment);
      Log.v("FragmentManager", localStringBuilder.toString());
    }
    mActive.put(mIndex, null);
    paramFragment.initState();
  }
  
  void moveFragmentToExpectedState(Fragment paramFragment)
  {
    if (paramFragment == null) {
      return;
    }
    int j = mCurState;
    int i = j;
    if (mRemoving) {
      if (paramFragment.isInBackStack()) {
        i = Math.min(j, 1);
      } else {
        i = Math.min(j, 0);
      }
    }
    moveToState(paramFragment, i, paramFragment.getNextTransition(), paramFragment.getNextTransitionStyle(), false);
    if (mView != null)
    {
      Object localObject = findFragmentUnder(paramFragment);
      if (localObject != null)
      {
        localObject = mView;
        ViewGroup localViewGroup = mContainer;
        i = localViewGroup.indexOfChild((View)localObject);
        j = localViewGroup.indexOfChild(mView);
        if (j < i)
        {
          localViewGroup.removeViewAt(j);
          localViewGroup.addView(mView, i);
        }
      }
      if ((mIsNewlyAdded) && (mContainer != null))
      {
        if (mPostponedAlpha > 0.0F) {
          mView.setAlpha(mPostponedAlpha);
        }
        mPostponedAlpha = 0.0F;
        mIsNewlyAdded = false;
        localObject = loadAnimation(paramFragment, paramFragment.getNextTransition(), true, paramFragment.getNextTransitionStyle());
        if (localObject != null)
        {
          setHWLayerAnimListenerIfAlpha(mView, (AnimationOrAnimator)localObject);
          if (animation != null)
          {
            mView.startAnimation(animation);
          }
          else
          {
            animator.setTarget(mView);
            animator.start();
          }
        }
      }
    }
    if (mHiddenChanged) {
      completeShowHideFragment(paramFragment);
    }
  }
  
  void moveToState(int paramInt, boolean paramBoolean)
  {
    if ((mHost == null) && (paramInt != 0)) {
      throw new IllegalStateException("No activity");
    }
    if ((!paramBoolean) && (paramInt == mCurState)) {
      return;
    }
    mCurState = paramInt;
    if (mActive != null)
    {
      int k = mAdded.size();
      int i = 0;
      Fragment localFragment;
      for (paramInt = i; i < k; paramInt = j)
      {
        localFragment = (Fragment)mAdded.get(i);
        moveFragmentToExpectedState(localFragment);
        j = paramInt;
        if (mLoaderManager != null) {
          j = paramInt | mLoaderManager.hasRunningLoaders();
        }
        i += 1;
      }
      k = mActive.size();
      int j = 0;
      i = paramInt;
      paramInt = j;
      while (paramInt < k)
      {
        localFragment = (Fragment)mActive.valueAt(paramInt);
        j = i;
        if (localFragment != null) {
          if (!mRemoving)
          {
            j = i;
            if (!mDetached) {}
          }
          else
          {
            j = i;
            if (!mIsNewlyAdded)
            {
              moveFragmentToExpectedState(localFragment);
              j = i;
              if (mLoaderManager != null) {
                j = i | mLoaderManager.hasRunningLoaders();
              }
            }
          }
        }
        paramInt += 1;
        i = j;
      }
      if (i == 0) {
        startPendingDeferredFragments();
      }
      if ((mNeedMenuInvalidate) && (mHost != null) && (mCurState == 5))
      {
        mHost.onSupportInvalidateOptionsMenu();
        mNeedMenuInvalidate = false;
      }
    }
  }
  
  void moveToState(Fragment paramFragment)
  {
    moveToState(paramFragment, mCurState, 0, 0, false);
  }
  
  void moveToState(Fragment paramFragment, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
  {
    boolean bool2 = mAdded;
    int j = 1;
    boolean bool1 = true;
    if ((bool2) && (!mDetached)) {
      break label44;
    }
    int i = paramInt1;
    paramInt1 = i;
    if (i > 1) {
      paramInt1 = 1;
    }
    label44:
    i = paramInt1;
    if (mRemoving)
    {
      i = paramInt1;
      if (paramInt1 > mState) {
        if ((mState == 0) && (paramFragment.isInBackStack())) {
          i = 1;
        } else {
          i = mState;
        }
      }
    }
    if ((mDeferStart) && (mState < 4) && (i > 3)) {
      paramInt1 = 3;
    } else {
      paramInt1 = i;
    }
    ViewGroup localViewGroup;
    if (mState <= paramInt1)
    {
      if ((mFromLayout) && (!mInLayout)) {
        return;
      }
      if ((paramFragment.getAnimatingAway() != null) || (paramFragment.getAnimator() != null))
      {
        paramFragment.setAnimatingAway(null);
        paramFragment.setAnimator(null);
        moveToState(paramFragment, paramFragment.getStateAfterAnimating(), 0, 0, true);
      }
      paramInt3 = paramInt1;
      i = paramInt1;
      j = paramInt1;
      paramInt2 = paramInt1;
      switch (mState)
      {
      default: 
        i = paramInt1;
        break;
      case 0: 
        paramInt3 = paramInt1;
        if (paramInt1 > 0)
        {
          if (DEBUG)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("moveto CREATED: ");
            ((StringBuilder)localObject).append(paramFragment);
            Log.v("FragmentManager", ((StringBuilder)localObject).toString());
          }
          paramInt3 = paramInt1;
          if (mSavedFragmentState != null)
          {
            mSavedFragmentState.setClassLoader(mHost.getContext().getClassLoader());
            mSavedViewState = mSavedFragmentState.getSparseParcelableArray("android:view_state");
            mTarget = getFragment(mSavedFragmentState, "android:target_state");
            if (mTarget != null) {
              mTargetRequestCode = mSavedFragmentState.getInt("android:target_req_state", 0);
            }
            mUserVisibleHint = mSavedFragmentState.getBoolean("android:user_visible_hint", true);
            paramInt3 = paramInt1;
            if (!mUserVisibleHint)
            {
              mDeferStart = true;
              paramInt3 = paramInt1;
              if (paramInt1 > 3) {
                paramInt3 = 3;
              }
            }
          }
          mHost = mHost;
          mParentFragment = mParent;
          if (mParent != null) {
            localObject = mParent.mChildFragmentManager;
          } else {
            localObject = mHost.getFragmentManagerImpl();
          }
          mFragmentManager = ((FragmentManagerImpl)localObject);
          if (mTarget != null)
          {
            if (mActive.get(mTarget.mIndex) != mTarget)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("Fragment ");
              ((StringBuilder)localObject).append(paramFragment);
              ((StringBuilder)localObject).append(" declared target fragment ");
              ((StringBuilder)localObject).append(mTarget);
              ((StringBuilder)localObject).append(" that does not belong to this FragmentManager!");
              throw new IllegalStateException(((StringBuilder)localObject).toString());
            }
            if (mTarget.mState < 1) {
              moveToState(mTarget, 1, 0, 0, true);
            }
          }
          dispatchOnFragmentPreAttached(paramFragment, mHost.getContext(), false);
          mCalled = false;
          paramFragment.onAttach(mHost.getContext());
          if (!mCalled)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("Fragment ");
            ((StringBuilder)localObject).append(paramFragment);
            ((StringBuilder)localObject).append(" did not call through to super.onAttach()");
            throw new SuperNotCalledException(((StringBuilder)localObject).toString());
          }
          if (mParentFragment == null) {
            mHost.onAttachFragment(paramFragment);
          } else {
            mParentFragment.onAttachFragment(paramFragment);
          }
          dispatchOnFragmentAttached(paramFragment, mHost.getContext(), false);
          if (!mIsCreated)
          {
            dispatchOnFragmentPreCreated(paramFragment, mSavedFragmentState, false);
            paramFragment.performCreate(mSavedFragmentState);
            dispatchOnFragmentCreated(paramFragment, mSavedFragmentState, false);
          }
          else
          {
            paramFragment.restoreChildFragmentState(mSavedFragmentState);
            mState = 1;
          }
          mRetaining = false;
        }
      case 1: 
        ensureInflatedFragmentView(paramFragment);
        i = paramInt3;
        if (paramInt3 > 1)
        {
          if (DEBUG)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("moveto ACTIVITY_CREATED: ");
            ((StringBuilder)localObject).append(paramFragment);
            Log.v("FragmentManager", ((StringBuilder)localObject).toString());
          }
          if (!mFromLayout) {
            if (mContainerId != 0)
            {
              if (mContainerId == -1)
              {
                localObject = new StringBuilder();
                ((StringBuilder)localObject).append("Cannot create fragment ");
                ((StringBuilder)localObject).append(paramFragment);
                ((StringBuilder)localObject).append(" for a container view with no id");
                throwException(new IllegalArgumentException(((StringBuilder)localObject).toString()));
              }
              localViewGroup = (ViewGroup)mContainer.onFindViewById(mContainerId);
              localObject = localViewGroup;
              if (localViewGroup != null) {
                break label1019;
              }
              localObject = localViewGroup;
              if (mRestored) {
                break label1019;
              }
            }
          }
        }
        break;
      }
    }
    try
    {
      localObject = paramFragment.getResources().getResourceName(mContainerId);
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      StringBuilder localStringBuilder;
      for (;;) {}
    }
    Object localObject = "unknown";
    localStringBuilder = new StringBuilder();
    localStringBuilder.append("No view found for id 0x");
    localStringBuilder.append(Integer.toHexString(mContainerId));
    localStringBuilder.append(" (");
    localStringBuilder.append((String)localObject);
    localStringBuilder.append(") for fragment ");
    localStringBuilder.append(paramFragment);
    throwException(new IllegalArgumentException(localStringBuilder.toString()));
    localObject = localViewGroup;
    break label1019;
    localObject = null;
    label1019:
    mContainer = ((ViewGroup)localObject);
    mView = paramFragment.performCreateView(paramFragment.performGetLayoutInflater(mSavedFragmentState), (ViewGroup)localObject, mSavedFragmentState);
    if (mView != null)
    {
      mInnerView = mView;
      mView.setSaveFromParentEnabled(false);
      if (localObject != null) {
        ((ViewGroup)localObject).addView(mView);
      }
      if (mHidden) {
        mView.setVisibility(8);
      }
      paramFragment.onViewCreated(mView, mSavedFragmentState);
      dispatchOnFragmentViewCreated(paramFragment, mView, mSavedFragmentState, false);
      if ((mView.getVisibility() == 0) && (mContainer != null)) {
        paramBoolean = bool1;
      } else {
        paramBoolean = false;
      }
      mIsNewlyAdded = paramBoolean;
    }
    else
    {
      mInnerView = null;
    }
    paramFragment.performActivityCreated(mSavedFragmentState);
    dispatchOnFragmentActivityCreated(paramFragment, mSavedFragmentState, false);
    if (mView != null) {
      paramFragment.restoreViewState(mSavedFragmentState);
    }
    mSavedFragmentState = null;
    i = paramInt3;
    j = i;
    if (i > 2)
    {
      mState = 3;
      j = i;
    }
    paramInt2 = j;
    if (j > 3)
    {
      if (DEBUG)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("moveto STARTED: ");
        ((StringBuilder)localObject).append(paramFragment);
        Log.v("FragmentManager", ((StringBuilder)localObject).toString());
      }
      paramFragment.performStart();
      dispatchOnFragmentStarted(paramFragment, false);
      paramInt2 = j;
    }
    i = paramInt2;
    if (paramInt2 > 4)
    {
      if (DEBUG)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("moveto RESUMED: ");
        ((StringBuilder)localObject).append(paramFragment);
        Log.v("FragmentManager", ((StringBuilder)localObject).toString());
      }
      paramFragment.performResume();
      dispatchOnFragmentResumed(paramFragment, false);
      mSavedFragmentState = null;
      mSavedViewState = null;
      i = paramInt2;
      break label2022;
      i = paramInt1;
      if (mState > paramInt1)
      {
        switch (mState)
        {
        default: 
          i = paramInt1;
          break;
        case 5: 
          if (paramInt1 < 5)
          {
            if (DEBUG)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("movefrom RESUMED: ");
              ((StringBuilder)localObject).append(paramFragment);
              Log.v("FragmentManager", ((StringBuilder)localObject).toString());
            }
            paramFragment.performPause();
            dispatchOnFragmentPaused(paramFragment, false);
          }
        case 4: 
          if (paramInt1 < 4)
          {
            if (DEBUG)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("movefrom STARTED: ");
              ((StringBuilder)localObject).append(paramFragment);
              Log.v("FragmentManager", ((StringBuilder)localObject).toString());
            }
            paramFragment.performStop();
            dispatchOnFragmentStopped(paramFragment, false);
          }
        case 3: 
          if (paramInt1 < 3)
          {
            if (DEBUG)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("movefrom STOPPED: ");
              ((StringBuilder)localObject).append(paramFragment);
              Log.v("FragmentManager", ((StringBuilder)localObject).toString());
            }
            paramFragment.performReallyStop();
          }
        case 2: 
          if (paramInt1 < 2)
          {
            if (DEBUG)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("movefrom ACTIVITY_CREATED: ");
              ((StringBuilder)localObject).append(paramFragment);
              Log.v("FragmentManager", ((StringBuilder)localObject).toString());
            }
            if ((mView != null) && (mHost.onShouldSaveFragmentState(paramFragment)) && (mSavedViewState == null)) {
              saveFragmentViewState(paramFragment);
            }
            paramFragment.performDestroyView();
            dispatchOnFragmentViewDestroyed(paramFragment, false);
            if ((mView != null) && (mContainer != null))
            {
              mContainer.endViewTransition(mView);
              mView.clearAnimation();
              if ((mCurState > 0) && (!mDestroyed) && (mView.getVisibility() == 0) && (mPostponedAlpha >= 0.0F)) {
                localObject = loadAnimation(paramFragment, paramInt2, false, paramInt3);
              } else {
                localObject = null;
              }
              mPostponedAlpha = 0.0F;
              if (localObject != null) {
                animateRemoveFragment(paramFragment, (AnimationOrAnimator)localObject, paramInt1);
              }
              mContainer.removeView(mView);
            }
            mContainer = null;
            mView = null;
            mInnerView = null;
            mInLayout = false;
          }
          break;
        }
        i = paramInt1;
        if (paramInt1 < 1)
        {
          if (mDestroyed) {
            if (paramFragment.getAnimatingAway() != null)
            {
              localObject = paramFragment.getAnimatingAway();
              paramFragment.setAnimatingAway(null);
              ((View)localObject).clearAnimation();
            }
            else if (paramFragment.getAnimator() != null)
            {
              localObject = paramFragment.getAnimator();
              paramFragment.setAnimator(null);
              ((Animator)localObject).cancel();
            }
          }
          if ((paramFragment.getAnimatingAway() == null) && (paramFragment.getAnimator() == null))
          {
            if (DEBUG)
            {
              localObject = new StringBuilder();
              ((StringBuilder)localObject).append("movefrom CREATED: ");
              ((StringBuilder)localObject).append(paramFragment);
              Log.v("FragmentManager", ((StringBuilder)localObject).toString());
            }
            if (!mRetaining)
            {
              paramFragment.performDestroy();
              dispatchOnFragmentDestroyed(paramFragment, false);
            }
            else
            {
              mState = 0;
            }
            paramFragment.performDetach();
            dispatchOnFragmentDetached(paramFragment, false);
            i = paramInt1;
            if (!paramBoolean) {
              if (!mRetaining)
              {
                makeInactive(paramFragment);
                i = paramInt1;
              }
              else
              {
                mHost = null;
                mParentFragment = null;
                mFragmentManager = null;
                i = paramInt1;
              }
            }
          }
          else
          {
            paramFragment.setStateAfterAnimating(paramInt1);
            i = j;
          }
        }
      }
    }
    label2022:
    if (mState != i)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("moveToState: Fragment state for ");
      ((StringBuilder)localObject).append(paramFragment);
      ((StringBuilder)localObject).append(" not updated inline; ");
      ((StringBuilder)localObject).append("expected state ");
      ((StringBuilder)localObject).append(i);
      ((StringBuilder)localObject).append(" found ");
      ((StringBuilder)localObject).append(mState);
      Log.w("FragmentManager", ((StringBuilder)localObject).toString());
      mState = i;
    }
  }
  
  public void noteStateNotSaved()
  {
    mSavedNonConfig = null;
    int i = 0;
    mStateSaved = false;
    int j = mAdded.size();
    while (i < j)
    {
      Fragment localFragment = (Fragment)mAdded.get(i);
      if (localFragment != null) {
        localFragment.noteStateNotSaved();
      }
      i += 1;
    }
  }
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    if (!"fragment".equals(paramString)) {
      return null;
    }
    paramString = paramAttributeSet.getAttributeValue(null, "class");
    TypedArray localTypedArray = paramContext.obtainStyledAttributes(paramAttributeSet, FragmentTag.Fragment);
    int i = 0;
    String str1 = paramString;
    if (paramString == null) {
      str1 = localTypedArray.getString(0);
    }
    int k = localTypedArray.getResourceId(1, -1);
    String str2 = localTypedArray.getString(2);
    localTypedArray.recycle();
    if (!Fragment.isSupportFragmentClass(mHost.getContext(), str1)) {
      return null;
    }
    if (paramView != null) {
      i = paramView.getId();
    }
    if ((i == -1) && (k == -1) && (str2 == null))
    {
      paramView = new StringBuilder();
      paramView.append(paramAttributeSet.getPositionDescription());
      paramView.append(": Must specify unique android:id, android:tag, or have a parent with an id for ");
      paramView.append(str1);
      throw new IllegalArgumentException(paramView.toString());
    }
    if (k != -1) {
      paramString = findFragmentById(k);
    } else {
      paramString = null;
    }
    paramView = paramString;
    if (paramString == null)
    {
      paramView = paramString;
      if (str2 != null) {
        paramView = findFragmentByTag(str2);
      }
    }
    paramString = paramView;
    if (paramView == null)
    {
      paramString = paramView;
      if (i != -1) {
        paramString = findFragmentById(i);
      }
    }
    if (DEBUG)
    {
      paramView = new StringBuilder();
      paramView.append("onCreateView: id=0x");
      paramView.append(Integer.toHexString(k));
      paramView.append(" fname=");
      paramView.append(str1);
      paramView.append(" existing=");
      paramView.append(paramString);
      Log.v("FragmentManager", paramView.toString());
    }
    if (paramString == null)
    {
      paramView = mContainer.instantiate(paramContext, str1, null);
      mFromLayout = true;
      int j;
      if (k != 0) {
        j = k;
      } else {
        j = i;
      }
      mFragmentId = j;
      mContainerId = i;
      mTag = str2;
      mInLayout = true;
      mFragmentManager = this;
      mHost = mHost;
      paramView.onInflate(mHost.getContext(), paramAttributeSet, mSavedFragmentState);
      addFragment(paramView, true);
    }
    else
    {
      if (mInLayout)
      {
        paramView = new StringBuilder();
        paramView.append(paramAttributeSet.getPositionDescription());
        paramView.append(": Duplicate id 0x");
        paramView.append(Integer.toHexString(k));
        paramView.append(", tag ");
        paramView.append(str2);
        paramView.append(", or parent id 0x");
        paramView.append(Integer.toHexString(i));
        paramView.append(" with another fragment for ");
        paramView.append(str1);
        throw new IllegalArgumentException(paramView.toString());
      }
      mInLayout = true;
      mHost = mHost;
      if (!mRetaining) {
        paramString.onInflate(mHost.getContext(), paramAttributeSet, mSavedFragmentState);
      }
      paramView = paramString;
    }
    if ((mCurState < 1) && (mFromLayout)) {
      moveToState(paramView, 1, 0, 0, false);
    } else {
      moveToState(paramView);
    }
    if (mView == null)
    {
      paramView = new StringBuilder();
      paramView.append("Fragment ");
      paramView.append(str1);
      paramView.append(" did not create a view.");
      throw new IllegalStateException(paramView.toString());
    }
    if (k != 0) {
      mView.setId(k);
    }
    if (mView.getTag() == null) {
      mView.setTag(str2);
    }
    return mView;
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    return onCreateView(null, paramString, paramContext, paramAttributeSet);
  }
  
  public void performPendingDeferredStart(Fragment paramFragment)
  {
    if (mDeferStart)
    {
      if (mExecutingActions)
      {
        mHavePendingDeferredStart = true;
        return;
      }
      mDeferStart = false;
      moveToState(paramFragment, mCurState, 0, 0, false);
    }
  }
  
  public void popBackStack()
  {
    enqueueAction(new PopBackStackState(null, -1, 0), false);
  }
  
  public void popBackStack(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Bad id: ");
      localStringBuilder.append(paramInt1);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    enqueueAction(new PopBackStackState(null, paramInt1, paramInt2), false);
  }
  
  public void popBackStack(String paramString, int paramInt)
  {
    enqueueAction(new PopBackStackState(paramString, -1, paramInt), false);
  }
  
  public boolean popBackStackImmediate()
  {
    checkStateLoss();
    return popBackStackImmediate(null, -1, 0);
  }
  
  public boolean popBackStackImmediate(int paramInt1, int paramInt2)
  {
    checkStateLoss();
    execPendingActions();
    if (paramInt1 < 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Bad id: ");
      localStringBuilder.append(paramInt1);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    return popBackStackImmediate(null, paramInt1, paramInt2);
  }
  
  public boolean popBackStackImmediate(String paramString, int paramInt)
  {
    checkStateLoss();
    return popBackStackImmediate(paramString, -1, paramInt);
  }
  
  boolean popBackStackState(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, String paramString, int paramInt1, int paramInt2)
  {
    if (mBackStack == null) {
      return false;
    }
    if ((paramString == null) && (paramInt1 < 0) && ((paramInt2 & 0x1) == 0))
    {
      paramInt1 = mBackStack.size() - 1;
      if (paramInt1 < 0) {
        return false;
      }
      paramArrayList.add(mBackStack.remove(paramInt1));
      paramArrayList1.add(Boolean.valueOf(true));
      return true;
    }
    int i;
    if ((paramString == null) && (paramInt1 < 0))
    {
      i = -1;
    }
    else
    {
      int j = mBackStack.size() - 1;
      BackStackRecord localBackStackRecord;
      while (j >= 0)
      {
        localBackStackRecord = (BackStackRecord)mBackStack.get(j);
        if (((paramString != null) && (paramString.equals(localBackStackRecord.getName()))) || ((paramInt1 >= 0) && (paramInt1 == mIndex))) {
          break;
        }
        j -= 1;
      }
      if (j < 0) {
        return false;
      }
      i = j;
      if ((paramInt2 & 0x1) != 0)
      {
        paramInt2 = j - 1;
        for (;;)
        {
          i = paramInt2;
          if (paramInt2 < 0) {
            break;
          }
          localBackStackRecord = (BackStackRecord)mBackStack.get(paramInt2);
          if ((paramString == null) || (!paramString.equals(localBackStackRecord.getName())))
          {
            i = paramInt2;
            if (paramInt1 < 0) {
              break;
            }
            i = paramInt2;
            if (paramInt1 != mIndex) {
              break;
            }
          }
          paramInt2 -= 1;
        }
      }
    }
    if (i == mBackStack.size() - 1) {
      return false;
    }
    paramInt1 = mBackStack.size() - 1;
    while (paramInt1 > i)
    {
      paramArrayList.add(mBackStack.remove(paramInt1));
      paramArrayList1.add(Boolean.valueOf(true));
      paramInt1 -= 1;
    }
    return true;
  }
  
  public void putFragment(Bundle paramBundle, String paramString, Fragment paramFragment)
  {
    if (mIndex < 0)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(paramFragment);
      localStringBuilder.append(" is not currently in the FragmentManager");
      throwException(new IllegalStateException(localStringBuilder.toString()));
    }
    paramBundle.putInt(paramString, mIndex);
  }
  
  public void registerFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks, boolean paramBoolean)
  {
    mLifecycleCallbacks.add(new Pair(paramFragmentLifecycleCallbacks, Boolean.valueOf(paramBoolean)));
  }
  
  public void removeFragment(Fragment paramFragment)
  {
    if (DEBUG)
    {
      ??? = new StringBuilder();
      ((StringBuilder)???).append("remove: ");
      ((StringBuilder)???).append(paramFragment);
      ((StringBuilder)???).append(" nesting=");
      ((StringBuilder)???).append(mBackStackNesting);
      Log.v("FragmentManager", ((StringBuilder)???).toString());
    }
    boolean bool = paramFragment.isInBackStack();
    if ((!mDetached) || ((bool ^ true))) {}
    synchronized (mAdded)
    {
      mAdded.remove(paramFragment);
      if ((mHasMenu) && (mMenuVisible)) {
        mNeedMenuInvalidate = true;
      }
      mAdded = false;
      mRemoving = true;
      return;
    }
  }
  
  public void removeOnBackStackChangedListener(FragmentManager.OnBackStackChangedListener paramOnBackStackChangedListener)
  {
    if (mBackStackChangeListeners != null) {
      mBackStackChangeListeners.remove(paramOnBackStackChangedListener);
    }
  }
  
  void reportBackStackChanged()
  {
    if (mBackStackChangeListeners != null)
    {
      int i = 0;
      while (i < mBackStackChangeListeners.size())
      {
        ((FragmentManager.OnBackStackChangedListener)mBackStackChangeListeners.get(i)).onBackStackChanged();
        i += 1;
      }
    }
  }
  
  void restoreAllState(Parcelable paramParcelable, FragmentManagerNonConfig arg2)
  {
    if (paramParcelable == null) {
      return;
    }
    FragmentManagerState localFragmentManagerState = (FragmentManagerState)paramParcelable;
    if (mActive == null) {
      return;
    }
    Object localObject4;
    Object localObject2;
    Object localObject3;
    int j;
    if (??? != null)
    {
      localObject4 = ???.getFragments();
      localObject2 = ???.getChildNonConfigs();
      localObject3 = ???.getViewModelStores();
      if (localObject4 != null) {
        i = ((List)localObject4).size();
      } else {
        i = 0;
      }
      j = 0;
      for (;;)
      {
        localObject1 = localObject2;
        paramParcelable = (Parcelable)localObject3;
        if (j >= i) {
          break;
        }
        paramParcelable = (Fragment)((List)localObject4).get(j);
        if (DEBUG)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("restoreAllState: re-attaching retained ");
          ((StringBuilder)localObject1).append(paramParcelable);
          Log.v("FragmentManager", ((StringBuilder)localObject1).toString());
        }
        int k = 0;
        while ((k < mActive.length) && (mActive[k].mIndex != mIndex)) {
          k += 1;
        }
        if (k == mActive.length)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Could not find active fragment with index ");
          ((StringBuilder)localObject1).append(mIndex);
          throwException(new IllegalStateException(((StringBuilder)localObject1).toString()));
        }
        localObject1 = mActive[k];
        mInstance = paramParcelable;
        mSavedViewState = null;
        mBackStackNesting = 0;
        mInLayout = false;
        mAdded = false;
        mTarget = null;
        if (mSavedFragmentState != null)
        {
          mSavedFragmentState.setClassLoader(mHost.getContext().getClassLoader());
          mSavedViewState = mSavedFragmentState.getSparseParcelableArray("android:view_state");
          mSavedFragmentState = mSavedFragmentState;
        }
        j += 1;
      }
    }
    Object localObject1 = null;
    paramParcelable = (Parcelable)localObject1;
    mActive = new SparseArray(mActive.length);
    int i = 0;
    while (i < mActive.length)
    {
      localObject4 = mActive[i];
      if (localObject4 != null)
      {
        if ((localObject1 != null) && (i < ((List)localObject1).size())) {
          localObject2 = (FragmentManagerNonConfig)((List)localObject1).get(i);
        } else {
          localObject2 = null;
        }
        if ((paramParcelable != null) && (i < paramParcelable.size())) {
          localObject3 = (ViewModelStore)paramParcelable.get(i);
        } else {
          localObject3 = null;
        }
        localObject2 = ((FragmentState)localObject4).instantiate(mHost, mContainer, mParent, (FragmentManagerNonConfig)localObject2, (ViewModelStore)localObject3);
        if (DEBUG)
        {
          localObject3 = new StringBuilder();
          ((StringBuilder)localObject3).append("restoreAllState: active #");
          ((StringBuilder)localObject3).append(i);
          ((StringBuilder)localObject3).append(": ");
          ((StringBuilder)localObject3).append(localObject2);
          Log.v("FragmentManager", ((StringBuilder)localObject3).toString());
        }
        mActive.put(mIndex, localObject2);
        mInstance = null;
      }
      i += 1;
    }
    if (??? != null)
    {
      paramParcelable = ???.getFragments();
      if (paramParcelable != null) {
        i = paramParcelable.size();
      } else {
        i = 0;
      }
      j = 0;
      while (j < i)
      {
        ??? = (Fragment)paramParcelable.get(j);
        if (mTargetIndex >= 0)
        {
          mTarget = ((Fragment)mActive.get(mTargetIndex));
          if (mTarget == null)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Re-attaching retained fragment ");
            ((StringBuilder)localObject1).append(???);
            ((StringBuilder)localObject1).append(" target no longer exists: ");
            ((StringBuilder)localObject1).append(mTargetIndex);
            Log.w("FragmentManager", ((StringBuilder)localObject1).toString());
          }
        }
        j += 1;
      }
    }
    mAdded.clear();
    if (mAdded != null)
    {
      i = 0;
      while (i < mAdded.length)
      {
        paramParcelable = (Fragment)mActive.get(mAdded[i]);
        if (paramParcelable == null)
        {
          ??? = new StringBuilder();
          ???.append("No instantiated fragment for index #");
          ???.append(mAdded[i]);
          throwException(new IllegalStateException(???.toString()));
        }
        mAdded = true;
        if (DEBUG)
        {
          ??? = new StringBuilder();
          ???.append("restoreAllState: added #");
          ???.append(i);
          ???.append(": ");
          ???.append(paramParcelable);
          Log.v("FragmentManager", ???.toString());
        }
        if (mAdded.contains(paramParcelable)) {
          throw new IllegalStateException("Already added!");
        }
        synchronized (mAdded)
        {
          mAdded.add(paramParcelable);
          i += 1;
        }
      }
    }
    if (mBackStack != null)
    {
      mBackStack = new ArrayList(mBackStack.length);
      i = 0;
      while (i < mBackStack.length)
      {
        paramParcelable = mBackStack[i].instantiate(this);
        if (DEBUG)
        {
          ??? = new StringBuilder();
          ???.append("restoreAllState: back stack #");
          ???.append(i);
          ???.append(" (index ");
          ???.append(mIndex);
          ???.append("): ");
          ???.append(paramParcelable);
          Log.v("FragmentManager", ???.toString());
          ??? = new PrintWriter(new LogWriter("FragmentManager"));
          paramParcelable.dump("  ", ???, false);
          ???.close();
        }
        mBackStack.add(paramParcelable);
        if (mIndex >= 0) {
          setBackStackIndex(mIndex, paramParcelable);
        }
        i += 1;
      }
    }
    mBackStack = null;
    if (mPrimaryNavActiveIndex >= 0) {
      mPrimaryNav = ((Fragment)mActive.get(mPrimaryNavActiveIndex));
    }
    mNextFragmentIndex = mNextFragmentIndex;
  }
  
  FragmentManagerNonConfig retainNonConfig()
  {
    setRetaining(mSavedNonConfig);
    return mSavedNonConfig;
  }
  
  Parcelable saveAllState()
  {
    forcePostponedTransactions();
    endAnimatingAwayFragments();
    execPendingActions();
    mStateSaved = true;
    Object localObject3 = null;
    mSavedNonConfig = null;
    if (mActive != null)
    {
      if (mActive.size() <= 0) {
        return null;
      }
      int m = mActive.size();
      FragmentState[] arrayOfFragmentState = new FragmentState[m];
      int k = 0;
      int i = 0;
      int j = i;
      while (i < m)
      {
        localObject1 = (Fragment)mActive.valueAt(i);
        if (localObject1 != null)
        {
          if (mIndex < 0)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Failure saving state: active ");
            ((StringBuilder)localObject2).append(localObject1);
            ((StringBuilder)localObject2).append(" has cleared index: ");
            ((StringBuilder)localObject2).append(mIndex);
            throwException(new IllegalStateException(((StringBuilder)localObject2).toString()));
          }
          localObject2 = new FragmentState((Fragment)localObject1);
          arrayOfFragmentState[i] = localObject2;
          StringBuilder localStringBuilder;
          if ((mState > 0) && (mSavedFragmentState == null))
          {
            mSavedFragmentState = saveFragmentBasicState((Fragment)localObject1);
            if (mTarget != null)
            {
              if (mTarget.mIndex < 0)
              {
                localStringBuilder = new StringBuilder();
                localStringBuilder.append("Failure saving state: ");
                localStringBuilder.append(localObject1);
                localStringBuilder.append(" has target not in fragment manager: ");
                localStringBuilder.append(mTarget);
                throwException(new IllegalStateException(localStringBuilder.toString()));
              }
              if (mSavedFragmentState == null) {
                mSavedFragmentState = new Bundle();
              }
              putFragment(mSavedFragmentState, "android:target_state", mTarget);
              if (mTargetRequestCode != 0) {
                mSavedFragmentState.putInt("android:target_req_state", mTargetRequestCode);
              }
            }
          }
          else
          {
            mSavedFragmentState = mSavedFragmentState;
          }
          if (DEBUG)
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("Saved state of ");
            localStringBuilder.append(localObject1);
            localStringBuilder.append(": ");
            localStringBuilder.append(mSavedFragmentState);
            Log.v("FragmentManager", localStringBuilder.toString());
          }
          j = 1;
        }
        i += 1;
      }
      if (j == 0)
      {
        if (DEBUG) {
          Log.v("FragmentManager", "saveAllState: no fragments!");
        }
        return null;
      }
      j = mAdded.size();
      if (j > 0)
      {
        localObject2 = new int[j];
        i = 0;
        for (;;)
        {
          localObject1 = localObject2;
          if (i >= j) {
            break;
          }
          localObject2[i] = mAdded.get(i)).mIndex;
          if (localObject2[i] < 0)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("Failure saving state: active ");
            ((StringBuilder)localObject1).append(mAdded.get(i));
            ((StringBuilder)localObject1).append(" has cleared index: ");
            ((StringBuilder)localObject1).append(localObject2[i]);
            throwException(new IllegalStateException(((StringBuilder)localObject1).toString()));
          }
          if (DEBUG)
          {
            localObject1 = new StringBuilder();
            ((StringBuilder)localObject1).append("saveAllState: adding fragment #");
            ((StringBuilder)localObject1).append(i);
            ((StringBuilder)localObject1).append(": ");
            ((StringBuilder)localObject1).append(mAdded.get(i));
            Log.v("FragmentManager", ((StringBuilder)localObject1).toString());
          }
          i += 1;
        }
      }
      Object localObject1 = null;
      Object localObject2 = localObject3;
      if (mBackStack != null)
      {
        j = mBackStack.size();
        localObject2 = localObject3;
        if (j > 0)
        {
          localObject3 = new BackStackState[j];
          i = k;
          for (;;)
          {
            localObject2 = localObject3;
            if (i >= j) {
              break;
            }
            localObject3[i] = new BackStackState((BackStackRecord)mBackStack.get(i));
            if (DEBUG)
            {
              localObject2 = new StringBuilder();
              ((StringBuilder)localObject2).append("saveAllState: adding back stack #");
              ((StringBuilder)localObject2).append(i);
              ((StringBuilder)localObject2).append(": ");
              ((StringBuilder)localObject2).append(mBackStack.get(i));
              Log.v("FragmentManager", ((StringBuilder)localObject2).toString());
            }
            i += 1;
          }
        }
      }
      localObject3 = new FragmentManagerState();
      mActive = arrayOfFragmentState;
      mAdded = ((int[])localObject1);
      mBackStack = ((BackStackState[])localObject2);
      if (mPrimaryNav != null) {
        mPrimaryNavActiveIndex = mPrimaryNav.mIndex;
      }
      mNextFragmentIndex = mNextFragmentIndex;
      saveNonConfig();
      return localObject3;
    }
    return null;
  }
  
  Bundle saveFragmentBasicState(Fragment paramFragment)
  {
    if (mStateBundle == null) {
      mStateBundle = new Bundle();
    }
    paramFragment.performSaveInstanceState(mStateBundle);
    dispatchOnFragmentSaveInstanceState(paramFragment, mStateBundle, false);
    if (!mStateBundle.isEmpty())
    {
      localObject2 = mStateBundle;
      mStateBundle = null;
    }
    else
    {
      localObject2 = null;
    }
    if (mView != null) {
      saveFragmentViewState(paramFragment);
    }
    Object localObject1 = localObject2;
    if (mSavedViewState != null)
    {
      localObject1 = localObject2;
      if (localObject2 == null) {
        localObject1 = new Bundle();
      }
      ((Bundle)localObject1).putSparseParcelableArray("android:view_state", mSavedViewState);
    }
    Object localObject2 = localObject1;
    if (!mUserVisibleHint)
    {
      localObject2 = localObject1;
      if (localObject1 == null) {
        localObject2 = new Bundle();
      }
      ((Bundle)localObject2).putBoolean("android:user_visible_hint", mUserVisibleHint);
    }
    return localObject2;
  }
  
  public Fragment.SavedState saveFragmentInstanceState(Fragment paramFragment)
  {
    if (mIndex < 0)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(paramFragment);
      localStringBuilder.append(" is not currently in the FragmentManager");
      throwException(new IllegalStateException(localStringBuilder.toString()));
    }
    int i = mState;
    StringBuilder localStringBuilder = null;
    if (i > 0)
    {
      Bundle localBundle = saveFragmentBasicState(paramFragment);
      paramFragment = localStringBuilder;
      if (localBundle != null) {
        paramFragment = new Fragment.SavedState(localBundle);
      }
      return paramFragment;
    }
    return null;
  }
  
  void saveFragmentViewState(Fragment paramFragment)
  {
    if (mInnerView == null) {
      return;
    }
    if (mStateArray == null) {
      mStateArray = new SparseArray();
    } else {
      mStateArray.clear();
    }
    mInnerView.saveHierarchyState(mStateArray);
    if (mStateArray.size() > 0)
    {
      mSavedViewState = mStateArray;
      mStateArray = null;
    }
  }
  
  void saveNonConfig()
  {
    if (mActive != null)
    {
      int i = 0;
      localObject1 = null;
      Object localObject3 = localObject1;
      Object localObject7;
      for (Object localObject2 = localObject3;; localObject2 = localObject7)
      {
        localObject6 = localObject1;
        localObject5 = localObject3;
        localObject4 = localObject2;
        if (i >= mActive.size()) {
          break;
        }
        Fragment localFragment = (Fragment)mActive.valueAt(i);
        localObject5 = localObject1;
        localObject6 = localObject3;
        localObject7 = localObject2;
        if (localFragment != null)
        {
          localObject4 = localObject1;
          int j;
          if (mRetainInstance)
          {
            localObject5 = localObject1;
            if (localObject1 == null) {
              localObject5 = new ArrayList();
            }
            ((ArrayList)localObject5).add(localFragment);
            if (mTarget != null) {
              j = mTarget.mIndex;
            } else {
              j = -1;
            }
            mTargetIndex = j;
            localObject4 = localObject5;
            if (DEBUG)
            {
              localObject1 = new StringBuilder();
              ((StringBuilder)localObject1).append("retainNonConfig: keeping retained ");
              ((StringBuilder)localObject1).append(localFragment);
              Log.v("FragmentManager", ((StringBuilder)localObject1).toString());
              localObject4 = localObject5;
            }
          }
          if (mChildFragmentManager != null)
          {
            mChildFragmentManager.saveNonConfig();
            localObject5 = mChildFragmentManager.mSavedNonConfig;
          }
          else
          {
            localObject5 = mChildNonConfig;
          }
          localObject1 = localObject3;
          if (localObject3 == null)
          {
            localObject1 = localObject3;
            if (localObject5 != null)
            {
              localObject3 = new ArrayList(mActive.size());
              j = 0;
              for (;;)
              {
                localObject1 = localObject3;
                if (j >= i) {
                  break;
                }
                ((ArrayList)localObject3).add(null);
                j += 1;
              }
            }
          }
          if (localObject1 != null) {
            ((ArrayList)localObject1).add(localObject5);
          }
          localObject3 = localObject2;
          if (localObject2 == null)
          {
            localObject3 = localObject2;
            if (mViewModelStore != null)
            {
              localObject2 = new ArrayList(mActive.size());
              j = 0;
              for (;;)
              {
                localObject3 = localObject2;
                if (j >= i) {
                  break;
                }
                ((ArrayList)localObject2).add(null);
                j += 1;
              }
            }
          }
          localObject5 = localObject4;
          localObject6 = localObject1;
          localObject7 = localObject3;
          if (localObject3 != null)
          {
            ((ArrayList)localObject3).add(mViewModelStore);
            localObject7 = localObject3;
            localObject6 = localObject1;
            localObject5 = localObject4;
          }
        }
        i += 1;
        localObject1 = localObject5;
        localObject3 = localObject6;
      }
    }
    Object localObject6 = null;
    Object localObject1 = localObject6;
    Object localObject4 = localObject1;
    Object localObject5 = localObject1;
    if ((localObject6 == null) && (localObject5 == null) && (localObject4 == null))
    {
      mSavedNonConfig = null;
      return;
    }
    mSavedNonConfig = new FragmentManagerNonConfig(localObject6, (List)localObject5, localObject4);
  }
  
  public void setBackStackIndex(int paramInt, BackStackRecord paramBackStackRecord)
  {
    try
    {
      if (mBackStackIndices == null) {
        mBackStackIndices = new ArrayList();
      }
      int j = mBackStackIndices.size();
      int i = j;
      StringBuilder localStringBuilder;
      if (paramInt < j)
      {
        if (DEBUG)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Setting back stack index ");
          localStringBuilder.append(paramInt);
          localStringBuilder.append(" to ");
          localStringBuilder.append(paramBackStackRecord);
          Log.v("FragmentManager", localStringBuilder.toString());
        }
        mBackStackIndices.set(paramInt, paramBackStackRecord);
      }
      else
      {
        while (i < paramInt)
        {
          mBackStackIndices.add(null);
          if (mAvailBackStackIndices == null) {
            mAvailBackStackIndices = new ArrayList();
          }
          if (DEBUG)
          {
            localStringBuilder = new StringBuilder();
            localStringBuilder.append("Adding available back stack index ");
            localStringBuilder.append(i);
            Log.v("FragmentManager", localStringBuilder.toString());
          }
          mAvailBackStackIndices.add(Integer.valueOf(i));
          i += 1;
        }
        if (DEBUG)
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Adding back stack index ");
          localStringBuilder.append(paramInt);
          localStringBuilder.append(" with ");
          localStringBuilder.append(paramBackStackRecord);
          Log.v("FragmentManager", localStringBuilder.toString());
        }
        mBackStackIndices.add(paramBackStackRecord);
      }
      return;
    }
    finally {}
  }
  
  public void setPrimaryNavigationFragment(Fragment paramFragment)
  {
    if ((paramFragment != null) && ((mActive.get(mIndex) != paramFragment) || ((mHost != null) && (paramFragment.getFragmentManager() != this))))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Fragment ");
      localStringBuilder.append(paramFragment);
      localStringBuilder.append(" is not an active fragment of FragmentManager ");
      localStringBuilder.append(this);
      throw new IllegalArgumentException(localStringBuilder.toString());
    }
    mPrimaryNav = paramFragment;
  }
  
  public void showFragment(Fragment paramFragment)
  {
    if (DEBUG)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("show: ");
      localStringBuilder.append(paramFragment);
      Log.v("FragmentManager", localStringBuilder.toString());
    }
    if (mHidden)
    {
      mHidden = false;
      mHiddenChanged ^= true;
    }
  }
  
  void startPendingDeferredFragments()
  {
    if (mActive == null) {
      return;
    }
    int i = 0;
    while (i < mActive.size())
    {
      Fragment localFragment = (Fragment)mActive.valueAt(i);
      if (localFragment != null) {
        performPendingDeferredStart(localFragment);
      }
      i += 1;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder(128);
    localStringBuilder.append("FragmentManager{");
    localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
    localStringBuilder.append(" in ");
    if (mParent != null) {
      DebugUtils.buildShortClassTag(mParent, localStringBuilder);
    } else {
      DebugUtils.buildShortClassTag(mHost, localStringBuilder);
    }
    localStringBuilder.append("}}");
    return localStringBuilder.toString();
  }
  
  public void unregisterFragmentLifecycleCallbacks(FragmentManager.FragmentLifecycleCallbacks paramFragmentLifecycleCallbacks)
  {
    CopyOnWriteArrayList localCopyOnWriteArrayList = mLifecycleCallbacks;
    int i = 0;
    for (;;)
    {
      try
      {
        int j = mLifecycleCallbacks.size();
        if (i < j)
        {
          if (mLifecycleCallbacks.get(i)).first != paramFragmentLifecycleCallbacks) {
            break label64;
          }
          mLifecycleCallbacks.remove(i);
        }
        return;
      }
      finally {}
      label64:
      i += 1;
    }
  }
  
  private static class AnimateOnHWLayerIfNeededListener
    extends FragmentManagerImpl.AnimationListenerWrapper
  {
    View mView;
    
    AnimateOnHWLayerIfNeededListener(View paramView, Animation.AnimationListener paramAnimationListener)
    {
      super(null);
      mView = paramView;
    }
    
    @CallSuper
    public void onAnimationEnd(Animation paramAnimation)
    {
      if ((!ViewCompat.isAttachedToWindow(mView)) && (Build.VERSION.SDK_INT < 24)) {
        mView.setLayerType(0, null);
      } else {
        mView.post(new Runnable()
        {
          public void run()
          {
            mView.setLayerType(0, null);
          }
        });
      }
      super.onAnimationEnd(paramAnimation);
    }
  }
  
  private static class AnimationListenerWrapper
    implements Animation.AnimationListener
  {
    private final Animation.AnimationListener mWrapped;
    
    private AnimationListenerWrapper(Animation.AnimationListener paramAnimationListener)
    {
      mWrapped = paramAnimationListener;
    }
    
    @CallSuper
    public void onAnimationEnd(Animation paramAnimation)
    {
      if (mWrapped != null) {
        mWrapped.onAnimationEnd(paramAnimation);
      }
    }
    
    @CallSuper
    public void onAnimationRepeat(Animation paramAnimation)
    {
      if (mWrapped != null) {
        mWrapped.onAnimationRepeat(paramAnimation);
      }
    }
    
    @CallSuper
    public void onAnimationStart(Animation paramAnimation)
    {
      if (mWrapped != null) {
        mWrapped.onAnimationStart(paramAnimation);
      }
    }
  }
  
  private static class AnimationOrAnimator
  {
    public final Animation animation;
    public final Animator animator;
    
    private AnimationOrAnimator(Animator paramAnimator)
    {
      animation = null;
      animator = paramAnimator;
      if (paramAnimator == null) {
        throw new IllegalStateException("Animator cannot be null");
      }
    }
    
    private AnimationOrAnimator(Animation paramAnimation)
    {
      animation = paramAnimation;
      animator = null;
      if (paramAnimation == null) {
        throw new IllegalStateException("Animation cannot be null");
      }
    }
  }
  
  private static class AnimatorOnHWLayerIfNeededListener
    extends AnimatorListenerAdapter
  {
    View mView;
    
    AnimatorOnHWLayerIfNeededListener(View paramView)
    {
      mView = paramView;
    }
    
    public void onAnimationEnd(Animator paramAnimator)
    {
      mView.setLayerType(0, null);
      paramAnimator.removeListener(this);
    }
    
    public void onAnimationStart(Animator paramAnimator)
    {
      mView.setLayerType(2, null);
    }
  }
  
  private static class EndViewTransitionAnimator
    extends AnimationSet
    implements Runnable
  {
    private final View mChild;
    private boolean mEnded;
    private final ViewGroup mParent;
    private boolean mTransitionEnded;
    
    EndViewTransitionAnimator(@NonNull Animation paramAnimation, @NonNull ViewGroup paramViewGroup, @NonNull View paramView)
    {
      super();
      mParent = paramViewGroup;
      mChild = paramView;
      addAnimation(paramAnimation);
    }
    
    public boolean getTransformation(long paramLong, Transformation paramTransformation)
    {
      if (mEnded) {
        return mTransitionEnded ^ true;
      }
      if (!super.getTransformation(paramLong, paramTransformation))
      {
        mEnded = true;
        mParent.post(this);
      }
      return true;
    }
    
    public boolean getTransformation(long paramLong, Transformation paramTransformation, float paramFloat)
    {
      if (mEnded) {
        return mTransitionEnded ^ true;
      }
      if (!super.getTransformation(paramLong, paramTransformation, paramFloat))
      {
        mEnded = true;
        mParent.post(this);
      }
      return true;
    }
    
    public void run()
    {
      mParent.endViewTransition(mChild);
      mTransitionEnded = true;
    }
  }
  
  static class FragmentTag
  {
    public static final int[] Fragment = { 16842755, 16842960, 16842961 };
    public static final int Fragment_id = 1;
    public static final int Fragment_name = 0;
    public static final int Fragment_tag = 2;
    
    FragmentTag() {}
  }
  
  static abstract interface OpGenerator
  {
    public abstract boolean generateOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1);
  }
  
  private class PopBackStackState
    implements FragmentManagerImpl.OpGenerator
  {
    final int mFlags;
    final int mId;
    final String mName;
    
    PopBackStackState(String paramString, int paramInt1, int paramInt2)
    {
      mName = paramString;
      mId = paramInt1;
      mFlags = paramInt2;
    }
    
    public boolean generateOps(ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1)
    {
      if ((mPrimaryNav != null) && (mId < 0) && (mName == null))
      {
        FragmentManager localFragmentManager = mPrimaryNav.peekChildFragmentManager();
        if ((localFragmentManager != null) && (localFragmentManager.popBackStackImmediate())) {
          return false;
        }
      }
      return popBackStackState(paramArrayList, paramArrayList1, mName, mId, mFlags);
    }
  }
  
  static class StartEnterTransitionListener
    implements Fragment.OnStartEnterTransitionListener
  {
    private final boolean mIsBack;
    private int mNumPostponed;
    private final BackStackRecord mRecord;
    
    StartEnterTransitionListener(BackStackRecord paramBackStackRecord, boolean paramBoolean)
    {
      mIsBack = paramBoolean;
      mRecord = paramBackStackRecord;
    }
    
    public void cancelTransaction()
    {
      mRecord.mManager.completeExecute(mRecord, mIsBack, false, false);
    }
    
    public void completeTransaction()
    {
      int i = mNumPostponed;
      int j = 0;
      if (i > 0) {
        i = 1;
      } else {
        i = 0;
      }
      FragmentManagerImpl localFragmentManagerImpl = mRecord.mManager;
      int k = mAdded.size();
      while (j < k)
      {
        Fragment localFragment = (Fragment)mAdded.get(j);
        localFragment.setOnStartEnterTransitionListener(null);
        if ((i != 0) && (localFragment.isPostponed())) {
          localFragment.startPostponedEnterTransition();
        }
        j += 1;
      }
      mRecord.mManager.completeExecute(mRecord, mIsBack, i ^ 0x1, true);
    }
    
    public boolean isReady()
    {
      return mNumPostponed == 0;
    }
    
    public void onStartEnterTransition()
    {
      mNumPostponed -= 1;
      if (mNumPostponed != 0) {
        return;
      }
      mRecord.mManager.scheduleCommit();
    }
    
    public void startListening()
    {
      mNumPostponed += 1;
    }
  }
}

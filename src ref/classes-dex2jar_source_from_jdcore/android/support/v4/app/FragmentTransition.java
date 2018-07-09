package android.support.v4.app;

import android.graphics.Rect;
import android.os.Build.VERSION;
import android.support.v4.util.ArrayMap;
import android.support.v4.view.ViewCompat;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import java.lang.reflect.Constructor;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

class FragmentTransition
{
  private static final int[] INVERSE_OPS = { 0, 3, 0, 1, 5, 4, 7, 6, 9, 8 };
  private static final FragmentTransitionImpl PLATFORM_IMPL;
  private static final FragmentTransitionImpl SUPPORT_IMPL = resolveSupportImpl();
  
  static
  {
    FragmentTransitionCompat21 localFragmentTransitionCompat21;
    if (Build.VERSION.SDK_INT >= 21) {
      localFragmentTransitionCompat21 = new FragmentTransitionCompat21();
    } else {
      localFragmentTransitionCompat21 = null;
    }
    PLATFORM_IMPL = localFragmentTransitionCompat21;
  }
  
  FragmentTransition() {}
  
  private static void addSharedElementsWithMatchingNames(ArrayList<View> paramArrayList, ArrayMap<String, View> paramArrayMap, Collection<String> paramCollection)
  {
    int i = paramArrayMap.size() - 1;
    while (i >= 0)
    {
      View localView = (View)paramArrayMap.valueAt(i);
      if (paramCollection.contains(ViewCompat.getTransitionName(localView))) {
        paramArrayList.add(localView);
      }
      i -= 1;
    }
  }
  
  private static void addToFirstInLastOut(BackStackRecord paramBackStackRecord, BackStackRecord.Op paramOp, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean1, boolean paramBoolean2)
  {
    Fragment localFragment = fragment;
    if (localFragment == null) {
      return;
    }
    int m = mContainerId;
    if (m == 0) {
      return;
    }
    if (paramBoolean1) {
      i = INVERSE_OPS[cmd];
    } else {
      i = cmd;
    }
    boolean bool = false;
    if (i != 1) {
      switch (i)
      {
      default: 
        i = 0;
        j = i;
        k = j;
        break;
      case 5: 
        if (paramBoolean2)
        {
          if ((!mHiddenChanged) || (mHidden) || (!mAdded)) {
            break label322;
          }
          break label316;
        }
        bool = mHidden;
        break;
      case 4: 
        if (paramBoolean2) {
          if ((!mHiddenChanged) || (!mAdded) || (!mHidden)) {
            break label247;
          }
        }
      case 3: 
      case 6: 
        for (;;)
        {
          break;
          if ((!mAdded) || (mHidden)) {
            break label247;
          }
          continue;
          if (!paramBoolean2) {
            break label253;
          }
          if ((mAdded) || (mView == null) || (mView.getVisibility() != 0) || (mPostponedAlpha < 0.0F)) {
            break label247;
          }
        }
        for (;;)
        {
          i = 1;
          break;
          label247:
          label253:
          do
          {
            i = 0;
            break;
          } while ((!mAdded) || (mHidden));
        }
        k = i;
        i = 0;
        j = 1;
        break;
      }
    }
    if (paramBoolean2) {
      bool = mIsNewlyAdded;
    } else if ((!mAdded) && (!mHidden)) {
      label316:
      bool = true;
    } else {
      label322:
      bool = false;
    }
    int j = 0;
    int k = j;
    int i = 1;
    Object localObject = (FragmentContainerTransition)paramSparseArray.get(m);
    paramOp = (BackStackRecord.Op)localObject;
    if (bool)
    {
      paramOp = ensureContainer((FragmentContainerTransition)localObject, paramSparseArray, m);
      lastIn = localFragment;
      lastInIsPop = paramBoolean1;
      lastInTransaction = paramBackStackRecord;
    }
    if ((!paramBoolean2) && (i != 0))
    {
      if ((paramOp != null) && (firstOut == localFragment)) {
        firstOut = null;
      }
      localObject = mManager;
      if ((mState < 1) && (mCurState >= 1) && (!mReorderingAllowed))
      {
        ((FragmentManagerImpl)localObject).makeActive(localFragment);
        ((FragmentManagerImpl)localObject).moveToState(localFragment, 1, 0, 0, false);
      }
    }
    localObject = paramOp;
    if (k != 0) {
      if (paramOp != null)
      {
        localObject = paramOp;
        if (firstOut != null) {}
      }
      else
      {
        localObject = ensureContainer(paramOp, paramSparseArray, m);
        firstOut = localFragment;
        firstOutIsPop = paramBoolean1;
        firstOutTransaction = paramBackStackRecord;
      }
    }
    if ((!paramBoolean2) && (j != 0) && (localObject != null) && (lastIn == localFragment)) {
      lastIn = null;
    }
  }
  
  public static void calculateFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean)
  {
    int j = mOps.size();
    int i = 0;
    while (i < j)
    {
      addToFirstInLastOut(paramBackStackRecord, (BackStackRecord.Op)mOps.get(i), paramSparseArray, false, paramBoolean);
      i += 1;
    }
  }
  
  private static ArrayMap<String, String> calculateNameOverrides(int paramInt1, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt2, int paramInt3)
  {
    ArrayMap localArrayMap = new ArrayMap();
    paramInt3 -= 1;
    while (paramInt3 >= paramInt2)
    {
      Object localObject = (BackStackRecord)paramArrayList.get(paramInt3);
      if (((BackStackRecord)localObject).interactsWith(paramInt1))
      {
        boolean bool = ((Boolean)paramArrayList1.get(paramInt3)).booleanValue();
        if (mSharedElementSourceNames != null)
        {
          int j = mSharedElementSourceNames.size();
          ArrayList localArrayList2;
          ArrayList localArrayList1;
          if (bool)
          {
            localArrayList2 = mSharedElementSourceNames;
            localArrayList1 = mSharedElementTargetNames;
          }
          else
          {
            localArrayList1 = mSharedElementSourceNames;
            localArrayList2 = mSharedElementTargetNames;
          }
          int i = 0;
          while (i < j)
          {
            localObject = (String)localArrayList1.get(i);
            String str1 = (String)localArrayList2.get(i);
            String str2 = (String)localArrayMap.remove(str1);
            if (str2 != null) {
              localArrayMap.put(localObject, str2);
            } else {
              localArrayMap.put(localObject, str1);
            }
            i += 1;
          }
        }
      }
      paramInt3 -= 1;
    }
    return localArrayMap;
  }
  
  public static void calculatePopFragments(BackStackRecord paramBackStackRecord, SparseArray<FragmentContainerTransition> paramSparseArray, boolean paramBoolean)
  {
    if (!mManager.mContainer.onHasView()) {
      return;
    }
    int i = mOps.size() - 1;
    while (i >= 0)
    {
      addToFirstInLastOut(paramBackStackRecord, (BackStackRecord.Op)mOps.get(i), paramSparseArray, true, paramBoolean);
      i -= 1;
    }
  }
  
  private static void callSharedElementStartEnd(Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean1, ArrayMap<String, View> paramArrayMap, boolean paramBoolean2)
  {
    if (paramBoolean1) {
      paramFragment1 = paramFragment2.getEnterTransitionCallback();
    } else {
      paramFragment1 = paramFragment1.getEnterTransitionCallback();
    }
    if (paramFragment1 != null)
    {
      paramFragment2 = new ArrayList();
      ArrayList localArrayList = new ArrayList();
      int j = 0;
      int i;
      if (paramArrayMap == null) {
        i = 0;
      } else {
        i = paramArrayMap.size();
      }
      while (j < i)
      {
        localArrayList.add(paramArrayMap.keyAt(j));
        paramFragment2.add(paramArrayMap.valueAt(j));
        j += 1;
      }
      if (paramBoolean2)
      {
        paramFragment1.onSharedElementStart(localArrayList, paramFragment2, null);
        return;
      }
      paramFragment1.onSharedElementEnd(localArrayList, paramFragment2, null);
    }
  }
  
  private static boolean canHandleAll(FragmentTransitionImpl paramFragmentTransitionImpl, List<Object> paramList)
  {
    int j = paramList.size();
    int i = 0;
    while (i < j)
    {
      if (!paramFragmentTransitionImpl.canHandle(paramList.get(i))) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private static ArrayMap<String, View> captureInSharedElements(FragmentTransitionImpl paramFragmentTransitionImpl, ArrayMap<String, String> paramArrayMap, Object paramObject, FragmentContainerTransition paramFragmentContainerTransition)
  {
    Fragment localFragment = lastIn;
    View localView = localFragment.getView();
    if ((!paramArrayMap.isEmpty()) && (paramObject != null) && (localView != null))
    {
      ArrayMap localArrayMap = new ArrayMap();
      paramFragmentTransitionImpl.findNamedViews(localArrayMap, localView);
      paramFragmentTransitionImpl = lastInTransaction;
      if (lastInIsPop)
      {
        paramObject = localFragment.getExitTransitionCallback();
        paramFragmentTransitionImpl = mSharedElementSourceNames;
      }
      else
      {
        paramObject = localFragment.getEnterTransitionCallback();
        paramFragmentTransitionImpl = mSharedElementTargetNames;
      }
      if (paramFragmentTransitionImpl != null)
      {
        localArrayMap.retainAll(paramFragmentTransitionImpl);
        localArrayMap.retainAll(paramArrayMap.values());
      }
      if (paramObject != null)
      {
        paramObject.onMapSharedElements(paramFragmentTransitionImpl, localArrayMap);
        int i = paramFragmentTransitionImpl.size() - 1;
        while (i >= 0)
        {
          paramFragmentContainerTransition = (String)paramFragmentTransitionImpl.get(i);
          paramObject = (View)localArrayMap.get(paramFragmentContainerTransition);
          if (paramObject == null)
          {
            paramObject = findKeyForValue(paramArrayMap, paramFragmentContainerTransition);
            if (paramObject != null) {
              paramArrayMap.remove(paramObject);
            }
          }
          else if (!paramFragmentContainerTransition.equals(ViewCompat.getTransitionName(paramObject)))
          {
            paramFragmentContainerTransition = findKeyForValue(paramArrayMap, paramFragmentContainerTransition);
            if (paramFragmentContainerTransition != null) {
              paramArrayMap.put(paramFragmentContainerTransition, ViewCompat.getTransitionName(paramObject));
            }
          }
          i -= 1;
        }
      }
      retainValues(paramArrayMap, localArrayMap);
      return localArrayMap;
    }
    paramArrayMap.clear();
    return null;
  }
  
  private static ArrayMap<String, View> captureOutSharedElements(FragmentTransitionImpl paramFragmentTransitionImpl, ArrayMap<String, String> paramArrayMap, Object paramObject, FragmentContainerTransition paramFragmentContainerTransition)
  {
    if ((!paramArrayMap.isEmpty()) && (paramObject != null))
    {
      paramObject = firstOut;
      ArrayMap localArrayMap = new ArrayMap();
      paramFragmentTransitionImpl.findNamedViews(localArrayMap, paramObject.getView());
      paramFragmentTransitionImpl = firstOutTransaction;
      if (firstOutIsPop)
      {
        paramObject = paramObject.getEnterTransitionCallback();
        paramFragmentTransitionImpl = mSharedElementTargetNames;
      }
      else
      {
        paramObject = paramObject.getExitTransitionCallback();
        paramFragmentTransitionImpl = mSharedElementSourceNames;
      }
      localArrayMap.retainAll(paramFragmentTransitionImpl);
      if (paramObject != null)
      {
        paramObject.onMapSharedElements(paramFragmentTransitionImpl, localArrayMap);
        int i = paramFragmentTransitionImpl.size() - 1;
        while (i >= 0)
        {
          paramFragmentContainerTransition = (String)paramFragmentTransitionImpl.get(i);
          paramObject = (View)localArrayMap.get(paramFragmentContainerTransition);
          if (paramObject == null)
          {
            paramArrayMap.remove(paramFragmentContainerTransition);
          }
          else if (!paramFragmentContainerTransition.equals(ViewCompat.getTransitionName(paramObject)))
          {
            paramFragmentContainerTransition = (String)paramArrayMap.remove(paramFragmentContainerTransition);
            paramArrayMap.put(ViewCompat.getTransitionName(paramObject), paramFragmentContainerTransition);
          }
          i -= 1;
        }
      }
      paramArrayMap.retainAll(localArrayMap.keySet());
      return localArrayMap;
    }
    paramArrayMap.clear();
    return null;
  }
  
  private static FragmentTransitionImpl chooseImpl(Fragment paramFragment1, Fragment paramFragment2)
  {
    ArrayList localArrayList = new ArrayList();
    if (paramFragment1 != null)
    {
      Object localObject = paramFragment1.getExitTransition();
      if (localObject != null) {
        localArrayList.add(localObject);
      }
      localObject = paramFragment1.getReturnTransition();
      if (localObject != null) {
        localArrayList.add(localObject);
      }
      paramFragment1 = paramFragment1.getSharedElementReturnTransition();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
    }
    if (paramFragment2 != null)
    {
      paramFragment1 = paramFragment2.getEnterTransition();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
      paramFragment1 = paramFragment2.getReenterTransition();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
      paramFragment1 = paramFragment2.getSharedElementEnterTransition();
      if (paramFragment1 != null) {
        localArrayList.add(paramFragment1);
      }
    }
    if (localArrayList.isEmpty()) {
      return null;
    }
    if ((PLATFORM_IMPL != null) && (canHandleAll(PLATFORM_IMPL, localArrayList))) {
      return PLATFORM_IMPL;
    }
    if ((SUPPORT_IMPL != null) && (canHandleAll(SUPPORT_IMPL, localArrayList))) {
      return SUPPORT_IMPL;
    }
    if ((PLATFORM_IMPL == null) && (SUPPORT_IMPL == null)) {
      return null;
    }
    throw new IllegalArgumentException("Invalid Transition types");
  }
  
  private static ArrayList<View> configureEnteringExitingViews(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject, Fragment paramFragment, ArrayList<View> paramArrayList, View paramView)
  {
    if (paramObject != null)
    {
      ArrayList localArrayList = new ArrayList();
      paramFragment = paramFragment.getView();
      if (paramFragment != null) {
        paramFragmentTransitionImpl.captureTransitioningViews(localArrayList, paramFragment);
      }
      if (paramArrayList != null) {
        localArrayList.removeAll(paramArrayList);
      }
      paramFragment = localArrayList;
      if (!localArrayList.isEmpty())
      {
        localArrayList.add(paramView);
        paramFragmentTransitionImpl.addTargets(paramObject, localArrayList);
        return localArrayList;
      }
    }
    else
    {
      paramFragment = null;
    }
    return paramFragment;
  }
  
  private static Object configureSharedElementsOrdered(FragmentTransitionImpl paramFragmentTransitionImpl, ViewGroup paramViewGroup, final View paramView, final ArrayMap<String, String> paramArrayMap, final FragmentContainerTransition paramFragmentContainerTransition, final ArrayList<View> paramArrayList1, final ArrayList<View> paramArrayList2, final Object paramObject1, final Object paramObject2)
  {
    final Fragment localFragment1 = lastIn;
    final Fragment localFragment2 = firstOut;
    if (localFragment1 != null)
    {
      if (localFragment2 == null) {
        return null;
      }
      final boolean bool = lastInIsPop;
      Object localObject;
      if (paramArrayMap.isEmpty()) {
        localObject = null;
      } else {
        localObject = getSharedElementTransition(paramFragmentTransitionImpl, localFragment1, localFragment2, bool);
      }
      final ArrayMap<String, String> localArrayMap = paramArrayMap;
      ArrayMap localArrayMap1 = captureOutSharedElements(paramFragmentTransitionImpl, localArrayMap, localObject, paramFragmentContainerTransition);
      if (paramArrayMap.isEmpty())
      {
        paramArrayMap = null;
      }
      else
      {
        paramArrayList1.addAll(localArrayMap1.values());
        paramArrayMap = (ArrayMap<String, String>)localObject;
      }
      if ((paramObject1 == null) && (paramObject2 == null) && (paramArrayMap == null)) {
        return null;
      }
      callSharedElementStartEnd(localFragment1, localFragment2, bool, localArrayMap1, true);
      if (paramArrayMap != null)
      {
        localObject = new Rect();
        paramFragmentTransitionImpl.setSharedElementTargets(paramArrayMap, paramView, paramArrayList1);
        setOutEpicenter(paramFragmentTransitionImpl, paramArrayMap, paramObject2, localArrayMap1, firstOutIsPop, firstOutTransaction);
        paramObject2 = localObject;
        if (paramObject1 != null)
        {
          paramFragmentTransitionImpl.setEpicenter(paramObject1, (Rect)localObject);
          paramObject2 = localObject;
        }
      }
      else
      {
        paramObject2 = null;
      }
      OneShotPreDrawListener.add(paramViewGroup, new Runnable()
      {
        public void run()
        {
          Object localObject = FragmentTransition.captureInSharedElements(val$impl, localArrayMap, paramArrayMap, paramFragmentContainerTransition);
          if (localObject != null)
          {
            paramArrayList2.addAll(((ArrayMap)localObject).values());
            paramArrayList2.add(paramView);
          }
          FragmentTransition.callSharedElementStartEnd(localFragment1, localFragment2, bool, (ArrayMap)localObject, false);
          if (paramArrayMap != null)
          {
            val$impl.swapSharedElementTargets(paramArrayMap, paramArrayList1, paramArrayList2);
            localObject = FragmentTransition.getInEpicenterView((ArrayMap)localObject, paramFragmentContainerTransition, paramObject1, bool);
            if (localObject != null) {
              val$impl.getBoundsOnScreen((View)localObject, paramObject2);
            }
          }
        }
      });
      return paramArrayMap;
    }
    return null;
  }
  
  private static Object configureSharedElementsReordered(final FragmentTransitionImpl paramFragmentTransitionImpl, ViewGroup paramViewGroup, View paramView, ArrayMap<String, String> paramArrayMap, final FragmentContainerTransition paramFragmentContainerTransition, final ArrayList<View> paramArrayList1, ArrayList<View> paramArrayList2, Object paramObject1, Object paramObject2)
  {
    Fragment localFragment1 = lastIn;
    final Fragment localFragment2 = firstOut;
    if (localFragment1 != null) {
      localFragment1.getView().setVisibility(0);
    }
    if (localFragment1 != null)
    {
      if (localFragment2 == null) {
        return null;
      }
      final boolean bool = lastInIsPop;
      Object localObject;
      if (paramArrayMap.isEmpty()) {
        localObject = null;
      } else {
        localObject = getSharedElementTransition(paramFragmentTransitionImpl, localFragment1, localFragment2, bool);
      }
      ArrayMap localArrayMap2 = captureOutSharedElements(paramFragmentTransitionImpl, paramArrayMap, localObject, paramFragmentContainerTransition);
      final ArrayMap localArrayMap1 = captureInSharedElements(paramFragmentTransitionImpl, paramArrayMap, localObject, paramFragmentContainerTransition);
      if (paramArrayMap.isEmpty())
      {
        if (localArrayMap2 != null) {
          localArrayMap2.clear();
        }
        if (localArrayMap1 != null) {
          localArrayMap1.clear();
        }
        paramArrayMap = null;
      }
      else
      {
        addSharedElementsWithMatchingNames(paramArrayList1, localArrayMap2, paramArrayMap.keySet());
        addSharedElementsWithMatchingNames(paramArrayList2, localArrayMap1, paramArrayMap.values());
        paramArrayMap = localObject;
      }
      if ((paramObject1 == null) && (paramObject2 == null) && (paramArrayMap == null)) {
        return null;
      }
      callSharedElementStartEnd(localFragment1, localFragment2, bool, localArrayMap2, true);
      if (paramArrayMap != null)
      {
        paramArrayList2.add(paramView);
        paramFragmentTransitionImpl.setSharedElementTargets(paramArrayMap, paramView, paramArrayList1);
        setOutEpicenter(paramFragmentTransitionImpl, paramArrayMap, paramObject2, localArrayMap2, firstOutIsPop, firstOutTransaction);
        paramView = new Rect();
        paramFragmentContainerTransition = getInEpicenterView(localArrayMap1, paramFragmentContainerTransition, paramObject1, bool);
        if (paramFragmentContainerTransition != null) {
          paramFragmentTransitionImpl.setEpicenter(paramObject1, paramView);
        }
        paramArrayList1 = paramView;
      }
      else
      {
        paramView = null;
        paramArrayList1 = paramView;
        paramFragmentContainerTransition = paramView;
      }
      OneShotPreDrawListener.add(paramViewGroup, new Runnable()
      {
        public void run()
        {
          FragmentTransition.callSharedElementStartEnd(val$inFragment, localFragment2, bool, localArrayMap1, false);
          if (paramFragmentContainerTransition != null) {
            paramFragmentTransitionImpl.getBoundsOnScreen(paramFragmentContainerTransition, paramArrayList1);
          }
        }
      });
      return paramArrayMap;
    }
    return null;
  }
  
  private static void configureTransitionsOrdered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap)
  {
    if (mContainer.onHasView()) {
      paramFragmentManagerImpl = (ViewGroup)mContainer.onFindViewById(paramInt);
    } else {
      paramFragmentManagerImpl = null;
    }
    if (paramFragmentManagerImpl == null) {
      return;
    }
    Fragment localFragment = lastIn;
    Object localObject4 = firstOut;
    FragmentTransitionImpl localFragmentTransitionImpl = chooseImpl((Fragment)localObject4, localFragment);
    if (localFragmentTransitionImpl == null) {
      return;
    }
    boolean bool1 = lastInIsPop;
    boolean bool2 = firstOutIsPop;
    Object localObject2 = getEnterTransition(localFragmentTransitionImpl, localFragment, bool1);
    Object localObject1 = getExitTransition(localFragmentTransitionImpl, (Fragment)localObject4, bool2);
    ArrayList localArrayList2 = new ArrayList();
    ArrayList localArrayList1 = new ArrayList();
    Object localObject3 = configureSharedElementsOrdered(localFragmentTransitionImpl, paramFragmentManagerImpl, paramView, paramArrayMap, paramFragmentContainerTransition, localArrayList2, localArrayList1, localObject2, localObject1);
    if ((localObject2 == null) && (localObject3 == null) && (localObject1 == null)) {
      return;
    }
    localObject4 = configureEnteringExitingViews(localFragmentTransitionImpl, localObject1, (Fragment)localObject4, localArrayList2, paramView);
    if ((localObject4 != null) && (!((ArrayList)localObject4).isEmpty())) {
      break label183;
    }
    localObject1 = null;
    label183:
    localFragmentTransitionImpl.addTarget(localObject2, paramView);
    paramFragmentContainerTransition = mergeTransitions(localFragmentTransitionImpl, localObject2, localObject1, localObject3, localFragment, lastInIsPop);
    if (paramFragmentContainerTransition != null)
    {
      localArrayList2 = new ArrayList();
      localFragmentTransitionImpl.scheduleRemoveTargets(paramFragmentContainerTransition, localObject2, localArrayList2, localObject1, (ArrayList)localObject4, localObject3, localArrayList1);
      scheduleTargetChange(localFragmentTransitionImpl, paramFragmentManagerImpl, localFragment, paramView, localArrayList1, localObject2, localArrayList2, localObject1, (ArrayList)localObject4);
      localFragmentTransitionImpl.setNameOverridesOrdered(paramFragmentManagerImpl, localArrayList1, paramArrayMap);
      localFragmentTransitionImpl.beginDelayedTransition(paramFragmentManagerImpl, paramFragmentContainerTransition);
      localFragmentTransitionImpl.scheduleNameReset(paramFragmentManagerImpl, localArrayList1, paramArrayMap);
    }
  }
  
  private static void configureTransitionsReordered(FragmentManagerImpl paramFragmentManagerImpl, int paramInt, FragmentContainerTransition paramFragmentContainerTransition, View paramView, ArrayMap<String, String> paramArrayMap)
  {
    if (mContainer.onHasView()) {
      paramFragmentManagerImpl = (ViewGroup)mContainer.onFindViewById(paramInt);
    } else {
      paramFragmentManagerImpl = null;
    }
    if (paramFragmentManagerImpl == null) {
      return;
    }
    Object localObject5 = lastIn;
    Object localObject3 = firstOut;
    FragmentTransitionImpl localFragmentTransitionImpl = chooseImpl((Fragment)localObject3, (Fragment)localObject5);
    if (localFragmentTransitionImpl == null) {
      return;
    }
    boolean bool1 = lastInIsPop;
    boolean bool2 = firstOutIsPop;
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    Object localObject2 = getEnterTransition(localFragmentTransitionImpl, (Fragment)localObject5, bool1);
    Object localObject1 = getExitTransition(localFragmentTransitionImpl, (Fragment)localObject3, bool2);
    Object localObject4 = configureSharedElementsReordered(localFragmentTransitionImpl, paramFragmentManagerImpl, paramView, paramArrayMap, paramFragmentContainerTransition, localArrayList2, localArrayList1, localObject2, localObject1);
    if ((localObject2 == null) && (localObject4 == null) && (localObject1 == null)) {
      return;
    }
    paramFragmentContainerTransition = (FragmentContainerTransition)localObject1;
    localObject1 = configureEnteringExitingViews(localFragmentTransitionImpl, paramFragmentContainerTransition, (Fragment)localObject3, localArrayList2, paramView);
    paramView = configureEnteringExitingViews(localFragmentTransitionImpl, localObject2, (Fragment)localObject5, localArrayList1, paramView);
    setViewVisibility(paramView, 4);
    localObject5 = mergeTransitions(localFragmentTransitionImpl, localObject2, paramFragmentContainerTransition, localObject4, (Fragment)localObject5, bool1);
    if (localObject5 != null)
    {
      replaceHide(localFragmentTransitionImpl, paramFragmentContainerTransition, (Fragment)localObject3, (ArrayList)localObject1);
      localObject3 = localFragmentTransitionImpl.prepareSetNameOverridesReordered(localArrayList1);
      localFragmentTransitionImpl.scheduleRemoveTargets(localObject5, localObject2, paramView, paramFragmentContainerTransition, (ArrayList)localObject1, localObject4, localArrayList1);
      localFragmentTransitionImpl.beginDelayedTransition(paramFragmentManagerImpl, localObject5);
      localFragmentTransitionImpl.setNameOverridesReordered(paramFragmentManagerImpl, localArrayList2, localArrayList1, (ArrayList)localObject3, paramArrayMap);
      setViewVisibility(paramView, 0);
      localFragmentTransitionImpl.swapSharedElementTargets(localObject4, localArrayList2, localArrayList1);
    }
  }
  
  private static FragmentContainerTransition ensureContainer(FragmentContainerTransition paramFragmentContainerTransition, SparseArray<FragmentContainerTransition> paramSparseArray, int paramInt)
  {
    FragmentContainerTransition localFragmentContainerTransition = paramFragmentContainerTransition;
    if (paramFragmentContainerTransition == null)
    {
      localFragmentContainerTransition = new FragmentContainerTransition();
      paramSparseArray.put(paramInt, localFragmentContainerTransition);
    }
    return localFragmentContainerTransition;
  }
  
  private static String findKeyForValue(ArrayMap<String, String> paramArrayMap, String paramString)
  {
    int j = paramArrayMap.size();
    int i = 0;
    while (i < j)
    {
      if (paramString.equals(paramArrayMap.valueAt(i))) {
        return (String)paramArrayMap.keyAt(i);
      }
      i += 1;
    }
    return null;
  }
  
  private static Object getEnterTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment, boolean paramBoolean)
  {
    if (paramFragment == null) {
      return null;
    }
    if (paramBoolean) {
      paramFragment = paramFragment.getReenterTransition();
    } else {
      paramFragment = paramFragment.getEnterTransition();
    }
    return paramFragmentTransitionImpl.cloneTransition(paramFragment);
  }
  
  private static Object getExitTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment, boolean paramBoolean)
  {
    if (paramFragment == null) {
      return null;
    }
    if (paramBoolean) {
      paramFragment = paramFragment.getReturnTransition();
    } else {
      paramFragment = paramFragment.getExitTransition();
    }
    return paramFragmentTransitionImpl.cloneTransition(paramFragment);
  }
  
  private static View getInEpicenterView(ArrayMap<String, View> paramArrayMap, FragmentContainerTransition paramFragmentContainerTransition, Object paramObject, boolean paramBoolean)
  {
    paramFragmentContainerTransition = lastInTransaction;
    if ((paramObject != null) && (paramArrayMap != null) && (mSharedElementSourceNames != null) && (!mSharedElementSourceNames.isEmpty()))
    {
      if (paramBoolean) {
        paramFragmentContainerTransition = (String)mSharedElementSourceNames.get(0);
      } else {
        paramFragmentContainerTransition = (String)mSharedElementTargetNames.get(0);
      }
      return (View)paramArrayMap.get(paramFragmentContainerTransition);
    }
    return null;
  }
  
  private static Object getSharedElementTransition(FragmentTransitionImpl paramFragmentTransitionImpl, Fragment paramFragment1, Fragment paramFragment2, boolean paramBoolean)
  {
    if ((paramFragment1 != null) && (paramFragment2 != null))
    {
      if (paramBoolean) {
        paramFragment1 = paramFragment2.getSharedElementReturnTransition();
      } else {
        paramFragment1 = paramFragment1.getSharedElementEnterTransition();
      }
      return paramFragmentTransitionImpl.wrapTransitionInSet(paramFragmentTransitionImpl.cloneTransition(paramFragment1));
    }
    return null;
  }
  
  private static Object mergeTransitions(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject1, Object paramObject2, Object paramObject3, Fragment paramFragment, boolean paramBoolean)
  {
    if ((paramObject1 != null) && (paramObject2 != null) && (paramFragment != null))
    {
      if (paramBoolean) {
        paramBoolean = paramFragment.getAllowReturnTransitionOverlap();
      } else {
        paramBoolean = paramFragment.getAllowEnterTransitionOverlap();
      }
    }
    else {
      paramBoolean = true;
    }
    if (paramBoolean) {
      return paramFragmentTransitionImpl.mergeTransitionsTogether(paramObject2, paramObject1, paramObject3);
    }
    return paramFragmentTransitionImpl.mergeTransitionsInSequence(paramObject2, paramObject1, paramObject3);
  }
  
  private static void replaceHide(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject, Fragment paramFragment, ArrayList<View> paramArrayList)
  {
    if ((paramFragment != null) && (paramObject != null) && (mAdded) && (mHidden) && (mHiddenChanged))
    {
      paramFragment.setHideReplaced(true);
      paramFragmentTransitionImpl.scheduleHideFragmentView(paramObject, paramFragment.getView(), paramArrayList);
      OneShotPreDrawListener.add(mContainer, new Runnable()
      {
        public void run()
        {
          FragmentTransition.setViewVisibility(val$exitingViews, 4);
        }
      });
    }
  }
  
  private static FragmentTransitionImpl resolveSupportImpl()
  {
    try
    {
      FragmentTransitionImpl localFragmentTransitionImpl = (FragmentTransitionImpl)Class.forName("android.support.transition.FragmentTransitionSupport").getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
      return localFragmentTransitionImpl;
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    return null;
  }
  
  private static void retainValues(ArrayMap<String, String> paramArrayMap, ArrayMap<String, View> paramArrayMap1)
  {
    int i = paramArrayMap.size() - 1;
    while (i >= 0)
    {
      if (!paramArrayMap1.containsKey((String)paramArrayMap.valueAt(i))) {
        paramArrayMap.removeAt(i);
      }
      i -= 1;
    }
  }
  
  private static void scheduleTargetChange(final FragmentTransitionImpl paramFragmentTransitionImpl, ViewGroup paramViewGroup, final Fragment paramFragment, final View paramView, final ArrayList<View> paramArrayList1, Object paramObject1, final ArrayList<View> paramArrayList2, final Object paramObject2, final ArrayList<View> paramArrayList3)
  {
    OneShotPreDrawListener.add(paramViewGroup, new Runnable()
    {
      public void run()
      {
        ArrayList localArrayList;
        if (val$enterTransition != null)
        {
          paramFragmentTransitionImpl.removeTarget(val$enterTransition, paramView);
          localArrayList = FragmentTransition.configureEnteringExitingViews(paramFragmentTransitionImpl, val$enterTransition, paramFragment, paramArrayList1, paramView);
          paramArrayList2.addAll(localArrayList);
        }
        if (paramArrayList3 != null)
        {
          if (paramObject2 != null)
          {
            localArrayList = new ArrayList();
            localArrayList.add(paramView);
            paramFragmentTransitionImpl.replaceTargets(paramObject2, paramArrayList3, localArrayList);
          }
          paramArrayList3.clear();
          paramArrayList3.add(paramView);
        }
      }
    });
  }
  
  private static void setOutEpicenter(FragmentTransitionImpl paramFragmentTransitionImpl, Object paramObject1, Object paramObject2, ArrayMap<String, View> paramArrayMap, boolean paramBoolean, BackStackRecord paramBackStackRecord)
  {
    if ((mSharedElementSourceNames != null) && (!mSharedElementSourceNames.isEmpty()))
    {
      if (paramBoolean) {
        paramBackStackRecord = (String)mSharedElementTargetNames.get(0);
      } else {
        paramBackStackRecord = (String)mSharedElementSourceNames.get(0);
      }
      paramArrayMap = (View)paramArrayMap.get(paramBackStackRecord);
      paramFragmentTransitionImpl.setEpicenter(paramObject1, paramArrayMap);
      if (paramObject2 != null) {
        paramFragmentTransitionImpl.setEpicenter(paramObject2, paramArrayMap);
      }
    }
  }
  
  private static void setViewVisibility(ArrayList<View> paramArrayList, int paramInt)
  {
    if (paramArrayList == null) {
      return;
    }
    int i = paramArrayList.size() - 1;
    while (i >= 0)
    {
      ((View)paramArrayList.get(i)).setVisibility(paramInt);
      i -= 1;
    }
  }
  
  static void startTransitions(FragmentManagerImpl paramFragmentManagerImpl, ArrayList<BackStackRecord> paramArrayList, ArrayList<Boolean> paramArrayList1, int paramInt1, int paramInt2, boolean paramBoolean)
  {
    if (mCurState < 1) {
      return;
    }
    SparseArray localSparseArray = new SparseArray();
    int i = paramInt1;
    Object localObject;
    while (i < paramInt2)
    {
      localObject = (BackStackRecord)paramArrayList.get(i);
      if (((Boolean)paramArrayList1.get(i)).booleanValue()) {
        calculatePopFragments((BackStackRecord)localObject, localSparseArray, paramBoolean);
      } else {
        calculateFragments((BackStackRecord)localObject, localSparseArray, paramBoolean);
      }
      i += 1;
    }
    if (localSparseArray.size() != 0)
    {
      localObject = new View(mHost.getContext());
      int j = localSparseArray.size();
      i = 0;
      while (i < j)
      {
        int k = localSparseArray.keyAt(i);
        ArrayMap localArrayMap = calculateNameOverrides(k, paramArrayList, paramArrayList1, paramInt1, paramInt2);
        FragmentContainerTransition localFragmentContainerTransition = (FragmentContainerTransition)localSparseArray.valueAt(i);
        if (paramBoolean) {
          configureTransitionsReordered(paramFragmentManagerImpl, k, localFragmentContainerTransition, (View)localObject, localArrayMap);
        } else {
          configureTransitionsOrdered(paramFragmentManagerImpl, k, localFragmentContainerTransition, (View)localObject, localArrayMap);
        }
        i += 1;
      }
    }
  }
  
  static boolean supportsTransition()
  {
    return (PLATFORM_IMPL != null) || (SUPPORT_IMPL != null);
  }
  
  static class FragmentContainerTransition
  {
    public Fragment firstOut;
    public boolean firstOutIsPop;
    public BackStackRecord firstOutTransaction;
    public Fragment lastIn;
    public boolean lastInIsPop;
    public BackStackRecord lastInTransaction;
    
    FragmentContainerTransition() {}
  }
}

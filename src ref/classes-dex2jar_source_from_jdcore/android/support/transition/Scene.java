package android.support.transition;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class Scene
{
  private Context mContext;
  private Runnable mEnterAction;
  private Runnable mExitAction;
  private View mLayout;
  private int mLayoutId = -1;
  private ViewGroup mSceneRoot;
  
  public Scene(@NonNull ViewGroup paramViewGroup)
  {
    mSceneRoot = paramViewGroup;
  }
  
  private Scene(ViewGroup paramViewGroup, int paramInt, Context paramContext)
  {
    mContext = paramContext;
    mSceneRoot = paramViewGroup;
    mLayoutId = paramInt;
  }
  
  public Scene(@NonNull ViewGroup paramViewGroup, @NonNull View paramView)
  {
    mSceneRoot = paramViewGroup;
    mLayout = paramView;
  }
  
  static Scene getCurrentScene(View paramView)
  {
    return (Scene)paramView.getTag(R.id.transition_current_scene);
  }
  
  @NonNull
  public static Scene getSceneForLayout(@NonNull ViewGroup paramViewGroup, @LayoutRes int paramInt, @NonNull Context paramContext)
  {
    Object localObject2 = (SparseArray)paramViewGroup.getTag(R.id.transition_scene_layoutid_cache);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new SparseArray();
      paramViewGroup.setTag(R.id.transition_scene_layoutid_cache, localObject1);
    }
    localObject2 = (Scene)((SparseArray)localObject1).get(paramInt);
    if (localObject2 != null) {
      return localObject2;
    }
    paramViewGroup = new Scene(paramViewGroup, paramInt, paramContext);
    ((SparseArray)localObject1).put(paramInt, paramViewGroup);
    return paramViewGroup;
  }
  
  static void setCurrentScene(View paramView, Scene paramScene)
  {
    paramView.setTag(R.id.transition_current_scene, paramScene);
  }
  
  public void enter()
  {
    if ((mLayoutId > 0) || (mLayout != null))
    {
      getSceneRoot().removeAllViews();
      if (mLayoutId > 0) {
        LayoutInflater.from(mContext).inflate(mLayoutId, mSceneRoot);
      } else {
        mSceneRoot.addView(mLayout);
      }
    }
    if (mEnterAction != null) {
      mEnterAction.run();
    }
    setCurrentScene(mSceneRoot, this);
  }
  
  public void exit()
  {
    if ((getCurrentScene(mSceneRoot) == this) && (mExitAction != null)) {
      mExitAction.run();
    }
  }
  
  @NonNull
  public ViewGroup getSceneRoot()
  {
    return mSceneRoot;
  }
  
  boolean isCreatedFromLayoutResource()
  {
    return mLayoutId > 0;
  }
  
  public void setEnterAction(@Nullable Runnable paramRunnable)
  {
    mEnterAction = paramRunnable;
  }
  
  public void setExitAction(@Nullable Runnable paramRunnable)
  {
    mExitAction = paramRunnable;
  }
}

package com.byimplication.sakay;

import android.support.v4.app.Fragment;
import android.view.View;
import java.util.HashMap;
import kotlin.Metadata;

@Metadata(bv={1, 0, 2}, d1={"\000\f\n\002\030\002\n\002\030\002\n\002\b\002\030\0002\0020\001B\005¢\006\002\020\002¨\006\003"}, d2={"Lcom/byimplication/sakay/TrainLoadGraph;", "Landroid/support/v4/app/Fragment;", "()V", "app_release"}, k=1, mv={1, 1, 9})
public final class TrainLoadGraph
  extends Fragment
{
  private HashMap _$_findViewCache;
  
  public TrainLoadGraph() {}
  
  public void _$_clearFindViewByIdCache()
  {
    if (_$_findViewCache != null) {
      _$_findViewCache.clear();
    }
  }
  
  public View _$_findCachedViewById(int paramInt)
  {
    if (_$_findViewCache == null) {
      _$_findViewCache = new HashMap();
    }
    View localView2 = (View)_$_findViewCache.get(Integer.valueOf(paramInt));
    View localView1 = localView2;
    if (localView2 == null)
    {
      localView1 = getView();
      if (localView1 == null) {
        return null;
      }
      localView1 = localView1.findViewById(paramInt);
      _$_findViewCache.put(Integer.valueOf(paramInt), localView1);
    }
    return localView1;
  }
}

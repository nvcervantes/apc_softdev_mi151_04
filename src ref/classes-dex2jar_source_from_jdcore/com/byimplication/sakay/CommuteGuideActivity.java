package com.byimplication.sakay;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;
import com.byimplication.sakay.model.Conveyance;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\020\013\n\002\b\002\n\002\030\002\n\000\030\0002\0020\001B\005¢\006\002\020\002J\022\020\003\032\0020\0042\b\020\005\032\004\030\0010\006H\024J\b\020\007\032\0020\bH\026J\016\020\t\032\0020\0042\006\020\n\032\0020\013¨\006\f"}, d2={"Lcom/byimplication/sakay/CommuteGuideActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onSupportNavigateUp", "", "showGuideFragment", "conveyance", "Lcom/byimplication/sakay/model/Conveyance;", "app_release"}, k=1, mv={1, 1, 9})
public final class CommuteGuideActivity
  extends AppCompatActivity
{
  private HashMap _$_findViewCache;
  
  public CommuteGuideActivity() {}
  
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
      localView1 = findViewById(paramInt);
      _$_findViewCache.put(Integer.valueOf(paramInt), localView1);
    }
    return localView1;
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427357);
    setSupportActionBar((Toolbar)_$_findCachedViewById(R.id.toolbar));
    paramBundle = getSupportActionBar();
    if (paramBundle != null) {
      paramBundle.setDisplayHomeAsUpEnabled(true);
    }
    ((LinearLayout)_$_findCachedViewById(R.id.busModeChoice)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.showGuideFragment(new Conveyance("BUS", ""));
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.cometModeChoice)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.showGuideFragment(new Conveyance("EJEEP", "COMET"));
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.ferryModeChoice)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.showGuideFragment(new Conveyance("FERRY", ""));
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.jeepneyModeChoice)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.showGuideFragment(new Conveyance("JEEP", ""));
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.mrtLrtModeChoice)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.showGuideFragment(new Conveyance("RAIL", ""));
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.taxiModeChoice)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.showGuideFragment(new Conveyance("TAXI", ""));
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.tricycleModeChoice)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.showGuideFragment(new Conveyance("TRICYCLE", ""));
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.uvModeChoice)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.showGuideFragment(new Conveyance("UV", ""));
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.walkingModeChoice)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.showGuideFragment(new Conveyance("WALK", ""));
      }
    });
  }
  
  public boolean onSupportNavigateUp()
  {
    onBackPressed();
    return true;
  }
  
  public final void showGuideFragment(@NotNull Conveyance paramConveyance)
  {
    Intrinsics.checkParameterIsNotNull(paramConveyance, "conveyance");
    CommuteGuideFragment.Companion.newInstance(paramConveyance).show(getSupportFragmentManager(), "guideFragment");
  }
}

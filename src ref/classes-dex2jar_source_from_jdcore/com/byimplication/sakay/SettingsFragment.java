package com.byimplication.sakay;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0004\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\016\n\002\b\002\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\005\030\000 \0232\0020\001:\001\023B\005¢\006\002\020\002J\006\020\006\032\0020\007J&\020\b\032\004\030\0010\t2\006\020\n\032\0020\0132\b\020\f\032\004\030\0010\r2\b\020\016\032\004\030\0010\017H\026J\032\020\020\032\0020\0072\006\020\021\032\0020\t2\b\020\016\032\004\030\0010\017H\026J\006\020\022\032\0020\007R\020\020\003\032\004\030\0010\004X\016¢\006\002\n\000R\020\020\005\032\004\030\0010\004X\016¢\006\002\n\000¨\006\024"}, d2={"Lcom/byimplication/sakay/SettingsFragment;", "Landroid/support/v4/app/Fragment;", "()V", "param1", "", "param2", "emailSakay", "", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "savedInstanceState", "Landroid/os/Bundle;", "onViewCreated", "view", "playStoreSakay", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class SettingsFragment
  extends Fragment
{
  public static final Companion Companion = new Companion(null);
  private HashMap _$_findViewCache;
  private String param1;
  private String param2;
  
  public SettingsFragment() {}
  
  @JvmStatic
  @NotNull
  public static final SettingsFragment newInstance()
  {
    return Companion.newInstance();
  }
  
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
  
  public final void emailSakay()
  {
    Intent localIntent = new Intent("android.intent.action.SENDTO");
    localIntent.setData(Uri.parse("mailto:"));
    localIntent.putExtra("android.intent.extra.EMAIL", new String[] { "contact@sakay.ph" });
    localIntent.putExtra("android.intent.extra.SUBJECT", "Sakay Android");
    Object localObject = getActivity();
    if (localObject != null) {
      localObject = ((FragmentActivity)localObject).getPackageManager();
    } else {
      localObject = null;
    }
    if (localIntent.resolveActivity((PackageManager)localObject) != null) {
      startActivity(localIntent);
    }
  }
  
  @Nullable
  public View onCreateView(@NotNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramLayoutInflater, "inflater");
    return paramLayoutInflater.inflate(2131427414, paramViewGroup, false);
  }
  
  public void onViewCreated(@NotNull View paramView, @Nullable Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramView, "view");
    ((LinearLayout)_$_findCachedViewById(R.id.reviewLayout)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.playStoreSakay();
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.emailLayout)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.emailSakay();
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.submitSuggestLayout)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.emailSakay();
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.twitterLayout)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        paramAnonymousView = this$0.getActivity();
        if (paramAnonymousView == null) {
          Intrinsics.throwNpe();
        }
        if (paramAnonymousView == null) {
          throw new TypeCastException("null cannot be cast to non-null type com.byimplication.sakay.MainActivity");
        }
        ((MainActivity)paramAnonymousView).twitterSakay();
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.fbLayout)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        paramAnonymousView = this$0.getActivity();
        if (paramAnonymousView == null) {
          Intrinsics.throwNpe();
        }
        if (paramAnonymousView == null) {
          throw new TypeCastException("null cannot be cast to non-null type com.byimplication.sakay.MainActivity");
        }
        ((MainActivity)paramAnonymousView).fbSakay();
      }
    });
    ((LinearLayout)_$_findCachedViewById(R.id.shareLayout)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        paramAnonymousView = this$0.getActivity();
        if (paramAnonymousView == null) {
          Intrinsics.throwNpe();
        }
        if (paramAnonymousView == null) {
          throw new TypeCastException("null cannot be cast to non-null type com.byimplication.sakay.MainActivity");
        }
        ((MainActivity)paramAnonymousView).shareSakay();
      }
    });
    ((TextView)_$_findCachedViewById(R.id.privacyText)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        paramAnonymousView = Uri.parse("https://sakay.ph/terms");
        Intrinsics.checkExpressionValueIsNotNull(paramAnonymousView, "Uri.parse(this)");
        paramAnonymousView = new Intent("android.intent.action.VIEW", paramAnonymousView);
        try
        {
          localObject = this$0.getActivity();
          if (localObject == null) {
            Intrinsics.throwNpe();
          }
          Intrinsics.checkExpressionValueIsNotNull(localObject, "activity!!");
          localObject = ((FragmentActivity)localObject).getPackageManager();
          if (localObject == null) {
            Intrinsics.throwNpe();
          }
          if (!getApplicationInfo"com.android.chrome"0enabled) {
            break label125;
          }
          paramAnonymousView.setPackage("com.android.chrome");
          this$0.startActivity(paramAnonymousView);
          return;
        }
        catch (Exception localException)
        {
          Object localObject;
          for (;;) {}
        }
        localObject = this$0.getActivity();
        if (localObject == null) {
          Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(localObject, "activity!!");
        if (paramAnonymousView.resolveActivity(((FragmentActivity)localObject).getPackageManager()) != null) {
          this$0.startActivity(paramAnonymousView);
        }
        label125:
      }
    });
    paramView = (TextView)_$_findCachedViewById(R.id.versionText);
    Intrinsics.checkExpressionValueIsNotNull(paramView, "versionText");
    paramBundle = getString(2131689833);
    Intrinsics.checkExpressionValueIsNotNull(paramBundle, "getString(R.string.version_blank0_blank1_android)");
    paramView.setText((CharSequence)StringsKt.replace$default(StringsKt.replace$default(paramBundle, "*blank0*", String.valueOf(3.0F), false, 4, null), "*blank1*", String.valueOf(15), false, 4, null));
  }
  
  public final void playStoreSakay()
  {
    Uri localUri = Uri.parse("http://play.google.com/store/apps/details?id=com.byimplication.sakay");
    Intrinsics.checkExpressionValueIsNotNull(localUri, "Uri.parse(this)");
    startActivity(new Intent("android.intent.action.VIEW", localUri));
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\022\n\002\030\002\n\002\020\000\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\b\020\003\032\0020\004H\007¨\006\005"}, d2={"Lcom/byimplication/sakay/SettingsFragment$Companion;", "", "()V", "newInstance", "Lcom/byimplication/sakay/SettingsFragment;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    @JvmStatic
    @NotNull
    public final SettingsFragment newInstance()
    {
      return new SettingsFragment();
    }
  }
}

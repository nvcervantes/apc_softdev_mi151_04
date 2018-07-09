package com.byimplication.sakay;

import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.byimplication.sakay.model.Conveyance;
import java.util.HashMap;
import java.util.List;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000>\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\003\030\000 \0232\0020\001:\002\023\024B\005¢\006\002\020\002J\022\020\007\032\0020\b2\b\020\t\032\004\030\0010\nH\026J\022\020\013\032\0020\f2\b\020\t\032\004\030\0010\nH\026J&\020\r\032\004\030\0010\0162\006\020\017\032\0020\0202\b\020\021\032\004\030\0010\0222\b\020\t\032\004\030\0010\nH\026R\016\020\003\032\0020\004X\016¢\006\002\n\000R\016\020\005\032\0020\006X\016¢\006\002\n\000¨\006\025"}, d2={"Lcom/byimplication/sakay/CommuteGuideFragment;", "Landroid/support/v4/app/DialogFragment;", "()V", "conveyance", "Lcom/byimplication/sakay/model/Conveyance;", "modeGuide", "Lcom/byimplication/sakay/ModeGuide;", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "Companion", "GuideListAdapter", "app_release"}, k=1, mv={1, 1, 9})
public final class CommuteGuideFragment
  extends DialogFragment
{
  private static final String CONVEYANCEPRIMARY = "conveyancePrimary";
  private static final String CONVEYANCESECONDARY = "conveyanceSecondary";
  public static final Companion Companion = new Companion(null);
  private HashMap _$_findViewCache;
  private Conveyance conveyance = new Conveyance("BUS", "");
  private ModeGuide modeGuide = GuideUtils.INSTANCE.getGuide(conveyance);
  
  public CommuteGuideFragment() {}
  
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
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (getArguments() != null)
    {
      paramBundle = getArguments();
      if (paramBundle == null) {
        Intrinsics.throwNpe();
      }
      paramBundle = paramBundle.getString(Companion.access$getCONVEYANCEPRIMARY$p(Companion));
      Intrinsics.checkExpressionValueIsNotNull(paramBundle, "arguments!!.getString(CONVEYANCEPRIMARY)");
      Bundle localBundle = getArguments();
      if (localBundle == null) {
        Intrinsics.throwNpe();
      }
      conveyance = new Conveyance(paramBundle, localBundle.getString(Companion.access$getCONVEYANCESECONDARY$p(Companion)));
      modeGuide = GuideUtils.INSTANCE.getGuide(conveyance);
    }
  }
  
  @NotNull
  public Dialog onCreateDialog(@Nullable Bundle paramBundle)
  {
    paramBundle = super.onCreateDialog(paramBundle);
    Intrinsics.checkExpressionValueIsNotNull(paramBundle, "dlg");
    paramBundle.getWindow().requestFeature(1);
    return paramBundle;
  }
  
  @Nullable
  public View onCreateView(@NotNull LayoutInflater paramLayoutInflater, @Nullable ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramLayoutInflater, "inflater");
    paramViewGroup = paramLayoutInflater.inflate(2131427398, paramViewGroup, false);
    if (paramViewGroup == null) {
      throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
    }
    paramViewGroup = (ViewGroup)paramViewGroup;
    ((ImageView)paramViewGroup.findViewById(2131296446)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.dismiss();
      }
    });
    paramBundle = paramViewGroup.findViewById(2131296445);
    Intrinsics.checkExpressionValueIsNotNull(paramBundle, "thisView.findViewById<Te…View>(R.id.guideModeText)");
    ((TextView)paramBundle).setText((CharSequence)getString(GuideUtils.INSTANCE.translateConveyanceForGuide(conveyance)));
    Log.d("SAKAY", "CommuteGuideFragment::onCreateView 0");
    paramBundle = (ListView)paramViewGroup.findViewById(2131296442);
    paramLayoutInflater = paramLayoutInflater.inflate(2131427421, null);
    ((ImageView)paramLayoutInflater.findViewById(2131296444)).setImageResource(modeGuide.getPicRes());
    View localView = paramLayoutInflater.findViewById(2131296443);
    Intrinsics.checkExpressionValueIsNotNull(localView, "header.findViewById<TextView>(R.id.guideModeDesc)");
    ((TextView)localView).setText(getText(modeGuide.getDescRes()));
    paramBundle.addHeaderView(paramLayoutInflater);
    Intrinsics.checkExpressionValueIsNotNull(paramBundle, "guideList");
    paramBundle.setAdapter((ListAdapter)new GuideListAdapter());
    return (View)paramViewGroup;
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000 \n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\005\n\002\030\002\n\000\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\t\032\0020\n2\006\020\013\032\0020\fR\024\020\003\032\0020\004XD¢\006\b\n\000\032\004\b\005\020\006R\024\020\007\032\0020\004XD¢\006\b\n\000\032\004\b\b\020\006¨\006\r"}, d2={"Lcom/byimplication/sakay/CommuteGuideFragment$Companion;", "", "()V", "CONVEYANCEPRIMARY", "", "getCONVEYANCEPRIMARY", "()Ljava/lang/String;", "CONVEYANCESECONDARY", "getCONVEYANCESECONDARY", "newInstance", "Lcom/byimplication/sakay/CommuteGuideFragment;", "conveyance", "Lcom/byimplication/sakay/model/Conveyance;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    private final String getCONVEYANCEPRIMARY()
    {
      return CommuteGuideFragment.access$getCONVEYANCEPRIMARY$cp();
    }
    
    private final String getCONVEYANCESECONDARY()
    {
      return CommuteGuideFragment.access$getCONVEYANCESECONDARY$cp();
    }
    
    @NotNull
    public final CommuteGuideFragment newInstance(@NotNull Conveyance paramConveyance)
    {
      Intrinsics.checkParameterIsNotNull(paramConveyance, "conveyance");
      CommuteGuideFragment localCommuteGuideFragment = new CommuteGuideFragment();
      Bundle localBundle = new Bundle();
      Companion localCompanion = (Companion)this;
      localBundle.putString(localCompanion.getCONVEYANCEPRIMARY(), paramConveyance.getPrimary());
      localBundle.putString(localCompanion.getCONVEYANCESECONDARY(), paramConveyance.getSecondary());
      localCommuteGuideFragment.setArguments(localBundle);
      return localCommuteGuideFragment;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000.\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\b\004\030\0002\0020\001B\005¢\006\002\020\002J\b\020\003\032\0020\004H\026J\020\020\005\032\0020\0062\006\020\007\032\0020\004H\026J\020\020\b\032\0020\t2\006\020\007\032\0020\004H\026J\"\020\n\032\0020\0132\006\020\f\032\0020\0042\b\020\r\032\004\030\0010\0132\006\020\016\032\0020\017H\026¨\006\020"}, d2={"Lcom/byimplication/sakay/CommuteGuideFragment$GuideListAdapter;", "Landroid/widget/BaseAdapter;", "(Lcom/byimplication/sakay/CommuteGuideFragment;)V", "getCount", "", "getItem", "Lcom/byimplication/sakay/GuideInstruction;", "itemNum", "getItemId", "", "getView", "Landroid/view/View;", "position", "convertView", "viewGroup", "Landroid/view/ViewGroup;", "app_release"}, k=1, mv={1, 1, 9})
  public final class GuideListAdapter
    extends BaseAdapter
  {
    public GuideListAdapter() {}
    
    public int getCount()
    {
      return CommuteGuideFragment.access$getModeGuide$p(this$0).getInstructions().size();
    }
    
    @NotNull
    public GuideInstruction getItem(int paramInt)
    {
      return (GuideInstruction)CommuteGuideFragment.access$getModeGuide$p(this$0).getInstructions().get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return paramInt;
    }
    
    @NotNull
    public View getView(int paramInt, @Nullable View paramView, @NotNull ViewGroup paramViewGroup)
    {
      Intrinsics.checkParameterIsNotNull(paramViewGroup, "viewGroup");
      if (paramView == null)
      {
        paramViewGroup = this$0.getActivity();
        if (paramViewGroup == null) {
          Intrinsics.throwNpe();
        }
        if (paramViewGroup == null) {
          Intrinsics.throwNpe();
        }
        Intrinsics.checkExpressionValueIsNotNull(paramViewGroup, "activity!!!!");
        paramViewGroup = paramViewGroup.getLayoutInflater().inflate(2131427420, null);
        Intrinsics.checkExpressionValueIsNotNull(paramViewGroup, "activity!!!!.layoutInfla…t.guide_list_entry, null)");
      }
      else
      {
        paramViewGroup = paramView;
      }
      if (paramView == null)
      {
        paramView = paramViewGroup.findViewById(2131296441);
        Intrinsics.checkExpressionValueIsNotNull(paramView, "theView.findViewById(R.id.guideEntryPic)");
        paramView = (ImageView)paramView;
        localObject1 = paramViewGroup.findViewById(2131296440);
        Intrinsics.checkExpressionValueIsNotNull(localObject1, "theView.findViewById(R.id.guideEntryLabel)");
        localObject1 = (TextView)localObject1;
        localObject2 = paramViewGroup.findViewById(2131296439);
        Intrinsics.checkExpressionValueIsNotNull(localObject2, "theView.findViewById(R.id.guideEntryDesc)");
        paramView = new GuideListEntryHolder(paramView, (TextView)localObject1, (TextView)localObject2);
        paramViewGroup.setTag(paramView);
      }
      else
      {
        paramView = paramView.getTag();
        if (paramView == null) {
          throw new TypeCastException("null cannot be cast to non-null type com.byimplication.sakay.GuideListEntryHolder");
        }
        paramView = (GuideListEntryHolder)paramView;
      }
      Object localObject2 = getItem(paramInt);
      paramView.getGuideEntryPic().setImageResource(((GuideInstruction)localObject2).getPicRes());
      TextView localTextView = paramView.getGuideEntryLabel();
      Object localObject1 = ((GuideInstruction)localObject2).getLabel();
      if ((((String)localObject1).hashCode() == 83067) && (((String)localObject1).equals("TIP"))) {
        localObject1 = (CharSequence)this$0.getString(2131689802);
      } else {
        localObject1 = (CharSequence)((GuideInstruction)localObject2).getLabel();
      }
      localTextView.setText((CharSequence)localObject1);
      paramView.getGuideEntryDesc().setText((CharSequence)this$0.getString(((GuideInstruction)localObject2).getDesc()));
      return paramViewGroup;
    }
  }
}

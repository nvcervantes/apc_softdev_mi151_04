package com.byimplication.sakay;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.SwitchCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.ImageView;
import android.widget.TextView;
import com.byimplication.sakay.action.OnEvent;
import com.byimplication.sakay.action.OnSimpleEvent;
import com.byimplication.sakay.action.SearchAgain;
import com.byimplication.sakay.action.SetModePrefs;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.json.JSONArray;

@Metadata(bv={1, 0, 2}, d1={"\000T\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\021\n\002\020\016\n\002\b\006\n\002\020\002\n\002\b\002\n\002\020\b\n\002\b\003\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\n\002\030\002\n\002\020\013\n\002\b\004\030\000 $2\0020\001:\001$B\005¢\006\002\020\002J\006\020\013\032\0020\fJ\006\020\r\032\0020\fJ\006\020\016\032\0020\017J\006\020\020\032\0020\017J\022\020\021\032\0020\f2\b\020\022\032\004\030\0010\023H\026J\022\020\024\032\0020\0252\b\020\022\032\004\030\0010\023H\026J&\020\026\032\004\030\0010\0272\006\020\030\032\0020\0312\b\020\032\032\004\030\0010\0332\b\020\022\032\004\030\0010\023H\026J\b\020\034\032\0020\fH\026J\032\020\035\032\0020\f2\006\020\036\032\0020\0272\b\020\022\032\004\030\0010\023H\026J\030\020\037\032\024\022\n\022\b\022\004\022\0020\0050\004\022\004\022\0020!0 J\006\020\"\032\0020\fJ\006\020#\032\0020\fR\"\020\003\032\b\022\004\022\0020\0050\004X\016¢\006\020\n\002\020\n\032\004\b\006\020\007\"\004\b\b\020\t¨\006%"}, d2={"Lcom/byimplication/sakay/ModePrefDialog;", "Landroid/support/v4/app/DialogFragment;", "()V", "savedModePrefs", "", "", "getSavedModePrefs", "()[Ljava/lang/String;", "setSavedModePrefs", "([Ljava/lang/String;)V", "[Ljava/lang/String;", "checkPrefsSelected", "", "loadPrefs", "numberOfSelected", "", "numberOfSelectedOnSaved", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onStart", "onViewCreated", "view", "savePrefs", "Lkotlin/Pair;", "", "selectAllPrefs", "unselectAllPrefs", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class ModePrefDialog
  extends DialogFragment
{
  @NotNull
  public static final String BUS = "BUS";
  public static final Companion Companion = new Companion(null);
  @NotNull
  public static final String E_JEEP = "EJEEP";
  @NotNull
  public static final String FERRY = "FERRY";
  @NotNull
  public static final String JEEP = "RBUS";
  @NotNull
  public static final String P2P_BUS = "PBUS";
  @NotNull
  public static final String PREFERS_BUS = "prefers_bus";
  @NotNull
  public static final String PREFERS_EJEEP = "prefers_ejeep";
  @NotNull
  public static final String PREFERS_FERRY = "prefers_ferry";
  @NotNull
  public static final String PREFERS_JEEPNEY = "prefers_jeepney";
  @NotNull
  public static final String PREFERS_P2P = "prefers_p2p";
  @NotNull
  public static final String PREFERS_RAIL = "prefers_rail";
  @NotNull
  public static final String PREFERS_UV = "prefers_uv";
  @NotNull
  public static final String RAIL = "RAIL";
  @NotNull
  public static final String UV = "UV";
  private HashMap _$_findViewCache;
  @NotNull
  private String[] savedModePrefs = new String[0];
  
  public ModePrefDialog() {}
  
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
  
  public final void checkPrefsSelected()
  {
    if (numberOfSelected() < 3)
    {
      localTextView = (TextView)_$_findCachedViewById(R.id.resetModesBtn);
      Intrinsics.checkExpressionValueIsNotNull(localTextView, "resetModesBtn");
      localTextView.setText((CharSequence)getString(2131689761));
      return;
    }
    TextView localTextView = (TextView)_$_findCachedViewById(R.id.resetModesBtn);
    Intrinsics.checkExpressionValueIsNotNull(localTextView, "resetModesBtn");
    localTextView.setText((CharSequence)getString(2131689762));
  }
  
  @NotNull
  public final String[] getSavedModePrefs()
  {
    return savedModePrefs;
  }
  
  public final void loadPrefs()
  {
    Object localObject = getActivity();
    if (localObject == null) {
      Intrinsics.throwNpe();
    }
    localObject = ((FragmentActivity)localObject).getSharedPreferences(getString(2131689734), 0);
    Intrinsics.checkExpressionValueIsNotNull(localObject, "activity!!.getSharedPref…y), Context.MODE_PRIVATE)");
    SwitchCompat localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.jeepModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(((SharedPreferences)localObject).getBoolean("prefers_jeepney", false));
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.busModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(((SharedPreferences)localObject).getBoolean("prefers_bus", false));
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.p2pModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(((SharedPreferences)localObject).getBoolean("prefers_p2p", false));
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.ejeepModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(((SharedPreferences)localObject).getBoolean("prefers_ejeep", false));
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.uvModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(((SharedPreferences)localObject).getBoolean("prefers_uv", false));
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.ferryModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(((SharedPreferences)localObject).getBoolean("prefers_ferry", false));
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.railModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(((SharedPreferences)localObject).getBoolean("prefers_rail", false));
    }
  }
  
  public final int numberOfSelected()
  {
    SwitchCompat localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.jeepModeSwitch);
    if ((localSwitchCompat != null) && (localSwitchCompat.isChecked() == true)) {
      j = 1;
    } else {
      j = 0;
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.busModeSwitch);
    int i = j;
    if (localSwitchCompat != null)
    {
      i = j;
      if (localSwitchCompat.isChecked() == true) {
        i = j + 1;
      }
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.p2pModeSwitch);
    int j = i;
    if (localSwitchCompat != null)
    {
      j = i;
      if (localSwitchCompat.isChecked() == true) {
        j = i + 1;
      }
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.ejeepModeSwitch);
    i = j;
    if (localSwitchCompat != null)
    {
      i = j;
      if (localSwitchCompat.isChecked() == true) {
        i = j + 1;
      }
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.uvModeSwitch);
    j = i;
    if (localSwitchCompat != null)
    {
      j = i;
      if (localSwitchCompat.isChecked() == true) {
        j = i + 1;
      }
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.ferryModeSwitch);
    i = j;
    if (localSwitchCompat != null)
    {
      i = j;
      if (localSwitchCompat.isChecked() == true) {
        i = j + 1;
      }
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.railModeSwitch);
    j = i;
    if (localSwitchCompat != null)
    {
      j = i;
      if (localSwitchCompat.isChecked() == true) {
        j = i + 1;
      }
    }
    return j;
  }
  
  public final int numberOfSelectedOnSaved()
  {
    Object localObject = getActivity();
    if (localObject == null) {
      Intrinsics.throwNpe();
    }
    localObject = ((FragmentActivity)localObject).getSharedPreferences(getString(2131689734), 0);
    Intrinsics.checkExpressionValueIsNotNull(localObject, "activity!!.getSharedPref…y), Context.MODE_PRIVATE)");
    if (((SharedPreferences)localObject).getBoolean("prefers_jeepney", false)) {
      j = 1;
    } else {
      j = 0;
    }
    int i = j;
    if (((SharedPreferences)localObject).getBoolean("prefers_bus", false)) {
      i = j + 1;
    }
    int j = i;
    if (((SharedPreferences)localObject).getBoolean("prefers_p2p", false)) {
      j = i + 1;
    }
    i = j;
    if (((SharedPreferences)localObject).getBoolean("prefers_ejeep", false)) {
      i = j + 1;
    }
    j = i;
    if (((SharedPreferences)localObject).getBoolean("prefers_uv", false)) {
      j = i + 1;
    }
    i = j;
    if (((SharedPreferences)localObject).getBoolean("prefers_ferry", false)) {
      i = j + 1;
    }
    j = i;
    if (((SharedPreferences)localObject).getBoolean("prefers_rail", false)) {
      j = i + 1;
    }
    return j;
  }
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    paramBundle = Companion;
    FragmentActivity localFragmentActivity = getActivity();
    if (localFragmentActivity == null) {
      Intrinsics.throwNpe();
    }
    Intrinsics.checkExpressionValueIsNotNull(localFragmentActivity, "activity!!");
    savedModePrefs = paramBundle.loadModePreferences((Context)localFragmentActivity);
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
    return paramLayoutInflater.inflate(2131427409, paramViewGroup, false);
  }
  
  public void onStart()
  {
    super.onStart();
    loadPrefs();
  }
  
  public void onViewCreated(@NotNull View paramView, @Nullable Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramView, "view");
    ((ImageView)_$_findCachedViewById(R.id.modePrefXButton)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        new OnSimpleEvent("Mode Preferences Close Button - Click").post();
        this$0.dismiss();
      }
    });
    ((TextView)_$_findCachedViewById(R.id.cancelModePrefBtn)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        new OnSimpleEvent("Mode Preferences Cancel Button - Click").post();
        this$0.dismiss();
      }
    });
    ((TextView)_$_findCachedViewById(R.id.doneModePrefBtn)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        paramAnonymousView = this$0.savePrefs();
        if (((Boolean)paramAnonymousView.getSecond()).booleanValue())
        {
          new OnEvent("Mode Preferences Done Button - Click", MapsKt.mapOf(new Pair("modes_preferred", new JSONArray(paramAnonymousView.getFirst()).toString())));
          new SearchAgain().post();
        }
        this$0.dismiss();
      }
    });
    paramView = (CompoundButton.OnCheckedChangeListener)new CompoundButton.OnCheckedChangeListener()
    {
      public final void onCheckedChanged(CompoundButton paramAnonymousCompoundButton, boolean paramAnonymousBoolean)
      {
        this$0.checkPrefsSelected();
      }
    };
    ((SwitchCompat)_$_findCachedViewById(R.id.jeepModeSwitch)).setOnCheckedChangeListener(paramView);
    ((SwitchCompat)_$_findCachedViewById(R.id.busModeSwitch)).setOnCheckedChangeListener(paramView);
    ((SwitchCompat)_$_findCachedViewById(R.id.p2pModeSwitch)).setOnCheckedChangeListener(paramView);
    ((SwitchCompat)_$_findCachedViewById(R.id.ejeepModeSwitch)).setOnCheckedChangeListener(paramView);
    ((SwitchCompat)_$_findCachedViewById(R.id.uvModeSwitch)).setOnCheckedChangeListener(paramView);
    ((SwitchCompat)_$_findCachedViewById(R.id.ferryModeSwitch)).setOnCheckedChangeListener(paramView);
    ((SwitchCompat)_$_findCachedViewById(R.id.railModeSwitch)).setOnCheckedChangeListener(paramView);
    ((TextView)_$_findCachedViewById(R.id.resetModesBtn)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        if (this$0.numberOfSelected() < 3)
        {
          paramAnonymousView = (TextView)this$0._$_findCachedViewById(R.id.resetModesBtn);
          Intrinsics.checkExpressionValueIsNotNull(paramAnonymousView, "resetModesBtn");
          paramAnonymousView.setText((CharSequence)this$0.getString(2131689762));
          this$0.selectAllPrefs();
          return;
        }
        paramAnonymousView = (TextView)this$0._$_findCachedViewById(R.id.resetModesBtn);
        Intrinsics.checkExpressionValueIsNotNull(paramAnonymousView, "resetModesBtn");
        paramAnonymousView.setText((CharSequence)this$0.getString(2131689761));
        this$0.unselectAllPrefs();
      }
    });
    paramBundle = (TextView)_$_findCachedViewById(R.id.resetModesBtn);
    Intrinsics.checkExpressionValueIsNotNull(paramBundle, "resetModesBtn");
    if (numberOfSelectedOnSaved() != 7) {
      paramView = (CharSequence)getString(2131689761);
    } else {
      paramView = (CharSequence)getString(2131689762);
    }
    paramBundle.setText(paramView);
  }
  
  @NotNull
  public final Pair<String[], Boolean> savePrefs()
  {
    Object localObject1 = new ArrayList();
    Object localObject2 = (SwitchCompat)_$_findCachedViewById(R.id.jeepModeSwitch);
    boolean bool1;
    if (localObject2 != null) {
      bool1 = ((SwitchCompat)localObject2).isChecked();
    } else {
      bool1 = false;
    }
    if (bool1) {
      ((Collection)localObject1).add("RBUS");
    }
    localObject2 = (SwitchCompat)_$_findCachedViewById(R.id.busModeSwitch);
    boolean bool2;
    if (localObject2 != null) {
      bool2 = ((SwitchCompat)localObject2).isChecked();
    } else {
      bool2 = false;
    }
    if (bool2) {
      ((Collection)localObject1).add("BUS");
    }
    localObject2 = (SwitchCompat)_$_findCachedViewById(R.id.p2pModeSwitch);
    boolean bool3;
    if (localObject2 != null) {
      bool3 = ((SwitchCompat)localObject2).isChecked();
    } else {
      bool3 = false;
    }
    if (bool3) {
      ((Collection)localObject1).add("PBUS");
    }
    localObject2 = (SwitchCompat)_$_findCachedViewById(R.id.ejeepModeSwitch);
    boolean bool4;
    if (localObject2 != null) {
      bool4 = ((SwitchCompat)localObject2).isChecked();
    } else {
      bool4 = false;
    }
    if (bool4) {
      ((Collection)localObject1).add("EJEEP");
    }
    localObject2 = (SwitchCompat)_$_findCachedViewById(R.id.uvModeSwitch);
    boolean bool5;
    if (localObject2 != null) {
      bool5 = ((SwitchCompat)localObject2).isChecked();
    } else {
      bool5 = false;
    }
    if (bool5) {
      ((Collection)localObject1).add("UV");
    }
    localObject2 = (SwitchCompat)_$_findCachedViewById(R.id.ferryModeSwitch);
    boolean bool6;
    if (localObject2 != null) {
      bool6 = ((SwitchCompat)localObject2).isChecked();
    } else {
      bool6 = false;
    }
    if (bool6) {
      ((Collection)localObject1).add("FERRY");
    }
    localObject2 = (SwitchCompat)_$_findCachedViewById(R.id.railModeSwitch);
    boolean bool7;
    if (localObject2 != null) {
      bool7 = ((SwitchCompat)localObject2).isChecked();
    } else {
      bool7 = false;
    }
    if (bool7) {
      ((Collection)localObject1).add("RAIL");
    }
    localObject1 = (Collection)localObject1;
    localObject2 = ((Collection)localObject1).toArray(new String[0]);
    if (localObject2 == null) {
      throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
    }
    localObject2 = (String[])localObject2;
    boolean bool8 = Arrays.equals((Object[])localObject2, (Object[])savedModePrefs) ^ true;
    if (bool8)
    {
      Object localObject3 = getActivity();
      if (localObject3 == null) {
        Intrinsics.throwNpe();
      }
      localObject3 = ((FragmentActivity)localObject3).getSharedPreferences(getString(2131689734), 0);
      Intrinsics.checkExpressionValueIsNotNull(localObject3, "activity!!.getSharedPref…y), Context.MODE_PRIVATE)");
      localObject3 = ((SharedPreferences)localObject3).edit();
      Intrinsics.checkExpressionValueIsNotNull(localObject3, "sharedPref.edit()");
      ((SharedPreferences.Editor)localObject3).putBoolean("prefers_jeepney", bool1);
      ((SharedPreferences.Editor)localObject3).putBoolean("prefers_bus", bool2);
      ((SharedPreferences.Editor)localObject3).putBoolean("prefers_p2p", bool3);
      ((SharedPreferences.Editor)localObject3).putBoolean("prefers_ejeep", bool4);
      ((SharedPreferences.Editor)localObject3).putBoolean("prefers_uv", bool5);
      ((SharedPreferences.Editor)localObject3).putBoolean("prefers_ferry", bool6);
      ((SharedPreferences.Editor)localObject3).putBoolean("prefers_rail", bool7);
      localObject1 = ((Collection)localObject1).toArray(new String[0]);
      if (localObject1 == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
      }
      new SetModePrefs((String[])localObject1, false, 2, null).post();
      ((SharedPreferences.Editor)localObject3).apply();
    }
    return new Pair(localObject2, Boolean.valueOf(bool8));
  }
  
  public final void selectAllPrefs()
  {
    SwitchCompat localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.jeepModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(true);
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.busModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(true);
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.p2pModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(true);
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.ejeepModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(true);
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.uvModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(true);
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.ferryModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(true);
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.railModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(true);
    }
  }
  
  public final void setSavedModePrefs(@NotNull String[] paramArrayOfString)
  {
    Intrinsics.checkParameterIsNotNull(paramArrayOfString, "<set-?>");
    savedModePrefs = paramArrayOfString;
  }
  
  public final void unselectAllPrefs()
  {
    SwitchCompat localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.jeepModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(false);
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.busModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(false);
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.p2pModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(false);
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.ejeepModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(false);
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.uvModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(false);
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.ferryModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(false);
    }
    localSwitchCompat = (SwitchCompat)_$_findCachedViewById(R.id.railModeSwitch);
    if (localSwitchCompat != null) {
      localSwitchCompat.setChecked(false);
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\0000\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\017\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\020\021\n\002\b\002\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\026\020\022\032\0020\0042\006\020\023\032\0020\0242\006\020\025\032\0020\004J\016\020\026\032\0020\0272\006\020\025\032\0020\004J\031\020\030\032\b\022\004\022\0020\0040\0312\006\020\023\032\0020\024¢\006\002\020\032J\006\020\033\032\0020\034R\016\020\003\032\0020\004XT¢\006\002\n\000R\016\020\005\032\0020\004XT¢\006\002\n\000R\016\020\006\032\0020\004XT¢\006\002\n\000R\016\020\007\032\0020\004XT¢\006\002\n\000R\016\020\b\032\0020\004XT¢\006\002\n\000R\016\020\t\032\0020\004XT¢\006\002\n\000R\016\020\n\032\0020\004XT¢\006\002\n\000R\016\020\013\032\0020\004XT¢\006\002\n\000R\016\020\f\032\0020\004XT¢\006\002\n\000R\016\020\r\032\0020\004XT¢\006\002\n\000R\016\020\016\032\0020\004XT¢\006\002\n\000R\016\020\017\032\0020\004XT¢\006\002\n\000R\016\020\020\032\0020\004XT¢\006\002\n\000R\016\020\021\032\0020\004XT¢\006\002\n\000¨\006\035"}, d2={"Lcom/byimplication/sakay/ModePrefDialog$Companion;", "", "()V", "BUS", "", "E_JEEP", "FERRY", "JEEP", "P2P_BUS", "PREFERS_BUS", "PREFERS_EJEEP", "PREFERS_FERRY", "PREFERS_JEEPNEY", "PREFERS_P2P", "PREFERS_RAIL", "PREFERS_UV", "RAIL", "UV", "codeToReadableString", "context", "Landroid/content/Context;", "modeCode", "getImageResource", "", "loadModePreferences", "", "(Landroid/content/Context;)[Ljava/lang/String;", "newInstance", "Lcom/byimplication/sakay/ModePrefDialog;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    @NotNull
    public final String codeToReadableString(@NotNull Context paramContext, @NotNull String paramString)
    {
      Intrinsics.checkParameterIsNotNull(paramContext, "context");
      Intrinsics.checkParameterIsNotNull(paramString, "modeCode");
      switch (paramString.hashCode())
      {
      default: 
        break;
      case 66783482: 
        if (paramString.equals("FERRY"))
        {
          paramContext = paramContext.getString(2131689659);
          Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.getString(R.string.mode_ferry)");
          return paramContext;
        }
        break;
      case 65996011: 
        if (paramString.equals("EJEEP"))
        {
          paramContext = paramContext.getString(2131689657);
          Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.getString(R.string.mode_ejeep)");
          return paramContext;
        }
        break;
      case 2509006: 
        if (paramString.equals("RBUS"))
        {
          paramContext = paramContext.getString(2131689660);
          Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.getString(R.string.mode_jeepney)");
          return paramContext;
        }
        break;
      case 2507666: 
        if (paramString.equals("RAIL"))
        {
          paramContext = paramContext.getString(2131689676);
          Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.getString(R.string.mode_train)");
          return paramContext;
        }
        break;
      case 2449424: 
        if (paramString.equals("PBUS"))
        {
          paramContext = paramContext.getString(2131689669);
          Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.getString(R.string.mode_p2p_bus)");
          return paramContext;
        }
        break;
      case 66144: 
        if (paramString.equals("BUS"))
        {
          paramContext = paramContext.getString(2131689653);
          Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.getString(R.string.mode_bus)");
          return paramContext;
        }
        break;
      case 2721: 
        if (paramString.equals("UV"))
        {
          paramContext = paramContext.getString(2131689682);
          Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.getString(R.string.mode_uv)");
          return paramContext;
        }
        break;
      }
      return "";
    }
    
    public final int getImageResource(@NotNull String paramString)
    {
      Intrinsics.checkParameterIsNotNull(paramString, "modeCode");
      int j = paramString.hashCode();
      int i = 2131558421;
      switch (j)
      {
      default: 
        return 2131558421;
      case 66783482: 
        if (paramString.equals("FERRY")) {
          return 2131558425;
        }
        break;
      case 65996011: 
        if (paramString.equals("EJEEP")) {
          return 2131558423;
        }
        break;
      case 2509006: 
        if (paramString.equals("RBUS")) {
          return 2131558427;
        }
        break;
      case 2507666: 
        if (paramString.equals("RAIL")) {
          return 2131558431;
        }
        break;
      case 2449424: 
        if (paramString.equals("PBUS")) {
          return 2131558429;
        }
        break;
      case 66144: 
        paramString.equals("BUS");
        return 2131558421;
      case 2721: 
        if (paramString.equals("UV")) {
          i = 2131558433;
        }
        break;
      }
      return i;
    }
    
    @NotNull
    public final String[] loadModePreferences(@NotNull Context paramContext)
    {
      Intrinsics.checkParameterIsNotNull(paramContext, "context");
      paramContext = paramContext.getSharedPreferences(paramContext.getString(2131689734), 0);
      Intrinsics.checkExpressionValueIsNotNull(paramContext, "context.getSharedPrefere…y), Context.MODE_PRIVATE)");
      ArrayList localArrayList = new ArrayList();
      if (paramContext.getBoolean("prefers_jeepney", false)) {
        localArrayList.add("RBUS");
      }
      if (paramContext.getBoolean("prefers_bus", false)) {
        localArrayList.add("BUS");
      }
      if (paramContext.getBoolean("prefers_p2p", false)) {
        localArrayList.add("PBUS");
      }
      if (paramContext.getBoolean("prefers_ejeep", false)) {
        localArrayList.add("EJEEP");
      }
      if (paramContext.getBoolean("prefers_uv", false)) {
        localArrayList.add("UV");
      }
      if (paramContext.getBoolean("prefers_ferry", false)) {
        localArrayList.add("FERRY");
      }
      if (paramContext.getBoolean("prefers_rail", false)) {
        localArrayList.add("RAIL");
      }
      paramContext = ((Collection)localArrayList).toArray(new String[0]);
      if (paramContext == null) {
        throw new TypeCastException("null cannot be cast to non-null type kotlin.Array<T>");
      }
      return (String[])paramContext;
    }
    
    @NotNull
    public final ModePrefDialog newInstance()
    {
      return new ModePrefDialog();
    }
  }
}

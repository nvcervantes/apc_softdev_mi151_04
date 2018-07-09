package com.byimplication.sakay;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.HashMap;
import kotlin.Metadata;
import kotlin.TypeCastException;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000F\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\016\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\004\030\000 \0272\0020\001:\002\027\030B\005¢\006\002\020\002J\022\020\007\032\0020\b2\b\020\t\032\004\030\0010\nH\026J\022\020\013\032\0020\b2\b\020\f\032\004\030\0010\rH\026J\022\020\016\032\0020\0172\b\020\f\032\004\030\0010\rH\026J&\020\020\032\004\030\0010\0212\006\020\022\032\0020\0232\b\020\024\032\004\030\0010\0252\b\020\f\032\004\030\0010\rH\026J\b\020\026\032\0020\bH\026R\020\020\003\032\004\030\0010\004X\016¢\006\002\n\000R\020\020\005\032\004\030\0010\006X\016¢\006\002\n\000¨\006\031"}, d2={"Lcom/byimplication/sakay/SavedRouteNamer;", "Landroid/support/v4/app/DialogFragment;", "()V", "currentName", "", "mListener", "Lcom/byimplication/sakay/SavedRouteNamer$OnFragmentInteractionListener;", "onAttach", "", "context", "Landroid/content/Context;", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onDetach", "Companion", "OnFragmentInteractionListener", "app_release"}, k=1, mv={1, 1, 9})
public final class SavedRouteNamer
  extends DialogFragment
{
  private static final String ARG_CURRENT_NAME = "currentName";
  public static final Companion Companion = new Companion(null);
  private HashMap _$_findViewCache;
  private String currentName;
  private OnFragmentInteractionListener mListener;
  
  public SavedRouteNamer() {}
  
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
  
  public void onAttach(@Nullable Context paramContext)
  {
    super.onAttach(paramContext);
    if ((paramContext instanceof OnFragmentInteractionListener))
    {
      mListener = ((OnFragmentInteractionListener)paramContext);
      return;
    }
    StringBuilder localStringBuilder = new StringBuilder();
    if (paramContext == null) {
      Intrinsics.throwNpe();
    }
    localStringBuilder.append(paramContext.toString());
    localStringBuilder.append(" must implement OnFragmentInteractionListener");
    throw ((Throwable)new RuntimeException(localStringBuilder.toString()));
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
      currentName = paramBundle.getString(Companion.access$getARG_CURRENT_NAME$p(Companion));
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
  public View onCreateView(@NotNull LayoutInflater paramLayoutInflater, @Nullable final ViewGroup paramViewGroup, @Nullable Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramLayoutInflater, "inflater");
    paramLayoutInflater = paramLayoutInflater.inflate(2131427412, paramViewGroup, false);
    if (paramLayoutInflater == null) {
      throw new TypeCastException("null cannot be cast to non-null type android.view.ViewGroup");
    }
    paramLayoutInflater = (ViewGroup)paramLayoutInflater;
    ((ImageView)paramLayoutInflater.findViewById(2131296619)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.dismiss();
      }
    });
    ((TextView)paramLayoutInflater.findViewById(2131296615)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.dismiss();
      }
    });
    paramViewGroup = (TextView)paramLayoutInflater.findViewById(2131296616);
    ((TextView)paramLayoutInflater.findViewById(2131296617)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        paramAnonymousView = SavedRouteNamer.access$getMListener$p(this$0);
        if (paramAnonymousView != null)
        {
          TextView localTextView = paramViewGroup;
          Intrinsics.checkExpressionValueIsNotNull(localTextView, "routeNamerEdit");
          paramAnonymousView.saveThisRoute(localTextView.getText().toString());
        }
        this$0.dismiss();
      }
    });
    return (View)paramLayoutInflater;
  }
  
  public void onDetach()
  {
    super.onDetach();
    mListener = ((OnFragmentInteractionListener)null);
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\034\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\003\n\002\030\002\n\002\b\002\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\007\032\0020\b2\006\020\t\032\0020\004R\024\020\003\032\0020\004XD¢\006\b\n\000\032\004\b\005\020\006¨\006\n"}, d2={"Lcom/byimplication/sakay/SavedRouteNamer$Companion;", "", "()V", "ARG_CURRENT_NAME", "", "getARG_CURRENT_NAME", "()Ljava/lang/String;", "newInstance", "Lcom/byimplication/sakay/SavedRouteNamer;", "currentName", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    private final String getARG_CURRENT_NAME()
    {
      return SavedRouteNamer.access$getARG_CURRENT_NAME$cp();
    }
    
    @NotNull
    public final SavedRouteNamer newInstance(@NotNull String paramString)
    {
      Intrinsics.checkParameterIsNotNull(paramString, "currentName");
      SavedRouteNamer localSavedRouteNamer = new SavedRouteNamer();
      Bundle localBundle = new Bundle();
      localBundle.putString(((Companion)this).getARG_CURRENT_NAME(), paramString);
      localSavedRouteNamer.setArguments(localBundle);
      return localSavedRouteNamer;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\026\n\002\030\002\n\002\020\000\n\000\n\002\020\002\n\000\n\002\020\016\n\000\bf\030\0002\0020\001J\020\020\002\032\0020\0032\006\020\004\032\0020\005H&¨\006\006"}, d2={"Lcom/byimplication/sakay/SavedRouteNamer$OnFragmentInteractionListener;", "", "saveThisRoute", "", "routeName", "", "app_release"}, k=1, mv={1, 1, 9})
  public static abstract interface OnFragmentInteractionListener
  {
    public abstract void saveThisRoute(@NotNull String paramString);
  }
}

package com.byimplication.sakay;

import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AlertDialog.Builder;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.support.v7.widget.RecyclerView.LayoutManager;
import android.support.v7.widget.RecyclerView.ViewHolder;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import com.byimplication.sakay.model.ScheduleType;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000.\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\002\b\005\n\002\020\002\n\000\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\002\030\0002\0020\001:\001\021B\005¢\006\002\020\002J\022\020\n\032\0020\0132\b\020\f\032\004\030\0010\rH\024J\b\020\016\032\0020\013H\024J\b\020\017\032\0020\020H\026R \020\003\032\b\022\004\022\0020\0050\004X\016¢\006\016\n\000\032\004\b\006\020\007\"\004\b\b\020\t¨\006\022"}, d2={"Lcom/byimplication/sakay/SavedRoutes;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "savedRoutes", "", "Lcom/byimplication/sakay/RouteFile;", "getSavedRoutes", "()Ljava/util/List;", "setSavedRoutes", "(Ljava/util/List;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onStart", "onSupportNavigateUp", "", "SavedRoutesAdapter", "app_release"}, k=1, mv={1, 1, 9})
public final class SavedRoutes
  extends AppCompatActivity
{
  private HashMap _$_findViewCache;
  @NotNull
  private List<RouteFile> savedRoutes = CollectionsKt.emptyList();
  
  public SavedRoutes() {}
  
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
  
  @NotNull
  public final List<RouteFile> getSavedRoutes()
  {
    return savedRoutes;
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427360);
    setSupportActionBar((Toolbar)_$_findCachedViewById(R.id.toolbar));
    paramBundle = getSupportActionBar();
    if (paramBundle != null) {
      paramBundle.setDisplayHomeAsUpEnabled(true);
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    Object localObject1 = LocalStorage.INSTANCE;
    Object localObject2 = (Context)this;
    savedRoutes = ((LocalStorage)localObject1).loadSavedRoutesList((Context)localObject2);
    localObject1 = (RecyclerView.LayoutManager)new LinearLayoutManager((Context)localObject2);
    ((RecyclerView)_$_findCachedViewById(R.id.savedRoutesList)).setHasFixedSize(true);
    localObject2 = (RecyclerView)_$_findCachedViewById(R.id.savedRoutesList);
    Intrinsics.checkExpressionValueIsNotNull(localObject2, "savedRoutesList");
    ((RecyclerView)localObject2).setLayoutManager((RecyclerView.LayoutManager)localObject1);
    localObject1 = (RecyclerView)_$_findCachedViewById(R.id.savedRoutesList);
    Intrinsics.checkExpressionValueIsNotNull(localObject1, "savedRoutesList");
    ((RecyclerView)localObject1).setAdapter((RecyclerView.Adapter)new SavedRoutesAdapter());
  }
  
  public boolean onSupportNavigateUp()
  {
    onBackPressed();
    return true;
  }
  
  public final void setSavedRoutes(@NotNull List<RouteFile> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "<set-?>");
    savedRoutes = paramList;
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000.\n\002\030\002\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020\b\n\000\n\002\020\002\n\002\b\004\n\002\030\002\n\002\b\003\b\004\030\0002\b\022\004\022\0020\0020\001:\001\024B\005¢\006\002\020\003J\b\020\n\032\0020\013H\026J\030\020\f\032\0020\r2\006\020\016\032\0020\0022\006\020\017\032\0020\013H\026J\030\020\020\032\0020\0022\006\020\021\032\0020\0222\006\020\023\032\0020\013H\026R\033\020\004\032\0020\0058FX\002¢\006\f\n\004\b\b\020\t\032\004\b\006\020\007¨\006\025"}, d2={"Lcom/byimplication/sakay/SavedRoutes$SavedRoutesAdapter;", "Landroid/support/v7/widget/RecyclerView$Adapter;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "(Lcom/byimplication/sakay/SavedRoutes;)V", "lastModifiedFormatter", "Ljava/text/SimpleDateFormat;", "getLastModifiedFormatter", "()Ljava/text/SimpleDateFormat;", "lastModifiedFormatter$delegate", "Lkotlin/Lazy;", "getItemCount", "", "onBindViewHolder", "", "holder", "position", "onCreateViewHolder", "parent", "Landroid/view/ViewGroup;", "viewType", "SavedRoutesViewHolder", "app_release"}, k=1, mv={1, 1, 9})
  public final class SavedRoutesAdapter
    extends RecyclerView.Adapter<RecyclerView.ViewHolder>
  {
    @NotNull
    private final Lazy lastModifiedFormatter$delegate = LazyKt.lazy((Function0)lastModifiedFormatter.2.INSTANCE);
    
    public SavedRoutesAdapter() {}
    
    public int getItemCount()
    {
      return this$0.getSavedRoutes().size();
    }
    
    @NotNull
    public final SimpleDateFormat getLastModifiedFormatter()
    {
      Lazy localLazy = lastModifiedFormatter$delegate;
      KProperty localKProperty = $$delegatedProperties[0];
      return (SimpleDateFormat)localLazy.getValue();
    }
    
    public void onBindViewHolder(@NotNull RecyclerView.ViewHolder paramViewHolder, int paramInt)
    {
      Intrinsics.checkParameterIsNotNull(paramViewHolder, "holder");
      if ((paramViewHolder instanceof SavedRoutesViewHolder))
      {
        final RouteFile localRouteFile = (RouteFile)this$0.getSavedRoutes().get(paramInt);
        Object localObject1;
        if (((RouteFile)this$0.getSavedRoutes().get(paramInt)).getRouteName().length() > 0)
        {
          localObject1 = (SavedRoutesViewHolder)paramViewHolder;
          ((SavedRoutesViewHolder)localObject1).getSavedNameText().setVisibility(0);
          ((SavedRoutesViewHolder)localObject1).getSavedNameText().setText((CharSequence)localRouteFile.getRouteName());
        }
        else
        {
          ((SavedRoutesViewHolder)paramViewHolder).getSavedNameText().setVisibility(8);
        }
        long l = localRouteFile.getSavedOn();
        Object localObject2;
        if (l > 0L)
        {
          localObject1 = getLastModifiedFormatter().format(Long.valueOf(l));
          localObject2 = this$0.getString(2131689756);
          Intrinsics.checkExpressionValueIsNotNull(localObject2, "getString(R.string.saved_on_blank)");
          Intrinsics.checkExpressionValueIsNotNull(localObject1, "lastModifiedText");
          localObject2 = SpannableString.valueOf((CharSequence)StringsKt.replace$default((String)localObject2, "*blank*", (String)localObject1, false, 4, null));
          Intrinsics.checkExpressionValueIsNotNull(localObject2, "SpannableString.valueOf(this)");
          localObject2 = (Spannable)localObject2;
          ((Spannable)localObject2).setSpan(new ForegroundColorSpan(ContextCompat.getColor((Context)this$0, 2131099770)), ((Spannable)localObject2).length() - ((String)localObject1).length(), ((Spannable)localObject2).length(), 33);
          localObject1 = (SavedRoutesViewHolder)paramViewHolder;
          ((SavedRoutesViewHolder)localObject1).getSavedOnText().setText((CharSequence)localObject2);
          ((SavedRoutesViewHolder)localObject1).getSavedOnText().setVisibility(0);
        }
        else
        {
          ((SavedRoutesViewHolder)paramViewHolder).getSavedOnText().setVisibility(8);
        }
        if (localRouteFile.getDepartureTime() != null)
        {
          localObject2 = (SavedRoutesViewHolder)paramViewHolder;
          ((SavedRoutesViewHolder)localObject2).getSavedSchedText().setVisibility(0);
          localObject1 = new SimpleDateFormat("h:mm a (EEE)", Locale.getDefault());
          if (Intrinsics.areEqual(localRouteFile.getScheduleType(), ScheduleType.DEPARTURE))
          {
            str1 = this$0.getString(2131689635);
            Intrinsics.checkExpressionValueIsNotNull(str1, "getString(R.string.leave_by_long)");
            localObject1 = ((SimpleDateFormat)localObject1).format(localRouteFile.getDepartureTime());
            Intrinsics.checkExpressionValueIsNotNull(localObject1, "sdf.format(savedData.departureTime)");
            localObject1 = StringsKt.replace$default(str1, "*time*", (String)localObject1, false, 4, null);
          }
          else
          {
            str1 = this$0.getString(2131689522);
            Intrinsics.checkExpressionValueIsNotNull(str1, "getString(R.string.arrive_by_long)");
            localObject1 = ((SimpleDateFormat)localObject1).format(localRouteFile.getDepartureTime());
            Intrinsics.checkExpressionValueIsNotNull(localObject1, "sdf.format(savedData.departureTime)");
            localObject1 = StringsKt.replace$default(str1, "*time*", (String)localObject1, false, 4, null);
          }
          String str1 = this$0.getString(2131689751);
          String str2 = this$0.getString(2131689614);
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(str1);
          localStringBuilder.append(" ");
          localStringBuilder.append((String)localObject1);
          localStringBuilder.append(" ");
          localStringBuilder.append(str2);
          localObject1 = SpannableString.valueOf((CharSequence)localStringBuilder.toString());
          Intrinsics.checkExpressionValueIsNotNull(localObject1, "SpannableString.valueOf(this)");
          localObject1 = (Spannable)localObject1;
          ((Spannable)localObject1).setSpan(new ForegroundColorSpan(ContextCompat.getColor((Context)this$0, 2131099770)), str1.length(), ((Spannable)localObject1).length() - str2.length(), 33);
          ((SavedRoutesViewHolder)localObject2).getSavedSchedText().setText((CharSequence)localObject1);
        }
        else
        {
          ((SavedRoutesViewHolder)paramViewHolder).getSavedSchedText().setVisibility(8);
        }
        paramViewHolder = (SavedRoutesViewHolder)paramViewHolder;
        paramViewHolder.getSavedFromText().setText((CharSequence)localRouteFile.getOriginAlias());
        paramViewHolder.getSavedToText().setText((CharSequence)localRouteFile.getDestinationAlias());
        paramViewHolder.getDeleteImage().setOnClickListener((View.OnClickListener)new View.OnClickListener()
        {
          public final void onClick(View paramAnonymousView)
          {
            paramAnonymousView = new AlertDialog.Builder((Context)this$0.this$0);
            paramAnonymousView.setTitle(2131689586);
            paramAnonymousView.setMessage(2131689518);
            paramAnonymousView.setPositiveButton(2131689856, (DialogInterface.OnClickListener)new DialogInterface.OnClickListener()
            {
              public final void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
              {
                LocalStorage.INSTANCE.deleteFile((Context)this$0.this$0.this$0, this$0.$savedData.getId());
                this$0.this$0.this$0.setSavedRoutes(LocalStorage.INSTANCE.loadSavedRoutesList((Context)this$0.this$0.this$0));
                this$0.this$0.notifyDataSetChanged();
              }
            });
            paramAnonymousView.setNegativeButton(2131689703, (DialogInterface.OnClickListener)2.INSTANCE);
            paramAnonymousView.create().show();
          }
        });
        paramViewHolder.getMView().setOnClickListener((View.OnClickListener)new View.OnClickListener()
        {
          public final void onClick(View paramAnonymousView)
          {
            paramAnonymousView = new Intent();
            paramAnonymousView.putExtra("id", localRouteFile.getId());
            this$0.this$0.setResult(-1, paramAnonymousView);
            this$0.this$0.finish();
          }
        });
      }
    }
    
    @NotNull
    public RecyclerView.ViewHolder onCreateViewHolder(@NotNull ViewGroup paramViewGroup, int paramInt)
    {
      Intrinsics.checkParameterIsNotNull(paramViewGroup, "parent");
      paramViewGroup = LayoutInflater.from(paramViewGroup.getContext()).inflate(2131427450, paramViewGroup, false);
      Intrinsics.checkExpressionValueIsNotNull(paramViewGroup, "savedRouteCard");
      return (RecyclerView.ViewHolder)new SavedRoutesViewHolder(paramViewGroup);
    }
    
    @Metadata(bv={1, 0, 2}, d1={"\000\"\n\002\030\002\n\002\030\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\002\b\013\b\004\030\0002\0020\001B\r\022\006\020\002\032\0020\003¢\006\002\020\004R\021\020\005\032\0020\006¢\006\b\n\000\032\004\b\007\020\bR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\t\020\nR\021\020\013\032\0020\f¢\006\b\n\000\032\004\b\r\020\016R\021\020\017\032\0020\f¢\006\b\n\000\032\004\b\020\020\016R\021\020\021\032\0020\f¢\006\b\n\000\032\004\b\022\020\016R\021\020\023\032\0020\f¢\006\b\n\000\032\004\b\024\020\016R\021\020\025\032\0020\f¢\006\b\n\000\032\004\b\026\020\016¨\006\027"}, d2={"Lcom/byimplication/sakay/SavedRoutes$SavedRoutesAdapter$SavedRoutesViewHolder;", "Landroid/support/v7/widget/RecyclerView$ViewHolder;", "mView", "Landroid/view/View;", "(Lcom/byimplication/sakay/SavedRoutes$SavedRoutesAdapter;Landroid/view/View;)V", "deleteImage", "Landroid/widget/ImageView;", "getDeleteImage", "()Landroid/widget/ImageView;", "getMView", "()Landroid/view/View;", "savedFromText", "Landroid/widget/TextView;", "getSavedFromText", "()Landroid/widget/TextView;", "savedNameText", "getSavedNameText", "savedOnText", "getSavedOnText", "savedSchedText", "getSavedSchedText", "savedToText", "getSavedToText", "app_release"}, k=1, mv={1, 1, 9})
    public final class SavedRoutesViewHolder
      extends RecyclerView.ViewHolder
    {
      @NotNull
      private final ImageView deleteImage;
      @NotNull
      private final View mView;
      @NotNull
      private final TextView savedFromText;
      @NotNull
      private final TextView savedNameText;
      @NotNull
      private final TextView savedOnText;
      @NotNull
      private final TextView savedSchedText;
      @NotNull
      private final TextView savedToText;
      
      public SavedRoutesViewHolder()
      {
        super();
        mView = localObject;
        this$1 = mView.findViewById(2131296633);
        Intrinsics.checkExpressionValueIsNotNull(SavedRoutes.SavedRoutesAdapter.this, "mView.findViewById(R.id.savedNameText)");
        savedNameText = ((TextView)SavedRoutes.SavedRoutesAdapter.this);
        this$1 = mView.findViewById(2131296634);
        Intrinsics.checkExpressionValueIsNotNull(SavedRoutes.SavedRoutesAdapter.this, "mView.findViewById(R.id.savedOnText)");
        savedOnText = ((TextView)SavedRoutes.SavedRoutesAdapter.this);
        this$1 = mView.findViewById(2131296637);
        Intrinsics.checkExpressionValueIsNotNull(SavedRoutes.SavedRoutesAdapter.this, "mView.findViewById(R.id.savedSchedText)");
        savedSchedText = ((TextView)SavedRoutes.SavedRoutesAdapter.this);
        this$1 = mView.findViewById(2131296632);
        Intrinsics.checkExpressionValueIsNotNull(SavedRoutes.SavedRoutesAdapter.this, "mView.findViewById(R.id.savedFromText)");
        savedFromText = ((TextView)SavedRoutes.SavedRoutesAdapter.this);
        this$1 = mView.findViewById(2131296638);
        Intrinsics.checkExpressionValueIsNotNull(SavedRoutes.SavedRoutesAdapter.this, "mView.findViewById(R.id.savedToText)");
        savedToText = ((TextView)SavedRoutes.SavedRoutesAdapter.this);
        this$1 = mView.findViewById(2131296374);
        Intrinsics.checkExpressionValueIsNotNull(SavedRoutes.SavedRoutesAdapter.this, "mView.findViewById(R.id.deleteImage)");
        deleteImage = ((ImageView)SavedRoutes.SavedRoutesAdapter.this);
      }
      
      @NotNull
      public final ImageView getDeleteImage()
      {
        return deleteImage;
      }
      
      @NotNull
      public final View getMView()
      {
        return mView;
      }
      
      @NotNull
      public final TextView getSavedFromText()
      {
        return savedFromText;
      }
      
      @NotNull
      public final TextView getSavedNameText()
      {
        return savedNameText;
      }
      
      @NotNull
      public final TextView getSavedOnText()
      {
        return savedOnText;
      }
      
      @NotNull
      public final TextView getSavedSchedText()
      {
        return savedSchedText;
      }
      
      @NotNull
      public final TextView getSavedToText()
      {
        return savedToText;
      }
    }
  }
}

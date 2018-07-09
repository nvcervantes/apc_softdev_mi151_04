package com.byimplication.sakay;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.BaseAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;
import com.byimplication.sakay.model.Announcement;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.CollectionsKt;
import kotlin.collections.MapsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000>\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020 \n\002\030\002\n\002\b\005\n\002\020$\n\002\020\b\n\002\020\013\n\002\b\005\n\002\020\002\n\000\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\030\0002\0020\001:\002\033\034B\005¢\006\002\020\002J\022\020\022\032\0020\0232\b\020\024\032\004\030\0010\025H\024J\022\020\026\032\0020\0232\b\020\027\032\004\030\0010\030H\024J\b\020\031\032\0020\023H\024J\b\020\032\032\0020\rH\026R \020\003\032\b\022\004\022\0020\0050\004X\016¢\006\016\n\000\032\004\b\006\020\007\"\004\b\b\020\tR&\020\n\032\016\022\004\022\0020\f\022\004\022\0020\r0\013X\016¢\006\016\n\000\032\004\b\016\020\017\"\004\b\020\020\021¨\006\035"}, d2={"Lcom/byimplication/sakay/AnnouncementsActivity;", "Landroid/support/v7/app/AppCompatActivity;", "()V", "announcements", "", "Lcom/byimplication/sakay/model/Announcement;", "getAnnouncements", "()Ljava/util/List;", "setAnnouncements", "(Ljava/util/List;)V", "announcementsRead", "", "", "", "getAnnouncementsRead", "()Ljava/util/Map;", "setAnnouncementsRead", "(Ljava/util/Map;)V", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onNewIntent", "intent", "Landroid/content/Intent;", "onStart", "onSupportNavigateUp", "AnnouncementHolder", "AnnouncementsAdapter", "app_release"}, k=1, mv={1, 1, 9})
public final class AnnouncementsActivity
  extends AppCompatActivity
{
  private HashMap _$_findViewCache;
  @NotNull
  private List<Announcement> announcements = CollectionsKt.emptyList();
  @NotNull
  private Map<Integer, Boolean> announcementsRead = MapsKt.emptyMap();
  
  public AnnouncementsActivity() {}
  
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
  public final List<Announcement> getAnnouncements()
  {
    return announcements;
  }
  
  @NotNull
  public final Map<Integer, Boolean> getAnnouncementsRead()
  {
    return announcementsRead;
  }
  
  protected void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    setContentView(2131427356);
    setSupportActionBar((Toolbar)_$_findCachedViewById(R.id.toolbar));
    paramBundle = getSupportActionBar();
    if (paramBundle != null) {
      paramBundle.setDisplayHomeAsUpEnabled(true);
    }
    announcements = LocalStorage.INSTANCE.loadAnnouncements((Context)this);
  }
  
  protected void onNewIntent(@Nullable Intent paramIntent)
  {
    super.onNewIntent(paramIntent);
    setIntent(paramIntent);
  }
  
  protected void onStart()
  {
    super.onStart();
    if ((((Collection)announcements).isEmpty() ^ true))
    {
      announcementsRead = LocalStorage.INSTANCE.loadAnnouncementsRead((Context)this);
      ListView localListView = (ListView)_$_findCachedViewById(R.id.announceList);
      Intrinsics.checkExpressionValueIsNotNull(localListView, "announceList");
      localListView.setAdapter((ListAdapter)new AnnouncementsAdapter());
      ((ListView)_$_findCachedViewById(R.id.announceList)).setOnItemClickListener((AdapterView.OnItemClickListener)new AdapterView.OnItemClickListener()
      {
        public final void onItemClick(AdapterView<?> paramAnonymousAdapterView, View paramAnonymousView, int paramAnonymousInt, long paramAnonymousLong)
        {
          paramAnonymousAdapterView = (ListView)this$0._$_findCachedViewById(R.id.announceList);
          Intrinsics.checkExpressionValueIsNotNull(paramAnonymousAdapterView, "announceList");
          paramAnonymousAdapterView = paramAnonymousAdapterView.getAdapter();
          if (paramAnonymousAdapterView == null) {
            throw new TypeCastException("null cannot be cast to non-null type com.byimplication.sakay.AnnouncementsActivity.AnnouncementsAdapter");
          }
          paramAnonymousAdapterView = ((AnnouncementsActivity.AnnouncementsAdapter)paramAnonymousAdapterView).getItem(paramAnonymousInt);
          this$0.setAnnouncementsRead(MapsKt.plus(this$0.getAnnouncementsRead(), new Pair(Integer.valueOf(paramAnonymousAdapterView.getId()), Boolean.valueOf(true))));
          LocalStorage.INSTANCE.saveAnnouncementsRead((Context)this$0, this$0.getAnnouncementsRead());
          paramAnonymousView = new Intent((Context)this$0, ViewAnnouncement.class);
          paramAnonymousView.putExtra("announcement", (Parcelable)paramAnonymousAdapterView);
          this$0.startActivity(paramAnonymousView);
        }
      });
    }
  }
  
  public boolean onSupportNavigateUp()
  {
    onBackPressed();
    return true;
  }
  
  public final void setAnnouncements(@NotNull List<Announcement> paramList)
  {
    Intrinsics.checkParameterIsNotNull(paramList, "<set-?>");
    announcements = paramList;
  }
  
  public final void setAnnouncementsRead(@NotNull Map<Integer, Boolean> paramMap)
  {
    Intrinsics.checkParameterIsNotNull(paramMap, "<set-?>");
    announcementsRead = paramMap;
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\007\030\0002\0020\001B\035\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\005¢\006\002\020\007R\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\002\020\bR\021\020\006\032\0020\005¢\006\b\n\000\032\004\b\t\020\nR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\013\020\n¨\006\f"}, d2={"Lcom/byimplication/sakay/AnnouncementsActivity$AnnouncementHolder;", "", "isNewDot", "Landroid/view/View;", "titleText", "Landroid/widget/TextView;", "subtitleText", "(Landroid/view/View;Landroid/widget/TextView;Landroid/widget/TextView;)V", "()Landroid/view/View;", "getSubtitleText", "()Landroid/widget/TextView;", "getTitleText", "app_release"}, k=1, mv={1, 1, 9})
  public static final class AnnouncementHolder
  {
    @NotNull
    private final View isNewDot;
    @NotNull
    private final TextView subtitleText;
    @NotNull
    private final TextView titleText;
    
    public AnnouncementHolder(@NotNull View paramView, @NotNull TextView paramTextView1, @NotNull TextView paramTextView2)
    {
      isNewDot = paramView;
      titleText = paramTextView1;
      subtitleText = paramTextView2;
    }
    
    @NotNull
    public final TextView getSubtitleText()
    {
      return subtitleText;
    }
    
    @NotNull
    public final TextView getTitleText()
    {
      return titleText;
    }
    
    @NotNull
    public final View isNewDot()
    {
      return isNewDot;
    }
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000.\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\b\n\000\n\002\030\002\n\002\b\002\n\002\020\t\n\000\n\002\030\002\n\002\b\003\n\002\030\002\n\000\b\004\030\0002\0020\001B\005¢\006\002\020\002J\b\020\003\032\0020\004H\026J\020\020\005\032\0020\0062\006\020\007\032\0020\004H\026J\020\020\b\032\0020\t2\006\020\007\032\0020\004H\026J\"\020\n\032\0020\0132\006\020\f\032\0020\0042\b\020\r\032\004\030\0010\0132\006\020\016\032\0020\017H\026¨\006\020"}, d2={"Lcom/byimplication/sakay/AnnouncementsActivity$AnnouncementsAdapter;", "Landroid/widget/BaseAdapter;", "(Lcom/byimplication/sakay/AnnouncementsActivity;)V", "getCount", "", "getItem", "Lcom/byimplication/sakay/model/Announcement;", "itemNum", "getItemId", "", "getView", "Landroid/view/View;", "position", "convertView", "viewGroup", "Landroid/view/ViewGroup;", "app_release"}, k=1, mv={1, 1, 9})
  public final class AnnouncementsAdapter
    extends BaseAdapter
  {
    public AnnouncementsAdapter() {}
    
    public int getCount()
    {
      return this$0.getAnnouncements().size();
    }
    
    @NotNull
    public Announcement getItem(int paramInt)
    {
      return (Announcement)this$0.getAnnouncements().get(paramInt);
    }
    
    public long getItemId(int paramInt)
    {
      return ((Announcement)this$0.getAnnouncements().get(paramInt)).getId();
    }
    
    @NotNull
    public View getView(int paramInt, @Nullable View paramView, @NotNull ViewGroup paramViewGroup)
    {
      Intrinsics.checkParameterIsNotNull(paramViewGroup, "viewGroup");
      if (paramView == null)
      {
        paramViewGroup = this$0.getLayoutInflater().inflate(2131427365, null);
        Intrinsics.checkExpressionValueIsNotNull(paramViewGroup, "layoutInflater.inflate(R…announcement_entry, null)");
      }
      else
      {
        paramViewGroup = paramView;
      }
      Object localObject1;
      if (paramView == null)
      {
        localObject1 = paramViewGroup.findViewById(2131296475);
        Intrinsics.checkExpressionValueIsNotNull(localObject1, "theView.findViewById(R.id.isNewImage)");
        localObject2 = paramViewGroup.findViewById(2131296310);
        Intrinsics.checkExpressionValueIsNotNull(localObject2, "theView.findViewById(R.id.announceTitle)");
        localObject2 = (TextView)localObject2;
        View localView = paramViewGroup.findViewById(2131296309);
        Intrinsics.checkExpressionValueIsNotNull(localView, "theView.findViewById(R.id.announceSubtitle)");
        localObject1 = new AnnouncementsActivity.AnnouncementHolder((View)localObject1, (TextView)localObject2, (TextView)localView);
      }
      else
      {
        localObject1 = paramView.getTag();
        if (localObject1 == null) {
          throw new TypeCastException("null cannot be cast to non-null type com.byimplication.sakay.AnnouncementsActivity.AnnouncementHolder");
        }
        localObject1 = (AnnouncementsActivity.AnnouncementHolder)localObject1;
      }
      if (paramView == null) {
        paramViewGroup.setTag(localObject1);
      }
      paramView = getItem(paramInt);
      Object localObject2 = (Boolean)this$0.getAnnouncementsRead().get(Integer.valueOf(paramView.getId()));
      if ((localObject2 != null) && (((Boolean)localObject2).booleanValue())) {
        ((AnnouncementsActivity.AnnouncementHolder)localObject1).isNewDot().setVisibility(8);
      } else {
        ((AnnouncementsActivity.AnnouncementHolder)localObject1).isNewDot().setVisibility(0);
      }
      ((AnnouncementsActivity.AnnouncementHolder)localObject1).getTitleText().setText((CharSequence)paramView.getTitle());
      paramView = new Date(paramView.getTimestamp() * 'Ϩ');
      localObject2 = new SimpleDateFormat("MMMM d, yyyy", Locale.getDefault());
      ((AnnouncementsActivity.AnnouncementHolder)localObject1).getSubtitleText().setText((CharSequence)((SimpleDateFormat)localObject2).format(paramView));
      return paramViewGroup;
    }
  }
}

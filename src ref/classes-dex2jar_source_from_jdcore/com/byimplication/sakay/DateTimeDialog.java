package com.byimplication.sakay;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.Shape;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.design.widget.TabLayout.OnTabSelectedListener;
import android.support.design.widget.TabLayout.Tab;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.TimePicker.OnTimeChangedListener;
import com.byimplication.sakay.action.OnEvent;
import com.byimplication.sakay.action.OnSimpleEvent;
import com.byimplication.sakay.action.SearchAgain;
import com.byimplication.sakay.action.SetPlanTime;
import com.byimplication.sakay.model.ScheduleType;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Locale;
import kotlin.Lazy;
import kotlin.LazyKt;
import kotlin.Metadata;
import kotlin.Pair;
import kotlin.TypeCastException;
import kotlin.collections.MapsKt;
import kotlin.jvm.functions.Function0;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Lambda;
import kotlin.reflect.KProperty;
import kotlin.text.StringsKt;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000^\n\002\030\002\n\002\030\002\n\002\b\002\n\002\020\013\n\002\b\002\n\002\030\002\n\000\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\020\b\n\000\n\002\020\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\013\030\000 .2\0020\001:\001.B\005¢\006\002\020\002J\b\020\022\032\0020\023H\002J\b\020\024\032\0020\023H\002J\020\020\025\032\0020\0262\006\020\027\032\0020\021H\002J\032\020\030\032\0020\0232\b\020\031\032\004\030\0010\0262\006\020\027\032\0020\021H\002J\022\020\032\032\0020\0232\b\020\033\032\004\030\0010\034H\026J\022\020\035\032\0020\0362\b\020\033\032\004\030\0010\034H\026J&\020\037\032\004\030\0010 2\006\020!\032\0020\"2\b\020#\032\004\030\0010$2\b\020\033\032\004\030\0010\034H\026J\b\020%\032\0020\023H\026J\020\020&\032\0020\0232\006\020'\032\0020\034H\026J\032\020(\032\0020\0232\006\020)\032\0020 2\b\020\033\032\004\030\0010\034H\026J\032\020*\032\0020\0232\b\020\031\032\004\030\0010\0262\006\020\027\032\0020\021H\002J\b\020+\032\0020\023H\002J\b\020,\032\0020\023H\002J\b\020-\032\0020\023H\002R\016\020\003\032\0020\004X\016¢\006\002\n\000R\016\020\005\032\0020\004X\016¢\006\002\n\000R\020\020\006\032\004\030\0010\007X\016¢\006\002\n\000R\033\020\b\032\0020\t8BX\002¢\006\f\n\004\b\f\020\r\032\004\b\n\020\013R\016\020\016\032\0020\017X\016¢\006\002\n\000R\016\020\020\032\0020\021X\016¢\006\002\n\000¨\006/"}, d2={"Lcom/byimplication/sakay/DateTimeDialog;", "Landroid/support/v4/app/DialogFragment;", "()V", "hasPlanDate", "", "isLeavingNow", "planDate", "Ljava/util/Date;", "planTimeDateFormat", "Ljava/text/SimpleDateFormat;", "getPlanTimeDateFormat", "()Ljava/text/SimpleDateFormat;", "planTimeDateFormat$delegate", "Lkotlin/Lazy;", "scheduleType", "Lcom/byimplication/sakay/model/ScheduleType;", "selectedDay", "", "disableDateTimeUi", "", "enableDateTimeUi", "getDayOfWeekView", "Landroid/widget/TextView;", "dowNum", "highlightDayOfWeek", "textView", "onCreate", "savedInstanceState", "Landroid/os/Bundle;", "onCreateDialog", "Landroid/app/Dialog;", "onCreateView", "Landroid/view/View;", "inflater", "Landroid/view/LayoutInflater;", "container", "Landroid/view/ViewGroup;", "onResume", "onSaveInstanceState", "outState", "onViewCreated", "view", "selectDayOfWeek", "setToCurrentTime", "unselectDayOfWeeks", "updateDateInfo", "Companion", "app_release"}, k=1, mv={1, 1, 9})
public final class DateTimeDialog
  extends DialogFragment
{
  public static final Companion Companion = new Companion(null);
  @NotNull
  private static final String HASPLANDATE = "hasPlanDate";
  @NotNull
  private static final String ISARRIVAL = "isArrival";
  @NotNull
  private static final String PLANDATEKEY = "planDATE";
  @NotNull
  private static final String PLANTIMEPARSEFORMAT = "MM-dd-YYYY";
  private HashMap _$_findViewCache;
  private boolean hasPlanDate;
  private boolean isLeavingNow;
  private Date planDate;
  private final Lazy planTimeDateFormat$delegate = LazyKt.lazy((Function0)planTimeDateFormat.2.INSTANCE);
  private ScheduleType scheduleType = ScheduleType.DEPARTURE;
  private int selectedDay = 2;
  
  public DateTimeDialog() {}
  
  private final void disableDateTimeUi()
  {
    Object localObject = getContext();
    if (localObject == null) {
      Intrinsics.throwNpe();
    }
    int i = ContextCompat.getColor((Context)localObject, 2131099730);
    localObject = (TimePicker)_$_findCachedViewById(R.id.timePicker);
    if (localObject != null) {
      ((TimePicker)localObject).setEnabled(false);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.monSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(false);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.monSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.tueSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(false);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.tueSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.wedSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(false);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.wedSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.thuSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(false);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.thuSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.friSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(false);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.friSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.satSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(false);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.satSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.sunSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(false);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.sunSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
  }
  
  private final void enableDateTimeUi()
  {
    Object localObject = getContext();
    if (localObject == null) {
      Intrinsics.throwNpe();
    }
    int i = ContextCompat.getColor((Context)localObject, 2131099724);
    localObject = (TimePicker)_$_findCachedViewById(R.id.timePicker);
    if (localObject != null) {
      ((TimePicker)localObject).setEnabled(true);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.monSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.monSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(true);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.tueSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.tueSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(true);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.wedSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.wedSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(true);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.thuSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.thuSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(true);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.friSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.friSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(true);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.satSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.satSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(true);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.sunSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.sunSelect);
    if (localObject != null) {
      ((TextView)localObject).setEnabled(true);
    }
  }
  
  private final TextView getDayOfWeekView(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      localTextView = (TextView)_$_findCachedViewById(R.id.sunSelect);
      Intrinsics.checkExpressionValueIsNotNull(localTextView, "sunSelect");
      return localTextView;
    case 7: 
      localTextView = (TextView)_$_findCachedViewById(R.id.satSelect);
      Intrinsics.checkExpressionValueIsNotNull(localTextView, "satSelect");
      return localTextView;
    case 6: 
      localTextView = (TextView)_$_findCachedViewById(R.id.friSelect);
      Intrinsics.checkExpressionValueIsNotNull(localTextView, "friSelect");
      return localTextView;
    case 5: 
      localTextView = (TextView)_$_findCachedViewById(R.id.thuSelect);
      Intrinsics.checkExpressionValueIsNotNull(localTextView, "thuSelect");
      return localTextView;
    case 4: 
      localTextView = (TextView)_$_findCachedViewById(R.id.wedSelect);
      Intrinsics.checkExpressionValueIsNotNull(localTextView, "wedSelect");
      return localTextView;
    case 3: 
      localTextView = (TextView)_$_findCachedViewById(R.id.tueSelect);
      Intrinsics.checkExpressionValueIsNotNull(localTextView, "tueSelect");
      return localTextView;
    }
    TextView localTextView = (TextView)_$_findCachedViewById(R.id.monSelect);
    Intrinsics.checkExpressionValueIsNotNull(localTextView, "monSelect");
    return localTextView;
  }
  
  private final SimpleDateFormat getPlanTimeDateFormat()
  {
    Lazy localLazy = planTimeDateFormat$delegate;
    KProperty localKProperty = $$delegatedProperties[0];
    return (SimpleDateFormat)localLazy.getValue();
  }
  
  private final void highlightDayOfWeek(TextView paramTextView, int paramInt)
  {
    ShapeDrawable localShapeDrawable = new ShapeDrawable((Shape)new OvalShape());
    Paint localPaint = localShapeDrawable.getPaint();
    Intrinsics.checkExpressionValueIsNotNull(localPaint, "selectedShape.paint");
    Context localContext = getContext();
    if (localContext == null) {
      Intrinsics.throwNpe();
    }
    localPaint.setColor(ContextCompat.getColor(localContext, 2131099692));
    localPaint = localShapeDrawable.getPaint();
    Intrinsics.checkExpressionValueIsNotNull(localPaint, "selectedShape.paint");
    localPaint.setAntiAlias(true);
    if (paramTextView != null) {
      paramTextView.setBackground((Drawable)localShapeDrawable);
    }
    if (paramTextView != null) {
      paramTextView.setTextColor(-1);
    }
  }
  
  private final void selectDayOfWeek(TextView paramTextView, int paramInt)
  {
    unselectDayOfWeeks();
    highlightDayOfWeek(paramTextView, paramInt);
    selectedDay = paramInt;
    updateDateInfo();
  }
  
  private final void setToCurrentTime()
  {
    Object localObject1 = Calendar.getInstance();
    Intrinsics.checkExpressionValueIsNotNull(localObject1, "calendar");
    ((Calendar)localObject1).setTime(new Date(System.currentTimeMillis()));
    Object localObject2 = (TimePicker)_$_findCachedViewById(R.id.timePicker);
    if (localObject2 != null) {
      ((TimePicker)localObject2).setCurrentHour(Integer.valueOf(((Calendar)localObject1).get(11)));
    }
    localObject2 = (TimePicker)_$_findCachedViewById(R.id.timePicker);
    if (localObject2 != null) {
      ((TimePicker)localObject2).setCurrentMinute(Integer.valueOf(((Calendar)localObject1).get(12)));
    }
    int i = ((Calendar)localObject1).get(7);
    unselectDayOfWeeks();
    disableDateTimeUi();
    highlightDayOfWeek(getDayOfWeekView(i), i);
    selectedDay = i;
    planDate = ((Date)null);
    scheduleType = ScheduleType.DEPARTURE;
    localObject2 = (TextView)_$_findCachedViewById(R.id.planTimeView);
    Intrinsics.checkExpressionValueIsNotNull(localObject2, "planTimeView");
    String str = getString(2131689635);
    Intrinsics.checkExpressionValueIsNotNull(str, "getString(R.string.leave_by_long)");
    localObject1 = getPlanTimeDateFormat().format(((Calendar)localObject1).getTime());
    Intrinsics.checkExpressionValueIsNotNull(localObject1, "planTimeDateFormat.format(calendar.time)");
    ((TextView)localObject2).setText((CharSequence)StringsKt.replace$default(str, "*time*", (String)localObject1, false, 4, null));
  }
  
  private final void unselectDayOfWeeks()
  {
    Object localObject = getContext();
    if (localObject == null) {
      Intrinsics.throwNpe();
    }
    int i = ContextCompat.getColor((Context)localObject, 2131099724);
    localObject = (TextView)_$_findCachedViewById(R.id.monSelect);
    if (localObject != null) {
      ((TextView)localObject).setBackground((Drawable)null);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.monSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.tueSelect);
    if (localObject != null) {
      ((TextView)localObject).setBackground((Drawable)null);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.tueSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.wedSelect);
    if (localObject != null) {
      ((TextView)localObject).setBackground((Drawable)null);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.wedSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.thuSelect);
    if (localObject != null) {
      ((TextView)localObject).setBackground((Drawable)null);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.thuSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.friSelect);
    if (localObject != null) {
      ((TextView)localObject).setBackground((Drawable)null);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.friSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.satSelect);
    if (localObject != null) {
      ((TextView)localObject).setBackground((Drawable)null);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.satSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.sunSelect);
    if (localObject != null) {
      ((TextView)localObject).setBackground((Drawable)null);
    }
    localObject = (TextView)_$_findCachedViewById(R.id.sunSelect);
    if (localObject != null) {
      ((TextView)localObject).setTextColor(i);
    }
  }
  
  private final void updateDateInfo()
  {
    Object localObject1 = Calendar.getInstance();
    Intrinsics.checkExpressionValueIsNotNull(localObject1, "calendar");
    ((Calendar)localObject1).setTime(new Date(System.currentTimeMillis()));
    int j = ((Calendar)localObject1).get(11);
    Object localObject2 = (TimePicker)_$_findCachedViewById(R.id.timePicker);
    Intrinsics.checkExpressionValueIsNotNull(localObject2, "timePicker");
    localObject2 = ((TimePicker)localObject2).getCurrentHour();
    int i;
    if (localObject2 != null) {
      i = ((Integer)localObject2).intValue();
    } else {
      i = j;
    }
    int n = i - j;
    ((Calendar)localObject1).set(11, i);
    int k = ((Calendar)localObject1).get(12);
    localObject2 = (TimePicker)_$_findCachedViewById(R.id.timePicker);
    Intrinsics.checkExpressionValueIsNotNull(localObject2, "timePicker");
    localObject2 = ((TimePicker)localObject2).getCurrentMinute();
    if (localObject2 != null) {
      i = ((Integer)localObject2).intValue();
    } else {
      i = k;
    }
    ((Calendar)localObject1).set(12, i);
    j = ((Calendar)localObject1).get(7);
    int m = selectedDay - j;
    j = m;
    if (m < 0) {
      j = m + 7;
    }
    ((Calendar)localObject1).add(7, j);
    if ((j == 0) && ((n < 0) || ((n == 0) && (i - k < 0)))) {
      ((Calendar)localObject1).add(7, 7);
    }
    planDate = ((Calendar)localObject1).getTime();
    localObject1 = (TextView)_$_findCachedViewById(R.id.planTimeView);
    Intrinsics.checkExpressionValueIsNotNull(localObject1, "planTimeView");
    if (Intrinsics.areEqual(scheduleType, ScheduleType.DEPARTURE)) {
      i = 2131689635;
    } else {
      i = 2131689522;
    }
    localObject2 = getString(i);
    Intrinsics.checkExpressionValueIsNotNull(localObject2, "getString(if (scheduleTy… R.string.arrive_by_long)");
    String str = getPlanTimeDateFormat().format(planDate);
    Intrinsics.checkExpressionValueIsNotNull(str, "planTimeDateFormat.format(planDate)");
    ((TextView)localObject1).setText((CharSequence)StringsKt.replace$default((String)localObject2, "*time*", str, false, 4, null));
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
  
  public void onCreate(@Nullable Bundle paramBundle)
  {
    super.onCreate(paramBundle);
    if (paramBundle == null) {
      paramBundle = getArguments();
    }
    if ((paramBundle != null) && (paramBundle.getBoolean(Companion.getHASPLANDATE(), false)))
    {
      isLeavingNow = false;
      planDate = SimpleDateFormat.getDateTimeInstance().parse(paramBundle.getString(Companion.getPLANDATEKEY()));
      if (paramBundle.getBoolean(Companion.getISARRIVAL())) {
        paramBundle = ScheduleType.ARRIVAL;
      } else {
        paramBundle = ScheduleType.DEPARTURE;
      }
      scheduleType = paramBundle;
      return;
    }
    isLeavingNow = true;
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
    return paramLayoutInflater.inflate(2131427399, paramViewGroup, false);
  }
  
  public void onResume()
  {
    super.onResume();
    if (planDate != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("DateTimeDialog Open plantime is ");
      Date localDate = planDate;
      if (localDate == null) {
        Intrinsics.throwNpe();
      }
      ((StringBuilder)localObject).append(localDate.toString());
      localObject = ((StringBuilder)localObject).toString();
    }
    else
    {
      localObject = "DateTimeDialog Open plantime is null";
    }
    Log.d("SAKAY", (String)localObject);
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("DateTimeDialog Open ");
    ((StringBuilder)localObject).append(String.valueOf(isLeavingNow));
    Log.d("SAKAY", ((StringBuilder)localObject).toString());
    if (isLeavingNow)
    {
      localObject = ((TabLayout)_$_findCachedViewById(R.id.scheduleTabBar)).getTabAt(2);
      if (localObject != null) {
        ((TabLayout.Tab)localObject).select();
      }
    }
    else if (Intrinsics.areEqual(scheduleType, ScheduleType.ARRIVAL))
    {
      localObject = ((TabLayout)_$_findCachedViewById(R.id.scheduleTabBar)).getTabAt(1);
      if (localObject != null) {
        ((TabLayout.Tab)localObject).select();
      }
    }
    else
    {
      localObject = ((TabLayout)_$_findCachedViewById(R.id.scheduleTabBar)).getTabAt(0);
      if (localObject != null) {
        ((TabLayout.Tab)localObject).select();
      }
    }
  }
  
  public void onSaveInstanceState(@NotNull Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramBundle, "outState");
    super.onSaveInstanceState(paramBundle);
    if ((planDate != null) && (!isLeavingNow))
    {
      DateFormat localDateFormat = SimpleDateFormat.getDateTimeInstance();
      paramBundle.putBoolean(Companion.getHASPLANDATE(), true);
      paramBundle.putString(Companion.getPLANDATEKEY(), localDateFormat.format(planDate));
      paramBundle.putBoolean(Companion.getISARRIVAL(), Intrinsics.areEqual(scheduleType, ScheduleType.ARRIVAL));
    }
  }
  
  public void onViewCreated(@NotNull View paramView, @Nullable Bundle paramBundle)
  {
    Intrinsics.checkParameterIsNotNull(paramView, "view");
    super.onViewCreated(paramView, paramBundle);
    ((ImageView)_$_findCachedViewById(R.id.dateXButton)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.dismiss();
        new OnSimpleEvent("Schedule Close Button - Click").post();
      }
    });
    ((TextView)_$_findCachedViewById(R.id.cancelDate)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        this$0.dismiss();
        new OnSimpleEvent("Schedule Cancel Button - Click").post();
      }
    });
    ((TextView)_$_findCachedViewById(R.id.doneDate)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        if (DateTimeDialog.access$isLeavingNow$p(this$0))
        {
          new SetPlanTime(null, ScheduleType.DEPARTURE, false, false, 12, null).post();
          new OnEvent("Schedule Done Button - Click", MapsKt.mapOf(new Pair("schedule_type", "NOW"))).post();
        }
        else
        {
          new SetPlanTime(DateTimeDialog.access$getPlanDate$p(this$0), DateTimeDialog.access$getScheduleType$p(this$0), false, false, 12, null).post();
          paramAnonymousView = DateTimeDialog.access$getPlanDate$p(this$0);
          if (paramAnonymousView == null) {
            Intrinsics.throwNpe();
          }
          new OnEvent("Schedule Done Button - Click", MapsKt.mapOf(new Pair[] { new Pair("schedule", paramAnonymousView.toString()), new Pair("schedule_type", DateTimeDialog.access$getScheduleType$p(this$0).name()) })).post();
        }
        new SearchAgain().post();
        this$0.dismiss();
      }
    });
    ((TextView)_$_findCachedViewById(R.id.sunSelect)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        new OnEvent("Schedule Day - Click", MapsKt.mapOf(new Pair("day", "SUNDAY"))).post();
        DateTimeDialog localDateTimeDialog = this$0;
        if (paramAnonymousView == null) {
          throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        DateTimeDialog.access$selectDayOfWeek(localDateTimeDialog, (TextView)paramAnonymousView, 1);
      }
    });
    ((TextView)_$_findCachedViewById(R.id.monSelect)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        new OnEvent("Schedule Day - Click", MapsKt.mapOf(new Pair("day", "MONDAY"))).post();
        DateTimeDialog localDateTimeDialog = this$0;
        if (paramAnonymousView == null) {
          throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        DateTimeDialog.access$selectDayOfWeek(localDateTimeDialog, (TextView)paramAnonymousView, 2);
      }
    });
    ((TextView)_$_findCachedViewById(R.id.tueSelect)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        new OnEvent("Schedule Day - Click", MapsKt.mapOf(new Pair("day", "TUESDAY"))).post();
        DateTimeDialog localDateTimeDialog = this$0;
        if (paramAnonymousView == null) {
          throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        DateTimeDialog.access$selectDayOfWeek(localDateTimeDialog, (TextView)paramAnonymousView, 3);
      }
    });
    ((TextView)_$_findCachedViewById(R.id.wedSelect)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        new OnEvent("Schedule Day - Click", MapsKt.mapOf(new Pair("day", "WEDNESDAY"))).post();
        DateTimeDialog localDateTimeDialog = this$0;
        if (paramAnonymousView == null) {
          throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        DateTimeDialog.access$selectDayOfWeek(localDateTimeDialog, (TextView)paramAnonymousView, 4);
      }
    });
    ((TextView)_$_findCachedViewById(R.id.thuSelect)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        new OnEvent("Schedule Day - Click", MapsKt.mapOf(new Pair("day", "THURSDAY"))).post();
        DateTimeDialog localDateTimeDialog = this$0;
        if (paramAnonymousView == null) {
          throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        DateTimeDialog.access$selectDayOfWeek(localDateTimeDialog, (TextView)paramAnonymousView, 5);
      }
    });
    ((TextView)_$_findCachedViewById(R.id.friSelect)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        new OnEvent("Schedule Day - Click", MapsKt.mapOf(new Pair("day", "FRIDAY"))).post();
        DateTimeDialog localDateTimeDialog = this$0;
        if (paramAnonymousView == null) {
          throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        DateTimeDialog.access$selectDayOfWeek(localDateTimeDialog, (TextView)paramAnonymousView, 6);
      }
    });
    ((TextView)_$_findCachedViewById(R.id.satSelect)).setOnClickListener((View.OnClickListener)new View.OnClickListener()
    {
      public final void onClick(View paramAnonymousView)
      {
        new OnEvent("Schedule Day - Click", MapsKt.mapOf(new Pair("day", "SATURDAY"))).post();
        DateTimeDialog localDateTimeDialog = this$0;
        if (paramAnonymousView == null) {
          throw new TypeCastException("null cannot be cast to non-null type android.widget.TextView");
        }
        DateTimeDialog.access$selectDayOfWeek(localDateTimeDialog, (TextView)paramAnonymousView, 7);
      }
    });
    if (planDate == null) {
      planDate = new Date(System.currentTimeMillis());
    }
    paramView = Calendar.getInstance();
    Intrinsics.checkExpressionValueIsNotNull(paramView, "Calendar.getInstance()");
    if (planDate != null) {
      paramView.setTime(planDate);
    }
    selectedDay = paramView.get(7);
    highlightDayOfWeek(getDayOfWeekView(selectedDay), selectedDay);
    paramBundle = (TextView)_$_findCachedViewById(R.id.planTimeView);
    Intrinsics.checkExpressionValueIsNotNull(paramBundle, "planTimeView");
    int i;
    if (Intrinsics.areEqual(scheduleType, ScheduleType.DEPARTURE)) {
      i = 2131689635;
    } else {
      i = 2131689522;
    }
    String str1 = getString(i);
    Intrinsics.checkExpressionValueIsNotNull(str1, "getString(if (scheduleTy… R.string.arrive_by_long)");
    String str2 = getPlanTimeDateFormat().format(paramView.getTime());
    Intrinsics.checkExpressionValueIsNotNull(str2, "planTimeDateFormat.format(calendar.time)");
    paramBundle.setText((CharSequence)StringsKt.replace$default(str1, "*time*", str2, false, 4, null));
    if (Intrinsics.areEqual(scheduleType, ScheduleType.DEPARTURE))
    {
      paramBundle = ((TabLayout)_$_findCachedViewById(R.id.scheduleTabBar)).getTabAt(0);
      if (paramBundle != null) {
        paramBundle.select();
      }
    }
    else
    {
      paramBundle = ((TabLayout)_$_findCachedViewById(R.id.scheduleTabBar)).getTabAt(1);
      if (paramBundle != null) {
        paramBundle.select();
      }
    }
    ((TabLayout)_$_findCachedViewById(R.id.scheduleTabBar)).addOnTabSelectedListener((TabLayout.OnTabSelectedListener)new TabLayout.OnTabSelectedListener()
    {
      public void onTabReselected(@Nullable TabLayout.Tab paramAnonymousTab) {}
      
      public void onTabSelected(@Nullable TabLayout.Tab paramAnonymousTab)
      {
        if ((paramAnonymousTab != null) && (paramAnonymousTab.getPosition() == 0))
        {
          DateTimeDialog.access$setScheduleType$p(this$0, ScheduleType.DEPARTURE);
          DateTimeDialog.access$setLeavingNow$p(this$0, false);
          DateTimeDialog.access$enableDateTimeUi(this$0);
          DateTimeDialog.access$highlightDayOfWeek(this$0, DateTimeDialog.access$getDayOfWeekView(this$0, DateTimeDialog.access$getSelectedDay$p(this$0)), DateTimeDialog.access$getSelectedDay$p(this$0));
          new OnSimpleEvent("Leave By Tab - Click").post();
        }
        else if ((paramAnonymousTab != null) && (paramAnonymousTab.getPosition() == 1))
        {
          DateTimeDialog.access$setScheduleType$p(this$0, ScheduleType.ARRIVAL);
          DateTimeDialog.access$setLeavingNow$p(this$0, false);
          DateTimeDialog.access$enableDateTimeUi(this$0);
          DateTimeDialog.access$highlightDayOfWeek(this$0, DateTimeDialog.access$getDayOfWeekView(this$0, DateTimeDialog.access$getSelectedDay$p(this$0)), DateTimeDialog.access$getSelectedDay$p(this$0));
          new OnSimpleEvent("Arrive By Tab - Click").post();
        }
        else
        {
          DateTimeDialog.access$setScheduleType$p(this$0, ScheduleType.DEPARTURE);
          DateTimeDialog.access$setLeavingNow$p(this$0, true);
          DateTimeDialog.access$setToCurrentTime(this$0);
          new OnSimpleEvent("Leave Now Tab - Click").post();
        }
        DateTimeDialog.access$updateDateInfo(this$0);
      }
      
      public void onTabUnselected(@Nullable TabLayout.Tab paramAnonymousTab) {}
    });
    paramBundle = (TimePicker)_$_findCachedViewById(R.id.timePicker);
    Intrinsics.checkExpressionValueIsNotNull(paramBundle, "timePicker");
    paramBundle.setCurrentHour(Integer.valueOf(paramView.get(11)));
    paramBundle = (TimePicker)_$_findCachedViewById(R.id.timePicker);
    Intrinsics.checkExpressionValueIsNotNull(paramBundle, "timePicker");
    paramBundle.setCurrentMinute(Integer.valueOf(paramView.get(12)));
    ((TimePicker)_$_findCachedViewById(R.id.timePicker)).setOnTimeChangedListener((TimePicker.OnTimeChangedListener)new TimePicker.OnTimeChangedListener()
    {
      public final void onTimeChanged(TimePicker paramAnonymousTimePicker, int paramAnonymousInt1, int paramAnonymousInt2)
      {
        DateTimeDialog.access$updateDateInfo(this$0);
      }
    });
  }
  
  @Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\016\n\002\b\t\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\b\003\030\0002\0020\001B\007\b\002¢\006\002\020\002J\030\020\r\032\0020\0162\b\020\017\032\004\030\0010\0202\006\020\021\032\0020\022R\024\020\003\032\0020\004XD¢\006\b\n\000\032\004\b\005\020\006R\024\020\007\032\0020\004XD¢\006\b\n\000\032\004\b\b\020\006R\024\020\t\032\0020\004XD¢\006\b\n\000\032\004\b\n\020\006R\024\020\013\032\0020\004XD¢\006\b\n\000\032\004\b\f\020\006¨\006\023"}, d2={"Lcom/byimplication/sakay/DateTimeDialog$Companion;", "", "()V", "HASPLANDATE", "", "getHASPLANDATE", "()Ljava/lang/String;", "ISARRIVAL", "getISARRIVAL", "PLANDATEKEY", "getPLANDATEKEY", "PLANTIMEPARSEFORMAT", "getPLANTIMEPARSEFORMAT", "newInstance", "Lcom/byimplication/sakay/DateTimeDialog;", "planDate", "Ljava/util/Date;", "scheduleType", "Lcom/byimplication/sakay/model/ScheduleType;", "app_release"}, k=1, mv={1, 1, 9})
  public static final class Companion
  {
    private Companion() {}
    
    @NotNull
    public final String getHASPLANDATE()
    {
      return DateTimeDialog.access$getHASPLANDATE$cp();
    }
    
    @NotNull
    public final String getISARRIVAL()
    {
      return DateTimeDialog.access$getISARRIVAL$cp();
    }
    
    @NotNull
    public final String getPLANDATEKEY()
    {
      return DateTimeDialog.access$getPLANDATEKEY$cp();
    }
    
    @NotNull
    public final String getPLANTIMEPARSEFORMAT()
    {
      return DateTimeDialog.access$getPLANTIMEPARSEFORMAT$cp();
    }
    
    @NotNull
    public final DateTimeDialog newInstance(@Nullable Date paramDate, @NotNull ScheduleType paramScheduleType)
    {
      Intrinsics.checkParameterIsNotNull(paramScheduleType, "scheduleType");
      DateTimeDialog localDateTimeDialog = new DateTimeDialog();
      if (paramDate != null)
      {
        Bundle localBundle = new Bundle();
        DateFormat localDateFormat = SimpleDateFormat.getDateTimeInstance();
        Companion localCompanion = (Companion)this;
        localBundle.putBoolean(localCompanion.getHASPLANDATE(), true);
        localBundle.putString(localCompanion.getPLANDATEKEY(), localDateFormat.format(paramDate));
        localBundle.putBoolean(localCompanion.getISARRIVAL(), Intrinsics.areEqual(paramScheduleType, ScheduleType.ARRIVAL));
        localDateTimeDialog.setArguments(localBundle);
      }
      return localDateTimeDialog;
    }
  }
}

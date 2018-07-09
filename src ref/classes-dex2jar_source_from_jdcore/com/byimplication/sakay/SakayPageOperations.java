package com.byimplication.sakay;

import android.view.View;
import android.view.ViewGroup;
import androidx.view.ViewGroupKt;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import kotlin.sequences.Sequence;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000&\n\002\030\002\n\002\020\000\n\000\n\002\020 \n\002\030\002\n\000\n\002\030\002\n\000\n\002\020\016\n\000\n\002\020\b\n\000\bf\030\0002\0020\001J\036\020\002\032\b\022\004\022\0020\0040\0032\006\020\005\032\0020\0062\006\020\007\032\0020\bH\026J\b\020\t\032\0020\nH\026¨\006\013"}, d2={"Lcom/byimplication/sakay/SakayPageOperations;", "", "findAllViewsWithTag", "", "Landroid/view/View;", "mView", "Landroid/view/ViewGroup;", "tag", "", "getModeNow", "", "app_release"}, k=1, mv={1, 1, 9})
public abstract interface SakayPageOperations
{
  @NotNull
  public abstract List<View> findAllViewsWithTag(@NotNull ViewGroup paramViewGroup, @NotNull String paramString);
  
  public abstract int getModeNow();
  
  @Metadata(bv={1, 0, 2}, k=3, mv={1, 1, 9})
  public static final class DefaultImpls
  {
    @NotNull
    public static List<View> findAllViewsWithTag(SakayPageOperations paramSakayPageOperations, @NotNull ViewGroup paramViewGroup, @NotNull String paramString)
    {
      Intrinsics.checkParameterIsNotNull(paramViewGroup, "mView");
      Intrinsics.checkParameterIsNotNull(paramString, "tag");
      ArrayList localArrayList = new ArrayList();
      paramViewGroup = ViewGroupKt.getChildren(paramViewGroup).iterator();
      while (paramViewGroup.hasNext())
      {
        View localView = (View)paramViewGroup.next();
        if (Intrinsics.areEqual(localView.getTag(), paramString)) {
          localArrayList.add(localView);
        }
        if ((localView instanceof ViewGroup)) {
          localArrayList.addAll((Collection)paramSakayPageOperations.findAllViewsWithTag((ViewGroup)localView, paramString));
        }
      }
      return CollectionsKt.toList((Iterable)localArrayList);
    }
    
    public static int getModeNow(SakayPageOperations paramSakayPageOperations)
    {
      paramSakayPageOperations = Calendar.getInstance(Locale.getDefault());
      return paramSakayPageOperations.get(11) * 60 + paramSakayPageOperations.get(12);
    }
  }
}

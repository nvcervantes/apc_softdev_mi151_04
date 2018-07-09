package com.byimplication.sakay;

import android.widget.ImageView;
import android.widget.TextView;
import kotlin.Metadata;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000\030\n\002\030\002\n\002\020\000\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\b\030\0002\0020\001B\035\022\006\020\002\032\0020\003\022\006\020\004\032\0020\005\022\006\020\006\032\0020\005¢\006\002\020\007R\021\020\006\032\0020\005¢\006\b\n\000\032\004\b\b\020\tR\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\n\020\tR\021\020\002\032\0020\003¢\006\b\n\000\032\004\b\013\020\f¨\006\r"}, d2={"Lcom/byimplication/sakay/GuideListEntryHolder;", "", "guideEntryPic", "Landroid/widget/ImageView;", "guideEntryLabel", "Landroid/widget/TextView;", "guideEntryDesc", "(Landroid/widget/ImageView;Landroid/widget/TextView;Landroid/widget/TextView;)V", "getGuideEntryDesc", "()Landroid/widget/TextView;", "getGuideEntryLabel", "getGuideEntryPic", "()Landroid/widget/ImageView;", "app_release"}, k=1, mv={1, 1, 9})
public final class GuideListEntryHolder
{
  @NotNull
  private final TextView guideEntryDesc;
  @NotNull
  private final TextView guideEntryLabel;
  @NotNull
  private final ImageView guideEntryPic;
  
  public GuideListEntryHolder(@NotNull ImageView paramImageView, @NotNull TextView paramTextView1, @NotNull TextView paramTextView2)
  {
    guideEntryPic = paramImageView;
    guideEntryLabel = paramTextView1;
    guideEntryDesc = paramTextView2;
  }
  
  @NotNull
  public final TextView getGuideEntryDesc()
  {
    return guideEntryDesc;
  }
  
  @NotNull
  public final TextView getGuideEntryLabel()
  {
    return guideEntryLabel;
  }
  
  @NotNull
  public final ImageView getGuideEntryPic()
  {
    return guideEntryPic;
  }
}

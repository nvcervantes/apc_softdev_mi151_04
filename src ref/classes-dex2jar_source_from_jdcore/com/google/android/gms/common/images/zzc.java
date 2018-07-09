package com.google.android.gms.common.images;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;
import com.google.android.gms.common.internal.zzbg;
import com.google.android.gms.internal.zzbge;
import com.google.android.gms.internal.zzbgj;
import java.lang.ref.WeakReference;

public final class zzc
  extends zza
{
  private WeakReference<ImageView> zzc;
  
  public zzc(ImageView paramImageView, int paramInt)
  {
    super(null, paramInt);
    com.google.android.gms.common.internal.zzc.zza(paramImageView);
    zzc = new WeakReference(paramImageView);
  }
  
  public zzc(ImageView paramImageView, Uri paramUri)
  {
    super(paramUri, 0);
    com.google.android.gms.common.internal.zzc.zza(paramImageView);
    zzc = new WeakReference(paramImageView);
  }
  
  public final boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof zzc)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    Object localObject = (zzc)paramObject;
    paramObject = (ImageView)zzc.get();
    localObject = (ImageView)zzc.get();
    return (localObject != null) && (paramObject != null) && (zzbg.zza(localObject, paramObject));
  }
  
  public final int hashCode()
  {
    return 0;
  }
  
  protected final void zza(Drawable paramDrawable, boolean paramBoolean1, boolean paramBoolean2, boolean paramBoolean3)
  {
    ImageView localImageView = (ImageView)zzc.get();
    if (localImageView != null)
    {
      int j = 0;
      int i;
      if ((!paramBoolean2) && (!paramBoolean3)) {
        i = 1;
      } else {
        i = 0;
      }
      if ((i != 0) && ((localImageView instanceof zzbgj)))
      {
        int k = zzbgj.zza();
        if ((zzb != 0) && (k == zzb)) {}
      }
      else
      {
        paramBoolean1 = zza(paramBoolean1, paramBoolean2);
        Object localObject2 = null;
        Object localObject1 = paramDrawable;
        if (paramBoolean1)
        {
          Drawable localDrawable = localImageView.getDrawable();
          if (localDrawable != null)
          {
            localObject1 = localDrawable;
            if ((localDrawable instanceof zzbge)) {
              localObject1 = ((zzbge)localDrawable).zza();
            }
          }
          else
          {
            localObject1 = null;
          }
          localObject1 = new zzbge((Drawable)localObject1, paramDrawable);
        }
        localImageView.setImageDrawable((Drawable)localObject1);
        if ((localImageView instanceof zzbgj))
        {
          paramDrawable = localObject2;
          if (paramBoolean3) {
            paramDrawable = zza.zza;
          }
          zzbgj.zza(paramDrawable);
          if (i != 0) {
            j = zzb;
          }
          zzbgj.zza(j);
        }
        if (paramBoolean1) {
          ((zzbge)localObject1).zza(250);
        }
      }
    }
  }
}

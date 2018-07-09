package com.google.android.gms.common.images;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.os.SystemClock;
import android.support.v4.util.LruCache;
import android.util.Log;
import android.widget.ImageView;
import com.google.android.gms.common.annotation.KeepName;
import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgk;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public final class ImageManager
{
  private static final Object zza = new Object();
  private static HashSet<Uri> zzb = new HashSet();
  private static ImageManager zzc;
  private final Context zzd;
  private final Handler zze;
  private final ExecutorService zzf;
  private final zza zzg;
  private final zzbgk zzh;
  private final Map<zza, ImageReceiver> zzi;
  private final Map<Uri, ImageReceiver> zzj;
  private final Map<Uri, Long> zzk;
  
  private ImageManager(Context paramContext, boolean paramBoolean)
  {
    zzd = paramContext.getApplicationContext();
    zze = new Handler(Looper.getMainLooper());
    zzf = Executors.newFixedThreadPool(4);
    zzg = null;
    zzh = new zzbgk();
    zzi = new HashMap();
    zzj = new HashMap();
    zzk = new HashMap();
  }
  
  public static ImageManager create(Context paramContext)
  {
    if (zzc == null) {
      zzc = new ImageManager(paramContext, false);
    }
    return zzc;
  }
  
  private final Bitmap zza(zzb paramZzb)
  {
    if (zzg == null) {
      return null;
    }
    return (Bitmap)zzg.get(paramZzb);
  }
  
  @Hide
  private final void zza(zza paramZza)
  {
    com.google.android.gms.common.internal.zzc.zza("ImageManager.loadImage() must be called in the main thread");
    new zzc(paramZza).run();
  }
  
  public final void loadImage(ImageView paramImageView, int paramInt)
  {
    zza(new zzc(paramImageView, paramInt));
  }
  
  public final void loadImage(ImageView paramImageView, Uri paramUri)
  {
    zza(new zzc(paramImageView, paramUri));
  }
  
  public final void loadImage(ImageView paramImageView, Uri paramUri, int paramInt)
  {
    paramImageView = new zzc(paramImageView, paramUri);
    zzb = paramInt;
    zza(paramImageView);
  }
  
  public final void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri)
  {
    zza(new zzd(paramOnImageLoadedListener, paramUri));
  }
  
  public final void loadImage(OnImageLoadedListener paramOnImageLoadedListener, Uri paramUri, int paramInt)
  {
    paramOnImageLoadedListener = new zzd(paramOnImageLoadedListener, paramUri);
    zzb = paramInt;
    zza(paramOnImageLoadedListener);
  }
  
  @KeepName
  final class ImageReceiver
    extends ResultReceiver
  {
    private final Uri zza;
    private final ArrayList<zza> zzb;
    
    ImageReceiver(Uri paramUri)
    {
      super();
      zza = paramUri;
      zzb = new ArrayList();
    }
    
    public final void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      paramBundle = (ParcelFileDescriptor)paramBundle.getParcelable("com.google.android.gms.extra.fileDescriptor");
      ImageManager.zzf(ImageManager.this).execute(new ImageManager.zzb(ImageManager.this, zza, paramBundle));
    }
    
    public final void zza()
    {
      Intent localIntent = new Intent("com.google.android.gms.common.images.LOAD_IMAGE");
      localIntent.putExtra("com.google.android.gms.extras.uri", zza);
      localIntent.putExtra("com.google.android.gms.extras.resultReceiver", this);
      localIntent.putExtra("com.google.android.gms.extras.priority", 3);
      ImageManager.zzb(ImageManager.this).sendBroadcast(localIntent);
    }
    
    public final void zza(zza paramZza)
    {
      com.google.android.gms.common.internal.zzc.zza("ImageReceiver.addImageRequest() must be called in the main thread");
      zzb.add(paramZza);
    }
    
    public final void zzb(zza paramZza)
    {
      com.google.android.gms.common.internal.zzc.zza("ImageReceiver.removeImageRequest() must be called in the main thread");
      zzb.remove(paramZza);
    }
  }
  
  public static abstract interface OnImageLoadedListener
  {
    public abstract void onImageLoaded(Uri paramUri, Drawable paramDrawable, boolean paramBoolean);
  }
  
  static final class zza
    extends LruCache<zzb, Bitmap>
  {}
  
  final class zzb
    implements Runnable
  {
    private final Uri zza;
    private final ParcelFileDescriptor zzb;
    
    public zzb(Uri paramUri, ParcelFileDescriptor paramParcelFileDescriptor)
    {
      zza = paramUri;
      zzb = paramParcelFileDescriptor;
    }
    
    public final void run()
    {
      Object localObject4;
      if (Looper.getMainLooper().getThread() == Thread.currentThread())
      {
        localObject1 = String.valueOf(Thread.currentThread());
        str = String.valueOf(Looper.getMainLooper().getThread());
        localObject4 = new StringBuilder(56 + String.valueOf(localObject1).length() + String.valueOf(str).length());
        ((StringBuilder)localObject4).append("checkNotMainThread: current thread ");
        ((StringBuilder)localObject4).append((String)localObject1);
        ((StringBuilder)localObject4).append(" IS the main thread ");
        ((StringBuilder)localObject4).append(str);
        ((StringBuilder)localObject4).append("!");
        Log.e("Asserts", ((StringBuilder)localObject4).toString());
        throw new IllegalStateException("LoadBitmapFromDiskRunnable can't be executed in the main thread");
      }
      boolean bool1 = false;
      boolean bool2 = false;
      Object localObject1 = null;
      String str = null;
      if (zzb != null)
      {
        try
        {
          localObject1 = BitmapFactory.decodeFileDescriptor(zzb.getFileDescriptor());
          bool1 = bool2;
        }
        catch (OutOfMemoryError localOutOfMemoryError)
        {
          localObject4 = String.valueOf(zza);
          StringBuilder localStringBuilder = new StringBuilder(34 + String.valueOf(localObject4).length());
          localStringBuilder.append("OOM while loading bitmap for uri: ");
          localStringBuilder.append((String)localObject4);
          Log.e("ImageManager", localStringBuilder.toString(), localOutOfMemoryError);
          bool1 = true;
          localObject2 = str;
        }
        try
        {
          zzb.close();
        }
        catch (IOException localIOException)
        {
          Log.e("ImageManager", "closed failed", localIOException);
        }
      }
      Object localObject3 = new CountDownLatch(1);
      ImageManager.zzg(ImageManager.this).post(new ImageManager.zzd(ImageManager.this, zza, (Bitmap)localObject2, bool1, (CountDownLatch)localObject3));
      try
      {
        ((CountDownLatch)localObject3).await();
        return;
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;) {}
      }
      Object localObject2 = String.valueOf(zza);
      localObject3 = new StringBuilder(32 + String.valueOf(localObject2).length());
      ((StringBuilder)localObject3).append("Latch interrupted while posting ");
      ((StringBuilder)localObject3).append((String)localObject2);
      Log.w("ImageManager", ((StringBuilder)localObject3).toString());
    }
  }
  
  final class zzc
    implements Runnable
  {
    private final zza zza;
    
    public zzc(zza paramZza)
    {
      zza = paramZza;
    }
    
    public final void run()
    {
      com.google.android.gms.common.internal.zzc.zza("LoadImageRunnable must be executed on the main thread");
      Object localObject1 = (ImageManager.ImageReceiver)ImageManager.zza(ImageManager.this).get(zza);
      if (localObject1 != null)
      {
        ImageManager.zza(ImageManager.this).remove(zza);
        ((ImageManager.ImageReceiver)localObject1).zzb(zza);
      }
      zzb localZzb = zza.zza;
      if (zza == null)
      {
        zza.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), true);
        return;
      }
      localObject1 = ImageManager.zza(ImageManager.this, localZzb);
      if (localObject1 != null)
      {
        zza.zza(ImageManager.zzb(ImageManager.this), (Bitmap)localObject1, true);
        return;
      }
      localObject1 = (Long)ImageManager.zzd(ImageManager.this).get(zza);
      if (localObject1 != null)
      {
        if (SystemClock.elapsedRealtime() - ((Long)localObject1).longValue() < 3600000L)
        {
          zza.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), true);
          return;
        }
        ImageManager.zzd(ImageManager.this).remove(zza);
      }
      zza.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this));
      ??? = (ImageManager.ImageReceiver)ImageManager.zze(ImageManager.this).get(zza);
      localObject1 = ???;
      if (??? == null)
      {
        localObject1 = new ImageManager.ImageReceiver(ImageManager.this, zza);
        ImageManager.zze(ImageManager.this).put(zza, localObject1);
      }
      ((ImageManager.ImageReceiver)localObject1).zza(zza);
      if (!(zza instanceof zzd)) {
        ImageManager.zza(ImageManager.this).put(zza, localObject1);
      }
      synchronized (ImageManager.zza())
      {
        if (!ImageManager.zzb().contains(zza))
        {
          ImageManager.zzb().add(zza);
          ((ImageManager.ImageReceiver)localObject1).zza();
        }
        return;
      }
    }
  }
  
  final class zzd
    implements Runnable
  {
    private final Uri zza;
    private final Bitmap zzb;
    private final CountDownLatch zzc;
    private boolean zzd;
    
    public zzd(Uri paramUri, Bitmap paramBitmap, boolean paramBoolean, CountDownLatch paramCountDownLatch)
    {
      zza = paramUri;
      zzb = paramBitmap;
      zzd = paramBoolean;
      zzc = paramCountDownLatch;
    }
    
    public final void run()
    {
      com.google.android.gms.common.internal.zzc.zza("OnBitmapLoadedRunnable must be executed in the main thread");
      int i;
      if (zzb != null) {
        i = 1;
      } else {
        i = 0;
      }
      if (ImageManager.zzh(ImageManager.this) != null)
      {
        if (zzd)
        {
          ImageManager.zzh(ImageManager.this).evictAll();
          System.gc();
          zzd = false;
          ImageManager.zzg(ImageManager.this).post(this);
          return;
        }
        if (i != 0) {
          ImageManager.zzh(ImageManager.this).put(new zzb(zza), zzb);
        }
      }
      ??? = (ImageManager.ImageReceiver)ImageManager.zze(ImageManager.this).remove(zza);
      if (??? != null)
      {
        ??? = ImageManager.ImageReceiver.zza((ImageManager.ImageReceiver)???);
        int k = ((ArrayList)???).size();
        int j = 0;
        while (j < k)
        {
          zza localZza = (zza)((ArrayList)???).get(j);
          if (i != 0)
          {
            localZza.zza(ImageManager.zzb(ImageManager.this), zzb, false);
          }
          else
          {
            ImageManager.zzd(ImageManager.this).put(zza, Long.valueOf(SystemClock.elapsedRealtime()));
            localZza.zza(ImageManager.zzb(ImageManager.this), ImageManager.zzc(ImageManager.this), false);
          }
          if (!(localZza instanceof zzd)) {
            ImageManager.zza(ImageManager.this).remove(localZza);
          }
          j += 1;
        }
      }
      zzc.countDown();
      synchronized (ImageManager.zza())
      {
        ImageManager.zzb().remove(zza);
        return;
      }
    }
  }
}

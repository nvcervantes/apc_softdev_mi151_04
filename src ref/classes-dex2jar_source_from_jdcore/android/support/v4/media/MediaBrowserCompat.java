package android.support.v4.media;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.BadParcelableException;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.annotation.RestrictTo;
import android.support.v4.app.BundleCompat;
import android.support.v4.media.session.IMediaSession;
import android.support.v4.media.session.IMediaSession.Stub;
import android.support.v4.media.session.MediaSessionCompat;
import android.support.v4.media.session.MediaSessionCompat.Token;
import android.support.v4.os.ResultReceiver;
import android.support.v4.util.ArrayMap;
import android.text.TextUtils;
import android.util.Log;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

public final class MediaBrowserCompat
{
  public static final String CUSTOM_ACTION_DOWNLOAD = "android.support.v4.media.action.DOWNLOAD";
  public static final String CUSTOM_ACTION_REMOVE_DOWNLOADED_FILE = "android.support.v4.media.action.REMOVE_DOWNLOADED_FILE";
  static final boolean DEBUG = Log.isLoggable("MediaBrowserCompat", 3);
  public static final String EXTRA_DOWNLOAD_PROGRESS = "android.media.browse.extra.DOWNLOAD_PROGRESS";
  public static final String EXTRA_MEDIA_ID = "android.media.browse.extra.MEDIA_ID";
  public static final String EXTRA_PAGE = "android.media.browse.extra.PAGE";
  public static final String EXTRA_PAGE_SIZE = "android.media.browse.extra.PAGE_SIZE";
  static final String TAG = "MediaBrowserCompat";
  private final MediaBrowserImpl mImpl;
  
  public MediaBrowserCompat(Context paramContext, ComponentName paramComponentName, ConnectionCallback paramConnectionCallback, Bundle paramBundle)
  {
    if (Build.VERSION.SDK_INT >= 26)
    {
      mImpl = new MediaBrowserImplApi26(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
      return;
    }
    if (Build.VERSION.SDK_INT >= 23)
    {
      mImpl = new MediaBrowserImplApi23(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
      return;
    }
    if (Build.VERSION.SDK_INT >= 21)
    {
      mImpl = new MediaBrowserImplApi21(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
      return;
    }
    mImpl = new MediaBrowserImplBase(paramContext, paramComponentName, paramConnectionCallback, paramBundle);
  }
  
  public void connect()
  {
    mImpl.connect();
  }
  
  public void disconnect()
  {
    mImpl.disconnect();
  }
  
  @Nullable
  public Bundle getExtras()
  {
    return mImpl.getExtras();
  }
  
  public void getItem(@NonNull String paramString, @NonNull ItemCallback paramItemCallback)
  {
    mImpl.getItem(paramString, paramItemCallback);
  }
  
  @NonNull
  public String getRoot()
  {
    return mImpl.getRoot();
  }
  
  @NonNull
  public ComponentName getServiceComponent()
  {
    return mImpl.getServiceComponent();
  }
  
  @NonNull
  public MediaSessionCompat.Token getSessionToken()
  {
    return mImpl.getSessionToken();
  }
  
  public boolean isConnected()
  {
    return mImpl.isConnected();
  }
  
  public void search(@NonNull String paramString, Bundle paramBundle, @NonNull SearchCallback paramSearchCallback)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("query cannot be empty");
    }
    if (paramSearchCallback == null) {
      throw new IllegalArgumentException("callback cannot be null");
    }
    mImpl.search(paramString, paramBundle, paramSearchCallback);
  }
  
  public void sendCustomAction(@NonNull String paramString, Bundle paramBundle, @Nullable CustomActionCallback paramCustomActionCallback)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("action cannot be empty");
    }
    mImpl.sendCustomAction(paramString, paramBundle, paramCustomActionCallback);
  }
  
  public void subscribe(@NonNull String paramString, @NonNull Bundle paramBundle, @NonNull SubscriptionCallback paramSubscriptionCallback)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("parentId is empty");
    }
    if (paramSubscriptionCallback == null) {
      throw new IllegalArgumentException("callback is null");
    }
    if (paramBundle == null) {
      throw new IllegalArgumentException("options are null");
    }
    mImpl.subscribe(paramString, paramBundle, paramSubscriptionCallback);
  }
  
  public void subscribe(@NonNull String paramString, @NonNull SubscriptionCallback paramSubscriptionCallback)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("parentId is empty");
    }
    if (paramSubscriptionCallback == null) {
      throw new IllegalArgumentException("callback is null");
    }
    mImpl.subscribe(paramString, null, paramSubscriptionCallback);
  }
  
  public void unsubscribe(@NonNull String paramString)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("parentId is empty");
    }
    mImpl.unsubscribe(paramString, null);
  }
  
  public void unsubscribe(@NonNull String paramString, @NonNull SubscriptionCallback paramSubscriptionCallback)
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("parentId is empty");
    }
    if (paramSubscriptionCallback == null) {
      throw new IllegalArgumentException("callback is null");
    }
    mImpl.unsubscribe(paramString, paramSubscriptionCallback);
  }
  
  private static class CallbackHandler
    extends Handler
  {
    private final WeakReference<MediaBrowserCompat.MediaBrowserServiceCallbackImpl> mCallbackImplRef;
    private WeakReference<Messenger> mCallbacksMessengerRef;
    
    CallbackHandler(MediaBrowserCompat.MediaBrowserServiceCallbackImpl paramMediaBrowserServiceCallbackImpl)
    {
      mCallbackImplRef = new WeakReference(paramMediaBrowserServiceCallbackImpl);
    }
    
    public void handleMessage(Message paramMessage)
    {
      MediaBrowserCompat.MediaBrowserServiceCallbackImpl localMediaBrowserServiceCallbackImpl;
      Messenger localMessenger;
      if ((mCallbacksMessengerRef != null) && (mCallbacksMessengerRef.get() != null))
      {
        if (mCallbackImplRef.get() == null) {
          return;
        }
        localObject = paramMessage.getData();
        ((Bundle)localObject).setClassLoader(MediaSessionCompat.class.getClassLoader());
        localMediaBrowserServiceCallbackImpl = (MediaBrowserCompat.MediaBrowserServiceCallbackImpl)mCallbackImplRef.get();
        localMessenger = (Messenger)mCallbacksMessengerRef.get();
      }
      try
      {
        switch (what)
        {
        case 3: 
          localMediaBrowserServiceCallbackImpl.onLoadChildren(localMessenger, ((Bundle)localObject).getString("data_media_item_id"), ((Bundle)localObject).getParcelableArrayList("data_media_item_list"), ((Bundle)localObject).getBundle("data_options"));
          return;
        }
      }
      catch (BadParcelableException localBadParcelableException)
      {
        for (;;) {}
      }
      localMediaBrowserServiceCallbackImpl.onConnectionFailed(localMessenger);
      return;
      localMediaBrowserServiceCallbackImpl.onServiceConnected(localMessenger, ((Bundle)localObject).getString("data_media_item_id"), (MediaSessionCompat.Token)((Bundle)localObject).getParcelable("data_media_session_token"), ((Bundle)localObject).getBundle("data_root_hints"));
      return;
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Unhandled message: ");
      ((StringBuilder)localObject).append(paramMessage);
      ((StringBuilder)localObject).append("\n  Client version: ");
      ((StringBuilder)localObject).append(1);
      ((StringBuilder)localObject).append("\n  Service version: ");
      ((StringBuilder)localObject).append(arg1);
      Log.w("MediaBrowserCompat", ((StringBuilder)localObject).toString());
      return;
      Log.e("MediaBrowserCompat", "Could not unparcel the data.");
      if (what == 1) {
        localMediaBrowserServiceCallbackImpl.onConnectionFailed(localMessenger);
      }
      return;
    }
    
    void setCallbacksMessenger(Messenger paramMessenger)
    {
      mCallbacksMessengerRef = new WeakReference(paramMessenger);
    }
  }
  
  public static class ConnectionCallback
  {
    ConnectionCallbackInternal mConnectionCallbackInternal;
    final Object mConnectionCallbackObj;
    
    public ConnectionCallback()
    {
      if (Build.VERSION.SDK_INT >= 21)
      {
        mConnectionCallbackObj = MediaBrowserCompatApi21.createConnectionCallback(new StubApi21());
        return;
      }
      mConnectionCallbackObj = null;
    }
    
    public void onConnected() {}
    
    public void onConnectionFailed() {}
    
    public void onConnectionSuspended() {}
    
    void setInternalConnectionCallback(ConnectionCallbackInternal paramConnectionCallbackInternal)
    {
      mConnectionCallbackInternal = paramConnectionCallbackInternal;
    }
    
    static abstract interface ConnectionCallbackInternal
    {
      public abstract void onConnected();
      
      public abstract void onConnectionFailed();
      
      public abstract void onConnectionSuspended();
    }
    
    private class StubApi21
      implements MediaBrowserCompatApi21.ConnectionCallback
    {
      StubApi21() {}
      
      public void onConnected()
      {
        if (mConnectionCallbackInternal != null) {
          mConnectionCallbackInternal.onConnected();
        }
        MediaBrowserCompat.ConnectionCallback.this.onConnected();
      }
      
      public void onConnectionFailed()
      {
        if (mConnectionCallbackInternal != null) {
          mConnectionCallbackInternal.onConnectionFailed();
        }
        MediaBrowserCompat.ConnectionCallback.this.onConnectionFailed();
      }
      
      public void onConnectionSuspended()
      {
        if (mConnectionCallbackInternal != null) {
          mConnectionCallbackInternal.onConnectionSuspended();
        }
        MediaBrowserCompat.ConnectionCallback.this.onConnectionSuspended();
      }
    }
  }
  
  public static abstract class CustomActionCallback
  {
    public CustomActionCallback() {}
    
    public void onError(String paramString, Bundle paramBundle1, Bundle paramBundle2) {}
    
    public void onProgressUpdate(String paramString, Bundle paramBundle1, Bundle paramBundle2) {}
    
    public void onResult(String paramString, Bundle paramBundle1, Bundle paramBundle2) {}
  }
  
  private static class CustomActionResultReceiver
    extends ResultReceiver
  {
    private final String mAction;
    private final MediaBrowserCompat.CustomActionCallback mCallback;
    private final Bundle mExtras;
    
    CustomActionResultReceiver(String paramString, Bundle paramBundle, MediaBrowserCompat.CustomActionCallback paramCustomActionCallback, Handler paramHandler)
    {
      super();
      mAction = paramString;
      mExtras = paramBundle;
      mCallback = paramCustomActionCallback;
    }
    
    protected void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      if (mCallback == null) {
        return;
      }
      switch (paramInt)
      {
      default: 
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Unknown result code: ");
        localStringBuilder.append(paramInt);
        localStringBuilder.append(" (extras=");
        localStringBuilder.append(mExtras);
        localStringBuilder.append(", resultData=");
        localStringBuilder.append(paramBundle);
        localStringBuilder.append(")");
        Log.w("MediaBrowserCompat", localStringBuilder.toString());
        return;
      case 1: 
        mCallback.onProgressUpdate(mAction, mExtras, paramBundle);
        return;
      case 0: 
        mCallback.onResult(mAction, mExtras, paramBundle);
        return;
      }
      mCallback.onError(mAction, mExtras, paramBundle);
    }
  }
  
  public static abstract class ItemCallback
  {
    final Object mItemCallbackObj;
    
    public ItemCallback()
    {
      if (Build.VERSION.SDK_INT >= 23)
      {
        mItemCallbackObj = MediaBrowserCompatApi23.createItemCallback(new StubApi23());
        return;
      }
      mItemCallbackObj = null;
    }
    
    public void onError(@NonNull String paramString) {}
    
    public void onItemLoaded(MediaBrowserCompat.MediaItem paramMediaItem) {}
    
    private class StubApi23
      implements MediaBrowserCompatApi23.ItemCallback
    {
      StubApi23() {}
      
      public void onError(@NonNull String paramString)
      {
        MediaBrowserCompat.ItemCallback.this.onError(paramString);
      }
      
      public void onItemLoaded(Parcel paramParcel)
      {
        if (paramParcel == null)
        {
          onItemLoaded(null);
          return;
        }
        paramParcel.setDataPosition(0);
        MediaBrowserCompat.MediaItem localMediaItem = (MediaBrowserCompat.MediaItem)MediaBrowserCompat.MediaItem.CREATOR.createFromParcel(paramParcel);
        paramParcel.recycle();
        onItemLoaded(localMediaItem);
      }
    }
  }
  
  private static class ItemReceiver
    extends ResultReceiver
  {
    private final MediaBrowserCompat.ItemCallback mCallback;
    private final String mMediaId;
    
    ItemReceiver(String paramString, MediaBrowserCompat.ItemCallback paramItemCallback, Handler paramHandler)
    {
      super();
      mMediaId = paramString;
      mCallback = paramItemCallback;
    }
    
    protected void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      if (paramBundle != null) {
        paramBundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
      }
      if ((paramInt == 0) && (paramBundle != null) && (paramBundle.containsKey("media_item")))
      {
        paramBundle = paramBundle.getParcelable("media_item");
        if ((paramBundle != null) && (!(paramBundle instanceof MediaBrowserCompat.MediaItem)))
        {
          mCallback.onError(mMediaId);
          return;
        }
        mCallback.onItemLoaded((MediaBrowserCompat.MediaItem)paramBundle);
        return;
      }
      mCallback.onError(mMediaId);
    }
  }
  
  static abstract interface MediaBrowserImpl
  {
    public abstract void connect();
    
    public abstract void disconnect();
    
    @Nullable
    public abstract Bundle getExtras();
    
    public abstract void getItem(@NonNull String paramString, @NonNull MediaBrowserCompat.ItemCallback paramItemCallback);
    
    @NonNull
    public abstract String getRoot();
    
    public abstract ComponentName getServiceComponent();
    
    @NonNull
    public abstract MediaSessionCompat.Token getSessionToken();
    
    public abstract boolean isConnected();
    
    public abstract void search(@NonNull String paramString, Bundle paramBundle, @NonNull MediaBrowserCompat.SearchCallback paramSearchCallback);
    
    public abstract void sendCustomAction(@NonNull String paramString, Bundle paramBundle, @Nullable MediaBrowserCompat.CustomActionCallback paramCustomActionCallback);
    
    public abstract void subscribe(@NonNull String paramString, @Nullable Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback);
    
    public abstract void unsubscribe(@NonNull String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback);
  }
  
  @RequiresApi(21)
  static class MediaBrowserImplApi21
    implements MediaBrowserCompat.MediaBrowserImpl, MediaBrowserCompat.MediaBrowserServiceCallbackImpl, MediaBrowserCompat.ConnectionCallback.ConnectionCallbackInternal
  {
    protected final Object mBrowserObj;
    protected Messenger mCallbacksMessenger;
    final Context mContext;
    protected final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
    private MediaSessionCompat.Token mMediaSessionToken;
    protected final Bundle mRootHints;
    protected MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
    protected int mServiceVersion;
    private final ArrayMap<String, MediaBrowserCompat.Subscription> mSubscriptions = new ArrayMap();
    
    MediaBrowserImplApi21(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      mContext = paramContext;
      Bundle localBundle = paramBundle;
      if (paramBundle == null) {
        localBundle = new Bundle();
      }
      localBundle.putInt("extra_client_version", 1);
      mRootHints = new Bundle(localBundle);
      paramConnectionCallback.setInternalConnectionCallback(this);
      mBrowserObj = MediaBrowserCompatApi21.createBrowser(paramContext, paramComponentName, mConnectionCallbackObj, mRootHints);
    }
    
    public void connect()
    {
      MediaBrowserCompatApi21.connect(mBrowserObj);
    }
    
    public void disconnect()
    {
      if ((mServiceBinderWrapper != null) && (mCallbacksMessenger != null)) {}
      try
      {
        mServiceBinderWrapper.unregisterCallbackMessenger(mCallbacksMessenger);
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
      Log.i("MediaBrowserCompat", "Remote error unregistering client messenger.");
      MediaBrowserCompatApi21.disconnect(mBrowserObj);
    }
    
    @Nullable
    public Bundle getExtras()
    {
      return MediaBrowserCompatApi21.getExtras(mBrowserObj);
    }
    
    public void getItem(@NonNull final String paramString, @NonNull final MediaBrowserCompat.ItemCallback paramItemCallback)
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("mediaId is empty");
      }
      if (paramItemCallback == null) {
        throw new IllegalArgumentException("cb is null");
      }
      if (!MediaBrowserCompatApi21.isConnected(mBrowserObj))
      {
        Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
        mHandler.post(new Runnable()
        {
          public void run()
          {
            paramItemCallback.onError(paramString);
          }
        });
        return;
      }
      if (mServiceBinderWrapper == null)
      {
        mHandler.post(new Runnable()
        {
          public void run()
          {
            paramItemCallback.onError(paramString);
          }
        });
        return;
      }
      Object localObject = new MediaBrowserCompat.ItemReceiver(paramString, paramItemCallback, mHandler);
      try
      {
        mServiceBinderWrapper.getMediaItem(paramString, (ResultReceiver)localObject, mCallbacksMessenger);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Remote error getting media item: ");
      ((StringBuilder)localObject).append(paramString);
      Log.i("MediaBrowserCompat", ((StringBuilder)localObject).toString());
      mHandler.post(new Runnable()
      {
        public void run()
        {
          paramItemCallback.onError(paramString);
        }
      });
    }
    
    @NonNull
    public String getRoot()
    {
      return MediaBrowserCompatApi21.getRoot(mBrowserObj);
    }
    
    public ComponentName getServiceComponent()
    {
      return MediaBrowserCompatApi21.getServiceComponent(mBrowserObj);
    }
    
    @NonNull
    public MediaSessionCompat.Token getSessionToken()
    {
      if (mMediaSessionToken == null) {
        mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(mBrowserObj));
      }
      return mMediaSessionToken;
    }
    
    public boolean isConnected()
    {
      return MediaBrowserCompatApi21.isConnected(mBrowserObj);
    }
    
    public void onConnected()
    {
      Object localObject = MediaBrowserCompatApi21.getExtras(mBrowserObj);
      if (localObject == null) {
        return;
      }
      mServiceVersion = ((Bundle)localObject).getInt("extra_service_version", 0);
      IBinder localIBinder = BundleCompat.getBinder((Bundle)localObject, "extra_messenger");
      if (localIBinder != null)
      {
        mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper(localIBinder, mRootHints);
        mCallbacksMessenger = new Messenger(mHandler);
        mHandler.setCallbacksMessenger(mCallbacksMessenger);
      }
      try
      {
        mServiceBinderWrapper.registerCallbackMessenger(mCallbacksMessenger);
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
      Log.i("MediaBrowserCompat", "Remote error registering client messenger.");
      localObject = IMediaSession.Stub.asInterface(BundleCompat.getBinder((Bundle)localObject, "extra_session_binder"));
      if (localObject != null) {
        mMediaSessionToken = MediaSessionCompat.Token.fromToken(MediaBrowserCompatApi21.getSessionToken(mBrowserObj), (IMediaSession)localObject);
      }
    }
    
    public void onConnectionFailed() {}
    
    public void onConnectionFailed(Messenger paramMessenger) {}
    
    public void onConnectionSuspended()
    {
      mServiceBinderWrapper = null;
      mCallbacksMessenger = null;
      mMediaSessionToken = null;
      mHandler.setCallbacksMessenger(null);
    }
    
    public void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle)
    {
      if (mCallbacksMessenger != paramMessenger) {
        return;
      }
      paramMessenger = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
      if (paramMessenger == null)
      {
        if (MediaBrowserCompat.DEBUG)
        {
          paramMessenger = new StringBuilder();
          paramMessenger.append("onLoadChildren for id that isn't subscribed id=");
          paramMessenger.append(paramString);
          Log.d("MediaBrowserCompat", paramMessenger.toString());
        }
        return;
      }
      paramMessenger = paramMessenger.getCallback(mContext, paramBundle);
      if (paramMessenger != null)
      {
        if (paramBundle == null)
        {
          if (paramList == null)
          {
            paramMessenger.onError(paramString);
            return;
          }
          paramMessenger.onChildrenLoaded(paramString, paramList);
          return;
        }
        if (paramList == null)
        {
          paramMessenger.onError(paramString, paramBundle);
          return;
        }
        paramMessenger.onChildrenLoaded(paramString, paramList, paramBundle);
      }
    }
    
    public void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle) {}
    
    public void search(@NonNull final String paramString, final Bundle paramBundle, @NonNull final MediaBrowserCompat.SearchCallback paramSearchCallback)
    {
      if (!isConnected()) {
        throw new IllegalStateException("search() called while not connected");
      }
      if (mServiceBinderWrapper == null)
      {
        Log.i("MediaBrowserCompat", "The connected service doesn't support search.");
        mHandler.post(new Runnable()
        {
          public void run()
          {
            paramSearchCallback.onError(paramString, paramBundle);
          }
        });
        return;
      }
      MediaBrowserCompat.SearchResultReceiver localSearchResultReceiver = new MediaBrowserCompat.SearchResultReceiver(paramString, paramBundle, paramSearchCallback, mHandler);
      try
      {
        mServiceBinderWrapper.search(paramString, paramBundle, localSearchResultReceiver, mCallbacksMessenger);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Remote error searching items with query: ");
        localStringBuilder.append(paramString);
        Log.i("MediaBrowserCompat", localStringBuilder.toString(), localRemoteException);
        mHandler.post(new Runnable()
        {
          public void run()
          {
            paramSearchCallback.onError(paramString, paramBundle);
          }
        });
      }
    }
    
    public void sendCustomAction(@NonNull final String paramString, final Bundle paramBundle, @Nullable final MediaBrowserCompat.CustomActionCallback paramCustomActionCallback)
    {
      if (!isConnected())
      {
        paramCustomActionCallback = new StringBuilder();
        paramCustomActionCallback.append("Cannot send a custom action (");
        paramCustomActionCallback.append(paramString);
        paramCustomActionCallback.append(") with ");
        paramCustomActionCallback.append("extras ");
        paramCustomActionCallback.append(paramBundle);
        paramCustomActionCallback.append(" because the browser is not connected to the ");
        paramCustomActionCallback.append("service.");
        throw new IllegalStateException(paramCustomActionCallback.toString());
      }
      if (mServiceBinderWrapper == null)
      {
        Log.i("MediaBrowserCompat", "The connected service doesn't support sendCustomAction.");
        if (paramCustomActionCallback != null) {
          mHandler.post(new Runnable()
          {
            public void run()
            {
              paramCustomActionCallback.onError(paramString, paramBundle, null);
            }
          });
        }
      }
      MediaBrowserCompat.CustomActionResultReceiver localCustomActionResultReceiver = new MediaBrowserCompat.CustomActionResultReceiver(paramString, paramBundle, paramCustomActionCallback, mHandler);
      try
      {
        mServiceBinderWrapper.sendCustomAction(paramString, paramBundle, localCustomActionResultReceiver, mCallbacksMessenger);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Remote error sending a custom action: action=");
        localStringBuilder.append(paramString);
        localStringBuilder.append(", extras=");
        localStringBuilder.append(paramBundle);
        Log.i("MediaBrowserCompat", localStringBuilder.toString(), localRemoteException);
        if (paramCustomActionCallback != null) {
          mHandler.post(new Runnable()
          {
            public void run()
            {
              paramCustomActionCallback.onError(paramString, paramBundle, null);
            }
          });
        }
      }
    }
    
    public void subscribe(@NonNull String paramString, Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      MediaBrowserCompat.Subscription localSubscription2 = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
      MediaBrowserCompat.Subscription localSubscription1 = localSubscription2;
      if (localSubscription2 == null)
      {
        localSubscription1 = new MediaBrowserCompat.Subscription();
        mSubscriptions.put(paramString, localSubscription1);
      }
      MediaBrowserCompat.SubscriptionCallback.access$100(paramSubscriptionCallback, localSubscription1);
      if (paramBundle == null) {
        paramBundle = null;
      } else {
        paramBundle = new Bundle(paramBundle);
      }
      localSubscription1.putCallback(mContext, paramBundle, paramSubscriptionCallback);
      if (mServiceBinderWrapper == null)
      {
        MediaBrowserCompatApi21.subscribe(mBrowserObj, paramString, MediaBrowserCompat.SubscriptionCallback.access$200(paramSubscriptionCallback));
        return;
      }
      try
      {
        mServiceBinderWrapper.addSubscription(paramString, MediaBrowserCompat.SubscriptionCallback.access$000(paramSubscriptionCallback), paramBundle, mCallbacksMessenger);
        return;
      }
      catch (RemoteException paramBundle)
      {
        for (;;) {}
      }
      paramBundle = new StringBuilder();
      paramBundle.append("Remote error subscribing media item: ");
      paramBundle.append(paramString);
      Log.i("MediaBrowserCompat", paramBundle.toString());
    }
    
    public void unsubscribe(@NonNull String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 66	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mSubscriptions	Landroid/support/v4/util/ArrayMap;
      //   4: aload_1
      //   5: invokevirtual 266	android/support/v4/util/ArrayMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
      //   8: checkcast 268	android/support/v4/media/MediaBrowserCompat$Subscription
      //   11: astore 4
      //   13: aload 4
      //   15: ifnonnull +4 -> 19
      //   18: return
      //   19: aload_0
      //   20: getfield 109	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   23: ifnonnull +104 -> 127
      //   26: aload_2
      //   27: ifnonnull +14 -> 41
      //   30: aload_0
      //   31: getfield 99	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mBrowserObj	Ljava/lang/Object;
      //   34: aload_1
      //   35: invokestatic 395	android/support/v4/media/MediaBrowserCompatApi21:unsubscribe	(Ljava/lang/Object;Ljava/lang/String;)V
      //   38: goto +226 -> 264
      //   41: aload 4
      //   43: invokevirtual 399	android/support/v4/media/MediaBrowserCompat$Subscription:getCallbacks	()Ljava/util/List;
      //   46: astore 5
      //   48: aload 4
      //   50: invokevirtual 402	android/support/v4/media/MediaBrowserCompat$Subscription:getOptionsList	()Ljava/util/List;
      //   53: astore 6
      //   55: aload 5
      //   57: invokeinterface 408 1 0
      //   62: iconst_1
      //   63: isub
      //   64: istore_3
      //   65: iload_3
      //   66: iflt +40 -> 106
      //   69: aload 5
      //   71: iload_3
      //   72: invokeinterface 411 2 0
      //   77: aload_2
      //   78: if_acmpne +21 -> 99
      //   81: aload 5
      //   83: iload_3
      //   84: invokeinterface 414 2 0
      //   89: pop
      //   90: aload 6
      //   92: iload_3
      //   93: invokeinterface 414 2 0
      //   98: pop
      //   99: iload_3
      //   100: iconst_1
      //   101: isub
      //   102: istore_3
      //   103: goto -38 -> 65
      //   106: aload 5
      //   108: invokeinterface 408 1 0
      //   113: ifne +151 -> 264
      //   116: aload_0
      //   117: getfield 99	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mBrowserObj	Ljava/lang/Object;
      //   120: aload_1
      //   121: invokestatic 395	android/support/v4/media/MediaBrowserCompatApi21:unsubscribe	(Ljava/lang/Object;Ljava/lang/String;)V
      //   124: goto +140 -> 264
      //   127: aload_2
      //   128: ifnonnull +19 -> 147
      //   131: aload_0
      //   132: getfield 109	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   135: aload_1
      //   136: aconst_null
      //   137: aload_0
      //   138: getfield 111	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mCallbacksMessenger	Landroid/os/Messenger;
      //   141: invokevirtual 418	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:removeSubscription	(Ljava/lang/String;Landroid/os/IBinder;Landroid/os/Messenger;)V
      //   144: goto +120 -> 264
      //   147: aload 4
      //   149: invokevirtual 399	android/support/v4/media/MediaBrowserCompat$Subscription:getCallbacks	()Ljava/util/List;
      //   152: astore 5
      //   154: aload 4
      //   156: invokevirtual 402	android/support/v4/media/MediaBrowserCompat$Subscription:getOptionsList	()Ljava/util/List;
      //   159: astore 6
      //   161: aload 5
      //   163: invokeinterface 408 1 0
      //   168: iconst_1
      //   169: isub
      //   170: istore_3
      //   171: iload_3
      //   172: iflt +92 -> 264
      //   175: aload 5
      //   177: iload_3
      //   178: invokeinterface 411 2 0
      //   183: aload_2
      //   184: if_acmpne +37 -> 221
      //   187: aload_0
      //   188: getfield 109	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mServiceBinderWrapper	Landroid/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper;
      //   191: aload_1
      //   192: aload_2
      //   193: invokestatic 384	android/support/v4/media/MediaBrowserCompat$SubscriptionCallback:access$000	(Landroid/support/v4/media/MediaBrowserCompat$SubscriptionCallback;)Landroid/os/IBinder;
      //   196: aload_0
      //   197: getfield 111	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mCallbacksMessenger	Landroid/os/Messenger;
      //   200: invokevirtual 418	android/support/v4/media/MediaBrowserCompat$ServiceBinderWrapper:removeSubscription	(Ljava/lang/String;Landroid/os/IBinder;Landroid/os/Messenger;)V
      //   203: aload 5
      //   205: iload_3
      //   206: invokeinterface 414 2 0
      //   211: pop
      //   212: aload 6
      //   214: iload_3
      //   215: invokeinterface 414 2 0
      //   220: pop
      //   221: iload_3
      //   222: iconst_1
      //   223: isub
      //   224: istore_3
      //   225: goto -54 -> 171
      //   228: new 179	java/lang/StringBuilder
      //   231: dup
      //   232: invokespecial 180	java/lang/StringBuilder:<init>	()V
      //   235: astore 5
      //   237: aload 5
      //   239: ldc_w 420
      //   242: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   245: pop
      //   246: aload 5
      //   248: aload_1
      //   249: invokevirtual 186	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   252: pop
      //   253: ldc 119
      //   255: aload 5
      //   257: invokevirtual 190	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   260: invokestatic 277	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
      //   263: pop
      //   264: aload 4
      //   266: invokevirtual 422	android/support/v4/media/MediaBrowserCompat$Subscription:isEmpty	()Z
      //   269: ifne +7 -> 276
      //   272: aload_2
      //   273: ifnonnull +12 -> 285
      //   276: aload_0
      //   277: getfield 66	android/support/v4/media/MediaBrowserCompat$MediaBrowserImplApi21:mSubscriptions	Landroid/support/v4/util/ArrayMap;
      //   280: aload_1
      //   281: invokevirtual 424	android/support/v4/util/ArrayMap:remove	(Ljava/lang/Object;)Ljava/lang/Object;
      //   284: pop
      //   285: return
      //   286: astore 5
      //   288: goto -60 -> 228
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	291	0	this	MediaBrowserImplApi21
      //   0	291	1	paramString	String
      //   0	291	2	paramSubscriptionCallback	MediaBrowserCompat.SubscriptionCallback
      //   64	161	3	i	int
      //   11	254	4	localSubscription	MediaBrowserCompat.Subscription
      //   46	210	5	localObject	Object
      //   286	1	5	localRemoteException	RemoteException
      //   53	160	6	localList	List
      // Exception table:
      //   from	to	target	type
      //   131	144	286	android/os/RemoteException
      //   147	171	286	android/os/RemoteException
      //   175	221	286	android/os/RemoteException
    }
  }
  
  @RequiresApi(23)
  static class MediaBrowserImplApi23
    extends MediaBrowserCompat.MediaBrowserImplApi21
  {
    MediaBrowserImplApi23(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      super(paramComponentName, paramConnectionCallback, paramBundle);
    }
    
    public void getItem(@NonNull String paramString, @NonNull MediaBrowserCompat.ItemCallback paramItemCallback)
    {
      if (mServiceBinderWrapper == null)
      {
        MediaBrowserCompatApi23.getItem(mBrowserObj, paramString, mItemCallbackObj);
        return;
      }
      super.getItem(paramString, paramItemCallback);
    }
  }
  
  @RequiresApi(26)
  static class MediaBrowserImplApi26
    extends MediaBrowserCompat.MediaBrowserImplApi23
  {
    MediaBrowserImplApi26(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      super(paramComponentName, paramConnectionCallback, paramBundle);
    }
    
    public void subscribe(@NonNull String paramString, @Nullable Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      if ((mServiceBinderWrapper != null) && (mServiceVersion >= 2))
      {
        super.subscribe(paramString, paramBundle, paramSubscriptionCallback);
        return;
      }
      if (paramBundle == null)
      {
        MediaBrowserCompatApi21.subscribe(mBrowserObj, paramString, MediaBrowserCompat.SubscriptionCallback.access$200(paramSubscriptionCallback));
        return;
      }
      MediaBrowserCompatApi26.subscribe(mBrowserObj, paramString, paramBundle, MediaBrowserCompat.SubscriptionCallback.access$200(paramSubscriptionCallback));
    }
    
    public void unsubscribe(@NonNull String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      if ((mServiceBinderWrapper != null) && (mServiceVersion >= 2))
      {
        super.unsubscribe(paramString, paramSubscriptionCallback);
        return;
      }
      if (paramSubscriptionCallback == null)
      {
        MediaBrowserCompatApi21.unsubscribe(mBrowserObj, paramString);
        return;
      }
      MediaBrowserCompatApi26.unsubscribe(mBrowserObj, paramString, MediaBrowserCompat.SubscriptionCallback.access$200(paramSubscriptionCallback));
    }
  }
  
  static class MediaBrowserImplBase
    implements MediaBrowserCompat.MediaBrowserImpl, MediaBrowserCompat.MediaBrowserServiceCallbackImpl
  {
    static final int CONNECT_STATE_CONNECTED = 3;
    static final int CONNECT_STATE_CONNECTING = 2;
    static final int CONNECT_STATE_DISCONNECTED = 1;
    static final int CONNECT_STATE_DISCONNECTING = 0;
    static final int CONNECT_STATE_SUSPENDED = 4;
    final MediaBrowserCompat.ConnectionCallback mCallback;
    Messenger mCallbacksMessenger;
    final Context mContext;
    private Bundle mExtras;
    final MediaBrowserCompat.CallbackHandler mHandler = new MediaBrowserCompat.CallbackHandler(this);
    private MediaSessionCompat.Token mMediaSessionToken;
    final Bundle mRootHints;
    private String mRootId;
    MediaBrowserCompat.ServiceBinderWrapper mServiceBinderWrapper;
    final ComponentName mServiceComponent;
    MediaServiceConnection mServiceConnection;
    int mState = 1;
    private final ArrayMap<String, MediaBrowserCompat.Subscription> mSubscriptions = new ArrayMap();
    
    public MediaBrowserImplBase(Context paramContext, ComponentName paramComponentName, MediaBrowserCompat.ConnectionCallback paramConnectionCallback, Bundle paramBundle)
    {
      if (paramContext == null) {
        throw new IllegalArgumentException("context must not be null");
      }
      if (paramComponentName == null) {
        throw new IllegalArgumentException("service component must not be null");
      }
      if (paramConnectionCallback == null) {
        throw new IllegalArgumentException("connection callback must not be null");
      }
      mContext = paramContext;
      mServiceComponent = paramComponentName;
      mCallback = paramConnectionCallback;
      if (paramBundle == null) {
        paramContext = null;
      } else {
        paramContext = new Bundle(paramBundle);
      }
      mRootHints = paramContext;
    }
    
    private static String getStateLabel(int paramInt)
    {
      switch (paramInt)
      {
      default: 
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("UNKNOWN/");
        localStringBuilder.append(paramInt);
        return localStringBuilder.toString();
      case 4: 
        return "CONNECT_STATE_SUSPENDED";
      case 3: 
        return "CONNECT_STATE_CONNECTED";
      case 2: 
        return "CONNECT_STATE_CONNECTING";
      case 1: 
        return "CONNECT_STATE_DISCONNECTED";
      }
      return "CONNECT_STATE_DISCONNECTING";
    }
    
    private boolean isCurrent(Messenger paramMessenger, String paramString)
    {
      if ((mCallbacksMessenger == paramMessenger) && (mState != 0) && (mState != 1)) {
        return true;
      }
      if ((mState != 0) && (mState != 1))
      {
        paramMessenger = new StringBuilder();
        paramMessenger.append(paramString);
        paramMessenger.append(" for ");
        paramMessenger.append(mServiceComponent);
        paramMessenger.append(" with mCallbacksMessenger=");
        paramMessenger.append(mCallbacksMessenger);
        paramMessenger.append(" this=");
        paramMessenger.append(this);
        Log.i("MediaBrowserCompat", paramMessenger.toString());
      }
      return false;
    }
    
    public void connect()
    {
      if ((mState != 0) && (mState != 1))
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("connect() called while neigther disconnecting nor disconnected (state=");
        localStringBuilder.append(getStateLabel(mState));
        localStringBuilder.append(")");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      mState = 2;
      mHandler.post(new Runnable()
      {
        public void run()
        {
          if (mState == 0) {
            return;
          }
          mState = 2;
          if ((MediaBrowserCompat.DEBUG) && (mServiceConnection != null))
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("mServiceConnection should be null. Instead it is ");
            ((StringBuilder)localObject).append(mServiceConnection);
            throw new RuntimeException(((StringBuilder)localObject).toString());
          }
          if (mServiceBinderWrapper != null)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("mServiceBinderWrapper should be null. Instead it is ");
            ((StringBuilder)localObject).append(mServiceBinderWrapper);
            throw new RuntimeException(((StringBuilder)localObject).toString());
          }
          if (mCallbacksMessenger != null)
          {
            localObject = new StringBuilder();
            ((StringBuilder)localObject).append("mCallbacksMessenger should be null. Instead it is ");
            ((StringBuilder)localObject).append(mCallbacksMessenger);
            throw new RuntimeException(((StringBuilder)localObject).toString());
          }
          Object localObject = new Intent("android.media.browse.MediaBrowserService");
          ((Intent)localObject).setComponent(mServiceComponent);
          mServiceConnection = new MediaBrowserCompat.MediaBrowserImplBase.MediaServiceConnection(MediaBrowserCompat.MediaBrowserImplBase.this);
          try
          {
            bool = mContext.bindService((Intent)localObject, mServiceConnection, 1);
          }
          catch (Exception localException)
          {
            boolean bool;
            for (;;) {}
          }
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("Failed binding to service ");
          ((StringBuilder)localObject).append(mServiceComponent);
          Log.e("MediaBrowserCompat", ((StringBuilder)localObject).toString());
          bool = false;
          if (!bool)
          {
            forceCloseConnection();
            mCallback.onConnectionFailed();
          }
          if (MediaBrowserCompat.DEBUG)
          {
            Log.d("MediaBrowserCompat", "connect...");
            dump();
          }
        }
      });
    }
    
    public void disconnect()
    {
      mState = 0;
      mHandler.post(new Runnable()
      {
        public void run()
        {
          if (mCallbacksMessenger != null) {}
          try
          {
            mServiceBinderWrapper.disconnect(mCallbacksMessenger);
          }
          catch (RemoteException localRemoteException)
          {
            StringBuilder localStringBuilder;
            int i;
            for (;;) {}
          }
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("RemoteException during connect for ");
          localStringBuilder.append(mServiceComponent);
          Log.w("MediaBrowserCompat", localStringBuilder.toString());
          i = mState;
          forceCloseConnection();
          if (i != 0) {
            mState = i;
          }
          if (MediaBrowserCompat.DEBUG)
          {
            Log.d("MediaBrowserCompat", "disconnect...");
            dump();
          }
        }
      });
    }
    
    void dump()
    {
      Log.d("MediaBrowserCompat", "MediaBrowserCompat...");
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mServiceComponent=");
      localStringBuilder.append(mServiceComponent);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mCallback=");
      localStringBuilder.append(mCallback);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mRootHints=");
      localStringBuilder.append(mRootHints);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mState=");
      localStringBuilder.append(getStateLabel(mState));
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mServiceConnection=");
      localStringBuilder.append(mServiceConnection);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mServiceBinderWrapper=");
      localStringBuilder.append(mServiceBinderWrapper);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mCallbacksMessenger=");
      localStringBuilder.append(mCallbacksMessenger);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mRootId=");
      localStringBuilder.append(mRootId);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("  mMediaSessionToken=");
      localStringBuilder.append(mMediaSessionToken);
      Log.d("MediaBrowserCompat", localStringBuilder.toString());
    }
    
    void forceCloseConnection()
    {
      if (mServiceConnection != null) {
        mContext.unbindService(mServiceConnection);
      }
      mState = 1;
      mServiceConnection = null;
      mServiceBinderWrapper = null;
      mCallbacksMessenger = null;
      mHandler.setCallbacksMessenger(null);
      mRootId = null;
      mMediaSessionToken = null;
    }
    
    @Nullable
    public Bundle getExtras()
    {
      if (!isConnected())
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("getExtras() called while not connected (state=");
        localStringBuilder.append(getStateLabel(mState));
        localStringBuilder.append(")");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      return mExtras;
    }
    
    public void getItem(@NonNull final String paramString, @NonNull final MediaBrowserCompat.ItemCallback paramItemCallback)
    {
      if (TextUtils.isEmpty(paramString)) {
        throw new IllegalArgumentException("mediaId is empty");
      }
      if (paramItemCallback == null) {
        throw new IllegalArgumentException("cb is null");
      }
      if (!isConnected())
      {
        Log.i("MediaBrowserCompat", "Not connected, unable to retrieve the MediaItem.");
        mHandler.post(new Runnable()
        {
          public void run()
          {
            paramItemCallback.onError(paramString);
          }
        });
        return;
      }
      Object localObject = new MediaBrowserCompat.ItemReceiver(paramString, paramItemCallback, mHandler);
      try
      {
        mServiceBinderWrapper.getMediaItem(paramString, (ResultReceiver)localObject, mCallbacksMessenger);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        for (;;) {}
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Remote error getting media item: ");
      ((StringBuilder)localObject).append(paramString);
      Log.i("MediaBrowserCompat", ((StringBuilder)localObject).toString());
      mHandler.post(new Runnable()
      {
        public void run()
        {
          paramItemCallback.onError(paramString);
        }
      });
    }
    
    @NonNull
    public String getRoot()
    {
      if (!isConnected())
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("getRoot() called while not connected(state=");
        localStringBuilder.append(getStateLabel(mState));
        localStringBuilder.append(")");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      return mRootId;
    }
    
    @NonNull
    public ComponentName getServiceComponent()
    {
      if (!isConnected())
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("getServiceComponent() called while not connected (state=");
        localStringBuilder.append(mState);
        localStringBuilder.append(")");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      return mServiceComponent;
    }
    
    @NonNull
    public MediaSessionCompat.Token getSessionToken()
    {
      if (!isConnected())
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("getSessionToken() called while not connected(state=");
        localStringBuilder.append(mState);
        localStringBuilder.append(")");
        throw new IllegalStateException(localStringBuilder.toString());
      }
      return mMediaSessionToken;
    }
    
    public boolean isConnected()
    {
      return mState == 3;
    }
    
    public void onConnectionFailed(Messenger paramMessenger)
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("onConnectFailed for ");
      localStringBuilder.append(mServiceComponent);
      Log.e("MediaBrowserCompat", localStringBuilder.toString());
      if (!isCurrent(paramMessenger, "onConnectFailed")) {
        return;
      }
      if (mState != 2)
      {
        paramMessenger = new StringBuilder();
        paramMessenger.append("onConnect from service while mState=");
        paramMessenger.append(getStateLabel(mState));
        paramMessenger.append("... ignoring");
        Log.w("MediaBrowserCompat", paramMessenger.toString());
        return;
      }
      forceCloseConnection();
      mCallback.onConnectionFailed();
    }
    
    public void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle)
    {
      if (!isCurrent(paramMessenger, "onLoadChildren")) {
        return;
      }
      if (MediaBrowserCompat.DEBUG)
      {
        paramMessenger = new StringBuilder();
        paramMessenger.append("onLoadChildren for ");
        paramMessenger.append(mServiceComponent);
        paramMessenger.append(" id=");
        paramMessenger.append(paramString);
        Log.d("MediaBrowserCompat", paramMessenger.toString());
      }
      paramMessenger = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
      if (paramMessenger == null)
      {
        if (MediaBrowserCompat.DEBUG)
        {
          paramMessenger = new StringBuilder();
          paramMessenger.append("onLoadChildren for id that isn't subscribed id=");
          paramMessenger.append(paramString);
          Log.d("MediaBrowserCompat", paramMessenger.toString());
        }
        return;
      }
      paramMessenger = paramMessenger.getCallback(mContext, paramBundle);
      if (paramMessenger != null)
      {
        if (paramBundle == null)
        {
          if (paramList == null)
          {
            paramMessenger.onError(paramString);
            return;
          }
          paramMessenger.onChildrenLoaded(paramString, paramList);
          return;
        }
        if (paramList == null)
        {
          paramMessenger.onError(paramString, paramBundle);
          return;
        }
        paramMessenger.onChildrenLoaded(paramString, paramList, paramBundle);
      }
    }
    
    public void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle)
    {
      if (!isCurrent(paramMessenger, "onConnect")) {
        return;
      }
      if (mState != 2)
      {
        paramMessenger = new StringBuilder();
        paramMessenger.append("onConnect from service while mState=");
        paramMessenger.append(getStateLabel(mState));
        paramMessenger.append("... ignoring");
        Log.w("MediaBrowserCompat", paramMessenger.toString());
        return;
      }
      mRootId = paramString;
      mMediaSessionToken = paramToken;
      mExtras = paramBundle;
      mState = 3;
      if (MediaBrowserCompat.DEBUG)
      {
        Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
        dump();
      }
      mCallback.onConnected();
      try
      {
        paramMessenger = mSubscriptions.entrySet().iterator();
        while (paramMessenger.hasNext())
        {
          paramToken = (Map.Entry)paramMessenger.next();
          paramString = (String)paramToken.getKey();
          paramBundle = (MediaBrowserCompat.Subscription)paramToken.getValue();
          paramToken = paramBundle.getCallbacks();
          paramBundle = paramBundle.getOptionsList();
          int i = 0;
          while (i < paramToken.size())
          {
            mServiceBinderWrapper.addSubscription(paramString, MediaBrowserCompat.SubscriptionCallback.access$000((MediaBrowserCompat.SubscriptionCallback)paramToken.get(i)), (Bundle)paramBundle.get(i), mCallbacksMessenger);
            i += 1;
          }
        }
      }
      catch (RemoteException paramMessenger)
      {
        for (;;) {}
      }
      Log.d("MediaBrowserCompat", "addSubscription failed with RemoteException.");
    }
    
    public void search(@NonNull final String paramString, final Bundle paramBundle, @NonNull final MediaBrowserCompat.SearchCallback paramSearchCallback)
    {
      if (!isConnected())
      {
        paramString = new StringBuilder();
        paramString.append("search() called while not connected (state=");
        paramString.append(getStateLabel(mState));
        paramString.append(")");
        throw new IllegalStateException(paramString.toString());
      }
      MediaBrowserCompat.SearchResultReceiver localSearchResultReceiver = new MediaBrowserCompat.SearchResultReceiver(paramString, paramBundle, paramSearchCallback, mHandler);
      try
      {
        mServiceBinderWrapper.search(paramString, paramBundle, localSearchResultReceiver, mCallbacksMessenger);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Remote error searching items with query: ");
        localStringBuilder.append(paramString);
        Log.i("MediaBrowserCompat", localStringBuilder.toString(), localRemoteException);
        mHandler.post(new Runnable()
        {
          public void run()
          {
            paramSearchCallback.onError(paramString, paramBundle);
          }
        });
      }
    }
    
    public void sendCustomAction(@NonNull final String paramString, final Bundle paramBundle, @Nullable final MediaBrowserCompat.CustomActionCallback paramCustomActionCallback)
    {
      if (!isConnected())
      {
        paramCustomActionCallback = new StringBuilder();
        paramCustomActionCallback.append("Cannot send a custom action (");
        paramCustomActionCallback.append(paramString);
        paramCustomActionCallback.append(") with ");
        paramCustomActionCallback.append("extras ");
        paramCustomActionCallback.append(paramBundle);
        paramCustomActionCallback.append(" because the browser is not connected to the ");
        paramCustomActionCallback.append("service.");
        throw new IllegalStateException(paramCustomActionCallback.toString());
      }
      MediaBrowserCompat.CustomActionResultReceiver localCustomActionResultReceiver = new MediaBrowserCompat.CustomActionResultReceiver(paramString, paramBundle, paramCustomActionCallback, mHandler);
      try
      {
        mServiceBinderWrapper.sendCustomAction(paramString, paramBundle, localCustomActionResultReceiver, mCallbacksMessenger);
        return;
      }
      catch (RemoteException localRemoteException)
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Remote error sending a custom action: action=");
        localStringBuilder.append(paramString);
        localStringBuilder.append(", extras=");
        localStringBuilder.append(paramBundle);
        Log.i("MediaBrowserCompat", localStringBuilder.toString(), localRemoteException);
        if (paramCustomActionCallback != null) {
          mHandler.post(new Runnable()
          {
            public void run()
            {
              paramCustomActionCallback.onError(paramString, paramBundle, null);
            }
          });
        }
      }
    }
    
    public void subscribe(@NonNull String paramString, Bundle paramBundle, @NonNull MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      MediaBrowserCompat.Subscription localSubscription2 = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
      MediaBrowserCompat.Subscription localSubscription1 = localSubscription2;
      if (localSubscription2 == null)
      {
        localSubscription1 = new MediaBrowserCompat.Subscription();
        mSubscriptions.put(paramString, localSubscription1);
      }
      if (paramBundle == null) {
        paramBundle = null;
      } else {
        paramBundle = new Bundle(paramBundle);
      }
      localSubscription1.putCallback(mContext, paramBundle, paramSubscriptionCallback);
      if (isConnected()) {}
      try
      {
        mServiceBinderWrapper.addSubscription(paramString, MediaBrowserCompat.SubscriptionCallback.access$000(paramSubscriptionCallback), paramBundle, mCallbacksMessenger);
        return;
      }
      catch (RemoteException paramBundle)
      {
        for (;;) {}
      }
      paramBundle = new StringBuilder();
      paramBundle.append("addSubscription failed with RemoteException parentId=");
      paramBundle.append(paramString);
      Log.d("MediaBrowserCompat", paramBundle.toString());
    }
    
    public void unsubscribe(@NonNull String paramString, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      MediaBrowserCompat.Subscription localSubscription = (MediaBrowserCompat.Subscription)mSubscriptions.get(paramString);
      if (localSubscription == null) {
        return;
      }
      if (paramSubscriptionCallback == null) {}
      try
      {
        if (!isConnected()) {
          break label170;
        }
        mServiceBinderWrapper.removeSubscription(paramString, null, mCallbacksMessenger);
      }
      catch (RemoteException localRemoteException)
      {
        Object localObject;
        List localList;
        int i;
        for (;;) {}
      }
      localObject = localSubscription.getCallbacks();
      localList = localSubscription.getOptionsList();
      i = ((List)localObject).size() - 1;
      while (i >= 0)
      {
        if (((List)localObject).get(i) == paramSubscriptionCallback)
        {
          if (isConnected()) {
            mServiceBinderWrapper.removeSubscription(paramString, MediaBrowserCompat.SubscriptionCallback.access$000(paramSubscriptionCallback), mCallbacksMessenger);
          }
          ((List)localObject).remove(i);
          localList.remove(i);
        }
        i -= 1;
        continue;
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("removeSubscription failed with RemoteException parentId=");
        ((StringBuilder)localObject).append(paramString);
        Log.d("MediaBrowserCompat", ((StringBuilder)localObject).toString());
      }
      label170:
      if ((localSubscription.isEmpty()) || (paramSubscriptionCallback == null)) {
        mSubscriptions.remove(paramString);
      }
    }
    
    private class MediaServiceConnection
      implements ServiceConnection
    {
      MediaServiceConnection() {}
      
      private void postOrRun(Runnable paramRunnable)
      {
        if (Thread.currentThread() == mHandler.getLooper().getThread())
        {
          paramRunnable.run();
          return;
        }
        mHandler.post(paramRunnable);
      }
      
      boolean isCurrent(String paramString)
      {
        if ((mServiceConnection == this) && (mState != 0) && (mState != 1)) {
          return true;
        }
        if ((mState != 0) && (mState != 1))
        {
          StringBuilder localStringBuilder = new StringBuilder();
          localStringBuilder.append(paramString);
          localStringBuilder.append(" for ");
          localStringBuilder.append(mServiceComponent);
          localStringBuilder.append(" with mServiceConnection=");
          localStringBuilder.append(mServiceConnection);
          localStringBuilder.append(" this=");
          localStringBuilder.append(this);
          Log.i("MediaBrowserCompat", localStringBuilder.toString());
        }
        return false;
      }
      
      public void onServiceConnected(final ComponentName paramComponentName, final IBinder paramIBinder)
      {
        postOrRun(new Runnable()
        {
          public void run()
          {
            if (MediaBrowserCompat.DEBUG)
            {
              localStringBuilder = new StringBuilder();
              localStringBuilder.append("MediaServiceConnection.onServiceConnected name=");
              localStringBuilder.append(paramComponentName);
              localStringBuilder.append(" binder=");
              localStringBuilder.append(paramIBinder);
              Log.d("MediaBrowserCompat", localStringBuilder.toString());
              dump();
            }
            if (!isCurrent("onServiceConnected")) {
              return;
            }
            mServiceBinderWrapper = new MediaBrowserCompat.ServiceBinderWrapper(paramIBinder, mRootHints);
            mCallbacksMessenger = new Messenger(mHandler);
            mHandler.setCallbacksMessenger(mCallbacksMessenger);
            mState = 2;
            try
            {
              if (MediaBrowserCompat.DEBUG)
              {
                Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
                dump();
              }
              mServiceBinderWrapper.connect(mContext, mCallbacksMessenger);
              return;
            }
            catch (RemoteException localRemoteException)
            {
              for (;;) {}
            }
            StringBuilder localStringBuilder = new StringBuilder();
            localStringBuilder.append("RemoteException during connect for ");
            localStringBuilder.append(mServiceComponent);
            Log.w("MediaBrowserCompat", localStringBuilder.toString());
            if (MediaBrowserCompat.DEBUG)
            {
              Log.d("MediaBrowserCompat", "ServiceCallbacks.onConnect...");
              dump();
            }
          }
        });
      }
      
      public void onServiceDisconnected(final ComponentName paramComponentName)
      {
        postOrRun(new Runnable()
        {
          public void run()
          {
            if (MediaBrowserCompat.DEBUG)
            {
              StringBuilder localStringBuilder = new StringBuilder();
              localStringBuilder.append("MediaServiceConnection.onServiceDisconnected name=");
              localStringBuilder.append(paramComponentName);
              localStringBuilder.append(" this=");
              localStringBuilder.append(this);
              localStringBuilder.append(" mServiceConnection=");
              localStringBuilder.append(mServiceConnection);
              Log.d("MediaBrowserCompat", localStringBuilder.toString());
              dump();
            }
            if (!isCurrent("onServiceDisconnected")) {
              return;
            }
            mServiceBinderWrapper = null;
            mCallbacksMessenger = null;
            mHandler.setCallbacksMessenger(null);
            mState = 4;
            mCallback.onConnectionSuspended();
          }
        });
      }
    }
  }
  
  static abstract interface MediaBrowserServiceCallbackImpl
  {
    public abstract void onConnectionFailed(Messenger paramMessenger);
    
    public abstract void onLoadChildren(Messenger paramMessenger, String paramString, List paramList, Bundle paramBundle);
    
    public abstract void onServiceConnected(Messenger paramMessenger, String paramString, MediaSessionCompat.Token paramToken, Bundle paramBundle);
  }
  
  public static class MediaItem
    implements Parcelable
  {
    public static final Parcelable.Creator<MediaItem> CREATOR = new Parcelable.Creator()
    {
      public MediaBrowserCompat.MediaItem createFromParcel(Parcel paramAnonymousParcel)
      {
        return new MediaBrowserCompat.MediaItem(paramAnonymousParcel);
      }
      
      public MediaBrowserCompat.MediaItem[] newArray(int paramAnonymousInt)
      {
        return new MediaBrowserCompat.MediaItem[paramAnonymousInt];
      }
    };
    public static final int FLAG_BROWSABLE = 1;
    public static final int FLAG_PLAYABLE = 2;
    private final MediaDescriptionCompat mDescription;
    private final int mFlags;
    
    MediaItem(Parcel paramParcel)
    {
      mFlags = paramParcel.readInt();
      mDescription = ((MediaDescriptionCompat)MediaDescriptionCompat.CREATOR.createFromParcel(paramParcel));
    }
    
    public MediaItem(@NonNull MediaDescriptionCompat paramMediaDescriptionCompat, int paramInt)
    {
      if (paramMediaDescriptionCompat == null) {
        throw new IllegalArgumentException("description cannot be null");
      }
      if (TextUtils.isEmpty(paramMediaDescriptionCompat.getMediaId())) {
        throw new IllegalArgumentException("description must have a non-empty media id");
      }
      mFlags = paramInt;
      mDescription = paramMediaDescriptionCompat;
    }
    
    public static MediaItem fromMediaItem(Object paramObject)
    {
      if ((paramObject != null) && (Build.VERSION.SDK_INT >= 21))
      {
        int i = MediaBrowserCompatApi21.MediaItem.getFlags(paramObject);
        return new MediaItem(MediaDescriptionCompat.fromMediaDescription(MediaBrowserCompatApi21.MediaItem.getDescription(paramObject)), i);
      }
      return null;
    }
    
    public static List<MediaItem> fromMediaItemList(List<?> paramList)
    {
      if ((paramList != null) && (Build.VERSION.SDK_INT >= 21))
      {
        ArrayList localArrayList = new ArrayList(paramList.size());
        paramList = paramList.iterator();
        while (paramList.hasNext()) {
          localArrayList.add(fromMediaItem(paramList.next()));
        }
        return localArrayList;
      }
      return null;
    }
    
    public int describeContents()
    {
      return 0;
    }
    
    @NonNull
    public MediaDescriptionCompat getDescription()
    {
      return mDescription;
    }
    
    public int getFlags()
    {
      return mFlags;
    }
    
    @Nullable
    public String getMediaId()
    {
      return mDescription.getMediaId();
    }
    
    public boolean isBrowsable()
    {
      return (mFlags & 0x1) != 0;
    }
    
    public boolean isPlayable()
    {
      return (mFlags & 0x2) != 0;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder("MediaItem{");
      localStringBuilder.append("mFlags=");
      localStringBuilder.append(mFlags);
      localStringBuilder.append(", mDescription=");
      localStringBuilder.append(mDescription);
      localStringBuilder.append('}');
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      paramParcel.writeInt(mFlags);
      mDescription.writeToParcel(paramParcel, paramInt);
    }
    
    @Retention(RetentionPolicy.SOURCE)
    @RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
    public static @interface Flags {}
  }
  
  public static abstract class SearchCallback
  {
    public SearchCallback() {}
    
    public void onError(@NonNull String paramString, Bundle paramBundle) {}
    
    public void onSearchResult(@NonNull String paramString, Bundle paramBundle, @NonNull List<MediaBrowserCompat.MediaItem> paramList) {}
  }
  
  private static class SearchResultReceiver
    extends ResultReceiver
  {
    private final MediaBrowserCompat.SearchCallback mCallback;
    private final Bundle mExtras;
    private final String mQuery;
    
    SearchResultReceiver(String paramString, Bundle paramBundle, MediaBrowserCompat.SearchCallback paramSearchCallback, Handler paramHandler)
    {
      super();
      mQuery = paramString;
      mExtras = paramBundle;
      mCallback = paramSearchCallback;
    }
    
    protected void onReceiveResult(int paramInt, Bundle paramBundle)
    {
      if (paramBundle != null) {
        paramBundle.setClassLoader(MediaBrowserCompat.class.getClassLoader());
      }
      if ((paramInt == 0) && (paramBundle != null) && (paramBundle.containsKey("search_results")))
      {
        Parcelable[] arrayOfParcelable = paramBundle.getParcelableArray("search_results");
        paramBundle = null;
        if (arrayOfParcelable != null)
        {
          ArrayList localArrayList = new ArrayList();
          int i = arrayOfParcelable.length;
          paramInt = 0;
          for (;;)
          {
            paramBundle = localArrayList;
            if (paramInt >= i) {
              break;
            }
            localArrayList.add((MediaBrowserCompat.MediaItem)arrayOfParcelable[paramInt]);
            paramInt += 1;
          }
        }
        mCallback.onSearchResult(mQuery, mExtras, paramBundle);
        return;
      }
      mCallback.onError(mQuery, mExtras);
    }
  }
  
  private static class ServiceBinderWrapper
  {
    private Messenger mMessenger;
    private Bundle mRootHints;
    
    public ServiceBinderWrapper(IBinder paramIBinder, Bundle paramBundle)
    {
      mMessenger = new Messenger(paramIBinder);
      mRootHints = paramBundle;
    }
    
    private void sendRequest(int paramInt, Bundle paramBundle, Messenger paramMessenger)
      throws RemoteException
    {
      Message localMessage = Message.obtain();
      what = paramInt;
      arg1 = 1;
      localMessage.setData(paramBundle);
      replyTo = paramMessenger;
      mMessenger.send(localMessage);
    }
    
    void addSubscription(String paramString, IBinder paramIBinder, Bundle paramBundle, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      BundleCompat.putBinder(localBundle, "data_callback_token", paramIBinder);
      localBundle.putBundle("data_options", paramBundle);
      sendRequest(3, localBundle, paramMessenger);
    }
    
    void connect(Context paramContext, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_package_name", paramContext.getPackageName());
      localBundle.putBundle("data_root_hints", mRootHints);
      sendRequest(1, localBundle, paramMessenger);
    }
    
    void disconnect(Messenger paramMessenger)
      throws RemoteException
    {
      sendRequest(2, null, paramMessenger);
    }
    
    void getMediaItem(String paramString, ResultReceiver paramResultReceiver, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      localBundle.putParcelable("data_result_receiver", paramResultReceiver);
      sendRequest(5, localBundle, paramMessenger);
    }
    
    void registerCallbackMessenger(Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putBundle("data_root_hints", mRootHints);
      sendRequest(6, localBundle, paramMessenger);
    }
    
    void removeSubscription(String paramString, IBinder paramIBinder, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_media_item_id", paramString);
      BundleCompat.putBinder(localBundle, "data_callback_token", paramIBinder);
      sendRequest(4, localBundle, paramMessenger);
    }
    
    void search(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_search_query", paramString);
      localBundle.putBundle("data_search_extras", paramBundle);
      localBundle.putParcelable("data_result_receiver", paramResultReceiver);
      sendRequest(8, localBundle, paramMessenger);
    }
    
    void sendCustomAction(String paramString, Bundle paramBundle, ResultReceiver paramResultReceiver, Messenger paramMessenger)
      throws RemoteException
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("data_custom_action", paramString);
      localBundle.putBundle("data_custom_action_extras", paramBundle);
      localBundle.putParcelable("data_result_receiver", paramResultReceiver);
      sendRequest(9, localBundle, paramMessenger);
    }
    
    void unregisterCallbackMessenger(Messenger paramMessenger)
      throws RemoteException
    {
      sendRequest(7, null, paramMessenger);
    }
  }
  
  private static class Subscription
  {
    private final List<MediaBrowserCompat.SubscriptionCallback> mCallbacks = new ArrayList();
    private final List<Bundle> mOptionsList = new ArrayList();
    
    public Subscription() {}
    
    public MediaBrowserCompat.SubscriptionCallback getCallback(Context paramContext, Bundle paramBundle)
    {
      if (paramBundle != null) {
        paramBundle.setClassLoader(paramContext.getClassLoader());
      }
      int i = 0;
      while (i < mOptionsList.size())
      {
        if (MediaBrowserCompatUtils.areSameOptions((Bundle)mOptionsList.get(i), paramBundle)) {
          return (MediaBrowserCompat.SubscriptionCallback)mCallbacks.get(i);
        }
        i += 1;
      }
      return null;
    }
    
    public List<MediaBrowserCompat.SubscriptionCallback> getCallbacks()
    {
      return mCallbacks;
    }
    
    public List<Bundle> getOptionsList()
    {
      return mOptionsList;
    }
    
    public boolean isEmpty()
    {
      return mCallbacks.isEmpty();
    }
    
    public void putCallback(Context paramContext, Bundle paramBundle, MediaBrowserCompat.SubscriptionCallback paramSubscriptionCallback)
    {
      if (paramBundle != null) {
        paramBundle.setClassLoader(paramContext.getClassLoader());
      }
      int i = 0;
      while (i < mOptionsList.size())
      {
        if (MediaBrowserCompatUtils.areSameOptions((Bundle)mOptionsList.get(i), paramBundle))
        {
          mCallbacks.set(i, paramSubscriptionCallback);
          return;
        }
        i += 1;
      }
      mCallbacks.add(paramSubscriptionCallback);
      mOptionsList.add(paramBundle);
    }
  }
  
  public static abstract class SubscriptionCallback
  {
    private final Object mSubscriptionCallbackObj;
    WeakReference<MediaBrowserCompat.Subscription> mSubscriptionRef;
    private final IBinder mToken = new Binder();
    
    public SubscriptionCallback()
    {
      if (Build.VERSION.SDK_INT >= 26)
      {
        mSubscriptionCallbackObj = MediaBrowserCompatApi26.createSubscriptionCallback(new StubApi26());
        return;
      }
      if (Build.VERSION.SDK_INT >= 21)
      {
        mSubscriptionCallbackObj = MediaBrowserCompatApi21.createSubscriptionCallback(new StubApi21());
        return;
      }
      mSubscriptionCallbackObj = null;
    }
    
    private void setSubscription(MediaBrowserCompat.Subscription paramSubscription)
    {
      mSubscriptionRef = new WeakReference(paramSubscription);
    }
    
    public void onChildrenLoaded(@NonNull String paramString, @NonNull List<MediaBrowserCompat.MediaItem> paramList) {}
    
    public void onChildrenLoaded(@NonNull String paramString, @NonNull List<MediaBrowserCompat.MediaItem> paramList, @NonNull Bundle paramBundle) {}
    
    public void onError(@NonNull String paramString) {}
    
    public void onError(@NonNull String paramString, @NonNull Bundle paramBundle) {}
    
    private class StubApi21
      implements MediaBrowserCompatApi21.SubscriptionCallback
    {
      StubApi21() {}
      
      List<MediaBrowserCompat.MediaItem> applyOptions(List<MediaBrowserCompat.MediaItem> paramList, Bundle paramBundle)
      {
        if (paramList == null) {
          return null;
        }
        int i = paramBundle.getInt("android.media.browse.extra.PAGE", -1);
        int m = paramBundle.getInt("android.media.browse.extra.PAGE_SIZE", -1);
        if ((i == -1) && (m == -1)) {
          return paramList;
        }
        int k = m * i;
        int j = k + m;
        if ((i >= 0) && (m >= 1) && (k < paramList.size()))
        {
          i = j;
          if (j > paramList.size()) {
            i = paramList.size();
          }
          return paramList.subList(k, i);
        }
        return Collections.EMPTY_LIST;
      }
      
      public void onChildrenLoaded(@NonNull String paramString, List<?> paramList)
      {
        if (mSubscriptionRef == null) {
          localObject = null;
        } else {
          localObject = (MediaBrowserCompat.Subscription)mSubscriptionRef.get();
        }
        if (localObject == null)
        {
          MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, MediaBrowserCompat.MediaItem.fromMediaItemList(paramList));
          return;
        }
        paramList = MediaBrowserCompat.MediaItem.fromMediaItemList(paramList);
        List localList = ((MediaBrowserCompat.Subscription)localObject).getCallbacks();
        Object localObject = ((MediaBrowserCompat.Subscription)localObject).getOptionsList();
        int i = 0;
        while (i < localList.size())
        {
          Bundle localBundle = (Bundle)((List)localObject).get(i);
          if (localBundle == null) {
            MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, paramList);
          } else {
            onChildrenLoaded(paramString, applyOptions(paramList, localBundle), localBundle);
          }
          i += 1;
        }
      }
      
      public void onError(@NonNull String paramString)
      {
        MediaBrowserCompat.SubscriptionCallback.this.onError(paramString);
      }
    }
    
    private class StubApi26
      extends MediaBrowserCompat.SubscriptionCallback.StubApi21
      implements MediaBrowserCompatApi26.SubscriptionCallback
    {
      StubApi26()
      {
        super();
      }
      
      public void onChildrenLoaded(@NonNull String paramString, List<?> paramList, @NonNull Bundle paramBundle)
      {
        MediaBrowserCompat.SubscriptionCallback.this.onChildrenLoaded(paramString, MediaBrowserCompat.MediaItem.fromMediaItemList(paramList), paramBundle);
      }
      
      public void onError(@NonNull String paramString, @NonNull Bundle paramBundle)
      {
        MediaBrowserCompat.SubscriptionCallback.this.onError(paramString, paramBundle);
      }
    }
  }
}

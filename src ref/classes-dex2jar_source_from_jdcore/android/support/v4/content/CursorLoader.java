package android.support.v4.content;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.os.CancellationSignal;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.Arrays;

public class CursorLoader
  extends AsyncTaskLoader<Cursor>
{
  CancellationSignal mCancellationSignal;
  Cursor mCursor;
  final Loader<Cursor>.ForceLoadContentObserver mObserver = new Loader.ForceLoadContentObserver(this);
  String[] mProjection;
  String mSelection;
  String[] mSelectionArgs;
  String mSortOrder;
  Uri mUri;
  
  public CursorLoader(@NonNull Context paramContext)
  {
    super(paramContext);
  }
  
  public CursorLoader(@NonNull Context paramContext, @NonNull Uri paramUri, @Nullable String[] paramArrayOfString1, @Nullable String paramString1, @Nullable String[] paramArrayOfString2, @Nullable String paramString2)
  {
    super(paramContext);
    mUri = paramUri;
    mProjection = paramArrayOfString1;
    mSelection = paramString1;
    mSelectionArgs = paramArrayOfString2;
    mSortOrder = paramString2;
  }
  
  public void cancelLoadInBackground()
  {
    super.cancelLoadInBackground();
    try
    {
      if (mCancellationSignal != null) {
        mCancellationSignal.cancel();
      }
      return;
    }
    finally {}
  }
  
  public void deliverResult(Cursor paramCursor)
  {
    if (isReset())
    {
      if (paramCursor != null) {
        paramCursor.close();
      }
      return;
    }
    Cursor localCursor = mCursor;
    mCursor = paramCursor;
    if (isStarted()) {
      super.deliverResult(paramCursor);
    }
    if ((localCursor != null) && (localCursor != paramCursor) && (!localCursor.isClosed())) {
      localCursor.close();
    }
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    super.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mUri=");
    paramPrintWriter.println(mUri);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mProjection=");
    paramPrintWriter.println(Arrays.toString(mProjection));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSelection=");
    paramPrintWriter.println(mSelection);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSelectionArgs=");
    paramPrintWriter.println(Arrays.toString(mSelectionArgs));
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mSortOrder=");
    paramPrintWriter.println(mSortOrder);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mCursor=");
    paramPrintWriter.println(mCursor);
    paramPrintWriter.print(paramString);
    paramPrintWriter.print("mContentChanged=");
    paramPrintWriter.println(mContentChanged);
  }
  
  @Nullable
  public String[] getProjection()
  {
    return mProjection;
  }
  
  @Nullable
  public String getSelection()
  {
    return mSelection;
  }
  
  @Nullable
  public String[] getSelectionArgs()
  {
    return mSelectionArgs;
  }
  
  @Nullable
  public String getSortOrder()
  {
    return mSortOrder;
  }
  
  @NonNull
  public Uri getUri()
  {
    return mUri;
  }
  
  /* Error */
  public Cursor loadInBackground()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: invokevirtual 139	android/support/v4/content/CursorLoader:isLoadInBackgroundCanceled	()Z
    //   6: ifeq +11 -> 17
    //   9: new 141	android/support/v4/os/OperationCanceledException
    //   12: dup
    //   13: invokespecial 143	android/support/v4/os/OperationCanceledException:<init>	()V
    //   16: athrow
    //   17: aload_0
    //   18: new 54	android/support/v4/os/CancellationSignal
    //   21: dup
    //   22: invokespecial 144	android/support/v4/os/CancellationSignal:<init>	()V
    //   25: putfield 52	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_0
    //   31: invokevirtual 148	android/support/v4/content/CursorLoader:getContext	()Landroid/content/Context;
    //   34: invokevirtual 154	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   37: aload_0
    //   38: getfield 38	android/support/v4/content/CursorLoader:mUri	Landroid/net/Uri;
    //   41: aload_0
    //   42: getfield 40	android/support/v4/content/CursorLoader:mProjection	[Ljava/lang/String;
    //   45: aload_0
    //   46: getfield 42	android/support/v4/content/CursorLoader:mSelection	Ljava/lang/String;
    //   49: aload_0
    //   50: getfield 44	android/support/v4/content/CursorLoader:mSelectionArgs	[Ljava/lang/String;
    //   53: aload_0
    //   54: getfield 46	android/support/v4/content/CursorLoader:mSortOrder	Ljava/lang/String;
    //   57: aload_0
    //   58: getfield 52	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   61: invokestatic 160	android/support/v4/content/ContentResolverCompat:query	(Landroid/content/ContentResolver;Landroid/net/Uri;[Ljava/lang/String;Ljava/lang/String;[Ljava/lang/String;Ljava/lang/String;Landroid/support/v4/os/CancellationSignal;)Landroid/database/Cursor;
    //   64: astore_1
    //   65: aload_1
    //   66: ifnull +32 -> 98
    //   69: aload_1
    //   70: invokeinterface 164 1 0
    //   75: pop
    //   76: aload_1
    //   77: aload_0
    //   78: getfield 32	android/support/v4/content/CursorLoader:mObserver	Landroid/support/v4/content/Loader$ForceLoadContentObserver;
    //   81: invokeinterface 168 2 0
    //   86: goto +12 -> 98
    //   89: astore_2
    //   90: aload_1
    //   91: invokeinterface 68 1 0
    //   96: aload_2
    //   97: athrow
    //   98: aload_0
    //   99: monitorenter
    //   100: aload_0
    //   101: aconst_null
    //   102: putfield 52	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   105: aload_0
    //   106: monitorexit
    //   107: aload_1
    //   108: areturn
    //   109: astore_1
    //   110: aload_0
    //   111: monitorexit
    //   112: aload_1
    //   113: athrow
    //   114: astore_1
    //   115: aload_0
    //   116: monitorenter
    //   117: aload_0
    //   118: aconst_null
    //   119: putfield 52	android/support/v4/content/CursorLoader:mCancellationSignal	Landroid/support/v4/os/CancellationSignal;
    //   122: aload_0
    //   123: monitorexit
    //   124: aload_1
    //   125: athrow
    //   126: astore_1
    //   127: aload_0
    //   128: monitorexit
    //   129: aload_1
    //   130: athrow
    //   131: astore_1
    //   132: aload_0
    //   133: monitorexit
    //   134: aload_1
    //   135: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	136	0	this	CursorLoader
    //   64	44	1	localCursor	Cursor
    //   109	4	1	localObject1	Object
    //   114	11	1	localObject2	Object
    //   126	4	1	localObject3	Object
    //   131	4	1	localObject4	Object
    //   89	8	2	localRuntimeException	RuntimeException
    // Exception table:
    //   from	to	target	type
    //   69	86	89	java/lang/RuntimeException
    //   100	107	109	finally
    //   110	112	109	finally
    //   30	65	114	finally
    //   69	86	114	finally
    //   90	98	114	finally
    //   117	124	126	finally
    //   127	129	126	finally
    //   2	17	131	finally
    //   17	30	131	finally
    //   132	134	131	finally
  }
  
  public void onCanceled(Cursor paramCursor)
  {
    if ((paramCursor != null) && (!paramCursor.isClosed())) {
      paramCursor.close();
    }
  }
  
  protected void onReset()
  {
    super.onReset();
    onStopLoading();
    if ((mCursor != null) && (!mCursor.isClosed())) {
      mCursor.close();
    }
    mCursor = null;
  }
  
  protected void onStartLoading()
  {
    if (mCursor != null) {
      deliverResult(mCursor);
    }
    if ((takeContentChanged()) || (mCursor == null)) {
      forceLoad();
    }
  }
  
  protected void onStopLoading()
  {
    cancelLoad();
  }
  
  public void setProjection(@Nullable String[] paramArrayOfString)
  {
    mProjection = paramArrayOfString;
  }
  
  public void setSelection(@Nullable String paramString)
  {
    mSelection = paramString;
  }
  
  public void setSelectionArgs(@Nullable String[] paramArrayOfString)
  {
    mSelectionArgs = paramArrayOfString;
  }
  
  public void setSortOrder(@Nullable String paramString)
  {
    mSortOrder = paramString;
  }
  
  public void setUri(@NonNull Uri paramUri)
  {
    mUri = paramUri;
  }
}

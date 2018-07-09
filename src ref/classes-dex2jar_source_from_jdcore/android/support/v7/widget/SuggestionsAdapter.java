package android.support.v7.widget;

import android.app.SearchManager;
import android.app.SearchableInfo;
import android.content.ComponentName;
import android.content.ContentResolver;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.content.res.Resources.NotFoundException;
import android.content.res.Resources.Theme;
import android.database.Cursor;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.ResourceCursorAdapter;
import android.support.v7.appcompat.R.attr;
import android.support.v7.appcompat.R.id;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.TextAppearanceSpan;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.WeakHashMap;

class SuggestionsAdapter
  extends ResourceCursorAdapter
  implements View.OnClickListener
{
  private static final boolean DBG = false;
  static final int INVALID_INDEX = -1;
  private static final String LOG_TAG = "SuggestionsAdapter";
  private static final int QUERY_LIMIT = 50;
  static final int REFINE_ALL = 2;
  static final int REFINE_BY_ENTRY = 1;
  static final int REFINE_NONE = 0;
  private boolean mClosed = false;
  private final int mCommitIconResId;
  private int mFlagsCol = -1;
  private int mIconName1Col = -1;
  private int mIconName2Col = -1;
  private final WeakHashMap<String, Drawable.ConstantState> mOutsideDrawablesCache;
  private final Context mProviderContext;
  private int mQueryRefinement = 1;
  private final SearchManager mSearchManager = (SearchManager)mContext.getSystemService("search");
  private final SearchView mSearchView;
  private final SearchableInfo mSearchable;
  private int mText1Col = -1;
  private int mText2Col = -1;
  private int mText2UrlCol = -1;
  private ColorStateList mUrlColor;
  
  public SuggestionsAdapter(Context paramContext, SearchView paramSearchView, SearchableInfo paramSearchableInfo, WeakHashMap<String, Drawable.ConstantState> paramWeakHashMap)
  {
    super(paramContext, paramSearchView.getSuggestionRowLayout(), null, true);
    mSearchView = paramSearchView;
    mSearchable = paramSearchableInfo;
    mCommitIconResId = paramSearchView.getSuggestionCommitIconResId();
    mProviderContext = paramContext;
    mOutsideDrawablesCache = paramWeakHashMap;
  }
  
  private Drawable checkIconCache(String paramString)
  {
    paramString = (Drawable.ConstantState)mOutsideDrawablesCache.get(paramString);
    if (paramString == null) {
      return null;
    }
    return paramString.newDrawable();
  }
  
  private CharSequence formatUrl(CharSequence paramCharSequence)
  {
    if (mUrlColor == null)
    {
      localObject = new TypedValue();
      mContext.getTheme().resolveAttribute(R.attr.textColorSearchUrl, (TypedValue)localObject, true);
      mUrlColor = mContext.getResources().getColorStateList(resourceId);
    }
    Object localObject = new SpannableString(paramCharSequence);
    ((SpannableString)localObject).setSpan(new TextAppearanceSpan(null, 0, 0, mUrlColor, null), 0, paramCharSequence.length(), 33);
    return localObject;
  }
  
  private Drawable getActivityIcon(ComponentName paramComponentName)
  {
    Object localObject = mContext.getPackageManager();
    try
    {
      ActivityInfo localActivityInfo = ((PackageManager)localObject).getActivityInfo(paramComponentName, 128);
      int i = localActivityInfo.getIconResource();
      if (i == 0) {
        return null;
      }
      localObject = ((PackageManager)localObject).getDrawable(paramComponentName.getPackageName(), i, applicationInfo);
      if (localObject == null)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Invalid icon resource ");
        ((StringBuilder)localObject).append(i);
        ((StringBuilder)localObject).append(" for ");
        ((StringBuilder)localObject).append(paramComponentName.flattenToShortString());
        Log.w("SuggestionsAdapter", ((StringBuilder)localObject).toString());
        return null;
      }
      return localObject;
    }
    catch (PackageManager.NameNotFoundException paramComponentName)
    {
      Log.w("SuggestionsAdapter", paramComponentName.toString());
    }
    return null;
  }
  
  private Drawable getActivityIconWithCache(ComponentName paramComponentName)
  {
    String str = paramComponentName.flattenToShortString();
    boolean bool = mOutsideDrawablesCache.containsKey(str);
    Object localObject = null;
    if (bool)
    {
      paramComponentName = (Drawable.ConstantState)mOutsideDrawablesCache.get(str);
      if (paramComponentName == null) {
        return null;
      }
      return paramComponentName.newDrawable(mProviderContext.getResources());
    }
    Drawable localDrawable = getActivityIcon(paramComponentName);
    if (localDrawable == null) {
      paramComponentName = localObject;
    } else {
      paramComponentName = localDrawable.getConstantState();
    }
    mOutsideDrawablesCache.put(str, paramComponentName);
    return localDrawable;
  }
  
  public static String getColumnString(Cursor paramCursor, String paramString)
  {
    return getStringOrNull(paramCursor, paramCursor.getColumnIndex(paramString));
  }
  
  private Drawable getDefaultIcon1(Cursor paramCursor)
  {
    paramCursor = getActivityIconWithCache(mSearchable.getSearchActivity());
    if (paramCursor != null) {
      return paramCursor;
    }
    return mContext.getPackageManager().getDefaultActivityIcon();
  }
  
  /* Error */
  private Drawable getDrawable(Uri paramUri)
  {
    // Byte code:
    //   0: ldc_w 290
    //   3: aload_1
    //   4: invokevirtual 295	android/net/Uri:getScheme	()Ljava/lang/String;
    //   7: invokevirtual 300	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   10: istore_2
    //   11: iload_2
    //   12: ifeq +45 -> 57
    //   15: aload_0
    //   16: aload_1
    //   17: invokevirtual 303	android/support/v7/widget/SuggestionsAdapter:getDrawableFromResourceUri	(Landroid/net/Uri;)Landroid/graphics/drawable/Drawable;
    //   20: astore_3
    //   21: aload_3
    //   22: areturn
    //   23: new 211	java/lang/StringBuilder
    //   26: dup
    //   27: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   30: astore_3
    //   31: aload_3
    //   32: ldc_w 305
    //   35: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   38: pop
    //   39: aload_3
    //   40: aload_1
    //   41: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   44: pop
    //   45: new 284	java/io/FileNotFoundException
    //   48: dup
    //   49: aload_3
    //   50: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokespecial 311	java/io/FileNotFoundException:<init>	(Ljava/lang/String;)V
    //   56: athrow
    //   57: aload_0
    //   58: getfield 101	android/support/v7/widget/SuggestionsAdapter:mProviderContext	Landroid/content/Context;
    //   61: invokevirtual 315	android/content/Context:getContentResolver	()Landroid/content/ContentResolver;
    //   64: aload_1
    //   65: invokevirtual 321	android/content/ContentResolver:openInputStream	(Landroid/net/Uri;)Ljava/io/InputStream;
    //   68: astore 4
    //   70: aload 4
    //   72: ifnonnull +37 -> 109
    //   75: new 211	java/lang/StringBuilder
    //   78: dup
    //   79: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   82: astore_3
    //   83: aload_3
    //   84: ldc_w 323
    //   87: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   90: pop
    //   91: aload_3
    //   92: aload_1
    //   93: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   96: pop
    //   97: new 284	java/io/FileNotFoundException
    //   100: dup
    //   101: aload_3
    //   102: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   105: invokespecial 311	java/io/FileNotFoundException:<init>	(Ljava/lang/String;)V
    //   108: athrow
    //   109: aload 4
    //   111: aconst_null
    //   112: invokestatic 327	android/graphics/drawable/Drawable:createFromStream	(Ljava/io/InputStream;Ljava/lang/String;)Landroid/graphics/drawable/Drawable;
    //   115: astore_3
    //   116: aload 4
    //   118: invokevirtual 332	java/io/InputStream:close	()V
    //   121: aload_3
    //   122: areturn
    //   123: astore 4
    //   125: new 211	java/lang/StringBuilder
    //   128: dup
    //   129: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   132: astore 5
    //   134: aload 5
    //   136: ldc_w 334
    //   139: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: pop
    //   143: aload 5
    //   145: aload_1
    //   146: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   149: pop
    //   150: ldc 19
    //   152: aload 5
    //   154: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   157: aload 4
    //   159: invokestatic 338	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   162: pop
    //   163: aload_3
    //   164: areturn
    //   165: astore_3
    //   166: aload 4
    //   168: invokevirtual 332	java/io/InputStream:close	()V
    //   171: goto +43 -> 214
    //   174: astore 4
    //   176: new 211	java/lang/StringBuilder
    //   179: dup
    //   180: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   183: astore 5
    //   185: aload 5
    //   187: ldc_w 334
    //   190: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   193: pop
    //   194: aload 5
    //   196: aload_1
    //   197: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   200: pop
    //   201: ldc 19
    //   203: aload 5
    //   205: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   208: aload 4
    //   210: invokestatic 338	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   213: pop
    //   214: aload_3
    //   215: athrow
    //   216: astore_3
    //   217: new 211	java/lang/StringBuilder
    //   220: dup
    //   221: invokespecial 212	java/lang/StringBuilder:<init>	()V
    //   224: astore 4
    //   226: aload 4
    //   228: ldc_w 340
    //   231: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   234: pop
    //   235: aload 4
    //   237: aload_1
    //   238: invokevirtual 308	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   241: pop
    //   242: aload 4
    //   244: ldc_w 342
    //   247: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   250: pop
    //   251: aload 4
    //   253: aload_3
    //   254: invokevirtual 345	java/io/FileNotFoundException:getMessage	()Ljava/lang/String;
    //   257: invokevirtual 218	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: pop
    //   261: ldc 19
    //   263: aload 4
    //   265: invokevirtual 229	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   268: invokestatic 235	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   271: pop
    //   272: aconst_null
    //   273: areturn
    //   274: astore_3
    //   275: goto -252 -> 23
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	278	0	this	SuggestionsAdapter
    //   0	278	1	paramUri	Uri
    //   10	2	2	bool	boolean
    //   20	144	3	localObject1	Object
    //   165	50	3	localObject2	Object
    //   216	38	3	localFileNotFoundException	FileNotFoundException
    //   274	1	3	localNotFoundException	Resources.NotFoundException
    //   68	49	4	localInputStream	java.io.InputStream
    //   123	44	4	localIOException1	java.io.IOException
    //   174	35	4	localIOException2	java.io.IOException
    //   224	40	4	localStringBuilder1	StringBuilder
    //   132	72	5	localStringBuilder2	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   116	121	123	java/io/IOException
    //   109	116	165	finally
    //   166	171	174	java/io/IOException
    //   0	11	216	java/io/FileNotFoundException
    //   15	21	216	java/io/FileNotFoundException
    //   23	57	216	java/io/FileNotFoundException
    //   57	70	216	java/io/FileNotFoundException
    //   75	109	216	java/io/FileNotFoundException
    //   116	121	216	java/io/FileNotFoundException
    //   125	163	216	java/io/FileNotFoundException
    //   166	171	216	java/io/FileNotFoundException
    //   176	214	216	java/io/FileNotFoundException
    //   214	216	216	java/io/FileNotFoundException
    //   15	21	274	android/content/res/Resources$NotFoundException
  }
  
  private Drawable getDrawableFromResourceValue(String paramString)
  {
    if ((paramString != null) && (!paramString.isEmpty())) {
      if ("0".equals(paramString)) {
        return null;
      }
    }
    try
    {
      int i = Integer.parseInt(paramString);
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("android.resource://");
      ((StringBuilder)localObject).append(mProviderContext.getPackageName());
      ((StringBuilder)localObject).append("/");
      ((StringBuilder)localObject).append(i);
      localObject = ((StringBuilder)localObject).toString();
      Drawable localDrawable = checkIconCache((String)localObject);
      if (localDrawable != null) {
        return localDrawable;
      }
      localDrawable = ContextCompat.getDrawable(mProviderContext, i);
      storeInIconCache((String)localObject, localDrawable);
      return localDrawable;
    }
    catch (NumberFormatException localNumberFormatException)
    {
      Object localObject;
      for (;;) {}
    }
    catch (Resources.NotFoundException localNotFoundException)
    {
      for (;;) {}
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Icon resource not found: ");
    ((StringBuilder)localObject).append(paramString);
    Log.w("SuggestionsAdapter", ((StringBuilder)localObject).toString());
    return null;
    localObject = checkIconCache(paramString);
    if (localObject != null) {
      return localObject;
    }
    localObject = getDrawable(Uri.parse(paramString));
    storeInIconCache(paramString, (Drawable)localObject);
    return localObject;
    return null;
  }
  
  private Drawable getIcon1(Cursor paramCursor)
  {
    if (mIconName1Col == -1) {
      return null;
    }
    Drawable localDrawable = getDrawableFromResourceValue(paramCursor.getString(mIconName1Col));
    if (localDrawable != null) {
      return localDrawable;
    }
    return getDefaultIcon1(paramCursor);
  }
  
  private Drawable getIcon2(Cursor paramCursor)
  {
    if (mIconName2Col == -1) {
      return null;
    }
    return getDrawableFromResourceValue(paramCursor.getString(mIconName2Col));
  }
  
  private static String getStringOrNull(Cursor paramCursor, int paramInt)
  {
    if (paramInt == -1) {
      return null;
    }
    try
    {
      paramCursor = paramCursor.getString(paramInt);
      return paramCursor;
    }
    catch (Exception paramCursor)
    {
      Log.e("SuggestionsAdapter", "unexpected error retrieving valid column from cursor, did the remote process die?", paramCursor);
    }
    return null;
  }
  
  private void setViewDrawable(ImageView paramImageView, Drawable paramDrawable, int paramInt)
  {
    paramImageView.setImageDrawable(paramDrawable);
    if (paramDrawable == null)
    {
      paramImageView.setVisibility(paramInt);
      return;
    }
    paramImageView.setVisibility(0);
    paramDrawable.setVisible(false, false);
    paramDrawable.setVisible(true, false);
  }
  
  private void setViewText(TextView paramTextView, CharSequence paramCharSequence)
  {
    paramTextView.setText(paramCharSequence);
    if (TextUtils.isEmpty(paramCharSequence))
    {
      paramTextView.setVisibility(8);
      return;
    }
    paramTextView.setVisibility(0);
  }
  
  private void storeInIconCache(String paramString, Drawable paramDrawable)
  {
    if (paramDrawable != null) {
      mOutsideDrawablesCache.put(paramString, paramDrawable.getConstantState());
    }
  }
  
  private void updateSpinnerState(Cursor paramCursor)
  {
    if (paramCursor != null) {
      paramCursor = paramCursor.getExtras();
    } else {
      paramCursor = null;
    }
    if ((paramCursor != null) && (paramCursor.getBoolean("in_progress"))) {}
  }
  
  public void bindView(View paramView, Context paramContext, Cursor paramCursor)
  {
    paramContext = (ChildViewCache)paramView.getTag();
    int i;
    if (mFlagsCol != -1) {
      i = paramCursor.getInt(mFlagsCol);
    } else {
      i = 0;
    }
    if (mText1 != null)
    {
      paramView = getStringOrNull(paramCursor, mText1Col);
      setViewText(mText1, paramView);
    }
    if (mText2 != null)
    {
      paramView = getStringOrNull(paramCursor, mText2UrlCol);
      if (paramView != null) {
        paramView = formatUrl(paramView);
      } else {
        paramView = getStringOrNull(paramCursor, mText2Col);
      }
      if (TextUtils.isEmpty(paramView))
      {
        if (mText1 != null)
        {
          mText1.setSingleLine(false);
          mText1.setMaxLines(2);
        }
      }
      else if (mText1 != null)
      {
        mText1.setSingleLine(true);
        mText1.setMaxLines(1);
      }
      setViewText(mText2, paramView);
    }
    if (mIcon1 != null) {
      setViewDrawable(mIcon1, getIcon1(paramCursor), 4);
    }
    if (mIcon2 != null) {
      setViewDrawable(mIcon2, getIcon2(paramCursor), 8);
    }
    if ((mQueryRefinement != 2) && ((mQueryRefinement != 1) || ((i & 0x1) == 0)))
    {
      mIconRefine.setVisibility(8);
      return;
    }
    mIconRefine.setVisibility(0);
    mIconRefine.setTag(mText1.getText());
    mIconRefine.setOnClickListener(this);
  }
  
  public void changeCursor(Cursor paramCursor)
  {
    if (mClosed)
    {
      Log.w("SuggestionsAdapter", "Tried to change cursor after adapter was closed.");
      if (paramCursor != null) {
        paramCursor.close();
      }
      return;
    }
    try
    {
      super.changeCursor(paramCursor);
      if (paramCursor != null)
      {
        mText1Col = paramCursor.getColumnIndex("suggest_text_1");
        mText2Col = paramCursor.getColumnIndex("suggest_text_2");
        mText2UrlCol = paramCursor.getColumnIndex("suggest_text_2_url");
        mIconName1Col = paramCursor.getColumnIndex("suggest_icon_1");
        mIconName2Col = paramCursor.getColumnIndex("suggest_icon_2");
        mFlagsCol = paramCursor.getColumnIndex("suggest_flags");
        return;
      }
    }
    catch (Exception paramCursor)
    {
      Log.e("SuggestionsAdapter", "error changing cursor and caching columns", paramCursor);
    }
  }
  
  public void close()
  {
    changeCursor(null);
    mClosed = true;
  }
  
  public CharSequence convertToString(Cursor paramCursor)
  {
    if (paramCursor == null) {
      return null;
    }
    String str = getColumnString(paramCursor, "suggest_intent_query");
    if (str != null) {
      return str;
    }
    if (mSearchable.shouldRewriteQueryFromData())
    {
      str = getColumnString(paramCursor, "suggest_intent_data");
      if (str != null) {
        return str;
      }
    }
    if (mSearchable.shouldRewriteQueryFromText())
    {
      paramCursor = getColumnString(paramCursor, "suggest_text_1");
      if (paramCursor != null) {
        return paramCursor;
      }
    }
    return null;
  }
  
  Drawable getDrawableFromResourceUri(Uri paramUri)
    throws FileNotFoundException
  {
    Object localObject = paramUri.getAuthority();
    if (TextUtils.isEmpty((CharSequence)localObject))
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("No authority: ");
      ((StringBuilder)localObject).append(paramUri);
      throw new FileNotFoundException(((StringBuilder)localObject).toString());
    }
    for (;;)
    {
      try
      {
        localResources = mContext.getPackageManager().getResourcesForApplication((String)localObject);
        localList = paramUri.getPathSegments();
        if (localList == null)
        {
          localObject = new StringBuilder();
          ((StringBuilder)localObject).append("No path: ");
          ((StringBuilder)localObject).append(paramUri);
          throw new FileNotFoundException(((StringBuilder)localObject).toString());
        }
        i = localList.size();
        if (i != 1) {}
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        Resources localResources;
        List localList;
        int i;
        continue;
      }
      try
      {
        i = Integer.parseInt((String)localList.get(0));
      }
      catch (NumberFormatException localNumberFormatException) {}
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Single path segment is not a resource ID: ");
    ((StringBuilder)localObject).append(paramUri);
    throw new FileNotFoundException(((StringBuilder)localObject).toString());
    if (i == 2)
    {
      i = localResources.getIdentifier((String)localList.get(1), (String)localList.get(0), (String)localObject);
      if (i == 0)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("No resource found for: ");
        ((StringBuilder)localObject).append(paramUri);
        throw new FileNotFoundException(((StringBuilder)localObject).toString());
      }
      return localResources.getDrawable(i);
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("More than two path segments: ");
    ((StringBuilder)localObject).append(paramUri);
    throw new FileNotFoundException(((StringBuilder)localObject).toString());
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("No package found for authority: ");
    ((StringBuilder)localObject).append(paramUri);
    throw new FileNotFoundException(((StringBuilder)localObject).toString());
  }
  
  public View getDropDownView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    try
    {
      paramView = super.getDropDownView(paramInt, paramView, paramViewGroup);
      return paramView;
    }
    catch (RuntimeException paramView)
    {
      Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", paramView);
      paramViewGroup = newDropDownView(mContext, mCursor, paramViewGroup);
      if (paramViewGroup != null) {
        getTagmText1.setText(paramView.toString());
      }
    }
    return paramViewGroup;
  }
  
  public int getQueryRefinement()
  {
    return mQueryRefinement;
  }
  
  Cursor getSearchManagerSuggestions(SearchableInfo paramSearchableInfo, String paramString, int paramInt)
  {
    Object localObject1 = null;
    if (paramSearchableInfo == null) {
      return null;
    }
    Object localObject2 = paramSearchableInfo.getSuggestAuthority();
    if (localObject2 == null) {
      return null;
    }
    localObject2 = new Uri.Builder().scheme("content").authority((String)localObject2).query("").fragment("");
    String str = paramSearchableInfo.getSuggestPath();
    if (str != null) {
      ((Uri.Builder)localObject2).appendEncodedPath(str);
    }
    ((Uri.Builder)localObject2).appendPath("search_suggest_query");
    str = paramSearchableInfo.getSuggestSelection();
    if (str != null)
    {
      paramSearchableInfo = new String[1];
      paramSearchableInfo[0] = paramString;
    }
    for (;;)
    {
      break;
      ((Uri.Builder)localObject2).appendPath(paramString);
      paramSearchableInfo = localObject1;
    }
    if (paramInt > 0) {
      ((Uri.Builder)localObject2).appendQueryParameter("limit", String.valueOf(paramInt));
    }
    paramString = ((Uri.Builder)localObject2).build();
    return mContext.getContentResolver().query(paramString, null, str, paramSearchableInfo, null);
  }
  
  public View getView(int paramInt, View paramView, ViewGroup paramViewGroup)
  {
    try
    {
      paramView = super.getView(paramInt, paramView, paramViewGroup);
      return paramView;
    }
    catch (RuntimeException paramView)
    {
      Log.w("SuggestionsAdapter", "Search suggestions cursor threw exception.", paramView);
      paramViewGroup = newView(mContext, mCursor, paramViewGroup);
      if (paramViewGroup != null) {
        getTagmText1.setText(paramView.toString());
      }
    }
    return paramViewGroup;
  }
  
  public boolean hasStableIds()
  {
    return false;
  }
  
  public View newView(Context paramContext, Cursor paramCursor, ViewGroup paramViewGroup)
  {
    paramContext = super.newView(paramContext, paramCursor, paramViewGroup);
    paramContext.setTag(new ChildViewCache(paramContext));
    ((ImageView)paramContext.findViewById(R.id.edit_query)).setImageResource(mCommitIconResId);
    return paramContext;
  }
  
  public void notifyDataSetChanged()
  {
    super.notifyDataSetChanged();
    updateSpinnerState(getCursor());
  }
  
  public void notifyDataSetInvalidated()
  {
    super.notifyDataSetInvalidated();
    updateSpinnerState(getCursor());
  }
  
  public void onClick(View paramView)
  {
    paramView = paramView.getTag();
    if ((paramView instanceof CharSequence)) {
      mSearchView.onQueryRefine((CharSequence)paramView);
    }
  }
  
  public Cursor runQueryOnBackgroundThread(CharSequence paramCharSequence)
  {
    if (paramCharSequence == null) {
      paramCharSequence = "";
    } else {
      paramCharSequence = paramCharSequence.toString();
    }
    if (mSearchView.getVisibility() == 0)
    {
      if (mSearchView.getWindowVisibility() != 0) {
        return null;
      }
      try
      {
        paramCharSequence = getSearchManagerSuggestions(mSearchable, paramCharSequence, 50);
        if (paramCharSequence != null)
        {
          paramCharSequence.getCount();
          return paramCharSequence;
        }
      }
      catch (RuntimeException paramCharSequence)
      {
        Log.w("SuggestionsAdapter", "Search suggestions query threw an exception.", paramCharSequence);
      }
      return null;
    }
    return null;
  }
  
  public void setQueryRefinement(int paramInt)
  {
    mQueryRefinement = paramInt;
  }
  
  private static final class ChildViewCache
  {
    public final ImageView mIcon1;
    public final ImageView mIcon2;
    public final ImageView mIconRefine;
    public final TextView mText1;
    public final TextView mText2;
    
    public ChildViewCache(View paramView)
    {
      mText1 = ((TextView)paramView.findViewById(16908308));
      mText2 = ((TextView)paramView.findViewById(16908309));
      mIcon1 = ((ImageView)paramView.findViewById(16908295));
      mIcon2 = ((ImageView)paramView.findViewById(16908296));
      mIconRefine = ((ImageView)paramView.findViewById(R.id.edit_query));
    }
  }
}

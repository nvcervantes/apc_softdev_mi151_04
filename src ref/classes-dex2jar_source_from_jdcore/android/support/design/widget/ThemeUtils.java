package android.support.design.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v7.appcompat.R.attr;

class ThemeUtils
{
  private static final int[] APPCOMPAT_CHECK_ATTRS = { R.attr.colorPrimary };
  
  ThemeUtils() {}
  
  static void checkAppCompatTheme(Context paramContext)
  {
    paramContext = paramContext.obtainStyledAttributes(APPCOMPAT_CHECK_ATTRS);
    boolean bool = paramContext.hasValue(0);
    paramContext.recycle();
    if ((bool ^ true)) {
      throw new IllegalArgumentException("You need to use a Theme.AppCompat theme (or descendant) with the design library.");
    }
  }
}

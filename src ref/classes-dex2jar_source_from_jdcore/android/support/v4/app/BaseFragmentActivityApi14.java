package android.support.v4.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.IntentSender.SendIntentException;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;

@RequiresApi(14)
abstract class BaseFragmentActivityApi14
  extends SupportActivity
{
  boolean mStartedIntentSenderFromFragment;
  
  BaseFragmentActivityApi14() {}
  
  static void checkForValidRequestCode(int paramInt)
  {
    if ((paramInt & 0xFFFF0000) != 0) {
      throw new IllegalArgumentException("Can only use lower 16 bits for requestCode");
    }
  }
  
  abstract View dispatchFragmentsOnCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet);
  
  public View onCreateView(View paramView, String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    View localView = dispatchFragmentsOnCreateView(paramView, paramString, paramContext, paramAttributeSet);
    if (localView == null) {
      return super.onCreateView(paramView, paramString, paramContext, paramAttributeSet);
    }
    return localView;
  }
  
  public View onCreateView(String paramString, Context paramContext, AttributeSet paramAttributeSet)
  {
    View localView = dispatchFragmentsOnCreateView(null, paramString, paramContext, paramAttributeSet);
    if (localView == null) {
      return super.onCreateView(paramString, paramContext, paramAttributeSet);
    }
    return localView;
  }
  
  public void startIntentSenderForResult(IntentSender paramIntentSender, int paramInt1, @Nullable Intent paramIntent, int paramInt2, int paramInt3, int paramInt4)
    throws IntentSender.SendIntentException
  {
    if ((!mStartedIntentSenderFromFragment) && (paramInt1 != -1)) {
      checkForValidRequestCode(paramInt1);
    }
    super.startIntentSenderForResult(paramIntentSender, paramInt1, paramIntent, paramInt2, paramInt3, paramInt4);
  }
}

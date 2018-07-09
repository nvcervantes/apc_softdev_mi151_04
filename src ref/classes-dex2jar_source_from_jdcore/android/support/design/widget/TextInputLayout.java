package android.support.design.widget;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff.Mode;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.Drawable.ConstantState;
import android.graphics.drawable.DrawableContainer;
import android.os.Build.VERSION;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.ClassLoaderCreator;
import android.os.Parcelable.Creator;
import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.annotation.StyleRes;
import android.support.annotation.VisibleForTesting;
import android.support.design.R.id;
import android.support.design.R.layout;
import android.support.design.R.string;
import android.support.design.R.styleable;
import android.support.v4.content.ContextCompat;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.support.v4.view.AbsSavedState;
import android.support.v4.view.AccessibilityDelegateCompat;
import android.support.v4.view.ViewCompat;
import android.support.v4.view.accessibility.AccessibilityNodeInfoCompat;
import android.support.v4.widget.Space;
import android.support.v4.widget.TextViewCompat;
import android.support.v4.widget.ViewGroupUtils;
import android.support.v7.appcompat.R.color;
import android.support.v7.content.res.AppCompatResources;
import android.support.v7.widget.AppCompatDrawableManager;
import android.support.v7.widget.AppCompatTextView;
import android.support.v7.widget.TintTypedArray;
import android.support.v7.widget.WithHint;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewPropertyAnimator;
import android.view.ViewStructure;
import android.view.accessibility.AccessibilityEvent;
import android.view.animation.AccelerateInterpolator;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.FrameLayout.LayoutParams;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;
import java.util.List;

public class TextInputLayout
  extends LinearLayout
  implements WithHint
{
  private static final int ANIMATION_DURATION = 200;
  private static final int INVALID_MAX_LENGTH = -1;
  private static final String LOG_TAG = "TextInputLayout";
  private ValueAnimator mAnimator;
  final CollapsingTextHelper mCollapsingTextHelper = new CollapsingTextHelper(this);
  boolean mCounterEnabled;
  private int mCounterMaxLength;
  private int mCounterOverflowTextAppearance;
  private boolean mCounterOverflowed;
  private int mCounterTextAppearance;
  private TextView mCounterView;
  private ColorStateList mDefaultTextColor;
  EditText mEditText;
  private CharSequence mError;
  private boolean mErrorEnabled;
  private boolean mErrorShown;
  private int mErrorTextAppearance;
  TextView mErrorView;
  private ColorStateList mFocusedTextColor;
  private boolean mHasPasswordToggleTintList;
  private boolean mHasPasswordToggleTintMode;
  private boolean mHasReconstructedEditTextBackground;
  private CharSequence mHint;
  private boolean mHintAnimationEnabled;
  private boolean mHintEnabled;
  private boolean mHintExpanded;
  private boolean mInDrawableStateChanged;
  private LinearLayout mIndicatorArea;
  private int mIndicatorsAdded;
  private final FrameLayout mInputFrame;
  private Drawable mOriginalEditTextEndDrawable;
  private CharSequence mOriginalHint;
  private CharSequence mPasswordToggleContentDesc;
  private Drawable mPasswordToggleDrawable;
  private Drawable mPasswordToggleDummyDrawable;
  private boolean mPasswordToggleEnabled;
  private ColorStateList mPasswordToggleTintList;
  private PorterDuff.Mode mPasswordToggleTintMode;
  private CheckableImageButton mPasswordToggleView;
  private boolean mPasswordToggledVisible;
  private boolean mRestoringSavedState;
  private Paint mTmpPaint;
  private final Rect mTmpRect = new Rect();
  private Typeface mTypeface;
  
  public TextInputLayout(Context paramContext)
  {
    this(paramContext, null);
  }
  
  public TextInputLayout(Context paramContext, AttributeSet paramAttributeSet)
  {
    this(paramContext, paramAttributeSet, 0);
  }
  
  public TextInputLayout(Context paramContext, AttributeSet paramAttributeSet, int paramInt)
  {
    super(paramContext, paramAttributeSet);
    ThemeUtils.checkAppCompatTheme(paramContext);
    setOrientation(1);
    setWillNotDraw(false);
    setAddStatesFromChildren(true);
    mInputFrame = new FrameLayout(paramContext);
    mInputFrame.setAddStatesFromChildren(true);
    addView(mInputFrame);
    mCollapsingTextHelper.setTextSizeInterpolator(AnimationUtils.FAST_OUT_SLOW_IN_INTERPOLATOR);
    mCollapsingTextHelper.setPositionInterpolator(new AccelerateInterpolator());
    mCollapsingTextHelper.setCollapsedTextGravity(8388659);
    paramContext = TintTypedArray.obtainStyledAttributes(paramContext, paramAttributeSet, R.styleable.TextInputLayout, paramInt, android.support.design.R.style.Widget_Design_TextInputLayout);
    mHintEnabled = paramContext.getBoolean(R.styleable.TextInputLayout_hintEnabled, true);
    setHint(paramContext.getText(R.styleable.TextInputLayout_android_hint));
    mHintAnimationEnabled = paramContext.getBoolean(R.styleable.TextInputLayout_hintAnimationEnabled, true);
    if (paramContext.hasValue(R.styleable.TextInputLayout_android_textColorHint))
    {
      paramAttributeSet = paramContext.getColorStateList(R.styleable.TextInputLayout_android_textColorHint);
      mFocusedTextColor = paramAttributeSet;
      mDefaultTextColor = paramAttributeSet;
    }
    if (paramContext.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, -1) != -1) {
      setHintTextAppearance(paramContext.getResourceId(R.styleable.TextInputLayout_hintTextAppearance, 0));
    }
    mErrorTextAppearance = paramContext.getResourceId(R.styleable.TextInputLayout_errorTextAppearance, 0);
    boolean bool1 = paramContext.getBoolean(R.styleable.TextInputLayout_errorEnabled, false);
    boolean bool2 = paramContext.getBoolean(R.styleable.TextInputLayout_counterEnabled, false);
    setCounterMaxLength(paramContext.getInt(R.styleable.TextInputLayout_counterMaxLength, -1));
    mCounterTextAppearance = paramContext.getResourceId(R.styleable.TextInputLayout_counterTextAppearance, 0);
    mCounterOverflowTextAppearance = paramContext.getResourceId(R.styleable.TextInputLayout_counterOverflowTextAppearance, 0);
    mPasswordToggleEnabled = paramContext.getBoolean(R.styleable.TextInputLayout_passwordToggleEnabled, false);
    mPasswordToggleDrawable = paramContext.getDrawable(R.styleable.TextInputLayout_passwordToggleDrawable);
    mPasswordToggleContentDesc = paramContext.getText(R.styleable.TextInputLayout_passwordToggleContentDescription);
    if (paramContext.hasValue(R.styleable.TextInputLayout_passwordToggleTint))
    {
      mHasPasswordToggleTintList = true;
      mPasswordToggleTintList = paramContext.getColorStateList(R.styleable.TextInputLayout_passwordToggleTint);
    }
    if (paramContext.hasValue(R.styleable.TextInputLayout_passwordToggleTintMode))
    {
      mHasPasswordToggleTintMode = true;
      mPasswordToggleTintMode = ViewUtils.parseTintMode(paramContext.getInt(R.styleable.TextInputLayout_passwordToggleTintMode, -1), null);
    }
    paramContext.recycle();
    setErrorEnabled(bool1);
    setCounterEnabled(bool2);
    applyPasswordToggleTint();
    if (ViewCompat.getImportantForAccessibility(this) == 0) {
      ViewCompat.setImportantForAccessibility(this, 1);
    }
    ViewCompat.setAccessibilityDelegate(this, new TextInputAccessibilityDelegate());
  }
  
  private void addIndicator(TextView paramTextView, int paramInt)
  {
    if (mIndicatorArea == null)
    {
      mIndicatorArea = new LinearLayout(getContext());
      mIndicatorArea.setOrientation(0);
      addView(mIndicatorArea, -1, -2);
      Space localSpace = new Space(getContext());
      LinearLayout.LayoutParams localLayoutParams = new LinearLayout.LayoutParams(0, 0, 1.0F);
      mIndicatorArea.addView(localSpace, localLayoutParams);
      if (mEditText != null) {
        adjustIndicatorPadding();
      }
    }
    mIndicatorArea.setVisibility(0);
    mIndicatorArea.addView(paramTextView, paramInt);
    mIndicatorsAdded += 1;
  }
  
  private void adjustIndicatorPadding()
  {
    ViewCompat.setPaddingRelative(mIndicatorArea, ViewCompat.getPaddingStart(mEditText), 0, ViewCompat.getPaddingEnd(mEditText), mEditText.getPaddingBottom());
  }
  
  private void applyPasswordToggleTint()
  {
    if ((mPasswordToggleDrawable != null) && ((mHasPasswordToggleTintList) || (mHasPasswordToggleTintMode)))
    {
      mPasswordToggleDrawable = DrawableCompat.wrap(mPasswordToggleDrawable).mutate();
      if (mHasPasswordToggleTintList) {
        DrawableCompat.setTintList(mPasswordToggleDrawable, mPasswordToggleTintList);
      }
      if (mHasPasswordToggleTintMode) {
        DrawableCompat.setTintMode(mPasswordToggleDrawable, mPasswordToggleTintMode);
      }
      if ((mPasswordToggleView != null) && (mPasswordToggleView.getDrawable() != mPasswordToggleDrawable)) {
        mPasswordToggleView.setImageDrawable(mPasswordToggleDrawable);
      }
    }
  }
  
  private static boolean arrayContains(int[] paramArrayOfInt, int paramInt)
  {
    int j = paramArrayOfInt.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfInt[i] == paramInt) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private void collapseHint(boolean paramBoolean)
  {
    if ((mAnimator != null) && (mAnimator.isRunning())) {
      mAnimator.cancel();
    }
    if ((paramBoolean) && (mHintAnimationEnabled)) {
      animateToExpansionFraction(1.0F);
    } else {
      mCollapsingTextHelper.setExpansionFraction(1.0F);
    }
    mHintExpanded = false;
  }
  
  private void ensureBackgroundDrawableStateWorkaround()
  {
    int i = Build.VERSION.SDK_INT;
    if ((i != 21) && (i != 22)) {
      return;
    }
    Drawable localDrawable1 = mEditText.getBackground();
    if (localDrawable1 == null) {
      return;
    }
    if (!mHasReconstructedEditTextBackground)
    {
      Drawable localDrawable2 = localDrawable1.getConstantState().newDrawable();
      if ((localDrawable1 instanceof DrawableContainer)) {
        mHasReconstructedEditTextBackground = DrawableUtils.setContainerConstantState((DrawableContainer)localDrawable1, localDrawable2.getConstantState());
      }
      if (!mHasReconstructedEditTextBackground)
      {
        ViewCompat.setBackground(mEditText, localDrawable2);
        mHasReconstructedEditTextBackground = true;
      }
    }
  }
  
  private void expandHint(boolean paramBoolean)
  {
    if ((mAnimator != null) && (mAnimator.isRunning())) {
      mAnimator.cancel();
    }
    if ((paramBoolean) && (mHintAnimationEnabled)) {
      animateToExpansionFraction(0.0F);
    } else {
      mCollapsingTextHelper.setExpansionFraction(0.0F);
    }
    mHintExpanded = true;
  }
  
  private boolean hasPasswordTransformation()
  {
    return (mEditText != null) && ((mEditText.getTransformationMethod() instanceof PasswordTransformationMethod));
  }
  
  private void passwordVisibilityToggleRequested(boolean paramBoolean)
  {
    if (mPasswordToggleEnabled)
    {
      int i = mEditText.getSelectionEnd();
      if (hasPasswordTransformation())
      {
        mEditText.setTransformationMethod(null);
        mPasswordToggledVisible = true;
      }
      else
      {
        mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
        mPasswordToggledVisible = false;
      }
      mPasswordToggleView.setChecked(mPasswordToggledVisible);
      if (paramBoolean) {
        mPasswordToggleView.jumpDrawablesToCurrentState();
      }
      mEditText.setSelection(i);
    }
  }
  
  private static void recursiveSetEnabled(ViewGroup paramViewGroup, boolean paramBoolean)
  {
    int j = paramViewGroup.getChildCount();
    int i = 0;
    while (i < j)
    {
      View localView = paramViewGroup.getChildAt(i);
      localView.setEnabled(paramBoolean);
      if ((localView instanceof ViewGroup)) {
        recursiveSetEnabled((ViewGroup)localView, paramBoolean);
      }
      i += 1;
    }
  }
  
  private void removeIndicator(TextView paramTextView)
  {
    if (mIndicatorArea != null)
    {
      mIndicatorArea.removeView(paramTextView);
      int i = mIndicatorsAdded - 1;
      mIndicatorsAdded = i;
      if (i == 0) {
        mIndicatorArea.setVisibility(8);
      }
    }
  }
  
  private void setEditText(EditText paramEditText)
  {
    if (mEditText != null) {
      throw new IllegalArgumentException("We already have an EditText, can only have one");
    }
    if (!(paramEditText instanceof TextInputEditText)) {
      Log.i("TextInputLayout", "EditText added is not a TextInputEditText. Please switch to using that class instead.");
    }
    mEditText = paramEditText;
    if (!hasPasswordTransformation()) {
      mCollapsingTextHelper.setTypefaces(mEditText.getTypeface());
    }
    mCollapsingTextHelper.setExpandedTextSize(mEditText.getTextSize());
    int i = mEditText.getGravity();
    mCollapsingTextHelper.setCollapsedTextGravity(0x30 | i & 0xFFFFFF8F);
    mCollapsingTextHelper.setExpandedTextGravity(i);
    mEditText.addTextChangedListener(new TextWatcher()
    {
      public void afterTextChanged(Editable paramAnonymousEditable)
      {
        updateLabelState(mRestoringSavedState ^ true);
        if (mCounterEnabled) {
          updateCounter(paramAnonymousEditable.length());
        }
      }
      
      public void beforeTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
      
      public void onTextChanged(CharSequence paramAnonymousCharSequence, int paramAnonymousInt1, int paramAnonymousInt2, int paramAnonymousInt3) {}
    });
    if (mDefaultTextColor == null) {
      mDefaultTextColor = mEditText.getHintTextColors();
    }
    if ((mHintEnabled) && (TextUtils.isEmpty(mHint)))
    {
      mOriginalHint = mEditText.getHint();
      setHint(mOriginalHint);
      mEditText.setHint(null);
    }
    if (mCounterView != null) {
      updateCounter(mEditText.getText().length());
    }
    if (mIndicatorArea != null) {
      adjustIndicatorPadding();
    }
    updatePasswordToggleView();
    updateLabelState(false, true);
  }
  
  private void setError(@Nullable final CharSequence paramCharSequence, boolean paramBoolean)
  {
    mError = paramCharSequence;
    if (!mErrorEnabled)
    {
      if (TextUtils.isEmpty(paramCharSequence)) {
        return;
      }
      setErrorEnabled(true);
    }
    mErrorShown = (TextUtils.isEmpty(paramCharSequence) ^ true);
    mErrorView.animate().cancel();
    if (mErrorShown)
    {
      mErrorView.setText(paramCharSequence);
      mErrorView.setVisibility(0);
      if (paramBoolean)
      {
        if (mErrorView.getAlpha() == 1.0F) {
          mErrorView.setAlpha(0.0F);
        }
        mErrorView.animate().alpha(1.0F).setDuration(200L).setInterpolator(AnimationUtils.LINEAR_OUT_SLOW_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter()
        {
          public void onAnimationStart(Animator paramAnonymousAnimator)
          {
            mErrorView.setVisibility(0);
          }
        }).start();
      }
      else
      {
        mErrorView.setAlpha(1.0F);
      }
    }
    else if (mErrorView.getVisibility() == 0)
    {
      if (paramBoolean)
      {
        mErrorView.animate().alpha(0.0F).setDuration(200L).setInterpolator(AnimationUtils.FAST_OUT_LINEAR_IN_INTERPOLATOR).setListener(new AnimatorListenerAdapter()
        {
          public void onAnimationEnd(Animator paramAnonymousAnimator)
          {
            mErrorView.setText(paramCharSequence);
            mErrorView.setVisibility(4);
          }
        }).start();
      }
      else
      {
        mErrorView.setText(paramCharSequence);
        mErrorView.setVisibility(4);
      }
    }
    updateEditTextBackground();
    updateLabelState(paramBoolean);
  }
  
  private void setHintInternal(CharSequence paramCharSequence)
  {
    mHint = paramCharSequence;
    mCollapsingTextHelper.setText(paramCharSequence);
  }
  
  private boolean shouldShowPasswordIcon()
  {
    return (mPasswordToggleEnabled) && ((hasPasswordTransformation()) || (mPasswordToggledVisible));
  }
  
  private void updateEditTextBackground()
  {
    if (mEditText == null) {
      return;
    }
    Drawable localDrawable2 = mEditText.getBackground();
    if (localDrawable2 == null) {
      return;
    }
    ensureBackgroundDrawableStateWorkaround();
    Drawable localDrawable1 = localDrawable2;
    if (android.support.v7.widget.DrawableUtils.canSafelyMutateDrawable(localDrawable2)) {
      localDrawable1 = localDrawable2.mutate();
    }
    if ((mErrorShown) && (mErrorView != null))
    {
      localDrawable1.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(mErrorView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
      return;
    }
    if ((mCounterOverflowed) && (mCounterView != null))
    {
      localDrawable1.setColorFilter(AppCompatDrawableManager.getPorterDuffColorFilter(mCounterView.getCurrentTextColor(), PorterDuff.Mode.SRC_IN));
      return;
    }
    DrawableCompat.clearColorFilter(localDrawable1);
    mEditText.refreshDrawableState();
  }
  
  private void updateInputLayoutMargins()
  {
    LinearLayout.LayoutParams localLayoutParams = (LinearLayout.LayoutParams)mInputFrame.getLayoutParams();
    int i;
    if (mHintEnabled)
    {
      if (mTmpPaint == null) {
        mTmpPaint = new Paint();
      }
      mTmpPaint.setTypeface(mCollapsingTextHelper.getCollapsedTypeface());
      mTmpPaint.setTextSize(mCollapsingTextHelper.getCollapsedTextSize());
      i = (int)-mTmpPaint.ascent();
    }
    else
    {
      i = 0;
    }
    if (i != topMargin)
    {
      topMargin = i;
      mInputFrame.requestLayout();
    }
  }
  
  private void updatePasswordToggleView()
  {
    if (mEditText == null) {
      return;
    }
    Drawable[] arrayOfDrawable;
    if (shouldShowPasswordIcon())
    {
      if (mPasswordToggleView == null)
      {
        mPasswordToggleView = ((CheckableImageButton)LayoutInflater.from(getContext()).inflate(R.layout.design_text_input_password_icon, mInputFrame, false));
        mPasswordToggleView.setImageDrawable(mPasswordToggleDrawable);
        mPasswordToggleView.setContentDescription(mPasswordToggleContentDesc);
        mInputFrame.addView(mPasswordToggleView);
        mPasswordToggleView.setOnClickListener(new View.OnClickListener()
        {
          public void onClick(View paramAnonymousView)
          {
            TextInputLayout.this.passwordVisibilityToggleRequested(false);
          }
        });
      }
      if ((mEditText != null) && (ViewCompat.getMinimumHeight(mEditText) <= 0)) {
        mEditText.setMinimumHeight(ViewCompat.getMinimumHeight(mPasswordToggleView));
      }
      mPasswordToggleView.setVisibility(0);
      mPasswordToggleView.setChecked(mPasswordToggledVisible);
      if (mPasswordToggleDummyDrawable == null) {
        mPasswordToggleDummyDrawable = new ColorDrawable();
      }
      mPasswordToggleDummyDrawable.setBounds(0, 0, mPasswordToggleView.getMeasuredWidth(), 1);
      arrayOfDrawable = TextViewCompat.getCompoundDrawablesRelative(mEditText);
      if (arrayOfDrawable[2] != mPasswordToggleDummyDrawable) {
        mOriginalEditTextEndDrawable = arrayOfDrawable[2];
      }
      TextViewCompat.setCompoundDrawablesRelative(mEditText, arrayOfDrawable[0], arrayOfDrawable[1], mPasswordToggleDummyDrawable, arrayOfDrawable[3]);
      mPasswordToggleView.setPadding(mEditText.getPaddingLeft(), mEditText.getPaddingTop(), mEditText.getPaddingRight(), mEditText.getPaddingBottom());
      return;
    }
    if ((mPasswordToggleView != null) && (mPasswordToggleView.getVisibility() == 0)) {
      mPasswordToggleView.setVisibility(8);
    }
    if (mPasswordToggleDummyDrawable != null)
    {
      arrayOfDrawable = TextViewCompat.getCompoundDrawablesRelative(mEditText);
      if (arrayOfDrawable[2] == mPasswordToggleDummyDrawable)
      {
        TextViewCompat.setCompoundDrawablesRelative(mEditText, arrayOfDrawable[0], arrayOfDrawable[1], mOriginalEditTextEndDrawable, arrayOfDrawable[3]);
        mPasswordToggleDummyDrawable = null;
      }
    }
  }
  
  public void addView(View paramView, int paramInt, ViewGroup.LayoutParams paramLayoutParams)
  {
    if ((paramView instanceof EditText))
    {
      FrameLayout.LayoutParams localLayoutParams = new FrameLayout.LayoutParams(paramLayoutParams);
      gravity = (0x10 | gravity & 0xFFFFFF8F);
      mInputFrame.addView(paramView, localLayoutParams);
      mInputFrame.setLayoutParams(paramLayoutParams);
      updateInputLayoutMargins();
      setEditText((EditText)paramView);
      return;
    }
    super.addView(paramView, paramInt, paramLayoutParams);
  }
  
  @VisibleForTesting
  void animateToExpansionFraction(float paramFloat)
  {
    if (mCollapsingTextHelper.getExpansionFraction() == paramFloat) {
      return;
    }
    if (mAnimator == null)
    {
      mAnimator = new ValueAnimator();
      mAnimator.setInterpolator(AnimationUtils.LINEAR_INTERPOLATOR);
      mAnimator.setDuration(200L);
      mAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener()
      {
        public void onAnimationUpdate(ValueAnimator paramAnonymousValueAnimator)
        {
          mCollapsingTextHelper.setExpansionFraction(((Float)paramAnonymousValueAnimator.getAnimatedValue()).floatValue());
        }
      });
    }
    mAnimator.setFloatValues(new float[] { mCollapsingTextHelper.getExpansionFraction(), paramFloat });
    mAnimator.start();
  }
  
  public void dispatchProvideAutofillStructure(ViewStructure paramViewStructure, int paramInt)
  {
    if ((mOriginalHint != null) && (mEditText != null))
    {
      CharSequence localCharSequence = mEditText.getHint();
      mEditText.setHint(mOriginalHint);
      try
      {
        super.dispatchProvideAutofillStructure(paramViewStructure, paramInt);
        return;
      }
      finally
      {
        mEditText.setHint(localCharSequence);
      }
    }
    super.dispatchProvideAutofillStructure(paramViewStructure, paramInt);
  }
  
  protected void dispatchRestoreInstanceState(SparseArray<Parcelable> paramSparseArray)
  {
    mRestoringSavedState = true;
    super.dispatchRestoreInstanceState(paramSparseArray);
    mRestoringSavedState = false;
  }
  
  public void draw(Canvas paramCanvas)
  {
    super.draw(paramCanvas);
    if (mHintEnabled) {
      mCollapsingTextHelper.draw(paramCanvas);
    }
  }
  
  protected void drawableStateChanged()
  {
    if (mInDrawableStateChanged) {
      return;
    }
    boolean bool2 = true;
    mInDrawableStateChanged = true;
    super.drawableStateChanged();
    int[] arrayOfInt = getDrawableState();
    if ((!ViewCompat.isLaidOut(this)) || (!isEnabled())) {
      bool2 = false;
    }
    updateLabelState(bool2);
    updateEditTextBackground();
    boolean bool1;
    if (mCollapsingTextHelper != null) {
      bool1 = mCollapsingTextHelper.setState(arrayOfInt) | false;
    } else {
      bool1 = false;
    }
    if (bool1) {
      invalidate();
    }
    mInDrawableStateChanged = false;
  }
  
  public int getCounterMaxLength()
  {
    return mCounterMaxLength;
  }
  
  @Nullable
  public EditText getEditText()
  {
    return mEditText;
  }
  
  @Nullable
  public CharSequence getError()
  {
    if (mErrorEnabled) {
      return mError;
    }
    return null;
  }
  
  @Nullable
  public CharSequence getHint()
  {
    if (mHintEnabled) {
      return mHint;
    }
    return null;
  }
  
  @Nullable
  public CharSequence getPasswordVisibilityToggleContentDescription()
  {
    return mPasswordToggleContentDesc;
  }
  
  @Nullable
  public Drawable getPasswordVisibilityToggleDrawable()
  {
    return mPasswordToggleDrawable;
  }
  
  @NonNull
  public Typeface getTypeface()
  {
    return mTypeface;
  }
  
  public boolean isCounterEnabled()
  {
    return mCounterEnabled;
  }
  
  public boolean isErrorEnabled()
  {
    return mErrorEnabled;
  }
  
  public boolean isHintAnimationEnabled()
  {
    return mHintAnimationEnabled;
  }
  
  public boolean isHintEnabled()
  {
    return mHintEnabled;
  }
  
  @VisibleForTesting
  final boolean isHintExpanded()
  {
    return mHintExpanded;
  }
  
  public boolean isPasswordVisibilityToggleEnabled()
  {
    return mPasswordToggleEnabled;
  }
  
  protected void onLayout(boolean paramBoolean, int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    super.onLayout(paramBoolean, paramInt1, paramInt2, paramInt3, paramInt4);
    if ((mHintEnabled) && (mEditText != null))
    {
      Rect localRect = mTmpRect;
      ViewGroupUtils.getDescendantRect(this, mEditText, localRect);
      paramInt1 = left + mEditText.getCompoundPaddingLeft();
      paramInt3 = right - mEditText.getCompoundPaddingRight();
      mCollapsingTextHelper.setExpandedBounds(paramInt1, top + mEditText.getCompoundPaddingTop(), paramInt3, bottom - mEditText.getCompoundPaddingBottom());
      mCollapsingTextHelper.setCollapsedBounds(paramInt1, getPaddingTop(), paramInt3, paramInt4 - paramInt2 - getPaddingBottom());
      mCollapsingTextHelper.recalculate();
    }
  }
  
  protected void onMeasure(int paramInt1, int paramInt2)
  {
    updatePasswordToggleView();
    super.onMeasure(paramInt1, paramInt2);
  }
  
  protected void onRestoreInstanceState(Parcelable paramParcelable)
  {
    if (!(paramParcelable instanceof SavedState))
    {
      super.onRestoreInstanceState(paramParcelable);
      return;
    }
    paramParcelable = (SavedState)paramParcelable;
    super.onRestoreInstanceState(paramParcelable.getSuperState());
    setError(error);
    if (isPasswordToggledVisible) {
      passwordVisibilityToggleRequested(true);
    }
    requestLayout();
  }
  
  public Parcelable onSaveInstanceState()
  {
    SavedState localSavedState = new SavedState(super.onSaveInstanceState());
    if (mErrorShown) {
      error = getError();
    }
    isPasswordToggledVisible = mPasswordToggledVisible;
    return localSavedState;
  }
  
  public void setCounterEnabled(boolean paramBoolean)
  {
    if (mCounterEnabled != paramBoolean) {
      if (paramBoolean)
      {
        mCounterView = new AppCompatTextView(getContext());
        mCounterView.setId(R.id.textinput_counter);
        if (mTypeface != null) {
          mCounterView.setTypeface(mTypeface);
        }
        mCounterView.setMaxLines(1);
      }
    }
    try
    {
      TextViewCompat.setTextAppearance(mCounterView, mCounterTextAppearance);
    }
    catch (Exception localException)
    {
      for (;;) {}
    }
    TextViewCompat.setTextAppearance(mCounterView, android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Caption);
    mCounterView.setTextColor(ContextCompat.getColor(getContext(), R.color.error_color_material));
    addIndicator(mCounterView, -1);
    if (mEditText == null)
    {
      updateCounter(0);
    }
    else
    {
      updateCounter(mEditText.getText().length());
      break label160;
      removeIndicator(mCounterView);
      mCounterView = null;
    }
    label160:
    mCounterEnabled = paramBoolean;
  }
  
  public void setCounterMaxLength(int paramInt)
  {
    if (mCounterMaxLength != paramInt)
    {
      if (paramInt > 0) {
        mCounterMaxLength = paramInt;
      } else {
        mCounterMaxLength = -1;
      }
      if (mCounterEnabled)
      {
        if (mEditText == null) {
          paramInt = 0;
        } else {
          paramInt = mEditText.getText().length();
        }
        updateCounter(paramInt);
      }
    }
  }
  
  public void setEnabled(boolean paramBoolean)
  {
    recursiveSetEnabled(this, paramBoolean);
    super.setEnabled(paramBoolean);
  }
  
  public void setError(@Nullable CharSequence paramCharSequence)
  {
    boolean bool;
    if ((ViewCompat.isLaidOut(this)) && (isEnabled()) && ((mErrorView == null) || (!TextUtils.equals(mErrorView.getText(), paramCharSequence)))) {
      bool = true;
    } else {
      bool = false;
    }
    setError(paramCharSequence, bool);
  }
  
  public void setErrorEnabled(boolean paramBoolean)
  {
    if (mErrorEnabled != paramBoolean)
    {
      if (mErrorView != null) {
        mErrorView.animate().cancel();
      }
      if (paramBoolean)
      {
        mErrorView = new AppCompatTextView(getContext());
        mErrorView.setId(R.id.textinput_error);
        if (mTypeface != null) {
          mErrorView.setTypeface(mTypeface);
        }
      }
    }
    try
    {
      TextViewCompat.setTextAppearance(mErrorView, mErrorTextAppearance);
      if (Build.VERSION.SDK_INT >= 23)
      {
        i = mErrorView.getTextColors().getDefaultColor();
        if (i == -65281) {}
      }
      else
      {
        i = 0;
      }
    }
    catch (Exception localException)
    {
      int i;
      for (;;) {}
    }
    i = 1;
    if (i != 0)
    {
      TextViewCompat.setTextAppearance(mErrorView, android.support.v7.appcompat.R.style.TextAppearance_AppCompat_Caption);
      mErrorView.setTextColor(ContextCompat.getColor(getContext(), R.color.error_color_material));
    }
    mErrorView.setVisibility(4);
    ViewCompat.setAccessibilityLiveRegion(mErrorView, 1);
    addIndicator(mErrorView, 0);
    break label200;
    mErrorShown = false;
    updateEditTextBackground();
    removeIndicator(mErrorView);
    mErrorView = null;
    label200:
    mErrorEnabled = paramBoolean;
  }
  
  public void setErrorTextAppearance(@StyleRes int paramInt)
  {
    mErrorTextAppearance = paramInt;
    if (mErrorView != null) {
      TextViewCompat.setTextAppearance(mErrorView, paramInt);
    }
  }
  
  public void setHint(@Nullable CharSequence paramCharSequence)
  {
    if (mHintEnabled)
    {
      setHintInternal(paramCharSequence);
      sendAccessibilityEvent(2048);
    }
  }
  
  public void setHintAnimationEnabled(boolean paramBoolean)
  {
    mHintAnimationEnabled = paramBoolean;
  }
  
  public void setHintEnabled(boolean paramBoolean)
  {
    if (paramBoolean != mHintEnabled)
    {
      mHintEnabled = paramBoolean;
      CharSequence localCharSequence = mEditText.getHint();
      if (!mHintEnabled)
      {
        if ((!TextUtils.isEmpty(mHint)) && (TextUtils.isEmpty(localCharSequence))) {
          mEditText.setHint(mHint);
        }
        setHintInternal(null);
      }
      else if (!TextUtils.isEmpty(localCharSequence))
      {
        if (TextUtils.isEmpty(mHint)) {
          setHint(localCharSequence);
        }
        mEditText.setHint(null);
      }
      if (mEditText != null) {
        updateInputLayoutMargins();
      }
    }
  }
  
  public void setHintTextAppearance(@StyleRes int paramInt)
  {
    mCollapsingTextHelper.setCollapsedTextAppearance(paramInt);
    mFocusedTextColor = mCollapsingTextHelper.getCollapsedTextColor();
    if (mEditText != null)
    {
      updateLabelState(false);
      updateInputLayoutMargins();
    }
  }
  
  public void setPasswordVisibilityToggleContentDescription(@StringRes int paramInt)
  {
    CharSequence localCharSequence;
    if (paramInt != 0) {
      localCharSequence = getResources().getText(paramInt);
    } else {
      localCharSequence = null;
    }
    setPasswordVisibilityToggleContentDescription(localCharSequence);
  }
  
  public void setPasswordVisibilityToggleContentDescription(@Nullable CharSequence paramCharSequence)
  {
    mPasswordToggleContentDesc = paramCharSequence;
    if (mPasswordToggleView != null) {
      mPasswordToggleView.setContentDescription(paramCharSequence);
    }
  }
  
  public void setPasswordVisibilityToggleDrawable(@DrawableRes int paramInt)
  {
    Drawable localDrawable;
    if (paramInt != 0) {
      localDrawable = AppCompatResources.getDrawable(getContext(), paramInt);
    } else {
      localDrawable = null;
    }
    setPasswordVisibilityToggleDrawable(localDrawable);
  }
  
  public void setPasswordVisibilityToggleDrawable(@Nullable Drawable paramDrawable)
  {
    mPasswordToggleDrawable = paramDrawable;
    if (mPasswordToggleView != null) {
      mPasswordToggleView.setImageDrawable(paramDrawable);
    }
  }
  
  public void setPasswordVisibilityToggleEnabled(boolean paramBoolean)
  {
    if (mPasswordToggleEnabled != paramBoolean)
    {
      mPasswordToggleEnabled = paramBoolean;
      if ((!paramBoolean) && (mPasswordToggledVisible) && (mEditText != null)) {
        mEditText.setTransformationMethod(PasswordTransformationMethod.getInstance());
      }
      mPasswordToggledVisible = false;
      updatePasswordToggleView();
    }
  }
  
  public void setPasswordVisibilityToggleTintList(@Nullable ColorStateList paramColorStateList)
  {
    mPasswordToggleTintList = paramColorStateList;
    mHasPasswordToggleTintList = true;
    applyPasswordToggleTint();
  }
  
  public void setPasswordVisibilityToggleTintMode(@Nullable PorterDuff.Mode paramMode)
  {
    mPasswordToggleTintMode = paramMode;
    mHasPasswordToggleTintMode = true;
    applyPasswordToggleTint();
  }
  
  public void setTypeface(@Nullable Typeface paramTypeface)
  {
    if (((mTypeface != null) && (!mTypeface.equals(paramTypeface))) || ((mTypeface == null) && (paramTypeface != null)))
    {
      mTypeface = paramTypeface;
      mCollapsingTextHelper.setTypefaces(paramTypeface);
      if (mCounterView != null) {
        mCounterView.setTypeface(paramTypeface);
      }
      if (mErrorView != null) {
        mErrorView.setTypeface(paramTypeface);
      }
    }
  }
  
  void updateCounter(int paramInt)
  {
    boolean bool2 = mCounterOverflowed;
    if (mCounterMaxLength == -1)
    {
      mCounterView.setText(String.valueOf(paramInt));
      mCounterOverflowed = false;
    }
    else
    {
      boolean bool1;
      if (paramInt > mCounterMaxLength) {
        bool1 = true;
      } else {
        bool1 = false;
      }
      mCounterOverflowed = bool1;
      if (bool2 != mCounterOverflowed)
      {
        TextView localTextView = mCounterView;
        int i;
        if (mCounterOverflowed) {
          i = mCounterOverflowTextAppearance;
        } else {
          i = mCounterTextAppearance;
        }
        TextViewCompat.setTextAppearance(localTextView, i);
      }
      mCounterView.setText(getContext().getString(R.string.character_counter_pattern, new Object[] { Integer.valueOf(paramInt), Integer.valueOf(mCounterMaxLength) }));
    }
    if ((mEditText != null) && (bool2 != mCounterOverflowed))
    {
      updateLabelState(false);
      updateEditTextBackground();
    }
  }
  
  void updateLabelState(boolean paramBoolean)
  {
    updateLabelState(paramBoolean, false);
  }
  
  void updateLabelState(boolean paramBoolean1, boolean paramBoolean2)
  {
    boolean bool1 = isEnabled();
    int i;
    if ((mEditText != null) && (!TextUtils.isEmpty(mEditText.getText()))) {
      i = 1;
    } else {
      i = 0;
    }
    boolean bool2 = arrayContains(getDrawableState(), 16842908);
    boolean bool3 = TextUtils.isEmpty(getError());
    if (mDefaultTextColor != null) {
      mCollapsingTextHelper.setExpandedTextColor(mDefaultTextColor);
    }
    if ((bool1) && (mCounterOverflowed) && (mCounterView != null)) {
      mCollapsingTextHelper.setCollapsedTextColor(mCounterView.getTextColors());
    } else if ((bool1) && (bool2) && (mFocusedTextColor != null)) {
      mCollapsingTextHelper.setCollapsedTextColor(mFocusedTextColor);
    } else if (mDefaultTextColor != null) {
      mCollapsingTextHelper.setCollapsedTextColor(mDefaultTextColor);
    }
    if ((i == 0) && ((!isEnabled()) || ((!bool2) && (!(true ^ bool3)))))
    {
      if ((paramBoolean2) || (!mHintExpanded)) {
        expandHint(paramBoolean1);
      }
    }
    else if ((paramBoolean2) || (mHintExpanded)) {
      collapseHint(paramBoolean1);
    }
  }
  
  static class SavedState
    extends AbsSavedState
  {
    public static final Parcelable.Creator<SavedState> CREATOR = new Parcelable.ClassLoaderCreator()
    {
      public TextInputLayout.SavedState createFromParcel(Parcel paramAnonymousParcel)
      {
        return new TextInputLayout.SavedState(paramAnonymousParcel, null);
      }
      
      public TextInputLayout.SavedState createFromParcel(Parcel paramAnonymousParcel, ClassLoader paramAnonymousClassLoader)
      {
        return new TextInputLayout.SavedState(paramAnonymousParcel, paramAnonymousClassLoader);
      }
      
      public TextInputLayout.SavedState[] newArray(int paramAnonymousInt)
      {
        return new TextInputLayout.SavedState[paramAnonymousInt];
      }
    };
    CharSequence error;
    boolean isPasswordToggledVisible;
    
    SavedState(Parcel paramParcel, ClassLoader paramClassLoader)
    {
      super(paramClassLoader);
      error = ((CharSequence)TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(paramParcel));
      int i = paramParcel.readInt();
      boolean bool = true;
      if (i != 1) {
        bool = false;
      }
      isPasswordToggledVisible = bool;
    }
    
    SavedState(Parcelable paramParcelable)
    {
      super();
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("TextInputLayout.SavedState{");
      localStringBuilder.append(Integer.toHexString(System.identityHashCode(this)));
      localStringBuilder.append(" error=");
      localStringBuilder.append(error);
      localStringBuilder.append("}");
      return localStringBuilder.toString();
    }
    
    public void writeToParcel(Parcel paramParcel, int paramInt)
    {
      throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.provideAs(TypeTransformer.java:780)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.e1expr(TypeTransformer.java:496)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:713)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.enexpr(TypeTransformer.java:698)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:719)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.exExpr(TypeTransformer.java:703)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.s1stmt(TypeTransformer.java:810)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.sxStmt(TypeTransformer.java:840)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:206)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
    }
  }
  
  private class TextInputAccessibilityDelegate
    extends AccessibilityDelegateCompat
  {
    TextInputAccessibilityDelegate() {}
    
    public void onInitializeAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onInitializeAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramAccessibilityEvent.setClassName(TextInputLayout.class.getSimpleName());
    }
    
    public void onInitializeAccessibilityNodeInfo(View paramView, AccessibilityNodeInfoCompat paramAccessibilityNodeInfoCompat)
    {
      super.onInitializeAccessibilityNodeInfo(paramView, paramAccessibilityNodeInfoCompat);
      paramAccessibilityNodeInfoCompat.setClassName(TextInputLayout.class.getSimpleName());
      paramView = mCollapsingTextHelper.getText();
      if (!TextUtils.isEmpty(paramView)) {
        paramAccessibilityNodeInfoCompat.setText(paramView);
      }
      if (mEditText != null) {
        paramAccessibilityNodeInfoCompat.setLabelFor(mEditText);
      }
      if (mErrorView != null) {
        paramView = mErrorView.getText();
      } else {
        paramView = null;
      }
      if (!TextUtils.isEmpty(paramView))
      {
        paramAccessibilityNodeInfoCompat.setContentInvalid(true);
        paramAccessibilityNodeInfoCompat.setError(paramView);
      }
    }
    
    public void onPopulateAccessibilityEvent(View paramView, AccessibilityEvent paramAccessibilityEvent)
    {
      super.onPopulateAccessibilityEvent(paramView, paramAccessibilityEvent);
      paramView = mCollapsingTextHelper.getText();
      if (!TextUtils.isEmpty(paramView)) {
        paramAccessibilityEvent.getText().add(paramView);
      }
    }
  }
}

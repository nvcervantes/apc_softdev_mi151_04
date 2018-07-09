package android.support.constraint;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.content.res.XmlResourceParser;
import android.os.Build.VERSION;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseIntArray;
import android.util.Xml;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class ConstraintSet
{
  private static final int ALPHA = 43;
  private static final int BARRIER_TYPE = 1;
  public static final int BASELINE = 5;
  private static final int BASELINE_TO_BASELINE = 1;
  public static final int BOTTOM = 4;
  private static final int BOTTOM_MARGIN = 2;
  private static final int BOTTOM_TO_BOTTOM = 3;
  private static final int BOTTOM_TO_TOP = 4;
  public static final int CHAIN_PACKED = 2;
  public static final int CHAIN_SPREAD = 0;
  public static final int CHAIN_SPREAD_INSIDE = 1;
  private static final boolean DEBUG = false;
  private static final int DIMENSION_RATIO = 5;
  private static final int EDITOR_ABSOLUTE_X = 6;
  private static final int EDITOR_ABSOLUTE_Y = 7;
  private static final int ELEVATION = 44;
  public static final int END = 7;
  private static final int END_MARGIN = 8;
  private static final int END_TO_END = 9;
  private static final int END_TO_START = 10;
  public static final int GONE = 8;
  private static final int GONE_BOTTOM_MARGIN = 11;
  private static final int GONE_END_MARGIN = 12;
  private static final int GONE_LEFT_MARGIN = 13;
  private static final int GONE_RIGHT_MARGIN = 14;
  private static final int GONE_START_MARGIN = 15;
  private static final int GONE_TOP_MARGIN = 16;
  private static final int GUIDE_BEGIN = 17;
  private static final int GUIDE_END = 18;
  private static final int GUIDE_PERCENT = 19;
  private static final int HEIGHT_DEFAULT = 55;
  private static final int HEIGHT_MAX = 57;
  private static final int HEIGHT_MIN = 59;
  public static final int HORIZONTAL = 0;
  private static final int HORIZONTAL_BIAS = 20;
  public static final int HORIZONTAL_GUIDELINE = 0;
  private static final int HORIZONTAL_STYLE = 41;
  private static final int HORIZONTAL_WEIGHT = 39;
  public static final int INVISIBLE = 4;
  private static final int LAYOUT_HEIGHT = 21;
  private static final int LAYOUT_VISIBILITY = 22;
  private static final int LAYOUT_WIDTH = 23;
  public static final int LEFT = 1;
  private static final int LEFT_MARGIN = 24;
  private static final int LEFT_TO_LEFT = 25;
  private static final int LEFT_TO_RIGHT = 26;
  public static final int MATCH_CONSTRAINT = 0;
  public static final int MATCH_CONSTRAINT_SPREAD = 0;
  public static final int MATCH_CONSTRAINT_WRAP = 1;
  private static final int ORIENTATION = 27;
  public static final int PARENT_ID = 0;
  public static final int RIGHT = 2;
  private static final int RIGHT_MARGIN = 28;
  private static final int RIGHT_TO_LEFT = 29;
  private static final int RIGHT_TO_RIGHT = 30;
  private static final int ROTATION = 60;
  private static final int ROTATION_X = 45;
  private static final int ROTATION_Y = 46;
  private static final int SCALE_X = 47;
  private static final int SCALE_Y = 48;
  public static final int START = 6;
  private static final int START_MARGIN = 31;
  private static final int START_TO_END = 32;
  private static final int START_TO_START = 33;
  private static final String TAG = "ConstraintSet";
  public static final int TOP = 3;
  private static final int TOP_MARGIN = 34;
  private static final int TOP_TO_BOTTOM = 35;
  private static final int TOP_TO_TOP = 36;
  private static final int TRANSFORM_PIVOT_X = 49;
  private static final int TRANSFORM_PIVOT_Y = 50;
  private static final int TRANSLATION_X = 51;
  private static final int TRANSLATION_Y = 52;
  private static final int TRANSLATION_Z = 53;
  public static final int UNSET = -1;
  private static final int UNUSED = 61;
  public static final int VERTICAL = 1;
  private static final int VERTICAL_BIAS = 37;
  public static final int VERTICAL_GUIDELINE = 1;
  private static final int VERTICAL_STYLE = 42;
  private static final int VERTICAL_WEIGHT = 40;
  private static final int VIEW_ID = 38;
  private static final int[] VISIBILITY_FLAGS = { 0, 4, 8 };
  public static final int VISIBLE = 0;
  private static final int WIDTH_DEFAULT = 54;
  private static final int WIDTH_MAX = 56;
  private static final int WIDTH_MIN = 58;
  public static final int WRAP_CONTENT = -2;
  private static SparseIntArray mapToConstant = new SparseIntArray();
  private HashMap<Integer, Constraint> mConstraints = new HashMap();
  
  static
  {
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintLeft_toLeftOf, 25);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintLeft_toRightOf, 26);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintRight_toLeftOf, 29);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintRight_toRightOf, 30);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintTop_toTopOf, 36);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintTop_toBottomOf, 35);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBottom_toTopOf, 4);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBottom_toBottomOf, 3);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBaseline_toBaselineOf, 1);
    mapToConstant.append(R.styleable.ConstraintSet_layout_editor_absoluteX, 6);
    mapToConstant.append(R.styleable.ConstraintSet_layout_editor_absoluteY, 7);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintGuide_begin, 17);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintGuide_end, 18);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintGuide_percent, 19);
    mapToConstant.append(R.styleable.ConstraintSet_android_orientation, 27);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintStart_toEndOf, 32);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintStart_toStartOf, 33);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintEnd_toStartOf, 10);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintEnd_toEndOf, 9);
    mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginLeft, 13);
    mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginTop, 16);
    mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginRight, 14);
    mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginBottom, 11);
    mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginStart, 15);
    mapToConstant.append(R.styleable.ConstraintSet_layout_goneMarginEnd, 12);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintVertical_weight, 40);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHorizontal_weight, 39);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHorizontal_chainStyle, 41);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintVertical_chainStyle, 42);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHorizontal_bias, 20);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintVertical_bias, 37);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintDimensionRatio, 5);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintLeft_creator, 61);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintTop_creator, 61);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintRight_creator, 61);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBottom_creator, 61);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintBaseline_creator, 61);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginLeft, 24);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginRight, 28);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginStart, 31);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginEnd, 8);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginTop, 34);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_marginBottom, 2);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_width, 23);
    mapToConstant.append(R.styleable.ConstraintSet_android_layout_height, 21);
    mapToConstant.append(R.styleable.ConstraintSet_android_visibility, 22);
    mapToConstant.append(R.styleable.ConstraintSet_android_alpha, 43);
    mapToConstant.append(R.styleable.ConstraintSet_android_elevation, 44);
    mapToConstant.append(R.styleable.ConstraintSet_android_rotationX, 45);
    mapToConstant.append(R.styleable.ConstraintSet_android_rotationY, 46);
    mapToConstant.append(R.styleable.ConstraintSet_android_rotation, 60);
    mapToConstant.append(R.styleable.ConstraintSet_android_scaleX, 47);
    mapToConstant.append(R.styleable.ConstraintSet_android_scaleY, 48);
    mapToConstant.append(R.styleable.ConstraintSet_android_transformPivotX, 49);
    mapToConstant.append(R.styleable.ConstraintSet_android_transformPivotY, 50);
    mapToConstant.append(R.styleable.ConstraintSet_android_translationX, 51);
    mapToConstant.append(R.styleable.ConstraintSet_android_translationY, 52);
    mapToConstant.append(R.styleable.ConstraintSet_android_translationZ, 53);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_default, 54);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_default, 55);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_max, 56);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_max, 57);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintWidth_min, 58);
    mapToConstant.append(R.styleable.ConstraintSet_layout_constraintHeight_min, 59);
    mapToConstant.append(R.styleable.ConstraintSet_android_id, 38);
  }
  
  public ConstraintSet() {}
  
  private void createHorizontalChain(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt5, int paramInt6, int paramInt7)
  {
    if (paramArrayOfInt.length < 2) {
      throw new IllegalArgumentException("must have 2 or more widgets in a chain");
    }
    if ((paramArrayOfFloat != null) && (paramArrayOfFloat.length != paramArrayOfInt.length)) {
      throw new IllegalArgumentException("must have 2 or more widgets in a chain");
    }
    if (paramArrayOfFloat != null) {
      get0verticalWeight = paramArrayOfFloat[0];
    }
    get0horizontalChainStyle = paramInt5;
    connect(paramArrayOfInt[0], paramInt6, paramInt1, paramInt2, -1);
    paramInt1 = 1;
    while (paramInt1 < paramArrayOfInt.length)
    {
      paramInt2 = paramArrayOfInt[paramInt1];
      paramInt2 = paramArrayOfInt[paramInt1];
      paramInt5 = paramInt1 - 1;
      connect(paramInt2, paramInt6, paramArrayOfInt[paramInt5], paramInt7, -1);
      connect(paramArrayOfInt[paramInt5], paramInt7, paramArrayOfInt[paramInt1], paramInt6, -1);
      if (paramArrayOfFloat != null) {
        gethorizontalWeight = paramArrayOfFloat[paramInt1];
      }
      paramInt1 += 1;
    }
    connect(paramArrayOfInt[(paramArrayOfInt.length - 1)], paramInt7, paramInt3, paramInt4, -1);
  }
  
  private Constraint fillFromAttributeList(Context paramContext, AttributeSet paramAttributeSet)
  {
    Constraint localConstraint = new Constraint(null);
    paramContext = paramContext.obtainStyledAttributes(paramAttributeSet, R.styleable.ConstraintSet);
    populateConstraint(localConstraint, paramContext);
    paramContext.recycle();
    return localConstraint;
  }
  
  private Constraint get(int paramInt)
  {
    if (!mConstraints.containsKey(Integer.valueOf(paramInt))) {
      mConstraints.put(Integer.valueOf(paramInt), new Constraint(null));
    }
    return (Constraint)mConstraints.get(Integer.valueOf(paramInt));
  }
  
  private static int lookupID(TypedArray paramTypedArray, int paramInt1, int paramInt2)
  {
    int i = paramTypedArray.getResourceId(paramInt1, paramInt2);
    paramInt2 = i;
    if (i == -1) {
      paramInt2 = paramTypedArray.getInt(paramInt1, -1);
    }
    return paramInt2;
  }
  
  private void populateConstraint(Constraint paramConstraint, TypedArray paramTypedArray)
  {
    int j = paramTypedArray.getIndexCount();
    int i = 0;
    while (i < j)
    {
      int k = paramTypedArray.getIndex(i);
      int m = mapToConstant.get(k);
      switch (m)
      {
      default: 
        StringBuilder localStringBuilder;
        switch (m)
        {
        default: 
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Unknown attribute 0x");
          localStringBuilder.append(Integer.toHexString(k));
          localStringBuilder.append("   ");
          localStringBuilder.append(mapToConstant.get(k));
          Log.w("ConstraintSet", localStringBuilder.toString());
          break;
        case 61: 
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("unused attribute 0x");
          localStringBuilder.append(Integer.toHexString(k));
          localStringBuilder.append("   ");
          localStringBuilder.append(mapToConstant.get(k));
          Log.w("ConstraintSet", localStringBuilder.toString());
          break;
        case 60: 
          rotation = paramTypedArray.getFloat(k, rotation);
        }
        break;
      case 53: 
        translationZ = paramTypedArray.getDimension(k, translationZ);
        break;
      case 52: 
        translationY = paramTypedArray.getDimension(k, translationY);
        break;
      case 51: 
        translationX = paramTypedArray.getDimension(k, translationX);
        break;
      case 50: 
        transformPivotY = paramTypedArray.getFloat(k, transformPivotY);
        break;
      case 49: 
        transformPivotX = paramTypedArray.getFloat(k, transformPivotX);
        break;
      case 48: 
        scaleY = paramTypedArray.getFloat(k, scaleY);
        break;
      case 47: 
        scaleX = paramTypedArray.getFloat(k, scaleX);
        break;
      case 46: 
        rotationY = paramTypedArray.getFloat(k, rotationY);
        break;
      case 45: 
        rotationX = paramTypedArray.getFloat(k, rotationX);
        break;
      case 44: 
        applyElevation = true;
        elevation = paramTypedArray.getDimension(k, elevation);
        break;
      case 43: 
        alpha = paramTypedArray.getFloat(k, alpha);
        break;
      case 42: 
        verticalChainStyle = paramTypedArray.getInt(k, verticalChainStyle);
        break;
      case 41: 
        horizontalChainStyle = paramTypedArray.getInt(k, horizontalChainStyle);
        break;
      case 40: 
        verticalWeight = paramTypedArray.getFloat(k, verticalWeight);
        break;
      case 39: 
        horizontalWeight = paramTypedArray.getFloat(k, horizontalWeight);
        break;
      case 38: 
        mViewId = paramTypedArray.getResourceId(k, mViewId);
        break;
      case 37: 
        verticalBias = paramTypedArray.getFloat(k, verticalBias);
        break;
      case 36: 
        topToTop = lookupID(paramTypedArray, k, topToTop);
        break;
      case 35: 
        topToBottom = lookupID(paramTypedArray, k, topToBottom);
        break;
      case 34: 
        topMargin = paramTypedArray.getDimensionPixelSize(k, topMargin);
        break;
      case 33: 
        startToStart = lookupID(paramTypedArray, k, startToStart);
        break;
      case 32: 
        startToEnd = lookupID(paramTypedArray, k, startToEnd);
        break;
      case 31: 
        startMargin = paramTypedArray.getDimensionPixelSize(k, startMargin);
        break;
      case 30: 
        rightToRight = lookupID(paramTypedArray, k, rightToRight);
        break;
      case 29: 
        rightToLeft = lookupID(paramTypedArray, k, rightToLeft);
        break;
      case 28: 
        rightMargin = paramTypedArray.getDimensionPixelSize(k, rightMargin);
        break;
      case 27: 
        orientation = paramTypedArray.getInt(k, orientation);
        break;
      case 26: 
        leftToRight = lookupID(paramTypedArray, k, leftToRight);
        break;
      case 25: 
        leftToLeft = lookupID(paramTypedArray, k, leftToLeft);
        break;
      case 24: 
        leftMargin = paramTypedArray.getDimensionPixelSize(k, leftMargin);
        break;
      case 23: 
        mWidth = paramTypedArray.getLayoutDimension(k, mWidth);
        break;
      case 22: 
        visibility = paramTypedArray.getInt(k, visibility);
        visibility = VISIBILITY_FLAGS[visibility];
        break;
      case 21: 
        mHeight = paramTypedArray.getLayoutDimension(k, mHeight);
        break;
      case 20: 
        horizontalBias = paramTypedArray.getFloat(k, horizontalBias);
        break;
      case 19: 
        guidePercent = paramTypedArray.getFloat(k, guidePercent);
        break;
      case 18: 
        guideEnd = paramTypedArray.getDimensionPixelOffset(k, guideEnd);
        break;
      case 17: 
        guideBegin = paramTypedArray.getDimensionPixelOffset(k, guideBegin);
        break;
      case 16: 
        goneTopMargin = paramTypedArray.getDimensionPixelSize(k, goneTopMargin);
        break;
      case 15: 
        goneStartMargin = paramTypedArray.getDimensionPixelSize(k, goneStartMargin);
        break;
      case 14: 
        goneRightMargin = paramTypedArray.getDimensionPixelSize(k, goneRightMargin);
        break;
      case 13: 
        goneLeftMargin = paramTypedArray.getDimensionPixelSize(k, goneLeftMargin);
        break;
      case 12: 
        goneEndMargin = paramTypedArray.getDimensionPixelSize(k, goneEndMargin);
        break;
      case 11: 
        goneBottomMargin = paramTypedArray.getDimensionPixelSize(k, goneBottomMargin);
        break;
      case 10: 
        endToStart = lookupID(paramTypedArray, k, endToStart);
        break;
      case 9: 
        bottomToTop = lookupID(paramTypedArray, k, endToEnd);
        break;
      case 8: 
        endMargin = paramTypedArray.getDimensionPixelSize(k, endMargin);
        break;
      case 7: 
        editorAbsoluteY = paramTypedArray.getDimensionPixelOffset(k, editorAbsoluteY);
        break;
      case 6: 
        editorAbsoluteX = paramTypedArray.getDimensionPixelOffset(k, editorAbsoluteX);
        break;
      case 5: 
        dimensionRatio = paramTypedArray.getString(k);
        break;
      case 4: 
        bottomToTop = lookupID(paramTypedArray, k, bottomToTop);
        break;
      case 3: 
        bottomToBottom = lookupID(paramTypedArray, k, bottomToBottom);
        break;
      case 2: 
        bottomMargin = paramTypedArray.getDimensionPixelSize(k, bottomMargin);
        break;
      }
      baselineToBaseline = lookupID(paramTypedArray, k, baselineToBaseline);
      i += 1;
    }
  }
  
  private String sideToString(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "undefined";
    case 7: 
      return "end";
    case 6: 
      return "start";
    case 5: 
      return "baseline";
    case 4: 
      return "bottom";
    case 3: 
      return "top";
    case 2: 
      return "right";
    }
    return "left";
  }
  
  public void addToHorizontalChain(int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    if (paramInt2 == 0) {
      i = 1;
    } else {
      i = 2;
    }
    connect(paramInt1, 1, paramInt2, i, 0);
    if (paramInt3 == 0) {
      i = 2;
    } else {
      i = 1;
    }
    connect(paramInt1, 2, paramInt3, i, 0);
    if (paramInt2 != 0) {
      connect(paramInt2, 2, paramInt1, 1, 0);
    }
    if (paramInt3 != 0) {
      connect(paramInt3, 1, paramInt1, 2, 0);
    }
  }
  
  public void addToHorizontalChainRTL(int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    if (paramInt2 == 0) {
      i = 6;
    } else {
      i = 7;
    }
    connect(paramInt1, 6, paramInt2, i, 0);
    if (paramInt3 == 0) {
      i = 7;
    } else {
      i = 6;
    }
    connect(paramInt1, 7, paramInt3, i, 0);
    if (paramInt2 != 0) {
      connect(paramInt2, 7, paramInt1, 6, 0);
    }
    if (paramInt3 != 0) {
      connect(paramInt3, 6, paramInt1, 7, 0);
    }
  }
  
  public void addToVerticalChain(int paramInt1, int paramInt2, int paramInt3)
  {
    int i;
    if (paramInt2 == 0) {
      i = 3;
    } else {
      i = 4;
    }
    connect(paramInt1, 3, paramInt2, i, 0);
    if (paramInt3 == 0) {
      i = 4;
    } else {
      i = 3;
    }
    connect(paramInt1, 4, paramInt3, i, 0);
    if (paramInt2 != 0) {
      connect(paramInt2, 4, paramInt1, 3, 0);
    }
    if (paramInt2 != 0) {
      connect(paramInt3, 3, paramInt1, 4, 0);
    }
  }
  
  public void applyTo(ConstraintLayout paramConstraintLayout)
  {
    applyToInternal(paramConstraintLayout);
    paramConstraintLayout.setConstraintSet(null);
  }
  
  void applyToInternal(ConstraintLayout paramConstraintLayout)
  {
    int j = paramConstraintLayout.getChildCount();
    Object localObject1 = new HashSet(mConstraints.keySet());
    int i = 0;
    Object localObject2;
    Object localObject3;
    Object localObject4;
    while (i < j)
    {
      localObject2 = paramConstraintLayout.getChildAt(i);
      int k = ((View)localObject2).getId();
      if (mConstraints.containsKey(Integer.valueOf(k)))
      {
        ((HashSet)localObject1).remove(Integer.valueOf(k));
        localObject3 = (Constraint)mConstraints.get(Integer.valueOf(k));
        if ((mHelperType != -1) && (mHelperType == 1))
        {
          localObject4 = (Barrier)localObject2;
          ((Barrier)localObject4).setId(k);
          ((Barrier)localObject4).setReferencedIds(mReferenceIds);
          ((Barrier)localObject4).setType(mBarrierDirection);
          ((Constraint)localObject3).applyTo(paramConstraintLayout.generateDefaultLayoutParams());
        }
        localObject4 = (ConstraintLayout.LayoutParams)((View)localObject2).getLayoutParams();
        ((Constraint)localObject3).applyTo((ConstraintLayout.LayoutParams)localObject4);
        ((View)localObject2).setLayoutParams((ViewGroup.LayoutParams)localObject4);
        ((View)localObject2).setVisibility(visibility);
        if (Build.VERSION.SDK_INT >= 17)
        {
          ((View)localObject2).setAlpha(alpha);
          ((View)localObject2).setRotation(rotation);
          ((View)localObject2).setRotationX(rotationX);
          ((View)localObject2).setRotationY(rotationY);
          ((View)localObject2).setScaleX(scaleX);
          ((View)localObject2).setScaleY(scaleY);
          ((View)localObject2).setPivotX(transformPivotX);
          ((View)localObject2).setPivotY(transformPivotY);
          ((View)localObject2).setTranslationX(translationX);
          ((View)localObject2).setTranslationY(translationY);
          if (Build.VERSION.SDK_INT >= 21)
          {
            ((View)localObject2).setTranslationZ(translationZ);
            if (applyElevation) {
              ((View)localObject2).setElevation(elevation);
            }
          }
        }
      }
      i += 1;
    }
    localObject1 = ((HashSet)localObject1).iterator();
    while (((Iterator)localObject1).hasNext())
    {
      localObject3 = (Integer)((Iterator)localObject1).next();
      localObject2 = (Constraint)mConstraints.get(localObject3);
      if ((mHelperType != -1) && (mHelperType == 1))
      {
        localObject4 = new Barrier(paramConstraintLayout.getContext());
        ((Barrier)localObject4).setId(((Integer)localObject3).intValue());
        ((Barrier)localObject4).setReferencedIds(mReferenceIds);
        ((Barrier)localObject4).setType(mBarrierDirection);
        ConstraintLayout.LayoutParams localLayoutParams = paramConstraintLayout.generateDefaultLayoutParams();
        ((Constraint)localObject2).applyTo(localLayoutParams);
        paramConstraintLayout.addView((View)localObject4, localLayoutParams);
      }
      if (mIsGuideline)
      {
        localObject4 = new Guideline(paramConstraintLayout.getContext());
        ((Guideline)localObject4).setId(((Integer)localObject3).intValue());
        localObject3 = paramConstraintLayout.generateDefaultLayoutParams();
        ((Constraint)localObject2).applyTo((ConstraintLayout.LayoutParams)localObject3);
        paramConstraintLayout.addView((View)localObject4, (ViewGroup.LayoutParams)localObject3);
      }
    }
  }
  
  public void center(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, float paramFloat)
  {
    if (paramInt4 < 0) {
      throw new IllegalArgumentException("margin must be > 0");
    }
    if (paramInt7 < 0) {
      throw new IllegalArgumentException("margin must be > 0");
    }
    if ((paramFloat > 0.0F) && (paramFloat <= 1.0F))
    {
      if ((paramInt3 != 1) && (paramInt3 != 2))
      {
        if ((paramInt3 != 6) && (paramInt3 != 7))
        {
          connect(paramInt1, 3, paramInt2, paramInt3, paramInt4);
          connect(paramInt1, 4, paramInt5, paramInt6, paramInt7);
          mConstraints.get(Integer.valueOf(paramInt1))).verticalBias = paramFloat;
          return;
        }
        connect(paramInt1, 6, paramInt2, paramInt3, paramInt4);
        connect(paramInt1, 7, paramInt5, paramInt6, paramInt7);
        mConstraints.get(Integer.valueOf(paramInt1))).horizontalBias = paramFloat;
        return;
      }
      connect(paramInt1, 1, paramInt2, paramInt3, paramInt4);
      connect(paramInt1, 2, paramInt5, paramInt6, paramInt7);
      mConstraints.get(Integer.valueOf(paramInt1))).horizontalBias = paramFloat;
      return;
    }
    throw new IllegalArgumentException("bias must be between 0 and 1 inclusive");
  }
  
  public void centerHorizontally(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0)
    {
      center(paramInt1, 0, 1, 0, 0, 2, 0, 0.5F);
      return;
    }
    center(paramInt1, paramInt2, 2, 0, paramInt2, 1, 0, 0.5F);
  }
  
  public void centerHorizontally(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, float paramFloat)
  {
    connect(paramInt1, 1, paramInt2, paramInt3, paramInt4);
    connect(paramInt1, 2, paramInt5, paramInt6, paramInt7);
    mConstraints.get(Integer.valueOf(paramInt1))).horizontalBias = paramFloat;
  }
  
  public void centerHorizontallyRtl(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0)
    {
      center(paramInt1, 0, 6, 0, 0, 7, 0, 0.5F);
      return;
    }
    center(paramInt1, paramInt2, 7, 0, paramInt2, 6, 0, 0.5F);
  }
  
  public void centerHorizontallyRtl(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, float paramFloat)
  {
    connect(paramInt1, 6, paramInt2, paramInt3, paramInt4);
    connect(paramInt1, 7, paramInt5, paramInt6, paramInt7);
    mConstraints.get(Integer.valueOf(paramInt1))).horizontalBias = paramFloat;
  }
  
  public void centerVertically(int paramInt1, int paramInt2)
  {
    if (paramInt2 == 0)
    {
      center(paramInt1, 0, 3, 0, 0, 4, 0, 0.5F);
      return;
    }
    center(paramInt1, paramInt2, 4, 0, paramInt2, 3, 0, 0.5F);
  }
  
  public void centerVertically(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, float paramFloat)
  {
    connect(paramInt1, 3, paramInt2, paramInt3, paramInt4);
    connect(paramInt1, 4, paramInt5, paramInt6, paramInt7);
    mConstraints.get(Integer.valueOf(paramInt1))).verticalBias = paramFloat;
  }
  
  public void clear(int paramInt)
  {
    mConstraints.remove(Integer.valueOf(paramInt));
  }
  
  public void clear(int paramInt1, int paramInt2)
  {
    if (mConstraints.containsKey(Integer.valueOf(paramInt1)))
    {
      Constraint localConstraint = (Constraint)mConstraints.get(Integer.valueOf(paramInt1));
      switch (paramInt2)
      {
      default: 
        throw new IllegalArgumentException("unknown constraint");
      case 7: 
        endToStart = -1;
        endToEnd = -1;
        endMargin = -1;
        goneEndMargin = -1;
        return;
      case 6: 
        startToEnd = -1;
        startToStart = -1;
        startMargin = -1;
        goneStartMargin = -1;
        return;
      case 5: 
        baselineToBaseline = -1;
        return;
      case 4: 
        bottomToTop = -1;
        bottomToBottom = -1;
        bottomMargin = -1;
        goneBottomMargin = -1;
        return;
      case 3: 
        topToBottom = -1;
        topToTop = -1;
        topMargin = -1;
        goneTopMargin = -1;
        return;
      case 2: 
        rightToRight = -1;
        rightToLeft = -1;
        rightMargin = -1;
        goneRightMargin = -1;
        return;
      }
      leftToRight = -1;
      leftToLeft = -1;
      leftMargin = -1;
      goneLeftMargin = -1;
    }
  }
  
  public void clone(Context paramContext, int paramInt)
  {
    clone((ConstraintLayout)LayoutInflater.from(paramContext).inflate(paramInt, null));
  }
  
  public void clone(ConstraintLayout paramConstraintLayout)
  {
    int j = paramConstraintLayout.getChildCount();
    mConstraints.clear();
    int i = 0;
    while (i < j)
    {
      View localView = paramConstraintLayout.getChildAt(i);
      ConstraintLayout.LayoutParams localLayoutParams = (ConstraintLayout.LayoutParams)localView.getLayoutParams();
      int k = localView.getId();
      if (!mConstraints.containsKey(Integer.valueOf(k))) {
        mConstraints.put(Integer.valueOf(k), new Constraint(null));
      }
      Constraint localConstraint = (Constraint)mConstraints.get(Integer.valueOf(k));
      localConstraint.fillFrom(k, localLayoutParams);
      visibility = localView.getVisibility();
      if (Build.VERSION.SDK_INT >= 17)
      {
        alpha = localView.getAlpha();
        rotation = localView.getRotation();
        rotationX = localView.getRotationX();
        rotationY = localView.getRotationY();
        scaleX = localView.getScaleX();
        scaleY = localView.getScaleY();
        transformPivotX = localView.getPivotX();
        transformPivotY = localView.getPivotY();
        translationX = localView.getTranslationX();
        translationY = localView.getTranslationY();
        if (Build.VERSION.SDK_INT >= 21)
        {
          translationZ = localView.getTranslationZ();
          if (applyElevation) {
            elevation = localView.getElevation();
          }
        }
      }
      i += 1;
    }
  }
  
  public void clone(ConstraintSet paramConstraintSet)
  {
    mConstraints.clear();
    Iterator localIterator = mConstraints.keySet().iterator();
    while (localIterator.hasNext())
    {
      Integer localInteger = (Integer)localIterator.next();
      mConstraints.put(localInteger, ((Constraint)mConstraints.get(localInteger)).clone());
    }
  }
  
  public void clone(Constraints paramConstraints)
  {
    int j = paramConstraints.getChildCount();
    mConstraints.clear();
    int i = 0;
    while (i < j)
    {
      View localView = paramConstraints.getChildAt(i);
      Constraints.LayoutParams localLayoutParams = (Constraints.LayoutParams)localView.getLayoutParams();
      int k = localView.getId();
      if (!mConstraints.containsKey(Integer.valueOf(k))) {
        mConstraints.put(Integer.valueOf(k), new Constraint(null));
      }
      Constraint localConstraint = (Constraint)mConstraints.get(Integer.valueOf(k));
      if ((localView instanceof ConstraintHelper)) {
        localConstraint.fillFromConstraints((ConstraintHelper)localView, k, localLayoutParams);
      }
      localConstraint.fillFromConstraints(k, localLayoutParams);
      i += 1;
    }
  }
  
  public void connect(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    if (!mConstraints.containsKey(Integer.valueOf(paramInt1))) {
      mConstraints.put(Integer.valueOf(paramInt1), new Constraint(null));
    }
    Object localObject = (Constraint)mConstraints.get(Integer.valueOf(paramInt1));
    switch (paramInt2)
    {
    default: 
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(sideToString(paramInt2));
      ((StringBuilder)localObject).append(" to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" unknown");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 7: 
      if (paramInt4 == 7)
      {
        endToEnd = paramInt3;
        endToStart = -1;
        return;
      }
      if (paramInt4 == 6)
      {
        endToStart = paramInt3;
        endToEnd = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 6: 
      if (paramInt4 == 6)
      {
        startToStart = paramInt3;
        startToEnd = -1;
        return;
      }
      if (paramInt4 == 7)
      {
        startToEnd = paramInt3;
        startToStart = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 5: 
      if (paramInt4 == 5)
      {
        baselineToBaseline = paramInt3;
        bottomToBottom = -1;
        bottomToTop = -1;
        topToTop = -1;
        topToBottom = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 4: 
      if (paramInt4 == 4)
      {
        bottomToBottom = paramInt3;
        bottomToTop = -1;
        baselineToBaseline = -1;
        return;
      }
      if (paramInt4 == 3)
      {
        bottomToTop = paramInt3;
        bottomToBottom = -1;
        baselineToBaseline = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 3: 
      if (paramInt4 == 3)
      {
        topToTop = paramInt3;
        topToBottom = -1;
        baselineToBaseline = -1;
        return;
      }
      if (paramInt4 == 4)
      {
        topToBottom = paramInt3;
        topToTop = -1;
        baselineToBaseline = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 2: 
      if (paramInt4 == 1)
      {
        rightToLeft = paramInt3;
        rightToRight = -1;
        return;
      }
      if (paramInt4 == 2)
      {
        rightToRight = paramInt3;
        rightToLeft = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    if (paramInt4 == 1)
    {
      leftToLeft = paramInt3;
      leftToRight = -1;
      return;
    }
    if (paramInt4 == 2)
    {
      leftToRight = paramInt3;
      leftToLeft = -1;
      return;
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("left to ");
    ((StringBuilder)localObject).append(sideToString(paramInt4));
    ((StringBuilder)localObject).append(" undefined");
    throw new IllegalArgumentException(((StringBuilder)localObject).toString());
  }
  
  public void connect(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    if (!mConstraints.containsKey(Integer.valueOf(paramInt1))) {
      mConstraints.put(Integer.valueOf(paramInt1), new Constraint(null));
    }
    Object localObject = (Constraint)mConstraints.get(Integer.valueOf(paramInt1));
    switch (paramInt2)
    {
    default: 
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(sideToString(paramInt2));
      ((StringBuilder)localObject).append(" to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" unknown");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 7: 
      if (paramInt4 == 7)
      {
        endToEnd = paramInt3;
        endToStart = -1;
      }
      else
      {
        if (paramInt4 != 6) {
          break label204;
        }
        endToStart = paramInt3;
        endToEnd = -1;
      }
      endMargin = paramInt5;
      return;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 6: 
      if (paramInt4 == 6)
      {
        startToStart = paramInt3;
        startToEnd = -1;
      }
      else
      {
        if (paramInt4 != 7) {
          break label305;
        }
        startToEnd = paramInt3;
        startToStart = -1;
      }
      startMargin = paramInt5;
      return;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 5: 
      if (paramInt4 == 5)
      {
        baselineToBaseline = paramInt3;
        bottomToBottom = -1;
        bottomToTop = -1;
        topToTop = -1;
        topToBottom = -1;
        return;
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 4: 
      if (paramInt4 == 4)
      {
        bottomToBottom = paramInt3;
        bottomToTop = -1;
        baselineToBaseline = -1;
      }
      else
      {
        if (paramInt4 != 3) {
          break label505;
        }
        bottomToTop = paramInt3;
        bottomToBottom = -1;
        baselineToBaseline = -1;
      }
      bottomMargin = paramInt5;
      return;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 3: 
      if (paramInt4 == 3)
      {
        topToTop = paramInt3;
        topToBottom = -1;
        baselineToBaseline = -1;
      }
      else
      {
        if (paramInt4 != 4) {
          break label616;
        }
        topToBottom = paramInt3;
        topToTop = -1;
        baselineToBaseline = -1;
      }
      topMargin = paramInt5;
      return;
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    case 2: 
      label204:
      label305:
      label505:
      label616:
      if (paramInt4 == 1)
      {
        rightToLeft = paramInt3;
        rightToRight = -1;
      }
      else
      {
        if (paramInt4 != 2) {
          break label715;
        }
        rightToRight = paramInt3;
        rightToLeft = -1;
      }
      rightMargin = paramInt5;
      return;
      label715:
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("right to ");
      ((StringBuilder)localObject).append(sideToString(paramInt4));
      ((StringBuilder)localObject).append(" undefined");
      throw new IllegalArgumentException(((StringBuilder)localObject).toString());
    }
    if (paramInt4 == 1)
    {
      leftToLeft = paramInt3;
      leftToRight = -1;
    }
    else
    {
      if (paramInt4 != 2) {
        break label814;
      }
      leftToRight = paramInt3;
      leftToLeft = -1;
    }
    leftMargin = paramInt5;
    return;
    label814:
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Left to ");
    ((StringBuilder)localObject).append(sideToString(paramInt4));
    ((StringBuilder)localObject).append(" undefined");
    throw new IllegalArgumentException(((StringBuilder)localObject).toString());
  }
  
  public void constrainDefaultHeight(int paramInt1, int paramInt2)
  {
    getheightDefault = paramInt2;
  }
  
  public void constrainDefaultWidth(int paramInt1, int paramInt2)
  {
    getwidthDefault = paramInt2;
  }
  
  public void constrainHeight(int paramInt1, int paramInt2)
  {
    getmHeight = paramInt2;
  }
  
  public void constrainMaxHeight(int paramInt1, int paramInt2)
  {
    getheightMax = paramInt2;
  }
  
  public void constrainMaxWidth(int paramInt1, int paramInt2)
  {
    getwidthMax = paramInt2;
  }
  
  public void constrainMinHeight(int paramInt1, int paramInt2)
  {
    getheightMin = paramInt2;
  }
  
  public void constrainMinWidth(int paramInt1, int paramInt2)
  {
    getwidthMin = paramInt2;
  }
  
  public void constrainWidth(int paramInt1, int paramInt2)
  {
    getmWidth = paramInt2;
  }
  
  public void create(int paramInt1, int paramInt2)
  {
    Constraint localConstraint = get(paramInt1);
    mIsGuideline = true;
    orientation = paramInt2;
  }
  
  public void createBarrier(int paramInt1, int paramInt2, int... paramVarArgs)
  {
    Constraint localConstraint = get(paramInt1);
    mHelperType = 1;
    mBarrierDirection = paramInt2;
    mIsGuideline = false;
    mReferenceIds = paramVarArgs;
  }
  
  public void createHorizontalChain(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt5)
  {
    createHorizontalChain(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt, paramArrayOfFloat, paramInt5, 1, 2);
  }
  
  public void createHorizontalChainRtl(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt5)
  {
    createHorizontalChain(paramInt1, paramInt2, paramInt3, paramInt4, paramArrayOfInt, paramArrayOfFloat, paramInt5, 6, 7);
  }
  
  public void createVerticalChain(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int[] paramArrayOfInt, float[] paramArrayOfFloat, int paramInt5)
  {
    if (paramArrayOfInt.length < 2) {
      throw new IllegalArgumentException("must have 2 or more widgets in a chain");
    }
    if ((paramArrayOfFloat != null) && (paramArrayOfFloat.length != paramArrayOfInt.length)) {
      throw new IllegalArgumentException("must have 2 or more widgets in a chain");
    }
    if (paramArrayOfFloat != null) {
      get0verticalWeight = paramArrayOfFloat[0];
    }
    get0verticalChainStyle = paramInt5;
    connect(paramArrayOfInt[0], 3, paramInt1, paramInt2, 0);
    paramInt1 = 1;
    while (paramInt1 < paramArrayOfInt.length)
    {
      paramInt2 = paramArrayOfInt[paramInt1];
      paramInt2 = paramArrayOfInt[paramInt1];
      paramInt5 = paramInt1 - 1;
      connect(paramInt2, 3, paramArrayOfInt[paramInt5], 4, 0);
      connect(paramArrayOfInt[paramInt5], 4, paramArrayOfInt[paramInt1], 3, 0);
      if (paramArrayOfFloat != null) {
        getverticalWeight = paramArrayOfFloat[paramInt1];
      }
      paramInt1 += 1;
    }
    connect(paramArrayOfInt[(paramArrayOfInt.length - 1)], 4, paramInt3, paramInt4, 0);
  }
  
  public boolean getApplyElevation(int paramInt)
  {
    return getapplyElevation;
  }
  
  public void load(Context paramContext, int paramInt)
  {
    XmlResourceParser localXmlResourceParser = paramContext.getResources().getXml(paramInt);
    try
    {
      paramInt = localXmlResourceParser.getEventType();
    }
    catch (IOException paramContext)
    {
      String str;
      Constraint localConstraint;
      paramContext.printStackTrace();
      return;
    }
    catch (XmlPullParserException paramContext)
    {
      paramContext.printStackTrace();
    }
    str = localXmlResourceParser.getName();
    localConstraint = fillFromAttributeList(paramContext, Xml.asAttributeSet(localXmlResourceParser));
    if (str.equalsIgnoreCase("Guideline")) {
      mIsGuideline = true;
    }
    mConstraints.put(Integer.valueOf(mViewId), localConstraint);
    break label83;
    localXmlResourceParser.getName();
    for (;;)
    {
      label83:
      paramInt = localXmlResourceParser.next();
      while (paramInt == 1) {
        return;
      }
      if (paramInt == 0) {
        break;
      }
      switch (paramInt)
      {
      }
    }
  }
  
  public void removeFromHorizontalChain(int paramInt)
  {
    if (mConstraints.containsKey(Integer.valueOf(paramInt)))
    {
      Constraint localConstraint = (Constraint)mConstraints.get(Integer.valueOf(paramInt));
      int i = leftToRight;
      int j = rightToLeft;
      if ((i == -1) && (j == -1))
      {
        j = startToEnd;
        int k = endToStart;
        if ((j != -1) || (k != -1)) {
          if ((j != -1) && (k != -1))
          {
            connect(j, 7, k, 6, 0);
            connect(k, 6, i, 7, 0);
          }
          else if ((i != -1) || (k != -1))
          {
            if (rightToRight != -1) {
              connect(i, 7, rightToRight, 7, 0);
            } else if (leftToLeft != -1) {
              connect(k, 6, leftToLeft, 6, 0);
            }
          }
        }
        clear(paramInt, 6);
        clear(paramInt, 7);
        return;
      }
      if ((i != -1) && (j != -1))
      {
        connect(i, 2, j, 1, 0);
        connect(j, 1, i, 2, 0);
      }
      else if ((i != -1) || (j != -1))
      {
        if (rightToRight != -1) {
          connect(i, 2, rightToRight, 2, 0);
        } else if (leftToLeft != -1) {
          connect(j, 1, leftToLeft, 1, 0);
        }
      }
      clear(paramInt, 1);
      clear(paramInt, 2);
    }
  }
  
  public void removeFromVerticalChain(int paramInt)
  {
    if (mConstraints.containsKey(Integer.valueOf(paramInt)))
    {
      Constraint localConstraint = (Constraint)mConstraints.get(Integer.valueOf(paramInt));
      int i = topToBottom;
      int j = bottomToTop;
      if ((i != -1) || (j != -1)) {
        if ((i != -1) && (j != -1))
        {
          connect(i, 4, j, 3, 0);
          connect(j, 3, i, 4, 0);
        }
        else if ((i != -1) || (j != -1))
        {
          if (bottomToBottom != -1) {
            connect(i, 4, bottomToBottom, 4, 0);
          } else if (topToTop != -1) {
            connect(j, 3, topToTop, 3, 0);
          }
        }
      }
    }
    clear(paramInt, 3);
    clear(paramInt, 4);
  }
  
  public void setAlpha(int paramInt, float paramFloat)
  {
    getalpha = paramFloat;
  }
  
  public void setApplyElevation(int paramInt, boolean paramBoolean)
  {
    getapplyElevation = paramBoolean;
  }
  
  public void setBarrierType(int paramInt1, int paramInt2) {}
  
  public void setDimensionRatio(int paramInt, String paramString)
  {
    getdimensionRatio = paramString;
  }
  
  public void setElevation(int paramInt, float paramFloat)
  {
    getelevation = paramFloat;
    getapplyElevation = true;
  }
  
  public void setGoneMargin(int paramInt1, int paramInt2, int paramInt3)
  {
    Constraint localConstraint = get(paramInt1);
    switch (paramInt2)
    {
    default: 
      throw new IllegalArgumentException("unknown constraint");
    case 7: 
      goneEndMargin = paramInt3;
      return;
    case 6: 
      goneStartMargin = paramInt3;
      return;
    case 5: 
      throw new IllegalArgumentException("baseline does not support margins");
    case 4: 
      goneBottomMargin = paramInt3;
      return;
    case 3: 
      goneTopMargin = paramInt3;
      return;
    case 2: 
      goneRightMargin = paramInt3;
      return;
    }
    goneLeftMargin = paramInt3;
  }
  
  public void setGuidelineBegin(int paramInt1, int paramInt2)
  {
    getguideBegin = paramInt2;
    getguideEnd = -1;
    getguidePercent = -1.0F;
  }
  
  public void setGuidelineEnd(int paramInt1, int paramInt2)
  {
    getguideEnd = paramInt2;
    getguideBegin = -1;
    getguidePercent = -1.0F;
  }
  
  public void setGuidelinePercent(int paramInt, float paramFloat)
  {
    getguidePercent = paramFloat;
    getguideEnd = -1;
    getguideBegin = -1;
  }
  
  public void setHorizontalBias(int paramInt, float paramFloat)
  {
    gethorizontalBias = paramFloat;
  }
  
  public void setHorizontalChainStyle(int paramInt1, int paramInt2)
  {
    gethorizontalChainStyle = paramInt2;
  }
  
  public void setHorizontalWeight(int paramInt, float paramFloat)
  {
    gethorizontalWeight = paramFloat;
  }
  
  public void setMargin(int paramInt1, int paramInt2, int paramInt3)
  {
    Constraint localConstraint = get(paramInt1);
    switch (paramInt2)
    {
    default: 
      throw new IllegalArgumentException("unknown constraint");
    case 7: 
      endMargin = paramInt3;
      return;
    case 6: 
      startMargin = paramInt3;
      return;
    case 5: 
      throw new IllegalArgumentException("baseline does not support margins");
    case 4: 
      bottomMargin = paramInt3;
      return;
    case 3: 
      topMargin = paramInt3;
      return;
    case 2: 
      rightMargin = paramInt3;
      return;
    }
    leftMargin = paramInt3;
  }
  
  public void setRotation(int paramInt, float paramFloat)
  {
    getrotation = paramFloat;
  }
  
  public void setRotationX(int paramInt, float paramFloat)
  {
    getrotationX = paramFloat;
  }
  
  public void setRotationY(int paramInt, float paramFloat)
  {
    getrotationY = paramFloat;
  }
  
  public void setScaleX(int paramInt, float paramFloat)
  {
    getscaleX = paramFloat;
  }
  
  public void setScaleY(int paramInt, float paramFloat)
  {
    getscaleY = paramFloat;
  }
  
  public void setTransformPivot(int paramInt, float paramFloat1, float paramFloat2)
  {
    Constraint localConstraint = get(paramInt);
    transformPivotY = paramFloat2;
    transformPivotX = paramFloat1;
  }
  
  public void setTransformPivotX(int paramInt, float paramFloat)
  {
    gettransformPivotX = paramFloat;
  }
  
  public void setTransformPivotY(int paramInt, float paramFloat)
  {
    gettransformPivotY = paramFloat;
  }
  
  public void setTranslation(int paramInt, float paramFloat1, float paramFloat2)
  {
    Constraint localConstraint = get(paramInt);
    translationX = paramFloat1;
    translationY = paramFloat2;
  }
  
  public void setTranslationX(int paramInt, float paramFloat)
  {
    gettranslationX = paramFloat;
  }
  
  public void setTranslationY(int paramInt, float paramFloat)
  {
    gettranslationY = paramFloat;
  }
  
  public void setTranslationZ(int paramInt, float paramFloat)
  {
    gettranslationZ = paramFloat;
  }
  
  public void setVerticalBias(int paramInt, float paramFloat)
  {
    getverticalBias = paramFloat;
  }
  
  public void setVerticalChainStyle(int paramInt1, int paramInt2)
  {
    getverticalChainStyle = paramInt2;
  }
  
  public void setVerticalWeight(int paramInt, float paramFloat)
  {
    getverticalWeight = paramFloat;
  }
  
  public void setVisibility(int paramInt1, int paramInt2)
  {
    getvisibility = paramInt2;
  }
  
  private static class Constraint
  {
    static final int UNSET = -1;
    public float alpha = 1.0F;
    public boolean applyElevation = false;
    public int baselineToBaseline = -1;
    public int bottomMargin = -1;
    public int bottomToBottom = -1;
    public int bottomToTop = -1;
    public String dimensionRatio = null;
    public int editorAbsoluteX = -1;
    public int editorAbsoluteY = -1;
    public float elevation = 0.0F;
    public int endMargin = -1;
    public int endToEnd = -1;
    public int endToStart = -1;
    public int goneBottomMargin = -1;
    public int goneEndMargin = -1;
    public int goneLeftMargin = -1;
    public int goneRightMargin = -1;
    public int goneStartMargin = -1;
    public int goneTopMargin = -1;
    public int guideBegin = -1;
    public int guideEnd = -1;
    public float guidePercent = -1.0F;
    public int heightDefault = -1;
    public int heightMax = -1;
    public int heightMin = -1;
    public float horizontalBias = 0.5F;
    public int horizontalChainStyle = 0;
    public float horizontalWeight = 0.0F;
    public int leftMargin = -1;
    public int leftToLeft = -1;
    public int leftToRight = -1;
    public int mBarrierDirection = -1;
    public int mHeight;
    public int mHelperType = -1;
    boolean mIsGuideline = false;
    public int[] mReferenceIds;
    int mViewId;
    public int mWidth;
    public int orientation = -1;
    public int rightMargin = -1;
    public int rightToLeft = -1;
    public int rightToRight = -1;
    public float rotation = 0.0F;
    public float rotationX = 0.0F;
    public float rotationY = 0.0F;
    public float scaleX = 1.0F;
    public float scaleY = 1.0F;
    public int startMargin = -1;
    public int startToEnd = -1;
    public int startToStart = -1;
    public int topMargin = -1;
    public int topToBottom = -1;
    public int topToTop = -1;
    public float transformPivotX = 0.0F;
    public float transformPivotY = 0.0F;
    public float translationX = 0.0F;
    public float translationY = 0.0F;
    public float translationZ = 0.0F;
    public float verticalBias = 0.5F;
    public int verticalChainStyle = 0;
    public float verticalWeight = 0.0F;
    public int visibility = 0;
    public int widthDefault = -1;
    public int widthMax = -1;
    public int widthMin = -1;
    
    private Constraint() {}
    
    private void fillFrom(int paramInt, ConstraintLayout.LayoutParams paramLayoutParams)
    {
      mViewId = paramInt;
      leftToLeft = leftToLeft;
      leftToRight = leftToRight;
      rightToLeft = rightToLeft;
      rightToRight = rightToRight;
      topToTop = topToTop;
      topToBottom = topToBottom;
      bottomToTop = bottomToTop;
      bottomToBottom = bottomToBottom;
      baselineToBaseline = baselineToBaseline;
      startToEnd = startToEnd;
      startToStart = startToStart;
      endToStart = endToStart;
      endToEnd = endToEnd;
      horizontalBias = horizontalBias;
      verticalBias = verticalBias;
      dimensionRatio = dimensionRatio;
      editorAbsoluteX = editorAbsoluteX;
      editorAbsoluteY = editorAbsoluteY;
      orientation = orientation;
      guidePercent = guidePercent;
      guideBegin = guideBegin;
      guideEnd = guideEnd;
      mWidth = width;
      mHeight = height;
      leftMargin = leftMargin;
      rightMargin = rightMargin;
      topMargin = topMargin;
      bottomMargin = bottomMargin;
      verticalWeight = verticalWeight;
      horizontalWeight = horizontalWeight;
      verticalChainStyle = verticalChainStyle;
      horizontalChainStyle = horizontalChainStyle;
      widthDefault = matchConstraintDefaultWidth;
      heightDefault = matchConstraintDefaultHeight;
      widthMax = matchConstraintMaxWidth;
      heightMax = matchConstraintMaxHeight;
      widthMin = matchConstraintMinWidth;
      heightMin = matchConstraintMinHeight;
      if (Build.VERSION.SDK_INT >= 17)
      {
        endMargin = paramLayoutParams.getMarginEnd();
        startMargin = paramLayoutParams.getMarginStart();
      }
    }
    
    private void fillFromConstraints(int paramInt, Constraints.LayoutParams paramLayoutParams)
    {
      fillFrom(paramInt, paramLayoutParams);
      alpha = alpha;
      rotation = rotation;
      rotationX = rotationX;
      rotationY = rotationY;
      scaleX = scaleX;
      scaleY = scaleY;
      transformPivotX = transformPivotX;
      transformPivotY = transformPivotY;
      translationX = translationX;
      translationY = translationY;
      translationZ = translationZ;
      elevation = elevation;
      applyElevation = applyElevation;
    }
    
    private void fillFromConstraints(ConstraintHelper paramConstraintHelper, int paramInt, Constraints.LayoutParams paramLayoutParams)
    {
      fillFromConstraints(paramInt, paramLayoutParams);
      if ((paramConstraintHelper instanceof Barrier))
      {
        mHelperType = 1;
        paramConstraintHelper = (Barrier)paramConstraintHelper;
        mBarrierDirection = paramConstraintHelper.getType();
        mReferenceIds = paramConstraintHelper.getReferencedIds();
      }
    }
    
    public void applyTo(ConstraintLayout.LayoutParams paramLayoutParams)
    {
      leftToLeft = leftToLeft;
      leftToRight = leftToRight;
      rightToLeft = rightToLeft;
      rightToRight = rightToRight;
      topToTop = topToTop;
      topToBottom = topToBottom;
      bottomToTop = bottomToTop;
      bottomToBottom = bottomToBottom;
      baselineToBaseline = baselineToBaseline;
      startToEnd = startToEnd;
      startToStart = startToStart;
      endToStart = endToStart;
      endToEnd = endToEnd;
      leftMargin = leftMargin;
      rightMargin = rightMargin;
      topMargin = topMargin;
      bottomMargin = bottomMargin;
      goneStartMargin = goneStartMargin;
      goneEndMargin = goneEndMargin;
      horizontalBias = horizontalBias;
      verticalBias = verticalBias;
      dimensionRatio = dimensionRatio;
      editorAbsoluteX = editorAbsoluteX;
      editorAbsoluteY = editorAbsoluteY;
      verticalWeight = verticalWeight;
      horizontalWeight = horizontalWeight;
      verticalChainStyle = verticalChainStyle;
      horizontalChainStyle = horizontalChainStyle;
      matchConstraintDefaultWidth = widthDefault;
      matchConstraintDefaultHeight = heightDefault;
      matchConstraintMaxWidth = widthMax;
      matchConstraintMaxHeight = heightMax;
      matchConstraintMinWidth = widthMin;
      matchConstraintMinHeight = heightMin;
      orientation = orientation;
      guidePercent = guidePercent;
      guideBegin = guideBegin;
      guideEnd = guideEnd;
      width = mWidth;
      height = mHeight;
      if (Build.VERSION.SDK_INT >= 17)
      {
        paramLayoutParams.setMarginStart(startMargin);
        paramLayoutParams.setMarginEnd(endMargin);
      }
      paramLayoutParams.validate();
    }
    
    public Constraint clone()
    {
      Constraint localConstraint = new Constraint();
      mIsGuideline = mIsGuideline;
      mWidth = mWidth;
      mHeight = mHeight;
      guideBegin = guideBegin;
      guideEnd = guideEnd;
      guidePercent = guidePercent;
      leftToLeft = leftToLeft;
      leftToRight = leftToRight;
      rightToLeft = rightToLeft;
      rightToRight = rightToRight;
      topToTop = topToTop;
      topToBottom = topToBottom;
      bottomToTop = bottomToTop;
      bottomToBottom = bottomToBottom;
      baselineToBaseline = baselineToBaseline;
      startToEnd = startToEnd;
      startToStart = startToStart;
      endToStart = endToStart;
      endToEnd = endToEnd;
      horizontalBias = horizontalBias;
      verticalBias = verticalBias;
      dimensionRatio = dimensionRatio;
      editorAbsoluteX = editorAbsoluteX;
      editorAbsoluteY = editorAbsoluteY;
      horizontalBias = horizontalBias;
      horizontalBias = horizontalBias;
      horizontalBias = horizontalBias;
      horizontalBias = horizontalBias;
      horizontalBias = horizontalBias;
      orientation = orientation;
      leftMargin = leftMargin;
      rightMargin = rightMargin;
      topMargin = topMargin;
      bottomMargin = bottomMargin;
      endMargin = endMargin;
      startMargin = startMargin;
      visibility = visibility;
      goneLeftMargin = goneLeftMargin;
      goneTopMargin = goneTopMargin;
      goneRightMargin = goneRightMargin;
      goneBottomMargin = goneBottomMargin;
      goneEndMargin = goneEndMargin;
      goneStartMargin = goneStartMargin;
      verticalWeight = verticalWeight;
      horizontalWeight = horizontalWeight;
      horizontalChainStyle = horizontalChainStyle;
      verticalChainStyle = verticalChainStyle;
      alpha = alpha;
      applyElevation = applyElevation;
      elevation = elevation;
      rotation = rotation;
      rotationX = rotationX;
      rotationY = rotationY;
      scaleX = scaleX;
      scaleY = scaleY;
      transformPivotX = transformPivotX;
      transformPivotY = transformPivotY;
      translationX = translationX;
      translationY = translationY;
      translationZ = translationZ;
      widthDefault = widthDefault;
      heightDefault = heightDefault;
      widthMax = widthMax;
      heightMax = heightMax;
      widthMin = widthMin;
      heightMin = heightMin;
      mBarrierDirection = mBarrierDirection;
      mHelperType = mHelperType;
      if (mReferenceIds != null) {
        mReferenceIds = Arrays.copyOf(mReferenceIds, mReferenceIds.length);
      }
      return localConstraint;
    }
  }
}

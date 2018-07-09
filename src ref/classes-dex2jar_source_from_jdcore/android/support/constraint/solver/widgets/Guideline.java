package android.support.constraint.solver.widgets;

import android.support.constraint.solver.LinearSystem;
import java.util.ArrayList;

public class Guideline
  extends ConstraintWidget
{
  public static final int HORIZONTAL = 0;
  public static final int RELATIVE_BEGIN = 1;
  public static final int RELATIVE_END = 2;
  public static final int RELATIVE_PERCENT = 0;
  public static final int RELATIVE_UNKNWON = -1;
  public static final int VERTICAL = 1;
  private ConstraintAnchor mAnchor = mTop;
  private Rectangle mHead = new Rectangle();
  private int mHeadSize = 8;
  private boolean mIsPositionRelaxed = false;
  private int mMinimumPosition = 0;
  private int mOrientation = 0;
  protected int mRelativeBegin = -1;
  protected int mRelativeEnd = -1;
  protected float mRelativePercent = -1.0F;
  
  public Guideline()
  {
    mAnchors.clear();
    mAnchors.add(mAnchor);
  }
  
  public void addToSolver(LinearSystem paramLinearSystem, int paramInt)
  {
    ConstraintWidgetContainer localConstraintWidgetContainer = (ConstraintWidgetContainer)getParent();
    if (localConstraintWidgetContainer == null) {
      return;
    }
    ConstraintAnchor localConstraintAnchor1 = localConstraintWidgetContainer.getAnchor(ConstraintAnchor.Type.LEFT);
    ConstraintAnchor localConstraintAnchor2 = localConstraintWidgetContainer.getAnchor(ConstraintAnchor.Type.RIGHT);
    if (mOrientation == 0)
    {
      localConstraintAnchor1 = localConstraintWidgetContainer.getAnchor(ConstraintAnchor.Type.TOP);
      localConstraintAnchor2 = localConstraintWidgetContainer.getAnchor(ConstraintAnchor.Type.BOTTOM);
    }
    if (mRelativeBegin != -1)
    {
      paramLinearSystem.addConstraint(LinearSystem.createRowEquals(paramLinearSystem, paramLinearSystem.createObjectVariable(mAnchor), paramLinearSystem.createObjectVariable(localConstraintAnchor1), mRelativeBegin, false));
      return;
    }
    if (mRelativeEnd != -1)
    {
      paramLinearSystem.addConstraint(LinearSystem.createRowEquals(paramLinearSystem, paramLinearSystem.createObjectVariable(mAnchor), paramLinearSystem.createObjectVariable(localConstraintAnchor2), -mRelativeEnd, false));
      return;
    }
    if (mRelativePercent != -1.0F) {
      paramLinearSystem.addConstraint(LinearSystem.createRowDimensionPercent(paramLinearSystem, paramLinearSystem.createObjectVariable(mAnchor), paramLinearSystem.createObjectVariable(localConstraintAnchor1), paramLinearSystem.createObjectVariable(localConstraintAnchor2), mRelativePercent, mIsPositionRelaxed));
    }
  }
  
  public void cyclePosition()
  {
    if (mRelativeBegin != -1)
    {
      inferRelativePercentPosition();
      return;
    }
    if (mRelativePercent != -1.0F)
    {
      inferRelativeEndPosition();
      return;
    }
    if (mRelativeEnd != -1) {
      inferRelativeBeginPosition();
    }
  }
  
  public ConstraintAnchor getAnchor()
  {
    return mAnchor;
  }
  
  public ConstraintAnchor getAnchor(ConstraintAnchor.Type paramType)
  {
    switch (1.$SwitchMap$android$support$constraint$solver$widgets$ConstraintAnchor$Type[paramType.ordinal()])
    {
    default: 
      break;
    case 5: 
    case 6: 
    case 7: 
    case 8: 
    case 9: 
      return null;
    case 3: 
    case 4: 
      if (mOrientation == 0) {
        return mAnchor;
      }
      break;
    case 1: 
    case 2: 
      if (mOrientation == 1) {
        return mAnchor;
      }
      break;
    }
    throw new AssertionError(paramType.name());
  }
  
  public ArrayList<ConstraintAnchor> getAnchors()
  {
    return mAnchors;
  }
  
  public Rectangle getHead()
  {
    mHead.setBounds(getDrawX() - mHeadSize, getDrawY() - mHeadSize * 2, mHeadSize * 2, mHeadSize * 2);
    if (getOrientation() == 0) {
      mHead.setBounds(getDrawX() - mHeadSize * 2, getDrawY() - mHeadSize, mHeadSize * 2, 2 * mHeadSize);
    }
    return mHead;
  }
  
  public int getOrientation()
  {
    return mOrientation;
  }
  
  public int getRelativeBegin()
  {
    return mRelativeBegin;
  }
  
  public int getRelativeBehaviour()
  {
    if (mRelativePercent != -1.0F) {
      return 0;
    }
    if (mRelativeBegin != -1) {
      return 1;
    }
    if (mRelativeEnd != -1) {
      return 2;
    }
    return -1;
  }
  
  public int getRelativeEnd()
  {
    return mRelativeEnd;
  }
  
  public float getRelativePercent()
  {
    return mRelativePercent;
  }
  
  public String getType()
  {
    return "Guideline";
  }
  
  void inferRelativeBeginPosition()
  {
    int i = getX();
    if (mOrientation == 0) {
      i = getY();
    }
    setGuideBegin(i);
  }
  
  void inferRelativeEndPosition()
  {
    int i = getParent().getWidth() - getX();
    if (mOrientation == 0) {
      i = getParent().getHeight() - getY();
    }
    setGuideEnd(i);
  }
  
  void inferRelativePercentPosition()
  {
    float f = getX() / getParent().getWidth();
    if (mOrientation == 0) {
      f = getY() / getParent().getHeight();
    }
    setGuidePercent(f);
  }
  
  public void setDrawOrigin(int paramInt1, int paramInt2)
  {
    if (mOrientation == 1)
    {
      paramInt1 -= mOffsetX;
      if (mRelativeBegin != -1)
      {
        setGuideBegin(paramInt1);
        return;
      }
      if (mRelativeEnd != -1)
      {
        setGuideEnd(getParent().getWidth() - paramInt1);
        return;
      }
      if (mRelativePercent != -1.0F) {
        setGuidePercent(paramInt1 / getParent().getWidth());
      }
    }
    else
    {
      paramInt1 = paramInt2 - mOffsetY;
      if (mRelativeBegin != -1)
      {
        setGuideBegin(paramInt1);
        return;
      }
      if (mRelativeEnd != -1)
      {
        setGuideEnd(getParent().getHeight() - paramInt1);
        return;
      }
      if (mRelativePercent != -1.0F) {
        setGuidePercent(paramInt1 / getParent().getHeight());
      }
    }
  }
  
  public void setGuideBegin(int paramInt)
  {
    if (paramInt > -1)
    {
      mRelativePercent = -1.0F;
      mRelativeBegin = paramInt;
      mRelativeEnd = -1;
    }
  }
  
  public void setGuideEnd(int paramInt)
  {
    if (paramInt > -1)
    {
      mRelativePercent = -1.0F;
      mRelativeBegin = -1;
      mRelativeEnd = paramInt;
    }
  }
  
  public void setGuidePercent(float paramFloat)
  {
    if (paramFloat > -1.0F)
    {
      mRelativePercent = paramFloat;
      mRelativeBegin = -1;
      mRelativeEnd = -1;
    }
  }
  
  public void setGuidePercent(int paramInt)
  {
    setGuidePercent(paramInt / 100.0F);
  }
  
  public void setMinimumPosition(int paramInt)
  {
    mMinimumPosition = paramInt;
  }
  
  public void setOrientation(int paramInt)
  {
    if (mOrientation == paramInt) {
      return;
    }
    mOrientation = paramInt;
    mAnchors.clear();
    if (mOrientation == 1) {
      mAnchor = mLeft;
    } else {
      mAnchor = mTop;
    }
    mAnchors.add(mAnchor);
  }
  
  public void setPositionRelaxed(boolean paramBoolean)
  {
    if (mIsPositionRelaxed == paramBoolean) {
      return;
    }
    mIsPositionRelaxed = paramBoolean;
  }
  
  public void updateFromSolver(LinearSystem paramLinearSystem, int paramInt)
  {
    if (getParent() == null) {
      return;
    }
    paramInt = paramLinearSystem.getObjectVariableValue(mAnchor);
    if (mOrientation == 1)
    {
      setX(paramInt);
      setY(0);
      setHeight(getParent().getHeight());
      setWidth(0);
      return;
    }
    setX(0);
    setY(paramInt);
    setWidth(getParent().getWidth());
    setHeight(0);
  }
}

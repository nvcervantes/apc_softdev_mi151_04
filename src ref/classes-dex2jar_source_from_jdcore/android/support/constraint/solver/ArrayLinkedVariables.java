package android.support.constraint.solver;

import java.io.PrintStream;
import java.util.Arrays;

public class ArrayLinkedVariables
{
  private static final boolean DEBUG = false;
  private static final int NONE = -1;
  private int ROW_SIZE = 8;
  private SolverVariable candidate = null;
  int currentSize = 0;
  private int[] mArrayIndices = new int[ROW_SIZE];
  private int[] mArrayNextIndices = new int[ROW_SIZE];
  private float[] mArrayValues = new float[ROW_SIZE];
  private final Cache mCache;
  private boolean mDidFillOnce = false;
  private int mHead = -1;
  private int mLast = -1;
  private final ArrayRow mRow;
  
  ArrayLinkedVariables(ArrayRow paramArrayRow, Cache paramCache)
  {
    mRow = paramArrayRow;
    mCache = paramCache;
  }
  
  public final void add(SolverVariable paramSolverVariable, float paramFloat)
  {
    if (paramFloat == 0.0F) {
      return;
    }
    if (mHead == -1)
    {
      mHead = 0;
      mArrayValues[mHead] = paramFloat;
      mArrayIndices[mHead] = id;
      mArrayNextIndices[mHead] = -1;
      currentSize += 1;
      if (!mDidFillOnce) {
        mLast += 1;
      }
      return;
    }
    int i = mHead;
    int j = 0;
    int m = -1;
    int k;
    while ((i != -1) && (j < currentSize))
    {
      k = mArrayIndices[i];
      if (k == id)
      {
        paramSolverVariable = mArrayValues;
        paramSolverVariable[i] += paramFloat;
        if (mArrayValues[i] == 0.0F)
        {
          if (i == mHead) {
            mHead = mArrayNextIndices[i];
          } else {
            mArrayNextIndices[m] = mArrayNextIndices[i];
          }
          mCache.mIndexedVariables[k].removeClientEquation(mRow);
          if (mDidFillOnce) {
            mLast = i;
          }
          currentSize -= 1;
        }
        return;
      }
      if (mArrayIndices[i] < id) {
        m = i;
      }
      i = mArrayNextIndices[i];
      j += 1;
    }
    i = mLast + 1;
    if (mDidFillOnce) {
      if (mArrayIndices[mLast] == -1) {
        i = mLast;
      } else {
        i = mArrayIndices.length;
      }
    }
    j = i;
    if (i >= mArrayIndices.length)
    {
      j = i;
      if (currentSize < mArrayIndices.length)
      {
        k = 0;
        for (;;)
        {
          j = i;
          if (k >= mArrayIndices.length) {
            break;
          }
          if (mArrayIndices[k] == -1)
          {
            j = k;
            break;
          }
          k += 1;
        }
      }
    }
    i = j;
    if (j >= mArrayIndices.length)
    {
      i = mArrayIndices.length;
      ROW_SIZE *= 2;
      mDidFillOnce = false;
      mLast = (i - 1);
      mArrayValues = Arrays.copyOf(mArrayValues, ROW_SIZE);
      mArrayIndices = Arrays.copyOf(mArrayIndices, ROW_SIZE);
      mArrayNextIndices = Arrays.copyOf(mArrayNextIndices, ROW_SIZE);
    }
    mArrayIndices[i] = id;
    mArrayValues[i] = paramFloat;
    if (m != -1)
    {
      mArrayNextIndices[i] = mArrayNextIndices[m];
      mArrayNextIndices[m] = i;
    }
    else
    {
      mArrayNextIndices[i] = mHead;
      mHead = i;
    }
    currentSize += 1;
    if (!mDidFillOnce) {
      mLast += 1;
    }
    if (mLast >= mArrayIndices.length)
    {
      mDidFillOnce = true;
      mLast = (mArrayIndices.length - 1);
    }
  }
  
  public final void clear()
  {
    mHead = -1;
    mLast = -1;
    mDidFillOnce = false;
    currentSize = 0;
  }
  
  final boolean containsKey(SolverVariable paramSolverVariable)
  {
    if (mHead == -1) {
      return false;
    }
    int j = mHead;
    int i = 0;
    while ((j != -1) && (i < currentSize))
    {
      if (mArrayIndices[j] == id) {
        return true;
      }
      j = mArrayNextIndices[j];
      i += 1;
    }
    return false;
  }
  
  public void display()
  {
    int j = currentSize;
    System.out.print("{ ");
    int i = 0;
    while (i < j)
    {
      SolverVariable localSolverVariable = getVariable(i);
      if (localSolverVariable != null)
      {
        PrintStream localPrintStream = System.out;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(localSolverVariable);
        localStringBuilder.append(" = ");
        localStringBuilder.append(getVariableValue(i));
        localStringBuilder.append(" ");
        localPrintStream.print(localStringBuilder.toString());
      }
      i += 1;
    }
    System.out.println(" }");
  }
  
  void divideByAmount(float paramFloat)
  {
    int j = mHead;
    int i = 0;
    while ((j != -1) && (i < currentSize))
    {
      float[] arrayOfFloat = mArrayValues;
      arrayOfFloat[j] /= paramFloat;
      j = mArrayNextIndices[j];
      i += 1;
    }
  }
  
  public final float get(SolverVariable paramSolverVariable)
  {
    int j = mHead;
    int i = 0;
    while ((j != -1) && (i < currentSize))
    {
      if (mArrayIndices[j] == id) {
        return mArrayValues[j];
      }
      j = mArrayNextIndices[j];
      i += 1;
    }
    return 0.0F;
  }
  
  SolverVariable getPivotCandidate()
  {
    if (candidate == null)
    {
      int j = mHead;
      int i = 0;
      Object localObject2;
      for (Object localObject1 = null; (j != -1) && (i < currentSize); localObject1 = localObject2)
      {
        localObject2 = localObject1;
        if (mArrayValues[j] < 0.0F)
        {
          SolverVariable localSolverVariable = mCache.mIndexedVariables[mArrayIndices[j]];
          if (localObject1 != null)
          {
            localObject2 = localObject1;
            if (strength >= strength) {}
          }
          else
          {
            localObject2 = localSolverVariable;
          }
        }
        j = mArrayNextIndices[j];
        i += 1;
      }
      return localObject1;
    }
    return candidate;
  }
  
  final SolverVariable getVariable(int paramInt)
  {
    int j = mHead;
    int i = 0;
    while ((j != -1) && (i < currentSize))
    {
      if (i == paramInt) {
        return mCache.mIndexedVariables[mArrayIndices[j]];
      }
      j = mArrayNextIndices[j];
      i += 1;
    }
    return null;
  }
  
  final float getVariableValue(int paramInt)
  {
    int j = mHead;
    int i = 0;
    while ((j != -1) && (i < currentSize))
    {
      if (i == paramInt) {
        return mArrayValues[j];
      }
      j = mArrayNextIndices[j];
      i += 1;
    }
    return 0.0F;
  }
  
  boolean hasAtLeastOnePositiveVariable()
  {
    int j = mHead;
    int i = 0;
    while ((j != -1) && (i < currentSize))
    {
      if (mArrayValues[j] > 0.0F) {
        return true;
      }
      j = mArrayNextIndices[j];
      i += 1;
    }
    return false;
  }
  
  void invert()
  {
    int j = mHead;
    int i = 0;
    while ((j != -1) && (i < currentSize))
    {
      float[] arrayOfFloat = mArrayValues;
      arrayOfFloat[j] *= -1.0F;
      j = mArrayNextIndices[j];
      i += 1;
    }
  }
  
  SolverVariable pickPivotCandidate()
  {
    int j = mHead;
    Object localObject2 = null;
    int i = 0;
    Object localObject4;
    for (Object localObject1 = null; (j != -1) && (i < currentSize); localObject1 = localObject4)
    {
      float f2 = mArrayValues[j];
      float f1;
      if (f2 < 0.0F)
      {
        f1 = f2;
        if (f2 > -0.001F) {
          mArrayValues[j] = 0.0F;
        }
      }
      else
      {
        for (;;)
        {
          f1 = 0.0F;
          break;
          f1 = f2;
          if (f2 >= 0.001F) {
            break;
          }
          mArrayValues[j] = 0.0F;
        }
      }
      Object localObject3 = localObject2;
      localObject4 = localObject1;
      if (f1 != 0.0F)
      {
        SolverVariable localSolverVariable = mCache.mIndexedVariables[mArrayIndices[j]];
        if (mType == SolverVariable.Type.UNRESTRICTED)
        {
          if (f1 < 0.0F) {
            return localSolverVariable;
          }
          localObject3 = localObject2;
          localObject4 = localObject1;
          if (localObject2 == null)
          {
            localObject3 = localSolverVariable;
            localObject4 = localObject1;
          }
        }
        else
        {
          localObject3 = localObject2;
          localObject4 = localObject1;
          if (f1 < 0.0F) {
            if (localObject1 != null)
            {
              localObject3 = localObject2;
              localObject4 = localObject1;
              if (strength >= strength) {}
            }
            else
            {
              localObject4 = localSolverVariable;
              localObject3 = localObject2;
            }
          }
        }
      }
      j = mArrayNextIndices[j];
      i += 1;
      localObject2 = localObject3;
    }
    if (localObject2 != null) {
      return localObject2;
    }
    return localObject1;
  }
  
  public final void put(SolverVariable paramSolverVariable, float paramFloat)
  {
    if (paramFloat == 0.0F)
    {
      remove(paramSolverVariable);
      return;
    }
    if (mHead == -1)
    {
      mHead = 0;
      mArrayValues[mHead] = paramFloat;
      mArrayIndices[mHead] = id;
      mArrayNextIndices[mHead] = -1;
      currentSize += 1;
      if (!mDidFillOnce) {
        mLast += 1;
      }
      return;
    }
    int i = mHead;
    int j = 0;
    int m = -1;
    while ((i != -1) && (j < currentSize))
    {
      if (mArrayIndices[i] == id)
      {
        mArrayValues[i] = paramFloat;
        return;
      }
      if (mArrayIndices[i] < id) {
        m = i;
      }
      i = mArrayNextIndices[i];
      j += 1;
    }
    i = mLast + 1;
    if (mDidFillOnce) {
      if (mArrayIndices[mLast] == -1) {
        i = mLast;
      } else {
        i = mArrayIndices.length;
      }
    }
    j = i;
    if (i >= mArrayIndices.length)
    {
      j = i;
      if (currentSize < mArrayIndices.length)
      {
        int k = 0;
        for (;;)
        {
          j = i;
          if (k >= mArrayIndices.length) {
            break;
          }
          if (mArrayIndices[k] == -1)
          {
            j = k;
            break;
          }
          k += 1;
        }
      }
    }
    i = j;
    if (j >= mArrayIndices.length)
    {
      i = mArrayIndices.length;
      ROW_SIZE *= 2;
      mDidFillOnce = false;
      mLast = (i - 1);
      mArrayValues = Arrays.copyOf(mArrayValues, ROW_SIZE);
      mArrayIndices = Arrays.copyOf(mArrayIndices, ROW_SIZE);
      mArrayNextIndices = Arrays.copyOf(mArrayNextIndices, ROW_SIZE);
    }
    mArrayIndices[i] = id;
    mArrayValues[i] = paramFloat;
    if (m != -1)
    {
      mArrayNextIndices[i] = mArrayNextIndices[m];
      mArrayNextIndices[m] = i;
    }
    else
    {
      mArrayNextIndices[i] = mHead;
      mHead = i;
    }
    currentSize += 1;
    if (!mDidFillOnce) {
      mLast += 1;
    }
    if (currentSize >= mArrayIndices.length) {
      mDidFillOnce = true;
    }
  }
  
  public final float remove(SolverVariable paramSolverVariable)
  {
    if (candidate == paramSolverVariable) {
      candidate = null;
    }
    if (mHead == -1) {
      return 0.0F;
    }
    int i = mHead;
    int j = 0;
    int k = -1;
    while ((i != -1) && (j < currentSize))
    {
      int m = mArrayIndices[i];
      if (m == id)
      {
        if (i == mHead) {
          mHead = mArrayNextIndices[i];
        } else {
          mArrayNextIndices[k] = mArrayNextIndices[i];
        }
        mCache.mIndexedVariables[m].removeClientEquation(mRow);
        currentSize -= 1;
        mArrayIndices[i] = -1;
        if (mDidFillOnce) {
          mLast = i;
        }
        return mArrayValues[i];
      }
      m = mArrayNextIndices[i];
      j += 1;
      k = i;
      i = m;
    }
    return 0.0F;
  }
  
  int sizeInBytes()
  {
    return 0 + 3 * (mArrayIndices.length * 4) + 36;
  }
  
  public String toString()
  {
    String str = "";
    int j = mHead;
    int i = 0;
    while ((j != -1) && (i < currentSize))
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(" -> ");
      str = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(mArrayValues[j]);
      localStringBuilder.append(" : ");
      str = localStringBuilder.toString();
      localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(mCache.mIndexedVariables[mArrayIndices[j]]);
      str = localStringBuilder.toString();
      j = mArrayNextIndices[j];
      i += 1;
    }
    return str;
  }
  
  void updateClientEquations(ArrayRow paramArrayRow)
  {
    int j = mHead;
    int i = 0;
    while ((j != -1) && (i < currentSize))
    {
      mCache.mIndexedVariables[mArrayIndices[j]].addClientEquation(paramArrayRow);
      j = mArrayNextIndices[j];
      i += 1;
    }
  }
  
  void updateFromRow(ArrayRow paramArrayRow1, ArrayRow paramArrayRow2)
  {
    int i = mHead;
    int j = 0;
    for (;;)
    {
      if ((i == -1) || (j >= currentSize)) {
        return;
      }
      if (mArrayIndices[i] == variable.id)
      {
        float f = mArrayValues[i];
        remove(variable);
        ArrayLinkedVariables localArrayLinkedVariables = variables;
        j = mHead;
        i = 0;
        while ((j != -1) && (i < currentSize))
        {
          add(mCache.mIndexedVariables[mArrayIndices[j]], mArrayValues[j] * f);
          j = mArrayNextIndices[j];
          i += 1;
        }
        constantValue += constantValue * f;
        variable.removeClientEquation(paramArrayRow1);
        i = mHead;
        break;
      }
      i = mArrayNextIndices[i];
      j += 1;
    }
  }
  
  void updateFromSystem(ArrayRow paramArrayRow, ArrayRow[] paramArrayOfArrayRow)
  {
    int i = mHead;
    int j = 0;
    for (;;)
    {
      if ((i == -1) || (j >= currentSize)) {
        return;
      }
      Object localObject = mCache.mIndexedVariables[mArrayIndices[i]];
      if (definitionId != -1)
      {
        float f = mArrayValues[i];
        remove((SolverVariable)localObject);
        localObject = paramArrayOfArrayRow[definitionId];
        if (!isSimpleDefinition)
        {
          ArrayLinkedVariables localArrayLinkedVariables = variables;
          j = mHead;
          i = 0;
          while ((j != -1) && (i < currentSize))
          {
            add(mCache.mIndexedVariables[mArrayIndices[j]], mArrayValues[j] * f);
            j = mArrayNextIndices[j];
            i += 1;
          }
        }
        constantValue += constantValue * f;
        variable.removeClientEquation(paramArrayRow);
        i = mHead;
        break;
      }
      i = mArrayNextIndices[i];
      j += 1;
    }
  }
}

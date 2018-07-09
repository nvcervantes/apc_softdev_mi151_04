package android.support.constraint.solver;

import android.support.constraint.solver.widgets.ConstraintAnchor;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class LinearSystem
{
  private static final boolean DEBUG = false;
  private static int POOL_SIZE = 1000;
  private int TABLE_SIZE = 32;
  private boolean[] mAlreadyTestedCandidates = new boolean[TABLE_SIZE];
  final Cache mCache;
  private Goal mGoal = new Goal();
  private int mMaxColumns = TABLE_SIZE;
  private int mMaxRows = TABLE_SIZE;
  int mNumColumns = 1;
  private int mNumRows = 0;
  private SolverVariable[] mPoolVariables = new SolverVariable[POOL_SIZE];
  private int mPoolVariablesCount = 0;
  private ArrayRow[] mRows = null;
  private HashMap<String, SolverVariable> mVariables = null;
  int mVariablesID = 0;
  private ArrayRow[] tempClientsCopy = new ArrayRow[TABLE_SIZE];
  
  public LinearSystem()
  {
    releaseRows();
    mCache = new Cache();
  }
  
  private SolverVariable acquireSolverVariable(SolverVariable.Type paramType)
  {
    Object localObject = (SolverVariable)mCache.solverVariablePool.acquire();
    if (localObject == null)
    {
      paramType = new SolverVariable(paramType);
    }
    else
    {
      ((SolverVariable)localObject).reset();
      ((SolverVariable)localObject).setType(paramType);
      paramType = (SolverVariable.Type)localObject;
    }
    if (mPoolVariablesCount >= POOL_SIZE)
    {
      POOL_SIZE *= 2;
      mPoolVariables = ((SolverVariable[])Arrays.copyOf(mPoolVariables, POOL_SIZE));
    }
    localObject = mPoolVariables;
    int i = mPoolVariablesCount;
    mPoolVariablesCount = (i + 1);
    localObject[i] = paramType;
    return paramType;
  }
  
  private void addError(ArrayRow paramArrayRow)
  {
    paramArrayRow.addError(createErrorVariable(), createErrorVariable());
  }
  
  private void addSingleError(ArrayRow paramArrayRow, int paramInt)
  {
    paramArrayRow.addSingleError(createErrorVariable(), paramInt);
  }
  
  private void computeValues()
  {
    int i = 0;
    while (i < mNumRows)
    {
      ArrayRow localArrayRow = mRows[i];
      variable.computedValue = constantValue;
      i += 1;
    }
  }
  
  public static ArrayRow createRowCentering(LinearSystem paramLinearSystem, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, float paramFloat, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, int paramInt2, boolean paramBoolean)
  {
    ArrayRow localArrayRow = paramLinearSystem.createRow();
    localArrayRow.createRowCentering(paramSolverVariable1, paramSolverVariable2, paramInt1, paramFloat, paramSolverVariable3, paramSolverVariable4, paramInt2);
    if (paramBoolean)
    {
      paramSolverVariable1 = paramLinearSystem.createErrorVariable();
      paramLinearSystem = paramLinearSystem.createErrorVariable();
      strength = 4;
      strength = 4;
      localArrayRow.addError(paramSolverVariable1, paramLinearSystem);
    }
    return localArrayRow;
  }
  
  public static ArrayRow createRowDimensionPercent(LinearSystem paramLinearSystem, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, float paramFloat, boolean paramBoolean)
  {
    ArrayRow localArrayRow = paramLinearSystem.createRow();
    if (paramBoolean) {
      paramLinearSystem.addError(localArrayRow);
    }
    return localArrayRow.createRowDimensionPercent(paramSolverVariable1, paramSolverVariable2, paramSolverVariable3, paramFloat);
  }
  
  public static ArrayRow createRowEquals(LinearSystem paramLinearSystem, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt, boolean paramBoolean)
  {
    ArrayRow localArrayRow = paramLinearSystem.createRow();
    localArrayRow.createRowEquals(paramSolverVariable1, paramSolverVariable2, paramInt);
    if (paramBoolean) {
      paramLinearSystem.addSingleError(localArrayRow, 1);
    }
    return localArrayRow;
  }
  
  public static ArrayRow createRowGreaterThan(LinearSystem paramLinearSystem, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt, boolean paramBoolean)
  {
    SolverVariable localSolverVariable = paramLinearSystem.createSlackVariable();
    ArrayRow localArrayRow = paramLinearSystem.createRow();
    localArrayRow.createRowGreaterThan(paramSolverVariable1, paramSolverVariable2, localSolverVariable, paramInt);
    if (paramBoolean) {
      paramLinearSystem.addSingleError(localArrayRow, (int)(-1.0F * variables.get(localSolverVariable)));
    }
    return localArrayRow;
  }
  
  public static ArrayRow createRowLowerThan(LinearSystem paramLinearSystem, SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt, boolean paramBoolean)
  {
    SolverVariable localSolverVariable = paramLinearSystem.createSlackVariable();
    ArrayRow localArrayRow = paramLinearSystem.createRow();
    localArrayRow.createRowLowerThan(paramSolverVariable1, paramSolverVariable2, localSolverVariable, paramInt);
    if (paramBoolean) {
      paramLinearSystem.addSingleError(localArrayRow, (int)(-1.0F * variables.get(localSolverVariable)));
    }
    return localArrayRow;
  }
  
  private SolverVariable createVariable(String paramString, SolverVariable.Type paramType)
  {
    if (mNumColumns + 1 >= mMaxColumns) {
      increaseTableSize();
    }
    paramType = acquireSolverVariable(paramType);
    paramType.setName(paramString);
    mVariablesID += 1;
    mNumColumns += 1;
    id = mVariablesID;
    if (mVariables == null) {
      mVariables = new HashMap();
    }
    mVariables.put(paramString, paramType);
    mCache.mIndexedVariables[mVariablesID] = paramType;
    return paramType;
  }
  
  private void displayRows()
  {
    displaySolverVariables();
    String str = "";
    int i = 0;
    while (i < mNumRows)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append(mRows[i]);
      str = ((StringBuilder)localObject).toString();
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append("\n");
      str = ((StringBuilder)localObject).toString();
      i += 1;
    }
    Object localObject = str;
    if (mGoal.variables.size() != 0)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append(mGoal);
      ((StringBuilder)localObject).append("\n");
      localObject = ((StringBuilder)localObject).toString();
    }
    System.out.println((String)localObject);
  }
  
  private void displaySolverVariables()
  {
    Object localObject1 = new StringBuilder();
    ((StringBuilder)localObject1).append("Display Rows (");
    ((StringBuilder)localObject1).append(mNumRows);
    ((StringBuilder)localObject1).append("x");
    ((StringBuilder)localObject1).append(mNumColumns);
    ((StringBuilder)localObject1).append(") :\n\t | C | ");
    localObject1 = ((StringBuilder)localObject1).toString();
    int i = 1;
    while (i <= mNumColumns)
    {
      localObject2 = mCache.mIndexedVariables[i];
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append((String)localObject1);
      localStringBuilder.append(localObject2);
      localObject1 = localStringBuilder.toString();
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(" | ");
      localObject1 = ((StringBuilder)localObject2).toString();
      i += 1;
    }
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append("\n");
    localObject1 = ((StringBuilder)localObject2).toString();
    System.out.println((String)localObject1);
  }
  
  private int enforceBFS(Goal paramGoal)
    throws Exception
  {
    int i = 0;
    while (i < mNumRows)
    {
      if ((mRows[i].variable.mType != SolverVariable.Type.UNRESTRICTED) && (mRows[i].constantValue < 0.0F))
      {
        i = 1;
        break label67;
      }
      i += 1;
    }
    i = 0;
    label67:
    if (i != 0)
    {
      int i2 = 0;
      i = 0;
      for (;;)
      {
        j = i;
        if (i2 != 0) {
          break;
        }
        int i4 = i + 1;
        float f1 = Float.MAX_VALUE;
        i = -1;
        j = i;
        int m = 0;
        ArrayRow localArrayRow;
        int n;
        for (int k = 0; m < mNumRows; k = n)
        {
          localArrayRow = mRows[m];
          int i3;
          int i1;
          float f2;
          if (variable.mType == SolverVariable.Type.UNRESTRICTED)
          {
            i3 = i;
            i1 = j;
            f2 = f1;
            n = k;
          }
          else
          {
            i3 = i;
            i1 = j;
            f2 = f1;
            n = k;
            if (constantValue < 0.0F)
            {
              n = i;
              i1 = 1;
              i = k;
              k = n;
              n = i1;
              while (n < mNumColumns)
              {
                SolverVariable localSolverVariable = mCache.mIndexedVariables[n];
                float f3 = variables.get(localSolverVariable);
                if (f3 > 0.0F)
                {
                  i3 = i;
                  i1 = k;
                  i = 0;
                  k = j;
                  for (j = i3; i < 6; j = i3)
                  {
                    f2 = strengthVector[i] / f3;
                    if ((f2 >= f1) || (i != j))
                    {
                      i3 = j;
                      if (i <= j) {}
                    }
                    else
                    {
                      f1 = f2;
                      i1 = m;
                      k = n;
                      i3 = i;
                    }
                    i += 1;
                  }
                  i = j;
                  j = k;
                  k = i1;
                }
                n += 1;
              }
              n = i;
              f2 = f1;
              i1 = j;
              i3 = k;
            }
          }
          m += 1;
          i = i3;
          j = i1;
          f1 = f2;
        }
        if (i != -1)
        {
          localArrayRow = mRows[i];
          variable.definitionId = -1;
          localArrayRow.pivot(mCache.mIndexedVariables[j]);
          variable.definitionId = i;
          i = 0;
          while (i < mNumRows)
          {
            mRows[i].updateRowWithEquation(localArrayRow);
            i += 1;
          }
          paramGoal.updateFromSystem(this);
          i = i4;
        }
        else
        {
          i2 = 1;
          i = i4;
        }
      }
    }
    int j = 0;
    i = 0;
    while (i < mNumRows)
    {
      if ((mRows[i].variable.mType != SolverVariable.Type.UNRESTRICTED) && (mRows[i].constantValue < 0.0F)) {
        return j;
      }
      i += 1;
    }
    return j;
  }
  
  private String getDisplaySize(int paramInt)
  {
    paramInt *= 4;
    int i = paramInt / 1024;
    int j = i / 1024;
    if (j > 0)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("");
      localStringBuilder.append(j);
      localStringBuilder.append(" Mb");
      return localStringBuilder.toString();
    }
    if (i > 0)
    {
      localStringBuilder = new StringBuilder();
      localStringBuilder.append("");
      localStringBuilder.append(i);
      localStringBuilder.append(" Kb");
      return localStringBuilder.toString();
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("");
    localStringBuilder.append(paramInt);
    localStringBuilder.append(" bytes");
    return localStringBuilder.toString();
  }
  
  private void increaseTableSize()
  {
    TABLE_SIZE *= 2;
    mRows = ((ArrayRow[])Arrays.copyOf(mRows, TABLE_SIZE));
    mCache.mIndexedVariables = ((SolverVariable[])Arrays.copyOf(mCache.mIndexedVariables, TABLE_SIZE));
    mAlreadyTestedCandidates = new boolean[TABLE_SIZE];
    mMaxColumns = TABLE_SIZE;
    mMaxRows = TABLE_SIZE;
    mGoal.variables.clear();
  }
  
  private int optimize(Goal paramGoal)
  {
    int i = 0;
    while (i < mNumColumns)
    {
      mAlreadyTestedCandidates[i] = false;
      i += 1;
    }
    int j = 0;
    i = j;
    int m = i;
    int k = i;
    i = j;
    while (i == 0)
    {
      int i1 = k + 1;
      Object localObject2 = paramGoal.getPivotCandidate();
      k = i;
      j = m;
      Object localObject1 = localObject2;
      if (localObject2 != null) {
        if (mAlreadyTestedCandidates[id] != 0)
        {
          localObject1 = null;
          k = i;
          j = m;
        }
        else
        {
          mAlreadyTestedCandidates[id] = true;
          m += 1;
          k = i;
          j = m;
          localObject1 = localObject2;
          if (m >= mNumColumns)
          {
            k = 1;
            localObject1 = localObject2;
            j = m;
          }
        }
      }
      if (localObject1 != null)
      {
        float f1 = Float.MAX_VALUE;
        m = -1;
        i = 0;
        while (i < mNumRows)
        {
          localObject2 = mRows[i];
          int n;
          float f2;
          if (variable.mType == SolverVariable.Type.UNRESTRICTED)
          {
            n = m;
            f2 = f1;
          }
          else
          {
            n = m;
            f2 = f1;
            if (((ArrayRow)localObject2).hasVariable(localObject1))
            {
              float f3 = variables.get(localObject1);
              n = m;
              f2 = f1;
              if (f3 < 0.0F)
              {
                f3 = -constantValue / f3;
                n = m;
                f2 = f1;
                if (f3 < f1)
                {
                  n = i;
                  f2 = f3;
                }
              }
            }
          }
          i += 1;
          m = n;
          f1 = f2;
        }
        if (m > -1)
        {
          localObject2 = mRows[m];
          variable.definitionId = -1;
          ((ArrayRow)localObject2).pivot(localObject1);
          variable.definitionId = m;
          i = 0;
          while (i < mNumRows)
          {
            mRows[i].updateRowWithEquation((ArrayRow)localObject2);
            i += 1;
          }
          paramGoal.updateFromSystem(this);
          try
          {
            enforceBFS(paramGoal);
            i = k;
            k = i1;
            m = j;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
            i = k;
            k = i1;
            m = j;
          }
          continue;
        }
      }
      i = 1;
      k = i1;
      m = j;
    }
    return k;
  }
  
  private void releaseRows()
  {
    int i = 0;
    while (i < mRows.length)
    {
      ArrayRow localArrayRow = mRows[i];
      if (localArrayRow != null) {
        mCache.arrayRowPool.release(localArrayRow);
      }
      mRows[i] = null;
      i += 1;
    }
  }
  
  private void updateRowFromVariables(ArrayRow paramArrayRow)
  {
    if (mNumRows > 0)
    {
      variables.updateFromSystem(paramArrayRow, mRows);
      if (variables.currentSize == 0) {
        isSimpleDefinition = true;
      }
    }
  }
  
  public void addCentering(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, float paramFloat, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, int paramInt2, int paramInt3)
  {
    ArrayRow localArrayRow = createRow();
    localArrayRow.createRowCentering(paramSolverVariable1, paramSolverVariable2, paramInt1, paramFloat, paramSolverVariable3, paramSolverVariable4, paramInt2);
    paramSolverVariable1 = createErrorVariable();
    paramSolverVariable2 = createErrorVariable();
    strength = paramInt3;
    strength = paramInt3;
    localArrayRow.addError(paramSolverVariable1, paramSolverVariable2);
    addConstraint(localArrayRow);
  }
  
  public void addConstraint(ArrayRow paramArrayRow)
  {
    if (paramArrayRow == null) {
      return;
    }
    if ((mNumRows + 1 >= mMaxRows) || (mNumColumns + 1 >= mMaxColumns)) {
      increaseTableSize();
    }
    if (!isSimpleDefinition)
    {
      updateRowFromVariables(paramArrayRow);
      paramArrayRow.ensurePositiveConstant();
      paramArrayRow.pickRowVariable();
      if (!paramArrayRow.hasKeyVariable()) {
        return;
      }
    }
    if (mRows[mNumRows] != null) {
      mCache.arrayRowPool.release(mRows[mNumRows]);
    }
    if (!isSimpleDefinition) {
      paramArrayRow.updateClientEquations();
    }
    mRows[mNumRows] = paramArrayRow;
    variable.definitionId = mNumRows;
    mNumRows += 1;
    int m = variable.mClientEquationsCount;
    if (m > 0)
    {
      while (tempClientsCopy.length < m) {
        tempClientsCopy = new ArrayRow[tempClientsCopy.length * 2];
      }
      ArrayRow[] arrayOfArrayRow = tempClientsCopy;
      int k = 0;
      int i = 0;
      int j;
      for (;;)
      {
        j = k;
        if (i >= m) {
          break;
        }
        arrayOfArrayRow[i] = variable.mClientEquations[i];
        i += 1;
      }
      while (j < m)
      {
        ArrayRow localArrayRow = arrayOfArrayRow[j];
        if (localArrayRow != paramArrayRow)
        {
          variables.updateFromRow(localArrayRow, paramArrayRow);
          localArrayRow.updateClientEquations();
        }
        j += 1;
      }
    }
  }
  
  public ArrayRow addEquality(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, int paramInt2)
  {
    ArrayRow localArrayRow = createRow();
    localArrayRow.createRowEquals(paramSolverVariable1, paramSolverVariable2, paramInt1);
    paramSolverVariable1 = createErrorVariable();
    paramSolverVariable2 = createErrorVariable();
    strength = paramInt2;
    strength = paramInt2;
    localArrayRow.addError(paramSolverVariable1, paramSolverVariable2);
    addConstraint(localArrayRow);
    return localArrayRow;
  }
  
  public void addEquality(SolverVariable paramSolverVariable, int paramInt)
  {
    int i = definitionId;
    if (definitionId != -1)
    {
      localArrayRow = mRows[i];
      if (isSimpleDefinition)
      {
        constantValue = paramInt;
        return;
      }
      localArrayRow = createRow();
      localArrayRow.createRowEquals(paramSolverVariable, paramInt);
      addConstraint(localArrayRow);
      return;
    }
    ArrayRow localArrayRow = createRow();
    localArrayRow.createRowDefinition(paramSolverVariable, paramInt);
    addConstraint(localArrayRow);
  }
  
  public void addGreaterThan(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, int paramInt2)
  {
    ArrayRow localArrayRow = createRow();
    SolverVariable localSolverVariable = createSlackVariable();
    strength = paramInt2;
    localArrayRow.createRowGreaterThan(paramSolverVariable1, paramSolverVariable2, localSolverVariable, paramInt1);
    addConstraint(localArrayRow);
  }
  
  public void addLowerThan(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, int paramInt2)
  {
    ArrayRow localArrayRow = createRow();
    SolverVariable localSolverVariable = createSlackVariable();
    strength = paramInt2;
    localArrayRow.createRowLowerThan(paramSolverVariable1, paramSolverVariable2, localSolverVariable, paramInt1);
    addConstraint(localArrayRow);
  }
  
  public SolverVariable createErrorVariable()
  {
    if (mNumColumns + 1 >= mMaxColumns) {
      increaseTableSize();
    }
    SolverVariable localSolverVariable = acquireSolverVariable(SolverVariable.Type.ERROR);
    mVariablesID += 1;
    mNumColumns += 1;
    id = mVariablesID;
    mCache.mIndexedVariables[mVariablesID] = localSolverVariable;
    return localSolverVariable;
  }
  
  public SolverVariable createObjectVariable(Object paramObject)
  {
    Object localObject = null;
    if (paramObject == null) {
      return null;
    }
    if (mNumColumns + 1 >= mMaxColumns) {
      increaseTableSize();
    }
    if ((paramObject instanceof ConstraintAnchor))
    {
      ConstraintAnchor localConstraintAnchor = (ConstraintAnchor)paramObject;
      localObject = localConstraintAnchor.getSolverVariable();
      paramObject = localObject;
      if (localObject == null)
      {
        localConstraintAnchor.resetSolverVariable(mCache);
        paramObject = localConstraintAnchor.getSolverVariable();
      }
      if ((id != -1) && (id <= mVariablesID))
      {
        localObject = paramObject;
        if (mCache.mIndexedVariables[id] != null) {}
      }
      else
      {
        if (id != -1) {
          paramObject.reset();
        }
        mVariablesID += 1;
        mNumColumns += 1;
        id = mVariablesID;
        mType = SolverVariable.Type.UNRESTRICTED;
        mCache.mIndexedVariables[mVariablesID] = paramObject;
        localObject = paramObject;
      }
    }
    return localObject;
  }
  
  public ArrayRow createRow()
  {
    ArrayRow localArrayRow = (ArrayRow)mCache.arrayRowPool.acquire();
    if (localArrayRow == null) {
      return new ArrayRow(mCache);
    }
    localArrayRow.reset();
    return localArrayRow;
  }
  
  public SolverVariable createSlackVariable()
  {
    if (mNumColumns + 1 >= mMaxColumns) {
      increaseTableSize();
    }
    SolverVariable localSolverVariable = acquireSolverVariable(SolverVariable.Type.SLACK);
    mVariablesID += 1;
    mNumColumns += 1;
    id = mVariablesID;
    mCache.mIndexedVariables[mVariablesID] = localSolverVariable;
    return localSolverVariable;
  }
  
  void displayReadableRows()
  {
    displaySolverVariables();
    String str = "";
    int i = 0;
    while (i < mNumRows)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append(mRows[i].toReadableString());
      str = ((StringBuilder)localObject).toString();
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append("\n");
      str = ((StringBuilder)localObject).toString();
      i += 1;
    }
    Object localObject = str;
    if (mGoal != null)
    {
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append(str);
      ((StringBuilder)localObject).append(mGoal);
      ((StringBuilder)localObject).append("\n");
      localObject = ((StringBuilder)localObject).toString();
    }
    System.out.println((String)localObject);
  }
  
  void displaySystemInformations()
  {
    int j = 0;
    for (int i = j; j < TABLE_SIZE; i = k)
    {
      k = i;
      if (mRows[j] != null) {
        k = i + mRows[j].sizeInBytes();
      }
      j += 1;
    }
    j = 0;
    int m;
    for (int k = j; j < mNumRows; k = m)
    {
      m = k;
      if (mRows[j] != null) {
        m = k + mRows[j].sizeInBytes();
      }
      j += 1;
    }
    PrintStream localPrintStream = System.out;
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Linear System -> Table size: ");
    localStringBuilder.append(TABLE_SIZE);
    localStringBuilder.append(" (");
    localStringBuilder.append(getDisplaySize(TABLE_SIZE * TABLE_SIZE));
    localStringBuilder.append(") -- row sizes: ");
    localStringBuilder.append(getDisplaySize(i));
    localStringBuilder.append(", actual size: ");
    localStringBuilder.append(getDisplaySize(k));
    localStringBuilder.append(" rows: ");
    localStringBuilder.append(mNumRows);
    localStringBuilder.append("/");
    localStringBuilder.append(mMaxRows);
    localStringBuilder.append(" cols: ");
    localStringBuilder.append(mNumColumns);
    localStringBuilder.append("/");
    localStringBuilder.append(mMaxColumns);
    localStringBuilder.append(" ");
    localStringBuilder.append(0);
    localStringBuilder.append(" occupied cells, ");
    localStringBuilder.append(getDisplaySize(0));
    localPrintStream.println(localStringBuilder.toString());
  }
  
  public void displayVariablesReadableRows()
  {
    displaySolverVariables();
    Object localObject1 = "";
    int i = 0;
    while (i < mNumRows)
    {
      localObject2 = localObject1;
      if (mRows[i].variable.mType == SolverVariable.Type.UNRESTRICTED)
      {
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append(mRows[i].toReadableString());
        localObject1 = ((StringBuilder)localObject2).toString();
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append((String)localObject1);
        ((StringBuilder)localObject2).append("\n");
        localObject2 = ((StringBuilder)localObject2).toString();
      }
      i += 1;
      localObject1 = localObject2;
    }
    Object localObject2 = localObject1;
    if (mGoal.variables.size() != 0)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(mGoal);
      ((StringBuilder)localObject2).append("\n");
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    System.out.println((String)localObject2);
  }
  
  public Cache getCache()
  {
    return mCache;
  }
  
  Goal getGoal()
  {
    return mGoal;
  }
  
  public int getMemoryUsed()
  {
    int i = 0;
    int k;
    for (int j = 0; i < mNumRows; j = k)
    {
      k = j;
      if (mRows[i] != null) {
        k = j + mRows[i].sizeInBytes();
      }
      i += 1;
    }
    return j;
  }
  
  public int getNumEquations()
  {
    return mNumRows;
  }
  
  public int getNumVariables()
  {
    return mVariablesID;
  }
  
  public int getObjectVariableValue(Object paramObject)
  {
    paramObject = ((ConstraintAnchor)paramObject).getSolverVariable();
    if (paramObject != null) {
      return (int)(computedValue + 0.5F);
    }
    return 0;
  }
  
  ArrayRow getRow(int paramInt)
  {
    return mRows[paramInt];
  }
  
  float getValueFor(String paramString)
  {
    paramString = getVariable(paramString, SolverVariable.Type.UNRESTRICTED);
    if (paramString == null) {
      return 0.0F;
    }
    return computedValue;
  }
  
  SolverVariable getVariable(String paramString, SolverVariable.Type paramType)
  {
    if (mVariables == null) {
      mVariables = new HashMap();
    }
    SolverVariable localSolverVariable2 = (SolverVariable)mVariables.get(paramString);
    SolverVariable localSolverVariable1 = localSolverVariable2;
    if (localSolverVariable2 == null) {
      localSolverVariable1 = createVariable(paramString, paramType);
    }
    return localSolverVariable1;
  }
  
  public void minimize()
    throws Exception
  {
    minimizeGoal(mGoal);
  }
  
  void minimizeGoal(Goal paramGoal)
    throws Exception
  {
    paramGoal.updateFromSystem(this);
    enforceBFS(paramGoal);
    optimize(paramGoal);
    computeValues();
  }
  
  void rebuildGoalFromErrors()
  {
    mGoal.updateFromSystem(this);
  }
  
  public void reset()
  {
    int i = 0;
    while (i < mCache.mIndexedVariables.length)
    {
      SolverVariable localSolverVariable = mCache.mIndexedVariables[i];
      if (localSolverVariable != null) {
        localSolverVariable.reset();
      }
      i += 1;
    }
    mCache.solverVariablePool.releaseAll(mPoolVariables, mPoolVariablesCount);
    mPoolVariablesCount = 0;
    Arrays.fill(mCache.mIndexedVariables, null);
    if (mVariables != null) {
      mVariables.clear();
    }
    mVariablesID = 0;
    mGoal.variables.clear();
    mNumColumns = 1;
    i = 0;
    while (i < mNumRows)
    {
      mRows[i].used = false;
      i += 1;
    }
    releaseRows();
    mNumRows = 0;
  }
}

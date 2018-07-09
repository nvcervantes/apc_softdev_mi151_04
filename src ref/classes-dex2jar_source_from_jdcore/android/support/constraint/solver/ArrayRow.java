package android.support.constraint.solver;

public class ArrayRow
{
  private static final boolean DEBUG = false;
  float constantValue = 0.0F;
  boolean isSimpleDefinition = false;
  boolean used = false;
  SolverVariable variable = null;
  final ArrayLinkedVariables variables;
  
  public ArrayRow(Cache paramCache)
  {
    variables = new ArrayLinkedVariables(this, paramCache);
  }
  
  public ArrayRow addError(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2)
  {
    variables.put(paramSolverVariable1, 1.0F);
    variables.put(paramSolverVariable2, -1.0F);
    return this;
  }
  
  ArrayRow addSingleError(SolverVariable paramSolverVariable, int paramInt)
  {
    variables.put(paramSolverVariable, paramInt);
    return this;
  }
  
  ArrayRow createRowCentering(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt1, float paramFloat, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, int paramInt2)
  {
    if (paramSolverVariable2 == paramSolverVariable3)
    {
      variables.put(paramSolverVariable1, 1.0F);
      variables.put(paramSolverVariable4, 1.0F);
      variables.put(paramSolverVariable2, -2.0F);
      return this;
    }
    if (paramFloat == 0.5F)
    {
      variables.put(paramSolverVariable1, 1.0F);
      variables.put(paramSolverVariable2, -1.0F);
      variables.put(paramSolverVariable3, -1.0F);
      variables.put(paramSolverVariable4, 1.0F);
      if ((paramInt1 > 0) || (paramInt2 > 0))
      {
        constantValue = (-paramInt1 + paramInt2);
        return this;
      }
    }
    else
    {
      if (paramFloat <= 0.0F)
      {
        variables.put(paramSolverVariable1, -1.0F);
        variables.put(paramSolverVariable2, 1.0F);
        constantValue = paramInt1;
        return this;
      }
      if (paramFloat >= 1.0F)
      {
        variables.put(paramSolverVariable3, -1.0F);
        variables.put(paramSolverVariable4, 1.0F);
        constantValue = paramInt2;
        return this;
      }
      ArrayLinkedVariables localArrayLinkedVariables = variables;
      float f = 1.0F - paramFloat;
      localArrayLinkedVariables.put(paramSolverVariable1, 1.0F * f);
      variables.put(paramSolverVariable2, -1.0F * f);
      variables.put(paramSolverVariable3, -1.0F * paramFloat);
      variables.put(paramSolverVariable4, 1.0F * paramFloat);
      if ((paramInt1 > 0) || (paramInt2 > 0)) {
        constantValue = (-paramInt1 * f + paramInt2 * paramFloat);
      }
    }
    return this;
  }
  
  ArrayRow createRowDefinition(SolverVariable paramSolverVariable, int paramInt)
  {
    variable = paramSolverVariable;
    float f = paramInt;
    computedValue = f;
    constantValue = f;
    isSimpleDefinition = true;
    return this;
  }
  
  ArrayRow createRowDimensionPercent(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, float paramFloat)
  {
    variables.put(paramSolverVariable1, -1.0F);
    variables.put(paramSolverVariable2, 1.0F - paramFloat);
    variables.put(paramSolverVariable3, paramFloat);
    return this;
  }
  
  public ArrayRow createRowDimensionRatio(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, SolverVariable paramSolverVariable4, float paramFloat)
  {
    variables.put(paramSolverVariable1, -1.0F);
    variables.put(paramSolverVariable2, 1.0F);
    variables.put(paramSolverVariable3, paramFloat);
    variables.put(paramSolverVariable4, -paramFloat);
    return this;
  }
  
  public ArrayRow createRowEqualDimension(float paramFloat1, float paramFloat2, float paramFloat3, SolverVariable paramSolverVariable1, int paramInt1, SolverVariable paramSolverVariable2, int paramInt2, SolverVariable paramSolverVariable3, int paramInt3, SolverVariable paramSolverVariable4, int paramInt4)
  {
    if ((paramFloat2 != 0.0F) && (paramFloat1 != paramFloat3))
    {
      paramFloat1 = paramFloat1 / paramFloat2 / (paramFloat3 / paramFloat2);
      constantValue = (-paramInt1 - paramInt2 + paramInt3 * paramFloat1 + paramInt4 * paramFloat1);
      variables.put(paramSolverVariable1, 1.0F);
      variables.put(paramSolverVariable2, -1.0F);
      variables.put(paramSolverVariable4, paramFloat1);
      variables.put(paramSolverVariable3, -paramFloat1);
      return this;
    }
    constantValue = (-paramInt1 - paramInt2 + paramInt3 + paramInt4);
    variables.put(paramSolverVariable1, 1.0F);
    variables.put(paramSolverVariable2, -1.0F);
    variables.put(paramSolverVariable4, 1.0F);
    variables.put(paramSolverVariable3, -1.0F);
    return this;
  }
  
  public ArrayRow createRowEquals(SolverVariable paramSolverVariable, int paramInt)
  {
    if (paramInt < 0)
    {
      constantValue = (-1 * paramInt);
      variables.put(paramSolverVariable, 1.0F);
      return this;
    }
    constantValue = paramInt;
    variables.put(paramSolverVariable, -1.0F);
    return this;
  }
  
  public ArrayRow createRowEquals(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, int paramInt)
  {
    int i = 0;
    int j = 0;
    if (paramInt != 0)
    {
      i = j;
      j = paramInt;
      if (paramInt < 0)
      {
        j = paramInt * -1;
        i = 1;
      }
      constantValue = j;
    }
    if (i == 0)
    {
      variables.put(paramSolverVariable1, -1.0F);
      variables.put(paramSolverVariable2, 1.0F);
      return this;
    }
    variables.put(paramSolverVariable1, 1.0F);
    variables.put(paramSolverVariable2, -1.0F);
    return this;
  }
  
  public ArrayRow createRowGreaterThan(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, int paramInt)
  {
    int i = 0;
    int j = 0;
    if (paramInt != 0)
    {
      i = j;
      j = paramInt;
      if (paramInt < 0)
      {
        j = paramInt * -1;
        i = 1;
      }
      constantValue = j;
    }
    if (i == 0)
    {
      variables.put(paramSolverVariable1, -1.0F);
      variables.put(paramSolverVariable2, 1.0F);
      variables.put(paramSolverVariable3, 1.0F);
      return this;
    }
    variables.put(paramSolverVariable1, 1.0F);
    variables.put(paramSolverVariable2, -1.0F);
    variables.put(paramSolverVariable3, -1.0F);
    return this;
  }
  
  public ArrayRow createRowLowerThan(SolverVariable paramSolverVariable1, SolverVariable paramSolverVariable2, SolverVariable paramSolverVariable3, int paramInt)
  {
    int i = 0;
    int j = 0;
    if (paramInt != 0)
    {
      i = j;
      j = paramInt;
      if (paramInt < 0)
      {
        j = paramInt * -1;
        i = 1;
      }
      constantValue = j;
    }
    if (i == 0)
    {
      variables.put(paramSolverVariable1, -1.0F);
      variables.put(paramSolverVariable2, 1.0F);
      variables.put(paramSolverVariable3, -1.0F);
      return this;
    }
    variables.put(paramSolverVariable1, 1.0F);
    variables.put(paramSolverVariable2, -1.0F);
    variables.put(paramSolverVariable3, 1.0F);
    return this;
  }
  
  void ensurePositiveConstant()
  {
    if (constantValue < 0.0F)
    {
      constantValue *= -1.0F;
      variables.invert();
    }
  }
  
  boolean hasAtLeastOnePositiveVariable()
  {
    return variables.hasAtLeastOnePositiveVariable();
  }
  
  boolean hasKeyVariable()
  {
    return (variable != null) && ((variable.mType == SolverVariable.Type.UNRESTRICTED) || (constantValue >= 0.0F));
  }
  
  boolean hasVariable(SolverVariable paramSolverVariable)
  {
    return variables.containsKey(paramSolverVariable);
  }
  
  void pickRowVariable()
  {
    SolverVariable localSolverVariable = variables.pickPivotCandidate();
    if (localSolverVariable != null) {
      pivot(localSolverVariable);
    }
    if (variables.currentSize == 0) {
      isSimpleDefinition = true;
    }
  }
  
  void pivot(SolverVariable paramSolverVariable)
  {
    if (variable != null)
    {
      variables.put(variable, -1.0F);
      variable = null;
    }
    float f = variables.remove(paramSolverVariable) * -1.0F;
    variable = paramSolverVariable;
    if (f == 1.0F) {
      return;
    }
    constantValue /= f;
    variables.divideByAmount(f);
  }
  
  public void reset()
  {
    variable = null;
    variables.clear();
    constantValue = 0.0F;
    isSimpleDefinition = false;
  }
  
  int sizeInBytes()
  {
    int i;
    if (variable != null) {
      i = 4;
    } else {
      i = 0;
    }
    return i + 4 + 4 + variables.sizeInBytes();
  }
  
  String toReadableString()
  {
    if (variable == null)
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("");
      ((StringBuilder)localObject1).append("0");
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    else
    {
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("");
      ((StringBuilder)localObject1).append(variable);
      localObject1 = ((StringBuilder)localObject1).toString();
    }
    Object localObject2 = new StringBuilder();
    ((StringBuilder)localObject2).append((String)localObject1);
    ((StringBuilder)localObject2).append(" = ");
    Object localObject1 = ((StringBuilder)localObject2).toString();
    float f1 = constantValue;
    int j = 0;
    int i;
    if (f1 != 0.0F)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append(constantValue);
      localObject1 = ((StringBuilder)localObject2).toString();
      i = 1;
    }
    else
    {
      i = 0;
    }
    int k = variables.currentSize;
    while (j < k)
    {
      localObject2 = variables.getVariable(j);
      if (localObject2 != null)
      {
        float f2 = variables.getVariableValue(j);
        String str = ((SolverVariable)localObject2).toString();
        if (i == 0)
        {
          localObject2 = localObject1;
          f1 = f2;
          if (f2 < 0.0F)
          {
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append((String)localObject1);
            ((StringBuilder)localObject2).append("- ");
            localObject2 = ((StringBuilder)localObject2).toString();
            f1 = f2 * -1.0F;
          }
        }
        else if (f2 > 0.0F)
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append(" + ");
          localObject2 = ((StringBuilder)localObject2).toString();
          f1 = f2;
        }
        else
        {
          localObject2 = new StringBuilder();
          ((StringBuilder)localObject2).append((String)localObject1);
          ((StringBuilder)localObject2).append(" - ");
          localObject2 = ((StringBuilder)localObject2).toString();
          f1 = f2 * -1.0F;
        }
        if (f1 == 1.0F)
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append((String)localObject2);
          ((StringBuilder)localObject1).append(str);
          localObject1 = ((StringBuilder)localObject1).toString();
        }
        else
        {
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append((String)localObject2);
          ((StringBuilder)localObject1).append(f1);
          ((StringBuilder)localObject1).append(" ");
          ((StringBuilder)localObject1).append(str);
          localObject1 = ((StringBuilder)localObject1).toString();
        }
        i = 1;
      }
      j += 1;
    }
    localObject2 = localObject1;
    if (i == 0)
    {
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append((String)localObject1);
      ((StringBuilder)localObject2).append("0.0");
      localObject2 = ((StringBuilder)localObject2).toString();
    }
    return localObject2;
  }
  
  public String toString()
  {
    return toReadableString();
  }
  
  void updateClientEquations()
  {
    variables.updateClientEquations(this);
  }
  
  boolean updateRowWithEquation(ArrayRow paramArrayRow)
  {
    variables.updateFromRow(this, paramArrayRow);
    return true;
  }
}

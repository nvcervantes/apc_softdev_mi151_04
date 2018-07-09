package android.support.constraint.solver;

import java.util.ArrayList;

public class Goal
{
  ArrayList<SolverVariable> variables = new ArrayList();
  
  public Goal() {}
  
  private void initFromSystemErrors(LinearSystem paramLinearSystem)
  {
    variables.clear();
    int i = 1;
    while (i < mNumColumns)
    {
      SolverVariable localSolverVariable = mCache.mIndexedVariables[i];
      int j = 0;
      while (j < 6)
      {
        strengthVector[j] = 0.0F;
        j += 1;
      }
      strengthVector[strength] = 1.0F;
      if (mType == SolverVariable.Type.ERROR) {
        variables.add(localSolverVariable);
      }
      i += 1;
    }
  }
  
  SolverVariable getPivotCandidate()
  {
    int n = variables.size();
    int m = 0;
    int j = 0;
    Object localObject2 = null;
    while (m < n)
    {
      SolverVariable localSolverVariable = (SolverVariable)variables.get(m);
      int i = 5;
      while (i >= 0)
      {
        float f = strengthVector[i];
        Object localObject1 = localObject2;
        int k = j;
        if (localObject2 == null)
        {
          localObject1 = localObject2;
          k = j;
          if (f < 0.0F)
          {
            localObject1 = localObject2;
            k = j;
            if (i >= j)
            {
              localObject1 = localSolverVariable;
              k = i;
            }
          }
        }
        localObject2 = localObject1;
        j = k;
        if (f > 0.0F)
        {
          localObject2 = localObject1;
          j = k;
          if (i > k)
          {
            localObject2 = null;
            j = i;
          }
        }
        i -= 1;
      }
      m += 1;
    }
    return localObject2;
  }
  
  public String toString()
  {
    String str = "Goal: ";
    int j = variables.size();
    int i = 0;
    while (i < j)
    {
      SolverVariable localSolverVariable = (SolverVariable)variables.get(i);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(str);
      localStringBuilder.append(localSolverVariable.strengthsToString());
      str = localStringBuilder.toString();
      i += 1;
    }
    return str;
  }
  
  void updateFromSystem(LinearSystem paramLinearSystem)
  {
    initFromSystemErrors(paramLinearSystem);
    int m = variables.size();
    int i = 0;
    while (i < m)
    {
      SolverVariable localSolverVariable1 = (SolverVariable)variables.get(i);
      if (definitionId != -1)
      {
        ArrayLinkedVariables localArrayLinkedVariables = getRowdefinitionId).variables;
        int n = currentSize;
        int j = 0;
        while (j < n)
        {
          SolverVariable localSolverVariable2 = localArrayLinkedVariables.getVariable(j);
          if (localSolverVariable2 != null)
          {
            float f = localArrayLinkedVariables.getVariableValue(j);
            int k = 0;
            while (k < 6)
            {
              float[] arrayOfFloat = strengthVector;
              arrayOfFloat[k] += strengthVector[k] * f;
              k += 1;
            }
            if (!variables.contains(localSolverVariable2)) {
              variables.add(localSolverVariable2);
            }
          }
          j += 1;
        }
        localSolverVariable1.clearStrengths();
      }
      i += 1;
    }
  }
}

package com.byimplication.sakay.model;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000>\n\002\030\002\n\002\020\000\n\000\n\002\020\t\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\000\n\002\030\002\n\002\b\024\n\002\020\013\n\002\b\002\n\002\020\b\n\000\n\002\020\016\n\000\b\b\030\0002\0020\001B3\022\b\020\002\032\004\030\0010\003\022\006\020\004\032\0020\005\022\b\020\006\032\004\030\0010\007\022\b\020\b\032\004\030\0010\t\022\006\020\n\032\0020\013¢\006\002\020\fJ\020\020\030\032\004\030\0010\003HÆ\003¢\006\002\020\026J\t\020\031\032\0020\005HÆ\003J\013\020\032\032\004\030\0010\007HÆ\003J\013\020\033\032\004\030\0010\tHÆ\003J\t\020\034\032\0020\013HÆ\003JF\020\035\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\b\b\002\020\004\032\0020\0052\n\b\002\020\006\032\004\030\0010\0072\n\b\002\020\b\032\004\030\0010\t2\b\b\002\020\n\032\0020\013HÆ\001¢\006\002\020\036J\023\020\037\032\0020 2\b\020!\032\004\030\0010\001HÖ\003J\t\020\"\032\0020#HÖ\001J\t\020$\032\0020%HÖ\001R\021\020\n\032\0020\013¢\006\b\n\000\032\004\b\r\020\016R\023\020\b\032\004\030\0010\t¢\006\b\n\000\032\004\b\017\020\020R\023\020\006\032\004\030\0010\007¢\006\b\n\000\032\004\b\021\020\022R\021\020\004\032\0020\005¢\006\b\n\000\032\004\b\023\020\024R\025\020\002\032\004\030\0010\003¢\006\n\n\002\020\027\032\004\b\025\020\026¨\006&"}, d2={"Lcom/byimplication/sakay/model/PlanResponse;", "", "searchId", "", "requestParameters", "Lcom/byimplication/sakay/model/RequestParameters;", "plan", "Lcom/byimplication/sakay/model/Plan;", "error", "Lcom/byimplication/sakay/model/PlanError;", "debugOutput", "Lcom/byimplication/sakay/model/DebugOutput;", "(Ljava/lang/Long;Lcom/byimplication/sakay/model/RequestParameters;Lcom/byimplication/sakay/model/Plan;Lcom/byimplication/sakay/model/PlanError;Lcom/byimplication/sakay/model/DebugOutput;)V", "getDebugOutput", "()Lcom/byimplication/sakay/model/DebugOutput;", "getError", "()Lcom/byimplication/sakay/model/PlanError;", "getPlan", "()Lcom/byimplication/sakay/model/Plan;", "getRequestParameters", "()Lcom/byimplication/sakay/model/RequestParameters;", "getSearchId", "()Ljava/lang/Long;", "Ljava/lang/Long;", "component1", "component2", "component3", "component4", "component5", "copy", "(Ljava/lang/Long;Lcom/byimplication/sakay/model/RequestParameters;Lcom/byimplication/sakay/model/Plan;Lcom/byimplication/sakay/model/PlanError;Lcom/byimplication/sakay/model/DebugOutput;)Lcom/byimplication/sakay/model/PlanResponse;", "equals", "", "other", "hashCode", "", "toString", "", "app_release"}, k=1, mv={1, 1, 9})
public final class PlanResponse
{
  @NotNull
  private final DebugOutput debugOutput;
  @Nullable
  private final PlanError error;
  @Nullable
  private final Plan plan;
  @NotNull
  private final RequestParameters requestParameters;
  @Nullable
  private final Long searchId;
  
  public PlanResponse(@Nullable Long paramLong, @NotNull RequestParameters paramRequestParameters, @Nullable Plan paramPlan, @Nullable PlanError paramPlanError, @NotNull DebugOutput paramDebugOutput)
  {
    searchId = paramLong;
    requestParameters = paramRequestParameters;
    plan = paramPlan;
    error = paramPlanError;
    debugOutput = paramDebugOutput;
  }
  
  @Nullable
  public final Long component1()
  {
    return searchId;
  }
  
  @NotNull
  public final RequestParameters component2()
  {
    return requestParameters;
  }
  
  @Nullable
  public final Plan component3()
  {
    return plan;
  }
  
  @Nullable
  public final PlanError component4()
  {
    return error;
  }
  
  @NotNull
  public final DebugOutput component5()
  {
    return debugOutput;
  }
  
  @NotNull
  public final PlanResponse copy(@Nullable Long paramLong, @NotNull RequestParameters paramRequestParameters, @Nullable Plan paramPlan, @Nullable PlanError paramPlanError, @NotNull DebugOutput paramDebugOutput)
  {
    Intrinsics.checkParameterIsNotNull(paramRequestParameters, "requestParameters");
    Intrinsics.checkParameterIsNotNull(paramDebugOutput, "debugOutput");
    return new PlanResponse(paramLong, paramRequestParameters, paramPlan, paramPlanError, paramDebugOutput);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject) {
      if ((paramObject instanceof PlanResponse))
      {
        paramObject = (PlanResponse)paramObject;
        if ((Intrinsics.areEqual(searchId, searchId)) && (Intrinsics.areEqual(requestParameters, requestParameters)) && (Intrinsics.areEqual(plan, plan)) && (Intrinsics.areEqual(error, error)) && (Intrinsics.areEqual(debugOutput, debugOutput))) {}
      }
      else
      {
        return false;
      }
    }
    return true;
  }
  
  @NotNull
  public final DebugOutput getDebugOutput()
  {
    return debugOutput;
  }
  
  @Nullable
  public final PlanError getError()
  {
    return error;
  }
  
  @Nullable
  public final Plan getPlan()
  {
    return plan;
  }
  
  @NotNull
  public final RequestParameters getRequestParameters()
  {
    return requestParameters;
  }
  
  @Nullable
  public final Long getSearchId()
  {
    return searchId;
  }
  
  public int hashCode()
  {
    Object localObject = searchId;
    int n = 0;
    int i;
    if (localObject != null) {
      i = localObject.hashCode();
    } else {
      i = 0;
    }
    localObject = requestParameters;
    int j;
    if (localObject != null) {
      j = localObject.hashCode();
    } else {
      j = 0;
    }
    localObject = plan;
    int k;
    if (localObject != null) {
      k = localObject.hashCode();
    } else {
      k = 0;
    }
    localObject = error;
    int m;
    if (localObject != null) {
      m = localObject.hashCode();
    } else {
      m = 0;
    }
    localObject = debugOutput;
    if (localObject != null) {
      n = localObject.hashCode();
    }
    return (((i * 31 + j) * 31 + k) * 31 + m) * 31 + n;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PlanResponse(searchId=");
    localStringBuilder.append(searchId);
    localStringBuilder.append(", requestParameters=");
    localStringBuilder.append(requestParameters);
    localStringBuilder.append(", plan=");
    localStringBuilder.append(plan);
    localStringBuilder.append(", error=");
    localStringBuilder.append(error);
    localStringBuilder.append(", debugOutput=");
    localStringBuilder.append(debugOutput);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

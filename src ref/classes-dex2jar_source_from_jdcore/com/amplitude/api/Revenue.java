package com.amplitude.api;

import org.json.JSONException;
import org.json.JSONObject;

public class Revenue
{
  public static final String TAG = "com.amplitude.api.Revenue";
  private static AmplitudeLog logger = ;
  protected Double price = null;
  protected String productId = null;
  protected JSONObject properties = null;
  protected int quantity = 1;
  protected String receipt = null;
  protected String receiptSig = null;
  protected String revenueType = null;
  
  public Revenue() {}
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (this == paramObject) {
      return true;
    }
    if (paramObject != null)
    {
      if (getClass() != paramObject.getClass()) {
        return false;
      }
      paramObject = (Revenue)paramObject;
      if (quantity != quantity) {
        return false;
      }
      if (productId != null)
      {
        if (!productId.equals(productId)) {
          return false;
        }
      }
      else if (productId != null) {
        return false;
      }
      if (price != null)
      {
        if (!price.equals(price)) {
          return false;
        }
      }
      else if (price != null) {
        return false;
      }
      if (revenueType != null)
      {
        if (!revenueType.equals(revenueType)) {
          return false;
        }
      }
      else if (revenueType != null) {
        return false;
      }
      if (receipt != null)
      {
        if (!receipt.equals(receipt)) {
          return false;
        }
      }
      else if (receipt != null) {
        return false;
      }
      if (receiptSig != null)
      {
        if (!receiptSig.equals(receiptSig)) {
          return false;
        }
      }
      else if (receiptSig != null) {
        return false;
      }
      if (properties != null) {
        if (Utils.compareJSONObjects(properties, properties)) {
          break label239;
        }
      } else if (properties == null) {
        return true;
      }
      bool = false;
      label239:
      return bool;
    }
    return false;
  }
  
  public int hashCode()
  {
    String str = productId;
    int i1 = 0;
    int i;
    if (str != null) {
      i = productId.hashCode();
    } else {
      i = 0;
    }
    int i2 = quantity;
    int j;
    if (price != null) {
      j = price.hashCode();
    } else {
      j = 0;
    }
    int k;
    if (revenueType != null) {
      k = revenueType.hashCode();
    } else {
      k = 0;
    }
    int m;
    if (receipt != null) {
      m = receipt.hashCode();
    } else {
      m = 0;
    }
    int n;
    if (receiptSig != null) {
      n = receiptSig.hashCode();
    } else {
      n = 0;
    }
    if (properties != null) {
      i1 = properties.hashCode();
    }
    return 31 * (((((i * 31 + i2) * 31 + j) * 31 + k) * 31 + m) * 31 + n) + i1;
  }
  
  protected boolean isValidRevenue()
  {
    if (price == null)
    {
      logger.w("com.amplitude.api.Revenue", "Invalid revenue, need to set price");
      return false;
    }
    return true;
  }
  
  public Revenue setEventProperties(JSONObject paramJSONObject)
  {
    properties = Utils.cloneJSONObject(paramJSONObject);
    return this;
  }
  
  public Revenue setPrice(double paramDouble)
  {
    price = Double.valueOf(paramDouble);
    return this;
  }
  
  public Revenue setProductId(String paramString)
  {
    if (Utils.isEmptyString(paramString))
    {
      logger.w("com.amplitude.api.Revenue", "Invalid empty productId");
      return this;
    }
    productId = paramString;
    return this;
  }
  
  public Revenue setQuantity(int paramInt)
  {
    quantity = paramInt;
    return this;
  }
  
  public Revenue setReceipt(String paramString1, String paramString2)
  {
    receipt = paramString1;
    receiptSig = paramString2;
    return this;
  }
  
  public Revenue setRevenueProperties(JSONObject paramJSONObject)
  {
    logger.w("com.amplitude.api.Revenue", "setRevenueProperties is deprecated, please use setEventProperties instead");
    return setEventProperties(paramJSONObject);
  }
  
  public Revenue setRevenueType(String paramString)
  {
    revenueType = paramString;
    return this;
  }
  
  protected JSONObject toJSONObject()
  {
    JSONObject localJSONObject;
    if (properties == null) {
      localJSONObject = new JSONObject();
    } else {
      localJSONObject = properties;
    }
    try
    {
      localJSONObject.put("$productId", productId);
      localJSONObject.put("$quantity", quantity);
      localJSONObject.put("$price", price);
      localJSONObject.put("$revenueType", revenueType);
      localJSONObject.put("$receipt", receipt);
      localJSONObject.put("$receiptSig", receiptSig);
      return localJSONObject;
    }
    catch (JSONException localJSONException)
    {
      logger.e("com.amplitude.api.Revenue", String.format("Failed to convert revenue object to JSON: %s", new Object[] { localJSONException.toString() }));
    }
    return localJSONObject;
  }
}

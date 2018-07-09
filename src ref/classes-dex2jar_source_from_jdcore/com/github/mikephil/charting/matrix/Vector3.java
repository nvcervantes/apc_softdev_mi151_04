package com.github.mikephil.charting.matrix;

public final class Vector3
{
  public static final Vector3 UNIT_X = new Vector3(1.0F, 0.0F, 0.0F);
  public static final Vector3 UNIT_Y = new Vector3(0.0F, 1.0F, 0.0F);
  public static final Vector3 UNIT_Z = new Vector3(0.0F, 0.0F, 1.0F);
  public static final Vector3 ZERO = new Vector3(0.0F, 0.0F, 0.0F);
  public float x;
  public float y;
  public float z;
  
  public Vector3() {}
  
  public Vector3(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    set(paramFloat1, paramFloat2, paramFloat3);
  }
  
  public Vector3(Vector3 paramVector3)
  {
    set(paramVector3);
  }
  
  public Vector3(float[] paramArrayOfFloat)
  {
    set(paramArrayOfFloat[0], paramArrayOfFloat[1], paramArrayOfFloat[2]);
  }
  
  public final void add(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    x += paramFloat1;
    y += paramFloat2;
    z += paramFloat3;
  }
  
  public final void add(Vector3 paramVector3)
  {
    x += x;
    y += y;
    z += z;
  }
  
  public final Vector3 cross(Vector3 paramVector3)
  {
    return new Vector3(y * z - z * y, z * x - x * z, x * y - y * x);
  }
  
  public final float distance2(Vector3 paramVector3)
  {
    float f1 = x - x;
    float f2 = y - y;
    float f3 = z - z;
    return f1 * f1 + f2 * f2 + f3 * f3;
  }
  
  public final void divide(float paramFloat)
  {
    if (paramFloat != 0.0F)
    {
      x /= paramFloat;
      y /= paramFloat;
      z /= paramFloat;
    }
  }
  
  public final float dot(Vector3 paramVector3)
  {
    return x * x + y * y + z * z;
  }
  
  public final float length()
  {
    return (float)Math.sqrt(length2());
  }
  
  public final float length2()
  {
    return x * x + y * y + z * z;
  }
  
  public final void multiply(float paramFloat)
  {
    x *= paramFloat;
    y *= paramFloat;
    z *= paramFloat;
  }
  
  public final void multiply(Vector3 paramVector3)
  {
    x *= x;
    y *= y;
    z *= z;
  }
  
  public final float normalize()
  {
    float f = length();
    if (f != 0.0F)
    {
      x /= f;
      y /= f;
      z /= f;
    }
    return f;
  }
  
  public final boolean pointsInSameDirection(Vector3 paramVector3)
  {
    return dot(paramVector3) > 0.0F;
  }
  
  public final void set(float paramFloat1, float paramFloat2, float paramFloat3)
  {
    x = paramFloat1;
    y = paramFloat2;
    z = paramFloat3;
  }
  
  public final void set(Vector3 paramVector3)
  {
    x = x;
    y = y;
    z = z;
  }
  
  public final void subtract(Vector3 paramVector3)
  {
    x -= x;
    y -= y;
    z -= z;
  }
  
  public final void subtractMultiple(Vector3 paramVector3, float paramFloat)
  {
    x -= x * paramFloat;
    y -= y * paramFloat;
    z -= z * paramFloat;
  }
  
  public final void zero()
  {
    set(0.0F, 0.0F, 0.0F);
  }
}

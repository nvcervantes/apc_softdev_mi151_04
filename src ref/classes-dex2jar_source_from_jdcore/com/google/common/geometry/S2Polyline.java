package com.google.common.geometry;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

public final class S2Polyline
  implements S2Region
{
  private static final Logger log = Logger.getLogger(S2Polyline.class.getCanonicalName());
  private final int numVertices;
  private final S2Point[] vertices;
  
  public strictfp S2Polyline(S2Polyline paramS2Polyline)
  {
    numVertices = paramS2Polyline.numVertices();
    vertices = ((S2Point[])vertices.clone());
  }
  
  public strictfp S2Polyline(List<S2Point> paramList)
  {
    numVertices = paramList.size();
    vertices = ((S2Point[])paramList.toArray(new S2Point[numVertices]));
  }
  
  public static strictfp boolean isValid(List<S2Point> paramList)
  {
    int j = paramList.size();
    int i = 0;
    StringBuilder localStringBuilder;
    while (i < j)
    {
      if (!S2.isUnitLength((S2Point)paramList.get(i)))
      {
        paramList = log;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Vertex ");
        localStringBuilder.append(i);
        localStringBuilder.append(" is not unit length");
        paramList.info(localStringBuilder.toString());
        return false;
      }
      i += 1;
    }
    i = 1;
    while (i < j)
    {
      int k = i - 1;
      if ((!((S2Point)paramList.get(k)).equals(paramList.get(i))) && (!((S2Point)paramList.get(k)).equals(S2Point.neg((S2Point)paramList.get(i)))))
      {
        i += 1;
      }
      else
      {
        paramList = log;
        localStringBuilder = new StringBuilder();
        localStringBuilder.append("Vertices ");
        localStringBuilder.append(k);
        localStringBuilder.append(" and ");
        localStringBuilder.append(i);
        localStringBuilder.append(" are identical or antipodal");
        paramList.info(localStringBuilder.toString());
        return false;
      }
    }
    return true;
  }
  
  public strictfp boolean contains(S2Cell paramS2Cell)
  {
    throw new UnsupportedOperationException("'containment' is not numerically well-defined except at the polyline vertices");
  }
  
  public strictfp boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof S2Polyline)) {
      return false;
    }
    paramObject = (S2Polyline)paramObject;
    if (numVertices != numVertices) {
      return false;
    }
    int i = 0;
    while (i < vertices.length)
    {
      if (!vertices[i].equals(vertices[i])) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public strictfp S1Angle getArclengthAngle()
  {
    int i = 1;
    double d = 0.0D;
    while (i < numVertices())
    {
      d += vertex(i - 1).angle(vertex(i));
      i += 1;
    }
    return S1Angle.radians(d);
  }
  
  public strictfp S2Cap getCapBound()
  {
    return getRectBound().getCapBound();
  }
  
  public strictfp int getNearestEdgeIndex(S2Point paramS2Point)
  {
    if (numVertices() <= 0) {
      throw new IllegalStateException("Empty polyline");
    }
    int j = numVertices();
    int i = 0;
    if (j == 1) {
      return 0;
    }
    Object localObject1 = S1Angle.radians(10.0D);
    j = -1;
    while (i < numVertices() - 1)
    {
      Object localObject2 = vertex(i);
      int k = i + 1;
      S1Angle localS1Angle = S2EdgeUtil.getDistance(paramS2Point, (S2Point)localObject2, vertex(k));
      localObject2 = localObject1;
      if (localS1Angle.lessThan((S1Angle)localObject1))
      {
        localObject2 = localS1Angle;
        j = i;
      }
      i = k;
      localObject1 = localObject2;
    }
    return j;
  }
  
  public strictfp S2LatLngRect getRectBound()
  {
    S2EdgeUtil.RectBounder localRectBounder = new S2EdgeUtil.RectBounder();
    int i = 0;
    while (i < numVertices())
    {
      localRectBounder.addPoint(vertex(i));
      i += 1;
    }
    return localRectBounder.getBound();
  }
  
  public strictfp int hashCode()
  {
    Object[] arrayOfObject = new Object[2];
    arrayOfObject[0] = Integer.valueOf(numVertices);
    arrayOfObject[1] = Integer.valueOf(Arrays.deepHashCode(vertices));
    int m = arrayOfObject.length;
    int j = 1;
    int i = 0;
    while (i < m)
    {
      Object localObject = arrayOfObject[i];
      int k;
      if (localObject == null) {
        k = 0;
      } else {
        k = localObject.hashCode();
      }
      j = k + 31 * j;
      i += 1;
    }
    return j;
  }
  
  public strictfp S2Point interpolate(double paramDouble)
  {
    if (paramDouble <= 0.0D) {
      return vertex(0);
    }
    double d1 = 0.0D;
    int i = 1;
    while (i < numVertices())
    {
      d1 += vertex(i - 1).angle(vertex(i));
      i += 1;
    }
    paramDouble *= d1;
    i = 1;
    while (i < numVertices())
    {
      int j = i - 1;
      d1 = vertex(j).angle(vertex(i));
      if (paramDouble < d1)
      {
        double d2 = Math.sin(paramDouble) / Math.sin(d1);
        return S2Point.add(S2Point.mul(vertex(j), Math.cos(paramDouble) - Math.cos(d1) * d2), S2Point.mul(vertex(i), d2));
      }
      paramDouble -= d1;
      i += 1;
    }
    return vertex(numVertices() - 1);
  }
  
  public strictfp boolean mayIntersect(S2Cell paramS2Cell)
  {
    if (numVertices() == 0) {
      return false;
    }
    int i = 0;
    while (i < numVertices())
    {
      if (paramS2Cell.contains(vertex(i))) {
        return true;
      }
      i += 1;
    }
    S2Point[] arrayOfS2Point = new S2Point[4];
    i = 0;
    while (i < 4)
    {
      arrayOfS2Point[i] = paramS2Cell.getVertex(i);
      i += 1;
    }
    i = 0;
    if (i < 4)
    {
      paramS2Cell = arrayOfS2Point[i];
      int k = i + 1;
      paramS2Cell = new S2EdgeUtil.EdgeCrosser(paramS2Cell, arrayOfS2Point[(k & 0x3)], vertex(0));
      int j = 1;
      for (;;)
      {
        i = k;
        if (j >= numVertices()) {
          break;
        }
        if (paramS2Cell.robustCrossing(vertex(j)) >= 0) {
          return true;
        }
        j += 1;
      }
    }
    return false;
  }
  
  public strictfp int numVertices()
  {
    return numVertices;
  }
  
  public strictfp S2Point projectToEdge(S2Point paramS2Point, int paramInt)
  {
    if (numVertices() <= 0) {
      throw new IllegalStateException("Empty polyline");
    }
    if ((numVertices() != 1) && (paramInt >= numVertices() - 1)) {
      throw new IllegalStateException("Invalid edge index");
    }
    if (numVertices() == 1) {
      return vertex(0);
    }
    return S2EdgeUtil.getClosestPoint(paramS2Point, vertex(paramInt), vertex(paramInt + 1));
  }
  
  public strictfp S2Point vertex(int paramInt)
  {
    return vertices[paramInt];
  }
}

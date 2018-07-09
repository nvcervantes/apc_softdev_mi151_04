package com.google.common.geometry;

import java.lang.reflect.Array;

public final class S2Cell
  implements S2Region
{
  private static final int MAX_CELL_SIZE = 1073741824;
  private static final double MAX_ERROR = 4.440892098500626E-16D;
  private static final double POLE_MIN_LAT = Math.asin(Math.sqrt(0.3333333333333333D)) - 4.440892098500626E-16D;
  S2CellId cellId;
  byte face;
  byte level;
  byte orientation;
  double[][] uv = (double[][])Array.newInstance(Double.TYPE, new int[] { 2, 2 });
  
  strictfp S2Cell() {}
  
  public strictfp S2Cell(S2CellId paramS2CellId)
  {
    init(paramS2CellId);
  }
  
  public strictfp S2Cell(S2LatLng paramS2LatLng)
  {
    init(S2CellId.fromLatLng(paramS2LatLng));
  }
  
  public strictfp S2Cell(S2Point paramS2Point)
  {
    init(S2CellId.fromPoint(paramS2Point));
  }
  
  public static strictfp double averageArea(int paramInt)
  {
    return S2Projections.AVG_AREA.getValue(paramInt);
  }
  
  public static strictfp S2Cell fromFacePosLevel(int paramInt1, byte paramByte, int paramInt2)
  {
    return new S2Cell(S2CellId.fromFacePosLevel(paramInt1, paramByte, paramInt2));
  }
  
  private strictfp double getLatitude(int paramInt1, int paramInt2)
  {
    S2Point localS2Point = S2Projections.faceUvToXyz(face, uv[0][paramInt1], uv[1][paramInt2]);
    return Math.atan2(z, Math.sqrt(x * x + y * y));
  }
  
  private strictfp double getLongitude(int paramInt1, int paramInt2)
  {
    S2Point localS2Point = S2Projections.faceUvToXyz(face, uv[0][paramInt1], uv[1][paramInt2]);
    return Math.atan2(y, x);
  }
  
  private strictfp void init(S2CellId paramS2CellId)
  {
    cellId = paramS2CellId;
    MutableInteger[] arrayOfMutableInteger = new MutableInteger[2];
    MutableInteger localMutableInteger = new MutableInteger(0);
    int i = 0;
    while (i < 2)
    {
      arrayOfMutableInteger[i] = new MutableInteger(0);
      i += 1;
    }
    face = ((byte)paramS2CellId.toFaceIJOrientation(arrayOfMutableInteger[0], arrayOfMutableInteger[1], localMutableInteger));
    orientation = ((byte)localMutableInteger.intValue());
    level = ((byte)paramS2CellId.level());
    int j = 1 << 30 - level;
    i = 0;
    while (i < 2)
    {
      int k = (arrayOfMutableInteger[i].intValue() & -j) * 2 - 1073741824;
      uv[i][0] = S2Projections.stToUV(k * 9.313225746154785E-10D);
      uv[i][1] = S2Projections.stToUV(9.313225746154785E-10D * (j * 2 + k));
      i += 1;
    }
  }
  
  public strictfp double approxArea()
  {
    if (level < 2) {
      return averageArea(level);
    }
    double d = 0.5D * S2Point.crossProd(S2Point.sub(getVertex(2), getVertex(0)), S2Point.sub(getVertex(3), getVertex(1))).norm();
    return 2.0D * d / (1.0D + Math.sqrt(1.0D - Math.min(0.3183098861837907D * d, 1.0D)));
  }
  
  public strictfp double averageArea()
  {
    return averageArea(level);
  }
  
  public strictfp S2Region clone()
  {
    S2Cell localS2Cell = new S2Cell();
    face = face;
    level = level;
    orientation = orientation;
    uv = ((double[][])uv.clone());
    return localS2Cell;
  }
  
  public strictfp boolean contains(S2Cell paramS2Cell)
  {
    return cellId.contains(cellId);
  }
  
  public strictfp boolean contains(S2Point paramS2Point)
  {
    paramS2Point = S2Projections.faceXyzToUv(face, paramS2Point);
    boolean bool2 = false;
    if (paramS2Point == null) {
      return false;
    }
    boolean bool1 = bool2;
    if (paramS2Point.x() >= uv[0][0])
    {
      bool1 = bool2;
      if (paramS2Point.x() <= uv[0][1])
      {
        bool1 = bool2;
        if (paramS2Point.y() >= uv[1][0])
        {
          bool1 = bool2;
          if (paramS2Point.y() <= uv[1][1]) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public strictfp boolean equals(Object paramObject)
  {
    boolean bool1 = paramObject instanceof S2Cell;
    boolean bool2 = false;
    if (bool1)
    {
      paramObject = (S2Cell)paramObject;
      bool1 = bool2;
      if (face == face)
      {
        bool1 = bool2;
        if (level == level)
        {
          bool1 = bool2;
          if (orientation == orientation)
          {
            bool1 = bool2;
            if (cellId.equals(cellId)) {
              bool1 = true;
            }
          }
        }
      }
      return bool1;
    }
    return false;
  }
  
  public strictfp double exactArea()
  {
    S2Point localS2Point1 = getVertex(0);
    S2Point localS2Point2 = getVertex(1);
    S2Point localS2Point3 = getVertex(2);
    S2Point localS2Point4 = getVertex(3);
    return S2.area(localS2Point1, localS2Point2, localS2Point3) + S2.area(localS2Point1, localS2Point3, localS2Point4);
  }
  
  public strictfp int face()
  {
    return face;
  }
  
  public strictfp S2Cap getCapBound()
  {
    Object localObject = uv;
    int i = 0;
    double d1 = localObject[0][0];
    double d2 = uv[0][1];
    double d3 = uv[1][0];
    double d4 = uv[1][1];
    localObject = S2Cap.fromAxisHeight(S2Point.normalize(S2Projections.faceUvToXyz(face, (d1 + d2) * 0.5D, 0.5D * (d3 + d4))), 0.0D);
    while (i < 4)
    {
      localObject = ((S2Cap)localObject).addPoint(getVertex(i));
      i += 1;
    }
    return localObject;
  }
  
  public strictfp S2Point getCenter()
  {
    return S2Point.normalize(getCenterRaw());
  }
  
  public strictfp S2Point getCenterRaw()
  {
    return cellId.toPointRaw();
  }
  
  public strictfp R2Vector getCenterUV()
  {
    MutableInteger localMutableInteger1 = new MutableInteger(0);
    MutableInteger localMutableInteger2 = new MutableInteger(0);
    cellId.toFaceIJOrientation(localMutableInteger1, localMutableInteger2, null);
    int i = 1 << 30 - level;
    int j = localMutableInteger1.intValue();
    int k = -i;
    return new R2Vector(S2Projections.stToUV(((j & k) * 2 + i - 1073741824) * 9.313225746154785E-10D), S2Projections.stToUV(9.313225746154785E-10D * ((localMutableInteger2.intValue() & k) * 2 + i - 1073741824)));
  }
  
  public strictfp S2Point getEdge(int paramInt)
  {
    return S2Point.normalize(getEdgeRaw(paramInt));
  }
  
  public strictfp S2Point getEdgeRaw(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return S2Point.neg(S2Projections.getUNorm(face, uv[0][0]));
    case 2: 
      return S2Point.neg(S2Projections.getVNorm(face, uv[1][1]));
    case 1: 
      return S2Projections.getUNorm(face, uv[0][1]);
    }
    return S2Projections.getVNorm(face, uv[1][0]);
  }
  
  public strictfp S2LatLngRect getRectBound()
  {
    if (level > 0)
    {
      Object localObject = uv;
      int j = 0;
      double d1 = localObject[0][0] + uv[0][1];
      double d2 = uv[1][0] + uv[1][1];
      if (getUAxisface).z == 0.0D) {
        if (d1 >= 0.0D) {}
      }
      int i;
      for (;;)
      {
        i = 1;
        break;
        do
        {
          i = 0;
          break;
        } while (d1 <= 0.0D);
      }
      if (getVAxisface).z == 0.0D)
      {
        if (d2 >= 0.0D) {}
      }
      else {
        while (d2 > 0.0D)
        {
          j = 1;
          break;
        }
      }
      d1 = getLatitude(i, j);
      int k = 1 - i;
      int m = 1 - j;
      localObject = R1Interval.fromPointPair(d1, getLatitude(k, m)).expanded(4.440892098500626E-16D).intersection(S2LatLngRect.fullLat());
      if ((((R1Interval)localObject).lo() != -1.5707963267948966D) && (((R1Interval)localObject).hi() != 1.5707963267948966D)) {
        return new S2LatLngRect((R1Interval)localObject, S1Interval.fromPointPair(getLongitude(i, m), getLongitude(k, j)).expanded(4.440892098500626E-16D));
      }
      return new S2LatLngRect((R1Interval)localObject, S1Interval.full());
    }
    switch (face)
    {
    default: 
      return new S2LatLngRect(new R1Interval(-1.5707963267948966D, -POLE_MIN_LAT), new S1Interval(-3.141592653589793D, 3.141592653589793D));
    case 4: 
      return new S2LatLngRect(new R1Interval(-0.7853981633974483D, 0.7853981633974483D), new S1Interval(-2.356194490192345D, -0.7853981633974483D));
    case 3: 
      return new S2LatLngRect(new R1Interval(-0.7853981633974483D, 0.7853981633974483D), new S1Interval(2.356194490192345D, -2.356194490192345D));
    case 2: 
      return new S2LatLngRect(new R1Interval(POLE_MIN_LAT, 1.5707963267948966D), new S1Interval(-3.141592653589793D, 3.141592653589793D));
    case 1: 
      return new S2LatLngRect(new R1Interval(-0.7853981633974483D, 0.7853981633974483D), new S1Interval(0.7853981633974483D, 2.356194490192345D));
    }
    return new S2LatLngRect(new R1Interval(-0.7853981633974483D, 0.7853981633974483D), new S1Interval(-0.7853981633974483D, 0.7853981633974483D));
  }
  
  public strictfp S2Point getVertex(int paramInt)
  {
    return S2Point.normalize(getVertexRaw(paramInt));
  }
  
  public strictfp S2Point getVertexRaw(int paramInt)
  {
    int i = face;
    double[] arrayOfDouble = uv[0];
    int j = paramInt >> 1;
    return S2Projections.faceUvToXyz(i, arrayOfDouble[(paramInt & 0x1 ^ j)], uv[1][j]);
  }
  
  public strictfp int hashCode()
  {
    return 37 * (((629 + face) * 37 + orientation) * 37 + level) + id().hashCode();
  }
  
  public strictfp S2CellId id()
  {
    return cellId;
  }
  
  public strictfp boolean isLeaf()
  {
    return level == 30;
  }
  
  public strictfp byte level()
  {
    return level;
  }
  
  public strictfp boolean mayIntersect(S2Cell paramS2Cell)
  {
    return cellId.intersects(cellId);
  }
  
  public strictfp byte orientation()
  {
    return orientation;
  }
  
  public strictfp boolean subdivide(S2Cell[] paramArrayOfS2Cell)
  {
    if (cellId.isLeaf()) {
      return false;
    }
    R2Vector localR2Vector = getCenterUV();
    S2CellId localS2CellId = cellId.childBegin();
    int i = 0;
    while (i < 4)
    {
      S2Cell localS2Cell = paramArrayOfS2Cell[i];
      face = face;
      level = ((byte)(level + 1));
      orientation = ((byte)(orientation ^ S2.posToOrientation(i)));
      cellId = localS2CellId;
      int k = S2.posToIJ(orientation, i);
      int j = 0;
      while (j < 2)
      {
        int m = 1 - (k >> 1 - j & 0x1);
        uv[j][m] = localR2Vector.get(j);
        double[] arrayOfDouble = uv[j];
        m = 1 - m;
        arrayOfDouble[m] = uv[j][m];
        j += 1;
      }
      i += 1;
      localS2CellId = localS2CellId.next();
    }
    return true;
  }
  
  public strictfp String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[");
    localStringBuilder.append(face);
    localStringBuilder.append(", ");
    localStringBuilder.append(level);
    localStringBuilder.append(", ");
    localStringBuilder.append(orientation);
    localStringBuilder.append(", ");
    localStringBuilder.append(cellId);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
}

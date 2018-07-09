package com.google.common.geometry;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

public final class S2Loop
  implements S2Region, Comparable<S2Loop>
{
  public static final double MAX_INTERSECTION_ERROR = 1.0E-15D;
  private static final Logger log = Logger.getLogger(S2Loop.class.getCanonicalName());
  private S2LatLngRect bound;
  private int depth;
  private int firstLogicalVertex;
  private S2EdgeIndex index;
  private final int numVertices;
  private boolean originInside;
  private Map<S2Point, Integer> vertexToIndex;
  private final S2Point[] vertices;
  
  public strictfp S2Loop(S2Cell paramS2Cell)
  {
    this(paramS2Cell, paramS2Cell.getRectBound());
  }
  
  public strictfp S2Loop(S2Cell paramS2Cell, S2LatLngRect paramS2LatLngRect)
  {
    bound = paramS2LatLngRect;
    numVertices = 4;
    vertices = new S2Point[numVertices];
    vertexToIndex = null;
    index = null;
    int i = 0;
    depth = 0;
    while (i < 4)
    {
      vertices[i] = paramS2Cell.getVertex(i);
      i += 1;
    }
    initOrigin();
    initFirstLogicalVertex();
  }
  
  public strictfp S2Loop(S2Loop paramS2Loop)
  {
    numVertices = paramS2Loop.numVertices();
    vertices = ((S2Point[])vertices.clone());
    vertexToIndex = vertexToIndex;
    index = index;
    firstLogicalVertex = firstLogicalVertex;
    bound = paramS2Loop.getRectBound();
    originInside = originInside;
    depth = paramS2Loop.depth();
  }
  
  public strictfp S2Loop(List<S2Point> paramList)
  {
    numVertices = paramList.size();
    vertices = new S2Point[numVertices];
    bound = S2LatLngRect.full();
    depth = 0;
    paramList.toArray(vertices);
    initOrigin();
    initBound();
    initFirstLogicalVertex();
  }
  
  private strictfp int checkEdgeCrossings(S2Loop paramS2Loop, S2EdgeUtil.WedgeRelation paramWedgeRelation)
  {
    S2EdgeIndex.DataEdgeIterator localDataEdgeIterator = getEdgeIterator(numVertices);
    int j = 1;
    int n;
    for (int i = 0; i < paramS2Loop.numVertices(); i = n)
    {
      Object localObject = paramS2Loop.vertex(i);
      n = i + 1;
      localObject = new S2EdgeUtil.EdgeCrosser((S2Point)localObject, paramS2Loop.vertex(n), vertex(0));
      int k = -2;
      localDataEdgeIterator.getCandidates(paramS2Loop.vertex(i), paramS2Loop.vertex(n));
      while (localDataEdgeIterator.hasNext())
      {
        int m = localDataEdgeIterator.index();
        if (k != m - 1) {
          ((S2EdgeUtil.EdgeCrosser)localObject).restartAt(vertex(m));
        }
        int i1 = m + 1;
        k = ((S2EdgeUtil.EdgeCrosser)localObject).robustCrossing(vertex(i1));
        if (k < 0)
        {
          k = j;
        }
        else
        {
          if (k > 0) {
            return -1;
          }
          k = j;
          if (vertex(i1).equals(paramS2Loop.vertex(n)))
          {
            j = Math.min(j, paramWedgeRelation.test(vertex(m), vertex(i1), vertex(m + 2), paramS2Loop.vertex(i), paramS2Loop.vertex(i + 2)));
            k = j;
            if (j < 0) {
              return j;
            }
          }
        }
        localDataEdgeIterator.next();
        j = k;
        k = m;
      }
    }
    return j;
  }
  
  private strictfp int findVertex(S2Point paramS2Point)
  {
    if (vertexToIndex == null)
    {
      vertexToIndex = new HashMap();
      int i = 1;
      while (i <= numVertices)
      {
        vertexToIndex.put(vertex(i), Integer.valueOf(i));
        i += 1;
      }
    }
    paramS2Point = (Integer)vertexToIndex.get(paramS2Point);
    if (paramS2Point == null) {
      return -1;
    }
    return paramS2Point.intValue();
  }
  
  private strictfp S2AreaCentroid getAreaCentroid(boolean paramBoolean)
  {
    if (numVertices() < 3) {
      return new S2AreaCentroid(0.0D, null);
    }
    Object localObject1 = vertex(0);
    int j = ((S2Point)localObject1).largestAbsComponent();
    int i = 1;
    j = (j + 1) % 3;
    double d1 = ((S2Point)localObject1).get(j) + 2.718281828459045E-10D;
    if (j == 0) {
      d2 = d1;
    } else {
      d2 = x;
    }
    double d3;
    if (j == 1) {
      d3 = d1;
    } else {
      d3 = y;
    }
    if (j != 2) {
      for (;;)
      {
        d1 = z;
      }
    }
    S2Point localS2Point = S2Point.normalize(new S2Point(d2, d3, d1));
    localObject1 = new S2Point(0.0D, 0.0D, 0.0D);
    d1 = 0.0D;
    while (i <= numVertices())
    {
      j = i - 1;
      d1 += S2.signedArea(localS2Point, vertex(j), vertex(i));
      Object localObject2 = localObject1;
      if (paramBoolean) {
        localObject2 = S2Point.add((S2Point)localObject1, S2.trueCentroid(localS2Point, vertex(j), vertex(i)));
      }
      i += 1;
      localObject1 = localObject2;
    }
    double d2 = d1;
    if (d1 < 0.0D) {
      d2 = d1 + 12.566370614359172D;
    }
    if (!paramBoolean) {
      localObject1 = null;
    }
    return new S2AreaCentroid(d2, (S2Point)localObject1);
  }
  
  private final strictfp S2EdgeIndex.DataEdgeIterator getEdgeIterator(int paramInt)
  {
    if (index == null) {
      index = new S2EdgeIndex()
      {
        protected strictfp S2Point edgeFrom(int paramAnonymousInt)
        {
          return vertex(paramAnonymousInt);
        }
        
        protected strictfp S2Point edgeTo(int paramAnonymousInt)
        {
          return vertex(paramAnonymousInt + 1);
        }
        
        protected strictfp int getNumEdges()
        {
          return numVertices;
        }
      };
    }
    index.predictAdditionalCalls(paramInt);
    return new S2EdgeIndex.DataEdgeIterator(index);
  }
  
  private strictfp void initBound()
  {
    Object localObject1 = new S2EdgeUtil.RectBounder();
    int i = 0;
    while (i <= numVertices())
    {
      ((S2EdgeUtil.RectBounder)localObject1).addPoint(vertex(i));
      i += 1;
    }
    Object localObject2 = ((S2EdgeUtil.RectBounder)localObject1).getBound();
    bound = S2LatLngRect.full();
    localObject1 = localObject2;
    if (contains(new S2Point(0.0D, 0.0D, 1.0D))) {
      localObject1 = new S2LatLngRect(new R1Interval(((S2LatLngRect)localObject2).lat().lo(), 1.5707963267948966D), S1Interval.full());
    }
    localObject2 = localObject1;
    if (((S2LatLngRect)localObject1).lng().isFull())
    {
      localObject2 = localObject1;
      if (contains(new S2Point(0.0D, 0.0D, -1.0D))) {
        localObject2 = new S2LatLngRect(new R1Interval(-1.5707963267948966D, ((S2LatLngRect)localObject1).lat().hi()), ((S2LatLngRect)localObject1).lng());
      }
    }
    bound = ((S2LatLngRect)localObject2);
  }
  
  private strictfp void initFirstLogicalVertex()
  {
    int i = 1;
    int k;
    for (int j = 0; i < numVertices; j = k)
    {
      k = j;
      if (vertex(i).compareTo(vertex(j)) < 0) {
        k = i;
      }
      i += 1;
    }
    firstLogicalVertex = j;
  }
  
  private strictfp void initOrigin()
  {
    if (!bound.contains(vertex(1))) {
      throw new IllegalStateException();
    }
    originInside = false;
    if (S2.orderedCCW(S2.ortho(vertex(1)), vertex(0), vertex(2), vertex(1)) != contains(vertex(1))) {
      originInside = true;
    }
  }
  
  public static strictfp boolean isValid(List<S2Point> paramList)
  {
    return new S2Loop(paramList).isValid();
  }
  
  strictfp boolean boundaryApproxEquals(S2Loop paramS2Loop, double paramDouble)
  {
    if (numVertices() != paramS2Loop.numVertices()) {
      return false;
    }
    int m = numVertices();
    int j = firstLogicalVertex;
    int i = firstLogicalVertex;
    int k = 0;
    while (k < m)
    {
      if (!S2.approxEquals(vertex(j), paramS2Loop.vertex(i), paramDouble)) {
        return false;
      }
      k += 1;
      j += 1;
      i += 1;
    }
    return true;
  }
  
  public strictfp int compareTo(S2Loop paramS2Loop)
  {
    if (numVertices() != paramS2Loop.numVertices()) {
      return numVertices() - paramS2Loop.numVertices();
    }
    int m = numVertices();
    int j = firstLogicalVertex;
    int i = firstLogicalVertex;
    int k = 0;
    while (k < m)
    {
      int n = vertex(j).compareTo(paramS2Loop.vertex(i));
      if (n != 0) {
        return n;
      }
      k += 1;
      j += 1;
      i += 1;
    }
    return 0;
  }
  
  public strictfp boolean contains(S2Cell paramS2Cell)
  {
    S2LatLngRect localS2LatLngRect = paramS2Cell.getRectBound();
    if (!bound.contains(localS2LatLngRect)) {
      return false;
    }
    return contains(new S2Loop(paramS2Cell, localS2LatLngRect));
  }
  
  public strictfp boolean contains(S2Loop paramS2Loop)
  {
    if (!bound.contains(paramS2Loop.getRectBound())) {
      return false;
    }
    if ((!contains(paramS2Loop.vertex(0))) && (findVertex(paramS2Loop.vertex(0)) < 0)) {
      return false;
    }
    if (checkEdgeCrossings(paramS2Loop, new S2EdgeUtil.WedgeContains()) <= 0) {
      return false;
    }
    return (!bound.union(paramS2Loop.getRectBound()).isFull()) || (!paramS2Loop.contains(vertex(0))) || (paramS2Loop.findVertex(vertex(0)) >= 0);
  }
  
  public strictfp boolean contains(S2Point paramS2Point)
  {
    boolean bool1 = bound.contains(paramS2Point);
    int i = 0;
    if (!bool1) {
      return false;
    }
    bool1 = originInside;
    S2Point localS2Point = S2.origin();
    S2EdgeUtil.EdgeCrosser localEdgeCrosser = new S2EdgeUtil.EdgeCrosser(localS2Point, paramS2Point, vertices[(numVertices - 1)]);
    boolean bool2;
    if (numVertices < 2000) {
      for (;;)
      {
        bool2 = bool1;
        if (i >= numVertices) {
          break;
        }
        bool1 ^= localEdgeCrosser.edgeOrVertexCrossing(vertices[i]);
        i += 1;
      }
    }
    S2EdgeIndex.DataEdgeIterator localDataEdgeIterator = getEdgeIterator(numVertices);
    i = -2;
    localDataEdgeIterator.getCandidates(localS2Point, paramS2Point);
    for (;;)
    {
      bool2 = bool1;
      if (!localDataEdgeIterator.hasNext()) {
        break;
      }
      int j = localDataEdgeIterator.index();
      if (i != j - 1) {
        localEdgeCrosser.restartAt(vertices[j]);
      }
      bool1 ^= localEdgeCrosser.edgeOrVertexCrossing(vertex(j + 1));
      localDataEdgeIterator.next();
      i = j;
    }
    return bool2;
  }
  
  public strictfp boolean containsNested(S2Loop paramS2Loop)
  {
    if (!bound.contains(paramS2Loop.getRectBound())) {
      return false;
    }
    int i = findVertex(paramS2Loop.vertex(1));
    if (i < 0) {
      return contains(paramS2Loop.vertex(1));
    }
    return new S2EdgeUtil.WedgeContains().test(vertex(i - 1), vertex(i), vertex(i + 1), paramS2Loop.vertex(0), paramS2Loop.vertex(2)) > 0;
  }
  
  public strictfp int containsOrCrosses(S2Loop paramS2Loop)
  {
    if (!bound.intersects(paramS2Loop.getRectBound())) {
      return 0;
    }
    int i = checkEdgeCrossings(paramS2Loop, new S2EdgeUtil.WedgeContainsOrCrosses());
    if (i <= 0) {
      return i;
    }
    if (!bound.contains(paramS2Loop.getRectBound())) {
      return 0;
    }
    if ((!contains(paramS2Loop.vertex(0))) && (findVertex(paramS2Loop.vertex(0)) < 0)) {
      return 0;
    }
    return 1;
  }
  
  public strictfp int depth()
  {
    return depth;
  }
  
  public strictfp double getArea()
  {
    return getAreaCentroid(false).getArea();
  }
  
  public strictfp S2AreaCentroid getAreaAndCentroid()
  {
    return getAreaCentroid(true);
  }
  
  public strictfp S2Cap getCapBound()
  {
    return bound.getCapBound();
  }
  
  public strictfp S2Point getCentroid()
  {
    return getAreaCentroid(true).getCentroid();
  }
  
  public strictfp S1Angle getDistance(S2Point paramS2Point)
  {
    S2Point localS2Point1 = S2Point.normalize(paramS2Point);
    paramS2Point = S1Angle.radians(3.141592653589793D);
    int i = 0;
    while (i < numVertices())
    {
      S2Point localS2Point2 = vertex(i);
      i += 1;
      paramS2Point = S1Angle.min(paramS2Point, S2EdgeUtil.getDistance(localS2Point1, localS2Point2, vertex(i)));
    }
    return paramS2Point;
  }
  
  public strictfp S2LatLngRect getRectBound()
  {
    return bound;
  }
  
  public strictfp boolean intersects(S2Loop paramS2Loop)
  {
    if (!bound.intersects(paramS2Loop.getRectBound())) {
      return false;
    }
    if (paramS2Loop.getRectBound().lng().getLength() > bound.lng().getLength()) {
      return paramS2Loop.intersects(this);
    }
    if ((contains(paramS2Loop.vertex(0))) && (findVertex(paramS2Loop.vertex(0)) < 0)) {
      return true;
    }
    if (checkEdgeCrossings(paramS2Loop, new S2EdgeUtil.WedgeIntersects()) < 0) {
      return true;
    }
    return (paramS2Loop.getRectBound().contains(bound)) && (paramS2Loop.contains(vertex(0))) && (paramS2Loop.findVertex(vertex(0)) < 0);
  }
  
  public strictfp void invert()
  {
    int j = numVertices() - 1;
    int i = (j - 1) / 2;
    while (i >= 0)
    {
      S2Point localS2Point = vertices[i];
      S2Point[] arrayOfS2Point1 = vertices;
      S2Point[] arrayOfS2Point2 = vertices;
      int k = j - i;
      arrayOfS2Point1[i] = arrayOfS2Point2[k];
      vertices[k] = localS2Point;
      i -= 1;
    }
    vertexToIndex = null;
    index = null;
    originInside ^= true;
    if ((bound.lat().lo() > -1.5707963267948966D) && (bound.lat().hi() < 1.5707963267948966D)) {
      bound = S2LatLngRect.full();
    } else {
      initBound();
    }
    initFirstLogicalVertex();
  }
  
  public strictfp boolean isHole()
  {
    return (depth & 0x1) != 0;
  }
  
  public strictfp boolean isNormalized()
  {
    return getArea() <= 6.283185307179596D;
  }
  
  public strictfp boolean isValid()
  {
    if (numVertices < 3)
    {
      log.info("Degenerate loop");
      return false;
    }
    int i = 0;
    while (i < numVertices)
    {
      if (!S2.isUnitLength(vertex(i)))
      {
        localObject1 = log;
        localObject2 = new StringBuilder();
        ((StringBuilder)localObject2).append("Vertex ");
        ((StringBuilder)localObject2).append(i);
        ((StringBuilder)localObject2).append(" is not unit length");
        ((Logger)localObject1).info(((StringBuilder)localObject2).toString());
        return false;
      }
      i += 1;
    }
    Object localObject2 = new HashMap();
    i = 0;
    while (i < numVertices)
    {
      localObject1 = (Integer)((HashMap)localObject2).put(vertex(i), Integer.valueOf(i));
      if (localObject1 != null)
      {
        localObject2 = log;
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Duplicate vertices: ");
        localStringBuilder.append(localObject1);
        localStringBuilder.append(" and ");
        localStringBuilder.append(i);
        ((Logger)localObject2).info(localStringBuilder.toString());
        return false;
      }
      i += 1;
    }
    Object localObject1 = getEdgeIterator(numVertices);
    int n;
    for (i = 0; i < numVertices; i = n)
    {
      n = i + 1;
      int i2 = n % numVertices;
      localObject2 = new S2EdgeUtil.EdgeCrosser(vertex(i), vertex(i2), vertex(0));
      int j = -2;
      ((S2EdgeIndex.DataEdgeIterator)localObject1).getCandidates(vertex(i), vertex(i2));
      while (((S2EdgeIndex.DataEdgeIterator)localObject1).hasNext())
      {
        int i3 = ((S2EdgeIndex.DataEdgeIterator)localObject1).index();
        int i1 = (i3 + 1) % numVertices;
        int k = j;
        if (i != i1)
        {
          k = j;
          if (i2 != i3)
          {
            k = j;
            if (i != i3)
            {
              double d = S2.angle(vertex(i), vertex(i2), vertex(i3));
              if ((!S2.approxEquals(d, 0.0D, 1.0E-15D)) && (!S2.approxEquals(d, 3.141592653589793D, 1.0E-15D))) {
                k = 0;
              } else {
                k = 1;
              }
              d = S2.angle(vertex(i), vertex(i2), vertex(i1));
              int m;
              if ((!S2.approxEquals(d, 0.0D, 1.0E-15D)) && (!S2.approxEquals(d, 3.141592653589793D, 1.0E-15D))) {
                m = 0;
              } else {
                m = 1;
              }
              if ((k != 0) && (m != 0))
              {
                k = j;
              }
              else
              {
                if (j != i3) {
                  ((S2EdgeUtil.EdgeCrosser)localObject2).restartAt(vertex(i3));
                }
                if (((S2EdgeUtil.EdgeCrosser)localObject2).robustCrossing(vertex(i1)) > 0) {
                  j = 1;
                } else {
                  j = 0;
                }
                if (j != 0)
                {
                  localObject1 = log;
                  localObject2 = new StringBuilder();
                  ((StringBuilder)localObject2).append("Edges ");
                  ((StringBuilder)localObject2).append(i);
                  ((StringBuilder)localObject2).append(" and ");
                  ((StringBuilder)localObject2).append(i3);
                  ((StringBuilder)localObject2).append(" cross");
                  ((Logger)localObject1).info(((StringBuilder)localObject2).toString());
                  log.info(String.format("Edge locations in degrees: %s-%s and %s-%s", new Object[] { new S2LatLng(vertex(i)).toStringDegrees(), new S2LatLng(vertex(i2)).toStringDegrees(), new S2LatLng(vertex(i3)).toStringDegrees(), new S2LatLng(vertex(i1)).toStringDegrees() }));
                  return false;
                }
                k = i1;
              }
            }
          }
        }
        ((S2EdgeIndex.DataEdgeIterator)localObject1).next();
        j = k;
      }
    }
    return true;
  }
  
  public strictfp boolean mayIntersect(S2Cell paramS2Cell)
  {
    S2LatLngRect localS2LatLngRect = paramS2Cell.getRectBound();
    if (!bound.intersects(localS2LatLngRect)) {
      return false;
    }
    return new S2Loop(paramS2Cell, localS2LatLngRect).intersects(this);
  }
  
  public strictfp void normalize()
  {
    if (!isNormalized()) {
      invert();
    }
  }
  
  public strictfp int numVertices()
  {
    return numVertices;
  }
  
  public strictfp void setDepth(int paramInt)
  {
    depth = paramInt;
  }
  
  public strictfp int sign()
  {
    if (isHole()) {
      return -1;
    }
    return 1;
  }
  
  public strictfp String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("S2Loop, ");
    S2Point[] arrayOfS2Point = vertices;
    int i = 0;
    localStringBuilder.append(arrayOfS2Point.length);
    localStringBuilder.append(" points. [");
    arrayOfS2Point = vertices;
    int j = arrayOfS2Point.length;
    while (i < j)
    {
      localStringBuilder.append(arrayOfS2Point[i].toString());
      localStringBuilder.append(" ");
      i += 1;
    }
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public strictfp S2Point vertex(int paramInt)
  {
    try
    {
      Object localObject = vertices;
      int i = paramInt;
      if (paramInt >= vertices.length) {
        i = paramInt - vertices.length;
      }
      localObject = localObject[i];
      return localObject;
    }
    catch (ArrayIndexOutOfBoundsException localArrayIndexOutOfBoundsException)
    {
      for (;;) {}
    }
    throw new IllegalStateException("Invalid vertex index");
  }
}

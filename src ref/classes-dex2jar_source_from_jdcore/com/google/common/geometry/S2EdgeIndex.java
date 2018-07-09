package com.google.common.geometry;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class S2EdgeIndex
{
  private static final double MAX_DET_ERROR = 1.0E-14D;
  private static final double THICKENING = 0.01D;
  private long[] cells;
  private int[] edges;
  private boolean indexComputed;
  private int minimumS2LevelUsed;
  private int queryCount;
  
  public strictfp S2EdgeIndex() {}
  
  private strictfp int binarySearch(long paramLong, int paramInt)
  {
    long[] arrayOfLong = cells;
    int i = 0;
    int j = arrayOfLong.length - 1;
    while (i <= j)
    {
      int k = i + j >>> 1;
      int m = compare(cells[k], edges[k], paramLong, paramInt);
      if (m < 0) {
        i = k + 1;
      } else if (m > 0) {
        j = k - 1;
      } else {
        return k;
      }
    }
    return -(i + 1);
  }
  
  private static final strictfp int compare(long paramLong1, int paramInt1, long paramLong2, int paramInt2)
  {
    if (paramLong1 < paramLong2) {
      return -1;
    }
    if (paramLong1 > paramLong2) {
      return 1;
    }
    if (paramInt1 < paramInt2) {
      return -1;
    }
    if (paramInt1 > paramInt2) {
      return 1;
    }
    return 0;
  }
  
  private static strictfp S2CellId containingCell(S2Point paramS2Point1, S2Point paramS2Point2)
  {
    S2CellId localS2CellId1 = S2CellId.fromPoint(paramS2Point1);
    S2CellId localS2CellId2 = S2CellId.fromPoint(paramS2Point2);
    paramS2Point1 = localS2CellId1;
    paramS2Point2 = localS2CellId2;
    if (localS2CellId1.face() != localS2CellId2.face()) {
      return S2CellId.sentinel();
    }
    while (!paramS2Point1.equals(paramS2Point2))
    {
      paramS2Point1 = paramS2Point1.parent();
      paramS2Point2 = paramS2Point2.parent();
    }
    return paramS2Point1;
  }
  
  private static strictfp S2CellId containingCell(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4)
  {
    S2CellId localS2CellId1 = S2CellId.fromPoint(paramS2Point1);
    paramS2Point2 = S2CellId.fromPoint(paramS2Point2);
    paramS2Point3 = S2CellId.fromPoint(paramS2Point3);
    S2CellId localS2CellId2 = S2CellId.fromPoint(paramS2Point4);
    if ((localS2CellId1.face() == paramS2Point2.face()) && (localS2CellId1.face() == paramS2Point3.face()))
    {
      paramS2Point1 = localS2CellId1;
      paramS2Point4 = localS2CellId2;
      if (localS2CellId1.face() == localS2CellId2.face()) {
        for (;;)
        {
          if ((paramS2Point1.equals(paramS2Point2)) && (paramS2Point1.equals(paramS2Point3)) && (paramS2Point1.equals(paramS2Point4))) {
            return paramS2Point1;
          }
          paramS2Point1 = paramS2Point1.parent();
          paramS2Point2 = paramS2Point2.parent();
          paramS2Point3 = paramS2Point3.parent();
          paramS2Point4 = paramS2Point4.parent();
        }
      }
    }
    return S2CellId.sentinel();
  }
  
  private static strictfp boolean edgeIntersectsCellBoundary(S2Point paramS2Point1, S2Point paramS2Point2, S2Cell paramS2Cell)
  {
    S2Point[] arrayOfS2Point = new S2Point[4];
    int i = 0;
    while (i < 4)
    {
      arrayOfS2Point[i] = paramS2Cell.getVertex(i);
      i += 1;
    }
    i = 0;
    while (i < 4)
    {
      paramS2Cell = arrayOfS2Point[i];
      int j = i + 1;
      i = j;
      if (lenientCrossing(paramS2Point1, paramS2Point2, paramS2Cell, arrayOfS2Point[(j % 4)])) {
        return true;
      }
    }
    return false;
  }
  
  private static strictfp int getCovering(S2Point paramS2Point1, S2Point paramS2Point2, boolean paramBoolean, ArrayList<S2CellId> paramArrayList)
  {
    paramArrayList.clear();
    double d = paramS2Point1.angle(paramS2Point2);
    int i = S2Projections.MIN_WIDTH.getMaxLevel(1.02D * d);
    Object localObject;
    if (!paramBoolean)
    {
      localObject = containingCell(paramS2Point1, paramS2Point2);
    }
    else if (i == 30)
    {
      localObject = new S2CellId(65520L).parent(3);
    }
    else
    {
      S2Point localS2Point2 = S2Point.mul(S2Point.minus(paramS2Point2, paramS2Point1), 0.01D);
      localObject = S2Point.mul(S2Point.normalize(S2Point.crossProd(localS2Point2, paramS2Point1)), d * 0.01D);
      S2Point localS2Point1 = S2Point.minus(paramS2Point1, localS2Point2);
      localS2Point2 = S2Point.add(paramS2Point2, localS2Point2);
      localObject = containingCell(S2Point.minus(localS2Point1, (S2Point)localObject), S2Point.add(localS2Point1, (S2Point)localObject), S2Point.minus(localS2Point2, (S2Point)localObject), S2Point.add(localS2Point2, (S2Point)localObject));
    }
    if ((!((S2CellId)localObject).equals(S2CellId.sentinel())) && (((S2CellId)localObject).level() >= i - 2))
    {
      paramArrayList.add(localObject);
      return ((S2CellId)localObject).level();
    }
    if (i == 0)
    {
      for (paramS2Point1 = S2CellId.begin(0); !paramS2Point1.equals(S2CellId.end(0)); paramS2Point1 = paramS2Point1.next()) {
        paramArrayList.add(paramS2Point1);
      }
      return 0;
    }
    paramS2Point1 = S2Point.normalize(S2Point.div(S2Point.add(paramS2Point1, paramS2Point2), 2.0D));
    i = Math.min(i, 29);
    S2CellId.fromPoint(paramS2Point1).getVertexNeighbors(i, paramArrayList);
    return i;
  }
  
  private strictfp int[] getEdges(long paramLong1, long paramLong2)
  {
    long l2 = paramLong1;
    long l1 = paramLong2;
    if (paramLong1 > paramLong2)
    {
      l1 = paramLong1;
      l2 = paramLong2;
    }
    return new int[] { -1 - binarySearch(l2, Integer.MIN_VALUE), -1 - binarySearch(l1, Integer.MAX_VALUE) };
  }
  
  private strictfp void getEdgesInChildrenCells(S2Point paramS2Point1, S2Point paramS2Point2, List<S2CellId> paramList, Set<Integer> paramSet)
  {
    Object localObject1 = null;
    while (!paramList.isEmpty())
    {
      S2CellId localS2CellId = (S2CellId)paramList.remove(paramList.size() - 1);
      Object localObject2 = getEdges(localS2CellId.rangeMin().id(), localS2CellId.rangeMax().id());
      int i = localObject2[1];
      int j = 0;
      if (i - localObject2[0] <= 16)
      {
        i = localObject2[0];
        while (i < localObject2[1])
        {
          paramSet.add(Integer.valueOf(edges[i]));
          i += 1;
        }
      }
      else
      {
        localObject2 = getEdges(localS2CellId.id(), localS2CellId.id());
        i = localObject2[0];
        while (i < localObject2[1])
        {
          paramSet.add(Integer.valueOf(edges[i]));
          i += 1;
        }
        localObject2 = localObject1;
        if (localObject1 == null)
        {
          localObject2 = new S2Cell[4];
          i = 0;
          while (i < 4)
          {
            localObject2[i] = new S2Cell();
            i += 1;
          }
        }
        new S2Cell(localS2CellId).subdivide((S2Cell[])localObject2);
        int k = localObject2.length;
        i = j;
        for (;;)
        {
          localObject1 = localObject2;
          if (i >= k) {
            break;
          }
          localObject1 = localObject2[i];
          if (edgeIntersectsCellBoundary(paramS2Point1, paramS2Point2, localObject1)) {
            paramList.add(localObject1.id());
          }
          i += 1;
        }
      }
    }
  }
  
  private strictfp void getEdgesInParentCells(List<S2CellId> paramList, Set<Integer> paramSet)
  {
    Object localObject = new HashSet();
    paramList = paramList.iterator();
    int i;
    while (paramList.hasNext())
    {
      S2CellId localS2CellId = (S2CellId)paramList.next();
      i = localS2CellId.level() - 1;
      while ((i >= minimumS2LevelUsed) && (((Set)localObject).add(localS2CellId.parent(i)))) {
        i -= 1;
      }
    }
    paramList = ((Set)localObject).iterator();
    while (paramList.hasNext())
    {
      localObject = (S2CellId)paramList.next();
      localObject = getEdges(((S2CellId)localObject).id(), ((S2CellId)localObject).id());
      i = localObject[0];
      while (i < localObject[1])
      {
        paramSet.add(Integer.valueOf(edges[i]));
        i += 1;
      }
    }
  }
  
  private static strictfp boolean lenientCrossing(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4)
  {
    double d1 = S2Point.crossProd(paramS2Point1, paramS2Point3).dotProd(paramS2Point2);
    double d2 = S2Point.crossProd(paramS2Point2, paramS2Point4).dotProd(paramS2Point1);
    if (Math.abs(d1) >= 1.0E-14D)
    {
      if (Math.abs(d2) < 1.0E-14D) {
        return true;
      }
      boolean bool2 = false;
      if (d2 * d1 < 0.0D) {
        return false;
      }
      d2 = S2Point.crossProd(paramS2Point3, paramS2Point2).dotProd(paramS2Point4);
      double d3 = S2Point.crossProd(paramS2Point3, paramS2Point1).dotProd(paramS2Point3);
      if (Math.abs(d2) >= 1.0E-14D)
      {
        if (Math.abs(d3) < 1.0E-14D) {
          return true;
        }
        boolean bool1 = bool2;
        if (d2 * d1 >= 0.0D)
        {
          bool1 = bool2;
          if (d1 * d3 >= 0.0D) {
            bool1 = true;
          }
        }
        return bool1;
      }
      return true;
    }
    return true;
  }
  
  private strictfp void sortIndex()
  {
    Object localObject = cells;
    int j = 0;
    localObject = new Integer[localObject.length];
    int i = 0;
    while (i < localObject.length)
    {
      localObject[i] = Integer.valueOf(i);
      i += 1;
    }
    Arrays.sort((Object[])localObject, new Comparator()
    {
      public strictfp int compare(Integer paramAnonymousInteger1, Integer paramAnonymousInteger2)
      {
        return S2EdgeIndex.compare(cells[paramAnonymousInteger1.intValue()], edges[paramAnonymousInteger1.intValue()], cells[paramAnonymousInteger2.intValue()], edges[paramAnonymousInteger2.intValue()]);
      }
    });
    long[] arrayOfLong = new long[cells.length];
    int[] arrayOfInt = new int[edges.length];
    i = j;
    while (i < localObject.length)
    {
      arrayOfLong[i] = cells[localObject[i].intValue()];
      arrayOfInt[i] = edges[localObject[i].intValue()];
      i += 1;
    }
    cells = arrayOfLong;
    edges = arrayOfInt;
  }
  
  public final strictfp void computeIndex()
  {
    if (indexComputed) {
      return;
    }
    ArrayList localArrayList1 = new ArrayList();
    ArrayList localArrayList2 = new ArrayList();
    int j = 0;
    int i = 0;
    while (i < getNumEdges())
    {
      Object localObject = edgeFrom(i);
      S2Point localS2Point = edgeTo(i);
      ArrayList localArrayList3 = new ArrayList();
      int k = getCovering((S2Point)localObject, localS2Point, true, localArrayList3);
      minimumS2LevelUsed = Math.min(minimumS2LevelUsed, k);
      localObject = localArrayList3.iterator();
      while (((Iterator)localObject).hasNext())
      {
        localArrayList1.add(Long.valueOf(((S2CellId)((Iterator)localObject).next()).id()));
        localArrayList2.add(Integer.valueOf(i));
      }
      i += 1;
    }
    cells = new long[localArrayList1.size()];
    edges = new int[localArrayList2.size()];
    i = j;
    while (i < cells.length)
    {
      cells[i] = ((Long)localArrayList1.get(i)).longValue();
      edges[i] = ((Integer)localArrayList2.get(i)).intValue();
      i += 1;
    }
    sortIndex();
    indexComputed = true;
  }
  
  protected abstract S2Point edgeFrom(int paramInt);
  
  protected abstract S2Point edgeTo(int paramInt);
  
  protected strictfp void findCandidateCrossings(S2Point paramS2Point1, S2Point paramS2Point2, List<Integer> paramList)
  {
    if (!indexComputed) {
      throw new IllegalStateException();
    }
    ArrayList localArrayList = new ArrayList();
    getCovering(paramS2Point1, paramS2Point2, false, localArrayList);
    HashSet localHashSet = new HashSet();
    getEdgesInParentCells(localArrayList, localHashSet);
    getEdgesInChildrenCells(paramS2Point1, paramS2Point2, localArrayList, localHashSet);
    paramList.clear();
    paramList.addAll(localHashSet);
  }
  
  protected abstract int getNumEdges();
  
  protected final strictfp void incrementQueryCount()
  {
    queryCount += 1;
  }
  
  public final strictfp boolean isIndexComputed()
  {
    return indexComputed;
  }
  
  public final strictfp void predictAdditionalCalls(int paramInt)
  {
    if (indexComputed) {
      return;
    }
    if ((getNumEdges() > 100) && (queryCount + paramInt > 30)) {
      computeIndex();
    }
  }
  
  public strictfp void reset()
  {
    minimumS2LevelUsed = 30;
    indexComputed = false;
    queryCount = 0;
    cells = null;
    edges = null;
  }
  
  public static class DataEdgeIterator
  {
    ArrayList<Integer> candidates;
    private int currentIndex;
    private int currentIndexInCandidates;
    private final S2EdgeIndex edgeIndex;
    private boolean isBruteForce;
    private int numEdges;
    
    public strictfp DataEdgeIterator(S2EdgeIndex paramS2EdgeIndex)
    {
      edgeIndex = paramS2EdgeIndex;
      candidates = new ArrayList();
    }
    
    public strictfp void getCandidates(S2Point paramS2Point1, S2Point paramS2Point2)
    {
      edgeIndex.predictAdditionalCalls(1);
      isBruteForce = (edgeIndex.isIndexComputed() ^ true);
      if (isBruteForce)
      {
        edgeIndex.incrementQueryCount();
        currentIndex = 0;
        numEdges = edgeIndex.getNumEdges();
        return;
      }
      candidates.clear();
      edgeIndex.findCandidateCrossings(paramS2Point1, paramS2Point2, candidates);
      currentIndexInCandidates = 0;
      if (!candidates.isEmpty()) {
        currentIndex = ((Integer)candidates.get(0)).intValue();
      }
    }
    
    public strictfp boolean hasNext()
    {
      boolean bool3 = isBruteForce;
      boolean bool2 = false;
      boolean bool1 = false;
      if (bool3)
      {
        if (currentIndex < numEdges) {
          bool1 = true;
        }
        return bool1;
      }
      bool1 = bool2;
      if (currentIndexInCandidates < candidates.size()) {
        bool1 = true;
      }
      return bool1;
    }
    
    public strictfp int index()
    {
      if (!hasNext()) {
        throw new IllegalStateException();
      }
      return currentIndex;
    }
    
    public strictfp void next()
    {
      if (!hasNext()) {
        throw new IllegalStateException();
      }
      if (isBruteForce)
      {
        currentIndex += 1;
        return;
      }
      currentIndexInCandidates += 1;
      if (currentIndexInCandidates < candidates.size()) {
        currentIndex = ((Integer)candidates.get(currentIndexInCandidates)).intValue();
      }
    }
  }
}

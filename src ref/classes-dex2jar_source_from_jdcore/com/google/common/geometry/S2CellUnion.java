package com.google.common.geometry;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class S2CellUnion
  implements S2Region, Iterable<S2CellId>
{
  private ArrayList<S2CellId> cellIds = new ArrayList();
  
  public strictfp S2CellUnion() {}
  
  private static strictfp int indexedBinarySearch(List<S2CellId> paramList, S2CellId paramS2CellId, int paramInt)
  {
    int i = paramList.size() - 1;
    while (paramInt <= i)
    {
      int j = paramInt + i >> 1;
      int k = ((S2CellId)paramList.get(j)).compareTo(paramS2CellId);
      if (k < 0) {
        paramInt = j + 1;
      } else if (k > 0) {
        i = j - 1;
      } else {
        return j;
      }
    }
    return paramInt;
  }
  
  public strictfp double approxArea()
  {
    Iterator localIterator = cellIds.iterator();
    for (double d = 0.0D; localIterator.hasNext(); d += new S2Cell((S2CellId)localIterator.next()).approxArea()) {}
    return d;
  }
  
  public strictfp double averageBasedArea()
  {
    return S2Cell.averageArea(30) * leafCellsCovered();
  }
  
  public strictfp S2CellId cellId(int paramInt)
  {
    return (S2CellId)cellIds.get(paramInt);
  }
  
  public strictfp ArrayList<S2CellId> cellIds()
  {
    return cellIds;
  }
  
  public strictfp S2Region clone()
  {
    S2CellUnion localS2CellUnion = new S2CellUnion();
    ArrayList localArrayList = new ArrayList(cellIds.size());
    System.arraycopy(cellIds, 0, localArrayList, 0, cellIds.size());
    localS2CellUnion.initRawCellIds(localArrayList);
    return localS2CellUnion;
  }
  
  public strictfp boolean contains(S2Cell paramS2Cell)
  {
    return contains(paramS2Cell.id());
  }
  
  public strictfp boolean contains(S2CellId paramS2CellId)
  {
    int j = Collections.binarySearch(cellIds, paramS2CellId);
    int i = j;
    if (j < 0) {
      i = -j - 1;
    }
    if ((i < cellIds.size()) && (((S2CellId)cellIds.get(i)).rangeMin().lessOrEquals(paramS2CellId))) {
      return true;
    }
    return (i != 0) && (((S2CellId)cellIds.get(i - 1)).rangeMax().greaterOrEquals(paramS2CellId));
  }
  
  public strictfp boolean contains(S2CellUnion paramS2CellUnion)
  {
    paramS2CellUnion = paramS2CellUnion.iterator();
    while (paramS2CellUnion.hasNext()) {
      if (!contains((S2CellId)paramS2CellUnion.next())) {
        return false;
      }
    }
    return true;
  }
  
  public strictfp boolean contains(S2Point paramS2Point)
  {
    return contains(S2CellId.fromPoint(paramS2Point));
  }
  
  public strictfp void denormalize(int paramInt1, int paramInt2, ArrayList<S2CellId> paramArrayList)
  {
    paramArrayList.clear();
    paramArrayList.ensureCapacity(size());
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      S2CellId localS2CellId1 = (S2CellId)localIterator.next();
      int k = localS2CellId1.level();
      int j = Math.max(paramInt1, k);
      int i = j;
      if (paramInt2 > 1) {
        i = Math.min(30, j + (30 - (j - paramInt1)) % paramInt2);
      }
      if (i == k)
      {
        paramArrayList.add(localS2CellId1);
      }
      else
      {
        S2CellId localS2CellId2 = localS2CellId1.childEnd(i);
        for (localS2CellId1 = localS2CellId1.childBegin(i); !localS2CellId1.equals(localS2CellId2); localS2CellId1 = localS2CellId1.next()) {
          paramArrayList.add(localS2CellId1);
        }
      }
    }
  }
  
  public strictfp boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof S2CellUnion)) {
      return false;
    }
    paramObject = (S2CellUnion)paramObject;
    return cellIds.equals(cellIds);
  }
  
  public strictfp double exactArea()
  {
    Iterator localIterator = cellIds.iterator();
    for (double d = 0.0D; localIterator.hasNext(); d += new S2Cell((S2CellId)localIterator.next()).exactArea()) {}
    return d;
  }
  
  public strictfp void expand(int paramInt)
  {
    ArrayList localArrayList = new ArrayList();
    long l = S2CellId.lowestOnBitForLevel(paramInt);
    int i = size() - 1;
    int j;
    do
    {
      S2CellId localS2CellId2 = cellId(i);
      j = i;
      S2CellId localS2CellId1 = localS2CellId2;
      if (localS2CellId2.lowestOnBit() < l)
      {
        localS2CellId2 = localS2CellId2.parent(paramInt);
        for (;;)
        {
          j = i;
          localS2CellId1 = localS2CellId2;
          if (i <= 0) {
            break;
          }
          j = i;
          localS2CellId1 = localS2CellId2;
          if (!localS2CellId2.contains(cellId(i - 1))) {
            break;
          }
          i -= 1;
        }
      }
      localArrayList.add(localS2CellId1);
      localS2CellId1.getAllNeighbors(paramInt, localArrayList);
      j -= 1;
      i = j;
    } while (j >= 0);
    initSwap(localArrayList);
  }
  
  public strictfp void expand(S1Angle paramS1Angle, int paramInt)
  {
    Iterator localIterator = iterator();
    for (int i = 30; localIterator.hasNext(); i = Math.min(i, ((S2CellId)localIterator.next()).level())) {}
    int j = S2Projections.MIN_WIDTH.getMaxLevel(paramS1Angle.radians());
    if ((j == 0) && (paramS1Angle.radians() > S2Projections.MIN_WIDTH.getValue(0))) {
      expand(0);
    }
    expand(Math.min(i + paramInt, j));
  }
  
  public strictfp S2Cap getCapBound()
  {
    if (cellIds.isEmpty()) {
      return S2Cap.empty();
    }
    Object localObject = new S2Point(0.0D, 0.0D, 0.0D);
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      S2CellId localS2CellId = (S2CellId)localIterator.next();
      double d = S2Cell.averageArea(localS2CellId.level());
      localObject = S2Point.add((S2Point)localObject, S2Point.mul(localS2CellId.toPoint(), d));
    }
    if (((S2Point)localObject).equals(new S2Point(0.0D, 0.0D, 0.0D))) {
      localObject = new S2Point(1.0D, 0.0D, 0.0D);
    } else {
      localObject = S2Point.normalize((S2Point)localObject);
    }
    localObject = S2Cap.fromAxisHeight((S2Point)localObject, 0.0D);
    localIterator = iterator();
    while (localIterator.hasNext()) {
      localObject = ((S2Cap)localObject).addCap(new S2Cell((S2CellId)localIterator.next()).getCapBound());
    }
    return localObject;
  }
  
  public strictfp void getIntersection(S2CellUnion paramS2CellUnion, S2CellId paramS2CellId)
  {
    cellIds.clear();
    if (paramS2CellUnion.contains(paramS2CellId))
    {
      cellIds.add(paramS2CellId);
      return;
    }
    int j = Collections.binarySearch(cellIds, paramS2CellId.rangeMin());
    int i = j;
    if (j < 0) {
      i = -j - 1;
    }
    paramS2CellId = paramS2CellId.rangeMax();
    j = cellIds.size();
    while ((i < j) && (((S2CellId)cellIds.get(i)).lessOrEquals(paramS2CellId)))
    {
      cellIds.add(cellIds.get(i));
      i += 1;
    }
  }
  
  public strictfp void getIntersection(S2CellUnion paramS2CellUnion1, S2CellUnion paramS2CellUnion2)
  {
    cellIds.clear();
    int j = 0;
    int i = 0;
    while ((j < cellIds.size()) && (i < cellIds.size()))
    {
      Object localObject = paramS2CellUnion1.cellId(j).rangeMin();
      S2CellId localS2CellId = paramS2CellUnion2.cellId(i).rangeMin();
      int k;
      if (((S2CellId)localObject).greaterThan(localS2CellId))
      {
        if (paramS2CellUnion1.cellId(j).lessOrEquals(paramS2CellUnion2.cellId(i).rangeMax()))
        {
          localObject = cellIds;
          k = j + 1;
          ((ArrayList)localObject).add(paramS2CellUnion1.cellId(j));
          j = k;
        }
        else
        {
          label114:
          k = indexedBinarySearch(cellIds, (S2CellId)localObject, i + 1);
          i = k;
          if (paramS2CellUnion1.cellId(j).lessOrEquals(paramS2CellUnion2.cellId(k - 1).rangeMax())) {
            i = k - 1;
          }
        }
      }
      else
      {
        if (localS2CellId.greaterThan((S2CellId)localObject)) {
          if (paramS2CellUnion2.cellId(i).lessOrEquals(paramS2CellUnion1.cellId(j).rangeMax()))
          {
            localObject = cellIds;
            k = i + 1;
            ((ArrayList)localObject).add(paramS2CellUnion2.cellId(i));
          }
        }
        for (i = k;; i = k)
        {
          break;
          k = indexedBinarySearch(cellIds, localS2CellId, j + 1);
          j = k;
          if (!paramS2CellUnion2.cellId(i).lessOrEquals(paramS2CellUnion1.cellId(k - 1).rangeMax())) {
            break;
          }
          j = k - 1;
          break;
          if (paramS2CellUnion1.cellId(j).lessThan(paramS2CellUnion2.cellId(i)))
          {
            localObject = cellIds;
            k = j + 1;
            ((ArrayList)localObject).add(paramS2CellUnion1.cellId(j));
            j = k;
            break label114;
          }
          localObject = cellIds;
          k = i + 1;
          ((ArrayList)localObject).add(paramS2CellUnion2.cellId(i));
        }
      }
    }
  }
  
  public strictfp S2LatLngRect getRectBound()
  {
    S2LatLngRect localS2LatLngRect = S2LatLngRect.empty();
    Iterator localIterator = iterator();
    while (localIterator.hasNext()) {
      localS2LatLngRect = localS2LatLngRect.union(new S2Cell((S2CellId)localIterator.next()).getRectBound());
    }
    return localS2LatLngRect;
  }
  
  public strictfp void getUnion(S2CellUnion paramS2CellUnion1, S2CellUnion paramS2CellUnion2)
  {
    cellIds.clear();
    cellIds.ensureCapacity(paramS2CellUnion1.size() + paramS2CellUnion2.size());
    cellIds.addAll(cellIds);
    cellIds.addAll(cellIds);
    normalize();
  }
  
  public strictfp int hashCode()
  {
    Iterator localIterator = iterator();
    for (int i = 17; localIterator.hasNext(); i = ((S2CellId)localIterator.next()).hashCode() + 37 * i) {}
    return i;
  }
  
  public strictfp void initFromCellIds(ArrayList<S2CellId> paramArrayList)
  {
    initRawCellIds(paramArrayList);
    normalize();
  }
  
  public strictfp void initFromIds(ArrayList<Long> paramArrayList)
  {
    initRawIds(paramArrayList);
    normalize();
  }
  
  public strictfp void initRawCellIds(ArrayList<S2CellId> paramArrayList)
  {
    cellIds = paramArrayList;
  }
  
  public strictfp void initRawIds(ArrayList<Long> paramArrayList)
  {
    cellIds = new ArrayList(paramArrayList.size());
    paramArrayList = paramArrayList.iterator();
    while (paramArrayList.hasNext())
    {
      Long localLong = (Long)paramArrayList.next();
      cellIds.add(new S2CellId(localLong.longValue()));
    }
  }
  
  public strictfp void initRawSwap(ArrayList<S2CellId> paramArrayList)
  {
    cellIds = new ArrayList(paramArrayList);
    paramArrayList.clear();
  }
  
  public strictfp void initSwap(ArrayList<S2CellId> paramArrayList)
  {
    initRawSwap(paramArrayList);
    normalize();
  }
  
  public strictfp boolean intersects(S2CellId paramS2CellId)
  {
    int j = Collections.binarySearch(cellIds, paramS2CellId);
    int i = j;
    if (j < 0) {
      i = -j - 1;
    }
    if ((i < cellIds.size()) && (((S2CellId)cellIds.get(i)).rangeMin().lessOrEquals(paramS2CellId.rangeMax()))) {
      return true;
    }
    return (i != 0) && (((S2CellId)cellIds.get(i - 1)).rangeMax().greaterOrEquals(paramS2CellId.rangeMin()));
  }
  
  public strictfp boolean intersects(S2CellUnion paramS2CellUnion)
  {
    paramS2CellUnion = paramS2CellUnion.iterator();
    while (paramS2CellUnion.hasNext()) {
      if (intersects((S2CellId)paramS2CellUnion.next())) {
        return true;
      }
    }
    return false;
  }
  
  public strictfp Iterator<S2CellId> iterator()
  {
    return cellIds.iterator();
  }
  
  public strictfp long leafCellsCovered()
  {
    Iterator localIterator = cellIds.iterator();
    for (long l = 0L; localIterator.hasNext(); l += (1L << (30 - ((S2CellId)localIterator.next()).level() << 1))) {}
    return l;
  }
  
  public strictfp boolean mayIntersect(S2Cell paramS2Cell)
  {
    return intersects(paramS2Cell.id());
  }
  
  public strictfp boolean normalize()
  {
    ArrayList localArrayList = new ArrayList(cellIds.size());
    localArrayList.ensureCapacity(cellIds.size());
    Collections.sort(cellIds);
    Iterator localIterator = iterator();
    while (localIterator.hasNext())
    {
      S2CellId localS2CellId2 = (S2CellId)localIterator.next();
      int i = localArrayList.size();
      if ((localArrayList.isEmpty()) || (!((S2CellId)localArrayList.get(i - 1)).contains(localS2CellId2)))
      {
        S2CellId localS2CellId1;
        for (;;)
        {
          localS2CellId1 = localS2CellId2;
          if (localArrayList.isEmpty()) {
            break;
          }
          localS2CellId1 = localS2CellId2;
          if (!localS2CellId2.contains((S2CellId)localArrayList.get(localArrayList.size() - 1))) {
            break;
          }
          localArrayList.remove(localArrayList.size() - 1);
        }
        while (localArrayList.size() >= 3)
        {
          int k = localArrayList.size();
          i = k - 3;
          long l1 = ((S2CellId)localArrayList.get(i)).id();
          int j = k - 2;
          long l2 = ((S2CellId)localArrayList.get(j)).id();
          k -= 1;
          if ((l1 ^ l2 ^ ((S2CellId)localArrayList.get(k)).id()) != localS2CellId1.id()) {
            break;
          }
          l1 = localS2CellId1.lowestOnBit() << 1;
          l1 = l1 + (l1 << 1) ^ 0xFFFFFFFFFFFFFFFF;
          l2 = localS2CellId1.id() & l1;
          if (((((S2CellId)localArrayList.get(i)).id() & l1) != l2) || ((((S2CellId)localArrayList.get(j)).id() & l1) != l2) || ((((S2CellId)localArrayList.get(k)).id() & l1) != l2) || (localS2CellId1.isFace())) {
            break;
          }
          localArrayList.remove(k);
          localArrayList.remove(j);
          localArrayList.remove(i);
          localS2CellId1 = localS2CellId1.parent();
        }
        localArrayList.add(localS2CellId1);
      }
    }
    if (localArrayList.size() < size())
    {
      initRawSwap(localArrayList);
      return true;
    }
    return false;
  }
  
  public strictfp void pack()
  {
    cellIds.trimToSize();
  }
  
  public strictfp int size()
  {
    return cellIds.size();
  }
}

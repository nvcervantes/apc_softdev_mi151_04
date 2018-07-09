package com.google.common.geometry;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;
import java.util.logging.Logger;

public final class S2Polygon
  implements S2Region, Comparable<S2Polygon>
{
  private static final Logger log = Logger.getLogger(S2Polygon.class.getCanonicalName());
  private S2LatLngRect bound;
  private boolean hasHoles;
  private List<S2Loop> loops = new ArrayList();
  private int numVertices;
  
  public strictfp S2Polygon()
  {
    bound = S2LatLngRect.empty();
    hasHoles = false;
    numVertices = 0;
  }
  
  public strictfp S2Polygon(S2Loop paramS2Loop)
  {
    bound = paramS2Loop.getRectBound();
    hasHoles = false;
    numVertices = paramS2Loop.numVertices();
    loops.add(paramS2Loop);
  }
  
  public strictfp S2Polygon(S2Polygon paramS2Polygon)
  {
    bound = paramS2Polygon.getRectBound();
    hasHoles = hasHoles;
    numVertices = numVertices;
    int i = 0;
    while (i < paramS2Polygon.numLoops())
    {
      loops.add(new S2Loop(paramS2Polygon.loop(i)));
      i += 1;
    }
  }
  
  public strictfp S2Polygon(List<S2Loop> paramList)
  {
    bound = S2LatLngRect.empty();
    init(paramList);
  }
  
  private static strictfp void addIntersection(S2Point paramS2Point1, S2Point paramS2Point2, S2Point paramS2Point3, S2Point paramS2Point4, boolean paramBoolean, int paramInt, List<ParametrizedS2Point> paramList)
  {
    if (paramInt > 0)
    {
      paramS2Point3 = S2EdgeUtil.getIntersection(paramS2Point1, paramS2Point2, paramS2Point3, paramS2Point4);
      paramList.add(new ParametrizedS2Point(S2EdgeUtil.getDistanceFraction(paramS2Point3, paramS2Point1, paramS2Point2), paramS2Point3));
      return;
    }
    if (S2EdgeUtil.vertexCrossing(paramS2Point1, paramS2Point2, paramS2Point3, paramS2Point4))
    {
      double d2 = 1.0D;
      double d1;
      if ((paramS2Point1 != paramS2Point3) && (paramS2Point1 != paramS2Point4)) {
        d1 = 1.0D;
      } else {
        d1 = 0.0D;
      }
      if ((!paramBoolean) && (paramS2Point2 == paramS2Point4)) {
        d1 = d2;
      }
      if (d1 != 0.0D) {
        paramS2Point1 = paramS2Point2;
      }
      paramList.add(new ParametrizedS2Point(d1, paramS2Point1));
    }
  }
  
  private strictfp boolean anyLoopContains(S2Loop paramS2Loop)
  {
    int i = 0;
    while (i < numLoops())
    {
      if (loop(i).contains(paramS2Loop)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  private static strictfp void clipBoundary(S2Polygon paramS2Polygon1, boolean paramBoolean1, S2Polygon paramS2Polygon2, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4, S2PolygonBuilder paramS2PolygonBuilder)
  {
    S2PolygonIndex localS2PolygonIndex = new S2PolygonIndex(paramS2Polygon2, paramBoolean2);
    localS2PolygonIndex.predictAdditionalCalls(paramS2Polygon1.getNumVertices());
    ArrayList localArrayList = new ArrayList();
    paramS2Polygon1 = loops.iterator();
    while (paramS2Polygon1.hasNext())
    {
      S2Loop localS2Loop = (S2Loop)paramS2Polygon1.next();
      int k = localS2Loop.numVertices();
      int j;
      if ((localS2Loop.isHole() ^ paramBoolean1)) {
        j = -1;
      } else {
        j = 1;
      }
      int m = paramS2Polygon2.contains(localS2Loop.vertex(0)) ^ paramBoolean3;
      if (j > 0) {
        i = 0;
      }
      int i1;
      for (int i = k; k > 0; i = i1)
      {
        S2Point localS2Point1 = localS2Loop.vertex(i);
        i1 = i + j;
        S2Point localS2Point2 = localS2Loop.vertex(i1);
        localArrayList.clear();
        clipEdge(localS2Point1, localS2Point2, localS2PolygonIndex, paramBoolean4, localArrayList);
        if (m != 0) {
          localArrayList.add(new ParametrizedS2Point(0.0D, localS2Point1));
        }
        if ((localArrayList.size() & 0x1) == 1) {
          i = 1;
        } else {
          i = 0;
        }
        if (i != 0) {
          localArrayList.add(new ParametrizedS2Point(1.0D, localS2Point2));
        }
        Collections.sort(localArrayList);
        int i2 = localArrayList.size();
        m = 1;
        while (m < i2)
        {
          paramS2PolygonBuilder.addEdge(((ParametrizedS2Point)localArrayList.get(m - 1)).getPoint(), ((ParametrizedS2Point)localArrayList.get(m)).getPoint());
          m += 2;
        }
        k -= 1;
        int n = i;
      }
    }
  }
  
  private static strictfp void clipEdge(S2Point paramS2Point1, S2Point paramS2Point2, S2LoopSequenceIndex paramS2LoopSequenceIndex, boolean paramBoolean, List<ParametrizedS2Point> paramList)
  {
    S2EdgeIndex.DataEdgeIterator localDataEdgeIterator = new S2EdgeIndex.DataEdgeIterator(paramS2LoopSequenceIndex);
    localDataEdgeIterator.getCandidates(paramS2Point1, paramS2Point2);
    S2EdgeUtil.EdgeCrosser localEdgeCrosser = new S2EdgeUtil.EdgeCrosser(paramS2Point1, paramS2Point2, paramS2Point1);
    Object localObject2;
    for (Object localObject1 = null; localDataEdgeIterator.hasNext(); localObject1 = localObject2)
    {
      localObject2 = paramS2LoopSequenceIndex.edgeFromTo(localDataEdgeIterator.index());
      S2Point localS2Point = ((S2Edge)localObject2).getStart();
      localObject2 = ((S2Edge)localObject2).getEnd();
      if (localObject1 != localS2Point) {
        localEdgeCrosser.restartAt(localS2Point);
      }
      int i = localEdgeCrosser.robustCrossing((S2Point)localObject2);
      if (i >= 0) {
        addIntersection(paramS2Point1, paramS2Point2, localS2Point, (S2Point)localObject2, paramBoolean, i, paramList);
      }
      localDataEdgeIterator.next();
    }
  }
  
  private strictfp boolean containsAllShells(S2Polygon paramS2Polygon)
  {
    int i = 0;
    while (i < paramS2Polygon.numLoops())
    {
      if ((paramS2Polygon.loop(i).sign() >= 0) && (containsOrCrosses(paramS2Polygon.loop(i)) <= 0)) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private strictfp int containsOrCrosses(S2Loop paramS2Loop)
  {
    int m = 0;
    int j = 0;
    int k;
    for (int i = j; j < numLoops(); i = k)
    {
      int n = loop(j).containsOrCrosses(paramS2Loop);
      if (n < 0) {
        return -1;
      }
      k = i;
      if (n > 0) {
        k = i ^ 0x1;
      }
      j += 1;
    }
    j = m;
    if (i != 0) {
      j = 1;
    }
    return j;
  }
  
  public static strictfp S2Polygon destructiveUnion(List<S2Polygon> paramList)
  {
    return destructiveUnionSloppy(paramList, S2EdgeUtil.DEFAULT_INTERSECTION_TOLERANCE);
  }
  
  public static strictfp S2Polygon destructiveUnionSloppy(List<S2Polygon> paramList, S1Angle paramS1Angle)
  {
    TreeMap localTreeMap = new TreeMap();
    Object localObject3 = paramList.iterator();
    S2Polygon localS2Polygon;
    int i;
    Object localObject2;
    Object localObject1;
    while (((Iterator)localObject3).hasNext())
    {
      localS2Polygon = (S2Polygon)((Iterator)localObject3).next();
      i = localS2Polygon.getNumVertices();
      localObject2 = (Collection)localTreeMap.get(Integer.valueOf(i));
      localObject1 = localObject2;
      if (localObject2 == null)
      {
        localObject1 = new TreeSet();
        localTreeMap.put(Integer.valueOf(i), localObject1);
      }
      ((Collection)localObject1).add(localS2Polygon);
    }
    paramList.clear();
    for (paramList = entries(localTreeMap); paramList.size() > 1; paramList = (List<S2Polygon>)localObject1)
    {
      localObject1 = entries(localTreeMap);
      localObject2 = ((Collection)localObject1).iterator();
      localObject3 = (Map.Entry)((Iterator)localObject2).next();
      i = ((Integer)((Map.Entry)localObject3).getKey()).intValue();
      paramList = (S2Polygon)((Map.Entry)localObject3).getValue();
      ((Iterator)localObject2).remove();
      remove(localTreeMap, (Map.Entry)localObject3);
      localObject3 = (Map.Entry)((Iterator)localObject2).next();
      int j = ((Integer)((Map.Entry)localObject3).getKey()).intValue();
      localS2Polygon = (S2Polygon)((Map.Entry)localObject3).getValue();
      ((Iterator)localObject2).remove();
      remove(localTreeMap, (Map.Entry)localObject3);
      localObject3 = new S2Polygon();
      ((S2Polygon)localObject3).initToUnionSloppy(paramList, localS2Polygon, paramS1Angle);
      i += j;
      localObject2 = (Collection)localTreeMap.get(Integer.valueOf(i));
      paramList = (List<S2Polygon>)localObject2;
      if (localObject2 == null)
      {
        paramList = new TreeSet();
        localTreeMap.put(Integer.valueOf(i), paramList);
      }
      paramList.add(localObject3);
    }
    paramList = localTreeMap.entrySet().iterator();
    if (paramList.hasNext())
    {
      paramList = ((Collection)((Map.Entry)paramList.next()).getValue()).iterator();
      if (paramList.hasNext()) {
        return (S2Polygon)paramList.next();
      }
    }
    return new S2Polygon();
  }
  
  private static strictfp <K, V> Collection<Map.Entry<K, V>> entries(Map<K, Collection<V>> paramMap)
  {
    ArrayList localArrayList = new ArrayList();
    paramMap = paramMap.entrySet().iterator();
    while (paramMap.hasNext())
    {
      Object localObject2 = (Map.Entry)paramMap.next();
      Object localObject1 = ((Map.Entry)localObject2).getKey();
      localObject2 = ((Collection)((Map.Entry)localObject2).getValue()).iterator();
      while (((Iterator)localObject2).hasNext()) {
        localArrayList.add(new AbstractMap.SimpleEntry(localObject1, ((Iterator)localObject2).next()));
      }
    }
    return localArrayList;
  }
  
  private strictfp boolean excludesAllHoles(S2Polygon paramS2Polygon)
  {
    int i = 0;
    while (i < paramS2Polygon.numLoops())
    {
      if ((paramS2Polygon.loop(i).sign() <= 0) && (containsOrCrosses(paramS2Polygon.loop(i)) != 0)) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  private strictfp S2AreaCentroid getAreaCentroid(boolean paramBoolean)
  {
    S2Point localS2Point = new S2Point(0.0D, 0.0D, 0.0D);
    double d1 = 0.0D;
    int i = 0;
    while (i < numLoops())
    {
      double d2;
      if (paramBoolean)
      {
        Object localObject = loop(i).getAreaAndCentroid();
        d2 = ((S2AreaCentroid)localObject).getArea();
        double d3 = loop(i).sign();
        localObject = ((S2AreaCentroid)localObject).getCentroid();
        localS2Point = new S2Point(x + x * d3, y + y * d3, z + d3 * z);
        d1 += d2 * d3;
      }
      else
      {
        d2 = loop(i).getArea();
        d1 += loop(i).sign() * d2;
      }
      i += 1;
    }
    if (!paramBoolean) {
      localS2Point = null;
    }
    return new S2AreaCentroid(d1, localS2Point);
  }
  
  private strictfp void initLoop(S2Loop paramS2Loop, int paramInt, Map<S2Loop, List<S2Loop>> paramMap)
  {
    if (paramS2Loop != null)
    {
      paramS2Loop.setDepth(paramInt);
      loops.add(paramS2Loop);
    }
    paramS2Loop = (List)paramMap.get(paramS2Loop);
    if (paramS2Loop != null)
    {
      paramS2Loop = paramS2Loop.iterator();
      while (paramS2Loop.hasNext()) {
        initLoop((S2Loop)paramS2Loop.next(), paramInt + 1, paramMap);
      }
    }
  }
  
  private static strictfp void insertLoop(S2Loop paramS2Loop1, S2Loop paramS2Loop2, Map<S2Loop, List<S2Loop>> paramMap)
  {
    Object localObject2 = (List)paramMap.get(paramS2Loop2);
    Object localObject1 = localObject2;
    if (localObject2 == null)
    {
      localObject1 = new ArrayList();
      paramMap.put(paramS2Loop2, localObject1);
    }
    paramS2Loop2 = ((List)localObject1).iterator();
    while (paramS2Loop2.hasNext())
    {
      localObject2 = (S2Loop)paramS2Loop2.next();
      if (((S2Loop)localObject2).containsNested(paramS2Loop1))
      {
        insertLoop(paramS2Loop1, (S2Loop)localObject2, paramMap);
        return;
      }
    }
    paramS2Loop2 = (List)paramMap.get(paramS2Loop1);
    int i = 0;
    while (i < ((List)localObject1).size())
    {
      S2Loop localS2Loop = (S2Loop)((List)localObject1).get(i);
      if (paramS2Loop1.containsNested(localS2Loop))
      {
        localObject2 = paramS2Loop2;
        if (paramS2Loop2 == null)
        {
          localObject2 = new ArrayList();
          paramMap.put(paramS2Loop1, localObject2);
        }
        ((List)localObject2).add(localS2Loop);
        ((List)localObject1).remove(i);
        paramS2Loop2 = (S2Loop)localObject2;
      }
      else
      {
        i += 1;
      }
    }
    ((List)localObject1).add(paramS2Loop1);
  }
  
  private strictfp boolean intersectsAnyShell(S2Polygon paramS2Polygon)
  {
    int i = 0;
    while (i < paramS2Polygon.numLoops())
    {
      if ((paramS2Polygon.loop(i).sign() >= 0) && (containsOrCrosses(paramS2Polygon.loop(i)) != 0)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static strictfp boolean isValid(List<S2Loop> paramList)
  {
    Object localObject1;
    int j;
    int k;
    if (paramList.size() > 1)
    {
      localObject1 = new HashMap();
      i = 0;
      while (i < paramList.size())
      {
        Object localObject2 = (S2Loop)paramList.get(i);
        for (j = 0; j < ((S2Loop)localObject2).numVertices(); j = k)
        {
          Object localObject3 = ((S2Loop)localObject2).vertex(j);
          k = j + 1;
          localObject3 = new UndirectedEdge((S2Point)localObject3, ((S2Loop)localObject2).vertex(k));
          LoopVertexIndexPair localLoopVertexIndexPair = new LoopVertexIndexPair(i, j);
          if (((Map)localObject1).containsKey(localObject3))
          {
            paramList = (LoopVertexIndexPair)((Map)localObject1).get(localObject3);
            localObject1 = log;
            localObject2 = new StringBuilder();
            ((StringBuilder)localObject2).append("Duplicate edge: loop ");
            ((StringBuilder)localObject2).append(i);
            ((StringBuilder)localObject2).append(", edge ");
            ((StringBuilder)localObject2).append(j);
            ((StringBuilder)localObject2).append(" and loop ");
            ((StringBuilder)localObject2).append(paramList.getLoopIndex());
            ((StringBuilder)localObject2).append(", edge ");
            ((StringBuilder)localObject2).append(paramList.getVertexIndex());
            ((Logger)localObject1).info(((StringBuilder)localObject2).toString());
            return false;
          }
          ((Map)localObject1).put(localObject3, localLoopVertexIndexPair);
        }
        i += 1;
      }
    }
    for (int i = 0; i < paramList.size(); i = j)
    {
      if (!((S2Loop)paramList.get(i)).isNormalized())
      {
        paramList = log;
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Loop ");
        ((StringBuilder)localObject1).append(i);
        ((StringBuilder)localObject1).append(" encloses more than half the sphere");
        paramList.info(((StringBuilder)localObject1).toString());
        return false;
      }
      j = i + 1;
      k = j;
      while (k < paramList.size())
      {
        if (((S2Loop)paramList.get(i)).containsOrCrosses((S2Loop)paramList.get(k)) < 0)
        {
          paramList = log;
          localObject1 = new StringBuilder();
          ((StringBuilder)localObject1).append("Loop ");
          ((StringBuilder)localObject1).append(i);
          ((StringBuilder)localObject1).append(" crosses loop ");
          ((StringBuilder)localObject1).append(k);
          paramList.info(((StringBuilder)localObject1).toString());
          return false;
        }
        k += 1;
      }
    }
    return true;
  }
  
  private static strictfp void remove(Map<Integer, Collection<S2Polygon>> paramMap, Map.Entry<Integer, S2Polygon> paramEntry)
  {
    int i = ((Integer)paramEntry.getKey()).intValue();
    Collection localCollection = (Collection)paramMap.get(Integer.valueOf(i));
    if (localCollection == null)
    {
      paramMap.remove(Integer.valueOf(i));
      return;
    }
    localCollection.remove(paramEntry.getValue());
    if (localCollection.isEmpty()) {
      paramMap.remove(Integer.valueOf(i));
    }
  }
  
  private static strictfp void sortValueLoops(Map<S2Loop, List<S2Loop>> paramMap)
  {
    Iterator localIterator = paramMap.keySet().iterator();
    while (localIterator.hasNext()) {
      Collections.sort((List)paramMap.get((S2Loop)localIterator.next()));
    }
  }
  
  strictfp boolean boundaryApproxEquals(S2Polygon paramS2Polygon, double paramDouble)
  {
    Object localObject1;
    Object localObject2;
    if (numLoops() != paramS2Polygon.numLoops())
    {
      localObject1 = log;
      localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append("!= loops: ");
      ((StringBuilder)localObject2).append(Integer.toString(numLoops()));
      ((StringBuilder)localObject2).append(" vs. ");
      ((StringBuilder)localObject2).append(Integer.toString(paramS2Polygon.numLoops()));
      ((Logger)localObject1).severe(((StringBuilder)localObject2).toString());
      return false;
    }
    int i = 0;
    for (;;)
    {
      int j = numLoops();
      int k = 1;
      if (i >= j) {
        break;
      }
      localObject1 = loop(i);
      j = 0;
      while (j < numLoops())
      {
        localObject2 = paramS2Polygon.loop(j);
        if ((((S2Loop)localObject2).depth() == ((S2Loop)localObject1).depth()) && (((S2Loop)localObject2).boundaryApproxEquals((S2Loop)localObject1, paramDouble)))
        {
          j = k;
          break label171;
        }
        j += 1;
      }
      j = 0;
      label171:
      if (j == 0) {
        return false;
      }
      i += 1;
    }
    return true;
  }
  
  public strictfp int compareTo(S2Polygon paramS2Polygon)
  {
    if (numLoops() != paramS2Polygon.numLoops()) {
      return numLoops() - paramS2Polygon.numLoops();
    }
    int i = 0;
    while (i < numLoops())
    {
      int j = ((S2Loop)loops.get(i)).compareTo((S2Loop)loops.get(i));
      if (j != 0) {
        return j;
      }
      i += 1;
    }
    return 0;
  }
  
  public strictfp boolean contains(S2Cell paramS2Cell)
  {
    if (numLoops() == 1) {
      return loop(0).contains(paramS2Cell);
    }
    S2LatLngRect localS2LatLngRect = paramS2Cell.getRectBound();
    if (!bound.contains(localS2LatLngRect)) {
      return false;
    }
    return contains(new S2Polygon(new S2Loop(paramS2Cell, localS2LatLngRect)));
  }
  
  public strictfp boolean contains(S2Point paramS2Point)
  {
    int j = numLoops();
    int i = 0;
    if (j == 1) {
      return loop(0).contains(paramS2Point);
    }
    if (!bound.contains(paramS2Point)) {
      return false;
    }
    boolean bool = false;
    while (i < numLoops())
    {
      bool ^= loop(i).contains(paramS2Point);
      if ((bool) && (!hasHoles)) {
        return bool;
      }
      i += 1;
    }
    return bool;
  }
  
  public strictfp boolean contains(S2Polygon paramS2Polygon)
  {
    if ((numLoops() == 1) && (paramS2Polygon.numLoops() == 1)) {
      return loop(0).contains(paramS2Polygon.loop(0));
    }
    if ((!bound.contains(paramS2Polygon.getRectBound())) && (!bound.lng().union(paramS2Polygon.getRectBound().lng()).isFull())) {
      return false;
    }
    if ((!hasHoles) && (!hasHoles))
    {
      int i = 0;
      while (i < paramS2Polygon.numLoops())
      {
        if (!anyLoopContains(paramS2Polygon.loop(i))) {
          return false;
        }
        i += 1;
      }
      return true;
    }
    return (containsAllShells(paramS2Polygon)) && (paramS2Polygon.excludesAllHoles(this));
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
    if (contains(paramS2Point)) {
      return S1Angle.radians(0.0D);
    }
    S1Angle localS1Angle = S1Angle.radians(3.141592653589793D);
    int i = 0;
    while (i < numLoops())
    {
      localS1Angle = S1Angle.min(localS1Angle, loop(i).getDistance(paramS2Point));
      i += 1;
    }
    return localS1Angle;
  }
  
  public strictfp int getLastDescendant(int paramInt)
  {
    if (paramInt < 0) {
      return numLoops() - 1;
    }
    int i = loop(paramInt).depth();
    do
    {
      paramInt += 1;
    } while ((paramInt < numLoops()) && (loop(paramInt).depth() > i));
    return paramInt - 1;
  }
  
  public strictfp int getNumVertices()
  {
    return numVertices;
  }
  
  public strictfp int getParent(int paramInt)
  {
    int i = loop(paramInt).depth();
    if (i == 0) {
      return -1;
    }
    do
    {
      paramInt -= 1;
    } while ((paramInt >= 0) && (loop(paramInt).depth() >= i));
    return paramInt;
  }
  
  public strictfp S2LatLngRect getRectBound()
  {
    return bound;
  }
  
  public strictfp void init(List<S2Loop> paramList)
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put(null, new ArrayList());
    Iterator localIterator = paramList.iterator();
    while (localIterator.hasNext())
    {
      S2Loop localS2Loop = (S2Loop)localIterator.next();
      insertLoop(localS2Loop, null, localHashMap);
      numVertices += localS2Loop.numVertices();
    }
    paramList.clear();
    sortValueLoops(localHashMap);
    initLoop(null, -1, localHashMap);
    int i = 0;
    hasHoles = false;
    bound = S2LatLngRect.empty();
    while (i < numLoops())
    {
      if (loop(i).sign() < 0) {
        hasHoles = true;
      } else {
        bound = bound.union(loop(i).getRectBound());
      }
      i += 1;
    }
  }
  
  public strictfp void initToIntersection(S2Polygon paramS2Polygon1, S2Polygon paramS2Polygon2)
  {
    initToIntersectionSloppy(paramS2Polygon1, paramS2Polygon2, S2EdgeUtil.DEFAULT_INTERSECTION_TOLERANCE);
  }
  
  public strictfp void initToIntersectionSloppy(S2Polygon paramS2Polygon1, S2Polygon paramS2Polygon2, S1Angle paramS1Angle)
  {
    if (numLoops() != 0) {
      throw new IllegalStateException();
    }
    if (!bound.intersects(bound)) {
      return;
    }
    S2PolygonBuilder.Options localOptions = S2PolygonBuilder.Options.DIRECTED_XOR;
    localOptions.setMergeDistance(paramS1Angle);
    paramS1Angle = new S2PolygonBuilder(localOptions);
    clipBoundary(paramS2Polygon1, false, paramS2Polygon2, false, false, true, paramS1Angle);
    clipBoundary(paramS2Polygon2, false, paramS2Polygon1, false, false, false, paramS1Angle);
    if (!paramS1Angle.assemblePolygon(this, null)) {
      log.severe("Bad directed edges");
    }
  }
  
  public strictfp void initToUnion(S2Polygon paramS2Polygon1, S2Polygon paramS2Polygon2)
  {
    initToUnionSloppy(paramS2Polygon1, paramS2Polygon2, S2EdgeUtil.DEFAULT_INTERSECTION_TOLERANCE);
  }
  
  public strictfp void initToUnionSloppy(S2Polygon paramS2Polygon1, S2Polygon paramS2Polygon2, S1Angle paramS1Angle)
  {
    if (numLoops() != 0) {
      throw new IllegalStateException();
    }
    S2PolygonBuilder.Options localOptions = S2PolygonBuilder.Options.DIRECTED_XOR;
    localOptions.setMergeDistance(paramS1Angle);
    paramS1Angle = new S2PolygonBuilder(localOptions);
    clipBoundary(paramS2Polygon1, false, paramS2Polygon2, false, true, true, paramS1Angle);
    clipBoundary(paramS2Polygon2, false, paramS2Polygon1, false, true, false, paramS1Angle);
    if (!paramS1Angle.assemblePolygon(this, null)) {
      log.severe("Bad directed edges");
    }
  }
  
  public strictfp boolean intersects(S2Polygon paramS2Polygon)
  {
    int i = numLoops();
    boolean bool = true;
    if ((i == 1) && (paramS2Polygon.numLoops() == 1)) {
      return loop(0).intersects(paramS2Polygon.loop(0));
    }
    if (!bound.intersects(paramS2Polygon.getRectBound())) {
      return false;
    }
    if ((!hasHoles) && (!hasHoles))
    {
      i = 0;
      while (i < numLoops())
      {
        int j = 0;
        while (j < paramS2Polygon.numLoops())
        {
          if (loop(i).intersects(paramS2Polygon.loop(j))) {
            return true;
          }
          j += 1;
        }
        i += 1;
      }
      return false;
    }
    if (!intersectsAnyShell(paramS2Polygon))
    {
      if (paramS2Polygon.intersectsAnyShell(this)) {
        return true;
      }
      bool = false;
    }
    return bool;
  }
  
  public strictfp boolean isNormalized()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject1 = null;
    int i = 0;
    while (i < numLoops())
    {
      S2Loop localS2Loop2 = loop(i);
      if (localS2Loop2.depth() != 0)
      {
        S2Loop localS2Loop1 = loop(getParent(i));
        Object localObject2 = localObject1;
        if (localS2Loop1 != localObject1)
        {
          localArrayList.clear();
          j = 0;
          while (j < localS2Loop1.numVertices())
          {
            localArrayList.add(localS2Loop1.vertex(j));
            j += 1;
          }
          localObject2 = localS2Loop1;
        }
        int j = 0;
        int m;
        for (int k = j; j < localS2Loop2.numVertices(); k = m)
        {
          m = k;
          if (localArrayList.contains(localS2Loop2.vertex(j))) {
            m = k + 1;
          }
          j += 1;
        }
        localObject1 = localObject2;
        if (k > 1) {
          return false;
        }
      }
      i += 1;
    }
    return true;
  }
  
  public strictfp S2Loop loop(int paramInt)
  {
    return (S2Loop)loops.get(paramInt);
  }
  
  public strictfp boolean mayIntersect(S2Cell paramS2Cell)
  {
    if (numLoops() == 1) {
      return loop(0).mayIntersect(paramS2Cell);
    }
    S2LatLngRect localS2LatLngRect = paramS2Cell.getRectBound();
    if (!bound.intersects(localS2LatLngRect)) {
      return false;
    }
    return intersects(new S2Polygon(new S2Loop(paramS2Cell, localS2LatLngRect)));
  }
  
  public strictfp int numLoops()
  {
    return loops.size();
  }
  
  public strictfp void release(List<S2Loop> paramList)
  {
    paramList.addAll(loops);
    loops.clear();
    bound = S2LatLngRect.empty();
    hasHoles = false;
    numVertices = 0;
  }
  
  public strictfp String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Polygon: (");
    localStringBuilder.append(numLoops());
    localStringBuilder.append(") loops:\n");
    int i = 0;
    while (i < numLoops())
    {
      S2Loop localS2Loop = loop(i);
      localStringBuilder.append("loop <\n");
      int j = 0;
      while (j < localS2Loop.numVertices())
      {
        localStringBuilder.append(localS2Loop.vertex(j).toDegreesString());
        localStringBuilder.append("\n");
        j += 1;
      }
      localStringBuilder.append(">\n");
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private static final class LoopVertexIndexPair
  {
    private final int loopIndex;
    private final int vertexIndex;
    
    public strictfp LoopVertexIndexPair(int paramInt1, int paramInt2)
    {
      loopIndex = paramInt1;
      vertexIndex = paramInt2;
    }
    
    public strictfp int getLoopIndex()
    {
      return loopIndex;
    }
    
    public strictfp int getVertexIndex()
    {
      return vertexIndex;
    }
  }
  
  private static final class ParametrizedS2Point
    implements Comparable<ParametrizedS2Point>
  {
    private final S2Point point;
    private final double time;
    
    public strictfp ParametrizedS2Point(double paramDouble, S2Point paramS2Point)
    {
      time = paramDouble;
      point = paramS2Point;
    }
    
    public strictfp int compareTo(ParametrizedS2Point paramParametrizedS2Point)
    {
      int i = Double.compare(time, time);
      if (i != 0) {
        return i;
      }
      return point.compareTo(point);
    }
    
    public strictfp S2Point getPoint()
    {
      return point;
    }
    
    public strictfp double getTime()
    {
      return time;
    }
  }
  
  private static abstract class S2LoopSequenceIndex
    extends S2EdgeIndex
  {
    private final int[] indexToLoop;
    private final int[] loopToFirstIndex;
    
    public strictfp S2LoopSequenceIndex(int[] paramArrayOfInt)
    {
      int k = paramArrayOfInt.length;
      int i = 0;
      int j = i;
      while (i < k)
      {
        j += paramArrayOfInt[i];
        i += 1;
      }
      indexToLoop = new int[j];
      loopToFirstIndex = new int[paramArrayOfInt.length];
      j = 0;
      i = j;
      while (j < paramArrayOfInt.length)
      {
        loopToFirstIndex[j] = i;
        k = 0;
        while (k < paramArrayOfInt[j])
        {
          indexToLoop[i] = j;
          i += 1;
          k += 1;
        }
        j += 1;
      }
    }
    
    public final strictfp S2Polygon.LoopVertexIndexPair decodeIndex(int paramInt)
    {
      int i = indexToLoop[paramInt];
      return new S2Polygon.LoopVertexIndexPair(i, paramInt - loopToFirstIndex[i]);
    }
    
    public strictfp S2Point edgeFrom(int paramInt)
    {
      return edgeFromTo(paramInt).getStart();
    }
    
    public abstract S2Edge edgeFromTo(int paramInt);
    
    protected strictfp S2Point edgeTo(int paramInt)
    {
      return edgeFromTo(paramInt).getEnd();
    }
    
    public final strictfp int getNumEdges()
    {
      return indexToLoop.length;
    }
  }
  
  private static final class S2PolygonIndex
    extends S2Polygon.S2LoopSequenceIndex
  {
    private final S2Polygon poly;
    private final boolean reverse;
    
    public strictfp S2PolygonIndex(S2Polygon paramS2Polygon, boolean paramBoolean)
    {
      super();
      poly = paramS2Polygon;
      reverse = paramBoolean;
    }
    
    private static strictfp int[] getVertices(S2Polygon paramS2Polygon)
    {
      int[] arrayOfInt = new int[paramS2Polygon.numLoops()];
      int i = 0;
      while (i < arrayOfInt.length)
      {
        arrayOfInt[i] = paramS2Polygon.loop(i).numVertices();
        i += 1;
      }
      return arrayOfInt;
    }
    
    public strictfp S2Edge edgeFromTo(int paramInt)
    {
      Object localObject = decodeIndex(paramInt);
      paramInt = ((S2Polygon.LoopVertexIndexPair)localObject).getLoopIndex();
      int i = ((S2Polygon.LoopVertexIndexPair)localObject).getVertexIndex();
      localObject = poly.loop(paramInt);
      if ((((S2Loop)localObject).isHole() ^ reverse))
      {
        int j = ((S2Loop)localObject).numVertices();
        paramInt = ((S2Loop)localObject).numVertices() * 2 - 2 - i;
        i = j - 1 - i;
      }
      else
      {
        paramInt = i + 1;
      }
      return new S2Edge(((S2Loop)localObject).vertex(i), ((S2Loop)localObject).vertex(paramInt));
    }
  }
  
  private static final class UndirectedEdge
  {
    private final S2Point a;
    private final S2Point b;
    
    public strictfp UndirectedEdge(S2Point paramS2Point1, S2Point paramS2Point2)
    {
      a = paramS2Point1;
      b = paramS2Point2;
    }
    
    public strictfp boolean equals(Object paramObject)
    {
      boolean bool2 = false;
      if (paramObject != null)
      {
        if (!(paramObject instanceof UndirectedEdge)) {
          return false;
        }
        paramObject = (UndirectedEdge)paramObject;
        boolean bool1;
        if ((!getStart().equals(paramObject.getStart())) || (!getEnd().equals(paramObject.getEnd())))
        {
          bool1 = bool2;
          if (getStart().equals(paramObject.getEnd()))
          {
            bool1 = bool2;
            if (!getEnd().equals(paramObject.getStart())) {}
          }
        }
        else
        {
          bool1 = true;
        }
        return bool1;
      }
      return false;
    }
    
    public strictfp S2Point getEnd()
    {
      return b;
    }
    
    public strictfp S2Point getStart()
    {
      return a;
    }
    
    public strictfp int hashCode()
    {
      return getStart().hashCode() + getEnd().hashCode();
    }
    
    public strictfp String toString()
    {
      return String.format("Edge: (%s <-> %s)\n   or [%s <-> %s]", new Object[] { a.toDegreesString(), b.toDegreesString(), a, b });
    }
  }
}

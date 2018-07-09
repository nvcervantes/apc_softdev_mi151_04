package com.google.common.geometry;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public final class S2RegionCoverer
{
  public static final int DEFAULT_MAX_CELLS = 8;
  private static final S2Cell[] FACE_CELLS = new S2Cell[6];
  private PriorityQueue<QueueEntry> candidateQueue = new PriorityQueue(10, new QueueEntriesComparator());
  private boolean interiorCovering;
  private int levelMod = 1;
  private int maxCells = 8;
  private int maxLevel = 30;
  private int minLevel = 0;
  S2Region region = null;
  ArrayList<S2CellId> result = new ArrayList();
  
  static
  {
    int i = 0;
    while (i < 6)
    {
      FACE_CELLS[i] = S2Cell.fromFacePosLevel(i, 0, 0);
      i += 1;
    }
  }
  
  public strictfp S2RegionCoverer() {}
  
  private strictfp void addCandidate(Candidate paramCandidate)
  {
    if (paramCandidate == null) {
      return;
    }
    if (isTerminal)
    {
      result.add(cell.id());
      return;
    }
    if (cell.level() < minLevel) {
      i = 1;
    } else {
      i = levelMod;
    }
    int i = expandChildren(paramCandidate, cell, i);
    if (numChildren == 0) {
      return;
    }
    if ((!interiorCovering) && (i == 1 << maxChildrenShift()) && (cell.level() >= minLevel))
    {
      Candidate.access$202(paramCandidate, true);
      addCandidate(paramCandidate);
      return;
    }
    i = -(((cell.level() << maxChildrenShift()) + numChildren << maxChildrenShift()) + i);
    candidateQueue.add(new QueueEntry(i, paramCandidate));
  }
  
  private strictfp int expandChildren(Candidate paramCandidate, S2Cell paramS2Cell, int paramInt)
  {
    int k = paramInt - 1;
    S2Cell[] arrayOfS2Cell = new S2Cell[4];
    int i = 0;
    paramInt = 0;
    while (paramInt < 4)
    {
      arrayOfS2Cell[paramInt] = new S2Cell();
      paramInt += 1;
    }
    paramS2Cell.subdivide(arrayOfS2Cell);
    for (int j = 0; i < 4; j = paramInt)
    {
      if (k > 0)
      {
        paramInt = j;
        if (region.mayIntersect(arrayOfS2Cell[i])) {
          paramInt = j + expandChildren(paramCandidate, arrayOfS2Cell[i], k);
        }
      }
      else
      {
        paramS2Cell = newCandidate(arrayOfS2Cell[i]);
        paramInt = j;
        if (paramS2Cell != null)
        {
          children[Candidate.access$408(paramCandidate)] = paramS2Cell;
          paramInt = j;
          if (isTerminal) {
            paramInt = j + 1;
          }
        }
      }
      i += 1;
    }
    return j;
  }
  
  private static strictfp void floodFill(S2Region paramS2Region, S2CellId paramS2CellId, ArrayList<S2CellId> paramArrayList)
  {
    HashSet localHashSet = new HashSet();
    ArrayList localArrayList = new ArrayList();
    paramArrayList.clear();
    localHashSet.add(paramS2CellId);
    localArrayList.add(paramS2CellId);
    while (!localArrayList.isEmpty())
    {
      S2CellId localS2CellId = (S2CellId)localArrayList.get(localArrayList.size() - 1);
      localArrayList.remove(localArrayList.size() - 1);
      if (paramS2Region.mayIntersect(new S2Cell(localS2CellId)))
      {
        paramArrayList.add(localS2CellId);
        paramS2CellId = new S2CellId[4];
        localS2CellId.getEdgeNeighbors(paramS2CellId);
        int i = 0;
        while (i < 4)
        {
          localS2CellId = paramS2CellId[i];
          if (!localHashSet.contains(localS2CellId))
          {
            localArrayList.add(localS2CellId);
            localHashSet.add(localS2CellId);
          }
          i += 1;
        }
      }
    }
  }
  
  private strictfp void getCoveringInternal(S2Region paramS2Region)
  {
    if ((candidateQueue.isEmpty()) && (result.isEmpty()))
    {
      region = paramS2Region;
      getInitialCandidates();
      while ((!candidateQueue.isEmpty()) && ((!interiorCovering) || (result.size() < maxCells)))
      {
        paramS2Region = candidateQueue.poll()).candidate;
        int k = cell.level();
        int m = minLevel;
        int j = 0;
        int i = j;
        if (k >= m)
        {
          i = j;
          if (numChildren != 1)
          {
            k = result.size();
            if (interiorCovering) {
              i = 0;
            } else {
              i = candidateQueue.size();
            }
            if (k + i + numChildren <= maxCells)
            {
              i = j;
            }
            else
            {
              if (interiorCovering) {
                continue;
              }
              Candidate.access$202(paramS2Region, true);
              addCandidate(paramS2Region);
              continue;
            }
          }
        }
        while (i < numChildren)
        {
          addCandidate(children[i]);
          i += 1;
        }
      }
      candidateQueue.clear();
      region = null;
      return;
    }
    throw new IllegalStateException();
  }
  
  private strictfp void getInitialCandidates()
  {
    int i = maxCells;
    int m = 0;
    int k = 0;
    int j = m;
    if (i >= 4)
    {
      S2Cap localS2Cap = region.getCapBound();
      j = Math.min(S2Projections.MIN_WIDTH.getMaxLevel(2.0D * localS2Cap.angle().radians()), Math.min(maxLevel(), 29));
      i = j;
      if (levelMod() > 1)
      {
        i = j;
        if (j > minLevel()) {
          i = j - (j - minLevel()) % levelMod();
        }
      }
      j = m;
      if (i > 0)
      {
        ArrayList localArrayList = new ArrayList(4);
        S2CellId.fromPoint(localS2Cap.axis()).getVertexNeighbors(i, localArrayList);
        i = k;
        while (i < localArrayList.size())
        {
          addCandidate(newCandidate(new S2Cell((S2CellId)localArrayList.get(i))));
          i += 1;
        }
        return;
      }
    }
    while (j < 6)
    {
      addCandidate(newCandidate(FACE_CELLS[j]));
      j += 1;
    }
  }
  
  public static strictfp void getSimpleCovering(S2Region paramS2Region, S2Point paramS2Point, int paramInt, ArrayList<S2CellId> paramArrayList)
  {
    floodFill(paramS2Region, S2CellId.fromPoint(paramS2Point).parent(paramInt), paramArrayList);
  }
  
  private strictfp int maxChildrenShift()
  {
    return 2 * levelMod;
  }
  
  private strictfp Candidate newCandidate(S2Cell paramS2Cell)
  {
    if (!region.mayIntersect(paramS2Cell)) {
      return null;
    }
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramS2Cell.level() >= minLevel)
    {
      if (interiorCovering) {
        if (!region.contains(paramS2Cell)) {}
      }
      do
      {
        do
        {
          bool1 = true;
          break;
          bool1 = bool2;
          if (paramS2Cell.level() + levelMod <= maxLevel) {
            break;
          }
          return null;
        } while (paramS2Cell.level() + levelMod > maxLevel);
        bool1 = bool2;
      } while (region.contains(paramS2Cell));
    }
    Candidate localCandidate = new Candidate();
    Candidate.access$102(localCandidate, paramS2Cell);
    Candidate.access$202(localCandidate, bool1);
    if (!bool1) {
      Candidate.access$302(localCandidate, new Candidate[1 << maxChildrenShift()]);
    }
    return localCandidate;
  }
  
  public strictfp S2CellUnion getCovering(S2Region paramS2Region)
  {
    S2CellUnion localS2CellUnion = new S2CellUnion();
    getCovering(paramS2Region, localS2CellUnion);
    return localS2CellUnion;
  }
  
  public strictfp void getCovering(S2Region paramS2Region, S2CellUnion paramS2CellUnion)
  {
    interiorCovering = false;
    getCoveringInternal(paramS2Region);
    paramS2CellUnion.initSwap(result);
  }
  
  public strictfp void getCovering(S2Region paramS2Region, ArrayList<S2CellId> paramArrayList)
  {
    getCovering(paramS2Region).denormalize(minLevel(), levelMod(), paramArrayList);
  }
  
  public strictfp S2CellUnion getInteriorCovering(S2Region paramS2Region)
  {
    S2CellUnion localS2CellUnion = new S2CellUnion();
    getInteriorCovering(paramS2Region, localS2CellUnion);
    return localS2CellUnion;
  }
  
  public strictfp void getInteriorCovering(S2Region paramS2Region, S2CellUnion paramS2CellUnion)
  {
    interiorCovering = true;
    getCoveringInternal(paramS2Region);
    paramS2CellUnion.initSwap(result);
  }
  
  public strictfp void getInteriorCovering(S2Region paramS2Region, ArrayList<S2CellId> paramArrayList)
  {
    getInteriorCovering(paramS2Region).denormalize(minLevel(), levelMod(), paramArrayList);
  }
  
  public strictfp int levelMod()
  {
    return levelMod;
  }
  
  public strictfp int maxCells()
  {
    return maxCells;
  }
  
  public strictfp int maxLevel()
  {
    return maxLevel;
  }
  
  public strictfp int minLevel()
  {
    return minLevel;
  }
  
  public strictfp void setLevelMod(int paramInt)
  {
    levelMod = Math.max(1, Math.min(3, paramInt));
  }
  
  public strictfp void setMaxCells(int paramInt)
  {
    maxCells = paramInt;
  }
  
  public strictfp void setMaxLevel(int paramInt)
  {
    maxLevel = Math.max(0, Math.min(30, paramInt));
  }
  
  public strictfp void setMinLevel(int paramInt)
  {
    minLevel = Math.max(0, Math.min(30, paramInt));
  }
  
  static class Candidate
  {
    private S2Cell cell;
    private Candidate[] children;
    private boolean isTerminal;
    private int numChildren;
    
    strictfp Candidate() {}
  }
  
  static class QueueEntriesComparator
    implements Comparator<S2RegionCoverer.QueueEntry>
  {
    strictfp QueueEntriesComparator() {}
    
    public strictfp int compare(S2RegionCoverer.QueueEntry paramQueueEntry1, S2RegionCoverer.QueueEntry paramQueueEntry2)
    {
      if (S2RegionCoverer.QueueEntry.access$000(paramQueueEntry1) < S2RegionCoverer.QueueEntry.access$000(paramQueueEntry2)) {
        return 1;
      }
      if (S2RegionCoverer.QueueEntry.access$000(paramQueueEntry1) > S2RegionCoverer.QueueEntry.access$000(paramQueueEntry2)) {
        return -1;
      }
      return 0;
    }
  }
  
  static class QueueEntry
  {
    private S2RegionCoverer.Candidate candidate;
    private int id;
    
    public strictfp QueueEntry(int paramInt, S2RegionCoverer.Candidate paramCandidate)
    {
      id = paramInt;
      candidate = paramCandidate;
    }
  }
}

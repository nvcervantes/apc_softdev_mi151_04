package android.support.v4.util;

public final class CircularIntArray
{
  private int mCapacityBitmask;
  private int[] mElements;
  private int mHead;
  private int mTail;
  
  public CircularIntArray()
  {
    this(8);
  }
  
  public CircularIntArray(int paramInt)
  {
    if (paramInt < 1) {
      throw new IllegalArgumentException("capacity must be >= 1");
    }
    if (paramInt > 1073741824) {
      throw new IllegalArgumentException("capacity must be <= 2^30");
    }
    int i = paramInt;
    if (Integer.bitCount(paramInt) != 1) {
      i = Integer.highestOneBit(paramInt - 1) << 1;
    }
    mCapacityBitmask = (i - 1);
    mElements = new int[i];
  }
  
  private void doubleCapacity()
  {
    int i = mElements.length;
    int j = i - mHead;
    int k = i << 1;
    if (k < 0) {
      throw new RuntimeException("Max array capacity exceeded");
    }
    int[] arrayOfInt = new int[k];
    System.arraycopy(mElements, mHead, arrayOfInt, 0, j);
    System.arraycopy(mElements, 0, arrayOfInt, j, mHead);
    mElements = arrayOfInt;
    mHead = 0;
    mTail = i;
    mCapacityBitmask = (k - 1);
  }
  
  public void addFirst(int paramInt)
  {
    mHead = (mHead - 1 & mCapacityBitmask);
    mElements[mHead] = paramInt;
    if (mHead == mTail) {
      doubleCapacity();
    }
  }
  
  public void addLast(int paramInt)
  {
    mElements[mTail] = paramInt;
    mTail = (mTail + 1 & mCapacityBitmask);
    if (mTail == mHead) {
      doubleCapacity();
    }
  }
  
  public void clear()
  {
    mTail = mHead;
  }
  
  public int get(int paramInt)
  {
    if ((paramInt >= 0) && (paramInt < size()))
    {
      int[] arrayOfInt = mElements;
      int i = mHead;
      return arrayOfInt[(mCapacityBitmask & i + paramInt)];
    }
    throw new ArrayIndexOutOfBoundsException();
  }
  
  public int getFirst()
  {
    if (mHead == mTail) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return mElements[mHead];
  }
  
  public int getLast()
  {
    if (mHead == mTail) {
      throw new ArrayIndexOutOfBoundsException();
    }
    return mElements[(mTail - 1 & mCapacityBitmask)];
  }
  
  public boolean isEmpty()
  {
    return mHead == mTail;
  }
  
  public int popFirst()
  {
    if (mHead == mTail) {
      throw new ArrayIndexOutOfBoundsException();
    }
    int i = mElements[mHead];
    mHead = (mHead + 1 & mCapacityBitmask);
    return i;
  }
  
  public int popLast()
  {
    if (mHead == mTail) {
      throw new ArrayIndexOutOfBoundsException();
    }
    int i = mTail - 1 & mCapacityBitmask;
    int j = mElements[i];
    mTail = i;
    return j;
  }
  
  public void removeFromEnd(int paramInt)
  {
    if (paramInt <= 0) {
      return;
    }
    if (paramInt > size()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    int i = mTail;
    mTail = (mCapacityBitmask & i - paramInt);
  }
  
  public void removeFromStart(int paramInt)
  {
    if (paramInt <= 0) {
      return;
    }
    if (paramInt > size()) {
      throw new ArrayIndexOutOfBoundsException();
    }
    int i = mHead;
    mHead = (mCapacityBitmask & i + paramInt);
  }
  
  public int size()
  {
    return mTail - mHead & mCapacityBitmask;
  }
}

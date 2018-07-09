package okio;

import javax.annotation.Nullable;

final class Segment
{
  static final int SHARE_MINIMUM = 1024;
  static final int SIZE = 8192;
  final byte[] data;
  int limit;
  Segment next;
  boolean owner;
  int pos;
  Segment prev;
  boolean shared;
  
  Segment()
  {
    data = new byte[' '];
    owner = true;
    shared = false;
  }
  
  Segment(Segment paramSegment)
  {
    this(data, pos, limit);
    shared = true;
  }
  
  Segment(byte[] paramArrayOfByte, int paramInt1, int paramInt2)
  {
    data = paramArrayOfByte;
    pos = paramInt1;
    limit = paramInt2;
    owner = false;
    shared = true;
  }
  
  public void compact()
  {
    if (prev == this) {
      throw new IllegalStateException();
    }
    if (!prev.owner) {
      return;
    }
    int j = limit - pos;
    int k = prev.limit;
    int i;
    if (prev.shared) {
      i = 0;
    } else {
      i = prev.pos;
    }
    if (j > 8192 - k + i) {
      return;
    }
    writeTo(prev, j);
    pop();
    SegmentPool.recycle(this);
  }
  
  @Nullable
  public Segment pop()
  {
    Segment localSegment;
    if (next != this) {
      localSegment = next;
    } else {
      localSegment = null;
    }
    prev.next = next;
    next.prev = prev;
    next = null;
    prev = null;
    return localSegment;
  }
  
  public Segment push(Segment paramSegment)
  {
    prev = this;
    next = next;
    next.prev = paramSegment;
    next = paramSegment;
    return paramSegment;
  }
  
  public Segment split(int paramInt)
  {
    if ((paramInt > 0) && (paramInt <= limit - pos))
    {
      Segment localSegment;
      if (paramInt >= 1024)
      {
        localSegment = new Segment(this);
      }
      else
      {
        localSegment = SegmentPool.take();
        System.arraycopy(data, pos, data, 0, paramInt);
      }
      limit = (pos + paramInt);
      pos += paramInt;
      prev.push(localSegment);
      return localSegment;
    }
    throw new IllegalArgumentException();
  }
  
  public void writeTo(Segment paramSegment, int paramInt)
  {
    if (!owner) {
      throw new IllegalArgumentException();
    }
    if (limit + paramInt > 8192)
    {
      if (shared) {
        throw new IllegalArgumentException();
      }
      if (limit + paramInt - pos > 8192) {
        throw new IllegalArgumentException();
      }
      System.arraycopy(data, pos, data, 0, limit - pos);
      limit -= pos;
      pos = 0;
    }
    System.arraycopy(data, pos, data, limit, paramInt);
    limit += paramInt;
    pos += paramInt;
  }
}

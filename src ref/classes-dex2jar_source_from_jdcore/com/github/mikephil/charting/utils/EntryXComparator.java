package com.github.mikephil.charting.utils;

import com.github.mikephil.charting.data.Entry;
import java.util.Comparator;

public class EntryXComparator
  implements Comparator<Entry>
{
  public EntryXComparator() {}
  
  public int compare(Entry paramEntry1, Entry paramEntry2)
  {
    float f = paramEntry1.getX() - paramEntry2.getX();
    if (f == 0.0F) {
      return 0;
    }
    if (f > 0.0F) {
      return 1;
    }
    return -1;
  }
}

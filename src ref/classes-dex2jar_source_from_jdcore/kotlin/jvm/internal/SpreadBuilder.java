package kotlin.jvm.internal;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;

public class SpreadBuilder
{
  private final ArrayList<Object> list;
  
  public SpreadBuilder(int paramInt)
  {
    list = new ArrayList(paramInt);
  }
  
  public void add(Object paramObject)
  {
    list.add(paramObject);
  }
  
  public void addSpread(Object paramObject)
  {
    if (paramObject == null) {
      return;
    }
    if ((paramObject instanceof Object[]))
    {
      paramObject = (Object[])paramObject;
      if (paramObject.length > 0)
      {
        list.ensureCapacity(list.size() + paramObject.length);
        int j = paramObject.length;
        int i = 0;
        while (i < j)
        {
          localObject = paramObject[i];
          list.add(localObject);
          i += 1;
        }
      }
    }
    else
    {
      if ((paramObject instanceof Collection))
      {
        list.addAll((Collection)paramObject);
        return;
      }
      if ((paramObject instanceof Iterable))
      {
        paramObject = ((Iterable)paramObject).iterator();
        while (paramObject.hasNext())
        {
          localObject = paramObject.next();
          list.add(localObject);
        }
      }
      if (!(paramObject instanceof Iterator)) {
        break label177;
      }
      paramObject = (Iterator)paramObject;
      while (paramObject.hasNext()) {
        list.add(paramObject.next());
      }
    }
    return;
    label177:
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Don't know how to spread ");
    ((StringBuilder)localObject).append(paramObject.getClass());
    throw new UnsupportedOperationException(((StringBuilder)localObject).toString());
  }
  
  public int size()
  {
    return list.size();
  }
  
  public Object[] toArray(Object[] paramArrayOfObject)
  {
    return list.toArray(paramArrayOfObject);
  }
}

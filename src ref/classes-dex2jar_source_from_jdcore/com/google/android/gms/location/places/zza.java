package com.google.android.gms.location.places;

import com.google.android.gms.common.internal.Hide;
import com.google.android.gms.internal.zzbgl;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Hide
public abstract class zza
  extends zzbgl
{
  public zza() {}
  
  static <E> List<E> zza(Collection<E> paramCollection)
  {
    if ((paramCollection != null) && (!paramCollection.isEmpty())) {
      return new ArrayList(paramCollection);
    }
    return Collections.emptyList();
  }
  
  static <E> Set<E> zza(List<E> paramList)
  {
    if ((paramList != null) && (!paramList.isEmpty())) {
      return Collections.unmodifiableSet(new HashSet(paramList));
    }
    return Collections.emptySet();
  }
  
  public abstract Set<String> getPlaceIds();
  
  public boolean isRestrictedToPlacesOpenNow()
  {
    return false;
  }
}

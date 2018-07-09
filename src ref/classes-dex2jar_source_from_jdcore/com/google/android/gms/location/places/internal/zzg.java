package com.google.android.gms.location.places.internal;

import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.CharacterStyle;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public final class zzg
{
  public static CharSequence zza(String paramString, List<zzb> paramList, CharacterStyle paramCharacterStyle)
  {
    if (paramCharacterStyle == null) {
      return paramString;
    }
    paramString = new SpannableString(paramString);
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      zzb localZzb = (zzb)paramList.next();
      paramString.setSpan(CharacterStyle.wrap(paramCharacterStyle), zza, zza + zzb, 0);
    }
    return paramString;
  }
  
  public static String zza(Collection<String> paramCollection)
  {
    if ((paramCollection != null) && (!paramCollection.isEmpty())) {
      return TextUtils.join(", ", paramCollection);
    }
    return null;
  }
}

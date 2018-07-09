package com.google.android.gms.maps.internal;

import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import com.google.android.gms.common.internal.Hide;

@Hide
public final class zzby
{
  private zzby() {}
  
  @Hide
  private static <T extends Parcelable> T zza(@Nullable Bundle paramBundle, String paramString)
  {
    if (paramBundle == null) {
      return null;
    }
    paramBundle.setClassLoader(zzby.class.getClassLoader());
    paramBundle = paramBundle.getBundle("map_state");
    if (paramBundle == null) {
      return null;
    }
    paramBundle.setClassLoader(zzby.class.getClassLoader());
    return paramBundle.getParcelable(paramString);
  }
  
  @Hide
  public static void zza(@Nullable Bundle paramBundle1, @Nullable Bundle paramBundle2)
  {
    if (paramBundle1 != null)
    {
      if (paramBundle2 == null) {
        return;
      }
      Parcelable localParcelable = zza(paramBundle1, "MapOptions");
      if (localParcelable != null) {
        zza(paramBundle2, "MapOptions", localParcelable);
      }
      localParcelable = zza(paramBundle1, "StreetViewPanoramaOptions");
      if (localParcelable != null) {
        zza(paramBundle2, "StreetViewPanoramaOptions", localParcelable);
      }
      localParcelable = zza(paramBundle1, "camera");
      if (localParcelable != null) {
        zza(paramBundle2, "camera", localParcelable);
      }
      if (paramBundle1.containsKey("position")) {
        paramBundle2.putString("position", paramBundle1.getString("position"));
      }
      if (paramBundle1.containsKey("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT")) {
        paramBundle2.putBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", paramBundle1.getBoolean("com.google.android.wearable.compat.extra.LOWBIT_AMBIENT", false));
      }
    }
  }
  
  @Hide
  public static void zza(Bundle paramBundle, String paramString, Parcelable paramParcelable)
  {
    paramBundle.setClassLoader(zzby.class.getClassLoader());
    Bundle localBundle2 = paramBundle.getBundle("map_state");
    Bundle localBundle1 = localBundle2;
    if (localBundle2 == null) {
      localBundle1 = new Bundle();
    }
    localBundle1.setClassLoader(zzby.class.getClassLoader());
    localBundle1.putParcelable(paramString, paramParcelable);
    paramBundle.putBundle("map_state", localBundle1);
  }
}

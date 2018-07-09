package com.google.android.gms.common.util;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.util.Log;
import java.io.File;

public final class zzx
{
  @TargetApi(21)
  public static File zza(Context paramContext)
  {
    if (zzs.zzg()) {
      return paramContext.getNoBackupFilesDir();
    }
    return zza(new File(getApplicationInfodataDir, "no_backup"));
  }
  
  private static File zza(File paramFile)
  {
    try
    {
      if ((!paramFile.exists()) && (!paramFile.mkdirs()))
      {
        boolean bool = paramFile.exists();
        if (bool) {
          return paramFile;
        }
        paramFile = String.valueOf(paramFile.getPath());
        if (paramFile.length() != 0) {
          paramFile = "Unable to create no-backup dir ".concat(paramFile);
        } else {
          paramFile = new String("Unable to create no-backup dir ");
        }
        Log.w("SupportV4Utils", paramFile);
        return null;
      }
      return paramFile;
    }
    finally {}
  }
}

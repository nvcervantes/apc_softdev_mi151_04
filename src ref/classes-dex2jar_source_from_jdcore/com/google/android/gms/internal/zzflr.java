package com.google.android.gms.internal;

import java.io.IOException;

public final class zzflr
  extends IOException
{
  public zzflr(String paramString)
  {
    super(paramString);
  }
  
  static zzflr zza()
  {
    return new zzflr("While parsing a protocol message, the input ended unexpectedly in the middle of a field.  This could mean either than the input has been truncated or that an embedded message misreported its own length.");
  }
  
  static zzflr zzb()
  {
    return new zzflr("CodedInputStream encountered an embedded string or message which claimed to have negative size.");
  }
  
  static zzflr zzc()
  {
    return new zzflr("CodedInputStream encountered a malformed varint.");
  }
  
  static zzflr zzd()
  {
    return new zzflr("Protocol message had too many levels of nesting.  May be malicious.  Use CodedInputStream.setRecursionLimit() to increase the depth limit.");
  }
}

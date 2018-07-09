package bolts;

import android.net.Uri;

public abstract interface AppLinkResolver
{
  public abstract Task<AppLink> getAppLinkFromUrlInBackground(Uri paramUri);
}

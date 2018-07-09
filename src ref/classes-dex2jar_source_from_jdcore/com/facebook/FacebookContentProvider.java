package com.facebook;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.util.Log;
import android.util.Pair;
import com.facebook.internal.NativeAppCallAttachmentStore;
import java.io.FileNotFoundException;
import java.util.UUID;

public class FacebookContentProvider
  extends ContentProvider
{
  private static final String ATTACHMENT_URL_BASE = "content://com.facebook.app.FacebookContentProvider";
  private static final String INVALID_FILE_NAME = "..";
  private static final String TAG = "com.facebook.FacebookContentProvider";
  
  public FacebookContentProvider() {}
  
  public static String getAttachmentUrl(String paramString1, UUID paramUUID, String paramString2)
  {
    return String.format("%s%s/%s/%s", new Object[] { "content://com.facebook.app.FacebookContentProvider", paramString1, paramUUID.toString(), paramString2 });
  }
  
  public int delete(Uri paramUri, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
  
  public String getType(Uri paramUri)
  {
    return null;
  }
  
  public Uri insert(Uri paramUri, ContentValues paramContentValues)
  {
    return null;
  }
  
  public boolean onCreate()
  {
    return true;
  }
  
  public ParcelFileDescriptor openFile(Uri paramUri, String paramString)
    throws FileNotFoundException
  {
    paramUri = parseCallIdAndAttachmentName(paramUri);
    if (paramUri == null) {
      throw new FileNotFoundException();
    }
    try
    {
      paramUri = NativeAppCallAttachmentStore.openAttachment((UUID)first, (String)second);
      if (paramUri == null) {
        throw new FileNotFoundException();
      }
      paramUri = ParcelFileDescriptor.open(paramUri, 268435456);
      return paramUri;
    }
    catch (FileNotFoundException paramUri)
    {
      paramString = TAG;
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("Got unexpected exception:");
      localStringBuilder.append(paramUri);
      Log.e(paramString, localStringBuilder.toString());
      throw paramUri;
    }
  }
  
  Pair<UUID, String> parseCallIdAndAttachmentName(Uri paramUri)
  {
    try
    {
      Object localObject = paramUri.getPath().substring(1).split("/");
      paramUri = localObject[0];
      localObject = localObject[1];
      if ((!"..".contentEquals(paramUri)) && (!"..".contentEquals((CharSequence)localObject))) {
        return new Pair(UUID.fromString(paramUri), localObject);
      }
      throw new Exception();
    }
    catch (Exception paramUri)
    {
      for (;;) {}
    }
    return null;
  }
  
  public Cursor query(Uri paramUri, String[] paramArrayOfString1, String paramString1, String[] paramArrayOfString2, String paramString2)
  {
    return null;
  }
  
  public int update(Uri paramUri, ContentValues paramContentValues, String paramString, String[] paramArrayOfString)
  {
    return 0;
  }
}

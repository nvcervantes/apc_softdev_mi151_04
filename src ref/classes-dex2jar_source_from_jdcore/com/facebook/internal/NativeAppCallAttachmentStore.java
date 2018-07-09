package com.facebook.internal;

import android.content.ContentResolver;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Bitmap.CompressFormat;
import android.net.Uri;
import android.util.Log;
import com.facebook.FacebookContentProvider;
import com.facebook.FacebookException;
import com.facebook.FacebookSdk;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.UUID;

public final class NativeAppCallAttachmentStore
{
  static final String ATTACHMENTS_DIR_NAME = "com.facebook.NativeAppCallAttachmentStore.files";
  private static final String TAG = "com.facebook.internal.NativeAppCallAttachmentStore";
  private static File attachmentsDirectory;
  
  private NativeAppCallAttachmentStore() {}
  
  public static void addAttachments(Collection<Attachment> paramCollection)
  {
    Object localObject1;
    Object localObject2;
    if (paramCollection != null)
    {
      if (paramCollection.size() == 0) {
        return;
      }
      if (attachmentsDirectory == null) {
        cleanupAllAttachments();
      }
      ensureAttachmentsDirectoryExists();
      localObject1 = new ArrayList();
      try
      {
        paramCollection = paramCollection.iterator();
        while (paramCollection.hasNext())
        {
          localObject2 = (Attachment)paramCollection.next();
          if (shouldCreateFile)
          {
            localObject3 = getAttachmentFile(callId, attachmentName, true);
            ((List)localObject1).add(localObject3);
            if (bitmap != null) {
              processAttachmentBitmap(bitmap, (File)localObject3);
            } else if (originalUri != null) {
              processAttachmentFile(originalUri, isContentUri, (File)localObject3);
            }
          }
        }
        return;
      }
      catch (IOException paramCollection)
      {
        localObject2 = TAG;
        Object localObject3 = new StringBuilder();
        ((StringBuilder)localObject3).append("Got unexpected exception:");
        ((StringBuilder)localObject3).append(paramCollection);
        Log.e((String)localObject2, ((StringBuilder)localObject3).toString());
        localObject1 = ((List)localObject1).iterator();
      }
    }
    for (;;)
    {
      if (((Iterator)localObject1).hasNext()) {
        localObject2 = (File)((Iterator)localObject1).next();
      }
      try
      {
        ((File)localObject2).delete();
      }
      catch (Exception localException) {}
      throw new FacebookException(paramCollection);
      return;
    }
  }
  
  public static void cleanupAllAttachments()
  {
    Utility.deleteDirectory(getAttachmentsDirectory());
  }
  
  public static void cleanupAttachmentsForCall(UUID paramUUID)
  {
    paramUUID = getAttachmentsDirectoryForCall(paramUUID, false);
    if (paramUUID != null) {
      Utility.deleteDirectory(paramUUID);
    }
  }
  
  public static Attachment createAttachment(UUID paramUUID, Bitmap paramBitmap)
  {
    Validate.notNull(paramUUID, "callId");
    Validate.notNull(paramBitmap, "attachmentBitmap");
    return new Attachment(paramUUID, paramBitmap, null, null);
  }
  
  public static Attachment createAttachment(UUID paramUUID, Uri paramUri)
  {
    Validate.notNull(paramUUID, "callId");
    Validate.notNull(paramUri, "attachmentUri");
    return new Attachment(paramUUID, null, paramUri, null);
  }
  
  static File ensureAttachmentsDirectoryExists()
  {
    File localFile = getAttachmentsDirectory();
    localFile.mkdirs();
    return localFile;
  }
  
  static File getAttachmentFile(UUID paramUUID, String paramString, boolean paramBoolean)
    throws IOException
  {
    paramUUID = getAttachmentsDirectoryForCall(paramUUID, paramBoolean);
    if (paramUUID == null) {
      return null;
    }
    try
    {
      paramUUID = new File(paramUUID, URLEncoder.encode(paramString, "UTF-8"));
      return paramUUID;
    }
    catch (UnsupportedEncodingException paramUUID) {}
    return null;
  }
  
  static File getAttachmentsDirectory()
  {
    try
    {
      if (attachmentsDirectory == null) {
        attachmentsDirectory = new File(FacebookSdk.getApplicationContext().getCacheDir(), "com.facebook.NativeAppCallAttachmentStore.files");
      }
      File localFile = attachmentsDirectory;
      return localFile;
    }
    finally {}
  }
  
  static File getAttachmentsDirectoryForCall(UUID paramUUID, boolean paramBoolean)
  {
    if (attachmentsDirectory == null) {
      return null;
    }
    paramUUID = new File(attachmentsDirectory, paramUUID.toString());
    if ((paramBoolean) && (!paramUUID.exists())) {
      paramUUID.mkdirs();
    }
    return paramUUID;
  }
  
  public static File openAttachment(UUID paramUUID, String paramString)
    throws FileNotFoundException
  {
    if ((!Utility.isNullOrEmpty(paramString)) && (paramUUID != null)) {}
    try
    {
      paramUUID = getAttachmentFile(paramUUID, paramString, false);
      return paramUUID;
    }
    catch (IOException paramUUID)
    {
      for (;;) {}
    }
    throw new FileNotFoundException();
    throw new FileNotFoundException();
  }
  
  private static void processAttachmentBitmap(Bitmap paramBitmap, File paramFile)
    throws IOException
  {
    paramFile = new FileOutputStream(paramFile);
    try
    {
      paramBitmap.compress(Bitmap.CompressFormat.JPEG, 100, paramFile);
      return;
    }
    finally
    {
      Utility.closeQuietly(paramFile);
    }
  }
  
  private static void processAttachmentFile(Uri paramUri, boolean paramBoolean, File paramFile)
    throws IOException
  {
    paramFile = new FileOutputStream(paramFile);
    if (!paramBoolean) {}
    try
    {
      paramUri = new FileInputStream(paramUri.getPath());
      break label39;
      paramUri = FacebookSdk.getApplicationContext().getContentResolver().openInputStream(paramUri);
      label39:
      Utility.copyAndCloseInputStream(paramUri, paramFile);
      Utility.closeQuietly(paramFile);
      return;
    }
    finally
    {
      for (;;) {}
    }
    Utility.closeQuietly(paramFile);
    throw paramUri;
  }
  
  public static final class Attachment
  {
    private final String attachmentName;
    private final String attachmentUrl;
    private Bitmap bitmap;
    private final UUID callId;
    private boolean isContentUri;
    private Uri originalUri;
    private boolean shouldCreateFile;
    
    private Attachment(UUID paramUUID, Bitmap paramBitmap, Uri paramUri)
    {
      callId = paramUUID;
      bitmap = paramBitmap;
      originalUri = paramUri;
      boolean bool = true;
      if (paramUri != null)
      {
        paramBitmap = paramUri.getScheme();
        if ("content".equalsIgnoreCase(paramBitmap))
        {
          isContentUri = true;
          if ((paramUri.getAuthority() == null) || (paramUri.getAuthority().startsWith("media"))) {
            bool = false;
          }
          shouldCreateFile = bool;
        }
        else if ("file".equalsIgnoreCase(paramUri.getScheme()))
        {
          shouldCreateFile = true;
        }
        else if (!Utility.isWebUri(paramUri))
        {
          paramUUID = new StringBuilder();
          paramUUID.append("Unsupported scheme for media Uri : ");
          paramUUID.append(paramBitmap);
          throw new FacebookException(paramUUID.toString());
        }
      }
      else
      {
        if (paramBitmap == null) {
          break label208;
        }
        shouldCreateFile = true;
      }
      if (!shouldCreateFile) {
        paramBitmap = null;
      } else {
        paramBitmap = UUID.randomUUID().toString();
      }
      attachmentName = paramBitmap;
      if (!shouldCreateFile) {
        paramUUID = originalUri.toString();
      } else {
        paramUUID = FacebookContentProvider.getAttachmentUrl(FacebookSdk.getApplicationId(), paramUUID, attachmentName);
      }
      attachmentUrl = paramUUID;
      return;
      label208:
      throw new FacebookException("Cannot share media without a bitmap or Uri set");
    }
    
    public String getAttachmentUrl()
    {
      return attachmentUrl;
    }
  }
}

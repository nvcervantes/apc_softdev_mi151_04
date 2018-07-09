package android.support.v4.provider;

import android.content.Context;
import android.net.Uri;
import android.os.Build.VERSION;
import android.provider.DocumentsContract;
import java.io.File;

public abstract class DocumentFile
{
  static final String TAG = "DocumentFile";
  private final DocumentFile mParent;
  
  DocumentFile(DocumentFile paramDocumentFile)
  {
    mParent = paramDocumentFile;
  }
  
  public static DocumentFile fromFile(File paramFile)
  {
    return new RawDocumentFile(null, paramFile);
  }
  
  public static DocumentFile fromSingleUri(Context paramContext, Uri paramUri)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return new SingleDocumentFile(null, paramContext, paramUri);
    }
    return null;
  }
  
  public static DocumentFile fromTreeUri(Context paramContext, Uri paramUri)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return new TreeDocumentFile(null, paramContext, DocumentsContract.buildDocumentUriUsingTree(paramUri, DocumentsContract.getTreeDocumentId(paramUri)));
    }
    return null;
  }
  
  public static boolean isDocumentUri(Context paramContext, Uri paramUri)
  {
    if (Build.VERSION.SDK_INT >= 19) {
      return DocumentsContractApi19.isDocumentUri(paramContext, paramUri);
    }
    return false;
  }
  
  public abstract boolean canRead();
  
  public abstract boolean canWrite();
  
  public abstract DocumentFile createDirectory(String paramString);
  
  public abstract DocumentFile createFile(String paramString1, String paramString2);
  
  public abstract boolean delete();
  
  public abstract boolean exists();
  
  public DocumentFile findFile(String paramString)
  {
    DocumentFile[] arrayOfDocumentFile = listFiles();
    int i = 0;
    int j = arrayOfDocumentFile.length;
    while (i < j)
    {
      DocumentFile localDocumentFile = arrayOfDocumentFile[i];
      if (paramString.equals(localDocumentFile.getName())) {
        return localDocumentFile;
      }
      i += 1;
    }
    return null;
  }
  
  public abstract String getName();
  
  public DocumentFile getParentFile()
  {
    return mParent;
  }
  
  public abstract String getType();
  
  public abstract Uri getUri();
  
  public abstract boolean isDirectory();
  
  public abstract boolean isFile();
  
  public abstract boolean isVirtual();
  
  public abstract long lastModified();
  
  public abstract long length();
  
  public abstract DocumentFile[] listFiles();
  
  public abstract boolean renameTo(String paramString);
}

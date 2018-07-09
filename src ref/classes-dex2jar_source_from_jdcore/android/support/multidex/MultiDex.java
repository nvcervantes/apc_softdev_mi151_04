package android.support.multidex;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.util.Log;
import dalvik.system.DexFile;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.zip.ZipFile;

public final class MultiDex
{
  private static final String CODE_CACHE_NAME = "code_cache";
  private static final String CODE_CACHE_SECONDARY_FOLDER_NAME = "secondary-dexes";
  private static final boolean IS_VM_MULTIDEX_CAPABLE = isVMMultidexCapable(System.getProperty("java.vm.version"));
  private static final int MAX_SUPPORTED_SDK_VERSION = 20;
  private static final int MIN_SDK_VERSION = 4;
  private static final String NO_KEY_PREFIX = "";
  private static final String OLD_SECONDARY_FOLDER_NAME = "secondary-dexes";
  static final String TAG = "MultiDex";
  private static final int VM_WITH_MULTIDEX_VERSION_MAJOR = 2;
  private static final int VM_WITH_MULTIDEX_VERSION_MINOR = 1;
  private static final Set<File> installedApk = new HashSet();
  
  private MultiDex() {}
  
  private static void clearOldDexDir(Context paramContext)
    throws Exception
  {
    paramContext = new File(paramContext.getFilesDir(), "secondary-dexes");
    if (paramContext.isDirectory())
    {
      Object localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Clearing old secondary dex dir (");
      ((StringBuilder)localObject1).append(paramContext.getPath());
      ((StringBuilder)localObject1).append(").");
      Log.i("MultiDex", ((StringBuilder)localObject1).toString());
      localObject1 = paramContext.listFiles();
      if (localObject1 == null)
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Failed to list secondary dex dir content (");
        ((StringBuilder)localObject1).append(paramContext.getPath());
        ((StringBuilder)localObject1).append(").");
        Log.w("MultiDex", ((StringBuilder)localObject1).toString());
        return;
      }
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        Object localObject2 = localObject1[i];
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Trying to delete old file ");
        localStringBuilder.append(localObject2.getPath());
        localStringBuilder.append(" of size ");
        localStringBuilder.append(localObject2.length());
        Log.i("MultiDex", localStringBuilder.toString());
        if (!localObject2.delete())
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Failed to delete old file ");
          localStringBuilder.append(localObject2.getPath());
          Log.w("MultiDex", localStringBuilder.toString());
        }
        else
        {
          localStringBuilder = new StringBuilder();
          localStringBuilder.append("Deleted old file ");
          localStringBuilder.append(localObject2.getPath());
          Log.i("MultiDex", localStringBuilder.toString());
        }
        i += 1;
      }
      if (!paramContext.delete())
      {
        localObject1 = new StringBuilder();
        ((StringBuilder)localObject1).append("Failed to delete secondary dex dir ");
        ((StringBuilder)localObject1).append(paramContext.getPath());
        Log.w("MultiDex", ((StringBuilder)localObject1).toString());
        return;
      }
      localObject1 = new StringBuilder();
      ((StringBuilder)localObject1).append("Deleted old secondary dex dir ");
      ((StringBuilder)localObject1).append(paramContext.getPath());
      Log.i("MultiDex", ((StringBuilder)localObject1).toString());
    }
  }
  
  /* Error */
  private static void doInstallation(Context paramContext, File paramFile1, File paramFile2, String paramString1, String paramString2, boolean paramBoolean)
    throws IOException, IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, SecurityException, ClassNotFoundException, InstantiationException
  {
    // Byte code:
    //   0: getstatic 62	android/support/multidex/MultiDex:installedApk	Ljava/util/Set;
    //   3: astore 6
    //   5: aload 6
    //   7: monitorenter
    //   8: getstatic 62	android/support/multidex/MultiDex:installedApk	Ljava/util/Set;
    //   11: aload_1
    //   12: invokeinterface 198 2 0
    //   17: ifeq +7 -> 24
    //   20: aload 6
    //   22: monitorexit
    //   23: return
    //   24: getstatic 62	android/support/multidex/MultiDex:installedApk	Ljava/util/Set;
    //   27: aload_1
    //   28: invokeinterface 201 2 0
    //   33: pop
    //   34: getstatic 206	android/os/Build$VERSION:SDK_INT	I
    //   37: bipush 20
    //   39: if_icmple +99 -> 138
    //   42: new 123	java/lang/StringBuilder
    //   45: dup
    //   46: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   49: astore 7
    //   51: aload 7
    //   53: ldc -48
    //   55: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   58: pop
    //   59: aload 7
    //   61: getstatic 206	android/os/Build$VERSION:SDK_INT	I
    //   64: invokevirtual 211	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   67: pop
    //   68: aload 7
    //   70: ldc -43
    //   72: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: pop
    //   76: aload 7
    //   78: bipush 20
    //   80: invokevirtual 211	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   83: pop
    //   84: aload 7
    //   86: ldc -41
    //   88: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   91: pop
    //   92: aload 7
    //   94: ldc -39
    //   96: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   99: pop
    //   100: aload 7
    //   102: ldc -37
    //   104: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   107: pop
    //   108: aload 7
    //   110: ldc 64
    //   112: invokestatic 70	java/lang/System:getProperty	(Ljava/lang/String;)Ljava/lang/String;
    //   115: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   118: pop
    //   119: aload 7
    //   121: ldc -35
    //   123: invokevirtual 130	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   126: pop
    //   127: ldc 46
    //   129: aload 7
    //   131: invokevirtual 139	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   134: invokestatic 154	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   137: pop
    //   138: aload_0
    //   139: invokevirtual 225	android/content/Context:getClassLoader	()Ljava/lang/ClassLoader;
    //   142: astore 7
    //   144: aload 7
    //   146: ifnonnull +15 -> 161
    //   149: ldc 46
    //   151: ldc -29
    //   153: invokestatic 230	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   156: pop
    //   157: aload 6
    //   159: monitorexit
    //   160: return
    //   161: aload_0
    //   162: invokestatic 232	android/support/multidex/MultiDex:clearOldDexDir	(Landroid/content/Context;)V
    //   165: goto +15 -> 180
    //   168: astore 8
    //   170: ldc 46
    //   172: ldc -22
    //   174: aload 8
    //   176: invokestatic 237	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   179: pop
    //   180: aload_0
    //   181: aload_2
    //   182: aload_3
    //   183: invokestatic 241	android/support/multidex/MultiDex:getDexDir	(Landroid/content/Context;Ljava/io/File;Ljava/lang/String;)Ljava/io/File;
    //   186: astore_2
    //   187: new 243	android/support/multidex/MultiDexExtractor
    //   190: dup
    //   191: aload_1
    //   192: aload_2
    //   193: invokespecial 246	android/support/multidex/MultiDexExtractor:<init>	(Ljava/io/File;Ljava/io/File;)V
    //   196: astore_3
    //   197: aconst_null
    //   198: astore_1
    //   199: aload_3
    //   200: aload_0
    //   201: aload 4
    //   203: iconst_0
    //   204: invokevirtual 250	android/support/multidex/MultiDexExtractor:load	(Landroid/content/Context;Ljava/lang/String;Z)Ljava/util/List;
    //   207: astore 8
    //   209: aload 7
    //   211: aload_2
    //   212: aload 8
    //   214: invokestatic 254	android/support/multidex/MultiDex:installSecondaryDexes	(Ljava/lang/ClassLoader;Ljava/io/File;Ljava/util/List;)V
    //   217: goto +38 -> 255
    //   220: astore 8
    //   222: iload 5
    //   224: ifne +6 -> 230
    //   227: aload 8
    //   229: athrow
    //   230: ldc 46
    //   232: ldc_w 256
    //   235: aload 8
    //   237: invokestatic 237	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   240: pop
    //   241: aload 7
    //   243: aload_2
    //   244: aload_3
    //   245: aload_0
    //   246: aload 4
    //   248: iconst_1
    //   249: invokevirtual 250	android/support/multidex/MultiDexExtractor:load	(Landroid/content/Context;Ljava/lang/String;Z)Ljava/util/List;
    //   252: invokestatic 254	android/support/multidex/MultiDex:installSecondaryDexes	(Ljava/lang/ClassLoader;Ljava/io/File;Ljava/util/List;)V
    //   255: aload_3
    //   256: invokevirtual 259	android/support/multidex/MultiDexExtractor:close	()V
    //   259: aload_1
    //   260: astore_0
    //   261: goto +4 -> 265
    //   264: astore_0
    //   265: aload_0
    //   266: ifnull +5 -> 271
    //   269: aload_0
    //   270: athrow
    //   271: aload 6
    //   273: monitorexit
    //   274: return
    //   275: astore_0
    //   276: aload_3
    //   277: invokevirtual 259	android/support/multidex/MultiDexExtractor:close	()V
    //   280: aload_0
    //   281: athrow
    //   282: astore_0
    //   283: ldc 46
    //   285: ldc_w 261
    //   288: aload_0
    //   289: invokestatic 237	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   292: pop
    //   293: aload 6
    //   295: monitorexit
    //   296: return
    //   297: astore_0
    //   298: aload 6
    //   300: monitorexit
    //   301: aload_0
    //   302: athrow
    //   303: astore_1
    //   304: goto -24 -> 280
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	307	0	paramContext	Context
    //   0	307	1	paramFile1	File
    //   0	307	2	paramFile2	File
    //   0	307	3	paramString1	String
    //   0	307	4	paramString2	String
    //   0	307	5	paramBoolean	boolean
    //   3	296	6	localSet	Set
    //   49	193	7	localObject	Object
    //   168	7	8	localThrowable	Throwable
    //   207	6	8	localList	List
    //   220	16	8	localIOException	IOException
    // Exception table:
    //   from	to	target	type
    //   161	165	168	java/lang/Throwable
    //   209	217	220	java/io/IOException
    //   255	259	264	java/io/IOException
    //   199	209	275	finally
    //   209	217	275	finally
    //   227	230	275	finally
    //   230	255	275	finally
    //   138	144	282	java/lang/RuntimeException
    //   8	23	297	finally
    //   24	138	297	finally
    //   138	144	297	finally
    //   149	160	297	finally
    //   161	165	297	finally
    //   170	180	297	finally
    //   180	197	297	finally
    //   255	259	297	finally
    //   269	271	297	finally
    //   271	274	297	finally
    //   276	280	297	finally
    //   280	282	297	finally
    //   283	296	297	finally
    //   298	301	297	finally
    //   276	280	303	java/io/IOException
  }
  
  private static void expandFieldArray(Object paramObject, String paramString, Object[] paramArrayOfObject)
    throws NoSuchFieldException, IllegalArgumentException, IllegalAccessException
  {
    paramString = findField(paramObject, paramString);
    Object[] arrayOfObject1 = (Object[])paramString.get(paramObject);
    Object[] arrayOfObject2 = (Object[])Array.newInstance(arrayOfObject1.getClass().getComponentType(), arrayOfObject1.length + paramArrayOfObject.length);
    System.arraycopy(arrayOfObject1, 0, arrayOfObject2, 0, arrayOfObject1.length);
    System.arraycopy(paramArrayOfObject, 0, arrayOfObject2, arrayOfObject1.length, paramArrayOfObject.length);
    paramString.set(paramObject, arrayOfObject2);
  }
  
  private static Field findField(Object paramObject, String paramString)
    throws NoSuchFieldException
  {
    for (Object localObject = paramObject.getClass(); localObject != null; localObject = ((Class)localObject).getSuperclass()) {
      try
      {
        Field localField = ((Class)localObject).getDeclaredField(paramString);
        if (!localField.isAccessible()) {
          localField.setAccessible(true);
        }
        return localField;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        for (;;) {}
      }
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Field ");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(" not found in ");
    ((StringBuilder)localObject).append(paramObject.getClass());
    throw new NoSuchFieldException(((StringBuilder)localObject).toString());
  }
  
  private static Method findMethod(Object paramObject, String paramString, Class<?>... paramVarArgs)
    throws NoSuchMethodException
  {
    for (Object localObject = paramObject.getClass(); localObject != null; localObject = ((Class)localObject).getSuperclass()) {
      try
      {
        Method localMethod = ((Class)localObject).getDeclaredMethod(paramString, paramVarArgs);
        if (!localMethod.isAccessible()) {
          localMethod.setAccessible(true);
        }
        return localMethod;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        for (;;) {}
      }
    }
    localObject = new StringBuilder();
    ((StringBuilder)localObject).append("Method ");
    ((StringBuilder)localObject).append(paramString);
    ((StringBuilder)localObject).append(" with parameters ");
    ((StringBuilder)localObject).append(Arrays.asList(paramVarArgs));
    ((StringBuilder)localObject).append(" not found in ");
    ((StringBuilder)localObject).append(paramObject.getClass());
    throw new NoSuchMethodException(((StringBuilder)localObject).toString());
  }
  
  private static ApplicationInfo getApplicationInfo(Context paramContext)
  {
    try
    {
      paramContext = paramContext.getApplicationInfo();
      return paramContext;
    }
    catch (RuntimeException paramContext)
    {
      Log.w("MultiDex", "Failure while trying to obtain ApplicationInfo from Context. Must be running in test mode. Skip patching.", paramContext);
    }
    return null;
  }
  
  private static File getDexDir(Context paramContext, File paramFile, String paramString)
    throws IOException
  {
    paramFile = new File(paramFile, "code_cache");
    try
    {
      mkdirChecked(paramFile);
      paramContext = paramFile;
    }
    catch (IOException paramFile)
    {
      for (;;) {}
    }
    paramContext = new File(paramContext.getFilesDir(), "code_cache");
    mkdirChecked(paramContext);
    paramContext = new File(paramContext, paramString);
    mkdirChecked(paramContext);
    return paramContext;
  }
  
  public static void install(Context paramContext)
  {
    Log.i("MultiDex", "Installing application");
    if (IS_VM_MULTIDEX_CAPABLE)
    {
      Log.i("MultiDex", "VM has multidex support, MultiDex support library is disabled.");
      return;
    }
    if (Build.VERSION.SDK_INT < 4)
    {
      paramContext = new StringBuilder();
      paramContext.append("MultiDex installation failed. SDK ");
      paramContext.append(Build.VERSION.SDK_INT);
      paramContext.append(" is unsupported. Min SDK version is ");
      paramContext.append(4);
      paramContext.append(".");
      throw new RuntimeException(paramContext.toString());
    }
    try
    {
      localObject = getApplicationInfo(paramContext);
      if (localObject == null)
      {
        Log.i("MultiDex", "No ApplicationInfo available, i.e. running on a test Context: MultiDex support library is disabled.");
        return;
      }
      doInstallation(paramContext, new File(sourceDir), new File(dataDir), "secondary-dexes", "", true);
      Log.i("MultiDex", "install done");
      return;
    }
    catch (Exception paramContext)
    {
      Log.e("MultiDex", "MultiDex installation failure", paramContext);
      Object localObject = new StringBuilder();
      ((StringBuilder)localObject).append("MultiDex installation failed (");
      ((StringBuilder)localObject).append(paramContext.getMessage());
      ((StringBuilder)localObject).append(").");
      throw new RuntimeException(((StringBuilder)localObject).toString());
    }
  }
  
  public static void installInstrumentation(Context paramContext1, Context paramContext2)
  {
    Log.i("MultiDex", "Installing instrumentation");
    if (IS_VM_MULTIDEX_CAPABLE)
    {
      Log.i("MultiDex", "VM has multidex support, MultiDex support library is disabled.");
      return;
    }
    if (Build.VERSION.SDK_INT < 4)
    {
      paramContext1 = new StringBuilder();
      paramContext1.append("MultiDex installation failed. SDK ");
      paramContext1.append(Build.VERSION.SDK_INT);
      paramContext1.append(" is unsupported. Min SDK version is ");
      paramContext1.append(4);
      paramContext1.append(".");
      throw new RuntimeException(paramContext1.toString());
    }
    try
    {
      Object localObject1 = getApplicationInfo(paramContext1);
      if (localObject1 == null)
      {
        Log.i("MultiDex", "No ApplicationInfo available for instrumentation, i.e. running on a test Context: MultiDex support library is disabled.");
        return;
      }
      ApplicationInfo localApplicationInfo = getApplicationInfo(paramContext2);
      if (localApplicationInfo == null)
      {
        Log.i("MultiDex", "No ApplicationInfo available, i.e. running on a test Context: MultiDex support library is disabled.");
        return;
      }
      Object localObject2 = new StringBuilder();
      ((StringBuilder)localObject2).append(paramContext1.getPackageName());
      ((StringBuilder)localObject2).append(".");
      paramContext1 = ((StringBuilder)localObject2).toString();
      localObject2 = new File(dataDir);
      localObject1 = new File(sourceDir);
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramContext1);
      localStringBuilder.append("secondary-dexes");
      doInstallation(paramContext2, (File)localObject1, (File)localObject2, localStringBuilder.toString(), paramContext1, false);
      doInstallation(paramContext2, new File(sourceDir), (File)localObject2, "secondary-dexes", "", false);
      Log.i("MultiDex", "Installation done");
      return;
    }
    catch (Exception paramContext1)
    {
      Log.e("MultiDex", "MultiDex installation failure", paramContext1);
      paramContext2 = new StringBuilder();
      paramContext2.append("MultiDex installation failed (");
      paramContext2.append(paramContext1.getMessage());
      paramContext2.append(").");
      throw new RuntimeException(paramContext2.toString());
    }
  }
  
  private static void installSecondaryDexes(ClassLoader paramClassLoader, File paramFile, List<? extends File> paramList)
    throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException, SecurityException, ClassNotFoundException, InstantiationException
  {
    if (!paramList.isEmpty())
    {
      if (Build.VERSION.SDK_INT >= 19)
      {
        V19.install(paramClassLoader, paramList, paramFile);
        return;
      }
      if (Build.VERSION.SDK_INT >= 14)
      {
        V14.install(paramClassLoader, paramList);
        return;
      }
      V4.install(paramClassLoader, paramList);
    }
  }
  
  static boolean isVMMultidexCapable(String paramString)
  {
    bool2 = false;
    bool1 = bool2;
    if (paramString != null)
    {
      localObject = Pattern.compile("(\\d+)\\.(\\d+)(\\.\\d+)?").matcher(paramString);
      bool1 = bool2;
      if (!((Matcher)localObject).matches()) {}
    }
    try
    {
      int i = Integer.parseInt(((Matcher)localObject).group(1));
      int j = Integer.parseInt(((Matcher)localObject).group(2));
      if (i <= 2)
      {
        bool1 = bool2;
        if (i == 2)
        {
          bool1 = bool2;
          if (j < 1) {}
        }
      }
      else
      {
        bool1 = true;
      }
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;)
      {
        bool1 = bool2;
      }
    }
    Object localObject = new StringBuilder();
    ((StringBuilder)localObject).append("VM with version ");
    ((StringBuilder)localObject).append(paramString);
    if (bool1) {
      paramString = " has multidex support";
    } else {
      paramString = " does not have multidex support";
    }
    ((StringBuilder)localObject).append(paramString);
    Log.i("MultiDex", ((StringBuilder)localObject).toString());
    return bool1;
  }
  
  private static void mkdirChecked(File paramFile)
    throws IOException
  {
    paramFile.mkdir();
    if (!paramFile.isDirectory())
    {
      Object localObject = paramFile.getParentFile();
      if (localObject == null)
      {
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append("Failed to create dir ");
        ((StringBuilder)localObject).append(paramFile.getPath());
        ((StringBuilder)localObject).append(". Parent file is null.");
        Log.e("MultiDex", ((StringBuilder)localObject).toString());
      }
      else
      {
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append("Failed to create dir ");
        localStringBuilder.append(paramFile.getPath());
        localStringBuilder.append(". parent file is a dir ");
        localStringBuilder.append(((File)localObject).isDirectory());
        localStringBuilder.append(", a file ");
        localStringBuilder.append(((File)localObject).isFile());
        localStringBuilder.append(", exists ");
        localStringBuilder.append(((File)localObject).exists());
        localStringBuilder.append(", readable ");
        localStringBuilder.append(((File)localObject).canRead());
        localStringBuilder.append(", writable ");
        localStringBuilder.append(((File)localObject).canWrite());
        Log.e("MultiDex", localStringBuilder.toString());
      }
      localObject = new StringBuilder();
      ((StringBuilder)localObject).append("Failed to create directory ");
      ((StringBuilder)localObject).append(paramFile.getPath());
      throw new IOException(((StringBuilder)localObject).toString());
    }
  }
  
  private static final class V14
  {
    private static final int EXTRACTED_SUFFIX_LENGTH = ".zip".length();
    private final ElementConstructor elementConstructor;
    
    private V14()
      throws ClassNotFoundException, SecurityException, NoSuchMethodException
    {
      Class localClass = Class.forName("dalvik.system.DexPathList$Element");
      for (;;)
      {
        try
        {
          localObject = new ICSElementConstructor(localClass);
        }
        catch (NoSuchMethodException localNoSuchMethodException1)
        {
          Object localObject;
          continue;
        }
        try
        {
          localObject = new JBMR11ElementConstructor(localClass);
        }
        catch (NoSuchMethodException localNoSuchMethodException2) {}
      }
      localObject = new JBMR2ElementConstructor(localClass);
      elementConstructor = ((ElementConstructor)localObject);
    }
    
    static void install(ClassLoader paramClassLoader, List<? extends File> paramList)
      throws IOException, SecurityException, IllegalArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, InvocationTargetException, NoSuchFieldException
    {
      paramClassLoader = MultiDex.findField(paramClassLoader, "pathList").get(paramClassLoader);
      paramList = new V14().makeDexElements(paramList);
      try
      {
        MultiDex.expandFieldArray(paramClassLoader, "dexElements", paramList);
        return;
      }
      catch (NoSuchFieldException localNoSuchFieldException)
      {
        Log.w("MultiDex", "Failed find field 'dexElements' attempting 'pathElements'", localNoSuchFieldException);
        MultiDex.expandFieldArray(paramClassLoader, "pathElements", paramList);
      }
    }
    
    private Object[] makeDexElements(List<? extends File> paramList)
      throws IOException, SecurityException, IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
    {
      Object[] arrayOfObject = new Object[paramList.size()];
      int i = 0;
      while (i < arrayOfObject.length)
      {
        File localFile = (File)paramList.get(i);
        arrayOfObject[i] = elementConstructor.newInstance(localFile, DexFile.loadDex(localFile.getPath(), optimizedPathFor(localFile), 0));
        i += 1;
      }
      return arrayOfObject;
    }
    
    private static String optimizedPathFor(File paramFile)
    {
      File localFile = paramFile.getParentFile();
      paramFile = paramFile.getName();
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append(paramFile.substring(0, paramFile.length() - EXTRACTED_SUFFIX_LENGTH));
      localStringBuilder.append(".dex");
      return new File(localFile, localStringBuilder.toString()).getPath();
    }
    
    private static abstract interface ElementConstructor
    {
      public abstract Object newInstance(File paramFile, DexFile paramDexFile)
        throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException;
    }
    
    private static class ICSElementConstructor
      implements MultiDex.V14.ElementConstructor
    {
      private final Constructor<?> elementConstructor;
      
      ICSElementConstructor(Class<?> paramClass)
        throws SecurityException, NoSuchMethodException
      {
        elementConstructor = paramClass.getConstructor(new Class[] { File.class, ZipFile.class, DexFile.class });
        elementConstructor.setAccessible(true);
      }
      
      public Object newInstance(File paramFile, DexFile paramDexFile)
        throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException, IOException
      {
        return elementConstructor.newInstance(new Object[] { paramFile, new ZipFile(paramFile), paramDexFile });
      }
    }
    
    private static class JBMR11ElementConstructor
      implements MultiDex.V14.ElementConstructor
    {
      private final Constructor<?> elementConstructor;
      
      JBMR11ElementConstructor(Class<?> paramClass)
        throws SecurityException, NoSuchMethodException
      {
        elementConstructor = paramClass.getConstructor(new Class[] { File.class, File.class, DexFile.class });
        elementConstructor.setAccessible(true);
      }
      
      public Object newInstance(File paramFile, DexFile paramDexFile)
        throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
      {
        return elementConstructor.newInstance(new Object[] { paramFile, paramFile, paramDexFile });
      }
    }
    
    private static class JBMR2ElementConstructor
      implements MultiDex.V14.ElementConstructor
    {
      private final Constructor<?> elementConstructor;
      
      JBMR2ElementConstructor(Class<?> paramClass)
        throws SecurityException, NoSuchMethodException
      {
        elementConstructor = paramClass.getConstructor(new Class[] { File.class, Boolean.TYPE, File.class, DexFile.class });
        elementConstructor.setAccessible(true);
      }
      
      public Object newInstance(File paramFile, DexFile paramDexFile)
        throws IllegalArgumentException, InstantiationException, IllegalAccessException, InvocationTargetException
      {
        return elementConstructor.newInstance(new Object[] { paramFile, Boolean.FALSE, paramFile, paramDexFile });
      }
    }
  }
  
  private static final class V19
  {
    private V19() {}
    
    static void install(ClassLoader paramClassLoader, List<? extends File> paramList, File paramFile)
      throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, InvocationTargetException, NoSuchMethodException, IOException
    {
      Object localObject = MultiDex.findField(paramClassLoader, "pathList").get(paramClassLoader);
      ArrayList localArrayList = new ArrayList();
      MultiDex.expandFieldArray(localObject, "dexElements", makeDexElements(localObject, new ArrayList(paramList), paramFile, localArrayList));
      if (localArrayList.size() > 0)
      {
        paramClassLoader = localArrayList.iterator();
        while (paramClassLoader.hasNext()) {
          Log.w("MultiDex", "Exception in makeDexElement", (IOException)paramClassLoader.next());
        }
        paramList = MultiDex.findField(localObject, "dexElementsSuppressedExceptions");
        paramFile = (IOException[])paramList.get(localObject);
        if (paramFile == null)
        {
          paramClassLoader = (IOException[])localArrayList.toArray(new IOException[localArrayList.size()]);
        }
        else
        {
          paramClassLoader = new IOException[localArrayList.size() + paramFile.length];
          localArrayList.toArray(paramClassLoader);
          System.arraycopy(paramFile, 0, paramClassLoader, localArrayList.size(), paramFile.length);
        }
        paramList.set(localObject, paramClassLoader);
        paramClassLoader = new IOException("I/O exception during makeDexElement");
        paramClassLoader.initCause((Throwable)localArrayList.get(0));
        throw paramClassLoader;
      }
    }
    
    private static Object[] makeDexElements(Object paramObject, ArrayList<File> paramArrayList, File paramFile, ArrayList<IOException> paramArrayList1)
      throws IllegalAccessException, InvocationTargetException, NoSuchMethodException
    {
      return (Object[])MultiDex.findMethod(paramObject, "makeDexElements", new Class[] { ArrayList.class, File.class, ArrayList.class }).invoke(paramObject, new Object[] { paramArrayList, paramFile, paramArrayList1 });
    }
  }
  
  private static final class V4
  {
    private V4() {}
    
    static void install(ClassLoader paramClassLoader, List<? extends File> paramList)
      throws IllegalArgumentException, IllegalAccessException, NoSuchFieldException, IOException
    {
      int i = paramList.size();
      Field localField = MultiDex.findField(paramClassLoader, "path");
      StringBuilder localStringBuilder = new StringBuilder((String)localField.get(paramClassLoader));
      String[] arrayOfString = new String[i];
      File[] arrayOfFile = new File[i];
      ZipFile[] arrayOfZipFile = new ZipFile[i];
      DexFile[] arrayOfDexFile = new DexFile[i];
      paramList = paramList.listIterator();
      while (paramList.hasNext())
      {
        Object localObject = (File)paramList.next();
        String str = ((File)localObject).getAbsolutePath();
        localStringBuilder.append(':');
        localStringBuilder.append(str);
        i = paramList.previousIndex();
        arrayOfString[i] = str;
        arrayOfFile[i] = localObject;
        arrayOfZipFile[i] = new ZipFile((File)localObject);
        localObject = new StringBuilder();
        ((StringBuilder)localObject).append(str);
        ((StringBuilder)localObject).append(".dex");
        arrayOfDexFile[i] = DexFile.loadDex(str, ((StringBuilder)localObject).toString(), 0);
      }
      localField.set(paramClassLoader, localStringBuilder.toString());
      MultiDex.expandFieldArray(paramClassLoader, "mPaths", arrayOfString);
      MultiDex.expandFieldArray(paramClassLoader, "mFiles", arrayOfFile);
      MultiDex.expandFieldArray(paramClassLoader, "mZips", arrayOfZipFile);
      MultiDex.expandFieldArray(paramClassLoader, "mDexs", arrayOfDexFile);
    }
  }
}

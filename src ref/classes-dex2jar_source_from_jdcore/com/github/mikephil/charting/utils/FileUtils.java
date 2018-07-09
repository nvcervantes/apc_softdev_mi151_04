package com.github.mikephil.charting.utils;

import android.os.Environment;
import android.util.Log;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.data.Entry;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileUtils
{
  private static final String LOG = "MPChart-FileUtils";
  
  public FileUtils() {}
  
  /* Error */
  public static List<BarEntry> loadBarEntriesFromAssets(android.content.res.AssetManager paramAssetManager, String paramString)
  {
    // Byte code:
    //   0: new 19	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 20	java/util/ArrayList:<init>	()V
    //   7: astore 4
    //   9: aconst_null
    //   10: astore_3
    //   11: aconst_null
    //   12: astore_2
    //   13: new 22	java/io/BufferedReader
    //   16: dup
    //   17: new 24	java/io/InputStreamReader
    //   20: dup
    //   21: aload_0
    //   22: aload_1
    //   23: invokevirtual 30	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   26: ldc 32
    //   28: invokespecial 35	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   31: invokespecial 38	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   34: astore_0
    //   35: aload_0
    //   36: invokevirtual 42	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   39: astore_1
    //   40: aload_1
    //   41: ifnull +45 -> 86
    //   44: aload_1
    //   45: ldc 44
    //   47: invokevirtual 50	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   50: astore_1
    //   51: aload 4
    //   53: new 52	com/github/mikephil/charting/data/BarEntry
    //   56: dup
    //   57: aload_1
    //   58: iconst_1
    //   59: aaload
    //   60: invokestatic 58	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   63: aload_1
    //   64: iconst_0
    //   65: aaload
    //   66: invokestatic 58	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   69: invokespecial 61	com/github/mikephil/charting/data/BarEntry:<init>	(FF)V
    //   72: invokeinterface 67 2 0
    //   77: pop
    //   78: aload_0
    //   79: invokevirtual 42	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   82: astore_1
    //   83: goto -43 -> 40
    //   86: aload_0
    //   87: ifnull +61 -> 148
    //   90: aload_0
    //   91: invokevirtual 70	java/io/BufferedReader:close	()V
    //   94: aload 4
    //   96: areturn
    //   97: astore_1
    //   98: goto +53 -> 151
    //   101: astore_1
    //   102: goto +12 -> 114
    //   105: astore_1
    //   106: aload_2
    //   107: astore_0
    //   108: goto +43 -> 151
    //   111: astore_1
    //   112: aload_3
    //   113: astore_0
    //   114: aload_0
    //   115: astore_2
    //   116: ldc 8
    //   118: aload_1
    //   119: invokevirtual 73	java/io/IOException:toString	()Ljava/lang/String;
    //   122: invokestatic 79	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   125: pop
    //   126: aload_0
    //   127: ifnull +21 -> 148
    //   130: aload_0
    //   131: invokevirtual 70	java/io/BufferedReader:close	()V
    //   134: aload 4
    //   136: areturn
    //   137: astore_0
    //   138: ldc 8
    //   140: aload_0
    //   141: invokevirtual 73	java/io/IOException:toString	()Ljava/lang/String;
    //   144: invokestatic 79	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   147: pop
    //   148: aload 4
    //   150: areturn
    //   151: aload_0
    //   152: ifnull +21 -> 173
    //   155: aload_0
    //   156: invokevirtual 70	java/io/BufferedReader:close	()V
    //   159: goto +14 -> 173
    //   162: astore_0
    //   163: ldc 8
    //   165: aload_0
    //   166: invokevirtual 73	java/io/IOException:toString	()Ljava/lang/String;
    //   169: invokestatic 79	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   172: pop
    //   173: aload_1
    //   174: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	175	0	paramAssetManager	android.content.res.AssetManager
    //   0	175	1	paramString	String
    //   12	104	2	localAssetManager	android.content.res.AssetManager
    //   10	103	3	localObject	Object
    //   7	142	4	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   35	40	97	finally
    //   44	83	97	finally
    //   35	40	101	java/io/IOException
    //   44	83	101	java/io/IOException
    //   13	35	105	finally
    //   116	126	105	finally
    //   13	35	111	java/io/IOException
    //   90	94	137	java/io/IOException
    //   130	134	137	java/io/IOException
    //   155	159	162	java/io/IOException
  }
  
  /* Error */
  public static List<Entry> loadEntriesFromAssets(android.content.res.AssetManager paramAssetManager, String paramString)
  {
    // Byte code:
    //   0: new 19	java/util/ArrayList
    //   3: dup
    //   4: invokespecial 20	java/util/ArrayList:<init>	()V
    //   7: astore 6
    //   9: aconst_null
    //   10: astore 5
    //   12: aconst_null
    //   13: astore 4
    //   15: new 22	java/io/BufferedReader
    //   18: dup
    //   19: new 24	java/io/InputStreamReader
    //   22: dup
    //   23: aload_0
    //   24: aload_1
    //   25: invokevirtual 30	android/content/res/AssetManager:open	(Ljava/lang/String;)Ljava/io/InputStream;
    //   28: ldc 32
    //   30: invokespecial 35	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;Ljava/lang/String;)V
    //   33: invokespecial 38	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   36: astore_0
    //   37: aload_0
    //   38: invokevirtual 42	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   41: astore_1
    //   42: aload_1
    //   43: ifnull +117 -> 160
    //   46: aload_1
    //   47: ldc 44
    //   49: invokevirtual 50	java/lang/String:split	(Ljava/lang/String;)[Ljava/lang/String;
    //   52: astore_1
    //   53: aload_1
    //   54: arraylength
    //   55: istore_3
    //   56: iconst_0
    //   57: istore_2
    //   58: iload_3
    //   59: iconst_2
    //   60: if_icmpgt +33 -> 93
    //   63: aload 6
    //   65: new 84	com/github/mikephil/charting/data/Entry
    //   68: dup
    //   69: aload_1
    //   70: iconst_1
    //   71: aaload
    //   72: invokestatic 58	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   75: aload_1
    //   76: iconst_0
    //   77: aaload
    //   78: invokestatic 58	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   81: invokespecial 85	com/github/mikephil/charting/data/Entry:<init>	(FF)V
    //   84: invokeinterface 67 2 0
    //   89: pop
    //   90: goto +62 -> 152
    //   93: aload_1
    //   94: arraylength
    //   95: iconst_1
    //   96: isub
    //   97: newarray float
    //   99: astore 4
    //   101: iload_2
    //   102: aload 4
    //   104: arraylength
    //   105: if_icmpge +20 -> 125
    //   108: aload 4
    //   110: iload_2
    //   111: aload_1
    //   112: iload_2
    //   113: aaload
    //   114: invokestatic 58	java/lang/Float:parseFloat	(Ljava/lang/String;)F
    //   117: fastore
    //   118: iload_2
    //   119: iconst_1
    //   120: iadd
    //   121: istore_2
    //   122: goto -21 -> 101
    //   125: aload 6
    //   127: new 52	com/github/mikephil/charting/data/BarEntry
    //   130: dup
    //   131: aload_1
    //   132: aload_1
    //   133: arraylength
    //   134: iconst_1
    //   135: isub
    //   136: aaload
    //   137: invokestatic 91	java/lang/Integer:parseInt	(Ljava/lang/String;)I
    //   140: i2f
    //   141: aload 4
    //   143: invokespecial 94	com/github/mikephil/charting/data/BarEntry:<init>	(F[F)V
    //   146: invokeinterface 67 2 0
    //   151: pop
    //   152: aload_0
    //   153: invokevirtual 42	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   156: astore_1
    //   157: goto -115 -> 42
    //   160: aload_0
    //   161: ifnull +64 -> 225
    //   164: aload_0
    //   165: invokevirtual 70	java/io/BufferedReader:close	()V
    //   168: aload 6
    //   170: areturn
    //   171: astore_1
    //   172: goto +56 -> 228
    //   175: astore_1
    //   176: goto +14 -> 190
    //   179: astore_1
    //   180: aload 4
    //   182: astore_0
    //   183: goto +45 -> 228
    //   186: astore_1
    //   187: aload 5
    //   189: astore_0
    //   190: aload_0
    //   191: astore 4
    //   193: ldc 8
    //   195: aload_1
    //   196: invokevirtual 73	java/io/IOException:toString	()Ljava/lang/String;
    //   199: invokestatic 79	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   202: pop
    //   203: aload_0
    //   204: ifnull +21 -> 225
    //   207: aload_0
    //   208: invokevirtual 70	java/io/BufferedReader:close	()V
    //   211: aload 6
    //   213: areturn
    //   214: astore_0
    //   215: ldc 8
    //   217: aload_0
    //   218: invokevirtual 73	java/io/IOException:toString	()Ljava/lang/String;
    //   221: invokestatic 79	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   224: pop
    //   225: aload 6
    //   227: areturn
    //   228: aload_0
    //   229: ifnull +21 -> 250
    //   232: aload_0
    //   233: invokevirtual 70	java/io/BufferedReader:close	()V
    //   236: goto +14 -> 250
    //   239: astore_0
    //   240: ldc 8
    //   242: aload_0
    //   243: invokevirtual 73	java/io/IOException:toString	()Ljava/lang/String;
    //   246: invokestatic 79	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   249: pop
    //   250: aload_1
    //   251: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	252	0	paramAssetManager	android.content.res.AssetManager
    //   0	252	1	paramString	String
    //   57	65	2	i	int
    //   55	6	3	j	int
    //   13	179	4	localObject1	Object
    //   10	178	5	localObject2	Object
    //   7	219	6	localArrayList	ArrayList
    // Exception table:
    //   from	to	target	type
    //   37	42	171	finally
    //   46	56	171	finally
    //   63	90	171	finally
    //   93	101	171	finally
    //   101	118	171	finally
    //   125	152	171	finally
    //   152	157	171	finally
    //   37	42	175	java/io/IOException
    //   46	56	175	java/io/IOException
    //   63	90	175	java/io/IOException
    //   93	101	175	java/io/IOException
    //   101	118	175	java/io/IOException
    //   125	152	175	java/io/IOException
    //   152	157	175	java/io/IOException
    //   15	37	179	finally
    //   193	203	179	finally
    //   15	37	186	java/io/IOException
    //   164	168	214	java/io/IOException
    //   207	211	214	java/io/IOException
    //   232	236	239	java/io/IOException
  }
  
  public static List<Entry> loadEntriesFromFile(String paramString)
  {
    Object localObject1 = new File(Environment.getExternalStorageDirectory(), paramString);
    paramString = new ArrayList();
    try
    {
      localObject1 = new BufferedReader(new FileReader((File)localObject1));
      for (;;)
      {
        Object localObject2 = ((BufferedReader)localObject1).readLine();
        if (localObject2 == null) {
          break;
        }
        localObject2 = ((String)localObject2).split("#");
        int j = localObject2.length;
        int i = 0;
        if (j <= 2)
        {
          paramString.add(new Entry(Float.parseFloat(localObject2[0]), Integer.parseInt(localObject2[1])));
        }
        else
        {
          float[] arrayOfFloat = new float[localObject2.length - 1];
          while (i < arrayOfFloat.length)
          {
            arrayOfFloat[i] = Float.parseFloat(localObject2[i]);
            i += 1;
          }
          paramString.add(new BarEntry(Integer.parseInt(localObject2[(localObject2.length - 1)]), arrayOfFloat));
        }
      }
      return paramString;
    }
    catch (IOException localIOException)
    {
      Log.e("MPChart-FileUtils", localIOException.toString());
    }
  }
  
  public static void saveToSdCard(List<Entry> paramList, String paramString)
  {
    paramString = new File(Environment.getExternalStorageDirectory(), paramString);
    if (!paramString.exists()) {
      try
      {
        paramString.createNewFile();
      }
      catch (IOException localIOException)
      {
        Log.e("MPChart-FileUtils", localIOException.toString());
      }
    }
    try
    {
      paramString = new BufferedWriter(new FileWriter(paramString, true));
      paramList = paramList.iterator();
      while (paramList.hasNext())
      {
        Entry localEntry = (Entry)paramList.next();
        StringBuilder localStringBuilder = new StringBuilder();
        localStringBuilder.append(localEntry.getY());
        localStringBuilder.append("#");
        localStringBuilder.append(localEntry.getX());
        paramString.append(localStringBuilder.toString());
        paramString.newLine();
      }
      paramString.close();
      return;
    }
    catch (IOException paramList)
    {
      Log.e("MPChart-FileUtils", paramList.toString());
    }
  }
}

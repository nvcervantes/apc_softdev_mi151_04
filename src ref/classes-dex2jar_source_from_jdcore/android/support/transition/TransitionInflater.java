package android.support.transition;

import android.content.Context;
import android.content.res.Resources.NotFoundException;
import android.content.res.TypedArray;
import android.support.annotation.NonNull;
import android.support.v4.content.res.TypedArrayUtils;
import android.support.v4.util.ArrayMap;
import android.util.AttributeSet;
import android.view.InflateException;
import android.view.ViewGroup;
import java.io.IOException;
import java.lang.reflect.Constructor;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;

public class TransitionInflater
{
  private static final ArrayMap<String, Constructor> CONSTRUCTORS = new ArrayMap();
  private static final Class<?>[] CONSTRUCTOR_SIGNATURE = { Context.class, AttributeSet.class };
  private final Context mContext;
  
  private TransitionInflater(@NonNull Context paramContext)
  {
    mContext = paramContext;
  }
  
  /* Error */
  private Object createCustom(AttributeSet paramAttributeSet, Class paramClass, String paramString)
  {
    // Byte code:
    //   0: aload_1
    //   1: aconst_null
    //   2: ldc 42
    //   4: invokeinterface 46 3 0
    //   9: astore 5
    //   11: aload 5
    //   13: ifnonnull +36 -> 49
    //   16: new 48	java/lang/StringBuilder
    //   19: dup
    //   20: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   23: astore_1
    //   24: aload_1
    //   25: aload_3
    //   26: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   29: pop
    //   30: aload_1
    //   31: ldc 55
    //   33: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: pop
    //   37: new 57	android/view/InflateException
    //   40: dup
    //   41: aload_1
    //   42: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   45: invokespecial 64	android/view/InflateException:<init>	(Ljava/lang/String;)V
    //   48: athrow
    //   49: getstatic 29	android/support/transition/TransitionInflater:CONSTRUCTORS	Landroid/support/v4/util/ArrayMap;
    //   52: astore 6
    //   54: aload 6
    //   56: monitorenter
    //   57: getstatic 29	android/support/transition/TransitionInflater:CONSTRUCTORS	Landroid/support/v4/util/ArrayMap;
    //   60: aload 5
    //   62: invokevirtual 68	android/support/v4/util/ArrayMap:get	(Ljava/lang/Object;)Ljava/lang/Object;
    //   65: checkcast 70	java/lang/reflect/Constructor
    //   68: astore 4
    //   70: aload 4
    //   72: astore_3
    //   73: aload 4
    //   75: ifnonnull +53 -> 128
    //   78: aload_0
    //   79: getfield 35	android/support/transition/TransitionInflater:mContext	Landroid/content/Context;
    //   82: invokevirtual 74	android/content/Context:getClassLoader	()Ljava/lang/ClassLoader;
    //   85: aload 5
    //   87: invokevirtual 80	java/lang/ClassLoader:loadClass	(Ljava/lang/String;)Ljava/lang/Class;
    //   90: aload_2
    //   91: invokevirtual 84	java/lang/Class:asSubclass	(Ljava/lang/Class;)Ljava/lang/Class;
    //   94: astore 7
    //   96: aload 4
    //   98: astore_3
    //   99: aload 7
    //   101: ifnull +27 -> 128
    //   104: aload 7
    //   106: getstatic 22	android/support/transition/TransitionInflater:CONSTRUCTOR_SIGNATURE	[Ljava/lang/Class;
    //   109: invokevirtual 88	java/lang/Class:getConstructor	([Ljava/lang/Class;)Ljava/lang/reflect/Constructor;
    //   112: astore_3
    //   113: aload_3
    //   114: iconst_1
    //   115: invokevirtual 92	java/lang/reflect/Constructor:setAccessible	(Z)V
    //   118: getstatic 29	android/support/transition/TransitionInflater:CONSTRUCTORS	Landroid/support/v4/util/ArrayMap;
    //   121: aload 5
    //   123: aload_3
    //   124: invokevirtual 96	android/support/v4/util/ArrayMap:put	(Ljava/lang/Object;Ljava/lang/Object;)Ljava/lang/Object;
    //   127: pop
    //   128: aload_3
    //   129: iconst_2
    //   130: anewarray 4	java/lang/Object
    //   133: dup
    //   134: iconst_0
    //   135: aload_0
    //   136: getfield 35	android/support/transition/TransitionInflater:mContext	Landroid/content/Context;
    //   139: aastore
    //   140: dup
    //   141: iconst_1
    //   142: aload_1
    //   143: aastore
    //   144: invokevirtual 100	java/lang/reflect/Constructor:newInstance	([Ljava/lang/Object;)Ljava/lang/Object;
    //   147: astore_1
    //   148: aload 6
    //   150: monitorexit
    //   151: aload_1
    //   152: areturn
    //   153: astore_1
    //   154: aload 6
    //   156: monitorexit
    //   157: aload_1
    //   158: athrow
    //   159: astore_1
    //   160: new 48	java/lang/StringBuilder
    //   163: dup
    //   164: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   167: astore_3
    //   168: aload_3
    //   169: ldc 102
    //   171: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   174: pop
    //   175: aload_3
    //   176: aload_2
    //   177: invokevirtual 105	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   180: pop
    //   181: aload_3
    //   182: ldc 107
    //   184: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: pop
    //   188: aload_3
    //   189: aload 5
    //   191: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   194: pop
    //   195: new 57	android/view/InflateException
    //   198: dup
    //   199: aload_3
    //   200: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   203: aload_1
    //   204: invokespecial 110	android/view/InflateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   207: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	208	0	this	TransitionInflater
    //   0	208	1	paramAttributeSet	AttributeSet
    //   0	208	2	paramClass	Class
    //   0	208	3	paramString	String
    //   68	29	4	localConstructor	Constructor
    //   9	181	5	str	String
    //   94	11	7	localClass	Class
    // Exception table:
    //   from	to	target	type
    //   57	70	153	finally
    //   78	96	153	finally
    //   104	128	153	finally
    //   128	151	153	finally
    //   154	157	153	finally
    //   49	57	159	java/lang/Exception
    //   157	159	159	java/lang/Exception
  }
  
  private Transition createTransitionFromXml(XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Transition paramTransition)
    throws XmlPullParserException, IOException
  {
    int i = paramXmlPullParser.getDepth();
    TransitionSet localTransitionSet;
    if ((paramTransition instanceof TransitionSet)) {
      localTransitionSet = (TransitionSet)paramTransition;
    } else {
      localTransitionSet = null;
    }
    Object localObject2 = null;
    do
    {
      Object localObject1;
      do
      {
        int j;
        do
        {
          j = paramXmlPullParser.next();
          if (((j == 3) && (paramXmlPullParser.getDepth() <= i)) || (j == 1)) {
            break;
          }
        } while (j != 2);
        localObject1 = paramXmlPullParser.getName();
        if ("fade".equals(localObject1))
        {
          localObject1 = new Fade(mContext, paramAttributeSet);
        }
        else if ("changeBounds".equals(localObject1))
        {
          localObject1 = new ChangeBounds(mContext, paramAttributeSet);
        }
        else if ("slide".equals(localObject1))
        {
          localObject1 = new Slide(mContext, paramAttributeSet);
        }
        else if ("explode".equals(localObject1))
        {
          localObject1 = new Explode(mContext, paramAttributeSet);
        }
        else if ("changeImageTransform".equals(localObject1))
        {
          localObject1 = new ChangeImageTransform(mContext, paramAttributeSet);
        }
        else if ("changeTransform".equals(localObject1))
        {
          localObject1 = new ChangeTransform(mContext, paramAttributeSet);
        }
        else if ("changeClipBounds".equals(localObject1))
        {
          localObject1 = new ChangeClipBounds(mContext, paramAttributeSet);
        }
        else if ("autoTransition".equals(localObject1))
        {
          localObject1 = new AutoTransition(mContext, paramAttributeSet);
        }
        else if ("changeScroll".equals(localObject1))
        {
          localObject1 = new ChangeScroll(mContext, paramAttributeSet);
        }
        else if ("transitionSet".equals(localObject1))
        {
          localObject1 = new TransitionSet(mContext, paramAttributeSet);
        }
        else if ("transition".equals(localObject1))
        {
          localObject1 = (Transition)createCustom(paramAttributeSet, Transition.class, "transition");
        }
        else if ("targets".equals(localObject1))
        {
          getTargetIds(paramXmlPullParser, paramAttributeSet, paramTransition);
          localObject1 = localObject2;
        }
        else if ("arcMotion".equals(localObject1))
        {
          if (paramTransition == null) {
            throw new RuntimeException("Invalid use of arcMotion element");
          }
          paramTransition.setPathMotion(new ArcMotion(mContext, paramAttributeSet));
          localObject1 = localObject2;
        }
        else if ("pathMotion".equals(localObject1))
        {
          if (paramTransition == null) {
            throw new RuntimeException("Invalid use of pathMotion element");
          }
          paramTransition.setPathMotion((PathMotion)createCustom(paramAttributeSet, PathMotion.class, "pathMotion"));
          localObject1 = localObject2;
        }
        else
        {
          if (!"patternPathMotion".equals(localObject1)) {
            break;
          }
          if (paramTransition == null) {
            throw new RuntimeException("Invalid use of patternPathMotion element");
          }
          paramTransition.setPathMotion(new PatternPathMotion(mContext, paramAttributeSet));
          localObject1 = localObject2;
        }
        localObject2 = localObject1;
      } while (localObject1 == null);
      if (!paramXmlPullParser.isEmptyElementTag()) {
        createTransitionFromXml(paramXmlPullParser, paramAttributeSet, (Transition)localObject1);
      }
      if (localTransitionSet != null)
      {
        localTransitionSet.addTransition((Transition)localObject1);
        break;
      }
      localObject2 = localObject1;
    } while (paramTransition == null);
    throw new InflateException("Could not add transition to another transition.");
    paramAttributeSet = new StringBuilder();
    paramAttributeSet.append("Unknown scene name: ");
    paramAttributeSet.append(paramXmlPullParser.getName());
    throw new RuntimeException(paramAttributeSet.toString());
    return localObject2;
  }
  
  private TransitionManager createTransitionManagerFromXml(XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, ViewGroup paramViewGroup)
    throws XmlPullParserException, IOException
  {
    int i = paramXmlPullParser.getDepth();
    TransitionManager localTransitionManager = null;
    for (;;)
    {
      int j = paramXmlPullParser.next();
      if (((j == 3) && (paramXmlPullParser.getDepth() <= i)) || (j == 1)) {
        break label146;
      }
      if (j == 2)
      {
        String str = paramXmlPullParser.getName();
        if (str.equals("transitionManager"))
        {
          localTransitionManager = new TransitionManager();
        }
        else
        {
          if ((!str.equals("transition")) || (localTransitionManager == null)) {
            break;
          }
          loadTransition(paramAttributeSet, paramXmlPullParser, paramViewGroup, localTransitionManager);
        }
      }
    }
    paramAttributeSet = new StringBuilder();
    paramAttributeSet.append("Unknown scene name: ");
    paramAttributeSet.append(paramXmlPullParser.getName());
    throw new RuntimeException(paramAttributeSet.toString());
    label146:
    return localTransitionManager;
  }
  
  public static TransitionInflater from(Context paramContext)
  {
    return new TransitionInflater(paramContext);
  }
  
  private void getTargetIds(XmlPullParser paramXmlPullParser, AttributeSet paramAttributeSet, Transition paramTransition)
    throws XmlPullParserException, IOException
  {
    int i = paramXmlPullParser.getDepth();
    for (;;)
    {
      int j = paramXmlPullParser.next();
      if (((j == 3) && (paramXmlPullParser.getDepth() <= i)) || (j == 1)) {
        break label342;
      }
      TypedArray localTypedArray;
      String str1;
      if (j == 2)
      {
        if (!paramXmlPullParser.getName().equals("target")) {
          break label304;
        }
        localTypedArray = mContext.obtainStyledAttributes(paramAttributeSet, Styleable.TRANSITION_TARGET);
        j = TypedArrayUtils.getNamedResourceId(localTypedArray, paramXmlPullParser, "targetId", 1, 0);
        if (j != 0)
        {
          paramTransition.addTarget(j);
        }
        else
        {
          j = TypedArrayUtils.getNamedResourceId(localTypedArray, paramXmlPullParser, "excludeId", 2, 0);
          if (j != 0)
          {
            paramTransition.excludeTarget(j, true);
          }
          else
          {
            str1 = TypedArrayUtils.getNamedString(localTypedArray, paramXmlPullParser, "targetName", 4);
            if (str1 != null)
            {
              paramTransition.addTarget(str1);
            }
            else
            {
              str1 = TypedArrayUtils.getNamedString(localTypedArray, paramXmlPullParser, "excludeName", 5);
              if (str1 != null)
              {
                paramTransition.excludeTarget(str1, true);
              }
              else
              {
                str1 = TypedArrayUtils.getNamedString(localTypedArray, paramXmlPullParser, "excludeClass", 3);
                if (str1 == null) {}
              }
            }
          }
        }
      }
      try
      {
        paramTransition.excludeTarget(Class.forName(str1), true);
        break label256;
        String str2 = TypedArrayUtils.getNamedString(localTypedArray, paramXmlPullParser, "targetClass", 0);
        if (str2 != null) {
          try
          {
            paramTransition.addTarget(Class.forName(str2));
          }
          catch (ClassNotFoundException paramAttributeSet)
          {
            paramXmlPullParser = str2;
            break label264;
          }
        }
        label256:
        localTypedArray.recycle();
      }
      catch (ClassNotFoundException paramAttributeSet)
      {
        for (;;)
        {
          label264:
          paramXmlPullParser = str1;
        }
      }
    }
    localTypedArray.recycle();
    paramTransition = new StringBuilder();
    paramTransition.append("Could not create ");
    paramTransition.append(paramXmlPullParser);
    throw new RuntimeException(paramTransition.toString(), paramAttributeSet);
    label304:
    paramAttributeSet = new StringBuilder();
    paramAttributeSet.append("Unknown scene name: ");
    paramAttributeSet.append(paramXmlPullParser.getName());
    throw new RuntimeException(paramAttributeSet.toString());
    label342:
  }
  
  private void loadTransition(AttributeSet paramAttributeSet, XmlPullParser paramXmlPullParser, ViewGroup paramViewGroup, TransitionManager paramTransitionManager)
    throws Resources.NotFoundException
  {
    TypedArray localTypedArray = mContext.obtainStyledAttributes(paramAttributeSet, Styleable.TRANSITION_MANAGER);
    int i = TypedArrayUtils.getNamedResourceId(localTypedArray, paramXmlPullParser, "transition", 2, -1);
    int j = TypedArrayUtils.getNamedResourceId(localTypedArray, paramXmlPullParser, "fromScene", 0, -1);
    Object localObject = null;
    if (j < 0) {
      paramAttributeSet = null;
    } else {
      paramAttributeSet = Scene.getSceneForLayout(paramViewGroup, j, mContext);
    }
    j = TypedArrayUtils.getNamedResourceId(localTypedArray, paramXmlPullParser, "toScene", 1, -1);
    if (j < 0) {
      paramXmlPullParser = localObject;
    } else {
      paramXmlPullParser = Scene.getSceneForLayout(paramViewGroup, j, mContext);
    }
    if (i >= 0)
    {
      paramViewGroup = inflateTransition(i);
      if (paramViewGroup != null)
      {
        if (paramXmlPullParser == null)
        {
          paramAttributeSet = new StringBuilder();
          paramAttributeSet.append("No toScene for transition ID ");
          paramAttributeSet.append(i);
          throw new RuntimeException(paramAttributeSet.toString());
        }
        if (paramAttributeSet == null) {
          paramTransitionManager.setTransition(paramXmlPullParser, paramViewGroup);
        } else {
          paramTransitionManager.setTransition(paramAttributeSet, paramXmlPullParser, paramViewGroup);
        }
      }
    }
    localTypedArray.recycle();
  }
  
  /* Error */
  public Transition inflateTransition(int paramInt)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 35	android/support/transition/TransitionInflater:mContext	Landroid/content/Context;
    //   4: invokevirtual 356	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   7: iload_1
    //   8: invokevirtual 362	android/content/res/Resources:getXml	(I)Landroid/content/res/XmlResourceParser;
    //   11: astore_2
    //   12: aload_0
    //   13: aload_2
    //   14: aload_2
    //   15: invokestatic 368	android/util/Xml:asAttributeSet	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/util/AttributeSet;
    //   18: aconst_null
    //   19: invokespecial 231	android/support/transition/TransitionInflater:createTransitionFromXml	(Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/support/transition/Transition;)Landroid/support/transition/Transition;
    //   22: astore_3
    //   23: aload_2
    //   24: invokeinterface 373 1 0
    //   29: aload_3
    //   30: areturn
    //   31: astore_3
    //   32: goto +72 -> 104
    //   35: astore_3
    //   36: new 48	java/lang/StringBuilder
    //   39: dup
    //   40: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   43: astore 4
    //   45: aload 4
    //   47: aload_2
    //   48: invokeinterface 376 1 0
    //   53: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: pop
    //   57: aload 4
    //   59: ldc_w 378
    //   62: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: pop
    //   66: aload 4
    //   68: aload_3
    //   69: invokevirtual 381	java/io/IOException:getMessage	()Ljava/lang/String;
    //   72: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: pop
    //   76: new 57	android/view/InflateException
    //   79: dup
    //   80: aload 4
    //   82: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: aload_3
    //   86: invokespecial 110	android/view/InflateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   89: athrow
    //   90: astore_3
    //   91: new 57	android/view/InflateException
    //   94: dup
    //   95: aload_3
    //   96: invokevirtual 382	org/xmlpull/v1/XmlPullParserException:getMessage	()Ljava/lang/String;
    //   99: aload_3
    //   100: invokespecial 110	android/view/InflateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   103: athrow
    //   104: aload_2
    //   105: invokeinterface 373 1 0
    //   110: aload_3
    //   111: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	112	0	this	TransitionInflater
    //   0	112	1	paramInt	int
    //   11	94	2	localXmlResourceParser	android.content.res.XmlResourceParser
    //   22	8	3	localTransition	Transition
    //   31	1	3	localObject	Object
    //   35	51	3	localIOException	IOException
    //   90	21	3	localXmlPullParserException	XmlPullParserException
    //   43	38	4	localStringBuilder	StringBuilder
    // Exception table:
    //   from	to	target	type
    //   12	23	31	finally
    //   36	90	31	finally
    //   91	104	31	finally
    //   12	23	35	java/io/IOException
    //   12	23	90	org/xmlpull/v1/XmlPullParserException
  }
  
  /* Error */
  public TransitionManager inflateTransitionManager(int paramInt, ViewGroup paramViewGroup)
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 35	android/support/transition/TransitionInflater:mContext	Landroid/content/Context;
    //   4: invokevirtual 356	android/content/Context:getResources	()Landroid/content/res/Resources;
    //   7: iload_1
    //   8: invokevirtual 362	android/content/res/Resources:getXml	(I)Landroid/content/res/XmlResourceParser;
    //   11: astore_3
    //   12: aload_0
    //   13: aload_3
    //   14: aload_3
    //   15: invokestatic 368	android/util/Xml:asAttributeSet	(Lorg/xmlpull/v1/XmlPullParser;)Landroid/util/AttributeSet;
    //   18: aload_2
    //   19: invokespecial 386	android/support/transition/TransitionInflater:createTransitionManagerFromXml	(Lorg/xmlpull/v1/XmlPullParser;Landroid/util/AttributeSet;Landroid/view/ViewGroup;)Landroid/support/transition/TransitionManager;
    //   22: astore_2
    //   23: aload_3
    //   24: invokeinterface 373 1 0
    //   29: aload_2
    //   30: areturn
    //   31: astore_2
    //   32: goto +92 -> 124
    //   35: astore_2
    //   36: new 48	java/lang/StringBuilder
    //   39: dup
    //   40: invokespecial 49	java/lang/StringBuilder:<init>	()V
    //   43: astore 4
    //   45: aload 4
    //   47: aload_3
    //   48: invokeinterface 376 1 0
    //   53: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   56: pop
    //   57: aload 4
    //   59: ldc_w 378
    //   62: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   65: pop
    //   66: aload 4
    //   68: aload_2
    //   69: invokevirtual 381	java/io/IOException:getMessage	()Ljava/lang/String;
    //   72: invokevirtual 53	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   75: pop
    //   76: new 57	android/view/InflateException
    //   79: dup
    //   80: aload 4
    //   82: invokevirtual 61	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   85: invokespecial 64	android/view/InflateException:<init>	(Ljava/lang/String;)V
    //   88: astore 4
    //   90: aload 4
    //   92: aload_2
    //   93: invokevirtual 390	android/view/InflateException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   96: pop
    //   97: aload 4
    //   99: athrow
    //   100: astore_2
    //   101: new 57	android/view/InflateException
    //   104: dup
    //   105: aload_2
    //   106: invokevirtual 382	org/xmlpull/v1/XmlPullParserException:getMessage	()Ljava/lang/String;
    //   109: invokespecial 64	android/view/InflateException:<init>	(Ljava/lang/String;)V
    //   112: astore 4
    //   114: aload 4
    //   116: aload_2
    //   117: invokevirtual 390	android/view/InflateException:initCause	(Ljava/lang/Throwable;)Ljava/lang/Throwable;
    //   120: pop
    //   121: aload 4
    //   123: athrow
    //   124: aload_3
    //   125: invokeinterface 373 1 0
    //   130: aload_2
    //   131: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	132	0	this	TransitionInflater
    //   0	132	1	paramInt	int
    //   0	132	2	paramViewGroup	ViewGroup
    //   11	114	3	localXmlResourceParser	android.content.res.XmlResourceParser
    //   43	79	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   12	23	31	finally
    //   36	100	31	finally
    //   101	124	31	finally
    //   12	23	35	java/io/IOException
    //   12	23	100	org/xmlpull/v1/XmlPullParserException
  }
}

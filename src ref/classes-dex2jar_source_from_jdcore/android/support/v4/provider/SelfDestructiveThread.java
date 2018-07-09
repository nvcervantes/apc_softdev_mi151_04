package android.support.v4.provider;

import android.os.Handler;
import android.os.Handler.Callback;
import android.os.HandlerThread;
import android.os.Message;
import android.support.annotation.GuardedBy;
import android.support.annotation.RestrictTo;
import android.support.annotation.VisibleForTesting;
import java.util.concurrent.Callable;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

@RestrictTo({android.support.annotation.RestrictTo.Scope.LIBRARY_GROUP})
public class SelfDestructiveThread
{
  private static final int MSG_DESTRUCTION = 0;
  private static final int MSG_INVOKE_RUNNABLE = 1;
  private Handler.Callback mCallback = new Handler.Callback()
  {
    public boolean handleMessage(Message paramAnonymousMessage)
    {
      switch (what)
      {
      default: 
        return true;
      case 1: 
        SelfDestructiveThread.this.onInvokeRunnable((Runnable)obj);
        return true;
      }
      SelfDestructiveThread.this.onDestruction();
      return true;
    }
  };
  private final int mDestructAfterMillisec;
  @GuardedBy("mLock")
  private int mGeneration;
  @GuardedBy("mLock")
  private Handler mHandler;
  private final Object mLock = new Object();
  private final int mPriority;
  @GuardedBy("mLock")
  private HandlerThread mThread;
  private final String mThreadName;
  
  public SelfDestructiveThread(String paramString, int paramInt1, int paramInt2)
  {
    mThreadName = paramString;
    mPriority = paramInt1;
    mDestructAfterMillisec = paramInt2;
    mGeneration = 0;
  }
  
  private void onDestruction()
  {
    synchronized (mLock)
    {
      if (mHandler.hasMessages(1)) {
        return;
      }
      mThread.quit();
      mThread = null;
      mHandler = null;
      return;
    }
  }
  
  private void onInvokeRunnable(Runnable arg1)
  {
    ???.run();
    synchronized (mLock)
    {
      mHandler.removeMessages(0);
      mHandler.sendMessageDelayed(mHandler.obtainMessage(0), mDestructAfterMillisec);
      return;
    }
  }
  
  private void post(Runnable paramRunnable)
  {
    synchronized (mLock)
    {
      if (mThread == null)
      {
        mThread = new HandlerThread(mThreadName, mPriority);
        mThread.start();
        mHandler = new Handler(mThread.getLooper(), mCallback);
        mGeneration += 1;
      }
      mHandler.removeMessages(0);
      mHandler.sendMessage(mHandler.obtainMessage(1, paramRunnable));
      return;
    }
  }
  
  @VisibleForTesting
  public int getGeneration()
  {
    synchronized (mLock)
    {
      int i = mGeneration;
      return i;
    }
  }
  
  @VisibleForTesting
  public boolean isRunning()
  {
    for (;;)
    {
      synchronized (mLock)
      {
        if (mThread != null)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public <T> void postAndReply(final Callable<T> paramCallable, final ReplyCallback<T> paramReplyCallback)
  {
    post(new Runnable()
    {
      public void run()
      {
        try
        {
          localObject = paramCallable.call();
        }
        catch (Exception localException)
        {
          final Object localObject;
          for (;;) {}
        }
        localObject = null;
        val$callingHandler.post(new Runnable()
        {
          public void run()
          {
            val$reply.onReply(localObject);
          }
        });
      }
    });
  }
  
  /* Error */
  public <T> T postAndWait(final Callable<T> paramCallable, int paramInt)
    throws java.lang.InterruptedException
  {
    // Byte code:
    //   0: new 144	java/util/concurrent/locks/ReentrantLock
    //   3: dup
    //   4: invokespecial 145	java/util/concurrent/locks/ReentrantLock:<init>	()V
    //   7: astore 7
    //   9: aload 7
    //   11: invokevirtual 149	java/util/concurrent/locks/ReentrantLock:newCondition	()Ljava/util/concurrent/locks/Condition;
    //   14: astore 8
    //   16: new 151	java/util/concurrent/atomic/AtomicReference
    //   19: dup
    //   20: invokespecial 152	java/util/concurrent/atomic/AtomicReference:<init>	()V
    //   23: astore 9
    //   25: new 154	java/util/concurrent/atomic/AtomicBoolean
    //   28: dup
    //   29: iconst_1
    //   30: invokespecial 157	java/util/concurrent/atomic/AtomicBoolean:<init>	(Z)V
    //   33: astore 10
    //   35: aload_0
    //   36: new 12	android/support/v4/provider/SelfDestructiveThread$3
    //   39: dup
    //   40: aload_0
    //   41: aload 9
    //   43: aload_1
    //   44: aload 7
    //   46: aload 10
    //   48: aload 8
    //   50: invokespecial 160	android/support/v4/provider/SelfDestructiveThread$3:<init>	(Landroid/support/v4/provider/SelfDestructiveThread;Ljava/util/concurrent/atomic/AtomicReference;Ljava/util/concurrent/Callable;Ljava/util/concurrent/locks/ReentrantLock;Ljava/util/concurrent/atomic/AtomicBoolean;Ljava/util/concurrent/locks/Condition;)V
    //   53: invokespecial 136	android/support/v4/provider/SelfDestructiveThread:post	(Ljava/lang/Runnable;)V
    //   56: aload 7
    //   58: invokevirtual 163	java/util/concurrent/locks/ReentrantLock:lock	()V
    //   61: aload 10
    //   63: invokevirtual 166	java/util/concurrent/atomic/AtomicBoolean:get	()Z
    //   66: ifne +16 -> 82
    //   69: aload 9
    //   71: invokevirtual 169	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
    //   74: astore_1
    //   75: aload 7
    //   77: invokevirtual 172	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   80: aload_1
    //   81: areturn
    //   82: getstatic 178	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   85: iload_2
    //   86: i2l
    //   87: invokevirtual 182	java/util/concurrent/TimeUnit:toNanos	(J)J
    //   90: lstore_3
    //   91: aload 8
    //   93: lload_3
    //   94: invokeinterface 187 3 0
    //   99: lstore 5
    //   101: aload 10
    //   103: invokevirtual 166	java/util/concurrent/atomic/AtomicBoolean:get	()Z
    //   106: ifne +16 -> 122
    //   109: aload 9
    //   111: invokevirtual 169	java/util/concurrent/atomic/AtomicReference:get	()Ljava/lang/Object;
    //   114: astore_1
    //   115: aload 7
    //   117: invokevirtual 172	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   120: aload_1
    //   121: areturn
    //   122: lload 5
    //   124: lstore_3
    //   125: lload 5
    //   127: lconst_0
    //   128: lcmp
    //   129: ifgt -38 -> 91
    //   132: new 142	java/lang/InterruptedException
    //   135: dup
    //   136: ldc -67
    //   138: invokespecial 192	java/lang/InterruptedException:<init>	(Ljava/lang/String;)V
    //   141: athrow
    //   142: astore_1
    //   143: aload 7
    //   145: invokevirtual 172	java/util/concurrent/locks/ReentrantLock:unlock	()V
    //   148: aload_1
    //   149: athrow
    //   150: astore_1
    //   151: lload_3
    //   152: lstore 5
    //   154: goto -53 -> 101
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	157	0	this	SelfDestructiveThread
    //   0	157	1	paramCallable	Callable<T>
    //   0	157	2	paramInt	int
    //   90	62	3	l1	long
    //   99	54	5	l2	long
    //   7	137	7	localReentrantLock	ReentrantLock
    //   14	78	8	localCondition	Condition
    //   23	87	9	localAtomicReference	AtomicReference
    //   33	69	10	localAtomicBoolean	AtomicBoolean
    // Exception table:
    //   from	to	target	type
    //   61	75	142	finally
    //   82	91	142	finally
    //   91	101	142	finally
    //   101	115	142	finally
    //   132	142	142	finally
    //   91	101	150	java/lang/InterruptedException
  }
  
  public static abstract interface ReplyCallback<T>
  {
    public abstract void onReply(T paramT);
  }
}

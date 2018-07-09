package okhttp3.internal;

public abstract class NamedRunnable
  implements Runnable
{
  protected final String name;
  
  public NamedRunnable(String paramString, Object... paramVarArgs)
  {
    name = Util.format(paramString, paramVarArgs);
  }
  
  protected abstract void execute();
  
  public final void run()
  {
    String str = Thread.currentThread().getName();
    Thread.currentThread().setName(name);
    try
    {
      execute();
      return;
    }
    finally
    {
      Thread.currentThread().setName(str);
    }
  }
}

package com.facebook.internal;

import com.facebook.FacebookSdk;
import java.util.concurrent.Executor;

public class WorkQueue
{
  public static final int DEFAULT_MAX_CONCURRENT = 8;
  private final Executor executor;
  private final int maxConcurrent;
  private WorkNode pendingJobs;
  private int runningCount = 0;
  private WorkNode runningJobs = null;
  private final Object workLock = new Object();
  
  public WorkQueue()
  {
    this(8);
  }
  
  public WorkQueue(int paramInt)
  {
    this(paramInt, FacebookSdk.getExecutor());
  }
  
  public WorkQueue(int paramInt, Executor paramExecutor)
  {
    maxConcurrent = paramInt;
    executor = paramExecutor;
  }
  
  private void execute(final WorkNode paramWorkNode)
  {
    executor.execute(new Runnable()
    {
      public void run()
      {
        try
        {
          paramWorkNode.getCallback().run();
          return;
        }
        finally
        {
          WorkQueue.this.finishItemAndStartNew(paramWorkNode);
        }
      }
    });
  }
  
  private void finishItemAndStartNew(WorkNode paramWorkNode)
  {
    Object localObject = workLock;
    if (paramWorkNode != null) {}
    try
    {
      runningJobs = paramWorkNode.removeFromList(runningJobs);
      runningCount -= 1;
      if (runningCount >= maxConcurrent) {
        break label123;
      }
      WorkNode localWorkNode = pendingJobs;
      paramWorkNode = localWorkNode;
      if (localWorkNode != null)
      {
        pendingJobs = localWorkNode.removeFromList(pendingJobs);
        runningJobs = localWorkNode.addToList(runningJobs, false);
        runningCount += 1;
        localWorkNode.setIsRunning(true);
        paramWorkNode = localWorkNode;
      }
    }
    finally
    {
      for (;;)
      {
        continue;
        paramWorkNode = null;
      }
    }
    if (paramWorkNode != null) {
      execute(paramWorkNode);
    }
    return;
    throw paramWorkNode;
  }
  
  private void startItem()
  {
    finishItemAndStartNew(null);
  }
  
  public WorkItem addActiveWorkItem(Runnable paramRunnable)
  {
    return addActiveWorkItem(paramRunnable, true);
  }
  
  public WorkItem addActiveWorkItem(Runnable arg1, boolean paramBoolean)
  {
    WorkNode localWorkNode = new WorkNode(???);
    synchronized (workLock)
    {
      pendingJobs = localWorkNode.addToList(pendingJobs, paramBoolean);
      startItem();
      return localWorkNode;
    }
  }
  
  public void validate()
  {
    synchronized (workLock)
    {
      if (runningJobs != null)
      {
        Object localObject1 = runningJobs;
        WorkNode localWorkNode;
        do
        {
          ((WorkNode)localObject1).verify(true);
          localWorkNode = ((WorkNode)localObject1).getNext();
          localObject1 = localWorkNode;
        } while (localWorkNode != runningJobs);
      }
      return;
    }
  }
  
  public static abstract interface WorkItem
  {
    public abstract boolean cancel();
    
    public abstract boolean isRunning();
    
    public abstract void moveToFront();
  }
  
  private class WorkNode
    implements WorkQueue.WorkItem
  {
    private final Runnable callback;
    private boolean isRunning;
    private WorkNode next;
    private WorkNode prev;
    
    WorkNode(Runnable paramRunnable)
    {
      callback = paramRunnable;
    }
    
    WorkNode addToList(WorkNode paramWorkNode, boolean paramBoolean)
    {
      if (paramWorkNode == null)
      {
        prev = this;
        next = this;
        paramWorkNode = this;
      }
      else
      {
        next = paramWorkNode;
        prev = prev;
        WorkNode localWorkNode = next;
        prev.next = this;
        prev = this;
      }
      if (paramBoolean) {
        paramWorkNode = this;
      }
      return paramWorkNode;
    }
    
    public boolean cancel()
    {
      synchronized (workLock)
      {
        if (!isRunning())
        {
          WorkQueue.access$202(WorkQueue.this, removeFromList(pendingJobs));
          return true;
        }
        return false;
      }
    }
    
    Runnable getCallback()
    {
      return callback;
    }
    
    WorkNode getNext()
    {
      return next;
    }
    
    public boolean isRunning()
    {
      return isRunning;
    }
    
    public void moveToFront()
    {
      synchronized (workLock)
      {
        if (!isRunning())
        {
          WorkQueue.access$202(WorkQueue.this, removeFromList(pendingJobs));
          WorkQueue.access$202(WorkQueue.this, addToList(pendingJobs, true));
        }
        return;
      }
    }
    
    WorkNode removeFromList(WorkNode paramWorkNode)
    {
      WorkNode localWorkNode = paramWorkNode;
      if (paramWorkNode == this) {
        if (next == this) {
          localWorkNode = null;
        } else {
          localWorkNode = next;
        }
      }
      next.prev = prev;
      prev.next = next;
      prev = null;
      next = null;
      return localWorkNode;
    }
    
    void setIsRunning(boolean paramBoolean)
    {
      isRunning = paramBoolean;
    }
    
    void verify(boolean paramBoolean) {}
  }
}

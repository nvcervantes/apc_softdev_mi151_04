package android.support.v7.recyclerview.extensions;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.util.AdapterListUpdateCallback;
import android.support.v7.util.DiffUtil;
import android.support.v7.util.DiffUtil.Callback;
import android.support.v7.util.DiffUtil.DiffResult;
import android.support.v7.util.DiffUtil.ItemCallback;
import android.support.v7.util.ListUpdateCallback;
import android.support.v7.widget.RecyclerView.Adapter;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Executor;

public class AsyncListDiffer<T>
{
  private final AsyncDifferConfig<T> mConfig;
  @Nullable
  private List<T> mList;
  private int mMaxScheduledGeneration;
  @NonNull
  private List<T> mReadOnlyList = Collections.emptyList();
  private final ListUpdateCallback mUpdateCallback;
  
  public AsyncListDiffer(@NonNull ListUpdateCallback paramListUpdateCallback, @NonNull AsyncDifferConfig<T> paramAsyncDifferConfig)
  {
    mUpdateCallback = paramListUpdateCallback;
    mConfig = paramAsyncDifferConfig;
  }
  
  public AsyncListDiffer(@NonNull RecyclerView.Adapter paramAdapter, @NonNull DiffUtil.ItemCallback<T> paramItemCallback)
  {
    mUpdateCallback = new AdapterListUpdateCallback(paramAdapter);
    mConfig = new AsyncDifferConfig.Builder(paramItemCallback).build();
  }
  
  private void latchList(@NonNull List<T> paramList, @NonNull DiffUtil.DiffResult paramDiffResult)
  {
    paramDiffResult.dispatchUpdatesTo(mUpdateCallback);
    mList = paramList;
    mReadOnlyList = Collections.unmodifiableList(paramList);
  }
  
  @NonNull
  public List<T> getCurrentList()
  {
    return mReadOnlyList;
  }
  
  public void submitList(final List<T> paramList)
  {
    if (paramList == mList) {
      return;
    }
    final int i = mMaxScheduledGeneration + 1;
    mMaxScheduledGeneration = i;
    if (paramList == null)
    {
      mUpdateCallback.onRemoved(0, mList.size());
      mList = null;
      mReadOnlyList = Collections.emptyList();
      return;
    }
    if (mList == null)
    {
      mUpdateCallback.onInserted(0, paramList.size());
      mList = paramList;
      mReadOnlyList = Collections.unmodifiableList(paramList);
      return;
    }
    final List localList = mList;
    mConfig.getBackgroundThreadExecutor().execute(new Runnable()
    {
      public void run()
      {
        final DiffUtil.DiffResult localDiffResult = DiffUtil.calculateDiff(new DiffUtil.Callback()
        {
          public boolean areContentsTheSame(int paramAnonymous2Int1, int paramAnonymous2Int2)
          {
            return mConfig.getDiffCallback().areContentsTheSame(val$oldList.get(paramAnonymous2Int1), val$newList.get(paramAnonymous2Int2));
          }
          
          public boolean areItemsTheSame(int paramAnonymous2Int1, int paramAnonymous2Int2)
          {
            return mConfig.getDiffCallback().areItemsTheSame(val$oldList.get(paramAnonymous2Int1), val$newList.get(paramAnonymous2Int2));
          }
          
          public int getNewListSize()
          {
            return val$newList.size();
          }
          
          public int getOldListSize()
          {
            return val$oldList.size();
          }
        });
        mConfig.getMainThreadExecutor().execute(new Runnable()
        {
          public void run()
          {
            if (mMaxScheduledGeneration == val$runGeneration) {
              AsyncListDiffer.this.latchList(val$newList, localDiffResult);
            }
          }
        });
      }
    });
  }
}

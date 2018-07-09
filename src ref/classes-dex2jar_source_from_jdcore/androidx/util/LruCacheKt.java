package androidx.util;

import android.util.LruCache;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.functions.Function4;
import kotlin.jvm.internal.Lambda;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\000:\n\000\n\002\030\002\n\002\b\002\n\002\020\000\n\000\n\002\020\b\n\000\n\002\030\002\n\002\030\002\n\002\b\004\n\002\030\002\n\000\n\002\030\002\n\002\020\013\n\002\b\003\n\002\020\002\n\000\032ø\001\020\000\032\016\022\004\022\002H\002\022\004\022\002H\0030\001\"\b\b\000\020\002*\0020\004\"\b\b\001\020\003*\0020\0042\006\020\005\032\0020\00628\b\006\020\007\0322\022\023\022\021H\002¢\006\f\b\t\022\b\b\n\022\004\b\b(\013\022\023\022\021H\003¢\006\f\b\t\022\b\b\n\022\004\b\b(\f\022\004\022\0020\0060\b2%\b\007\020\r\032\037\022\023\022\021H\002¢\006\f\b\t\022\b\b\n\022\004\b\b(\013\022\006\022\004\030\001H\0030\0162d\b\006\020\017\032^\022\023\022\0210\021¢\006\f\b\t\022\b\b\n\022\004\b\b(\022\022\023\022\021H\002¢\006\f\b\t\022\b\b\n\022\004\b\b(\013\022\023\022\021H\003¢\006\f\b\t\022\b\b\n\022\004\b\b(\023\022\025\022\023\030\001H\003¢\006\f\b\t\022\b\b\n\022\004\b\b(\024\022\004\022\0020\0250\020H\b¨\006\026"}, d2={"lruCache", "Landroid/util/LruCache;", "K", "V", "", "maxSize", "", "sizeOf", "Lkotlin/Function2;", "Lkotlin/ParameterName;", "name", "key", "value", "create", "Lkotlin/Function1;", "onEntryRemoved", "Lkotlin/Function4;", "", "evicted", "oldValue", "newValue", "", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class LruCacheKt
{
  @NotNull
  public static final <K, V> LruCache<K, V> lruCache(final int paramInt, @NotNull Function2<? super K, ? super V, Integer> paramFunction2, @NotNull final Function1<? super K, ? extends V> paramFunction1, @NotNull final Function4<? super Boolean, ? super K, ? super V, ? super V, Unit> paramFunction4)
  {
    (LruCache)new LruCache(paramFunction2)
    {
      @Nullable
      protected V create(@NotNull K paramAnonymousK)
      {
        return paramFunction1.invoke(paramAnonymousK);
      }
      
      protected void entryRemoved(boolean paramAnonymousBoolean, @NotNull K paramAnonymousK, @NotNull V paramAnonymousV1, @Nullable V paramAnonymousV2)
      {
        paramFunction4.invoke(Boolean.valueOf(paramAnonymousBoolean), paramAnonymousK, paramAnonymousV1, paramAnonymousV2);
      }
      
      protected int sizeOf(@NotNull K paramAnonymousK, @NotNull V paramAnonymousV)
      {
        return ((Number)$sizeOf.invoke(paramAnonymousK, paramAnonymousV)).intValue();
      }
    };
  }
}

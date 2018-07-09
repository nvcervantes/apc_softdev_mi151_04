package androidx.view;

import android.view.Menu;
import android.view.MenuItem;
import java.util.Iterator;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.functions.Function2;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.markers.KMutableIterator;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000:\n\000\n\002\020\b\n\002\030\002\n\002\b\003\n\002\020\013\n\000\n\002\030\002\n\000\n\002\020\002\n\000\n\002\030\002\n\002\030\002\n\002\b\002\n\002\030\002\n\002\b\005\n\002\020)\n\000\032\025\020\005\032\0020\006*\0020\0022\006\020\007\032\0020\bH\002\0320\020\t\032\0020\n*\0020\0022!\020\013\032\035\022\023\022\0210\b¢\006\f\b\r\022\b\b\016\022\004\b\b(\007\022\004\022\0020\n0\fH\b\032E\020\017\032\0020\n*\0020\00226\020\013\0322\022\023\022\0210\001¢\006\f\b\r\022\b\b\016\022\004\b\b(\021\022\023\022\0210\b¢\006\f\b\r\022\b\b\016\022\004\b\b(\007\022\004\022\0020\n0\020H\b\032\025\020\022\032\0020\b*\0020\0022\006\020\021\032\0020\001H\n\032\r\020\023\032\0020\006*\0020\002H\b\032\r\020\024\032\0020\006*\0020\002H\b\032\023\020\025\032\b\022\004\022\0020\b0\026*\0020\002H\002\"\026\020\000\032\0020\001*\0020\0028Æ\002¢\006\006\032\004\b\003\020\004¨\006\027"}, d2={"size", "", "Landroid/view/Menu;", "getSize", "(Landroid/view/Menu;)I", "contains", "", "item", "Landroid/view/MenuItem;", "forEach", "", "action", "Lkotlin/Function1;", "Lkotlin/ParameterName;", "name", "forEachIndexed", "Lkotlin/Function2;", "index", "get", "isEmpty", "isNotEmpty", "iterator", "", "core-ktx_release"}, k=2, mv={1, 1, 9})
public final class MenuKt
{
  public static final boolean contains(@NotNull Menu paramMenu, @NotNull MenuItem paramMenuItem)
  {
    int j = paramMenu.size();
    int i = 0;
    while (i < j)
    {
      if (Intrinsics.areEqual(paramMenu.getItem(i), paramMenuItem)) {
        return true;
      }
      i += 1;
    }
    return false;
  }
  
  public static final void forEach(@NotNull Menu paramMenu, @NotNull Function1<? super MenuItem, Unit> paramFunction1)
  {
    int j = paramMenu.size();
    int i = 0;
    while (i < j)
    {
      MenuItem localMenuItem = paramMenu.getItem(i);
      Intrinsics.checkExpressionValueIsNotNull(localMenuItem, "getItem(index)");
      paramFunction1.invoke(localMenuItem);
      i += 1;
    }
  }
  
  public static final void forEachIndexed(@NotNull Menu paramMenu, @NotNull Function2<? super Integer, ? super MenuItem, Unit> paramFunction2)
  {
    int j = paramMenu.size();
    int i = 0;
    while (i < j)
    {
      MenuItem localMenuItem = paramMenu.getItem(i);
      Intrinsics.checkExpressionValueIsNotNull(localMenuItem, "getItem(index)");
      paramFunction2.invoke(Integer.valueOf(i), localMenuItem);
      i += 1;
    }
  }
  
  @NotNull
  public static final MenuItem get(@NotNull Menu paramMenu, int paramInt)
  {
    paramMenu = paramMenu.getItem(paramInt);
    Intrinsics.checkExpressionValueIsNotNull(paramMenu, "getItem(index)");
    return paramMenu;
  }
  
  public static final int getSize(@NotNull Menu paramMenu)
  {
    return paramMenu.size();
  }
  
  public static final boolean isEmpty(@NotNull Menu paramMenu)
  {
    return paramMenu.size() == 0;
  }
  
  public static final boolean isNotEmpty(@NotNull Menu paramMenu)
  {
    return paramMenu.size() != 0;
  }
  
  @NotNull
  public static final Iterator<MenuItem> iterator(@NotNull Menu paramMenu)
  {
    (Iterator)new Iterator()
    {
      private int index;
      
      public boolean hasNext()
      {
        return index < receiver$0.size();
      }
      
      @NotNull
      public MenuItem next()
      {
        Object localObject = receiver$0;
        int i = index;
        index = (i + 1);
        localObject = ((Menu)localObject).getItem(i);
        if (localObject != null) {
          return localObject;
        }
        throw ((Throwable)new IndexOutOfBoundsException());
      }
      
      public void remove()
      {
        Menu localMenu = receiver$0;
        index -= 1;
        localMenu.removeItem(index);
      }
    };
  }
}

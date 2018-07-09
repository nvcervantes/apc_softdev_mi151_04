package com.byimplication.sakay;

import com.byimplication.sakay.model.Conveyance;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;

@Metadata(bv={1, 0, 2}, d1={"\000$\n\002\030\002\n\002\020\000\n\002\b\002\n\002\020\021\n\002\030\002\n\002\b\005\n\002\030\002\n\000\n\002\020\b\n\000\bÆ\002\030\0002\0020\001B\007\b\002¢\006\002\020\002J\016\020\t\032\0020\0052\006\020\n\032\0020\013J\016\020\f\032\0020\r2\006\020\n\032\0020\013R\031\020\003\032\b\022\004\022\0020\0050\004¢\006\n\n\002\020\b\032\004\b\006\020\007¨\006\016"}, d2={"Lcom/byimplication/sakay/GuideUtils;", "", "()V", "commuteGuide", "", "Lcom/byimplication/sakay/ModeGuide;", "getCommuteGuide", "()[Lcom/byimplication/sakay/ModeGuide;", "[Lcom/byimplication/sakay/ModeGuide;", "getGuide", "conveyance", "Lcom/byimplication/sakay/model/Conveyance;", "translateConveyanceForGuide", "", "app_release"}, k=1, mv={1, 1, 9})
public final class GuideUtils
{
  public static final GuideUtils INSTANCE = new GuideUtils();
  @NotNull
  private static final ModeGuide[] commuteGuide = { new ModeGuide(2131689653, 2131230860, 2131689538, CollectionsKt.listOf(new GuideInstruction[] { new GuideInstruction("1", 2131230861, 2131689539), new GuideInstruction("2", 2131230862, 2131689540), new GuideInstruction("3", 2131230863, 2131689541), new GuideInstruction("TIP", 2131230864, 2131689542), new GuideInstruction("TIP", 2131230865, 2131689543) })), new ModeGuide(2131689655, 2131230866, 2131689555, CollectionsKt.listOf(new GuideInstruction[] { new GuideInstruction("1", 2131230867, 2131689556), new GuideInstruction("2", 2131230868, 2131689557), new GuideInstruction("3", 2131230869, 2131689558), new GuideInstruction("4", 2131230870, 2131689559) })), new ModeGuide(2131689659, 2131230871, 2131689605, CollectionsKt.listOf(new GuideInstruction[] { new GuideInstruction("1", 2131230872, 2131689606), new GuideInstruction("2", 2131230873, 2131689607), new GuideInstruction("3", 2131230874, 2131689608), new GuideInstruction("4", 2131230875, 2131689609), new GuideInstruction("5", 2131230876, 2131689610), new GuideInstruction("6", 2131230877, 2131689611) })), new ModeGuide(2131689660, 2131230878, 2131689627, CollectionsKt.listOf(new GuideInstruction[] { new GuideInstruction("1", 2131230879, 2131689628), new GuideInstruction("2", 2131230880, 2131689629), new GuideInstruction("3", 2131230881, 2131689630), new GuideInstruction("4", 2131230882, 2131689631), new GuideInstruction("TIP", 2131230883, 2131689632) })), new ModeGuide(2131689668, 2131230884, 2131689687, CollectionsKt.listOf(new GuideInstruction[] { new GuideInstruction("1", 2131230885, 2131689688), new GuideInstruction("2", 2131230888, 2131689691), new GuideInstruction("3", 2131230889, 2131689692), new GuideInstruction("4", 2131230890, 2131689693), new GuideInstruction("5", 2131230891, 2131689694), new GuideInstruction("6", 2131230892, 2131689695), new GuideInstruction("7", 2131230893, 2131689696), new GuideInstruction("8", 2131230894, 2131689697), new GuideInstruction("9", 2131230895, 2131689698), new GuideInstruction("10", 2131230886, 2131689689), new GuideInstruction("11", 2131230887, 2131689690) })), new ModeGuide(2131689675, 2131230896, 2131689793, CollectionsKt.listOf(new GuideInstruction[] { new GuideInstruction("1", 2131230897, 2131689794), new GuideInstruction("2", 2131230898, 2131689795), new GuideInstruction("3", 2131230899, 2131689796), new GuideInstruction("TIP", 2131230900, 2131689797), new GuideInstruction("TIP", 2131230901, 2131689798) })), new ModeGuide(2131689678, 2131230902, 2131689815, CollectionsKt.listOf(new GuideInstruction[] { new GuideInstruction("1", 2131230903, 2131689816), new GuideInstruction("2", 2131230904, 2131689817), new GuideInstruction("3", 2131230905, 2131689818) })), new ModeGuide(2131689682, 2131230906, 2131689827, CollectionsKt.listOf(new GuideInstruction[] { new GuideInstruction("1", 2131230907, 2131689828), new GuideInstruction("2", 2131230908, 2131689829), new GuideInstruction("3", 2131230909, 2131689830), new GuideInstruction("4", 2131230910, 2131689831) })), new ModeGuide(2131689683, 2131230911, 2131689835, CollectionsKt.listOf(new GuideInstruction[] { new GuideInstruction("TIP", 2131230912, 2131689836), new GuideInstruction("TIP", 2131230913, 2131689837), new GuideInstruction("TIP", 2131230914, 2131689838), new GuideInstruction("TIP", 2131230915, 2131689839), new GuideInstruction("TIP", 2131230916, 2131689840) })), new ModeGuide(2131689680, 2131230911, 2131689825, CollectionsKt.emptyList()) };
  
  private GuideUtils() {}
  
  @NotNull
  public final ModeGuide[] getCommuteGuide()
  {
    return commuteGuide;
  }
  
  @NotNull
  public final ModeGuide getGuide(@NotNull Conveyance paramConveyance)
  {
    Intrinsics.checkParameterIsNotNull(paramConveyance, "conveyance");
    paramConveyance = paramConveyance.getPrimary();
    switch (paramConveyance.hashCode())
    {
    default: 
      break;
    case 66783482: 
      if (paramConveyance.equals("FERRY")) {
        return commuteGuide[2];
      }
      break;
    case 65996011: 
      if (paramConveyance.equals("EJEEP")) {
        return commuteGuide[1];
      }
      break;
    case 2656713: 
      if (paramConveyance.equals("WALK")) {
        return commuteGuide[8];
      }
      break;
    case 2567710: 
      if (paramConveyance.equals("TAXI")) {
        return commuteGuide[5];
      }
      break;
    case 2507666: 
      if (paramConveyance.equals("RAIL")) {
        return commuteGuide[4];
      }
      break;
    case 2273062: 
      if (paramConveyance.equals("JEEP")) {
        return commuteGuide[3];
      }
      break;
    case 66144: 
      if (paramConveyance.equals("BUS")) {
        return commuteGuide[0];
      }
      break;
    case 2721: 
      if (paramConveyance.equals("UV")) {
        return commuteGuide[7];
      }
      break;
    case -2012407269: 
      if (paramConveyance.equals("TRICYCLE")) {
        return commuteGuide[6];
      }
      break;
    }
    return commuteGuide[9];
  }
  
  public final int translateConveyanceForGuide(@NotNull Conveyance paramConveyance)
  {
    Intrinsics.checkParameterIsNotNull(paramConveyance, "conveyance");
    String str = paramConveyance.getPrimary();
    switch (str.hashCode())
    {
    default: 
      break;
    case 66783482: 
      if (str.equals("FERRY")) {
        return 2131689659;
      }
      break;
    case 65996011: 
      if (str.equals("EJEEP")) {
        return 2131689657;
      }
      break;
    case 2656713: 
      if (str.equals("WALK")) {
        return 2131689683;
      }
      break;
    case 2567710: 
      if (str.equals("TAXI")) {
        return 2131689675;
      }
      break;
    case 2507666: 
      if (str.equals("RAIL"))
      {
        str = paramConveyance.getSecondary();
        if (str != null)
        {
          int i = str.hashCode();
          if (i != 2375364) {
            switch (i)
            {
            default: 
              break;
            case 2345572: 
              paramConveyance = "LRT2";
            }
          } else {
            for (;;)
            {
              str.equals(paramConveyance);
              break;
              paramConveyance = "LRT1";
              continue;
              paramConveyance = "MRT3";
            }
          }
        }
        return 2131689668;
      }
      break;
    case 2273062: 
      if (str.equals("JEEP")) {
        return 2131689660;
      }
      break;
    case 66144: 
      if (str.equals("BUS")) {
        return 2131689653;
      }
      break;
    case 2721: 
      if (str.equals("UV")) {
        return 2131689682;
      }
      break;
    case -2012407269: 
      if (str.equals("TRICYCLE")) {
        return 2131689678;
      }
      break;
    }
    return 2131689680;
  }
}

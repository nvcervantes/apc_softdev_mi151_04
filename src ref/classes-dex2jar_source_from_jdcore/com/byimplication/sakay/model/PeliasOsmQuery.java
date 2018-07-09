package com.byimplication.sakay.model;

import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

@Metadata(bv={1, 0, 2}, d1={"\0004\n\002\030\002\n\002\020\000\n\000\n\002\020\016\n\002\b\002\n\002\020 \n\000\n\002\020\b\n\000\n\002\020\013\n\000\n\002\020\006\n\002\b\002\n\002\030\002\n\002\b\037\b\b\030\0002\0020\001BW\022\b\020\002\032\004\030\0010\003\022\b\020\004\032\004\030\0010\003\022\016\020\005\032\n\022\004\022\0020\003\030\0010\006\022\006\020\007\032\0020\b\022\006\020\t\032\0020\n\022\b\020\013\032\004\030\0010\f\022\b\020\r\032\004\030\0010\f\022\b\020\016\032\004\030\0010\017¢\006\002\020\020J\013\020 \032\004\030\0010\003HÆ\003J\013\020!\032\004\030\0010\003HÆ\003J\021\020\"\032\n\022\004\022\0020\003\030\0010\006HÆ\003J\t\020#\032\0020\bHÆ\003J\t\020$\032\0020\nHÆ\003J\020\020%\032\004\030\0010\fHÆ\003¢\006\002\020\022J\020\020&\032\004\030\0010\fHÆ\003¢\006\002\020\022J\013\020'\032\004\030\0010\017HÆ\003Jp\020(\032\0020\0002\n\b\002\020\002\032\004\030\0010\0032\n\b\002\020\004\032\004\030\0010\0032\020\b\002\020\005\032\n\022\004\022\0020\003\030\0010\0062\b\b\002\020\007\032\0020\b2\b\b\002\020\t\032\0020\n2\n\b\002\020\013\032\004\030\0010\f2\n\b\002\020\r\032\004\030\0010\f2\n\b\002\020\016\032\004\030\0010\017HÆ\001¢\006\002\020)J\023\020*\032\0020\n2\b\020+\032\004\030\0010\001HÖ\003J\t\020,\032\0020\bHÖ\001J\t\020-\032\0020\003HÖ\001R\025\020\013\032\004\030\0010\f¢\006\n\n\002\020\023\032\004\b\021\020\022R\025\020\r\032\004\030\0010\f¢\006\n\n\002\020\023\032\004\b\024\020\022R\023\020\016\032\004\030\0010\017¢\006\b\n\000\032\004\b\025\020\026R\023\020\004\032\004\030\0010\003¢\006\b\n\000\032\004\b\027\020\030R\021\020\t\032\0020\n¢\006\b\n\000\032\004\b\031\020\032R\021\020\007\032\0020\b¢\006\b\n\000\032\004\b\033\020\034R\023\020\002\032\004\030\0010\003¢\006\b\n\000\032\004\b\035\020\030R\031\020\005\032\n\022\004\022\0020\003\030\0010\006¢\006\b\n\000\032\004\b\036\020\037¨\006."}, d2={"Lcom/byimplication/sakay/model/PeliasOsmQuery;", "", "text", "", "parser", "tokens", "", "size", "", "private", "", "focusPointLat", "", "focusPointLon", "lang", "Lcom/byimplication/sakay/model/PeliasOsmLang;", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;IZLjava/lang/Double;Ljava/lang/Double;Lcom/byimplication/sakay/model/PeliasOsmLang;)V", "getFocusPointLat", "()Ljava/lang/Double;", "Ljava/lang/Double;", "getFocusPointLon", "getLang", "()Lcom/byimplication/sakay/model/PeliasOsmLang;", "getParser", "()Ljava/lang/String;", "getPrivate", "()Z", "getSize", "()I", "getText", "getTokens", "()Ljava/util/List;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "copy", "(Ljava/lang/String;Ljava/lang/String;Ljava/util/List;IZLjava/lang/Double;Ljava/lang/Double;Lcom/byimplication/sakay/model/PeliasOsmLang;)Lcom/byimplication/sakay/model/PeliasOsmQuery;", "equals", "other", "hashCode", "toString", "app_release"}, k=1, mv={1, 1, 9})
public final class PeliasOsmQuery
{
  @Nullable
  private final Double focusPointLat;
  @Nullable
  private final Double focusPointLon;
  @Nullable
  private final PeliasOsmLang lang;
  @Nullable
  private final String parser;
  private final boolean private;
  private final int size;
  @Nullable
  private final String text;
  @Nullable
  private final List<String> tokens;
  
  public PeliasOsmQuery(@Nullable String paramString1, @Nullable String paramString2, @Nullable List<String> paramList, int paramInt, boolean paramBoolean, @Nullable Double paramDouble1, @Nullable Double paramDouble2, @Nullable PeliasOsmLang paramPeliasOsmLang)
  {
    text = paramString1;
    parser = paramString2;
    tokens = paramList;
    size = paramInt;
    private = paramBoolean;
    focusPointLat = paramDouble1;
    focusPointLon = paramDouble2;
    lang = paramPeliasOsmLang;
  }
  
  @Nullable
  public final String component1()
  {
    return text;
  }
  
  @Nullable
  public final String component2()
  {
    return parser;
  }
  
  @Nullable
  public final List<String> component3()
  {
    return tokens;
  }
  
  public final int component4()
  {
    return size;
  }
  
  public final boolean component5()
  {
    return private;
  }
  
  @Nullable
  public final Double component6()
  {
    return focusPointLat;
  }
  
  @Nullable
  public final Double component7()
  {
    return focusPointLon;
  }
  
  @Nullable
  public final PeliasOsmLang component8()
  {
    return lang;
  }
  
  @NotNull
  public final PeliasOsmQuery copy(@Nullable String paramString1, @Nullable String paramString2, @Nullable List<String> paramList, int paramInt, boolean paramBoolean, @Nullable Double paramDouble1, @Nullable Double paramDouble2, @Nullable PeliasOsmLang paramPeliasOsmLang)
  {
    return new PeliasOsmQuery(paramString1, paramString2, paramList, paramInt, paramBoolean, paramDouble1, paramDouble2, paramPeliasOsmLang);
  }
  
  public boolean equals(Object paramObject)
  {
    if (this != paramObject)
    {
      if ((paramObject instanceof PeliasOsmQuery))
      {
        paramObject = (PeliasOsmQuery)paramObject;
        if ((Intrinsics.areEqual(text, text)) && (Intrinsics.areEqual(parser, parser)) && (Intrinsics.areEqual(tokens, tokens)))
        {
          int i;
          if (size == size) {
            i = 1;
          } else {
            i = 0;
          }
          if (i != 0)
          {
            if (private == private) {
              i = 1;
            } else {
              i = 0;
            }
            if ((i != 0) && (Intrinsics.areEqual(focusPointLat, focusPointLat)) && (Intrinsics.areEqual(focusPointLon, focusPointLon)) && (Intrinsics.areEqual(lang, lang))) {
              return true;
            }
          }
        }
      }
      return false;
    }
    return true;
  }
  
  @Nullable
  public final Double getFocusPointLat()
  {
    return focusPointLat;
  }
  
  @Nullable
  public final Double getFocusPointLon()
  {
    return focusPointLon;
  }
  
  @Nullable
  public final PeliasOsmLang getLang()
  {
    return lang;
  }
  
  @Nullable
  public final String getParser()
  {
    return parser;
  }
  
  public final boolean getPrivate()
  {
    return private;
  }
  
  public final int getSize()
  {
    return size;
  }
  
  @Nullable
  public final String getText()
  {
    return text;
  }
  
  @Nullable
  public final List<String> getTokens()
  {
    return tokens;
  }
  
  public int hashCode()
  {
    throw new Runtime("d2j fail translate: java.lang.RuntimeException: can not merge I and Z\r\n\tat com.googlecode.dex2jar.ir.TypeClass.merge(TypeClass.java:100)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeRef.updateTypeClass(TypeTransformer.java:174)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.copyTypes(TypeTransformer.java:311)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.fixTypes(TypeTransformer.java:226)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer$TypeAnalyze.analyze(TypeTransformer.java:207)\r\n\tat com.googlecode.dex2jar.ir.ts.TypeTransformer.transform(TypeTransformer.java:44)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.optimize(Dex2jar.java:162)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertCode(Dex2Asm.java:414)\r\n\tat com.googlecode.d2j.dex.ExDex2Asm.convertCode(ExDex2Asm.java:42)\r\n\tat com.googlecode.d2j.dex.Dex2jar$2.convertCode(Dex2jar.java:128)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertMethod(Dex2Asm.java:509)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertClass(Dex2Asm.java:406)\r\n\tat com.googlecode.d2j.dex.Dex2Asm.convertDex(Dex2Asm.java:422)\r\n\tat com.googlecode.d2j.dex.Dex2jar.doTranslate(Dex2jar.java:172)\r\n\tat com.googlecode.d2j.dex.Dex2jar.to(Dex2jar.java:272)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.doCommandLine(Dex2jarCmd.java:108)\r\n\tat com.googlecode.dex2jar.tools.BaseCmd.doMain(BaseCmd.java:288)\r\n\tat com.googlecode.dex2jar.tools.Dex2jarCmd.main(Dex2jarCmd.java:32)\r\n");
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PeliasOsmQuery(text=");
    localStringBuilder.append(text);
    localStringBuilder.append(", parser=");
    localStringBuilder.append(parser);
    localStringBuilder.append(", tokens=");
    localStringBuilder.append(tokens);
    localStringBuilder.append(", size=");
    localStringBuilder.append(size);
    localStringBuilder.append(", private=");
    localStringBuilder.append(private);
    localStringBuilder.append(", focusPointLat=");
    localStringBuilder.append(focusPointLat);
    localStringBuilder.append(", focusPointLon=");
    localStringBuilder.append(focusPointLon);
    localStringBuilder.append(", lang=");
    localStringBuilder.append(lang);
    localStringBuilder.append(")");
    return localStringBuilder.toString();
  }
}

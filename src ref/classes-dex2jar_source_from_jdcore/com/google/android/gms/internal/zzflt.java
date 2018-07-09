package com.google.android.gms.internal;

import java.lang.reflect.Array;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public final class zzflt
{
  public static <T extends zzfls> String zza(T paramT)
  {
    if (paramT == null) {
      return "";
    }
    StringBuffer localStringBuffer = new StringBuffer();
    try
    {
      zza(null, paramT, new StringBuffer(), localStringBuffer);
      return localStringBuffer.toString();
    }
    catch (InvocationTargetException paramT)
    {
      paramT = String.valueOf(paramT.getMessage());
      if (paramT.length() != 0) {
        return "Error printing proto: ".concat(paramT);
      }
      return new String("Error printing proto: ");
    }
    catch (IllegalAccessException paramT)
    {
      paramT = String.valueOf(paramT.getMessage());
      if (paramT.length() != 0) {
        return "Error printing proto: ".concat(paramT);
      }
    }
    return new String("Error printing proto: ");
  }
  
  private static String zza(String paramString)
  {
    StringBuffer localStringBuffer = new StringBuffer();
    int i = 0;
    while (i < paramString.length())
    {
      char c2 = paramString.charAt(i);
      if (i == 0) {}
      for (;;)
      {
        char c1 = Character.toLowerCase(c2);
        do
        {
          localStringBuffer.append(c1);
          break;
          c1 = c2;
        } while (!Character.isUpperCase(c2));
        localStringBuffer.append('_');
      }
      i += 1;
    }
    return localStringBuffer.toString();
  }
  
  private static void zza(String paramString, Object paramObject, StringBuffer paramStringBuffer1, StringBuffer paramStringBuffer2)
    throws IllegalAccessException, InvocationTargetException
  {
    if (paramObject != null)
    {
      if ((paramObject instanceof zzfls))
      {
        int m = paramStringBuffer1.length();
        if (paramString != null)
        {
          paramStringBuffer2.append(paramStringBuffer1);
          paramStringBuffer2.append(zza(paramString));
          paramStringBuffer2.append(" <\n");
          paramStringBuffer1.append("  ");
        }
        Class localClass = paramObject.getClass();
        Object localObject1 = localClass.getFields();
        int n = localObject1.length;
        int i = 0;
        Object localObject3;
        while (i < n)
        {
          Object localObject4 = localObject1[i];
          j = localObject4.getModifiers();
          localObject2 = localObject4.getName();
          if ((!"cachedSize".equals(localObject2)) && ((j & 0x1) == 1) && ((j & 0x8) != 8) && (!((String)localObject2).startsWith("_")) && (!((String)localObject2).endsWith("_")))
          {
            localObject3 = localObject4.getType();
            localObject4 = localObject4.get(paramObject);
            int k;
            if ((((Class)localObject3).isArray()) && (((Class)localObject3).getComponentType() != Byte.TYPE))
            {
              if (localObject4 == null) {
                j = 0;
              } else {
                j = Array.getLength(localObject4);
              }
              k = 0;
            }
            while (k < j)
            {
              zza((String)localObject2, Array.get(localObject4, k), paramStringBuffer1, paramStringBuffer2);
              k += 1;
              continue;
              zza((String)localObject2, localObject4, paramStringBuffer1, paramStringBuffer2);
            }
          }
          i += 1;
        }
        Object localObject2 = localClass.getMethods();
        int j = localObject2.length;
        i = 0;
        while (i < j)
        {
          localObject1 = localObject2[i].getName();
          if (((String)localObject1).startsWith("set")) {
            localObject3 = ((String)localObject1).substring(3);
          }
          try
          {
            localObject1 = String.valueOf(localObject3);
            if (((String)localObject1).length() != 0) {
              localObject1 = "has".concat((String)localObject1);
            } else {
              localObject1 = new String("has");
            }
            localObject1 = localClass.getMethod((String)localObject1, new Class[0]);
            if (((Boolean)((Method)localObject1).invoke(paramObject, new Object[0])).booleanValue())
            {
              localObject1 = String.valueOf(localObject3);
              if (((String)localObject1).length() != 0) {
                localObject1 = "get".concat((String)localObject1);
              } else {
                localObject1 = new String("get");
              }
              localObject1 = localClass.getMethod((String)localObject1, new Class[0]);
              zza((String)localObject3, ((Method)localObject1).invoke(paramObject, new Object[0]), paramStringBuffer1, paramStringBuffer2);
            }
          }
          catch (NoSuchMethodException localNoSuchMethodException)
          {
            for (;;) {}
          }
          i += 1;
        }
        if (paramString != null)
        {
          paramStringBuffer1.setLength(m);
          paramStringBuffer2.append(paramStringBuffer1);
          paramStringBuffer2.append(">\n");
        }
        return;
      }
      paramString = zza(paramString);
      paramStringBuffer2.append(paramStringBuffer1);
      paramStringBuffer2.append(paramString);
      paramStringBuffer2.append(": ");
      if ((paramObject instanceof String))
      {
        paramObject = (String)paramObject;
        paramString = paramObject;
        if (!paramObject.startsWith("http"))
        {
          paramString = paramObject;
          if (paramObject.length() > 200) {
            paramString = String.valueOf(paramObject.substring(0, 200)).concat("[...]");
          }
        }
        paramString = zzb(paramString);
        paramStringBuffer2.append("\"");
        paramStringBuffer2.append(paramString);
        paramStringBuffer2.append("\"");
      }
      else if ((paramObject instanceof byte[]))
      {
        zza((byte[])paramObject, paramStringBuffer2);
      }
      else
      {
        paramStringBuffer2.append(paramObject);
      }
      paramStringBuffer2.append("\n");
    }
  }
  
  private static void zza(byte[] paramArrayOfByte, StringBuffer paramStringBuffer)
  {
    if (paramArrayOfByte == null)
    {
      paramStringBuffer.append("\"\"");
      return;
    }
    paramStringBuffer.append('"');
    int i = 0;
    while (i < paramArrayOfByte.length)
    {
      int j = paramArrayOfByte[i] & 0xFF;
      if ((j != 92) && (j != 34))
      {
        if ((j < 32) || (j >= 127))
        {
          paramStringBuffer.append(String.format("\\%03o", new Object[] { Integer.valueOf(j) }));
          break label103;
        }
      }
      else {
        paramStringBuffer.append('\\');
      }
      paramStringBuffer.append((char)j);
      label103:
      i += 1;
    }
    paramStringBuffer.append('"');
  }
  
  private static String zzb(String paramString)
  {
    int j = paramString.length();
    StringBuilder localStringBuilder = new StringBuilder(j);
    int i = 0;
    while (i < j)
    {
      char c = paramString.charAt(i);
      if ((c >= ' ') && (c <= '~') && (c != '"') && (c != '\'')) {
        localStringBuilder.append(c);
      } else {
        localStringBuilder.append(String.format("\\u%04x", new Object[] { Integer.valueOf(c) }));
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
}

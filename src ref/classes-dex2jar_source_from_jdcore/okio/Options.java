package okio;

import java.util.AbstractList;
import java.util.RandomAccess;

public final class Options
  extends AbstractList<ByteString>
  implements RandomAccess
{
  final ByteString[] byteStrings;
  
  private Options(ByteString[] paramArrayOfByteString)
  {
    byteStrings = paramArrayOfByteString;
  }
  
  public static Options of(ByteString... paramVarArgs)
  {
    return new Options((ByteString[])paramVarArgs.clone());
  }
  
  public ByteString get(int paramInt)
  {
    return byteStrings[paramInt];
  }
  
  public int size()
  {
    return byteStrings.length;
  }
}

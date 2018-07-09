package com.github.mikephil.charting.data;

import android.graphics.drawable.Drawable;
import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.github.mikephil.charting.utils.Utils;

public class Entry
  extends BaseEntry
  implements Parcelable
{
  public static final Parcelable.Creator<Entry> CREATOR = new Parcelable.Creator()
  {
    public Entry createFromParcel(Parcel paramAnonymousParcel)
    {
      return new Entry(paramAnonymousParcel);
    }
    
    public Entry[] newArray(int paramAnonymousInt)
    {
      return new Entry[paramAnonymousInt];
    }
  };
  private float x = 0.0F;
  
  public Entry() {}
  
  public Entry(float paramFloat1, float paramFloat2)
  {
    super(paramFloat2);
    x = paramFloat1;
  }
  
  public Entry(float paramFloat1, float paramFloat2, Drawable paramDrawable)
  {
    super(paramFloat2, paramDrawable);
    x = paramFloat1;
  }
  
  public Entry(float paramFloat1, float paramFloat2, Drawable paramDrawable, Object paramObject)
  {
    super(paramFloat2, paramDrawable, paramObject);
    x = paramFloat1;
  }
  
  public Entry(float paramFloat1, float paramFloat2, Object paramObject)
  {
    super(paramFloat2, paramObject);
    x = paramFloat1;
  }
  
  protected Entry(Parcel paramParcel)
  {
    x = paramParcel.readFloat();
    setY(paramParcel.readFloat());
    if (paramParcel.readInt() == 1) {
      setData(paramParcel.readParcelable(Object.class.getClassLoader()));
    }
  }
  
  public Entry copy()
  {
    return new Entry(x, getY(), getData());
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equalTo(Entry paramEntry)
  {
    if (paramEntry == null) {
      return false;
    }
    if (paramEntry.getData() != getData()) {
      return false;
    }
    if (Math.abs(x - x) > Utils.FLOAT_EPSILON) {
      return false;
    }
    return Math.abs(paramEntry.getY() - getY()) <= Utils.FLOAT_EPSILON;
  }
  
  public float getX()
  {
    return x;
  }
  
  public void setX(float paramFloat)
  {
    x = paramFloat;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("Entry, x: ");
    localStringBuilder.append(x);
    localStringBuilder.append(" y: ");
    localStringBuilder.append(getY());
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeFloat(x);
    paramParcel.writeFloat(getY());
    if (getData() != null)
    {
      if ((getData() instanceof Parcelable))
      {
        paramParcel.writeInt(1);
        paramParcel.writeParcelable((Parcelable)getData(), paramInt);
        return;
      }
      throw new ParcelFormatException("Cannot parcel an Entry with non-parcelable data");
    }
    paramParcel.writeInt(0);
  }
}

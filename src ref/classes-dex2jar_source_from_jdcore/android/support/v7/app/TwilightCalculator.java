package android.support.v7.app;

class TwilightCalculator
{
  private static final float ALTIDUTE_CORRECTION_CIVIL_TWILIGHT = -0.10471976F;
  private static final float C1 = 0.0334196F;
  private static final float C2 = 3.49066E-4F;
  private static final float C3 = 5.236E-6F;
  public static final int DAY = 0;
  private static final float DEGREES_TO_RADIANS = 0.017453292F;
  private static final float J0 = 9.0E-4F;
  public static final int NIGHT = 1;
  private static final float OBLIQUITY = 0.4092797F;
  private static final long UTC_2000 = 946728000000L;
  private static TwilightCalculator sInstance;
  public int state;
  public long sunrise;
  public long sunset;
  
  TwilightCalculator() {}
  
  static TwilightCalculator getInstance()
  {
    if (sInstance == null) {
      sInstance = new TwilightCalculator();
    }
    return sInstance;
  }
  
  public void calculateTwilight(long paramLong, double paramDouble1, double paramDouble2)
  {
    float f1 = (float)(paramLong - 946728000000L) / 8.64E7F;
    float f2 = 6.24006F + 0.01720197F * f1;
    double d2 = f2;
    double d1 = 0.03341960161924362D * Math.sin(d2) + d2 + 3.4906598739326E-4D * Math.sin(2.0F * f2) + 5.236000106378924E-6D * Math.sin(3.0F * f2) + 1.796593063D + 3.141592653589793D;
    paramDouble2 = -paramDouble2 / 360.0D;
    paramDouble2 = (float)Math.round(f1 - 9.0E-4F - paramDouble2) + 9.0E-4F + paramDouble2 + 0.0053D * Math.sin(d2) + -0.0069D * Math.sin(2.0D * d1);
    d1 = Math.asin(Math.sin(d1) * Math.sin(0.4092797040939331D));
    paramDouble1 = 0.01745329238474369D * paramDouble1;
    paramDouble1 = (Math.sin(-0.10471975803375244D) - Math.sin(paramDouble1) * Math.sin(d1)) / (Math.cos(paramDouble1) * Math.cos(d1));
    if (paramDouble1 >= 1.0D)
    {
      state = 1;
      sunset = -1L;
      sunrise = -1L;
      return;
    }
    if (paramDouble1 <= -1.0D)
    {
      state = 0;
      sunset = -1L;
      sunrise = -1L;
      return;
    }
    paramDouble1 = (float)(Math.acos(paramDouble1) / 6.283185307179586D);
    sunset = (Math.round((paramDouble2 + paramDouble1) * 8.64E7D) + 946728000000L);
    sunrise = (Math.round((paramDouble2 - paramDouble1) * 8.64E7D) + 946728000000L);
    if ((sunrise < paramLong) && (sunset > paramLong))
    {
      state = 0;
      return;
    }
    state = 1;
  }
}

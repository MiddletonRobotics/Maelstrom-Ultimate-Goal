package MidnightLibrary.MidnightResources;

import static java.lang.System.nanoTime;
import static java.util.Locale.US;

public class MidnightClock implements MidnightHardware {
    private long startTime;


    public MidnightClock() {this.reset();}
    public void reset() {startTime = nanoTime();}

    public long nanoseconds() {return nanoTime() - startTime;}
    public double milliseconds() {return nanoseconds() * 1E-6;}
    public double seconds() {return nanoseconds() * 1E-9;}

    public enum Resolution {
        NANOSECONDS (1),
        MILLISECONDS (1E6),
        SECONDS (1E9);
        private final double multiplier;
        Resolution (double multiplier) {this.multiplier = multiplier;}
    }


    public boolean hasNotPassed(double time, Resolution resolution) {
        return nanoseconds() <= (long) (time * resolution.multiplier);
    }
    public boolean hasNotPassed(double time) {
        return hasNotPassed(time, Resolution.SECONDS);
    }

    public String getName() {return "CLOCK";}
    public String[] getDash() {
        return new String[]{
                String.format(US, "Seconds passed: %.5f", seconds())
        };
    }

}
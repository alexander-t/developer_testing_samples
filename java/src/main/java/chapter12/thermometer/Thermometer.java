package chapter12.thermometer;

import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.util.Locale;

/**
 * Thermometer class that shows that the indirect output in update()
 * is a result of indirect input (value from the sensor) and a
 * transformation (the formatting/conversion).
 */
public class Thermometer {
    private DisplayMode displayMode;
    private Sensor sensor;
    private Display display;
    private DecimalFormat df;

    public Thermometer(DisplayMode displayMode) {
        this.displayMode = displayMode;

        // Since code lives in Europe...
        df = new DecimalFormat("#.0", new DecimalFormatSymbols(Locale.US));
    }

    public void update() {
        double temperature = sensor.getTemperature();

        if (displayMode == DisplayMode.CELSIUS) {
            display.output(formatForDisplay(temperature));
        } else {
            display.output(formatForDisplay(
                    celsiusToFahrenheit(temperature)));
        }
    }

    private double celsiusToFahrenheit(double celsius) {
        return celsius * 1.8 + 32;
    }

    private String formatForDisplay(double number) {
        return df.format(number) + " °"
                + displayMode.getSymbol();
    }
}

package calculatorOptions;

public class CalculatorOptions {

    private static final CalculatorOptions instance = new CalculatorOptions();

    public DisplayMode getDisplayMode() {
        return displayMode;
    }

    public void setDisplayMode(DisplayMode displayMode) {
        this.displayMode = displayMode;
    }

    private DisplayMode displayMode = DisplayMode.DECIMAL;

    public UnitsMode getUnitsMode() {
        return unitsMode;
    }

    public void setUnitsMode(UnitsMode unitsMode) {
        this.unitsMode = unitsMode;
    }

    private UnitsMode unitsMode = UnitsMode.RADIANS;

    //private constructor to avoid client applications to use constructor
    private CalculatorOptions(){}
    public static CalculatorOptions getInstance(){
        return instance;
    }

    public void rotateMode() {
        this.displayMode = this.displayMode.getNextMode();
    }
}
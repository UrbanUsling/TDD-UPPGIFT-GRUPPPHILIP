public class Car {
    private boolean fullLightsOn = false;
    private boolean halfLightsOn = false;
    private boolean brakeLightsOn = false;
    private boolean emergencyLightsOn = false;
    private boolean engineRunning = false;
    private boolean brakeApplied = false;

    private boolean driveApplied = false;
    private boolean reverseApplied = false;
    private boolean cableAttached = false;

    private int speed = 0;
    private double batteryLevel = 100;

    public void setBatteryLevel(double batteryLevel) {
        this.batteryLevel = batteryLevel;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public int getSpeed() {
        return speed;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }
    // Constructors, getters, and setters go here

    public void turnOnLights(String state) {
        if(state.equalsIgnoreCase("full")){
            fullLightsOn = true;
            halfLightsOn = false;
            setBatteryLevel(batteryLevel -0.5);
        }else if(state.equalsIgnoreCase("half")){
            halfLightsOn = true;
            fullLightsOn = false;
            setBatteryLevel(batteryLevel -0.3);
        }

    }
    public void turnBreakLightsOn(){
        brakeLightsOn = true;
        setBatteryLevel(batteryLevel -0.5);
    }

    public void turnOffLights() {
        fullLightsOn = false;
        halfLightsOn = false;
    }
    public void clutch(String state) {
        if (getSpeed() == 0) {
            if (state.equalsIgnoreCase("drive")) {
                driveApplied = true;
                reverseApplied = false;
            } else if (state.equalsIgnoreCase("reverse")) {
                reverseApplied = true;
                driveApplied = false;
            }
        }
    }
    public void turnOnEmergencyLights() {
        emergencyLightsOn = true;
    }
    public void turnOffEmergencyLights(){
        emergencyLightsOn = false;
    }

    public void startEngine() {
        if(batteryLevel>0.5) {
            engineRunning = true;
            turnOnLights("half");
        }
    }

    public void stopEngine() {
        engineRunning = false;
        turnOffLights();
        turnOffEmergencyLights();
        brakeApplied = false;
        reverseApplied = false;
        driveApplied = false;
        brakeLightsOn = false;
    }

    public void accelerate(int speed) {
        brakeLightsOn = false;
        brakeApplied = false;
        if (driveApplied||reverseApplied) {
            if (batteryLevel > (batteryLevel - 0.1 * speed)) {
                setSpeed(speed);
                setBatteryLevel((batteryLevel - 0.1 * speed));
            }
        }
    }


    public void brake(int speed) {
        this.speed = speed;
        brakeApplied = true;
        if (speed < getSpeed() && speed >= 0) {
            turnBreakLightsOn();
            setSpeed(speed);
        }
    }

    public void chargeBattery() {
        cableAttached = true;
        double batteryGap = 100-getBatteryLevel();
        System.out.println("It will take " + batteryGap+ "% charging");
        setBatteryLevel(100);
        cableAttached = false;
        System.out.println("The battery is fully loaded an the cost is " + (batteryGap*10));
    }

    public boolean isFullLightsOn() {
        return fullLightsOn;
    }

    public boolean isHalfLightsOn() {
        return halfLightsOn;
    }

    public boolean isEmergencyLightsOn() {
        return emergencyLightsOn;
    }

    public boolean isEngineRunning() {
        return engineRunning;
    }

    public boolean isBrakeApplied() {
        return brakeApplied;
    }

    public boolean isBrakeLightsOn() {
        return brakeLightsOn;
    }

    public boolean isDriveApplied() {
        return driveApplied;
    }

    public boolean isReverseApplied() {
        return reverseApplied;
    }
}

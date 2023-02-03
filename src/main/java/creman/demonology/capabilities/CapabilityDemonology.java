package creman.demonology.capabilities;

public class CapabilityDemonology implements ICapabilityDemonology
{
    private float fogRed = 0.0F;
    private float fogGreen = 0.0F;
    private float fogBlue = 0.0F;
    private float fogDensity = 0.0F;
    private boolean ritualActive = false;

    @Override
    public void setFogParameter(int parameter, float value)
    {
        switch (parameter) {
            case 0:
                setFogDensity(value);
                break;
            case 1:
                setFogRed(value);
                break;
            case 2:
                setFogGreen(value);
                break;
            case 3:
                setFogBlue(value);
                break;
            default:
                break;
        }
    }

    @Override
    public float getFogParameter(int parameter)
    {
        switch (parameter) {
            case 0:
                return this.fogDensity;
            case 1:
                return this.fogRed;
            case 2:
                return this.fogGreen;
            case 3:
                return this.fogBlue;
            default:
                return 0.0F;
        }
    }

    @Override
    public void fillFogParameter(int parameter, float value)
    {
        setFogParameter(parameter, getFogParameter(parameter) + value);
    }

    @Override
    public void consumeFogParameter(int parameter, float value)
    {
        setFogParameter(parameter, getFogParameter(parameter) - value);
    }

    @Override
    public boolean isRitualActive()
    {
        return this.ritualActive;
    }

    @Override
    public void setRitualActive(boolean ritualActive)
    {
        this.ritualActive = ritualActive;
    }

    private void setFogRed(float value)
    {
        this.fogRed = handlerSetFog(value);
    }
    private void setFogGreen(float value)
    {
        this.fogGreen = handlerSetFog(value);
    }
    private void setFogBlue(float value)
    {
        this.fogBlue = handlerSetFog(value);
    }
    private void setFogDensity(float value)
    {
        this.fogDensity = handlerSetFog(value);
    }
    private float handlerSetFog(float value)
    {
        if(value >= 1.0F) return 1.0F;
        else return Math.max(value, 0.0F);
    }
}

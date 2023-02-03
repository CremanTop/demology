package creman.demonology.capabilities;

public interface IMana
{
    public void consume(float points);
    public void fill(float points);
    public void set(float points);

    public float getMana();
}

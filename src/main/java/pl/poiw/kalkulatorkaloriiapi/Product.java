package pl.poiw.kalkulatorkaloriiapi;

public class Product
{
    private Items[] items;

    public Items[] getItems ()
    {
        return items;
    }

    public void setItems (Items[] items)
    {
        this.items = items;
    }

    @Override
    public String toString()
    {
        return "Product [items = "+items+"]";
    }
}

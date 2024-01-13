package pl.poiw.kalkulatorkaloriiapi.model.meal;

import pl.poiw.kalkulatorkaloriiapi.model.Items;

public class SummaryDB {
    private static Items summaryItem = new Items();

    public static Items getSummaryItem() {
        return summaryItem;
    }

    public static void setSummaryItem(Items summaryItem) {
        SummaryDB.summaryItem = summaryItem;
    }

}

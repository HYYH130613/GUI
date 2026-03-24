package task04;

import task04.items.Item;

import java.util.Comparator;

public class ItemNameComparator implements Comparator<Item> {

    @Override
    public int compare(Item a, Item b) {
        return a.getName().compareToIgnoreCase(b.getName());
    }
}

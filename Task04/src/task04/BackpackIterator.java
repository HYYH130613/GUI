package task04;

import task04.items.Item;

import java.util.Iterator;
import java.util.NoSuchElementException;

class BackpackIterator implements Iterator<Item> {
    private Item[] items;
    private int itemCount;
    private int currentIndex;

    public BackpackIterator(Item[] items, int itemCount) {
        this.items = items;
        this.itemCount = itemCount;
        currentIndex = 0;
        skipWorthless();
    }

    private void skipWorthless() {
        while (currentIndex < itemCount && items[currentIndex].isWorthless()) {
            currentIndex++;
        }
    }

    @Override
    public boolean hasNext() {
        return currentIndex < itemCount;
    }

    @Override
    public Item next() {
        if (hasNext() == false) {
            throw new NoSuchElementException("No more valuable items");
        }

        Item item = items[currentIndex++];
        skipWorthless();
        return item;
    }
}

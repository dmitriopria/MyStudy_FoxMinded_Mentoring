package ua.foxminded.task5;

public class CacheDecorator implements CharCounter {
    private Cache cache = new Cache();
    private CharCounter resultCharCounter;

    public CacheDecorator(CharCounter resultCharCounter) {
        this.resultCharCounter = resultCharCounter;
    }

    @Override
    public String countCharacters(String newInput) {
        if (cache.containsValue(newInput)) {
            return cache.get(newInput);
        }
        String result = resultCharCounter.countCharacters(newInput);
        cache.put(newInput, result);
        return result;
    }
}

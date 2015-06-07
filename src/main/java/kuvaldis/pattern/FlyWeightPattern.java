package kuvaldis.pattern;

import java.util.HashMap;

// let you reuse heavy objects
public class FlyWeightPattern {

    private static class HeavyObject {
        private void longInitOperation() {
            System.out.println("That's really long lasting and resource consuming operation");
        }
        private void action() {
            System.out.println("Action is done");
        }
    }

    private static class Factory {

        private final HashMap<String, HeavyObject> pull = new HashMap<>();

        private HeavyObject create(final String key) {
            return pull.computeIfAbsent(key, it -> {
                final HeavyObject heavyObject = new HeavyObject();
                heavyObject.longInitOperation();
                return heavyObject;
            });
        }
    }

    public static void main(String[] args) {
        final Factory factory = new Factory();
        final HeavyObject object1 = factory.create("1");
        object1.action();
        final HeavyObject object2 = factory.create("2");
        object2.action();
        final HeavyObject object3 = factory.create("1");
        object3.action();
    }
}

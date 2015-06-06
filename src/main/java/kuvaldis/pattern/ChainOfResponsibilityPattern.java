package kuvaldis.pattern;

import java.util.Arrays;

// filter in servlet containers for instance
public class ChainOfResponsibilityPattern {

    private static class Person {
        private final String name;
        private final Integer age;

        private Person(String name, Integer age) {
            this.name = name;
            this.age = age;
        }
    }

    private static abstract class Handler {
        private final Handler nextHandler;
        private Handler(Handler nextHandler) {
            this.nextHandler = nextHandler;
        }
        private void handle(final Person person) {
            doHandle(person);
            if (nextHandler != null) {
                nextHandler.handle(person);
            }
        }
        abstract void doHandle(Person person);
    }

    private static class AlcoholHandler extends Handler {
        private AlcoholHandler(Handler nextHandler) {
            super(nextHandler);
        }

        @Override
        public void doHandle(final Person person) {
            if (person.age >= 21) {
                System.out.println(String.format("%s can buy alcohol", person.name));
            }
        }
    }

    private static class PensionHandler extends Handler {
        private PensionHandler(Handler nextHandler) {
            super(nextHandler);
        }

        @Override
        void doHandle(Person person) {
            if (person.age > 55) {
                System.out.println(String.format("%s can claim for pension", person.name));
            }
        }
    }

    public static void main(String[] args) {
        final Handler alcoholHandler = new AlcoholHandler(new PensionHandler(null));
        final Person fred = new Person("Fred", 21);
        final Person garry = new Person("Garry", 64);
        Arrays.asList(fred, garry).forEach(alcoholHandler::handle);
    }
}

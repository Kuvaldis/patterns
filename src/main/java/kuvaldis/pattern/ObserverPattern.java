package kuvaldis.pattern;

import java.util.ArrayList;
import java.util.List;

// all the listeners in GUI
public class ObserverPattern {

    private interface Observer {
        void observe(String state);
    }

    private interface Observable {
        void addObserver(final Observer observer);
        void removeObserver(final Observer observer);
        void notifyObservers();
    }

    private static class Display implements Observer {
        @Override
        public void observe(String state) {
            System.out.println("The state is: " + state);
        }
    }

    private static class Cat implements Observable {

        private String state;

        private List<Observer> observers = new ArrayList<>();

        @Override
        public void addObserver(Observer observer) {
            observers.add(observer);
        }

        @Override
        public void removeObserver(Observer observer) {
            observers.remove(observer);
        }

        @Override
        public void notifyObservers() {
            observers.forEach(observer -> observer.observe(state));
        }

        public void setState(String state) {
            this.state = state;
            notifyObservers();
        }
    }

    public static void main(String[] args) {
        final Cat cat = new Cat();
        final Observer display = new Display();
        cat.addObserver(display);
        cat.setState("Mew!");
        cat.setState("Happy");
        cat.setState("Snuggly");
        cat.setState("Conquer the world!");
    }
}

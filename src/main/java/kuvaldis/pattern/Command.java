package kuvaldis.pattern;

// decouples invoker and receiver
public class Command {

    // invoker
    private static class Performer {

        void perform(final Task task) {
            System.out.println("Prepare for execution");
            System.out.println("Executing task");
            task.execute();
        }
    }

    // receiver
    private static class Lighter {
        private void turnOn() {
            System.out.println("Lighter is on");
        }
        private void turnOff() {
            System.out.println("Lighter is off");
        }
    }

    // command
    private interface Task {
        void execute();
    }

    private static class TurnLighterOn implements Task {
        private final Lighter lighter;

        private TurnLighterOn(Lighter lighter) {
            this.lighter = lighter;
        }

        @Override
        public void execute() {
            lighter.turnOn();
        }
    }

    private static class TurnLighterOff implements Task {

        private final Lighter lighter;

        private TurnLighterOff(Lighter lighter) {
            this.lighter = lighter;
        }

        @Override
        public void execute() {
            lighter.turnOff();
        }
    }

    public static void main(String[] args) {
        final Lighter lighter = new Lighter();
        final Performer performer = new Performer();
        performer.perform(new TurnLighterOn(lighter));
        performer.perform(new TurnLighterOff(lighter));
    }
}

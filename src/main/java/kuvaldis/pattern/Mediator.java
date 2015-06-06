package kuvaldis.pattern;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

// let you communicate between colleagues within a group with a mediator.
// every colleague doesn't have to keep links to all the others in the group, it'll do mediator,
// so to perform some action on other colleagues/or concrete colleague the colleague perform an action on mediator.
public class Mediator {

    private static abstract class Colleague {
        private final String name;
        private final MailingSystem mailingSystem;

        private Colleague(String name, MailingSystem mailingSystem) {
            this.name = name;
            this.mailingSystem = mailingSystem;
        }

        private void send(final String message) {
            mailingSystem.send(message, this);
        }

        private void receive(final String message, final Colleague sender) {
            System.out.println(this.name + " thinks about that: " + doReceive(message, sender));
        }

        abstract String doReceive(String message, Colleague sender);
    }

    // mediator
    private interface MailingSystem {
        void send(final String message, final Colleague sender);
    }

    private static class PlainColleague extends Colleague {

        private PlainColleague(String name, MailingSystem mailingSystem) {
            super(name, mailingSystem);
        }

        @Override
        String doReceive(String message, Colleague sender) {
            return "Thanks for the letter, dear " + sender.name;
        }
    }

    private static class AngryColleague extends Colleague {

        private AngryColleague(String name, MailingSystem mailingSystem) {
            super(name, mailingSystem);
        }

        @Override
        String doReceive(String message, Colleague sender) {
            return "Fuck you with your letters, dear " + sender.name + ". I want Anti spam mailing system implementation.";
        }
    }

    private static class DistributeToAllMailingSystem implements MailingSystem {
        private List<Colleague> colleagues = new ArrayList<>();

        private DistributeToAllMailingSystem addColleague(final Colleague colleague) {
            colleagues.add(colleague);
            return this;
        }

        @Override
        public void send(String message, Colleague sender) {
            System.out.println(sender.name + " sent message: " + message);
            colleagues.stream()
                    .filter(Predicate.isEqual(sender).negate())
                    .forEach((receiver) -> receiver.receive(message, sender));
        }
    }

    public static void main(String[] args) {
        final DistributeToAllMailingSystem mailingSystem = new DistributeToAllMailingSystem();
        final Colleague garry = new PlainColleague("Garry", mailingSystem);
        final Colleague sisi = new PlainColleague("Sisi", mailingSystem);
        final Colleague bob = new AngryColleague("Bob", mailingSystem);
        mailingSystem.addColleague(garry).addColleague(sisi).addColleague(bob);
        garry.send("Hi everybody!");
        sisi.send("Welcome! Don't mention Bob. He is angy.");
        bob.send("Fuck you!");
    }
}

package kuvaldis.pattern;

// lets you create different object from the same method
public class FactoryMethodPattern {

    private static abstract class MessageHandler {
        final String message;

        protected MessageHandler(String message) {
            this.message = message;
        }

        abstract void handle();
    }

    private static class ShortMessageHandler extends MessageHandler {

        private ShortMessageHandler(String message) {
            super(message);
        }

        @Override
        void handle() {
            System.out.println("Handle short message: " + message);
        }
    }

    private static class LongMessageHandler extends MessageHandler {

        private LongMessageHandler(String message) {
            super(message);
        }

        @Override
        void handle() {
            System.out.println("Handle long message: " + message);
        }
    }

    private static class MessageHandlerFactory {
        private MessageHandler create(final String message) {
            if (message.length() < 10) {
                return new ShortMessageHandler(message);
            }
            return new LongMessageHandler(message);
        }
    }

    public static void main(String[] args) {
        final MessageHandlerFactory factory = new MessageHandlerFactory();
        factory.create("Hi!").handle();
        factory.create("Hello my dear friend!").handle();

    }
}

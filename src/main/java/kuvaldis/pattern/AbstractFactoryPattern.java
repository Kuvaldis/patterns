package kuvaldis.pattern;

// lets you create object without knowledge what concrete object it is
public class AbstractFactoryPattern {

    private interface Printer {
        void print();
    }

    private interface PrinterFactory {
        Printer create();
    }

    private static class HelloPrinter implements Printer {

        @Override
        public void print() {
            System.out.println("Hello!");
        }
    }

    private static class FuckYouPrinter implements Printer {

        @Override
        public void print() {
            System.out.println("Fuck you!");
        }
    }

    private static class HelloPrinterFactory implements PrinterFactory {

        @Override
        public Printer create() {
            return new HelloPrinter();
        }
    }

    private static class FuckYouPrinterFactory implements PrinterFactory {

        @Override
        public Printer create() {
            return new FuckYouPrinter();
        }
    }

    public static void main(String[] args) {
        createAndPrint(new HelloPrinterFactory());
        createAndPrint(new FuckYouPrinterFactory());
    }

    private static void createAndPrint(final PrinterFactory factory) {
        factory.create().print();
    }
}

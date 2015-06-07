package kuvaldis.pattern;

// let you change behavior of existing class by adding some new with wrapping it
public class DecoratorPattern {

    private interface Printer {
        void print();
    }

    private static class SimplePrinter implements Printer {

        @Override
        public void print() {
            System.out.println("Hi there!");
        }
    }

    private static class PrinterDecorator implements Printer {

        private final Printer printer;

        private PrinterDecorator(Printer printer) {
            this.printer = printer;
        }

        @Override
        public void print() {
            System.out.println("Decorated printing start");
            printer.print();
            System.out.println("Decorated printing end");
        }
    }

    public static void main(String[] args) {
        final Printer printer = new SimplePrinter();
        print(printer);
        final Printer decorator = new PrinterDecorator(printer);
        print(decorator);
    }

    private static void print(final Printer printer) {
        System.out.println("Print from printer");
        printer.print();
    }
}

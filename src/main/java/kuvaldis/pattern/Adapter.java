package kuvaldis.pattern;

import java.util.Arrays;
import java.util.List;

// lets you adapt existing unmodifiable class to some interface
public class Adapter {

    private interface Printer {
        void print();
    }

    private static class StandardPrinter implements Printer {

        @Override
        public void print() {
            System.out.println("Hi there!");
        }
    }

    private static final class LegacyPrinter {
        void output() {
            System.out.println("Output: lalala");
        }
    }

    private static class LegacyPrinterAdapter implements Printer {

        private final LegacyPrinter legacyPrinter;

        private LegacyPrinterAdapter(LegacyPrinter legacyPrinter) {
            this.legacyPrinter = legacyPrinter;
        }

        @Override
        public void print() {
            legacyPrinter.output();
        }
    }

    public static void main(String[] args) {
        final LegacyPrinter legacyPrinter = new LegacyPrinter();
        final List<Printer> printers = Arrays.asList(new StandardPrinter(), new LegacyPrinterAdapter(legacyPrinter));
        printers.forEach(Printer::print);
    }
}

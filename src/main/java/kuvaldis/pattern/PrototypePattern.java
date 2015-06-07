package kuvaldis.pattern;

// lets you create objects from pattern
public class PrototypePattern {

    private static class Cookie {
        private final int size;
        private final String phrase;

        private Cookie(int size, String phrase) {
            this.size = size;
            this.phrase = phrase;
        }

        private Cookie(Cookie template, String phrase) {
            this.size = template.size;
            this.phrase = phrase;
        }

        @Override
        public String toString() {
            return "Cookie{" +
                    "size=" + size +
                    ", phrase='" + phrase + '\'' +
                    '}';
        }
    }

    public static void main(String[] args) {
        final Cookie protoCookie = new Cookie(10, null);
        final Cookie gratsCookie = new Cookie(protoCookie, "Congratulations!");
        final Cookie fuckYouCookie = new Cookie(protoCookie, "Fuck you!");
        System.out.println(gratsCookie);
        System.out.println(fuckYouCookie);
    }
}

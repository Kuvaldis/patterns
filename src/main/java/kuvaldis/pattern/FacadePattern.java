package kuvaldis.pattern;

// lets you create high level service class calling others
public class FacadePattern {

    private static class LowLevelService {
        private void action() {
            System.out.println("Low level action");
        }
    }

    private static class AnotherLowLevelService {
        private void action() {
            System.out.println("Another low level action");
        }
    }

    private static class HighLevelFacade {
        private final LowLevelService lowLevelService;
        private final AnotherLowLevelService anotherLowLevelService;

        private HighLevelFacade(LowLevelService lowLevelService, AnotherLowLevelService anotherLowLevelService) {
            this.lowLevelService = lowLevelService;
            this.anotherLowLevelService = anotherLowLevelService;
        }

        private void firstLowLevelAction() {
            lowLevelService.action();
        }

        private void secondLowLevelAction() {
            anotherLowLevelService.action();
        }
    }

    public static void main(String[] args) {
        final LowLevelService lowLevelService = new LowLevelService();
        final AnotherLowLevelService anotherLowLevelService = new AnotherLowLevelService();
        final HighLevelFacade facade = new HighLevelFacade(lowLevelService, anotherLowLevelService);
        facade.firstLowLevelAction();
        facade.secondLowLevelAction();
    }
}

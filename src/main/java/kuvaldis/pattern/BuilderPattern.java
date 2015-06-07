package kuvaldis.pattern;

import java.util.ArrayList;
import java.util.List;

// lets create a new object with chain of method calls.
// The object will be finally created after final build action but not in the middle.
// StringBuilder for example
public class BuilderPattern {

    private static class TodoList {
        private final String title;
        private final List<String> notes;

        private TodoList(String title, List<String> notes) {
            this.title = title;
            this.notes = notes;
        }

        @Override
        public String toString() {
            return "TodoList{" +
                    "title='" + title + '\'' +
                    ", notes=" + notes +
                    '}';
        }
    }

    private static class TodoListsBuilder {

        private String title;
        private List<String> notes;

        private TodoListsBuilder setTitle(final String title) {
            this.title = title;
            return this;
        }

        private TodoListsBuilder addNote(final String note) {
            if (notes == null ) {
                notes = new ArrayList<>();
            }
            notes.add(note);
            return this;
        }

        private TodoList build() {
            final TodoList result = new TodoList(title, notes);
            title = null;
            notes = null;
            return result;
        }
    }

    public static void main(String[] args) {
        final TodoListsBuilder builder = new TodoListsBuilder();
        final TodoList todoList = builder.setTitle("Things to do today")
                .addNote("Nothing")
                .addNote("More of nothing")
                .addNote("Bring a low")
                .addNote("Sleep")
                .build();
        System.out.println(todoList);
    }
}

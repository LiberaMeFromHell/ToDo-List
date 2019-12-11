package ru.job4j.todolist.model.database;

public class TaskDBSchema {
    public static final class TaskTable {
        public static final String TITLE = "tasks";

        public static final class Cols {
            public static final String ID = "id";
            public static final String NAME = "title";
            public static final String DESC = "description";
            public static final String CREATED = "created";
        }
    }
}

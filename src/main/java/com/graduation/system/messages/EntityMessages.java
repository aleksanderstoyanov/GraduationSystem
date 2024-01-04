package com.graduation.system.messages;

public class EntityMessages {
    public static class CommonMessage {
        public static final String NameLength = "Name should be less than 4 and more than 15 characters!";
        public static final String NameNotNull = "Name should not be null!";
        public static final String StartDateNotNull = "Start Date should not be null";
        public static final String TitleLength = "Title should be less than 4 and more than 15 characters!";
        public static final String TitleNotNull = "Title should not be null!";
        public static final String TextNotNull = "Text cannot be null!";
        public static final String TextLength = "Text should not be less than 10 and more than 550 characters!";
        public static final String SubmittedDateNotNull = "Submitted Date cannot be null!";
    }

    public static class ApplicationMessage {
        public static final String SubjectLength = "Subject should be less than 5 and more than 30 characters!";
        public static final String SubjectNotNull = "Subject should not be null!";
        public static final String TaskLength = "Task should be less than 5 and more than 50 characters!";
        public static final String TaskNotNull = "Task should not be null!";
        public static final String PurposeLength = "Purpose should be less than 5 and more than 50 characters!";
        public static final String PurposeNotNull = "Purpose should not be null!";
    }

    public static class StudentMessage {
        public static final String FnNotNull = "Faculty Number cannot be null!";
        public static final String FnLength = "Student Name should not be less than 4 and more than 15 characters!";
    }

    public static class TeacherMessage {
        public static final String PositionNotNull = "Position of Teacher cannot be null!";
    }

    public static class ReviewMessage {
        public static final String SummaryNotNull = "Text cannot be null!";
        public static final String SummaryLength = "Summary should not be less than 10 and more than 550 characters!";
    }

    public static class DefenseMessage {
        public static final String GradeValue = "Grade should not be less than 2 and no more than 6";
    }
}

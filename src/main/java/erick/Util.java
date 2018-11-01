package erick;

public class Util {
    private Util() {
        throw new UnsupportedOperationException("This is a erick.Util class. Can't be instantiated.");
    }

    public static int jobSatisfactionToNumeric(String jobSatisfaction) {
        switch (jobSatisfaction) {
            case "Extremely dissatisfied":
                return 0;
            case "Moderately dissatisfied":
                return 1;
            case "Slightly dissatisfied":
                return 2;
            case "Neither satisfied nor dissatisfied":
                return 3;
            case "Slightly satisfied":
                return 4;
            case "Moderately satisfied":
                return 5;
            default:
                return 6;
        }
    }
}

public class WithArrayAndReturnType {
    public static String DayChoose(int day) {
        String ans = switch (day) {
            case 1 -> "sunday";
            case 2 -> "Monday";
            case 3 -> "Tuesday";
            default -> "Enter valid day number ";
        };
        return ans;
    }


    public static void main(String[] args) {

        int n = 22;
        String ans = DayChoose(n);
        System.out.println("Enter day number->  " + ans);
        StringBuilder st = new StringBuilder();
        StringBuffer stb = new StringBuffer();


    }
}
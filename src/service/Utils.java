package service;

public class Utils {
    public static void clearConsole() {
        try {
            // Phát hiện hệ điều hành
            if (System.getProperty("os.name").contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("clear").inheritIO().start().waitFor();
            }
        } catch (Exception e) {
            System.out.println("Không thể xóa màn hình.");
        }
    }
}

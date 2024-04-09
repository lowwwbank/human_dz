import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите имя:");
        String name = scanner.nextLine();
        try {
            isCyrillicWord(name);
        }catch (Exception e){
            System.out.println("Ошибка! " + e.getMessage());
        }
        System.out.println("Введите фамилию:");
        String surname = scanner.nextLine();
        try {
            isCyrillicWord(surname);
        }catch (Exception e){
            System.out.println("Ошибка! " + e.getMessage());
        }
        System.out.println("Введите отчество:");
        String patronymic = scanner.nextLine();
        try {
            isCyrillicWord(patronymic);
        }catch (Exception e){
            System.out.println("Ошибка! " + e.getMessage());
        }
        System.out.println("Введите дату рождения в формате дд.мм.гггг:");
        String date = scanner.nextLine();
        int age = 0;
        try {
            age = isCorrectDate(date);
        }catch (Exception e){
            System.out.println("Ошибка! Введите дату в валидном формате!");
        }
        System.out.println("Результат: \n");
        System.out.printf("ФИО: %s %c.%c.\n", surname, name.charAt(0), patronymic.charAt(0));
        System.out.printf("Пол: %s\n", getSex(patronymic));
        System.out.printf("Возраст: %d\n", age);
    }

    private static int isCorrectDate(String date) throws DateTimeParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        LocalDate dateTime = LocalDate.parse(date, formatter);
        if (LocalDate.now().compareTo(dateTime) > LocalDate.now().compareTo(dateTime.minusYears(1))){
            return 0;
        }else return LocalDate.now().compareTo(dateTime);
    }

    static String getSex(String p){
        if (p.charAt((p.length()-1)) == 'а'){
            return "Женщина";
        }
        else return "Мужчина";
    }

    private static void isCyrillicWord(String s) throws IllegalArgumentException{
        if (s == null){
            throw new IllegalArgumentException("Поле не должно быть пустым!");
        }
        else {
            for (int i = 0; i < s.length(); i++) {
                if (!Character.UnicodeBlock.of(s.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)){
                    throw new IllegalArgumentException("Вводите ФИО на русском языке, без пробелов!");
                }
            }
        }
    }
}
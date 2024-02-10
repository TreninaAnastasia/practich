public class isclucheniya {

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class UserInputApplication {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные в формате 'Фамилия Имя Отчество дата_рождения номер_телефона пол':");
        String input = scanner.nextLine();

        try {
            processInput(input);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private static void processInput(String input) throws Exception {
        String[] parts = input.split(" ");
        if (parts.length != 6) {
            throw new IllegalArgumentException("Неверное количество аргументов. Ожидалось 6.");
        }

        String lastName = parts[0];
        String firstName = parts[1];
        String middleName = parts[2];
        String birthDate = parts[3];
        String phoneNumber = parts[4];
        String gender = parts[5];

        validateData(firstName, middleName, lastName, birthDate, phoneNumber, gender);
        writeToFile(lastName, String.join(" ", parts));
    }

    private static void validateData(String firstName, String middleName, String lastName, String birthDate,
            String phoneNumber, String gender) throws Exception {
        if (!birthDate.matches("\\d{2}\\.\\d{2}\\.\\d{4}")) {
            throw new IllegalArgumentException("Неверный формат даты рождения.");
        }

        if (!phoneNumber.matches("\\d+")) {
            throw new IllegalArgumentException("Номер телефона должен содержать только цифры.");
        }

        if (!gender.matches("[fm]")) {
            throw new IllegalArgumentException("Пол должен быть указан как 'f' или 'm'.");
        }
    }

    private static void writeToFile(String lastName, String data) {
        String fileName = lastName + ".txt";
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
            writer.write(data);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}}

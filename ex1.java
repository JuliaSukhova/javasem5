import java.util.*;

public class TelephoneBook {
    public static void main(String[] args) {
        HashMap<String, List<String>> phoneBook = new HashMap<>();

        // Добавляем контакты в телефонную книгу
        addContact(phoneBook, "John Doe", "123456789");
        addContact(phoneBook, "Jane Smith", "987654321");
        addContact(phoneBook, "John Doe", "555555555");
        addContact(phoneBook, "Jane Smith", "111111111");

        // Выводим телефонную книгу, отсортированную по убыванию числа телефонов
        printSortedPhoneBook(phoneBook);
    }

    public static void addContact(HashMap<String, List<String>> phoneBook, String name, String phoneNumber) {
        List<String> phoneNumbers = phoneBook.getOrDefault(name, new ArrayList<>());
        phoneNumbers.add(phoneNumber);
        phoneBook.put(name, phoneNumbers);
    }

    public static void printSortedPhoneBook(HashMap<String, List<String>> phoneBook) {
        List<Map.Entry<String, List<String>>> sortedEntries = new ArrayList<>(phoneBook.entrySet());

        // Сортировка по убыванию числа телефонов
        sortedEntries.sort((entry1, entry2) -> Integer.compare(entry2.getValue().size(), entry1.getValue().size()));

        // Вывод отсортированных контактов
        for (Map.Entry<String, List<String>> entry : sortedEntries) {
            String name = entry.getKey();
            List<String> phoneNumbers = entry.getValue();
            System.out.println(name + ": " + phoneNumbers);
        }
    }
}

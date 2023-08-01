import java.util.*;

public class NotebookStore {
    private Set<Notebook> notebooks;

    public NotebookStore() {
        notebooks = new HashSet<>();
    }

    public void addNotebook(Notebook notebook) {
        notebooks.add(notebook);
    }

    public void filterNotebooks(Map<Integer, Object> filters) {
        List<Notebook> filteredNotebooks = new ArrayList<>(notebooks);

        for (Integer filterKey : filters.keySet()) {
            Object filterValue = filters.get(filterKey);

            switch (filterKey) {
                case 1: // ОЗУ
                    filteredNotebooks.removeIf(notebook -> notebook.getRamSizeGB() < (int) filterValue);
                    break;
                case 2: // Объем ЖД
                    filteredNotebooks.removeIf(notebook -> notebook.getStorageSizeGB() < (int) filterValue);
                    break;
                case 3: // Операционная система
                    filteredNotebooks.removeIf(notebook -> !notebook.getOs().equals(filterValue));
                    break;
                case 4: // Цвет
                    filteredNotebooks.removeIf(notebook -> !notebook.getColor().equals(filterValue));
                    break;
                // Добавьте другие критерии фильтрации по необходимости
                default:
                    System.out.println("Некорректный критерий фильтрации: " + filterKey);
            }
        }

        printFilteredNotebooks(filteredNotebooks);
    }

    private void printFilteredNotebooks(List<Notebook> notebooks) {
        if (notebooks.isEmpty()) {
            System.out.println("Нет ноутбуков, соответствующих условиям фильтрации.");
        } else {
            System.out.println("Список ноутбуков, соответствующих условиям фильтрации:");
            for (Notebook notebook : notebooks) {
                System.out.println(notebook.getBrand() + ", ОЗУ: " + notebook.getRamSizeGB() + " ГБ, " +
                        "ЖД: " + notebook.getStorageSizeGB() + " ГБ, ОС: " + notebook.getOs() + ", Цвет: " + notebook.getColor());
            }
        }
    }

    public static void main(String[] args) {
        NotebookStore notebookStore = new NotebookStore();

        // Добавляем некоторые ноутбуки в магазин
        notebookStore.addNotebook(new Notebook("Brand1", 8, 256, "Windows", "Black"));
        notebookStore.addNotebook(new Notebook("Brand2", 16, 512, "macOS", "Silver"));
        notebookStore.addNotebook(new Notebook("Brand3", 8, 512, "Linux", "White"));
        // Добавьте ещё ноутбуков по необходимости

        // Запрос критериев фильтрации у пользователя (пример)
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Object> filters = new HashMap<>();

        System.out.println("Введите цифру, соответствующую критерию фильтрации:");
        System.out.println("1 - ОЗУ\n2 - Объем ЖД\n3 - Операционная система\n4 - Цвет");

        int filterKey = scanner.nextInt();
        Object filterValue;

        switch (filterKey) {
            case 1:
                System.out.print("Введите минимальный объем ОЗУ (ГБ): ");
                filterValue = scanner.nextInt();
                filters.put(filterKey, filterValue);
                break;
            case 2:
                System.out.print("Введите минимальный объем ЖД (ГБ): ");
                filterValue = scanner.nextInt();
                filters.put(filterKey, filterValue);
                break;
            case 3:
                System.out.print("Введите операционную систему: ");
                scanner.nextLine(); // consume the newline character
                filterValue = scanner.nextLine();
                filters.put(filterKey, filterValue);
                break;
            case 4:
                System.out.print("Введите цвет: ");
                scanner.nextLine(); // consume the newline character
                filterValue = scanner.nextLine();
                filters.put(filterKey, filterValue);
                break;
            default:
                System.out.println("Некорректный критерий фильтрации.");
                scanner.close();
                return;
        }

        notebookStore.filterNotebooks(filters);

        scanner.close();
    }
}

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class AddressBook {
    private HashMap<String, String> contacts;
    private String fileName;

    public AddressBook(String fileName) {
        this.contacts = new HashMap<>();
        this.fileName = fileName;
        load();
    }

    public void load() {
        try (BufferedReader reader = new BufferedReader(new FileReader(fileName))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",", 2);
                if (parts.length >= 2) {
                    String number = parts[0];
                    String name = parts[1];
                    contacts.put(number, name);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading contacts: " + e.getMessage());
        }
    }

    public void save() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(fileName))) {
            for (Map.Entry<String, String> entry : contacts.entrySet()) {
                writer.println(entry.getKey() + "," + entry.getValue());
            }
        } catch (IOException e) {
            System.out.println("Error saving contacts: " + e.getMessage());
        }
    }

    public void list() {
        System.out.println("-------------------------------------------------------");
        System.out.println("Contactos:");
        for (Map.Entry<String, String> entry : contacts.entrySet()) {
            System.out.println(entry.getKey() + " : " + entry.getValue());
        }
    }

    public void create(String number, String name) {
        contacts.put(number, name);
        save();
    }

    public void delete(String number) {
        if (contacts.remove(number) != null) {
            System.out.println("Contacto eliminado.");
            save();
        } else {
            System.out.println("Contacto no encontrado.");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AddressBook addressBook = new AddressBook("contacts.txt");

        while (true) {
            System.out.println("-------------------------------------------------------");
            System.out.println("Seleccione una opción:");
            System.out.println("1. Listar contactos");
            System.out.println("2. Crear contacto");
            System.out.println("3. Borrar contacto");
            System.out.println("4. Salir");

            String choice = scanner.nextLine();

            switch (choice) {
                case "1":
                    addressBook.list();
                    break;
                case "2":
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Ingrese el número del contacto:");
                    String number = scanner.nextLine();
                    System.out.println("Ingrese el nombre del contacto:");
                    String name = scanner.nextLine();
                    addressBook.create(number, name);
                    break;
                case "3":
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Ingrese el número del contacto a eliminar:");
                    String deleteNumber = scanner.nextLine();
                    addressBook.delete(deleteNumber);
                    break;
                case "4":
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Saliendo...");
                    scanner.close();
                    return;
                default:
                    System.out.println("-------------------------------------------------------");
                    System.out.println("Opción no válida.");
                    break;
            }
        }
    }
}
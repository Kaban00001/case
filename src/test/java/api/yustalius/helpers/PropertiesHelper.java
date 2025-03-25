package api.yustalius.helpers;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

public class PropertiesHelper {
    private final String filePath;

    // Конструктор принимает путь к .properties файлу
    public PropertiesHelper(String filePath) {
        this.filePath = filePath;
    }

    // Загрузка свойств из файла
    private Properties loadProperties() {
        Properties properties = new Properties();
        try (FileInputStream input = new FileInputStream(filePath)) {
            properties.load(input);
        } catch (IOException e) {
            System.out.println("Файл не найден или ошибка чтения: " + e.getMessage());
        }
        return properties;
    }

    // Получение значения по ключу
    public String getProperty(String key) {
        Properties properties = loadProperties(); // Загружаем свойства
        return properties.getProperty(key);
    }

    // Установка нового значения для ключа
    public void setProperty(String key, String value) {
        Properties properties = loadProperties(); // Загружаем свойства
        properties.setProperty(key, value);
        saveProperties(properties); // Сохраняем изменения
    }

    // Удаление свойства по ключу
    public void removeProperty(String key) {
        Properties properties = loadProperties(); // Загружаем свойства
        properties.remove(key);
        saveProperties(properties); // Сохраняем изменения
    }

    // Проверка наличия ключа
    public boolean containsKey(String key) {
        Properties properties = loadProperties(); // Загружаем свойства
        return properties.containsKey(key);
    }

    // Сохранение всех свойств обратно в файл
    private void saveProperties(Properties properties) {
        try (FileOutputStream output = new FileOutputStream(filePath)) {
            properties.store(output, "Updated by PropertiesHelper");
        } catch (IOException e) {
            System.out.println("Ошибка записи в файл: " + e.getMessage());
        }
    }
}


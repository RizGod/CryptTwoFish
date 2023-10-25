import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) {

        FileEncryption fileEncryption = new FileEncryption();
        FileDecryption fileDecryption = new FileDecryption();

        // Создаем окно
        JFrame frame = new JFrame("ИБ20-3 Шифрование/Дешифрование");

        // Создаем текстовые метки
        JLabel label1 = new JLabel("Введите ключ:");
        JLabel label2 = new JLabel("Введите путь к файлу (шифрование):");
        JLabel label3 = new JLabel("Введите путь к файлу (дешифрование)");

        // Создаем текстовые поля
        JTextField textField1 = new JTextField(20);
        JTextField textField2 = new JTextField(20);
        JTextField textField3 = new JTextField(20);

        // Создаем панели для группировки меток и полей
        JPanel panel1 = new JPanel();
        panel1.add(label1);
        panel1.add(textField1);

        JPanel panel2 = new JPanel();
        panel2.add(label2);
        panel2.add(textField2);

        JPanel panel3 = new JPanel();
        panel3.add(label3);
        panel3.add(textField3);

        // Создаем основную панель для размещения всех элементов
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));
        mainPanel.add(panel1);
        mainPanel.add(panel2);
        mainPanel.add(panel3);

        // Определяем размер экрана
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Вычисляем координаты для размещения окна по центру
        int x = (screenSize.width - frame.getWidth()) / 2 - 150;
        int y = (screenSize.height - frame.getHeight()) / 2 - 175;

        // Создаем кнопку
        JButton button = new JButton("Выполнить");

        // Добавляем основную панель на окно
        frame.add(mainPanel, BorderLayout.CENTER);
        frame.add(button, BorderLayout.SOUTH);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Здесь вы можете обработать нажатие на кнопку
                // Например, получить значения из текстовых полей
                String key = textField1.getText();
                String enPath = textField2.getText();
                String dePath = textField3.getText();

                boolean isError = true;
                String errorMessage = "Неверный путь или пустая строка";

                try {
                    if (!(enPath.isBlank() || enPath.isEmpty()) && !(key.isBlank() || key.isEmpty())) {
                        fileEncryption.encrypt(key, enPath);
                        isError = false;
                    }

                    if (!(dePath.isBlank() || dePath.isEmpty()) && !(key.isBlank() || key.isEmpty())) {
                        fileDecryption.decrypt(key, dePath);
                        isError = false;
                    }

                } catch (NullPointerException exception) {
                    isError = true;
                    System.out.println(exception);
                    errorMessage = "Неверный путь к файлу или пустая строка";
                } catch (Exception exception) {
                    isError = true;
                    System.out.println(exception);
                    errorMessage = exception.getMessage();
                }

                if (!isError)
                    JOptionPane.showMessageDialog(frame, "Выполнено", "Успешно", JOptionPane.INFORMATION_MESSAGE);
                else
                    JOptionPane.showMessageDialog(frame, "Не выполнено\nПричина: " + errorMessage, "Ошибка", JOptionPane.ERROR_MESSAGE);
            }
        });

        // Устанавливаем размер окна и видимость
        frame.setSize(300, 350);
        frame.setLocation(x, y);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
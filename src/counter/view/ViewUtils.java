package counter.view;

import counter.exceptions.NegativeNumberException;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.awt.Font;
import java.util.Objects;

public final class ViewUtils {
    private static final JLabel label = new JLabel();

    static {
        label.setFont(new Font(null, Font.PLAIN, 20));
    }

    private ViewUtils() {
        throw new AssertionError("Не должно быть экземпляров класса ViewUtils!");
    }

    public static void showErrorDialog(String text) {
        showDialog(text, "Ошибка", JOptionPane.ERROR_MESSAGE);
    }

    public static void showResultDialog(String text) {
        showDialog(text, "Результат", JOptionPane.INFORMATION_MESSAGE);
    }

    private static void showDialog(String text, String title, int messageType) {
        label.setText(text);
        JOptionPane.showMessageDialog(null, label, title, messageType);
        label.setText("");
    }

    public static boolean showYesNoDialogWithWarning(String text) {
        label.setText(text);
        int answer = JOptionPane.showConfirmDialog(null, label, "", JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        label.setText("");
        return answer == 0;
    }

    public static int parseInt(JTextField textField) throws NumberFormatException {
        Objects.requireNonNull(textField, "Параметр textField не должен быть null!");
        String text = textField.getText().replaceAll(" ", "");
        return text.isEmpty()? 0 : Integer.parseInt(text);
    }

    public static int parsePositiveInt(JTextField textField) {
        int result = parseInt(textField);
        if(result <  0) {
            throw new NegativeNumberException();
        }
        return result;
    }

    public static String toResultList(String prefix, int[] elements) {
        StringBuilder list = new StringBuilder();
        list.append("<html>");
        for(int i = 0; i < elements.length; i++) {
            list.append(prefix).append(i + 1).append(": ").append(elements[i]).append(";<br>");
        }
        list.append("</html>");
        return list.toString();
    }
}

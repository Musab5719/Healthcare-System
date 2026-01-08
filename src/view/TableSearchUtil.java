package view;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.TableRowSorter;
import java.util.regex.Pattern;

public class TableSearchUtil {

    public static void attach(JTable table, JTextField searchField) {
        TableRowSorter sorter = new TableRowSorter(table.getModel());
        table.setRowSorter(sorter);

        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override public void insertUpdate(DocumentEvent e) { apply(); }
            @Override public void removeUpdate(DocumentEvent e) { apply(); }
            @Override public void changedUpdate(DocumentEvent e) { apply(); }

            private void apply() {
                String text = searchField.getText();
                if (text == null || text.trim().isEmpty()) {
                    sorter.setRowFilter(null);
                    return;
                }
                sorter.setRowFilter(RowFilter.regexFilter("(?i)" + Pattern.quote(text.trim())));
            }
        });
    }
}

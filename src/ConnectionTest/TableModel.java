package ConnectionTest;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class TableModel extends AbstractTableModel {

    private final ArrayList<String[]> dataArrayList;

    public TableModel() {
        dataArrayList = new ArrayList<>();
    }

    @Override
    public int getRowCount() {
        return dataArrayList.size();
    }

    @Override
    public int getColumnCount() {
        return 5;
    }

    @Override
    public String getColumnName(int columnIndex) {
        return switch (columnIndex) {
            case 0 -> "id";
            case 1 -> "ФИО";
            case 2 -> "E-mail";
            case 3 -> "Телефон";
            case 4 -> "Номер группы";
            default -> "";
        };
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        String[] rows = dataArrayList.get(rowIndex);
        return rows[columnIndex];
    }

    public void addData(String[] row) {
        dataArrayList.add(row);
    }

    public void updateTable(ConnectionDB connect) {
        String sql = "SELECT * from users";
        ResultSet result = connect.resultSetQuery(sql);
        dataArrayList.clear();
        try {
            while (result.next()) {
                String[] row = {
                        result.getString("id"),
                        result.getString("username"),
                        result.getString("email"),
                        result.getString("telephone"),
                        result.getString("group_number")
                };
                addData(row);
            }
            result.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addNewField(ConnectionDB connect, String username, String email, String telephone, String group, String password) {
        String sql = "INSERT INTO users(username, email, telephone, group_number, password) VALUES (?,?,?,?,?)";
        try {
            connect.prepareSetQuery(sql, username, email, telephone, group, password);
            updateTable(connect);
            JOptionPane.showMessageDialog(null, "Пользователь добавлен! Чтобы увидеть результат, обновите страницу");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteField(ConnectionDB connect, String cell) {
        String sql = "DELETE FROM users WHERE id = " + cell;
        try {
            connect.prepareQuery(sql);
            updateTable(connect);
            JOptionPane.showMessageDialog(null, "Пользователь c id = "+ cell + " удален! Чтобы увидеть результат, обновите страницу");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

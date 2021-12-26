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

    public void addRole(ConnectionDB connect, int id, int role) {
        String sql = "INSERT INTO user_roles(user_id, role_id) VALUES(?,?)";
        try{
            connect.addUserRole(sql, id, role);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void addNewField(ConnectionDB connect, int id, String username, String email, String telephone, String group, String password) {
        String sql = "INSERT INTO users(id, username, email, telephone, group_number, password) VALUES (?,?,?,?,?,?)";
        try {
            connect.prepareSetQuery(sql, id, username, email, telephone, group, password);
            JOptionPane.showMessageDialog(null, "Пользователь добавлен!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteField(ConnectionDB connect, String id) {
        String sql1 = "DELETE FROM users WHERE id = " + id;
        String sql2 = "DELETE FROM cards WHERE user_id = " + id;
        String sql3 = "DELETE FROM user_roles WHERE user_id = " + id;
        String sql4 = "DELETE FROM payments WHERE card_id = " + id;
        String sql5 = "DELETE FROM transfers WHERE card_id = " + id;
        try {
            connect.prepareQuery(sql5);
            connect.prepareQuery(sql4);
            connect.prepareQuery(sql3);
            connect.prepareQuery(sql2);
            connect.prepareQuery(sql1);

            JOptionPane.showMessageDialog(null, "Пользователь c id = "+ id + " удален!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}

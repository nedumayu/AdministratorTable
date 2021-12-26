package ConnectionTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFrame extends JPanel {

    private final ConnectionDB connect;

    private final TableModel tb = new TableModel();
    private final JTable table = new JTable(tb);

    private final JLabel idLabel = new JLabel("Id");
    private final JLabel usernameLabel = new JLabel("ФИО");
    private final JLabel emailLabel = new JLabel("E-mail");
    private final JLabel groupLabel = new JLabel("Номер группы");
    private final JLabel telephoneLabel = new JLabel("Телефон");
    private final JLabel roleLabel = new JLabel("Роль");

    private final JTextField idTextField = new JTextField();
    private final JTextField usernameTextField = new JTextField();
    private final JTextField emailTextField = new JTextField();
    private final JTextField groupTextField = new JTextField();
    private final JTextField telephoneTextField = new JTextField();

    private final JRadioButton userRadioButton = new JRadioButton("Студент");
    private final JRadioButton adminRadioButton = new JRadioButton("Администратор");
    private final JRadioButton accountantRadioButton = new JRadioButton("Сотрудник бухгалтерии");

    private final JButton addButton = new JButton("Add");
    private final JButton deleteButton = new JButton("Delete");

    public PanelFrame(ConnectionDB connect) {
        this.connect = connect;
        setLayout(new GridBagLayout());
    }

    public void init() {
        JScrollPane tableScroll = new JScrollPane(table);
        tableScroll.setPreferredSize(new Dimension(700, 200));

        add(tableScroll, new GridBagConstraints(0, 0, 4, 1, 1, 1,
                GridBagConstraints.NORTH, GridBagConstraints.BOTH,
                new Insets(1, 1, 1, 1), 0, 0));


        //поле ввода Id
        add(idLabel, new GridBagConstraints(0, 1, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 1, 1, 1), 0, 0));
        add(idTextField, new GridBagConstraints(1, 1, 2, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(20, 1, 1, 1), 0, 0));

        //поле ввода ФИО
        add(usernameLabel, new GridBagConstraints(0, 2, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        add(usernameTextField, new GridBagConstraints(1, 2, 2, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        //поле ввода Email
        add(emailLabel, new GridBagConstraints(0, 3, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        add(emailTextField, new GridBagConstraints(1, 3, 2, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        //поле ввода группы
        add(groupLabel, new GridBagConstraints(0, 4, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        add(groupTextField, new GridBagConstraints(1, 4, 2, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        //поле ввода телефона
        add(telephoneLabel, new GridBagConstraints(0, 5, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        add(telephoneTextField, new GridBagConstraints(1, 5, 2, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));

        //Радиокнопки
        add(roleLabel, new GridBagConstraints(0, 6, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 30, 1), 0, 0));
        add(userRadioButton, new GridBagConstraints(1, 6, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 30, 1), 0, 0));
        add(adminRadioButton, new GridBagConstraints(2, 6, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 30, 1), 0, 0));
        add(accountantRadioButton, new GridBagConstraints(3, 6, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 30, 1), 0, 0));

        //кнопки добавления и удаления
        add(addButton, new GridBagConstraints(0, 7, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));
        add(deleteButton, new GridBagConstraints(1, 7, 1, 1, 1, 1,
                GridBagConstraints.SOUTH, GridBagConstraints.HORIZONTAL,
                new Insets(1, 1, 1, 1), 0, 0));


        addButton.addActionListener(new AddButtonActionListener());
        deleteButton.addActionListener(new DeleteButtonActionListener());

        tb.updateTable(connect);
    }

    public class AddButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String num = idTextField.getText();
            int id = Integer.parseInt(num.trim());
            String username = usernameTextField.getText();
            String email = emailTextField.getText();
            String telephone = telephoneTextField.getText();
            String group = groupTextField.getText();

            int role = 1;
            if (userRadioButton.isSelected()) { role = 1;}
            else if (adminRadioButton.isSelected()) { role = 3;}
            else if (accountantRadioButton.isSelected()) { role = 2;}

            String password = "$2a$10$0l2YuN.z9q5kdATAaCip3urWGcrFbf8JG7p9KUmFra/3aM5rq/38S";  //123123

            tb.addNewField(connect, id, username, email, telephone, group, password);
            tb.addRole(connect, id, role);

            idTextField.setText(null);
            usernameTextField.setText(null);
            emailTextField.setText(null);
            telephoneTextField.setText(null);
            groupTextField.setText(null);
            userRadioButton.setSelected(false);
            adminRadioButton.setSelected(false);
            accountantRadioButton.setSelected(false);

            tb.updateTable(connect);
            repaint();
        }
    }

    public class DeleteButtonActionListener implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            int row = table.getSelectedRow();
            String id = table.getModel().getValueAt(row, 0).toString();

            tb.deleteField(connect, id);
            tb.updateTable(connect);
            repaint();
        }
    }

}

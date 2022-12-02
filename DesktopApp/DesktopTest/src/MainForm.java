import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.util.Objects;

public class MainForm {
    private JPanel mainPanel;

    private JTextArea name;
    private JTextArea surname;
    private JTextArea lastname;
    private JLabel nameLabel;

    private JLabel surnameLabel;
    private JLabel lastnameLabel;
    private JButton collapseButton;
    private JButton clearButton;
    public JPanel getMainPanel() {
        return mainPanel;
    }

    public MainForm() {
        clearButton.addActionListener ( new Action () {
            @Override
            public Object getValue(String key) {
                return null;
            }

            @Override
            public void putValue(String key, Object value) {

            }

            @Override
            public void setEnabled(boolean b) {

            }

            @Override
            public boolean isEnabled() {
                return false;
            }

            @Override
            public void addPropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void removePropertyChangeListener(PropertyChangeListener listener) {

            }

            @Override
            public void actionPerformed(ActionEvent e) {
                if(!Objects.equals ( name.getText (), "" )) {
                    name.setText ( "" );
                    surname.setText ( "" );
                    lastname.setText ( "" );

                    if(Objects.equals ( name.getText (), "" ) || Objects.equals ( surname.getText (), "" ) || Objects.equals ( lastname.getText (), "" )) {
                        JOptionPane.showMessageDialog(
                                mainPanel,
                                "Очистка произвелась успешно",
                        "Очистка",
                        JOptionPane.PLAIN_MESSAGE
                        );
                    }
                }

            }
        } );
        collapseButton.addActionListener(actionEvent -> {
            if (collapseButton.getText().equals("Collapse")) {
                if (name.getText().isEmpty() || surname.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(mainPanel, "Заполните имя и фамилию.");
                    name.setText ( "" );
                    surname.setText ( "" );
                    lastname.setText ( "" );
                } else {
                    String collapseText = (name.getText() + " " + surname.getText() + " " + lastname.getText());
                    lastname.setPreferredSize(new Dimension(300,14));
                    lastname.setText(collapseText);
                    lastnameLabel.setText("Ф.И.О");
                    collapseButton.setText("Expand");
                    name.setVisible(false);
                    nameLabel.setVisible(false);
                    surname.setVisible(false);
                    surnameLabel.setVisible(false);

                }
            }
            if (collapseButton.getText().equals("Expand")) {
                String[] fio = lastname.getText().split(" ");
                if (fio.length > 3 || fio.length < 2) {
                    JOptionPane.showMessageDialog(mainPanel, "Ф.И.О введено не коректно");
                    } else {
                    if(fio.length == 2) {
                        name.setText(fio[0]);
                        surname.setText(fio[1]);
                        lastname.setText("");
                    }
                    if (fio.length == 3) {
                        name.setText ( fio[0] );
                        surname.setText ( fio[1] );
                        lastname.setText ( fio[2] );
                    }
                        name.setVisible ( true );
                        nameLabel.setVisible ( true );
                        surname.setVisible ( true );
                        surnameLabel.setVisible ( true );
                        nameLabel.setVisible(true);
                        surname.setVisible(true);
                        lastnameLabel.setText("Отчество");
                        lastname.setPreferredSize(new Dimension(70,14));
                        collapseButton.setText ( "Collapse" );
                    }
                }
        });
    }
}

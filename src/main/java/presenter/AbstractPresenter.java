package presenter;

import javax.swing.*;
import java.awt.event.ActionListener;

public class AbstractPresenter implements IPresenter {

    protected JInternalFrame view;

    @Override
    public JInternalFrame getView() {
        return view;
    }

    public void resetButtonActions(JButton b) {
        for(ActionListener al : b.getActionListeners()) {
            b.removeActionListener(al);
        }
    }
}

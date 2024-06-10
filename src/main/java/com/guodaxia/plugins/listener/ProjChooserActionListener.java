package com.guodaxia.plugins.listener;

import com.guodaxia.plugins.consts.GenerateEntitiesConfigConsts;
import com.intellij.ide.util.PropertiesComponent;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @Author: Guodaxia
 * @Date: 2024-06-05
 * @Desc:
 **/
public class ProjChooserActionListener implements ActionListener {

    private JTextField projPathField;

    public ProjChooserActionListener(JTextField projPathField) {
        this.projPathField = projPathField;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.openDirectoriesChooser();
    }

    private void openDirectoriesChooser() {
        JFileChooser projChooser = new JFileChooser();
        projChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        int state = projChooser.showOpenDialog(null);
        String projPath = projChooser.getSelectedFile().toString();
        projPathField.setText(projPath);
        PropertiesComponent.getInstance().setValue(GenerateEntitiesConfigConsts.PROJ_PATH, projPath);
    }

}

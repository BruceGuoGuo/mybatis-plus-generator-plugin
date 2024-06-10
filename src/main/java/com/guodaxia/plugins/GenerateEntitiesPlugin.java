package com.guodaxia.plugins;

import com.guodaxia.plugins.dialog.MysqlInfoInputDialog;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.project.Project;


/**
 * @Author: Guodaxia
 * @Date: 2024-06-05
 * @Desc:
 **/
public class GenerateEntitiesPlugin extends AnAction {


    @Override
    public void actionPerformed(AnActionEvent e) {
        Project project = e.getProject();
        MysqlInfoInputDialog dialog = new MysqlInfoInputDialog(project);
        dialog.show();
    }

}

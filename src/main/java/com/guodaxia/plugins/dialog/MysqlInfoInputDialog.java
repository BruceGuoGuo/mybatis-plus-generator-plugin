package com.guodaxia.plugins.dialog;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.ITypeConvert;
import com.baomidou.mybatisplus.generator.config.converts.MySqlTypeConvert;
import com.baomidou.mybatisplus.generator.config.rules.DbColumnType;
import com.baomidou.mybatisplus.generator.config.rules.IColumnType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.guodaxia.plugins.consts.GenerateEntitiesConfigConsts;
import com.guodaxia.plugins.listener.ProjChooserActionListener;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.Messages;
import com.intellij.openapi.ui.ValidationInfo;
import com.intellij.openapi.ui.VerticalFlowLayout;
import com.intellij.ui.JBColor;
import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.beans.PropertyChangeListener;
import java.io.File;
import java.util.regex.Pattern;

/**
 * @Author: Guodaxia
 * @Date: 2024-06-05
 * @Desc:
 **/
public class MysqlInfoInputDialog extends DialogWrapper implements Action {

    private static String author = "Guodaxia";

    private CustomOKAction okAction;
    private CustomApplyAction applyAction;
    private DialogWrapperExitAction exitAction;

    private JTextField projPathField;
    private JTextField hostField;
    private JTextField usernameField;
    private JTextField passwordField;
    private JTextField dbnameField;
    private JTextArea tablesField;
    private JTextField packageNameField;
    private static final int HEIGHT = 30;
    private static final int LABEL_WIDTH = 80;

    private static final int INPUT_WIDTH = 300;

    private static final int BTN_WIDTH = 50;

    public MysqlInfoInputDialog(@Nullable Project project) {
        super(project);
        init();
        setTitle("DB Config");
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new VerticalFlowLayout());

        JPanel projPathPanel = new JPanel();
        JLabel projLabel = new JLabel("Proj Path");
        projLabel.setPreferredSize(new Dimension(LABEL_WIDTH, HEIGHT));
        projPathField = new JTextField();
        projPathField.setPreferredSize(new Dimension(INPUT_WIDTH - BTN_WIDTH, HEIGHT));
        String projPath = PropertiesComponent.getInstance().getValue(GenerateEntitiesConfigConsts.PROJ_PATH);
        projPathField.setText(projPath);
        JButton projChooserBtn = new JButton("open");
        projChooserBtn.setPreferredSize(new Dimension(BTN_WIDTH, HEIGHT));
        projChooserBtn.addActionListener(new ProjChooserActionListener(projPathField));
        projPathPanel.add(projLabel, BorderLayout.NORTH);
        projPathPanel.add(projPathField);
        projPathPanel.add(projChooserBtn);

        JPanel hostPanel = new JPanel();
        JLabel hostLabel = new JLabel("Host");
        hostLabel.setPreferredSize(new Dimension(LABEL_WIDTH, HEIGHT));
        hostField = new JTextField();
        hostField.setPreferredSize(new Dimension(INPUT_WIDTH, HEIGHT));
        String host = PropertiesComponent.getInstance().getValue(GenerateEntitiesConfigConsts.HOST);
        hostField.setText(host);
        hostPanel.add(hostLabel, BorderLayout.NORTH);
        hostPanel.add(hostField);

        JPanel usernamePanel = new JPanel();
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setPreferredSize(new Dimension(LABEL_WIDTH, HEIGHT));
        usernameField = new JTextField();
        usernameField.setPreferredSize(new Dimension(INPUT_WIDTH, HEIGHT));
        String username = PropertiesComponent.getInstance().getValue(GenerateEntitiesConfigConsts.USERNAME);
        usernameField.setText(username);
        usernamePanel.add(usernameLabel, BorderLayout.NORTH);
        usernamePanel.add(usernameField);

        JPanel passwordPanel = new JPanel();
        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setPreferredSize(new Dimension(LABEL_WIDTH, HEIGHT));
        passwordField = new JPasswordField();
        passwordField.setPreferredSize(new Dimension(INPUT_WIDTH, HEIGHT));
        String password = PropertiesComponent.getInstance().getValue(GenerateEntitiesConfigConsts.PASSWORD);
        passwordField.setText(password);
        passwordPanel.add(passwordLabel, BorderLayout.NORTH);
        passwordPanel.add(passwordField);

        JPanel dbnamePanel = new JPanel();
        JLabel dbnameLabel = new JLabel("Dbname");
        dbnameLabel.setPreferredSize(new Dimension(LABEL_WIDTH, HEIGHT));
        dbnameField = new JTextField();
        dbnameField.setPreferredSize(new Dimension(INPUT_WIDTH, HEIGHT));
        String dbname = PropertiesComponent.getInstance().getValue(GenerateEntitiesConfigConsts.DBNAME);
        dbnameField.setText(dbname);
        dbnamePanel.add(dbnameLabel, BorderLayout.NORTH);
        dbnamePanel.add(dbnameField);

        JPanel tablesPanel = new JPanel();
        JLabel tablesLabel = new JLabel("Tables");
        tablesLabel.setPreferredSize(new Dimension(LABEL_WIDTH, HEIGHT));
        tablesField = new JTextArea();
        tablesField.setPreferredSize(new Dimension(INPUT_WIDTH, HEIGHT * 5));
        tablesField.setBorder(new LineBorder(JBColor.GRAY, 1));
        tablesField.setLineWrap(true);
        tablesField.setWrapStyleWord(true);
        tablesField.setAutoscrolls(true);
        String tables = PropertiesComponent.getInstance().getValue(GenerateEntitiesConfigConsts.TABLES);
        tablesField.setText(tables);
        tablesPanel.add(tablesLabel, BorderLayout.NORTH);
        tablesPanel.add(tablesField);

        JPanel packageNamePanel = new JPanel();
        JLabel packageNameLabel = new JLabel("Package");
        packageNameLabel.setPreferredSize(new Dimension(LABEL_WIDTH, HEIGHT));
        packageNameField = new JTextField();
        packageNameField.setPreferredSize(new Dimension(INPUT_WIDTH, HEIGHT));
        String packageName = PropertiesComponent.getInstance().getValue(GenerateEntitiesConfigConsts.PACKAGE_NAME);
        packageNameField.setText(packageName);
        packageNamePanel.add(packageNameLabel, BorderLayout.NORTH);
        packageNamePanel.add(packageNameField);

        mainPanel.add(projPathPanel, BorderLayout.NORTH);
        mainPanel.add(packageNamePanel);
        mainPanel.add(hostPanel);
        mainPanel.add(usernamePanel);
        mainPanel.add(passwordPanel);
        mainPanel.add(dbnamePanel);
        mainPanel.add(tablesPanel);

        return mainPanel;
    }


    @Override
    protected @Nullable ValidationInfo doValidate() {
        String projPath = projPathField.getText();
        if (StringUtils.isBlank(projPath)) {
            return new ValidationInfo("Proj path is empty");
        }
        String host = hostField.getText();
        if (StringUtils.isBlank(host)) {
            return new ValidationInfo("Host is empty");
        }
        String username = usernameField.getText();
        if (StringUtils.isBlank(username)) {
            return new ValidationInfo("Username is empty");
        }
        String password = passwordField.getText();
        if (StringUtils.isBlank(password)) {
            return new ValidationInfo("Password is empty");
        }
        String dbname = dbnameField.getText();
        if (StringUtils.isBlank(dbname)) {
            return new ValidationInfo("Db name is empty");
        }
        String tables = tablesField.getText();
        if (StringUtils.isBlank(tables)) {
            return new ValidationInfo("Tables is empty");
        }
        String packageName = packageNameField.getText();
        if (StringUtils.isBlank(packageName)) {
            return new ValidationInfo("Package name is empty");
        }
        return null;
    }

    /**
     * 覆盖默认的ok/cancel按钮
     *
     * @return
     */
    @NotNull
    @Override
    protected Action[] createActions() {
        exitAction = new DialogWrapperExitAction("Cancel", CANCEL_EXIT_CODE);
        okAction = new CustomOKAction();
        applyAction = new CustomApplyAction();
        okAction.putValue(DialogWrapper.DEFAULT_ACTION, true);
        return new Action[]{okAction, applyAction, exitAction};
    }


    protected class CustomOKAction extends DialogWrapperAction {
        protected CustomOKAction() {
            super("OK");
        }

        @Override
        protected void doAction(ActionEvent e) {
            saveInfo();
            ValidationInfo validationInfo = doValidate();
            if (validationInfo != null) {
                Messages.showMessageDialog(validationInfo.message, "Info", Messages.getInformationIcon());
            } else {
                generatorEntities();
                close(CANCEL_EXIT_CODE);
            }
        }
    }

    protected class CustomApplyAction extends DialogWrapperAction {
        protected CustomApplyAction() {
            super("Apply");
        }

        @Override
        protected void doAction(ActionEvent e) {
            saveInfo();
        }
    }

    private void saveInfo() {
        PropertiesComponent.getInstance().setValue(GenerateEntitiesConfigConsts.PROJ_PATH, projPathField.getText());
        PropertiesComponent.getInstance().setValue(GenerateEntitiesConfigConsts.HOST, hostField.getText());
        PropertiesComponent.getInstance().setValue(GenerateEntitiesConfigConsts.USERNAME, usernameField.getText());
        PropertiesComponent.getInstance().setValue(GenerateEntitiesConfigConsts.PASSWORD, passwordField.getText());
        PropertiesComponent.getInstance().setValue(GenerateEntitiesConfigConsts.DBNAME, dbnameField.getText());
        PropertiesComponent.getInstance().setValue(GenerateEntitiesConfigConsts.TABLES, tablesField.getText());
        PropertiesComponent.getInstance().setValue(GenerateEntitiesConfigConsts.PACKAGE_NAME, packageNameField.getText());
    }


    public void generatorEntities() {

        String projPath = projPathField.getText();
        String host = hostField.getText();
        String username = usernameField.getText();
        String password = passwordField.getText();
        String dbName = dbnameField.getText();
        String strTableNames = tablesField.getText().replaceAll(" ", "");
        String packageName = packageNameField.getText();

        try {
            String[] tableNames = strTableNames.split(",");
            String url = "jdbc:mysql://" + host + "/" + dbName + "?useSSL=false";
            DataSourceConfig.Builder dataSourceConfigBuilder = createDataSourceConfigBuilder(url, username, password);
            FastAutoGenerator
                    .create(dataSourceConfigBuilder)
                    .globalConfig(builder -> {
                        builder.author(author) //设置作者
                                .fileOverride() // 覆盖已生成文件
                                .disableOpenDir()
                                .outputDir(getSrcJavaDir(projPath)); // 制定输出目录
                    })
                    .packageConfig(builder -> {
                        builder.parent(packageName) // 设置父包名
                                .moduleName(dbName)// 设置父包模块名
                                .serviceImpl("domain")
                                .build();
                    })
                    .templateConfig(builder -> {
                        builder.controller(null)
                                .service(null)
                                .serviceImpl("Domain.java")
                                .mapper("Mapper.java")
                                .entity("DO.java")
                                .mapperXml(null)
                                .build();
                    })
                    .strategyConfig(builder -> {
                        builder.addInclude(tableNames); // 设置需要生成的表名
                        builder.entityBuilder()
                                .columnNaming(NamingStrategy.underline_to_camel)
                                .formatFileName("%sDO")
                                .build();
                        builder.mapperBuilder()
                                .formatMapperFileName("%sMapper")
                                .build();
                        builder.serviceBuilder()
                                .formatServiceImplFileName("%sDomain")
                                .build();
                    })
                    .templateEngine(new FreemarkerTemplateEngine())
                    .execute();
            JOptionPane.showMessageDialog(null, "Successful", "Info", JOptionPane.INFORMATION_MESSAGE);
        } catch (Exception e) {
            String message = e.getLocalizedMessage();
            JOptionPane.showMessageDialog(null, message, "Info", JOptionPane.INFORMATION_MESSAGE);
        }
    }

    private static String getSrcJavaDir(String projPath) {
        return projPath + File.separator +
               "src" + File.separator +
               "main" + File.separator +
               "java" + File.separator;
    }

    private static DataSourceConfig.Builder createDataSourceConfigBuilder(String url, String username, String pwd) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        DataSourceConfig.Builder dataSourceConfigBuilder = new DataSourceConfig.Builder(url, username, pwd);
        dataSourceConfigBuilder.typeConvert(new ITypeConvert() {
            @Override
            public IColumnType processTypeConvert(GlobalConfig globalConfig, String fieldType) {
                String t = fieldType.toLowerCase();

                if (Pattern.matches("varchar(\\(\\d+\\))?", t)) {
                    return DbColumnType.STRING;
                }
                if (Pattern.matches("char(\\(\\d+\\))?", t)) {
                    return DbColumnType.STRING;
                }
                if (Pattern.matches("(tiny|medium|long)*text", t)) {
                    return DbColumnType.STRING;
                }
                if (Pattern.matches("decimal(\\(\\d+,\\d+\\))?", t)) {
                    return DbColumnType.BIG_DECIMAL;
                }
                if (Pattern.matches("(tiny|small|medium)*int(\\(\\d+\\))?", t)) {
                    return DbColumnType.INTEGER;
                }
                if (Pattern.matches("bigint(\\(\\d+\\))?", t)) {
                    return DbColumnType.LONG;
                }
                if ("integer".equals(t)) {
                    return DbColumnType.INTEGER;
                }
                if ("int4".equals(t)) {
                    return DbColumnType.INTEGER;
                }
                if ("int8".equals(t)) {
                    return DbColumnType.LONG;
                }
                if ("date".equals(t)) {
                    return DbColumnType.LOCAL_DATE;
                }
                if ("datetime".equals(t)) {
                    return DbColumnType.LOCAL_DATE_TIME;
                }
                if ("timestamp".equals(t)) {
                    return DbColumnType.LOCAL_DATE_TIME;
                }
                if ("time".equals(t)) {
                    return DbColumnType.LOCAL_TIME;
                }
                if ("boolean".equals(t)) {
                    return DbColumnType.BOOLEAN;
                }
                // 其他字段采用默认转换（非mysql数据库可以使用其他默认的数据库转换器）
                return new MySqlTypeConvert().processTypeConvert(globalConfig, fieldType);
            }
        });
        return dataSourceConfigBuilder;
    }


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

    }
}

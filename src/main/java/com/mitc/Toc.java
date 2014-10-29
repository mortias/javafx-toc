package com.mitc;

import com.mitc.tools.Utils;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import org.apache.log4j.Logger;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;import java.text.MessageFormat;

public class Toc extends Application {

    private Utils utils = new Utils();

    private static final Logger logger = Logger.getLogger(Toc.class);

    private static final String configPath = "config.yml";
    private static final String iconImagePath = "icon.png";
    private static final String templatePath = "template.html";
    private static final String indexPath = "index.html";

    public double initialX;
    public double initialY;

    private Stage stage;

    public static void main(String[] args) throws IOException, java.awt.AWTException, URISyntaxException {
        launch(args);
    }

    @Override
    public void init() throws Exception {
        utils.loadConfig(configPath);
        utils.prepareContent(indexPath, templatePath);
    }

    @Override
    public void start(Stage stage) throws IOException, URISyntaxException {

        // stores a reference to the stage.
        this.stage = stage;

        // instructs the javafx system not to exit implicitly when the last application window is shut.
        Platform.setImplicitExit(false);

        // sets up the tray icon (using awt code run on the swing thread).
        javax.swing.SwingUtilities.invokeLater(this::addAppToTray);

        // out stage will be translucent, so give it a transparent style.
        stage.initStyle(StageStyle.TRANSPARENT);
        stage.initStyle(StageStyle.UNDECORATED);
        stage.setTitle(utils.lang.getString("title"));

        // load the site
        URL url = new File(utils.config.getSite() + "html/" + indexPath).toURI().toURL();
        logger.info(MessageFormat.format(utils.lang.getString("browsing.file"), url.toString()));
        Browser browser = new Browser(url.toString());
        addDraggableNode(browser.getWebView());

        // create the layout for the javafx stage.
        StackPane stackPane = new StackPane(browser);
        stackPane.setPrefSize(utils.config.getWidth(), utils.config.getHeight());

        Scene scene = new Scene(stackPane, utils.config.getWidth(), utils.config.getHeight(), Color.web("#000000"));
        scene.setFill(Color.TRANSPARENT);
        stage.setScene(scene);
        stage.show();

    }

    // Sets up a system tray icon for the application.
    private void addAppToTray() {
        try {

            // ensure awt toolkit is initialized.
            Toolkit.getDefaultToolkit();

            // app requires system tray support, just exit if there is no support.
            if (!SystemTray.isSupported()) {
                logger.error(utils.lang.getString("errTraySupport"));
                Platform.exit();
            }

            // set up a system tray icon.
            SystemTray tray = SystemTray.getSystemTray();
            ImageIcon ico = new ImageIcon(this.getClass().getResource("/images/" + iconImagePath));
            TrayIcon trayIcon = new TrayIcon(ico.getImage());

            // if the user double-clicks on the tray icon, show the main app stage.
            trayIcon.addActionListener(event -> Platform.runLater(this::showStage));

            MenuItem exitItem = new MenuItem("Exit");
            exitItem.addActionListener(event -> {
                Platform.exit();
                tray.remove(trayIcon);
            });

            // setup the popup menu for the application.
            final PopupMenu popup = new PopupMenu();
            popup.add(exitItem);
            trayIcon.setPopupMenu(popup);

            // add the application tray icon to the system tray.
            tray.add(trayIcon);

        } catch (AWTException e) {
            logger.error(utils.lang.getString("errTrayInit"));
            e.printStackTrace();
        }
    }

    private void showStage() {
        if (stage != null) {
            stage.show();
            stage.toFront();
        }
    }

    private void addDraggableNode(final Node node) {

        node.setOnMousePressed(me -> {
            if (me.getButton() != MouseButton.MIDDLE) {
                initialX = me.getSceneX();
                initialY = me.getSceneY();
            }
        });

        node.setOnMouseDragged(me -> {
            if (me.getButton() != MouseButton.MIDDLE) {
                node.getScene().getWindow().setX(me.getScreenX() - initialX);
                node.getScene().getWindow().setY(me.getScreenY() - initialY);
            }
        });
    }

}
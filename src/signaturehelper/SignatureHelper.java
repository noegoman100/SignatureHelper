/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signaturehelper;

import java.io.File;
import java.nio.file.Path;
//import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
//import javax.swing.JFileChooser;

/**
 *
 * @author Ed
 * The bulk of this application's functionality resides inside SignatureHelperController.java.
 */
public class SignatureHelper extends Application {
    public static Scene homeScene;
    public static Stage homeStage;
    @Override
    public void start(Stage primaryStage) {
        homeStage = primaryStage;
        primaryStage.setTitle("Signature Helper");

        try{
        Parent homeRoot = FXMLLoader.load(getClass().getResource("SignatureHelperView.fxml"));
        //Scene homeScene = new Scene(homeRoot);
        homeScene = new Scene(homeRoot);
        primaryStage.setScene(homeScene);
        primaryStage.show();   
        } catch (Exception e){
            System.out.println("There was a problem changing the scene.");
        }

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

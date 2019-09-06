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
 */
public class SignatureHelper extends Application {
    public static Scene homeScene;
    public static Stage homeStage;
    @Override
    public void start(Stage primaryStage) {
        homeStage = primaryStage;
        Button btn = new Button();
        btn.setText("Say 'Hello World'");
        btn.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                //List<File> selectedFiles = new ArrayList<>();
//                List<File> selectedFiles = new ArrayList<>();
//                
//                System.out.println("Hello World!");
//                FileChooser fileChooser = new FileChooser();
//                fileChooser.setTitle("Open Resource File");
//                selectedFiles.addAll(fileChooser.showOpenMultipleDialog(primaryStage));
//                Collections.sort(selectedFiles);
//                File currentPath = new File(selectedFiles.get(1).getPath());
//                System.out.println("Current Path: " + currentPath.getPath());
//                System.out.println("Absolute Path: " + currentPath.getAbsolutePath());
//                System.out.println("Current Parent: " + currentPath.getParent());
//                //Files files =  new Files();
//                for (int i = 0; i < selectedFiles.size(); i++){
//                    System.out.println(selectedFiles.get(i).toString());
//                    selectedFiles.get(i).renameTo(new File(currentPath.getParent() + "\\Page_P-10" + i + ".pdf"));
//                    
//                    //singleFile
//                }
                
            }
        });
        
        StackPane root = new StackPane();
        root.getChildren().add(btn);
        
        Scene scene = new Scene(root, 300, 250);
        try{
        Parent homeRoot = FXMLLoader.load(getClass().getResource("SignatureHelperView.fxml"));
        //Scene homeScene = new Scene(homeRoot);
        homeScene = new Scene(homeRoot);
        primaryStage.setScene(homeScene);
        primaryStage.show();   
        } catch (Exception e){
            System.out.println("There was a problem changing the scene.");
        }
        
//        primaryStage.setTitle("Hello World!");
//        primaryStage.setScene(scene);
//        primaryStage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}

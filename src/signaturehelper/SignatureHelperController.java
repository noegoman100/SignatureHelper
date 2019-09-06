/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package signaturehelper;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Ed
 */
public class SignatureHelperController implements Initializable {
    @FXML private ListView<File> selectedFilesListView;
    @FXML private ListView<String> outputListView;
    @FXML private RadioButton radio4to8;
    @FXML private RadioButton radio2to8;
    @FXML private TextField pubcodeField;
    @FXML private TextField dateField;
    @FXML private TextField zoneField;
    private ToggleGroup group = new ToggleGroup();
    private ObservableList<File> outputFiles = FXCollections.observableArrayList();
    private ObservableList<File> selectedFiles = FXCollections.observableArrayList();
    
    @Override public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //selectedFilesListView
        radio4to8.setToggleGroup(group);
        radio2to8.setToggleGroup(group);
        radio4to8.setSelected(true);
    }    
    @FXML public void selectFiles(ActionEvent e){
                
                FileChooser fileChooser = new FileChooser();
                selectedFiles.clear();
                fileChooser.setTitle("Open Resource File");
                selectedFiles.addAll(fileChooser.showOpenMultipleDialog(SignatureHelper.homeStage));
                Collections.sort(selectedFiles);
                File currentPath = new File(selectedFiles.get(1).getPath());
                System.out.println("Current Path: " + currentPath.getPath());
                System.out.println("Absolute Path: " + currentPath.getAbsolutePath());
                System.out.println("Current Parent: " + currentPath.getParent());
                selectedFilesListView.setItems(selectedFiles);
                populateOutputPreview();
                //selectedFilesListView.setC.setCellValueFactory(new PropertyValueFactory(name));
    }
    
    public void populateOutputPreview(){
        ObservableList<File> tempFiles = FXCollections.observableArrayList();
        tempFiles.setAll(selectedFiles);
        
        if (radio2to8.isSelected()){
            ObservableList<String> tempList = FXCollections.observableArrayList();
            System.out.println("radio2to8 processing...");
            outputFiles.clear();
            outputFiles.add(new File("2to8-1"));
            outputFiles.add(new File("2to8-2"));
            outputFiles.add(new File("2to8-3"));
            outputFiles.add(new File("2to8-4"));
            
            outputListView.setItems(tempList);
            return;
        }
        if (radio4to8.isSelected()){
            System.out.println("radio4to8 processing...");
            outputFiles.clear();
            //outputFiles =  FXCollections.observableArrayList();
//            outputFiles.add(new File("4to8-1"));
//            outputFiles.add(new File("4to8-2"));
//            outputFiles.add(new File("4to8-3"));
//            outputFiles.add(new File("4to8-4")); 
            ObservableList<String> tempList = FXCollections.observableArrayList();

            
            outputFiles.add(0, tempFiles.get(0));
            outputFiles.add(1, tempFiles.get(1));
            outputFiles.add(2, tempFiles.get(1));
            outputFiles.add(3, tempFiles.get(0));
            outputFiles.add(4, tempFiles.get(3));
            outputFiles.add(5, tempFiles.get(2));
            outputFiles.add(6, tempFiles.get(2));
            outputFiles.add(7, tempFiles.get(3));
            //outputFiles.addAll(outputFiles);
            //new File(" " + tempFiles.get(0).toString())
            tempList.add(0, tempFiles.get(0).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-001.pdf"); 
            tempList.add(1, tempFiles.get(1).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-002.pdf"); 
            tempList.add(2, tempFiles.get(1).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-003.pdf"); 
            tempList.add(3, tempFiles.get(0).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-004.pdf"); 
            tempList.add(4, tempFiles.get(3).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-005.pdf"); 
            tempList.add(5, tempFiles.get(2).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-006.pdf"); 
            tempList.add(6, tempFiles.get(2).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-007.pdf"); 
            tempList.add(7, tempFiles.get(3).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-008.pdf");     
            
            outputListView.setItems(tempList);
            
            return;
        }

        
    }
    @FXML public void changeFiles(ActionEvent e){
        
        if (radio2to8.isSelected()){
            outputFiles.clear();
            selectedFiles.clear();
            return;
        } 
        if (radio4to8.isSelected()){
            outputFiles.get(6).renameTo(new File(outputFiles.get(6).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-007.pdf"));
            outputFiles.get(7).renameTo(new File(outputFiles.get(7).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-008.pdf"));
            outputFiles.get(0).renameTo(new File(outputFiles.get(7).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-001.pdf"));
            outputFiles.get(1).renameTo(new File(outputFiles.get(7).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-002.pdf"));
            try {
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-001.pdf").toPath(), new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-004.pdf").toPath());
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-002.pdf").toPath(), new File(outputFiles.get(1).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-003.pdf").toPath());
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-007.pdf").toPath(), new File(outputFiles.get(6).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-006.pdf").toPath());
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-008.pdf").toPath(), new File(outputFiles.get(7).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-005.pdf").toPath());
            } catch (Exception exception){ System.out.println("Error Copying/Moving Files"); System.out.println(exception); }
            
            
            outputFiles.clear();
            selectedFiles.clear();
            outputListView.getItems().clear();
            
            return;
        }
    }
}

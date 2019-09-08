package signaturehelper;

import java.io.File;
import java.net.URL;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.ResourceBundle;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
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
                File currentPath = new File(selectedFiles.get(0).getPath());
                System.out.println("Current Path: " + currentPath.getPath());
                System.out.println("Absolute Path: " + currentPath.getAbsolutePath());
                System.out.println("Current Parent: " + currentPath.getParent());
                selectedFilesListView.setItems(selectedFiles);//This loads the Incoming Files List View with the selected files. 
                populateOutputPreview();
                //selectedFilesListView.setC.setCellValueFactory(new PropertyValueFactory(name));
                if (validFilename(selectedFiles.get(0).getName())){
                    System.out.println("Valid Filename");
                } else {System.out.println("Not Valid Filename");}
    }
    
    public void populateOutputPreview(){
        ObservableList<File> tempFiles = FXCollections.observableArrayList();
        tempFiles.setAll(selectedFiles);
        outputListView.getItems().clear();
        
        if (radio2to8.isSelected() && !selectedFiles.isEmpty()){
            if (selectedFiles.size() == 2){
                ObservableList<String> tempList = FXCollections.observableArrayList();
                System.out.println("radio2to8 processing...");
                outputFiles.clear();
                ObservableList<String> tempList2 = FXCollections.observableArrayList();


                outputFiles.add(0, tempFiles.get(0));
                outputFiles.add(1, tempFiles.get(1));

                //outputFiles.addAll(outputFiles);
                //new File(" " + tempFiles.get(0).toString())
                tempList2.add(0, tempFiles.get(0).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-001.pdf"); 
                tempList2.add(1, tempFiles.get(0).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-004.pdf"); 
                tempList2.add(2, tempFiles.get(0).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-005.pdf"); 
                tempList2.add(3, tempFiles.get(0).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-008.pdf"); 
                tempList2.add(4, tempFiles.get(1).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-003.pdf"); 
                tempList2.add(5, tempFiles.get(1).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-006.pdf"); 
                tempList2.add(6, tempFiles.get(1).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-007.pdf"); 
                tempList2.add(7, tempFiles.get(1).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-002.pdf");     

                outputListView.setItems(tempList2);
            }//End Nested If
            return;
        }//End If
        if (radio4to8.isSelected() && !selectedFiles.isEmpty()){
            if (selectedFiles.size() == 4){
                System.out.println("radio4to8 processing...");
                outputFiles.clear();
                ObservableList<String> tempList = FXCollections.observableArrayList();

                outputFiles.add(0, tempFiles.get(0));
                outputFiles.add(1, tempFiles.get(1));
                outputFiles.add(2, tempFiles.get(2));
                outputFiles.add(3, tempFiles.get(3));

                tempList.add(0, tempFiles.get(0).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-001.pdf"); 
                tempList.add(1, tempFiles.get(1).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-002.pdf"); 
                tempList.add(2, tempFiles.get(1).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-003.pdf"); 
                tempList.add(3, tempFiles.get(0).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-004.pdf"); 
                tempList.add(4, tempFiles.get(3).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-005.pdf"); 
                tempList.add(5, tempFiles.get(2).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-006.pdf"); 
                tempList.add(6, tempFiles.get(2).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-007.pdf"); 
                tempList.add(7, tempFiles.get(3).getName() + " to \\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-008.pdf");     

                outputListView.setItems(tempList);
            }//End Nested If
            return;
        }

        
    }
    @FXML public void changeFiles(ActionEvent e){
        
        if (radio2to8.isSelected() && !selectedFiles.isEmpty()){
            outputFiles.get(0).renameTo(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-001.pdf"));
            outputFiles.get(1).renameTo(new File(outputFiles.get(1).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-002.pdf"));
            try {
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-001.pdf").toPath(), new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-004.pdf").toPath());
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-001.pdf").toPath(), new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-005.pdf").toPath());
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-001.pdf").toPath(), new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-008.pdf").toPath());
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-002.pdf").toPath(), new File(outputFiles.get(1).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-003.pdf").toPath());
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-002.pdf").toPath(), new File(outputFiles.get(1).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-006.pdf").toPath());
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-002.pdf").toPath(), new File(outputFiles.get(1).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-007.pdf").toPath());
            } catch (Exception exception){ System.out.println("Error Copying/Moving Files"); System.out.println(exception); }
            
            
            outputFiles.clear();
            selectedFiles.clear();
            outputListView.getItems().clear();
            
            return;
        } 
        if (radio4to8.isSelected() && !selectedFiles.isEmpty()){
            
            outputFiles.get(0).renameTo(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-001.pdf"));
            outputFiles.get(1).renameTo(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-002.pdf"));
            outputFiles.get(2).renameTo(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-007.pdf"));
            outputFiles.get(3).renameTo(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-008.pdf"));
            try {
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-001.pdf").toPath(), new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-004.pdf").toPath());
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-002.pdf").toPath(), new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-003.pdf").toPath());
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-007.pdf").toPath(), new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-006.pdf").toPath());
            Files.copy(new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-008.pdf").toPath(), new File(outputFiles.get(0).getParent() + "\\" + pubcodeField.getText() + "_" + dateField.getText() + "_" + zoneField.getText() + "-005.pdf").toPath());
            } catch (Exception exception){ System.out.println("Error Copying/Moving Files"); System.out.println(exception); }
            
            
            outputFiles.clear();
            selectedFiles.clear();
            outputListView.getItems().clear();
            
            return;
        }
        
    }
    public boolean validFilename(String matchMe){
        String patternString = "(([A-Za-z0-9]+\\-[A-Za-z0-9]+)|([A-Za-z0-9]+))_0906_[A-Za-z0-9]+-[0-9]{3}.[A-Za-z0-9]+"; //PUBCODE-SECTION_0906_ZONE-001
        Pattern pattern = Pattern.compile(patternString);
        return Pattern.matches(patternString, matchMe);
    }
}//End Class

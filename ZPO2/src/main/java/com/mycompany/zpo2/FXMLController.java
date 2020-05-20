package com.mycompany.zpo2;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.DirectoryNotEmptyException;
import java.nio.file.DirectoryStream;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.MouseEvent;
import javafx.scene.input.TransferMode;

public class FXMLController implements Initializable {
    
    @FXML
    private Label diskSpaceLabel;
    @FXML
    private Label diskSpaceLabel2;
    @FXML
    private Label filePathLabel;
    @FXML
    private Label filePathLabel2;
    @FXML
    private TableView<String> fileTable1;
    @FXML
    private TableView<String> fileTable2;
    @FXML
    private TableColumn<String,String> files;
    @FXML
    private TableColumn<String,String> files2;
    @FXML
    private ChoiceBox diskOpt;
    @FXML
    private ChoiceBox diskOpt2;
    @FXML
    private TextField newDirName;
    @FXML
    private TextField newDirName2;
    @FXML
    private Button createNewDir;
    @FXML
    private Button createNewDir2;
    @FXML
    private Button dirBack;
    @FXML
    private Button dirBack2;
    @FXML
    private Button copyDir;
    @FXML
    private Button moveDir;
    @FXML
    private CheckBox delDirWithContent;
    
    ObservableList<String> fileList = FXCollections.observableArrayList();
    ObservableList<String> diskList = FXCollections.observableArrayList();
    ObservableList<String> fileList2 = FXCollections.observableArrayList();
    ObservableList<String> diskList2 = FXCollections.observableArrayList();
    
    public List<String> listDisks(){
        List<String>disksList = new ArrayList<>();
        Iterable<Path> dirs = FileSystems.getDefault().getRootDirectories();
        for (Path name: dirs) {
            disksList.add(name.toString());        
        }
        return disksList;
    }
    
    public List<String> listFiles(String directory){
        List<String> filesList = new ArrayList<>();
        try (DirectoryStream<Path> directoryStream = Files.newDirectoryStream(Paths.get(directory))) {
            for (Path path : directoryStream) {
                filesList.add(path.toString());
            }
        } catch (IOException ex) {System.out.println("IO Error");}
        return filesList;
    }
    
    
    public void copyFile(Path pathSrc,Path pathDst) throws IOException{
        System.out.println("Copy"+pathSrc+" -> "+pathDst);
                if(pathSrc==null || pathDst==null){
                    System.out.println("Error, please select item on both tables!");
                }
                else{
                    Files.copy(pathSrc, pathDst);
                    //to do FileUtils.copyDirectory(pathSrc, pathDst);
                }
    }
    
    public void moveFile(Path pathSrc,Path pathDst) throws IOException{
        System.out.println("Move"+pathSrc+" -> "+pathDst);
        if(pathSrc==null || pathDst==null){
                    System.out.println("Error, please select item on both tables!");
                }
                else{
                    Files.move(pathSrc, pathDst);
                    refreshTable(pathSrc.getParent().toString(),"fileTable1");
                }
    }
    
    public void deleteDirectoryStream(Path path) throws IOException {
    Files.walk(path)
        .sorted(Comparator.reverseOrder())
        .map(Path::toFile)
        .forEach(File::delete);
    }
    
    public void deleteFile(Path path) throws IOException{
        try {
            Files.delete(path);
        } catch (NoSuchFileException x) {
            System.err.format("%s: no such" + " file or directory%n", path);
        } catch (DirectoryNotEmptyException x) {
            if(delDirWithContent.isSelected()){
                deleteDirectoryStream(path);
            }
            else System.err.format("%s not empty%n", path);
        } catch (IOException x) {
            // File permission problems are caught here.
            System.err.println(x);
        }
        refreshTable(path.getParent().toString(),"fileTable1");
    }
    
    public void refreshTable(String selectedItem,String tableName){
        switch(tableName){
            case "fileTable1":
                fileTable1.getItems().clear();
                files.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
                fileList.addAll(listFiles(selectedItem));
                fileTable1.setItems(fileList);
                break;
            case "fileTable2":
                fileTable2.getItems().clear();
                files2.setCellValueFactory(param -> new ReadOnlyStringWrapper(param.getValue()));
                fileList2.addAll(listFiles(selectedItem));
                fileTable2.setItems(fileList2);
                break;
        }
    }
    
    @FXML
    void handleDragDetection(MouseEvent event) {
        Dragboard db = fileTable2.startDragAndDrop(TransferMode.ANY);
        ClipboardContent clipBoard = new ClipboardContent();
        clipBoard.putString(fileTable1.getSelectionModel().getSelectedItem());
        db.setContent(clipBoard);
        event.consume();
    }
    
    @FXML
    void handleDragOver(DragEvent event) {
        if (event.getDragboard().hasString()) {
            event.acceptTransferModes(TransferMode.ANY);
        }
    }

    @FXML
    void handleDragDrop(DragEvent event) throws IOException {
        Path dirPath=Paths.get(event.getDragboard().getString());
        Path dirPathDst=null;
        if(fileTable2.getSelectionModel().getSelectedItem()==null){
            dirPathDst=Paths.get(filePathLabel2.getText(),dirPath.getFileName().toString());
        }
        else{
            dirPathDst=Paths.get(fileTable2.getSelectionModel().getSelectedItem(),dirPath.getFileName().toString());
        }
        copyFile(dirPath,dirPathDst);
        
    }
    
    @FXML
    private void handleButtonAction(ActionEvent event) throws IOException {
        Button button =(Button)event.getSource();
        String buttonId = button.getId();
        Path dirPath = null,dirPathDst=null;
        switch(buttonId){
            case("dirBack"):
                dirPath=Paths.get(filePathLabel.getText());
                dirPath=dirPath.getParent();
                if(dirPath==null){
                    System.out.println("Root directiory, no parent exist!");
                }
                else{
                    refreshTable(dirPath.toString(),"fileTable1");
                    filePathLabel.setText(dirPath.toString());
                }
                break;
            case("dirBack2"):
                dirPath=Paths.get(filePathLabel2.getText());
                dirPath=dirPath.getParent();
                if(dirPath==null){
                    System.out.println("Root directiory, no parent exist!");
                }
                else{
                    refreshTable(dirPath.toString(),"fileTable2");
                    filePathLabel2.setText(dirPath.toString());
                }
                break;
            case("createNewDir"):
                dirPath=Paths.get(filePathLabel.getText(),newDirName.getText());
                Files.createDirectory(dirPath);
                refreshTable(dirPath.getParent().toString(),"fileTable1");
                break;
            case("createNewDir2"):
                dirPath=Paths.get(filePathLabel2.getText(),newDirName2.getText());
                Files.createDirectory(dirPath);
                refreshTable(dirPath.getParent().toString(),"fileTable2");
                break;
            case("delDir"):
                dirPath=Paths.get(fileTable1.getSelectionModel().getSelectedItem());
                deleteFile(dirPath);
                break;
            case("moveDir"):
                dirPath=Paths.get(fileTable1.getSelectionModel().getSelectedItem());
                dirPathDst=Paths.get(fileTable2.getSelectionModel().getSelectedItem(),dirPath.getFileName().toString());
                moveFile(dirPath,dirPathDst);
                break;
            case("copyDir"):
                dirPath=Paths.get(fileTable1.getSelectionModel().getSelectedItem());
                dirPathDst=Paths.get(fileTable2.getSelectionModel().getSelectedItem(),dirPath.getFileName().toString());
                copyFile(dirPath,dirPathDst);
                break;
        }
    }
    
    @FXML
    private void handleMouseAction(MouseEvent event) throws IOException{
        // get table ID to method work on both tables
        Path dir=null;
        if (event.getClickCount() == 2) {
            String tableSource=event.getSource().toString().substring(13, 23);
            switch(tableSource){
                case "fileTable1":
                dir=Paths.get(fileTable1.getSelectionModel().getSelectedItem());
                filePathLabel.setText(dir.toString());
                break;
            case "fileTable2":
                dir=Paths.get(fileTable2.getSelectionModel().getSelectedItem());
                filePathLabel2.setText(dir.toString());
                break;
            }
            if(Files.isDirectory(dir)){
                refreshTable(dir.toString(),tableSource);
            }
            else if(Files.isExecutable(dir) || Files.isReadable(dir)){
                Desktop.getDesktop().open(new File(dir.toString()));
            }
            else{
                System.out.println("File is not executable or readable!");
            }
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        diskList.addAll(listDisks());
        diskList2.addAll(listDisks());
        diskOpt.setItems(diskList);
        diskOpt2.setItems(diskList2);
        diskOpt.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            diskSpaceLabel.setText("Available disc space : ");
            refreshTable(newValue.toString(),"fileTable1");
            File drive=new File(newValue.toString());
            long space=drive.getUsableSpace()/(1024*1024);
            diskSpaceLabel.setText(diskSpaceLabel.getText()+space+" MB");
            filePathLabel.setText(newValue.toString());
        });
        diskOpt2.getSelectionModel().selectedItemProperty().addListener((obs, oldValue, newValue) -> {
            diskSpaceLabel2.setText("Available disc space : ");
            refreshTable(newValue.toString(),"fileTable2");
            File drive=new File(newValue.toString());
            long space=drive.getUsableSpace()/(1024*1024);
            diskSpaceLabel2.setText(diskSpaceLabel2.getText()+space+" MB");
            filePathLabel2.setText(newValue.toString());
        });
        
        
    }    
}

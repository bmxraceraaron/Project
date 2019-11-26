package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import java.net.URL;
import java.util.ResourceBundle;

public class Controller implements Initializable {



    @FXML private ChoiceBox<String> typeCB;
    @FXML private  TextField codeTF;
    @FXML private  TextField nameTF;
    @FXML private  TextField brandTF;
    @FXML private  TextField quantityTF;
    @FXML private  TextField priceTF;
    @FXML private  Button addButton;
    @FXML private  Button updateButton;
    @FXML private  Button removeButton;
    @FXML private  Button printButton;
    @FXML private  Button cancelButton;
    @FXML private  Button saveButton;
    @FXML private  TextField discountTF;
    @FXML private TableView<Item> projectTV;
    @FXML TableColumn col1;
    @FXML TableColumn col2;
    @FXML TableColumn col3;
    @FXML TableColumn col4;
    @FXML TableColumn col5;
    @FXML TableColumn col6;
    @FXML TableColumn col7;
    @FXML private TabPane projectTP;
    @FXML private Tab tab1;
    @FXML private Tab tab2;
    @FXML private Label label1;
    @FXML private Label label2;
    @FXML private Label label3;
    @FXML private Label label4;
    @FXML private Label label5;
    @FXML private Label label6;
    @FXML private Label label7;


    private ObservableList<Item> items;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        projectTP.setTabClosingPolicy(TabPane.TabClosingPolicy.UNAVAILABLE);
        tab2.setDisable(true);
        col1.setCellValueFactory(new PropertyValueFactory<Item, String>("Item_Type"));
        col2.setCellValueFactory(new PropertyValueFactory<Item, Integer>("Item_Code"));
        col3.setCellValueFactory(new PropertyValueFactory<Item, String>("Item_Name"));
        col4.setCellValueFactory(new PropertyValueFactory<Item, String>("Brand"));
        col5.setCellValueFactory(new PropertyValueFactory<Item, Integer>("Quantity"));
        col6.setCellValueFactory(new PropertyValueFactory<Item, Double>("Price"));
        col7.setCellValueFactory(new PropertyValueFactory<Item, Double>("Discount"));


        typeCB.getItems().addAll("Grocery", "Clothing", "Accessories", "Electronic", "Kitchen", "Furniture");
        codeTF.setPromptText("Item Code");
        nameTF.setPromptText("Item Name");
        brandTF.setPromptText("Brand");
        quantityTF.setPromptText("Quantity");
        priceTF.setPromptText("Price");
        discountTF.setPromptText("Discount");
        cancelButton.setVisible(false);
        saveButton.setVisible(false);



    }

    @FXML
    protected void handleAddButton(ActionEvent event){
        items = FXCollections.observableArrayList();
        Item item = new Item();
        item.setItem_Type(typeCB.getValue());
        item.setItem_Code(Integer.parseInt(codeTF.getText()));
        item.setItem_Name(nameTF.getText());
        item.setBrand(brandTF.getText());
        item.setQuantity(Integer.parseInt(quantityTF.getText()));
        item.setPrice(Double.parseDouble(priceTF.getText()));
        item.setDiscount(Double.parseDouble(discountTF.getText()));
        items.add(item);
        projectTV.getItems().add(item);
        codeTF.clear();
        nameTF.clear();
        brandTF.clear();
        quantityTF.clear();
        priceTF.clear();
        discountTF.clear();

    }

    @FXML
    protected void handleRemoveButton(ActionEvent event){
        try {
            Item selectedItem = projectTV.getSelectionModel().getSelectedItem();
            projectTV.getItems().remove(selectedItem);
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No record selected for deleting");
            alert.showAndWait();
        }

    }

    @FXML
    protected void handleUpdateButton(ActionEvent event){
        addButton.setDisable(true);
        removeButton.setDisable(true);
        updateButton.setDisable(true);
        printButton.setDisable(true);

        saveButton.setVisible(true);
        cancelButton.setVisible(true);
        typeCB.setVisible(false);
        codeTF.setVisible(false);
        nameTF.setVisible(false);
        brandTF.setVisible(false);
        discountTF.setVisible(false);
        try {
            Item selectedItem = projectTV.getSelectionModel().getSelectedItem();
        }
        catch(Exception e){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error Dialog");
            alert.setHeaderText(null);
            alert.setContentText("No record selected for updating");
            alert.showAndWait();
        }
    }

    @FXML
    protected void handlePrintButton(ActionEvent event){
        projectTP.getSelectionModel().select(tab2);
        Item thisItem = items.get(0);

        if(thisItem.getItem_Type() != "Grocery"){// This is for grocery
            if(thisItem.getItem_Type() == "Furniture" && (thisItem.getPrice()*thisItem.getQuantity()>2000)){//For Furnature
                double furnatureTotal = thisItem.getPrice()*thisItem.getQuantity();
                double taxedTotal = furnatureTotal*0.0825;
                label1.setText(Double.toString(furnatureTotal) );
                label2.setText(Double.toString(furnatureTotal*0.85));
                label3.setText(Double.toString(furnatureTotal*0.15));
                label4.setText(Double.toString(furnatureTotal*0.0825));
                label5.setText(Double.toString(furnatureTotal*0.85));
                label6.setText(Double.toString(taxedTotal+(furnatureTotal*0.85)));
                double finalTotal = taxedTotal+(furnatureTotal*0.85);
                double points = (finalTotal/1000) * 50;
                    label7.setText("You have earned: " +points+ " nif you earn 1000 points, you earn a %20 discount!");
            }
            else if(thisItem.getItem_Type() == "Furniture" && (thisItem.getPrice()*thisItem.getQuantity()<2000)){//Also for Furnature
                double furnatureTotal = thisItem.getPrice()*thisItem.getQuantity();
                double taxedTotal = furnatureTotal*0.0825;
                label1.setText(Double.toString(furnatureTotal) );
                label2.setText(Double.toString(furnatureTotal*0));
                label3.setText(Double.toString(furnatureTotal*0));
                label4.setText(Double.toString(furnatureTotal*0.0825));
                label5.setText(Double.toString(furnatureTotal));
                label6.setText(Double.toString(taxedTotal+(furnatureTotal)));
                double finalTotal = taxedTotal+(furnatureTotal*0.85);
                double points = (finalTotal/1000) * 50;
                    label7.setText("You have earned: " +points+ " if you earn 1000 points, you earn a %20 discount!");
            }
            else if(thisItem.getBrand() != "Levi's" && thisItem.getItem_Type()=="Clothing"){ //Clothes that are NOT Levi's
                double brandTotal = thisItem.getPrice()*thisItem.getQuantity();
                double discount = thisItem.getDiscount()/100;
                double discountedPrice = brandTotal-(brandTotal * discount);
                double taxedTotal = discountedPrice*0.0825;
                label1.setText(Double.toString(brandTotal));
                label2.setText(Double.toString(discountedPrice));
                label3.setText(Double.toString(brandTotal-discountedPrice));
                label4.setText(Double.toString(taxedTotal));
                label5.setText(Double.toString(discountedPrice));
                label6.setText(Double.toString(taxedTotal+discountedPrice));
                double finalTotal = taxedTotal+discountedPrice;
                double points = (finalTotal/1000) * 50;
                label7.setText("You have earned: " +points+ " if you earn 1000 points, you earn a %20 discount!");
        }
            double total = thisItem.getPrice()*thisItem.getQuantity();//Everything else
            double discount = thisItem.getDiscount()/100;
            double discountedPrice = total-(total * discount);
            double taxedTotal = discountedPrice*0.0825;
            label1.setText(Double.toString(total));
            label2.setText(Double.toString(discountedPrice));
            label3.setText(Double.toString(total-discountedPrice));
            label4.setText(Double.toString(taxedTotal));
            label5.setText(Double.toString(discountedPrice));
            label6.setText(Double.toString(taxedTotal+discountedPrice));
            double finalTotal = taxedTotal+discountedPrice;
            double points = (finalTotal/1000) * 50;
            label7.setText("You have earned: " +points+ " if you earn 1000 points, you earn a %20 discount!");

        }
        else{
            double groceryTotal = thisItem.getPrice()*thisItem.getQuantity();
            label1.setText(Double.toString(groceryTotal) );
            label2.setText(Double.toString(groceryTotal));
            label3.setText(Double.toString(0));
            label4.setText(Double.toString(0));
            label5.setText(Double.toString(groceryTotal));
            label6.setText(Double.toString(groceryTotal));
            double finalTotal = groceryTotal;
            double points = (finalTotal/1000) * 50;
                label7.setText("You have earned: " +points+ " if you earn 1000 points, you earn a %20 discount!");
        }




    }

    @FXML
    protected void handleSaveButton(ActionEvent event){
        Item selectedItem = projectTV.getSelectionModel().getSelectedItem();
        selectedItem.setPrice(Double.parseDouble(priceTF.getText()));
        selectedItem.setQuantity(Integer.parseInt(quantityTF.getText()));
        projectTV.refresh();
        typeCB.setVisible(true);
        codeTF.setVisible(true);
        nameTF.setVisible(true);
        brandTF.setVisible(true);
        discountTF.setVisible(true);
        priceTF.setVisible(true);
        quantityTF.setVisible(true);
        projectTV.setEditable(false);
        saveButton.setVisible(false);
        cancelButton.setVisible(false);
        quantityTF.clear();
        priceTF.clear();
        addButton.setDisable(false);
        removeButton.setDisable(false);
        updateButton.setDisable(false);
        printButton.setDisable(false);
    }

    @FXML
    protected void handleCancelButton(ActionEvent event){
        addButton.setDisable(false);
        removeButton.setDisable(false);
        updateButton.setDisable(false);
        printButton.setDisable(false);
        cancelButton.setVisible(false);
        saveButton.setVisible(false);
        typeCB.setVisible(true);
        codeTF.setVisible(true);
        nameTF.setVisible(true);
        brandTF.setVisible(true);
        discountTF.setVisible(true);
        priceTF.setVisible(true);
        quantityTF.setVisible(true);
        projectTV.setEditable(false);
        quantityTF.clear();
        priceTF.clear();
    }
}

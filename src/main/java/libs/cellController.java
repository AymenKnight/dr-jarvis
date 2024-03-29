package libs;

import DataClass.Drug;
import com.jfoenix.controls.JFXButton;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import model.popupMenu;

public class cellController<type>  {

    public int index;
    public IntegerProperty clicked=new SimpleIntegerProperty(0);
    public IntegerProperty MenuDispatcher = new SimpleIntegerProperty(-1);
    public IntegerProperty comboDispatcher = new SimpleIntegerProperty(-1);
    Callback<TableColumn<type, String>, TableCell<type, String>> cellFactory;
    public cellController(){
    }
    public Callback<TableColumn<type, String>, TableCell<type, String>> MCellFactory(String[] imgPath,String[]items){
        return
                new Callback<TableColumn<type, String>, TableCell<type, String>>() {
                    @Override
                    public TableCell<type,String > call(final TableColumn<type, String> param) {
                        final TableCell<type, String> cell = new TableCell<type, String>() {

                            popupMenu menu=new popupMenu(imgPath,items);

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    menu.getItems().forEach(n->n.setOnAction(event -> {
                                        index=getIndex();
                                        MenuDispatcher.set(-1);
                                        MenuDispatcher.set(menu.getItems().indexOf(n));
                                    }));
                                    setGraphic(menu);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

    }
    public Callback<TableColumn<type, String>, TableCell<type, String>> BCellFactory(JFXButton button){
        return
                new Callback<TableColumn<type, String>, TableCell<type, String>>() {
                    @Override
                    public TableCell<type,String> call(final TableColumn<type, String> param) {
                        return new TableCell<type, String>() {
                            JFXButton notice = new JFXButton();
                            {
                               notice.setText(button.getText());notice.getStyleClass().addAll(button.getStyleClass());
                            }

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    notice.setOnMouseClicked(v->{
                                        index=getIndex();
                                        clicked.setValue((clicked.getValue()+1)%2);}
                                    );
                                    setGraphic(notice);
                                    setText(null);
                                }
                            }
                        };
                    }
                };

    }
    public Callback<TableColumn<type, String>, TableCell<type, String>> CCellFactory(String dataType,String of){
        return
                new Callback<TableColumn<type, String>, TableCell<type, String>>() {
                    @Override
                    public TableCell<type,String> call(final TableColumn<type, String> param) {
                        return new TableCell<type, String>() {

                            // popupMenu menu=new popupMenu(new String[]{"dr/image/trash_24px.png", "dr/image/ball_point_pen_24px.png"},new  String[]{"Delete...","Edit..."});

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    ComboBox<String> menu=null;
                                    if(dataType.equals("drug")) {
                                        ObservableList<String> elements=null;
                                        if(of.equals("type"))
                                            elements=FXCollections.observableList(((Drug) getTableView().getItems().get(getIndex())).getType());
                                        if(of.equals("dose"))
                                            elements= FXCollections.observableList(((Drug) getTableView().getItems().get(getIndex())).getDose());

                                        menu = new ComboBox<String>(elements);


                                        // menu.getStyleClass().addall(.costume-combo);
                                    }

                                    assert menu != null;
                                    menu.getSelectionModel().select(0);
                                    ComboBox<String> finalMenu = menu;
                                    menu.setOnAction(v->{
                                        index=getIndex();
                                        comboDispatcher.set((comboDispatcher.get()+1)/2);
                                    });
                                    setGraphic(menu);
                                    setText(null);
                                    menu.getStyleClass().add("combo_box");
                                }
                            }
                        };
                    }
                };

    }
    public Callback<TableColumn<type, String>, TableCell<type, String>> subCellFactory(String dataType,String of){
        return
                new Callback<TableColumn<type, String>, TableCell<type, String>>() {
                    @Override
                    public TableCell<type,String> call(final TableColumn<type, String> param) {
                        return new TableCell<type, String>() {

                            // popupMenu menu=new popupMenu(new String[]{"dr/image/trash_24px.png", "dr/image/ball_point_pen_24px.png"},new  String[]{"Delete...","Edit..."});

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    ComboBox<String> menu=null;
                                        ObservableList<String> elements=null;
                                            elements=FXCollections.observableList(((Drug) getTableView().getItems().get(getIndex())).getDose());
                                        //ObservableList<String> dz = FXCollections.observableList(((Drug) getTableView().getItems().get(getIndex())).getType());


                                    menu = new ComboBox<>(elements);

                                    menu.getSelectionModel().select(0);
                                    setGraphic(menu);
                                    setText(null);
                                    menu.getStyleClass().add("combo_box");
                                }
                            }
                        };
                    }
                };

    }
   

}


/*
        Callback<TableColumn<drug, String>, TableCell<drug, String>> cellFactory = new Callback<TableColumn<drug, String>, TableCell<drug, String>>() {
                    @Override
                    public TableCell call(final TableColumn<drug, String> param) {
                        final TableCell<drug, String> cell = new TableCell<drug, String>() {

        popupMenu menu=new popupMenu(new String[]{"dr/image/trash_24px.png", "dr/image/ball_point_pen_24px.png"},new  String[]{"Delete...","Edit..."});

                            @Override
                            public void updateItem(String item, boolean empty) {
                                super.updateItem(item, empty);
                                if (empty) {
                                    setGraphic(null);
                                    setText(null);
                                } else {
                                    menu.getItems().get(1).setOnAction(event -> {
                                        drug drug = getTableView().getItems().get(getIndex());
                                        System.out.println(getIndex());
                                    });
                                    setGraphic(menu);
                                    setText(null);
                                }
                            }
                        };
                        return cell;
                    }
                };

 */
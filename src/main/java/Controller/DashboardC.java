package Controller;

import DataClass.Drug;
import DataClass.Patient;
import DataClass.prescriptionsHistory;
import com.jfoenix.controls.JFXButton;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import libs.requestFormer;
import model.components.recentComp;
import sun.security.krb5.internal.PAData;

import java.awt.*;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;

import static dr.FinalsVal.database;
import static dr.FinalsVal.requestP;
import static java.time.temporal.TemporalQueries.localDate;

public class DashboardC implements Initializable {


    public AnchorPane dashborad_pane;
    public Pane boxes_pane;
    public Label doctor_name_label;
    public Label date_label;
    public Label all_prec_cpt;
    public Label drug_cpt;
    public Label patient_cpt;
    public Label today_precp_cpt;
    public Pane recent_pane1;
    public JFXButton shwo_all_btn1;
    public Label date_label1;
    public Pane recent_btn_pane1;
    public GridPane recent_grid;
    boolean enough=false;
    static public final requestFormer<Patient> req=new requestFormer<>();
    @Override
    public void initialize(URL location, ResourceBundle resources) {

        Thread timer = new Thread(() -> {
            SimpleDateFormat clock = new SimpleDateFormat("k:mm:ss");
            SimpleDateFormat date = new SimpleDateFormat("d MMMMM y");
            while(!enough) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ignored) {}
                Date dateNow = new Date();
                final String time = clock.format(dateNow);
                final String dmy=date.format(dateNow);
                Platform.runLater(()-> {
                    date_label1.setText(time);
                    date_label.setText(dmy);
                });
            }
        });
        timer.start();
loadRecent();

//        recent_grid.add(new recentComp(new Patient().Patient("001", "masoud", "ouzil", LocalDate.now(), 1, LocalDate.now(), "N/D")),0,1);
    //    recent_grid.add(new recentComp(),0,1);
       // recent_grid.add(new recentComp(),0,2);
    }
    void setGridList(List<Patient> list){
        for(int i=0;i<list.size();i++)
            recent_grid.add(new recentComp(list.get(i)),0,i);
    }
void loadRecent(){
    req.onReceive(v->{
        Platform.runLater(()->all_prec_cpt.setText(database.getUUID("prescriptions")+""));
        setGridList(req.respond);
    });

    requestP.offer(req.get(3));
}
    public void pan_selected(MouseEvent mouseEvent) {


    }

    public void show_prescription(ActionEvent actionEvent) {
    }

    public void show_all_pres(ActionEvent actionEvent) {
    }
}

package sample;

import javafx.beans.property.ReadOnlyStringWrapper;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.util.Callback;
import java.util.Arrays;
import java.util.concurrent.ThreadLocalRandom;

public class Controller {

    @FXML
    private TextField textFieldA;
    @FXML
    private TextField textFieldB;
    @FXML
    private TableView table1;

    private Double[][]  arr= new Double[10][2];

    public void onButtonCalculateClick(){
        int a=Integer.parseInt(textFieldA.getText());
        int b=Integer.parseInt(textFieldB.getText());
        Double kSum=0.0;
        arr[0][0]=ThreadLocalRandom.current().nextDouble(0, 100 + 1);
        /*
        arr[0][0]=41.74342406523438;
        arr[1][0]=64.39332215245058;
        arr[2][0]=58.03188961218958;
        arr[3][0]=25.96451934393319;
        arr[4][0]=0.8103377551977098;
        arr[5][0]=96.68551565619254;
        arr[6][0]=74.19499719972521;
        arr[7][0]=58.00501336998136;
        arr[8][0]=59.27504691317262;
        arr[9][0]=18.976353605592976;
        */

        for(int i =1;i<10;i++){
            arr[i][0]=ThreadLocalRandom.current().nextDouble(0, 100 + 1);
            kSum+=arr[i-1][0];
            arr[i][1]=Math.sqrt( Math.pow(Math.cos(arr[i][0]),2)/ (  (Math.pow(a,2)+Math.pow(b,2)) -Math.sin(arr[i][0])) ) * kSum;

        }
        show();
    }

    public void show(){
        ObservableList<Double[]> observableList = FXCollections.observableArrayList();
        observableList.addAll(Arrays.asList(arr));

        table1.getColumns().clear();
        for (int i = 0; i < arr[0].length; i++) {
            int columnNumber = i;
            TableColumn tableColumn = new TableColumn();
            tableColumn.setPrefWidth(130);

            tableColumn.setCellValueFactory(
                    new Callback<TableColumn.CellDataFeatures<Double[], Double>,
                                                ObservableValue<String>>()
                    {
                        @Override
                        public ObservableValue<String> call(TableColumn.CellDataFeatures<Double[],
                                Double> p )
                        {
                            return new ReadOnlyStringWrapper(
                                    String.valueOf(p.getValue()[columnNumber])
                            );
                        }
                    } );

            table1.getColumns().add(tableColumn);
        }
        ((TableColumn)table1.getColumns().get(0)).setText("K(i)");
        ((TableColumn)table1.getColumns().get(1)).setText("Y(i)");
        table1.setItems(observableList);
    }


    public void onButtonRandomFillClick(){
        textFieldA.setText(String.valueOf(ThreadLocalRandom.current().nextInt(-10, 10 + 1)));
        textFieldB.setText(String.valueOf(ThreadLocalRandom.current().nextInt(-10, 10 + 1)));
    }

    public void onButtonClearClick(){
        textFieldA.clear();
        textFieldB.clear();
        table1.getColumns().clear();
    }

    public void onButtonExitClick()
    {
        System.exit(0);
    }
}

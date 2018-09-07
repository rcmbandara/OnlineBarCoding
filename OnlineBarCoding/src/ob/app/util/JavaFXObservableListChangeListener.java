package ob.app.util;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
 
public class JavaFXObservableListChangeListener extends Application {
     
    int count = 0;
    ObservableList observableList;
     
    @Override
    public void start(Stage primaryStage) {
         
        observableList = FXCollections.observableArrayList();
        observableList.addListener(new ListChangeListener(){
 
            @Override
            public void onChanged(ListChangeListener.Change c) {
                System.out.println("\nonChanged()");
 
                while(c.next()){
                    System.out.println("next: ");
                    if(c.wasAdded()){
                        System.out.println("- wasAdded");
                    }
                    if(c.wasPermutated()){
                        System.out.println("- wasPermutated");
                    }
                    if(c.wasRemoved()){
                        System.out.println("- wasRemoved");
                    }
                    if(c.wasReplaced()){
                        System.out.println("- wasReplaced");
                    }
                    if(c.wasUpdated()){
                        System.out.println("- wasUpdated");
                    }
                }
                 
                for(Object i : observableList){
                    System.out.println(i);
                }
            }
        });
 
        Button btnAdd = new Button();
        btnAdd.setText("Add item");
        btnAdd.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
                observableList.add(count);
                count++;
            }
        });
         
        Button btnRemove = new Button();
        btnRemove.setText("Remove item");
        btnRemove.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
                 
                int size = observableList.size();
                if(size > 0){
                    observableList.remove(size-1);
                }
            }
        });
         
        Button btnReplace = new Button();
        btnReplace.setText("Replace last item (+1)");
        btnReplace.setOnAction(new EventHandler<ActionEvent>() {
             
            @Override
            public void handle(ActionEvent event) {
                 
                int size = observableList.size();
                if(size > 0){
                    observableList.set(
                        size-1, 
                        (int)observableList.get(size-1)+1);
                }
            }
        });
         
        VBox vBox = new VBox();
        vBox.getChildren().addAll(btnAdd, btnRemove, btnReplace);
         
        StackPane root = new StackPane();
        root.getChildren().add(vBox);
         
        Scene scene = new Scene(root, 300, 250);
         
        primaryStage.setTitle("Hello World!");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
     
}
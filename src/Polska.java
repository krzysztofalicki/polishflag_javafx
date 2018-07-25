import javafx.application.Application;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import java.util.LinkedList;

public class Polska extends Application {

    StackPane root = new StackPane();
    public LinkedList<Rectangle> rectangles = new LinkedList<>();
    public int[][] table = {{0,1},{1,0}};

    public void redraw() {
        double height = root.getHeight();
        double width = root.getWidth();
        if(height<width){
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    Rectangle temp = rectangles.get(i*2+j);
                    temp.setHeight(height/2);
                    temp.setWidth(height/2);
                    temp.setX(j*height/2);
                    temp.setY(i*height/2);
                    if(table[i][j] == 0) temp.setFill(Color.WHITE);
                    else if(table[i][j] == 1) temp.setFill(Color.RED);
                    else temp.setFill(Color.BLUE);
                }
            }
        }
        else {
            for (int i = 0; i < 2; i++) {
                for (int j = 0; j < 2; j++) {
                    Rectangle temp = rectangles.get(i*2+j);
                    temp.setHeight(width/2);
                    temp.setWidth(width/2);
                    temp.setX(j*width/2);
                    temp.setY(i*width/2);
                    if(table[i][j] == 0) temp.setFill(Color.WHITE);
                    else if(table[i][j] == 1) temp.setFill(Color.RED);
                    else temp.setFill(Color.BLUE);
                }
            }
        }
    }

    public void changeColor(double x,double y) {
        double height = root.getHeight();
        double width = root.getWidth();
        if(height<width) {
            if((x<(width-height)/2)||(x>(width-height)/2+height)) ;
            else {
                double tmpx = (x-(width-height)/2)/(height/2);
                int indx = (int) tmpx;
                double tmpy = y/(height/2);
                int indy = (int) tmpy;
                table[indy][indx] = (table[indy][indx]+1)%3;
                redraw();
            }
        }
        else {
            if((y<(height-width)/2)||(y>(height-width)/2+width)) ;
            else {
                double tmpy = (y-(height-width)/2)/(width/2);
                int indy = (int) tmpy;
                double tmpx = x/(width/2);
                int indx = (int) tmpx;
                table[indy][indx] = (table[indy][indx]+1)%3;
                redraw();
            }
        }
    }

    @Override
    public void start(Stage primaryStage) {


        Scene scene = new Scene(root, 600, 600, Color.GREEN);
        primaryStage.setScene(scene);
        primaryStage.setMinHeight(150);
        primaryStage.setMinWidth(100);

        Group group = new Group();
        Rectangle rect1 = new Rectangle(0,0, scene.getWidth()/2,scene.getHeight()/2);
        rect1.setFill(Color.WHITE);
        Rectangle rect2 = new Rectangle(scene.getWidth()/2,0, scene.getWidth()/2,scene.getHeight()/2);
        rect2.setFill(Color.RED);
        Rectangle rect3 = new Rectangle(0,scene.getHeight()/2, scene.getWidth()/2,scene.getHeight()/2);
        rect3.setFill(Color.RED);
        Rectangle rect4 = new Rectangle(scene.getWidth()/2,scene.getHeight()/2, scene.getWidth()/2,scene.getHeight()/2);
        rect4.setFill(Color.WHITE);

        rectangles.add(rect1);
        rectangles.add(rect2);
        rectangles.add(rect3);
        rectangles.add(rect4);

        group.getChildren().add(rect1);
        group.getChildren().add(rect2);
        group.getChildren().add(rect3);
        group.getChildren().add(rect4);
        root.getChildren().add(group);

        scene.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                double x = event.getX();
                double y = event.getY();
                changeColor(x,y);
            }
        });

        StackPane pane = new StackPane();
        Text text = new Text("POLSKA");
        text.setFont(Font.font("Tahoma", FontWeight.NORMAL, 50));
        pane.getChildren().add(text);
        root.getChildren().add(pane);
        pane.setAlignment(Pos.BOTTOM_CENTER);

        primaryStage.show();

        root.heightProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                redraw();
            }
        });

        root.widthProperty().addListener(new InvalidationListener() {
            @Override
            public void invalidated(Observable observable) {
                redraw();
            }
        });

    }

    public static void main(String[] args) {
        launch(args);
    }
}
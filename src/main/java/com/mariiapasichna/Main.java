package com.mariiapasichna;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.mariiapasichna.paint.Board;
import com.mariiapasichna.paint.Direction;
import com.mariiapasichna.paint.DisplayDriver;
import com.mariiapasichna.platform.DisplayDriverImpl;
import com.mariiapasichna.save.BoardSave;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.*;

public class Main extends Application {
    private GraphicsContext gc;
    private Board board;
    private Direction direction;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("JavaFxSample");
        Canvas canvas = new Canvas();
        canvas.setWidth(Const.BOARD_WIDTH);
        canvas.setHeight(Const.BOARD_HEIGHT);
        BorderPane group = new BorderPane(canvas);
        Scene scene = new Scene(group);
        primaryStage.setScene(scene);
        primaryStage.show();
        gc = canvas.getGraphicsContext2D();

        DisplayDriver displayDriver = new DisplayDriverImpl(gc);
        board = new Board(displayDriver);
        drawFrame();
        scene.setOnMouseClicked(this::mouseClick);
        scene.setOnKeyPressed(this::keyPress);
    }

    private void keyPress(KeyEvent keyEvent) {
        drawFrame();
        switch (keyEvent.getCode()) {
            case DIGIT1:
                board.addCircle();
                break;
            case DIGIT2:
                board.addSquare();
                break;
            case DIGIT3:
                board.addTriangle();
                break;
            case DIGIT4:
                board.addHexagon();
                break;
            case UP:
                direction = Direction.UP;
                board.move(direction);
                break;
            case DOWN:
                direction = Direction.DOWN;
                board.move(direction);
                break;
            case RIGHT:
                direction = Direction.RIGHT;
                board.move(direction);
                break;
            case LEFT:
                direction = Direction.LEFT;
                board.move(direction);
                break;
            case F1:
                board.fill();
                break;
            case F2:
                board.grow();
                break;
            case F3:
                board.shrink();
                break;
            case F4:
                board.combineShapesToList();
                break;
            case F5:
                board.combineShapes();
                break;
            case F6:
                board.delete();
                break;
            case F7:
                board.cloneShape();
                break;
            case F8:
                save(Const.FILE_NAME);
                break;
            case F9:
                download(Const.FILE_NAME);
                break;
            case ESCAPE:
                board.clear();
                break;
        }
        board.drawShape();
    }

    private void mouseClick(MouseEvent mouseEvent) {
        board.setX(mouseEvent.getX());
        board.setY(mouseEvent.getY());
        board.Active();
    }

    private void drawFrame() {
        gc.clearRect(0, 0, Const.BOARD_WIDTH, Const.BOARD_HEIGHT);
        gc.setFill(Const.BOARD_COLOR);
        gc.fillRect(0, 0, Const.BOARD_WIDTH, Const.BOARD_HEIGHT);
        gc.setFill(Color.LIME);
        gc.fillText("create: 1-4; active: mouse click; fill: F1; inc: F2; dec: F3; combine: F4->F5; delete: F6; clone: F7; save F8; download: F9; clear: esc", 2, 15);
        gc.fillText("move: UP, DOWN, LEFT, RIGHT", 600, 30);
    }

    private void save(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            Gson gson = new GsonBuilder().create();
            writer.write(gson.toJson(board.makeBoardSave()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void download(String fileName) {
        try (BufferedReader br = new BufferedReader(new FileReader(fileName))) {
            String download;
            while ((download = br.readLine()) != null) {
                Gson gson = new GsonBuilder().create();
                BoardSave boardSave = gson.fromJson(download, BoardSave.class);
                board.downloadBoardSave(boardSave);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
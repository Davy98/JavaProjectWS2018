package com.GUI;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

import java.util.ArrayList;

public class InputMatrix {
    //Default matrix size
    static int row = 6 , col = 7 ;
    static int totalRow = row + col;
    static boolean[] state;

    //Text field matrix to hold the cost between cities and generators, cities
    static TextField[][] tf;
    static private ArrayList<ToggleSwitch> switches;

    public static GridPane matrixDisplay(){

        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setPadding( new Insets(20, 20, 20, 20));
        tf = new TextField[totalRow][col];
        int num = 0;

        for(int i = 0; i < totalRow+1; i++){
            for(int j = 0;  j < col+1; j++){
                //Generate title rows and columns
                if(i == 0 && j == 0){
                    Label x = new Label("G\\D");
                    GridPane.setConstraints(x,j,i);
                    grid.getChildren().add(x);
                }
                else if(i == 0){
                    Label lb = new Label("D"+j);
                    lb.setAlignment(Pos.CENTER);
                    GridPane.setConstraints(lb,j,i);
                    grid.getChildren().add(lb);
                }
                else  if(j == 0 ){
                    if(i < row+1) {
                        Label lb = new Label("G" + i);
                        lb.setAlignment(Pos.CENTER);
                        GridPane.setConstraints(lb, j, i);
                        grid.getChildren().add(lb);
                    }else{
                        num += 1;
                        Label lb = new Label("D" + num);
                        lb.setAlignment(Pos.CENTER);
                        GridPane.setConstraints(lb, j, i);
                        grid.getChildren().add(lb);
                    }
                }

                //Then generate grid text field
                else{
                    tf[i-1][j-1] = new TextField();
                    tf[i-1][j-1].setPrefHeight(50);
                    tf[i-1][j-1].setPrefWidth(50);
                    tf[i-1][j-1].setAlignment(Pos.CENTER);
                    tf[i-1][j-1].setText("");
                    GridPane.setConstraints(tf[i-1][j-1],j,i);
                    //Handle invalid input (ie. Sysmbol, text..)
                    //Receive only integer

                    grid.getChildren().add(tf[i-1][j-1]);
                }
            }
        }
        return grid;
    }

    public static GridPane generatorState(){
        switches = new ArrayList<>();
        GridPane grid = new GridPane();
        grid.setVgap(5);
        grid.setHgap(5);
        grid.setPadding( new Insets(10, 10, 10, 10));

        //Create a list of ToggleSwitch

        for(int i = 0; i < (totalRow-col)+1; i++){
            for(int j = 0;  j < 2; j++){
                //Generate title rows and columns
                if(i == 0 && j == 0){
                    Label x = new Label("Gn");
                    GridPane.setConstraints(x,j,i);
                    grid.getChildren().add(x);
                }
                else if(i == 0 || j == 0){
                    if(i != 0 && j == 0){
                        Label lb = new Label("G"+i+"  ");
                        GridPane.setConstraints(lb,j,i);
                        grid.getChildren().add(lb);
                    }
                    else if(i == 0 && j == 1){
                        Label onOff = new Label("State");
                        onOff.setPadding( new Insets(5, 30, 5, 30));
                        onOff.setAlignment(Pos.CENTER);
                        GridPane.setConstraints(onOff,j,i);
                        grid.getChildren().add(onOff);
                    }
                }
                //Then generate grid text field
                else{

                    Pane switchWrap = new Pane();
                    ToggleSwitch tSwitch = new ToggleSwitch();
                    switches.add(tSwitch); //Add new switch to Array list of switches

                    switchWrap.getChildren().add(tSwitch);
                    switchWrap.setPadding(new Insets(10,0,0,0));
                    GridPane.setConstraints(switchWrap,j,i);
                    grid.getChildren().add(switchWrap);
                }
            }
        }

        return grid;
    }

    public static boolean[] getState(){
        state = new boolean[totalRow-col];
        int i = 0;
        for(ToggleSwitch tSwitch : switches){
            state[i] = tSwitch.switchOnProperty().get();
            i++;
        }
        return state;
    }

    public static double[][] getMatrixValue(){
        double intMatrix[][] = new double[totalRow][col];

        for(int i = 0; i < totalRow; i++){
            for(int j = 0;  j < col; j++){
                if(tf[i][j].getText().length() == 0) intMatrix[i][j] = 0;
                else intMatrix[i][j] = Double.parseDouble(tf[i][j].getText());
            }
        }
        return intMatrix;
    }

    public static String matrixToText(double[][] matrix, int row, int col){
        String text = new String();
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                text +=Double.toString(matrix[i][j])+" ";
            }
            text+="\n";
        }
        return text;
    }

    public static void clearMatrixValue(){
        for(int i = 0; i < totalRow; i++){
            for(int j = 0;  j < col; j++){
                tf[i][j].setText("");
            }
        }
    }

}

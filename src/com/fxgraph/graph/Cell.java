package com.fxgraph.graph;
import java.util.ArrayList;
import java.util.List;

import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.Node;
import javafx.scene.layout.Pane;


public class Cell extends Pane {

    String cellId;

    List<Cell> children = new ArrayList<>();
    List<Cell> parents = new ArrayList<>();

    Node shape;
    //String text;

    public Cell(String cellId) {
        this.cellId = cellId;
    }

    public void addCellChild(Cell cell) {
        children.add(cell);
    }

    public List<Cell> getCellChildren() {
        return children;
    }

    public void addCellParent(Cell cell) {
        parents.add(cell);
    }

    public List<Cell> getCellParents() {
        return parents;
    }

    public void removeCellChild(Cell cell) {
        children.remove(cell);
    }

    public void setView(Node shape, String cellId) {
        Text text = new Text(10,25,cellId);
        text.setFont(new Font(16));
        text.setStyle("-fx-font-weight: bold");

        this.shape = shape;

        getChildren().addAll(shape,text);

    }

    public void removeView(Node shape, String cellId) {
        Text text = new Text(10,25,cellId);
        text.setFont(new Font(16));
        text.setStyle("-fx-font-weight: bold");

        this.shape = shape;

        getChildren().removeAll(shape,text);

    }

    public Node getView() {
        return this.shape;
    }

    public String getCellId() {
        return cellId;
    }

    public void setState(){}

    public boolean getState(){return true;}
}

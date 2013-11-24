/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanaldiyar.projects.engender.lang;

import java.util.Map;

/**
 *
 * @author kazim
 */
public class BooleanExpressionCommand implements Command {

    private Command left, right;
    private BooleanOperation booleanOperation;

    public void setLeft(Command left) {
        this.left = left;
    }

    public void setRight(Command right) {
        this.right = right;
    }

    public void setBooleanOperation(BooleanOperation booleanOperation) {
        this.booleanOperation = booleanOperation;
    }
    
    @Override
    public String applyData(Map<String, Object> data) {
        return null;
    }

}

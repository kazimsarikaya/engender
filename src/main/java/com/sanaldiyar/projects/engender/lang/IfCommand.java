/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanaldiyar.projects.engender.lang;

import java.util.ArrayList;
import java.util.Map;

/**
 *
 * @author kazim
 */
public class IfCommand implements Command {

    private BooleanExpressionCommand booleanExpressionCommand;
    private ArrayList<Command> innerCommands;

    public void setBooleanExpression(BooleanExpressionCommand booleanExpressionCommand) {
        this.booleanExpressionCommand = booleanExpressionCommand;
    }

    public void setInnerCommands(ArrayList<Command> innerCommands) {
        this.innerCommands = innerCommands;
    }

    @Override
    public String applyData(Map<String, Object> data) {
        return "";
    }

}

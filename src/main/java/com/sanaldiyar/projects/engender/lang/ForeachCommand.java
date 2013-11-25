/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sanaldiyar.projects.engender.lang;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author kazim
 */
public class ForeachCommand implements Command {

    private String variableName;
    private PrintVariableCommand printVariableCommand;
    private ArrayList<Command> innerCommands;

    public void setInnerCommands(ArrayList<Command> innerCommands) {
        this.innerCommands = innerCommands;
    }

    public void setPrintVariableCommand(PrintVariableCommand printVariableCommand) {
        this.printVariableCommand = printVariableCommand;
    }

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public Object applyData(Map<String, Object> data) {
        Object titems = printVariableCommand.applyData(data);
        ArrayList<Object> datas = new ArrayList<>();
        if (titems instanceof Iterable) {
            for (Object item : (Iterable) titems) {
                datas.add(applyItemData(item, data));
            }
        } else {
            for (Object item : (Object[]) titems) {
                datas.add(applyItemData(item, data));
            }
        }
        return datas;
    }

    private Object applyItemData(Object item, Map<String, Object> data) {
        Map<String, Object> ndata = new HashMap<>(data);
        ndata.put(variableName, item);
        ArrayList<Object> datas = new ArrayList<>();
        for (Command cmd : innerCommands) {
            datas.add(cmd.applyData(ndata));
        }
        return datas;
    }

}

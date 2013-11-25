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
public class CommandList implements Command {

    private ArrayList<Command> commands;

    public void setCommands(ArrayList<Command> commands) {
        this.commands = commands;
    }

    @Override
    public Object applyData(Map<String, Object> data) {
        ArrayList<Object> datas = new ArrayList<>();
        for (Command cmd : commands) {
            datas.add(cmd.applyData(data));
        }
        return (Iterable<Object>) datas;
    }

}

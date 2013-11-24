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
public class PrintVariableCommand implements Command {

    private String variableName;

    public void setVariableName(String variableName) {
        this.variableName = variableName;
    }

    @Override
    public String applyData(Map<String, Object> data) {
        String[] split = variableName.split("\\.");
        Map tmp = data;
        int i = 0;
        while (i < split.length - 1) {
            if (tmp.containsKey(split[i])) {
                break;
            }
            tmp = (Map) tmp.get(split[i]);
            i++;
        }
        return tmp.get(split[i]).toString();
    }

}

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
public class ConstantCommand implements Command {

    private String stringValue = null;
    private Number numberValue = null;

    public void setNumberValue(Number numberValue) {
        this.numberValue = numberValue;
    }

    public void setStringValue(String stringValue) {
        this.stringValue = stringValue;
    }

    public Number getNumberValue() {
        return numberValue;
    }

    public String getStringValue() {
        return stringValue;
    }

    @Override
    public String applyData(Map<String, Object> data) {
        return (String) (stringValue == null ? numberValue : stringValue);
    }

}

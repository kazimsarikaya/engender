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
        this.stringValue = this.stringValue.replace("\\n", "\n");
        this.stringValue = this.stringValue.replace("\\r", "\r");
        this.stringValue = this.stringValue.replace("\\t", "\t");
    }

    @Override
    public Object applyData(Map<String, Object> data) {
        return stringValue == null ? numberValue : stringValue;
    }

}

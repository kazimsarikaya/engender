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
    private BooleanMerger booleanMerger;
    private BooleanExpressionCommand nextCommand;

    public void setLeft(Command left) {
        this.left = left;
    }

    public void setRight(Command right) {
        this.right = right;
    }

    public void setBooleanMerger(BooleanMerger booleanMerger) {
        this.booleanMerger = booleanMerger;
    }

    public void setNextCommand(BooleanExpressionCommand nextCommand) {
        this.nextCommand = nextCommand;
    }

    public void setBooleanOperation(BooleanOperation booleanOperation) {
        this.booleanOperation = booleanOperation;
    }

    @Override
    public Object applyData(Map<String, Object> data) {
        Comparable lleft, lright;
        lleft = (Comparable) left.applyData(data);
        lright = (Comparable) right.applyData(data);
        boolean res = false;
        switch (booleanOperation) {
            case BIG:
                res = lleft.compareTo(lright) > 0;
                break;
            case BIGNEQ:
                res = lleft.compareTo(lright) > 0 || lleft.compareTo(lright) == 0;
                break;
            case EQUALS:
                res = lleft.compareTo(lright) == 0;
                break;
            case LESS:
                res = lleft.compareTo(lright) < 0;
                break;
            case LESSNEQ:
                res = lleft.compareTo(lright) < 0 || lleft.compareTo(lright) == 0;
                break;
            case NOTEQUALS:
                res = lleft.compareTo(lright) != 0;
                break;
        }
        BooleanExpressionCommand tmp = nextCommand;
        while (tmp != null) {
            if (booleanMerger == BooleanMerger.AND) {
                res &= (Boolean) tmp.applyData(data);
            } else {
                res |= (Boolean) tmp.applyData(data);
            }
            tmp = tmp.nextCommand;
        }
        return Boolean.valueOf(res);
    }

}

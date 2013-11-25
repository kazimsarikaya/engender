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
public class MathCommand implements Command {

    private Command right, left;
    private MathOp mathOp;

    public void setLeft(Command left) {
        this.left = left;
    }

    public void setRight(Command right) {
        this.right = right;
    }

    public void setMathOp(MathOp mathOp) {
        this.mathOp = mathOp;
    }

    @Override
    public Object applyData(Map<String, Object> data) {
        Number lleft = (Number) left.applyData(data);
        Number lright = (Number) right.applyData(data);
        double res = 0;
        switch (mathOp) {
            case PLUS:
                res = lleft.doubleValue() + lright.doubleValue();
                break;
            case MINUS:
                res = lleft.doubleValue() - lright.doubleValue();
                break;
            case TIMES:
                res = lleft.doubleValue() * lright.doubleValue();
                break;
            case DIVIDE:
                res = lleft.doubleValue() / lright.doubleValue();
                break;
        }
        if (lleft instanceof Integer && lright instanceof Integer) {
            return Integer.valueOf((int) res);
        } else if ((lleft instanceof Integer && lright instanceof Long) || (lleft instanceof Long && lright instanceof Integer)  || (lleft instanceof Long && lright instanceof Long)) {
            return Long.valueOf((long) res);
        } else {
            return Double.valueOf(res);
        }
    }

}

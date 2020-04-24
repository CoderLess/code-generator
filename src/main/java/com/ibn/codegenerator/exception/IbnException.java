package com.ibn.codegenerator.exception;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @version 1.0
 * @description:
 * @projectName：code-generator
 * @see: com.ibn.codegenerator
 * @author： RenBin
 * @createTime：2020/4/24 10:37
 */
@Data
@AllArgsConstructor
public class IbnException extends Exception {
    private static final long serialVersionUID = -6826437542331141808L;
    private String msg;

    public IbnException(String msg, Throwable e) {
        super(msg, e);
        this.msg = msg;
    }


}

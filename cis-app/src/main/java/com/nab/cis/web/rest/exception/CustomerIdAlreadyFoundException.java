package com.nab.cis.web.rest.exception;

import org.zalando.problem.AbstractThrowableProblem;
import org.zalando.problem.Status;

public class CustomerIdAlreadyFoundException extends AbstractThrowableProblem {

    private static final long serialVersionUID = 1L;

    public CustomerIdAlreadyFoundException() {
        super(null, "Customer Id Already Found", Status.BAD_REQUEST);
    }
}


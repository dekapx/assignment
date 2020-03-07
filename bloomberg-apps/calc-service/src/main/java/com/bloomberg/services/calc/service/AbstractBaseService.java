package com.bloomberg.services.calc.service;

import com.google.common.base.Preconditions;

abstract class AbstractBaseService {
    protected void validatePositiveNumber(final int number) {
        final String errorMessage = "Argument must not be zero or a negative number.";
        Preconditions.checkArgument(number > 0, errorMessage);
    }
}

package com.bloomberg.services.calc.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NegativeOrZero;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MultiplyRequest {
    @NegativeOrZero(message = "Argument must not be a negative number and greater than zero")
    private int firstArg;
    @Min(value = 1, message = "Please enter a valid number")
    private int secondArg;
}

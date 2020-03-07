package com.bloomberg.services.network.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MultiplyRequest {
    private int firstArg;
    private int secondArg;
}

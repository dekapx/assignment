package com.bloomberg.services.calc.service;

import com.bloomberg.services.calc.model.MultiplyRequest;
import com.bloomberg.services.calc.model.MultiplyResponse;

public interface CalcService {
    MultiplyResponse multiply(MultiplyRequest request);
}

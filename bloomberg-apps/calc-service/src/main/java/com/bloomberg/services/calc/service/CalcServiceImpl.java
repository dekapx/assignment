package com.bloomberg.services.calc.service;

import com.bloomberg.services.calc.model.MultiplyRequest;
import com.bloomberg.services.calc.model.MultiplyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalcServiceImpl implements CalcService {
    @Override
    public MultiplyResponse multiply(final MultiplyRequest request) {
        log.info("multiply operation invoked with arg {} & {}", request.getFirstArg(), request.getSecondArg());
        return new MultiplyResponse(request.getFirstArg() * request.getSecondArg());
    }
}

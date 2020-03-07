package com.bloomberg.services.calc.service;

import com.bloomberg.services.calc.model.MultiplyRequest;
import com.bloomberg.services.calc.model.MultiplyResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class CalcServiceImpl extends AbstractBaseService implements CalcService {
    @Override
    public MultiplyResponse multiply(final MultiplyRequest request) {
        validatePositiveNumber(request.getFirstArg());
        validatePositiveNumber(request.getSecondArg());
        final int result = request.getFirstArg() * request.getSecondArg();
        log.info("Perform multiply operation with {} & {} and return {}.", request.getFirstArg(), request.getSecondArg(), result);
        return MultiplyResponse.builder().result(result).build();
    }
}

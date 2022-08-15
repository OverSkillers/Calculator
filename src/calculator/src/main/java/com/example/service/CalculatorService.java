package com.example.service;

import com.example.model.Operands;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.MathContext;
import java.math.RoundingMode;

@Service
public class CalculatorService {
    private final static Logger logger = LoggerFactory.getLogger(CalculatorService.class);
    private final static String SUM_OPERATION = "sum";
    private final static String DIV_OPERATION = "div";
    private final static String MULT_OPERATION = "mult";
    private final static String SUB_OPERATION = "sub";

    public String calculate(Operands operands) {
        String calculatedValue;


        BigDecimal result = null;
        BigDecimal decimalA = new BigDecimal(operands.getA());
        BigDecimal decimalB = new BigDecimal(operands.getB());

        if (SUM_OPERATION.equals(operands.getOperation())) {
            result = decimalA.add(decimalB);
            calculatedValue = result.toString();
        } else if (DIV_OPERATION.equals(operands.getOperation())) {
            result = decimalA.divide(decimalB,2,RoundingMode.HALF_EVEN);
            //result.round(new MathContext(1, RoundingMode.HALF_EVEN));
            calculatedValue = result.toString();
        } else if (MULT_OPERATION.equals(operands.getOperation())) {
            result = decimalA.multiply(decimalB);
            calculatedValue = result.toString();
        } else if (SUB_OPERATION.equals(operands.getOperation())) {
            result = decimalA.subtract(decimalB);
            calculatedValue = result.toString();
        } else {
            logger.error("Unrecognized/unsupported operation: {}", operands.getOperation());
            return "invalid";
        }

        logger.info("Calculated {} {} {}: {}",
                operands.getA(),
                operands.getOperation(),
                operands.getB(),
                calculatedValue);

        return calculatedValue;
    }
}

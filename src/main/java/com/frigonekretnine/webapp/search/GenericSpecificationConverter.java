package com.frigonekretnine.webapp.search;

import lombok.NoArgsConstructor;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.jpa.domain.Specification;

import java.io.Serializable;
import java.util.Deque;

@NoArgsConstructor
public class GenericSpecificationConverter implements Converter<String, Specification<?>> {

    @Override
    public Specification<?> convert(String str) {
        if (str.isEmpty() || "undefined".equals(str)) {
            return null;
        }

        try {
            Deque<?> deque = new CriteriaParser().parse(str);
            GenericSpecificationBuilder<? extends Serializable> specificationBuilder = new GenericSpecificationBuilder<>();
            return specificationBuilder.build(deque, GenericSpecification::new);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}

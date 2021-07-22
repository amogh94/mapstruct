/*
 * Copyright MapStruct Authors.
 *
 * Licensed under the Apache License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package org.mapstruct.ap.spi;

import java.util.Arrays;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * Applies case transformation to the source enum
 *
 * @author jpbassinello
 * @since 1.5
 */
public class CaseEnumTransformationStrategy implements EnumTransformationStrategy {

    private static final String UPPER = "upper";
    private static final String LOWER = "lower";
    private static final String CAPITAL = "capital";
    private static final String CASE_ENUM_TRANSFORMATION_STRATEGIES = UPPER + ", " + LOWER + ", " + CAPITAL;

    @Override
    public String getStrategyName() {
        return "case";
    }

    @Override
    public String transform(String value, String configuration) {
        switch ( configuration.toLowerCase() ) {
            case UPPER:
                return value.toUpperCase( Locale.ROOT );
            case LOWER:
                return value.toLowerCase( Locale.ROOT );
            case CAPITAL:
                return capitalize( value );
            default:
                throw new IllegalArgumentException(
                        "Unexpected configuration for enum case transformation: " + configuration +
                                ". Allowed values: " + CASE_ENUM_TRANSFORMATION_STRATEGIES);
        }
    }

    private static String capitalize(String value) {
        return Arrays.stream( value.split( "_" ) )
            .map( CaseEnumTransformationStrategy::upperCaseFirst )
            .collect( Collectors.joining( "_" ) );
    }

    private static String upperCaseFirst(String value) {
        char[] array = value.toCharArray();
        array[0] = Character.toUpperCase( array[0] );
        for ( int i = 1; i < array.length; i++ ) {
            array[i] = Character.toLowerCase( array[i] );
        }
        return new String( array );
    }

}

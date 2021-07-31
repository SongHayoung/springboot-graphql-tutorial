package com.sumfi.graphql.scalars;

import graphql.language.StringValue;
import graphql.scalars.util.Kit;
import graphql.schema.*;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.TemporalAccessor;
import java.util.function.Function;

/**
 * graphql 17.0.0 이상부터 GraphQLScalarType constructor가 private로 변경될 예정.
 */
@Deprecated
public class DateScalar extends GraphQLScalarType {
    public DateScalar(final DateTimeFormatter dateFormatter) {
        super("Date", "Sumfi Custom Date Scalar With Own Formatter", new Coercing<LocalDate, String>() {
            public String serialize(Object input) throws CoercingSerializeException {
                Object temporalAccessor;
                if (input instanceof TemporalAccessor) {
                    temporalAccessor = (TemporalAccessor)input;
                } else {
                    if (!(input instanceof String)) {
                        throw new CoercingSerializeException("Expected a 'String' or 'java.time.temporal.TemporalAccessor' but was '" + Kit.typeName(input) + "'.");
                    }

                    temporalAccessor = this.parseLocalDate(input.toString(), CoercingSerializeException::new);
                }

                try {
                    return dateFormatter.format((TemporalAccessor)temporalAccessor);
                } catch (DateTimeException var4) {
                    throw new CoercingSerializeException("Unable to turn TemporalAccessor into full date because of : '" + var4.getMessage() + "'.");
                }
            }

            public LocalDate parseValue(Object input) throws CoercingParseValueException {
                Object temporalAccessor;
                if (input instanceof TemporalAccessor) {
                    temporalAccessor = (TemporalAccessor)input;
                } else {
                    if (!(input instanceof String)) {
                        throw new CoercingParseValueException("Expected a 'String' or 'java.time.temporal.TemporalAccessor' but was '" + Kit.typeName(input) + "'.");
                    }

                    temporalAccessor = this.parseLocalDate(input.toString(), CoercingParseValueException::new);
                }

                try {
                    return LocalDate.from((TemporalAccessor)temporalAccessor);
                } catch (DateTimeException var4) {
                    throw new CoercingParseValueException("Unable to turn TemporalAccessor into full date because of : '" + var4.getMessage() + "'.");
                }
            }

            public LocalDate parseLiteral(Object input) throws CoercingParseLiteralException {
                if (!(input instanceof StringValue)) {
                    throw new CoercingParseLiteralException("Expected AST type 'StringValue' but was '" + Kit.typeName(input) + "'.");
                } else {
                    return this.parseLocalDate(((StringValue)input).getValue(), CoercingParseLiteralException::new);
                }
            }

            private LocalDate parseLocalDate(String s, Function<String, RuntimeException> exceptionMaker) {
                try {
                    TemporalAccessor temporalAccessor = dateFormatter.parse(s);
                    return LocalDate.from(temporalAccessor);
                } catch (DateTimeParseException var4) {
                    throw (RuntimeException)exceptionMaker.apply("Invalid RFC3339 full date value : '" + s + "'. because of : '" + var4.getMessage() + "'");
                }
            }
        });
    }
}

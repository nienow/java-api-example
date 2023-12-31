package dev.randombits.api.config;


import graphql.language.StringValue;
import graphql.schema.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.graphql.execution.RuntimeWiringConfigurer;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@Configuration
public class DateTimeScalarConfiguration {


    @Bean
    public RuntimeWiringConfigurer runtimeWiringConfigurer() {
        return wiringBuilder -> wiringBuilder.scalar(GraphQLScalarType.newScalar()
                .name("DateTime")
                .description("DateTime")
                .coercing(new Coercing<LocalDateTime, String>() {
                    @Override
                    public String serialize(final Object dataFetcherResult) {
                        if (dataFetcherResult instanceof LocalDateTime) {
                            return dataFetcherResult.toString();
                        } else {
                            throw new CoercingSerializeException("Expected a LocalDate object.");
                        }
                    }

                    @Override
                    public LocalDateTime parseValue(final Object input) {
                        try {
                            if (input instanceof String) {
                                return LocalDateTime.parse((String) input);
                            } else {
                                throw new CoercingParseValueException("Expected a String");
                            }
                        } catch (DateTimeParseException e) {
                            throw new CoercingParseValueException(String.format("Not a valid date: '%s'.", input), e
                            );
                        }
                    }

                    @Override
                    public LocalDateTime parseLiteral(final Object input) {
                        if (input instanceof StringValue) {
                            try {
                                return LocalDateTime.parse(((StringValue) input).getValue());
                            } catch (DateTimeParseException e) {
                                throw new CoercingParseLiteralException(e);
                            }
                        } else {
                            throw new CoercingParseLiteralException("Expected a StringValue.");
                        }
                    }
                }).build());
    }
  
}

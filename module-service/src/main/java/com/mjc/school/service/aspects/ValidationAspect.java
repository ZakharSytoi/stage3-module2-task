package com.mjc.school.service.aspects;

import com.mjc.school.repository.impl.AuthorRepository;
import com.mjc.school.service.dto.AuthorDtoRequest;
import com.mjc.school.service.dto.NewsDtoRequest;
import com.mjc.school.service.exceptions.ValidatorException;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import static com.mjc.school.service.exceptions.ServiceErrorCode.*;
import static com.mjc.school.service.exceptions.ServiceErrorCode.VALIDATE_STRING_LENGTH;

@Component
@Aspect
public class ValidationAspect {
    private static final String AUTHOR_ID = "Author id";
    private static final String AUTHOR_NAME = "Author name";
    private static final Integer AUTHOR_NAME_MIN_LENGTH = 3;
    private static final Integer AUTHOR_NAME_MAX_LENGTH = 15;
    private static final String NEWS_ID = "News id";
    private static final String NEWS_CONTENT = "News content";
    private static final String NEWS_TITLE = "News title";
    private static final Integer NEWS_CONTENT_MIN_LENGTH = 5;
    private static final Integer NEWS_CONTENT_MAX_LENGTH = 255;
    private static final Integer NEWS_TITLE_MIN_LENGTH = 5;
    private static final Integer NEWS_TITLE_MAX_LENGTH = 30;
    private final AuthorRepository authorRepository;

    @Autowired
    private ValidationAspect(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Before("Pointcuts.authorServiceUpdateAndCreateMethods()")
    public void validateAuthorDtoAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof AuthorDtoRequest dtoRequest) {
            validateString(dtoRequest.name(),
                    AUTHOR_NAME,
                    AUTHOR_NAME_MIN_LENGTH,
                    AUTHOR_NAME_MAX_LENGTH);
        }
    }
    @Before("Pointcuts.authorDtoUpdate()")
    public void validateAuthorCreateDtoAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof AuthorDtoRequest dtoRequest) {
            validateNumber(dtoRequest.id(), AUTHOR_ID);
        }
    }

    @Before("Pointcuts.readDeleteByID()")
    public void validateNumberAdvice(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof Long id) {
            if (id < 1) {
                throw new ValidatorException(
                        String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), AUTHOR_ID, AUTHOR_ID, id));
            }
        } else throw new ValidatorException(
                String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), AUTHOR_ID, AUTHOR_ID, null));
    }

    @Before("Pointcuts.newsServiceUpdateAndCreateMethods()")
    public void validateNewsDto(JoinPoint joinPoint) {
        Object[] args = joinPoint.getArgs();
        if (args[0] instanceof NewsDtoRequest dtoRequest) {
            validateString(dtoRequest.title(),
                    NEWS_TITLE,
                    NEWS_TITLE_MIN_LENGTH,
                    NEWS_TITLE_MAX_LENGTH);
            validateString(dtoRequest.content(),
                    NEWS_CONTENT,
                    NEWS_CONTENT_MIN_LENGTH,
                    NEWS_CONTENT_MAX_LENGTH);
            validateAuthorId(dtoRequest.authorId());
        }
    }

    @Before("Pointcuts.newsDtoUpdate()")
    public void validateNewsId(JoinPoint joinPoint) {
        if (joinPoint.getArgs()[0] instanceof NewsDtoRequest dtoRequest) {
            validateNumber(dtoRequest.id(), NEWS_ID);
        } else throw new ValidatorException(
                String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), NEWS_ID, NEWS_ID, null));
    }

    private void validateAuthorId(Long authorId) {
        validateNumber(authorId, AUTHOR_ID);
        if (!authorRepository.existById(authorId)) {
            throw new ValidatorException(String.format(AUTHOR_ID_DOES_NOT_EXIST.getMessage(), authorId));
        }
    }

    private void validateNumber(Long id, String parameter) {
        if (id == null || id < 1) {
            throw new ValidatorException(
                    String.format(VALIDATE_NEGATIVE_OR_NULL_NUMBER.getMessage(), parameter, parameter, id));
        }
    }

    private void validateString(String value, String parameter, int minLength, int maxLength) {
        if (value == null) {
            throw new ValidatorException(
                    String.format(VALIDATE_NULL_STRING.getMessage(), parameter, parameter));
        }
        if (value.trim().length() < minLength || value.trim().length() > maxLength) {
            throw new ValidatorException(
                    String.format(
                            VALIDATE_STRING_LENGTH.getMessage(),
                            parameter,
                            minLength,
                            maxLength,
                            parameter,
                            value));
        }
    }
}

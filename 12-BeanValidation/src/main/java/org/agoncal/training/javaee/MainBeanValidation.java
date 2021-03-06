package org.agoncal.training.javaee;

import org.agoncal.training.javaee.model.Book;
import org.agoncal.training.javaee.model.Language;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.enterprise.inject.Vetoed;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.sql.SQLException;
import java.util.Set;

/**
 * @author Antonio Goncalves
 *         Training - Beginning with The Java EE 7 Platform
 *         http://www.antoniogoncalves.org
 *         --
 */
@Vetoed
public class MainBeanValidation {

    // ======================================
    // =             Attributes             =
    // ======================================

    private static final Logger logger = LogManager.getLogger(MainBeanValidation.class);

    private static ValidatorFactory vf;
    private static Validator validator;

    // ======================================
    // =          Business methods          =
    // ======================================

    public static void main(String[] args) throws SQLException, ClassNotFoundException {

        // Gets a Validator
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();

        // Creates a book
        // TODO Change the values to invalid ones and see what is displayed
        Book book = new Book(4044L, "H2G2", 12.5F, "Best IT Scifi Book", 247, true, Language.ENGLISH);

        // Validates the book
        Set<ConstraintViolation<Book>> constraints = validator.validate(book);
        logger.info("Number of violated constraints : " + constraints.size());
        for (ConstraintViolation<Book> violation : constraints) {
            logger.info("   Bean     : " + violation.getRootBeanClass().getSimpleName());
            logger.info("   Property : " + violation.getPropertyPath());
            logger.info("   Value    : " + violation.getInvalidValue());
            logger.info("   Template : " + violation.getMessageTemplate());
            logger.info("   Message  : " + violation.getMessage());
        }

        vf.close();
    }
}



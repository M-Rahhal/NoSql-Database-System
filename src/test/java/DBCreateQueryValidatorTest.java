import com.Queries.QueriesManegers.Validators.syntax.DBCreateQueryValidator;
import org.junit.Test;

import static org.junit.Assert.*;

public class DBCreateQueryValidatorTest {
    @Test
    public void validateThrowsException() {
        DBCreateQueryValidator validator ;
        try {
            validator = new DBCreateQueryValidator("create database student");
            assertThrows( Exception.class , ()->validator.isValidQuery());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void returnsInvalid() {
        DBCreateQueryValidator validator ;
        try {
            validator = new DBCreateQueryValidator("create database student name,number");
            assertFalse(validator.isValidQuery());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Test
    public void Valid() {
        DBCreateQueryValidator validator ;
        try {
            validator = new DBCreateQueryValidator("create database student name,id");
            assertTrue(validator.isValidQuery());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
package org.uberfire.ext.editor.commons.backend.validation;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;

public class DefaultFileNameValidatorTest {

    private final String[] names = {"`", "\"", "?", "*", "/", "\\", "<", ">", "|", ":", "\n", "\r", "\t", "\0", "\f"};
    private final String[] emptyNames = {"", " "};

    private final DefaultFileNameValidator validator = new DefaultFileNameValidator();

    @Test
    public void verifyValidFilenamePasses() {
        assertTrue(validator.isValid("some VALID name.drl"));
    }

    @Test
    public void testInvalidChars() {
        for (String filename : names) {
            doTestFor(filename);
            doTestFor("prefix" + filename);
            doTestFor(filename + "suffix");
            doTestFor("prefix" + filename + "suffix");
        }
    }

    @Test
    public void testEmptyFilename() {
        for (String filename : emptyNames) {
            doTestFor(filename);
        }
    }

    private void doTestFor(String filename) {
        String desc = "tested filename: '" + filename + "'";
        assertNotNull(desc, validator.isValid(filename));
        assertFalse(desc, validator.isValid(filename));
    }
}

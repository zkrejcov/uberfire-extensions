package org.uberfire.ext.editor.commons.backend.service;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import javax.enterprise.inject.Instance;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Spy;
import org.uberfire.ext.editor.commons.backend.validation.FileNameValidator;

@RunWith(MockitoJUnitRunner.class)
public class ValidationServiceImplTest {

    protected FileNameValidator validator = mock(FileNameValidator.class);

    @Spy
    private MockInstance fileNameValidatorBeans = new MockInstance();

    @InjectMocks
    private ValidationServiceImpl service;

    @Test
    public void verifyValidationCalled() {
        String input = "some text";
        when(validator.accept(input)).thenReturn(Boolean.TRUE);
        service.configureValidators();
        service.isFileNameValid(input);
        verify(validator).isValid(input);
    }

    public class MockInstance implements Instance<FileNameValidator> {

        private final List<FileNameValidator> items = Arrays.asList(validator);

        @Override
        public Iterator<FileNameValidator> iterator() {
            return items.iterator();
        }

        @Override
        public Instance<FileNameValidator> select(Annotation... antns) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public <U extends FileNameValidator> Instance<U> select(Class<U> type, Annotation... antns) {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isUnsatisfied() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public boolean isAmbiguous() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public FileNameValidator get() {
            throw new UnsupportedOperationException("Not supported yet.");
        }

        @Override
        public void destroy(FileNameValidator t) {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }
}

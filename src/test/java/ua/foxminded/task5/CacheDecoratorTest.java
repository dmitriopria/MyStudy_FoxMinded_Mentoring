package ua.foxminded.task5;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoSettings;

@MockitoSettings
class CacheDecoratorTest {
    @Mock
    private CharCounter counterMock = Mockito.mock(CharCounter.class);
    @InjectMocks
    private CacheDecorator decorator = new CacheDecorator(counterMock);

    @Test
    void charCounter() {
        Mockito.when(counterMock.countCharacters("q")).thenReturn("\"q\" - 1");
        decorator.countCharacters("q");
        decorator.countCharacters("q");
        decorator.countCharacters("q");
        Assertions.assertEquals("\"q\" - 1", decorator.countCharacters("q"));
        Mockito.verify(counterMock, Mockito.times(4)).countCharacters("q");
        Mockito.verifyNoMoreInteractions(counterMock);
    }
}

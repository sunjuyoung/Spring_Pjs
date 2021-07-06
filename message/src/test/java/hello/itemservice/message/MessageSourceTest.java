package hello.itemservice.message;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.MessageSource;
import org.springframework.context.NoSuchMessageException;

import java.util.Locale;

import static org.assertj.core.api.Assertions.*;

@SpringBootTest
public class MessageSourceTest {

    @Autowired
    MessageSource messageSource;

    @Test
    public void helloMessage(){
        String result = messageSource.getMessage("hello",null, Locale.US);
        assertThat(result).isEqualTo("hello");

    }
    @Test
    public void helloMessage1(){
        String result = messageSource.getMessage("hello",null, null);
        assertThat(result).isEqualTo("안녕");
    }

    @DisplayName("에러 발생")
    @Test
    public void noMessage1(){
        //String result = messageSource.getMessage("hello",null, null);
        assertThatThrownBy(()->{
            messageSource.getMessage("no-code",null, null);
        }).isInstanceOf(NoSuchMessageException.class);
    }

    @DisplayName("디폴트 메시지")
    @Test
    public void noMessage2(){
        String result = messageSource.getMessage("no-code",null ,"기본 메세지",null);
        assertThat(result).isEqualTo("기본 메세지");
    }

    @DisplayName("메세지 argument")
    @Test
    public void argMessage(){
        String result = messageSource.getMessage("hello.name",new Object[]{"Spring"} ,null);
        assertThat(result).isEqualTo("안녕 Spring");
    }

    @DisplayName("메세지 argument")
    @Test
    public void defaultLang(){
        String result = messageSource.getMessage("hello.name",new Object[]{"Spring"} ,null);
        assertThat(result).isEqualTo("안녕 Spring");
    }



}

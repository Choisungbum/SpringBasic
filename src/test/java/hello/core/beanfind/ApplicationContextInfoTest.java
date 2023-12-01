package hello.core.beanfind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {
    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName(" 모든 빈 출력하기 ")
    void findAllBean () { // Junit 5 부터 접근제어자 생략 가능
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            Object bean = ac.getBean(beanDefinitionName);
            System.out.println("name = " + beanDefinitionName + "Object = " + bean);
        }
    }

    @Test
    @DisplayName(" 애플리케이션 빈 출력하기 ")
    void findApplicationBean () {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String beanDefinitionName : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(beanDefinitionName);// 빈에 대한 정보들 , 빈 하나하나에 대한 메타데이터

            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈, 애플리케이션을 개발하기 위해서 등록한 빈 OR 외부 라이브러리 관련
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(beanDefinitionName);
                System.out.println("name = " + beanDefinitionName + "Object = " + bean);
            }

        }
    }
}

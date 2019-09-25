package dao;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *  App版本Spring容器查看清单
 *  @author: Gary SU
 *  @Date: 2019/9/22 7:25
 *  @Description: 加载spring配置文件，查看容器对象清单
 */
public class springContainerTest {
    public static void main(String args[]) {
        ApplicationContext context=new ClassPathXmlApplicationContext("classpath:applicationContext.xml");
        String[] str=context.getBeanDefinitionNames();
        for (String string : str) {
            System.out.println("..."+string);
        }

        //获取指定类型的对象
        //Object obj= context.getType("ExamClassServiceImpl");
        Object obj2 = context.getBean("examClassServiceImpl");
        System.out.println("----"+obj2);

    }
}

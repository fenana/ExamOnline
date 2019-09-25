package dao;

import java.util.List;

import javax.annotation.Resource;

import com.exam.dao.IArticlesDao;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import com.exam.model.Articles;
import junit.framework.Assert;

/**
 * IArticlesDao 测试类 JUnit
 * 
 * @author Gary
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath*:applicationContext.xml"})
//知道了mapper在那里，并能把mapper转换成java（反射技术）
public class ArticlesDaoTest {
	
	@Autowired
    IArticlesDao dao;
	
	public static void main(String[] args) {
		//写测试代码  谁都会的测试方法
	}
	
	@Before
	public void init() {
		//运行其它方法时，这个方法先运行
	}

	@Test
	public void testAdd() {
		//写测试代码
		Articles model = new Articles();
		model.setTitle("一个好人。。。。");
		model.setContent("测试数据");
		
		//IArticlesDao dao = new ?;
		int result = dao.add(model);
		
		Assert.assertEquals(1, result);
	}

	@Test
	public void testUpdate() {
		
		Articles model = new Articles();
		model.setArticleId(1);
		model.setTitle("hello");
		model.setContent("world....");

		dao.createNewTable();
		int result = dao.update(model);
		Assert.assertEquals("断言数据修改结果一定为1！", 1, result); 

	}

	@Test
	public void testDelete() {
		int result = dao.delete(2);
		Assert.assertEquals("断言数据修改结果一定为1！", 1, result); 
	}

	@Test
	public void testFindAll() {

		List<Articles> items = dao.findAll();
		
		Assert.assertEquals("断言数据查询结果一定为2条！", 2, items.size()); 
	}

	@Test
	public void testFindById() {
		Articles model = dao.findById(1);
		Assert.assertEquals("断言文章编号", Integer.valueOf(1), model.getArticleId());
		Assert.assertEquals("断言文章标题", "hello", model.getTitle()); 
		Assert.assertEquals("断言文章内容", "world", model.getContent()); 
	}

}

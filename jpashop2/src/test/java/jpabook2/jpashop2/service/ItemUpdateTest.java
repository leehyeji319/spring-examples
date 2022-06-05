package jpabook2.jpashop2.service;

import javax.persistence.EntityManager;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jpabook2.jpashop2.domain.item.Book;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ItemUpdateTest {

	@Autowired
	EntityManager em;

	@Test
	public void updateTest() throws Exception {
	    Book book = em.find(Book.class, 1L);

		//TX
		book.setName("asdfadf");

		//변경 감지 == dirty checking
		//TX commit

	}
}

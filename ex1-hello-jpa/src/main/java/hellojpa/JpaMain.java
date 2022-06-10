package hellojpa;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();
		//code

		try {
			//비영속상태
			// Member member = new Member();
			// member.setId(101L);
			// member.setName("HelloJPA");

			//영속
			// System.out.println("=== BEFORE ===");
			// em.persist(member); //이 때 DB에 저장되지 않음
			// System.out.println("=== AFTER ===");

			Member findMember = em.find(Member.class, 101L);
			Member findMember2 = em.find(Member.class, 101L);

			System.out.println("result = " + (findMember == findMember2));

			// System.out.println("findMember.id = " + findMember.getId());
			// System.out.println("findMember.name = " + findMember.getName());
			//select query가 나가지 않음

			tx.commit(); //커밋을 꼭해줘야함 안하면 반영이 안돼
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close(); //자원을 다 쓰면 꼭 반환해줘야한다
		}

		emf.close();
	}
}

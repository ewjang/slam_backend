package org.slam.slam_backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;



@SpringBootApplication
public class SlamBackendApplication {

    public static void main(String[] args) {
        SpringApplication.run(SlamBackendApplication.class, args);
        
        
        
        /* 이전꺼
        //SpringApplication.run(SlamBackendApplication.class, args);

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("slam");
        EntityManager em = emf.createEntityManager();
        EntityTransaction tx= em.getTransaction();

        tx.begin();

        try {
            Major major =new Major("Computer Science","College of Enginerring");
            em.persist(major);

            Student stu = new Student("kim", "3");
            stu.setMajor(major);

            em.persist(stu);
            em.flush();
            //em.clear();

            Student foundStudent = em.find(Student.class, stu.getStudentId());
            //System.out.println(foundStudent);
            System.out.println(foundStudent.getMajor().getName());

            //Major foundMajor = em.find(Major.class, foundStudent.getMajorId());
            //System.out.println(foundMajor);

            //CustomerEntity cu = new CustomerEntity(4, "홍길동", new Date());
            //em.persist(cu); //영속 객체로 등록 ,   insert 쿼리 x , 1차 캐시 등록 o
            //em.flush();
            tx.commit(); // 쿼리 실행
        }catch (Exception e) {
            tx.rollback();
        } finally {
            em.close();
        }
        */
    }

}

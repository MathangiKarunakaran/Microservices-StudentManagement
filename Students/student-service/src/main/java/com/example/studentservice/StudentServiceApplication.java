package com.example.studentservice;

import java.util.stream.Stream;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@SpringBootApplication
public class StudentServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentServiceApplication.class, args);
		
		
	}
	 @Bean
     ApplicationRunner init(StudentRepository repository) {
         return args -> {
             Stream.of("Mathangi", "John", "Siri", "Tara", "Diara",
                     "Kamal", "Nimal", "Krish", "Yu").forEach(name -> {
                 repository.save(new Student(name));
             });
             repository.findAll().forEach(System.out::println);
         };
 }

}

@Data
@NoArgsConstructor
@Entity
class Student {

    public Student(String name) {
        this.name = name;
    }

    @Id
    @GeneratedValue
    private Long id;

    @NonNull
    private String name;
}

@RepositoryRestResource
interface StudentRepository extends JpaRepository<Student, Long> {
}

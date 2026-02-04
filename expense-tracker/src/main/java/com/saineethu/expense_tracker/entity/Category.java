package com.saineethu.expense_tracker.entity;



import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "categories")
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(nullable = false, unique = true)
    private String name;


    //protected Category() {}

    public Category(String name){
        this.name = name;
    }

   // public UUID getId(){
   //     return id;
    //}

   // public String getName(){
   //     return name;
   // }


}

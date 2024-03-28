package com.godcare.api.entity;

import com.godcare.common.dto.ResisterProductRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@SQLDelete(sql = "UPDATE product SET is_deleted = true WHERE id = ?")
@Table(name = "product")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "main_img")
    private String mainImg;

    @Column(name = "category_id")
    private Long categoryId;

    @CreationTimestamp
    @Column(name = "time_created", nullable = false, updatable = false)
    private Instant timeCreated;

    @UpdateTimestamp
    @Column(name = "time_updated", insertable = false)
    private Instant timeUpdated;

    @ColumnDefault(value = "false")
    @Column(name = "is_deleted")
    private Boolean isDeleted;



    public void assignId(Long id) {
        this.id = id;
    }
    public static Product from(ResisterProductRequest request) {
        String mainImg = request.getMainImg();
        Long categoryId = request.getCategoryId();
        Long id = null;
        Instant timeCreated = Instant.now();
        Boolean isDeleted = false;
        return new Product(id, mainImg, categoryId, timeCreated, null, isDeleted);
    }

    public void update(String mainImg, Long categoryId) {
      this.mainImg = mainImg;
      this.categoryId = categoryId;
    }
}

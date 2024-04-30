package com.godcare.api.entity;

import com.godcare.common.dto.FileResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.time.Instant;

@Table(name = "product_photo")
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class ProductPhoto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Lob
    @Column(name = "original_name")
    private String originalName;

    @Lob
    @Column(name = "image_url")
    private String imgUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "product_id", nullable = false)
    private Product product;

    @CreationTimestamp
    @Column(name = "time_created", nullable = false, updatable = false)
    private Instant timeCreated;

    @UpdateTimestamp
    @Column(name = "time_updated", insertable = false)
    private Instant timeUpdated;

    @ColumnDefault(value = "false")
    @Column(name = "is_deleted")
    private Boolean isDeleted;

    public static ProductPhoto from(FileResponse file, Product product) {
        Long id = null;
        String originalName = file.getOriginalFileName();
        String imgUrl = file.getUploadFileUrl();
        Product pro = product;
        Instant timeCreated = Instant.now();
        Instant timeUpdated = null;
        Boolean isDeleted = false;
        return new ProductPhoto(id, originalName, imgUrl, pro, timeCreated, timeUpdated, isDeleted);
    }
}
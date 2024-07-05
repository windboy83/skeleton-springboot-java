package com.sample.domain.mongodb.product;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.CompoundIndex;
import org.springframework.data.mongodb.core.index.CompoundIndexes;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@CompoundIndexes({
        @CompoundIndex(name = "IDX_PRODUCT_01", def = "{'id' : 1, 'isUse' : 1}"),
        @CompoundIndex(name = "IDX_PRODUCT_02", def = "{'isUse' : 1, 'updDate' : 1}")
})
@Document(collection = "product")
public class ProductDocument {
    @Id
    private String id;

    @Indexed
    private int brandCd;
    @Indexed
    private String vendorCd;
    @Indexed
    private String mediumCd;
    @Indexed
    private int categoryCd;
    @Indexed
    private String mediumCd_categoryCd;
    @Indexed
    private String vendorCd_brandCd;
    @Indexed
    private boolean isUse;
    @Indexed
    private LocalDateTime updDate;

    @Builder
    public ProductDocument(String id, int brandCd, String vendorCd, String mediumCd, int categoryCd
            , String mediumCd_categoryCd, String vendorCd_brandCd, boolean isUse, LocalDateTime updDate) {
        this.id = id;
        this.brandCd = brandCd;
        this.vendorCd = vendorCd;
        this.mediumCd = mediumCd;
        this.categoryCd = categoryCd;
        this.mediumCd_categoryCd = mediumCd_categoryCd;
        this.vendorCd_brandCd = vendorCd_brandCd;
        this.isUse = isUse;
        this.updDate = updDate;
    }
}


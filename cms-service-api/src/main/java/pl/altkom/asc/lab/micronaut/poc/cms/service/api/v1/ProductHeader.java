package pl.altkom.asc.lab.micronaut.poc.cms.service.api.v1;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProductHeader {
    private String code;
    private String title;
    private String shortDescription;
    private String mainPictureUrl;
}
